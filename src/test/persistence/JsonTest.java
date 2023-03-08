package persistence;

import model.*;
import org.junit.jupiter.api.BeforeEach;

public class JsonTest {

    protected CreditCard card1;
    protected CreditCard card2;
    protected RewardType reward1;
    protected RewardType reward2;
    protected ListOfCreditCards listOfCreditCards;
    protected ListOfRewardTypes listOfRewardTypes;
    protected MonthlySpending monthlySpending;
    protected JsonWriter jsonWriterCards;
    protected JsonWriter jsonWriterRewards;
    protected JsonWriter jsonWriterSpending;
    protected JsonReader jsonReaderCards;
    protected JsonReader jsonReaderRewards;
    protected JsonReader jsonReaderSpending;

    @BeforeEach
    protected void runBefore() {
        listOfCreditCards = new ListOfCreditCards(false);
        listOfRewardTypes = new ListOfRewardTypes(false);
        monthlySpending = new MonthlySpending();
    }

    protected void loadTwoCardsAndTwoRewards() {
        card1 = new CreditCard("Test Credit Card 1", "Cashback", 100,
                1.1, 1.3, 1.5, 0.9, 1, 0.7,
                2, 0.5, 0.8);
        card2 = new CreditCard("Test Credit Card 2", "Aeroplan", 0,
                0.9, 1, 2, 3, 4, 0.5,
                0.7, 2, 1);
        listOfCreditCards.addCreditCard(card1);
        listOfCreditCards.addCreditCard(card2);


        reward1 = new RewardType("Cashback", 1);
        reward2 = new RewardType("Aeroplan", 2.1);
        listOfRewardTypes.addRewardType(reward1);
        listOfRewardTypes.addRewardType(reward2);
    }

    protected void updateMonthlySpending() {
        monthlySpending.setGeneralSpending(1000);
        monthlySpending.setTravelSpending(100);
        monthlySpending.setGrocerySpending(500);
        monthlySpending.setRestaurantSpending(200);
        monthlySpending.setGasSpending(75);
        monthlySpending.setDrugStoreSpending(50);
        monthlySpending.setTransitSpending(46);
        monthlySpending.setEntertainmentSpending(60);
        monthlySpending.setRecurringSpending(150);
    }

}
