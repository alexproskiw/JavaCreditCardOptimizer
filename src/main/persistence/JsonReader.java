package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: reads list of credit cards from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ListOfCreditCards readListOfCreditCards() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseListOfCreditCards(jsonObject);
    }

    // EFFECTS: parses listOfCreditCards from JSON object and returns it
    private ListOfCreditCards parseListOfCreditCards(JSONObject jsonObject) {
        ListOfCreditCards listOfCreditCards = new ListOfCreditCards(false);
        addCreditCards(listOfCreditCards, jsonObject);
        return listOfCreditCards;
    }

    // MODIFIES: listOfCreditCards
    // EFFECTS: parses credit cards from JSON object and adds them to listOfCreditCards
    private void addCreditCards(ListOfCreditCards listOfCreditCards, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("credit cards");
        for (Object json : jsonArray) {
            JSONObject nextCreditCard = (JSONObject) json;
            addCreditCard(listOfCreditCards, nextCreditCard);
        }
    }

    // MODIFIES: listOfCreditCards
    // EFFECTS: parses credit card from JSON object and adds it to listOfCreditCards
    private void addCreditCard(ListOfCreditCards listOfCreditCards, JSONObject jsonObject) {
        String cardName = jsonObject.getString("card name");
        String rewardName = jsonObject.getString("reward name");
        double annualFee = jsonObject.getDouble("annual fee");
        double general = jsonObject.getDouble("general rewards");
        double travel = jsonObject.getDouble("travel rewards");
        double grocery = jsonObject.getDouble("grocery rewards");
        double restaurant = jsonObject.getDouble("restaurant rewards");
        double gas = jsonObject.getDouble("gas rewards");
        double drugStore = jsonObject.getDouble("drug store rewards");
        double transit = jsonObject.getDouble("transit rewards");
        double entertainment = jsonObject.getDouble("entertainment rewards");
        double recurring = jsonObject.getDouble("recurring rewards");
        CreditCard card = new CreditCard(cardName, rewardName, annualFee, general, travel, grocery, restaurant, gas,
                drugStore, transit, entertainment, recurring);
        listOfCreditCards.addCreditCard(card);
    }

    // EFFECTS: reads list of reward types from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ListOfRewardTypes readListOfRewardTypes() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseListOfRewardTypes(jsonObject);
    }

    // EFFECTS: parses listOfRewardTypes from JSON object and returns it
    private ListOfRewardTypes parseListOfRewardTypes(JSONObject jsonObject) {
        ListOfRewardTypes listOfRewardTypes = new ListOfRewardTypes(false);
        addRewards(listOfRewardTypes, jsonObject);
        return listOfRewardTypes;
    }

    // MODIFIES: listOfRewardTypes
    // EFFECTS: parses reward types from JSON object and adds them to listOfRewardTypes
    private void addRewards(ListOfRewardTypes listOfRewardTypes, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("reward types");
        for (Object json : jsonArray) {
            JSONObject nextReward = (JSONObject) json;
            addReward(listOfRewardTypes, nextReward);
        }
    }

    // MODIFIES: listOfRewardTypes
    // EFFECTS: parses reward from JSON object and adds it to listOfRewardType
    private void addReward(ListOfRewardTypes listOfRewardTypes, JSONObject jsonObject) {
        String rewardName = jsonObject.getString("reward name");
        double value = jsonObject.getDouble("reward value (cpp)");
        RewardType reward = new RewardType(rewardName, value);
        listOfRewardTypes.addRewardType(reward);
    }

    // EFFECTS: reads monthly spending from file and returns it;
    // throws IOException if an error occurs reading data from file
    public MonthlySpending readMonthlySpending() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        double generalSpending = jsonObject.getDouble("general spending");
        double travelSpending = jsonObject.getDouble("travel spending");
        double grocerySpending = jsonObject.getDouble("grocery spending");
        double restaurantSpending = jsonObject.getDouble("restaurant spending");
        double gasSpending = jsonObject.getDouble("gas spending");
        double drugStoreSpending = jsonObject.getDouble("drug store spending");
        double transitSpending = jsonObject.getDouble("transit spending");
        double entertainmentSpending = jsonObject.getDouble("entertainment spending");
        double recurringSpending = jsonObject.getDouble("recurring spending");
        MonthlySpending spending = new MonthlySpending();
        spending.setGeneralSpending(generalSpending);
        spending.setTravelSpending(travelSpending);
        spending.setGrocerySpending(grocerySpending);
        spending.setRestaurantSpending(restaurantSpending);
        spending.setGasSpending(gasSpending);
        spending.setDrugStoreSpending(drugStoreSpending);
        spending.setTransitSpending(transitSpending);
        spending.setEntertainmentSpending(entertainmentSpending);
        spending.setRecurringSpending(recurringSpending);
        return spending;
    }

}
