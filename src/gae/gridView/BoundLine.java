package gae.gridView;

import javafx.beans.property.DoubleProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;


/**
 * A class written to display the boundlines, coming out of the start/end points to the control
 * points
 *
 * @author Kei
 *
 */
public class BoundLine extends Line {
    public BoundLine (DoubleProperty startX,
                      DoubleProperty startY,
                      DoubleProperty endX,
                      DoubleProperty endY) {
        startXProperty().bind(startX);
        startYProperty().bind(startY);
        endXProperty().bind(endX);
        endYProperty().bind(endY);
        setStrokeWidth(2);
        setStroke(Color.GRAY.deriveColor(0, 1, 1, 0.5));
        setStrokeLineCap(StrokeLineCap.BUTT);
        getStrokeDashArray().setAll(10.0, 5.0);
    }
}
