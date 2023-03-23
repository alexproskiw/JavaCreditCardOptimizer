package ui.tabs;

import model.MonthlySpending;
import ui.CreditCardManagerGraphical;

import javax.swing.*;

public class MonthlySpendingTab extends Tab {

    private JButton monthlySpendingEditButton;
    private JButton monthlySpendingSaveChangesButton;

    public MonthlySpendingTab(CreditCardManagerGraphical creditCardManagerGraphical) {
        super(creditCardManagerGraphical);
        loadSpending();
    }

    @Override
    protected void addHeader() {
        JLabel header = new JLabel("Monthly Spending Tab");
        add(header);
    }

    @Override
    protected void addMessageBanner() {

    }


    protected void loadButtons() {
        monthlySpendingEditButton = new JButton("Edit Spending");
        add(monthlySpendingEditButton);
        monthlySpendingSaveChangesButton = new JButton("Save Changes");
        add(monthlySpendingSaveChangesButton);
    }

    private void loadSpending() {
    }

}
