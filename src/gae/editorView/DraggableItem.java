package gae.editorView;

import javafx.scene.Group;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import gae.editor.EditingParser;
import gae.editor.ObjectComponentEditor;
import javafx.scene.paint.Color;


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
        // this.edited = edited;
        // edited.addListener((obsevable, oldValue, newValue) -> {
        // System.out.println("changed");
        // this.getChildren().clear();
        // this.getChildren().add(setFields(new HBox(),((Title) object).getTitle()));
        // });
        this.getChildren().add(setFields(new HBox(),
                                         GameObjectInformation.getInstance().getTitle(object)));
    }

    private HBox setFields (HBox newBox, String name) {
        Label label = new Label(name);
        label.getStyleClass().add("draggable-label");
        newBox.getChildren().add(label);
        newBox.setOnMousePressed(e -> {
            label.setTextFill(Color.RED);
            if (e.getClickCount() == 2) {
                label.setTextFill(Color.BLACK);
                if (klass.getSimpleName().equals("Collider")) {
                    new ColliderEditorOpener(editor.getBiConsumer(), klass, index);
                }
                else {
                    editor.popNewEditor(index);
                }
            }
            if (e.isSecondaryButtonDown()) {
                ContextMenu contextmenu = new ContextMenu();
                MenuItem item = new MenuItem("Delete");
                item.setOnAction(action -> {
                    ((Group) this.getParent()).getChildren().remove(this);
                });
                contextmenu.getItems().add(item);
                contextmenu.show(newBox, e.getSceneX(), e.getSceneY());
            }
        });
        final Tooltip tooltip = new Tooltip("Double Click to Edit " + name);
        Tooltip.install(newBox, tooltip);
        return newBox;
    }

    public String getClassType () {
        return klass.getSimpleName();
    }

    public Object getDraggedObject () {
        try {
            return Class.forName(EditingParser.getInterfaceClassFromMap(klass)).cast(object);
        }
        catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public DraggableItem getNewInstance () {
        return new DraggableItem(object, index, klass, editor);
    }
}
