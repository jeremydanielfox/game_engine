package gae.editorView;

import gae.editor.SimpleEditor;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class PopUpEditorView {

    public PopUpEditorView (Consumer<Object> consumer, BiConsumer<Class<?>, Object> biConsumer, Class<?> klass, int index) {
        Stage editorStage = new Stage();
        ScrollPane scroll = new ScrollPane();
        scroll.setPrefSize(1500, 800);
        Scene editorScene = new Scene(scroll);
        consumer = consumer.andThen(e -> editorStage.close());
        GameObjectEditorView editor = new GameObjectEditorView(editorScene, consumer, biConsumer, klass, index);
        Node pane = (BorderPane) editor.getObject();
        scroll.setContent(pane);
        editorStage.setScene(editorScene);
        editorStage.show();
    }
}
