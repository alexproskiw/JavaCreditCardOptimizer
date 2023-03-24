package ui.tabs;

import ui.CreditCardManagerGraphical;

import javax.swing.*;
import java.awt.*;


// Abstract class representing one of the tabs on the sidebar of the GUI
public abstract class Tab extends JPanel {

    protected final CreditCardManagerGraphical creditCardManagerGraphical;

    // Effects: constructs a new tab with the given creditCardManager, sets the layout, and adds a header
    //              and message banner
    public Tab(CreditCardManagerGraphical creditCardManagerGraphical) {
        this.creditCardManagerGraphical = creditCardManagerGraphical;
        this.setLayout(new BorderLayout(20, 20));
        addHeader();
        addMessageBanner();
    }

    // Effects: adds a tab header at the top of the GUI
    protected abstract void addHeader();

    // Effects: adds a message banner at the bottom of the GUI
    protected abstract void addMessageBanner();

}
