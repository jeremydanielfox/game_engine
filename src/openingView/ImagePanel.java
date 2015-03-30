package openingView;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ImagePanel {

    private static final ImageView SET_PATH_GAME = new ImageView("/images/bloonsdefense.jpg");
    private static final ImageView FREE_PATH_GAME = new ImageView("/images/desktopdefense.jpg");
    private static final ImageView SIDE_VIEW_GAME = new ImageView("/images/cartoonwars.jpg");
    private static final Text SET_PATH = new Text("Set Path");
    private static final Text FREE_PATH = new Text("Free Path");
    private static final Text SIDE_VIEW = new Text("Side View / Runner");
    private static final double IMAGE_WIDTH = OpeningView.SCREEN.getVisualBounds().getWidth() / 4;
    private static final double IMAGE_HEIGHT = OpeningView.SCREEN.getVisualBounds().getHeight() / 4;
    private static final Text HEADER = new Text("Select Tower Defense Genre: ");

    private VBox panel;
    private VBox images;
    private HoverPicture setPath, freePath, sideView;

    public ImagePanel () {
        initialize();
    }

    public Node getPanel () {
        return panel;
    }

    private void initialize () {
        panel = new VBox(15);
        images = new VBox(15);
        HEADER.setId("selectGameText");
        panel.setId("rightPanel");
        images.setId("imagePanel");
        
        setPath = new HoverPicture(SET_PATH_GAME, SET_PATH);
        freePath = new HoverPicture(FREE_PATH_GAME, FREE_PATH);
        sideView = new HoverPicture(SIDE_VIEW_GAME, SIDE_VIEW);
        
        images.getChildren().addAll(setPath.getView(), freePath.getView(), sideView.getView());
        setUpClick(images);
        panel.getChildren().addAll(HEADER, images);
    }

    private void setUpClick (Node choices) {
        
    }

    private class HoverPicture {

        private StackPane view;
        private ImageView image;
        private Text label;

        public HoverPicture (ImageView i, Text subtext) {
            view = new StackPane();
            image = i;
            image.getStyleClass().add("hoverPicture");
            i.setFitHeight(IMAGE_HEIGHT);
            i.setFitWidth(IMAGE_WIDTH);
            label = subtext;
            view.getChildren().addAll(i, label);
            setHoverEffect();
        }
        
        public Node getView() {
            return view;
        }

        private void setHoverEffect () {
            image.setOnMouseEntered(e -> {
                label.setVisible(false);
            });
            image.setOnMouseExited(e -> {
                label.setVisible(true);
            });
        }
    }
}
