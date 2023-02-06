package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ListOfRewardTypesTest {

    ListOfRewardTypes lort;

    @BeforeEach
    void runBefore() {
        lort = new ListOfRewardTypes();
    }

    @Test
    void testConstructor() {
        assertEquals(7, lort.getSize());
    }

    @Test
    void testAddRewardType() {
        RewardType testReward = new RewardType("Test Reward", 2);
        assertTrue(lort.addRewardType(testReward));
        assertEquals(8, lort.getSize());
        assertEquals(testReward, lort.getRewardType("Test Reward"));
    }

    @Test
    void testAddRewardTypeAlreadyInList() {
        RewardType aeroplan = new RewardType("Aeroplan", 2.1);
        assertFalse(lort.addRewardType(aeroplan));
        assertEquals(7, lort.getSize());
    }

    @Test
    void testRemoveRewardType() {
        assertTrue(lort.removeRewardType("Cashback"));
        assertEquals(6, lort.getSize());
    }

    @Test
    void testRemoveMultipleRewardTypes() {
        assertTrue(lort.removeRewardType("Aventura"));
        assertTrue(lort.removeRewardType("AMEX Rewards"));
        assertEquals(5, lort.getSize());
    }

    @Test
    void testRemoveRewardTypeMultipleTimes() {
        assertTrue(lort.removeRewardType("Aeroplan"));
        assertEquals(6, lort.getSize());
        assertFalse(lort.removeRewardType("Aeroplan"));
        assertEquals(6, lort.getSize());
    }

    @Test
    void testRemoveRewardTypeNotInList() {
        assertFalse(lort.removeRewardType("Avion Rewards"));
        assertEquals(7, lort.getSize());
    }

    @Test
    void testContainsRewardInList() {
        assertTrue(lort.containsReward("Aeroplan"));
    }

    @Test
    void testContainsRewardNotInList() {
        assertFalse(lort.containsReward("Avion Rewards"));
    }

}
