package gae.listView;

import gae.backend.Placeable;
import gae.gridView.ContainerWrapper;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;


/**
 * Abstract class to be extended to all Editable object's lists
 *
 * @author Kei & Nina
 *
 */
public class PaneList {
    private Map<Authorable, ObservableList<Authorable>> instancesEditableMap;
    private Group root;
    private StackPane stack;
    private ObservableList<Authorable> observableList;
    private ContainerWrapper wrapper;
    private String type;
    private Scene scene;
    private Node node;

    /**
     * Initializes the specific pane class
     *
     * @param root
     * @param node
     * @param scene
     * @param wrapper
     * @return
     */
    public TitledPane initialize (Group root,
                                  Node node,
                                  Scene scene,
                                  ContainerWrapper wrapper,
                                  ObservableList<Authorable> editableObservableList,
                                  String type) {
        this.root = root;
        this.node = node;
        stack = (StackPane) node;
        observableList = editableObservableList;
        this.wrapper = wrapper;
        this.type = type;
        this.scene = scene;
        instancesEditableMap = new HashMap<>();
        root.setManaged(false);
        TitledPane pane = getTitledPane(type);
        Accordion accordion = new Accordion();
        pane.setContent(accordion);
        ObservableList<TitledPane> paneList = accordion.getPanes();
        setUpObservableList(paneList);
        return pane;
    }

    public void removeRoot () {
        stack.getChildren().remove(root);
    }

    public void addRoot () {
        if (!stack.getChildren().contains(root)) {
            stack.getChildren().add(root);
        }
    }

    public Map<Authorable, ObservableList<Authorable>> getMap () {
        return instancesEditableMap;
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
        return pane;
    }

    private void setUpObservableList (ObservableList<TitledPane> paneList) {

        for (Authorable previous : observableList) {
            setUpNewInstanceList(paneList, previous);
        }
        observableList.addListener( (ListChangeListener.Change<? extends Authorable> change) -> {
            while (change.next()) {
                if (change.wasAdded()) { // if an editablenode was added
                    Authorable added = change.getAddedSubList().get(0);
                    setUpNewInstanceList(paneList, added);
                }
            }
        });
    }

    private void setUpNewInstanceList (ObservableList<TitledPane> paneList,
                                       Authorable authorable) {
        if (authorable.getType().equals(type)) {
            ObservableList<Authorable> instanceList =
                    FXCollections.observableArrayList();
            instancesEditableMap.put(authorable, instanceList);
            TitledPane newPane = setTitledPaneClick(authorable, instanceList);
            paneList.add(newPane);
        }
    }

    /**
     * sets up the clicking of the TitledPane such that one can instantiate an object by right
     * clicking the object. Also deals with binding the image to the cursor
     *
     * @param node
     * @param root
     * @param pane
     * @param scene
     * @param wrapper
     * @return
     */
    private TitledPane setTitledPaneClick (Authorable authorable,
                                           ObservableList<Authorable> instanceList) {
        TitledPane newPane = new TitledPane();
        Placeable editable = (Placeable) authorable;
        newPane.setText(editable.getTitle());
        newPane.setContent(ListViewUtilities.createList(instanceList, scene, "Editable"));
        newPane.setOnMousePressed(me -> {
            if (me.isSecondaryButtonDown()) {
                ContextMenu contextmenu = new ContextMenu();
                MenuItem item = new MenuItem("New");
                item.setOnAction(ae -> {
                    DraggableUtilities.makeEditablePlaceable(me, editable, node, instanceList,
                                                             wrapper, root);
                });
                contextmenu.getItems().add(item);
                contextmenu.show(newPane, me.getSceneX(), me.getSceneY());

            }
        });
        return newPane;
    }
}
