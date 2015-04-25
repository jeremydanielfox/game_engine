package gae.editorView;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;


public class DraggableItem extends Region {
    private String classType;
    private Class<?> klass;
    private Object object;

    public DraggableItem (Object object, Class<?> klass, String classType) {
        this.classType = classType;
        this.object = object;
        this.klass = klass;
        this.getChildren().add(setFields(new HBox()));
    }

    private HBox setFields (HBox newBox) {
        Label label = new Label(classType);
        label.setStyle("-fx-font-size: 12px;\n" +
                       "    -fx-font-weight: bold;\n" +
                       "    -fx-text-fill: #333333;\n" +
                       "    -fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
        newBox.getChildren().add(label);
        newBox.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                // popNewEditor.handle(e);
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
        return new DraggableItem(classType, klass, classType);
    }
}
