package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Tests for methods in the RewardType class
public class RewardTypeTest {

    RewardType testReward1;
    RewardType testReward2;

    @BeforeEach
    void runBefore() {
        testReward1 = new RewardType("My Reward 1", 1.5);
        testReward2 = new RewardType("My Reward 2", 0.67);
    }

    @Test
    void testConstructor() {
        assertEquals("My Reward 1", testReward1.getRewardName());
        assertEquals("My Reward 2", testReward2.getRewardName());
        assertEquals(1.5, testReward1.getRewardValue());
        assertEquals(0.67, testReward2.getRewardValue());
    }

    @Test
    void testSetters() {
        testReward1.setRewardName("Aeroplan");
        assertEquals("Aeroplan", testReward1.getRewardName());

        testReward2.setRewardValue(2.22);
        assertEquals(2.22, testReward2.getRewardValue());
    }

    @Test
    void testToJson() {
        JSONObject json1 = testReward1.toJson();
        JSONObject json2 = testReward2.toJson();
        assertEquals("My Reward 1", json1.getString("reward name"));
        assertEquals(1.5, json1.getDouble("reward value (cpp)"));
        assertEquals("My Reward 2", json2.getString("reward name"));
        assertEquals(0.67, json2.getDouble("reward value (cpp)"));
    }

}
