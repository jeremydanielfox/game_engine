package usecases;

import java.util.ArrayList;
import java.util.List;

public class StoryBoard {
	private ArrayList<Event> myEvents;
	private int count=0;
	public StoryBoard(List<Event> eventList){
		myEvents = new ArrayList<Event>(eventList);
	}
	
	public void executeNext(){
		if(count<myEvents.size()){
			myEvents.get(count).execute();
			count++;
		}
	}
}
