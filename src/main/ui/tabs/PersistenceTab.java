package ui.tabs;

import persistence.JsonReader;
import persistence.JsonWriter;
import ui.CreditCardManagerGraphical;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PersistenceTab extends Tab {


    private static final String JSON_STORE_CARDS = "./data/creditcards.json";
    private static final String JSON_STORE_REWARDS = "./data/rewardtypes.json";
    private static final String JSON_STORE_SPENDING = "./data/spending.json";

    private JPanel masterPanel;
    private JPanel centerButtonPanel;

    private JButton saveButton;
    private JButton loadButton;

    private JLabel messageBanner;

    public PersistenceTab(CreditCardManagerGraphical creditCardManagerGraphical) {
        super(creditCardManagerGraphical);
        loadMasterPanel();
    }

    @Override
    protected void addHeader() {
        JLabel header = new JLabel("Persistence Tab");
        header.setHorizontalAlignment(JLabel.CENTER);
        add(header, BorderLayout.PAGE_START);
    }

    @Override
    protected void addMessageBanner() {
        messageBanner = new JLabel();
        messageBanner.setText("Please click 'save' or 'load' to save or load your data");
        messageBanner.setHorizontalAlignment(JLabel.CENTER);
        add(messageBanner, BorderLayout.PAGE_END);
    }

    private void loadMasterPanel() {
        masterPanel = new JPanel();
        masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.PAGE_AXIS));
        loadCenterButtons();
        add(masterPanel, BorderLayout.CENTER);
    }

    private void loadCenterButtons() {
        centerButtonPanel = new JPanel();
        loadSaveButton();
        loadLoadButton();
        masterPanel.add(centerButtonPanel);
    }

    private void loadSaveButton() {
        saveButton = new JButton("Save Data");
        saveButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        handleClickOnSaveButton();
                    }
                }
        );
        centerButtonPanel.add(saveButton);
    }

    private void loadLoadButton() {
        loadButton = new JButton("Load Data");
        loadButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        handleClickOnLoadButton();
                    }
                }
        );
        centerButtonPanel.add(loadButton);
    }

    private void handleClickOnSaveButton() {
        creditCardManagerGraphical.saveData();
        messageBanner.setText("Your data has been saved.");
    }

    private void handleClickOnLoadButton() {
        creditCardManagerGraphical.loadData();
        messageBanner.setText("Your data has been loaded.");
    }

}