package engine.shop;

import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.effect.Glow;
import engine.gameobject.Graphic;


/**
 * Represents an item's icon in the shop.
 * 
 * @author Nathan Prabhu and Tom Puglisi
 *
 */
public class ItemGraphic extends Parent {

    private static final double GLOW_VALUE = 0.5;

    private Node itemNode;
    private String name;
    private Graphic shopGraphic;

    public ItemGraphic (String name, Graphic shopGraphic) {
        this.name = name;
        this.shopGraphic = shopGraphic;
        initialize();
    }

    private void initialize () {
        itemNode = shopGraphic.getNode();
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
    
    public String getName() {
        return name;
    }

}
