package gae.gridView;

import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;


/*
 * https://www.youtube.com/watch?v=ag8U6sUptsY
 * Want to base it on this ^^^^^
 */
public class TileContainer extends Region {
    public static final double SCREEN_HEIGHT =
            Screen.getPrimary().getVisualBounds().getHeight() - 120;
    public static final double SCREEN_WIDTH =
            Screen.getPrimary().getVisualBounds().getWidth() - 120;

    public TileContainer (int size) {
//        System.out.println(stack.getWidth());
//        stack.getHeight();
        addTiles(size);
    }

    private void addTiles (int size) {
        double length = SCREEN_HEIGHT / size;
        for (double i = 0; Math.round(i) < SCREEN_HEIGHT; i += SCREEN_HEIGHT / size) {
            for (double j = 0; Math.round(j) < SCREEN_HEIGHT; j += SCREEN_HEIGHT / size) {
                TileView tileView =
                        new TileView(length, new TileData(i / length, j / length));
                tileView.setLayoutX(i);
                tileView.setLayoutY(j);
                this.getChildren().add(tileView);
            }
        }
        this.setMaxWidth(SCREEN_HEIGHT);
        this.setMaxHeight(SCREEN_HEIGHT);
    }
}
