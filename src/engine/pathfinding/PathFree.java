package engine.pathfinding;

import engine.gameobject.GameObject;
import engine.gameobject.PointSimple;
import gameworld.CoordinateTransformer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import voogasalad.util.pathsearch.graph.GridCell;
import voogasalad.util.pathsearch.pathalgorithms.NoPathExistsException;
import voogasalad.util.pathsearch.wrappers.GridWrapper;
import engine.fieldsetting.Settable;

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
    private GridCell bounds;
    private List<GridCell> endPoints, spawnPoints, obstacles;

    public PathFree (CoordinateTransformer cTrans, GameObject[][] matrix) {
        myAlgorithm = new GridWrapper();
        myTrans = cTrans;
        objectMatrix = matrix;
        endPoints = new ArrayList<>();
        endPoints.add(new GridCell(9, 9));
//        endPoints.add(new GridCell(0, 9));
//        endPoints.add(new GridCell(9, 0));
        spawnPoints = new ArrayList<>();
        spawnPoints.add(new GridCell(0, 0));
//        spawnPoints.add(new GridCell(4, 4));
        bounds = new GridCell(matrix.length, matrix[0].length);
        obstacles = new ArrayList<>();
    }

    @Override
    public void updatePath () throws NoPathExistsException {
    	Boolean[][] obstacleMatrix = new Boolean[objectMatrix.length][objectMatrix[0].length];
    	for(int r = 0; r < objectMatrix.length; r++){
    		for(int c = 0; c < objectMatrix[0].length; c++){
    			obstacleMatrix[r][c] = objectMatrix[r][c] != null || obstacles.contains(new GridCell(r,c));
    		}
    	}
        myAlgorithm.initializeGraph(obstacleMatrix, b -> (Boolean)b);
        myPath = myAlgorithm.allShortestPaths(GridCell.toArray(endPoints));
        
        for(GridCell end : endPoints){
        	for(GridCell start : spawnPoints){
                myAlgorithm.shortestPath(end.getRow(),end.getCol(), start.getRow(), start.getCol());
        	}
        }
        // myPathCoordinates = myPath.stream().map(cell ->
        // myTrans.tranformGridToWorld(cell)).collect(Collectors.toList());
        // p = new PathFixed();
        // for(int i = 0; i < myPathCoordinates.size()-1; i++){
        // p.addPathSegment(new
        // PathSegmentStraight(myPathCoordinates.get(i),myPathCoordinates.get(i+1)));
        // }
    }

    @Override
    public PointSimple getNextLocation (double distance, double speed, PointSimple current)
                                                                                           throws EndOfPathException {
        GridCell currentCell = myTrans.transformWorldToGrid(current);
        GridCell nextCell = myPath.get(currentCell);

        // GridWrapper.printGradient(myPath);
        for (GridCell endpoint : endPoints) {
            if (currentCell.equals(endpoint)) {
                throw new EndOfPathException();
            }
        }
        if (nextCell != null) {
            return PointSimple.pointOnLine(
                                           current,
                                           myTrans.tranformGridToWorld(nextCell), speed);
        }
        else {
            if (currentCell.withinBounds(bounds)) {// TODO make better algorithmically
                for (GridCell c : myPath.keySet()) {
                    if (c.distance(currentCell) == 1) {
                        return myTrans.transformGridToWorldInexact(c);
                    }
                }
            }
            return myTrans.tranformGridToWorld(spawnPoints.get((int)(Math.random()*spawnPoints.size())));
        }
    }

	@Override
	public void addPathSegment(PathSegment ps) {}	
	
	public void setEndPoints(List<GridCell> endpoints){
		endPoints = endpoints;
	}
	
	public void setSpawnPoints(List<GridCell> spawnpoints){
		spawnPoints = spawnpoints;
	}
	
	public void setObstacles(List<GridCell> obstacles){
		this.obstacles = obstacles;
	}

}
