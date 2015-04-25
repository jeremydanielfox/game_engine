package gae.gameView;

import gae.backend.Placeable;
import gae.listView.Authorable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.collections.ListChangeListener;

public class ShopCheckList extends CheckList{
    private ObservableList<Placeable> myObjects;
    
    @SuppressWarnings("unchecked")
    public ShopCheckList (ObservableList<Authorable> objects) {
        super();
        myObjects = (ObservableList<Placeable>) (ObservableList<?>) objects;

        myObjects.addListener( (ListChangeListener.Change<? extends Placeable> change) -> {
            while (change.next()) {
                change.getAddedSubList().stream()
                        .forEach(e -> {
                            createCheckOption(new ShopCheckListItem(e));
                        });
                change.getRemoved().stream().forEach(e -> {
                    for (CheckListItem key : getMap().keySet()) {
                        if (((ShopCheckListItem) key).getPlaceable().equals(e)) {
                            ((Pane) getCheckList()).getChildren().remove(key.getNode());
                        }
                    }
                });
            }
        });
      
        // myObjects.forEach(e -> {
        // createCheckOption(e);
        // });
    }
    
    public List<Placeable> getSelectedPlaceables(){
        List<Placeable> list=new ArrayList<>();
        getSelectedItems().stream().forEach(e->list.add(((ShopCheckListItem) e).getPlaceable()));
        return list;
    }
    
}
