package engine.gameobject;

import engine.grid.Gridlike;

/**
 * Provides a function to convert from game world coordinates to a (row,col) entry
 * of the grid, and vice versa. The cartesian point returned by a transform on a
 * grid position is the point at the center of the cell.
 * @author Kaighn
 *
 */
public interface CoordinateTransformer {
	public Gridlike transformWorldToGrid(double x, double y);
	public Pointlike tranformGridToWorld(int row, int col);
}
