package game;

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

    }

    public void init () {

    }
    
    public LevelBoard getLevelBoard() {
        return myLevelBoard;
    }

}
