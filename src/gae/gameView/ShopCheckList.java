package gae.gameView;

import gae.backend.Placeable;
import gae.listView.Authorable;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import javafx.collections.ListChangeListener;


/**
 * Creates a checklist to be used in the shop that displays the created Placeables. Creates
 * checklist items as more objects are authored.
 * 
 * @author Nina Sun
 */
public class ShopCheckList extends CheckList {
    private ObservableList<Placeable> myObjects;

    @SuppressWarnings("unchecked")
    public ShopCheckList (ObservableList<Authorable> objects) {
        super();
        myObjects = (ObservableList<Placeable>) (ObservableList<?>) objects;
        myObjects.forEach(e->{
            if(e.getShopTag()!=null){
            createCheckOption(new ShopCheckListItem(e));
            }
        });
        myObjects.addListener( (ListChangeListener.Change<? extends Placeable> change) -> {
            while (change.next()) {
                change.getAddedSubList()
                        .forEach(e->{
                            if(e.getShopTag()!=null)
                                createCheckOption(new ShopCheckListItem(e));
                            });
                change.getRemoved().forEach(e -> {
                    for (CheckListItem key : getMap().keySet()) {
                        if (((ShopCheckListItem) key).getPlaceable().equals(e)) {
                            ((Pane) getCheckList()).getChildren().remove(key.getNode());
                        }
                    }
                });
            }
        });
    }

    /**
     * Returns the list of selected placeables
     * 
     * @return list
     */
    public List<Placeable> getSelectedPlaceables () {
        List<Placeable> list = new ArrayList<>();
        getSelectedItems().forEach(e -> list.add(((ShopCheckListItem) e).getPlaceable()));
        return list;
    }

}
