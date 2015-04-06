package engine.game;

import gameworld.GameWorld;

public class ConcreteGame implements Game {

    private Player myPlayer;
    private LevelBoard myLevelBoard;

    public ConcreteGame (Player player,LevelBoard level) {
        myPlayer = player;
        myLevelBoard=level;
    }

    @Override
    public void update () {
        // TODO Auto-generated method stub
        myLevelBoard.update();
    }

    public void init () {

    }
    
    public LevelBoard getLevelBoard() {
        return myLevelBoard;
    }

    @Override
    public Player getPlayer () {
        // TODO Auto-generated method stub
        return myPlayer;
    }


}
