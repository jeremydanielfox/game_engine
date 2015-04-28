package gae.openingView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * ImagePanel holds visuals to represent the types of game available for the author to create. A mix
 * of CSS and Java logic is used to set up effects such as hover, select, etc.
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
    private HoverPicture setPath, freePath, sideView;
    private SimpleStringProperty bindValue;
    private List<HoverPicture> hoverPictures;

    public ImagePanel (UIMediator mediator, SimpleStringProperty binded) {
        myMediator = mediator;
        panel = new VBox(15);
        selections = new VBox(15);
        bindValue = binded;
        hoverPictures = new ArrayList<>();
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
        selections.getChildren().addAll(setPath.getObject(), freePath.getObject(),
                                        sideView.getObject());
        hoverPictures = Arrays.asList(setPath, freePath, sideView);
        setUpClick(hoverPictures);
    }

    /**
     * sets up the logic of clicking one game option
     *
     * @param choices
     */
    private void setUpClick (List<HoverPicture> list) {
        list.forEach(e -> {
            e.getObject().setOnMouseClicked(click -> {
                if (!e.selected() && !anySelected()) {
                    e.changeSelectEffect();
                    bindValue.set(e.getName());
                    alterRest(e);
                }
                else if (e.selected()){
                    alterRest(e);
                    e.changeSelectEffect();
                    bindValue.set(OpeningView.DEFAULT_TYPE_MSG);
                }
            });
        });
    }

    /**
     * checks to see if any of the images have been selected
     * 
     * @return
     */
    private boolean anySelected () {
        for (int i = 0; i < hoverPictures.size(); i++) {
            if (hoverPictures.get(i).selected())
                return true;
        }
        return false;
    }

    /**
     * alters the status of the rest of the hover pictures except the one selected
     *
     * @param hp
     */
    private void alterRest (HoverPicture hp) {
        hoverPictures.forEach(e -> {
            if (!e.equals(hp)) {
                e.alter();
            }
        });
    }
}
