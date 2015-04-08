package gae.backend;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Pair;
import engine.gameobject.Editable;
import engine.gameobject.weapon.Weapon;


public class TempTower implements Editable {
    private static int ourID;
    private int myID;
    private int Size = 10;
    private int Health = 100;
    private Weapon weapon;
    private Pair location;
    private String image = "/images/Bloons_DartMonkeyIcon.jpg";
    private String myName = "TempTower";

    @Override
    public void edit () {
        // TODO Auto-generated method stub

    }

    public TempTower () {
        myID=ourID ++;
    }

    public TempTower (String name) {
        myName = name;
    }

    @Override
    public String getImage () {
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

}
