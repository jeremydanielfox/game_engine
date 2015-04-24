package engine.gameobject.test;

import engine.interactions.BuffImparter;
import engine.interactions.CollisionEngine;


/**
 *
 * @author Jeremy
 *
 */
public class CollisionEngineTest extends CollisionEngine {
    public CollisionEngineTest () {
        super.put(new ProjectileLabel(), new EnemyLabel(), new BuffImparter());
    }
}
