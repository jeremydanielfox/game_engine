package gameworld;

import javafx.scene.Node;
import javafx.scene.shape.Circle;
import engine.fieldsetting.Settable;
import engine.gameobject.PointSimple;
import engine.pathfinding.EndOfPathException;

@Settable
public class FixedWorld extends AbstractWorld {
	public FixedWorld(int numRows, int numCols) {
		super(numRows, numCols);
	}
}
