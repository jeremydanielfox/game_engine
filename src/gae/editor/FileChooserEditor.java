package gae.editor;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

/**
 * Represents editor based on choosing a file. Could be used to select the graphic for the object
 * (weapons, towers, etc.)
 * 
 * @author Brandon Choi
 *
 */

public class FileChooserEditor implements ComponentEditor{
    
    private HBox editBox;
    private Label label;
    private FileChooser fileChooser;

    public FileChooserEditor (String name) {
        editBox = new HBox();
        label = new Label(name);
        fileChooser = new FileChooser();
    }

    @Override
    public Node getObject () {
        return editBox;
    }

    @Override
    public void clear () {
        
    }

    @Override
    public void defaultField () {
        
    }
}
