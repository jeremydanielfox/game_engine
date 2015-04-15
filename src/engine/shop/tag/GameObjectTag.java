package engine.shop.tag;

import engine.gameobject.Graphic;


public interface GameObjectTag extends PriceTag {
    /**
     * Labels allow the GameEngine to differentiate between GameObject types (e.g. towers vs.
     * enemies).
     * 
     * @return: A string
     */
    public String getLabel ();

    /**
     * Graphics are used for front end display of GameObjects
     * 
     * @return
     */
    public Graphic getGraphic ();

}
