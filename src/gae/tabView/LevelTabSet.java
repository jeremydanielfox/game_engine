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

    public LevelTabSet (Node world, Node waves, Node interactionTable) {
        initialize(world, waves, interactionTable);
    }

    private void initialize (Node world, Node waves, Node interactionTable) {
        baseNode = new TabPane();

        baseNode.getTabs().addAll(makeTabContainer("World", world),
                                  makeTabContainer("Waves", waves),
                                  makeTabContainer("Interactions", interactionTable));
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
