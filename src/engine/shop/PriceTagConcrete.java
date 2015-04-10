package engine.shop;

import engine.fieldsetting.Settable;
import engine.gameobject.Graphic;

@Settable
public class PriceTagConcrete implements PriceTag {
    private String name;
    private String description;
    private Graphic shopGraphic;
    private Purchasable purchasable;
    private static double markup;

    public PriceTagConcrete () {
        name = "";
        description = "";
        shopGraphic = new Graphic();
        purchasable = null;
        markup = 1;
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
    
    @Override
    public double getPrice () {
        return getValue()*markup;
    }

    @Settable
    public void setName (String name) {
        this.name = name;
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
    public void setPurchasable (Purchasable purchasable) {
        this.purchasable = purchasable;
    }
    
    @Settable
    public void setMarkup (double markup) {
        this.markup = markup;
    }
}
