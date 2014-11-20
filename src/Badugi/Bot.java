package Badugi;

/**
 * Created by Adam on 2014-11-20.
 */
import java.util.ArrayList;
import java.util.Random;


public class Bot extends Player {

    public static final int EASY_BOT = 1;
    public static final int HARDCORE_BOT = 2;
    Random random = new Random();
    private int botLevel;

    /**
     * Konstruktor domyslny
     */
    public Bot(String nick) {
        super(nick);
        this.botLevel = 1;
    }

    /*
     * Konstruktor umozliwiajacy wybranie stopnia zaawansowania IQ bota
     * @param botLevel - poziom inteligencji bota
     */
    public Bot(int botLevel) {
        super("I'm BOT");
        this.botLevel = botLevel;
    }

    public ArrayList<Card> chooseCardsToExchange() {
        ArrayList<Card> toExchange = null;

        switch(botLevel) {
            case EASY_BOT:
                toExchange = easyStrategy(hand.cards);
                break;
        }
        howManyCardsExchanged = toExchange.size();
        return toExchange;
    }

    /*
     * Easy strategy
     *
     * @param cards cards in hand
     * @return  cards which bot'd like to exchange
     */
    private ArrayList<Card> easyStrategy(ArrayList<Card> cards) {
        ArrayList<Card> toExchange;
        do {

            toExchange = new ArrayList<>();
            for (int i = 0; i < cards.size(); i++) {
                if (random.nextBoolean()) {
                    // losuj karte o indeksie i
                    Card c = randomCard();
                    toExchange.add(c);
                }
            }
        } while (toExchange.size() == cards.size());
        return toExchange;
    }

    private Card randomCard()
    {
        return hand.cards.get(random.nextInt(hand.cards.size()));
    }




}
