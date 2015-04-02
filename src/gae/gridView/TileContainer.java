package gae.gridView;

import javafx.scene.layout.Region;
import javafx.stage.Screen;


public class TileContainer extends Region {
    public static final double SCREEN_HEIGHT =
            Screen.getPrimary().getVisualBounds().getHeight() - 100;
    public static final double SCREEN_WIDTH =
            Screen.getPrimary().getVisualBounds().getWidth() - 100;

    public TileContainer (int size) {
        addTiles(size);
    }

    private void addTiles (int size) {
        for (double i = 0; i < SCREEN_HEIGHT; i += SCREEN_HEIGHT / size) {
            for (double j = 0; j < SCREEN_HEIGHT; j += SCREEN_HEIGHT / size) {
                TileView tileView =
                        new TileView(SCREEN_HEIGHT / size,
                                     new TileData((int) (i / (SCREEN_HEIGHT / size)), (int) (j /
                                                  (SCREEN_HEIGHT / size))));
                tileView.setLayoutX(i);
                tileView.setLayoutY(j);
                this.getChildren().add(tileView);
            }
        }
        this.setMaxWidth(SCREEN_HEIGHT);
        this.setMaxHeight(SCREEN_HEIGHT);
    }
}
