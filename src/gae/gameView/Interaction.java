package gae.gameView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Defines a single interaction between game objects
 * 
 * @author Brandon Choi
 *
 */

/*
 * label1 [collide || no collide] label2
 * -need access to library view for labels / objects
 * -create tree of hierarchy for labels
 */

public class Interaction {

    private HBox container;
    private DropDown interactionType;
    private ObjectContainer box1, box2;

    public Interaction () {
        container = new HBox();
        interactionType = new DropDown("Interaction Type", Arrays.asList("Collide", "Do not collide"));
        box1 = new ObjectContainer();
        box2 = new ObjectContainer();
        createInteraction();
    }

    private void createInteraction () {
        container.getChildren().addAll(box1.getContainer(), interactionType.getDropDown(), box2.getContainer());
    }
    
    public Node getInteractionSetter() {
        return container;
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
            container.setId("interactionOptions");
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
        private VBox selected;
        private ScrollPane scroller;
        private List<String> options;
        private Button adder;

        public ObjectContainer() { 
            container = new VBox();
            selected = new VBox();
            container.setId("interactionBox");
            scroller = new ScrollPane();
            options = new ArrayList<>();
            adder = new Button();
            adder.setGraphic(new ImageView("/images/plus_sign.jpg"));
            createObjectContainer();
        }
        
        private void createObjectContainer() {
            scroller.setContent(container);
            adder.setOnMouseClicked(e -> {
                
            });
            container.getChildren().addAll(selected, adder);
        }
        
        public Node getContainer(){
            return scroller;
        }
    }
}
