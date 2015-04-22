package engine.pathfinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.swing.text.html.HTMLDocument.Iterator;

import voogasalad.util.pathsearch.graph.GridCell;
import voogasalad.util.pathsearch.pathalgorithms.NoPathExistsException;
import voogasalad.util.pathsearch.wrappers.GridWrapper;
import engine.gameobject.GameObject;
import engine.gameobject.PointSimple;
import gameworld.CoordinateTransformer;

/**
 * A path to represent a free path. Game objects will receive their next
 * destination not through some lookup of preset paths (ie fixed path) but will
 * use pathfinding to navigate obstacles.
 * 
 * @author Kaighn
 *
 */
public class PathFree implements Path {
	private GridWrapper myAlgorithm;
	private Map<GridCell, GridCell> myPath;
	private CoordinateTransformer myTrans;
	private GameObject[][] objectMatrix;
	private GridCell endPoint, spawnPoint, bounds; 

	public PathFree(CoordinateTransformer cTrans, GameObject[][] matrix) {
		myAlgorithm = new GridWrapper();
		myTrans = cTrans;
		objectMatrix = matrix;
		endPoint = new GridCell(8, 8);
		spawnPoint = new GridCell(0, 0);
		bounds = new GridCell(matrix.length,matrix[0].length);
	}

	@Override
	public void updatePath() throws NoPathExistsException {
		myAlgorithm.initializeGraph(objectMatrix, go -> go != null);
		int[] endNodes = {8,8};
		myPath = myAlgorithm.allShortestPaths(endNodes);		
		// myPathCoordinates = myPath.stream().map(cell ->
		// myTrans.tranformGridToWorld(cell)).collect(Collectors.toList());
		// p = new PathFixed();
		// for(int i = 0; i < myPathCoordinates.size()-1; i++){
		// p.addPathSegment(new
		// PathSegmentStraight(myPathCoordinates.get(i),myPathCoordinates.get(i+1)));
		// }
	}

	@Override
	public PointSimple getNextLocation(double distance, double speed, PointSimple current)
			throws EndOfPathException {
		GridCell currentCell = myTrans.transformWorldToGrid(current);
		GridCell nextCell = myPath.get(currentCell);

		if (nextCell != null) {
			return PointSimple.pointOnLine(
					current,
					myTrans.tranformGridToWorld(nextCell), speed);
		} else {
			if(currentCell.equals(endPoint)){
				throw new EndOfPathException();
			}
			else if(currentCell.withinBounds(bounds)){//TODO make better algorithmically
				for(GridCell c : myPath.keySet()){
					if(c.distance(currentCell)==1){
						return myTrans.tranformGridToWorld(c);
//						return PointSimple.pointOnLine(current,myTrans.tranformGridToWorld(c),speed);
					}
				}
			}
			return myTrans.tranformGridToWorld(spawnPoint);
		}
	}

	@Override
	public void addPathSegment(PathSegment ps) {}

}
