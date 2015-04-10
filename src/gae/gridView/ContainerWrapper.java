package gae.gridView;

import javafx.geometry.Point2D;


public interface ContainerWrapper {
    public boolean checkBounds (double x, double y);

    public Point2D convertCoordinates (double x, double y);
}
