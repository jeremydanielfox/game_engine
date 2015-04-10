package gae.listView;

import gae.gridView.ContainerWrapper;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.StackPane;


public class TowerPaneList extends PaneList {
    private ObservableList<TitledPane> towerPaneList;
    private List<EditableNode> towerEditablesList; // the list of types of Towers
    private Group root;
    private Node workspace;
    private Scene scene;
    private StackPane stack;
    private boolean added;
    private boolean initialized;
    private ContainerWrapper wrapper;

    public TowerPaneList () {
        towerEditablesList = new ArrayList<>();
    }

    @Override
    public void addToGenericList (EditableNode editableNode) {
        towerEditablesList.add(editableNode);
        TitledPane newPane = setTitledPaneClick(editableNode, root, workspace, scene, wrapper);
        towerPaneList.add(newPane);
        initialized = true;
    }

    @Override
    public TitledPane initialize (Group root, Node node, Scene scene, ContainerWrapper wrapper) {
        this.root = root;
        root.setManaged(false);
        this.workspace = node;
        this.stack = (StackPane) workspace;
        this.scene = scene;
        this.wrapper = wrapper;
        TitledPane pane = getTitledPane("Tower");
        towerPaneList = setAccordion(pane);
        return pane;
    }

    @Override
    public String getType () {
        return "Tower";
    }

    @Override
    public void removeRoot () {
        if (initialized) {
            System.out.println("removing root");
            // does not work if path is added first before towers are added
            stack.getChildren().remove(root);
            added = true;
        }
    }

    @Override
    public void addRoot () {
        if (added && initialized) {
            System.out.println("adding root");
            stack.getChildren().add(root);
            added = false;
        }
    }

}
