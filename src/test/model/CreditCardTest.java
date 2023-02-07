package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CreditCardTest {

    CreditCard card;

    @BeforeEach
    void runBefore() {
        card = new CreditCard("Test Credit Card", "Cashback", 100,
                1.1, 1.3, 1.5, 0.9, 1, 0.7,
                2, 0.5, 0.8);
    }

    @Test
    void testConstructor() {
        assertEquals("Test Credit Card", card.getCardName());
        assertEquals("Cashback", card.getRewardName());
        assertEquals(100, card.getAnnualFee());
        assertEquals(1.1, card.getGeneralRewards());
        assertEquals(1.3, card.getTravelRewards());
        assertEquals(1.5, card.getGroceryRewards());
        assertEquals(0.9, card.getRestaurantRewards());
        assertEquals(1, card.getGasRewards());
        assertEquals(0.7, card.getDrugStoreRewards());
        assertEquals(2, card.getTransitRewards());
        assertEquals(0.5, card.getEntertainmentRewards());
        assertEquals(0.8, card.getRecurringRewards());
    }

    @Test
    void testSetters() {
        card.setCardName("My Card");
        card.setRewardName("Aeroplan");
        card.setAnnualFee(999);
        card.setGeneralRewards(0.9);
        card.setTravelRewards(3.1);
        card.setGroceryRewards(0.1);
        card.setRestaurantRewards(0);
        card.setGasRewards(5);
        card.setDrugStoreRewards(1);
        card.setTransitRewards(0.45789);
        card.setEntertainmentRewards(1.75);
        card.setRecurringRewards(1.2);
        assertEquals("My Card", card.getCardName());
        assertEquals("Aeroplan", card.getRewardName());
        assertEquals(999, card.getAnnualFee());
        assertEquals(0.9, card.getGeneralRewards());
        assertEquals(3.1, card.getTravelRewards());
        assertEquals(0.1, card.getGroceryRewards());
        assertEquals(0, card.getRestaurantRewards());
        assertEquals(5, card.getGasRewards());
        assertEquals(1, card.getDrugStoreRewards());
        assertEquals(0.45789, card.getTransitRewards());
        assertEquals(1.75, card.getEntertainmentRewards());
        assertEquals(1.2, card.getRecurringRewards());
    }

}