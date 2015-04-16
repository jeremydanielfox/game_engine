package gae.gameView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Defines a single interaction between game objects
 * 
 * @author Brandon Choi
 *
 */

public class Interaction {

    private HBox container;
    private DropDown interactionType;
    private ObjectContainer box1, box2;

    public Interaction () {
        container = new HBox();
        interactionType = new DropDown("Interaction Type", Arrays.asList("1", "2", "3"));
        createInteraction();
    }

    private void createInteraction () {

    }

    /**
     * Comprised of a label and a combobox with options for the user to select from
     * 
     * @author Brandon Choi
     *
     */
    private class DropDown {

        private VBox container;
        private Label label;
        private ComboBox choices;

        public DropDown (String n, List<String> options) {
            container = new VBox();
            label = new Label(n);
            choices = new ComboBox();
            createDropDown(options);
        }

        private void createDropDown (List<String> options) {
            options.forEach(e -> choices.getItems().add(e));
            container.getChildren().addAll(label, choices);
        }

        public Node getDropDown () {
            return container;
        }
    }

    /**
     * Holds objects that are selected between interactions
     * 
     * @author Brandon Choi
     *
     */
    private class ObjectContainer {
        
        private VBox container;
        private ScrollPane scroller;
        private List<String> options;

        public ObjectContainer() { 
            container = new VBox();
            scroller = new ScrollPane();
            options = new ArrayList<>();
        }
        
        private void createObjectContainer() {
            
        }
        
        public Node getContainer(){
            return scroller;
        }
    }
}
