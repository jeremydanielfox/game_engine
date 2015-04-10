package engine.gameobject.weapon.firingstrategy;

import java.util.ArrayList;
import java.util.List;
import engine.gameobject.GameObject;
import engine.gameobject.PointSimple;
import engine.gameobject.units.Buffable;
import gameworld.GameWorld;
import gameworld.ObjectCollection;


public class MultipleProjectile implements FiringStrategy {
    
    private int projectilesCreated;
    
    public MultipleProjectile(int numProjectiles){
        projectilesCreated = numProjectiles;
    }
//TODO: CLEAN THIS WHOLE CLASS UP
//TODO: implement this
    @Override
    public void execute(ObjectCollection world, Buffable target, PointSimple location, Buffer prototype) {
        List<Buffer> projectile = new ArrayList<Buffer>();
        Buffable myTarget = target;
        double referenceAngle = findReferenceAngle(location, myTarget.getPoint());
        //List<PointSimple> shootingLocations = getLocations(referenceAngle);

    }
    
    private PointSimple rotatePoint(PointSimple center, PointSimple target, double radian){
        double radius = center.distance(center, target);
        double xDiff = target.getX() - center.getX();
        double yDiff = target.getY() - center.getY();
        double referenceAngle = findReferenceAngle(center, target);
        PointSimple newPoint = center.add(new PointSimple(radius*Math.cos(referenceAngle), radius*Math.sin(referenceAngle)));
        return newPoint;
    }

    private double findReferenceAngle(PointSimple center, PointSimple target){
        double xDiff = target.getX() - center.getX();
        double yDiff = target.getY() - center.getY();
        if (xDiff == 0 && yDiff == 0)
                return 0;
        if (xDiff == 0)
                return Math.toDegrees((yDiff > 0 ? 1 : -1)*Math.PI/2);
        double finalAngle = Math.atan(yDiff/xDiff);
        if (xDiff < 0)
                finalAngle += Math.PI;
        return finalAngle;
    }
    
    private List<Double> getLocations(double referenceAngle){
        List<Double> myAngles = new ArrayList<Double>();
        for (int i = 0; i < projectilesCreated; i++){
            myAngles.add(referenceAngle + i * Math.PI/projectilesCreated);
        }
        return myAngles;
    }
}
