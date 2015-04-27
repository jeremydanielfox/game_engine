package engine.gameobject.test.bloons;

import xml.DataManager;
import engine.gameobject.GameObjectSimple;
import engine.gameobject.Graphic;
import engine.gameobject.HealthSimple;
import engine.gameobject.MoverPath;
import engine.gameobject.PointSimple;
import engine.gameobject.behaviors.Behavior;
import engine.gameobject.behaviors.SummonBehavior;
import engine.gameobject.test.EnemyLabel;
import engine.gameobject.weapon.NullWeapon;
import engine.pathfinding.PathFixed;

public class BlueBloon extends GameObjectSimple {
    
    public BlueBloon () {
        super();
        setLabel(new EnemyLabel());
        setGraphic(new Graphic(25, 35, "Blue_Bloon.png"));
        setPoint(new PointSimple(0, 10000));
        setHealth(new HealthSimple(1));
        setWeapon(new NullWeapon());
        PathFixed myPath = new PathFixed();
        myPath = DataManager.readFromXML(PathFixed.class, "src/xml/Path.xml");
        setMover(new MoverPath(myPath, 1.4));
        SummonBehavior summon = new SummonBehavior();
        summon.addSummon(new RedBloon());
        
        addOnDeathBehavior(summon);
    }
}
