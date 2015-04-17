package gae.gameView;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * table that will allow the user to define specific interactions between game objects such as what
 * happens when a tower and enemy collide, etc.
 * 
 * @author Brandon Choi
 *
 */

public class InteractionTable extends Application{

    private static final String ADD_TEXT = "Add New Interaction";
    
    private BorderPane container;
    private ScrollPane scroller;
    private VBox content;
    private Button adder;
    private List<Interaction> interactions;

    public InteractionTable () {
        container = new BorderPane();
        scroller = new ScrollPane();
        container.setCenter(scroller);
        adder = new Button(ADD_TEXT);
        container.setTop(adder);
        interactions = new ArrayList<>();
        content = new VBox();
        scroller.setContent(content);
        setUpButtons();
    }

    private void setUpButtons () {
        adder.setOnMouseClicked(e -> {
            Interaction i = new Interaction();
            interactions.add(i);
            content.getChildren().add(i.getInteractionSetter());
        });
    }

    
    public static void main (String[] args) {
        launch(args);
    }

    @Override
    public void start (Stage arg0) throws Exception {
        Stage myStage = new Stage();
        myStage.setWidth(Main.SCREEN_WIDTH);
        myStage.setHeight(Main.SCREEN_HEIGHT);
        myStage.setScene(new Scene(container));
        myStage.show();
    }
}
