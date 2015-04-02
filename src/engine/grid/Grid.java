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
	private CoordinateTransformer myCoordTrans;
	
	
	@SuppressWarnings("unchecked")
	public Grid(int numRows, int numCols){
		myGrid = (List<GameObject>[][]) new LinkedList<?>[numRows][numCols];
		myGameObjects = new LinkedList<>();
		myInteractionEngine = new ConcreteInteractionEngine();
	}
	
	public void addStructure(GameObject o){
		GridCell cell = myCoordTrans.transformWorldToGrid(o.getPoint());
		myGrid[cell.getRow()][cell.getCol()].add(o);
		myGameObjects.add(o);
	}
	public void removeObject(GameObject o){
		myGameObjects.remove(o);
		for(List<GameObject>[] row : myGrid){
			for(List<GameObject> tileList : row){
				if(tileList.contains(o)){
					tileList.remove(o);
				}
			}
		}
	}
	public void addObject(GameObject o){
		myGameObjects.remove(o);
		for(List<GameObject>[] row : myGrid){
			for(List<GameObject> tileList : row){
				tileList.add(o);//TODO duplicated with "removeObject"
			}
		}
	}

	public void detectRange(){
		for(GameObject o : myGameObjects){
			Weapon w = o.getWeapon();
				myGameObjects.stream()
					.filter(go -> go.getPoint().withinRange(o.getPoint(), w.getRange()));
//					.forEach(go -> { myInteractionEngine.interact(w.getProjectiles(), o);}); //need to rework weapons first TODO
					//TODO make a weapon not fire upon everything in range. Also, rework weapon/projectile.
			
		}
	}
}
