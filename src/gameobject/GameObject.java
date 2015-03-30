package gameobject;

import java.util.List;

public interface GameObject extends Actor, Movable, Health {
	// public void updateGraphics ();//cannot implement yet
	public void addWeapon(Weapon weapon);
	
	public List<Weapon> getWeapons();
	
	public String getLabel();

	public GameObject clone();
	
	public Pointlike getPoint();

}