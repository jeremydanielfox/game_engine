package gae.editor;

import gae.gameView.GameView;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class PopUpEditor extends SimpleEditor{

    public PopUpEditor (Class<?> c, GameView gameView) {
        super(c);
        init(c, gameView);
    }
    
    public PopUpEditor (Class<?> c, String title, GameView gameView) {
        super(c, title);
        init(c, gameView);
    }
    
    private void init(Class<?> c, GameView gameView) {
        VBox editor = (VBox)getObject();
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            Object obj = createObject(c);
            gameView.getAddFunction(obj);
        });
        editor.getChildren().add(addButton);
    }
    
    
}
