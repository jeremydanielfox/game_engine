package engine.gameobject;

import engine.gameobject.labels.Label;
import engine.gameobject.units.Buffable;
import engine.gameobject.units.Colliding;
import engine.gameobject.units.Firing;
import engine.prototype.Prototype;
import engine.shop.PurchasableGameObject;
import engine.shop.tag.GameObjectTag;
import gameworld.ObjectCollection;


/**
 *
 * @author Jeremy, Kaighn
 *
 */

public interface GameObject extends Firing, Colliding, Buffable, Movable, Health,
        PurchasableGameObject, Prototype<GameObject> {
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

    public Mover getMover ();

    public Label getLabel ();

    public void setGraphic (Graphic graphic);

    public void setLabel (Label label);

    /**
     * Tags contain important GameObject info (e.g. name, description, image)
     *
     * @return
     */
    @Override
    public GameObjectTag getTag ();

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

    public void setTag (GameObjectTag myTag);

}
