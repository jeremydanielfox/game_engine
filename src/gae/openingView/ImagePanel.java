package gae.openingView;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * ImagePanel holds visuals to represent the types of game available for the author to create.
 * A mix of CSS and Java logic is used to set up effects such as hover, select, etc.
 * 
 * @author Brandon Choi
 *
 */

public class ImagePanel implements UIObject {

    private static final ImageView SET_PATH_GAME = new ImageView("/images/bloonsdefense.jpg");
    private static final ImageView FREE_PATH_GAME = new ImageView("/images/desktopdefense.jpg");
    private static final ImageView SIDE_VIEW_GAME = new ImageView("/images/cartoonwars.jpg");
    private static final Text SET_PATH = new Text("Set Path");
    private static final Text FREE_PATH = new Text("Free Path");
    private static final Text SIDE_VIEW = new Text("Side View");
    private static final Text HEADER = new Text("Select Tower Defense Genre: ");

    private UIMediator myMediator;
    private VBox panel, selections;
    private UIObject setPath, freePath, sideView;

    public ImagePanel (UIMediator mediator) {
        myMediator = mediator;
        panel = new VBox(15);
        selections = new VBox(15);
        setIDs();
        createSelectOptions();
        panel.getChildren().addAll(HEADER, selections);
    }
    
    @Override
    public Node getObject () {
        return panel;
    }

    /**
     * set up IDs for nodes in order to enable editing through CSS files
     */
    private void setIDs () {
        HEADER.setId("selectGameText");
        panel.setId("rightPanel");
        selections.setId("imagePanel");
    }

    
    /**
     * creates all the select options by making new instances of HoverPicture
     */
    private void createSelectOptions () {
        setPath = new HoverPicture(SET_PATH_GAME, SET_PATH);
        freePath = new HoverPicture(FREE_PATH_GAME, FREE_PATH);
        sideView = new HoverPicture(SIDE_VIEW_GAME, SIDE_VIEW);
        selections.getChildren().addAll(setPath.getObject(), freePath.getObject(), sideView.getObject());
        setUpClick(selections);
    }
    
    
    /**
     * 
     * @param choices
     */
    private void setUpClick (Pane choices) {
        //if one is selected, disable the rest
        
    }
}
