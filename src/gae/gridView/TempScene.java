package gae.gridView;

import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;


public class TempScene {
    public static final double SCREEN_HEIGHT =
            Screen.getPrimary().getVisualBounds().getHeight() - 100;
    public static final double SCREEN_WIDTH =
            Screen.getPrimary().getVisualBounds().getWidth() - 100;
    private Scene myScene;
    private StackPane stack;
    private PathView pathView;

    public Scene getScene () {
        stack = new StackPane();
        ImageView background = new ImageView(new Image("/images/Park_Path.png"));

        TileContainer container = new TileContainer(20);
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
        ImageView background = new ImageView(new Image("/images/Park_Path.png"));

        TileContainer container = new TileContainer(20);
        background.fitWidthProperty().bind(container.widthProperty());
        background.fitHeightProperty().bind(container.heightProperty());

        stack.getChildren().addAll(background, container, makeBezierCurve(), completePath());

        StackPane.setAlignment(background, Pos.CENTER);
        StackPane.setAlignment(container, Pos.CENTER);
        pathView = new PathView(stack, scene);
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
        complete.setOnMouseClicked(e -> {
            List<Path> path = pathView.createPathObjects();
            for (int i = 0; i < path.size(); i++) {
                System.out.println("Path " + i + "'s coordinates");
                path.get(i).printInfo();
                System.out.println();
            }
        });
        return complete;
    }
}
