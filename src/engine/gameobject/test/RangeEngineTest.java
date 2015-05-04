package engine.gameobject.test;

import engine.gameobject.interactions.RangeEngine;
import engine.gameobject.interactions.ShootAt;
import gameworld.GameWorld;


/**
 *
 * @author Jeremy
 *
 */
public class RangeEngineTest extends RangeEngine {
    public RangeEngineTest (GameWorld world) {
        super.setWorld(world);
        super.put(new FriendlyTowerType(), new EnemyTowerType(), new ShootAt());
    }

}
