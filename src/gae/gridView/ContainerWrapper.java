package gae.gridView;

import javafx.geometry.Point2D;


/**
 * Interface written to wrap the TileContainer so that other classes can find coordinates relative
 * to the grid system.
 * 
 * @author Kei
 *
 */
public interface ContainerWrapper {
    public boolean checkBounds (double x, double y);

    public Point2D convertCoordinates (double x, double y);
}
