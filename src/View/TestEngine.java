package View;

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
        View view = new ConcreteView(new ConcreteGame(new Player("Boi",0,0,0),board),root);
        board.addLevel(new ConcreteLevel("images/Park_Path.png"));
        view.initializeGameWorld(null);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
}
    
}
