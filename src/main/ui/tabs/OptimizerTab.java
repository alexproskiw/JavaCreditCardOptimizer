package ui.tabs;

import model.CreditCardOptimizer;
import ui.CreditCardManagerGraphical;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

// A tab which recommends the best card for the user based on their unique spending
public class OptimizerTab extends Tab {

    private static final JLabel RECOMMENDED_CARD_LABEL = new JLabel("Your recommended card is:");
    private static final JLabel VALUE_LABEL = new JLabel("With your spending, "
            + "each year you will get a net value of $");
    private static final int DELAY = 500;

    private JPanel masterPanel;
    private JPanel optimizerDetailPanel;
    private JPanel centerButtonPanel;
    private JPanel recommendedCardPanel;
    private JPanel valuePanel;
    private JPanel optimizeImagePanel;

    private JButton optimizerButton;
    private JProgressBar progressBar;

    private JTextField recommendedCardField;
    private JTextField valueField;

    private JLabel messageBanner;

    private CreditCardOptimizer optimizer;
    private DecimalFormat df;

    // Effects: constructs an optimizer tab with recommendation fields,
    //              a master panel, an optimize buttons, and a visual loading bar
    public OptimizerTab(CreditCardManagerGraphical creditCardManagerGraphical) {
        super(creditCardManagerGraphical);
        optimizer = new CreditCardOptimizer();
        df = new DecimalFormat("0.00");
        initializeOptimizerDetailFields();
        loadMasterPanel();
    }

    // Modifies: this
    // Effects: adds a tab header at the top of the GUI
    @Override
    protected void addHeader() {
        JLabel header = new JLabel("Optimizer Tab");
        header.setHorizontalAlignment(JLabel.CENTER);
        add(header, BorderLayout.PAGE_START);
    }

    // Modifies: this
    // Effects: adds a message banner at the bottom of the GUI
    @Override
    protected void addMessageBanner() {
        messageBanner = new JLabel();
        messageBanner.setText("Please click 'optimize' to determine your recommended credit card");
        messageBanner.setHorizontalAlignment(JLabel.CENTER);
        add(messageBanner, BorderLayout.PAGE_END);
    }

    // Effects: Initializes text fields for the recommended card and value, calls methods to set the fields to
    //              the value stored in the optimizer object
    private void initializeOptimizerDetailFields() {
        recommendedCardField = new JTextField(20);
        valueField = new JTextField(20);
        setOptimizerDetailFields();
    }

    // Modifies: this
    // Effects: creates a master panel and adds it to the optimizer tab, calls methods to set up the
    //              optimizer details, load applicable buttons, and load the image panel
    private void loadMasterPanel() {
        masterPanel = new JPanel();
        masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.PAGE_AXIS));
        listOptimizerDetails();
        loadCenterButtons();
        loadOptimizerImagePanel();
        add(masterPanel, BorderLayout.CENTER);
    }

    // Modifies: this
    // Effects: Loads the optimize button. Adds it to the master panel.
    private void loadCenterButtons() {
        centerButtonPanel = new JPanel();
        loadOptimizerButton();
        masterPanel.add(centerButtonPanel);
    }

    // Modifies: this
    // Effects: Initializes optimize button. Adds to the button panel.
    private void loadOptimizerButton() {
        optimizerButton = new JButton("Run Optimizer");
        optimizerButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        handleClickOnOptimizerButton();
                    }
                }
        );
        centerButtonPanel.add(optimizerButton);
    }

    // Modifies: this
    // Effects: Initializes the image panel with a progress bar. Adds to the master panel.
    //              Sets visibility to false.
    private void loadOptimizerImagePanel() {
        optimizeImagePanel = new JPanel();
        progressBar = new JProgressBar();
        progressBar.setPreferredSize(new Dimension(500, 100));
        progressBar.setStringPainted(true);
        optimizeImagePanel.add(progressBar);
        masterPanel.add(optimizeImagePanel);
        optimizeImagePanel.setVisible(false);
    }

    // Modifies: this
    // Effects: sets the recommended card and value fields equal to the value stored in the monthlySpending object,
    //              also sets the fields not editable
    private void setOptimizerDetailFields() {
        recommendedCardField.setText(optimizer.getOptimalCard());
        valueField.setText(String.valueOf(optimizer.getMaxRewards()));
        recommendedCardField.setEditable(false);
        valueField.setEditable(false);
    }

    // Modifies: this
    // Effects: Creates a panel and calls methods to load in panels for the recommended card and value.
    private void listOptimizerDetails() {
        optimizerDetailPanel = new JPanel();
        optimizerDetailPanel.setLayout(new BoxLayout(optimizerDetailPanel, BoxLayout.Y_AXIS));
        createRecommendedCardPanel();
        createValuePanel();
        masterPanel.add(optimizerDetailPanel);
    }

    // Modifies: this
    // Effects: Initializes a panel to display the recommended card
    private void createRecommendedCardPanel() {
        recommendedCardPanel = new JPanel();
        recommendedCardPanel.add(RECOMMENDED_CARD_LABEL);
        recommendedCardPanel.add(recommendedCardField);
        optimizerDetailPanel.add(recommendedCardPanel);
    }

    // Modifies: this
    // Effects: Initializes a panel to display the value of the recommended card
    private void createValuePanel() {
        valuePanel = new JPanel();
        valuePanel.add(VALUE_LABEL);
        valuePanel.add(valueField);
        optimizerDetailPanel.add(valuePanel);
    }

    // Modifies: this
    // Effects: Process clicking on the optimize button by making the progress bar visible, visually updating the
    //              progress bar to simulate the computer "thinking", and then displaying the optimizer results
    private void handleClickOnOptimizerButton() {
        optimizeImagePanel.setVisible(true);
        Runnable updateProgressBar = new Runnable() {
            public void run() {
                int i = 0;
                while (i < 100) {
                    progressBar.setValue(i + 10);
                    try {
                        Thread.sleep(DELAY);
                    } catch (Exception e) {
                        //
                    }
                    i += 10;
                }
                if (i == 100) {
                    displayOptimizeResult();
                }
            }
        };
        Thread t = new Thread(updateProgressBar);
        t.start();
    }

    // Modifies: this
    // Effects: hides the progress bar, updates the recommended card and value with the values from the optimizer
    //              object, and resets the progress bar to 0 for future optimizations
    private void displayOptimizeResult() {
        optimizeImagePanel.setVisible(false);
        recommendedCardField.setEditable(true);
        valueField.setEditable(true);
        messageBanner.setText("Your recommended card has been determined!");
        optimizer.calculateMaxRewards(creditCardManagerGraphical.getListOfCreditCards(),
                creditCardManagerGraphical.getListOfRewardTypes(),
                creditCardManagerGraphical.getMonthlySpending());
        recommendedCardField.setText(optimizer.getOptimalCard());
        valueField.setText(String.valueOf(df.format(optimizer.getMaxRewards())));
        progressBar.setValue(0);
        recommendedCardField.setEditable(false);
        valueField.setEditable(false);
    }

}
