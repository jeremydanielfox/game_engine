package SuperAwesomeDemo;

import engine.gameobject.test.EnemyTowerType;
import engine.gameobject.test.FriendlyTowerType;
import engine.interactions.BuffImparter;
import engine.interactions.CollisionEngine;


public class CollisionEngineAwesome extends CollisionEngine {
    public CollisionEngineAwesome () {
        this.put(new FriendlyProjectileType(), new EnemyTowerType(), new BuffImparter());
        this.put(new EnemyProjectileType(), new FriendlyTowerType(), new BuffImparter());
    }
}
