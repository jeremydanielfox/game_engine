package engine.gameobject;

import java.io.Serializable;
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
@Settable
public class Graphic {

    // note to self: need to change this image path default when using data files
    private static final String DEFAULT_IMAGE_PATH_PREFIX = "images/";

    private double myHeight;
    private double myWidth;
    private String myImageName;
    @XStreamOmitField
    private transient ImageView myImageView;

    public Graphic () {
        myImageName = "";
        myImageView = new ImageView();
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
        return getImageView();
    }

    public Node getResizedGraphic (double scaleFactor) {
        getImageView().setPreserveRatio(true);
        getImageView().setFitHeight(myHeight * scaleFactor);
        return getImageView();
    }

    public void setPoint (PointSimple point) {
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
    }
    
    private ImageView getImageView() {
        if (myImageView == null) 
            initializeImageView();
        return myImageView;
    }
}
