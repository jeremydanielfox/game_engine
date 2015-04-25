package engine.gameobject;

import javafx.scene.image.ImageView;


/**
 * A class can implement this interface when it has the ability to rotate something when given a
 * Point Simple.
 *
 * @author Sierra Smith
 *
 */
public interface Rotator {

    /**
     * Depending on the implementation of the rotator, this method will rotate the given node based
     * on a calculation, potentially involving the point passed in.
     *
     * @param point
     */
    public void rotate (ImageView image, PointSimple from, PointSimple to);
}
