package gameobject;


public interface Weapon {
	public double getRange();
	public double getFiringRate();
	public GameObject getProjectile(); //<-- rework weapons/projectiles?
}
