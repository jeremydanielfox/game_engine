package engine.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import engine.events.Event;
import engine.fieldsetting.Settable;
import engine.fieldsetting.Triggerable;


/**
 * Manages the event progression for a level
 * 
 * @author Tom Puglisi
 * @author Sierra Smith
 * @author Cosette Goldstein
 *
 */
@Settable
public class StoryBoard extends Observable {
    private List<Event> eventList;

    public StoryBoard (Event ... events) {
        eventList = new ArrayList<Event>();
        addEvent(events);
        System.out.println(eventList.size());
    }

    public boolean addEvent (Event ... events) {
        for (Event event : events) {
            eventList.add(event);
        }
        return true;
    }

    /**
     * Update all events
     * 
     * @return false if the StoryBoard has no more events to update
     */
    public boolean update () {
        if (eventList.size() == 0) {
            return false;
        }
        updateEvent(getCurrentEvent());
        return true;
    }

    /**
     * If an event is complete, remove it from the list
     * 
     * @param event
     */
    private void updateEvent (Event event) {
        if (!event.update()) {
            eventList.remove(event);
            setChanged();
            notifyObservers();
        }
    }

    /**
     * 
     * @return the next event in the List, if there is one
     */
    private Event getCurrentEvent () {
        if (eventList.size() > 0) {
            return eventList.get(0);
        }
        return null;
    }

    /**
     * Sets the current event's start conditions to true, but only if they are not already true
     */
    @Triggerable
    public void startNextEvent () {
        Event currentEvent = getCurrentEvent();
        if (currentEvent != null && !currentEvent.canStart()) {
            currentEvent.setCanStart();
        }
    }

    @Settable
    public void setEvents (List<Event> events) {
        eventList = events;
    }

    /**
     * Returns true if the current event (which is being updated) can start. Assumes that if the
     * current event's canStart() method evaluates to true, that it has started because we are
     * calling update on it.
     */
    public boolean eventInProgress () {
        if (getCurrentEvent() != null) {
            return getCurrentEvent().canStart();
        }
        return false;
    }

    /**
     * Returns the number of events left in the storyboard.
     * 
     * @return no. of events left in storyboard
     */
    public int currentEventCount () {
        return eventList.size();
    }

}
