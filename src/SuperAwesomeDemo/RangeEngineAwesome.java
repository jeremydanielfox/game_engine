package SuperAwesomeDemo;

import engine.gameobject.test.EnemyTowerType;
import engine.gameobject.test.FriendlyTowerType;
import engine.interactions.RangeEngine;
import engine.interactions.ShootAt;


public class RangeEngineAwesome extends RangeEngine {
    public RangeEngineAwesome () {
        this.put(new FriendlyTowerType(), new EnemyTowerType(), new ShootAt());
        this.put(new EnemyTowerType(), new FriendlyTowerType(), new ShootAt());
    }
}
