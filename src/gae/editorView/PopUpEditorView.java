package gae.editorView;

import gae.editor.ObjectComponentEditor;
import java.util.function.Consumer;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PopUpEditorView {
    
    public PopUpEditorView(ObjectComponentEditor componentEditor) {
        Stage editorStage = new Stage();
        ScrollPane scroll = new ScrollPane();
        scroll.setPrefSize(1000, 500);
        Scene editorScene = new Scene(scroll);
        GameObjectEditorView editor = new GameObjectEditorView(editorScene, componentEditor.getObjectClass());
        scroll.setContent(editor.getObject());
        editorStage.setScene(editorScene);
        editorStage.show();
        VBox vbox = (VBox) editor.getObject();
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
//            componentEditor.setObject(editor.createObject());
            editorStage.close();
        });
        vbox.getChildren().add(addButton);
    }
}
