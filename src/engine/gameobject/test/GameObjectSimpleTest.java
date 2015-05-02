package engine.gameobject.test;

import java.util.ArrayList;
import java.util.List;
import xml.DataManager;
import engine.gameobject.GameObjectSimple;
import engine.gameobject.PointSimple;
import engine.gameobject.graphics.Graphic;
import engine.gameobject.healths.HealthSimple;
import engine.gameobject.movers.MoverPath;
import engine.gameobject.weapon.NullWeapon;
import engine.pathfinding.PathFixed;
import engine.pathfinding.PathSegmentBezier;


/**
 * This is a test class for GameObjects, to be used only by the Game Engine for testing purposes.
 * The
 * GameObjectSimpleTest has a Node that is a Circle.
 *
 * @author Jeremy
 *
 */
public class GameObjectSimpleTest extends GameObjectSimple {

    public GameObjectSimpleTest () {
        super();
        setType(new EnemyTowerType());
        setGraphic(new Graphic(25, 25, "/images/robertDuvall.jpg"));
        setPoint(new PointSimple(0, 10000)); // This initializes them off the screen. If we don't do
                                             // this, it will show a frame at this point. Needs to
                                             // be fixed in a better manner.
        setHealth(new HealthSimple(4));
        setWeapon(new NullWeapon());
        PathFixed myPath = new PathFixed();
        myPath = DataManager.readFromXML(PathFixed.class, "src/xml/Path.xml");
        // XStream xstream = new XStream(new DomDriver());
        // File file = new File("src/gae/listView/Test.xml");
        // myPath = (PathFixed) xstream.fromXML(file);
        setMover(new MoverPath(myPath, 1));
    }

}
