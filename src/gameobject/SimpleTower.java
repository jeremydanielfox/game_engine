package gameobject;

import gameworld.GraphicsComponent;
import gameworld.MovementComponent;
import gameworld.PhysicsComponent;

import java.util.Collection;

import javafx.geometry.Point2D;

public class SimpleTower implements Tower {
	private HealthComponent myHealth;
	private Collection<Weapon> myWeapons;
	private MovementComponent myMover;
	private PhysicsComponent myPhysics;
	private GraphicsComponent myGraphics;

	@Override
	public void changeHealth(double amount) {
		myHealth.changeHealth(amount);
	}

	@Override
	public boolean isDead() {
		return myHealth.isDead();
	}

	@Override
	public void updatePosition() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updatePhysics() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateGraphics() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onBorn() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Point2D getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addWeapon(Weapon weapon) {
		myWeapons.add(weapon);

	}

	@Override
	public Collection<Weapon> getWeapons() {
		return myWeapons;
	}

	public GameObject clone(){
	    return null;
	}
	
}
