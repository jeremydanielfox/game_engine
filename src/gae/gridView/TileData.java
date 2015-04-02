package gae.gridView;

public class TileData {
    private int row;
    private int col;
    private boolean walkable = true;

    public TileData (int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void changeState () {
        walkable = !walkable;
        System.out.println("Row is: " + row + " Col is: " + col);
        System.out.println("New state is: " + walkable);
    }
}
