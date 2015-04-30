package gae.tabView;

import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * Holds the individual tabs for each level
 * 
 * @author JohnGilhuly
 *
 */

public class LevelTabSet {
    private TabPane baseNode;

    public LevelTabSet (Node world, Node waves, Node interactionTable, Node prefs) {
        initialize(world, waves, interactionTable, prefs);
    }

    private void initialize (Node world, Node waves, Node interactionTable, Node prefs) {
        baseNode = new TabPane();

        baseNode.getTabs().addAll(makeTabContainer("World", world),
                                  makeTabContainer("Waves", waves),
                                  makeTabContainer("Interactions", interactionTable),
                                  makeTabContainer("Preferences", prefs));
    }

    private Tab makeTabContainer (String title, Node interactionTable) {
        Tab interactionTab = new Tab(title);
        interactionTab.setContent(interactionTable);
        interactionTab.setClosable(false);
        return interactionTab;
    }

    public TabPane getBaseNode () {
        return baseNode;
    }

}