package engine.gameobject.graphics;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import View.ViewUtil;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import engine.fieldsetting.Settable;
import engine.gameobject.PointSimple;


/**
 * This class encapsulates the node for each game object.
 *
 * @author Sierra Smith
 * @author Cosette Goldstein
 *
 */
@Settable
public class Graphic {

    private static final String DEFAULT_IMAGE_NAME = "/images/robertDuvall.jpg";
    private double myHeight;
    private double myWidth;
    private String myImageName;
    private Point2D myPoint;
    @XStreamOmitField
    private transient ImageView myImageView;
    private Rotator myRotator;

    public Graphic () {
        myImageName = DEFAULT_IMAGE_NAME;
        myPoint = new Point2D(0, 0);
        myImageView = new ImageView();
        myRotator = new RotateToPoint();
    }

    public Graphic (double height, double width, String name) {
        myHeight = height;
        myWidth = width;
        myImageName = name;
        myRotator = new RotateToPoint();
        myPoint = new Point2D(0, 0);
    }

    @Override
    public Graphic clone () {
        Graphic clone = new Graphic(myHeight, myWidth, myImageName);
        clone.initializeImageView();
        return clone;
    }

    private void initializeImageView () {
        myImageView = new ImageView(myImageName);
        myImageView.setX(myPoint.getX());
        myImageView.setY(myPoint.getY());
        myImageView.setFitHeight(myHeight);
        myImageView.setFitWidth(myWidth);
    }

    public Node getNode () {
        return getImageView();
    }

    public Node getResizedGraphic (double scaleFactor) {
        getImageView().setPreserveRatio(true);
        getImageView().setFitHeight(myHeight * scaleFactor);
        return getImageView();
    }

    /**
     * Assign the given point to both the ImageView and a copy of the point in this class.
     *
     * @param point
     */
    public void setPoint (PointSimple point) {
        Point2D temp = new Point2D(point.getX() + ViewUtil.getCenterOffsetX(myImageView),
                                   point.getY() + ViewUtil.getCenterOffsetY(myImageView));
        myPoint = temp;
        getImageView().relocate(myPoint.getX(), myPoint.getY());
    }

    @Settable
    public void setWidth (double width) {
        myWidth = width;
    }

    @Settable
    public void setHeight (double height) {
        myHeight = height;
    }

    @Settable(primary = true)
    public void setImagePath (String imageName) {
        myImageName = imageName.substring(imageName.lastIndexOf("/images"));
        // myImageName = imageName;
        initializeImageView();
    }

    /**
     * This method will be called both before and after this object is written to xstream. Thus,
     * At some point, the ImageView will be null because this object will have been written out of
     * xstream, and the imageview will have been omitted. At this point, the method re-initializes
     * the imageview using the stored criteria.
     *
     * @return
     */
    public ImageView getImageView () {
        if (myImageView == null) {
            initializeImageView();
        }
        return myImageView;
    }

    public double getCenterX () {
        return myImageView.getX() + ViewUtil.getCenterOffsetX(myImageView);
    }

    public double getCenterY () {
        return myImageView.getY() + ViewUtil.getCenterOffsetY(myImageView);
    }

    public String getImagePath () {
        return myImageName;
    }

    /**
     * Rotates the node for this graphic according to a point and its rotator. It uses the center of
     * the graphic as
     * the current/from point and the point passed in as the "to" point.
     *
     * @param point
     */
    public void rotate (PointSimple point) {
        Point2D centerOfMyImage =
                new Point2D(myPoint.getX() - ViewUtil.getCenterOffsetX(myImageView),
                            myPoint.getY() - ViewUtil.getCenterOffsetY(myImageView));
        myRotator.rotate(myImageView, new PointSimple(centerOfMyImage), point);
    }

    public void setRotator (Rotator rotator) {
        myRotator = rotator;
    }
}
