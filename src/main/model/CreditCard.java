package model;

public class CreditCard {

    private String name;
    private String rewardName;
    private double annualFee;
    private double generalRewards;
    private double travelRewards;
    private double groceryRewards;
    private double restaurantRewards;
    private double gasRewards;
    private double drugStoreRewards;
    private double transitRewards;
    private double entertainmentRewards;
    private double recurringRewards;

    public CreditCard(String name, String rewardName, double annualFee,  double general, double travel,
                      double grocery, double restaurant, double gas, double drugStore, double transit,
                      double entertainment, double recurring) {
        this.name = name;
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

    public String getName() {
        return name;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setRewardName(String rewardName) {
        this.rewardName = rewardName;
    }

    public void setAnnualFee(double annualFee) {
        this.annualFee = annualFee;
    }

    public void setGeneralRewards(double general) {
        this.generalRewards = general;
    }

    public void setTravelRewards(double travel) {
        this.travelRewards = travel;
    }

    public void setGroceryRewards(double grocery) {
        this.groceryRewards = grocery;
    }

    public void setRestaurantRewards(double restaurant) {
        this.restaurantRewards = restaurant;
    }

    public void setGasRewards(double gas) {
        this.gasRewards = gas;
    }

    public void setDrugStoreRewards(double drugStore) {
        this.drugStoreRewards = drugStore;
    }

    public void setTransitRewards(double transit) {
        this.transitRewards = transit;
    }

    public void setEntertainmentRewards(double entertainment) {
        this.entertainmentRewards = entertainment;
    }

    public void setRecurringRewards(double recurring) {
        this.recurringRewards = recurring;
    }

}
