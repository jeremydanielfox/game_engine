package engine.shop.tag;

import engine.gameobject.Graphic;


public interface PriceTag {
    /**
     * 
     * @return the associated object's unique name (given by the authoring environment)
     */
    public String getName ();

    /**
     * 
     * @return the Graphic containing the front end image to be displayed in the shop (may be
     *         different from an object's image while in-play)
     */
    public Graphic getShopGraphic ();

    /**
     * 
     * @return a description of the object to be displayed to the user
     */
    public String getDescription ();

    /**
     * 
     * @return Resell value of this purchasable. This value is incrementally added to
     *         the receiving weapon's total value.
     */
    public double getValue ();

    public void setName (String name);

    public void setShopGraphic (Graphic graphic);

    public void setDescription (String description);

}
