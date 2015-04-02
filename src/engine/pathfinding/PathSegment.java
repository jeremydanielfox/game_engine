package engine.pathfinding;

import engine.gameobject.Pointlike;




public interface PathSegment {
    public Pointlike getNextPoint (Pointlike current);
}
