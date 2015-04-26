package gae.editor;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class PopUpEditor extends SimpleEditor {

    public PopUpEditor (Class<?> c, Consumer<Object> function, BiConsumer<Class<?>, Object> biConsumer) {
        super(c, biConsumer);
        init(c, function);
    }

    public PopUpEditor (Class<?> c, String title, Consumer<Object> function, BiConsumer<Class<?>, Object> biConsumer) {
        super(c, biConsumer, title);
        init(c, function);
    }

    private void init (Class<?> c, Consumer<Object> function) {
        Stage s = new Stage();
        VBox editor = (VBox) getObject();
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            Object obj = createObject(c);
            function.accept(obj);
            s.close();
        });
        editor.getChildren().add(addButton);
        s.setScene(new Scene(editor));
        s.show();
    }

}
