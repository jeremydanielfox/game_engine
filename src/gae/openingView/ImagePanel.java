package gae.openingView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
        /*
         * TODO: fix error with clicking other box and changing the field type? weird.
         */
        list.forEach(e -> {
            e.getObject().setOnMouseClicked(f -> {
                if (!e.selected()) {
                    e.changeSelectEffect();
                    alterRest(e);
                    bindValue.setValue(e.getName());
                }
                else if (e.selected()) {
                    e.changeSelectEffect();
                    alterRest(e);
                    bindValue.setValue(OpeningView.DEFAULT_TYPE_MSG);
                }
            });
        });
    }

    /**
     * alters the status of the rest of the hover pictures except the one selected
     * 
     * @param hp
     */
    private void alterRest (HoverPicture hp) {
        hoverPictures.forEach(e -> {
            if (!e.equals(hp))
                e.alter();
        });
    }
}
