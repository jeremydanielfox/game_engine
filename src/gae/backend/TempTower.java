package gae.backend;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import engine.gameobject.weapon.Weapon;
import gae.gridView.Pair;
import gae.listView.MovableImage;
import gae.openingView.UIObject;


public class TempTower implements Editable, UIObject {
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
    private MovableImage editableImage;

    @Override
    public void edit () {
        // TODO Auto-generated method stub

    }
    
    //added by brandon
    public void setImage (String i) {
        image = i;
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
        return "TempTower ";
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
    public void setMovableImage (MovableImage image) {
        // TODO Auto-generated method stub
        editableImage = image;
    }

    @Override
    public MovableImage getMovableImage () {
        return editableImage;
    }

    @Override
    public Node getObject () {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public Object clone(){  
        try{  
            return super.clone();  
        }catch(Exception e){ 
            return null; 
        }
    }

    @Override
    public int getID () {
        return myID;
    }
}
