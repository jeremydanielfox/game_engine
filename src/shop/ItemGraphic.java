package shop;

import java.util.Random;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;


/**
 * Represents an item's icon in the shop.
 * 
 * @author Nathan Prabhu
 *
 */
public class ItemGraphic extends Parent {

    private static final double ITEM_RADIUS = 25;
    private static final double GLOW_VALUE = 0.5;

    private static Random ourGenerator = new Random();

    private String name;
    private int cost;
    private String description;
    private Circle circle;
    private String image;

    public ItemGraphic (String image) {
        this.image = image;
        name = "name";
        cost = ourGenerator.nextInt(100);
        description = "this is my description";
        initialize();
    }

    private void initialize () {
        circle = new Circle(ITEM_RADIUS);
        circle.setFill(new ImagePattern(new Image(image), 0, 0, 1, 1, true));
        circle.setOnMouseEntered(mouseEvent -> hoverAction());
        circle.setOnMouseExited(mouseEvent -> circle.setEffect(null));
        getChildren().add(circle);
    }

    private void hoverAction () {
        circle.setCursor(Cursor.HAND);
        circle.setEffect(new Glow(GLOW_VALUE));
        System.out.println(String.format("Name: %s", name));
        System.out.println(String.format("Cost: %d", cost));
        System.out.println(String.format("Description: %s", description));
    }

    public double getRadius () {
        return circle.getRadius();
    }

    public Point2D getCenter () {
        return new Point2D(circle.getCenterX(), circle.getCenterY());
    }
}
