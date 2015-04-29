package gameworld;

import voogasalad.util.pathsearch.graph.GridCell;
import javafx.scene.Node;
import javafx.scene.shape.Circle;
import engine.fieldsetting.Settable;
import engine.gameobject.GameObject;
import engine.gameobject.PointSimple;
import engine.pathfinding.EndOfPathException;

@Settable
public class FixedWorld extends AbstractWorld {
	public FixedWorld(int numRows, int numCols) {
		super(numRows, numCols);
	}

	@Override
	public boolean isPlaceable (Node n, PointSimple pixelCoords) {
//		n.setLayoutX(pixelCoords.getX());
//		n.setLayoutY(pixelCoords.getY());
//		for(GameObject o : getObjects()){
//			if(o.getGraphic().getNode().intersects(n.getBoundsInParent())){
//				
//			}
//		}
		return super.isPlaceable(n, pixelCoords);
	}
}
