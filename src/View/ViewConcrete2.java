package View;

import java.util.Observable;
import java.util.Observer;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import engine.game.Game;
import engine.game.LevelBoard;



public class ViewConcrete2 implements View, Observer {

    public static final double GAME_WIDTH_TO_HEIGHT = 1.25;
    
    private Game myGame;
    private Integer myRate=200;
    private Timeline myAnimation;
    private LevelBoard myLevelBoard;
    
    
    //old way
    private Pane myGameWorldPane;
    //private Group myTotalGroup;
    
    //new way 
    private BorderPane myPane;
    
    private double myDisplayWidth;
    private double myDisplayHeight;
    
    public ViewConcrete2(Game game, double width,double height) {
        myGame=game;
        myLevelBoard=myGame.getLevelBoard();
        myLevelBoard.addObserver(this);
        
        //myTotalGroup=group;
        
        myDisplayWidth=width;
        myDisplayHeight=height;
    }
    
    @Override
    public Node initializeView(){
        myPane = new BorderPane();
        myGameWorldPane=new Pane();
        myGameWorldPane.setMaxWidth(myDisplayHeight*GAME_WIDTH_TO_HEIGHT);
        myPane.setCenter(myGameWorldPane);
        initializeGameWorld();
        return myPane;
    }
    
    @Override
    public void initializeGameWorld () {
        setCurrentBackground();
        HeadsUpDisplay headsUp=new HeadsUpDisplay(myGame.getPlayer());
        VBox vbox=headsUp.makeDisplay();
        //vbox.setMinWidth(myDisplayWidth-myDisplayHeight);
        //vbox.setLayoutY(vbox.getLayoutX()+500);
        myPane.setRight(vbox);
        buildTimeline();
        //for testing purposes:
        PopUpScreen popup=new PopUpScreen();
        popup.makeScreen("Begin Level 1", "Start"); // these should be from resource files
        Button btn=new Button("Dec");
        btn.setOnAction(e->myGame.getPlayer().changeHealth(-100));
        Button btn2=new Button("Inc");
        btn2.setTranslateX(btn2.getLayoutX()+200);
        btn2.setOnAction(e->myGame.getPlayer().increaseScore(100));//.changeScore(100));
        //myTotalGroup.getChildren().addAll(btn,vbox,btn2);
        vbox.getChildren().addAll(btn, btn2);
        play();
    }

    @Override
    public void buildTimeline () {
        KeyFrame frame = makeKeyFrame(60);
        myAnimation = new Timeline();
        myAnimation.setCycleCount(Animation.INDEFINITE);
        myAnimation.getKeyFrames().add(frame);
    }
    
    private KeyFrame makeKeyFrame(int frameRate) {
        return new KeyFrame(Duration.millis(myRate / frameRate),
                            e->executeFrameActions());
    }

    @Override
    public void executeFrameActions () {
        //after updating game, how to update after level ends? need to look into checking something like gameEnded()
        myGame.update();
        //System.out.println("Game loop running");
    }

    @Override
    public void displayShop () {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void update (Observable o, Object arg) {
        System.out.println("in update method");
        if (myLevelBoard.equals(o)) {
            pause();
            System.out.println("calling game over check");
            if (myLevelBoard.gameOver()) {
                //note: display game over screen
                PopUpScreen gameOver=new PopUpScreen();
                gameOver.makeScreen("GAME OVER", "Play Again");
                //ideally gamePlayer/observers should be notified here
                
            }
            else {
                myLevelBoard.startNextLevel();
                //display new background
                //display new sprites
                //popup window
                //then after closing popup window, play();
                setCurrentBackground();
                //display new sprites
                play();
            }
        }
    }

    private void setCurrentBackground(){
        ImageView image = new ImageView(myLevelBoard.getCurrentLevelMap());
        image.setFitHeight(myDisplayHeight);
        image.setFitWidth(myDisplayHeight*GAME_WIDTH_TO_HEIGHT);
        myGameWorldPane.getChildren().clear();
        myGameWorldPane.getChildren().add(image);
    }
    
    public void test() {
        pause();
        myLevelBoard.startNextLevel();
        //display new background
        //display new sprites
        //popup window
        //then after closing popup window, play();
        ImageView image = new ImageView(myLevelBoard.getCurrentLevelMap());
        //image.setPreserveRatio(true);
        image.setFitHeight(myDisplayHeight);
        image.setFitWidth(myDisplayHeight);
        myGameWorldPane.getChildren().clear();
        myGameWorldPane.getChildren().add(image);
        //display new sprites
        play();
    }
    
    @Override
    public void pause () {
        myAnimation.pause();
    }

    @Override
    public void play () {
        myAnimation.play();
    }

}
