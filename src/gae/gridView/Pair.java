package gae.gridView;

/**
 * Pair class written for easy conversion of coordinates into XML. Didn't use Point2D as it is a
 * JavaFX object.
 *
 * @author Kei
 *
 */
public class Pair {
    private double x;
    private double y;

    public Pair (double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX () {
        return x;
    }

    public double getY () {
        return y;
    }
}
