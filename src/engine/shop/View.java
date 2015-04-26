package engine.shop;

import engine.gameobject.units.UpgradeType;
import engine.gameobject.units.directdamage.DamageBuff;
import engine.gameobject.units.freeze.FreezeBuff;
import engine.gameobject.weapon.Upgrade;
import engine.gameobject.weapon.UpgradeSet;
import engine.gameobject.weapon.range.RangeUpgrade;
import gameworld.GameWorld;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;


/**
 * View of the shop. Right now it is being tested using its own stage/launch().
 *
 * @author Nathan Prabhu and Tom Puglisi
 *
 */
public class View extends Application {

    private final static int SCENE_WIDTH = 600;
    private final static int SCENE_HEIGHT = 600;
    private final static int SHOP_WIDTH = 160;

    private BorderPane pane;

    private ShopModel shopModel;
    private GameWorld world;

    @Override
    public void start (Stage stage) {
        // pane = new BorderPane();
        // Scene scene = new Scene(pane, SCENE_WIDTH, SCENE_HEIGHT);
        // stage.setScene(scene);
        // world = new FixedWorld();
        //
        // // FlowPane contains the entire store. This is what should be moved around.
        // FlowPane shopDisplay = new FlowPane();
        // shopDisplay.setHgap(5);
        // shopDisplay.setVgap(5);
        // pane.setRight(shopDisplay);
        // shopDisplay.setMaxWidth(SHOP_WIDTH);
        // shopDisplay.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
        //
        // // add Icons
        // addIcons(shopDisplay);
        //
        // // add general gameWorld view
        // BorderPane gameView = new BorderPane();
        // pane.setCenter(gameView);
        // gameView.setBackground(new Background(new BackgroundFill(Color.BEIGE, null, null)));
        // gameView.setOnMousePressed(mouseEvent -> {
        // // System.out.println("Relative coordinates: " +
        // // ViewUtilities.normalizePixels(mouseEvent, gameView));
        // GameObject object = new GameObjectSimple();
        // try {
        // world.addObject(object, new PointSimple(mouseEvent.getX(), mouseEvent.getY()));
        // } catch (Exception e) {
        // //TODO HANDLE EXCEPTION
        // }
        // object.getGraphic().getNode().relocate(mouseEvent.getX(), mouseEvent.getY());
        //
        // });
        stage.show();
        Set<Upgrade> test = new UpgradeSet<>();
        test.add(new DamageBuff(5));
        FreezeBuff freeze1 = new FreezeBuff(10);
        freeze1.setBuffType(UpgradeType.COLLISION);
        FreezeBuff freeze2 = new FreezeBuff(100);
        freeze2.setBuffType(UpgradeType.COLLISION);
        FreezeBuff freeze3 = new FreezeBuff(100);
        freeze3.setBuffType(UpgradeType.EXPLOSION);
        test.add(freeze1);
        test.add(freeze2);
        test.add(freeze3);

        test.add(new RangeUpgrade(1));
//        System.out.println(test);
    }

    private void addIcons (FlowPane shopDisplay) {
        Map<String, String> shopImages = new HashMap<String, String>();
        String[] iconImages = new String[] { "/images/Bloons_DartMonkeyIcon.jpg",
        "/images/Bloons_TackShooterIcon.jpg" };
        String[] towerImages = new String[] { "/images/Bloons_DartMonkey.png",
        "/images/Bloons_TackShooter.png" };
        for (int i = 0; i < iconImages.length; i++) {
            shopImages.put(iconImages[i], towerImages[i]);
        }

        List<Node> items = new ArrayList<Node>();
        // for (int i = 0; i < ITEM_COUNT / iconImages.length; i++) {
        // shopImages.forEach( (icon, tower) -> {
        // ItemGraphic item = new ItemGraphic(icon, tower);
        // TransitionGameObject transitionTower = new TransitionGameObject(item.getTower());
        // Node towerNode = transitionTower.getView();
        // item.setOnMouseClicked(mouseEvent -> {
        // addTransitionTower(ViewUtilities.getMouseLocation(mouseEvent, towerNode),
        // towerNode);
        // });
        // towerNode.setOnMouseClicked(mouseEvent -> {
        // System.out.println(mouseEvent);
        // GameObject object = new BuffableUnit();
        // world.addObject(object, new PointSimple(mouseEvent.getSceneX(), mouseEvent.getSceneY()));
        // ViewUtilities.unbindCursor(pane);
        // Point2D location = ViewUtilities.getMouseLocation(mouseEvent, towerNode);
        // towerNode.relocate(location.getX(), location.getY());
        // transitionTower.changeColor();
        // });
        // items.add(item);
        // });
        // }
        shopDisplay.getChildren().addAll(items);
    }

    // private void addTransitionTower (Point2D initial, Node node) {
    // Node bindedTower =
    // ViewUtilities.bindCursor(node, pane, initial, KeyCode.ESCAPE);
    // pane.getChildren().add(bindedTower);
    // }

    public static void main (String[] args) {
        launch(args);
    }

}
