package gae.gridView;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        Button previous = previousPath();
        stack.getChildren().addAll(root, bezier, path, previous);

        background.fitWidthProperty().bind(container.widthProperty());
        background.fitHeightProperty().bind(container.heightProperty());

        pathView = new PathView(stack, this.scene);
        return stack;
    }

    private Button makeBezierCurve () {
        Button makeCurve = new Button("Make Path");
        makeCurve.setTranslateX(500);
        makeCurve.setTranslateY(0);
        makeCurve.setOnMouseClicked(e -> pathView.makeBezierCurve());
        return makeCurve;
    }

    private Button completePath () {
        Button complete = new Button("Path Complete");
        complete.setTranslateX(500);
        complete.setTranslateY(50);
        allPaths = new ArrayList<>();
        previousPaths = new ArrayList<>();
        complete.setOnMouseClicked(e -> {
            List<Path> path = pathView.createPathObjects();
            previousPaths.add(pathView);
            allPaths.add(path);
            for (int i = 0; i < path.size(); i++) {
                System.out.println("Path " + i + "'s coordinates");
                path.get(i).printInfo();
                System.out.println();
            }
            pathView = new PathView(stack, this.scene);
        });
        return complete;
    }
    private Button previousPath () {
        Button previous = new Button("Previous Path");
        previous.setTranslateX(500);
        previous.setTranslateY(100);
        previous.setOnMouseClicked(e -> setOldPath(previousPaths.get(0)));
        return previous;
    }
    /*
     * This method is able to bring back PathView's that had already been made and replace it with
     * the current screen
     */
    public void setOldPath(PathView view) {
        pathView = view;
        pathView.remakePath();
    }
}
