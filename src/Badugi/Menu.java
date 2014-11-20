package Badugi;

/**
 * Created by Adam on 2014-11-20.
 */
import java.util.Scanner;

public class Menu
{
    Scanner sc = new Scanner(System.in);
    int option;

    public void start() throws Exception
    {
        System.out.println("1. Begin game.");
        System.out.println("2. Abandon game.");
        System.out.println("What's your choice?");
        String choice = sc.nextLine();

        switch(choice)
        {
            case "1": playersChoice();
                break;
            case "2": System.out.println("Game over!");
                break;
            default:
            {
                simulateScreenCleanup();
                System.out.println("Wrong option!");
                start();
            }
            break;
        }
    }

    private void playersChoice() throws Exception
    {
        System.out.println("===== 2 PLAYERS =====");
        System.out.println("\t1. 1 Player, 1 Bot");
        System.out.println("\t2. 2 Players");
        System.out.println("\t3. 2 Bots");
        System.out.println("===== 3 PLAYERS =====");
        System.out.println("\t4. 1 Player, 2 Bots");
        System.out.println("\t5. 2 Players, 1 Bot");
        System.out.println("\t6. 3 Players");
        System.out.println("\t7. 3 Bots");
        System.out.println("===== 4 PLAYERS =====");
        System.out.println("\t8. 1 Player, 3 Bots");
        System.out.println("\t9. 2 Players, 2 Bots");
        System.out.println("\t10. 3 Players, 1 Bot");
        System.out.println("\t11. 4 Players");
        System.out.println("\t12. 4 Bots");
        System.out.println("===== 5 PLAYERS =====");
        System.out.println("\t13. 1 Player, 4 Bots");
        System.out.println("\t14. 2 Players, 3 Bots");
        System.out.println("\t15. 3 Players, 2 Bots");
        System.out.println("\t16. 4 Players, 1 Bot");
        System.out.println("\t17. 5 Players");
        System.out.println("\t18. 5 Bots");
        System.out.println("===== 6 PLAYERS =====");
        System.out.println("\t19. 1 Player, 5 Bots");
        System.out.println("\t20. 2 Players, 4 Bots");
        System.out.println("\t21. 3 Players, 3 Bots");
        System.out.println("\t22. 4 Players, 2 Bots");
        System.out.println("\t23. 5 Players, 1 Bot");
        System.out.println("\t24. 6 Players");
        System.out.println("\t25. 6 Bots");
        System.out.println("0. RETURN");
        System.out.println("What's your choice??");

        int choice = sc.nextInt();
        switch(choice)
        {
            case 1:
            {
                option = 1;
                play(1, 1);
            }
            break;
            case 2:
            {
                option = 2;
               play(2, 0);
            }
            break;
            case 3:
            {
                option = 3;
                play(0, 2);
            }
            break;
            case 4:
            {
                option = 4;
                play(1, 2);
            }
            break;
            case 5:
            {
                option = 5;
                play(2, 1);
            }
            break;
            case 6:
            {
                option = 6;
                play(3, 0);
            }
            break;
            case 7:
            {
                option = 7;
                play(0, 3);
            }
            break;
            case 8:
            {
                option = 8;
                play(1, 3);
            }
            break;
            case 9:
            {
                option = 9;
                play(2, 2);
            }
            break;
            case 10:
            {
                option = 10;
                play(3, 1);
            }
            break;
            case 11:
            {
                option = 11;
                play(4, 0);
            }
            break;
            case 12:
            {
                option = 12;
                play(0, 4);
            }
            break;
            case 13:
            {
                option = 13;
                play(1, 4);
            }
            break;
            case 14:
            {
                option = 14;
                play(2, 3);
            }
            break;
            case 15:
            {
                option = 15;
                play(3, 2);
            }
            break;
            case 16:
            {
                option = 16;
                play(4, 1);
            }
            break;
            case 17:
            {
                option = 17;
                play(5, 0);
            }
            break;
            case 18:
            {
                option = 18;
                play(0, 5);
            }
            break;
            case 19:
            {
                option = 19;
                play(1, 5);
            }
            break;
            case 20:
            {
                option = 20;
                play(2, 4);
            }
            break;
            case 21:
            {
                option = 21;
                play(3, 3);
            }
            break;
            case 22:
            {
                option = 22;
                play(4, 2);
            }
            break;
            case 23:
            {
                option = 23;
                play(5, 1);
            }
            break;
            case 24:
            {
                option = 24;
                play(6, 0);
            }
            break;
            case 25:
            {
                option = 25;
                play(0, 6);
            }
            break;
            case 0: start();
                break;
            default: playersChoice();
                break;
        }
    }

    private void play(int playersNumber, int botsNumber) throws Exception
    {
        Table table = new Table(playersNumber,botsNumber);
        table.game();
        
        System.out.println("Game over.");
        //start();
    }

    private void simulateScreenCleanup()
    {
        int i = 15;
        while (i-- > 0)
        {
            System.out.println("");
        }
    }


}
