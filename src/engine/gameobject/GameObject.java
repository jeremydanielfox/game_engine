package engine.gameobject;

import java.util.List;
import engine.gameobject.weapon.Weapon;

/**
 * 
 * @author Jeremy
 *
 */
public interface GameObject extends Movable, Health {
    // public void updateGraphics ();//cannot implement yet
    /**
     * Adds a weapon to the GameObject's Collection of Weapons
     */
    public void addWeapon (Weapon weapon);
/**
 * Returns the GameObject's Collection of Weapons
 */
    public List<Weapon> getWeapons ();
/**
 * Labels allow the GameEngine to differentiate
 * @return: A string
 */
    public String getLabel ();

    public GameObject clone ();

    public PointSimple getPoint ();
    
    public Graphic getGraphic();

}