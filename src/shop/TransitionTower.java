package shop;

import java.util.Random;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

/**
 * Experimental object used to model a transition tower.
 * @author Nathan Prabhu
 *
 */

public class TransitionTower {

    private static Random ourGenerator = new Random();
    private Point2D myCenter;
    private int mySize;
    private Point2D myVelocity;
    private Color myColor;
    @XStreamOmitField
    private transient Circle myShape;

    public TransitionTower () {
        init(new Point2D(ourGenerator.nextInt(400), ourGenerator.nextInt(4)),
             ourGenerator.nextInt(16) + 15,
             new Point2D(ourGenerator.nextInt(5) - 3, ourGenerator.nextInt(5) - 3),
             new Color(ourGenerator.nextDouble(), ourGenerator.nextDouble(), ourGenerator.nextDouble(), 1));
    }
    
    public void update(){
        myCenter = myCenter.add(myVelocity);
        myShape.setCenterX(myCenter.getX());
        myShape.setCenterY(myCenter.getY());
    }

    // This method now only needs to be called once
    public Circle getView () {
        return myShape;
    }

    // Shared initialization method
    private void init (Point2D center, int size, Point2D velocity, Color color) {
        myCenter = new Point2D(center.getX(), center.getY());
        mySize = size;
        myVelocity = new Point2D(velocity.getX(), velocity.getY());
        myColor = color;
        myShape = new Circle(center.getX(), center.getY(), size);
        myShape.setFill(color);
    }

}
