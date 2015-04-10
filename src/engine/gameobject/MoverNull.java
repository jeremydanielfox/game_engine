package engine.gameobject;

import engine.pathfinding.EndOfPathException;

public class MoverNull implements Mover {
	@Override
	public PointSimple move(PointSimple current) throws EndOfPathException {
		return current;
	}

	@Override
	public void setSpeed(double speed) {}

	@Override
	public Mover clone() {
		return null;
		// TODO Auto-generated method stub
	}

}
