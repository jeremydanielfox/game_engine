package shop;

import java.util.Random;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


/**
 * Represents an item's icon in the shop.
 * 
 * @author Nathan Prabhu
 *
 */
public class ItemGraphic extends Parent {

    private static final int ITEM_RADIUS = 25;

    private static Random ourGenerator = new Random();

    private String name;
    private int cost;
    private String description;
    private Circle circle;

    public ItemGraphic () {
        name = "name";
        cost = ourGenerator.nextInt(100);
        description = "this is my description";
        initialize();
    }

    private void initialize () {
        circle = new Circle(ITEM_RADIUS, Color.AQUA);
        circle.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEvent -> hoverAction());
        getChildren().add(circle);
    }

    private void hoverAction () {
        circle.setCursor(Cursor.HAND);
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
