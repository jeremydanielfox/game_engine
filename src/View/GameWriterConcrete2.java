package View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import SuperAwesomeDemo.Barrel;
import SuperAwesomeDemo.CollisionEngineAwesome;
import SuperAwesomeDemo.RangeEngineAwesome;
import javafx.application.Application;
import javafx.stage.Stage;
import voogasalad.util.pathsearch.graph.GridCell;
import voogasalad.util.pathsearch.pathalgorithms.NoPathExistsException;
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
import engine.game.Level;
import engine.game.Player;
import engine.game.PlayerUnit;
import engine.game.StoryBoard;
import engine.game.Timer;
import engine.game.TimerConcrete;
import engine.gameobject.GameObject;
import engine.gameobject.GameObjectSimple;
import engine.gameobject.PointSimple;
import engine.gameobject.graphics.RotatorNull;
import engine.gameobject.movers.Mover;
import engine.gameobject.movers.MoverNull;
import engine.gameobject.movers.MoverPath;
import engine.gameobject.movers.MoverUser;
import engine.gameobject.test.GameObjectSimpleTest;
import engine.gameobject.test.TestTower;
import engine.gameobject.weapon.NullWeapon;
import engine.gameobject.weapon.firingstrategy.SingleProjectile;
import engine.gameobject.weapon.firingstrategy.UserStrategy;
import engine.goals.Goal;
import engine.goals.HealthGoal;
import engine.goals.NoCurrentEventGoal;
import engine.goals.ScoreGoal;
import engine.goals.TimerGoal;
import engine.shop.ShopModel;
import engine.shop.ShopModelSimple;
import engine.shop.wallet.ConcreteWallet;
import engine.shop.wallet.Wallet;
import gameworld.FixedWorld;
import gameworld.FreeWorld;
import gameworld.GameWorld;
import gameworld.StructurePlacementException;

public class GameWriterConcrete2 extends Application {
	static GameWriterConcrete2 myWriter;
	private static final String FILE_DESTINATION = "src/xml/GameFreePath.xml";
	GameObject hero;

	/**
	 * @param world
	 * @return
	 */
	private StoryBoard makeStoryBoard(GameWorld world, Player player) {
		List<GameObject> waveObjects = new ArrayList<>();
		Mover moverPath = new MoverPath(world.getPath(), .25);
		for (int i = 0; i < 10; i++) {
			GameObject toAdd = new GameObjectSimpleTest();
			toAdd.setMover(moverPath);
			waveObjects.add(toAdd);
		}
		GameObjectQueue q = new ConcreteQueue(waveObjects);
		TimedEvent wave = new RandomSpanWave(2, 20, q, world);
		wave.setEndingAction(e -> player.changeScore(57));

		List<GameObject> waveObjects2 = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			waveObjects2.add(new GameObjectSimpleTest());
		}
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
	private ConcreteLevelBoard makeLevelBoard(GameWorld world,
			StoryBoard story, Player myPlayer) {
		ConcreteLevelBoard board = new ConcreteLevelBoard();
		HealthGoal healthy = new HealthGoal(myPlayer, 0);
		Timer t = new TimerConcrete(3, 5, "time");
		List<Goal> list = new ArrayList<Goal>();
		list.add(healthy);
		list.add(new TimerGoal(t, 0));
		ScoreGoal score = new ScoreGoal(myPlayer, 200);
		List<Goal> list2 = new ArrayList<Goal>();
		list2.add(score);
		List<Goal> list3 = new ArrayList<Goal>();
		ScoreGoal score2 = new ScoreGoal(myPlayer, 300);
		list3.add(score2);
		Level levelOne = new ConcreteLevel("images/Park_Path.png", list2, list,
				world, story);
		levelOne.addTimer(t);
		board.addLevel(levelOne);
		board.addLevel(new ConcreteLevel("images/example_path.jpeg", list3,
				list, new FixedWorld(13,13), new StoryBoard()));

		return board;
	}

	/**
	 * @return
	 */
	public Player makePlayer() {
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
	private StoryBoard makeStoryBoard(TimedEvent wave) {
		StoryBoard story = new StoryBoard(wave);
		return story;
	}

	/**
	 * @return
	 */
	public GameWorld makeWorld() {

		FreeWorld world = new FreeWorld(10,10);
		world.setCollisionEngine(new CollisionEngineAwesome());
		world.setRangeEngine(new RangeEngineAwesome());
		GridCell[] sPoints = { new GridCell(0, 0), new GridCell(9, 0) };
		List<GridCell> startPoints = Arrays.asList(sPoints);
		GridCell[] ePoints = { new GridCell(9, 9), new GridCell(0, 9),
				new GridCell(6, 9) };
		List<GridCell> endPoints = Arrays.asList(ePoints);
		GridCell[] oPoints = { new GridCell(0, 3), new GridCell(1, 3),
				new GridCell(2, 3) };
		List<GridCell> obstaclePoints = Arrays.asList(oPoints);
		GridCell[] tPoints = {new GridCell(7,3), new GridCell(8,3), new GridCell(9,3),
				new GridCell(7,2), new GridCell(8,2), new GridCell(9,2),
				new GridCell(7,1), new GridCell(8,1),
				new GridCell(7,0), new GridCell(8,0), new GridCell(9,1)};
		List<GridCell> towerPoints = Arrays.asList(tPoints);
		world.setEndPoints(endPoints);
		world.setSpawnPoints(startPoints);
		world.setObstacles(obstaclePoints);
		world.setTowerObstacles(towerPoints);

		try {
			hero = new TestTower(2, 330, 330);
			MoverUser m = new MoverUser();
			m.setGraphic(hero.getGraphic());
			hero.setMover(m);
//			UserStrategy pewpew = new UserStrategy();
//			pewpew.setGraphic(g.getGraphic());
//			g.getWeapon().setFiringStrategy(pewpew);
//			g.getWeapon().setFiringRate(100);
			hero.setWeapon(new NullWeapon());
			hero.getGraphic().setRotator(new RotatorNull());
			hero.getGraphic().setImagePath("/images/BoxheadHero.png");
			world.addObject(hero, new PointSimple(300,300));
		}
		catch (StructurePlacementException e1) {
			e1.printStackTrace();
		}

		try {
			world.getPath().updatePath();
		} catch (NoPathExistsException e) {
		}

		return world;
	}

	public static void main(String[] args) {
		myWriter = new GameWriterConcrete2();
		myWriter.writeGame();
	}

	private void writeGame() {
		Player myPlayer = makePlayer();
		GameWorld myWorld = makeWorld();
		ShopModel myShop = makeShop(myPlayer, myWorld);
		Game myGame = makeGame(myPlayer, myWorld, myShop);

		DataManager.writeToXML(myGame, FILE_DESTINATION);
		System.out.println("Written");
		System.exit(0);
	}

	public ShopModel makeShop(Player player, GameWorld world) {
		ShopModelSimple shop = new ShopModelSimple(world, player, 1);
		GameObject go = new TestTower(0,0,0);
		go.getWeapon().setFiringRate(0);
		shop.addPurchasable(go);
		
		shop.addPurchasable(hero);
		
		
//		shop.addPurchasable(new Spikes());
		return shop;
	}

	/**
	 * @param myPlayer
	 * @param myWorld
	 * @return
	 */
	public Game makeGame(Player myPlayer, GameWorld myWorld, ShopModel myShop) {
		StoryBoard myStory = makeStoryBoard(myWorld, myPlayer);
		Game myGame = new ConcreteGame(myShop, myPlayer, makeLevelBoard(
				myWorld, myStory, myPlayer), new ArrayList<ButtonWrapper>());
		ButtonWrapper wrap = new ButtonWrapper("wave",
				e -> myStory.startNextEvent(), new NoCurrentEventGoal(myStory));
		myGame.addButton(wrap);
		return myGame;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub

	}

}
