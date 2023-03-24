package ui.tabs;

import model.CreditCard;
import ui.CreditCardManagerGraphical;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// A tab displaying all credit cards, their details, and functionality for editing, adding, and removing cards
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

    private CreditCard currentCreditCard;

    // Effects: constructs a credit card tab with card detail fields initialized to "N/A", a left and right panel,
    //              and various buttons
    public CreditCardTab(CreditCardManagerGraphical creditCardManagerGraphical) {
        super(creditCardManagerGraphical);
        initializeCardDetailFields();
        loadMasterPanel();
        loadLeftPanel();
        loadRightPanel();
        loadButtonStatesInitial();
    }

    // Modifies: this
    // Effects: adds a tab header at the top of the GUI
    @Override
    protected void addHeader() {
        JLabel header = new JLabel("Credit Card Tab");
        header.setHorizontalAlignment(JLabel.CENTER);
        add(header, BorderLayout.PAGE_START);
    }

    // Modifies: this
    // Effects: adds a message banner at the bottom of the GUI
    @Override
    protected void addMessageBanner() {
        messageBanner = new JLabel();
        messageBanner.setText("Please select a card for more options, or click 'add' to add a new card");
        messageBanner.setHorizontalAlignment(JLabel.CENTER);
        add(messageBanner, BorderLayout.PAGE_END);
    }

    // Effects: Initializes text fields for the details for a credit card, calls methods to set the fields to
    //              "N/A" and make the fields not editable
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

    // Modifies: this
    // Effects: creates a master panel and adds it to the credit card tab
    private void loadMasterPanel() {
        masterPanel = new JPanel();
        masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.LINE_AXIS));
        add(masterPanel, BorderLayout.CENTER);
    }

    // Modifies: this
    // Effects: creates a left panel and adds it to the master panel, calls methods to set up the credit card list
    //              and load applicable buttons
    protected void loadLeftPanel() {
        leftPanel = new JPanel();
        setUpCreditCardListPanel();
        loadLeftButtons();
        masterPanel.add(leftPanel);
    }

    // Modifies: this
    // Effects: creates a right panel and adds it to the master panel, calls methods to set up the credit card details
    //              and load applicable buttons
    protected void loadRightPanel() {
        rightPanel = new JPanel();
        listCreditCardDetails();
        loadRightButtons();
        masterPanel.add(rightPanel);
    }

    // Modifies: this
    // Effects: Loads edit, add, and remove buttons. Adds them to the left panel.
    private void loadLeftButtons() {
        leftButtonPanel = new JPanel();
        loadCreditCardEditButton();
        loadCreditCardAddButton();
        loadCreditCardRemoveButton();
        leftPanel.add(leftButtonPanel);
    }

    // Modifies: this
    // Effects: Loads save changes, confirm add, and confirm remove buttons. Adds them to the right panel.
    private void loadRightButtons() {
        rightButtonPanel = new JPanel();
        loadCreditCardSaveChangesButton();
        loadCreditCardConfirmAddButton();
        loadCreditCardConfirmRemoveButton();
        rightPanel.add(rightButtonPanel);
    }

    // Modifies: this
    // Effects: Initializes edit button. Adds to left button panel.
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

    // Modifies: this
    // Effects: Initializes add button. Adds to left button panel.
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

    // Modifies: this
    // Effects: Initializes remove button. Adds to left button panel.
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

    // Modifies: this
    // Effects: Initializes save changes button. Adds to right button panel.
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

    // Modifies: this
    // Effects: Initializes confirm add button. Adds to right button panel.
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

    // Modifies: this
    // Effects: Initializes confirm remove button. Adds to right button panel.
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

    // Modifies: this
    // Effects: Creates a panel and loads a scrollable list of credit cards to it.
    //              Assigns this panel to the left panel.
    private void setUpCreditCardListPanel() {
        creditCardListPanel = new JPanel();
        loadCardNamesToScrollableList();
        leftPanel.add(creditCardListPanel);
    }

    // Modifies: this
    // Effects: Creates a scrollable list of all the credit card names and adds the list to the panel.
    //              Calls the method to identify when a given credit card is clicked.
    private void loadCardNamesToScrollableList() {
        listOfCreditCardNames = new JList();
        listOfCreditCardNamesModel = new DefaultListModel();
        for (CreditCard c : creditCardManagerGraphical.getListOfCreditCards().getListOfCreditCards()) {
            listOfCreditCardNamesModel.addElement(c.getCardName());
        }
        listOfCreditCardNames.setModel(listOfCreditCardNamesModel);
        scrollPane = new JScrollPane(listOfCreditCardNames);
        creditCardListPanel.add(scrollPane);
        listenForSelectedCreditCard();
    }

    // Effects: adds a method to respond to a given credit card in the list being clicked
    private void listenForSelectedCreditCard() {
        listOfCreditCardNames.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent evt) {
                handleNewCreditCardSelected(evt);
            }
        });
    }

    // Modifies: this
    // Effects: Takes the selected credit card and sets it to the "currentCreditCard". Calls a method to
    //              show the details for the selected credit card. Also updates buttons as appropriate.
    private void handleNewCreditCardSelected(javax.swing.event.ListSelectionEvent evt) {
        String s = (String) listOfCreditCardNames.getSelectedValue();
        for (CreditCard c : creditCardManagerGraphical.getListOfCreditCards().getListOfCreditCards()) {
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

    // Modifies: this
    // Effects: Fills in card detail fields with info from the "currentCreditCard" variable
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

    // Modifies: this
    // Effects: Creates a panel and calls methods to load in panels for the various credit card details.
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

    // Modifies: this
    // Effects: Initializes a panel to display the credit card name
    private void createCardNamePanel() {
        cardNamePanel = new JPanel();
        cardNamePanel.add(CARD_NAME_LABEL);
        cardNamePanel.add(cardNameField);
        cardDetailPanel.add(cardNamePanel);
    }

    // Modifies: this
    // Effects: Initializes a panel to display the credit card reward name
    private void createRewardNamePanel() {
        rewardNamePanel = new JPanel();
        rewardNamePanel.add(REWARD_NAME_LABEL);
        rewardNamePanel.add(rewardNameField);
        cardDetailPanel.add(rewardNamePanel);
    }

    // Modifies: this
    // Effects: Initializes a panel to display the credit card annual fee
    private void createAnnualFeePanel() {
        annualFeePanel = new JPanel();
        annualFeePanel.add(ANNUAL_FEE_LABEL);
        annualFeePanel.add(annualFeeField);
        cardDetailPanel.add(annualFeePanel);
    }

    // Modifies: this
    // Effects: Initializes a panel to display the credit card general reward points
    private void createGeneralRewardPanel() {
        generalRewardPanel = new JPanel();
        generalRewardPanel.add(GENERAL_REWARDS_LABEL);
        generalRewardPanel.add(generalRewardField);
        cardDetailPanel.add(generalRewardPanel);
    }

    // Modifies: this
    // Effects: Initializes a panel to display the credit card travel reward points
    private void createTravelRewardPanel() {
        travelRewardPanel = new JPanel();
        travelRewardPanel.add(TRAVEL_REWARDS_LABEL);
        travelRewardPanel.add(travelRewardField);
        cardDetailPanel.add(travelRewardPanel);
    }

    // Modifies: this
    // Effects: Initializes a panel to display the credit card grocery reward points
    private void createGroceryRewardPanel() {
        groceryRewardPanel = new JPanel();
        groceryRewardPanel.add(GROCERY_REWARDS_LABEL);
        groceryRewardPanel.add(groceryRewardField);
        cardDetailPanel.add(groceryRewardPanel);
    }

    // Modifies: this
    // Effects: Initializes a panel to display the credit card restaurant reward points
    private void createRestaurantRewardPanel() {
        restaurantRewardPanel = new JPanel();
        restaurantRewardPanel.add(RESTAURANT_REWARDS_LABEL);
        restaurantRewardPanel.add(restaurantRewardField);
        cardDetailPanel.add(restaurantRewardPanel);
    }

    // Modifies: this
    // Effects: Initializes a panel to display the credit card gas reward points
    private void createGasRewardPanel() {
        gasRewardPanel = new JPanel();
        gasRewardPanel.add(GAS_REWARDS_LABEL);
        gasRewardPanel.add(gasRewardField);
        cardDetailPanel.add(gasRewardPanel);
    }

    // Modifies: this
    // Effects: Initializes a panel to display the credit card drug store reward points
    private void createDrugStoreRewardPanel() {
        drugStoreRewardPanel = new JPanel();
        drugStoreRewardPanel.add(DRUG_STORE_REWARDS_LABEL);
        drugStoreRewardPanel.add(drugStoreRewardField);
        cardDetailPanel.add(drugStoreRewardPanel);
    }

    // Modifies: this
    // Effects: Initializes a panel to display the credit card transit reward points
    private void createTransitRewardPanel() {
        transitRewardPanel = new JPanel();
        transitRewardPanel.add(TRANSIT_REWARDS_LABEL);
        transitRewardPanel.add(transitRewardField);
        cardDetailPanel.add(transitRewardPanel);
    }

    // Modifies: this
    // Effects: Initializes a panel to display the credit card entertainment reward points
    private void createEntertainmentRewardPanel() {
        entertainmentRewardPanel = new JPanel();
        entertainmentRewardPanel.add(ENTERTAINMENT_REWARDS_LABEL);
        entertainmentRewardPanel.add(entertainmentRewardField);
        cardDetailPanel.add(entertainmentRewardPanel);
    }

    // Modifies: this
    // Effects: Initializes a panel to display the credit card recurring reward points
    private void createRecurringRewardPanel() {
        recurringRewardPanel = new JPanel();
        recurringRewardPanel.add(RECURRING_REWARDS_LABEL);
        recurringRewardPanel.add(recurringRewardField);
        cardDetailPanel.add(recurringRewardPanel);
    }

    // Modifies: this
    // Effects: Sets buttons to the initial state (only add)
    private void loadButtonStatesInitial() {
        creditCardEditButton.setEnabled(false);
        creditCardAddButton.setEnabled(true);
        creditCardRemoveButton.setEnabled(false);
        creditCardSaveChangesButton.setEnabled(false);
        creditCardConfirmAddButton.setEnabled(false);
        creditCardConfirmRemoveButton.setEnabled(false);
    }

    // Modifies: this
    // Effects: Sets buttons to the state once a card is selected from the list (allowing edit/add/remove)
    private void loadButtonStatesOnCardClick() {
        creditCardEditButton.setEnabled(true);
        creditCardAddButton.setEnabled(true);
        creditCardRemoveButton.setEnabled(true);
        creditCardSaveChangesButton.setEnabled(false);
        creditCardConfirmAddButton.setEnabled(false);
        creditCardConfirmRemoveButton.setEnabled(false);
    }

    // Modifies: this
    // Effects: Sets buttons to the state once a card is being edited (allowing save edits)
    private void loadButtonStatesEdit() {
        creditCardEditButton.setEnabled(false);
        creditCardAddButton.setEnabled(false);
        creditCardRemoveButton.setEnabled(false);
        creditCardSaveChangesButton.setEnabled(true);
        creditCardConfirmAddButton.setEnabled(false);
        creditCardConfirmRemoveButton.setEnabled(false);
    }

    // Modifies: this
    // Effects: Sets buttons to the state once a card is being added (allowing confirm add)
    private void loadButtonStatesAdd() {
        creditCardEditButton.setEnabled(false);
        creditCardAddButton.setEnabled(false);
        creditCardRemoveButton.setEnabled(false);
        creditCardSaveChangesButton.setEnabled(false);
        creditCardConfirmAddButton.setEnabled(true);
        creditCardConfirmRemoveButton.setEnabled(false);
    }

    // Modifies: this
    // Effects: Sets buttons to the state once a card is being removed (allowing confirm remove)
    private void loadButtonStatesRemove() {
        creditCardEditButton.setEnabled(false);
        creditCardAddButton.setEnabled(false);
        creditCardRemoveButton.setEnabled(false);
        creditCardSaveChangesButton.setEnabled(false);
        creditCardConfirmAddButton.setEnabled(false);
        creditCardConfirmRemoveButton.setEnabled(true);
    }

    // Modifies: this
    // Effects: Prevents any of the credit card fields from being edited
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

    // Modifies: this
    // Effects: Allows all the credit card fields to be edited
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

    // Modifies: this
    // Effects: Allows all the credit card fields to be edited except for the card name
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

    // Effects: Process clicking on the edit button by updating the appropriate buttons and
    //              making fields editable except for the card name
    private void handleClickOnEditButton() {
        loadButtonStatesEdit();
        setCardDetailFieldsEditableExceptCardName();
        messageBanner.setText("Please update card details and save");
    }

    // Effects: Process clicking on the add button by resetting all fields, updating the appropriate buttons,
    //              and making fields editable
    private void handleClickOnAddButton() {
        resetCreditCardDetailFields();
        loadButtonStatesAdd();
        setCardDetailFieldsEditable();
        messageBanner.setText("Please enter card details and confirm add");
    }

    // Effects: Process clicking on the remove button by updating the appropriate buttons (2-step removal process)
    private void handleClickOnRemoveButton() {
        loadButtonStatesRemove();
        messageBanner.setText("Please confirm you want to remove this card");
    }

    // Modifies: this
    // Effects: Process clicking on the save changes button by calling a helper method, refreshing the
    //              credit card list, updating the appropriate buttons, and making the fields no longer editable
    private void handleClickOnSaveChangesButton() {
        getUpdatedCardDetailsAndSave();
        refreshCardList();
        loadButtonStatesInitial();
        setCardDetailFieldsNotEditable();
        messageBanner.setText("Changes saved");
    }

    // Modifies: this
    // Effects: Process clicking on the confirm add button by calling a helper method, refreshing the
    //              credit card list, updating the appropriate buttons, and making the fields no longer editable
    private void handleClickOnConfirmAddButton() {
        getNewCardDetailsAndSave();
        refreshCardList();
        loadButtonStatesInitial();
        setCardDetailFieldsNotEditable();
    }

    // Modifies: this
    // Effects: Process clicking on the confirm remove button by removing the selected card, refreshing the
    //              credit card list, updating the appropriate buttons, and resetting the field text
    private void handleClickOnConfirmRemoveButton() {
        creditCardManagerGraphical.getListOfCreditCards().removeCreditCard(currentCreditCard.getCardName());
        refreshCardList();
        loadButtonStatesInitial();
        resetCreditCardDetailFields();
        messageBanner.setText("Card removed");
    }

    // Requires: rewardNameField must be an existing RewardType, and all subsequent fields must be filled with Doubles
    // Modifies: this
    // Effects: for the selected credit card, updates its parameters with the values entered in the text fields
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

    // Requires: rewardNameField must be an existing RewardType, and all subsequent fields must be filled with Doubles
    // Modifies: this
    // Effects: creates a new credit card with the values entered in the text fields, unless the card name
    //              is already taken in which case the addition of the new card fails
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
        Boolean wasAddSuccess = creditCardManagerGraphical.getListOfCreditCards().addCreditCard(newCard);
        if (wasAddSuccess) {
            messageBanner.setText("Card added");
        } else {
            messageBanner.setText("There is already a card with that name. This one was not added.");
        }
    }

    // Modifies: this
    // Effects: updates the scrollable list of credit cards to reflect any edits/additions/removals
    public void refreshCardList() {
        listOfCreditCardNamesModel = new DefaultListModel();
        for (CreditCard c : creditCardManagerGraphical.getListOfCreditCards().getListOfCreditCards()) {
            listOfCreditCardNamesModel.addElement(c.getCardName());
        }
        listOfCreditCardNames.setModel(listOfCreditCardNamesModel);
    }

    // Modifies: this
    // Effects: set all credit card fields to "N/A"
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
