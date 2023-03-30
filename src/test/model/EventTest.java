package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

// Tests for methods in the Event class
public class EventTest {
    private Event e;
    private Date d;

    //NOTE: these tests might fail if time at which line (2) below is executed
    //is different from time that line (1) is executed.  Lines (1) and (2) must
    //run in same millisecond for this test to make sense and pass.

    @BeforeEach
    public void runBefore() {
        e = new Event("Sensor open at door");   // (1)
        d = Calendar.getInstance().getTime();   // (2)
    }

    @Test
    public void testConstructor() {
        assertEquals("Sensor open at door", e.getDescription());
        assertEquals(d.toInstant().truncatedTo(ChronoUnit.SECONDS),
                e.getDate().toInstant().truncatedTo(ChronoUnit.SECONDS));
    }

    @Test
    public void testToString() {
        assertEquals(d.toString() + "\n" + "Sensor open at door", e.toString());
    }

    @Test
    public void testEqualsAndHashcode() {
        Event e1 = new Event("Event 1");
        Event e2 = new Event("Event 1");
        Event e3 = new Event("Event 3");
        Event e4 = null;
        RewardType reward = new RewardType("Test Reward", 1);
        assertEquals(e1, e2);
        assertEquals(e1.hashCode(), e2.hashCode());
        assertNotEquals(e1, e3);
        assertNotEquals(e2, e3);
        assertNotEquals(e1, e4);
        assertNotEquals(e1, reward);
    }
}
