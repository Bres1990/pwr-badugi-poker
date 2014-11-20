package Badugi;

/**
 * Created by Adam on 2014-11-20.
 */
import java.util.ArrayList;
import java.util.Collections;

public class Hand
{
    ArrayList<Card> cards;
    Setup setup;

    public Hand(ArrayList<Card> cards)
    {
        Collections.sort(cards);
        this.cards = cards;
    }

    public Card takeCard(int index)
    {
        return cards.get(index);
    }

    public void addCard(Card c)
    {
        if (cards.size() < 4)
        {
            cards.add(c);
        }
        Collections.sort(cards);
    }

    public void deleteCards(ArrayList<Card> cardsToDelete)
    {
        cards.removeAll(cardsToDelete);
    }

    public String getSetup()
    {
        determineSetup();
        return setup.name();
    }

    public void determineSetup()
    {
        if (isFourCardBadugi())
        {
            setup = Setup.FourCardBadugi;
        }
        else if (isThreeCardBadugi())
        {
            setup = Setup.ThreeCardBadugi;
        }
        else if (isPair())
        {
            setup = Setup.Pair;
        }
        else if (isTwoCardBadugi())
        {
            setup = Setup.TwoCardBadugi;
        }
        else if (isTwoPairs())
        {
            setup = Setup.TwoPairs;
        }
        else if (isThree())
        {
            setup = Setup.Three;
        }
        else if (isStreet())
        {
            setup = Setup.Street;
        }
        else if (isColour())
        {
            setup = Setup.Colour;
        }
        else
        {
            setup = Setup.LowestBadugi;
        }
    }

    private boolean isFourCardBadugi()
    {
        if (isColour() || isStreet() || isThree() || isPair())
        {
            return false;
        }
        else
        {
            return true;
        }

    }

    private boolean isThreeCardBadugi()
    {
        if (isColour() || isStreet() || isThree())
        {
            return false;
        }
        else
        {
            return true;
        }

    }

    private boolean isPair()
    {
        return compareCardFigures(0, 1) || compareCardFigures(1, 2) || compareCardFigures(2, 3);

    }

    private boolean isTwoCardBadugi()
    {
        if (isColour() || isStreet())
        {
            return false;
        }
        else
        {
            return true;
        }

    }

    private boolean isTwoPairs()
    {
        return compareCardFigures(0, 1) && compareCardFigures(2, 3);

    }

    private boolean isThree()
    {
        return compareCardFigures(0, 2) || compareCardFigures(1, 3);

    }

    private boolean isStreet()
    {
        ArrayList<Card> handCopy = (ArrayList<Card>) cards.clone();

        Card first = handCopy.get(0);
        Card last = handCopy.get(3);

        return (Math.abs((last.figure.getValue() - first.figure.getValue())) == 4) && !isPair();

    }

    private boolean isColour()
    {
        ArrayList<Card> handCopy = (ArrayList<Card>) cards.clone();
        boolean sameSuits = true;
        Card first = handCopy.remove(0);

        for (Card card : handCopy)
        {
            if ( !card.suit.equals(first.suit))
            {
                sameSuits = false;
                break;
            }

        }

        return sameSuits;

    }

    private boolean compareCardFigures(int indexOfFirst, int indexOfSecond)
    {
        return  cards.get(indexOfFirst).figure.equals(cards.get(indexOfSecond).figure);
    }

    private ArrayList<Card> returnThreeSetup()
    {
        ArrayList<Card> setup = new ArrayList<>();

        if (compareCardFigures(0, 2))
        {
            setup.add(cards.get(0));
            setup.add(cards.get(1));
            setup.add(cards.get(2));
        }
        else if (compareCardFigures(1, 3))
        {
            setup.add(cards.get(1));
            setup.add(cards.get(2));
            setup.add(cards.get(3));
        }

        return setup;
    }

    private ArrayList<Card> returnTwoPairsSetup()
    {

        ArrayList<Card> setup = new ArrayList<>();
        if (compareCardFigures(0, 1) && compareCardFigures(2, 3))
        {
            @SuppressWarnings("unchecked")
            ArrayList<Card> handCopy = (ArrayList<Card>) cards.clone();
            setup.addAll(handCopy);
            setup.remove(4);
        }

        return setup;
    }

    private ArrayList<Card> returnPairSetup()
    {
        ArrayList<Card> setup = new ArrayList<>();


        if (compareCardFigures(0, 1))
        {
            setup.add(cards.get(0));
            setup.add(cards.get(1));
        }
        else if (compareCardFigures(1, 2))
        {
            setup.add(cards.get(1));
            setup.add(cards.get(2));
        }
        else if ( compareCardFigures(2, 3))
        {
            setup.add(cards.get(2));
            setup.add(cards.get(3));
        }

        return setup;
    }

    private int sumOfHand()
    {
        return sumOfCards(cards);
    }

    private int sumOfCards(ArrayList<Card> cards)
    {
        int sum = 0;
        for (Card c : cards)
        {
            sum += c.figure.ordinal();
        }
        return sum;
    }

    public int compareTo(Hand secondHand)
    {
        determineSetup();
        secondHand.determineSetup();
        int comparisonResult = 0;


        if (setup.compareTo(secondHand.setup) == 0)
        {
            if (setup.equals(Setup.FourCardBadugi))
            {

            }
            else if (setup.equals(Setup.ThreeCardBadugi))
            {

            }
            else if (setup.equals(Setup.Pair))
            {
                comparisonResult = returnPairSetup().get(0).compareTo(secondHand.returnPairSetup().get(0));
                if (comparisonResult == 0)
                {
                    comparisonResult = cards.get(3).compareTo(secondHand.cards.get(3));
                }
            }
            else if (setup.equals(Setup.TwoCardBadugi))
            {

            }
            else if (setup.equals(Setup.TwoPairs))
            {
                if (sumOfCards(returnTwoPairsSetup()) == secondHand.sumOfCards(secondHand.returnTwoPairsSetup()))
                {
                    ArrayList<Card> cardsCopy = (ArrayList<Card>) cards.clone();
                    cardsCopy.removeAll(returnTwoPairsSetup());

                    ArrayList<Card> secondHandCardsCopy = (ArrayList<Card>)secondHand.cards.clone();
                    secondHandCardsCopy.removeAll(secondHand.returnTwoPairsSetup());
                    comparisonResult = cardsCopy.get(0).compareTo(secondHandCardsCopy.get(0));
                }
            }
            else if (setup.equals(Setup.Three))
            {
                comparisonResult = returnThreeSetup().get(0).compareTo(secondHand.returnThreeSetup().get(0));
            }
            else if (setup.equals(Setup.Street))
            {
                comparisonResult = cards.get(0).compareTo(secondHand.cards.get(0));
            }
            else if (setup.equals(Setup.Colour))
            {
                if (sumOfHand() > secondHand.sumOfHand())
                {
                    comparisonResult = 1;
                }
                else
                {
                    comparisonResult = -1;
                }
            }
            else if (setup.equals(Setup.LowestBadugi))
            {
                comparisonResult = cards.get(3).compareTo(secondHand.cards.get(3));
            }
        }
        else
        {
            comparisonResult = setup.compareTo(secondHand.setup);
        }

        return comparisonResult;
    }

    @Override
    public String toString() {
        return Card.getCardList(cards);
    }

}
