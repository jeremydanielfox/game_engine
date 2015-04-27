package gae.gameWorld;

import gameworld.AbstractWorld;
import gameworld.FreeWorld;
import gameworld.GameWorld;
import java.awt.Dimension;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * Creates GameWorlds with free paths
 * @author JohnGilhuly
 *
 */

public class FreeGameWorldFactory implements GameWorldFactory {

    ObjectProperty<Dimension> dimensions;
    
    public void bindGridSize(ObjectProperty<Dimension> dimensionsIn) {
        if (dimensions == null) {
            dimensions = new SimpleObjectProperty<Dimension>();
        }
        
        dimensions.bind(dimensionsIn);
    }
    
    @Override
    public AbstractWorld createGameWorld () {
        return new FreeWorld(dimensions.get().width, dimensions.get().height) ;
    }

}
