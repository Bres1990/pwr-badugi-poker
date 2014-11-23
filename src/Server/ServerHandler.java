package Server;

/**
 * Created by Adam on 2014-11-23.
 */
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * Server class, responsible for its handling
 *
 */
public class ServerHandler {

    ServerSocket server_socket = null;
    String line, answer, temp;
    DataInputStream is;
    PrintStream os;
    Socket client_socket = null;
    private final int max_client_count = 256;
    private ClientServerThread[] client_thread;
    /**
     * Custom constructor, setting server port to 2202.
     */
    ServerHandler() {
        startServer(2222);
    }
    /**
     * Main constructor, setting server port according to the user's instruction
     * @param port port to start the server on.
     */
    ServerHandler(int port){
        startServer(port);
    }
    /**
     * Uruchamia, konfiguruje i obsługuję serwer.
     * @param port port na którym ma być uruchomiony serwer.
     */
    private void startServer(int port) {
        client_thread = new ClientServerThread[max_client_count];
		/* Creating server socket */
        System.out.println("Creating server on port " + port + "...");
        try {
            server_socket = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println("Port " + port + " is not available.");
            System.exit(-1);
        }
        while (true) {
            try {
                client_socket = server_socket.accept();
                int i = 0;
                for (i = 0; i < max_client_count; i++) {
                    if (client_thread[i] == null) {
                        client_thread[i] = new ClientServerThread(client_socket);
                        client_thread[i].start();
                        break;
                    }
                    else {
                        if (client_thread[i].isClosed()) {
                            client_thread[i] = null;
                            --i;
                        }

                    }

                }
                if (i == max_client_count) {
                    PrintStream os = new PrintStream(client_socket.getOutputStream());
                    os.println("Server too busy. Try later.");
                    os.close();
                    client_socket.close();
                    System.out.println(client_socket.isClosed());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
