package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MonthlySpendingTest {

    MonthlySpending spend;

    @BeforeEach
    void runBefore() {
        spend = new MonthlySpending(1000, 100, 500, 200, 75,
                50, 46, 60, 150);
    }

    @Test
    void testConstructor() {
        assertEquals(1000, spend.getGeneralSpending());
        assertEquals(100, spend.getTravelSpending());
        assertEquals(500, spend.getGrocerySpending());
        assertEquals(200, spend.getRestaurantSpending());
        assertEquals(75, spend.getGasSpending());
        assertEquals(50, spend.getDrugStoreSpending());
        assertEquals(46, spend.getTransitSpending());
        assertEquals(60, spend.getEntertainmentSpending());
        assertEquals(150, spend.getRecurringSpending());
    }

}
