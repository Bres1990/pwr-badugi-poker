package Badugi;

/**
 * Created by Adam on 2014-11-20.
 */
import java.util.ArrayList;

public abstract class Player implements Comparable<Player>
{
    protected Hand hand;
    protected String nick;
    int howManyCardsExchanged;
    public int chips, victories;
    boolean dealerButton, smallBlind = false, bigBlind = false;

    public Player(String nick)
    {
        this.nick = nick;
    }

    public abstract ArrayList<Card> chooseCardsToExchange();

    public int howManyCardsExchanged()
    {
        return howManyCardsExchanged;
    }

    public String getSetup()
    {
        return hand.setup.name();
    }

    @Override
    public int compareTo(Player secondPlayer)
    {
        return hand.compareTo(secondPlayer.hand);
    }

    public String toString()
    {
        return nick;
    }

    public String getNick()
    {
        return nick;
    }

    public Hand getHand()
    {
        return hand;
    }

    public String getCards()
    {
        return hand.toString();
    }
}

