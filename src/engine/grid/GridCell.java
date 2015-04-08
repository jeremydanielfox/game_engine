package engine.grid;


/**
 * Simple class for containing a (int,int) tuple. Typically thought of
 * as a (row,column) point in some grid.
 * @author Kaighn
 *
 */
public class GridCell {
	private int myRow, myCol;
	
	public GridCell(int r, int c){
		myRow = r;
		myCol = c;
	}
	
	public int getRow() {
		return myRow;
	}

	public int getCol() {
		return myCol;
	}
	
}
