package model;

// Carries out an optimization to determine the best credit card for a user based on their spending
public class CreditCardOptimizer {

    private double maxRewards;      // the maximum $ value the user can get in rewards from a credit card
    private String optimalCard;     // the name of the credit card that gives the user the maximum $ of rewards

    // Effects: constructs an optimizer with the maximum rewards initialized to 0 and optimal card set to "N/A"
    public CreditCardOptimizer() {
        this.maxRewards = 0;
        this.optimalCard = "N/A";
    }

    // Requires:
    // Modifies: this
    // Effects:
    public void calculateMaxRewards(ListOfCreditCards listOfCreditCards, ListOfRewardTypes listOfRewardTypes,
                                    MonthlySpending monthlySpending) {
        for (CreditCard card : listOfCreditCards.getListOfCreditCards()) {
            String rewardName = card.getRewardName();
            RewardType reward = listOfRewardTypes.getRewardType(rewardName);
            Double rewardValue = reward.getRewardValue();
            double cardRewards = calculateRewards(card, rewardValue, monthlySpending);
            if (cardRewards > maxRewards) {
                maxRewards = cardRewards;
                optimalCard = card.getCardName();
            }
        }
    }

    // Requires: card must not be null, rewardValue must be >= 0
    // Effects: for a given credit card, calculates the annual $ value of rewards this card will provide based on
    //              the given reward value in cents per point (cpp) and a particular monthly spending
    public double calculateRewards(CreditCard card, Double rewardValue, MonthlySpending monthlySpending) {
        double annualFee = card.getAnnualFee();
        double generalRewards = 12 * monthlySpending.getGeneralSpending() * card.getGeneralRewards();
        double travelRewards = 12 * monthlySpending.getTravelSpending() * card.getTravelRewards();
        double groceryRewards = 12 * monthlySpending.getGrocerySpending() * card.getGroceryRewards();
        double restaurantRewards = 12 * monthlySpending.getRestaurantSpending() * card.getRestaurantRewards();
        double gasRewards = 12 * monthlySpending.getGasSpending() * card.getGasRewards();
        double drugStoreRewards = 12 * monthlySpending.getDrugStoreSpending() * card.getDrugStoreRewards();
        double transitRewards = 12 * monthlySpending.getTransitSpending() * card.getTransitRewards();
        double entertainmentRewards = 12 * monthlySpending.getEntertainmentSpending() * card.getEntertainmentRewards();
        double recurringRewards = 12 * monthlySpending.getRecurringSpending() * card.getRecurringRewards();
        double totalRewards = (rewardValue / 100) * (generalRewards + travelRewards + groceryRewards
                + restaurantRewards + gasRewards + drugStoreRewards + transitRewards + entertainmentRewards
                + recurringRewards) - annualFee;
        return totalRewards;
    }

    public double getMaxRewards() {
        return maxRewards;
    }

    public String getOptimalCard() {
        return optimalCard;
    }
}
