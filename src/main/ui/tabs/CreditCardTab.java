package ui.tabs;

import model.CreditCard;
import model.ListOfCreditCards;
import ui.CreditCardManagerGraphical;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class CreditCardTab extends Tab {

    private static final JLabel CARD_NAME_LABEL = new JLabel("Card Name:");
    private static final JLabel REWARD_NAME_LABEL = new JLabel("Reward:");
    private static final JLabel ANNUAL_FEE_LABEL = new JLabel("Annual Fee:");
    private static final JLabel GENERAL_REWARDS_LABEL = new JLabel("General Rewards:");
    private static final JLabel TRAVEL_REWARDS_LABEL = new JLabel("Travel Rewards:");
    private static final JLabel GROCERY_REWARDS_LABEL = new JLabel("Grocery Rewards:");
    private static final JLabel RESTAURANT_REWARDS_LABEL = new JLabel("Restaurant Rewards:");
    private static final JLabel GAS_REWARDS_LABEL = new JLabel("Gas Rewards:");
    private static final JLabel DRUG_STORE_REWARDS_LABEL = new JLabel("Drug Store Rewards:");
    private static final JLabel TRANSIT_REWARDS_LABEL = new JLabel("Transit Rewards:");
    private static final JLabel ENTERTAINMENT_REWARDS_LABEL = new JLabel("Entertainment Rewards:");
    private static final JLabel RECURRING_REWARDS_LABEL = new JLabel("Recurring Rewards:");

    private JButton creditCardAddButton;
    private JButton creditCardRemoveButton;
    private JButton creditCardEditButton;
    private JButton creditCardSaveChangesButton;
    private JButton creditCardSaveAdditionButton;
    private ListOfCreditCards listOfCreditCards;
    private JList listOfCreditCardNames;
    private JScrollPane scrollPane;
    private JPanel creditCardListAndDetailsPanel;
    private JTextArea textArea1;

    private String currentCardName;
    private String currentRewardName;
    private String currentAnnualFee;
    private String currentGeneralRewards;
    private String currentTravelRewards;
    private String currentGroceryRewards;
    private String currentRestaurantRewards;
    private String currentGasRewards;
    private String currentDrugStoreRewards;
    private String currentTransitRewards;
    private String currentEntertainmentRewards;
    private String currentRecurringRewards;

    private JTextField cardNameField;
    private JTextField rewardNameField;
    private JTextField annualFeeField;
    private JTextField generalRewardField;
    private JTextField travelRewardField;
    private JTextField groceryRewardField;
    private JTextField restaurantRewardField;
    private JTextField gasRewardField;
    private JTextField drugStoreRewardField;
    private JTextField transitRewardField;
    private JTextField entertainmentRewardField;
    private JTextField recurringRewardField;

    private JPanel cardDetailPanel;
    private JPanel cardNamePanel;
    private JPanel rewardNamePanel;
    private JPanel annualFeePanel;
    private JPanel generalRewardPanel;
    private JPanel travelRewardPanel;
    private JPanel groceryRewardPanel;
    private JPanel restaurantRewardPanel;
    private JPanel gasRewardPanel;
    private JPanel drugStoreRewardPanel;
    private JPanel transitRewardPanel;
    private JPanel entertainmentRewardPanel;
    private JPanel recurringRewardPanel;

    public CreditCardTab(CreditCardManagerGraphical creditCardManagerGraphical) {
        super(creditCardManagerGraphical);
        initializeCardDetails();
        initializeCardDetailFields();
        listOfCreditCards = creditCardManagerGraphical.getListOfCreditCards();
        setUpCreditCardListAndDetailsPanel();
        listCreditCardDetails();
    }

    @Override
    protected void addHeader() {
        JLabel header = new JLabel("Credit Card Tab");
        add(header);
    }

    @Override
    protected void loadButtons() {
        creditCardAddButton = new JButton("Add A Credit Card");
        add(creditCardAddButton);
        creditCardRemoveButton = new JButton("Remove Selected Credit Card");
        add(creditCardRemoveButton);
        creditCardEditButton = new JButton("Edit Selected Credit Card");
        add(creditCardEditButton);
        creditCardSaveChangesButton = new JButton("Save Changes");
        add(creditCardSaveChangesButton);
        creditCardSaveAdditionButton = new JButton("Confirm Addition");
        add(creditCardSaveAdditionButton);
    }

    private void setUpCreditCardListAndDetailsPanel() {
        creditCardListAndDetailsPanel = new JPanel();
        loadCardNamesToScrollableList();
        textArea1 = new JTextArea();
        creditCardListAndDetailsPanel.add(textArea1);
        add(creditCardListAndDetailsPanel);
    }

    private void loadCardNamesToScrollableList() {
        int n = listOfCreditCards.getListOfCreditCards().size();
        String[] cardNames = new String[n];
        int i = 0;
        for (CreditCard c : listOfCreditCards.getListOfCreditCards()) {
            cardNames[i] = c.getCardName();
            i++;
        }
        listOfCreditCardNames = new JList(cardNames);
        scrollPane = new JScrollPane(listOfCreditCardNames);
        creditCardListAndDetailsPanel.add(scrollPane);
        listenForSelectedCreditCard();
    }

    private void listenForSelectedCreditCard() {
        listOfCreditCardNames.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent evt) {
                listOfCreditCardNamesValueChanged(evt);
            }
        });
    }

    private void listOfCreditCardNamesValueChanged(javax.swing.event.ListSelectionEvent evt) {
        String s = (String) listOfCreditCardNames.getSelectedValue();
        for (CreditCard c : listOfCreditCards.getListOfCreditCards()) {
            if (s.equals(c.getCardName())) {
                cardNameField.setText(c.getCardName());
                rewardNameField.setText(c.getRewardName());
                annualFeeField.setText(String.valueOf(c.getAnnualFee()));
                generalRewardField.setText(String.valueOf(c.getGeneralRewards()));
                travelRewardField.setText(String.valueOf(c.getTravelRewards()));
                groceryRewardField.setText(String.valueOf(c.getGroceryRewards()));
                restaurantRewardField.setText(String.valueOf(c.getRestaurantRewards()));
                gasRewardField.setText(String.valueOf(c.getGasRewards()));
                drugStoreRewardField.setText(String.valueOf(c.getDrugStoreRewards()));
                transitRewardField.setText(String.valueOf(c.getTransitRewards()));
                entertainmentRewardField.setText(String.valueOf(c.getEntertainmentRewards()));
                recurringRewardField.setText(String.valueOf(c.getRecurringRewards()));
                break;
            }
        }
    }

    private void listCreditCardDetails() {
        cardDetailPanel = new JPanel();
        cardDetailPanel.setLayout(new BoxLayout(cardDetailPanel, BoxLayout.Y_AXIS));
        createCardNamePanel();
        createRewardNamePanel();
        createAnnualFeePanel();
        createGeneralRewardPanel();
        createTravelRewardPanel();
        createGroceryRewardPanel();
        createRestaurantRewardPanel();
        createGasRewardPanel();
        createDrugStoreRewardPanel();
        createTransitRewardPanel();
        createEntertainmentRewardPanel();
        createRecurringRewardPanel();
        add(cardDetailPanel);
    }

    private void createCardNamePanel() {
        cardNamePanel = new JPanel();
        cardNamePanel.add(CARD_NAME_LABEL);
        cardNamePanel.add(cardNameField);
        cardDetailPanel.add(cardNamePanel);
    }

    private void createRewardNamePanel() {
        rewardNamePanel = new JPanel();
        rewardNamePanel.add(REWARD_NAME_LABEL);
        rewardNamePanel.add(rewardNameField);
        cardDetailPanel.add(rewardNamePanel);
    }

    private void createAnnualFeePanel() {
        annualFeePanel = new JPanel();
        annualFeePanel.add(ANNUAL_FEE_LABEL);
        annualFeePanel.add(annualFeeField);
        cardDetailPanel.add(annualFeePanel);
    }

    private void createGeneralRewardPanel() {
        generalRewardPanel = new JPanel();
        generalRewardPanel.add(GENERAL_REWARDS_LABEL);
        generalRewardPanel.add(generalRewardField);
        cardDetailPanel.add(generalRewardPanel);
    }

    private void createTravelRewardPanel() {
        travelRewardPanel = new JPanel();
        travelRewardPanel.add(TRAVEL_REWARDS_LABEL);
        travelRewardPanel.add(travelRewardField);
        cardDetailPanel.add(travelRewardPanel);
    }

    private void createGroceryRewardPanel() {
        groceryRewardPanel = new JPanel();
        groceryRewardPanel.add(GROCERY_REWARDS_LABEL);
        groceryRewardPanel.add(groceryRewardField);
        cardDetailPanel.add(groceryRewardPanel);
    }

    private void createRestaurantRewardPanel() {
        restaurantRewardPanel = new JPanel();
        restaurantRewardPanel.add(RESTAURANT_REWARDS_LABEL);
        restaurantRewardPanel.add(restaurantRewardField);
        cardDetailPanel.add(restaurantRewardPanel);
    }

    private void createGasRewardPanel() {
        gasRewardPanel = new JPanel();
        gasRewardPanel.add(GAS_REWARDS_LABEL);
        gasRewardPanel.add(gasRewardField);
        cardDetailPanel.add(gasRewardPanel);
    }

    private void createDrugStoreRewardPanel() {
        drugStoreRewardPanel = new JPanel();
        drugStoreRewardPanel.add(DRUG_STORE_REWARDS_LABEL);
        drugStoreRewardPanel.add(drugStoreRewardField);
        cardDetailPanel.add(drugStoreRewardPanel);
    }

    private void createTransitRewardPanel() {
        transitRewardPanel = new JPanel();
        transitRewardPanel.add(TRANSIT_REWARDS_LABEL);
        transitRewardPanel.add(transitRewardField);
        cardDetailPanel.add(transitRewardPanel);
    }

    private void createEntertainmentRewardPanel() {
        entertainmentRewardPanel = new JPanel();
        entertainmentRewardPanel.add(ENTERTAINMENT_REWARDS_LABEL);
        entertainmentRewardPanel.add(entertainmentRewardField);
        cardDetailPanel.add(entertainmentRewardPanel);
    }

    private void createRecurringRewardPanel() {
        recurringRewardPanel = new JPanel();
        recurringRewardPanel.add(RECURRING_REWARDS_LABEL);
        recurringRewardPanel.add(recurringRewardField);
        cardDetailPanel.add(recurringRewardPanel);
    }

    private void initializeCardDetails() {
        currentCardName = "N/A";
        currentRewardName = "N/A";
        currentAnnualFee = "N/A";
        currentGeneralRewards = "N/A";
        currentTravelRewards = "N/A";
        currentGroceryRewards = "N/A";
        currentRestaurantRewards = "N/A";
        currentGasRewards = "N/A";
        currentDrugStoreRewards = "N/A";
        currentTransitRewards = "N/A";
        currentEntertainmentRewards = "N/A";
        currentRecurringRewards = "N/A";
    }

    private void initializeCardDetailFields() {
        cardNameField = new JTextField(currentCardName);
        rewardNameField = new JTextField(currentRewardName);
        annualFeeField = new JTextField(currentAnnualFee);
        generalRewardField = new JTextField(currentGeneralRewards);
        travelRewardField = new JTextField(currentTravelRewards);
        groceryRewardField = new JTextField(currentGroceryRewards);
        restaurantRewardField = new JTextField(currentRestaurantRewards);
        gasRewardField = new JTextField(currentGasRewards);
        drugStoreRewardField = new JTextField(currentDrugStoreRewards);
        transitRewardField = new JTextField(currentTransitRewards);
        entertainmentRewardField = new JTextField(currentEntertainmentRewards);
        recurringRewardField = new JTextField(currentRecurringRewards);
    }

}
