package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Tests for methods in the ListOfRewardTypes class
public class ListOfRewardTypesTest {

    ListOfRewardTypes listOfRewardTypes;

    @BeforeEach
    void runBefore() {
        listOfRewardTypes = new ListOfRewardTypes(true);
    }

    @Test
    void testConstructorNoDefaultsLoaded() {
        listOfRewardTypes = new ListOfRewardTypes(false);
        assertEquals(0, listOfRewardTypes.getListOfRewardTypes().size());
    }

    @Test
    void testConstructorDefaultsLoaded() {
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
        RewardType testReward1 = new RewardType("Test Reward 1", 2);
        RewardType testReward2 = new RewardType("Test Reward 2", 0.7);
        assertTrue(listOfRewardTypes.addRewardType(testReward1));
        assertEquals(8, listOfRewardTypes.getListOfRewardTypes().size());
        assertEquals(testReward1, listOfRewardTypes.getRewardType("Test Reward 1"));
        assertTrue(listOfRewardTypes.addRewardType(testReward2));
        assertEquals(9, listOfRewardTypes.getListOfRewardTypes().size());
        assertEquals(testReward2, listOfRewardTypes.getRewardType("Test Reward 2"));
    }

    @Test
    void testAddMultipleRewardTypes() {
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

    @Test
    void testToJson() {
        listOfRewardTypes = new ListOfRewardTypes(false);
        RewardType testReward1 = new RewardType("Test Reward 1", 2);
        RewardType testReward2 = new RewardType("Test Reward 2", 0.7);
        assertTrue(listOfRewardTypes.addRewardType(testReward1));
        assertTrue(listOfRewardTypes.addRewardType(testReward2));

        JSONObject json = listOfRewardTypes.toJson();
        JSONArray jsonArray = json.getJSONArray("reward types");
        assertEquals(2, jsonArray.length());
    }

}
