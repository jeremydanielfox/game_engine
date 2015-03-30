package game;

import java.util.LinkedList;
import events.Event;

public class StoryBoard {
    private LinkedList<Event> eventList;
    
    public StoryBoard(Event...events) {
        eventList = new LinkedList<Event>();
        addEvent(events);
    }
    
    public boolean addEvent(Event...events) {
        for(Event event : events) {
            eventList.add(event);
        }
        return true;
    }
    
    public Event getNextEvent() {
        return eventList.poll();
    }
    
    
}
