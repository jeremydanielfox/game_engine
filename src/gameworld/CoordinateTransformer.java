package gameworld;

import voogasalad.util.pathsearch.graph.GridCell;
import engine.gameobject.PointSimple;


/**
 * Provides a function to convert from game world coordinates to a (row,col) entry
 * of the grid, and vice versa. The cartesian point returned by a transform on a
 * grid position is the point at the center of the cell.
 * 
 * @author Kaighn
 *
 */
public class CoordinateTransformer {
    private double h, w;

    public CoordinateTransformer (int numRows, int numCols, double windowHeight, double windowWidth) {
        h = windowHeight / numRows;
        w = windowWidth / numCols;
    }

    public GridCell transformWorldToGrid (PointSimple point) {
      return new GridCell((int) (point.getY() / h), (int) (point.getX() / w));
    }

    public PointSimple tranformGridToWorld (GridCell cell) {
        return new PointSimple(cell.getCol() * h + h / 2, cell.getRow() * w + w / 2);
    }

}
