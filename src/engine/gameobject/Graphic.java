package engine.gameobject;

import com.thoughtworks.xstream.annotations.XStreamOmitField;
import engine.fieldsetting.Settable;
import javafx.scene.Node;
import javafx.scene.image.ImageView;


/**
 * This class encapsulates the node for each game object.
 * 
 * @author Sierra Smith and Cosette Goldstein
 *
 */
public class Graphic {

    // note to self: need to change this image path default when using data files
    private static final String DEFAULT_IMAGE_PATH_PREFIX = "images/";

    private double myHeight;
    private double myWidth;
    private String myImageName;
    @XStreamOmitField
    private transient ImageView myImageView;

    public Graphic () {
        myImageName="";
        myImageView=new ImageView();
    }
    
    public Graphic (double height, double width, String name) {
        myHeight = height;
        myWidth = width;
        myImageName = name;
        initializeImageView();
    }

    private void initializeImageView () {
        myImageView = new ImageView(DEFAULT_IMAGE_PATH_PREFIX + myImageName);
        myImageView.setFitHeight(myHeight);
        myImageView.setFitWidth(myWidth);
    }

    // note: may not need get method if resizeGraphic returns node
    public Node getNode () {
        return myImageView;
    }

    public Node getResizedGraphic (double scaleFactor) {
        myImageView.setPreserveRatio(true);
        myImageView.setFitHeight(myHeight * scaleFactor);
        return myImageView;
    }

    public void setPoint (PointSimple point) {
        myImageView.setX(point.getX());
        myImageView.setY(point.getY());
    }
    
    @Settable
    public void setWidth (double width) {
        myWidth = width;
    }
    
    @Settable
    public void setHeight (double height) {
        myHeight = height;
    }

    @Settable
    public void setImageName (String imageName) {
        myImageName = imageName;
    }
}
