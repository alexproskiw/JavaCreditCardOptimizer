package ui.tabs;

import ui.CreditCardManagerGraphical;

import javax.swing.*;

public class OptimizerTab extends Tab {

    private JButton optimizerButton;

    public OptimizerTab(CreditCardManagerGraphical creditCardManagerGraphical) {
        super(creditCardManagerGraphical);
    }

    @Override
    protected void addHeader() {
        JLabel header = new JLabel("Optimizer Tab");
        add(header);
    }

    @Override
    protected void addMessageBanner() {

    }


    protected void loadButtons() {
        optimizerButton = new JButton("Optimize");
        add(optimizerButton);
    }

}
