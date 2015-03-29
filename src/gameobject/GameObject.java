package gameobject;

import gameworld.WorldInhabitant;


public interface GameObject extends WorldInhabitant {

    public void updatePosition ();

    public void updatePhysics ();

    public void updateGraphics ();

    public void onBorn ();
    
    public GameObject clone();
}