package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CreditCardOptimizerTest {

    ListOfCreditCards locc;
    ListOfRewardTypes lort;
    MonthlySpending spend;
    CreditCardOptimizer optimizer;

    @BeforeEach
    void runBefore() {
        locc = new ListOfCreditCards();
        lort = new ListOfRewardTypes();
        spend = new MonthlySpending(1000, 100, 500, 200, 100,
                50, 50, 50, 200);
        optimizer = new CreditCardOptimizer();
    }

    @Test
    void testConstructor() {
        assertEquals(0, optimizer.getMaxRewards());
        assertEquals("N/A", optimizer.getOptimalCard());
    }

    @Test
    void testCalculateRewards() {
        CreditCard card = locc.getCreditCard(0);
        String rewardName = card.getRewardName();
        RewardType reward = lort.getRewardType(rewardName);
        double rewardValue = reward.getRewardValue();
        double result = optimizer.calculateRewards(card, rewardValue, spend);
        assertEquals(160.25, result);
    }

    @Test
    void testCalculateMaxRewards() {
        optimizer.calculateMaxRewards(locc, lort, spend);
        assertEquals(516.2, optimizer.getMaxRewards());
        assertEquals("CIBC Aeroplan Visa Infinite", optimizer.getOptimalCard());
    }

    @Test
    void testCalculateMaxRewardsNoSpend() {
        spend = new MonthlySpending(0, 0, 0, 0, 0,
                0, 0, 0, 0);
        optimizer.calculateMaxRewards(locc, lort, spend);
        assertEquals(0, optimizer.getMaxRewards());
        assertEquals("N/A", optimizer.getOptimalCard());
    }

    @Test
    void testCalculateMaxRewardsLowSpend() {
        spend = new MonthlySpending(100, 0, 200, 50, 25,
                30, 60, 20, 80);
        optimizer.calculateMaxRewards(locc, lort, spend);
        assertEquals(114.1056, optimizer.getMaxRewards());
        assertEquals("CIBC Aeroplan Visa", optimizer.getOptimalCard());
    }

    @Test
    void testCalculateMaxRewardsHighSpend() {
        spend = new MonthlySpending(500, 750, 1000, 2000, 400,
                200, 0, 400, 300);
        optimizer.calculateMaxRewards(locc, lort, spend);
        assertEquals(2184.12, optimizer.getMaxRewards());
        assertEquals("AMEX Cobalt", optimizer.getOptimalCard());
    }
}
