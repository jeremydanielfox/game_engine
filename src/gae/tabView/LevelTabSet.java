package gae.tabView;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;

public class LevelTabSet {
    private TabPane baseNode;
    
    public LevelTabSet (StackPane world, StackPane prefs) {
        initialize(world, prefs);
    }

    private void initialize (StackPane world, StackPane prefs) {   
        baseNode = new TabPane();
        Tab worldTab = new Tab("World");
        worldTab.setContent(world);
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
