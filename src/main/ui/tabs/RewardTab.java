package ui.tabs;

import model.RewardType;
import ui.CreditCardManagerGraphical;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// A tab displaying all rewards, their details, and functionality for editing, adding, and removing rewards
public class RewardTab extends Tab {

    private static final JLabel REWARD_NAME_LABEL = new JLabel("Reward:");
    private static final JLabel REWARD_VALUE_LABEL = new JLabel("Reward Value:");

    private JPanel masterPanel;
    private JPanel leftPanel;
    private JPanel rewardListPanel;
    private JPanel leftButtonPanel;
    private JPanel rightPanel;
    private JPanel rewardDetailPanel;
    private JPanel rightButtonPanel;
    private JPanel rewardNamePanel;
    private JPanel rewardValuePanel;

    private JButton rewardEditButton;
    private JButton rewardAddButton;
    private JButton rewardRemoveButton;
    private JButton rewardSaveChangesButton;
    private JButton rewardConfirmAddButton;
    private JButton rewardConfirmRemoveButton;

    private JTextField rewardNameField;
    private JTextField rewardValueField;

    private JLabel messageBanner;
    private JScrollPane scrollPane;
    private JList listOfRewardNames;
    private DefaultListModel<String> listOfRewardNamesModel;

    private RewardType currentReward;

    // Effects: constructs a reward tab with reward detail fields initialized to "N/A", a left and right panel,
    //              and various buttons
    public RewardTab(CreditCardManagerGraphical creditCardManagerGraphical) {
        super(creditCardManagerGraphical);
        initializeRewardDetailFields();
        loadMasterPanel();
        loadLeftPanel();
        loadRightPanel();
        loadButtonStatesInitial();
    }

    // Modifies: this
    // Effects: adds a tab header at the top of the GUI
    @Override
    protected void addHeader() {
        JLabel header = new JLabel("Reward Tab");
        header.setHorizontalAlignment(JLabel.CENTER);
        add(header, BorderLayout.PAGE_START);
    }

    // Modifies: this
    // Effects: adds a message banner at the bottom of the GUI
    @Override
    protected void addMessageBanner() {
        messageBanner = new JLabel();
        messageBanner.setText("Please select a reward for more options, or click 'add' to add a new reward");
        messageBanner.setHorizontalAlignment(JLabel.CENTER);
        add(messageBanner, BorderLayout.PAGE_END);
    }

    // Effects: Initializes text fields for the details for a reward, calls methods to set the fields to
    //              "N/A" and make the fields not editable
    private void initializeRewardDetailFields() {
        rewardNameField = new JTextField(20);
        rewardValueField = new JTextField(20);
        resetRewardDetailFields();
        setRewardDetailFieldsNotEditable();
    }

    // Modifies: this
    // Effects: creates a master panel and adds it to the reward tab
    private void loadMasterPanel() {
        masterPanel = new JPanel();
        masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.LINE_AXIS));
        add(masterPanel, BorderLayout.CENTER);
    }

    // Modifies: this
    // Effects: creates a left panel and adds it to the master panel, calls methods to set up the reward type list
    //              and load applicable buttons
    protected void loadLeftPanel() {
        leftPanel = new JPanel();
        setUpRewardListPanel();
        loadLeftButtons();
        masterPanel.add(leftPanel);
    }

    // Modifies: this
    // Effects: creates a right panel and adds it to the master panel, calls methods to set up the reward details
    //              and load applicable buttons
    protected void loadRightPanel() {
        rightPanel = new JPanel();
        listRewardDetails();
        loadRightButtons();
        masterPanel.add(rightPanel);
    }

    // Modifies: this
    // Effects: Loads edit, add, and remove buttons. Adds them to the left panel.
    private void loadLeftButtons() {
        leftButtonPanel = new JPanel();
        loadRewardEditButton();
        loadRewardAddButton();
        loadRewardRemoveButton();
        leftPanel.add(leftButtonPanel);
    }

    // Modifies: this
    // Effects: Loads save changes, confirm add, and confirm remove buttons. Adds them to the right panel.
    private void loadRightButtons() {
        rightButtonPanel = new JPanel();
        loadRewardSaveChangesButton();
        loadRewardConfirmAddButton();
        loadRewardConfirmRemoveButton();
        rightPanel.add(rightButtonPanel);
    }

    // Modifies: this
    // Effects: Initializes edit button. Adds to left button panel.
    private void loadRewardEditButton() {
        rewardEditButton = new JButton("Edit Reward");
        rewardEditButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        handleClickOnEditButton();
                    }
                }
        );
        leftButtonPanel.add(rewardEditButton);
    }

    // Modifies: this
    // Effects: Initializes add button. Adds to left button panel.
    private void loadRewardAddButton() {
        rewardAddButton = new JButton("Add New Reward");
        rewardAddButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        handleClickOnAddButton();
                    }
                }
        );
        leftButtonPanel.add(rewardAddButton);
    }

    // Modifies: this
    // Effects: Initializes remove button. Adds to left button panel.
    private void loadRewardRemoveButton() {
        rewardRemoveButton = new JButton("Remove Reward");
        rewardRemoveButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        handleClickOnRemoveButton();
                    }
                }
        );
        leftButtonPanel.add(rewardRemoveButton);
    }

    // Modifies: this
    // Effects: Initializes save changes button. Adds to right button panel.
    private void loadRewardSaveChangesButton() {
        rewardSaveChangesButton = new JButton("Save Edits");
        rewardSaveChangesButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        handleClickOnSaveChangesButton();
                    }
                }
        );
        rightButtonPanel.add(rewardSaveChangesButton);
    }

    // Modifies: this
    // Effects: Initializes confirm add button. Adds to right button panel.
    private void loadRewardConfirmAddButton() {
        rewardConfirmAddButton = new JButton("Add");
        rewardConfirmAddButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        handleClickOnConfirmAddButton();
                    }
                }
        );
        rightButtonPanel.add(rewardConfirmAddButton);
    }

    // Modifies: this
    // Effects: Initializes confirm remove button. Adds to right button panel.
    private void loadRewardConfirmRemoveButton() {
        rewardConfirmRemoveButton = new JButton("Remove");
        rewardConfirmRemoveButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        handleClickOnConfirmRemoveButton();
                    }
                }
        );
        rightButtonPanel.add(rewardConfirmRemoveButton);
    }

    // Modifies: this
    // Effects: Creates a panel and loads a scrollable list of rewards to it.
    //              Assigns this panel to the left panel.
    private void setUpRewardListPanel() {
        rewardListPanel = new JPanel();
        loadRewardNamesToScrollableList();
        leftPanel.add(rewardListPanel);
    }

    // Modifies: this
    // Effects: Creates a scrollable list of all the reward names and adds the list to the panel.
    //              Calls the method to identify when a given reward is clicked.
    private void loadRewardNamesToScrollableList() {
        listOfRewardNames = new JList();
        listOfRewardNamesModel = new DefaultListModel();
        for (RewardType r : creditCardManagerGraphical.getListOfRewardTypes().getListOfRewardTypes()) {
            listOfRewardNamesModel.addElement(r.getRewardName());
        }
        listOfRewardNames.setModel(listOfRewardNamesModel);
        scrollPane = new JScrollPane(listOfRewardNames);
        rewardListPanel.add(scrollPane);
        listenForSelectedReward();
    }

    // Effects: adds a method to respond to a given reward in the list being clicked
    private void listenForSelectedReward() {
        listOfRewardNames.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent evt) {
                handleNewRewardSelected(evt);
            }
        });
    }

    // Modifies: this
    // Effects: Takes the selected reward and sets it to the "currentReward". Calls a method to
    //              show the details for the selected reward. Also updates buttons as appropriate.
    private void handleNewRewardSelected(javax.swing.event.ListSelectionEvent evt) {
        String s = (String) listOfRewardNames.getSelectedValue();
        for (RewardType r : creditCardManagerGraphical.getListOfRewardTypes().getListOfRewardTypes()) {
            if (s == null) {
                break;
            }
            if (s.equals(r.getRewardName())) {
                currentReward = r;
                setRewardDetailFields();
                messageBanner.setText("Please choose one of 'edit', 'add', or 'remove'");
                break;
            }
        }
        loadButtonStatesOnRewardClick();
    }

    // Modifies: this
    // Effects: Fills in reward detail fields with info from the "currentReward" variable
    private void setRewardDetailFields() {
        rewardNameField.setText(currentReward.getRewardName());
        rewardValueField.setText(String.valueOf(currentReward.getRewardValue()));
    }

    // Modifies: this
    // Effects: Creates a panel and calls methods to load in panels for the various reward details.
    private void listRewardDetails() {
        rewardDetailPanel = new JPanel();
        rewardDetailPanel.setLayout(new BoxLayout(rewardDetailPanel, BoxLayout.Y_AXIS));
        createRewardNamePanel();
        createRewardValuePanel();
        rightPanel.add(rewardDetailPanel);
    }

    // Modifies: this
    // Effects: Initializes a panel to display the reward name
    private void createRewardNamePanel() {
        rewardNamePanel = new JPanel();
        rewardNamePanel.add(REWARD_NAME_LABEL);
        rewardNamePanel.add(rewardNameField);
        rewardDetailPanel.add(rewardNamePanel);
    }

    // Modifies: this
    // Effects: Initializes a panel to display the reward value (in cpp)
    private void createRewardValuePanel() {
        rewardValuePanel = new JPanel();
        rewardValuePanel.add(REWARD_VALUE_LABEL);
        rewardValuePanel.add(rewardValueField);
        rewardDetailPanel.add(rewardValuePanel);
    }

    // Modifies: this
    // Effects: Sets buttons to the initial state (only add)
    private void loadButtonStatesInitial() {
        rewardEditButton.setEnabled(false);
        rewardAddButton.setEnabled(true);
        rewardRemoveButton.setEnabled(false);
        rewardSaveChangesButton.setEnabled(false);
        rewardConfirmAddButton.setEnabled(false);
        rewardConfirmRemoveButton.setEnabled(false);
    }

    // Modifies: this
    // Effects: Sets buttons to the state once a reward is selected from the list (allowing edit/add/remove)
    private void loadButtonStatesOnRewardClick() {
        rewardEditButton.setEnabled(true);
        rewardAddButton.setEnabled(true);
        rewardRemoveButton.setEnabled(true);
        rewardSaveChangesButton.setEnabled(false);
        rewardConfirmAddButton.setEnabled(false);
        rewardConfirmRemoveButton.setEnabled(false);
    }

    // Modifies: this
    // Effects: Sets buttons to the state once a reward is being edited (allowing save edits)
    private void loadButtonStatesEdit() {
        rewardEditButton.setEnabled(false);
        rewardAddButton.setEnabled(false);
        rewardRemoveButton.setEnabled(false);
        rewardSaveChangesButton.setEnabled(true);
        rewardConfirmAddButton.setEnabled(false);
        rewardConfirmRemoveButton.setEnabled(false);
    }

    // Modifies: this
    // Effects: Sets buttons to the state once a reward is being added (allowing confirm add)
    private void loadButtonStatesAdd() {
        rewardEditButton.setEnabled(false);
        rewardAddButton.setEnabled(false);
        rewardRemoveButton.setEnabled(false);
        rewardSaveChangesButton.setEnabled(false);
        rewardConfirmAddButton.setEnabled(true);
        rewardConfirmRemoveButton.setEnabled(false);
    }

    // Modifies: this
    // Effects: Sets buttons to the state once a reward is being removed (allowing confirm remove)
    private void loadButtonStatesRemove() {
        rewardEditButton.setEnabled(false);
        rewardAddButton.setEnabled(false);
        rewardRemoveButton.setEnabled(false);
        rewardSaveChangesButton.setEnabled(false);
        rewardConfirmAddButton.setEnabled(false);
        rewardConfirmRemoveButton.setEnabled(true);
    }

    // Modifies: this
    // Effects: Prevents any of the credit card fields from being edited
    private void setRewardDetailFieldsNotEditable() {
        rewardNameField.setEditable(false);
        rewardValueField.setEditable(false);
    }

    // Modifies: this
    // Effects: Allows all the reward fields to be edited
    private void setRewardDetailFieldsEditable() {
        rewardNameField.setEditable(true);
        rewardValueField.setEditable(true);
    }

    // Modifies: this
    // Effects: Allows all the reward fields to be edited except for name
    private void setRewardDetailFieldsEditableExceptRewardName() {
        rewardNameField.setEditable(false);
        rewardValueField.setEditable(true);
    }

    // Effects: Process clicking on the edit button by updating the appropriate buttons and
    //              making fields editable except for the reward name
    private void handleClickOnEditButton() {
        loadButtonStatesEdit();
        setRewardDetailFieldsEditableExceptRewardName();
        messageBanner.setText("Please update reward details and save");
    }

    // Effects: Process clicking on the add button by resetting all fields, updating the appropriate buttons,
    //              and making fields editable
    private void handleClickOnAddButton() {
        resetRewardDetailFields();
        loadButtonStatesAdd();
        setRewardDetailFieldsEditable();
        messageBanner.setText("Please enter reward details and confirm add");
    }

    // Effects: Process clicking on the remove button by updating the appropriate buttons (2-step removal process)
    private void handleClickOnRemoveButton() {
        loadButtonStatesRemove();
        messageBanner.setText("Please confirm you want to remove this reward");
    }

    // Modifies: this
    // Effects: Process clicking on the save changes button by calling a helper method, refreshing the
    //              reward list, updating the appropriate buttons, and making the fields no longer editable
    private void handleClickOnSaveChangesButton() {
        getUpdatedRewardDetailsAndSave();
        refreshRewardList();
        loadButtonStatesInitial();
        setRewardDetailFieldsNotEditable();
        messageBanner.setText("Changes saved");
    }

    // Modifies: this
    // Effects: Process clicking on the confirm add button by calling a helper method, refreshing the
    //              reward list, updating the appropriate buttons, and making the fields no longer editable
    private void handleClickOnConfirmAddButton() {
        getNewRewardDetailsAndSave();
        refreshRewardList();
        loadButtonStatesInitial();
        setRewardDetailFieldsNotEditable();
    }

    // Modifies: this
    // Effects: Process clicking on the confirm remove button by removing the selected reward, refreshing the
    //              reward list, updating the appropriate buttons, and resetting the field text
    private void handleClickOnConfirmRemoveButton() {
        creditCardManagerGraphical.getListOfRewardTypes().removeRewardType(currentReward.getRewardName());
        refreshRewardList();
        loadButtonStatesInitial();
        resetRewardDetailFields();
        messageBanner.setText("Reward removed");
    }

    // Requires: rewardNameField must be a string and reward value must be a double
    // Modifies: this
    // Effects: for the selected reward, updates its parameters with the values entered in the text fields
    private void getUpdatedRewardDetailsAndSave() {
        currentReward.setRewardName(rewardNameField.getText());
        currentReward.setRewardValue(Double.valueOf(rewardValueField.getText()));
    }

    // Requires: rewardNameField must be a string and reward value must be a double
    // Modifies: this
    // Effects: creates a new reward type with the values entered in the text fields, unless the reward name
    //              is already taken in which case the addition of the new reward fails
    private void getNewRewardDetailsAndSave() {
        String rewardName = rewardNameField.getText();
        Double rewardValue = Double.valueOf(rewardValueField.getText());
        RewardType newReward = new RewardType(rewardName, rewardValue);
        Boolean wasAddSuccess = creditCardManagerGraphical.getListOfRewardTypes().addRewardType(newReward);
        if (wasAddSuccess) {
            messageBanner.setText("Reward added");
        } else {
            messageBanner.setText("There is already a reward with that name. This one was not added.");
        }
    }

    // Modifies: this
    // Effects: updates the scrollable list of rewards to reflect any edits/additions/removals
    public void refreshRewardList() {
        listOfRewardNamesModel = new DefaultListModel();
        for (RewardType r : creditCardManagerGraphical.getListOfRewardTypes().getListOfRewardTypes()) {
            listOfRewardNamesModel.addElement(r.getRewardName());
        }
        listOfRewardNames.setModel(listOfRewardNamesModel);
    }

    // Modifies: this
    // Effects: set all reward fields to "N/A"
    private void resetRewardDetailFields() {
        rewardNameField.setText("N/A");
        rewardValueField.setText("N/A");
    }

}
