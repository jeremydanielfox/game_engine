package engine.gameobject;
import javafx.geometry.Point2D;

public class PointSimple implements Pointlike {
	private Point2D myPoint;
	
	public PointSimple(double x, double y){
		myPoint = new Point2D(x,y);
	}
	public PointSimple(Point2D point){
		myPoint = new Point2D(point.getX(), point.getY());
	}
	
	@Override
	public double getX() {
		return myPoint.getX();
	}

	@Override
	public double getY() {
		return myPoint.getY();
	}
	
	@Override
	public boolean withinRange(Pointlike point, double range) {
		return myPoint.distance(new Point2D(point.getX(), point.getY())) <= range;
	}
	
}
