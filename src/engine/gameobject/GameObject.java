package engine.gameobject;

import engine.gameobject.behaviors.EndBehaviorful;
import engine.gameobject.labels.Type;
import engine.gameobject.units.Buffable;
import engine.gameobject.units.Colliding;
import engine.gameobject.units.Firing;
import engine.shop.RangeDisplay;
import gameworld.ObjectCollection;


/**
 * Interface that defines the objects that will be used in-game. GameObjects represent all in game
 * objects -- towers, enemies,
 * projectiles, walls, platforms, powerups, etc. are all GameObjects.
 * 
 * What can a GameObject do?
 * GameObjects can collide with other objects, they can shoot at other objects, they can give buffs
 * to and receive buffs from other objects
 * they can be purchased, they can move, they can be seen on screen, and they can die. They don't
 * have to do any of these things. But, if you want to make something that can do some or all of
 * these things,
 * you can probably make it.
 * 
 * @author Jeremy
 * @author Kaighn
 *
 */

public interface GameObject extends Firing, Colliding, Buffable, Movable, Health,
        Purchasable<GameObject>, EndBehaviorful {

    /**
     * Updates the object accordingly within the objectcollection (usually gameworld) given the
     * world.
     *
     * @param world
     */
    public void update (ObjectCollection world);

    /**
     * Performs the ondeath behavior of the object upon the world
     * 
     * @param world
     */
    public void onDeath (ObjectCollection world);

    /**
     * Returns the Mover associated with the GameObject
     * 
     * @return the Mover of the GameObject
     */
    public Mover getMover ();

    /**
     * Returns the Type for the GameObject
     * 
     * @return
     */
    public Type getLabel ();

    // public void setGraphic (Graphic graphic);

    public void setLabel (Type label);

    /**
     * Returns the Cartesian coordinate of the game object.
     */
    public PointSimple getPoint ();

    /**
     * Sets the object's location to point
     *
     * @param point
     */
    public void setPoint (PointSimple point);

    /**
     * Sets the GameObject's Mover
     */
    public void setMover (Mover mover);

    public void setHealth (Health clone);

    public RangeDisplay getRangeDisplay ();

    public Graphic getGraphic ();

    public Health getHealth ();

    public void setGraphic (Graphic myGraphic);

}
