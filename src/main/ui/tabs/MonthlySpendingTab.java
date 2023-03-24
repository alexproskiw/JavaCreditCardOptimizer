package ui.tabs;

import model.MonthlySpending;
import ui.CreditCardManagerGraphical;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// A tab displaying all the users monthly spending in different categories,
//      and functionality for editing this spending
public class MonthlySpendingTab extends Tab {

    private static final JLabel GENERAL_SPENDING_LABEL = new JLabel("General Spending:");
    private static final JLabel TRAVEL_SPENDING_LABEL = new JLabel("Travel Spending:");
    private static final JLabel GROCERY_SPENDING_LABEL = new JLabel("Grocery Spending:");
    private static final JLabel RESTAURANT_SPENDING_LABEL = new JLabel("Restaurant Spending:");
    private static final JLabel GAS_SPENDING_LABEL = new JLabel("Gas Spending:");
    private static final JLabel DRUG_STORE_SPENDING_LABEL = new JLabel("Drug Store Spending:");
    private static final JLabel TRANSIT_SPENDING_LABEL = new JLabel("Transit Spending:");
    private static final JLabel ENTERTAINMENT_SPENDING_LABEL = new JLabel("Entertainment Spending:");
    private static final JLabel RECURRING_SPENDING_LABEL = new JLabel("Recurring Spending:");

    private JPanel masterPanel;
    private JPanel spendingDetailPanel;
    private JPanel centerButtonPanel;
    private JPanel generalSpendingPanel;
    private JPanel travelSpendingPanel;
    private JPanel grocerySpendingPanel;
    private JPanel restaurantSpendingPanel;
    private JPanel gasSpendingPanel;
    private JPanel drugStoreSpendingPanel;
    private JPanel transitSpendingPanel;
    private JPanel entertainmentSpendingPanel;
    private JPanel recurringSpendingPanel;

    private JButton spendingEditButton;
    private JButton spendingSaveChangesButton;

    private JTextField generalSpendingField;
    private JTextField travelSpendingField;
    private JTextField grocerySpendingField;
    private JTextField restaurantSpendingField;
    private JTextField gasSpendingField;
    private JTextField drugStoreSpendingField;
    private JTextField transitSpendingField;
    private JTextField entertainmentSpendingField;
    private JTextField recurringSpendingField;

    private JLabel messageBanner;

    // Effects: constructs a monthly spending tab with spending detail fields,
    //              a master panel, and edit/save buttons
    public MonthlySpendingTab(CreditCardManagerGraphical creditCardManagerGraphical) {
        super(creditCardManagerGraphical);
        initializeSpendingDetailFields();
        loadMasterPanel();
        loadButtonStatesInitial();
    }

    // Modifies: this
    // Effects: adds a tab header at the top of the GUI
    @Override
    protected void addHeader() {
        JLabel header = new JLabel("Monthly Spending Tab");
        header.setHorizontalAlignment(JLabel.CENTER);
        add(header, BorderLayout.PAGE_START);
    }

    // Modifies: this
    // Effects: adds a message banner at the bottom of the GUI
    @Override
    protected void addMessageBanner() {
        messageBanner = new JLabel();
        messageBanner.setText("Please click 'edit' to enter your monthly spending");
        messageBanner.setHorizontalAlignment(JLabel.CENTER);
        add(messageBanner, BorderLayout.PAGE_END);
    }

    // Effects: Initializes text fields for the monthly spending details, calls methods to set the fields to
    //              the value stored in the monthly spending object, and make the fields not editable
    private void initializeSpendingDetailFields() {
        generalSpendingField = new JTextField(20);
        travelSpendingField = new JTextField(20);
        grocerySpendingField = new JTextField(20);
        restaurantSpendingField = new JTextField(20);
        gasSpendingField = new JTextField(20);
        drugStoreSpendingField = new JTextField(20);
        transitSpendingField = new JTextField(20);
        entertainmentSpendingField = new JTextField(20);
        recurringSpendingField = new JTextField(20);
        setSpendingDetailFields();
        setSpendingDetailFieldsNotEditable();
    }

    // Modifies: this
    // Effects: creates a master panel and adds it to the monthly spending tab, calls methods to set up the
    //              spending details, and load applicable buttons
    private void loadMasterPanel() {
        masterPanel = new JPanel();
        masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.PAGE_AXIS));
        listSpendingDetails();
        loadCenterButtons();
        add(masterPanel, BorderLayout.CENTER);
    }

    // Modifies: this
    // Effects: Loads edit, and save changes buttons. Adds them to the master panel.
    private void loadCenterButtons() {
        centerButtonPanel = new JPanel();
        loadSpendingEditButton();
        loadSpendingSaveChangesButton();
        masterPanel.add(centerButtonPanel);
    }

    // Modifies: this
    // Effects: Initializes edit button. Adds to the button panel.
    private void loadSpendingEditButton() {
        spendingEditButton = new JButton("Edit Spending");
        spendingEditButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        handleClickOnEditButton();
                    }
                }
        );
        centerButtonPanel.add(spendingEditButton);
    }

    // Modifies: this
    // Effects: Initializes save changes button. Adds to right button panel.
    private void loadSpendingSaveChangesButton() {
        spendingSaveChangesButton = new JButton("Save Edits");
        spendingSaveChangesButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        handleClickOnSaveChangesButton();
                    }
                }
        );
        centerButtonPanel.add(spendingSaveChangesButton);
    }

    // Modifies: this
    // Effects: sets each monthly spending category equal to the value stored in the monthlySpending object
    public void setSpendingDetailFields() {
        generalSpendingField.setText(String.valueOf(
                creditCardManagerGraphical.getMonthlySpending().getGeneralSpending()));
        travelSpendingField.setText(String.valueOf(
                creditCardManagerGraphical.getMonthlySpending().getTravelSpending()));
        grocerySpendingField.setText(String.valueOf(
                creditCardManagerGraphical.getMonthlySpending().getGrocerySpending()));
        restaurantSpendingField.setText(String.valueOf(
                creditCardManagerGraphical.getMonthlySpending().getRestaurantSpending()));
        gasSpendingField.setText(String.valueOf(
                creditCardManagerGraphical.getMonthlySpending().getGasSpending()));
        drugStoreSpendingField.setText(String.valueOf(
                creditCardManagerGraphical.getMonthlySpending().getDrugStoreSpending()));
        transitSpendingField.setText(String.valueOf(
                creditCardManagerGraphical.getMonthlySpending().getTransitSpending()));
        entertainmentSpendingField.setText(String.valueOf(
                creditCardManagerGraphical.getMonthlySpending().getEntertainmentSpending()));
        recurringSpendingField.setText(String.valueOf(
                creditCardManagerGraphical.getMonthlySpending().getRecurringSpending()));
    }

    // Modifies: this
    // Effects: Creates a panel and calls methods to load in panels for the various monthly spending categories.
    private void listSpendingDetails() {
        spendingDetailPanel = new JPanel();
        spendingDetailPanel.setLayout(new BoxLayout(spendingDetailPanel, BoxLayout.Y_AXIS));
        createGeneralSpendingPanel();
        createTravelSpendingPanel();
        createGrocerySpendingPanel();
        createRestaurantSpendingPanel();
        createGasSpendingPanel();
        createDrugStoreSpendingPanel();
        createTransitSpendingPanel();
        createEntertainmentSpendingPanel();
        createRecurringSpendingPanel();
        masterPanel.add(spendingDetailPanel);
    }

    // Modifies: this
    // Effects: Initializes a panel to display the general spending
    private void createGeneralSpendingPanel() {
        generalSpendingPanel = new JPanel();
        generalSpendingPanel.add(GENERAL_SPENDING_LABEL);
        generalSpendingPanel.add(generalSpendingField);
        spendingDetailPanel.add(generalSpendingPanel);
    }

    // Modifies: this
    // Effects: Initializes a panel to display the travel spending
    private void createTravelSpendingPanel() {
        travelSpendingPanel = new JPanel();
        travelSpendingPanel.add(TRAVEL_SPENDING_LABEL);
        travelSpendingPanel.add(travelSpendingField);
        spendingDetailPanel.add(travelSpendingPanel);
    }

    // Modifies: this
    // Effects: Initializes a panel to display the grocery spending
    private void createGrocerySpendingPanel() {
        grocerySpendingPanel = new JPanel();
        grocerySpendingPanel.add(GROCERY_SPENDING_LABEL);
        grocerySpendingPanel.add(grocerySpendingField);
        spendingDetailPanel.add(grocerySpendingPanel);
    }

    // Modifies: this
    // Effects: Initializes a panel to display the restaurant spending
    private void createRestaurantSpendingPanel() {
        restaurantSpendingPanel = new JPanel();
        restaurantSpendingPanel.add(RESTAURANT_SPENDING_LABEL);
        restaurantSpendingPanel.add(restaurantSpendingField);
        spendingDetailPanel.add(restaurantSpendingPanel);
    }

    // Modifies: this
    // Effects: Initializes a panel to display the gas spending
    private void createGasSpendingPanel() {
        gasSpendingPanel = new JPanel();
        gasSpendingPanel.add(GAS_SPENDING_LABEL);
        gasSpendingPanel.add(gasSpendingField);
        spendingDetailPanel.add(gasSpendingPanel);
    }

    // Modifies: this
    // Effects: Initializes a panel to display the drug store spending
    private void createDrugStoreSpendingPanel() {
        drugStoreSpendingPanel = new JPanel();
        drugStoreSpendingPanel.add(DRUG_STORE_SPENDING_LABEL);
        drugStoreSpendingPanel.add(drugStoreSpendingField);
        spendingDetailPanel.add(drugStoreSpendingPanel);
    }

    // Modifies: this
    // Effects: Initializes a panel to display the transit spending
    private void createTransitSpendingPanel() {
        transitSpendingPanel = new JPanel();
        transitSpendingPanel.add(TRANSIT_SPENDING_LABEL);
        transitSpendingPanel.add(transitSpendingField);
        spendingDetailPanel.add(transitSpendingPanel);
    }

    // Modifies: this
    // Effects: Initializes a panel to display the entertainment spending
    private void createEntertainmentSpendingPanel() {
        entertainmentSpendingPanel = new JPanel();
        entertainmentSpendingPanel.add(ENTERTAINMENT_SPENDING_LABEL);
        entertainmentSpendingPanel.add(entertainmentSpendingField);
        spendingDetailPanel.add(entertainmentSpendingPanel);
    }

    // Modifies: this
    // Effects: Initializes a panel to display the recurring spending
    private void createRecurringSpendingPanel() {
        recurringSpendingPanel = new JPanel();
        recurringSpendingPanel.add(RECURRING_SPENDING_LABEL);
        recurringSpendingPanel.add(recurringSpendingField);
        spendingDetailPanel.add(recurringSpendingPanel);
    }

    // Modifies: this
    // Effects: Sets buttons to the initial state (only edit)
    private void loadButtonStatesInitial() {
        spendingEditButton.setEnabled(true);
        spendingSaveChangesButton.setEnabled(false);
    }

    // Modifies: this
    // Effects: Sets buttons to the state once the spending is being edited (allowing save edits)
    private void loadButtonStatesEdit() {
        spendingEditButton.setEnabled(false);
        spendingSaveChangesButton.setEnabled(true);
    }

    // Modifies: this
    // Effects: Prevents any of the spending fields from being edited
    private void setSpendingDetailFieldsNotEditable() {
        generalSpendingField.setEditable(false);
        travelSpendingField.setEditable(false);
        grocerySpendingField.setEditable(false);
        restaurantSpendingField.setEditable(false);
        gasSpendingField.setEditable(false);
        drugStoreSpendingField.setEditable(false);
        transitSpendingField.setEditable(false);
        entertainmentSpendingField.setEditable(false);
        recurringSpendingField.setEditable(false);
    }

    // Modifies: this
    // Effects: Allows all the spending fields to be edited
    private void setSpendingDetailFieldsEditable() {
        generalSpendingField.setEditable(true);
        travelSpendingField.setEditable(true);
        grocerySpendingField.setEditable(true);
        restaurantSpendingField.setEditable(true);
        gasSpendingField.setEditable(true);
        drugStoreSpendingField.setEditable(true);
        transitSpendingField.setEditable(true);
        entertainmentSpendingField.setEditable(true);
        recurringSpendingField.setEditable(true);
    }

    // Effects: Process clicking on the edit button by updating the appropriate buttons and
    //              making fields editable
    private void handleClickOnEditButton() {
        loadButtonStatesEdit();
        setSpendingDetailFieldsEditable();
        messageBanner.setText("Please update spending details and save");
    }

    // Modifies: this
    // Effects: Process clicking on the save changes button by calling a helper method,
    //              updating the appropriate buttons, and making the fields no longer editable
    private void handleClickOnSaveChangesButton() {
        getUpdatedSpendingDetailsAndSave();
        loadButtonStatesInitial();
        setSpendingDetailFieldsNotEditable();
        messageBanner.setText("Changes saved");
    }

    // Requires: all spending entries must be doubles
    // Modifies: this
    // Effects: updates the monthly spending parameters with the values entered in the text fields
    private void getUpdatedSpendingDetailsAndSave() {
        creditCardManagerGraphical.getMonthlySpending().setGeneralSpending(
                Double.valueOf(generalSpendingField.getText()));
        creditCardManagerGraphical.getMonthlySpending().setTravelSpending(
                Double.valueOf(travelSpendingField.getText()));
        creditCardManagerGraphical.getMonthlySpending().setGrocerySpending(
                Double.valueOf(grocerySpendingField.getText()));
        creditCardManagerGraphical.getMonthlySpending().setRestaurantSpending(
                Double.valueOf(restaurantSpendingField.getText()));
        creditCardManagerGraphical.getMonthlySpending().setGasSpending(
                Double.valueOf(gasSpendingField.getText()));
        creditCardManagerGraphical.getMonthlySpending().setDrugStoreSpending(
                Double.valueOf(drugStoreSpendingField.getText()));
        creditCardManagerGraphical.getMonthlySpending().setTransitSpending(
                Double.valueOf(transitSpendingField.getText()));
        creditCardManagerGraphical.getMonthlySpending().setEntertainmentSpending(
                Double.valueOf(entertainmentSpendingField.getText()));
        creditCardManagerGraphical.getMonthlySpending().setRecurringSpending(
                Double.valueOf(recurringSpendingField.getText()));
    }

}
