package engine.shop;

import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import engine.gameobject.Graphic;
import engine.gameobject.Graphical;


/**
 * A GameObject that has yet to neither be placed in the GameWorld nor purchased from the shop. It
 * contains the same graphic as the prototype GameObject it will instantiate if placed in the World.
 * 
 * @author Nathan Prabhu and Tom Puglisi
 *
 */

public class TransitionGameObject implements Graphical {

    private static final Color ERROR_COLOR = Color.rgb(255, 51, 51, 0.5); // half-transparent red

    // TODO: these shouldn't be constants; should reflect corresponding Prototype GameObject
    private static final double TOWER_WIDTH = 50;
    private static final double TOWER_RADIUS = widthToRadius(TOWER_WIDTH);
    private static final double RANGE = 30 + TOWER_RADIUS; 
  
    private Graphic myGraphic;
    private String name;

    // TODO: initialize from corresponding prototype GameObject

    private static double widthToRadius (double width) {
        return width * Math.sqrt(2) / 2;
    }

    @XStreamOmitField
    private transient StackPane pane;

    public TransitionGameObject (Graphic myGraphic, String name) {
        this.myGraphic = myGraphic;
        this.name = name;
        initialize();
    }

    // This method now only needs to be called once
    public Node getView () {
        return pane;
    }

    // Shared initialization method
    private void initialize () {
        pane = new StackPane();

        Circle rangeDetection = new Circle(RANGE, ERROR_COLOR);
        rangeDetection.setStroke(Color.BLACK);

        pane.getChildren().addAll(rangeDetection, myGraphic.getNode());
    }

    @Override
    public Graphic getGraphic () {
       return myGraphic;
    }
    
    public String getName () {
        return name;
    }

}
