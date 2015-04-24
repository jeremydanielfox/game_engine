package gae.backend;

import engine.gameobject.PointSimple;
import engine.gameobject.weapon.Weapon;
import gae.gridView.Path;
import gae.listView.DeepCopy;
import gae.listView.MovableImage;
import gae.openingView.UIObject;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import View.ImageUtilities;


public class TempEnemy implements Editable, UIObject {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    private int myID = 0;
    private static int ourID = 0;
    private int Size = 10;
    private int Health = 100;
    private Weapon weapon;
    private PointSimple location;
    private String image = "/images/robertDuvall.jpg";
    private String myName = "TempTower";
    private MovableImage editableImage;

    @Override
    public void edit () {
        // TODO Auto-generated method stub

    }

    @Override
    public String getImagePath () {
        return image;
    }

    @Override
    public String getName () {
        // TODO Auto-generated method stub
        return "TempEnemy ";
    }

    @Override
    public String getType () {
        // TODO Auto-generated method stub
        return "Enemy";
    }

    @Override
    public void setID (int id) {
        myID = id;
    }

    @Override
    public void setMovableImage (MovableImage image) {
        // TODO Auto-generated method stub
        editableImage = image;
    }

    @Override
    public MovableImage getMovableImage () {
        return editableImage;
    }

    @Override
    public Node getObject () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object clone () {
        try {
            return super.clone();
        }
        catch (Exception e) {
            return null;
        }
    }

    @Override
    public int getID () {
        return myID;
    }

    // @Override
    // public Path getPath () {
    // // TODO Auto-generated method stub
    // return null;
    // }
    //
    // @Override
    // public void setPath (Path path) {
    // // TODO Auto-generated method stub
    //
    // }

    @Override
    public void setWidth (int width) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setHeight (int height) {
        // TODO Auto-generated method stub

    }

    @Override
    public int getWidth () {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getHeight () {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public PointSimple getLocation () {
        // TODO Auto-generated method stub
        return location;
    }

    @Override
    public void setLocation (PointSimple point) {
        // TODO Auto-generated method stub
        location = point;
    }

    @Override
    public ImageView getImageView () {
        return ImageUtilities.changeImageSize(new ImageView(new Image(image)),
                                              75, 75);
    }

    @Override
    public Editable makeNewInstance () {
        // need to keep track of ID some other way because no more EditableNode
        Editable copy = (Editable) DeepCopy.copy(this);
        copy.setID(ourID);
        ourID++;
        return copy;
    }

    @Override
    public List<List<Path>> getPath () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setPath (List<List<Path>> path) {
        // TODO Auto-generated method stub

    }

    @Override
    public double getHealth () {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double setHealth (double health) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Weapon getWeapon () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setWeapon (Weapon weapon) {
        // TODO Auto-generated method stub

    }
}
