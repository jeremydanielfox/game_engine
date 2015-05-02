package gae.backend;

import java.io.Serializable;
import javafx.scene.image.ImageView;
import engine.gameobject.GameObject;
import engine.gameobject.PointSimple;
import engine.gameobject.graphics.Graphic;
import engine.gameobject.healths.Health;
import engine.gameobject.labels.Type;
import engine.gameobject.movers.Mover;
import engine.gameobject.units.Collider;
import engine.gameobject.weapon.Weapon;
import engine.shop.ShopTag;
import gae.listView.Authorable;
import gae.listView.MovableImage;


public interface Placeable extends Serializable, Authorable {
    public void edit ();

    public String getName ();

    public String getType ();

    public String getImagePath ();

    public String getShopImagePath ();

    public ImageView getImageView ();

    public ImageView getShopImageView ();

    public PointSimple getLocation ();

    public Health getHealth ();

    public int getID ();

    public Weapon getWeapon ();

    public Mover getPath ();

    public int getWidth ();

    public int getHeight ();

    public Type getLabel ();

    public void setLocation (PointSimple point);

    public void setMovableImage (MovableImage image);

    public MovableImage getMovableImage ();

    public void setID (int id);

    public void setPath (Mover path);

    public void setWidth (int width);

    public void setHeight (int height);

    public void setHealth (Health health);

    public void setWeapon (Weapon weapon);

    public void setType (String type);

    public void setImagePath (String path);

    public void setGraphic (Graphic graphic);

    public void setShopImagePath (String path);

    public void setLabel (Type label);

    public void setCollider (Collider collider);
    
    public String getTitle();

    public Object clone ();

    public Placeable makeNewInstance ();

    public Graphic getGraphic ();

    public void setName (String name);

    public void setDescription (String description);

    public ShopTag getShopTag ();

    public String getDescription ();

    public GameObject getGameObject ();

    public Collider getCollider ();
}
