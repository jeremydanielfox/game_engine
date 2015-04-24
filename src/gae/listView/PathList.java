package gae.listView;

import engine.gameobject.PointSimple;
import engine.pathfinding.PathFixed;
import engine.pathfinding.PathSegmentBezier;
import gae.gridView.ContainerWrapper;
import gae.gridView.Path;
import gae.gridView.PathView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import xml.DataManager;


/**
 * A list that keeps track of Path objects and previously created PathViews. Goal is to separate the
 * storing of back-end and front-end objects into separate classes
 *
 * @author Kei
 *
 */
public class PathList {
    private PathView pathView;
    private List<List<Path>> allPaths;
    private List<PathView> previousPaths;
    private static int counter;
    private ObservableList<PathView> paths;
    private StackPane stack;
    private Scene scene;
    private int listIndex;
    private List<Boolean> buttonStates;
    private Button bezier;
    private Button completePath;
    private Button newPath;
    private Button displayPath;
    private Button updatePath;

    private List<Button> buttonList;

    public PathList (StackPane stack, Scene scene, ContainerWrapper container) {
        pathView = new PathView(stack, scene);
        pathView.setContainerArea(container);
        this.stack = stack;
        this.scene = scene;
        bezier = makeBezierCurve();
        completePath = completePath();
        newPath = newPath();
        displayPath = displayPaths();
        updatePath = updatePath();
        buttonList = new ArrayList<>();
        buttonStates = new ArrayList<>();

        buttonList.addAll(Arrays.asList(new Button[] { bezier, completePath, newPath, displayPath,
                                                       updatePath }));
        // stack.getChildren().addAll(bezier, completePath, newPath, displayPath, updatePath);
        stack.getChildren().add(makeGridPane());
    }

    /**
     * Creates the TitledPane for path and sets it such that objects can be removed. Looking to
     * refactor this code soon.
     */
    public TitledPane getTitledPane (ObservableList<PathView> paths, String text) {
        this.paths = paths;
        TitledPane pane = new TitledPane();
        pane.setText(text);
        pane.setTextFill(Color.RED);

        final ListView<PathView> listView = new ListView<>(paths);
        listView.setPrefSize(200, 250);

        listView.setEditable(true);

        listView.setOnMouseClicked(e -> {
            PathView selected = listView.getSelectionModel().getSelectedItem();
            setOldPath(selected);
            listIndex = listView.getSelectionModel().getSelectedIndex();
            scene.setOnKeyPressed(keyEvent -> {
                if (keyEvent.getCode().equals(KeyCode.BACK_SPACE)) {
                    paths.remove(selected);

                    allPaths.remove(listIndex);
                    selected.resetScreen(stack);
                }
            });
        });
        listView.setCellFactory( (myList) -> {
            return new ListCell<PathView>() {
                @Override
                protected void updateItem (PathView pathView, boolean bln) {
                    super.updateItem(pathView, bln);
                    if (bln) {
                        setText(null);
                        setGraphic(null);
                    }
                    else if (pathView != null) {
                        HBox content = new HBox();
                        content.getChildren().add(new Label("Path " + pathView.getID()));
                        setGraphic(content);
                    }
                }
            };
        });
        listView.setMaxWidth(300);
        pane.setContent(listView);
        return pane;
    }

    /**
     * disables the screen by removing the root and disabling the path buttons
     */
    public void disableScreen () {
        pathView.resetScreen(stack);
        for (Button button : buttonList) {
            buttonStates.add(button.disableProperty().get());
            button.setDisable(true);
        }
    }

    /**
     * sets the screen by resetting the screen, enabling the buttons, and reinstantiating a PathView
     * object
     */
    public void setScreen () {
        pathView.resetScreen(stack);
        try {
            for (int i = 0; i < buttonList.size(); i++) {
                buttonList.get(i).setDisable(buttonStates.get(i));
            }
        }
        catch (IndexOutOfBoundsException e) {
            // means Path Pane was clicked first
        }
        pathView = new PathView(stack, scene);
    }

    private void setOldPath (PathView view) {
        pathView.resetScreen(stack);
        pathView = view;
        pathView.remakePath(stack);
    }

    private Button makeBezierCurve () {
        Button makeCurve = new Button();
        Label label = new Label("Make Path");
        makeCurve.setGraphic(label);
        makeCurve.setOnMouseClicked(e -> {
            pathView.makeBezierCurve();
            label.textProperty().bind(pathView.addPathInstructionsProperty());
            pathView.addPathInstructionsProperty().setValue("Place Starting Point");
            label.setTextFill(Color.RED);
            pathView.addPathProperty().addListener( (observable, oldValue, newValue) -> {
                if (newValue.intValue() == 0) {
                    makeCurve.setDisable(true);
                }
                else {
                    makeCurve.setDisable(false);
                    label.setTextFill(Color.BLACK);
                }
            });
        });
        return makeCurve;
    }

    private Button completePath () {
        Button complete = new Button("Add to Path List");
        allPaths = new ArrayList<>();
        previousPaths = new ArrayList<>();
        complete.setOnMouseClicked(e -> {
            List<Path> path = pathView.createPathObjects();
            previousPaths.add(pathView);
            allPaths.add(path);
            System.out.println("ADDING INDEX : " + paths.size());
            paths.add(pathView);
            pathView.setID(counter);
            counter++;
            pathView = new PathView(stack, scene);
            completePath.setDisable(true);
        });
        return complete;
    }

    private Button newPath () {
        Button newPath = new Button("Make New Path");
        newPath.setOnMouseClicked(e -> {
            pathView.resetScreen(stack);
            pathView = new PathView(stack, scene);
            completePath.setDisable(false);
        });
        return newPath;
    }

    /**
     * This button must be clicked to not only display the path coordinates but to also write them
     * into a XML file
     */
    private Button displayPaths () {
        Button display = new Button("make XML");
        // XStream xst = new XStream(new DomDriver());
        display.setOnMouseClicked(e -> {
            // this is the information that'll be passed into XML (allPaths)
            // *****************************************//
            System.out.println("PRINTING OUT ALLPATHS LIST!");
            PathFixed myPath = new PathFixed();
            for (List<Path> lists : allPaths) {

                for (int i = 0; i < lists.size(); i++) {
                    // System.out.println("Path " + i + "'s coordinates");
                    Path temp = lists.get(i);
                    temp.printInfo();
                    // System.out.println();
                    PathSegmentBezier tempBez = new PathSegmentBezier();
                    List<PointSimple> points = new ArrayList<>();
                    points.add(temp.getStart());
                    points.add(temp.getControlOne());
                    points.add(temp.getControlTwo());
                    points.add(temp.getEnd());
                    tempBez.setPoints(points);
                    myPath.addPathSegment(tempBez);
                }
            }
            // *****************************************//
            DataManager.writeToXML(myPath, "src/gae/listView/Test.xml");
        });

        return display;
    }

    /**
     * This button must be pressed in order to update a path that had already been instantiated
     */
    private Button updatePath () {
        Button update = new Button("Update Path List");
        update.setDisable(true);
        completePath.disableProperty().addListener(e -> {
            update.setDisable(!completePath.disabledProperty().get());
        });
        update.setOnMouseClicked(e -> {
            allPaths.set(listIndex, pathView.createPathObjects());
        });
        return update;
    }

    private GridPane makeGridPane () {
        GridPane grid = new GridPane();
        grid.setHgap(0);
        grid.setTranslateX(scene.getWidth() * 2 / 3);
        for (int i = 0; i < buttonList.size(); i++) {
            grid.add(buttonList.get(i), 0, i);
        }
        return grid;
    }
}
