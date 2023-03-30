# Credit Card Optimizer
> A Java project by Alexander Proskiw

![Credit Cards](data/credit-card-picture.jpg)

## <ins>Application Purpose:</ins>
According to the Bank of Canada, 9 in 10 Canadians own a credit card. Most of
these cards provide some type of reward, such as getting cashback or aeroplan points 
for every dollar you spend.

The purpose of this application is to:
- Allow the user to create a list of credit cards they are interested in;
- View and modify the credit cards in this list; and 
- Get a recommendation for the best credit card optimized to their personal monthly spending. 

The optimized recommendation will account for the difference in value between reward types 
by converting each reward type to an equivalent $ value.

## <ins>User Information/Stories</ins>
This application will be used by people who want to keep track of multiple different credit cards
that they are considering getting. In addition, the application will be used be people who want 
to optimize their credit card choice by minimizing fees while maximizing rewards.

Key user stories that will be implemented in this application include:
- As a user, I want to be able to add/remove credit cards to/from a list of credit cards I'm considering getting. At minimum, a credit card will have a name, annual fee, and reward rate.
- As a user, I want to be able to select a credit card from the list of credit cards and view/modify its details.
- As a user, I want to be able to enter my monthly credit card spending.
- As a user, I want to be able to receive a recommendation of the credit card that will give me the highest reward value based on my monthly spending.
- As a user, I want the option to save the changes made to my credit cards, rewards, and spending information to file.
- As a user, I want the option to load my saved credit cards, rewards, and spending information from file.

## <ins>Project Rationale</ins>
This project is of interest to me because for years I simply 
had a basic credit card from my bank. But recently, I discovered
that based on my current spending I could get hundreds of dollars 
in rewards more if I had a different credit card from a different bank.

When I first started assessing other credit card options, I utilized excel.
However, it was a very tedious process, and begged to be automated.
Hence, the idea for this Credit Card Optimizer application was born.

## <ins>Disclaimer and limitations:</ins>
1. *Credit cards often carry high interest rates. 
You should avoid carrying a balance whenever possible.* 
2. *The purpose of this application is not to encourage
you to spend more just to earn more rewards. It is not worth spending
money you don't have just to earn a few extra dollars in
rewards.*
3. *For the sake of simplicity and equal comparison, this project focuses 
solely on the rewards earned through regular spending. Limited time 
offers associated with specific credit cards such as signup bonuses have 
not been considered. Similarly, less tangible perks such as airport lounge 
access and VIP status have not been accounted for.*

## <ins>References:</ins>
- Incorporated elements of code from UBC CPSC210 - Json Serialization Demo. Accessed online March 7, 2023 at: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
- Incorporated elements of code from UBC CPSC210 - Smarthome Project. Accessed online March 21, 2023 at: https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters
- For the event logging, incorporated elements of code from UBC CPSC210 - Alarm System Project. Accessed online March 29, 2023 at: https://github.students.cs.ubc.ca/CPSC210/AlarmSystem
- Bank of Canada. How Canadians Pay For Things. Accessed online Jan 25, 2023 at: https://www.bankofcanada.ca/2019/10/how-canadians-pay-for-things/
- Credit Card Picture: <a href="https://www.freepik.com/free-photo/credit-card-payment-buy-sell-products-service_5469634.htm#query=credit%20cards&position=1&from_view=search&track=sph">Image by jcomp</a> on Freepik

## <ins>Phase 3: Instructions for Grader:</ins>
- You can generate the first required action related to adding credit cards to a list of credit cards by clicking on the "Credit Cards" tab, clicking "Add New Card", filling in the appropriate details, and clicking "Add"
- You can generate the second required action related to removing credit cards from a list of credit cards by clicking on the "Credit Cards" tab, clicking on a credit card in the list, clicking "Remove Card", and confirming by clicking "Remove"
- You can generate a third action related to editing a credit card within the list of credit cards by clicking on the "Credit Cards" tab, clicking on a credit card in the list, clicking "Edit Card", editing the appropriate fields, and clicking "Save Edits"
- You can locate the visual component by clicking the "Optimizer" tab and clicking the "Optimize" button. A progress bar will appear, visually updating as the optimization proceeds.
- You can save the state of the application by clicking the "Save/Load" tab and clicking the "Save Data" button
- You can reload the state of the application by clicking the "Save/Load" tab and clicking the "Load Data" button

## <ins>Phase 4: Task 2</ins>
An example of the event log that gets printed to the terminal when I remove a card, add a card, and edit a card is as follows:
- Wed Mar 29 17:41:03 PDT 2023
- Removed card: CIBC Aventura Visa Infinite Privilege
- Wed Mar 29 17:41:25 PDT 2023
- Added new card: New Credit Card
- Wed Mar 29 17:41:45 PDT 2023
- Set card name to: AMEX Cobalt
- Wed Mar 29 17:41:45 PDT 2023
- Set reward name to: AMEX Rewards
- Wed Mar 29 17:41:45 PDT 2023
- Set annual fee to: 0.0
- Wed Mar 29 17:41:45 PDT 2023
- Set general rewards to: 9.0
- Wed Mar 29 17:41:45 PDT 2023
- Set travel rewards to: 8.0
- Wed Mar 29 17:41:45 PDT 2023
- Set grocery rewards to: 7.0
- Wed Mar 29 17:41:45 PDT 2023
- Set restaurant rewards to: 6.0
- Wed Mar 29 17:41:45 PDT 2023
- Set gas rewards to: 5.0
- Wed Mar 29 17:41:45 PDT 2023
- Set drug store rewards to: 4.0
- Wed Mar 29 17:41:45 PDT 2023
- Set transit rewards to: 3.0
- Wed Mar 29 17:41:45 PDT 2023
- Set entertainment rewards to: 2.0
- Wed Mar 29 17:41:45 PDT 2023
- Set recurring rewards to: 1.0
- 
## <ins>Phase 4: Task 3</ins>
Please see the completed UML Class Diagram for my project here: [UML Design Diagram](UML_Design_Diagram.pdf)

I am generally happy with the design presented in the UML Class Diagram for this project. I managed to maintain
good separation between the different components of the model, with each class having a distinct responsibility.
For example, the difference between credit cards, reward types, and monthly spending are clearly delineated
between the different classes in the Model package.

However, the diagram helped me identify several areas of improvement for future refactoring if I had more time. 
Namely, some of the classes in the UI package have direct associations to classes within the model package.
These include:
- The CreditCardTab class having a field of type CreditCard, 
- The RewardTab having a field of type RewardType, and 
- The OptimizerTab having a field of type CreditCardOptimizer 

To tidy up the overall relation between the Model and UI packages, I would move these relationships to be handled 
by the CreditCardManagerGraphical class, which already has associations with the ListOfCreditCards, ListOfRewardType,
and MonthlySpending classes. This would help reduce coupling between classes.

Another improvement I would attempt to make is have the CreditCardManagerGraphical class simply have
5 associations to the abstract class Tab, instead of single associations to the 5 different tabs
(e.g., CreditCardTab, RewardTab, etc.) that extend Tab. This would help reduce coupling, and make future 
revisions easier if I wanted to add a new type of tab that extended Tab.

Another refactoring change I might consider is how I handle the Json writers and readers.
Right now the project has 3 of each - one to read/write credit cards, one to read/write rewards,
and one to read/write monthly spending. Perhaps these could be combined into a single reader and a single writer,
as the functionality between them is not significantly different 
(just taking one of my objects and reading/writing it to/from a Json file).