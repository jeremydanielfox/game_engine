package gae.backend;

import java.io.Serializable;
import javafx.scene.image.ImageView;
import engine.gameobject.PointSimple;
import engine.gameobject.weapon.Weapon;
import gae.gridView.Path;
import gae.listView.MovableImage;
import java.util.List;


public interface Editable extends Serializable {
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

    public void setLocation (PointSimple point);

    public void setMovableImage (MovableImage image);

    public MovableImage getMovableImage ();

    public void setID (int id);

    public void setPath (List<List<Path>> path);

    public void setWidth (int width);

    public void setHeight (int height);

    public double setHealth (double health);

    public void setWeapon (Weapon weapon);

    public Object clone ();

    public Editable makeNewInstance ();

}
