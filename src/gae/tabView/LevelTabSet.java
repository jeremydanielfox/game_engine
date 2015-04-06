package gae.tabView;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class LevelTabSet {
    private TabPane baseNode;
    
    public LevelTabSet (StackPane world, StackPane list, StackPane prefs) {
        initialize(world, list, prefs);
        
        // actual code is only world and prefs
    }

    private void initialize (StackPane world, StackPane list, StackPane prefs) {   
        baseNode = new TabPane();
        // actual code is worldTab.setContent(world)
        Tab worldTab = new Tab("World");
        BorderPane bp = new BorderPane();
        bp.setCenter(world);
        bp.setLeft(list);
        worldTab.setContent(bp);
        worldTab.setClosable(false);
        
        Tab prefsTab = new Tab("Preferences");
        prefsTab.setContent(prefs);
        worldTab.setClosable(false);

        baseNode.getTabs().addAll(worldTab, prefsTab);
    }
    
    public TabPane getBaseNode() {
        return baseNode;
    }
    

}
