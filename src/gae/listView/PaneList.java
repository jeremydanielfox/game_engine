package gae.listView;

import java.util.HashMap;
import java.util.Map;
import gae.backend.Editable;
import View.ViewUtil;
import exception.ObjectOutOfBoundsException;
import gae.gridView.ContainerWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;


/**
 * Abstract class to be extended to all Editable object's lists
 * 
 * @author Kei & Nina
 *
 */
public class PaneList {
    private Map<Editable, ObservableList<Editable>> instancesEditableMap;
    private Group root;
    private StackPane stack;
    private ObservableList<Editable> observableList;
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
                                  ObservableList<Editable> editableObservableList,
                                  String type) {
        this.root = root;
        this.node = node;
        this.stack = (StackPane) node;
        this.observableList = editableObservableList;
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

    public Map<Editable, ObservableList<Editable>> getMap () {
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

        for (Editable previous : observableList) {
            setUpNewInstanceList(paneList, previous);
        }
        observableList.addListener( (ListChangeListener.Change<? extends Editable> change) -> {
            while (change.next()) {
                if (change.wasAdded()) { // if an editablenode was added
                    Editable added = (Editable) change.getAddedSubList().get(0);
                    setUpNewInstanceList(paneList, added);
                }
            }
        });
    }

    private void setUpNewInstanceList (ObservableList<TitledPane> paneList,
                                       Editable editable) {
        if (editable.getType().equals(type)) {
            ObservableList<Editable> instanceList =
                    FXCollections.observableArrayList();
            instancesEditableMap.put(editable, instanceList);
            TitledPane newPane = setTitledPaneClick(editable, instanceList);
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
    private TitledPane setTitledPaneClick (Editable editable,
                                           ObservableList<Editable> instanceList) {
        TitledPane newPane = new TitledPane();
        newPane.setText(editable.getName());
        newPane.setContent(ListViewUtilities.createList(instanceList, scene));
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
