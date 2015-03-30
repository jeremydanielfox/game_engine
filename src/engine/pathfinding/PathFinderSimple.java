package engine.pathfinding;

import engine.grid.Gridlike;

/**
 * 
 * @author Kaighn
 *
 */
public class PathFinderSimple implements PathFinder {	
	public void updatePaths(boolean[][] grid, int[][] endCoordinates){
		if(endCoordinates[0].length != 2) return;
		//TODO
	}
	
	@Override
	public Gridlike getNextLocation(Gridlike cell) {
		return null; //TODO implement graph search
	}

}
