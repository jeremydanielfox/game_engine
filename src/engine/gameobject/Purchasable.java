package engine.gameobject;

import engine.gameobject.graphics.Graphic;
import engine.shop.RangeDisplay;



public interface Purchasable<E> {
    /**
     *
     * @return the associated object's unique name (given by the authoring environment)
     */
    public String getName ();

    /**
     *
     * @return a description of the object to be displayed to the user
     */
    public String getDescription ();

    /**
     *
     * @return the Graphic containing the front end image to be displayed in the shop (may be
     *         different from an object's image while in-play)
     */
    public Graphic getShopGraphic ();

    /**
     *
     * @return Resell value of this GameObject. This value is incrementally added to
     *         the receiving weapon's total value.
     */
    public double getValue ();
    
    /**
     * Returns the RangeDisplay
     * 
     * @return RangeDisplay
     */
    public RangeDisplay getRangeDisplay ();


    public E clone ();

}
