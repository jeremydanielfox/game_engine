package gae.backend;

import java.io.Serializable;
import java.util.List;
import javafx.scene.image.ImageView;
import engine.gameobject.PointSimple;
import engine.gameobject.labels.Type;
import engine.gameobject.weapon.Weapon;
import engine.shop.tag.GameObjectTag;
import gae.gridView.Path;
import gae.listView.Authorable;
import gae.listView.MovableImage;


public interface Placeable extends Serializable, Authorable {
    public void edit ();

    public String getName ();

    public String getType ();

    public String getImagePath ();

    public ImageView getImageView ();

    public PointSimple getLocation ();

    public double getHealth ();

    public int getID ();

    public Weapon getWeapon ();

    public List<List<Path>> getPath ();

    public int getWidth ();

    public int getHeight ();

    public GameObjectTag getTag ();
    
    public Type getLabel();

    public void setLocation (PointSimple point);

    public void setMovableImage (MovableImage image);

    public MovableImage getMovableImage ();

    public void setID (int id);

    public void setPath (List<List<Path>> path);

    public void setWidth (int width);

    public void setHeight (int height);

    public double setHealth (double health);

    public void setWeapon (Weapon weapon);

    public void setTag (GameObjectTag tag);

    public void setType (String type);

    public void setImagePath (String path);
    
    public void setLabel(Type label);

    public Object clone ();

    public Placeable makeNewInstance ();

}
