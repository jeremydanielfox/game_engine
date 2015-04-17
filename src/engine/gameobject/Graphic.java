package engine.gameobject;


import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import engine.fieldsetting.Settable;


/**
 * This class encapsulates the node for each game object.
 * 
 * @author Sierra Smith and Cosette Goldstein
 *
 */
@Settable
public class Graphic {

    // note to self: need to change this image path default when using data files
    private static final String DEFAULT_IMAGE_PATH_PREFIX = "images/";
    private static final String DEFAULT_IMAGE_NAME = "robertDuvall.jpg";

    private double myHeight;
    private double myWidth;
    private String myImageName;
    private Point2D myPoint;
    @XStreamOmitField
    private transient ImageView myImageView;

    public Graphic () {
        myImageName = DEFAULT_IMAGE_NAME;
        myPoint = new Point2D(0, 0);
        myImageView = new ImageView();
    }

    public Graphic (double height, double width, String name) {
        myHeight = height;
        myWidth = width;
        myImageName = name;
    }

    private void initializeImageView () {
        myImageView = new ImageView(DEFAULT_IMAGE_PATH_PREFIX + myImageName);
        // for TEST purpose:
        if (myPoint==null){
            myPoint = new Point2D(0, 0);
        }
        myImageView.setX(myPoint.getX());
        myImageView.setY(myPoint.getY());
        myImageView.setFitHeight(myHeight);
        myImageView.setFitWidth(myWidth);
    }

    // note: may not need get method if resizeGraphic returns node
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
        myPoint = new Point2D(point.getX(), point.getY());
        getImageView().setX(point.getX());
        getImageView().setY(point.getY());
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
    public void setImageName (String imageName) {
        myImageName = imageName;
        initializeImageView();
    }

    /**
     * This method will be called both before and after this object is written to xstream. Thus,
     * At some point, the ImageView will be null because this object will have been written out of
     * xstream,
     * and the imageview will have been omitted. At this point, the method re-initializes the
     * imageview
     * using the stored criteria.
     * 
     * @return
     */
    private ImageView getImageView () {
        if (myImageView == null)
            initializeImageView();
        return myImageView;
    }


     public double getCenterX () {
     // To do
     return 0;
     }
    
     public double getCenterY () {
     // To do
     return 0;
     }

    
    public String getImagePath () {
        return DEFAULT_IMAGE_PATH_PREFIX + myImageName;
    }
}
