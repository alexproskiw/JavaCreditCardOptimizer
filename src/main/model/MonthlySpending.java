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
    }

    public void setTravelSpending(double travel) {
        this.travelSpending = travel;
    }

    public void setGrocerySpending(double grocery) {
        this.grocerySpending = grocery;
    }

    public void setRestaurantSpending(double grocery) {
        this.restaurantSpending = grocery;
    }

    public void setGasSpending(double gas) {
        this.gasSpending = gas;
    }

    public void setDrugStoreSpending(double drugStore) {
        this.drugStoreSpending = drugStore;
    }

    public void setTransitSpending(double transit) {
        this.transitSpending = transit;
    }

    public void setEntertainmentSpending(double entertainment) {
        this.entertainmentSpending = entertainment;
    }

    public void setRecurringSpending(double recurring) {
        this.recurringSpending = recurring;
    }

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
