package shop;

import java.util.Random;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Represents an item's icon in the shop.
 * @author Nathan Prabhu
 *
 */
public class ItemGraphic extends Parent {
    
    private static Random ourGenerator = new Random();
    
    private String name;
    private int cost;
    private String description;
    private Circle circle;

    
    public ItemGraphic() {
        //setId("item-graphic");
        
        name = "name";
        cost = ourGenerator.nextInt(100);
        description = "this is my description";
        initialize();
    }
    
    private void initialize(){
        circle = new Circle(25, Color.AQUA);
        circle.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEvent -> hoverAction());
        //circle.addEventHandler(MouseEvent.MOUSE_PRESSED, mouseEvent -> onClicked());
        getChildren().add(circle);
    }

    private void hoverAction(){
        System.out.println(String.format("Name: %s", name));
        System.out.println(String.format("Cost: %d", cost));
        System.out.println(String.format("Description: %s", description));
    }
    
    private void onClicked () {
    }

}
