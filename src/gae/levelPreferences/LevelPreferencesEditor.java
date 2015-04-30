package gae.levelPreferences;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import engine.fieldsetting.Settable;
import engine.game.Level;
import engine.goals.Goal;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import gae.editor.EditingParser;
import gae.gameView.CheckListItem;
import gae.gridView.NumberTextField;
import gae.openingView.UIObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.*;


public class LevelPreferencesEditor implements UIObject {
    private static final String TIMER_IMAGE = "/images/timer.png";
    private static final String HEALTH_IMAGE = "/images/HealthImage.png";
    private static final String SCORE_IMAGE = "/images/trophy.png";
    private static final String TIME = "Timed Game";
    private static final String HEALTH = "Health Losing Condition";
    private static final String SCORE = "Score Winning Condition";
    private static final String DEFAULT_VALUE = "0";
    private static final int TEXT_BOX_WIDTH = 100;
    private static final int NUMBER_TEXT_BOX_WIDTH = 50;
    private ScrollPane base;
    private GoalHoverPicture timedGame;
    private GoalHoverPicture health;
    private GoalHoverPicture score;
    private TextField sec;
    private TextField min;
    private DoubleOption scoreOpt;
    private DoubleOption healthOpt;
    private LevelPreferencesData data;
    private List<Goal> winningConditions;
    private List<Goal> losingConditions;
    private CheckListItem timer;
    private Level myLevel;

    public LevelPreferencesEditor () {
        base = new ScrollPane();
        data = new LevelPreferencesData();
        healthOpt = new DoubleOption("Minimum health:");
//        healthOpt.setAction(e -> data.setHealthGoal(healthOpt.getValue()));
        scoreOpt = new DoubleOption("Winning score:");
//        scoreOpt.setAction(e -> data.setScoreGoal(scoreOpt.getValue()));
        timedGame =
                new GoalHoverPicture(new ImageView(TIMER_IMAGE), new Text(TIME), makeTimerOptions());
        health =
                new GoalHoverPicture(new ImageView(HEALTH_IMAGE), new Text(HEALTH),
                                     healthOpt.getNode());
        score =
                new GoalHoverPicture(new ImageView(SCORE_IMAGE), new Text(SCORE),
                                     scoreOpt.getNode());
        VBox vbox = new VBox(timedGame.getObject(), health.getObject(), score.getObject());
        base.setContent(vbox);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().add(saveButton());

    }

    @Override
    public Node getObject () {
        return base;
    }

    public Node makeTimerOptions () {
        Label labelprompt = new Label("Timer label:");
        TextField label = new TextField();
        label.setOnAction(e -> data.setTimelabel(label.getText()));
        label.setMaxWidth(TEXT_BOX_WIDTH);
        Label timeprompt = new Label("Time:");
        GridPane grid = new GridPane();
        grid.add(labelprompt, 0, 0);
        grid.add(label, 1, 0);
        grid.add(timeprompt, 0, 1);
        grid.add(makeTimeTextField(), 1, 1);
        timer = new TextCheckListItem("Timed losing condition");
        DoubleOption timeGoalOpt = new DoubleOption("Minimum time:");
        timeGoalOpt.setAction(e -> data.setTimeGoal(timeGoalOpt.getValue()));
        timeGoalOpt.getNode().setVisible(false);
        timer.getSelectedProperty().addListener( (obs, oldVal, newVal) -> {
            timeGoalOpt.getNode().setVisible(newVal);
        });
        VBox vbox = new VBox(grid, timer.getNode(), timeGoalOpt.getNode());
        return vbox;
    }

    private Node makeTimeTextField () {
        min = new NumberTextField();
        sec = new NumberTextField();
        min.setMaxWidth(NUMBER_TEXT_BOX_WIDTH);
        sec.setMaxWidth(NUMBER_TEXT_BOX_WIDTH);
        min.setPromptText("min");
        sec.setPromptText("sec");
        Label colon = new Label(":");
        min.setOnAction(e -> data.setMinutes(Integer.parseInt(min.getText())));
        sec.setOnAction(e -> data.setSeconds(Integer.parseInt(sec.getText())));
        HBox hbox = new HBox(min, colon, sec);
        return hbox;
    }

    private static class DoubleOption {
        private Node node;
        private StringProperty val = new SimpleStringProperty();
        private TextField textfield;

        public DoubleOption (String label) {
            Label prompt = new Label(label);
            textfield = new NumberTextField();
            val.bind(textfield.textProperty());
            textfield.setText(DEFAULT_VALUE);
            node = new HBox(prompt, textfield);
        }

        public double getValue () {
            return Double.parseDouble(val.getValue());
        }

        public void setAction (EventHandler<ActionEvent> event) {
            textfield.setOnAction(event);
        }

        public Node getNode () {
            return node;
        }
    }

    private Button saveButton () {
        Button button = new Button("Save");
        button.setOnAction(e -> {
            List<Goal> winlist = new ArrayList<>();
            if (score.isSelected()) {
                data.setScoreGoal(scoreOpt.getValue());
                winlist.add(data.getScoreGoal());
            }
            else if (timedGame.isSelected() && !timer.getSelectedProperty().get()) {
                data.setMinutes(Integer.parseInt(min.getText()));
                data.setSeconds(Integer.parseInt(sec.getText()));
                winlist.add(data.getTimerGoal());
                myLevel.addTimer(data.getTimer());
            }
            winningConditions = winlist;

            List<Goal> loselist = new ArrayList<>();
            
            if (health.isSelected()) {
                data.setHealthGoal(healthOpt.getValue());
                loselist.add(data.getHealthGoal());
            }
            else if (timedGame.isSelected() && timer.getSelectedProperty().get()) {
                data.setMinutes(Integer.parseInt(min.getText()));
                data.setSeconds(Integer.parseInt(sec.getText()));
                loselist.add(data.getTimerGoal());
                myLevel.addTimer(data.getTimer());
            }
            losingConditions = loselist;
            
            setLevelGoals();
        });
        return button;
    }

    private void setLevelGoals () {
        try {
            for (Method m : EditingParser.getMethodsWithAnnotation(Class.forName(myLevel
                    .getClass()
                    .getName()), Settable.class)) {
                if (m.getName().equals("setWinningGoals")) {
                    m.invoke(myLevel, winningConditions);
                }
                else if (m.getName().equals("setLosingGoals")) {
                    m.invoke(myLevel, losingConditions);
                }
            }
        }
        catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void setLevel (Level levelData) {
        myLevel = levelData;
    }
}
