package gae.listView;

import engine.gameobject.GameObjectSimple;
import engine.gameobject.weapon.Weapon;
import gae.backend.Editable;
import gae.gridView.Pair;


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
        gameObject = gameobject;
        name = gameObject.getName();
        imagePath = gameObject.getImagePath();
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
        return name + " " + myID;
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
}
