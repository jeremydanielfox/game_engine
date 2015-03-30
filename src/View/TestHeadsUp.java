package View;

import game.Player;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TestHeadsUp extends Application{

//    public static void main(String[] args){
//        
//        Stage theStage = new Stage();
//        Group newGroup = new Group();
//        Scene theScene = new Scene(newGroup);
//        
//        Player me = new Player("Sierra", 100,500, 0);
//        HeadsUpDisplay display = new HeadsUpDisplay(me);
//        newGroup.getChildren().add(display.makeDisplay());
//        theStage.setScene(theScene);
//        theStage.show();
//    }
    
    @Override
    public void start(Stage primaryStage) {
//            try {
//                    BorderPane root = new BorderPane();
//                    Scene scene = new Scene(root,400,400);
//                    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//                    primaryStage.setScene(scene);
//                    primaryStage.show();
//            } catch(Exception e) {
//                    e.printStackTrace();
//            }
        
//      Stage theStage = new Stage();
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
