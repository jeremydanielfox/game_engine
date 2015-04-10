package engine.shop;

import java.util.Random;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import engine.gameobject.Graphic;
import engine.gameobject.Graphical;


/**
 * A GameObject that has yet to neither be placed in the GameWorld nor purchased from the shop. It
 * contains the same graphic as the prototype GameObject it will instantiate if placed in the World.
 * 
 * @author Nathan Prabhu
 *
 */

public class TransitionGameObject implements Graphical {

    private static Random ourGenerator = new Random();

    private static final Color ERROR_COLOR = Color.rgb(255, 51, 51, 0.5); // half-transparent red
    private static final Color WHITE_COLOR = Color.rgb(255, 255, 255, 0.5); // half-transparent red

    // TODO: these shouldn't be constants; should reflect corresponding Prototype GameObject
    private static final double TOWER_WIDTH = 50;
    private static final double TOWER_RADIUS = widthToRadius(TOWER_WIDTH);
    private static final double RANGE = 30 + TOWER_RADIUS; 
  
    private Graphic myGraphic;
    private Circle rangeDetection;
    
    // TODO: initialize from corresponding prototype GameObject

    private static double widthToRadius (double width) {
        return width * Math.sqrt(2) / 2;
    }

    @XStreamOmitField
    private transient StackPane pane;

    public TransitionGameObject (String image) {
        initialize(image);
    }

    // This method now only needs to be called once
    public Node getView () {
        return pane;
    }

    // Shared initialization method
    private void initialize (String image) {
        pane = new StackPane();

        rangeDetection = new Circle(RANGE, ERROR_COLOR);
        rangeDetection.setStroke(Color.BLACK);

        Rectangle tower = new Rectangle(TOWER_WIDTH, TOWER_WIDTH);
        tower.setFill(new ImagePattern(new Image(image)));
        pane.getChildren().addAll(rangeDetection, tower);
    }

    @Override
    public Graphic getGraphic () {
       return myGraphic;
    }
    
    public void changeColor () {
        rangeDetection.setFill(WHITE_COLOR);
    }

}
