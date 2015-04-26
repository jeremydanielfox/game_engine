package gae.editorView;

import exception.EmptyListException;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import engine.titles.Title;
import gae.editor.ObjectComponentEditor;


public class DraggableItem extends Region {
    private String name;
    private Class<?> klass;
    private Object object;
    private ObjectComponentEditor editor;

    public DraggableItem (Object object, Class<?> klass, ObjectComponentEditor editor) {
        name = ((Title) object).getTitle();
        this.object = object;
        this.klass = klass;
        this.editor = editor;
        this.getChildren().add(setFields(new HBox()));
    }

    private HBox setFields (HBox newBox) {
        Label label = new Label(name);
        label.setStyle("-fx-font-size: 30px;\n" +
                       "    -fx-font-weight: bold;\n" +
                       "    -fx-text-fill: #333333;\n" +
                       "    -fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
        newBox.getChildren().add(label);
        newBox.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                editor.popNewEditor();
            }
        });
        return newBox;
    }

    public Object getDraggedObject () {
        // TODO: obtain this object by looping through everything in the wrapper and add these
        // objects to the existing object
        return klass.cast(object);
    }

    public DraggableItem getNewInstance () {
        return new DraggableItem(object, klass, editor);
    }
}
