package gae.editor;

import java.util.Arrays;

import gae.openingView.UIObject;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This represents the main editor that would open up in a new tab when creating a new game object.
 * It will display the image, basic editable properties such as name, and more advanced features
 * such as weapons (for towers), size, etc.
 * 
 * Implements UIObject to be a part of the Mediator pattern for the authoring environment.
 * 
 * @author Brandon Choi
 *
 */

public class ObjectEditor extends Application implements UIObject{
    
    private BorderPane objectEditor;
    private VBox rightContainer, leftContainer;
    private Pane imageBox;
    private SimpleEditor simpleEditor;

    public ObjectEditor () {
        objectEditor = new BorderPane();
        rightContainer = new VBox();
        leftContainer = new VBox();
        imageBox = new Pane();
        simpleEditor = new SimpleEditor();
        setUpContainers();
    }
    
    @Override
    public Node getObject () {
        return objectEditor;
    }
    
    private void setUpContainers() {
        objectEditor.setRight(rightContainer);
        leftContainer.getChildren().addAll(imageBox, simpleEditor.getObject());
        objectEditor.setLeft(leftContainer);
    }

    public static void main (String[] args) {
        launch(args);
    }

    @Override
    public void start (Stage arg0) throws Exception {
        SimpleEditor se = new SimpleEditor();
        
        Group root = new Group();
        root.getChildren().add(se.getObject());
        
        Stage s = new Stage();
        Scene scene = new Scene(root, 500, 500);
        s.setScene(scene);
        s.show();
    }
}
