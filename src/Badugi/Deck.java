package Badugi;

/**
 * Created by Adam on 2014-11-20.
 */
import java.util.ArrayList;
import java.util.Random;


public class Deck
{
    final int CARDSNUMBER = 52;
    ArrayList<Card> cards;

    public Deck()
    {
        cards = new ArrayList<>(CARDSNUMBER);
        generateDeck();

    }

    public void addCards(ArrayList<Card> cardsToAdd)
    {
        if(cards.size()+cardsToAdd.size()<= 52)
            cards.addAll(cardsToAdd);
    }

    private void generateDeck()
    {
        Suit[] suits = Suit.values();
        Figure[] figures = Figure.values();


        for(Suit suit : suits)
        {
            for(Figure figure : figures)
            {
                cards.add(new Card(figure,suit));
            }
        }

    }

    public int howManyCards()
    {
        return cards.size();
    }

    public Card randomCard()
    {
        Random rnd = new Random();
        return cards.remove(rnd.nextInt(howManyCards()));
    }

    public Hand randomHand()
    {
        ArrayList<Card> hand = new ArrayList<>(4);

        hand.add(randomCard());
        hand.add(randomCard());
        hand.add(randomCard());
        hand.add(randomCard());

        return new Hand(hand);
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        int counter = 1;
        for(Card c : cards)
        {
            sb.append(c).append(" | ");
            if(counter++ % 10 == 0)
                sb.append("\n");
        }
        return sb.toString();
    }

}
