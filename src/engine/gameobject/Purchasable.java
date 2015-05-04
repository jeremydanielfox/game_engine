// This entire file is part of my masterpiece.
// JEREMY FOX
package engine.gameobject;

import engine.shop.ShopComponent;


public interface Purchasable<E> {

    public ShopComponent getShopComponent ();

    public E clone ();

}
