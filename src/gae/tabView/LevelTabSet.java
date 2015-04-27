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

    public LevelTabSet (Pane world, Node waves) {
        initialize(world, waves);
    }

    private void initialize (Pane world, Node waves) {
        baseNode = new TabPane();
        // actual code is worldTab.setContent(world)
        Tab worldTab = new Tab("World");
        worldTab.setContent(world);
        worldTab.setClosable(false);

        Tab waveTab = new Tab("Waves");
        waveTab.setContent(waves);
        waveTab.setClosable(false);

        baseNode.getTabs().addAll(worldTab, waveTab);
    }

    public TabPane getBaseNode () {
        return baseNode;
    }

}
