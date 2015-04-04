package gae.editor;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ObjectEditor extends Editor{
    
    private BorderPane objectEditor;
    private VBox rightContainer;
    private VBox leftContainer;
    private Pane imageBox;
    
    public ObjectEditor () {
        objectEditor = new BorderPane();
        rightContainer = new VBox();
        leftContainer = new VBox();
        imageBox = new Pane();     
        objectEditor.setRight(rightContainer);
        objectEditor.setLeft(leftContainer);
        leftContainer.getChildren().add(imageBox);
    }
    
    public Pane getEditor () {
        return objectEditor;
    }

    @Override
    void setDefaults () {
        // TODO Auto-generated method stub
        
    }
}
