package engine.shop.tag;

import engine.fieldsetting.Settable;
import engine.gameobject.Graphic;
import engine.shop.PurchasableGameObject;


@Settable
public class GameObjectTagSimple implements GameObjectTag {
    private String name;
    private String description;
    private Graphic shopGraphic;
    private PurchasableGameObject purchasable;

    public GameObjectTagSimple () {
        name = "";
        description = "";
        shopGraphic = new Graphic();
        purchasable = null;
    }
    
    public GameObjectTagSimple (String name, String desc, Graphic shopGraphic, PurchasableGameObject purchasable){
        this.name = name;
        description = desc;
        this.shopGraphic = shopGraphic;
        this.purchasable = purchasable;
    }

    @Override
    public String getName () {
        return name;
    }

    @Override
    public String getDescription () {
        return description;
    }

    @Override
    public Graphic getGraphic () {
        return (purchasable != null) ? purchasable.getGraphic()
                                     : new Graphic(40, 40, "Bloons_TackShooter.png");
    }

    @Override
    public Graphic getShopGraphic () {
        return shopGraphic;
    }

    @Override
    public double getValue () {
        return (purchasable != null) ? purchasable.getValue() : 10;
    }

    @Override
    @Settable
    public void setName (String name) {
        this.name = name;
    }

    @Override
    @Settable
    public void setDescription (String description) {
        this.description = description;
    }

    @Override
    @Settable
    public void setShopGraphic (Graphic shopGraphic) {
        this.shopGraphic = shopGraphic;
    }

    @Settable
    public void setPurchasable (PurchasableGameObject purchasable) {
        this.purchasable = purchasable;
    }

}
