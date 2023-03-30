package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// A list of credit cards
public class ListOfCreditCards implements Writable {

    private ArrayList<CreditCard> listOfCreditCards;        // a list of credit cards

    // Default credit cards
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

    // Effects: constructs a list of credit cards, and adds the default ones
    public ListOfCreditCards(Boolean loadDefaults) {
        this.listOfCreditCards = new ArrayList<CreditCard>();
        if (loadDefaults) {
            loadDefaultCreditCards();
        }
    }

    // Getter
    public ArrayList<CreditCard> getListOfCreditCards() {
        return listOfCreditCards;
    }

    // Effects: Returns the credit card that has the given card name, or returns null if the card does not exist
    public CreditCard getCreditCard(String cardName) {
        for (CreditCard card : listOfCreditCards) {
            if (card.getCardName().equals(cardName)) {
                return card;
            }
        }
        return null;
    }

    // Effects: Returns true if the credit card that has the given card name is in the list of credit cards,
    //              otherwise returns false
    public boolean containsCard(String cardName) {
        for (CreditCard card : listOfCreditCards) {
            if (card.getCardName().equals(cardName)) {
                return true;
            }
        }
        return false;
    }

    // Requires: card must not be null
    // Modifies: this
    // Effects: if the given card is not already in the list of cards, it is added and the method returns true,
    //              but if the card is already in the list it is not added and the method returns false
    public boolean addCreditCard(CreditCard card) {
        if (containsCard(card.getCardName())) {
            return false;
        } else {
            listOfCreditCards.add(card);
            EventLog.getInstance().logEvent(new Event("Added new card: " + card.getCardName()));
            return true;
        }
    }

    // Requires: cardName has a non-zero length
    // Modifies: this
    // Effects: if the card with the given reward name is in the list of cards, it is removed from the list
    //              and the method returns true. Returns false if there is no card matching the given card name.
    public boolean removeCreditCard(String cardName) {
        for (CreditCard card : listOfCreditCards) {
            if (card.getCardName().equals(cardName)) {
                listOfCreditCards.remove(card);
                EventLog.getInstance().logEvent(new Event("Removed card: " + card.getCardName()));
                return true;
            }
        }
        return false;
    }

    // Modifies: this
    // Effects: Adds the default credit cards to the list of credit cards
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

    // Effects: Create a json object for the list of credit cards
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("credit cards", creditCardsToJson());
        return json;
    }

    // EFFECTS: returns credit cards in this list of credit cards as a JSON array
    private JSONArray creditCardsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (CreditCard card : listOfCreditCards) {
            jsonArray.put(card.toJson());
        }

        return jsonArray;
    }

}

