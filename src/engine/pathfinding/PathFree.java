package engine.pathfinding;

import engine.gameobject.GameObject;
import engine.gameobject.PointSimple;
import gameworld.CoordinateTransformer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


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
    private GridCell spawnPoint, bounds;
    private List<GridCell> endPoints;

    public PathFree (CoordinateTransformer cTrans, GameObject[][] matrix) {
        myAlgorithm = new GridWrapper();
        myTrans = cTrans;
        objectMatrix = matrix;
        endPoints = new ArrayList<>();
        endPoints.add(new GridCell(8, 8));
        endPoints.add(new GridCell(5, 0));
        spawnPoint = new GridCell(0, 0);
        bounds = new GridCell(matrix.length, matrix[0].length);
    }

    @Override
    public void updatePath () throws NoPathExistsException {
        myAlgorithm.initializeGraph(objectMatrix, go -> go != null);
        myPath = myAlgorithm.allShortestPaths(GridCell.toArray(endPoints));
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
                        return myTrans.tranformGridToWorld(c);
                        // return
                        // PointSimple.pointOnLine(current,myTrans.tranformGridToWorld(c),speed);
                    }
                }
            }
            return myTrans.tranformGridToWorld(spawnPoint);
        }
    }

    @Override
    public void addPathSegment (PathSegment ps) {
    }

}
