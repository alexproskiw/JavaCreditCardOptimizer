package persistence;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        jsonReaderCards = new JsonReader("./data/noSuchFile.json");
        jsonReaderRewards = new JsonReader("./data/noSuchFile.json");
        jsonReaderSpending = new JsonReader("./data/noSuchFile.json");

        try {
            listOfCreditCards = jsonReaderCards.readListOfCreditCards();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
        try {
            listOfRewardTypes = jsonReaderRewards.readListOfRewardTypes();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
        try {
            monthlySpending = jsonReaderSpending.readMonthlySpending();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyCardsRewardsAndNoSpending() {
        jsonReaderCards = new JsonReader("./data/testReaderEmptyCards.json");
        jsonReaderRewards = new JsonReader("./data/testReaderEmptyRewards.json");
        jsonReaderSpending = new JsonReader("./data/testReaderNoSpending.json");

        try {
            listOfCreditCards = jsonReaderCards.readListOfCreditCards();
            listOfRewardTypes = jsonReaderRewards.readListOfRewardTypes();
            monthlySpending = jsonReaderSpending.readMonthlySpending();

            assertEquals(0, listOfCreditCards.getListOfCreditCards().size());
            assertEquals(0, listOfRewardTypes.getListOfRewardTypes().size());
            assertEquals(0, monthlySpending.getGeneralSpending());
            assertEquals(0, monthlySpending.getTravelSpending());
            assertEquals(0, monthlySpending.getGrocerySpending());
            assertEquals(0, monthlySpending.getRestaurantSpending());
            assertEquals(0, monthlySpending.getGasSpending());
            assertEquals(0, monthlySpending.getDrugStoreSpending());
            assertEquals(0, monthlySpending.getTransitSpending());
            assertEquals(0, monthlySpending.getEntertainmentSpending());
            assertEquals(0, monthlySpending.getRecurringSpending());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderTwoCardsTwoRewardsWithSpending() {
        jsonReaderCards = new JsonReader("./data/testReaderTwoCards.json");
        jsonReaderRewards = new JsonReader("./data/testReaderTwoRewards.json");
        jsonReaderSpending = new JsonReader("./data/testReaderWithSpending.json");

        try {
            listOfCreditCards = jsonReaderCards.readListOfCreditCards();
            listOfRewardTypes = jsonReaderRewards.readListOfRewardTypes();
            monthlySpending = jsonReaderSpending.readMonthlySpending();

            assertEquals(2, listOfCreditCards.getListOfCreditCards().size());
            assertEquals(2, listOfRewardTypes.getListOfRewardTypes().size());

            card1 = listOfCreditCards.getListOfCreditCards().get(0);
            assertEquals("Test Credit Card 1", card1.getCardName());
            assertEquals("Cashback", card1.getRewardName());
            assertEquals(100, card1.getAnnualFee());
            assertEquals(1.1, card1.getGeneralRewards());
            assertEquals(1.3, card1.getTravelRewards());
            assertEquals(1.5, card1.getGroceryRewards());
            assertEquals(0.9, card1.getRestaurantRewards());
            assertEquals(1, card1.getGasRewards());
            assertEquals(0.7, card1.getDrugStoreRewards());
            assertEquals(2, card1.getTransitRewards());
            assertEquals(0.5, card1.getEntertainmentRewards());
            assertEquals(0.8, card1.getRecurringRewards());

            card2 = listOfCreditCards.getListOfCreditCards().get(1);
            assertEquals("Test Credit Card 2", card2.getCardName());
            assertEquals("Aeroplan", card2.getRewardName());
            assertEquals(0, card2.getAnnualFee());
            assertEquals(0.9, card2.getGeneralRewards());
            assertEquals(1, card2.getTravelRewards());
            assertEquals(2, card2.getGroceryRewards());
            assertEquals(3, card2.getRestaurantRewards());
            assertEquals(4, card2.getGasRewards());
            assertEquals(0.5, card2.getDrugStoreRewards());
            assertEquals(0.7, card2.getTransitRewards());
            assertEquals(2, card2.getEntertainmentRewards());
            assertEquals(1, card2.getRecurringRewards());

            reward1 = listOfRewardTypes.getListOfRewardTypes().get(0);
            assertEquals("Cashback", reward1.getRewardName());
            assertEquals(1, reward1.getRewardValue());

            reward2 = listOfRewardTypes.getListOfRewardTypes().get(1);
            assertEquals("Aeroplan", reward2.getRewardName());
            assertEquals(2.1, reward2.getRewardValue());

            assertEquals(2, listOfRewardTypes.getListOfRewardTypes().size());
            assertEquals(1000, monthlySpending.getGeneralSpending());
            assertEquals(100, monthlySpending.getTravelSpending());
            assertEquals(500, monthlySpending.getGrocerySpending());
            assertEquals(200, monthlySpending.getRestaurantSpending());
            assertEquals(75, monthlySpending.getGasSpending());
            assertEquals(50, monthlySpending.getDrugStoreSpending());
            assertEquals(46, monthlySpending.getTransitSpending());
            assertEquals(60, monthlySpending.getEntertainmentSpending());
            assertEquals(150, monthlySpending.getRecurringSpending());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
