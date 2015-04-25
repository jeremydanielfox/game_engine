package gae.gameView;

import javafx.scene.Node;

/**
 * Item to be added to a checklist
 *
 * @author Nina Sun
 *
 */

public interface CheckListItem {
    /**
     * 
     *
     *@return Node that will have a checkbox next to it
     */
    public Node getNode();
    
}
