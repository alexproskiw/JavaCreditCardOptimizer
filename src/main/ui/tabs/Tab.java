package ui.tabs;

import ui.CreditCardManagerGraphical;

import javax.swing.*;
import java.awt.*;

public abstract class Tab extends JPanel {

    protected final CreditCardManagerGraphical creditCardManagerGraphical;

    public Tab(CreditCardManagerGraphical creditCardManagerGraphical) {
        this.creditCardManagerGraphical = creditCardManagerGraphical;
        this.setLayout(new BorderLayout(20, 20));
        addHeader();
        addMessageBanner();
    }

    protected abstract void addHeader();

    protected abstract void addMessageBanner();

}
