package engine.shop;

import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;


/**
 * Represents an item's icon in the shop.
 * 
 * @author Nathan Prabhu and Tom Puglisi
 *
 */
public class ItemGraphic extends Parent {

    private static final double ITEM_RADIUS = 25;
    private static final double GLOW_VALUE = 0.5;

    private Circle circle;
    private PriceTag myPriceTag;

    public ItemGraphic (PriceTag priceTag) {
        myPriceTag = priceTag;
        initialize();
    }

    private void initialize () {
        circle = new Circle(ITEM_RADIUS);
        circle.setFill(new ImagePattern(((ImageView) myPriceTag.getShopGraphic().getNode()).getImage()));
        circle.setOnMouseEntered(mouseEvent -> hoverAction());
        circle.setOnMouseExited(mouseEvent -> circle.setEffect(null));
        getChildren().add(circle);
    }

    private void hoverAction () {
        circle.setCursor(Cursor.HAND);
        circle.setEffect(new Glow(GLOW_VALUE));
        System.out.println(String.format("Name: %s", myPriceTag.getName()));
        System.out.println(String.format("Cost: %d", myPriceTag.getPrice()));
        System.out.println(String.format("Description: %s", myPriceTag.getDescription()));
    }

    public double getRadius () {
        return ITEM_RADIUS;
    }
    
    public String getName() {
        return myPriceTag.getName();
    }

    public Point2D getCenter () {
        return new Point2D(circle.getCenterX(), circle.getCenterY());
    }

}
