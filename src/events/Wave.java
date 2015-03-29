package events;

import java.util.HashMap;
import java.util.Map;

/**
 * A wave encapsulates a sequence of events. Uses composite pattern.
 * @author myungoh
 *
 */
public class Wave implements Event{
	private HashMap<Event, Integer> eventMap;
	private int timePassed;
	public Wave(Map<Event, Integer> eventMap){
		this.eventMap = new HashMap<>(eventMap);
	}

/**
 * This is one iteration of the loop. Every unit of time, this method would run
 * and the "timePassed" would be updated. Using this "timePassed", the correct Event
 * would be executed.
 */
	public void execute(){
		for (Event e : eventMap.keySet()){
			if (eventMap.get(e) == timePassed)
				e.execute();
		}
		keepTime();
	}
	
	private void keepTime(){
		timePassed++;
	}
}
