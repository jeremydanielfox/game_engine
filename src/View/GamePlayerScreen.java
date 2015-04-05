package View;

import engine.game.ConcreteGame;
import engine.game.ConcreteLevelBoard;
import engine.game.Game;
import engine.game.Player;
import gae.gameView.Main;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GamePlayerScreen extends Application {

    private VBox myVbox;
    private Game myGame;
    
    public GamePlayerScreen() {
        myGame=new ConcreteGame(new Player("Eric",null,null,null),new ConcreteLevelBoard());
        myVbox=new VBox(30);
        makeDis();
    }
    
    private void addDetails(String label,String text) {
        VBox insideBox=new VBox(10);
        Label labelText=new Label(label);
        
        Text description=new Text(text);
        insideBox.getChildren().addAll(labelText,description);
        insideBox.setAlignment(Pos.CENTER);
        myVbox.getChildren().addAll(insideBox);
    }
    
    public void makeDis() {
        addDetails("Name","this is an example of a name");
        addDetails("Description","this is an example of a description");
        addDetails("Instructions","this is an example of instructions");
        
        ConcreteView view=new ConcreteView(myGame,new Group(),Main.SCREEN_WIDTH,Main.SCREEN_WIDTH);
        Button scoreBtn=new Button("View high scores");
        Button playBtn=new Button("play");
        playBtn.setOnAction(e-> view.initializeGameWorld());
        myVbox.getChildren().addAll(scoreBtn,playBtn);
    }
    
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start (Stage primaryStage) throws Exception {
        primaryStage.setWidth(Main.SCREEN_WIDTH);
        primaryStage.setHeight(Main.SCREEN_HEIGHT);
        BorderPane pane=new BorderPane();
        pane.setPadding(new Insets(0,40,0,0));
        Scene scene=new Scene(pane);
        myVbox.setAlignment(Pos.CENTER);
        pane.setRight(myVbox);
        VBox veebz=new VBox();
        ImageView image=new ImageView("images/Park_Path.png");
        image.setPreserveRatio(true);
        image.setFitHeight(Main.SCREEN_HEIGHT);
        Label label = new Label("Game Type");
        label.setTextFill(Color.BLUE);
        veebz.getChildren().addAll(image,label);
        veebz.setAlignment(Pos.CENTER);
        pane.setLeft(veebz);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}
