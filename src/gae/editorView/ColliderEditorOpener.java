package gae.editorView;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import gae.editor.ComponentEditor;
import gae.editor.SimpleEditor;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class ColliderEditorOpener extends EditorOpener {
    private static final String TITLE = "Collider Editor";

    public ColliderEditorOpener (BiConsumer<Class<?>, Object> biconsumer,
                                 Class<?> klass,
                                 BiConsumer<List<List<Object>>, List<Method>> listSetter) {
        super(biconsumer, klass, listSetter);
    }

    @Override
    public Parent setUpParent (SimpleEditor editor, BiConsumer<Class<?>, Object> biconsumer,
                               BiConsumer<List<List<Object>>, List<Method>> listSetter) {
        BorderPane border = new BorderPane();
        List<ComponentEditor> simpleList = editor.getSimpleComponentEditors();
        SimpleEditorView simpleEditorView = new SimpleEditorView(simpleList);
        VBox top = (VBox) simpleEditorView.getObject();
        border.setTop(top);
        BuffEditor explosion = new BuffEditor();
        BuffEditor collision = new BuffEditor();
        border.setLeft(explosion.setLists("Explosion", biconsumer));
        border.setRight(collision.setLists("Collison", biconsumer));
        Button add = new Button("Add lists");
        add.setOnAction(e-> {
            List<List<Object>> buffLists = new ArrayList<>();
            buffLists.add(explosion.getBuffList());
            buffLists.add(collision.getBuffList());
            listSetter.accept(buffLists, editor.getSetListMethods());
            close();
        });
        border.setBottom(add);
        return border;
    }

    @Override
    public String getTitle () {
        // TODO Auto-generated method stub
        return TITLE;
    }

}
