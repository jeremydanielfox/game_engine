package engine.shop.tag;

import engine.fieldsetting.Settable;
import engine.gameobject.Graphic;
import engine.shop.PurchasableGameObject;

@Settable
public class GameObjectTagSimple implements GameObjectTag {
    private String name;
    private String label;
    private String description;
    private Graphic shopGraphic;
    private PurchasableGameObject purchasable;

    public GameObjectTagSimple () {
        name = "";
        description = "";
        shopGraphic = new Graphic();
        purchasable = null;
    }

    @Override
    public String getName () {
        return name;
    }
    

    @Override
    public String getLabel () {
        return label;
    }

    @Override
    public String getDescription () {
        return description;
    }

    @Override
    public Graphic getGraphic () {
        return purchasable.getGraphic();
    }

    @Override
    public Graphic getShopGraphic () {
        return shopGraphic;
    }

    @Override
    public double getValue () {
        return purchasable.getValue();
    }

    @Settable
    public void setName (String name) {
        this.name = name;
    }
    
    @Settable
    public void setLabel (String label) {
        this.label = label;
    }

    @Settable
    public void setDescription (String description) {
        this.description = description;
    }

    @Settable
    public void setShopGraphic (Graphic shopGraphic) {
        this.shopGraphic = shopGraphic;
    }

    @Settable
    public void setPurchasable (PurchasableGameObject purchasable) {
        this.purchasable = purchasable;
    }

}
