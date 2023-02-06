package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CreditCardTest {

    CreditCard cc;

    @BeforeEach
    void runBefore() {
        cc = new CreditCard("Test Credit Card", "Cashback", 100,
                1.1, 1.3, 1.5, 0.9, 1, 0.7,
                2, 0.5, 0.8);
    }

    @Test
    void testConstructor() {
        assertEquals("Test Credit Card", cc.getName());
        assertEquals("Cashback", cc.getRewardName());
        assertEquals(100, cc.getAnnualFee());
        assertEquals(1.1, cc.getGeneralRewards());
        assertEquals(1.3, cc.getTravelRewards());
        assertEquals(1.5, cc.getGroceryRewards());
        assertEquals(0.9, cc.getRestaurantRewards());
        assertEquals(1, cc.getGasRewards());
        assertEquals(0.7, cc.getDrugStoreRewards());
        assertEquals(2, cc.getTransitRewards());
        assertEquals(0.5, cc.getEntertainmentRewards());
        assertEquals(0.8, cc.getRecurringRewards());
    }

    @Test
    void testSetters() {
        cc.setName("My Card");
        cc.setRewardName("Aeroplan");
        cc.setAnnualFee(999);
        cc.setGeneralRewards(0.9);
        cc.setTravelRewards(3.1);
        cc.setGroceryRewards(0.1);
        cc.setRestaurantRewards(0);
        cc.setGasRewards(5);
        cc.setDrugStoreRewards(1);
        cc.setTransitRewards(0.45789);
        cc.setEntertainmentRewards(1.75);
        cc.setRecurringRewards(1.2);
        assertEquals("My Card", cc.getName());
        assertEquals("Aeroplan", cc.getRewardName());
        assertEquals(999, cc.getAnnualFee());
        assertEquals(0.9, cc.getGeneralRewards());
        assertEquals(3.1, cc.getTravelRewards());
        assertEquals(0.1, cc.getGroceryRewards());
        assertEquals(0, cc.getRestaurantRewards());
        assertEquals(5, cc.getGasRewards());
        assertEquals(1, cc.getDrugStoreRewards());
        assertEquals(0.45789, cc.getTransitRewards());
        assertEquals(1.75, cc.getEntertainmentRewards());
        assertEquals(1.2, cc.getRecurringRewards());
    }

}