package engine.grid;

import engine.gameobject.PointSimple;

/**
 * Provides a function to convert from game world coordinates to a (row,col) entry
 * of the grid, and vice versa. The cartesian point returned by a transform on a
 * grid position is the point at the center of the cell.
 * @author Kaighn
 *
 */
public interface CoordinateTransformer {
	public GridCell transformWorldToGrid(PointSimple point);
	public PointSimple tranformGridToWorld(GridCell cell);
}
