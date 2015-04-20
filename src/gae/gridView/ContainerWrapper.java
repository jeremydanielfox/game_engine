package gae.gridView;

import engine.gameobject.PointSimple;


/**
 * Interface written to wrap the TileContainer so that other classes can find coordinates relative
 * to the grid system.
 * 
 * @author Kei
 *
 */
public interface ContainerWrapper {
    public boolean checkBounds (double x, double y);

    public PointSimple convertCoordinates (double x, double y);
}
