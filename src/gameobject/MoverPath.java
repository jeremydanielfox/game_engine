package gameobject;

import engine.grid.GridCell;
import engine.grid.Gridlike;
import engine.pathfinding.PathFinder;

/**
 * A mover that moves according to a pathfinder.
 * @author Kaighn
 *
 */
public class MoverPath implements Mover {
	PathFinder myPathFinder;
	CoordinateTransformer myCoordinateTransformer;
	
	@Override
	public Pointlike move(double x, double y) {
		Gridlike cell = myCoordinateTransformer.transformWorldToGrid(x, y);
		int row = cell.getRow();
		int col = cell.getCol();
		Gridlike nextCell = myPathFinder.getNextLocation(new GridCell(row,col));
		return myCoordinateTransformer.tranformGridToWorld(nextCell.getRow(), nextCell.getCol());
	}
}
