package ui;

import model.CreditCardOptimizer;
import model.ListOfCreditCards;
import model.ListOfRewardTypes;
import model.MonthlySpending;

import java.time.Month;

public class Main {
    public static void main(String[] args) {

        ListOfCreditCards locc = new ListOfCreditCards();
        ListOfRewardTypes lort = new ListOfRewardTypes();
        MonthlySpending spend = new MonthlySpending(1000, 100, 500, 200, 100,
                50, 50, 50, 200);
        CreditCardOptimizer optimizer = new CreditCardOptimizer();
        optimizer.calculateMaxRewards(locc,lort,spend);
        System.out.println("Your recommended card is the " + optimizer.getOptimalCard());
        System.out.println("With your spending, you will get a net value of $" + optimizer.getMaxRewards()
                + " in rewards per year");
    }
}
