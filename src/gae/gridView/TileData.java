package gae.gridView;

public class TileData {
    private int row;
    private int col;
    private boolean walkable = true;

    public TileData (double row, double col) {
        this.row = (int) Math.round(row);
        this.col = (int) Math.round(col);
    }

    public void changeState () {
        walkable = !walkable;
        System.out.println("Row is: " + row + " Col is: " + col);
        System.out.println("New state is: " + walkable);
    }
}
