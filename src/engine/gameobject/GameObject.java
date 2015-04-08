package engine.gameobject;

import java.util.List;
import engine.gameobject.weapon.Weapon;


/**
 * 
 * @author Jeremy, Kaighn
 *
 */
public interface GameObject extends Movable, Health, Graphical {
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

    /**
     * Creates an identical game object (with different reference).
     */
    public GameObject clone ();

    /**
     * Returns the Cartesian coordinate of the game object.
     */
    public PointSimple getPoint ();


}
