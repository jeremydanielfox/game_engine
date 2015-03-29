package engine.grid;

import java.util.LinkedList;
import java.util.List;

import gameobject.GameObject;

public class Grid {
	private List<GameObject>[][] myGrid;
	
	@SuppressWarnings("unchecked")
	public Grid(int numRows, int numCols){
		myGrid = (List<GameObject>[][]) new LinkedList<?>[numRows][numCols];
	}
	
	public void addObject(int row, int col, GameObject o){
		myGrid[row][col].add(o);
	}
	public void removeObject(int row, int col, GameObject o){
		myGrid[row][col].remove(o);
	}
	boolean canPlace(int row, int col, GameObject o){
		//TODO implement. GameObject needs a definition of how many cells it takes up.
		return true;
	}
}
