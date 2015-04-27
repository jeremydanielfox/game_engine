package gae.editorView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import gae.editor.ComponentEditor;
import gae.editor.SimpleEditor;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class ColliderEditorOpener extends EditorOpener {
    private static final String TITLE = "Collider Editor";
    private TextField title;

    public ColliderEditorOpener (BiConsumer<Class<?>, Object> biconsumer,
                                 Class<?> klass, int index) {
        super(biconsumer, klass, index);
    }

    @Override
    public Parent setUpParent (Class<?> klass, BiConsumer<Class<?>, Object> biconsumer, int index) {
        BorderPane border = new BorderPane();
        SimpleEditor editor = new SimpleEditor(klass, biconsumer);
        List<ComponentEditor> simpleList = editor.getSimpleComponentEditors();
        SimpleEditorView simpleEditorView = new SimpleEditorView(simpleList);

        VBox top = new VBox();
        top.getChildren().add(getTitleBox());
        top.getChildren().add((VBox) simpleEditorView.getObject());
        border.setTop(top);
        BuffEditor explosion = new BuffEditor();
        BuffEditor collision = new BuffEditor();
        border.setLeft(explosion.setLists("Explosion", biconsumer));
        border.setRight(collision.setLists("Collison", biconsumer));
        Button add = new Button("Add lists");
        add.setOnAction(e -> {
            List<List<Object>> buffLists = new ArrayList<>();
            buffLists.add(explosion.getBuffList());
            buffLists.add(collision.getBuffList());
            setList(editor, klass, buffLists, editor.getSetListMethods(), biconsumer, index);
            close();
        });
        border.setBottom(add);
        return border;
    }

    private HBox getTitleBox () {
        HBox hbox = new HBox();
        title = new TextField();
        title.setPromptText("Set Title");
        hbox.getChildren().addAll(new Label("Title"), title);
        return hbox;
    }

    private void setList (SimpleEditor editor,
                          Class<?> klass,
                          List<List<Object>> list,
                          List<Method> methodList,
                          BiConsumer<Class<?>, Object> biConsumer, int index) {
        try {
            Object obj = editor.createObject(klass);
            for (int i = 0; i < methodList.size(); i++) {
                methodList.get(i).invoke(obj, list.get(i));
            }
            GameObjectInformation.getInstance().addInformation(obj, title.getText(), index);
            biConsumer.accept(klass, obj);
        }
        catch (IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public String getTitle () {
        // TODO Auto-generated method stub
        return TITLE;
    }

}
