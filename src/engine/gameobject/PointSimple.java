package engine.gameobject;
import javafx.geometry.Point2D;

public class PointSimple  {
	private Point2D myPoint;
	
	public PointSimple(double x, double y){
		myPoint = new Point2D(x,y);
	}
	public PointSimple(Point2D point){
		myPoint = new Point2D(point.getX(), point.getY());
	}
	public PointSimple(PointSimple point){
		myPoint = new Point2D(point.getX(), point.getY());
	}
	
	public double getX() {
		return myPoint.getX();
	}

	public double getY() {
		return myPoint.getY();
	}
	
	public boolean withinRange(PointSimple point, double range) {
		return myPoint.distance(new Point2D(point.getX(), point.getY())) <= range;
	}
	
	public static PointSimple pointOnLine(PointSimple start, PointSimple end, double distance){
		double rise = end.getY() - start.getY();
		double run = end.getX() - start.getX();
		double norm = norm(rise,run);
		double mult = distance/norm;
		return new PointSimple(run*mult,rise*mult);
	}

	public static boolean pointInBetween(PointSimple start, PointSimple end, PointSimple point){
		return inRange(start.getX(), end.getX(), point.getX()) && inRange(start.getY(), end.getY(), point.getY());
	}
	public static double norm(PointSimple a, PointSimple b){
		return norm(Math.abs(a.getX()-b.getX()), Math.abs(a.getY()-b.getY()));
	}
	
	private static double norm(double a, double b){
		return Math.sqrt(Math.pow(a, 2)+Math.pow(b, 2));
	}
	private static boolean inRange(double start, double end, double a){
		return a>=start && a<=end;
	}
	
}
