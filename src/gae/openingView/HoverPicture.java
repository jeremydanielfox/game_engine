package gae.openingView;

import gae.gameView.Main;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class HoverPicture {

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
