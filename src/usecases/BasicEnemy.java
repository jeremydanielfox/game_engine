package usecases;

import gameobject.Enemy;

public class BasicEnemy implements Enemy {

	private int id;
	public BasicEnemy(int id){
		this.id = id;
	}
	@Override
	public void changeHealth(double amount) {
		
	}

	public void onBorn() {
		System.out.println("Enemy id # " + id + " spawned!!");
	}
	@Override
	public boolean isDead() {
		return false;
	}

	@Override
	public void updatePhysics() {
		
	}

	@Override
	public void updateGraphics() {
		
	}

	@Override
	public void getImage() {
		
	}
	@Override
	public void updatePosition() {
		// TODO Auto-generated method stub
		
	}

}
