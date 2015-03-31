package View;

import java.util.ArrayList;
import java.util.List;
import engine.goals.Goal;
import engine.goals.HealthDepletionGoal;
import game.ConcreteGame;
import game.ConcreteLevel;
import game.ConcreteLevelBoard;
import game.Player;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestEngine extends Application {

    @Override
    public void start (Stage primaryStage) throws Exception {
        ConcreteLevelBoard board=new ConcreteLevelBoard();
        Group root=new Group();
        primaryStage.setHeight(600); // needs to account for scaling; add constants
        primaryStage.setWidth(600);// needs to account for scaling; add constants
        Scene scene=new Scene(root);
        Player myPlayer=new Player("Boi",100,100,100);
        View view = new ConcreteView(new ConcreteGame(myPlayer,board),root);
        HealthDepletionGoal healthy=new HealthDepletionGoal(myPlayer);
        List<Goal> list=new ArrayList<Goal>();
        list.add(healthy);
        board.addLevel(new ConcreteLevel("images/Park_Path.png",list,list));
        board.addLevel(new ConcreteLevel("images/example_path.jpeg",list,list));
        view.initializeGameWorld(null);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
}
    
}
