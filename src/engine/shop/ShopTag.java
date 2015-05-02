package engine.shop;

import engine.gameobject.graphics.Graphic;


public interface ShopTag {
    public String getName ();

    public String getDescription ();

    public Graphic getShopGraphic ();

    public ShopTag clone ();
}
