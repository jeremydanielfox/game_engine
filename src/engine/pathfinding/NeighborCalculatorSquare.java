package engine.pathfinding;

public class NeighborCalculatorSquare implements NeighborCalculator {
    @Override
    public boolean isNeighbor (int aRow, int aCol, int bRow, int bCol) {
        return Math.abs(aRow - bRow) <= 1 && Math.abs(aCol - bCol) <= 1;
    }
}
