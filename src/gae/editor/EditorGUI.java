package gae.editor;

import java.util.function.Consumer;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class EditorGUI extends SimpleEditor {

    public EditorGUI (Class<?> c, Consumer<Object> function) {
        super(c);
        init(c, function);
    }
    
    public EditorGUI (Class<?> c, String title, Consumer<Object> function) {
        super(c, title);
        init(c, function);
    }
    
    private void init(Class<?> c, Consumer<Object> function) {
        VBox editor = (VBox)getObject();
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            Object obj = createObject(c);
            function.accept(obj);
        });
        editor.getChildren().add(addButton);
    }
    
}
