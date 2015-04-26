package gae.backend;


import engine.gameobject.Graphic;
import engine.gameobject.Mover;
import engine.gameobject.PointSimple;
import engine.gameobject.labels.Type;
import engine.gameobject.weapon.Weapon;
import engine.shop.ShopTag;
import gae.listView.Authorable;
import gae.listView.MovableImage;
import java.io.Serializable;
import javafx.scene.image.ImageView;


public interface Placeable extends Serializable, Authorable {
    public void edit ();

    public String getName ();

    public String getType ();

    public String getImagePath ();
    
    public String getShopImagePath ();

    public ImageView getImageView ();
    
    public ImageView getShopImageView ();

    public PointSimple getLocation ();

    public double getHealth ();

    public int getID ();

    public Weapon getWeapon ();

    public Mover getPath ();

    public int getWidth ();

    public int getHeight ();
    
    public Type getLabel();

    public void setLocation (PointSimple point);

    public void setMovableImage (MovableImage image);

    public MovableImage getMovableImage ();

    public void setID (int id);

    public void setPath (Mover path);

    public void setWidth (int width);

    public void setHeight (int height);

    public double setHealth (double health);

    public void setWeapon (Weapon weapon);

    public void setType (String type);

    public void setImagePath (String path);

    public void setGraphic (Graphic graphic);
    
    public void setShopImagePath (String path);
    
    public void setLabel(Type label);

    public Object clone ();

    public Placeable makeNewInstance ();
    
    public Graphic getGraphic ();
    
    public void setName (String name);
    
    public void setDescription (String description);

    public ShopTag getShopTag ();

    public String getDescription ();

}
