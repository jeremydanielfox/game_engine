package gae.gridView;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class TileView extends Group {
    private TileData data;

    public TileView (double size, TileData data) {
        this.data = data;
        Rectangle rect = new Rectangle(size, size, Color.TRANSPARENT);
        rect.setStroke(Color.BLACK);
        rect.setStrokeWidth(1);
        this.getChildren().addAll(rect);
        setUp(rect);
    }

    private void setUp (Rectangle rect) {
        this.setOnMouseEntered(e -> {
            rect.setFill(Color.LIGHTGREEN);
        });
        this.setOnMouseExited(e -> {
            rect.setFill(Color.TRANSPARENT);
        });
        this.setOnMouseClicked(e -> {
            System.out.println("clicked");
            data.changeState();
        });
    }
}
