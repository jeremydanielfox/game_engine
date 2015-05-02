package engine.gameobject.graphics;

import engine.gameobject.PointSimple;
import javafx.scene.image.ImageView;


/**
 * This class implements the Rotator interface and thus can be used as/considered a rotator, but it
 * does not actually rotate the given node and thus can be used when a rotator is required but no
 * rotation is desired.
 *
 * @author Sierra Smith
 *
 */
public class RotatorNull implements Rotator {

    public RotatorNull () {
    }

    @Override
    public void rotate (ImageView image, PointSimple from, PointSimple to) {
        // Does nothing
    }

}
