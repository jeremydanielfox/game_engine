package engine.gameobject;

import engine.gameobject.weapon.Weapon;
import engine.prototype.Prototype;
import engine.shop.PurchasableGameObject;
import engine.shop.tag.GameObjectTag;
import gameworld.ObjectCollection;


/**
 * 
 * @author Jeremy, Kaighn
 *
 */

public interface GameObject extends Movable, Health, PurchasableGameObject, Prototype<GameObject> {
    // public void updateGraphics ();//cannot implement yet

    /**
     * Updates the object accordingly within the objectcollection (usually gameworld) given
     * 
     * @param world
     */
    public void update (ObjectCollection world);

    /**
     * Executes the defined ondeath behavior when the object is removed from the world.
     * TODO: THINK ABOUT THIS EXTREMELY CAREFULLY. It's possible it shouldn't be the gameworld, but
     * the gameobject itself that executes the ondeath behavior.
     */
    public void onDeath (ObjectCollection world);

    public void setWeapon (Weapon weapon);

    /**
     * Returns the GameObject's Weapon
     */
    public Weapon getWeapon ();
    
    public Mover getMover ();

    /**
     * Labels allow the GameEngine to differentiate between GameObject types (e.g. towers vs.
     * enemies).
     * 
     * @return: A string
     */
    public String getLabel ();

    /**
     * Tags contain important GameObject info (e.g. name, description, image)
     * 
     * @return
     */
    public GameObjectTag getTag ();

    /**
     * Returns the Cartesian coordinate of the game object.
     */
    public PointSimple getPoint ();

    public void setPoint (PointSimple point);

    /**
     * Sets the GameObject's Mover
     */
    public void setMover (Mover mover);

}
