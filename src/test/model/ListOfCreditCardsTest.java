package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ListOfCreditCardsTest {

    ListOfCreditCards locc;

    @BeforeEach
    void runBefore() {
        locc = new ListOfCreditCards();
    }

    @Test
    void testConstructor() {
        assertEquals(12, locc.getSize());
    }

    @Test
    void testAddCreditCard() {
        CreditCard card = new CreditCard("Test Credit Card", "Cashback", 100,
                1.1, 1.3, 1.5, 0.9, 1, 0.7,
                2, 0.5, 0.8);
        assertTrue(locc.addCreditCard(card));
        assertEquals(13, locc.getSize());
        assertEquals(card, locc.getCreditCard("Test Credit Card"));
    }

    @Test
    void testAddCreditCardAlreadyInList() {
        CreditCard amexCobalt = new CreditCard("AMEX Cobalt",
                "AMEX Rewards", 155.88, 1, 2, 5, 5, 2,
                1, 2, 3, 1);
        assertFalse(locc.addCreditCard(amexCobalt));
        assertEquals(12, locc.getSize());
    }

    @Test
    void testRemoveCreditCard() {
        assertTrue(locc.removeCreditCard("CIBC Dividend Visa"));
        assertEquals(11, locc.getSize());
    }

    @Test
    void testRemoveMultipleCreditCards() {
        assertTrue(locc.removeCreditCard("CIBC Dividend Visa"));
        assertTrue(locc.removeCreditCard("Simplii Cash Back Visa"));
        assertEquals(10, locc.getSize());
    }

    @Test
    void testRemoveCreditCardMultipleTimes() {
        assertTrue(locc.removeCreditCard("CIBC Dividend Visa"));
        assertEquals(11, locc.getSize());
        assertFalse(locc.removeCreditCard("CIBC Dividend Visa"));
        assertEquals(11, locc.getSize());
    }

    @Test
    void testRemoveCreditCardNotInList() {
        assertFalse(locc.removeCreditCard("Scotia Momentum Visa Infinite"));
        assertEquals(12, locc.getSize());
    }

    @Test
    void testContainsCardInList() {
        assertTrue(locc.containsCard("AMEX Cobalt"));
    }

    @Test
    void testContainsCardNotInList() {
        assertFalse(locc.containsCard("Scotia Momentum Visa Infinite"));
    }

}
