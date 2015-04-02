package gae.gridView;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;


public class GridView {
    private Group root;
    private Scene myScene;

    public Scene getScene () {
        root = new Group();
        root.getChildren().addAll(makeGrid(10, 10));
        myScene = new Scene(root);
        return myScene;
    }

    private GridPane makeGrid (int row, int col) {
        GridPane grid = new GridPane();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Tile tile = new Tile();
                tile.xProperty().bind(grid.heightProperty().divide(row).multiply(i));
                tile.yProperty().bind(grid.heightProperty().divide(col).multiply(j));
                tile.heightProperty().bind(grid.heightProperty().divide(row));
                tile.widthProperty().bind(tile.heightProperty());
                grid.getChildren().add(tile);

            }
        }
        return grid;
    }
}
