package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Tests for methods in the ListOfCreditCard class
public class ListOfCreditCardsTest {

    ListOfCreditCards listOfCreditCards;

    @BeforeEach
    void runBefore() {
        listOfCreditCards = new ListOfCreditCards(true);
    }

    @Test
    void testConstructorNoDefaultsLoaded() {
        listOfCreditCards = new ListOfCreditCards(false);
        assertEquals(0, listOfCreditCards.getListOfCreditCards().size());
    }

    @Test
    void testConstructorDefaultsLoaded() {
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
    void testAddMultipleCreditCards() {
        CreditCard testCard1 = new CreditCard("Test Credit Card 1", "Cashback", 100,
                1.1, 1.3, 1.5, 0.9, 1, 0.7,
                2, 0.5, 0.8);
        CreditCard testCard2 = new CreditCard("Test Credit Card 2", "Aeroplan", 0,
                0.9, 1, 2, 3, 4, 0.5,
                0.7, 2, 1);
        assertTrue(listOfCreditCards.addCreditCard(testCard1));
        assertEquals(13, listOfCreditCards.getListOfCreditCards().size());
        assertEquals(testCard1, listOfCreditCards.getCreditCard("Test Credit Card 1"));
        assertTrue(listOfCreditCards.addCreditCard(testCard2));
        assertEquals(14, listOfCreditCards.getListOfCreditCards().size());
        assertEquals(testCard2, listOfCreditCards.getCreditCard("Test Credit Card 2"));
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

    @Test
    void testToJson() {
        listOfCreditCards = new ListOfCreditCards(false);
        CreditCard testCard1 = new CreditCard("Test Credit Card 1", "Cashback", 100,
                1.1, 1.3, 1.5, 0.9, 1, 0.7,
                2, 0.5, 0.8);
        CreditCard testCard2 = new CreditCard("Test Credit Card 2", "Aeroplan", 0,
                0.9, 1, 2, 3, 4, 0.5,
                0.7, 2, 1);
        assertTrue(listOfCreditCards.addCreditCard(testCard1));
        assertTrue(listOfCreditCards.addCreditCard(testCard2));

        JSONObject json = listOfCreditCards.toJson();
        JSONArray jsonArray = json.getJSONArray("credit cards");
        assertEquals(2, jsonArray.length());
    }

}
