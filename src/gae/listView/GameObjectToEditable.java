package gae.listView;

import engine.gameobject.GameObject;
import engine.gameobject.GameObjectSimple;
import engine.gameobject.Graphic;
import engine.gameobject.Mover;
import engine.gameobject.PointSimple;
import engine.gameobject.labels.Label;
import engine.gameobject.weapon.Weapon;
import engine.shop.tag.GameObjectTag;
import gae.backend.Placeable;
import gae.gridView.Path;
import java.util.List;
import View.ImageUtilities;
import javafx.scene.image.ImageView;


/**
 * A temporary adaptor class that converts the engine's GameObjectSimple object to an Editable
 * object, as GAE has been working with Editables. Essentially takes in the engine's object and
 * takes out necessary information from it for GAE use.
 *
 * @author Kei
 *
 */
public class GameObjectToEditable implements Placeable {
    private GameObject gameObject;
    private static final long serialVersionUID = 1L;
    private static int ourID = 0;
    private int myID = 0;
    private int Size = 10;
    private Weapon weapon;
    private PointSimple location;
    private String imagePath;
    private MovableImage movableImage;
    private Graphic graphic;
    private String name;
    private String type;
    private Mover path;
    private int width;
    private int height;
    private ImageView imageView;
    private double health;
    private GameObjectTag tag;
    private Label label;

    public GameObjectToEditable () {

    }

    public GameObjectToEditable (GameObject gameObject) {
        this.gameObject = gameObject;
        /*
         * doing the following instantiation because it doesn't copy GameObjectSimple (not
         * Serializable)
         * TODO: find out how to copy the object
         */
        name = gameObject.getTag().getName();
        imagePath = gameObject.getGraphic().getImagePath();
        type = gameObject.getLabel().getName();
//        imageView = (ImageView) gameObject.getTag().getGraphic().getResizedGraphic(1);
        System.out.println(imagePath);
        imageView = new ImageView(gameObject.getGraphic().getImagePath());
        tag = gameObject.getTag();
        path = gameObject.getMover();
        graphic = gameObject.getGraphic();
        // gameobject is not serializable and gives an error so must set to null
        // gameObject = null;
    }

    @Override
    public void edit () {
        // TODO Auto-generated method stub
    }

    public GameObject getGameObject () {
        return gameObject;
    }

    @Override
    public String getImagePath () {
        return imagePath;
    }

    @Override
    public String getName () {
        return name + " ";
    }

    @Override
    public String getType () {
        // TODO Auto-generated method stub
        return type;
    }

    @Override
    public void setID (int id) {
        myID = id;
    }

    @Override
    public void setMovableImage (MovableImage image) {
        // TODO Auto-generated method stub
        movableImage = image;
    }

    @Override
    public MovableImage getMovableImage () {
        return movableImage;
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

    @Override
    public Mover getPath () {
        // TODO Auto-generated method stub
        return path;
    }

    @Override
    public void setPath (Mover path) {
        // TODO Auto-generated method stub
        this.path = path;
    }

    @Override
    public int getWidth () {
        // TODO Auto-generated method stub
        return width;
    }

    @Override
    public int getHeight () {
        // TODO Auto-generated method stub
        return height;
    }

    @Override
    public void setWidth (int width) {
        // TODO Auto-generated method stub
        this.width = width;
    }

    @Override
    public void setHeight (int height) {
        // TODO Auto-generated method stub
        this.height = height;
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
        // currently hardcoding imageView but if Tag + Graphic work then...
        // return ImageUtilities.changeImageSize(new ImageView(new Image(imagePath)),
        // 75, 75);
        //
        //
        // imageView = (ImageView) gameObject.getTag().getGraphic().getResizedGraphic(1);
        // System.out.println("WIDTH IS : " + width);
        return ImageUtilities.changeImageSize(new ImageView(imageView.getImage()), 75, 75);
    }

    @Override
    public Placeable makeNewInstance () {
        // Placeable copy = (Placeable) DeepCopy.copy(this);
        GameObjectToEditable copy = new GameObjectToEditable();
        copy.setHealth(health);
        copy.setHeight(height);
        copy.setLocation(location);
        copy.setPath(path);
        copy.setTag(tag);
        copy.setWeapon(weapon);
        copy.setWidth(width);
        copy.setType(type);
        copy.setImagePath(imagePath);
        copy.setID(ourID);
        copy.setImageView(imageView);
        ourID++;
        return copy;
    }

    public void setImagePath (String imagePath) {
        this.imagePath = imagePath;
    }

    public void setImageView (ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    public double getHealth () {
        // TODO Auto-generated method stub
        return health;
    }

    @Override
    public double setHealth (double health) {
        // TODO Auto-generated method stub
        return this.health = health;
    }

    @Override
    public Weapon getWeapon () {
        // TODO Auto-generated method stub
        return weapon;
    }

    @Override
    public void setWeapon (Weapon weapon) {
        // TODO Auto-generated method stub
        this.weapon = weapon;
    }

    @Override
    public GameObjectTag getTag () {
        // TODO Auto-generated method stub
        return tag;
    }

    @Override
    public void setTag (GameObjectTag tag) {
        // TODO Auto-generated method stub
        this.tag = tag;
    }

    @Override
    public void setType (String type) {
        // TODO Auto-generated method stub
        this.type = type;
    }

    @Override
    public Label getLabel () {
        // TODO Auto-generated method stub
        return label;
    }

    @Override
    public void setLabel (Label label) {
        // TODO Auto-generated method stub
        this.label = label;
    }

    @Override
    public Graphic getGraphic () {
        // TODO Auto-generated method stub
        return graphic;
    }

    @Override
    public void setGraphic (Graphic graphic) {
        // TODO Auto-generated method stub
        this.graphic = graphic;
    }
}
