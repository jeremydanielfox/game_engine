package gae.listView;

import java.util.ArrayList;
import java.util.List;
import engine.gameobject.GameObjectSimple;
import engine.gameobject.Graphic;
import engine.gameobject.Health;
import engine.gameobject.HealthSimple;
import gae.backend.Editable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * Library Data class that stores the list of Editables
 * 
 * @author Kei
 *
 */
public class LibraryData {
    private static LibraryData instance = new LibraryData();

    private LibraryData () {
    }

    public static LibraryData getInstance () {
        return instance;
    }

    private ObservableList<Editable> editableList = FXCollections.observableArrayList();

    public ObservableList<Editable> getObservableList () {
        return editableList;
    }

    public void addToList (Editable editable) {
        editableList.add(editable);
    }

    public List<GameObjectSimple> getGameObjectList () {
        List<GameObjectSimple> gameObjectList = new ArrayList<>();
        for (Editable editable : editableList) {
            GameObjectSimple object = new GameObjectSimple();
            object.setGraphic(new Graphic(editable.getWidth(), editable.getHeight(),
                                          editable.getImagePath()));
            // object.setLabel(editableNode.getLabel());
            // object.setTag(editableNode.getTag());
            object.setPoint(editable.getLocation());
//            object.setHealth(new HealthSimple(editable.getHealth()));
            // WHAT IS TAG/LABEL
            // set Collider
        }
        return gameObjectList;
    }
}
