package View;

import javafx.scene.image.ImageView;


public class ImageUtilities {
    public static ImageView changeImageSize (ImageView original, double width, double height) {
        original.setFitWidth(width);
        original.setFitHeight(height);
        return original;
    }
}
