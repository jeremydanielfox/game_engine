package gae.listView;

import gae.backend.Editable;
import gae.gridView.ContainerWrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.StackPane;


/**
 * A subclass to keep track of Tower GameObjects that are made through the Editor.
 * 
 * @author Kei
 *
 */
public class TowerPaneList extends PaneList {
    private Map<EditableNode, ObservableList<Editable>> instancesEditableNodeMap;
    private Group root;
    private StackPane stack;
    private boolean added;
    private boolean initialized;
    private static final String TYPE = "Tower";

    public TowerPaneList () {
        instancesEditableNodeMap = new HashMap<>();
    }

    @Override
    public TitledPane initialize (Group root,
                                  Node node,
                                  Scene scene,
                                  ContainerWrapper wrapper,
                                  ObservableList<EditableNode> observableList) {
        this.root = root;
        root.setManaged(false);
        this.stack = (StackPane) node;
        TitledPane pane = getTitledPane(TYPE);
        ObservableList<TitledPane> towerPaneList = setAccordion(pane);
        setUpObservableList(towerPaneList, observableList, TYPE, instancesEditableNodeMap, root,
                            node, scene, wrapper);
        initialized = true;
        return pane;
    }

    @Override
    public void removeRoot () {
        if (initialized) {
            System.out.println("removing tower root");
            // does not work if path is added first before towers are added
            stack.getChildren().remove(root);
            added = true;
        }
    }

    @Override
    public void addRoot () {
        if (added && initialized) {
            System.out.println("adding tower root");
            stack.getChildren().add(root);
            added = false;
        }
    }

    @Override
    public Map<EditableNode, ObservableList<Editable>> getMap () {
        return instancesEditableNodeMap;
    }

}
