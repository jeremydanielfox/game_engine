package gae.gridView;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Tile extends Group {
    public Tile (double size) {

        Rectangle rect = new Rectangle(size, size, Color.TRANSPARENT);
        rect.setStroke(Color.BLACK);
        rect.setStrokeWidth(1);
        this.getChildren().addAll(rect);
        setUp(rect);
    }

    private void setUp (Rectangle rect) {
        this.setOnMouseEntered(e -> {
            rect.setFill(Color.LIGHTGREEN);
            // rect.setStyle("-fx-background-color: blue;");
        });
        this.setOnMouseExited(e -> {
            rect.setFill(Color.TRANSPARENT);
            // rect.setStyle("-fx-background-color: transparent;");
        });
        this.setOnMouseClicked(e -> {
            System.out.println("clicked");
        });
    }
}
