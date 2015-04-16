package gae.listView;

import engine.gameobject.GameObjectSimple;
import engine.gameobject.weapon.Weapon;
import gae.backend.Editable;
import gae.gridView.Pair;
import gae.gridView.Path;


/**
 * A temporary adaptor class that converts the engine's GameObjectSimple object to an Editable
 * object, as GAE has been working with Editables. Essentially takes in the engine's object and
 * takes out necessary information from it for GAE use.
 * 
 * @author Kei
 *
 */
public class GameObjectToEditable implements Editable {
    private GameObjectSimple gameObject;
    private static final long serialVersionUID = 1L;
    private int myID = 0;
    private int Size = 10;
    private int Health = 100;
    private Weapon weapon;
    private Pair location;
    private String imagePath;
    private MovableImage movableImage;
    private String name;
    private String type;
    private Path myPath;

    public GameObjectToEditable (GameObjectSimple gameobject) {
        this.gameObject = gameobject;
        /*
         * doing the following instantiation because it doesn't copy GameObjectSimple (not
         * Serializable)
         * TODO: find out how to copy the object
         */
        name = gameObject.getTag().getName();
        imagePath = gameObject.getTag().getGraphic().getImagePath();
        type = gameObject.getLabel();
        // gameobject is not serializable and gives an error so must set to null
        gameObject = null;
    }

    @Override
    public void edit () {
        // TODO Auto-generated method stub
    }

    public GameObjectSimple getGameObject () {
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

    public Pair getLocation () {
        return location;
    }

    public void setLocation (double x, double y) {
        location = new Pair(x, y);
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
    public Path getPath () {
        // TODO Auto-generated method stub
        return myPath;
    }

    @Override
    public void setPath (Path path) {
        // TODO Auto-generated method stub
        myPath = path;
    }
}
