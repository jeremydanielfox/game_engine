package gae.listView;

import gae.backend.TempTower;
import gae.gridView.ContainerWrapper;
import gae.gridView.PathView;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
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
    private String[] gameObjects = { "Tower", "Enemy", "Path" };
    private List<PaneList> listOfListObjects;
    private Group root;
    private Group objectGroup;
    private Node nodeScene;
    private ObservableList<PathView> pathObservableList;
    private ObservableList<EditableNode> editableNodeObservableList;
    private Scene myScene;
    private Accordion accordion;
    private TitledPane pathTitledPane;
    private PathList pathList;
    private ContainerWrapper wrapper;

    public LibraryView (ObservableList<EditableNode> editableNodeObservableList) {
        this.editableNodeObservableList = editableNodeObservableList;
    }

    public Scene getScene () {
        root = new Group();
        // root.getChildren().addAll(view(), tempButton());
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
                           ObjectProperty<Image> backgroundProperty, ContainerWrapper wrapper) {
        this.nodeScene = pane;
        this.pathObservableList = pathList;
        this.myScene = scene;
        this.wrapper = wrapper;
        root = new Group();
        objectGroup = new Group();
        root.getChildren().addAll(view(), changeBackground(backgroundProperty),
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
            try {
                Class<?> className = Class.forName("gae.listView." + gameObjects[i] + "PaneList");
                Object instance = className.getConstructor().newInstance();
                listOfListObjects.add((PaneList) instance);
                Method setUpList =
                        className.getMethod("initialize", Group.class, Node.class, Scene.class,
                                            ContainerWrapper.class, ObservableList.class);
                accordion.getPanes().add((TitledPane) setUpList
                        .invoke(instance, objectGroup, nodeScene, myScene,
                                wrapper, editableNodeObservableList));
            }
            catch (ClassNotFoundException | NoSuchMethodException | InstantiationException
                    | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException e) {
                pathList = new PathList((StackPane) nodeScene, myScene, wrapper);
                pathTitledPane =
                        pathList.getTitledPane(pathObservableList, gameObjects[i]);
                accordion.getPanes().add(pathTitledPane);
            }
        }
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
        // for (TitledPane panes : accordion.getPanes()) {

        for (int i = 0; i < accordion.getPanes().size(); i++) {
            TitledPane chosen = accordion.getPanes().get(i);
            chosen.setOnMouseClicked(event -> {
                if (!chosen.equals(pathTitledPane) && event.getClickCount() == 1) {
                    for (PaneList list : listOfListObjects) {
                        list.addRoot();
                    }
                    // attempt at trying to
                    // PaneList correct =
                    // listOfListObjects.get(accordion.getPanes().indexOf(chosen));
                    // correct.addRoot();
                    // for (PaneList lists : listOfListObjects) {
                    // if (!correct.equals(lists))
                    // lists.removeRoot();
                    // }
                    pathList.disableScreen();
                    System.out.println("removing path!");
                }
            });
        }
    }

    // /**
    // * method used to add an EditableNode to its specific list
    // *
    // * @param node
    // */
    // public void addToList (EditableNode node) {
    // for (PaneList paneList : listOfListObjects) {
    // if (paneList.getType().equals(node.getType())) {
    // paneList.addToGenericList(node);
    // }
    // }
    // }

    private Button changeBackground (ObjectProperty<Image> backgroundProperty) {
        Button background = new Button("Change Background");
        background.setTranslateX(0);
        background.setTranslateY(550);
        background.setOnAction(e -> {
            Stage stage = new Stage();
            FileChooser fc = new FileChooser();
            File picked = fc.showOpenDialog(stage);
            backgroundProperty.setValue(new Image(picked.toURI().toString()));
        });
        return background;
    }
}
