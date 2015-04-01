package usecases;

import engine.gameobject.Enemy;
import gameworld.BasicWorld;
import gameworld.GameWorld;
import java.util.HashMap;

public class WaveUseCase {
	private static Wave myWave;
	private static GameWorld myWorld;
	
	private static void init(){
		HashMap<Event, Integer> waveMap = new HashMap<>();
		myWorld = new BasicWorld();
		//This loop makes the wavemap by making the enemies, then the spawnevent that spawns the enemy
		for (int i = 0; i < 100; i++){
			Enemy currentEnemy = new BasicEnemy(i);
			waveMap.put(new SpawnEvent(currentEnemy, myWorld), i);
		}
		myWave = new Wave(waveMap);
	}
	
	public static void main(String[] args){
		init();
		/*
		 * This is a big implementation question: Do we manage the waves in order to manage timestamps correctly, or
		 * would the wave "schedule" enemies in the world beforehand so the wave doesn't have to be managed?
		 */
		for (int i = 0; i < 100; i++)
			myWave.execute();
	}
}
