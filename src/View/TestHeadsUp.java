package View;

import engine.game.Player;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class TestHeadsUp extends Application{
    
    @Override
    public void start(Stage primaryStage) {
      Group newGroup = new Group();
      Scene theScene = new Scene(newGroup);
      
      Player me = new Player("Sierra", 100,500, 0);
      HeadsUpDisplay display = new HeadsUpDisplay(me);
      newGroup.getChildren().add(display.makeDisplay());
      Button press = new Button("press me");
      press.setOnAction(e -> doSomething(me));
      newGroup.getChildren().add(press);
      primaryStage.setScene(theScene);
      primaryStage.show();
    }
    
    public void doSomething(Player player){
        player.changeCurrency(100);
    }
    
    public static void main(String[] args) {
            launch(args);
    }
    
}
