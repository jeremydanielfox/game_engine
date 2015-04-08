package engine.shop;

import engine.gameobject.Graphic;

// TODO: might need to make a distinction between GameObject purchasables and UpgradeBundle
// purchasables b/c of the getValue() method

public interface Purchasable {
    // public Object clone ();

    public String getName ();

    /**
     * 
     * @return Resell value of this purchasable. This value is incrementally added to
     *         the receiving weapon's total value.
     */
    public double getValue ();
    
    /**
     * 
     * @return Cost of purchase of this purchasable.
     */
    public double getPrice ();

    public Graphic getGraphic ();
    
    public String getDescription ();
}
