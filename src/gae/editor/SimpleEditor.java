package gae.editor;

import gae.openingView.UIObject;
import javafx.scene.Node;
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
        simpleEditor = new VBox();
    }

    @Override
    public Node getObject () {
        return simpleEditor;
    }
    
    private void createEditor () {
        
    }

    @Override
    void setDefaults () {

    }
}
