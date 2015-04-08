package engine.shop;

// TODO: might need to make a distinction between GameObject purchasables and UpgradeBundle
// purchasables b/c of the getValue() method

public interface Purchasable {
    // public Object clone ();

    public String getName ();

    /**
     * 
     * @return Price at which the Shop values this Purchasable. This value is incrementally added to
     *         the receiving weapon's total value.
     */
    public double getValue ();

    public String getGraphic ();
}
