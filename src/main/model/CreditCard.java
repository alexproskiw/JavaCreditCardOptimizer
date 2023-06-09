package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a credit card having a card name, associated reward name, an annual fee, and reward points gained
//      for purchases in different categories (e.g., general spending, grocery, etc.)
public class CreditCard implements Writable {

    private String cardName;                // card name
    private String rewardName;              // the type of reward the card earns
    private double annualFee;               // the annual fee for owning the card
    private double generalRewards;          // the # of rewards/points the card earns for each $1 spent in general
    private double travelRewards;           // the # of rewards/points the card earns for each $1 spent on travel
    private double groceryRewards;          // the # of rewards/points the card earns for each $1 spent on groceries
    private double restaurantRewards;       // the # of rewards/points the card earns for each $1 spent on restaurants
    private double gasRewards;              // the # of rewards/points the card earns for each $1 spent on gas
    private double drugStoreRewards;        // the # of rewards/points the card earns for each $1 spent at drug stores
    private double transitRewards;          // the # of rewards/points the card earns for each $1 spent on transit
    private double entertainmentRewards;    // the # of rewards/points the card earns for each $1 spent on entertainment
    private double recurringRewards;        // the # of rewards/points the card earns for each $1 in recurring purchases

    // Requires: cardName has a non-zero length, reward name must be of an existing reward type,
    //              annualFee >= 0, and different reward categories >= 0
    // Effects: constructs a credit card with the given card name, reward name, annual fee, and reward points gained
    //              for purchases in different categories
    public CreditCard(String cardName, String rewardName, double annualFee,  double general, double travel,
                      double grocery, double restaurant, double gas, double drugStore, double transit,
                      double entertainment, double recurring) {
        this.cardName = cardName;
        this.rewardName = rewardName;
        this.annualFee = annualFee;
        this.generalRewards = general;
        this.travelRewards = travel;
        this.groceryRewards = grocery;
        this.restaurantRewards = restaurant;
        this.gasRewards = gas;
        this.drugStoreRewards = drugStore;
        this.transitRewards = transit;
        this.entertainmentRewards = entertainment;
        this.recurringRewards = recurring;
    }

    // Getters

    public String getCardName() {
        return cardName;
    }

    public String getRewardName() {
        return rewardName;
    }

    public double getAnnualFee() {
        return annualFee;
    }

    public double getGeneralRewards() {
        return generalRewards;
    }

    public double getTravelRewards() {
        return travelRewards;
    }

    public double getGroceryRewards() {
        return groceryRewards;
    }

    public double getRestaurantRewards() {
        return restaurantRewards;
    }

    public double getGasRewards() {
        return gasRewards;
    }

    public double getDrugStoreRewards() {
        return drugStoreRewards;
    }

    public double getTransitRewards() {
        return transitRewards;
    }

    public double getEntertainmentRewards() {
        return entertainmentRewards;
    }

    public double getRecurringRewards() {
        return recurringRewards;
    }

    // Setters

    public void setCardName(String cardName) {
        this.cardName = cardName;
        EventLog.getInstance().logEvent(new Event("Set card name to: " + cardName));
    }

    public void setRewardName(String rewardName) {
        this.rewardName = rewardName;
        EventLog.getInstance().logEvent(new Event("Set reward name to: " + rewardName));
    }

    public void setAnnualFee(double annualFee) {
        this.annualFee = annualFee;
        EventLog.getInstance().logEvent(new Event("Set annual fee to: " + annualFee));
    }

    public void setGeneralRewards(double general) {
        this.generalRewards = general;
        EventLog.getInstance().logEvent(new Event("Set general rewards to: " + general));
    }

    public void setTravelRewards(double travel) {
        this.travelRewards = travel;
        EventLog.getInstance().logEvent(new Event("Set travel rewards to: " + travel));
    }

    public void setGroceryRewards(double grocery) {
        this.groceryRewards = grocery;
        EventLog.getInstance().logEvent(new Event("Set grocery rewards to: " + grocery));
    }

    public void setRestaurantRewards(double restaurant) {
        this.restaurantRewards = restaurant;
        EventLog.getInstance().logEvent(new Event("Set restaurant rewards to: " + restaurant));
    }

    public void setGasRewards(double gas) {
        this.gasRewards = gas;
        EventLog.getInstance().logEvent(new Event("Set gas rewards to: " + gas));
    }

    public void setDrugStoreRewards(double drugStore) {
        this.drugStoreRewards = drugStore;
        EventLog.getInstance().logEvent(new Event("Set drug store rewards to: " + drugStore));
    }

    public void setTransitRewards(double transit) {
        this.transitRewards = transit;
        EventLog.getInstance().logEvent(new Event("Set transit rewards to: " + transit));
    }

    public void setEntertainmentRewards(double entertainment) {
        this.entertainmentRewards = entertainment;
        EventLog.getInstance().logEvent(new Event("Set entertainment rewards to: " + entertainment));
    }

    public void setRecurringRewards(double recurring) {
        this.recurringRewards = recurring;
        EventLog.getInstance().logEvent(new Event("Set recurring rewards to: " + recurring));
    }

    // Effects: Create a json object for the credit card
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("card name", cardName);
        json.put("reward name", rewardName);
        json.put("annual fee", annualFee);
        json.put("general rewards", generalRewards);
        json.put("travel rewards", travelRewards);
        json.put("grocery rewards", groceryRewards);
        json.put("restaurant rewards", restaurantRewards);
        json.put("gas rewards", gasRewards);
        json.put("drug store rewards", drugStoreRewards);
        json.put("transit rewards", transitRewards);
        json.put("entertainment rewards", entertainmentRewards);
        json.put("recurring rewards", recurringRewards);
        return json;
    }

}
