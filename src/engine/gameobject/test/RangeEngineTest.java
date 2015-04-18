package engine.gameobject.test;

import engine.interactions.RangeEngine;
import engine.interactions.ShootAt;
import gameworld.GameWorld;


/**
 * 
 * @author Jeremy
 *
 */
public class RangeEngineTest extends RangeEngine {
    public RangeEngineTest (GameWorld world) {
        super.setWorld(world);
        super.put(new TowerLabel(), new EnemyLabel(), new ShootAt());
    }

}
