package engine.shop;

import engine.gameobject.Graphic;


public interface PriceTag extends Tag {
    public Graphic getGraphic ();
    
    public Graphic getShopGraphic ();

    public String getDescription ();

    /**
     * 
     * @return Resell value of this purchasable. This value is incrementally added to
     *         the receiving weapon's total value.
     */
    public double getValue ();

}
