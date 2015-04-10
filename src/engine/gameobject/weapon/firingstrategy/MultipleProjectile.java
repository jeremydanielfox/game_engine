package engine.gameobject.weapon.firingstrategy;

import engine.gameobject.PointSimple;
import engine.gameobject.units.Buffable;
import gameworld.ObjectCollection;


public class MultipleProjectile extends BasicStrategy {
    
    private int projectilesCreated;
    
    public MultipleProjectile(int numProjectiles){
        projectilesCreated = numProjectiles;
    }
    
    @Override
    public void execute(ObjectCollection world, Buffable target, PointSimple location, Buffer prototype) {
        PointSimple referencePoint = target.getPoint();
        for (int i =0; i < projectilesCreated; i++){
            PointSimple newPoint = rotatePoint(location, referencePoint, i * 2 * Math.PI / projectilesCreated);
            Buffer newProjectile = makeProjectile(location, newPoint, prototype);
            world.addObject(newProjectile);
        }
    }
    
    /**
     * Rotates target with respect to center by radian counterclockwise
     * @param center
     * @param target
     * @param radian
     * @return
     */
    private PointSimple rotatePoint(PointSimple center, PointSimple target, double radian){
        double radius = PointSimple.distance(center, target);
        double referenceAngle = findReferenceAngle(center, target);
        double newAngle = referenceAngle + radian;
        PointSimple newPoint = center.add(new PointSimple(radius*Math.cos(newAngle), radius*Math.sin(newAngle)));
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
    
}
