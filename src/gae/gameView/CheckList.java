package gae.gameView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import engine.gameobject.Graphic;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import gae.backend.Placeable;
import gae.listView.Authorable;
import javafx.collections.ListChangeListener;


/**
 * List of options that can be checked to indicate selection
 *
 * @author Brandon Choi and Nina Sun
 *
 */

public abstract class CheckList {

    private VBox checkList;
    private Map<CheckListItem, Boolean> myMap;
    
    public CheckList () {
        checkList= new VBox(15);
        myMap=new HashMap<>();
    }
    
//    public CheckList (List<? extends Object> objects) {
//        checkList = new VBox(15);
////        myObjects = (ObservableList<Placeable>) (ObservableList<?>) objects;
////
////        myObjects.addListener( (ListChangeListener.Change<? extends Placeable> change) -> {
////            while (change.next()) {
////                change.getAddedSubList().stream()
////                        .forEach(e -> {
////                            createCheckOption(new CheckListItem(e));
////                        });
////                change.getRemoved().stream().forEach(e -> {
////                    for (CheckListItem key : myMap.keySet()) {
////                        if (key.getPlaceable().equals(e)) {
////                            checkList.getChildren().remove(key.getNode());
////                        }
////                    }
////                });
////            }
////        });
//        myObjects=objects;
//        myMap = new HashMap<>();
//        // myObjects.forEach(e -> {
//        // createCheckOption(e);
//        // });
//    }

    public Node getCheckList () {
        return checkList;
    }

    /**
     * Called by outside class to display the check list
     */
    public void showCheckList () {
        Stage temp = new Stage();
        Scene scene = new Scene(checkList);
        temp.setScene(scene);
        temp.show();
        temp.centerOnScreen();
    }

    /**
     * create one check option based on object given
     *
     * @param s
     */
    public void createCheckOption (CheckListItem item) {

        myMap.put(item, item.getCheckBox().isSelected());
        item.getCheckBox().selectedProperty().addListener( (obs, old, newVal) -> {
            myMap.put(item, newVal);
        });
        checkList.getChildren().add(item.getNode());
    }
    
    public Map<CheckListItem, Boolean> getMap(){
        return myMap;
    }


    /**
     * gives list of selected items in checklist
     *
     * 
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
