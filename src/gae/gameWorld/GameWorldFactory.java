package gae.gameWorld;

import java.awt.Dimension;
import javafx.beans.property.ObjectProperty;
import gameworld.GameWorld;

/**
 * Interface that creates a GameWorld object
 * @author JohnGilhuly
 *
 */

public interface GameWorldFactory {
    public GameWorld createGameWorld();
    public void bindGridSize(ObjectProperty<Dimension> dimensionsIn);
}
