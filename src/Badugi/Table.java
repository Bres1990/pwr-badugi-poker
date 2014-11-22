package Badugi;

/**
 * Created by Adam on 2014-11-20.
 */
import java.util.*;

public class Table
{
    ArrayList<Player> players;
    Deck deck;
    Decision d;
    Scanner sc = new Scanner(System.in);
    Random rnd = new Random();
    private Scanner money;
    Player randomPlayer, beginningPlayer, victor, smallBlindPlayer, bigBlindPlayer;

    public static boolean dealerButton = false, smallBlind = false, bigBlind = false, continued = false, allInFlag = false;
    int AllIn = 0, bet = 0, bid = 0;
    public static int bank = 0, paymentSmallBlind, paymentBigBlind, biddingNumber;

    public Table(int playersNumber, int botsNumber)
    {
        players = new ArrayList<>();
        deck = new Deck();

        while (playersNumber-- > 0)
        {
            addPlayer(new Human("Player " + (playersNumber + 1)));
        }
        while (botsNumber-- > 0)
        {
            addPlayer(new Bot("Bot " + (playersNumber + 1)));
        }
    }

    final void addPlayer(final Player p)
    {
        if (players.size() < 6)
        {
            players.add(p);
        }
    }

    final void dealCards()
    {
        for (Player p : players)
        {
            p.hand = deck.randomHand();
        }
    }

    final void randomCardsForPlayer(final ArrayList<Card> cardsToExchange, final Player p)
    {
        p.hand.deleteCards(cardsToExchange);
        int howManyCardsToExchange = cardsToExchange.size();
        while (howManyCardsToExchange-- > 0)
        {
            p.hand.addCard(deck.randomCard());
        }
        deck.addCards(cardsToExchange);
    }

    final Player determineVictor()
    {
        Collections.sort(players);
        return players.get(players.size() - 1);
    }

    final void displayTable()
    {
        for (Player p : players)
        {
            System.out.print(p + ": ");
            System.out.print(p.hand);
            System.out.println("  = " + p.hand.getSetup());
        }
    }

    void dealChips() throws Exception
    {
        try {
            if (continued == false)
            {
                System.out.println("What's the initial chips amount (higher than 0)?");
                money = new Scanner(System.in);

                int sumOfChips = money.nextInt();
                if (sumOfChips <= 0) {
                    System.out.println("Chips amount must be higher than 0! Try again . . .");
                    money = new Scanner(System.in);
                    sumOfChips = money.nextInt();
                }

                for (Player currentPlayer : players) {
                    currentPlayer.chips = sumOfChips;
                }
            }
        }
        catch (InputMismatchException f) {
            System.out.println("Try Again!");
            dealChips();
        }
    }

    void chipsCounting()
    {

                for (Player currentPlayer : players)
                {
                        if (allInFlag == false) {
                                if (currentPlayer == victor)
                                {
                                        currentPlayer.chips += bank;
                                }
                        }
                        else  // if player went All-In
                        {
                                if (currentPlayer == victor) {
                                        if (bank < (players.size() * AllIn)) { // if there's less than players_amount * allIn-budget in the bank, player takes it all
                                                currentPlayer.chips += bank ;
                                        } // if there's more than allIn-budget * players_amount in the bank, player takes only the equivalent of his allIn from every player
                                        else
                                        {
                                                currentPlayer.chips += (players.size() * AllIn);
                                        }
                                }
                        }
                }

    }

    void randomDealerButton()
    {
        randomPlayer = players.get(rnd.nextInt(players.size()));
        randomPlayer.dealerButton = true;
        System.out.println(randomPlayer + " has dealer button.");
    }

    void assignDealerButton()
    {
        for (ListIterator<Player> i = players.listIterator(); i.hasNext(); )
        {
            Player currentPlayer = i.next();
            if (currentPlayer.dealerButton == true)
            {
                System.out.println(currentPlayer+" had dealer button.");
                currentPlayer.dealerButton = false;
                System.out.println(currentPlayer+" no longer has dealer button.");
                currentPlayer = i.next();
                currentPlayer.dealerButton = true;
                System.out.println(currentPlayer+" has dealer button now.");
                currentPlayer = randomPlayer;
            }
        }
    }



    void assignBlinds()
    {
        if (players.size() == 2)
        {
            if (players.get(0).dealerButton == true)
            {
                players.get(0).bigBlind = true;
                System.out.println(players.get(0)+" has big blind.");
                players.get(1).smallBlind = true;
                System.out.println(players.get(1)+" has small blind.");
            } else if (players.get(1).dealerButton == true)
            {
                players.get(0).smallBlind = true;
                System.out.println(players.get(0)+" has small blind.");
                players.get(1).bigBlind = true;
                System.out.println(players.get(1)+" has big blind.");
            }
        }
        else if (players.size() == 3)
        {
            if (players.get(0).dealerButton == true)
            {
                players.get(1).smallBlind = true;
                System.out.println(players.get(1)+" has small blind.");
                players.get(2).bigBlind = true;
                System.out.println(players.get(2)+" has big blind.");
            } else if (players.get(1).dealerButton == true)
            {
                players.get(2).smallBlind = true;
                System.out.println(players.get(2)+" has small blind.");
                players.get(0).bigBlind = true;
                System.out.println(players.get(0)+" has big blind.");
            } else if (players.get(2).dealerButton == true)
            {
                players.get(0).smallBlind = true;
                System.out.println(players.get(0)+" has small blind.");
                players.get(1).bigBlind = true;
                System.out.println(players.get(1)+" has big blind.");
            }
        }
        else if (players.size() == 4)
        {
            if (players.get(0).dealerButton == true)
            {
                players.get(1).smallBlind = true;
                System.out.println(players.get(1)+" has small blind.");
                players.get(2).bigBlind = true;
                System.out.println(players.get(2)+" has big blind.");
            } else if (players.get(1).dealerButton == true)
            {
                players.get(2).smallBlind = true;
                System.out.println(players.get(2)+" has small blind.");
                players.get(3).bigBlind = true;
                System.out.println(players.get(3)+" has big blind.");
            } else if (players.get(2).dealerButton == true)
            {
                players.get(3).smallBlind = true;
                System.out.println(players.get(3)+" has small blind.");
                players.get(0).bigBlind = true;
                System.out.println(players.get(0)+" has big blind.");
            } else if (players.get(3).dealerButton == true)
            {
                players.get(0).smallBlind = true;
                System.out.println(players.get(0)+" has small blind.");
                players.get(1).bigBlind = true;
                System.out.println(players.get(1)+" has big blind.");
            }
        }
        else if (players.size() == 5)
        {
            if (players.get(0).dealerButton == true)
            {
                players.get(1).smallBlind = true;
                System.out.println(players.get(1)+" has small blind.");
                players.get(2).bigBlind = true;
                System.out.println(players.get(2)+" has big blind.");
            } else if (players.get(1).dealerButton == true)
            {
                players.get(2).smallBlind = true;
                System.out.println(players.get(2)+" has small blind.");
                players.get(3).bigBlind = true;
                System.out.println(players.get(3)+" has big blind.");
            } else if (players.get(2).dealerButton == true)
            {
                players.get(3).smallBlind = true;
                System.out.println(players.get(3)+" has small blind.");
                players.get(4).bigBlind = true;
                System.out.println(players.get(4)+" has big blind.");
            } else if (players.get(3).dealerButton == true)
            {
                players.get(4).smallBlind = true;
                System.out.println(players.get(4)+" has small blind.");
                players.get(0).bigBlind = true;
                System.out.println(players.get(0)+" has big blind.");
            } else if (players.get(4).dealerButton == true)
            {
                players.get(0).smallBlind = true;
                System.out.println(players.get(0)+" has small blind.");
                players.get(1).bigBlind = true;
                System.out.println(players.get(1)+" has big blind.");
            }
        }
        else if (players.size() == 6)
        {
            if (players.get(0).dealerButton == true)
            {
                players.get(1).smallBlind = true;
                System.out.println(players.get(1)+" has small blind.");
                players.get(2).bigBlind = true;
                System.out.println(players.get(2)+" has big blind.");
            } else if (players.get(1).dealerButton == true)
            {
                players.get(2).smallBlind = true;
                System.out.println(players.get(2)+" has small blind.");
                players.get(3).bigBlind = true;
                System.out.println(players.get(3)+" has big blind.");
            } else if (players.get(2).dealerButton == true)
            {
                players.get(3).smallBlind = true;
                System.out.println(players.get(3)+" has small blind.");
                players.get(4).bigBlind = true;
                System.out.println(players.get(4)+" has big blind.");
            } else if (players.get(3).dealerButton == true)
            {
                players.get(4).smallBlind = true;
                System.out.println(players.get(4)+" has small blind.");
                players.get(5).bigBlind = true;
                System.out.println(players.get(5)+" has big blind.");
            } else if (players.get(4).dealerButton == true)
            {
                players.get(5).smallBlind = true;
                System.out.println(players.get(5)+" has small blind.");
                players.get(0).bigBlind = true;
                System.out.println(players.get(0)+" has big blind.");
            } else if (players.get(5).dealerButton == true)
            {
                players.get(0).smallBlind = true;
                System.out.println(players.get(0)+" has small blind.");
                players.get(1).bigBlind = true;
                System.out.println(players.get(1)+" has big blind.");
            }
        }
    }





    void betting() throws Exception
    {
        System.out.println("Betting begins. ");
        System.out.println("-----------------------------------------------");
        System.out.println("Specify entry payment amount:");
        int payment = sc.nextInt();

        if (payment <= 0)
        {
            System.out.println("Payment ought to be higher than 0! Try again . . .");
            sc = new Scanner(System.in);
            payment = sc.nextInt();
        }

        betsSmallBig();

        for (Iterator<Player> i = players.iterator(); i.hasNext(); )
        {
            Player currentPlayer = i.next();
            if (currentPlayer.chips >= payment) // check if player can afford the payment
            {
                if (currentPlayer.smallBlind == true)
                {
                    currentPlayer.chips -= paymentSmallBlind;
                    bank += paymentSmallBlind;
                    System.out.println(currentPlayer+" has "+currentPlayer.chips+" chips left.");
                }
                else if (currentPlayer.bigBlind == true)
                {
                    currentPlayer.chips -= paymentBigBlind;
                    bank += paymentBigBlind;
                    System.out.println(currentPlayer+" has "+currentPlayer.chips+" chips left.");
                }
                else
                {
                    currentPlayer.chips -= payment;
                    bank += payment;
                    System.out.println(currentPlayer+" has "+currentPlayer.chips+" chips left.");
                }
            }
            else
            {
                System.out.println(currentPlayer+ " does not have enough chips to proceed!");
                if (players.size() == 1)
                {
                    System.out.println("The last player dropped out of the game!");
                    System.exit(0);
                }
                else
                {
                    i.remove();
                }
            }
        }
        System.out.println("Bank raised "+bank+" chips.");


    }

    void betsSmallBig()
    {
        System.out.println("Specify entry Small Blind payment:");
        money = new Scanner(System.in);
        paymentSmallBlind = money.nextInt();

        System.out.println("Specify entry Big Blind payment:");
        money = new Scanner(System.in);
        paymentBigBlind = money.nextInt();
    }

    void bidding(int biddingNumber)
    {
        this.biddingNumber = biddingNumber;
        //global variables: whoBegins, biddingNumber
        if (biddingNumber == 1)
        {
            bet = 0;
            bid = 0;
        }

        System.out.println("-----------------------------------------------");
        System.out.println("Bidding phase number "+biddingNumber+ " is in effect.");
        whoBegins();

        for (ListIterator<Player> i = players.listIterator(); i.hasNext(); )
        {
            Player currentPlayer = i.next();
            if (currentPlayer != beginningPlayer)
            {
                currentPlayer = i.next();
            }

            if (currentPlayer instanceof Human)
            {
                if (bet == 0) //if the first bid was not specified yet
                {
                    System.out.println("Now it's "+currentPlayer+" turn.");
                    System.out.print("Other players please close your eyes. Press any key to continue.");
                    sc.nextLine();
                    simulateScreenCleanup();
                    System.out.println("Time for "+currentPlayer+" move.");
                    System.out.println("1. Wait.");
                    System.out.println("2. Establish first bid.");
                    System.out.println("3. Waist.");
                    System.out.println("4. All-In.");
                    System.out.println();
                    System.out.println("You got "+currentPlayer.chips+" chips left.");
                    System.out.println("Current bidding bid is: "+bet+ " chips.");
                    System.out.println("Bank raised "+bank+" chips so far.");
                    System.out.println("What's your next move?");
                    try
                    {
                        int decision = sc.nextInt();
                        switch(decision)
                        {
                            case 1:
                                break;
                            case 2:
                            {
                                System.out.println("Specify bid amount: ");
                                bid = sc.nextInt();

                                if (currentPlayer.chips >= bid)
                                {
                                    bet += bid;

                                    if (bet <= 0)
                                    {
                                        bet -= bid;
                                        System.out.println("Deal ought to be higher than 0! Try again . . .");
                                        sc = new Scanner(System.in);
                                        bet += sc.nextInt();
                                    }

                                    currentPlayer.chips -= bet;
                                    bank += bet;
                                }
                                else
                                {
                                    System.out.println("You don't have enough chips! Specify bid amount again: ");
                                    bet += sc.nextInt();
                                    currentPlayer.chips -= bet;
                                    bank += bet;
                                }
                            }
                            break;
                            case 3:
                            {
                                if (players.size() > 1) {
                                    i.remove();
                                }
                                else
                                {
                                    System.out.println("The round finishes! All players' deals stay in the bank.");
                                }
                            }
                            break;
                            case 4:
                            {
                                allInFlag = true;
                                AllIn = currentPlayer.chips;
                                bank += currentPlayer.chips;
                                currentPlayer.chips = 0;

                                int numberOfPlayersLeft = players.size() - (int)(players.indexOf(currentPlayer)+1);

                                for (int x = 1; x <= numberOfPlayersLeft; x++)
                                {
                                    currentPlayer = i.next();
                                }
                                exchange(biddingNumber);
                                displayTable();
                                whoHasWon();
                                finishGame();
                            }
                            break;
                            default: bidding(1);
                                break;
                        }
                    }
                    catch (InputMismatchException f)
                    {
                        System.out.println("Try again!");
                        bidding(1);
                    }
                }
                else
                {
                    if(bet != 0)
                    {
                        System.out.println("Now it's " + currentPlayer+" turn!");
                        System.out.print("Other players please close your eyes. Press any key to continue.");
                        sc.nextLine();
                        simulateScreenCleanup();
                        System.out.println("Time for "+currentPlayer+" move.");
                        System.out.println("1. Wait.");
                        System.out.println("2. Raise.");
                        System.out.println("3. Call.");
                        System.out.println("4. Waist.");
                        System.out.println("5. All-In.");
                        System.out.println();
                        System.out.println("You got "+currentPlayer.chips+" chips left.");
                        System.out.println("Current bidding bid is: "+bet+ " chips.");
                        System.out.println("Bank raised "+bank+" chips so far.");
                        System.out.println("What's your next move?");
                        try
                        {
                            int decision = sc.nextInt();
                            switch(decision)
                            {
                                case 1:
                                    break;
                                case 2:
                                {
                                    System.out.println("How much would you like to raise?");
                                    int raise = sc.nextInt();
                                    bet += raise;

                                    if (bet <= 0)
                                    {
                                        bet -= raise;
                                        System.out.println("Bid ought to be higher than 0! Try again...");
                                        sc = new Scanner(System.in);
                                        bet = sc.nextInt();
                                    }

                                    if (currentPlayer.chips >= bet)
                                    {
                                        currentPlayer.chips -= bet;
                                        bank += bet;
                                    }
                                    else
                                    {
                                        bet -= raise;
                                        System.out.println("You don't have enough chips! Specify bid amount again:");
                                        bet += sc.nextInt();
                                        currentPlayer.chips -= bet;
                                        bank += bet;
                                    }
                                }
                                case 3:
                                {
                                    if (currentPlayer.chips >= bet)
                                    {
                                        currentPlayer.chips -= bet ;
                                        bank += bet;
                                    }
                                    else
                                    {
                                        System.out.println("You don't have enough chips! You lose your turn for fraud!");
                                    }
                                }
                                break;
                                case 4:
                                {
                                    if (players.size() > 1) {
                                        i.remove();
                                    }
                                    else
                                    {
                                        System.out.println("The round finishes! All players' deals stay in the bank.");
                                    }
                                }
                                break;
                                case 5:
                                {
                                    allInFlag = true;
                                    AllIn = currentPlayer.chips;
                                    bank += currentPlayer.chips;
                                    currentPlayer.chips = 0;

                                    int numberOfPlayersLeft = players.size() - (int)(players.indexOf(currentPlayer)+1);

                                    for (int x = 1; x <= numberOfPlayersLeft; x++)
                                    {
                                        currentPlayer = i.next();
                                    }
                                    exchange(biddingNumber);
                                    displayTable();
                                    whoHasWon();
                                    finishGame();
                                }
                                break;
                                default: bidding(1);
                                    break;
                            }
                        }
                        catch(InputMismatchException f)
                        {
                            System.out.println("Try Again!");
                            bidding(1);
                        }
                    }
                }
            }
        }








    }

    public final void cardsExchange(final Player currentPlayer)
    {
        System.out.print("Would you like to exchange cards?['Y'|'N']: ");
        String odp = sc.next();
        if (odp.equals("Y"))
        {
            randomCardsForPlayer(currentPlayer.chooseCardsToExchange(), currentPlayer);
            System.out.println("Your cards after exchange: " + currentPlayer.getCards());
        }
        else
        {
            System.out.println("!!!!!You don't exchange any.!!!!!");
        }
    }

    void exchange(int exchangeNumber)
    {
        for (Iterator<Player> i = players.iterator(); i.hasNext();)
        {
            Player currentPlayer = i.next();
            if (currentPlayer == beginningPlayer) { // beginning player doesn't exchange the cards
                currentPlayer = i.next();
            }
            if (currentPlayer instanceof Bot)
            {
                System.out.println("Bot makes his move...");
                currentPlayer.chooseCardsToExchange();
                System.out.println("Bot exchanged " + currentPlayer.howManyCardsExchanged() + " cards.");
                System.out.println("Press any key to finish his move: ");
                sc.nextLine();
            }
            else
            {
                System.out.println("");
                System.out.println("Time for " + currentPlayer +" move.");
                System.out.print("Other players please close your eyes. Press any key if you're ready.");
                sc.nextLine();
                simulateScreenCleanup();
                System.out.println("Your Cards: " +currentPlayer.getCards());
                System.out.println("-----------------------------------------------");
                cardsExchange(currentPlayer);
                sc.nextLine();
                System.out.print("Press any key to continue: ");
                sc.nextLine();
                simulateScreenCleanup();
                System.out.println(currentPlayer + " exchanged: " + currentPlayer.howManyCardsExchanged() + " cards.");

            }

        }
    }

    void whoBegins()
    {
        if (biddingNumber == 1)
        {
            for (ListIterator<Player> i = players.listIterator(); i.hasNext(); )
            {
                Player investigatedPlayer = i.next();
                if (investigatedPlayer.dealerButton == false && investigatedPlayer.smallBlind == false && investigatedPlayer.bigBlind == false)
                {
                    beginningPlayer = investigatedPlayer;
                }
            }
        }
        else if (biddingNumber > 1 && biddingNumber <= 4)
        {
            for (ListIterator<Player> i = players.listIterator(); i.hasNext(); )
            {
                Player investigatedPlayer = i.next();
                if (investigatedPlayer  == smallBlindPlayer)
                {
                    beginningPlayer = investigatedPlayer;
                }
            }
        }
    }

    void whoHasWon()
    {
        Player victor = determineVictor();
        System.out.println("---------------------------------------------------");
        System.out.println(victor + " has won with a setup of: " + victor.getSetup());
    }

    private void simulateScreenCleanup()
    {
        int i = 10;
        while (i-- > 0) {
            System.out.println("");
        }
    }

    void finishGame()
    {
        System.out.println("1. Continue game.");
        System.out.println("2. Finish game.");
        try
        {
            int decision = sc.nextInt();
            switch(decision)
            {
                case 1:
                    continued = true;
                    game();
                    break;
                case 2:
                    continued = false;
                    break;
                default: finishGame();
                    break;

            }
        }
        catch (InputMismatchException f)
        {
            System.out.println("Try again!");
            finishGame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void game() throws Exception
    {
        System.out.println("The game has begun.");
        allInFlag = false;
        bank = 0;
        randomDealerButton();
        assignBlinds();
        dealChips();
        betting();
        dealCards();
        System.out.println("Cards dealt.");
        bidding(1);
        exchange(1);
        bidding(2);
        exchange(2);
        bidding(3);
        exchange(3);
        bidding(4);
        displayTable();
        whoHasWon();
        chipsCounting();
//              System.out.println("chips counted");
        finishGame();
    }

}

