package gae.gameWorld;

import gameworld.FreeWorld;
import gameworld.GameWorld;
import java.awt.Dimension;
import javafx.beans.property.ObjectProperty;

public class FreeGameWorldFactory implements GameWorldFactory {

    ObjectProperty<Dimension> dimensions;
    
    public void bindGridSize(ObjectProperty<Dimension> dimensionsIn) {
        dimensions.bind(dimensionsIn);
    }
    
    @Override
    public GameWorld createGameWorld () {
        return new FreeWorld(dimensions.get().width, dimensions.get().height) ;
    }

}
