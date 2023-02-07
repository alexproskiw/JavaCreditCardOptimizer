package model;

public class CreditCardOptimizer {

    private double maxRewards;
    private String optimalCard;

    public CreditCardOptimizer() {
        this.maxRewards = 0;
        this.optimalCard = "N/A";
    }

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
