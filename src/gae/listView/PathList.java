package gae.listView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import gae.gridView.Path;
import gae.gridView.PathView;
import engine.gameobject.PointSimple;
import engine.pathfinding.PathFixed;
import engine.pathfinding.PathSegmentBezier;
import gae.gridView.ContainerWrapper;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import xml.DataManager;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


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
    private int counter;
    private ObservableList<PathView> paths;
    private StackPane stack;
    private Scene scene;
    private int listIndex;
    private Button bezier;
    private Button completePath;
    private Button newPath;
    private Button displayPath;
    private Button updatePath;

    private List<Button> buttonList;

    public PathList (StackPane stack, Scene scene, ContainerWrapper container) {
        this.pathView = new PathView(stack, scene);
        pathView.setContainerArea(container);
        this.stack = stack;
        this.scene = scene;
        bezier = makeBezierCurve();
        completePath = completePath();
        newPath = newPath();
        displayPath = displayPaths();
        updatePath = updatePath();
        buttonList = new ArrayList<>();

        buttonList.addAll(Arrays.asList(new Button[] { bezier, completePath, newPath, displayPath,
                                                      updatePath }));
        stack.getChildren().addAll(bezier, completePath, newPath, displayPath, updatePath);
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

        listView.setItems(paths);
        listView.setOnMouseClicked(e -> {
            PathView selected = listView.getSelectionModel().getSelectedItem();
            setOldPath(selected);
            listIndex = listView.getSelectionModel().getSelectedIndex();
            scene.setOnKeyPressed(keyEvent -> {
                if (keyEvent.getCode().equals(KeyCode.BACK_SPACE)) {
                    paths.remove(selected);

                    allPaths.remove(listIndex);
                    selected.resetScreen();
                }
            });
        });
        listView.setCellFactory(new Callback<ListView<PathView>, ListCell<PathView>>() {
            @Override
            public ListCell<PathView> call (ListView<PathView> p) {
                ListCell<PathView> cell = new ListCell<PathView>() {
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
                return cell;
            }
        });
        listView.setMaxWidth(300);
        pane.setContent(listView);
        return pane;
    }

    /**
     * disables the screen by removing the root and disabling the path buttons
     */
    public void disableScreen () {
        pathView.resetScreen();
        for (Button button : buttonList) {
            button.setDisable(true);
        }
    }

    /**
     * sets the screen by resetting the screen, enabling the buttons, and reinstantiating a PathView
     * object
     */
    public void setScreen () {
        pathView.resetScreen();
        for (Button button : buttonList) {
            button.setDisable(false);
        }
        pathView = new PathView(stack, this.scene);
    }

    private void setOldPath (PathView view) {
        pathView.resetScreen();
        pathView = view;
        pathView.remakePath();
    }

    private Button makeBezierCurve () {
        Button makeCurve = new Button("Make Path");
        makeCurve.setTranslateX(400);
        makeCurve.setTranslateY(0);
        makeCurve.setOnMouseClicked(e -> {
            pathView.makeBezierCurve();
        });
        return makeCurve;
    }

    private Button completePath () {
        Button complete = new Button("Path Complete");
        complete.setTranslateX(400);
        complete.setTranslateY(50);
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
            pathView = new PathView(stack, this.scene);
        });
        return complete;
    }

    private Button newPath () {
        Button newPath = new Button("New Path");
        newPath.setTranslateX(400);
        newPath.setTranslateY(100);
        newPath.setOnMouseClicked(e -> {
            pathView.resetScreen();
            pathView = new PathView(stack, this.scene);
        });
        return newPath;
    }

    /**
     * This button must be clicked to not only display the path coordinates but to also write them
     * into a XML file
     */
    private Button displayPaths () {
        Button display = new Button("Display Paths");
        display.setTranslateX(400);
        display.setTranslateY(150);
        XStream xst = new XStream(new DomDriver());
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
                    points.add(new PointSimple(temp.getStart()));
                    points.add(new PointSimple(temp.getControlOne()));
                    points.add(new PointSimple(temp.getControlTwo()));
                    points.add(new PointSimple(temp.getEnd()));
                    tempBez.setPoints(points);
                    myPath.addPathSegment(tempBez);
                }
            }
            // *****************************************//
            DataManager.writeToXML(myPath, "src/gae/listView/TestDemo.xml");
        });

        return display;
    }

    /**
     * This button must be pressed in order to update a path that had already been instantiated
     */
    private Button updatePath () {
        Button update = new Button("Update Path");
        update.setTranslateX(400);
        update.setTranslateY(200);
        update.setOnMouseClicked(e -> {
            allPaths.set(listIndex, pathView.createPathObjects());
        });
        return update;
    }
}
