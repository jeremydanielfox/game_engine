package gae.editorView;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;


public class DraggableItem extends Region {
    private String classType;
    private EventHandler<? super MouseEvent> popNewEditor;

    public DraggableItem (String classType, EventHandler<? super MouseEvent> popNewEditor) {
        this.popNewEditor = popNewEditor;
        this.classType = classType;
        this.getChildren().add(setFields(new HBox()));
    }

    public void openEditor (MouseEvent me) {
        popNewEditor.handle(me);
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
                popNewEditor.handle(e);
            }
        });
        return newBox;
    }

    public DraggableItem getNewInstance () {
        return new DraggableItem(classType, popNewEditor);
    }
}
