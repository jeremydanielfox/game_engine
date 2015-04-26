package engine.shop;

import engine.fieldsetting.Settable;
import engine.gameobject.Graphic;
import engine.titles.Title;


public class ShopTagSimple implements ShopTag, Title {
    private String myName;
    private String myDescription;
    private Graphic myShopGraphic;
    private String myTitle="";

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
    

    @Override
    public String getTitle () {
        // TODO Auto-generated method stub
        return myTitle;
    }

    @Settable
    @Override
    public void setTitle (String title) {
        myTitle = title;
    }

}
