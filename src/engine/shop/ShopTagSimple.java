package engine.shop;

import engine.fieldsetting.Settable;
import engine.gameobject.Graphic;


public class ShopTagSimple implements ShopTag {
    private String myName;
    private String myDescription;
    private Graphic myShopGraphic;

    public ShopTagSimple () {
        myName = "";
        myDescription = "";
        myShopGraphic = new Graphic();
    }
    
    @Override
    public String getName () {
        return myName;
    }

    @Override
    public String getDescription () {
        return myDescription;
    }

    @Override
    public Graphic getShopGraphic () {
        return myShopGraphic;
    }

    @Settable
    public void setName (String name) {
        myName = name;
    }

    @Settable
    public void setDescription (String description) {
        myDescription = description;
    }

    @Settable
    public void setShopGraphic (Graphic shopGraphic) {
        myShopGraphic = shopGraphic;
    }
    
    public ShopTag clone () {
        ShopTagSimple clone = new ShopTagSimple();
        clone.setName(myName);
        clone.setDescription(myDescription);
        clone.setShopGraphic(myShopGraphic.clone());
        return clone;
    }
}
