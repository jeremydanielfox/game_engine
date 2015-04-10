package gae.editor;

import java.util.Arrays;
import java.util.List;

import engine.gameobject.GameObjectSimpleTest;
import gae.backend.TempTower;
import gae.listView.EditableImage;
import gae.openingView.UIMediator;
import gae.openingView.UIObject;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
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

    private UIMediator myMediator;
    private VBox simpleEditor;
    private List<ComponentEditor> editFields;

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

        SliderEditor se = new SliderEditor("Hello", 0, 10);
        TextEditor te = new TextEditor("Hi");
        FileChooserEditor fe = new FileChooserEditor("File");
        Button b = new Button("done");
        simpleEditor.getChildren().addAll(Arrays.asList(se.getObject(), te.getObject(), fe.getObject(), b));
               
        TempTower tt = new TempTower("KEI'S TOWER");
        b.setOnMouseClicked(e -> {  
            tt.setImage("/images/" + fe.getFile().getName());
            myMediator.handleEvent(tt, e);
        });
        System.out.println(tt.getImagePath()); 
    }

    @Override
    void setDefaults () {
        editFields.forEach(e -> e.defaultField());
    }

    @Override
    void clearValues () {
        editFields.forEach(e -> e.clear());
    }
}
