package View;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import engine.events.ConcreteQueue;
import engine.events.ConstantSpacingWave;
import engine.events.GameObjectQueue;
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
import engine.goals.Goal;
import engine.goals.HealthDepletionGoal;
import engine.goals.NullGoal;
import engine.goals.ScoreGoal;
import engine.grid.StructurePlacementException;
import engine.shop.wallet.ConcreteWallet;
import engine.shop.wallet.Wallet;
import gae.gameView.Main;
import gameworld.BasicWorld;
import gameworld.GameWorld;


public class GamePlayerScreen extends Application {

    private VBox myVbox;
    private Game myGame;
    private Stage myStage;
    private ViewConcrete2 myGameView;

    public GamePlayerScreen () {
        // myGame =
        // new ConcreteGame(new Player("myPlayer", null, null, null),
        // new ConcreteLevelBoard(), new ArrayList<ButtonWrapper>());
        myVbox = new VBox(30);
        makeDis();
    }

    private void addDetails (String label, String text) {
        VBox insideBox = new VBox(10);
        Label labelText = new Label(label);

        Text description = new Text(text);
        insideBox.getChildren().addAll(labelText, description);
        insideBox.setAlignment(Pos.CENTER);
        myVbox.getChildren().addAll(insideBox);
    }

    public void makeDis () {
        addDetails("Name", "this is an example of a name"); // this should be taken in from the GAE
        addDetails("Description", "this is an example of a description"); // this should be taken in
                                                                          // from the GAE
        addDetails("Instructions", "this is an example of instructions"); // this should be taken in
                                                                          // from the GAE

        Button scoreBtn = new Button("View high scores");
        Button playBtn = new Button("play");

        playBtn.setOnAction(e -> startGame());

        myVbox.getChildren().addAll(scoreBtn, playBtn);
    }

    private void startGame () {
        // myGameView = new ViewConcrete2(myGame, Main.SCREEN_WIDTH, Main.SCREEN_WIDTH);

        Group root = new Group();
        root.getChildren().add(makeDemoGame());
        Scene scene = new Scene(root);
        myStage.setScene(scene);

    }

    public Node makeDemoGame () {
        ConcreteLevelBoard board = new ConcreteLevelBoard();

        GameWorld world = new BasicWorld();

        world.addObject(new GameObjectSimpleTest());

        GameObjectQueue q = new ConcreteQueue(new ArrayList<GameObject>());
        TimedEvent wave = new ConstantSpacingWave(2.0, q, world);
        StoryBoard story = new StoryBoard(wave);

        PlayerUnit health = new PlayerUnit(100, "Health");
        PlayerUnit scoreUnit = new PlayerUnit(100, "Score");
        Wallet wallet = new ConcreteWallet(scoreUnit);
        Player myPlayer = new Player("PlayerName", health, scoreUnit, wallet);
        myGame = new ConcreteGame(myPlayer, board, new ArrayList<ButtonWrapper>());
        // ButtonWrapper wrap=new ButtonWrapper("wave",e->story.startNextEvent(),new NullGoal());
        ButtonWrapper wrap = new ButtonWrapper("wave", e -> story.startNextEvent(), new NullGoal());
        myGame.addButton(wrap);

        HealthDepletionGoal healthy = new HealthDepletionGoal(myPlayer);
        List<Goal> list = new ArrayList<Goal>();
        list.add(healthy);
        ScoreGoal score = new ScoreGoal(myPlayer, 200);
        List<Goal> list2 = new ArrayList<Goal>();
        list2.add(score);
        List<Goal> list3 = new ArrayList<Goal>();
        ScoreGoal score2 = new ScoreGoal(myPlayer, 300);
        list3.add(score2);

        board.addLevel(new ConcreteLevel("images/Park_Path.png", list2, list, world, story));
        board.addLevel(new ConcreteLevel("images/example_path.jpeg", list3, list, new BasicWorld(),
                                         story));

        myGameView = new ViewConcrete2(myGame, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        Node node = myGameView.initializeView();
        return node;
    }

    public static void main (String[] args) {
        launch(args);
    }

    @Override
    public void start (Stage primaryStage) throws Exception {
        myStage = primaryStage;
        myStage.setWidth(Main.SCREEN_WIDTH);
        myStage.setHeight(Main.SCREEN_HEIGHT);
        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(0, 40, 0, 0));
        Scene scene = new Scene(pane);
        myVbox.setAlignment(Pos.CENTER);
        pane.setRight(myVbox);
        VBox veebz = new VBox();
        ImageView image = new ImageView("images/Park_Path.png");
        image.setPreserveRatio(true);
        image.setFitHeight(Main.SCREEN_HEIGHT);
        Label label = new Label("Game Type");
        label.setTextFill(Color.BLUE);
        veebz.getChildren().addAll(image, label);
        veebz.setAlignment(Pos.CENTER);
        pane.setLeft(veebz);
        myStage.setScene(scene);
        myStage.show();
    }

}
