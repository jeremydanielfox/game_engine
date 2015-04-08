package gae.gridView;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;


public class WorldView {
    private Scene myScene;
    private StackPane stack;
    private PathView pathView;
    private List<List<Path>> allPaths;
    private List<PathView> previousPaths;
    private Scene scene;

    private ObservableList paths =
            FXCollections.observableArrayList();

    public Scene getScene () {
        stack = new StackPane();
        ImageView background = new ImageView(new Image("/images/Park_Path.png"));

        TileContainer container = new TileContainer(10);
        background.fitWidthProperty().bind(container.widthProperty());
        background.fitHeightProperty().bind(container.heightProperty());

        stack.getChildren().addAll(background, container, makeBezierCurve(), completePath());

        StackPane.setAlignment(background, Pos.CENTER);
        StackPane.setAlignment(container, Pos.CENTER);

        myScene = new Scene(stack);
        pathView = new PathView(stack, myScene);
        return myScene;
    }

    /*
     * Used by CentralTabView to create a new Level. Could be changed
     */
    public StackPane getStack (Scene scene) {
        stack = new StackPane();
        this.scene = scene;
        ImageView background = new ImageView(new Image("/images/Park_Path.png"));
        Group root = new Group();
        TileContainer container = new TileContainer(20);
        root.getChildren().addAll(background, container);

        Button bezier = makeBezierCurve();
        Button path = completePath();
        Button newPath = newPath();
        stack.getChildren().addAll(root, bezier, path, newPath);

        background.fitWidthProperty().bind(container.widthProperty());
        background.fitHeightProperty().bind(container.heightProperty());

        pathView = new PathView(stack, this.scene);
        return stack;
    }

    private Button makeBezierCurve () {
        Button makeCurve = new Button("Make Path");
        makeCurve.setTranslateX(400);
        makeCurve.setTranslateY(0);
        makeCurve.setOnMouseClicked(e -> pathView.makeBezierCurve());
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
            paths.add(pathView);
            for (int i = 0; i < path.size(); i++) {
                System.out.println("Path " + i + "'s coordinates");
                path.get(i).printInfo();
                System.out.println();
            }
            pathView = new PathView(stack, this.scene);
        });
        return complete;
    }

    private Button newPath() {
        Button newPath = new Button("New Path");
        newPath.setTranslateX(400);
        newPath.setTranslateY(100);
        newPath.setOnMouseClicked(e -> {
            pathView.resetScreen();
            pathView = new PathView(stack, this.scene);
        });
        return newPath;
    }

    /*
     * This method is able to bring back PathView's that had already been made and replace it with
     * the current screen
     */
    public void setOldPath (PathView view) {
        pathView.resetScreen();
        pathView = view;
        pathView.remakePath();
    }

    public StackPane tempListView () {
        final ListView listView = new ListView(paths);
        listView.setPrefSize(200, 250);
        listView.setEditable(true);

        listView.setItems(paths);
        listView.setOnMouseClicked(e -> {
            setOldPath((PathView) listView.getSelectionModel().getSelectedItem());
        });
        StackPane root = new StackPane();
        StackPane.setAlignment(listView, Pos.TOP_LEFT);
        root.getChildren().add(listView);
        listView.setMaxWidth(300);
        return root;
    }
}
