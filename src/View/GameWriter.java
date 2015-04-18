package View;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.stage.Stage;
import xml.DataManager;
import engine.events.ConcreteQueue;
import engine.events.ConstantSpacingWave;
import engine.events.GameObjectQueue;
import engine.events.RandomSpanWave;
import engine.events.TimedEvent;
import engine.game.ConcreteGame;
import engine.game.ConcreteLevel;
import engine.game.ConcreteLevelBoard;
import engine.game.Game;
import engine.game.Player;
import engine.game.PlayerUnit;
import engine.game.StoryBoard;
import engine.gameobject.GameObject;
import engine.gameobject.GameObjectSimpleTest;
import engine.gameobject.test.TestTower;
import engine.goals.Goal;
import engine.goals.HealthGoal;
import engine.goals.NullGoal;
import engine.goals.ScoreGoal;
import engine.pathfinding.PathFixed;
import engine.shop.ShopModel;
import engine.shop.ShopModelSimple;
import engine.shop.wallet.ConcreteWallet;
import engine.shop.wallet.Wallet;
import gameworld.FixedWorld;
import gameworld.GameWorld;


public class GameWriter extends Application {
    static GameWriter myWriter;
    private static final String FILE_DESTINATION = "src/xml/Game.xml";

    /**
     * @param world
     * @return
     */
    private StoryBoard makeStoryBoard (GameWorld world) {
        List<GameObject> waveObjects = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            waveObjects.add(new GameObjectSimpleTest());
        }
        GameObjectQueue q = new ConcreteQueue(waveObjects);
        TimedEvent wave = new RandomSpanWave(2, 20, q, world);
        //TimedEvent wave = new ConstantSpacingWave(2, 1, q, world);
        StoryBoard story = makeStoryBoard(wave);
        return story;
    }

    /**
     * @param world
     * @param story
     * @param myPlayer
     * @return
     */
    private ConcreteLevelBoard makeLevelBoard (GameWorld world, StoryBoard story, Player myPlayer) {
        ConcreteLevelBoard board = new ConcreteLevelBoard();
        HealthGoal healthy = new HealthGoal(myPlayer, 0);
        List<Goal> list = new ArrayList<Goal>();
        list.add(healthy);
        ScoreGoal score = new ScoreGoal(myPlayer, 200);
        List<Goal> list2 = new ArrayList<Goal>();
        list2.add(score);
        List<Goal> list3 = new ArrayList<Goal>();
        ScoreGoal score2 = new ScoreGoal(myPlayer, 300);
        list3.add(score2);

        board.addLevel(new ConcreteLevel("images/Park_Path.png", list2, list, world, story));
        board.addLevel(new ConcreteLevel("images/example_path.jpeg", list3, list, new FixedWorld(),
                                         story));
        return board;
    }

    /**
     * @return
     */
    public Player makePlayer () {
        PlayerUnit health = new PlayerUnit(100, "Health");
        PlayerUnit scoreUnit = new PlayerUnit(100, "Score");
        Wallet wallet = new ConcreteWallet(scoreUnit);
        Player myPlayer = new Player("PlayerName", health, scoreUnit, wallet);
        return myPlayer;
    }

    /**
     * @param wave
     * @return
     */
    private StoryBoard makeStoryBoard (TimedEvent wave) {
        StoryBoard story = new StoryBoard(wave);
        return story;
    }

    /**
     * @return
     */
    public GameWorld makeWorld () {
        FixedWorld world = new FixedWorld();
//        world.addObject(new TestTower(2, 330, 130));
//        world.addObject(new TestTower(4, 270, 270));
//        world.addObject(new TestTower(3, 355, 455));
        world.setPath(DataManager.readFromXML(PathFixed.class, "src/gae/listView/Test.xml"));
        return world;
    }

    public static void main (String[] args) {
        myWriter = new GameWriter();
        myWriter.writeGame();
    }

    private void writeGame () {
        Player myPlayer = makePlayer();
        GameWorld myWorld = makeWorld();
        ShopModel myShop = new ShopModelSimple(myWorld, myPlayer, 1);
        Game myGame = makeGame(myPlayer, myWorld, myShop);

        DataManager.writeToXML(myGame, FILE_DESTINATION);
        System.out.println("Written");
    }

    public ShopModel makeShop (Player player, GameWorld world) {
        return new ShopModelSimple(world, player, 1.2);
    }

    /**
     * @param myPlayer
     * @param myWorld
     * @return
     */
    public Game makeGame (Player myPlayer, GameWorld myWorld, ShopModel myShop) {
        StoryBoard myStory = makeStoryBoard(myWorld);
        Game myGame =
                new ConcreteGame(myShop, myPlayer, makeLevelBoard(myWorld, myStory,
                                                                  myPlayer),
                                 new ArrayList<ButtonWrapper>());
        ButtonWrapper wrap =
                new ButtonWrapper("wave", e -> myStory.startNextEvent(), new NullGoal());
        // ButtonWrapper wrap=new ButtonWrapper("wave",e->story.startNextEvent(),new
        // NoCurrentEventGoal());
        myGame.addButton(wrap);
        return myGame;
    }

    @Override
    public void start (Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub

    }

}
