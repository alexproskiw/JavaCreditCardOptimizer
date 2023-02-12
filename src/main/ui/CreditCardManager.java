package ui;

import model.*;

import java.text.DecimalFormat;
import java.util.Scanner;

// Credit card manager application
// Note: certain elements of the scanner input/handling were modified from the CPSC 210 "teller" project
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
    private DecimalFormat df;

    // Effects: constructs a new credit card manager initialized with a scanner, the default list of credit cards,
    //              the default list of reward types, and monthly spending set to $0 in all categories
    public CreditCardManager() {
        input = new Scanner(System.in);
        runProgram = true;
        listOfCreditCards = new ListOfCreditCards();
        listOfRewardTypes = new ListOfRewardTypes();
        monthlySpending = new MonthlySpending();
        df = new DecimalFormat("0.00");
    }

    // Effects: starts the credit card manager application
    public void startCreditCardManager() {
        printSpacing();
        System.out.println("Welcome to your credit card optimizer");
        handleMainUserInput();
    }

    // Effects: displays the main menu and runs the program
    public void handleMainUserInput() {
        printMainInstructions();

        while (runProgram) {
            String str = getUserInputString();
            parseMainInput(str);
        }
    }

    // Effects: displays the main menu of options to the user
    private void printMainInstructions() {
        printSpacing();
        System.out.println("You can perform the following actions:");
        System.out.println("-> Enter '" + CARDS_COMMAND + "' to view and edit your list of credit cards");
        System.out.println("-> Enter '" + REWARDS_COMMAND + "' to view and edit your list of rewards");
        System.out.println("-> Enter '" + SPENDING_COMMAND + "' to view and edit your monthly spending");
        System.out.println("-> Enter '" + OPTIMIZE_COMMAND + "' to determine your optimal credit card");
        System.out.println("-> Enter '" + QUIT_COMMAND + "' to quit the program at any time");
        printSpacing();
    }

    // Effects: obtains the user's input and returns it in lower case format
    private String getUserInputString() {
        String str = "";
        if (input.hasNext()) {
            str = input.nextLine().toLowerCase();
        }
        return str;
    }

    // Effects: selects the appropriate sub-menu based on the user's input
    private void parseMainInput(String str) {
        if (str.equals(CARDS_COMMAND)) {
            handleCardsUserInput();
        } else if (str.equals(REWARDS_COMMAND)) {
            handleRewardsUserInput();
        } else if (str.equals(SPENDING_COMMAND)) {
            handleSpendingUserInput();
        } else if (str.equals(OPTIMIZE_COMMAND)) {
            handleOptimizeUserInput();
        } else if (str.equals(QUIT_COMMAND)) {
            endProgram();
        } else {
            printInvalidCommand();
            printMainInstructions();
        }
    }

    // Effects: displays info, gets input, and handles the user's input specific to credit cards
    private void handleCardsUserInput() {
        printCardsInstructions();
        String str = getUserInputString();
        parseCardsInput(str);
    }

    // Effects: displays the sub menu of options for credit cards to the user
    private void printCardsInstructions() {
        printSpacing();
        System.out.println("You can perform the following actions:");
        System.out.println("-> Enter '" + VIEW_COMMAND + "' to view your list of credit cards");
        System.out.println("-> Enter '" + DETAILS_COMMAND + "' to see the details of a specific credit card");
        System.out.println("-> Enter '" + ADD_COMMAND + "' to add a credit card to your list");
        System.out.println("-> Enter '" + REMOVE_COMMAND + "' to remove a credit card from your list");
        System.out.println("-> Enter '" + EDIT_COMMAND + "' to edit the details of a particular credit card");
        System.out.println("-> Enter '" + GO_BACK_COMMAND + "' to return to the previous menu");
        System.out.println("-> Enter '" + QUIT_COMMAND + "' to quit the program at any time");
        printSpacing();
    }

    // Effects: selects the appropriate credit card action based on the user's input
    private void parseCardsInput(String str) {
        if (str.equals(VIEW_COMMAND)) {
            handleCardsViewInput();
        } else if (str.equals(DETAILS_COMMAND)) {
            handleCardsDetailsInput();
        } else if (str.equals(ADD_COMMAND)) {
            handleCardsAddInput();
        } else if (str.equals(REMOVE_COMMAND)) {
            handleCardsRemoveInput();
        } else if (str.equals(EDIT_COMMAND)) {
            handleCardsEditInput();
        } else if (str.equals(GO_BACK_COMMAND)) {
            printMainInstructions();
            return;
        } else if (str.equals(QUIT_COMMAND)) {
            endProgram();
            return;
        } else {
            printInvalidCommand();
        }
        handleCardsUserInput();
    }

    // Effects: displays a list of the names of all credit cards in the user's list of credit cards
    private void handleCardsViewInput() {
        System.out.println("Your list of credit cards is as follows:");
        for (CreditCard card : listOfCreditCards.getListOfCreditCards()) {
            System.out.println("- " + card.getCardName());
        }
    }

    // Effects: determines which credit card the user wants details for and displays its details. If the user
    //              enters a card not in the list, prompt them to retry.
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
    }

    // Effects: displays the specific details for a specific credit card
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

    // Modifies: this
    // Effects: adds a new credit card with the information specified by the user
    private void handleCardsAddInput() {
        System.out.println("To add a new credit card, please enter the following information");
        String cardName = getCardNameFromUser();
        String rewardName = handleRewardNameInput();
        Double annualFee = getCardAnnualFeeFromUser();
        Double generalSpending = getCardGeneralPointsFromUser();
        Double travelSpending = getCardTravelPointsFromUser();
        Double grocerySpending = getCardGroceryPointsFromUser();
        Double restaurantSpending = getCardRestaurantPointsFromUser();
        Double gasSpending = getCardGasPointsFromUser();
        Double drugStoreSpending = getCardDrugStorePointsFromUser();
        Double transitSpending = getCardTransitPointsFromUser();
        Double entertainmentSpending = getCardEntertainmentPointsFromUser();
        Double recurringSpending = getCardRecurringPointsFromUser();
        CreditCard newCard = new CreditCard(cardName, rewardName, annualFee, generalSpending, travelSpending,
                grocerySpending, restaurantSpending, gasSpending, drugStoreSpending, transitSpending,
                entertainmentSpending, recurringSpending);
        listOfCreditCards.addCreditCard(newCard);
        System.out.println("Congratulations, the new credit card has been added to your list!");
        input.nextLine();
    }

    // Effects: returns the credit card name specified by the user
    private String getCardNameFromUser() {
        System.out.println("What is the card's name?");
        String cardName = input.nextLine();
        return cardName;
    }

    // Effects: returns the credit card annual fee specified by the user
    private Double getCardAnnualFeeFromUser() {
        System.out.println("What is the card's annual fee?");
        Double annualFee = input.nextDouble();
        return annualFee;
    }

    // Effects: returns the points the credit card earns in general purchases specified by the user
    private Double getCardGeneralPointsFromUser() {
        System.out.println("How many points does the card get for each dollar spent on general spending?");
        Double generalSpending = input.nextDouble();
        return generalSpending;
    }

    // Effects: returns the points the credit card earns in travel purchases specified by the user
    private Double getCardTravelPointsFromUser() {
        System.out.println("How many points does the card get for each dollar spent on travel?");
        Double travelSpending = input.nextDouble();
        return travelSpending;
    }

    // Effects: returns the points the credit card earns in grocery purchases specified by the user
    private Double getCardGroceryPointsFromUser() {
        System.out.println("How many points does the card get for each dollar spent on groceries?");
        Double grocerySpending = input.nextDouble();
        return grocerySpending;
    }

    // Effects: returns the points the credit card earns in restaurant purchases specified by the user
    private Double getCardRestaurantPointsFromUser() {
        System.out.println("How many points does the card get for each dollar spent on restaurants?");
        Double restaurantSpending = input.nextDouble();
        return restaurantSpending;
    }

    // Effects: returns the points the credit card earns in gas purchases specified by the user
    private Double getCardGasPointsFromUser() {
        System.out.println("How many points does the card get for each dollar spent on gas?");
        Double gasSpending = input.nextDouble();
        return gasSpending;
    }

    // Effects: returns the points the credit card earns in drug store purchases specified by the user
    private Double getCardDrugStorePointsFromUser() {
        System.out.println("How many points does the card get for each dollar spent at drug stores?");
        Double drugStoreSpending = input.nextDouble();
        return drugStoreSpending;
    }

    // Effects: returns the points the credit card earns in transit purchases specified by the user
    private Double getCardTransitPointsFromUser() {
        System.out.println("How many points does the card get for each dollar spent on transit?");
        Double transitSpending = input.nextDouble();
        return transitSpending;
    }

    // Effects: returns the points the credit card earns in entertainment purchases specified by the user
    private Double getCardEntertainmentPointsFromUser() {
        System.out.println("How many points does the card get for each dollar spent on entertainment?");
        Double entertainmentSpending = input.nextDouble();
        return entertainmentSpending;
    }

    // Effects: returns the points the credit card earns in recurring purchases specified by the user
    private Double getCardRecurringPointsFromUser() {
        System.out.println("How many points does the card get for each dollar spent on recurring purchases?");
        Double recurringSpending = input.nextDouble();
        return recurringSpending;
    }

    // Modifies: this
    // Effects: removes the credit card specified by the user. If the user enters a card not in the list,
    //              prompt them to retry.
    private void handleCardsRemoveInput() {
        System.out.println("Please enter the name of the credit card you would like to remove from your list");
        String cardName = input.nextLine();
        if (! listOfCreditCards.containsCard(cardName)) {
            System.out.println("Sorry, that card name is not in your list of credit cards. Please try again.");
        } else {
            listOfCreditCards.removeCreditCard(cardName);
            System.out.println("Congratulations, the credit card has been removed from your list!");
        }
    }

    // Effects: prompts the user to edit a specific credit. If the user enters a card not in the list,
    //              prompt them to retry.
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
    }

    // Modifies: this
    // Effects: a helper method which modifies the various fields of a given credit card based on provided user input
    private void editCardInfo(CreditCard card) {
        card.setCardName(getCardNameFromUser());
        card.setRewardName(handleRewardNameInput());
        card.setAnnualFee(getCardAnnualFeeFromUser());
        card.setGeneralRewards(getCardGeneralPointsFromUser());
        card.setTravelRewards(getCardTravelPointsFromUser());
        card.setGroceryRewards(getCardGroceryPointsFromUser());
        card.setRestaurantRewards(getCardRestaurantPointsFromUser());
        card.setGasRewards(getCardGasPointsFromUser());
        card.setDrugStoreRewards(getCardDrugStorePointsFromUser());
        card.setTransitRewards(getCardTransitPointsFromUser());
        card.setEntertainmentRewards(getCardEntertainmentPointsFromUser());
        card.setRecurringRewards(getCardRecurringPointsFromUser());
        System.out.println("Congratulations, the card's information has been updated");
        input.nextLine();
    }

    // Modifies: this
    // Effects: returns the reward name the credit card earns based on user input. If the reward is not already in the
    //              list of rewards, it prompts the user to retry or create a new reward.
    private String handleRewardNameInput() {
        System.out.println("What type of rewards does the card collect?");
        System.out.println("Or type 'add' to a new reward type.");
        String rewardName = input.nextLine();
        if (rewardName.equals("add")) {
            addRewardType();
            rewardName = handleRewardNameInput();
        } else if (! listOfRewardTypes.containsReward(rewardName)) {
            System.out.println("That is an invalid reward name. Possible reward types include:");
            displayRewardTypes();
            printSpacing();
            System.out.println("Please try again by typing an existing reward type, or adding a new reward type.");
            printSpacing();
            rewardName = handleRewardNameInput();
        }
        return rewardName;
    }

    // Effects: displays info, gets input, and handles the user's input specific to reward types
    private void handleRewardsUserInput() {
        printRewardsInstructions();
        String str = getUserInputString();
        parseRewardsInput(str);
    }

    // Effects: displays the sub menu of options for reward types to the user
    private void printRewardsInstructions() {
        printSpacing();
        System.out.println("You can perform the following actions:");
        System.out.println("-> Enter '" + VIEW_COMMAND + "' to view your list of reward types");
        System.out.println("-> Enter '" + ADD_COMMAND + "' to add a reward type to your list");
        System.out.println("-> Enter '" + REMOVE_COMMAND + "' to remove a reward type from your list");
        System.out.println("-> Enter '" + EDIT_COMMAND + "' to edit the details of a particular reward type");
        System.out.println("-> Enter '" + GO_BACK_COMMAND + "' to return to the previous menu");
        System.out.println("-> Enter '" + QUIT_COMMAND + "' to quit the program at any time");
        printSpacing();
    }

    // Effects: selects the appropriate reward type action based on the user's input
    private void parseRewardsInput(String str) {
        if (str.equals(VIEW_COMMAND)) {
            handleRewardsViewInput();
        } else if (str.equals(ADD_COMMAND)) {
            handleRewardsAddInput();
        } else if (str.equals(REMOVE_COMMAND)) {
            handleRewardsRemoveInput();
        } else if (str.equals(EDIT_COMMAND)) {
            handleRewardsEditInput();
        } else if (str.equals(GO_BACK_COMMAND)) {
            printMainInstructions();
            return;
        } else if (str.equals(QUIT_COMMAND)) {
            endProgram();
            return;
        } else {
            printInvalidCommand();
        }
        handleRewardsUserInput();
    }

    // Effects: displays a list of the names and reward values of all reward types in the user's list of reward types
    private void handleRewardsViewInput() {
        System.out.println("Your list of reward points and their values in cents per point (cpp) are as follows:");
        displayRewardTypes();
    }

    // Effects: helper method that displays the name and value for a specific reward type
    private void displayRewardTypes() {
        for (RewardType reward: listOfRewardTypes.getListOfRewardTypes()) {
            System.out.println("- " + reward.getRewardName() + ": " +  reward.getRewardValue() + " cpp");
        }
    }

    // Effects: prompts the user to add a new reward type
    private void handleRewardsAddInput() {
        System.out.println("To add a new reward type, please enter the following information");
        addRewardType();
    }

    // Modifies: this
    // Effects: adds a new reward type with the information specified by the user
    private void addRewardType() {
        System.out.println("What is the reward name?");
        String rewardName = input.nextLine();
        System.out.println("What is the value of the reward in cpp (cents per point)?");
        double rewardValue = input.nextDouble();
        RewardType newReward = new RewardType(rewardName, rewardValue);
        listOfRewardTypes.addRewardType(newReward);
        System.out.println("Congratulations, the new reward type has been added to your list!");
        input.nextLine();
    }

    // Modifies: this
    // Effects: removes the reward type specified by the user. If the user enters a reward not in the list,
    //              prompt them to retry.
    private void handleRewardsRemoveInput() {
        System.out.println("Please enter the name of the reward type you would like to remove from your list");
        String rewardName = input.nextLine();
        if (! listOfRewardTypes.containsReward(rewardName)) {
            System.out.println("Sorry, that reward type is not in your list of reward types. Please try again.");
        } else {
            listOfRewardTypes.removeRewardType(rewardName);
            System.out.println("Congratulations, the reward type has been removed from your list!");
        }
    }

    // Effects: prompts the user to edit a specific reward type. If the user enters a reward not in the list,
    //              prompt them to retry.
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
    }

    // Modifies: this
    // Effects: a helper method which modifies the various fields of a given reward type based on provided user input
    private void editRewardInfo(RewardType reward) {
        System.out.println("What is the reward type's name?");
        reward.setRewardName(input.nextLine());
        System.out.println("What is the value of the reward in cpp (cents per point)?");
        reward.setRewardValue(input.nextDouble());
        System.out.println("Congratulations, the reward's information has been updated");
        input.nextLine();
    }

    // Effects: displays info, gets input, and handles the user's input specific to monthly spending
    private void handleSpendingUserInput() {
        printSpendingInstructions();
        String str = getUserInputString();
        parseSpendingInput(str);
    }

    // Effects: displays the sub menu of options for monthly spending to the user
    private void printSpendingInstructions() {
        printSpacing();
        System.out.println("You can perform the following actions:");
        System.out.println("-> Enter '" + VIEW_COMMAND + "' to view your monthly spending");
        System.out.println("-> Enter '" + UPDATE_COMMAND + "' to update your monthly spending");
        System.out.println("-> Enter '" + GO_BACK_COMMAND + "' to return to the previous menu");
        System.out.println("-> Enter '" + QUIT_COMMAND + "' to quit the program at any time");
        printSpacing();
    }

    // Effects: selects the appropriate monthly spending action based on the user's input
    private void parseSpendingInput(String str) {
        if (str.equals(VIEW_COMMAND)) {
            handleSpendingViewInput();
        } else if (str.equals(UPDATE_COMMAND)) {
            handleSpendingUpdateInput();
        } else if (str.equals(GO_BACK_COMMAND)) {
            printMainInstructions();
            return;
        } else if (str.equals(QUIT_COMMAND)) {
            endProgram();
            return;
        } else {
            printInvalidCommand();
        }
        handleSpendingUserInput();
    }

    // Effects: displays a list of the users monthly spending in the different categories
    private void handleSpendingViewInput() {
        System.out.println("Your average monthly spending is:");
        System.out.println("$" + df.format(monthlySpending.getGeneralSpending())
                + " on general purchases");
        System.out.println("$" + df.format(monthlySpending.getTravelSpending())
                + " on travel purchases");
        System.out.println("$" + df.format(monthlySpending.getGrocerySpending())
                + " on grocery purchases");
        System.out.println("$" + df.format(monthlySpending.getRestaurantSpending())
                + " on restaurant purchases");
        System.out.println("$" + df.format(monthlySpending.getGasSpending())
                + " on gas purchases");
        System.out.println("$" + df.format(monthlySpending.getDrugStoreSpending())
                + " on drug store purchases");
        System.out.println("$" + df.format(monthlySpending.getTransitSpending())
                + " on transit purchases");
        System.out.println("$" + df.format(monthlySpending.getEntertainmentSpending())
                + " on entertainment purchases");
        System.out.println("$" + df.format(monthlySpending.getRecurringSpending())
                + " on recurring purchases");
    }

    // Modifies: this
    // Effects: edits the monthly spending with the information specified by the user
    private void handleSpendingUpdateInput() {
        System.out.println("Please enter your updated average monthly spending (in the form of a number):");
        monthlySpending.setGeneralSpending(getUserGeneralSpending());
        monthlySpending.setTravelSpending(getUserTravelSpending());
        monthlySpending.setGrocerySpending(getUserGrocerySpending());
        monthlySpending.setRestaurantSpending(getUserRestaurantSpending());
        monthlySpending.setGasSpending(getUserGasSpending());
        monthlySpending.setDrugStoreSpending(getUserDrugStoreSpending());
        monthlySpending.setTransitSpending(getUserTransitSpending());
        monthlySpending.setEntertainmentSpending(getUserEntertainmentSpending());
        monthlySpending.setRecurringSpending(getUserRecurringSpending());
        System.out.println("Thank you for entering your updated spending information!");
        input.nextLine();
    }

    // Effects: returns the $ spent per month in general purchases specified by the user
    private Double getUserGeneralSpending() {
        System.out.println("How much do you spend on general purchases?");
        Double generalSpending = input.nextDouble();
        return generalSpending;
    }

    // Effects: returns the $ spent per month in travel purchases specified by the user
    private Double getUserTravelSpending() {
        System.out.println("How much do you spend on travel purchases?");
        Double travelSpending = input.nextDouble();
        return travelSpending;
    }

    // Effects: returns the $ spent per month in grocery purchases specified by the user
    private Double getUserGrocerySpending() {
        System.out.println("How much do you spend on grocery purchases?");
        Double grocerySpending = input.nextDouble();
        return grocerySpending;
    }

    // Effects: returns the $ spent per month in restaurant purchases specified by the user
    private Double getUserRestaurantSpending() {
        System.out.println("How much do you spend on restaurant purchases?");
        Double restaurantSpending = input.nextDouble();
        return restaurantSpending;
    }

    // Effects: returns the $ spent per month in gas purchases specified by the user
    private Double getUserGasSpending() {
        System.out.println("How much do you spend on gas purchases?");
        Double gasSpending = input.nextDouble();
        return gasSpending;
    }

    // Effects: returns the $ spent per month in drug store purchases specified by the user
    private Double getUserDrugStoreSpending() {
        System.out.println("How much do you spend on drug store purchases?");
        Double drugStoreSpending = input.nextDouble();
        return drugStoreSpending;
    }

    // Effects: returns the $ spent per month in transit purchases specified by the user
    private Double getUserTransitSpending() {
        System.out.println("How much do you spend on transit purchases?");
        Double transitSpending = input.nextDouble();
        return transitSpending;
    }

    // Effects: returns the $ spent per month in entertainment purchases specified by the user
    private Double getUserEntertainmentSpending() {
        System.out.println("How much do you spend on entertainment purchases?");
        Double entertainmentSpending = input.nextDouble();
        return entertainmentSpending;
    }

    // Effects: returns the $ spent per month in recurring purchases specified by the user
    private Double getUserRecurringSpending() {
        System.out.println("How much do you spend on recurring purchases?");
        Double recurringSpending = input.nextDouble();
        return recurringSpending;
    }

    // Effects: displays info, gets input, and handles the user's input specific to optimization
    private void handleOptimizeUserInput() {
        printOptimizeInstructions();
        String str = getUserInputString();
        parseOptimizeInput(str);
    }

    // Effects: displays the sub menu of options for optimization to the user
    private void printOptimizeInstructions() {
        printSpacing();
        System.out.println("Before proceeding, please ensure your spending information is up to date");
        System.out.println("If not, please go back and update your spending information");
        System.out.println("Otherwise, you can perform the following actions:");
        System.out.println("-> Enter '" + PROCEED_COMMAND + "' to proceed with optimization");
        System.out.println("-> Enter '" + GO_BACK_COMMAND + "' to return to the previous menu");
        System.out.println("-> Enter '" + QUIT_COMMAND + "' to quit the program at any time");
        printSpacing();
    }

    // Effects: selects the appropriate optimization action based on the user's input
    private void parseOptimizeInput(String str) {
        if (str.equals(PROCEED_COMMAND)) {
            handleOptimizeInput();
        } else if (str.equals(GO_BACK_COMMAND)) {
            printMainInstructions();
            return;
        } else if (str.equals(QUIT_COMMAND)) {
            endProgram();
            return;
        } else {
            printInvalidCommand();
            printOptimizeInstructions();
        }
    }

    // Modifies: this
    // Effects: runs the optimization based on the user's list of credit cards, list of reward types, and monthly
    //              spending, displaying the optimal card and $ value in rewards it creates
    private void handleOptimizeInput() {
        optimizer = new CreditCardOptimizer();
        optimizer.calculateMaxRewards(listOfCreditCards, listOfRewardTypes, monthlySpending);
        System.out.println("Your recommended card is the " + optimizer.getOptimalCard());
        double rewards = optimizer.getMaxRewards();
        System.out.println("With your spending, you will get a net value of $" + df.format(rewards)
                + " in rewards per year");
        printMainInstructions();
    }

    // Modifies: this
    // Effects: ends the program
    public void endProgram() {
        runProgram = false;
        System.out.println("Thank you for using your credit card optimizer");
        input.close();
    }

    // Effects: prints a separation line for formatting
    private void printSpacing() {
        System.out.println("=====================================");
    }

    // Effects: Informs the user they entered an invalid command
    private void printInvalidCommand() {
        System.out.println("That's an invalid command: Please try again");
    }
    
}
