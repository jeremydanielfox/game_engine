package engine.gameobject;

import java.util.List;
import engine.gameobject.weapon.Weapon;
import gameworld.ObjectCollection;


/**
 * 
 * @author Jeremy, Kaighn
 *
 */

public interface GameObject extends Movable, Health, Graphical {
    // public void updateGraphics ();//cannot implement yet
   
    /**
     * Updates the object accordingly within the objectcollection (usually gameworld) given
     * @param world
     */
    public void update(ObjectCollection world);
    
    /**
     * Executes the defined ondeath behavior when the object is removed from the world.
     * TODO: THINK ABOUT THIS EXTREMELY CAREFULLY. It's possible it shouldn't be the gameworld, but
     * the gameobject itself that executes the ondeath behavior.
     */
    public void onDeath(ObjectCollection world);
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
    
    public void setPoint (PointSimple point);
    
    /**
     * Sets the GameObject's Weapon
     */
    public void setMover (Mover mover);

    /**
     * Returns object's mover
     */
    public BasicMover getMover ();
}
