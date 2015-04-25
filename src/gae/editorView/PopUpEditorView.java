package gae.editorView;

import gae.listView.LibraryData;
import java.util.function.Consumer;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class PopUpEditorView {

    public PopUpEditorView (Consumer<Object> consumer, Class<?> klass) {
        Stage editorStage = new Stage();
        ScrollPane scroll = new ScrollPane();
        scroll.setPrefSize(1000, 500);
        Scene editorScene = new Scene(scroll);

        GameObjectEditorView editor = new GameObjectEditorView(editorScene, klass);
        BorderPane pane = (BorderPane) editor.getObject();
        scroll.setContent(pane);
        editorStage.setScene(editorScene);
        editorStage.show();
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            Object object = editor.createObject();
            consumer.accept(object);
            // temporary, before we set up Consumers
            LibraryData.getInstance().addCreatedObjectToList(klass, object);
            editorStage.close();
        });
        editor.addButton().accept(addButton);
    }
}
