package gae.listView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class LibraryData {
    private static LibraryData instance = new LibraryData();

    private LibraryData () {
    }

    public static LibraryData getInstance () {
        return instance;
    }

    private ObservableList<EditableNode> editableNodeList = FXCollections.observableArrayList();

    public ObservableList<EditableNode> getObservableList () {
        return editableNodeList;
    }

    public void addToList (EditableNode editableNode) {
        editableNodeList.add(editableNode);
    }
}
