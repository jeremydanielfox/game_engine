package engine.shop;

import javafx.beans.property.DoubleProperty;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import engine.gameobject.Graphic;


/**
 *
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
    private String name;
    @XStreamOmitField
    private Circle rangeDetection;
    @XStreamOmitField
    private transient StackPane pane;

    public RangeDisplay (String name, Graphic myGraphic, DoubleProperty range) {
        this.pane = new StackPane();
        this.name = name;
        this.graphic = myGraphic;
        range.addListener((obs, ov, nv) -> initialize(range.get()));
        initialize(range.get());
    }

    private void initialize (double range) {
        pane.getChildren().clear();
        rangeDetection = new Circle(range, NORMAL_COLOR);
        rangeDetection.setStroke(Color.BLACK);
        pane.getChildren().addAll(rangeDetection, graphic.getNode());
    }

    public Node getNode () {
        return pane;
    }

    public String getName () {
        return name;
    }

    public void setRangeCircleColor (Boolean isPlacable) {
        rangeDetection.setFill(isPlacable ? NORMAL_COLOR : ERROR_COLOR);
    }

}
