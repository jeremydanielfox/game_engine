package gae.editor;

import engine.gameobject.GameObjectSimpleTest;
import gae.openingView.UIObject;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * SimpleEditor is a component of the ObjectEditor and will be represented on the top right corner
 * when an object is pressed. It will have all the basic properties of any game object such as its
 * name.
 * 
 * @author Brandon Choi
 *
 */

public class SimpleEditor extends Editor implements UIObject {

    private VBox simpleEditor;

    public SimpleEditor () {
        simpleEditor = new VBox(30);
        createEditor();
    }

    @Override
    public Node getObject () {
        return simpleEditor;
    }

    /**
     * creates the simple editor by iterating through all the methods and extracting the necessary
     * fields
     */
    private void createEditor () {
        TreeNode root = getMethodsTree(GameObjectSimpleTest.class, null);
        for (TreeNode n : root.getChildren()) {
            simpleEditor.getChildren()
                    .add(createEditField(getPropertyName(n.getMethod().getName())));
        }
    }

    /**
     * returns some field that allows user to edit value of object's property
     * 
     * @param name
     * @return
     */
    private HBox createEditField (String name) {
        HBox field = new HBox();
        Label label = new Label(name);
        label.setPrefWidth(100);
        TextField input = new TextField();
        input.setPrefWidth(100);
        field.setPrefWidth(200);
        field.getChildren().addAll(label, input);
        return field;
    }

    /* TO DO:
     * move createEditField to abstract class. add different methods for creating different types of
     * editors such as sliders, file choosers, text fields, etc.
     */

    @Override
    void setDefaults () {

    }
}
