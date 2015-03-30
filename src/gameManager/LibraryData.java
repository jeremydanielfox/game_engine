package gameManager;

import javafx.collections.ObservableList;
import gameobject.Editable;

public interface LibraryData {

    public void addToList(Editable e);
    public ObservableList<Editable> getEditablesList();
}
