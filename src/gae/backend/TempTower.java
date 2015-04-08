package gae.backend;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Pair;
import engine.gameobject.Editable;
import engine.gameobject.weapon.Weapon;


public class TempTower implements Editable {
    private int Size = 10;
    private int Health = 100;
    private Weapon weapon;
    private Pair location;
    private String image="/images/Bloons_DartMonkeyIcon.jpg";
    private String myName="no name yet";

    @Override
    public void edit () {
        // TODO Auto-generated method stub

    }
    
    public TempTower(){
        
    }
    
    public TempTower(String name){
        myName=name;
    }
   public String getImage(){
       return image;
   }
   
   public String getName(){
       return myName;
   }


}
