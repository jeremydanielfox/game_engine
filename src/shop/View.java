package shop;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


/**
 * View of the shop. Right now it is being tested using its own stage/launch().
 * 
 * @author Nathan Prabhu
 *
 */
public class View extends Application {
    
    private final static int SCENE_WIDTH = 400;
    private final static int SCENE_HEIGHT = 400;
    private final static int SHOP_WIDTH = 160;
    private final static int ITEM_COUNT = 15;

    private BorderPane pane;

    @Override
    public void start (Stage stage) {
        pane = new BorderPane();
        Scene scene = new Scene(pane, SCENE_WIDTH, SCENE_HEIGHT);
        stage.setScene(scene);
        FlowPane shopDisplay = new FlowPane();
        shopDisplay.setHgap(5);
        shopDisplay.setVgap(5);
        pane.setRight(shopDisplay);
        shopDisplay.setMaxWidth(SHOP_WIDTH);
        shopDisplay.backgroundProperty()
                .set(new Background(new BackgroundFill(Color.GRAY, null, null)));

        // add Items
        List<Node> items = new ArrayList<Node>();
        for (int i = 0; i < ITEM_COUNT; i++) {
            ItemGraphic item = new ItemGraphic();
            item.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                TransitionTower transitionTower = new TransitionTower();
                transitionTower.getView()
                        .relocate(mouseEvent.getSceneX() - item.getRadius(),
                                  mouseEvent.getSceneY() - item.getRadius());
                pane.getChildren()
                        .add(CursorBinder.bindCursor(transitionTower.getView(),
                                                     pane.getScene(), KeyCode.ESCAPE));

            });
            items.add(item);
        }
        shopDisplay.getChildren().addAll(items);
        stage.show();
    }

    public static void main (String[] args) {
        launch(args);
    }

}
