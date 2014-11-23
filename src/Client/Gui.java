package Client;

/**
 * Created by Adam on 2014-11-23.
 */
import javax.swing.*;
import java.awt.event.*;

/**
 *
 * Class rendering the application GUI
 */
public class Gui extends JPanel {

    ClientHandler client = null;
    String addr;
    int port;



    /**
     * @brief Class constructor
     *
     */
    public Gui() {

    }
    /**
     * Starts connection with server.
     * @param addr Server address.
     * @param port Port.
     * @return true if it happens.
     */
    public boolean startConnection(String addr, int port){
        this.addr = addr; this.port = port;
        client = new ClientHandler(addr, port);
        client.run();
        return client.isConnected();
    }
    /**
     * Kills connection
     */
    public void end() {
        if (client != null)
            client.killMe();
    }
    /**
     *
     * @brief Class handling button pressing
     */
    private class HandlerClass implements ActionListener {
        /**
         * @brief ActionListener method handling button pressing
         */
        public void actionPerformed(ActionEvent event) {

        }
    }

    /**
     *
     * @brief Class handling keyboard key pressing
     */
    private class KeyHandler implements KeyListener {
        /**
         * @brief KeyListener method handling keyboard key pressing
         */
        public void keyTyped(KeyEvent event) {
//
//            if (event.getKeyChar() == '0')
//                display.setText(display.getText() + "0");
//            if (event.getKeyChar() == '1')
//                display.setText(display.getText() + "1");
//            if (event.getKeyChar() == '+') {
//                if (display.getText().length() > 1 && isAble(display.getText()))
//                    display.setText(display.getText() + "+");
//            }
//            if (event.getKeyChar() == '-') {
//                if (display.getText().length() > 1 && isAble(display.getText()))
//                    display.setText(display.getText() + "-");
//            }
//            if (event.getKeyChar() == '*') {
//                if (display.getText().length() > 1 && isAble(display.getText()))
//                    display.setText(display.getText() + "*");
//            }
//            if (event.getKeyChar() == '/') {
//                if (display.getText().length() > 1 && isAble(display.getText()))
//                    display.setText(display.getText() + "/");
//            }
//            if (event.getKeyChar() == '(') {
//
//                display.setText(display.getText() + "(");
//            }
//            if (event.getKeyChar() == ')') {
//
//                display.setText(display.getText() + ")");
//            }
//            if (event.getKeyChar() == '=') {
//                try {
//                    display.setText(client.sendToServer(display.getText()));
//                } catch (IOException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//            }
//            if (event.getKeyChar() == 'E') {
//                display.setText("");
//            }
//            if (event.getKeyChar() == 'C') {
//                display.setText(removeLastChar(display.getText()));
             }

        /**
         * Invoked when a key has been pressed.
         * See the class description for {@link java.awt.event.KeyEvent} for a definition of
         * a key pressed event.
         *
         * @param e
         */
        @Override
        public void keyPressed(KeyEvent e) {

        }

        /**
         * Invoked when a key has been released.
         * See the class description for {@link java.awt.event.KeyEvent} for a definition of
         * a key released event.
         *
         * @param e
         */
        @Override
        public void keyReleased(KeyEvent e) {

        }
    }



    /**
     * @brief Class responsible for monitoring changes in components.Klasa odpowiedzialna za nadzorowanie zmian w komponentach, użyta
     *        Used for handling window size and font size.
     *
     *
     */
    private class compListener implements ComponentListener {
        public void componentHidden(ComponentEvent e) {
        }

        public void componentMoved(ComponentEvent e) {
        }

        public void componentShown(ComponentEvent e) {
        }

        @Override
        /**
         * @brief Method called while changing component size.
         */
        public void componentResized(ComponentEvent e) {

        }


    }

}