package gae.listView;

import javafx.scene.Node;
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
    private String image = "/images/ArcaneTower.png";
    private String myName = "TempTower";
    private EditableImage editableImage;

    public GameObjectToEditable (GameObjectSimple gameobject) {
        gameObject = gameobject;
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
        return image;
    }

    @Override
    public String getName () {
        // TODO Auto-generated method stub
        return gameObject.getName() + myID;
    }

    @Override
    public String getType () {
        // TODO Auto-generated method stub
        return gameObject.getLabel();
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
    public void setEditableImage (EditableImage image) {
        // TODO Auto-generated method stub
        editableImage = image;
    }

    @Override
    public EditableImage getEditableImage () {
        return editableImage;
    }

}
