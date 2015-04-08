package gae.backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import engine.gameobject.Editable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;


public class LibraryData {

    
    Map<String, ObservableList<Editable>> database = new HashMap<>();

    /**
     * The library will contain certain data and the LibraryView will
     * share the ObservableList to update when the data is changed. 
     */

    public void addToList (Editable editable) {
        String editableType = editable.getClass().getSimpleName();
        if (!database.containsKey(editableType)) {
            database.put(editableType, FXCollections.observableList(new ArrayList<>()));
        }
        database.get(editableType).add(editable);
    }

    public Map<String, ObservableList<Editable>> getMap () {
        // tower.add(new TempTower());
        return database;
    }

}
