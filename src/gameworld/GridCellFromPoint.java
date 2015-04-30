package gameworld;

import engine.gameobject.GameObject;
import engine.gameobject.PointSimple;
import voogasalad.util.pathsearch.graph.GridCell;

public class GridCellFromPoint extends GridCell {
	CoordinateTransformer myTrans;
	GameObject myObject;
	
	public GridCellFromPoint(CoordinateTransformer t, GameObject o){
		super(t.transformWorldToGrid(o.getPoint()).getRow(), t.transformWorldToGrid(o.getPoint()).getCol());
		myTrans = t;
		myObject = o;
	}
	
	public GridCellFromPoint(int row, int col) {
		super(row, col);
	}
	
	@Override
	public int getRow(){
		return myTrans.transformWorldToGrid(myObject.getPoint()).getRow();
	}
	
	@Override
	public int getCol(){
		return myTrans.transformWorldToGrid(myObject.getPoint()).getCol();
	}
	
	@Override
	public boolean equals(Object c) {
		if (c == null) {
			return false;
		}
		if (c instanceof GridCell) {
			GridCell cell = (GridCell) c;
			return getRow() == cell.getRow() && getCol() == cell.getCol();
		}
		return false;
	}
	@Override
	public String toString() {
		return getRow() + ", " + getCol();
	}
}


