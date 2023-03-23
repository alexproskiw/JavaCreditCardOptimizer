package ui.tabs;

import ui.CreditCardManagerGraphical;

import javax.swing.*;

public class RewardTab extends Tab {

    private JButton rewardAddButton;
    private JButton rewardRemoveButton;
    private JButton rewardEditButton;
    private JButton rewardSaveChangesButton;
    private JButton rewardSaveAdditionButton;

    public RewardTab(CreditCardManagerGraphical creditCardManagerGraphical) {
        super(creditCardManagerGraphical);
    }

    @Override
    protected void addHeader() {
        JLabel header = new JLabel("Reward Tab");
        add(header);
    }

    @Override
    protected void loadButtons() {
        rewardAddButton = new JButton("Add A Reward");
        add(rewardAddButton);
        rewardRemoveButton = new JButton("Remove Selected Reward");
        add(rewardRemoveButton);
        rewardEditButton = new JButton("Edit Selected Reward");
        add(rewardEditButton);
        rewardSaveChangesButton = new JButton("Save Changes");
        add(rewardSaveChangesButton);
        rewardSaveAdditionButton = new JButton("Confirm Addition");
        add(rewardSaveAdditionButton);
    }

}
