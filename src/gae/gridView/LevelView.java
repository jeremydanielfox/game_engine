package gae.gridView;

import gae.backend.Editable;
import gae.backend.TempEnemy;
import gae.backend.TempTower;
import gae.listView.LibraryData;
import gae.listView.LibraryView;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;


/**
 * A class that instantiates the necessary components of the level. Contains the Library on the left
 * and grid/background in the center.
 * 
 * @author Kei
 *
 */
public class LevelView {
    private StackPane stack;
    private Scene scene;
    private BorderPane border;
    private ObjectProperty<Image> backgroundProperty;
    private ContainerWrapper wrapper;
    private ObservableList<PathView> paths =
            FXCollections.observableArrayList();
    private LibraryView libraryview;
    private LibraryData libraryData;

    public BorderPane getBorder (Scene scene) {
        border = new BorderPane();
        border.setCenter(getStack(scene));
        border.setLeft(getLibraryView());
        return border;
    }

    public Image getBackgroundImage () {
        return backgroundProperty.get();
    }

    /**
     * Temporary method to pass in the EditableNode all the way to the LibraryView
     * 
     * @param node
     */
    public void getAddFunction (Editable editable) {
        libraryData.addToList(editable);
    }

    /**
     * Creates a StackPane that includes the background image and the TileContainer, put together in
     * a Group. It's put in a Group so that it's easy to modify.
     * 
     * @param scene
     * @return
     */
    private StackPane getStack (Scene scene) {
        stack = new StackPane();
        this.scene = scene;
        ImageView background = new ImageView(new Image("/images/Park_Path.png"));
        backgroundProperty = background.imageProperty();
        Group root = new Group();
        TileContainer container = new TileContainer(20, border);
        root.getChildren().addAll(background, container, tempGrid());

        stack.getChildren().addAll(root);

        background.fitWidthProperty().bind(container.getGridWidthProperty());
        background.fitHeightProperty().bind(container.getGridHeightProperty());

        wrapper = (ContainerWrapper) container;
        return stack;
    }

    /**
     * creates a Group from the LibraryView, which contains the different buttons, the Accordion
     * view, as well as all the objects in one group so that it's easily hidden/unhidden.
     * 
     * @return
     */
    private Group getLibraryView () {
        libraryData = LibraryData.getInstance();
        libraryview = new LibraryView(libraryData.getObservableList());
        Group leftview =
                libraryview.getGroup(stack, scene, paths, backgroundProperty, wrapper);
        // TODO: can't do the above since it messes up the coordinates - got to fix
        return leftview;
    }

    private GridPane tempGrid () {
        GridPane grid = new GridPane();
        grid.setHgap(0);
        grid.setTranslateX(500);
        grid.add(tempButtonTower(), 0, 0);
        grid.add(tempButtonEnemy(), 0, 1);
        return grid;
    }

    private Button tempButtonTower () {
        Button temp = new Button("add Tower");
        Editable editable = new TempTower();
        temp.setOnAction(e -> libraryData.addToList(editable));
        return temp;
    }

    private Button tempButtonEnemy () {
        Button temp = new Button("add Enemy");
        Editable editable = new TempEnemy();
        temp.setOnAction(e -> libraryData.addToList(editable));
        return temp;
    }
}
