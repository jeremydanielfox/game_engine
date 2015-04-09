package View;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
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
import engine.shop.wallet.ConcreteWallet;
import engine.shop.wallet.Wallet;
import gae.gameView.Main;
import gameworld.BasicWorld;
import gameworld.GameWorld;

public class TestEngine extends Application {
    
    @Override
    public void start (Stage primaryStage) throws Exception {
        ConcreteLevelBoard board=new ConcreteLevelBoard();
        Group root=new Group();
        primaryStage.setHeight(Main.SCREEN_HEIGHT); // needs to account for scaling; add constants
        primaryStage.setWidth(Main.SCREEN_WIDTH);// needs to account for scaling; add constants
        //BorderPane pane = new BorderPane();
        
        GameWorld world = new BasicWorld();
        world.addObject(new GameObjectSimpleTest());
        GameObjectQueue q = new ConcreteQueue(new ArrayList<GameObject>());
        TimedEvent wave = new ConstantSpacingWave(2.0, q, world);
        StoryBoard story = new StoryBoard(wave);
        
        
        Scene scene=new Scene(root);
        PlayerUnit health = new PlayerUnit(100, "Health");
        PlayerUnit scoreUnit = new PlayerUnit(100, "Score");
        Wallet wallet = new ConcreteWallet(scoreUnit);
        Player myPlayer=new Player("PlayerName",health,scoreUnit,wallet);
        Game game=new ConcreteGame(myPlayer,board,new ArrayList<ButtonWrapper>());
        ButtonWrapper wrap=new ButtonWrapper("wave",e->story.startNextEvent(),new NullGoal());
        //ButtonWrapper wrap=new ButtonWrapper("wave",e->story.startNextEvent(),new NoCurrentEventGoal());
        game.addButton(wrap);
        View view = new ViewConcrete2(game,Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
        
        
        HealthDepletionGoal healthy=new HealthDepletionGoal(myPlayer);
        List<Goal> list=new ArrayList<Goal>();
        list.add(healthy);
        ScoreGoal score=new ScoreGoal(myPlayer,200);
        List<Goal> list2=new ArrayList<Goal>();
        list2.add(score);
        List<Goal> list3=new ArrayList<Goal>();
        ScoreGoal score2=new ScoreGoal(myPlayer,300);
        list3.add(score2);
       
        
        board.addLevel(new ConcreteLevel("images/Park_Path.png",list2,list,world, story));
        board.addLevel(new ConcreteLevel("images/example_path.jpeg",list3,list,new BasicWorld(),story));
        //pane.setCenter(view.initializeView());
        
        //view.addButton(addWavesButtonTest(event,myPlayer), 0, 0);
        
        root.getChildren().add(view.initializeView());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
}
    
}
