package engine.pathgraphsearch;

public class GraphNodeIDRectangle implements GraphNodeID{
	private int myRow, myCol, myWidth;
	
	public GraphNodeIDRectangle(int width){
		myRow = 0;
		myCol = 0;
	}
	public GraphNodeIDRectangle(int row, int col, int width){
		myRow = row;
		myCol = col;
		myWidth = width;
	}

	@Override
	public void increment() {
		if(myCol == myWidth - 1){
			myCol = 0;
			myRow++;
		}
		else{
			myCol++;
		}
	}

	@Override
	public GraphNodeID getClone() {
		// TODO Auto-generated method stub
		return null;
	}
}
