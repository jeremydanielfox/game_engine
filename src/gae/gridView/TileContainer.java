package gae.gridView;

import exception.ObjectOutOfBoundsException;
import javafx.geometry.Point2D;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.stage.Screen;


/*
 * https://www.youtube.com/watch?v=ag8U6sUptsY
 * Want to base it on this ^^^^^
 */
public class TileContainer extends Region implements ContainerWrapper {
    public static final double SCREEN_HEIGHT =
            Screen.getPrimary().getVisualBounds().getHeight() - 150;
    public static final double SCREEN_WIDTH =
            Screen.getPrimary().getVisualBounds().getWidth() - 120;
    private BorderPane border;

    public TileContainer (int size, BorderPane border) {
        this.border = border;
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
        border.prefHeightProperty().bind(this.heightProperty());
        // this.setMaxWidth(SCREEN_HEIGHT);
        // this.setMaxHeight(SCREEN_HEIGHT);

    }

    public boolean checkBounds (double x, double y) {
        Point2D point = this.screenToLocal(x, y);
        System.out.println("X IS: " + point.getX());
        System.out.println("Y IS : " + y);
        if (point.getX() < 0 || point.getX() > this.getWidth() || y < 0 ||
            y > this.getHeight()) {
            return true;
        }
        return false;
    }

    @Override
    public Point2D convertCoordinates (double x, double y) {
        return this.parentToLocal(x, y);
    }

}
