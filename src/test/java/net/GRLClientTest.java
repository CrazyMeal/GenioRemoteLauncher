package net;

import org.junit.Test;

/**
 * Created by KVN on 29/05/2016.
 */
public class GRLClientTest {

    @Test
    public void test(){
        String hostname = "localhost";

        try {
            PRLClient client = new PRLClient(hostname, Protocol.port);
            client.run();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
