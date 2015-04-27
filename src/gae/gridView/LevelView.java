package gae.gridView;

import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import voogasalad.util.pathsearch.graph.GridCell;
import gae.backend.Placeable;
import gae.gridView.TileViewToggle.TileMode;
import gae.listView.LibraryData;
import gae.listView.LibraryView;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
 * A class that instantiates the necessary components of the level. Contains the Library on the left
 * and grid/background in the center.
 * 
 * @author Kei and Nina
 *
 */
public class LevelView {
    private static final String DEFAULT_IMAGE_PATH = "/images/Park_Path.png";
    private static final Dimension DEFAULT_GRID_DIMENSIONS = new Dimension(20, 20);
    private static final int TEXT_BOX_WIDTH = 100;
    private StackPane stack;
    private Scene scene;
    private Pane border;
    private ObjectProperty<Image> backgroundProperty;
    private ObjectProperty<Dimension> gridSizeProperty;
    private ContainerWrapper wrapper;
    private LibraryView libraryview;
    private LibraryData libraryData;
    private TileViewToggle container;
    private VBox gridOptions;
    private ObjectProperty<TileMode> tileModeProperty =
            new SimpleObjectProperty<>(TileMode.TOWERMODE);
    private BiConsumer<List<GridCell>, List<GridCell>> setSpawn;
    private BiConsumer<List<GridCell>, List<GridCell>> setWalkable;

    private BooleanProperty isFreeWorld;

    public LevelView (BiConsumer<List<GridCell>, List<GridCell>> biconsumer,
                      BiConsumer<List<GridCell>, List<GridCell>> setWalkable,
                      BooleanProperty isFreeWorld) {
        this.setSpawn = biconsumer;
        this.setWalkable = setWalkable;
        this.isFreeWorld = isFreeWorld;
    }

    public Pane getBorder (Scene scene) {
        border = new Pane();
        border.getChildren().add(getStack(scene));
        border.getChildren().add(getLibraryView());
        border.prefHeightProperty().bind(scene.heightProperty());
        border.prefWidthProperty().bind(scene.widthProperty());
        return border;
    }

    public Image getBackgroundImage () {
        return backgroundProperty.get();
    }

    /**
     * Temporary method to pass in the PlaceableNode all the way to the LibraryView
     * 
     * @param node
     */
    public void getAddFunction (Placeable Placeable) {
        libraryData.addEditableToList(Placeable);
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
        ImageView background = new ImageView(new Image(DEFAULT_IMAGE_PATH));
        backgroundProperty = background.imageProperty();
        gridSizeProperty = new SimpleObjectProperty<>(DEFAULT_GRID_DIMENSIONS);
        Pane root = new Pane();
        container = new TileViewToggle(gridSizeProperty, scene);
        container.getTileModeProperty().bind(tileModeProperty);
        root.getChildren().addAll(background, container);

        stack.getChildren().addAll(root);
        root.setTranslateX(scene.getWidth() / 6);
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
        libraryview =
                new LibraryView();
        Group leftview =
                libraryview.getGroup(stack, scene, wrapper);
        setGridOptions();
        leftview.getChildren().add(gridOptions);
        return leftview;
    }

    /**
     * creates button to change the background. added into gridoptions
     * 
     * @return
     */
    private Button changeBackground (ObjectProperty<Image> backgroundProperty) {
        Button background = new Button("Change Background");
        background.setOnAction(e -> {
            Stage stage = new Stage();
            FileChooser fc = new FileChooser();
            File picked = fc.showOpenDialog(stage);
            backgroundProperty.setValue(new Image(picked.toURI().toString()));
        });
        return background;
    }

    /**
     * Sets location of VBox and fills grid options
     * 
     */
    private void setGridOptions () {
        gridOptions = new VBox();
        gridOptions.setTranslateX(scene.getWidth() * 2 / 3);
        gridOptions.setTranslateY(scene.getHeight() / 3);
        Text title = new Text("Grid Properties");

        gridOptions.getChildren().addAll(title, changeBackground(backgroundProperty));
        makeTileDimensions();
        List<ToggleButton> optionList =
                Arrays.asList(new ToggleButton[] {
                                                  makeToggleButton("Enemy Grid", TileMode.ENEMYMODE),
                                                  makeToggleButton("Tower Grid", TileMode.TOWERMODE),
                                                  makeToggleButton("Spawn Point",
                                                                   TileMode.STARTPOINTMODE),
                                                  makeToggleButton("End Point",
                                                                   TileMode.ENDPOINTMODE) });
        List<ToggleButton> gridOptionsList =
                Arrays.asList(new ToggleButton[] { makeToggleButton("Show Grid", true),
                                                  makeToggleButton("Hide Grid", false) });
        Node tileMode = makeToggle(optionList, (obs, old, newVal) -> {
            tileModeProperty.setValue((TileMode) newVal.getUserData());
        });
        gridOptions.getChildren().add(makeToggle(gridOptionsList, (obs, old, newVal) -> {
            container.setVisible((boolean) newVal
                    .getUserData());
            tileMode.setVisible((boolean) newVal
                    .getUserData());
        }));
        gridOptions.getChildren().addAll(tileMode, setWalkablePoints());
        isFreeWorld.addListener( (observable, oldv, newv) -> {
            boolean isFree = (boolean) newv;
            if (isFree) {
                gridOptions.getChildren().add(setSpawnPoints());
            }
        });
    }
    private Button setWalkablePoints() {
        Button button = new Button("Set Walkable Grids");
        button.setOnAction(e -> {
            List<GridCell> towerWalkable = new ArrayList<>();
            List<GridCell> enemyWalkable = new ArrayList<>();
            for (Point point : container.getTowerUnwalkable()) {
                towerWalkable.add(new GridCell(point.x, point.y));
            }
            for (Point point : container.getEnemyUnwalkable()) {
                enemyWalkable.add(new GridCell(point.x, point.y));
            }
            setWalkable.accept(towerWalkable, enemyWalkable);
        });
        return button;
    }
    private Button setSpawnPoints () {
        Button button = new Button("Set Spawn Points");

        button.setOnAction(e -> {
            List<GridCell> start = new ArrayList<>();
            List<GridCell> end = new ArrayList<>();
            for (Point point : container.getStartPoints()) {
                start.add(new GridCell(point.x, point.y));
            }
            for (Point point : container.getEndPoints()) {
                end.add(new GridCell(point.x, point.y));
            }
            setSpawn.accept(start, end);
        });
        return button;
    }

    /**
     * Makes fields for changing Grid dimensions. Called in setGridOptions()
     * 
     */
    private void makeTileDimensions () {
        Label widthLabel = new Label("# Rows");
        TextField setWidth = new NumberTextField();
        setWidth.setMaxWidth(TEXT_BOX_WIDTH);
        Label heightLabel = new Label("# Columns");
        TextField setHeight = new NumberTextField();
        setHeight.setMaxWidth(TEXT_BOX_WIDTH);
        GridPane grid = new GridPane();
        grid.add(widthLabel, 0, 0);
        grid.add(setWidth, 1, 0);
        grid.add(heightLabel, 0, 1);
        grid.add(setHeight, 1, 1);
        Button setDimensions = new Button("Change Grid Dimensions");
        setDimensions.setOnAction(e -> {
            int width =
                    setWidth.getText().isEmpty() ? DEFAULT_GRID_DIMENSIONS.width : Integer
                            .parseInt(setWidth.getText());
            int height =
                    setHeight.getText().isEmpty() ? DEFAULT_GRID_DIMENSIONS.height : Integer
                            .parseInt(setHeight.getText());
            gridSizeProperty.setValue(new Dimension(width, height));
        });
        gridOptions.getChildren().addAll(grid, setDimensions);
    }

    /**
     * Makes toggle. Called in setGridOptions() to make toggle for tilemode and showing grid
     * 
     * @param first ToggleButton
     * @param second ToggleButton
     * @param ChangeListener for what happens when toggle selected
     */

    private Node makeToggle (List<ToggleButton> list,
                             ChangeListener<? super Toggle> listener) {
        ToggleGroup group = new ToggleGroup();
        group.selectedToggleProperty().addListener(listener);
        HBox hbox = new HBox();
        for (ToggleButton button : list) {
            group.getToggles().add(button);
            hbox.getChildren().add(button);
        }
        return hbox;
    }

    /**
     * Makes a toggle button
     * 
     * @param name of button
     * @param data toggle holds
     * 
     */
    private ToggleButton makeToggleButton (String label, Object data) {
        ToggleButton button = new ToggleButton(label);
        button.setUserData(data);
        return button;
    }

    public Consumer<Placeable> getConsumer () {
        Consumer<Placeable> consumer = e -> libraryData.addEditableToList(e);
        return consumer;
    }

    public ObjectProperty<Dimension> getGridDimensionProperty () {
        return gridSizeProperty;
    }

    public String getBackgroundImagePath () {
        return DEFAULT_IMAGE_PATH;
    }
}
