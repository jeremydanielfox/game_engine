package gae.backend;

import javafx.scene.image.ImageView;
import engine.gameobject.Editable;
import engine.gameobject.weapon.Weapon;


public class TempTower implements Editable {
    private int Size = 10;
    private int Health = 100;
    private Weapon weapon;

    @Override
    public void edit () {
        // TODO Auto-generated method stub

    }

    @Override
    public String getName () {
        // TODO Auto-generated method stub
        return "TempTower";
    }

    @Override
    public String getType () {
        // TODO Auto-generated method stub
        return "Tower";
    }

    @Override
    public ImageView getImage () {
        // TODO Auto-generated method stub
        return null;
    }

}
