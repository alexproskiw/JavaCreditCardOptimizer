package ui.tabs;

import model.CreditCard;
import model.ListOfCreditCards;
import ui.CreditCardManagerGraphical;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    private JPanel masterPanel;
    private JPanel leftPanel;
    private JPanel creditCardListPanel;
    private JPanel leftButtonPanel;
    private JPanel rightPanel;
    private JPanel cardDetailPanel;
    private JPanel rightButtonPanel;
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

    private JButton creditCardEditButton;
    private JButton creditCardAddButton;
    private JButton creditCardRemoveButton;
    private JButton creditCardSaveChangesButton;
    private JButton creditCardConfirmAddButton;
    private JButton creditCardConfirmRemoveButton;

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

    private JLabel messageBanner;
    private JScrollPane scrollPane;
    private JList listOfCreditCardNames;
    private DefaultListModel<String> listOfCreditCardNamesModel;

    private ListOfCreditCards listOfCreditCards;
    private CreditCard currentCreditCard;

    public CreditCardTab(CreditCardManagerGraphical creditCardManagerGraphical) {
        super(creditCardManagerGraphical);
        listOfCreditCards = creditCardManagerGraphical.getListOfCreditCards();
        initializeCardDetailFields();
        loadMasterPanel();
        loadLeftPanel();
        loadRightPanel();
        loadButtonStatesInitial();
    }

    @Override
    protected void addHeader() {
        JLabel header = new JLabel("Credit Card Tab");
        header.setHorizontalAlignment(JLabel.CENTER);
        add(header, BorderLayout.PAGE_START);
    }

    @Override
    protected void addMessageBanner() {
        messageBanner = new JLabel();
        messageBanner.setText("Please select a card for more options, or click 'add' to add a new card");
        messageBanner.setHorizontalAlignment(JLabel.CENTER);
        add(messageBanner, BorderLayout.PAGE_END);
    }

    private void initializeCardDetailFields() {
        cardNameField = new JTextField(20);
        rewardNameField = new JTextField(20);
        annualFeeField = new JTextField(20);
        generalRewardField = new JTextField(20);
        travelRewardField = new JTextField(20);
        groceryRewardField = new JTextField(20);
        restaurantRewardField = new JTextField(20);
        gasRewardField = new JTextField(20);
        drugStoreRewardField = new JTextField(20);
        transitRewardField = new JTextField(20);
        entertainmentRewardField = new JTextField(20);
        recurringRewardField = new JTextField(20);
        resetCreditCardDetailFields();
        setCardDetailFieldsNotEditable();
    }

    private void loadMasterPanel() {
        masterPanel = new JPanel();
        masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.LINE_AXIS));
        add(masterPanel, BorderLayout.CENTER);
    }

    protected void loadLeftPanel() {
        leftPanel = new JPanel();
        setUpCreditCardListAndDetailsPanel();
        loadLeftButtons();
        masterPanel.add(leftPanel);
    }

    protected void loadRightPanel() {
        rightPanel = new JPanel();
        listCreditCardDetails();
        loadRightButtons();
        masterPanel.add(rightPanel);
    }

    private void loadLeftButtons() {
        leftButtonPanel = new JPanel();
        loadCreditCardEditButton();
        loadCreditCardAddButton();
        loadCreditCardRemoveButton();
        leftPanel.add(leftButtonPanel);
    }

    private void loadRightButtons() {
        rightButtonPanel = new JPanel();
        loadCreditCardSaveChangesButton();
        loadCreditCardConfirmAddButton();
        loadCreditCardConfirmRemoveButton();
        rightPanel.add(rightButtonPanel);
    }

    private void loadCreditCardEditButton() {
        creditCardEditButton = new JButton("Edit Card");
        creditCardEditButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        handleClickOnEditButton();
                    }
                }
        );
        leftButtonPanel.add(creditCardEditButton);
    }

    private void loadCreditCardAddButton() {
        creditCardAddButton = new JButton("Add New Card");
        creditCardAddButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        handleClickOnAddButton();
                    }
                }
        );
        leftButtonPanel.add(creditCardAddButton);
    }

    private void loadCreditCardRemoveButton() {
        creditCardRemoveButton = new JButton("Remove Card");
        creditCardRemoveButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        handleClickOnRemoveButton();
                    }
                }
        );
        leftButtonPanel.add(creditCardRemoveButton);
    }

    private void loadCreditCardSaveChangesButton() {
        creditCardSaveChangesButton = new JButton("Save Edits");
        creditCardSaveChangesButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        handleClickOnSaveChangesButton();
                    }
                }
        );
        rightButtonPanel.add(creditCardSaveChangesButton);
    }

    private void loadCreditCardConfirmAddButton() {
        creditCardConfirmAddButton = new JButton("Add");
        creditCardConfirmAddButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        handleClickOnConfirmAddButton();
                    }
                }
        );
        rightButtonPanel.add(creditCardConfirmAddButton);
    }

    private void loadCreditCardConfirmRemoveButton() {
        creditCardConfirmRemoveButton = new JButton("Remove");
        creditCardConfirmRemoveButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        handleClickOnConfirmRemoveButton();
                    }
                }
        );
        rightButtonPanel.add(creditCardConfirmRemoveButton);
    }

    private void setUpCreditCardListAndDetailsPanel() {
        creditCardListPanel = new JPanel();
        loadCardNamesToScrollableList();
        leftPanel.add(creditCardListPanel);
    }

    private void loadCardNamesToScrollableList() {
        listOfCreditCardNames = new JList();
        listOfCreditCardNamesModel = new DefaultListModel();
        for (CreditCard c : listOfCreditCards.getListOfCreditCards()) {
            listOfCreditCardNamesModel.addElement(c.getCardName());
        }
        listOfCreditCardNames.setModel(listOfCreditCardNamesModel);
        scrollPane = new JScrollPane(listOfCreditCardNames);
        creditCardListPanel.add(scrollPane);
        listenForSelectedCreditCard();
    }

    private void listenForSelectedCreditCard() {
        listOfCreditCardNames.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent evt) {
                handleNewCreditCardSelected(evt);
            }
        });
    }

    private void handleNewCreditCardSelected(javax.swing.event.ListSelectionEvent evt) {
        String s = (String) listOfCreditCardNames.getSelectedValue();
        for (CreditCard c : listOfCreditCards.getListOfCreditCards()) {
            if (s == null) {
                break;
            }
            if (s.equals(c.getCardName())) {
                currentCreditCard = c;
                setCardDetailFields();
                messageBanner.setText("Please choose one of 'edit', 'add', or 'remove'");
                break;
            }
        }
        loadButtonStatesOnCardClick();
    }

    private void setCardDetailFields() {
        cardNameField.setText(currentCreditCard.getCardName());
        rewardNameField.setText(currentCreditCard.getRewardName());
        annualFeeField.setText(String.valueOf(currentCreditCard.getAnnualFee()));
        generalRewardField.setText(String.valueOf(currentCreditCard.getGeneralRewards()));
        travelRewardField.setText(String.valueOf(currentCreditCard.getTravelRewards()));
        groceryRewardField.setText(String.valueOf(currentCreditCard.getGroceryRewards()));
        restaurantRewardField.setText(String.valueOf(currentCreditCard.getRestaurantRewards()));
        gasRewardField.setText(String.valueOf(currentCreditCard.getGasRewards()));
        drugStoreRewardField.setText(String.valueOf(currentCreditCard.getDrugStoreRewards()));
        transitRewardField.setText(String.valueOf(currentCreditCard.getTransitRewards()));
        entertainmentRewardField.setText(String.valueOf(currentCreditCard.getEntertainmentRewards()));
        recurringRewardField.setText(String.valueOf(currentCreditCard.getRecurringRewards()));
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
        rightPanel.add(cardDetailPanel);
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

    private void loadButtonStatesInitial() {
        creditCardEditButton.setEnabled(false);
        creditCardAddButton.setEnabled(true);
        creditCardRemoveButton.setEnabled(false);
        creditCardSaveChangesButton.setEnabled(false);
        creditCardConfirmAddButton.setEnabled(false);
        creditCardConfirmRemoveButton.setEnabled(false);
    }

    private void loadButtonStatesOnCardClick() {
        creditCardEditButton.setEnabled(true);
        creditCardAddButton.setEnabled(true);
        creditCardRemoveButton.setEnabled(true);
        creditCardSaveChangesButton.setEnabled(false);
        creditCardConfirmAddButton.setEnabled(false);
        creditCardConfirmRemoveButton.setEnabled(false);
    }

    private void loadButtonStatesEdit() {
        creditCardEditButton.setEnabled(false);
        creditCardAddButton.setEnabled(false);
        creditCardRemoveButton.setEnabled(false);
        creditCardSaveChangesButton.setEnabled(true);
        creditCardConfirmAddButton.setEnabled(false);
        creditCardConfirmRemoveButton.setEnabled(false);
    }

    private void loadButtonStatesAdd() {
        creditCardEditButton.setEnabled(false);
        creditCardAddButton.setEnabled(false);
        creditCardRemoveButton.setEnabled(false);
        creditCardSaveChangesButton.setEnabled(false);
        creditCardConfirmAddButton.setEnabled(true);
        creditCardConfirmRemoveButton.setEnabled(false);
    }

    private void loadButtonStatesRemove() {
        creditCardEditButton.setEnabled(false);
        creditCardAddButton.setEnabled(false);
        creditCardRemoveButton.setEnabled(false);
        creditCardSaveChangesButton.setEnabled(false);
        creditCardConfirmAddButton.setEnabled(false);
        creditCardConfirmRemoveButton.setEnabled(true);
    }

    private void setCardDetailFieldsNotEditable() {
        cardNameField.setEditable(false);
        rewardNameField.setEditable(false);
        annualFeeField.setEditable(false);
        generalRewardField.setEditable(false);
        travelRewardField.setEditable(false);
        groceryRewardField.setEditable(false);
        restaurantRewardField.setEditable(false);
        gasRewardField.setEditable(false);
        drugStoreRewardField.setEditable(false);
        transitRewardField.setEditable(false);
        entertainmentRewardField.setEditable(false);
        recurringRewardField.setEditable(false);
    }

    private void setCardDetailFieldsEditable() {
        cardNameField.setEditable(true);
        rewardNameField.setEditable(true);
        annualFeeField.setEditable(true);
        generalRewardField.setEditable(true);
        travelRewardField.setEditable(true);
        groceryRewardField.setEditable(true);
        restaurantRewardField.setEditable(true);
        gasRewardField.setEditable(true);
        drugStoreRewardField.setEditable(true);
        transitRewardField.setEditable(true);
        entertainmentRewardField.setEditable(true);
        recurringRewardField.setEditable(true);
    }

    private void setCardDetailFieldsEditableExceptCardName() {
        cardNameField.setEditable(false);
        rewardNameField.setEditable(true);
        annualFeeField.setEditable(true);
        generalRewardField.setEditable(true);
        travelRewardField.setEditable(true);
        groceryRewardField.setEditable(true);
        restaurantRewardField.setEditable(true);
        gasRewardField.setEditable(true);
        drugStoreRewardField.setEditable(true);
        transitRewardField.setEditable(true);
        entertainmentRewardField.setEditable(true);
        recurringRewardField.setEditable(true);
    }

    private void handleClickOnEditButton() {
        loadButtonStatesEdit();
        setCardDetailFieldsEditableExceptCardName();
        messageBanner.setText("Please update card details and save");
    }

    private void handleClickOnAddButton() {
        resetCreditCardDetailFields();
        loadButtonStatesAdd();
        setCardDetailFieldsEditable();
        messageBanner.setText("Please enter card details and confirm add");
    }

    private void handleClickOnRemoveButton() {
        loadButtonStatesRemove();
        messageBanner.setText("Please confirm you want to remove this card");
    }

    private void handleClickOnSaveChangesButton() {
        getUpdatedCardDetailsAndSave();
        refreshCardList();
        loadButtonStatesInitial();
        setCardDetailFieldsNotEditable();
        messageBanner.setText("Changes saved");
    }

    private void handleClickOnConfirmAddButton() {
        getNewCardDetailsAndSave();
        refreshCardList();
        loadButtonStatesInitial();
        setCardDetailFieldsNotEditable();
    }

    private void handleClickOnConfirmRemoveButton() {
        listOfCreditCards.removeCreditCard(currentCreditCard.getCardName());
        refreshCardList();
        loadButtonStatesInitial();
        resetCreditCardDetailFields();
        messageBanner.setText("Card removed");
    }

    private void getUpdatedCardDetailsAndSave() {
        currentCreditCard.setCardName(cardNameField.getText());
        currentCreditCard.setRewardName(rewardNameField.getText());
        currentCreditCard.setAnnualFee(Double.valueOf(annualFeeField.getText()));
        currentCreditCard.setGeneralRewards(Double.valueOf(generalRewardField.getText()));
        currentCreditCard.setTravelRewards(Double.valueOf(travelRewardField.getText()));
        currentCreditCard.setGroceryRewards(Double.valueOf(groceryRewardField.getText()));
        currentCreditCard.setRestaurantRewards(Double.valueOf(restaurantRewardField.getText()));
        currentCreditCard.setGasRewards(Double.valueOf(gasRewardField.getText()));
        currentCreditCard.setDrugStoreRewards(Double.valueOf(drugStoreRewardField.getText()));
        currentCreditCard.setTransitRewards(Double.valueOf(transitRewardField.getText()));
        currentCreditCard.setEntertainmentRewards(Double.valueOf(entertainmentRewardField.getText()));
        currentCreditCard.setRecurringRewards(Double.valueOf(recurringRewardField.getText()));
    }

    private void getNewCardDetailsAndSave() {
        String cardName = cardNameField.getText();
        String rewardName = rewardNameField.getText();
        Double annualFee = Double.valueOf(annualFeeField.getText());
        Double generalSpending = Double.valueOf(generalRewardField.getText());
        Double travelSpending = Double.valueOf(travelRewardField.getText());
        Double grocerySpending = Double.valueOf(groceryRewardField.getText());
        Double restaurantSpending = Double.valueOf(restaurantRewardField.getText());
        Double gasSpending = Double.valueOf(gasRewardField.getText());
        Double drugStoreSpending = Double.valueOf(drugStoreRewardField.getText());
        Double transitSpending = Double.valueOf(transitRewardField.getText());
        Double entertainmentSpending = Double.valueOf(entertainmentRewardField.getText());
        Double recurringSpending = Double.valueOf(recurringRewardField.getText());
        CreditCard newCard = new CreditCard(cardName, rewardName, annualFee, generalSpending, travelSpending,
                grocerySpending, restaurantSpending, gasSpending, drugStoreSpending, transitSpending,
                entertainmentSpending, recurringSpending);
        Boolean wasAddSuccess = listOfCreditCards.addCreditCard(newCard);
        if (wasAddSuccess) {
            messageBanner.setText("Card added");
        } else {
            messageBanner.setText("There is already a card with that name. This one was not added.");
        }
    }

    private void refreshCardList() {
        listOfCreditCardNamesModel = new DefaultListModel();
        for (CreditCard c : listOfCreditCards.getListOfCreditCards()) {
            listOfCreditCardNamesModel.addElement(c.getCardName());
        }
        listOfCreditCardNames.setModel(listOfCreditCardNamesModel);
    }

    private void resetCreditCardDetailFields() {
        cardNameField.setText("N/A");
        rewardNameField.setText("N/A");
        annualFeeField.setText("N/A");
        generalRewardField.setText("N/A");
        travelRewardField.setText("N/A");
        groceryRewardField.setText("N/A");
        restaurantRewardField.setText("N/A");
        gasRewardField.setText("N/A");
        drugStoreRewardField.setText("N/A");
        transitRewardField.setText("N/A");
        entertainmentRewardField.setText("N/A");
        recurringRewardField.setText("N/A");
    }

}
