package gae.gameView;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;

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
     *@return Node with checkbox
     */
    public Node getNode();
    
    public CheckBox getCheckBox();
    
}
