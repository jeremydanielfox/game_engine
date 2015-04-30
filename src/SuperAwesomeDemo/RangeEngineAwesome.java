package SuperAwesomeDemo;

import engine.gameobject.test.EnemyLabel;
import engine.gameobject.test.TowerLabel;
import engine.interactions.RangeEngine;
import engine.interactions.ShootAt;


public class RangeEngineAwesome extends RangeEngine {
    public RangeEngineAwesome () {
        this.put(new TowerLabel(), new EnemyLabel(), new ShootAt());
    }
}
