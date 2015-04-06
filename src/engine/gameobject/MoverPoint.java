package engine.gameobject;

import engine.pathfinding.EndOfPathException;

/**
 * Mover that moves to a point specified in the constructor.
 * @author Kaighn
 *
 */
public class MoverPoint implements Mover {
	private double mySpeed;
	private PointSimple myPoint;
	
	public MoverPoint(PointSimple point, double speed){
		mySpeed = speed;
		myPoint = point;
	}
	
	@Override
	public PointSimple move(PointSimple current) throws EndOfPathException {
		PointSimple p = PointSimple.pointOnLine(current, myPoint, mySpeed);
		if(PointSimple.pointInBetween(current, myPoint, p)){
			throw new EndOfPathException();
		}
		return p;
	}

	@Override
	public void setSpeed(double speed) {
		mySpeed = speed;
	}
}
