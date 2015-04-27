package gae.levelPreferences;

import engine.game.TimerConcrete;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import gae.gameView.CheckListItem;
import gae.gridView.NumberTextField;
import gae.openingView.UIObject;

public class LevelPreferencesEditor implements UIObject{
    private static final ImageView TIMER_IMAGE = new ImageView("/images/bloonsdefense.jpg");
    private static final ImageView HEALTH_IMAGE = new ImageView("/images/desktopdefense.jpg");
    private static final ImageView SCORE_IMAGE = new ImageView("/images/cartoonwars.jpg");
    private static final Text TIME = new Text("Timed Game");
    private static final Text HEALTH = new Text("Health Losing Condition");
    private static final Text SCORE = new Text("Score Winning Condition");
    private static final int TEXT_BOX_WIDTH=100;
    private static final int NUMBER_TEXT_BOX_WIDTH=50;
    private ScrollPane base;
    private int minutes;
    private int seconds;
    private double healthGoal;
    private double scoreGoal;
 
    
    public LevelPreferencesEditor(){
        base=new ScrollPane();
       // base.getChildren().add(makeTimerOptions());
       GoalHoverPicture timedGame=new GoalHoverPicture(TIMER_IMAGE, TIME, makeTimerOptions());
       GoalHoverPicture health=new GoalHoverPicture(HEALTH_IMAGE, HEALTH, makeHealthOptions());
       GoalHoverPicture score=new GoalHoverPicture(SCORE_IMAGE, SCORE, makeScoreOptions());
       VBox vbox=new VBox(timedGame.getObject(), health.getObject(), score.getObject());
       base.setContent(vbox);
        vbox.setAlignment(Pos.CENTER);
    }
    
    public void makeOptions(){
            
    }

    @Override
    public Node getObject () {
        return base;
    }
  
    
    public Node makeTimerOptions(){
        Label labelprompt = new Label("Timer label:");
        TextField label = new TextField();
        label.setMaxWidth(TEXT_BOX_WIDTH);
        Label timeprompt = new Label("Time:");
        GridPane grid = new GridPane();
        grid.add(labelprompt, 0, 0);
        grid.add(label, 1, 0);
        grid.add(timeprompt, 0, 1);
        grid.add(makeTimeTextField(), 1, 1);
        CheckListItem timer=new TextCheckListItem("Timed Level");
        timer.getCheckBox().selectedProperty().addListener((obs, oldVal, newVal)->{
            
        });
        VBox vbox=new VBox(timer.getNode(), grid);
        return vbox;
    }
 
      
    private Node makeTimeTextField(){
        TextField min=new NumberTextField();
        TextField sec=new NumberTextField();
        min.setMaxWidth(NUMBER_TEXT_BOX_WIDTH);
        sec.setMaxWidth(NUMBER_TEXT_BOX_WIDTH);       
        min.setPromptText(Integer.toString(TimerConcrete.MINUTE_DEFAULT));
        sec.setPromptText(Integer.toString(TimerConcrete.SECOND_DEFAULT));
        Label colon=new Label(":");
        min.setOnAction(e->minutes=Integer.parseInt(min.getText()));
        sec.setOnAction(e->seconds=Integer.parseInt(min.getText()));
        HBox hbox=new HBox(min, colon, sec);
        return hbox;
    }
    
    private Node makeHealthOptions(){
        Label prompt=new Label("Minimum health:");
        TextField textfield=new NumberTextField();
        textfield.setOnAction(e->{
            healthGoal=Double.parseDouble(textfield.getText());
        });
        HBox hbox=new HBox(prompt, textfield);
        return hbox;
    }
    
    private Node makeScoreOptions(){
        Label prompt=new Label("Target Score:");
        TextField textfield=new NumberTextField();
        textfield.setOnAction(e->{
            healthGoal=Double.parseDouble(textfield.getText());
        });
        HBox hbox=new HBox(prompt, textfield);
        return hbox;
    }
    
    
  
}
