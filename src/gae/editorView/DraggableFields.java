package gae.editorView;

import View.ImageUtilities;
import gae.listView.Authorable;
import javafx.scene.image.ImageView;


public class DraggableFields implements Authorable {
    private ImageView image;
    private String name;

    public DraggableFields (String imagePath, String name) {
        ImageView image = new ImageView(imagePath);
        this.image = ImageUtilities.changeImageSize(image, 200, 200);
        this.name = name;
    }

    public DraggableFields (ImageView imageView) {
        this.image = imageView;
        // this.name = name;
    }

    public ImageView getImageView () {
        return image;
    }

    public ImageView getCloneImage () {
        return new ImageView(image.getImage());
    }

    public String getType () {
        // TODO Auto-generated method stub
        return "Image";
    }

    @Override
    public String getName () {
        // TODO Auto-generated method stub
        return name;
    }

    @Override
    public int getID () {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String getImagePath () {
        // TODO Auto-generated method stub
        return null;
    }

}
