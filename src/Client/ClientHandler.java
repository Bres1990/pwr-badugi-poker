package Client;

/**
 * Created by Adam on 2014-11-23.
 */
import javax.swing.*;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Client thread responsible for communicating with the server.
 *
 *
 */
public class ClientHandler extends Thread {
    Socket client_socket = null;
    DataInputStream is = null;
    PrintStream os = null;
    DataInputStream inputLine = null;

    public ClientHandler(String addr, int port) {
        try {
            client_socket = new Socket(addr, port);
            os = new PrintStream(client_socket.getOutputStream());
            is = new DataInputStream(client_socket.getInputStream());
            inputLine = new DataInputStream(new BufferedInputStream(System.in));
        } catch (UnknownHostException e) {
            JOptionPane.showMessageDialog(null, "Cannot find host...", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Cannot connect to host...",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void run() {
        if (client_socket != null && os != null && is != null) {
            try {
                String responseLine;

                while ((responseLine = is.readLine()) != null) {
                    System.out.println(responseLine);
                    if (client_socket.isClosed())
                        break;
                    os.println(inputLine.readLine());
                }
                os.close();
                is.close();
                client_socket.close();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Connection lost...",
                        "ERROR", JOptionPane.ERROR_MESSAGE);
                System.out.println("Connection lost...");
            }
        }
    }

    /**
     * Checks if connection is being set up.
     *
     * @return true if it is.
     */
    public boolean isConnected() {
        if (client_socket != null && os != null && is != null)
            return client_socket.isConnected();
        else
            return false;

    }

    /**
     * Kill thread
     */
    public void killMe() {
        try {
            if (client_socket != null) {
                os.println("end");
                client_socket.close();
                client_socket = null;
            }
        } catch (IOException e) {

        }
    }

    /**
     * Send expression string and return result
     *
     * @param str
     *            expression
     * @return result
     * @throws IOException
     */
    public String sendToServer(String str) throws IOException {
        if (client_socket != null) {
            try {
                os.println(str);
                String line = is.readLine();
                return line;
            } catch (SocketException e) {
                return "connection problem...";
            }
        }
        return str;
    }
}

