package gameobject;

import gameworld.WorldInhabitant;

public interface GameObject extends WorldInhabitant {
	abstract void updatePosition();
	public void onBorn();
	abstract void updatePhysics();

	abstract void updateGraphics();
}
