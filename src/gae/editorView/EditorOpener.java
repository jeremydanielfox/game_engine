package gae.editorView;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public abstract class EditorOpener {
    public void initialize () {
        Stage s = new Stage();
        Scene scene = new Scene(setUpParent());
        s.setWidth(330);
        s.setHeight(500);
        s.setScene(scene);
        s.setTitle(getTitle());
        s.show();
    }

    public abstract Parent setUpParent ();
    
    public abstract String getTitle();
}
