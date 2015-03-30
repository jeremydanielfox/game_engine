package engine.pathfinding;

/**
 * Strategy for calculating if two grid entries are neighbors. Ex: Square versus Hexagon.
 * @author Kaighn
 *
 */
public interface NeighborCalculator {
	public boolean isNeighbor(int aRow, int aCol, int bRow, int bCol);
}
