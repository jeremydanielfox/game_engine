package gae.backend;

import java.io.Serializable;
import gae.gridView.Pair;
import gae.listView.MovableImage;


public interface Editable extends Serializable {
    public void edit ();

    public String getName ();

    public String getType ();

    public String getImagePath ();

    public void setLocation (double x, double y);

    public Pair getLocation ();

    public void setEditableImage (MovableImage image);

    public MovableImage getEditableImage ();

    public void setID (int id);

    public int getID ();

    public Object clone ();

}
