package net;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by KVN on 29/05/2016.
 */
public class GRLClient {
    private NetDataReader ndr;
    private NetDataWriter ndw;

    private Socket socket;
    private boolean alive;

    public GRLClient(String hostname, int port){
        try {
            this.socket = new Socket(hostname, port);
            this.ndr = new NetDataReader(socket.getInputStream());
            this.ndw = new NetDataWriter(socket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        this.alive = true;
        System.out.println("Genio Remote Launcher is now alive");
    }

    public void stop(){
        this.alive = false;
    }
}
