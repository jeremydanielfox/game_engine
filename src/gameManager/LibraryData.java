package gameManager;

import engine.gameobject.Editable;
import javafx.collections.ObservableList;

public interface LibraryData {

    public void addToList(Editable e);
    public ObservableList<Editable> getEditablesList();
}
