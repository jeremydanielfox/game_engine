package gae.listView;

import engine.gameobject.GameObjectSimple;
import engine.gameobject.weapon.Weapon;
import gae.backend.Editable;
import gae.gridView.Pair;


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
    private MovableImage editableImage;
    private String name;
    private String type;

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
    public void setEditableImage (MovableImage image) {
        // TODO Auto-generated method stub
        editableImage = image;
    }

    @Override
    public MovableImage getEditableImage () {
        return editableImage;
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
}
