package gae.editorView;

import exception.EmptyListException;
import javafx.beans.property.BooleanProperty;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import gae.editor.ObjectComponentEditor;


public class DraggableItem extends Region {
    private Class<?> klass;
    private Object object;
    private ObjectComponentEditor editor;
    private int index;

    public DraggableItem (Object object,
                          int listIndex,
                          Class<?> klass,
                          ObjectComponentEditor editor) {
        this.object = object;
        this.klass = klass;
        this.editor = editor;
        this.index = listIndex;
//        this.edited = edited;
//        edited.addListener((obsevable, oldValue, newValue) -> {
//            System.out.println("changed");
//            this.getChildren().clear();
//            this.getChildren().add(setFields(new HBox(),((Title) object).getTitle()));
//        });
        this.getChildren().add(setFields(new HBox(),GameObjectInformation.getInstance().getTitle(object)));
    }

    private HBox setFields (HBox newBox, String name) {
        Label label = new Label(name);
        label.setStyle("-fx-font-size: 30px;\n" +
                       "    -fx-font-weight: bold;\n" +
                       "    -fx-text-fill: #333333;\n" +
                       "    -fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
        newBox.getChildren().add(label);
        newBox.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                editor.popNewEditor(index);
            }
        });
        final Tooltip tooltip = new Tooltip("Double Click to Edit " + name);
        Tooltip.install(newBox, tooltip);
        return newBox;
    }

    public Object getDraggedObject () {
        // TODO: obtain this object by looping through everything in the wrapper and add these
        // objects to the existing object
        return klass.cast(object);
    }

    public DraggableItem getNewInstance () {
        return new DraggableItem(object, index, klass, editor);
    }
}
