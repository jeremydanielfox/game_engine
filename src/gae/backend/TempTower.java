package gae.backend;

import engine.gameobject.Graphic;
import engine.gameobject.Mover;
import engine.gameobject.PointSimple;
import engine.gameobject.labels.Type;
import engine.gameobject.weapon.Weapon;
import engine.shop.ShopTag;
import gae.listView.DeepCopy;
import gae.listView.MovableImage;
import gae.openingView.UIObject;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import View.ImageUtilities;


public class TempTower implements Placeable, UIObject {
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
    private String image = "/images/ArcaneTower.png";
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
        return "TempTower ";
    }

    @Override
    public String getType () {
        // TODO Auto-generated method stub
        return "Tower";
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
    public Placeable makeNewInstance () {
        // need to keep track of ID some other way because no more EditableNode
        Placeable copy = (Placeable) DeepCopy.copy(this);
        copy.setID(ourID);
        ourID++;
        return copy;
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

    @Override
    public void setType (String type) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setImagePath (String path) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public ShopTag getShopTag () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getShopImagePath () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ImageView getShopImageView () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setShopImagePath (String path) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Graphic getGraphic () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setName (String name) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setDescription (String description) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Mover getPath () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Type getLabel () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setPath (Mover path) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setGraphic (Graphic graphic) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setLabel (Type label) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String getDescription () {
        // TODO Auto-generated method stub
        return null;
    }
}
