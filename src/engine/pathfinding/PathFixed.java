package engine.pathfinding;

import java.util.LinkedList;
import java.util.List;

import engine.gameobject.PointSimple;

/**
 * A path finder for following a 
 * @author Kaighn
 *
 */
public class PathFixed implements Path {
	private List<PathSegment> myPathSegments;
	
	public PathFixed(){
		myPathSegments = new LinkedList<>();
	}
	
	@Override
	public void addPathSegment(PathSegment ps) {
		myPathSegments.add(ps);
	}

	@Override
	public PointSimple getNextLocation(PointSimple current, double distance) throws EndOfPathException{
		double count = 0;
		for(PathSegment seg : myPathSegments){
			count += seg.getLength();
			if(count >= distance){
				PathSegment pathSeg = seg;
				return pathSeg.getPoint(distance - count - seg.getLength());
			}
		}
		throw new EndOfPathException();
	}

}
