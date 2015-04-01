package shop;

import java.util.Random;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import com.thoughtworks.xstream.annotations.XStreamOmitField;


/**
 * Experimental object used to model a transition tower.
 * 
 * @author Nathan Prabhu
 *
 */

public class TransitionTower {

    private static Random ourGenerator = new Random();

    private static final double TOWER_RADIUS = 25;
    private static final double RANGE = 30 + TOWER_RADIUS;
    private static final Color ERROR_COLOR = Color.rgb(255, 51, 51, 0.5); // half-transparent red

    @XStreamOmitField
    private transient StackPane pane;

    public TransitionTower () {
        initialize(new Color(ourGenerator.nextDouble(), ourGenerator.nextDouble(), ourGenerator
                .nextDouble(), 1));
    }

    // This method now only needs to be called once
    public Node getView () {
        return pane;
    }

    // Shared initialization method
    private void initialize (Color color) {
        pane = new StackPane();

        Circle rangeDetection = new Circle(RANGE, ERROR_COLOR);
        rangeDetection.setStroke(Color.BLACK);

        Circle tower = new Circle(TOWER_RADIUS, color);
        //tower.setFill(new ImagePattern(new Image("/images/Bloons_DartMonkeyIcon.jpg"), 0, 0, 1, 1, true));
        pane.getChildren().addAll(rangeDetection, tower);
    }

}
