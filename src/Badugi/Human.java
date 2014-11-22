package Badugi;

/**
 * Created by Adam on 2014-11-20.
 */
import java.util.ArrayList;
import java.util.Scanner;

public class Human extends Player {
    Scanner sc = new Scanner(System.in);

    public Human(String nick)
    {
        super(nick);
    }

    public int howManyCardsExchanged()
    {
        return howManyCardsExchanged;
    }


    public ArrayList<Card> chooseCardsToExchange()
    {

        ArrayList<Card> toExchange = new ArrayList<>();
        String choice;
        do {
            toExchange.clear();

            System.out.print("Which cards do you want to exchange?: ");
            choice = sc.next();
            boolean errorOccurred = false;
            if (choice.contains(",") || choice.length() == 1)
            {
                String[] cardIndices = choice.trim().split(",");
                cardIndices = deleteEmptyElements(cardIndices);

                if (cardIndices.length <= 4)
                {
                    for (String indexString : cardIndices)
                    {
                        if (isDigitBetweenOneAndFour(indexString))
                        {
                            int cardIndex = Integer.parseInt(indexString);
                            toExchange.add(hand.takeCard(cardIndex - 1));
                        }
                        else
                        {
                            System.out.println("Chosen card doesn't exist.");
                        }
                    }
                }
                else
                {
                    System.out.println("You may exchange UP TO 4 cards.");
                    errorOccurred = true;
                }
            }
            else
            {
                System.out.println("Wrong data format.");
                errorOccurred = true;
            }
            if (errorOccurred)
            {
                System.out.println("Try again.");
                choice = "N";
            }
            else {
                System.out.println("Your choice: " + Card.getCardList(toExchange));
                System.out.print("Do you confirm?: [Y/N] ");
                choice = sc.next();
            }
        } while (!choice.equals("Y"));

        // Ktore cards chcesz wymienic?: 1,2,3
        // Bledny format danych | Mozesz wymienic maskymalnie 3 cards
        // Wybrales: 1 Trelf, 2 Karo, 3 Czarne
        // Zatwierdzasz wybor?: TAK
        howManyCardsExchanged = toExchange.size();
        return toExchange;
    }

    private boolean isDigitBetweenOneAndFour(String str)
    {
        boolean isDigitAppropriate = false;
        char[] signsInString = str.toCharArray();
        if (signsInString.length == 1)
        {
            char sign = signsInString[0];
            isDigitAppropriate = Character.isDigit(sign) && Character.getNumericValue(sign) > 0 &&  Character.getNumericValue(sign) < 5;
        }
        return isDigitAppropriate;
    }

    private String[] deleteEmptyElements(String[] array)
    {
        ArrayList<String> dynamicArrayNoEmptyElements = new ArrayList<>();
        for (String str : array)
        {
            if (!str.isEmpty())
                dynamicArrayNoEmptyElements.add(str);
        }
        String[] arrayNoEmptyElements = new String[dynamicArrayNoEmptyElements.size()];
        arrayNoEmptyElements = dynamicArrayNoEmptyElements.toArray(arrayNoEmptyElements);

        return arrayNoEmptyElements;
    }

    public String getNick() {

        return nick;
    }


}
