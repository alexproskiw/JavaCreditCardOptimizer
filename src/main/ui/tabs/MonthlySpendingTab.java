package ui.tabs;

import model.MonthlySpending;
import ui.CreditCardManagerGraphical;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public MonthlySpendingTab(CreditCardManagerGraphical creditCardManagerGraphical) {
        super(creditCardManagerGraphical);
        initializeSpendingDetailFields();
        loadMasterPanel();
        loadButtonStatesInitial();
    }

    @Override
    protected void addHeader() {
        JLabel header = new JLabel("Monthly Spending Tab");
        header.setHorizontalAlignment(JLabel.CENTER);
        add(header, BorderLayout.PAGE_START);
    }

    @Override
    protected void addMessageBanner() {
        messageBanner = new JLabel();
        messageBanner.setText("Please click 'edit' to enter your monthly spending");
        messageBanner.setHorizontalAlignment(JLabel.CENTER);
        add(messageBanner, BorderLayout.PAGE_END);
    }

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

    private void loadMasterPanel() {
        masterPanel = new JPanel();
        masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.PAGE_AXIS));
        listSpendingDetails();
        loadCenterButtons();
        add(masterPanel, BorderLayout.CENTER);
    }

    private void loadCenterButtons() {
        centerButtonPanel = new JPanel();
        loadSpendingEditButton();
        loadSpendingSaveChangesButton();
        masterPanel.add(centerButtonPanel);
    }

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

    private void createGeneralSpendingPanel() {
        generalSpendingPanel = new JPanel();
        generalSpendingPanel.add(GENERAL_SPENDING_LABEL);
        generalSpendingPanel.add(generalSpendingField);
        spendingDetailPanel.add(generalSpendingPanel);
    }

    private void createTravelSpendingPanel() {
        travelSpendingPanel = new JPanel();
        travelSpendingPanel.add(TRAVEL_SPENDING_LABEL);
        travelSpendingPanel.add(travelSpendingField);
        spendingDetailPanel.add(travelSpendingPanel);
    }

    private void createGrocerySpendingPanel() {
        grocerySpendingPanel = new JPanel();
        grocerySpendingPanel.add(GROCERY_SPENDING_LABEL);
        grocerySpendingPanel.add(grocerySpendingField);
        spendingDetailPanel.add(grocerySpendingPanel);
    }

    private void createRestaurantSpendingPanel() {
        restaurantSpendingPanel = new JPanel();
        restaurantSpendingPanel.add(RESTAURANT_SPENDING_LABEL);
        restaurantSpendingPanel.add(restaurantSpendingField);
        spendingDetailPanel.add(restaurantSpendingPanel);
    }

    private void createGasSpendingPanel() {
        gasSpendingPanel = new JPanel();
        gasSpendingPanel.add(GAS_SPENDING_LABEL);
        gasSpendingPanel.add(gasSpendingField);
        spendingDetailPanel.add(gasSpendingPanel);
    }

    private void createDrugStoreSpendingPanel() {
        drugStoreSpendingPanel = new JPanel();
        drugStoreSpendingPanel.add(DRUG_STORE_SPENDING_LABEL);
        drugStoreSpendingPanel.add(drugStoreSpendingField);
        spendingDetailPanel.add(drugStoreSpendingPanel);
    }

    private void createTransitSpendingPanel() {
        transitSpendingPanel = new JPanel();
        transitSpendingPanel.add(TRANSIT_SPENDING_LABEL);
        transitSpendingPanel.add(transitSpendingField);
        spendingDetailPanel.add(transitSpendingPanel);
    }

    private void createEntertainmentSpendingPanel() {
        entertainmentSpendingPanel = new JPanel();
        entertainmentSpendingPanel.add(ENTERTAINMENT_SPENDING_LABEL);
        entertainmentSpendingPanel.add(entertainmentSpendingField);
        spendingDetailPanel.add(entertainmentSpendingPanel);
    }

    private void createRecurringSpendingPanel() {
        recurringSpendingPanel = new JPanel();
        recurringSpendingPanel.add(RECURRING_SPENDING_LABEL);
        recurringSpendingPanel.add(recurringSpendingField);
        spendingDetailPanel.add(recurringSpendingPanel);
    }

    private void loadButtonStatesInitial() {
        spendingEditButton.setEnabled(true);
        spendingSaveChangesButton.setEnabled(false);
    }


    private void loadButtonStatesEdit() {
        spendingEditButton.setEnabled(false);
        spendingSaveChangesButton.setEnabled(true);
    }

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

    private void handleClickOnEditButton() {
        loadButtonStatesEdit();
        setSpendingDetailFieldsEditable();
        messageBanner.setText("Please update spending details and save");
    }

    private void handleClickOnSaveChangesButton() {
        getUpdatedSpendingDetailsAndSave();
        loadButtonStatesInitial();
        setSpendingDetailFieldsNotEditable();
        messageBanner.setText("Changes saved");
    }


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
