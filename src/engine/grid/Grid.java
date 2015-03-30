package engine.grid;

import java.util.LinkedList;
import java.util.List;

import gameobject.GameObject;

public class Grid {
	private List<GameObject>[][] myGrid;
	private List<GameObject> myGameObjects;
	
	@SuppressWarnings("unchecked")
	public Grid(int numRows, int numCols){
		myGrid = (List<GameObject>[][]) new LinkedList<?>[numRows][numCols];
		myGameObjects = new LinkedList<>();
	}
	
	public void addStructure(int row, int col, GameObject o){
		myGrid[row][col].add(o);
		myGameObjects.remove(o);
	}
	public void removeObject(int row, int col, GameObject o){
		myGrid[row][col].remove(o);
	}
	boolean canPlace(int row, int col, GameObject o){
		//TODO implement. GameObject needs a definition of how many cells it takes up.
		return true;
	}
	public void detectRange(){
		for(GameObject o : myGameObjects){
			myGameObjects.stream(); //TODO finish
		}
	}
}
