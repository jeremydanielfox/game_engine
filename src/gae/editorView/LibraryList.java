package gae.editorView;

import gae.backend.Placeable;
import gae.listView.Authorable;
import gae.listView.LibraryData;
import gae.listView.ListViewUtilities;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;


/**
 * Class that shows the library reflecting the libraryData
 * 
 * @author Kei
 *
 */
public class LibraryList {

    private Map<String, ObservableList<Authorable>> editableMap;
    private static final double ACCORDION_PROPORTIONS = 0.15;
    private LibraryData data = LibraryData.getInstance();
    private Scene scene;
    private Accordion accordion;

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
        accordion = new Accordion();
        accordion.setPrefWidth(Screen.getPrimary().getVisualBounds().getWidth() *
                               ACCORDION_PROPORTIONS);
        setUpLists(data.getEditableObservableList());
        return accordion;
    }

    private void setUpLists (ObservableList<Authorable> observableList) {
        observableList.addListener( (ListChangeListener.Change<? extends Authorable> change) -> {
            while (change.next()) {
                if (change.wasAdded()) { // if an editablenode was added
                    Placeable added = (Placeable) change.getAddedSubList().get(0);
                    if (!editableMap.containsKey(added.getType())) {
                        ObservableList<Authorable> newList = FXCollections.observableArrayList();
                        editableMap.put(added.getType(), newList);
                        TitledPane titledPane = getTitledPane(added.getType());
                        accordion.getPanes().add(titledPane);
                    }
                    editableMap.get(added.getType()).add(added);
                }
            }
        });
        for (Authorable authorable : observableList) {
            Placeable editable = (Placeable) authorable;
            try {
                editableMap.get(editable.getType()).add(editable);
            }
            catch (NullPointerException e) {

            }
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
