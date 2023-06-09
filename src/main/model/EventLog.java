package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

// Represents a log of events, using the Singleton Design Pattern to ensure that there is only one EventLog at a time
public class EventLog implements Iterable<Event> {
	/** the only EventLog in the system (Singleton Design Pattern) */
    private static EventLog theLog;
    private Collection<Event> events;
	

	// Effects: Creates a new EventLog, preventing external construction (Singleton Design Pattern)
    private EventLog() {
        events = new ArrayList<Event>();
    }

    // Modifies: this
    // Effects: Gets instance of EventLog - creates it if it doesn't already exist (Singleton Design Pattern)
    public static EventLog getInstance() {
        if (theLog == null) {
            theLog = new EventLog();
        }
        return theLog;
    }

    // Modifies: this
    // Effects: Adds an event to the event log
    public void logEvent(Event e) {
        events.add(e);
    }

    // Modifies: this
    // Effects: Clears the event log and logs the event
    public void clear() {
        events.clear();
        logEvent(new Event("Event log cleared."));
    }

    @Override
    public Iterator<Event> iterator() {
        return events.iterator();
    }
}
