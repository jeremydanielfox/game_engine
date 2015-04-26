package gae.editorView;

import gae.editor.SimpleEditor;
import java.lang.reflect.Method;
import java.util.List;
import java.util.function.BiConsumer;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public abstract class EditorOpener {
    private Stage s;

    public EditorOpener (BiConsumer<Class<?>, Object> biconsumer,
                         Class<?> klass,
                         BiConsumer<List<List<Object>>, List<Method>> listSetter) {
        s = new Stage();
        Scene scene =
                new Scene(setUpParent(new SimpleEditor(klass, biconsumer), biconsumer, listSetter));
        s.setWidth(330);
        s.setHeight(500);
        s.setScene(scene);
        s.setTitle(getTitle());
        s.show();
    }

    protected void close () {
        s.close();
    }

    public abstract Parent setUpParent (SimpleEditor editor,
                                        BiConsumer<Class<?>, Object> biconsumer,
                                        BiConsumer<List<List<Object>>, List<Method>> listSetter);

    public abstract String getTitle ();
}
