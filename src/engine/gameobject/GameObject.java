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
     * Sets the GameObject's Weapon
     */
    public void setWeapon (Weapon weapon);

    /**
     * Returns the GameObject's Weapon
     */
    public Weapon getWeapon();

    /**
     * Labels allow the GameEngine to differentiate
     * 
     * @return: A string
     */
    public String getLabel ();

    public GameObject clone ();

    public PointSimple getPoint ();

    public Graphic getGraphic ();

}
