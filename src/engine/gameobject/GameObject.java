// This entire file is part of my masterpiece.
// JEREMY FOX
package engine.gameobject;

import engine.gameobject.behaviors.EndBehaviorful;
import engine.gameobject.graphics.Graphical;
import engine.gameobject.healths.Health;
import engine.gameobject.healths.HealthComponent;
import engine.gameobject.movers.Movable;
import engine.gameobject.types.TypeComponent;
import engine.gameobject.units.Buffable;
import engine.gameobject.units.Colliding;
import engine.gameobject.units.Firing;
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

public interface GameObject extends Firing, Colliding, Buffable, Movable,
        Purchasable<GameObject>, EndBehaviorful, Graphical {

    /**
     * Updates the object accordingly within the objectcollection (usually gameworld) given the
     * world.
     *
     * @param world
     */
    public void update (ObjectCollection world);

    /**
     * Returns the TypeComponent for the GameObject
     * 
     * @return
     */
    public TypeComponent getTypeComponent ();

    /**
     * Returns the Cartesian coordinate of the game object as a PointSimple.
     */
    public PointSimple getPoint ();

    /**
     * Sets the object's location to point
     *
     * @param point : Point in Cartesian coordinates that the GameObject will be set to
     */
    public void setPoint (PointSimple point);

    /**
     * Returns the Health Component for the GameObject
     * 
     * @return
     */
    public HealthComponent getHealthComponent ();
}
