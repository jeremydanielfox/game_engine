package gae.tabView;

import gae.editorVIew.GameObjectEditorView;
import javafx.scene.control.Tab;


// Potentially combine this with the ShopTab using an abstract class

public class GameObjectEditorTab {

    private Tab baseNode;
    private GameObjectEditorView view;

    public GameObjectEditorTab () {
        baseNode = new Tab();
        baseNode.setText("Game Object Editor");
        
        view = new GameObjectEditorView();
        baseNode.setContent(view.getObject());
        baseNode.setClosable(false);
    }
    
    public Tab getBaseTabNode () {
        return baseNode;
    }
}
