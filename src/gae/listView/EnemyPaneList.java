package gae.listView;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TitledPane;


public class EnemyPaneList extends PaneList {
    private ObservableList<TitledPane> enemyPaneList;
    private List<EditableNode> enemyEditablesList;
    private Group root;
    private Node scene;

    public EnemyPaneList () {
        enemyEditablesList = new ArrayList<>();
    }

    @Override
    public void addToGenericList (EditableNode node) {
        enemyEditablesList.add(node);
        TitledPane newPane = setTitledPaneClick(node, root, scene);
        enemyPaneList.add(newPane);
    }

    @Override
    public TitledPane initialize (Group root, Node scene) {
        this.root = root;
        this.scene = scene;
        TitledPane pane = getTitledPane("Enemy");
        enemyPaneList = setAccordion(pane);
        return pane;
    }

    @Override
    public String getType () {
        return "Enemy";
    }

}
