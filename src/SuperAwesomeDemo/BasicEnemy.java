package SuperAwesomeDemo;

import xml.DataManager;
import engine.gameobject.GameObjectSimple;
import engine.gameobject.Graphic;
import engine.gameobject.HealthSimple;
import engine.gameobject.MoverPath;
import engine.gameobject.PointSimple;
import engine.gameobject.RotatorNull;
import engine.gameobject.test.EnemyTowerType;
import engine.gameobject.weapon.NullWeapon;
import engine.pathfinding.PathFixed;


public class BasicEnemy extends GameObjectSimple {
    public BasicEnemy () {
        super();
        setLabel(new EnemyTowerType());
        Graphic myGraphic = new Graphic(40, 40, "/images/GanonScary.png");
        myGraphic.setRotator(new RotatorNull());
        setGraphic(myGraphic);
        setPoint(new PointSimple(0, 10000));
        setHealth(new HealthSimple(1));
        setWeapon(new NullWeapon());
        PathFixed myPath = new PathFixed();
        myPath = DataManager.readFromXML(PathFixed.class, "src/xml/Path.xml");
        setMover(new MoverPath(myPath, 1.4));
    }
  
}
