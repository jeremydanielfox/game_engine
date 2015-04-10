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


public class EnemyPaneList extends PaneList {
    private ObservableList<TitledPane> enemyPaneList;
    private List<EditableNode> enemyEditablesList;
    private Group root;
    private Node workspace;
    private Scene scene;
    private boolean added;
    private StackPane stack;
    private ContainerWrapper wrapper;
    private boolean initialized;

    public EnemyPaneList () {
        enemyEditablesList = new ArrayList<>();
    }

    @Override
    public void addToGenericList (EditableNode editableNode) {
        enemyEditablesList.add(editableNode);
        TitledPane newPane = setTitledPaneClick(editableNode, root, workspace, scene, wrapper);
        enemyPaneList.add(newPane);
        initialized = true;
    }

    @Override
    public TitledPane initialize (Group root, Node node, Scene scene, ContainerWrapper wrapper) {
        this.root = root;
        root.setManaged(false);
        this.workspace = node;
        this.scene = scene;
        this.stack = (StackPane) workspace;
        this.wrapper = wrapper;
        TitledPane pane = getTitledPane("Enemy");
        enemyPaneList = setAccordion(pane);
        return pane;
    }

    @Override
    public String getType () {
        return "Enemy";
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
