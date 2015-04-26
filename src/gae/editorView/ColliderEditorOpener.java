package gae.editorView;

import java.util.List;
import java.util.function.BiConsumer;
import gae.editor.ComponentEditor;
import gae.editor.SimpleEditor;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class ColliderEditorOpener extends EditorOpener {
    private static final String TITLE = "Collider Editor";
    private BiConsumer<Class<?>, Object> biconsumer;

    public ColliderEditorOpener (BiConsumer<Class<?>, Object> biconsumer,
                                 Class<?> klass) {
        super(biconsumer, klass);
        this.biconsumer = biconsumer;
    }

    @Override
    public Parent setUpParent (SimpleEditor editor) {
        BorderPane border = new BorderPane();
        List<ComponentEditor> simpleList = editor.getSimpleComponentEditors();
        SimpleEditorView simpleEditorView = new SimpleEditorView(simpleList);
        VBox top = (VBox) simpleEditorView.getObject();
        border.setTop(top);
        BuffEditor explosion = new BuffEditor();
        BuffEditor collision = new BuffEditor();
        border.setLeft(explosion.setLists("Explosion", biconsumer));
        border.setRight(collision.setLists("Collison", biconsumer));
        return border;
    }

    @Override
    public String getTitle () {
        // TODO Auto-generated method stub
        return TITLE;
    }

}
