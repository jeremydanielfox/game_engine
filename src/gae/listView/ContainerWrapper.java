package gae.listView;

import javafx.geometry.Point2D;


public interface ContainerWrapper {
    public void checkBounds (double x, double y);

    public Point2D convertCoordinates (double x, double y);
}
