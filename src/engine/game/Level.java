package engine.game;

import gameworld.GameWorld;

public interface Level {
	public boolean isWon();

	public boolean isLost();
	
	public String getLevelBackground();
	
	public void update();
	
	public GameWorld getGameWorld();
}
