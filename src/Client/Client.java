package Client;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
 * Created by Adam on 2014-11-20.
 */
public class Client extends Exception
{
    public static void main(String[] args)
    {
        if (args.length < 2) {
            System.out.println("run with args <addr> <port>");
            System.exit(-121);
        }

        String addr = args[0];
        int port = 0;
        try {
            port = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid port number");
            System.exit(-123);
        }

        Gui gui = new Gui();
        JFrame frame = new JFrame();
        frame.add(gui);
        frame.setSize(300, 300);
        frame.setTitle("Client");
        frame.addWindowListener(new MyWindowAdapter(gui));
        frame.setVisible(true);
        if(!gui.startConnection(addr,port)) {
            System.out.println("Unable to connect...");
            //System.exit(0);
        }

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

    /**
     * Small private class for window handling
     *
     *
     */
    private static class MyWindowAdapter extends WindowAdapter {
        private Gui gui;

        public MyWindowAdapter(Gui gui){
            this.gui= gui;
        }

        public void windowClosing(WindowEvent e) {
            gui.end();
            System.exit(0);
        }
    }
}

