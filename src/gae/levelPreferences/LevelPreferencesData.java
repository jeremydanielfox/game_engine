package gae.levelPreferences;

import engine.game.Timer;
import engine.game.TimerConcrete;
import engine.goals.Goal;
import engine.goals.HealthGoal;
import engine.goals.ScoreGoal;
import engine.goals.TimerGoal;

public class LevelPreferencesData {
    private int minutes;
    private int seconds;
    private double healthGoal;
    private double scoreGoal;
    private double timeGoal;
    private String timelabel;

    public LevelPreferencesData () {

    }

    public void setMinutes (int minutes) {
        this.minutes = minutes;
    }

    public void setSeconds (int seconds) {
        this.seconds = seconds;
    }

    public void setHealthGoal (double healthGoal) {
        this.healthGoal = healthGoal;
    }

    public void setScoreGoal (double scoreGoal) {
        this.scoreGoal = scoreGoal;
    }

    public void setTimeGoal (double timeGoal) {
        this.timeGoal = timeGoal;
    }

    public void setTimelabel (String timelabel) {
        this.timelabel = timelabel;
    }
    
    public Timer getTimer(){
        Timer timer=new TimerConcrete(minutes, seconds, timelabel);
        return timer;
    }
    
    public Goal getHealthGoal(){
        Goal goal=new HealthGoal();
        ((HealthGoal) goal).setHealthTarget(healthGoal);
        return goal;
    }
    
    public Goal getTimerGoal(Timer timer){
        Goal goal=new TimerGoal(timer, (int) timeGoal);
        return goal;
    }
    
    public Goal getScoreGoal(){
        Goal goal=new ScoreGoal();
        ((ScoreGoal) goal).setScoreTarget((int) scoreGoal);
        return goal;
    }

}
