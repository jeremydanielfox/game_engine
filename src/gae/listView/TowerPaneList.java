package gae.listView;

import gae.backend.TempTower;
import java.util.ArrayList;
import java.util.List;
import View.ViewUtilities;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;


public class TowerPaneList extends PaneList {
    private ObservableList<TitledPane> towerPaneList;
    private List<EditableNode> towerEditablesList;
    private Group root;
    private Node scene;

    public TowerPaneList () {
        towerEditablesList = new ArrayList<>();
    }

    @Override
    public void addToGenericList (EditableNode node) {
        towerEditablesList.add(node);
        // this is the TitledPane that Nina created
        // if we can make this a Node with all the information and attach it to this
        TitledPane newPane = setTitledPaneClick(node, root, scene);
        
        towerPaneList.add(newPane);
    }

    @Override
    public TitledPane initialize (Group root, Node scene) {
        this.root = root;
        this.scene = scene;
        TitledPane pane = getTitledPane("Tower");
        towerPaneList = setAccordion(pane);
        return pane;
    }

    @Override
    public String getType () {
        return "Tower";
    }

}
