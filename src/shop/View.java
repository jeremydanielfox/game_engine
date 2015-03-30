package shop;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * View of the shop. Right now it is being tested using its own stage/launch(). 
 * @author Nathan Prabhu
 *
 */
public class View extends Application {


    private BorderPane pane;

    @Override
    public void start (Stage stage) {
        pane = new BorderPane();
        FlowPane shopDisplay = new FlowPane();
        shopDisplay.setHgap(5);
        shopDisplay.setVgap(5);
        pane.setRight(shopDisplay);
        shopDisplay.setMaxWidth(180);
        shopDisplay.backgroundProperty().set(new Background(new BackgroundFill(Color.GRAY, null,
                                                                               null)));

        // add 9 Circles
        List<Node> items = new ArrayList();
        for (int i = 0; i < 9; i++) {
            ItemGraphic item = new ItemGraphic();
            item.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                TransitionTower transitionTower = new TransitionTower();
                pane.getChildren().add(DraggableTransform.makeDraggable(transitionTower.getView()));
            });
            items.add(item);
            
        }
        shopDisplay.getChildren().addAll(items);

        // group.getChildren().add(makeDraggable(circle.getView()));
        // pane.getChildren().add(makeDraggable(circle.getView()));
        // pane.setRight(makeDraggable(circle.getView()));

        // circle.getView().addEventHandler(MouseEvent.MOUSE_ENTERED,
        // mouseEvent -> {
        // circle.update();
        // System.out.println(String
        // .format("%f, %f",
        // circle.getView().getCenterX(),
        // circle.getView().getCenterY()));
        //
        // });

        Scene scene = new Scene(pane, 400, 400);
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }

}
