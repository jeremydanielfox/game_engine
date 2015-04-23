package gae.listView;

import gae.backend.Editable;
import gae.gridView.ContainerWrapper;
import gae.gridView.PathView;
import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
 * Class created to keep track of all the information stored in the Library, as well as display
 * them. The plan is to separate out the front-end component and the back-end storing component into
 * separate classes
 * 
 * @author Kei
 *
 */
public class LibraryView {
    /*
     * should be able to get the gameObjects list from a properties file
     */
    private String[] gameObjects = { "Tower", "Enemy" };
    private List<PaneList> listOfListObjects;
    private Group root;
    private Group objectGroup;
    private Node nodeScene;
    private ObservableList<PathView> pathObservableList;
    private ObservableList<Editable> editableObservableList;
    private Scene myScene;
    private Accordion accordion;
    private TitledPane pathTitledPane;
    private PathList pathList;
    private ContainerWrapper wrapper;

    public LibraryView (ObservableList<Editable> editableObservableList) {
        this.editableObservableList = editableObservableList;
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
                           ObservableList<PathView> pathList,
                           ContainerWrapper wrapper) {
        this.nodeScene = pane;
        this.pathObservableList = pathList;
        this.myScene = scene;
        this.wrapper = wrapper;
        root = new Group();
        objectGroup = new Group();
        root.getChildren().addAll(view(),
                                  objectGroup);
        root.setManaged(false);
        return root;
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
        for (int i = 0; i < gameObjects.length; i++) {
            PaneList paneList = new PaneList();
            listOfListObjects.add(paneList);
            accordion.getPanes().add(paneList.initialize(objectGroup, nodeScene, myScene, wrapper,
                                                         editableObservableList, gameObjects[i]));

        }
        pathList = new PathList((StackPane) nodeScene, myScene, wrapper);
        pathTitledPane =
                pathList.getTitledPane(pathObservableList, "Path");
        accordion.getPanes().add(pathTitledPane);
        setUpToggle();
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
