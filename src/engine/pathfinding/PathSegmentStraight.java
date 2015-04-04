package engine.pathfinding;

import javafx.geometry.Point2D;
import engine.gameobject.PointSimple;
/**
 * 
 * @author Jeremy
 *
 */
public class PathSegmentStraight implements PathSegment {
	private PointSimple start, end;
	
	public PathSegmentStraight(Point2D start, Point2D end){
		this.start = new PointSimple(start);
		this.end = new PointSimple(end);
	}
	
    @Override
    public PointSimple getPoint (double distance){
        return PointSimple.pointOnLine(start, end, distance);
    }
    
    private boolean onSegment(PointSimple point){
    	return PointSimple.pointInBetween(start, end, point);
    }

	@Override
	public double getLength() {
		return PointSimple.norm(start, end);
	}

}
