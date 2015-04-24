package gae.tabView;

import gae.editorView.GameObjectEditorView;
import javafx.scene.Scene;
import javafx.scene.control.Tab;


// Potentially combine this with the ShopTab using an abstract class

public class GameObjectEditorTab {

    private Tab baseNode;
    private GameObjectEditorView view;

    public GameObjectEditorTab (Scene scene) {
        baseNode = new Tab();
        baseNode.setText("Game Object Editor");

        view = new GameObjectEditorView(scene);
        baseNode.setContent(view.getObject());
        baseNode.setClosable(false);
    }

    public Tab getBaseTabNode () {
        return baseNode;
    }
}
