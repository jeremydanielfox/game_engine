package engine.grid;

import java.util.LinkedList;
import java.util.List;
import engine.gameobject.GameObject;
import engine.gameobject.Weapon;
import engine.interactions.ConcreteInteractionEngine;
import engine.interactions.InteractionEngine;

public class Grid {
	private List<GameObject>[][] myGrid;
	private List<GameObject> myGameObjects;
	private InteractionEngine myInteractionEngine;
	
	
	@SuppressWarnings("unchecked")
	public Grid(int numRows, int numCols){
		myGrid = (List<GameObject>[][]) new LinkedList<?>[numRows][numCols];
		myGameObjects = new LinkedList<>();
		myInteractionEngine = new ConcreteInteractionEngine();
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
			for(Weapon w : o.getWeapons()){
				myGameObjects.stream()
					.filter(go -> go.getPoint().withinRange(o.getPoint(), w.getRange()))
					.forEach(go -> { myInteractionEngine.interact(w.getProjectile(), o);});
					//TODO make a weapon not fire upon everything in range. Also, rework weapon/projectile.
			}
		}
	}
}
