package gae.tabView;

import java.util.function.Consumer;
import gae.editorView.GameObjectEditorView;
import javafx.scene.Scene;
import javafx.scene.control.Tab;


// Potentially combine this with the ShopTab using an abstract class

public class GameObjectEditorTab {

    private Tab baseNode;
    private GameObjectEditorView view;

    public GameObjectEditorTab (Scene scene, Consumer<Object> consumer) {
        baseNode = new Tab();
        baseNode.setText("Game Object Editor");

        view = new GameObjectEditorView(scene, consumer);
        baseNode.setContent(view.getObject());
        baseNode.setClosable(false);
    }

    public Tab getBaseTabNode () {
        return baseNode;
    }
}
