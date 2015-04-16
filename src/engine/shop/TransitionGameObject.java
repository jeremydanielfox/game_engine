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
    private static final Color WHITE_COLOR = Color.rgb(255, 255, 255, 0.5); // half-transparent white
  
    private Graphic myGraphic;
    private String name;
    private double range;
    @XStreamOmitField
    private Circle rangeDetection;
    @XStreamOmitField
    private transient StackPane pane;

    public TransitionGameObject (String name, Graphic myGraphic, double range) {
        this.myGraphic = myGraphic;
        this.name = name;
        this.range = range;
        initialize();
    }

    // This method now only needs to be called once
    public Node getView () {
        return pane;
    }

    // Shared initialization method
    private void initialize () {
        pane = new StackPane();

        rangeDetection = new Circle(range, ERROR_COLOR);
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
    
    public void changeColor () {
        rangeDetection.setFill(WHITE_COLOR);
    }

}
