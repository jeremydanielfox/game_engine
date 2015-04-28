package gae.gameView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.Node;
import javafx.scene.layout.VBox;


/**
 * List of options that can be checked to indicate selection. Subclasses LabelCheckList and
 * ShopCheckList
 *
 * @author Brandon Choi and Nina Sun
 *
 */

public abstract class CheckList {

    private VBox checkList;
    private Map<CheckListItem, Boolean> myMap;

    public CheckList () {
        checkList = new VBox(15);
        myMap = new HashMap<>();
    }

    /**
     * Returns the checklist vbox to be added to a scene
     * 
     * @return checklist
     */
    public Node getCheckList () {
        return checkList;
    }

    /**
     * Puts a CheckListItem in a map that tracks whether it is selected, and adds the node to the
     * checklist
     *
     * @param item CheckListItem to be added to map and checklist node
     */
    public void createCheckOption (CheckListItem item) {
        myMap.put(item, item.getCheckBox().isSelected());
        item.getCheckBox().selectedProperty().addListener( (obs, old, newVal) -> {
            myMap.put(item, newVal);
        });
        if (!checkList.getChildren().contains(item.getNode()))
            checkList.getChildren().add(item.getNode());
    }

    public Map<CheckListItem, Boolean> getMap () {
        return myMap;
    }

    /**
     * Gives list of selected items in checklist
     *
     * @return list of CheckListItems
     */
    public List<CheckListItem> getSelectedItems () {
        List<CheckListItem> selected = new ArrayList<>();
        for (CheckListItem key : myMap.keySet()) {
            if (myMap.get(key))
                selected.add(key);
        }
        return selected;
    }
}
