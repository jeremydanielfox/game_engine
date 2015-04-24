package gae.listView;

import javafx.scene.image.ImageView;


public class ImageContainer implements Authorable {
    private ImageView image;

    public ImageContainer (ImageView image) {
        this.image = image;
    }

    public ImageView getImageView () {
        return image;
    }

    public String getType () {
        // TODO Auto-generated method stub
        return "Image";
    }

    @Override
    public String getName () {
        // TODO Auto-generated method stub
        return null;
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
