package gae.listView;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.control.TitledPane;


public class EnemyPaneList extends PaneList {
    private ObservableList<TitledPane> enemyPaneList;
    private List<EditableNode> enemyEditablesList;

    public EnemyPaneList () {
        enemyEditablesList = new ArrayList<>();
    }

    @Override
    public void addToGenericList (EditableNode node) {
        enemyEditablesList.add(node);
        TitledPane newPane = new TitledPane();
        newPane.setText(node.getName());
        // newPane's content should be the node
        enemyPaneList.add(newPane);
    }

    @Override
    public TitledPane setUpList () {
        TitledPane pane = getTitledPane("Enemy");
        enemyPaneList = setAccordion(pane);
        return pane;
    }

    @Override
    public String getType () {
        return "Enemy";
    }

}
