package gae.openingView;

import gae.gameView.Main;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ImagePanel {

    private static final ImageView SET_PATH_GAME = new ImageView("/images/bloonsdefense.jpg");
    private static final ImageView FREE_PATH_GAME = new ImageView("/images/desktopdefense.jpg");
    private static final ImageView SIDE_VIEW_GAME = new ImageView("/images/cartoonwars.jpg");
    private static final Text SET_PATH = new Text("Set Path");
    private static final Text FREE_PATH = new Text("Free Path");
    private static final Text SIDE_VIEW = new Text("Side View");
    private static final double IMAGE_WIDTH = Main.SCREEN_WIDTH / 4;
    private static final double IMAGE_HEIGHT = Main.SCREEN_HEIGHT / 4;
    private static final Text HEADER = new Text("Select Tower Defense Genre: ");

    private VBox panel;
    private VBox selections;
    private HoverPicture setPath, freePath, sideView;

    public ImagePanel () {
        initialize();
    }

    public Node getPanel () {
        return panel;
    }

    private void initialize () {
        panel = new VBox(15);
        selections = new VBox(15);
        HEADER.setId("selectGameText");
        panel.setId("rightPanel");
        selections.setId("imagePanel");

        setPath = new HoverPicture(SET_PATH_GAME, SET_PATH);
        freePath = new HoverPicture(FREE_PATH_GAME, FREE_PATH);
        sideView = new HoverPicture(SIDE_VIEW_GAME, SIDE_VIEW);

        selections.getChildren().addAll(setPath.getView(), freePath.getView(), sideView.getView());
        setUpClick(selections);
        panel.getChildren().addAll(HEADER, selections);
    }

    private void setUpClick (Pane choices) {
        //if one is selected, disable the rest
        
    }

    private class HoverPicture {

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
            setSelectEffect();
        }

        public Node getView () {
            return view;
        }
        
        public boolean selectStatus () {
            return selected;
        }

        private void setHoverEffect () {
            if (!selected) {
                image.setOnMouseEntered(e -> {
                    label.setVisible(false);
                });
                image.setOnMouseExited(e -> {
                    label.setVisible(true);
                });
            }
        }

        private void setSelectEffect () {
            if (selected) {
                view.setOnMouseClicked(e -> {
                    selected = false;
                });
            }
            else {
                view.setOnMouseClicked(e -> {
                    selected = true;
                });
            }
        }
    }
}
