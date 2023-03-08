package persistence;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            jsonWriterCards = new JsonWriter("./data/my\0illegal:fileName.json");
            jsonWriterCards.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
        try {
            jsonWriterRewards = new JsonWriter("./data/my\0illegal:fileName.json");
            jsonWriterRewards.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
        try {
            jsonWriterSpending = new JsonWriter("./data/my\0illegal:fileName.json");
            jsonWriterSpending.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyCardsRewardsAndNoSpending() {
        try {
            jsonWriterCards = new JsonWriter("./data/testWriterEmptyCards.json");
            jsonWriterRewards = new JsonWriter("./data/testWriterEmptyRewards.json");
            jsonWriterSpending = new JsonWriter("./data/testWriterNoSpending.json");

            jsonWriterCards.open();
            jsonWriterCards.write(listOfCreditCards);
            jsonWriterCards.close();

            jsonWriterRewards.open();
            jsonWriterRewards.write(listOfRewardTypes);
            jsonWriterRewards.close();

            jsonWriterSpending.open();
            jsonWriterSpending.write(monthlySpending);
            jsonWriterSpending.close();

            jsonReaderCards = new JsonReader("./data/testWriterEmptyCards.json");
            jsonReaderRewards = new JsonReader("./data/testWriterEmptyRewards.json");
            jsonReaderSpending = new JsonReader("./data/testWriterNoSpending.json");

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
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterTwoCardsTwoRewardsWithSpending() {
        loadTwoCardsAndTwoRewards();
        updateMonthlySpending();

        try {
            jsonWriterCards = new JsonWriter("./data/testWriterTwoCards.json");
            jsonWriterRewards = new JsonWriter("./data/testWriterTwoRewards.json");
            jsonWriterSpending = new JsonWriter("./data/testWriterWithSpending.json");

            jsonWriterCards.open();
            jsonWriterCards.write(listOfCreditCards);
            jsonWriterCards.close();

            jsonWriterRewards.open();
            jsonWriterRewards.write(listOfRewardTypes);
            jsonWriterRewards.close();

            jsonWriterSpending.open();
            jsonWriterSpending.write(monthlySpending);
            jsonWriterSpending.close();

            jsonReaderCards = new JsonReader("./data/testWriterTwoCards.json");
            jsonReaderRewards = new JsonReader("./data/testWriterTwoRewards.json");
            jsonReaderSpending = new JsonReader("./data/testWriterWithSpending.json");

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
            fail("Exception should not have been thrown");
        }
    }

}
