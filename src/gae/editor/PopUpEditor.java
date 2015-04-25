package gae.editor;

import java.util.function.Consumer;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;


public class PopUpEditor extends SimpleEditor {

    public PopUpEditor (Class<?> c, Consumer<Object> function) {
        super(c);
        init(c, function);
    }

    public PopUpEditor (Class<?> c, String title, Consumer<Object> function) {
        super(c, title);
        init(c, function);
    }

    private void init (Class<?> c, Consumer<Object> function) {
        VBox editor = (VBox) getObject();
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            Object obj = createObject(c);
            function.accept(obj);
        });
        editor.getChildren().add(addButton);
    }

}
