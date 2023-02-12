package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Tests for methods in the ListOfRewardTypes class
public class ListOfRewardTypesTest {

    ListOfRewardTypes listOfRewardTypes;

    @BeforeEach
    void runBefore() {
        listOfRewardTypes = new ListOfRewardTypes();
    }

    @Test
    void testConstructor() {
        assertEquals(7, listOfRewardTypes.getListOfRewardTypes().size());
    }

    @Test
    void testGetRewardTypeNotInList() {
        assertNull(listOfRewardTypes.getRewardType("Fake Reward"));
    }

    @Test
    void testContainsRewardInList() {
        assertTrue(listOfRewardTypes.containsReward("Aeroplan"));
    }

    @Test
    void testContainsRewardNotInList() {
        assertFalse(listOfRewardTypes.containsReward("Avion Rewards"));
    }

    @Test
    void testAddRewardType() {
        RewardType testReward = new RewardType("Test Reward", 2);
        assertTrue(listOfRewardTypes.addRewardType(testReward));
        assertEquals(8, listOfRewardTypes.getListOfRewardTypes().size());
        assertEquals(testReward, listOfRewardTypes.getRewardType("Test Reward"));
    }

    @Test
    void testAddRewardTypeAlreadyInList() {
        RewardType aeroplan = new RewardType("Aeroplan", 2.1);
        assertFalse(listOfRewardTypes.addRewardType(aeroplan));
        assertEquals(7, listOfRewardTypes.getListOfRewardTypes().size());
    }

    @Test
    void testRemoveRewardType() {
        assertTrue(listOfRewardTypes.removeRewardType("Cashback"));
        assertEquals(6, listOfRewardTypes.getListOfRewardTypes().size());
    }

    @Test
    void testRemoveMultipleRewardTypes() {
        assertTrue(listOfRewardTypes.removeRewardType("Aventura"));
        assertTrue(listOfRewardTypes.removeRewardType("AMEX Rewards"));
        assertEquals(5, listOfRewardTypes.getListOfRewardTypes().size());
    }

    @Test
    void testRemoveRewardTypeMultipleTimes() {
        assertTrue(listOfRewardTypes.removeRewardType("Aeroplan"));
        assertEquals(6, listOfRewardTypes.getListOfRewardTypes().size());
        assertFalse(listOfRewardTypes.removeRewardType("Aeroplan"));
        assertEquals(6, listOfRewardTypes.getListOfRewardTypes().size());
    }

    @Test
    void testRemoveRewardTypeNotInList() {
        assertFalse(listOfRewardTypes.removeRewardType("Avion Rewards"));
        assertEquals(7, listOfRewardTypes.getListOfRewardTypes().size());
    }

}
