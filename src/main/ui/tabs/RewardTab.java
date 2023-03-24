package ui.tabs;

import model.RewardType;
import ui.CreditCardManagerGraphical;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public RewardTab(CreditCardManagerGraphical creditCardManagerGraphical) {
        super(creditCardManagerGraphical);
        initializeRewardDetailFields();
        loadMasterPanel();
        loadLeftPanel();
        loadRightPanel();
        loadButtonStatesInitial();
    }

    @Override
    protected void addHeader() {
        JLabel header = new JLabel("Reward Tab");
        header.setHorizontalAlignment(JLabel.CENTER);
        add(header, BorderLayout.PAGE_START);
    }

    @Override
    protected void addMessageBanner() {
        messageBanner = new JLabel();
        messageBanner.setText("Please select a reward for more options, or click 'add' to add a new reward");
        messageBanner.setHorizontalAlignment(JLabel.CENTER);
        add(messageBanner, BorderLayout.PAGE_END);
    }

    private void initializeRewardDetailFields() {
        rewardNameField = new JTextField(20);
        rewardValueField = new JTextField(20);
        resetRewardDetailFields();
        setRewardDetailFieldsNotEditable();
    }

    private void loadMasterPanel() {
        masterPanel = new JPanel();
        masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.LINE_AXIS));
        add(masterPanel, BorderLayout.CENTER);
    }

    protected void loadLeftPanel() {
        leftPanel = new JPanel();
        setUpRewardListAndDetailsPanel();
        loadLeftButtons();
        masterPanel.add(leftPanel);
    }

    protected void loadRightPanel() {
        rightPanel = new JPanel();
        listRewardDetails();
        loadRightButtons();
        masterPanel.add(rightPanel);
    }

    private void loadLeftButtons() {
        leftButtonPanel = new JPanel();
        loadRewardEditButton();
        loadRewardAddButton();
        loadRewardRemoveButton();
        leftPanel.add(leftButtonPanel);
    }

    private void loadRightButtons() {
        rightButtonPanel = new JPanel();
        loadRewardSaveChangesButton();
        loadRewardConfirmAddButton();
        loadRewardConfirmRemoveButton();
        rightPanel.add(rightButtonPanel);
    }

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

    private void setUpRewardListAndDetailsPanel() {
        rewardListPanel = new JPanel();
        loadRewardNamesToScrollableList();
        leftPanel.add(rewardListPanel);
    }

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

    private void listenForSelectedReward() {
        listOfRewardNames.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent evt) {
                handleNewRewardSelected(evt);
            }
        });
    }

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

    private void setRewardDetailFields() {
        rewardNameField.setText(currentReward.getRewardName());
        rewardValueField.setText(String.valueOf(currentReward.getRewardValue()));
    }

    private void listRewardDetails() {
        rewardDetailPanel = new JPanel();
        rewardDetailPanel.setLayout(new BoxLayout(rewardDetailPanel, BoxLayout.Y_AXIS));
        createRewardNamePanel();
        createRewardValuePanel();
        rightPanel.add(rewardDetailPanel);
    }

    private void createRewardNamePanel() {
        rewardNamePanel = new JPanel();
        rewardNamePanel.add(REWARD_NAME_LABEL);
        rewardNamePanel.add(rewardNameField);
        rewardDetailPanel.add(rewardNamePanel);
    }

    private void createRewardValuePanel() {
        rewardValuePanel = new JPanel();
        rewardValuePanel.add(REWARD_VALUE_LABEL);
        rewardValuePanel.add(rewardValueField);
        rewardDetailPanel.add(rewardValuePanel);
    }

    private void loadButtonStatesInitial() {
        rewardEditButton.setEnabled(false);
        rewardAddButton.setEnabled(true);
        rewardRemoveButton.setEnabled(false);
        rewardSaveChangesButton.setEnabled(false);
        rewardConfirmAddButton.setEnabled(false);
        rewardConfirmRemoveButton.setEnabled(false);
    }

    private void loadButtonStatesOnRewardClick() {
        rewardEditButton.setEnabled(true);
        rewardAddButton.setEnabled(true);
        rewardRemoveButton.setEnabled(true);
        rewardSaveChangesButton.setEnabled(false);
        rewardConfirmAddButton.setEnabled(false);
        rewardConfirmRemoveButton.setEnabled(false);
    }

    private void loadButtonStatesEdit() {
        rewardEditButton.setEnabled(false);
        rewardAddButton.setEnabled(false);
        rewardRemoveButton.setEnabled(false);
        rewardSaveChangesButton.setEnabled(true);
        rewardConfirmAddButton.setEnabled(false);
        rewardConfirmRemoveButton.setEnabled(false);
    }

    private void loadButtonStatesAdd() {
        rewardEditButton.setEnabled(false);
        rewardAddButton.setEnabled(false);
        rewardRemoveButton.setEnabled(false);
        rewardSaveChangesButton.setEnabled(false);
        rewardConfirmAddButton.setEnabled(true);
        rewardConfirmRemoveButton.setEnabled(false);
    }

    private void loadButtonStatesRemove() {
        rewardEditButton.setEnabled(false);
        rewardAddButton.setEnabled(false);
        rewardRemoveButton.setEnabled(false);
        rewardSaveChangesButton.setEnabled(false);
        rewardConfirmAddButton.setEnabled(false);
        rewardConfirmRemoveButton.setEnabled(true);
    }

    private void setRewardDetailFieldsNotEditable() {
        rewardNameField.setEditable(false);
        rewardValueField.setEditable(false);
    }

    private void setRewardDetailFieldsEditable() {
        rewardNameField.setEditable(true);
        rewardValueField.setEditable(true);
    }

    private void setRewardDetailFieldsEditableExceptRewardName() {
        rewardNameField.setEditable(true);
        rewardValueField.setEditable(true);
    }

    private void handleClickOnEditButton() {
        loadButtonStatesEdit();
        setRewardDetailFieldsEditableExceptRewardName();
        messageBanner.setText("Please update reward details and save");
    }

    private void handleClickOnAddButton() {
        resetRewardDetailFields();
        loadButtonStatesAdd();
        setRewardDetailFieldsEditable();
        messageBanner.setText("Please enter reward details and confirm add");
    }

    private void handleClickOnRemoveButton() {
        loadButtonStatesRemove();
        messageBanner.setText("Please confirm you want to remove this reward");
    }

    private void handleClickOnSaveChangesButton() {
        getUpdatedRewardDetailsAndSave();
        refreshRewardList();
        loadButtonStatesInitial();
        setRewardDetailFieldsNotEditable();
        messageBanner.setText("Changes saved");
    }

    private void handleClickOnConfirmAddButton() {
        getNewRewardDetailsAndSave();
        refreshRewardList();
        loadButtonStatesInitial();
        setRewardDetailFieldsNotEditable();
    }

    private void handleClickOnConfirmRemoveButton() {
        creditCardManagerGraphical.getListOfRewardTypes().removeRewardType(currentReward.getRewardName());
        refreshRewardList();
        loadButtonStatesInitial();
        resetRewardDetailFields();
        messageBanner.setText("Reward removed");
    }

    private void getUpdatedRewardDetailsAndSave() {
        currentReward.setRewardName(rewardNameField.getText());
        currentReward.setRewardValue(Double.valueOf(rewardValueField.getText()));
    }

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

    public void refreshRewardList() {
        listOfRewardNamesModel = new DefaultListModel();
        for (RewardType r : creditCardManagerGraphical.getListOfRewardTypes().getListOfRewardTypes()) {
            listOfRewardNamesModel.addElement(r.getRewardName());
        }
        listOfRewardNames.setModel(listOfRewardNamesModel);
    }

    private void resetRewardDetailFields() {
        rewardNameField.setText("N/A");
        rewardValueField.setText("N/A");
    }

}
