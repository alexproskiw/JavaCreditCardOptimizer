package model;

public class CreditCardOptimizer {

    private double maxRewards;
    private String optimalCard;

    public CreditCardOptimizer() {
        this.maxRewards = 0;
        this.optimalCard = "N/A";
    }

    public void calculateMaxRewards(ListOfCreditCards locc, ListOfRewardTypes lort, MonthlySpending spend) {
        for (int i = 0; i < locc.getSize(); i++) {
            CreditCard card = locc.getCreditCard(i);
            String rewardName = card.getRewardName();
            RewardType reward = lort.getRewardType(rewardName);
            Double rewardValue = reward.getRewardValue();
            double cardRewards = calculateRewards(card, rewardValue, spend);
            if (cardRewards > maxRewards) {
                maxRewards = cardRewards;
                optimalCard = card.getName();
            }
        }
    }

    public double calculateRewards(CreditCard card, Double rewardValue, MonthlySpending spend) {
        double annualFee = card.getAnnualFee();
        double generalRewards = 12 * spend.getGeneralSpending() * card.getGeneralRewards();
        double travelRewards = 12 * spend.getTravelSpending() * card.getTravelRewards();
        double groceryRewards = 12 * spend.getGrocerySpending() * card.getGroceryRewards();
        double restaurantRewards = 12 * spend.getRestaurantSpending() * card.getRestaurantRewards();
        double gasRewards = 12 * spend.getGasSpending() * card.getGasRewards();
        double drugStoreRewards = 12 * spend.getDrugStoreSpending() * card.getDrugStoreRewards();
        double transitRewards = 12 * spend.getTransitSpending() * card.getTransitRewards();
        double entertainmentRewards = 12 * spend.getEntertainmentSpending() * card.getEntertainmentRewards();
        double recurringRewards = 12 * spend.getRecurringSpending() * card.getRecurringRewards();
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
