package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MonthlySpendingTest {

    MonthlySpending monthlySpending;

    @BeforeEach
    void runBefore() {
        monthlySpending = new MonthlySpending();
    }

    @Test
    void testConstructor() {
        assertEquals(0, monthlySpending.getGeneralSpending());
        assertEquals(0, monthlySpending.getTravelSpending());
        assertEquals(0, monthlySpending.getGrocerySpending());
        assertEquals(0, monthlySpending.getRestaurantSpending());
        assertEquals(0, monthlySpending.getGasSpending());
        assertEquals(0, monthlySpending.getDrugStoreSpending());
        assertEquals(0, monthlySpending.getTransitSpending());
        assertEquals(0, monthlySpending.getEntertainmentSpending());
        assertEquals(0, monthlySpending.getRecurringSpending());
    }

    @Test
    void testSetters() {
        monthlySpending.setGeneralSpending(1000);
        monthlySpending.setTravelSpending(100);
        monthlySpending.setGrocerySpending(500);
        monthlySpending.setRestaurantSpending(200);
        monthlySpending.setGasSpending(75);
        monthlySpending.setDrugStoreSpending(50);
        monthlySpending.setTransitSpending(46);
        monthlySpending.setEntertainmentSpending(60);
        monthlySpending.setRecurringSpending(150);
        assertEquals(1000, monthlySpending.getGeneralSpending());
        assertEquals(100, monthlySpending.getTravelSpending());
        assertEquals(500, monthlySpending.getGrocerySpending());
        assertEquals(200, monthlySpending.getRestaurantSpending());
        assertEquals(75, monthlySpending.getGasSpending());
        assertEquals(50, monthlySpending.getDrugStoreSpending());
        assertEquals(46, monthlySpending.getTransitSpending());
        assertEquals(60, monthlySpending.getEntertainmentSpending());
        assertEquals(150, monthlySpending.getRecurringSpending());
    }

}
