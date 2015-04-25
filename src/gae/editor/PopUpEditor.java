package gae.editor;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;


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
        VBox editor = (VBox) getObject();
        for (Node node: editor.getChildren()) {
            System.out.println("ADDED TO VBOX : " + node);
        }
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            Object obj = createObject(c);
            function.accept(obj);
        });
        editor.getChildren().add(addButton);
    }

}
