package engine.gameobject;

import engine.fieldsetting.Settable;

/**
 * Most traditional implementation of health. Can take damage and die appropriately.
 * @author Kaighn
 *
 */
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
