package engine.gameobject.test;

import engine.gameobject.interactions.BuffImparter;
import engine.gameobject.interactions.CollisionEngine;


/**
 *
 * @author Jeremy
 *
 */
public class CollisionEngineTest extends CollisionEngine {
    public CollisionEngineTest () {
        super.put(new ProjectileLabel(), new EnemyTowerType(), new BuffImparter());
    }
}
