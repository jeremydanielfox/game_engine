package engine.shop;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.event.WeakEventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import View.ViewUtil;
import engine.gameobject.GameObject;
import engine.gameobject.PointSimple;
import engine.shop.ShopModelSimple.ItemInfo;


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
    private Pane pane; // TODO: use View to call method instead

    private FlowPane shopIcons;
    private StackPane infoBox;

    private BooleanProperty infoBoxActive;

    public ShopView (ShopModel model, Pane pane) {
        this.model = model;
        this.pane = pane;
        infoBoxActive = new SimpleBooleanProperty(false);
        initialize();
    }

    private void initialize () {
        VBox shopContainer = new VBox();
        shopContainer.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
        shopContainer.setMaxWidth(SHOP_WIDTH);
        shopIcons = new FlowPane();
        infoBox = new StackPane();

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
        icons.forEach(gameObjectIcon -> {
            gameObjectIcon.setOnMouseEntered(mouseEvent -> displayGameObjectInfo(gameObjectIcon));
            gameObjectIcon.setOnMouseExited(mouseEvent -> infoBox.getChildren().clear());
            gameObjectIcon.setOnMouseClicked(mouseEvent -> {
                TransitionGameObject transition =
                        model.getTransitionGameObject(gameObjectIcon.getName());
                Point2D location = ViewUtil.getMouseSceneLoc(mouseEvent, transition.getNode());
                initializeTransition(model.getTransitionGameObject(gameObjectIcon.getName()),
                                     location);
            });

        });
        shopIcons.getChildren().addAll(icons);
    }

    private void displayGameObjectInfo (ItemGraphic icon) {
        VBox base = new VBox();
        base.setSpacing(10);
        base.setPadding(new Insets(10, 10, 10, 10));
        base.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT,
                                                             null, null)));

        Map<ItemInfo, Label> labels = makeInfoLabels(model.getInfo(icon.getName()));
        base.getChildren().addAll(labels.values());
        Label name = labels.get(ItemInfo.NAME);
        name.setStyle("-fx-font-weight: bold");
        infoBox.getChildren().add(base);
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
        VBox base = new VBox();
        base.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255, 0.8),
                                                             null, null)));
        Label name = new Label(gameObject.getTag().getName());
        base.getChildren().add(name);

        List<ItemGraphic> upgrades = model.getUpgradeGraphics(gameObject);
        for (ItemGraphic upgrade : upgrades) {
            base.getChildren().add(makeUpgradePanel(upgrade));
        }
        infoBox.getChildren().add(base);
    }

    private StackPane makeUpgradePanel (ItemGraphic upgrade) {
        StackPane upgradePanel = new StackPane();
        upgradePanel.setBackground(new Background(new BackgroundFill(Color.LAWNGREEN, null, null)));

        VBox entries = new VBox();
        Map<ItemInfo, Label> labels = makeInfoLabels(model.getInfo(upgrade.getName()));
        HBox first = new HBox();
        first.getChildren().addAll(upgrade.getGraphic(), labels.get(ItemInfo.NAME));
        Label second = labels.get(ItemInfo.DESCRIPTION);
        Label third = labels.get(ItemInfo.PRICE);
        third.setTextFill(Color.YELLOW);

        entries.getChildren().addAll(first, second, third);
        upgradePanel.getChildren().add(entries);

        return upgradePanel;
    }

    private void bindCursor (Point2D initial, Node node) {
        Node result = ViewUtil.bindCursor(node, pane, initial, KeyCode.ESCAPE, true);
        pane.getChildren().add(result);
    }

    private void initializeTransition (TransitionGameObject transition, Point2D initial) {
        Node transNode = transition.getNode();
        bindCursor(initial, transNode);

        transNode.setOnMouseMoved(event -> {
            PointSimple current = new PointSimple(event.getSceneX(), event.getSceneY());
            transition.setRangeCircleColor(model.checkPlacement(transition.getName(),
                                                                current));
        });

        transNode.setOnMouseClicked(event -> {
            PointSimple current = new PointSimple(event.getSceneX(), event.getSceneY());
            if (model.purchaseGameObject(transition.getName(), current)){
                ViewUtil.removeNode(transNode);
                // new TestTower
                // onClicked - show radius
                // onClicked - show available upgrades -- call displayUpgrades
                
            };
        });
    }
}
