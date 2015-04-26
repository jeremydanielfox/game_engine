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

public class CheckList {

    private VBox checkList;
    private ObservableList<Placeable> myObjects;
    private Map<CheckListItem, Boolean> myMap;

    @SuppressWarnings("unchecked")
    public CheckList (ObservableList<Authorable> objects) {
        checkList = new VBox(15);
        myObjects = (ObservableList<Placeable>) (ObservableList<?>) objects;

        myObjects.addListener( (ListChangeListener.Change<? extends Placeable> change) -> {
            while (change.next()) {
                change.getAddedSubList().stream()
                        .forEach(e -> {
                            createCheckOption(new CheckListItem(e));
                        });
                change.getRemoved().stream().forEach(e -> {
                    for (CheckListItem key : myMap.keySet()) {
                        if (key.getPlaceable().equals(e)) {
                            checkList.getChildren().remove(key.getNode());
                        }
                    }
                });
            }
        });
        myMap = new HashMap<>();
        // myObjects.forEach(e -> {
        // createCheckOption(e);
        // });
    }

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
    private void createCheckOption (CheckListItem item) {

        myMap.put(item, item.getCheckBox().isSelected());
        item.getCheckBox().selectedProperty().addListener( (obs, old, newVal) -> {
            myMap.put(item, newVal);
        });
        checkList.getChildren().add(item.getNode());
    }

    /**
     * Item in checklist which holds a thumbnail, name, and checkbox
     *
     * 
     */
    private static class CheckListItem {
        private Placeable placeable;
        private CheckBox checkbox;

        public CheckListItem (Placeable obj) {
            placeable = obj;
            checkbox = new CheckBox();
        }

        public Node getNode () {
            HBox node = new HBox(10);
            Graphic graphic = placeable.getGraphic().clone();
            graphic.setHeight(50);
            Node image = graphic.getResizedGraphic(1);
            Label label = new Label(placeable.getName());

            node.getChildren().addAll(image, label, checkbox);
            return node;
        }

        public Placeable getPlaceable () {
            return placeable;
        }

        public CheckBox getCheckBox () {
            return checkbox;
        }

    }

    /**
     * gives list of selected items in checklist
     *
     * 
     */
    public List<? extends Placeable> getSelectedPlaceables () {
        List<Placeable> selected = new ArrayList<>();
        for (CheckListItem key : myMap.keySet()) {
            if (myMap.get(key))
                selected.add(key.getPlaceable());
        }
        return selected;
    }
}
