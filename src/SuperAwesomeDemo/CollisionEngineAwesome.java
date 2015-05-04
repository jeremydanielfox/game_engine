package SuperAwesomeDemo;

import engine.gameobject.interactions.BuffImparter;
import engine.gameobject.interactions.CollisionEngine;
import engine.gameobject.test.EnemyTowerType;
import engine.gameobject.test.FriendlyTowerType;


public class CollisionEngineAwesome extends CollisionEngine {
    public CollisionEngineAwesome () {
        this.put(new FriendlyProjectileType(), new EnemyTowerType(), new BuffImparter());
        this.put(new EnemyProjectileType(), new FriendlyTowerType(), new BuffImparter());
        this.put(new FriendlyProjectileType(), new NeutralGameType(), new BuffImparter());
        this.put(new EnemyProjectileType(), new NeutralGameType(), new BuffImparter());
        this.put(new PowerupType(), new FriendlyTowerType(), new BuffImparter());
    }
}
