package gameobject;

import gameworld.WorldInhabitant;

public interface GameObject extends WorldInhabitant {
	abstract void updatePosition();

	abstract void updatePhysics();

	abstract void updateGraphics();
}
