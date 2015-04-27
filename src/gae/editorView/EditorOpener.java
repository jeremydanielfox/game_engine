package gae.editorView;

import java.util.function.BiConsumer;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public abstract class EditorOpener {
    private Stage s;

    public EditorOpener (BiConsumer<Class<?>, Object> biconsumer,
                         Class<?> klass, int index) {
        s = new Stage();
        Scene scene =
                new Scene(setUpParent(klass, biconsumer, index));
        s.setWidth(330);
        s.setHeight(500);
        s.setScene(scene);
        s.setTitle(getTitle());
        s.show();
    }

    protected void close () {
        s.close();
    }

    public abstract Parent setUpParent (Class<?> klass,
                                        BiConsumer<Class<?>, Object> biconsumer, int index);

    public abstract String getTitle ();
}
