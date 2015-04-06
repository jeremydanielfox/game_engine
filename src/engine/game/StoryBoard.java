package engine.game;

import java.util.ArrayList;
import java.util.List;

import engine.events.TimedEvent;

/**
 * Manages the event progression for a level
 * 
 * @author Tom Puglisi
 *
 */
public class StoryBoard {
    private List<TimedEvent> eventList;

    public StoryBoard (TimedEvent ... events) {
        eventList = new ArrayList<TimedEvent>();
        addEvent(events);
    }

    public boolean addEvent (TimedEvent ... events) {
        for (TimedEvent event : events) {
            eventList.add(event);
        }
        return true;
    }

    /**
     * Update all events
     * @return false if the StoryBoard has no more events to update
     */
    public boolean update () {
        if (eventList.size() == 0) {
            return false;
        }
        eventList.stream().forEach(event -> updateEvent(event));
        return true;
    }
    
    /**
     * If an event is complete, remove it from the list
     * @param event
     */
    public void updateEvent(TimedEvent event) {
        if (!event.update()) {
            eventList.remove(event);
        }
    }

}
