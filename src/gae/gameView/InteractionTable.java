package gae.gameView;

import engine.gameobject.labels.Type;
import gae.listView.LibraryData;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * table that will allow the user to define specific interactions between game objects such as what
 * happens when a tower and enemy collide, etc.
 *
 * @author Brandon Choi
 *
 */

public class InteractionTable {

    private static final String ADD_TEXT = "Add New Interaction";

    private InteractionData myInteractionData;
    private LibraryData myLibraryData;
    private BorderPane container;
    private ScrollPane scroller;
    private VBox content;
    private Button adder;
    private List<InteractionInstance> interactions;
    private ObservableSet<Type> gaeLabels;

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
        gaeLabels = myLibraryData.getLabelSet();
        //addLabelListener();
        scroller.setContent(content);
        setUpButtons();
    }
    
    public Node getTable() {
        return container;
    }
    
    public InteractionData getData() {
        return myInteractionData;
    }

    /**
     * adds a listener to the observable list of labels so that it remains updated whenever a new
     * label is added
     */
    private void addLabelListener () {
        //TODO: do you want this to handle the case where a type is deleted as well?
        gaeLabels.addListener((SetChangeListener.Change<? extends Type> change)->{
            gaeLabels.add(change.getElementAdded());
        });
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
}
