package gameworld;

import java.util.List;

import View.ViewConcrete2;
import voogasalad.util.pathsearch.graph.GridCell;
import voogasalad.util.pathsearch.pathalgorithms.NoPathExistsException;
import javafx.scene.Node;
import engine.fieldsetting.Settable;
import engine.gameobject.GameObject;
import engine.gameobject.GameObjectSimple;
import engine.gameobject.PointSimple;
import engine.pathfinding.PathFree;


/**
 * GameWorld where structures belong to a grid.
 * 
 * @author Kaighn
 *
 */
public class FreeWorld extends AbstractWorld {
	// private List<GameObject>[][] myGrid;
	private GameObject[][] myGrid;
	private CoordinateTransformer myTransform;
	private List<GridCell> mySpawnPoints, myEndPoints;

	// @SuppressWarnings("unchecked")
	public FreeWorld (int numRows, int numCols) {
		// myGrid = (List<GameObject>[][]) new LinkedList<?>[numRows][numCols];
		myGrid = new GameObject[numRows][numCols];
		myTransform = new CoordinateTransformer(numRows, numCols, ViewConcrete2.getWorldWidth(), ViewConcrete2.getWorldHeight()); // TODO fix window 1000
		// 1000 measurements
		myPath = new PathFree(myTransform, myGrid);
	}

	@Override
	public void addObject (GameObject toSpawn, PointSimple pixelCoords)
			throws StructurePlacementException {
		if (!isPlaceable(toSpawn.getGraphic().getNode(), pixelCoords)) {
			throw new StructurePlacementException();
		}
		GridCell c = myTransform.transformWorldToGrid(pixelCoords);
		myGrid[c.getRow()][c.getCol()] = toSpawn;
		toSpawn.setPoint(myTransform.tranformGridToWorld(c));
		try {
			myPath.updatePath();
		} catch (NoPathExistsException e) {
			myGrid[c.getRow()][c.getCol()] = null;
			try {
				myPath.updatePath();
			} catch (NoPathExistsException e1) { System.out.println("what");}
			throw new StructurePlacementException();
		}
		super.addObject(toSpawn);
	}

	@Override
	public boolean isPlaceable (Node n, PointSimple pixelCoords) {
		GridCell c = myTransform.transformWorldToGrid(pixelCoords);
		if (c.getRow() < 0 || c.getRow() >= myGrid.length || c.getCol() < 0 ||
				c.getCol() >= myGrid[0].length) {
			return false;
		}
		for(GridCell reservedCell : myEndPoints){
			if(reservedCell.equals(c)){
				return false;
			}
		}
		for(GridCell reservedCell : mySpawnPoints){
			if(reservedCell.equals(c)){
				return false;
			}
		}
		return myGrid[c.getRow()][c.getCol()] == null;
	}

	@Settable
	public void setEndPoints(List<GridCell> endpoints){
		myEndPoints = endpoints;
		PathFree path = (PathFree) myPath;
		path.setEndPoints(endpoints);
	}

	@Settable
	public void setSpawnPoints(List<GridCell> spawnpoints){
		mySpawnPoints = spawnpoints;
		PathFree path = (PathFree) myPath;
		path.setSpawnPoints(spawnpoints);
	}

}