package gameobject;

public interface HealthComponent {
	public boolean isDead();
	public void changeHealth(double amount);
}
