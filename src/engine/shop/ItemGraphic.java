package engine.shop;

import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.effect.Glow;


/**
 * Represents an item's icon in the shop.
 * 
 * @author Nathan Prabhu and Tom Puglisi
 *
 */
public class ItemGraphic extends Parent {

    private static final double GLOW_VALUE = 0.5;

    private PriceTag myPriceTag;
    private Node itemNode;

    public ItemGraphic (PriceTag priceTag) {
        myPriceTag = priceTag;
        initialize();
    }

    private void initialize () {
        itemNode = myPriceTag.getShopGraphic().getNode();
        itemNode.setOnMouseEntered(mouseEvent -> hoverAction());
        itemNode.setOnMouseExited(mouseEvent -> itemNode.setEffect(null));
        getChildren().add(itemNode);
    }

    private void hoverAction () {
        itemNode.setCursor(Cursor.HAND);
        itemNode.setEffect(new Glow(GLOW_VALUE));
        //System.out.println(String.format("Name: %s", myPriceTag.getName()));
        //System.out.println(String.format("Cost: %d", myPriceTag.getPrice()));
        //System.out.println(String.format("Description: %s", myPriceTag.getDescription()));
    }

    public double getRadius () {
        //return ITEM_RADIUS;
        return 0;
    }
    
    public String getName() {
        return myPriceTag.getName();
    }

    public Point2D getCenter () {
        return new Point2D(myPriceTag.getShopGraphic().getCenterX(), myPriceTag.getShopGraphic().getCenterY());
    }

}
