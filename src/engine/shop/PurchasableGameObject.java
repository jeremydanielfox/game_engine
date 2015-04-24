package engine.shop;

import engine.gameobject.Graphic;


/**
 * Created to pass into a PriceTag so it has access to a GameObject's value and Graphic without
 * having access to anything else.
 *
 * @author Tom Puglisi
 *
 */
public interface PurchasableGameObject extends Purchasable {

    public Graphic getGraphic ();
}
