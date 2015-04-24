package gae.editorView;

import gae.backend.Placeable;
import gae.gridView.ContainerWrapper;
import gae.listView.Authorable;
import gae.listView.LibraryData;
import gae.listView.ListViewUtilities;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;


/**
 * Class that shows the library reflecting the libraryData
 * 
 * @author Kei
 *
 */
public class LibraryList {

    private Map<String, ObservableList<Authorable>> editableMap;
    private LibraryData data = LibraryData.getInstance();
    private String[] hardCodeForNow = { "Tower", "Enemy" }; // types of GameObjects
    private Scene scene;

    public LibraryList (Scene scene) {
        this.scene = scene;
        editableMap = new HashMap<>();
    }

    /**
     * Initializes the specific pane class
     * 
     * @param root
     * @param node
     * @param scene
     * @param wrapper
     * @return
     */
    public Accordion initialize () {
        Accordion accordion = new Accordion();
        for (String type : hardCodeForNow) {
            ObservableList<Authorable> list = FXCollections.observableArrayList();
            editableMap.put(type, list);
            TitledPane titledPane = getTitledPane(type);
            accordion.getPanes().add(titledPane);
        }
        setUpLists(data.getEditableObservableList());
        return accordion;
    }

    private void setUpLists (ObservableList<Authorable> observableList) {
        observableList.addListener( (ListChangeListener.Change<? extends Authorable> change) -> {
            while (change.next()) {
                if (change.wasAdded()) { // if an editablenode was added
                    Placeable added = (Placeable) change.getAddedSubList().get(0);
                    editableMap.get(added.getType()).add(added);
                }
            }
        });
        for (Authorable authorable : observableList) {
            Placeable editable = (Placeable) authorable;
            editableMap.get(editable.getType()).add(editable);
        }
    }


    /**
     * method created to make a simple TitledPane with a text
     * 
     * @param text
     * @return
     */
    private TitledPane getTitledPane (String text) {
        TitledPane pane = new TitledPane();
        pane.setText(text);
        pane.setTextFill(Color.RED);
        pane.setContent(ListViewUtilities.createList(editableMap.get(text), scene, "Editable"));
        return pane;
    }

}
