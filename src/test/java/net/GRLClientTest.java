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
            GRLClient client = new GRLClient(hostname, Protocol.port);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
