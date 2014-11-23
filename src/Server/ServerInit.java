package Server;

/**
 * Created by Adam on 2014-11-23.
 */
public class ServerInit
{
    /**
     * @param args First argument is the port number, if it lacks or is unappropriate, 2222 gets set.
     */
    public static void main(String[] args) {

        if (args.length > 0) {
            try{
                int port = Integer.parseInt(args[0]);
                if (port > 65535 || port < 1) {
                    System.out.println("Invalid port number... starting as default 2222");
                    new ServerHandler();
                }
                else
                    new ServerHandler(port);
            } catch (NumberFormatException e) {
                System.out.println("Invalid port number... starting as default 2222");
                new ServerHandler();
            }
        } else {
            new ServerHandler();
        }

    }
}
