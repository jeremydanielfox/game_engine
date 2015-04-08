package gae.listView;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.control.TitledPane;


public class TowerPaneList extends PaneList {
    private ObservableList<TitledPane> towerPaneList;
    private List<EditableNode> towerEditablesList;

    public TowerPaneList () {
        towerEditablesList = new ArrayList<>();
    }

    @Override
    public void addToGenericList (EditableNode node) {
        towerEditablesList.add(node);
        // this is the TitledPane that Nina created
        // if we can make this a Node with all the information and attach it to this
        TitledPane newPane = new TitledPane();
        // newPane's content should be the node
        newPane.setText(node.getName());
        newPane.setContent(node);
        newPane.setOnMouseClicked(e -> {
            EditableNode editableNode = (EditableNode) newPane.getContent();
            // do things with editablenode
        });
        towerPaneList.add(newPane);
    }

    @Override
    public TitledPane setUpList () {
        TitledPane pane = getTitledPane("Tower");
        towerPaneList = setAccordion(pane);
        return pane;
    }

    @Override
    public String getType () {
        return "Tower";
    }

}
