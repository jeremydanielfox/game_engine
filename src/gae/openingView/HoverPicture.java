package gae.openingView;

import gae.gameView.Main;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * HoverPicture is a specific object used by ImagePanel. Each HoverPicture represents an image of
 * the game type as well as an overlying text with the label. Text disappears when author hovers
 * over the image.
 * 
 * @author Brandon Choi
 *
 */

public class HoverPicture implements UIObject {

    private static final double IMAGE_WIDTH = Main.SCREEN_WIDTH / 4;
    private static final double IMAGE_HEIGHT = Main.SCREEN_HEIGHT / 4;
    private StackPane view;
    private ImageView image;
    private Text label;
    private boolean selected;

    public HoverPicture (ImageView i, Text subtext) {
        view = new StackPane();
        image = i;
        image.getStyleClass().add("hoverPicture");
        i.setFitHeight(IMAGE_HEIGHT);
        i.setFitWidth(IMAGE_WIDTH);
        label = subtext;
        label.getStyleClass().add("gameLabel");
        view.getChildren().addAll(i, label);
        setHoverEffect();
        selected = false;
    }

    /**
     * returns the name of the HoverPicture
     * @return
     */
    public String getName () {
        return label.getText();
    }
    
    
    @Override
    public Node getObject () {
        return view;
    }

    /**
     * returns whether or not the instance of HoverPicture has been selected
     * @return
     */
    public boolean selected () {
        return selected;
    }

    /**
     * sets up the hovering effect by altering the text's visibility
     */
    private void setHoverEffect () {
        if (!selected) {
            image.setOnMouseEntered(e -> {
                label.setVisible(false);
            });
            image.setOnMouseExited(e -> {
                label.setVisible(true);
            });
        }
        else {
            label.setVisible(false);
        }
    }

    /**
     * sets the select status
     */
    public void changeSelectEffect () {
        selected = !selected;
    }
    
    /**
     * alter status of the hover picture
     */
    public void alter() {
        if (image.isDisable()){
            view.getChildren().add(label);
            image.setDisable(false);
        }
        else {
            view.getChildren().remove(label);
            image.setDisable(true);
        }
    }
}
