package ui.tabs;

import ui.CreditCardManagerGraphical;

import javax.swing.*;

public abstract class Tab extends JPanel {

    protected final CreditCardManagerGraphical creditCardManagerGraphical;

    public Tab(CreditCardManagerGraphical creditCardManagerGraphical) {
        this.creditCardManagerGraphical = creditCardManagerGraphical;
        // setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        addHeader();
        loadButtons();
    }

    protected abstract void addHeader();

    protected abstract void loadButtons();
}
