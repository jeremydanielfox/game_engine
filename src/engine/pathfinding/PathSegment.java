package engine.pathfinding;

import gameobject.Pointlike;


public interface PathSegment {
    public Pointlike getNextPoint (Pointlike current);
}
