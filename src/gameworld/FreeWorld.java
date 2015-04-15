package gameworld;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import engine.gameobject.GameObject;
import engine.gameobject.GameObjectSimpleTest;
import engine.gameobject.PointSimple;
import engine.gameobject.test.TestTower;
import engine.gameobject.units.Buffable;
import engine.gameobject.weapon.firingstrategy.Buffer;
import engine.grid.Grid;
import engine.grid.GridCell;
import engine.grid.GridFree;
import engine.interactions.InteractionEngine;
import engine.pathfinding.EndOfPathException;
import engine.pathfinding.Path;

/**
 * GameWorld where structures belong to a grid.
 * @author Kaighn
 *
 */
public class FreeWorld extends AbstractWorld {
//	private List<GameObject>[][] myGrid;
	private GameObject[][] myGrid;
	private CoordinateTransformer myTransform;
	
//	@SuppressWarnings("unchecked")
	public FreeWorld(int numRows, int numCols){
//		myGrid = (List<GameObject>[][]) new LinkedList<?>[numRows][numCols];
		myGrid = new GameObject[numRows][numCols];
		myTransform = new CoordinateTransformer(numRows, numCols, 1000, 1000); //TODO fix window 1000 1000 measurements
	}

    @Override
    public void addObject (GameObject toSpawn, PointSimple pixelCoords) throws StructurePlacementException { 
		if(!isPlacable(toSpawn,pixelCoords)){
			throw new StructurePlacementException();
		}
		GridCell c = myTransform.transformWorldToGrid(toSpawn.getPoint());
		myGrid[c.getRow()][c.getCol()] = toSpawn;
        super.addObject(toSpawn);
        toSpawn.setPoint(myTransform.tranformGridToWorld(c));
    }

    @Override
    public boolean isPlacable (GameObject toSpawn, PointSimple pixelCoords) {
    	GridCell c = myTransform.transformWorldToGrid(toSpawn.getPoint());
    	return myGrid[c.getRow()][c.getCol()] == null;
    }

}
