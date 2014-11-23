package Server;

/**
 * Created by Adam on 2014-11-23.
 */
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
/**
 * Thread class responsible for client-server communication
 *
 */
@SuppressWarnings("deprecation")
public class ClientServerThread extends Thread {

    private DataInputStream is = null;
    private PrintStream os = null;
    private Socket client_socket = null;
    /**
     * Class constructor
     * @param client_socket reference to socket that accepted the connection
     */

    public ClientServerThread(Socket client_socket) {
        this.client_socket = client_socket;
    }
    /**
     * Starts thread and handles communication with a client
     */
    public void run() {

        try {
            is = new DataInputStream(client_socket.getInputStream());
            os = new PrintStream(client_socket.getOutputStream());
            String line = null, answer = null;
            System.out.println("Connected with: " + client_socket.getLocalAddress());
            os.println("Connected.");
            while (true) {
                line = is.readLine();
                System.out.println("Received packet from " +client_socket.getInetAddress() + ": " + line);

                if (line.equals("Finished."))
                    break;
                if (line != null) {
                    if (line.indexOf(' ') != -1) {
                        line = line.replaceAll(" ", "");
                    }
                    try {
                       // badugi-connected answers
                    } catch (Exception e) {
                        answer = e.getMessage();
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Connected with "
                    + client_socket.getInetAddress() + " Lost connection");
            try {
                os.close();
                is.close();
                client_socket.close();
            } catch (IOException ee) {
            }
        }

        try {
            System.out.println("Connection with "
                    + client_socket.getInetAddress() + " finished");
            os.close();
            is.close();
            client_socket.close();
        } catch (IOException e) {
        }

    }
    /**
     * Checks if client socket is closed
     * @return true if closed, false if open
     */
    public boolean isClosed() {
        return client_socket.isClosed();
    }
}
