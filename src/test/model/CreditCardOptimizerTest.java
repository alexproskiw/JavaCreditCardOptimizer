package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Tests for methods in the CreditCardOptimizer class
public class CreditCardOptimizerTest {

    ListOfCreditCards listOfCreditCards;
    ListOfRewardTypes listOfRewardTypes;
    MonthlySpending monthlySpending;
    CreditCardOptimizer optimizer;

    @BeforeEach
    void runBefore() {
        listOfCreditCards = new ListOfCreditCards(true);
        listOfRewardTypes = new ListOfRewardTypes(true);
        monthlySpending = new MonthlySpending();
        optimizer = new CreditCardOptimizer();
    }

    @Test
    void testConstructor() {
        assertEquals(0, optimizer.getMaxRewards());
        assertEquals("N/A", optimizer.getOptimalCard());
    }

    @Test
    void testCalculateRewards() {
        updateMonthlySpending(1000, 100, 500, 200, 100,
                50, 50, 50, 200);
        CreditCard card = listOfCreditCards.getListOfCreditCards().get(0);
        String rewardName = card.getRewardName();
        RewardType reward = listOfRewardTypes.getRewardType(rewardName);
        double rewardValue = reward.getRewardValue();
        double result = optimizer.calculateRewards(card, rewardValue, monthlySpending);
        assertEquals(160.25, result);
    }

    @Test
    void testCalculateMaxRewardsNoSpend() {
        optimizer.calculateMaxRewards(listOfCreditCards, listOfRewardTypes, monthlySpending);
        assertEquals(0, optimizer.getMaxRewards());
        assertEquals("N/A", optimizer.getOptimalCard());
    }

    @Test
    void testCalculateRewardsNoCardsOrRewardTypes() {
        listOfCreditCards = new ListOfCreditCards(false);
        listOfRewardTypes = new ListOfRewardTypes(false);
        updateMonthlySpending(1000, 100, 500, 200, 100,
                50, 50, 50, 200);
        optimizer.calculateMaxRewards(listOfCreditCards, listOfRewardTypes, monthlySpending);
        assertEquals(0, optimizer.getMaxRewards());
        assertEquals("N/A", optimizer.getOptimalCard());
    }

    @Test
    void testCalculateMaxRewardsLowSpend() {
        updateMonthlySpending(100, 0, 200, 50, 25,
                30, 60, 20, 80);
        optimizer.calculateMaxRewards(listOfCreditCards, listOfRewardTypes, monthlySpending);
        assertEquals(114.1056, optimizer.getMaxRewards());
        assertEquals("CIBC Aeroplan Visa", optimizer.getOptimalCard());
    }

    @Test
    void testCalculateMaxRewardsAverageSpend() {
        updateMonthlySpending(1000, 100, 500, 200, 100,
                50, 50, 50, 200);
        optimizer.calculateMaxRewards(listOfCreditCards, listOfRewardTypes, monthlySpending);
        assertEquals(516.2, optimizer.getMaxRewards());
        assertEquals("CIBC Aeroplan Visa Infinite", optimizer.getOptimalCard());
    }

    @Test
    void testCalculateMaxRewardsHighSpend() {
        updateMonthlySpending(500, 750, 1000, 2000, 400,
                200, 0, 400, 300);
        optimizer.calculateMaxRewards(listOfCreditCards, listOfRewardTypes, monthlySpending);
        assertEquals(2184.12, optimizer.getMaxRewards());
        assertEquals("AMEX Cobalt", optimizer.getOptimalCard());
    }

    private void updateMonthlySpending(double general, double travel, double grocery, double restaurant, double gas,
                                       double drugStore, double transit, double entertainment, double recurring) {
        monthlySpending.setGeneralSpending(general);
        monthlySpending.setTravelSpending(travel);
        monthlySpending.setGrocerySpending(grocery);
        monthlySpending.setRestaurantSpending(restaurant);
        monthlySpending.setGasSpending(gas);
        monthlySpending.setDrugStoreSpending(drugStore);
        monthlySpending.setTransitSpending(transit);
        monthlySpending.setEntertainmentSpending(entertainment);
        monthlySpending.setRecurringSpending(recurring);
    }
}
