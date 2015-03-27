package gameworld;

import java.awt.geom.Point2D;
import java.util.Collection;

import GameObject.GameObject;

public interface GameObjectManager {
public Collection<GameObject> getSprites(Point2D location);
}
