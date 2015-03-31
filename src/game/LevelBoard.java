package game;

public interface LevelBoard {
    
    abstract void startNextLevel ();

    public boolean gameOver();
    
    public String getCurrentLevelMap ();
}
