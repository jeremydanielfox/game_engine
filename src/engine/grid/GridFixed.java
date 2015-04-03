package engine.grid;

import java.util.LinkedList;

import engine.pathfinding.Path;

public class GridFixed extends GridAbstract implements Grid {
	Path myPath;
	
	public GridFixed(Path p){
		myObjects = new LinkedList<>();
		myPath = p;
	}

}
