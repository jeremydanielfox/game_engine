package usecases;

import gameobject.GameObject;
import gameworld.GameWorld;

/********
 * 
 * @author myungoh
 * This is a class that encapsulates the event of spawning a gameobject in the world.
 * Executing this event will spawn whatever is stored in toSpawn.
 */
public class SpawnEvent implements Event{
	private GameObject toSpawn;
	private GameWorld myWorld;
	public SpawnEvent(GameObject toSpawn, GameWorld myWorld){
		this.toSpawn = toSpawn;
		this.myWorld = myWorld;
	}
	
	public void execute(){
		myWorld.addObject(toSpawn);
	}
}
