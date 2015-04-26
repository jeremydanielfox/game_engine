package gae.backend;

import java.io.Serializable;
import java.util.List;
import javafx.scene.image.ImageView;
import engine.gameobject.Graphic;
import engine.gameobject.Mover;
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

    public Mover getPath ();

    public int getWidth ();

    public int getHeight ();

    public Graphic getGraphic ();

    public GameObjectTag getTag ();
    
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

    public void setTag (GameObjectTag tag);

    public void setType (String type);

    public void setImagePath (String path);
<<<<<<< HEAD

    public void setLabel (Label label);

    public void setGraphic (Graphic graphic);
=======
    
    public void setLabel(Type label);
>>>>>>> 0aac56c7c4d06524d21d2c4028cea44c18cc76ba

    public Object clone ();

    public Placeable makeNewInstance ();

}
