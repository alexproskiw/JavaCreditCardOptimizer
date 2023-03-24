package ui;

import model.CreditCardOptimizer;
import model.ListOfCreditCards;
import model.ListOfRewardTypes;
import model.MonthlySpending;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.tabs.*;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;

// Credit card manager application, graphics interface version
// Note: certain elements of the panels and tabs were modified from the CPSC 210 "smarthome" project
public class CreditCardManagerGraphical {

    private static final int CREDIT_CARD_TAB_INDEX = 0;
    private static final int REWARD_TAB_INDEX = 1;
    private static final int MONTHLY_SPENDING_TAB_INDEX = 2;
    private static final int OPTIMIZER_TAB_INDEX = 3;
    private static final int PERSISTENCE_TAB_INDEX = 4;
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 600;
    private static final String TITLE = "Credit Card Manager";
    private static final String JSON_STORE_CARDS = "./data/creditcards.json";
    private static final String JSON_STORE_REWARDS = "./data/rewardtypes.json";
    private static final String JSON_STORE_SPENDING = "./data/spending.json";

    private JFrame frame;
    private CreditCardTab creditCardTab;
    private RewardTab rewardTab;
    private MonthlySpendingTab monthlySpendingTab;
    private OptimizerTab optimizerTab;
    private PersistenceTab persistenceTab;
    private JTabbedPane sidebar;

    private ListOfCreditCards listOfCreditCards;
    private ListOfRewardTypes listOfRewardTypes;
    private MonthlySpending monthlySpending;

    private JsonWriter jsonWriterCards;
    private JsonWriter jsonWriterRewards;
    private JsonWriter jsonWriterSpending;
    private JsonReader jsonReaderCards;
    private JsonReader jsonReaderRewards;
    private JsonReader jsonReaderSpending;


    // Effects: constructs a new credit card manager tabbed GUI initialized with the default list of credit cards,
    //              the default list of reward types, and monthly spending set to $0 in all categories
    public CreditCardManagerGraphical() {
        listOfCreditCards = new ListOfCreditCards(true);
        listOfRewardTypes = new ListOfRewardTypes(true);
        monthlySpending = new MonthlySpending();

        setFrame();
        loadSidebar();
        loadTabs();
        initializeWritersAndReaders();
    }

    // Modifies: this
    // Effects: creates the overall frame for the GUI
    private void setFrame() {
        frame = new JFrame();
        frame.setTitle(TITLE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // Modifies: this
    // Effects: adds a tabbed sidebar to the left of the frame
    private void loadSidebar() {
        sidebar = new JTabbedPane();
        sidebar.setTabPlacement(JTabbedPane.LEFT);
        frame.add(sidebar);
    }

    // Modifies: this
    // Effects: Creates the separate credit card tab, reward tab, monthly spending tab, optimizer tab, and
    //              persistence tab. Adds them to the sidebar.
    private void loadTabs() {
        creditCardTab = new CreditCardTab(this);
        rewardTab = new RewardTab(this);
        monthlySpendingTab = new MonthlySpendingTab(this);
        optimizerTab = new OptimizerTab(this);
        persistenceTab = new PersistenceTab(this);

        sidebar.add(creditCardTab, CREDIT_CARD_TAB_INDEX);
        sidebar.setTitleAt(CREDIT_CARD_TAB_INDEX, "Credit Cards");
        sidebar.add(rewardTab, REWARD_TAB_INDEX);
        sidebar.setTitleAt(REWARD_TAB_INDEX, "Rewards");
        sidebar.add(monthlySpendingTab, MONTHLY_SPENDING_TAB_INDEX);
        sidebar.setTitleAt(MONTHLY_SPENDING_TAB_INDEX, "Monthly Spending");
        sidebar.add(optimizerTab, OPTIMIZER_TAB_INDEX);
        sidebar.setTitleAt(OPTIMIZER_TAB_INDEX, "Optimizer");
        sidebar.add(persistenceTab, PERSISTENCE_TAB_INDEX);
        sidebar.setTitleAt(PERSISTENCE_TAB_INDEX, "Save/Load");
    }

    // Modifies: this
    // Effects: initializes the separate json writers and readers for the list of credit cards,
    //              list of reward types, and monthly spending
    private void initializeWritersAndReaders() {
        jsonWriterCards = new JsonWriter(JSON_STORE_CARDS);
        jsonWriterRewards = new JsonWriter(JSON_STORE_REWARDS);
        jsonWriterSpending = new JsonWriter(JSON_STORE_SPENDING);
        jsonReaderCards = new JsonReader(JSON_STORE_CARDS);
        jsonReaderRewards = new JsonReader(JSON_STORE_REWARDS);
        jsonReaderSpending = new JsonReader(JSON_STORE_SPENDING);
    }

    // Effects: saves the current listOfCreditCards, listOfRewardTypes, and monthlySpending to file
    public void saveData() {
        saveCreditCards();
        saveRewardTypes();
        saveMonthlySpending();
    }

    // Effects: saves the current listOfCreditCards to file
    private void saveCreditCards() {
        try {
            jsonWriterCards.open();
            jsonWriterCards.write(listOfCreditCards);
            jsonWriterCards.close();
            System.out.println("Saved your current list of credit cards to " + JSON_STORE_CARDS);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_CARDS);
        }
    }

    // Effects: saves the current listOfRewardTypes to file
    private void saveRewardTypes() {
        try {
            jsonWriterRewards.open();
            jsonWriterRewards.write(listOfRewardTypes);
            jsonWriterRewards.close();
            System.out.println("Saved your current list of reward types to " + JSON_STORE_REWARDS);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_REWARDS);
        }
    }

    // Effects: saves the current monthlySpending to file
    private void saveMonthlySpending() {
        try {
            jsonWriterSpending.open();
            jsonWriterSpending.write(monthlySpending);
            jsonWriterSpending.close();
            System.out.println("Saved your current monthly spending to " + JSON_STORE_SPENDING);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_SPENDING);
        }
    }

    // Effects: loads listOfCreditCards, listOfRewardTypes, and monthlySpending from file.
    //              Also refresh the appropriate display for each tab to reflect these loaded changes.
    public void loadData() {
        loadCreditCards();
        loadRewardTypes();
        loadMonthlySpending();

        creditCardTab.refreshCardList();
        rewardTab.refreshRewardList();
        monthlySpendingTab.setSpendingDetailFields();
    }

    // Modifies: this
    // Effects: loads listOfCreditCards from file
    private void loadCreditCards() {
        try {
            listOfCreditCards = jsonReaderCards.readListOfCreditCards();
            System.out.println("Loaded the list of credit cards from " + JSON_STORE_CARDS);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_CARDS);
        }
    }

    // Modifies: this
    // Effects: loads listOfRewardTypes from file
    private void loadRewardTypes() {
        try {
            listOfRewardTypes = jsonReaderRewards.readListOfRewardTypes();
            System.out.println("Loaded the list of reward types from " + JSON_STORE_REWARDS);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_REWARDS);
        }
    }

    // Modifies: this
    // Effects: loads monthlySpending from file
    private void loadMonthlySpending() {
        try {
            monthlySpending = jsonReaderSpending.readMonthlySpending();
            System.out.println("Loaded the monthly spending from " + JSON_STORE_SPENDING);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_SPENDING);
        }
    }

    // Getters

    public ListOfCreditCards getListOfCreditCards() {
        return listOfCreditCards;
    }

    public ListOfRewardTypes getListOfRewardTypes() {
        return listOfRewardTypes;
    }

    public MonthlySpending getMonthlySpending() {
        return monthlySpending;
    }

}
