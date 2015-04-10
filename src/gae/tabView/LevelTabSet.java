package gae.tabView;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;

public class LevelTabSet {
    private TabPane baseNode;
    
    public LevelTabSet (Pane world, Pane prefs) {
        initialize(world, prefs);
        
        // actual code is only world and prefs
    }

    private void initialize (Pane world, Pane prefs) {   
        baseNode = new TabPane();
        // actual code is worldTab.setContent(world)
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
