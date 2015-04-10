package engine.game;

import java.util.List;
import View.ButtonWrapper;
import gameworld.GameWorld;


public interface Game {

    public void update ();

    public LevelBoard getLevelBoard ();

    public void addButton (ButtonWrapper button);
    
    public List<ButtonWrapper> getButtons();
    // getPlayer method is temporary, just for testing
    public Player getPlayer ();

}
