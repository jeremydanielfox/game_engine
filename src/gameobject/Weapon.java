package gameobject;

import engine.gameobject.GameObject;

public interface Weapon {

    public double getRange ();

    public double getFiringRate ();

    public void addRange (double value);

    public GameObject getProjectile (); // <-- rework weapons/projectiles?
}
