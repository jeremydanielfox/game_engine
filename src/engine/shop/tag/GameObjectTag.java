package engine.shop.tag;

import engine.gameobject.Graphic;


public interface GameObjectTag extends PriceTag {

    /**
     * Graphics are used for front end display of GameObjects
     *
     * @return
     */
    public Graphic getGraphic ();

}
