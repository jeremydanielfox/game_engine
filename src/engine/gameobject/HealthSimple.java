package engine.gameobject;

public class HealthSimple implements Health {
	private double myHealth;

	@Override
	public boolean isDead() {
		return myHealth <= 0;
	}

	@Override
	public void changeHealth(double amount) {
		myHealth += amount;
	}

}
