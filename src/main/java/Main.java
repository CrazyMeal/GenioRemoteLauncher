import net.GRLClient;
import net.Protocol;

/**
 * Created by KVN on 30/05/2016.
 */
public class Main {

    public static void main(String... args){
        String hostname = "localhost";

        try {
            GRLClient client = new GRLClient(hostname, Protocol.port);
            client.setConsoleMode(true);
            client.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
