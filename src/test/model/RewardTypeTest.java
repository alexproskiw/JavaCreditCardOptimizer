package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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

}
