package gae.listView;

import gae.backend.Placeable;
import gae.gridView.ContainerWrapper;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.BooleanProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.StackPane;


/**
 * Class created to keep track of all the information stored in the Library, as well as display
 * them. The plan is to separate out the front-end component and the back-end storing component into
 * separate classes
 *
 * @author Kei
 *
 */
public class LibraryView {
    private List<PaneList> listOfListObjects;
    private Group root;
    private Group objectGroup;
    private Node nodeScene;
    private ObservableList<Authorable> editableObservableList;
    private List<String> instantiatedTypes;
    private Scene myScene;
    private Accordion accordion;
    private TitledPane pathTitledPane;
    private PathList pathList;
    private ContainerWrapper wrapper;

    public LibraryView () {
        instantiatedTypes = new ArrayList<>();
    }

    public Scene getScene () {
        root = new Group();
        root.getChildren().add(view());
        return new Scene(root);
    }

    /**
     * Obtains the group that contains the view, the buttons, and all the placed objects
     *
     * @param pane
     * @param scene
     * @param pathList
     * @param backgroundProperty
     * @param wrapper
     * @return
     */
    public Group getGroup (Node pane,
                           Scene scene,
                           ContainerWrapper wrapper) {
        nodeScene = pane;
        myScene = scene;
        this.wrapper = wrapper;
        root = new Group();
        objectGroup = new Group();
        root.getChildren().addAll(view(),
                                  objectGroup);
        root.setManaged(false);
        return root;
    }

    private void setUpTitledPane (Authorable authorable) {
        String type = authorable.getType();
        if (!instantiatedTypes.contains(type)) {
            instantiatedTypes.add(type);
            PaneList paneList = new PaneList();
            listOfListObjects.add(paneList);
            TitledPane pane = paneList.initialize(objectGroup, nodeScene, myScene,
                                                  wrapper,
                                                  editableObservableList,
                                                  type);
            pane.setOnMouseClicked(event -> {
                if (!pane.equals(pathTitledPane) && event.getClickCount() == 1) {
                    for (PaneList list : listOfListObjects) {
                        list.addRoot();
                    }
                    pathList.disableScreen();
                }
            });
            accordion.getPanes().add(pane);
        }
    }

    /**
     * Uses reflection to instantiate each GameObject's Pane subclass. Currently using a try/catch
     * block as Path isn't part of the Generic GameObjects that we're using. Trying to figure out
     * more common traits to be able to combine them.
     *
     * @return
     */
    private Node view () {
        listOfListObjects = new ArrayList<>();
        accordion = new Accordion();
        editableObservableList = LibraryData.getInstance().getEditableObservableList();
        for (Authorable authorable : editableObservableList) {
            Placeable existing = (Placeable) authorable;
            setUpTitledPane(existing);
        }
        editableObservableList
                .addListener( (ListChangeListener.Change<? extends Authorable> change) -> {
                    while (change.next()) {
                        if (change.wasAdded()) { // if an editablenode was added
                    Placeable added = (Placeable) change.getAddedSubList().get(0);
                    setUpTitledPane(added);
                    setUpToggle();
                }
            }
        });
        pathList = new PathList((StackPane) nodeScene, myScene, wrapper);
        pathTitledPane = pathList.getTitledPane("Path");
        pathTitledPane.setOnMousePressed(event -> {
            for (PaneList list : listOfListObjects) {
                list.removeRoot();
            }
            pathList.setScreen();
        });
        accordion.getPanes().add(pathTitledPane);
//        setUpToggle();
        return accordion;
    }

    /**
     * Sets up the clicking such that when the Path TitledPane is clicked, all the GameObjects are
     * gone (as it clutters the screen). On the other hand, when a GameObject TitledPane is being
     * clicked, the paths disappear.
     */
    private void setUpToggle () {
        pathTitledPane.setOnMousePressed(event -> {
            for (PaneList list : listOfListObjects) {
                list.removeRoot();
            }
            pathList.setScreen();
        });
        for (int i = 0; i < accordion.getPanes().size(); i++) {
            TitledPane chosen = accordion.getPanes().get(i);
            chosen.setOnMouseClicked(event -> {
                if (!chosen.equals(pathTitledPane) && event.getClickCount() == 1) {
                    for (PaneList list : listOfListObjects) {
                        list.addRoot();
                    }
                    pathList.disableScreen();
                }
            });
        }
    }

}
