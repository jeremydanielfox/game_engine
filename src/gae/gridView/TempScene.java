package gae.gridView;

import javafx.geometry.Pos;
import javafx.scene.Scene;
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

    public Scene getScene () {
        StackPane stack = new StackPane();
        ImageView background = new ImageView(new Image("/images/Park_Path.png"));

        TileContainer container = new TileContainer(10);
        background.fitWidthProperty().bind(container.widthProperty());
        background.fitHeightProperty().bind(container.heightProperty());
        stack.getChildren().addAll(background, container);

        StackPane.setAlignment(background, Pos.CENTER);
        StackPane.setAlignment(container, Pos.CENTER);
        myScene = new Scene(stack);
        return myScene;
    }
}
