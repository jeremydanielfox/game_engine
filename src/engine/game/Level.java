package engine.game;

import java.util.List;
import View.Displayable;
import gameworld.GameWorld;

public interface Level {
	public boolean isWon();

	public boolean isLost();
	
	public String getLevelBackground();
	
	public void update();
	
	public GameWorld getGameWorld();
	
	public List<Displayable> getDisplayables();
	
	public void addTimer(Timer t);
}
