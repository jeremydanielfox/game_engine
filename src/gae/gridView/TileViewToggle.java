package gae.gridView;

import java.awt.Dimension;
import java.awt.Point;
import java.util.List;
import engine.gameobject.PointSimple;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Group;
import javafx.scene.Scene;


/**
 * Holds the multiple TileContainers such that users can set boolean properties for towers and
 * enemies
 *
 * @author Nina Sun
 *
 */
public class TileViewToggle extends Group implements ContainerWrapper {
    private ObjectProperty<TileMode> tileModeProperty;
    private TileContainer towerview;
    private TileContainer enemyview;
    private TileContainer startpointview;
    private TileContainer endpointview;

    public TileViewToggle (ObjectProperty<Dimension> sizeProp, Scene scene) {
        towerview = new TileContainer(sizeProp, scene, "blue");
        enemyview = new TileContainer(sizeProp, scene, "red");
        startpointview = new TileContainer(sizeProp, scene, "yellow");
        endpointview = new TileContainer(sizeProp, scene, "green");
        tileModeProperty = new SimpleObjectProperty<>();
        tileModeProperty.addListener( (obs, oldVal, newVal) -> {
            switch (newVal) {
                case ENEMYMODE:
                    getChildren().clear();
                    getChildren().add(enemyview);
                    break;
                case TOWERMODE:
                    getChildren().clear();
                    getChildren().add(towerview);
                    break;
                case STARTPOINTMODE:
                    getChildren().clear();
                    getChildren().add(startpointview);
                    break;
                case ENDPOINTMODE:
                    getChildren().clear();
                    getChildren().add(endpointview);
                    break;
            }

        });
    }

    public enum TileMode {
        ENEMYMODE, TOWERMODE, STARTPOINTMODE, ENDPOINTMODE
    }

    public ObjectProperty<TileMode> getTileModeProperty () {
        return tileModeProperty;
    }

    public DoubleProperty getGridHeightProperty () {
        return towerview.getGridHeightProperty();
    }

    public DoubleProperty getGridWidthProperty () {
        return towerview.getGridWidthProperty();
    }

    @Override
    public boolean checkBounds (double x, double y) {
        return tileModeProperty.get().equals(TileMode.TOWERMODE) ? towerview.checkBounds(x, y)
                                                                : enemyview.checkBounds(x, y);
    }

    @Override
    public PointSimple convertCoordinates (double x, double y) {
        return tileModeProperty.get().equals(TileMode.TOWERMODE) ? towerview.convertCoordinates(x,
                                                                                                y)
                                                                : enemyview
                                                                        .convertCoordinates(x, y);
    }

    /**
     * To be used in making grid
     * 
     * @return list of grid coordinates where towers cannot be placed
     *
     */
    public List<Point> getTowerUnwalkable () {
        return towerview.getUnwalkableTiles();
    }

    /**
     * To be used in making grid
     * 
     * @return list of grid coordinates where enemies cannot walk
     *
     */
    public List<Point> getEnemyUnwalkable () {
        return enemyview.getUnwalkableTiles();
    }
    
    public List<Point> getStartPoints () {
        return startpointview.getUnwalkableTiles();
    }
    
    public List<Point> getEndPoints () {
        return endpointview.getUnwalkableTiles();
    }
}
