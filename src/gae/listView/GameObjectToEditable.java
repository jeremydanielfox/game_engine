package gae.listView;

import engine.gameobject.GameObject;
import engine.gameobject.Graphic;
import engine.gameobject.Health;
import engine.gameobject.Mover;
import engine.gameobject.PointSimple;
import engine.gameobject.labels.Type;
import engine.gameobject.units.Collider;
import engine.gameobject.weapon.Weapon;
import engine.shop.ShopTag;
import engine.shop.ShopTagSimple;
import gae.backend.Placeable;
import gae.editorView.GameObjectInformation;
import javafx.scene.image.ImageView;
import View.ImageUtilities;


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
    private Weapon weapon;
    private PointSimple location;
    private String imagePath;
    private String shopImagePath;
    private MovableImage movableImage;
    private Graphic graphic;
    private Graphic shopGraphic;
    private String name;
    private String type;
    private Mover path;
    private int width;
    private int height;
    private ImageView imageView;
    private ImageView shopImageView;
    private String title;
    private String description;
    private Type gameObjectType;
    private Collider collider;
    private Health health;

    public GameObjectToEditable () {

    }

    public GameObjectToEditable (GameObject gameObject) {
        this.gameObject = gameObject;
        name = gameObject.getName();
        imagePath = gameObject.getGraphic().getImagePath();
        gameObjectType = gameObject.getLabel();
        type = gameObjectType.getName();
        description = gameObject.getDescription();
        shopImagePath = gameObject.getShopGraphic().getImagePath();
        imageView = new ImageView(gameObject.getGraphic().getImagePath());
        shopImageView = new ImageView(gameObject.getShopGraphic().getImagePath());
        path = gameObject.getMover();
        graphic = gameObject.getGraphic();
        shopGraphic = gameObject.getShopGraphic();
        location = gameObject.getPoint();
        weapon = gameObject.getWeapon();
        health = gameObject.getHealth();
        collider = gameObject.getCollider();
        title = GameObjectInformation.getInstance().getTitle(gameObject);
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
        copy.setWeapon(weapon);
        copy.setWidth(width);
        copy.setType(type);
        copy.setLabel(gameObjectType);
        copy.setImagePath(imagePath);
        copy.setShopImagePath(shopImagePath);
        copy.setID(ourID);
        copy.setImageView(imageView);
        copy.setShopImageView(shopImageView);
        copy.setName(name);
        copy.setDescription(description);
        copy.setCollider(collider);
        copy.setTitle(title);
        ourID++;
        return copy;
    }

    public void setTitle (String title) {
        this.title = title;
    }

    public void setImagePath (String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public void setShopImagePath (String path) {
        shopImagePath = path;
    }

    public void setImageView (ImageView imageView) {
        this.imageView = imageView;
    }

    public void setShopImageView (ImageView imageView) {
        this.shopImageView = imageView;
    }

    @Override
    public Health getHealth () {
        return health;
    }

    @Override
    public void setHealth (Health health) {
        this.health = health;
    }

    @Override
    public Weapon getWeapon () {
        return weapon;
    }

    @Override
    public void setWeapon (Weapon weapon) {
        this.weapon = weapon;
    }

    @Override
    public void setType (String type) {
        this.type = type;
    }

    public Type getLabel () {
        return gameObjectType;
    }

    @Override
    public Graphic getGraphic () {
        return graphic;
    }

    public void setLabel (Type label) {
        this.gameObjectType = label;
    }

    @Override
    public String getShopImagePath () {
        return shopImagePath;
    }

    @Override
    public ImageView getShopImageView () {
        return shopImageView;
    }

    @Override
    public void setName (String name) {
        this.name = name;
    }

    @Override
    public void setDescription (String description) {
        this.description = description;
    }

    @Override
    public Mover getPath () {
        return path;
    }

    @Override
    public void setPath (Mover path) {
        this.path = path;
    }

    @Override
    public void setGraphic (Graphic graphic) {
        this.graphic = graphic;
    }

    @Override
    public ShopTag getShopTag () {
        ShopTagSimple shopTag = new ShopTagSimple();
        shopTag.setName(name);
        shopTag.setDescription(description);
        shopTag.setShopGraphic(shopGraphic.clone());
        return shopTag;
    }

    @Override
    public String getDescription () {
        return description;
    }

    @Override
    public void setCollider (Collider collider) {
        this.collider = collider;
    }

    @Override
    public Collider getCollider () {
        return collider;
    }

    @Override
    public String getTitle () {
        // TODO Auto-generated method stub
        return title;
    }

}
