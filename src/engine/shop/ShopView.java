package engine.shop;

import engine.gameobject.GameObject;
import engine.gameobject.PointSimple;
import engine.shop.ShopModelSimple.ItemInfo;
import engine.shop.ShopModelSimple.UpgradeGraphic;
import gameworld.GameWorld;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import View.ViewUtil;


/**
 * View of the shop. Contains a node that can be directly added to the main View.
 *
 * NOTE: layout of the shop is being hard-coded now, to resemble bloons. Future refactoring will
 * factor out the separate nodes in the container to allow for more customization.
 *
 * @author Nathan Prabhu
 *
 */

// TODO: use CSS
// TODO: add "active" property to StackPane, alternatively listener
public class ShopView extends Parent {

    private static final double SHOP_WIDTH = 160;
    private static final double INFO_HEIGHT = 250;

    private ShopModel model;
    private Scene scene;
    private Pane pane;

    private Stack<Node> infoBoxStack = new Stack<>();

    private FlowPane shopIcons;
    private StackPane infoBox;
    private GameObjectSelector selector;
    private GameWorld world;

    public ShopView (ShopModel model, Pane pane) {
        this.model = model;
        this.pane = pane;
        initialize();
    }

    private void initialize () {
        VBox shopContainer = new VBox();
        shopContainer.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
        shopContainer.setMaxWidth(SHOP_WIDTH);
        shopIcons = new FlowPane();
        infoBox = new StackPane();
        selector =
                new GameObjectSelector(this::displayUpgrades, this::removeFromInfoBox, pane);

        shopContainer.getChildren().addAll(shopIcons, infoBox);

        // add Icons
        addIcons();

        // initialize InfoBox
        infoBox.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255, 0.8),
                                                                null, null)));
        infoBox.setMinHeight(INFO_HEIGHT);

        getChildren().add(shopContainer);
    }

    // TODO this should only be for GameObject ItemGraphics
    // TODO probably shouldn't hard-code MouseClick as command type
    private void addIcons () {
        shopIcons.setHgap(5);
        shopIcons.setVgap(5);
        List<ItemGraphic> icons = model.getItemGraphics();
        icons.forEach(icon -> {
            Node base = makeGameObjectInfo(icon);
            icon.setOnMouseEntered(mouseEvent -> {
                addToInfoBox(base);
                // clearInfoBox();
                // infoBox.getChildren().add(base);
            });
            icon.setOnMouseExited(mouseEvent -> removeFromInfoBox());
            icon.setOnMouseClicked(mouseEvent -> {
                RangeDisplay transition =
                        model.getRangeDisplay(icon.getName());
                Point2D location = ViewUtil.getMouseSceneLoc(mouseEvent, transition.getNode());
                initializeTransition(model.getRangeDisplay(icon.getName()),
                                     location);
            });

        });
        shopIcons.getChildren().addAll(icons);
    }

    private void addToInfoBox (Node base) {
        infoBoxStack.push(base);
        readInfoBoxStack();
    }

    private void removeFromInfoBox () {
        if (!infoBoxStack.isEmpty()) {
            infoBoxStack.pop();
        }
        readInfoBoxStack();
    }

    private void readInfoBoxStack () {
        infoBox.getChildren().clear();
        if (!infoBoxStack.isEmpty()) {
            infoBox.getChildren().add(infoBoxStack.peek());
        }
    }

    private Node makeGameObjectInfo (ItemGraphic icon) {
        VBox base = new VBox();
        base.setSpacing(10);
        base.setPadding(new Insets(10, 10, 10, 10));
        base.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT,
                                                             null, null)));

        Map<ItemInfo, Label> labels = makeInfoLabels(model.getInfo(icon.getName()));
        base.getChildren().addAll(labels.values());
        Label name = labels.get(ItemInfo.NAME);
        name.setStyle("-fx-font-weight: bold");
        return base;
    }

    private Map<ItemInfo, Label> makeInfoLabels (Map<ItemInfo, String> infoMap) {
        Map<ItemInfo, Label> result = new EnumMap<ItemInfo, Label>(ItemInfo.class);
        result.put(ItemInfo.NAME, new Label(infoMap.get(ItemInfo.NAME)));
        result.put(ItemInfo.DESCRIPTION, new Label(infoMap.get(ItemInfo.DESCRIPTION)));
        result.put(ItemInfo.PRICE,
                   new Label(String.format("Cost: %s", infoMap.get(ItemInfo.PRICE))));
        result.values().forEach(label -> {
            label.setWrapText(true);
            label.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT,
                                                                  null, null)));
        });

        return result;
    }

    /**
     * To be called by View when gameobject is selected during game.
     *
     * @param gameObject selected
     */
    public void displayUpgrades (GameObject gameObject) {
        removeFromInfoBox();
        VBox base = new VBox();
        base.setSpacing(10);
        base.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255, 0.8),
                                                             null, null)));
        Label name = new Label(gameObject.getName());
        base.getChildren().add(name);

        List<UpgradeGraphic> upgrades = model.getUpgradeGraphics(gameObject);
        for (UpgradeGraphic upgrade : upgrades) {
            base.getChildren().add(makeUpgradePanel(upgrade));
        }

        Button sellButton = new Button(String.format("Sell for: %d",
                                                     Math.round(gameObject.getValue())));
        base.getChildren().add(sellButton);
        sellButton.setOnAction(event -> model.sellGameObject(gameObject));
        addToInfoBox(base);
    }

    private StackPane makeUpgradePanel (UpgradeGraphic upgrade) {
        ItemGraphic graphic = upgrade.getGraphic();

        StackPane upgradePanel = new StackPane();
        upgradePanel.setOnMouseEntered(event -> upgradePanel.setCursor(Cursor.HAND));
        upgradePanel.setOnMouseClicked(event -> {
            if (!upgrade.isFinal() && upgrade.canAfford()) {
                model.purchaseUpgrade(graphic.getName(), this::displayUpgrades);
            }
        });

        Paint panelColor = setPanelColor(upgrade);

        upgradePanel.setBackground(new Background(new BackgroundFill(panelColor, null, null)));

        VBox entries = new VBox();
        Map<ItemInfo, Label> labels = makeInfoLabels(model.getInfo(graphic.getName()));
        HBox first = new HBox();
        first.getChildren().addAll(graphic, labels.get(ItemInfo.NAME));
        Label second = labels.get(ItemInfo.DESCRIPTION);
        Label third = (upgrade.isFinal()) ?
                                         new Label("Already bought") : labels.get(ItemInfo.PRICE);
        third.setTextFill(Color.YELLOW);

        entries.getChildren().addAll(first, second, third);
        upgradePanel.getChildren().add(entries);

        return upgradePanel;
    }

    private Paint setPanelColor (UpgradeGraphic upgrade) {
        if (!upgrade.canAfford()) {
            return Color.RED;
        }
        else if (upgrade.isFinal()) {
            return Color.LAWNGREEN;
        }
        else { // normal upgrade
            return Color.MEDIUMSEAGREEN;
        }
    }

    private void bindCursor (Point2D initial, Node node) {
        Node result = ViewUtil.bindCursor(node, pane, initial, KeyCode.ESCAPE, true);
        pane.getChildren().add(result);
    }

    private void initializeTransition (RangeDisplay transition, Point2D initial) {
        Node transNode = transition.getNode();
        bindCursor(initial, transNode);

        transNode.setOnMouseMoved(event -> {
            PointSimple current = new PointSimple(event.getSceneX(), event.getSceneY());
            transition.setRangeCircleColor(model.checkPlacement(transition.getName(),
                                                                current));
        });

        transNode.setOnMouseClicked(event -> {
            PointSimple current = new PointSimple(event.getSceneX(), event.getSceneY());
            EventHandler<MouseEvent> selected = selection -> selectGameObject(selection);
            if (model.purchaseGameObject(transition.getName(), current, selected)) {
                ViewUtil.unbindCursor(pane, transNode);
            }
        });
    }

    private void selectGameObject (MouseEvent event) {
        GameObject selected = model.getObjectFromNode((Node) event.getSource());
        selector.select(selected);
    }
}