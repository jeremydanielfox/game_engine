package gae.editor;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ObjectEditor extends Application{
    
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
    
    public static void main (String [] args) {
        launch(args);
    }

    @Override
    public void start (Stage arg0) throws Exception {
        Stage s = new Stage();
        Scene scene = new Scene(objectEditor);
        s.setScene(scene);
        s.show();
    }
}
