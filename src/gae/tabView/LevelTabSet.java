package gae.tabView;

import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;


/**
 * Holds the individual tabs for each level
 * 
 * @author JohnGilhuly
 *
 */

public class LevelTabSet {
    private TabPane baseNode;

    public LevelTabSet (Pane world, Node waves, Node prefs) {
        initialize(world, waves, prefs);
    }

    private void initialize (Pane world, Node waves, Node prefs) {
        baseNode = new TabPane();
        // actual code is worldTab.setContent(world)
        Tab worldTab = new Tab("World");
        worldTab.setContent(world);
        worldTab.setClosable(false);

        Tab waveTab = new Tab("Waves");
        waveTab.setContent(waves);
        waveTab.setClosable(false);
        
        Tab prefTab = new Tab("Preferences");
        prefTab.setContent(prefs);
        prefTab.setClosable(false);

        baseNode.getTabs().addAll(worldTab, waveTab, prefTab);
    }

    public TabPane getBaseNode () {
        return baseNode;
    }

}
