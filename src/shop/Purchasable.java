package shop;

import gameobject.GameObject;


public interface Purchasable {
    public GameObject clone ();

    public String getName ();

    public double getPrice ();

    public String getImage ();
}
