package engine.gameobject.graphics;

import engine.gameobject.PointSimple;
import javafx.scene.image.ImageView;


/**
 * This class rotates the given node towards the point it's given from it's current location.
 *
 * @author Sierra Smith
 *
 */
public class RotateToPoint implements Rotator {

    public RotateToPoint () {
    }

    @Override
    public void rotate (ImageView image, PointSimple from, PointSimple to) {

        // note: duplicated code from multiple projectile, consider putting in a static or shared
        // class?
        double xDiff = to.getX() - from.getX();
        double yDiff = to.getY() - from.getY();
        double angle;
        if (xDiff == 0 && yDiff == 0) {
//            finalAngle = 0;
            return;
        }
        else if (xDiff == 0) {
            angle = (yDiff > 0 ? 1 : -1) * Math.PI / 2;
        }
        else {
            angle = Math.atan(yDiff / xDiff);
            if (xDiff < 0) {
                angle += Math.PI;
            }
        }
        if (image != null) {
            image.setRotate(Math.toDegrees(angle));
        }
    }

}
