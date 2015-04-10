package gae.gridView;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;


/**
 * Holds data about the tile
 * 
 * @author Kei & Nina
 *
 */
public class TileData {
    private int row;
    private int col;
    private BooleanProperty walkableProperty = new SimpleBooleanProperty();

    public TileData (double row, double col) {
        walkableProperty.set(true);
        this.row = (int) Math.round(row);
        this.col = (int) Math.round(col);
    }

    protected void changeState () {
        walkableProperty.set(!walkableProperty.get());
        System.out.println("Row is: " + row + " Col is: " + col);
        System.out.println("New state is: " + walkableProperty.get());
    }

    protected void setWalkable (boolean canwalk) {
        walkableProperty.set(canwalk);
    }

    protected BooleanProperty getWalkableProperty () {
        return walkableProperty;
    }
}
