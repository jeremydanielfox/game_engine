package engine.gameobject;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import xml.DataManager;
import engine.gameobject.units.BuffableUnit;
import engine.gameobject.weapon.BasicWeapon;
import engine.gameobject.weapon.NullWeapon;
import engine.pathfinding.PathFixed;
import engine.pathfinding.PathSegmentBezier;
import gameworld.ObjectCollection;


/**
 * This is a test class for GameObjects, to be used only by the Game Engine for testing purposes.
 * The
 * GameObjectSimpleTest has a Node that is a Circle.
 * 
 * @author Jeremy
 *
 */
public class GameObjectSimpleTest extends BuffableUnit{

    public GameObjectSimpleTest () {
        myImagePath = "robertDuvall.jpg";
        myLabel = "test object";
        myPoint = new PointSimple(0, 10000); //This initializes them off the screen. If we don't do this, it will show a frame at this point. Needs to be fixed in a better manner.
        myHealth = new HealthSimple(4);
        super.setWeapon(new NullWeapon());
        PathFixed myPath = new PathFixed();
        PathSegmentBezier myBez = new PathSegmentBezier();
        List<PointSimple> points = new ArrayList<PointSimple>();
        points.add(new PointSimple(0,0));
        points.add(new PointSimple(100,800));
        points.add(new PointSimple(800,100));
        points.add(new PointSimple(500,500));
        myBez.setPoints(points);
        myPath.addPathSegment(myBez);
        myPath = DataManager.readFromXML(PathFixed.class, "src/gae/listView/Test.xml");
//        XStream xstream = new XStream(new DomDriver());
//        File file = new File("src/gae/listView/Test.xml");
//        myPath = (PathFixed) xstream.fromXML(file);
        myMover = new MoverPath(myPath,1);
        myGraphic = new Graphic(25, 25, myImagePath);
        myGraphic.setPoint(myPoint);
    }

    //This method is outdated. Now encapsulated in graphics class.
//    private void createNode () {
//        Circle circle = new Circle();
//        circle.setFill(Color.ALICEBLUE);
//        myNode = circle;
//    }

    // temporary
    public GameObject clone () {
            return (GameObject) super.clone();
    }
}