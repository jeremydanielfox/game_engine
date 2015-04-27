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

	/**
	 * On the wall between two adjacent cells, return a point on the wall that is a certain
	 * percentage along it. Ie percentage=.5 would be the midpoint. Returns a point that is
	 * within the cell c1.
	 * @param c1
	 * @param c2
	 * @param percentage
	 * @return
	 */
	public PointSimple transformGridCellsToWorldWall(GridCell c1, GridCell c2, double percentage){
		PointSimple p1 = this.tranformGridToWorld(c1);
		PointSimple p2 = this.tranformGridToWorld(c2);
		PointSimple midpoint = PointSimple.pointOnLine(p1, p2, PointSimple.distance(p1, p2)/2);
		if(c1.getRow()==c2.getRow()){
			PointSimple ret = new PointSimple(midpoint.getX(),(percentage-.5)*h + midpoint.getY());
			int i = c1.getCol() < c2.getCol() ? -1 : 1;
			return new PointSimple(ret.getX()+i*2,ret.getY());	
		}
		else{
			PointSimple ret = new PointSimple((percentage-.5)*w + midpoint.getX(),midpoint.getY()); 
			int i = c1.getRow() < c2.getRow() ? -1 : 1;
			return new PointSimple(ret.getX(),ret.getY()+i*2);
		}
	}

	public PointSimple findIntraCellPosition(PointSimple point){
		GridCell c = this.transformWorldToGrid(point);
		PointSimple center = this.tranformGridToWorld(c);
		double x = (w - (center.getX() - point.getX() + w/2))/w;
		double y = (w - (center.getY() - point.getY() + h/2))/h;
		return new PointSimple(x,y);
	}

	public PointSimple findCellPointAtPosition(GridCell cell, PointSimple position){
		PointSimple center = this.tranformGridToWorld(cell);
		PointSimple origin = new PointSimple(center.getX()-w/2, center.getY()-h/2);
		return new PointSimple(origin.getX() + position.getX()*w, origin.getY() + position.getY()*h);
	}
}
