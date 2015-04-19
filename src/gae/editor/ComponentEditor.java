package gae.editor;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import gae.openingView.UIObject;

/**
 * Interface for all single component editors. To be implemented by TextEditor, SliderEditor, etc.
 * Subclasses would be used to represent an editor for a single property of an object.
 * 
 * @author Brandon Choi
 * @author Eric Saba
 *
 */

public abstract class ComponentEditor implements UIObject, Edits {
    private HBox editBox;
    private Label label;
    
    public ComponentEditor() {
        editBox = new HBox();
        label = new Label();
    }
    
    @Override
    public Node getObject () {
        return getEditBox();
    }
    
    /**
     * clears field 
     */
    abstract void clear ();
    
    /**
     * sets field to default
     */
    abstract void defaultField();
    
    /**
     * sets name of the Object
     */
    void setName(String name) {
        label.setText(Editor.getPropertyName(name));
    }
    
    protected HBox getEditBox() {
        return editBox;
    }
    
    protected Label getLabel() {
        return label;
    }
}
