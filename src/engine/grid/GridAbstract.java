package engine.grid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import engine.gameobject.GameObject;
import engine.gameobject.weapon.BasicWeapon;
import engine.interactions.ConcreteInteractionEngine;
import engine.interactions.InteractionEngine;

public class GridAbstract implements Grid {

	protected List<GameObject> myObjects;
	protected InteractionEngine myInteractionEngine;

	public GridAbstract() {
		//super();
	    myObjects=new ArrayList<GameObject>();
	    myInteractionEngine=new ConcreteInteractionEngine();
	}

	@Override
	public void removeObject(GameObject o) {
		myObjects.remove(o);
	}

	@Override
	public void addObject(GameObject o) {
		myObjects.add(o);
	}

	@Override
	public void detectRange() {
		// for(GameObject o : myGameObjects){
		// Weapon w = o.getWeapon();
		// myGameObjects.stream()
		// .filter(go -> go.getPoint().withinRange(o.getPoint(), w.getRange()));
		// .forEach(go -> { myInteractionEngine.interact(w.getProjectiles(),
		// o);}); //need to rework weapons first TODO
		// TODO make a weapon not fire upon everything in range. Also, rework
		// weapon/projectile.
		//
		// }
	}
	
	@Override
	public void detectCollisions(){
		
	}

}