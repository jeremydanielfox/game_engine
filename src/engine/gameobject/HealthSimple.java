package engine.gameobject;

import engine.fieldsetting.Settable;

public class HealthSimple implements Health {
	private double myHealth;

	@Override
	public boolean isDead() {
		return myHealth <= 0;
	}
	@Settable
	@Override
	public void changeHealth(double amount) {
		myHealth += amount;
	}

}
