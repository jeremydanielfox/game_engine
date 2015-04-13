package gae.gridView;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


/**
 * Label class made to attach to the paths as an index
 * 
 * @author Kei
 *
 */
public class PathLabel extends Region {
    private static final int CIRCLE_RADIUS = 10;
    private Label label;

    public PathLabel (int index) {
        StackPane stack = new StackPane();
        Circle circle = new Circle(CIRCLE_RADIUS, Color.WHITE);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(1);
        label = new Label(Integer.toString(index));
        stack.getChildren().addAll(circle, label);
        StackPane.setAlignment(circle, Pos.CENTER);
        StackPane.setAlignment(label, Pos.CENTER);
        this.getChildren().add(stack);
    }

    public void changeValue (int newValue) {
        label.setText(Integer.toString(newValue));
    }
}
