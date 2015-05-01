package SuperAwesomeDemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.application.Application;
import javafx.stage.Stage;
import voogasalad.util.pathsearch.graph.GridCell;
import voogasalad.util.pathsearch.pathalgorithms.NoPathExistsException;
import xml.DataManager;
import View.ButtonWrapper;
import View.ViewConcrete2;
import engine.events.ConcreteQueue;
import engine.events.ConstantSpacingWave;
import engine.events.GameObjectQueue;
import engine.events.TimedEvent;
import engine.game.ConcreteGame;
import engine.game.ConcreteLevel;
import engine.game.ConcreteLevelBoard;
import engine.game.Game;
import engine.game.Level;
import engine.game.Player;
import engine.game.PlayerUnit;
import engine.game.StoryBoard;
import engine.game.Timer;
import engine.game.TimerConcrete;
import engine.gameobject.GameObject;
import engine.gameobject.Graphic;
import engine.gameobject.PointSimple;
import engine.gameobject.behaviors.PlayerChangeBehavior;
import engine.goals.Goal;
import engine.goals.HealthGoal;
import engine.goals.NoCurrentEventGoal;
import engine.goals.TimerGoal;
import engine.shop.ShopModel;
import engine.shop.ShopModelSimple;
import engine.shop.wallet.ConcreteWallet;
import engine.shop.wallet.Wallet;
import gameworld.CoordinateTransformer;
import gameworld.FixedWorld;
import gameworld.FreeWorld;
import gameworld.GameWorld;
import gameworld.GridCellFromPoint;
import gameworld.StructurePlacementException;


public class GameWriterSuperAwesome extends Application {
    static GameWriterSuperAwesome myWriter;
    private static final String FILE_DESTINATION = "src/SuperAwesomeDemo/SuperAwesomeGame.xml";

    /**
     * @param world
     * @return
     */
    private StoryBoard makeStoryBoard (GameWorld world, Player player) {
        List<GameObject> waveObjects = new ArrayList<>();
        PlayerChangeBehavior pointBehavior = new PlayerChangeBehavior();
        PlayerChangeBehavior healthBehavior = new PlayerChangeBehavior();
        healthBehavior.addPlayer(player);
        healthBehavior.setHealth(10);
        pointBehavior.addPlayer(player);
        pointBehavior.setMoney(10);
        pointBehavior.setPoint(10);
        for (int i = 0; i < 10; i++) {
//            Devil toAdd = new Devil(world);
            BasicEnemy toAdd = new BasicEnemy(world);
            waveObjects.add(toAdd);
        }
        for (int i=1;i<4;i++) {
            waveObjects.add(new Devil(world));
        }
        
        for (int i=0;i<7;i++) {
            waveObjects.add(new BasicEnemy(world));
        }
        
        for (int i=0;i<20;i++) {
            waveObjects.add(new Devil(world));
        }
        

        for (int i = 0; i < 100; i++) {
            waveObjects.add(new BasicEnemy(world));
        }
        
        GameObjectQueue q = new ConcreteQueue(waveObjects);
        TimedEvent wave = new ConstantSpacingWave(2,2, q, world);
        wave.setEndingAction(e -> player.changeScore(57));

        List<GameObject> waveObjects2 = new ArrayList<>();
        waveObjects2.add(new Devil(world));
        waveObjects2.add(new Devil(world));
        GameObjectQueue q2 = new ConcreteQueue(waveObjects2);
        TimedEvent wave2 = new ConstantSpacingWave(2, q2, world);

        StoryBoard story = makeStoryBoard(wave);
        story.addEvent(wave2);
        
        
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
        Timer t = new TimerConcrete(3, 5, "time");
        List<Goal> list = new ArrayList<Goal>();
        list.add(healthy);
        list.add(new TimerGoal(t, 0));
        // ScoreGoal score = new ScoreGoal(myPlayer, 200);
        List<Goal> list2 = new ArrayList<Goal>();
        // list2.add(score);
        List<Goal> list3 = new ArrayList<Goal>();
        // ScoreGoal score2 = new ScoreGoal(myPlayer, 300);
        // list3.add(score2);
        Level levelOne = new ConcreteLevel("images/Gray_Hexagon.jpg", list2, list, world, story);
        levelOne.addTimer(t);
        board.addLevel(levelOne);
        board.addLevel(new ConcreteLevel("images/example_path.jpeg", list3, list,
                                         new FixedWorld(10, 10),
                                         new StoryBoard()));

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
        FreeWorld world = new FreeWorld(10, 10);
        world.setCollisionEngine(new CollisionEngineAwesome());
        world.setRangeEngine(new RangeEngineAwesome());
        // world.addObject(new TestTower(2, 330, 130));
        // world.addObject(new TestTower(5, 270, 270));
        // world.addObject(new TestTower(3, 355, 455));
        Hero hero = new Hero(100, 100);
        // start initializing weapon powerups
        
        Graphic mineGraphic = new Graphic(40,40,"/images/Mine.png");
        WeaponPowerUp mine = new WeaponPowerUp(new MineLayer(hero),mineGraphic);
        mine.setPoint(new PointSimple(550,50));
        
        Graphic towerShooterGraphic = new Graphic (35,56, "/images/Cool_Turret.png");
        WeaponPowerUp towerShooter = new WeaponPowerUp(new TowerShooter(hero), towerShooterGraphic);
        towerShooter.setPoint(new PointSimple(550, 250));
        
        Graphic shotgunGraphic = new Graphic(30, 100, "/images/Shotgun.png");
        WeaponPowerUp shotgun = new WeaponPowerUp(new Shotgun(hero),shotgunGraphic);
        shotgun.setPoint(new PointSimple(50, 250));
        
        Graphic rocketGraphic = new Graphic(45,150, "/images/Missile_Launcher.png");
        WeaponPowerUp rocket = new WeaponPowerUp(new RocketLauncher(hero), rocketGraphic);
        rocket.setPoint(new PointSimple(80, 500));
        world.addObject(mine);
        world.addObject(towerShooter);
        world.addObject(shotgun);
        world.addObject(rocket);
        
        Graphic mindGraphic = new Graphic(50, 50, "images/brain_shooter.png");
        WeaponPowerUp mind = new WeaponPowerUp(new MindController(hero), mindGraphic);
        mind.setPoint(new PointSimple(250, 550));
        
        world.addObject(mind);
        
        Graphic machineGraphic = new Graphic(50,50,"images/Machine_Gun.png");
        WeaponPowerUp machine = new WeaponPowerUp(new MachineGun(hero), machineGraphic);
        machine.setPoint(new PointSimple(550, 550));
        world.addObject(machine);

        GridCell[] sPoints = { new GridCell(0, 0), new GridCell(9, 0) };
        List<GridCell> startPoints = Arrays.asList(sPoints);
        world.setSpawnPoints(startPoints);
        try {
            world.addObject(new Barrel(), new PointSimple(100, 100));
        }
        catch (StructurePlacementException e1) {
            System.out.println("can't place");
        }

        try {
            world.addObject(hero, new PointSimple(300, 300));
        }
        catch (StructurePlacementException e) {
        }

        GridCellFromPoint c =
                new GridCellFromPoint(new CoordinateTransformer(10, 10,
                                                                ViewConcrete2.getWorldWidth(),
                                                                ViewConcrete2.getWorldHeight()),
                                      hero);
        List<GridCell> lst = new ArrayList<>();
        lst.add(c);
        world.setEndPoints(lst);

        try {
            world.getPath().updatePath();
        }
        catch (NoPathExistsException e) {
        }

        return world;
    }

    public static void main (String[] args) {
        myWriter = new GameWriterSuperAwesome();
        myWriter.writeGame();
    }

    private void writeGame () {
        Player myPlayer = makePlayer();
        GameWorld myWorld = makeWorld();
        Game myGame = makeGame(myPlayer, myWorld, makeShop(myPlayer, myWorld));

        DataManager.writeToXML(myGame, FILE_DESTINATION);
        System.out.println("Written");
        System.exit(0);
    }

    public ShopModel makeShop (Player player, GameWorld world) {
        ShopModelSimple shop = new ShopModelSimple(world, player, 1);
        
        shop.addPurchasable(new Wall());
        shop.addPurchasable(new Barrel());
//        shop.addPurchasable(new Hero(0,0));
        return shop;
    }

    /**
     * @param myPlayer
     * @param myWorld
     * @return
     */
    public Game makeGame (Player myPlayer, GameWorld myWorld, ShopModel myShop) {
        StoryBoard myStory = makeStoryBoard(myWorld, myPlayer);
        Game myGame =
                new ConcreteGame(myShop, myPlayer, makeLevelBoard(myWorld, myStory,
                                                                  myPlayer),
                                 new ArrayList<ButtonWrapper>());
        ButtonWrapper wrap =
                new ButtonWrapper("wave", e -> myStory.startNextEvent(),
                                  new NoCurrentEventGoal(myStory));
        myGame.addButton(wrap);
        return myGame;
    }

    @Override
    public void start (Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub

    }

}
