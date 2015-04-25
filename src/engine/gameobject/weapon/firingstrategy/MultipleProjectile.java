package engine.gameobject.weapon.firingstrategy;

import engine.gameobject.GameObject;
import engine.gameobject.PointSimple;
import gameworld.ObjectCollection;


public class MultipleProjectile extends BasicStrategy {

    private int projectilesCreated;

    public MultipleProjectile (int numProjectiles) {
        projectilesCreated = numProjectiles;
    }

    @Override
    public void execute (ObjectCollection world,
                         GameObject target,
                         PointSimple location,
                         GameObject prototype) {
        // PointSimple referencePoint = target.getPoint();
        // A point 45 degrees to current location as reference TODO: This only works for 8
        // projectiles right now.
        PointSimple referencePoint = new PointSimple(location.getX() + 5, location.getY() + 5);
        for (int i = 0; i < projectilesCreated; i++) {
            PointSimple newPoint =
                    rotatePoint(location, referencePoint, i * 2 * Math.PI / projectilesCreated);
            GameObject newProjectile = makeProjectile(location, newPoint, prototype);
            world.addObject(newProjectile);
        }
    }

    /**
     * Rotates target with respect to center by radian counterclockwise
     * 
     * @param center
     * @param target
     * @param radian
     * @return
     */
    private PointSimple rotatePoint (PointSimple center, PointSimple target, double radian) {
        double radius = PointSimple.distance(center, target);
        double referenceAngle = findReferenceAngle(center, target);
        double newAngle = referenceAngle + radian;
        PointSimple newPoint =
                center.add(new PointSimple(radius * Math.cos(newAngle), radius * Math.sin(newAngle)));
        return newPoint;
    }

    private double findReferenceAngle (PointSimple center, PointSimple target) {
        double xDiff = target.getX() - center.getX();
        double yDiff = target.getY() - center.getY();
        double finalAngle;
        if (xDiff == 0 && yDiff == 0) {
            finalAngle = 0;
        }
        else if (xDiff == 0) {
            finalAngle = (yDiff > 0 ? 1 : -1) * Math.PI / 2;
        }
        else {
            finalAngle = Math.atan(yDiff / xDiff);
            if (xDiff < 0) {
                finalAngle += Math.PI;
            }
        }
        return finalAngle;
    }

}
