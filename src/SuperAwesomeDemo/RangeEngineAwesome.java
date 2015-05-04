package SuperAwesomeDemo;

import engine.gameobject.interactions.RangeEngine;
import engine.gameobject.interactions.ShootAt;
import engine.gameobject.test.EnemyTowerType;
import engine.gameobject.test.FriendlyTowerType;


public class RangeEngineAwesome extends RangeEngine {
    public RangeEngineAwesome () {
        this.put(new FriendlyTowerType(), new EnemyTowerType(), new ShootAt());
        this.put(new EnemyTowerType(), new FriendlyTowerType(), new ShootAt());
    }
}
