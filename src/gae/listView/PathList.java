package gae.listView;

import engine.gameobject.PointSimple;
import engine.pathfinding.PathFixed;
import engine.pathfinding.PathSegmentBezier;
import gae.gridView.ContainerWrapper;
import gae.gridView.AuthoringPath;
import gae.gridView.PathView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.beans.property.BooleanProperty;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
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
    private List<List<AuthoringPath>> allPaths;
    private List<PathView> previousPaths;
    private static int counter;
    private ObservableList<Authorable> paths;
    private StackPane stack;
    private Scene scene;
    private int listIndex;
    private List<Boolean> buttonStates;
    private Button bezier;
    private Button completePath;
    private Button newPath;
    private Button updatePath;

    private List<Button> buttonList;

    public PathList (StackPane stack,
                     Scene scene,
                     ContainerWrapper container) {
        pathView = new PathView(stack, scene);
        pathView.setContainerArea(container);
        this.stack = stack;
        this.scene = scene;
        bezier = makeBezierCurve();
        completePath = completePath();
        newPath = newPath();
        updatePath = updatePath();
        buttonList = new ArrayList<>();
        buttonStates = new ArrayList<>();

        buttonList
                .addAll(Arrays.asList(new Button[] { bezier, completePath, newPath, updatePath }));
        stack.getChildren().add(makeGridPane());
    }

    /**
     * Creates the TitledPane for path and sets it such that objects can be removed. Looking to
     * refactor this code soon.
     */
    public TitledPane getTitledPane (String text) {
        paths = LibraryData.getInstance().getPathObservableList();
        TitledPane pane = new TitledPane();
        pane.setText(text);
        pane.setTextFill(Color.RED);

        final ListView<Authorable> listView = ListViewUtilities.createList(paths, scene, "Path");
        listView.setOnMouseClicked(e -> {
            PathView selected = (PathView) listView.getSelectionModel().getSelectedItem();
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
            List<AuthoringPath> path = pathView.createPathObjects();
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
