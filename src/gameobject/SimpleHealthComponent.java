package gameobject;

public class SimpleHealthComponent implements HealthComponent {
	private double myHealth;
	private double lowerHealthLimit;

	@Override
	public boolean isDead() {
		return myHealth <= lowerHealthLimit;
	}

	@Override
	public void changeHealth(double amount) {
		myHealth += amount;
	}

}
