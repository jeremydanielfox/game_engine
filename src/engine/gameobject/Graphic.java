package engine.gameobject;

import com.thoughtworks.xstream.annotations.XStreamOmitField;
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

    private int myHeight;
    private int myWidth;
    private String myImageName;
    @XStreamOmitField
    private transient ImageView myImageView;

    public Graphic (int height, int width, String name) {
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
}
