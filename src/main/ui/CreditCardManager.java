package ui;

import model.*;

import java.util.Locale;
import java.util.Scanner;

public class CreditCardManager {

    private static final String CARDS_COMMAND = "cards";
    private static final String REWARDS_COMMAND = "rewards";
    private static final String SPENDING_COMMAND = "spending";
    private static final String OPTIMIZE_COMMAND = "optimize";
    private static final String VIEW_COMMAND = "view";
    private static final String DETAILS_COMMAND = "details";
    private static final String ADD_COMMAND = "add";
    private static final String REMOVE_COMMAND = "remove";
    private static final String EDIT_COMMAND = "edit";
    private static final String UPDATE_COMMAND = "update";
    private static final String PROCEED_COMMAND = "proceed";
    private static final String GO_BACK_COMMAND = "back";
    private static final String QUIT_COMMAND = "quit";

    private Scanner input;
    private boolean runProgram;
    private ListOfCreditCards listOfCreditCards;
    private ListOfRewardTypes listOfRewardTypes;
    private MonthlySpending monthlySpending;
    private CreditCardOptimizer optimizer;

    public CreditCardManager() {
        input = new Scanner(System.in);
        runProgram = true;
        listOfCreditCards = new ListOfCreditCards();
        listOfRewardTypes = new ListOfRewardTypes();
        monthlySpending = new MonthlySpending();
        optimizer = new CreditCardOptimizer();
    }

    public void startCreditCardManager() {
        printSpacing();
        System.out.println("Welcome to your credit card optimizer");
        printSpacing();
        handleMainUserInput();
    }

    public void handleMainUserInput() {
        printMainInstructions();

        while (runProgram) {
            String str = getUserInputString();
            parseMainInput(str);
        }
    }

    private void printMainInstructions() {
        System.out.println("You can perform the following actions:");
        System.out.println("-> Enter '" + CARDS_COMMAND + "' to view and edit your list of credit cards");
        System.out.println("-> Enter '" + REWARDS_COMMAND + "' to view and edit your list of rewards");
        System.out.println("-> Enter '" + SPENDING_COMMAND + "' to view and edit your monthly spending");
        System.out.println("-> Enter '" + OPTIMIZE_COMMAND + "' to determine your optimal credit card");
        System.out.println("-> Enter '" + QUIT_COMMAND + "' to quit the program at any time");
    }

    private String getUserInputString() {
        String str = "";
        if (input.hasNext()) {
            str = input.nextLine().toLowerCase();
        }
        return str;
    }

    private void parseMainInput(String str) {
        if (str.length() > 0) {
            switch (str) {
                case CARDS_COMMAND:
                    handleCardsUserInput();
                    break;
                case REWARDS_COMMAND:
                    handleRewardsUserInput();
                    break;
                case SPENDING_COMMAND:
                    handleSpendingUserInput();
                    break;
                case OPTIMIZE_COMMAND:
                    handleOptimizeUserInput();
                    break;
                case QUIT_COMMAND:
                    endProgram();
                    break;
                default:
                    System.out.println("That's an invalid command. Please try again.");
                    printMainInstructions();
                    break;
            }
        }
    }

    private void handleCardsUserInput() {
        printCardsInstructions();
        String str = getUserInputString();
        parseCardsInput(str);
    }

    private void printCardsInstructions() {
        System.out.println("You can perform the following actions:");
        System.out.println("-> Enter '" + VIEW_COMMAND + "' to view your list of credit cards");
        System.out.println("-> Enter '" + DETAILS_COMMAND + "' to see the details of a specific credit card");
        System.out.println("-> Enter '" + ADD_COMMAND + "' to add a credit card to your list");
        System.out.println("-> Enter '" + REMOVE_COMMAND + "' to remove a credit card from your list");
        System.out.println("-> Enter '" + EDIT_COMMAND + "' to edit the details of a particular credit card");
        System.out.println("-> Enter '" + GO_BACK_COMMAND + "' to return to the previous menu");
        System.out.println("-> Enter '" + QUIT_COMMAND + "' to quit the program at any time");
    }

    private void parseCardsInput(String str) {
        if (str.length() > 0) {
            switch (str) {
                case VIEW_COMMAND:
                    handleCardsViewInput();
                    break;
                case DETAILS_COMMAND:
                    handleCardsDetailsInput();
                    break;
                case ADD_COMMAND:
                    handleCardsAddInput();
                    break;
                case REMOVE_COMMAND:
                    handleCardsRemoveInput();
                    break;
                case EDIT_COMMAND:
                    handleCardsEditInput();
                    break;
                case GO_BACK_COMMAND:
                    printMainInstructions();
                    break;
                case QUIT_COMMAND:
                    endProgram();
                    break;
                default:
                    printInvalidCommand();
                    handleCardsUserInput();
                    break;
            }
        }
    }

    private void handleCardsViewInput() {
        System.out.println("Your list of credit cards is as follows:");
        for (CreditCard card : listOfCreditCards.getListOfCreditCards()) {
            System.out.println("- " + card.getCardName());
        }
        handleCardsUserInput();
    }

    private void handleCardsDetailsInput() {
        System.out.println("Please enter the name of the credit card you would like to see details for");
        String cardName = input.nextLine();
        if (! listOfCreditCards.containsCard(cardName)) {
            System.out.println("Sorry, that card name is not in your list of credit cards. Please try again.");
        } else {
            for (CreditCard card : listOfCreditCards.getListOfCreditCards()) {
                if (card.getCardName().equals(cardName)) {
                    displayCardInfo(card);
                    break;
                }
            }
        }
        handleCardsUserInput();
    }

    private void displayCardInfo(CreditCard card) {
        System.out.println("The card's name is: " + card.getCardName());
        System.out.println("The card's reward type is: " + card.getRewardName());
        System.out.println("The card's annual fee is: $" + card.getAnnualFee());
        System.out.println("The card's reward rates are as follows:");
        System.out.println("- General rewards: " + card.getGeneralRewards() + " points per $ spent");
        System.out.println("- Travel rewards: " + card.getTravelRewards() + " points per $ spent");
        System.out.println("- Grocery rewards: " + card.getGroceryRewards() + " points per $ spent");
        System.out.println("- Restaurant rewards: " + card.getRestaurantRewards() + " points per $ spent");
        System.out.println("- Gas rewards: " + card.getGasRewards() + " points per $ spent");
        System.out.println("- Drug store rewards: " + card.getDrugStoreRewards() + " points per $ spent");
        System.out.println("- Transit rewards: " + card.getTransitRewards() + " points per $ spent");
        System.out.println("- Entertainment rewards: " + card.getEntertainmentRewards()
                + " points per $ spent");
        System.out.println("- Recurring rewards: " + card.getRecurringRewards() + " points per $ spent");
    }

    private void handleCardsAddInput() {
        System.out.println("To add a new credit card, please enter the following information");
        System.out.println("What is the card's name?");
        String cardName = input.nextLine();
        System.out.println("What type of rewards does the card collect?");
        String rewardType = input.nextLine();
        System.out.println("What is the card's annual fee?");
        Double annualFee = input.nextDouble();
        System.out.println("How many points does the card get for each dollar spent on general spending?");
        Double generalSpending = input.nextDouble();
        System.out.println("How many points does the card get for each dollar spent on travel?");
        Double travelSpending = input.nextDouble();
        System.out.println("How many points does the card get for each dollar spent on groceries?");
        Double grocerySpending = input.nextDouble();
        System.out.println("How many points does the card get for each dollar spent on restaurants?");
        Double restaurantSpending = input.nextDouble();
        System.out.println("How many points does the card get for each dollar spent on gas?");
        Double gasSpending = input.nextDouble();
        System.out.println("How many points does the card get for each dollar spent at drug stores?");
        Double drugStoreSpending = input.nextDouble();
        System.out.println("How many points does the card get for each dollar spent on transit?");
        Double transitSpending = input.nextDouble();
        System.out.println("How many points does the card get for each dollar spent on entertainment?");
        Double entertainmentSpending = input.nextDouble();
        System.out.println("How many points does the card get for each dollar spent on recurring purchases?");
        Double recurringSpending = input.nextDouble();
        CreditCard newCard = new CreditCard(cardName, rewardType, annualFee, generalSpending, travelSpending,
                grocerySpending, restaurantSpending, gasSpending, drugStoreSpending, transitSpending,
                entertainmentSpending, recurringSpending);
        listOfCreditCards.addCreditCard(newCard);
        System.out.println("Congratulations, the new credit card has been added to your list!");
        input.nextLine();
        handleCardsUserInput();
    }

    private void handleCardsRemoveInput() {
        System.out.println("Please enter the name of the credit card you would like to remove from your list");
        String cardName = input.nextLine();
        if (! listOfCreditCards.containsCard(cardName)) {
            System.out.println("Sorry, that card name is not in your list of credit cards. Please try again.");
        } else {
            listOfCreditCards.removeCreditCard(cardName);
            System.out.println("Congratulations, the credit card has been removed from your list!");
        }
        handleCardsUserInput();
    }

    private void handleCardsEditInput() {
        System.out.println("Please enter the name of the credit card you would like to edit details for");
        String cardName = input.nextLine();
        if (! listOfCreditCards.containsCard(cardName)) {
            System.out.println("Sorry, that card name is not in your list of credit cards. Please try again.");
        } else {
            for (CreditCard card : listOfCreditCards.getListOfCreditCards()) {
                if (card.getCardName().equals(cardName)) {
                    editCardInfo(card);
                    break;
                }
            }
        }
        handleCardsUserInput();
    }

    private void editCardInfo(CreditCard card) {
        System.out.println("What is the card's name?");
        card.setCardName(input.nextLine());
        System.out.println("What type of rewards does the card collect?");
        card.setRewardName(input.nextLine());
        System.out.println("What is the card's annual fee?");
        card.setAnnualFee(input.nextDouble());
        System.out.println("How many points does the card get for each dollar spent on general spending?");
        card.setGeneralRewards(input.nextDouble());
        System.out.println("How many points does the card get for each dollar spent on travel?");
        card.setTravelRewards(input.nextDouble());
        System.out.println("How many points does the card get for each dollar spent on groceries?");
        card.setGroceryRewards(input.nextDouble());
        System.out.println("How many points does the card get for each dollar spent on restaurants?");
        card.setRestaurantRewards(input.nextDouble());
        System.out.println("How many points does the card get for each dollar spent on gas?");
        card.setGasRewards(input.nextDouble());
        System.out.println("How many points does the card get for each dollar spent at drug stores?");
        card.setDrugStoreRewards(input.nextDouble());
        System.out.println("How many points does the card get for each dollar spent on transit?");
        card.setTransitRewards(input.nextDouble());
        System.out.println("How many points does the card get for each dollar spent on entertainment?");
        card.setEntertainmentRewards(input.nextDouble());
        System.out.println("How many points does the card get for each dollar spent on recurring purchases?");
        card.setRecurringRewards(input.nextDouble());
        System.out.println("Congratulations, the card's information has been updated");
        input.nextLine();
    }

    private void handleRewardsUserInput() {
        printRewardsInstructions();
        String str = getUserInputString();
        parseRewardsInput(str);
    }

    private void printRewardsInstructions() {
        System.out.println("You can perform the following actions:");
        System.out.println("-> Enter '" + VIEW_COMMAND + "' to view your list of reward types");
        System.out.println("-> Enter '" + ADD_COMMAND + "' to add a reward type to your list");
        System.out.println("-> Enter '" + REMOVE_COMMAND + "' to remove a reward type from your list");
        System.out.println("-> Enter '" + EDIT_COMMAND + "' to edit the details of a particular reward type");
        System.out.println("-> Enter '" + GO_BACK_COMMAND + "' to return to the previous menu");
        System.out.println("-> Enter '" + QUIT_COMMAND + "' to quit the program at any time");
    }

    private void parseRewardsInput(String str) {
        if (str.length() > 0) {
            switch (str) {
                case VIEW_COMMAND:
                    handleRewardsViewInput();
                    break;
                case ADD_COMMAND:
                    handleRewardsAddInput();
                    break;
                case REMOVE_COMMAND:
                    handleRewardsRemoveInput();
                    break;
                case EDIT_COMMAND:
                    handleRewardsEditInput();
                    break;
                case GO_BACK_COMMAND:
                    printMainInstructions();
                    break;
                case QUIT_COMMAND:
                    endProgram();
                    break;
                default:
                    printInvalidCommand();
                    handleRewardsUserInput();
                    break;
            }
        }
    }

    private void handleRewardsViewInput() {
        System.out.println("Your list of reward points and their values in cents per point (cpp) are as follows:");
        for (RewardType reward: listOfRewardTypes.getListOfRewardTypes()) {
            System.out.println("- " + reward.getRewardName() + ": " +  reward.getRewardValue() + " cpp");
        }
        handleRewardsUserInput();
    }

    private void handleRewardsAddInput() {
        System.out.println("To add a new reward type, please enter the following information");
        System.out.println("What is the reward name?");
        String rewardName = input.nextLine();
        System.out.println("What is the value of the reward in cpp (cents per point)?");
        Double rewardValue = input.nextDouble();
        RewardType newReward = new RewardType(rewardName, rewardValue);
        listOfRewardTypes.addRewardType(newReward);
        System.out.println("Congratulations, the new reward type has been added to your list!");
        input.nextLine();
        handleRewardsUserInput();
    }

    private void handleRewardsRemoveInput() {
        System.out.println("Please enter the name of the reward type you would like to remove from your list");
        String rewardName = input.nextLine();
        if (! listOfRewardTypes.containsReward(rewardName)) {
            System.out.println("Sorry, that reward type is not in your list of reward types. Please try again.");
        } else {
            listOfRewardTypes.removeRewardType(rewardName);
            System.out.println("Congratulations, the reward type has been removed from your list!");
        }
        handleRewardsUserInput();
    }

    private void handleRewardsEditInput() {
        System.out.println("Please enter the name of the reward type you would like to edit details for");
        String rewardName = input.nextLine();
        if (! listOfRewardTypes.containsReward(rewardName)) {
            System.out.println("Sorry, that reward type is not in your list of reward types. Please try again.");
        } else {
            for (RewardType reward : listOfRewardTypes.getListOfRewardTypes()) {
                if (reward.getRewardName().equals(rewardName)) {
                    editRewardInfo(reward);
                    break;
                }
            }
        }
        handleRewardsUserInput();
    }

    private void editRewardInfo(RewardType reward) {
        System.out.println("What is the reward type's name?");
        reward.setRewardName(input.nextLine());
        System.out.println("What is the value of the reward in cpp (cents per point)?");
        reward.setRewardValue(input.nextDouble());
        System.out.println("Congratulations, the reward's information has been updated");
        input.nextLine();
    }

    private void handleSpendingUserInput() {
        printSpendingInstructions();
        String str = getUserInputString();
        parseSpendingInput(str);
    }

    private void printSpendingInstructions() {
        System.out.println("You can perform the following actions:");
        System.out.println("-> Enter '" + VIEW_COMMAND + "' to view your monthly spending");
        System.out.println("-> Enter '" + UPDATE_COMMAND + "' to update your monthly spending");
        System.out.println("-> Enter '" + GO_BACK_COMMAND + "' to return to the previous menu");
        System.out.println("-> Enter '" + QUIT_COMMAND + "' to quit the program at any time");
    }

    private void parseSpendingInput(String str) {
        if (str.length() > 0) {
            switch (str) {
                case VIEW_COMMAND:
                    handleSpendingViewInput();
                    break;
                case UPDATE_COMMAND:
                    handleSpendingUpdateInput();
                    break;
                case GO_BACK_COMMAND:
                    printMainInstructions();
                    break;
                case QUIT_COMMAND:
                    endProgram();
                    break;
                default:
                    printInvalidCommand();
                    handleSpendingUserInput();
                    break;
            }
        }
    }

    private void handleSpendingViewInput() {
        System.out.println("Your average monthly spending is:");
        System.out.println("$" + Math.round(monthlySpending.getGeneralSpending() * 100) / 100d
                + " on general purchases");
        System.out.println("$" + Math.round(monthlySpending.getTravelSpending() * 100) / 100d
                + " on travel purchases");
        System.out.println("$" + Math.round(monthlySpending.getGrocerySpending() * 100) / 100d
                + " on grocery purchases");
        System.out.println("$" + Math.round(monthlySpending.getRestaurantSpending() * 100) / 100d
                + " on restaurant purchases");
        System.out.println("$" + Math.round(monthlySpending.getGasSpending() * 100) / 100d
                + " on gas purchases");
        System.out.println("$" + Math.round(monthlySpending.getDrugStoreSpending() * 100) / 100d
                + " on drug store purchases");
        System.out.println("$" + Math.round(monthlySpending.getTransitSpending() * 100) / 100d
                + " on transit purchases");
        System.out.println("$" + Math.round(monthlySpending.getEntertainmentSpending() * 100) / 100d
                + " on entertainment purchases");
        System.out.println("$" + Math.round(monthlySpending.getRecurringSpending() * 100) / 100d
                + " on recurring purchases");
        handleSpendingUserInput();
    }

    private void handleSpendingUpdateInput() {
        System.out.println("Please enter your updated average monthly spending (in the form of a number):");
        System.out.println("How much do you spend on general purchases?");
        Double generalSpending = input.nextDouble();
        monthlySpending.setGeneralSpending(generalSpending);
        System.out.println("How much do you spend on travel purchases?");
        Double travelSpending = input.nextDouble();
        monthlySpending.setTravelSpending(travelSpending);
        System.out.println("How much do you spend on grocery purchases?");
        Double grocerySpending = input.nextDouble();
        monthlySpending.setGrocerySpending(grocerySpending);
        System.out.println("How much do you spend on restaurant purchases?");
        Double restaurantSpending = input.nextDouble();
        monthlySpending.setRestaurantSpending(restaurantSpending);
        System.out.println("How much do you spend on gas purchases?");
        Double gasSpending = input.nextDouble();
        monthlySpending.setGasSpending(gasSpending);
        System.out.println("How much do you spend on drug store purchases?");
        Double drugStoreSpending = input.nextDouble();
        monthlySpending.setDrugStoreSpending(drugStoreSpending);
        System.out.println("How much do you spend on transit purchases?");
        Double transitSpending = input.nextDouble();
        monthlySpending.setTransitSpending(transitSpending);
        System.out.println("How much do you spend on entertainment purchases?");
        Double entertainmentSpending = input.nextDouble();
        monthlySpending.setEntertainmentSpending(entertainmentSpending);
        System.out.println("How much do you spend on recurring purchases?");
        Double recurringSpending = input.nextDouble();
        monthlySpending.setRecurringSpending(recurringSpending);
        System.out.println("Thank you for entering your updated spending information!");
        input.nextLine();
        handleSpendingUserInput();
    }

    private void handleOptimizeUserInput() {
        printOptimizeInstructions();
        String str = getUserInputString();
        parseOptimizeInput(str);
    }

    private void printOptimizeInstructions() {
        System.out.println("Before proceeding, please ensure your spending information is up to date");
        System.out.println("If not, please go back and update your spending information");
        System.out.println("Otherwise, you can perform the following actions:");
        System.out.println("-> Enter '" + PROCEED_COMMAND + "' to proceed with optimization");
        System.out.println("-> Enter '" + GO_BACK_COMMAND + "' to return to the previous menu");
        System.out.println("-> Enter '" + QUIT_COMMAND + "' to quit the program at any time");
    }

    private void parseOptimizeInput(String str) {
        if (str.length() > 0) {
            switch (str) {
                case PROCEED_COMMAND:
                    handleOptimizeInput();
                    break;
                case GO_BACK_COMMAND:
                    printMainInstructions();
                    break;
                case QUIT_COMMAND:
                    endProgram();
                    break;
                default:
                    printInvalidCommand();
                    handleOptimizeUserInput();
                    break;
            }
        }
    }

    private void handleOptimizeInput() {
        optimizer = new CreditCardOptimizer();
        optimizer.calculateMaxRewards(listOfCreditCards, listOfRewardTypes, monthlySpending);
        System.out.println("Your recommended card is the " + optimizer.getOptimalCard());
        Double rewards = Math.round(optimizer.getMaxRewards() * 100) / 100d;
        System.out.println("With your spending, you will get a net value of $" + rewards
                + " in rewards per year");
        handleMainUserInput();
    }

    public void endProgram() {
        runProgram = false;
        System.out.println("Thank you for using your credit card optimizer");
        input.close();
    }

    private void printSpacing() {
        System.out.println("=====================================");
    }

    private void printInvalidCommand() {
        System.out.println("That's an invalid command: Please try again");
    }
    
}
