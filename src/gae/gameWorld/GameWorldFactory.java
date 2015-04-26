package gae.gameWorld;

import java.awt.Dimension;
import javafx.beans.property.ObjectProperty;
import gameworld.GameWorld;

public interface GameWorldFactory {
    public GameWorld createGameWorld();
    public void bindGridSize(ObjectProperty<Dimension> dimensionsIn);
}
