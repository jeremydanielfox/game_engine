package gae.gameView;

import javafx.beans.property.BooleanProperty;
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
     * Returns node to be added to the checklist
     *
     * @return Node with checkbox
     */
    public Node getNode ();

    /**
     * Returns checkbox property such that listener can be added in checklist
     *
     * @return booleanproperty
     */
    public BooleanProperty getSelectedProperty ();

}
