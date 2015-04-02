package shop;

import java.util.Random;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import com.thoughtworks.xstream.annotations.XStreamOmitField;


/**
 * Experimental object used to model a transition tower.
 * 
 * @author Nathan Prabhu
 *
 */

public class TransitionTower {

    private static Random ourGenerator = new Random();

    private static final double TOWER_WIDTH = 50;
    private static final double TOWER_RADIUS = widthToRadius(TOWER_WIDTH);
    private static final double RANGE = 30 + TOWER_RADIUS; // TODO: make as a field
    private static final Color ERROR_COLOR = Color.rgb(255, 51, 51, 0.5); // half-transparent red

    private static double widthToRadius (double width) {
        return width * Math.sqrt(2) / 2;
    }

    @XStreamOmitField
    private transient StackPane pane;

    public TransitionTower (String image) {
        initialize(image);
    }

    // This method now only needs to be called once
    public Node getView () {
        return pane;
    }

    // Shared initialization method
    private void initialize (String image) {
        pane = new StackPane();

        Circle rangeDetection = new Circle(RANGE, ERROR_COLOR);
        rangeDetection.setStroke(Color.BLACK);

        Rectangle tower = new Rectangle(TOWER_WIDTH, TOWER_WIDTH);
        tower.setFill(new ImagePattern(new Image(image)));
        pane.getChildren().addAll(rangeDetection, tower);
    }

}
