package gae.gameWorld;

import gameworld.FixedWorld;
import gameworld.GameWorld;
import java.awt.Dimension;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * Factory to create a GameWorld with a fixed path
 * 
 * @author JohnGilhuly
 *
 */

public class FixedGameWorldFactory implements GameWorldFactory {

    ObjectProperty<Dimension> dimensions;

    public void bindGridSize(ObjectProperty<Dimension> dimensionsIn) {
        if (dimensions == null) {
            dimensions = new SimpleObjectProperty<Dimension>();
        }
        
        dimensions.bind(dimensionsIn);
    }

    @Override
    public GameWorld createGameWorld () {
        return new FixedWorld(dimensions.get().width, dimensions.get().height);
    }

}
