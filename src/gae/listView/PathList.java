package gae.listView;

import java.util.ArrayList;
import java.util.List;
import gae.gridView.Path;
import gae.gridView.PathView;
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


public class PathList {
    private PathView pathView;
    private List<List<Path>> allPaths;
    private List<PathView> previousPaths;
    private int counter;
    private ObservableList<PathView> paths;
    private StackPane stack;
    private Scene scene;
    private int listIndex;

    public PathList (PathView pathView, StackPane stack, Scene scene) {
        this.pathView = pathView;
        this.stack = stack;
        this.scene = scene;
        stack.getChildren().addAll(makeBezierCurve(), completePath(), newPath(), displayPaths(),
                                   updatePath());
    }

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
                            // TODO : Nina - add a counter to the path
                            content.getChildren().add(new Label("Path"));
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

    private Button displayPaths () {
        Button display = new Button("Display Paths");
        display.setTranslateX(400);
        display.setTranslateY(150);
        display.setOnMouseClicked(e -> {
            // this is the information that'll be passed into XML (allPaths)
            // *****************************************//
            System.out.println("PRINTING OUT ALLPATHS LIST!");
            for (List<Path> lists : allPaths) {
                for (int i = 0; i < lists.size(); i++) {
                    System.out.println("Path " + i + "'s coordinates");
                    lists.get(i).printInfo();
                    System.out.println();
                }
            }
            // *****************************************//
        });
        return display;
    }

    // TODO: Must update the path if changes are made
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
