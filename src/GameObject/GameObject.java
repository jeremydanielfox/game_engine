package GameObject;

import gameworld.WorldInhabitant;

public interface GameObject extends WorldInhabitant {
	abstract void updateInput();

	abstract void updatePhysics();

	abstract void updateGraphics();
}
