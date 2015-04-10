package gae.editor;

import java.lang.reflect.Method;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class PopUpEditor extends SimpleEditor{

    public PopUpEditor (Class<?> c) {
        super(c);
        init(c);
    }
    
    public PopUpEditor (Class<?> c, String title) {
        super(c, title);
        init(c);
    }
    
    private void init(Class<?> c) {
        VBox editor = (VBox)getObject();
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            Object obj = createObject(c);
            //TODO: send to library/game object to add
        });
        editor.getChildren().add(addButton);
    }
    
    
}
