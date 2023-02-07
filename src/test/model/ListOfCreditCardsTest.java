package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ListOfCreditCardsTest {

    ListOfCreditCards listOfCreditCards;

    @BeforeEach
    void runBefore() {
        listOfCreditCards = new ListOfCreditCards();
    }

    @Test
    void testConstructor() {
        assertEquals(12, listOfCreditCards.getListOfCreditCards().size());
    }

    @Test
    void testGetCreditCardNotInList() {
        assertNull(listOfCreditCards.getCreditCard("Fake Credit Card"));
    }

    @Test
    void testContainsCardInList() {
        assertTrue(listOfCreditCards.containsCard("AMEX Cobalt"));
    }

    @Test
    void testContainsCardNotInList() {
        assertFalse(listOfCreditCards.containsCard("Scotia Momentum Visa Infinite"));
    }
    @Test
    void testAddCreditCard() {
        CreditCard testCard = new CreditCard("Test Credit Card", "Cashback", 100,
                1.1, 1.3, 1.5, 0.9, 1, 0.7,
                2, 0.5, 0.8);
        assertTrue(listOfCreditCards.addCreditCard(testCard));
        assertEquals(13, listOfCreditCards.getListOfCreditCards().size());
        assertEquals(testCard, listOfCreditCards.getCreditCard("Test Credit Card"));
    }

    @Test
    void testAddCreditCardAlreadyInList() {
        CreditCard amexCobalt = new CreditCard("AMEX Cobalt",
                "AMEX Rewards", 155.88, 1, 2, 5, 5, 2,
                1, 2, 3, 1);
        assertFalse(listOfCreditCards.addCreditCard(amexCobalt));
        assertEquals(12, listOfCreditCards.getListOfCreditCards().size());
    }

    @Test
    void testRemoveCreditCard() {
        assertTrue(listOfCreditCards.removeCreditCard("CIBC Dividend Visa"));
        assertEquals(11, listOfCreditCards.getListOfCreditCards().size());
    }

    @Test
    void testRemoveMultipleCreditCards() {
        assertTrue(listOfCreditCards.removeCreditCard("CIBC Dividend Visa"));
        assertTrue(listOfCreditCards.removeCreditCard("Simplii Cash Back Visa"));
        assertEquals(10, listOfCreditCards.getListOfCreditCards().size());
    }

    @Test
    void testRemoveCreditCardMultipleTimes() {
        assertTrue(listOfCreditCards.removeCreditCard("CIBC Dividend Visa"));
        assertEquals(11, listOfCreditCards.getListOfCreditCards().size());
        assertFalse(listOfCreditCards.removeCreditCard("CIBC Dividend Visa"));
        assertEquals(11, listOfCreditCards.getListOfCreditCards().size());
    }

    @Test
    void testRemoveCreditCardNotInList() {
        assertFalse(listOfCreditCards.removeCreditCard("Scotia Momentum Visa Infinite"));
        assertEquals(12, listOfCreditCards.getListOfCreditCards().size());
    }

}
