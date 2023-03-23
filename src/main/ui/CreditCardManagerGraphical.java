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
import java.text.DecimalFormat;


public class CreditCardManagerGraphical {

    private static final int CREDIT_CARD_TAB_INDEX = 0;
    private static final int REWARD_TAB_INDEX = 1;
    private static final int MONTHLY_SPENDING_TAB_INDEX = 2;
    private static final int OPTIMIZER_TAB_INDEX = 3;
    private static final int PERSISTENCE_TAB_INDEX = 4;
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 1000;
    private static final String TITLE = "Credit Card Manager";
    private static final String JSON_STORE_CARDS = "./data/creditcards.json";
    private static final String JSON_STORE_REWARDS = "./data/rewardtypes.json";
    private static final String JSON_STORE_SPENDING = "./data/spending.json";

    private JFrame frame;
    private JPanel creditCardTab;
    private JPanel rewardTab;
    private JPanel monthlySpendingTab;
    private JPanel optimizerTab;
    private JPanel persistenceTab;
    private JTabbedPane sidebar;

    private ListOfCreditCards listOfCreditCards;
    private ListOfRewardTypes listOfRewardTypes;
    private MonthlySpending monthlySpending;
    private CreditCardOptimizer optimizer;
    private DecimalFormat df;
    private JsonWriter jsonWriterCards;
    private JsonWriter jsonWriterRewards;
    private JsonWriter jsonWriterSpending;
    private JsonReader jsonReaderCards;
    private JsonReader jsonReaderRewards;
    private JsonReader jsonReaderSpending;

    public CreditCardManagerGraphical() {
        listOfCreditCards = new ListOfCreditCards(true);
        listOfRewardTypes = new ListOfRewardTypes(true);
        monthlySpending = new MonthlySpending();
        df = new DecimalFormat("0.00");
        initializeWritersAndReaders();

        setFrame();
        loadSidebar();
        loadTabs();
    }

    private void setFrame() {
        frame = new JFrame();
        frame.setTitle(TITLE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void loadSidebar() {
        sidebar = new JTabbedPane();
        sidebar.setTabPlacement(JTabbedPane.LEFT);
        frame.add(sidebar);
    }

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
