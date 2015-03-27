package gameworld;

import gameobject.GameObject;
import java.awt.geom.Point2D;
import java.util.Collection;

public interface GameObjectManager {
public Collection<GameObject> getSprites(Point2D location);
}
