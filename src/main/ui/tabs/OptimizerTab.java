package ui.tabs;

import model.CreditCardOptimizer;
import ui.CreditCardManagerGraphical;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

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

    public OptimizerTab(CreditCardManagerGraphical creditCardManagerGraphical) {
        super(creditCardManagerGraphical);
        optimizer = new CreditCardOptimizer();
        df = new DecimalFormat("0.00");
        initializeOptimizerDetailFields();
        loadMasterPanel();
    }

    @Override
    protected void addHeader() {
        JLabel header = new JLabel("Optimizer Tab");
        header.setHorizontalAlignment(JLabel.CENTER);
        add(header, BorderLayout.PAGE_START);
    }

    @Override
    protected void addMessageBanner() {
        messageBanner = new JLabel();
        messageBanner.setText("Please click 'optimize' to determine your recommended credit card");
        messageBanner.setHorizontalAlignment(JLabel.CENTER);
        add(messageBanner, BorderLayout.PAGE_END);
    }

    private void initializeOptimizerDetailFields() {
        recommendedCardField = new JTextField(20);
        valueField = new JTextField(20);
        setOptimizerDetailFields();
    }

    private void loadMasterPanel() {
        masterPanel = new JPanel();
        masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.PAGE_AXIS));
        listOptimizerDetails();
        loadCenterButtons();
        loadOptimizerImagePanel();
        add(masterPanel, BorderLayout.CENTER);
    }

    private void loadCenterButtons() {
        centerButtonPanel = new JPanel();
        loadOptimizerButton();
        masterPanel.add(centerButtonPanel);
    }

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

    private void loadOptimizerImagePanel() {
        optimizeImagePanel = new JPanel();
        progressBar = new JProgressBar();
        progressBar.setPreferredSize(new Dimension(500, 100));
        progressBar.setStringPainted(true);
        optimizeImagePanel.add(progressBar);
        masterPanel.add(optimizeImagePanel);
        optimizeImagePanel.setVisible(false);
    }

    private void setOptimizerDetailFields() {
        recommendedCardField.setText(optimizer.getOptimalCard());
        valueField.setText(String.valueOf(optimizer.getMaxRewards()));
        recommendedCardField.setEditable(false);
        valueField.setEditable(false);
    }

    private void listOptimizerDetails() {
        optimizerDetailPanel = new JPanel();
        optimizerDetailPanel.setLayout(new BoxLayout(optimizerDetailPanel, BoxLayout.Y_AXIS));
        createRecommendedCardPanel();
        createValuePanel();
        masterPanel.add(optimizerDetailPanel);
    }

    private void createRecommendedCardPanel() {
        recommendedCardPanel = new JPanel();
        recommendedCardPanel.add(RECOMMENDED_CARD_LABEL);
        recommendedCardPanel.add(recommendedCardField);
        optimizerDetailPanel.add(recommendedCardPanel);
    }

    private void createValuePanel() {
        valuePanel = new JPanel();
        valuePanel.add(VALUE_LABEL);
        valuePanel.add(valueField);
        optimizerDetailPanel.add(valuePanel);
    }

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
