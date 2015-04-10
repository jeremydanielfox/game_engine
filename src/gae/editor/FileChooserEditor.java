package gae.editor;

import java.io.File;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Represents editor based on choosing a file. Could be used to select the graphic for the object
 * (weapons, towers, etc.)
 * 
 * @author Brandon Choi
 *
 */

public class FileChooserEditor extends ComponentEditor {
    
    private FileChooser fileChooser;
    private Button chooserOpener;
    private Stage stage;

    public FileChooserEditor (String name) {
        fileChooser = new FileChooser();
        chooserOpener = new Button("LOAD FILE");
        stage = new Stage();
        chooserOpener.setOnMouseClicked(e -> {
            File file = fileChooser.showOpenDialog(stage);
        }); 
        getEditBox().getChildren().addAll(getLabel(), chooserOpener);
    }

    @Override
    public Node getObject () {
        return getEditBox();
    }

    @Override
    public void clear () {
        
    }

    @Override
    public void defaultField () {
        
    }

    @Override
    public Object createObject (Class<?> c) {
        // TODO Auto-generated method stub
        return null;
    }
}
