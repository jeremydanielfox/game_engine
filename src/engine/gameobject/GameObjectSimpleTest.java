package engine.gameobject;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import engine.fieldsetting.Settable;
import engine.gameobject.units.BuffableUnit;
import engine.gameobject.weapon.BasicWeapon;
import engine.gameobject.weapon.Weapon;
import engine.pathfinding.EndOfPathException;
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
        //createNode();
        myImagePath = "robertDuvall.jpg";
        myLabel = "test object";
        myPoint = new PointSimple(300,300);
        myHealth = new HealthSimple(3);
        
//        myMover = new MoverPoint(new PointSimple(600,600), .2);
        
        PathFixed myPath = new PathFixed();
        PathSegmentBezier myBez = new PathSegmentBezier();
        List<PointSimple> points = new ArrayList<PointSimple>();
        points.add(new PointSimple(0,0));
        points.add(new PointSimple(100,800));
        points.add(new PointSimple(800,100));
        points.add(new PointSimple(500,500));
        myBez.setPoints(points);
        myPath.addPathSegment(myBez);
        myMover = new MoverPath(myPath,1);
        setWeapon(new BasicWeapon());
        myGraphic = new Graphic(100, 100, myImagePath);
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

    @Override
    public void move () throws EndOfPathException {
        // TODO Auto-generated method stub
        PointSimple point = myMover.move(myPoint);
        myPoint = new PointSimple(new Point2D(point.getX(), point.getY()));
        myGraphic.setPoint(myPoint);
    }
    
	@Override
	    public void update (ObjectCollection world) {
	        if (isDead()){
	            onDeath();
	            return;
	        }
	        try{
	            move();
	        }
	        catch (EndOfPathException e){
	            
	        }
	        
	    }
}
