package model;

public class MonthlySpending {

    private double generalSpending;
    private double travelSpending;
    private double grocerySpending;
    private double restaurantSpending;
    private double gasSpending;
    private double drugStoreSpending;
    private double transitSpending;
    private double entertainmentSpending;
    private double recurringSpending;

    public MonthlySpending(double general, double travel, double grocery, double restaurant, double gas,
                           double drugStore, double transit, double entertainment, double recurring) {
        this.generalSpending = general;
        this.travelSpending = travel;
        this.grocerySpending = grocery;
        this.restaurantSpending = restaurant;
        this.gasSpending = gas;
        this.drugStoreSpending = drugStore;
        this.transitSpending = transit;
        this.entertainmentSpending = entertainment;
        this.recurringSpending = recurring;
    }

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
        this.grocerySpending = grocery;
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
}
