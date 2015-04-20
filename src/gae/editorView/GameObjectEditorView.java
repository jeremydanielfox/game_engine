package gae.editorView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
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
    private ObservableList<Node> optionList = FXCollections.observableArrayList();
    private String[] imagePaths = { "/images/WeaponImage.png", "/images/HealthImage.jpeg",
                                   "/images/PathImage.png" };
    private String[] settable = { "Weapon", "Health", "Mover" };
    private Group root;
    private Scene scene;
    private BorderPane border;
    private static final int TAB_HEIGHT = 160;
    private static final int SIDE_WIDTH = 430;
    private GameObjectContainer bottom;
    private AnchorPane anchor;

    public GameObjectEditorView (Scene scene) {
        root = new Group();
        root.setManaged(false);
        this.scene = scene;
    }

    private BorderPane setUpBorder () {
        border = new BorderPane();

        border.setRight(setUpList());
        border.setCenter(setUpAnchor());

        return border;
    }

    private AnchorPane setUpAnchor () {
        double vboxHeight = (Screen.getPrimary().getVisualBounds().getHeight() - TAB_HEIGHT) / 2;
        double vboxWidth = Screen.getPrimary().getVisualBounds().getWidth() - SIDE_WIDTH;

        VBox top = new VBox();
        top.setPrefSize(vboxWidth, vboxHeight);

        bottom = new GameObjectContainer(vboxWidth, vboxHeight);
        bottom.setPrefSize(vboxWidth, vboxHeight);
        bottom.getChildren().add(root);

        ScrollPane topHalf = new ScrollPane();
        topHalf.setContent(top);
        ScrollPane bottomHalf = new ScrollPane();
        bottomHalf.setContent(bottom);
        anchor = new AnchorPane(topHalf, bottomHalf);

        AnchorPane.setTopAnchor(topHalf, 0.0);
        AnchorPane.setBottomAnchor(bottomHalf, 5.0);
        return anchor;
    }

    private ListView<Node> setUpList () {
        ListView<Node> list = ListViewUtilities.createList(optionList);
        for (String path : imagePaths) {
            optionList.add(new ImageView(path));
        }
        list.setOnMouseClicked(me -> {
            ImageView selected = (ImageView) list.getSelectionModel().getSelectedItem();

            for (int i = 0; i < bottom.getRectangles().size(); i++) {
                if (i == list.getSelectionModel().getSelectedIndex()) {
                    DraggableUtilities.makeImagePlaceable(me, selected, bottom, bottom
                            .getRectangles().get(i), root);
                }
            }
        });
        return list;
    }

    @Override
    public Node getObject () {
        // TODO Auto-generated method stub
        return setUpBorder();
    }

}
