package gae.backend;

import java.io.Serializable;
import gae.gridView.Pair;
import gae.gridView.Path;
import gae.listView.MovableImage;


public interface Editable extends Serializable {
    public void edit ();

    public String getName ();

    public String getType ();

    public String getImagePath ();

    public void setLocation (double x, double y);

    public Pair getLocation ();

    public void setMovableImage (MovableImage image);

    public MovableImage getMovableImage ();

    public void setID (int id);

    public int getID ();

    public Object clone ();

    public Path getPath ();

    public void setPath (Path path);
}
