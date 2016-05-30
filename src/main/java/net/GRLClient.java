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
    private boolean alive;
    private boolean consoleMode;


    public GRLClient(String hostname, int port){
        try {
            this.socket = new Socket(hostname, port);

            if(this.socket != null){
                this.ndr = new NetDataReader(socket.getInputStream());
                this.ndw = new NetDataWriter(socket.getOutputStream());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

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
