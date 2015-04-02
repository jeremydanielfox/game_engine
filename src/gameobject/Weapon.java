package gameobject;

public interface Weapon {

    public double getRange ();

    public double getFiringRate ();

    public void addRange (double value);

    public GameObject getProjectile (); // <-- rework weapons/projectiles?
}
