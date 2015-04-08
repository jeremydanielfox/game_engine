package engine.game;

import engine.fieldsetting.Settable;


/**
 * 
 * @author Jeremy
 *
 */
@Settable
public class ConcreteGame implements Game {

    private Player myPlayer;
    private LevelBoard myLevelBoard;
    
    public ConcreteGame() {
        myPlayer = new Player();
        myLevelBoard = new ConcreteLevelBoard();
    }

    public ConcreteGame (Player player, LevelBoard level) {
        myPlayer = player;
        myLevelBoard = level;
    }

    @Override
    public void update () {
        // TODO Auto-generated method stub
        myLevelBoard.update();
    }

    public void init () {

    }

    public LevelBoard getLevelBoard () {
        return myLevelBoard;
    }

    @Override
    public Player getPlayer () {
        // TODO Auto-generated method stub
        return myPlayer;
    }

    @Settable
    public void setPlayer (Player player) {
        myPlayer = player;
    }

    @Settable
    public void setLevelBoard (LevelBoard levelBoard) {
        myLevelBoard = levelBoard;
    }

}
