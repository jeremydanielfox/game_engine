package engine.game;

import gameworld.GameWorld;
import java.util.Observer;

public interface LevelBoard {
    
    abstract void startNextLevel ();

    public boolean gameOver();
    
    public String getCurrentLevelMap ();
    
    public void update ();
    
    public void addObserver(Observer o);
    
    public GameWorld getGameWorld();
}
