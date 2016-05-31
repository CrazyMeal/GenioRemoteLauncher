package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by KVN on 29/05/2016.
 */
public class GRLClient {
    private NetDataReader ndr;
    private NetDataWriter ndw;

    private Socket socket;
    private String hostname;
    private int port;
    private boolean alive;
    private boolean consoleMode;


    public GRLClient(String hostname, int port){
        this.hostname = hostname;
        this.port = port;
    }

    public void run(){
        this.alive = true;
        System.out.println("Genio Remote Launcher is now alive");

        if(this.consoleMode){
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            this.alive = true;
            String userInput = "";
            try {
                while(this.alive && (userInput = stdIn.readLine()) != null){
                    if(userInput.equals("Stop")){
                        this.stop();
                    }

                    if(userInput.equals("Test")){
                        System.out.println("Envoi d'un test");
                        this.sendTest();
                    }

                    if(userInput.equals("Connect")){
                        boolean connection_state = this.connect();
                        System.out.println("Connected > " + connection_state);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public boolean connect(){
        boolean connectionSucceed = false;
        try {
            this.socket = new Socket(this.hostname, this.port);

            this.ndw = new NetDataWriter(this.socket.getOutputStream());
            this.ndr = new NetDataReader(this.socket.getInputStream());

            this.ndw.writeDiscriminant(Protocol.CONNECT_REQUEST);
            this.ndw.send();

            if(this.ndr.readDiscriminant() == Protocol.OK){
                connectionSucceed = true;
            } else {
                connectionSucceed = false;
            }

        } catch (IOException e) {
            connectionSucceed = false;
            e.printStackTrace();
        }

        return connectionSucceed;
    }

    public void sendTest() throws IOException {
        this.ndw.writeDiscriminant(Protocol.TEST);
        this.ndw.send();
    }

    public void stop(){
        this.alive = false;
    }

    public boolean isConsoleMode() {
        return consoleMode;
    }

    public void setConsoleMode(boolean consoleMode) {
        this.consoleMode = consoleMode;
    }
}
