package engine.grid;

public class GridCell implements Gridlike {
	int myRow, myCol;
	
	public GridCell(int r, int c){
		myRow = r;
		myCol = c;
	}
	
	@Override
	public int getRow() {
		return myRow;
	}

	@Override
	public int getCol() {
		return myCol;
	}
	
}
