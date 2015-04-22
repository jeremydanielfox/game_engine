package engine.shop;

import javafx.beans.property.BooleanProperty;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import engine.gameobject.Graphic;


/**
 * A GameObject's graphic overlaid on top of its current range.
 * 
 * @author Nathan Prabhu and Tom Puglisi
 *
 */

public class RangeDisplay {

    private static final Color ERROR_COLOR = Color.rgb(255, 51, 51, 0.5); // half-transparent red
    private static final Color NORMAL_COLOR = Color.rgb(255, 255, 255, 0.5); // half-transparent
                                                                            // white

    private Graphic graphic;
    private double range;
    private String name;
    private BooleanProperty isPlaceable;
    @XStreamOmitField
    private Circle rangeDetection;
    @XStreamOmitField
    private transient StackPane pane;


    public RangeDisplay (String name, Graphic myGraphic, double range) {
        this.name = name;
        this.graphic = myGraphic;
        this.range = range;
        initialize();
    }

    // Shared initialization method
    private void initialize () {
        pane = new StackPane();

        rangeDetection = new Circle(range, NORMAL_COLOR);
        rangeDetection.setStroke(Color.BLACK);

        pane.getChildren().addAll(rangeDetection, graphic.getNode());
    }

    public Node getNode () {
        return pane;
    }

    public Graphic getGraphic () {
        return graphic;
    }
    
    public String getName () {
        return name;
    }

    public void setRangeCircleColor (Boolean isPlacable) {
        rangeDetection.setFill(isPlacable ? NORMAL_COLOR : ERROR_COLOR);
    }

}
