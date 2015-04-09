package gae.editor;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 * Represents an editor based on input text from the author. Could be used for features such as
 * object name.
 * 
 * @author Brandon Choi
 *
 */

public class TextEditor implements ComponentEditor {
    
    private HBox editBox;
    private Label label;
    private TextField field;
    
    public TextEditor (String name) {
        editBox = new HBox();
        label = new Label(name);
        field = new TextField();      
        editBox.getChildren().addAll(label, field);
    }

    @Override
    public Node getObject () {
        return editBox;
    }

    @Override
    public void clear () {
        field.clear();
    }

    @Override
    public void defaultField () {
        field.setText("*DEFAULT*");
    }
}
