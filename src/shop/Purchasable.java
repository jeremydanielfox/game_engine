package shop;

import gameobject.GameObject;


public interface Purchasable {
    public Object clone ();

    public String getName ();

    public double getPrice ();

    public String getImage ();
}
