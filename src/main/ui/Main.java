package ui;

import java.io.FileNotFoundException;

// Launches the application
public class Main {

    // Effects: creates a new credit card manager and runs the application
    public static void main(String[] args) {
        try {
            CreditCardManager manager = new CreditCardManager();
            manager.startCreditCardManager();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}