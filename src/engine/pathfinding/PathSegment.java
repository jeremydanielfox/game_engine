package engine.pathfinding;

import engine.gameobject.PointSimple;




public interface PathSegment {
    public PointSimple getPoint (double distance);
    public double getLength();
}
