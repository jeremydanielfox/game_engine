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

    /**
     * Returns the cell which contains the point.
     * @param point
     * @return
     */
    public GridCell transformWorldToGrid (PointSimple point) {
      return new GridCell((int) (point.getY() / h), (int) (point.getX() / w));
    }

    /**
     * Returns the exact pixel center of the cell.
     * @param cell
     * @return
     */
    public PointSimple tranformGridToWorld (GridCell cell) {
        return new PointSimple(cell.getCol() * h + h / 2, cell.getRow() * w + w / 2);
    }
    
    /**
     * Returns a randomly off center point near the middle of the cell.
     * @param cell
     * @return
     */
    public PointSimple transformGridToWorldInexact(GridCell cell){
    	PointSimple p = tranformGridToWorld(cell);
    	double offsetX = Math.min(h, w)*(Math.random()-.5);
    	double offsetY = Math.min(h, w)*(Math.random()-.5);
    	return new PointSimple(p.getX() + offsetX, p.getY() + offsetY);
    }
    
    public PointSimple transformGridCellsToWorldMidPoint(GridCell c1, GridCell c2){
    	PointSimple p1 = this.tranformGridToWorld(c1);
    	PointSimple p2 = this.tranformGridToWorld(c2);
    	return PointSimple.pointOnLine(p1, p2, PointSimple.distance(p1, p2)/2);
    }
}
