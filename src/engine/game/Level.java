package engine.game;

public interface Level {
	public boolean isWon();

	public boolean isLost();
	
	public String getLevelBackground();
	
	public void update();
}
