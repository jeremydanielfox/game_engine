package engine.shop.tag;

import engine.fieldsetting.Settable;
import engine.gameobject.Graphic;
import engine.shop.Purchasable;


@Settable
public class UpgradeTagSimple implements UpgradeTag {
    private String name;
    private Graphic shopGraphic;
    private String description;
    private Purchasable purchasable;

    public UpgradeTagSimple () {
        name = "TestTag";
        description = "Test description";
        shopGraphic = new Graphic(30, 30, "Bloons_DartMonkey.png");
        purchasable = null;
    }

    @Override
    public String getName () {
        return name;
    }

    @Override
    public Graphic getShopGraphic () {
        return shopGraphic;
    }

    @Override
    public String getDescription () {
        return description;
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
    public void setShopGraphic (Graphic shopGraphic) {
        this.shopGraphic = shopGraphic;
    }

    @Override
    @Settable
    public void setDescription (String description) {
        this.description = description;
    }

    @Settable
    public void setPurchasable (Purchasable purchasable) {
        this.purchasable = purchasable;
    }
    
    public UpgradeTag clone () {
        return new UpgradeTagSimple();
    }

}
