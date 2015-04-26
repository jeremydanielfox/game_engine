package gameworld;

import javafx.scene.Node;
import javafx.scene.shape.Circle;
import engine.fieldsetting.Settable;
import engine.gameobject.PointSimple;
import engine.pathfinding.EndOfPathException;

public class FixedWorld extends AbstractWorld {
	public FixedWorld(int numRows, int numCols) {
		super(numRows, numCols);
	}

	private double myPathWidth = 17;

	@Override
	public boolean isPlaceable(Node n, PointSimple pixelCoords) {
		if (!super.isPlaceable(n, pixelCoords)) {
			return false;
		}
		n.setLayoutX(pixelCoords.getX());
		n.setLayoutY(pixelCoords.getY());
		int i = 0;
		Circle c = new Circle(myPathWidth);
		while (true) {
			try {
				PointSimple pathPoint = myPath.getNextLocation(i, 10,
						new PointSimple(c.getCenterX(), c.getCenterY()));
				c.setCenterX(pathPoint.getX() + 28);
				c.setCenterY(pathPoint.getY() + 28);
				if (c.intersects(n.getBoundsInParent())) {
					return false;
				}
			} catch (EndOfPathException e) {
				break;
			}
			i += 20;
		}
		return true;
	}

	@Settable
	public void setPathWidth(double width) {
		myPathWidth = width;
	}
}
