package engine.grid;

public class GridCell {
	int myRow, myCol;
	
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
