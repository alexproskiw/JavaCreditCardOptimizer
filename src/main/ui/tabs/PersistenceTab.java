package ui.tabs;

import ui.CreditCardManagerGraphical;

import javax.swing.*;

public class PersistenceTab extends Tab {

    private JButton saveButton;
    private JButton loadButton;

    public PersistenceTab(CreditCardManagerGraphical creditCardManagerGraphical) {
        super(creditCardManagerGraphical);
    }

    @Override
    protected void addHeader() {
        JLabel header = new JLabel("Persistence Tab");
        add(header);
    }

    @Override
    protected void addMessageBanner() {

    }

    protected void loadButtons() {
        saveButton = new JButton("Save Data");
        add(saveButton);
        loadButton = new JButton("Load Data");
        add(loadButton);
    }

}
