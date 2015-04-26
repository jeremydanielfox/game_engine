package gae.editorView;

import gae.editor.SimpleEditor;
import java.util.function.BiConsumer;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public abstract class EditorOpener {
    public EditorOpener (BiConsumer<Class<?>, Object> biconsumer, Class<?> klass) {
        Stage s = new Stage();
        Scene scene = new Scene(setUpParent(new SimpleEditor(klass, biconsumer)));
        s.setWidth(330);
        s.setHeight(500);
        s.setScene(scene);
        s.setTitle(getTitle());
        s.show();
    }

    public abstract Parent setUpParent (SimpleEditor editor);

    public abstract String getTitle ();
}
