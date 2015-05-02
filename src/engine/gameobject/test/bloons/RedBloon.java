package engine.gameobject.test.bloons;

import xml.DataManager;
import engine.gameobject.GameObjectSimple;
import engine.gameobject.PointSimple;
import engine.gameobject.graphics.Graphic;
import engine.gameobject.healths.HealthSimple;
import engine.gameobject.movers.MoverPath;
import engine.gameobject.test.EnemyTowerType;
import engine.gameobject.weapon.NullWeapon;
import engine.pathfinding.PathFixed;

public class RedBloon extends GameObjectSimple{

    public RedBloon(){
        super();
        setType(new EnemyTowerType());
        setGraphic(new Graphic(18, 23, "/images/Red_Bloon.png"));
        setPoint(new PointSimple(0, 10000)); 
        setHealth(new HealthSimple(1));
        setWeapon(new NullWeapon());
        PathFixed myPath = new PathFixed();
        myPath = DataManager.readFromXML(PathFixed.class, "src/xml/Path.xml");
        setMover(new MoverPath(myPath, 1));
    }
}
