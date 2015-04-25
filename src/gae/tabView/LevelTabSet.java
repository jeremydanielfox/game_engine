package gae.tabView;

import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;


public class LevelTabSet {
    private TabPane baseNode;

    public LevelTabSet (Pane world, Pane prefs, Node waves) {
        initialize(world, prefs, waves);
    }

    private void initialize (Pane world, Pane prefs, Node waves) {
        baseNode = new TabPane();
        // actual code is worldTab.setContent(world)
        Tab worldTab = new Tab("World");
        worldTab.setContent(world);
        worldTab.setClosable(false);

        Tab prefsTab = new Tab("Preferences");
        prefsTab.setContent(prefs);
        prefsTab.setClosable(false);

        Tab waveTab = new Tab("Waves");
        waveTab.setContent(waves);
        waveTab.setClosable(false);

        baseNode.getTabs().addAll(worldTab, prefsTab, waveTab);
    }

    public TabPane getBaseNode () {
        return baseNode;
    }

}
