package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a user's monthly spending in various categories
public class MonthlySpending implements Writable {

    // Different spending categories
    private double generalSpending;
    private double travelSpending;
    private double grocerySpending;
    private double restaurantSpending;
    private double gasSpending;
    private double drugStoreSpending;
    private double transitSpending;
    private double entertainmentSpending;
    private double recurringSpending;

    // Effects: constructs a user's monthly spending, initialized with $0 spent in every category
    public MonthlySpending() {
        this.generalSpending = 0;
        this.travelSpending = 0;
        this.grocerySpending = 0;
        this.restaurantSpending = 0;
        this.gasSpending = 0;
        this.drugStoreSpending = 0;
        this.transitSpending = 0;
        this.entertainmentSpending = 0;
        this.recurringSpending = 0;
    }

    // Getters

    public double getGeneralSpending() {
        return generalSpending;
    }

    public double getTravelSpending() {
        return travelSpending;
    }

    public double getGrocerySpending() {
        return grocerySpending;
    }

    public double getRestaurantSpending() {
        return restaurantSpending;
    }

    public double getGasSpending() {
        return gasSpending;
    }

    public double getDrugStoreSpending() {
        return drugStoreSpending;
    }

    public double getTransitSpending() {
        return transitSpending;
    }

    public double getEntertainmentSpending() {
        return entertainmentSpending;
    }

    public double getRecurringSpending() {
        return recurringSpending;
    }

    // Setters

    public void setGeneralSpending(double general) {
        this.generalSpending = general;
        EventLog.getInstance().logEvent(new Event("Set general spending to: " + general));
    }

    public void setTravelSpending(double travel) {
        this.travelSpending = travel;
        EventLog.getInstance().logEvent(new Event("Set travel spending to: " + travel));
    }

    public void setGrocerySpending(double grocery) {
        this.grocerySpending = grocery;
        EventLog.getInstance().logEvent(new Event("Set grocery spending to: " + grocery));
    }

    public void setRestaurantSpending(double restaurant) {
        this.restaurantSpending = restaurant;
        EventLog.getInstance().logEvent(new Event("Set restaurant spending to: " + restaurant));
    }

    public void setGasSpending(double gas) {
        this.gasSpending = gas;
        EventLog.getInstance().logEvent(new Event("Set gas spending to: " + gas));
    }

    public void setDrugStoreSpending(double drugStore) {
        this.drugStoreSpending = drugStore;
        EventLog.getInstance().logEvent(new Event("Set drug store spending to: " + drugStore));
    }

    public void setTransitSpending(double transit) {
        this.transitSpending = transit;
        EventLog.getInstance().logEvent(new Event("Set transit spending to: " + transit));
    }

    public void setEntertainmentSpending(double entertainment) {
        this.entertainmentSpending = entertainment;
        EventLog.getInstance().logEvent(new Event("Set entertainment spending to: " + entertainment));
    }

    public void setRecurringSpending(double recurring) {
        this.recurringSpending = recurring;
        EventLog.getInstance().logEvent(new Event("Set recurring spending to: " + recurring));
    }

    // Effects: Create a json object for the monthly spending
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("general spending", generalSpending);
        json.put("travel spending", travelSpending);
        json.put("grocery spending", grocerySpending);
        json.put("restaurant spending", restaurantSpending);
        json.put("gas spending", gasSpending);
        json.put("drug store spending", drugStoreSpending);
        json.put("transit spending", transitSpending);
        json.put("entertainment spending", entertainmentSpending);
        json.put("recurring spending", recurringSpending);
        return json;
    }
}
