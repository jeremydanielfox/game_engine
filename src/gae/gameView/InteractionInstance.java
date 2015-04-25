package gae.gameView;

import gae.listView.LibraryData;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


/**
 * Defines a single interaction between game objects
 *
 *
 * @author Brandon Choi
 *
 */

/*
 * label1 [collide || no collide] label2 -need access to library view for labels / objects -create
 * tree of hierarchy for labels
 */

public class InteractionInstance {

    private InteractionData myInteractionData;
    private LibraryData myLibraryData;
    private HBox container;
    private DropDown interactionType;
    private ObjectContainer box1, box2;
    private Button create;

    /*
     * TODO: pull classes via reflection and then map the options in the interactionType to specific
     * interaction classes in the engine
     */

    public InteractionInstance (InteractionData data, LibraryData library) {
        myInteractionData = data;
        myLibraryData = library;
        container = new HBox(80);
        interactionType = new DropDown("CHOOSE INTERACTION", getInteractions());
        box1 = new ObjectContainer();
        box2 = new ObjectContainer();
        create = new Button("CREATE");
        createButtonFunction();
        createInteraction();
    }

    /**
     * Takes the interaction map from myInteractionData and uses the keyset to pull all of the
     * interaction labels
     *
     * @return
     */
    private List<String> getInteractions () {
        List<String> interactions = new ArrayList<>();
        myInteractionData.getInteractionMap().keySet().forEach(e -> {
            interactions.add(e);
        });
        return interactions;
    }

    private void createButtonFunction () {
        create.setOnMousePressed(e -> {
            /*
             * TODO
             */
        });
    }

    private void createInteraction () {
        container.getChildren().addAll(box1.getContainer(), interactionType.getDropDown(),
                                       box2.getContainer(), create);
    }

    public Node getInteractionSetter () {
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
            container.setAlignment(Pos.CENTER);
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
        private Button adder;
        private HBox addBox;
        private LabelCheckList myChecker;

        public ObjectContainer () {
            container = new VBox();
            selected = new VBox();
            scroller = new ScrollPane();
            adder = new Button();
            addBox = new HBox(15);
            Text addText = new Text("Add Labels");
            myChecker = new LabelCheckList(myLibraryData.getLabelList());

            container.setId("interactionBox");

            addBox.getChildren().addAll(addText, adder);
            ImageView buttonGraphic = new ImageView("/images/plus_sign.jpg");
            adder.setGraphic(buttonGraphic);
            buttonGraphic.setFitWidth(25);
            buttonGraphic.setFitHeight(25);
            createObjectContainer();
        }

        /**
         * creates the object container by adding nodes to the VBox and setting up functionalities
         * such as button pressing
         */
        private void createObjectContainer () {
            scroller.setContent(container);
            adder.setOnMouseClicked(e -> {
                myChecker.showCheckList();
            });
            container.getChildren().addAll(selected, addBox);
        }

        public Node getContainer () {
            return scroller;
        }
    }
}
