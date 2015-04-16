package gae.listView;

import java.util.ArrayList;
import java.util.List;
import engine.gameobject.GameObjectSimple;
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

    private ObservableList<EditableNode> editableNodeList = FXCollections.observableArrayList();

    public ObservableList<EditableNode> getObservableList () {
        return editableNodeList;
    }

    public void addToList (EditableNode editableNode) {
        editableNodeList.add(editableNode);
    }

    public List<GameObjectSimple> getGameObjectList () {
        List<GameObjectSimple> gameObjectList = new ArrayList<>();
        for (EditableNode editableNode : editableNodeList) {
            // can't instatiate GameObject - how to send it to XML
            // GameObjectSimple object = new GameObjectSimple();
        }
        return null;
    }
}
