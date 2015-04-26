package gameworld;

import engine.fieldsetting.Settable;

public class TerrainTile {
	private boolean canWalk = true, canPlace = true;
	
	@Settable
	public void setWalk(boolean b){
		canWalk = b;
	}
	
	@Settable
	public void setPlace(boolean b){
		canPlace = b;
	}
	
	public boolean getWalk(){ 
		return canWalk;
	}
	public boolean getPlace(){
		return canPlace;
	}
	
}
