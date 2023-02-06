package model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ListOfCreditCards {

    private ArrayList<CreditCard> listOfCreditCards;

    // Create default credit cards
    private CreditCard cibcAventuraVIP = new CreditCard("CIBC Aventura Visa Infinite Privilege",
            "Aventura", 499, 1.25, 3, 2, 2, 2,
            1.25, 2, 2, 1.25);
    private CreditCard cibcAventuraVI = new CreditCard("CIBC Aventura Visa Infinite",
            "Aventura", 139, 1, 2, 1.5, 1, 1.5,
            1.5, 1, 1, 1);
    private CreditCard cibcAventura = new CreditCard("CIBC Aventura Visa",
            "Aventura", 0, 0.5, 1, 1, 0.5, 1,
            1, 0.5, 0.5, 0.5);
    private CreditCard cibcAeroplanVIP = new CreditCard("CIBC Aeroplan Visa Infinite Privilege",
            "Aeroplan", 599, 1.25, 2, 1.5, 1.5, 1.5,
            1.25, 1.25, 1.25, 1.25);
    private CreditCard cibcAeroplanVI = new CreditCard("CIBC Aeroplan Visa Infinite",
            "Aeroplan", 139, 1, 1.5, 1.5, 1, 1.5,
            1, 1, 1, 1);
    private CreditCard cibcAeroplan = new CreditCard("CIBC Aeroplan Visa",
            "Aeroplan", 0, 0.67, 1, 1, 0.67, 1,
            0.67, 0.67, 0.67, 0.67);
    private CreditCard cibcDividendVI = new CreditCard("CIBC Dividend Visa Infinite",
            "Cashback", 120, 1, 1, 4, 2, 4,
            1, 2, 1, 2);
    private CreditCard cibcDividendPlat = new CreditCard("CIBC Dividend Visa Platinum",
            "Cashback", 99, 1, 1, 3, 2, 3,
            1, 2, 1, 2);
    private CreditCard cibcDividend = new CreditCard("CIBC Dividend Visa",
            "Cashback", 0, 0.5, 0.5, 2, 1, 1,
            0.5, 1, 0.5, 1);
    private CreditCard cibcCostco = new CreditCard("CIBC Costco Mastercard",
            "Cashback", 0, 1, 1, 1, 3, 2,
            1, 1, 1, 1);
    private CreditCard simpliiVisa = new CreditCard("Simplii Cash Back Visa",
            "Cashback", 0, 0.5, 0.5, 1.5, 4, 1.5,
            1.5, 0.5, 0.5, 1.5);
    private CreditCard amexCobalt = new CreditCard("AMEX Cobalt",
            "AMEX Rewards", 155.88, 1, 2, 5, 5, 2,
            1, 2, 3, 1);

    // Effects: constructs a list of CreditCard, and loads default cards for the user
    public ListOfCreditCards() {
        this.listOfCreditCards = new ArrayList<CreditCard>();
        loadDefaultCreditCards();
    }

//    public void listAllCreditCards() {
//        System.out.println("Your database of credit cards includes:");
//        for (CreditCard card: listOfCreditCards) {
//            System.out.println("- " + card.getName());
//        }
//    }
//
//    public void displayCardInfo(String cardName) {
//        for (CreditCard card: listOfCreditCards) {
//            if (card.getName().equals(cardName)) {
//                System.out.println("The card's name is: " + card.getName());
//                System.out.println("The card's reward type is: " + card.getRewardName());
//                System.out.println("The card's annual fee is: $" + card.getAnnualFee());
//                System.out.println("The card's reward rates are as follows:");
//                System.out.println("- General rewards: " + card.getGeneralRewards() + " points per $ spent");
//                System.out.println("- Travel rewards: " + card.getTravelRewards() + " points per $ spent");
//                System.out.println("- Grocery rewards: " + card.getGroceryRewards() + " points per $ spent");
//                System.out.println("- Restaurant rewards: " + card.getRestaurantRewards() + " points per $ spent");
//                System.out.println("- Gas rewards: " + card.getGasRewards() + " points per $ spent");
//                System.out.println("- Drug store rewards: " + card.getDrugStoreRewards() + " points per $ spent");
//                System.out.println("- Transit rewards: " + card.getTransitRewards() + " points per $ spent");
//                System.out.println("- Entertainment rewards: " + card.getEntertainmentRewards()
//                        + " points per $ spent");
//                System.out.println("- Recurring rewards: " + card.getRecurringRewards() + " points per $ spent");
//            }
//        }
//    }

    public boolean addCreditCard(CreditCard card) {
        if (containsCard(card.getName())) {
            return false;
        } else {
            listOfCreditCards.add(card);
            return true;
        }
    }

    public boolean removeCreditCard(String cardName) {
        for (int i = 0; i < listOfCreditCards.size(); i++) {
            if (listOfCreditCards.get(i).getName().equals(cardName)) {
                listOfCreditCards.remove(i);
                return true;
            }
        }
        return false;
    }

    public int getSize() {
        return listOfCreditCards.size();
    }

    public CreditCard getCreditCard(String cardName) {
        for (int i = 0; i < listOfCreditCards.size(); i++) {
            if (listOfCreditCards.get(i).getName().equals(cardName)) {
                return listOfCreditCards.get(i);
            }
        }
        return null;
    }

    public CreditCard getCreditCard(int i) {
        return listOfCreditCards.get(i);
    }

    public boolean containsCard(String cardName) {
        for (int i = 0; i < listOfCreditCards.size(); i++) {
            if (listOfCreditCards.get(i).getName().equals(cardName)) {
                return true;
            }
        }
        return false;
    }


    // Modifies: this
    // Effects: Adds the default credit cards to the listOfCreditCards
    private void loadDefaultCreditCards() {
        listOfCreditCards.add(cibcAventuraVIP);
        listOfCreditCards.add(cibcAventuraVI);
        listOfCreditCards.add(cibcAventura);
        listOfCreditCards.add(cibcAeroplanVIP);
        listOfCreditCards.add(cibcAeroplanVI);
        listOfCreditCards.add(cibcAeroplan);
        listOfCreditCards.add(cibcDividendVI);
        listOfCreditCards.add(cibcDividendPlat);
        listOfCreditCards.add(cibcDividend);
        listOfCreditCards.add(cibcCostco);
        listOfCreditCards.add(simpliiVisa);
        listOfCreditCards.add(amexCobalt);
    }

}

