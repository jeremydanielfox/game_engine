package gameworld;

import voogasalad.util.pathsearch.graph.GridCell;
import engine.gameobject.PointSimple;

public class Terrain {
	private TerrainTile[][] myMatrix;
	private CoordinateTransformer myTrans;
	private GridCell myBounds;
	
	public Terrain(int numRows, int numCols, CoordinateTransformer t){
		myMatrix = new TerrainTile[numRows][numCols];
		myTrans = t;
		for(int i = 0; i < myMatrix.length; i++){
			for(int j = 0; j < myMatrix.length; j++){
				myMatrix[i][j] = new TerrainTile();
			}
		}
		myBounds = new GridCell(numRows,numCols);
	}
	
	public boolean canPlaceStructure(PointSimple point){
		GridCell c = myTrans.transformWorldToGrid(point);
		return canPlaceStructure(c);
	}
	
	public boolean canEnemyWalk(PointSimple point){
		GridCell c = myTrans.transformWorldToGrid(point);
		return canEnemyWalk(c);
	}
	
	public boolean canEnemyWalk(GridCell c){
		return myMatrix[c.getRow()][c.getCol()].getPlace();
	}
	public boolean canPlaceStructure(GridCell c){
		return myMatrix[c.getRow()][c.getCol()].getPlace();
	}
	
	public void setTerrain(TerrainTile t, int r, int c){
		myMatrix[r][c] = t;
	}
	public TerrainTile getTerrainTile(GridCell c) throws InvalidArgumentException{
		if(!c.withinBounds(myBounds)){
			throw new InvalidArgumentException();
		}
		return myMatrix[c.getRow()][c.getCol()];
	}
}
