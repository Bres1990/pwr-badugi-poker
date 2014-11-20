package Badugi;

/**
 * Created by Adam on 2014-11-20.
 */
import java.util.ArrayList;

public class Card implements Comparable<Card>
{
    Figure figure;
    Suit suit;

    public Card(Figure f, Suit s)
    {
        figure = f;
        suit = s;
    }

    public String toString()
    {
        String figureName = figure.name();
        String colourName = suit.name();
        figureName = figureName.replaceFirst("f", "");
        return capitalize(figureName).concat(capitalize(colourName));
    }

    private String capitalize(String line)
    {
        return Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }

    @Override
    public int compareTo(Card secondCard)
    {
        return figure.compareTo(secondCard.figure);
    }

    static String getCardList(ArrayList <Card> cards)
    {
        StringBuilder sb = new StringBuilder();
        for (Card k : cards)
        {
            sb.append(k).append(" | ");
        }
        return sb.toString();
    }
}

