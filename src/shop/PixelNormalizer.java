package shop;

import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;

public class PixelNormalizer {
    
    public static Point2D normalize(MouseEvent mouseEvent, Parent parent){
       return normalize(mouseEvent.getX(), mouseEvent.getY(), parent);
    }
    
    public static Point2D normalize(double x, double y, Parent parent){
        return new Point2D(x/parent.getBoundsInLocal().getWidth(),
                          y/parent.getBoundsInLocal().getHeight());
    }

}
