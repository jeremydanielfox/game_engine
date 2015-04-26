package gae.gameView;

import gae.listView.LibraryData;
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

public class InteractionTable extends Application {

    private static final String ADD_TEXT = "Add New Interaction";

    private static InteractionTable instance;

    private InteractionData myInteractionData;
    private LibraryData myLibraryData;
    private BorderPane container;
    private ScrollPane scroller;
    private VBox content;
    private Button adder;
    private List<InteractionInstance> interactions;
    
    /*
     * use observable list from library data. get labels and add listener. 
     */

    public InteractionTable () {
        myInteractionData = new InteractionData();
        myLibraryData = LibraryData.getInstance();
        container = new BorderPane();
        scroller = new ScrollPane();
        container.setCenter(scroller);
        adder = new Button(ADD_TEXT);
        container.setTop(adder);
        interactions = new ArrayList<>();
        content = new VBox(35);
        scroller.setContent(content);
        setUpButtons();
    }

    /**
     * implements the Singleton design pattern in order to keep the InteractionTable consistent
     * throughout the entire GAE
     *
     * @return
     */
    public static synchronized InteractionTable getInstance () {
        if (instance == null) {
            instance = new InteractionTable();
        }
        return instance;
    }

    /**
     * Sets up Buttons and their pressed functions
     */
    private void setUpButtons () {
        adder.setOnMouseClicked(e -> {
            InteractionInstance i = new InteractionInstance(myInteractionData, myLibraryData);
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
