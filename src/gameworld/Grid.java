package gameworld;

import GameObject.GameObject;

public interface Grid {
	public void addObject(double row, double col, GameObject o);

	public void removeObject(double row, double col, GameObject o);

	boolean canPlace(double row, double col, GameObject o);
}
