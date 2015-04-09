package gae.backend;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import engine.gameobject.Editable;
import engine.gameobject.weapon.Weapon;
import gae.gridView.Pair;
import gae.listView.EditableImage;


public class TempTower implements Editable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 
     */
    private int myID=0;
    private int Size = 10;
    private int Health = 100;
    private Weapon weapon;
    private Pair location;
    private String image = "/images/ArcaneTower.png";
    private String myName = "TempTower";
    private EditableImage editableImage;

    @Override
    public void edit () {
        // TODO Auto-generated method stub

    }

    public TempTower () {
    }

    public TempTower (String name) {
        myName = name;
    }

    @Override
    public String getImagePath () {
        return image;
    }

    @Override
    public String getName () {
        // TODO Auto-generated method stub
        return "TempTower "+myID;
    }

    @Override
    public String getType () {
        // TODO Auto-generated method stub
        return "Tower";
    }
    
    @Override
    public void setID(int id){
        myID=id;
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
