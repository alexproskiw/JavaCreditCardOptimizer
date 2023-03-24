package ui.tabs;

import ui.CreditCardManagerGraphical;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// A tab which allows the user to save/load their credit cards, rewards, and monthly spending
public class PersistenceTab extends Tab {

    private JPanel masterPanel;
    private JPanel centerButtonPanel;

    private JButton saveButton;
    private JButton loadButton;

    private JLabel messageBanner;

    // Effects: constructs a persistence tab with a master panel and save/load buttons
    public PersistenceTab(CreditCardManagerGraphical creditCardManagerGraphical) {
        super(creditCardManagerGraphical);
        loadMasterPanel();
    }

    // Modifies: this
    // Effects: adds a tab header at the top of the GUI
    @Override
    protected void addHeader() {
        JLabel header = new JLabel("Persistence Tab");
        header.setHorizontalAlignment(JLabel.CENTER);
        add(header, BorderLayout.PAGE_START);
    }

    // Modifies: this
    // Effects: adds a message banner at the bottom of the GUI
    @Override
    protected void addMessageBanner() {
        messageBanner = new JLabel();
        messageBanner.setText("Please click 'save' or 'load' to save or load your data");
        messageBanner.setHorizontalAlignment(JLabel.CENTER);
        add(messageBanner, BorderLayout.PAGE_END);
    }

    // Modifies: this
    // Effects: creates a master panel and adds it to the persistence tab, and load applicable buttons
    private void loadMasterPanel() {
        masterPanel = new JPanel();
        masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.PAGE_AXIS));
        loadCenterButtons();
        add(masterPanel, BorderLayout.CENTER);
    }

    // Modifies: this
    // Effects: Loads the save and load button. Adds them to the master panel.
    private void loadCenterButtons() {
        centerButtonPanel = new JPanel();
        loadSaveButton();
        loadLoadButton();
        masterPanel.add(centerButtonPanel);
    }

    // Modifies: this
    // Effects: Initializes saves button. Adds to the button panel.
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

    // Modifies: this
    // Effects: Initializes load button. Adds to the button panel.
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

    // Modifies: this
    // Effects: Process clicking on the save button by calling the saveData method in creditCardManagerGraphical
    //              which has access to all the program data
    private void handleClickOnSaveButton() {
        creditCardManagerGraphical.saveData();
        messageBanner.setText("Your data has been saved.");
    }

    // Modifies: this
    // Effects: Process clicking on the load button by calling the loadData method in creditCardManagerGraphical
    //              which has access to all the program data
    private void handleClickOnLoadButton() {
        creditCardManagerGraphical.loadData();
        messageBanner.setText("Your data has been loaded.");
    }

}