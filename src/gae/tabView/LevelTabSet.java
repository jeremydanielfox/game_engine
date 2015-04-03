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
        baseNode.getTabs().addAll(createInteriorTab("World", world),
                                  createInteriorTab("Preferences", prefs));
    }

    private Tab createInteriorTab (String name, StackPane contents) {
        Tab tab = new Tab(name);
        tab.setContent(contents);
        tab.setClosable(false);
        return tab;
    }

    public TabPane getBaseNode () {
        return baseNode;
    }

}
