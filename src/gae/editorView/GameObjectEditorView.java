package gae.editorView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import gae.backend.Editable;
import gae.backend.TempTower;
import gae.gridView.ContainerWrapper;
import gae.listView.DraggableUtilities;
import gae.listView.ListViewUtilities;
import gae.openingView.UIObject;


public class GameObjectEditorView implements UIObject {
    private ObservableList<Editable> createdList = FXCollections.observableArrayList();
    private Group root;
    private Scene scene;
    private BorderPane border;
    private static final int TAB_HEIGHT = 128;

    public GameObjectEditorView (Scene scene) {
        root = new Group();
        this.scene = scene;
    }

    private BorderPane setUpBorder () {
        border = new BorderPane();
        
        border.setRight(setUpList());
        border.setCenter(setUpAnchor());
        

        return border;
    }

    private AnchorPane setUpAnchor () {

        ScrollPane topHalf = new ScrollPane();
        VBox top = new VBox();
        // top.setPrefSize(arg0, arg1);
        double vboxHeight = (Screen.getPrimary().getVisualBounds().getHeight() - TAB_HEIGHT) / 2;
        top.setPrefHeight(vboxHeight);
        VBox bottom = new VBox();
        bottom.setPrefHeight(vboxHeight);
        topHalf.setContent(top);
        System.out.println(border.getPrefWidth());
        ScrollPane bottomHalf = new GameObjectContainer();
        bottomHalf.setContent(bottom);
        AnchorPane anchor = new AnchorPane(topHalf, bottomHalf);
        AnchorPane.setTopAnchor(topHalf, 0.0);
        AnchorPane.setBottomAnchor(bottomHalf, 0.0);
        return anchor;
    }

    private ListView<Editable> setUpList () {
        ListView<Editable> list = ListViewUtilities.createList(createdList);
        createdList.add(new TempTower());
        createdList.add(new TempTower());
        createdList.add(new TempTower());
        createdList.add(new TempTower());
        createdList.add(new TempTower());
        createdList.add(new TempTower());
        createdList.add(new TempTower());
        createdList.add(new TempTower());
        list.setOnMouseClicked(me -> {
            Editable selected = list.getSelectionModel().getSelectedItem();
            // DraggableUtilities.makeEditablePlaceable(me, selected, anchor, createdList, wrapper,
            // root);
        });
        return list;
    }

    @Override
    public Node getObject () {
        // TODO Auto-generated method stub
        return setUpBorder();
    }

}
