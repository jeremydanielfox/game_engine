package engine.game;

public interface Game {
    
    public void update ();
    
    public LevelBoard getLevelBoard();
    //getPlayer method is temporary, just for testing
    public Player getPlayer();

}
