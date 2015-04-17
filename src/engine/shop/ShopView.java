package engine.shop;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import View.ViewUtilities;
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
        // infoBoxActive.addListener((obs, ov, nv) ->{
        // if (!nv){
        // infoBox.getChildren().clear();
        // }
        // });
        shopContainer.getChildren().addAll(shopIcons, infoBox);

        // add Icons
        addIcons();

        // initialize InfoBox
        infoBox.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255, 0.5),
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
            gameObjectIcon.setOnMouseClicked(mouseEvent ->
                    initializeTransition(model.getTransitionGameObject(gameObjectIcon.getName()),
                                         mouseEvent));
        });
        shopIcons.getChildren().addAll(icons);
    }

    private void displayGameObjectInfo (ItemGraphic icon) {
        VBox base = new VBox();
        base.setBackground(new Background(new BackgroundFill(Color.WHITE,
                                                             null, null)));

        Map<ItemInfo, Label> labels = makeInfoLabels(model.getInfo(icon.getName()));
        base.getChildren().addAll(labels.values());
        infoBox.getChildren().add(base);
    }

    private Map<ItemInfo, Label> makeInfoLabels (Map<ItemInfo, String> infoMap) {
        Map<ItemInfo, Label> result = new EnumMap<ItemInfo, Label>(ItemInfo.class);
        result.put(ItemInfo.NAME, new Label(infoMap.get(ItemInfo.NAME)));
        result.put(ItemInfo.PRICE,
                   new Label(String.format("Cost: %s", infoMap.get(ItemInfo.PRICE))));
        result.put(ItemInfo.DESCRIPTION, new Label(infoMap.get(ItemInfo.DESCRIPTION)));
        result.values().forEach(label -> label.setWrapText(true));
        return result;
    }

    /**
     * To be called by View when gameobject is selected during game.
     * 
     * @param gameObject selected
     */
    public void displayUpgrades (GameObject gameObject) {
        VBox base = new VBox();
        base.setBackground(new Background(new BackgroundFill(Color.WHITE,
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
        Node result = ViewUtilities.bindCursor(node, pane, initial, KeyCode.ESCAPE);
        pane.getChildren().add(result);
    }

    private void initializeTransition (TransitionGameObject transition, MouseEvent mouseEvent) {
        Node transNode = transition.getNode();
        Point2D location = ViewUtilities.getMouseLocation(mouseEvent, transNode);
        transNode.relocate(location.getX(), location.getY());
        bindCursor(location, transition.getGraphic());

        transNode.setOnMouseMoved(event2 -> {
            transition.setRangeCircleColor(model.checkPlacement(transition.getName(),
                                                                new PointSimple(location)));
        });

        transNode.setOnMouseClicked(event2 -> {
            model.purchaseGameObject(transition.getName(), new PointSimple(location));
        });
    }
}
