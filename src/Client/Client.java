package Client;

import java.awt.EventQueue;
/**
 * Created by Adam on 2014-11-20.
 */
public class Client extends Exception
{
    public static void main(String[] args)
    {
        final Badugi.Menu menu = new Badugi.Menu();
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                try {
                    menu.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
        });
    }
}
