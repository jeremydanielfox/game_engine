package gae.listView;

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

    public EnemyPaneList () {
        enemyEditablesList = new ArrayList<>();
    }

    @Override
    public void addToGenericList (EditableNode editableNode) {
        enemyEditablesList.add(editableNode);
        TitledPane newPane = setTitledPaneClick(editableNode, root, workspace, scene);
        enemyPaneList.add(newPane);
    }

    @Override
    public TitledPane initialize (Group root, Node node, Scene scene) {
        this.root = root;
        this.workspace = node;
        this.scene = scene;
        this.stack = (StackPane) node;
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
        stack.getChildren().remove(root);
        added = false;
    }

    @Override
    public void addRoot () {
        if (!added) {
            stack.getChildren().add(root);
            added = true;
        }
    }

}
