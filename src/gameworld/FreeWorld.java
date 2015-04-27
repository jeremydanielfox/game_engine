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
	private List<GridCell> mySpawnPoints, myEndPoints;

	// @SuppressWarnings("unchecked")
	public FreeWorld(int numRows, int numCols) {
		super(numRows, numCols);
		// myGrid = (List<GameObject>[][]) new LinkedList<?>[numRows][numCols];
		myGrid = new GameObject[numRows][numCols];
		// 1000 measurements
		myPath = new PathFree(myTrans, myGrid);
	}

	@Override
	public void addObject(GameObject toSpawn, PointSimple pixelCoords)
			throws StructurePlacementException {
		if (!isPlaceable(toSpawn.getGraphic().getNode(), pixelCoords)) {
			throw new StructurePlacementException();
		}
		GridCell c = myTrans.transformWorldToGrid(pixelCoords);
		myGrid[c.getRow()][c.getCol()] = toSpawn;
		toSpawn.setPoint(myTrans.tranformGridToWorld(c));
		try {
			myPath.updatePath();
		} catch (NoPathExistsException e) {
			myGrid[c.getRow()][c.getCol()] = null;
			try {
				myPath.updatePath();
			} catch (NoPathExistsException e1) {
			} // impossible
			throw new StructurePlacementException();

		}
		super.addObject(toSpawn);
	}

	@Override
	public boolean isPlaceable(Node n, PointSimple pixelCoords) {
		GridCell c = myTrans.transformWorldToGrid(pixelCoords);
		if (c.getRow() < 0 || c.getRow() >= myGrid.length || c.getCol() < 0
				|| c.getCol() >= myGrid[0].length) {
			return false;
		}
		if (myEndPoints.contains(c) || mySpawnPoints.contains(c)
				|| !super.isPlaceable(n, pixelCoords)) {
			return false;
		}
		return myGrid[c.getRow()][c.getCol()] == null;
	}

	@Settable
	public void setEndPoints(List<GridCell> endpoints) {
		myEndPoints = endpoints;
		PathFree path = (PathFree) myPath;
		path.setEndPoints(endpoints);
	}

	@Settable
	public void setSpawnPoints(List<GridCell> spawnpoints) {
		mySpawnPoints = spawnpoints;
		PathFree path = (PathFree) myPath;
		path.setSpawnPoints(spawnpoints);
	}

	@Override
	public void updateGameObjects() {
		super.updateGameObjects();
		//TODO refactor during freeze
		for (int r = 0; r < myGrid.length; r++) {
			for (int c = 0; c < myGrid[0].length; c++) {
				GameObject g = myGrid[r][c];
				if (g != null) {
					GridCell cell = myTrans.transformWorldToGrid(g.getPoint());
					if (myGrid[cell.getRow()][cell.getCol()] != g) {
						if(myGrid[cell.getRow()][cell.getCol()] != null){
							PointSimple position = myTrans.findIntraCellPosition(g.getPoint());
							if(cell.getRow() == r){
								g.setPoint(myTrans.transformGridCellsToWorldWall(new GridCell(r,c), cell, position.getY()));
							}
							if(cell.getCol() == c){
								g.setPoint(myTrans.transformGridCellsToWorldWall(new GridCell(r,c), cell, position.getX()));
							}
						}
						else{
							myGrid[cell.getRow()][cell.getCol()] = g;
							myGrid[r][c] = null;
							try {
								myPath.updatePath();
							} catch (NoPathExistsException e) {
								PointSimple position = myTrans.findIntraCellPosition(g.getPoint());
								if(cell.getRow() == r){
									g.setPoint(myTrans.transformGridCellsToWorldWall(new GridCell(r,c), cell, position.getY()));
								}
								else if(cell.getCol() == c){
									g.setPoint(myTrans.transformGridCellsToWorldWall(new GridCell(r,c), cell, position.getX()));
								}
							}
						}
					}
				}
			}
		}
	}

}