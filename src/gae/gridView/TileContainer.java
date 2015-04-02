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
                Tile tile = new Tile(SCREEN_HEIGHT / size);
                tile.setLayoutX(i);
                tile.setLayoutY(j);
                this.getChildren().add(tile);
            }
        }
        this.setMaxWidth(SCREEN_HEIGHT);
        this.setMaxHeight(SCREEN_HEIGHT);
    }
    
}
