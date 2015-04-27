package gae.gameWorld;

import java.awt.Dimension;

import javafx.beans.property.ObjectProperty;
import gameworld.AbstractWorld;
import gameworld.GameWorld;

/**
 * Interface that creates a GameWorld object
 * @author JohnGilhuly
 *
 */

public interface GameWorldFactory {
    public AbstractWorld createGameWorld();
    public void bindGridSize(ObjectProperty<Dimension> dimensionsIn);
}
