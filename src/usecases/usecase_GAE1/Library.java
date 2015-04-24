package usecases.usecase_GAE1;

import gae.backend.Editable;
import java.util.ArrayList;
import java.util.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;


public class Library extends Observable {

    // We would need this Editable ObservableList to be held by the Library
    // class and LibraryView class

    ObservableList<Editable> tower = FXCollections
            .observableList(new ArrayList<>());

    // This ObjectProperty is bound to the ObjectProperty in the EditorView
    // class. Therefore, when the Library class's property is changed, it
    // automatically reflects in the EditorView's property.

    ObjectProperty<Editable> property = new SimpleObjectProperty<>();

    /**
     * The library will contain certain data and the observer (LibraryView) will
     * see these changes - the observer will be added to the observable in
     * another class
     */

    public Library () {
        tower.addListener( (ListChangeListener.Change<? extends Editable> e) -> onChanged(e));
    }

    public void updateObservers () {
        setChanged();
        notifyObservers();
    }

    public void addToList (Editable editable) {
        tower.add(editable);

    }

    private void onChanged (ListChangeListener.Change<? extends Editable> e) {
        // while(e.next()) {
        //
        // }
    }
}
