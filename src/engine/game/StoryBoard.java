package engine.game;

import java.util.ArrayList;
import java.util.List;
import engine.events.Event;
import engine.events.TimedEvent;

/**
 * Manages the event progression for a level
 * 
 * @author Tom Puglisi
 *
 */
public class StoryBoard {
    private List<Event> eventList;

    public StoryBoard (Event ... events) {
        eventList = new ArrayList<Event>();
        addEvent(events);
    }

    public boolean addEvent (Event ... events) {
        for (Event event : events) {
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
        updateEvent(eventList.get(0));
        return true;
    }
    
    /**
     * If an event is complete, remove it from the list
     * @param event
     */
    public void updateEvent(Event event) {
        if (!event.update()) {
            eventList.remove(event);
        }
    }

}
