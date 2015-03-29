package gameobject;

import java.util.List;

public class GameObjectSimple implements GameObject {
	private Health myHealth;
	private Mover myMover;
	private Actor myActor;
	private List<Weapon> myWeapons;
	
	
	@Override
	public void act(GameObject a, GameObject b) {
		myActor.act(a, b);
	}

	@Override
	public void move() {
		myMover.move();
	}

	@Override
	public void addWeapon(Weapon weapon) {
		myWeapons.add(weapon);
	}

	@Override
	public boolean isDead() {
		return myHealth.isDead();
	}

	@Override
	public void changeHealth(double amount) {
		myHealth.changeHealth(amount);
	}

}
