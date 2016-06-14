package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by KVN on 29/05/2016.
 */
public class PRLClient {
    private NetDataReader ndr;
    private NetDataWriter ndw;

    private Socket socket;
    private String hostname;
    private int port;
    private boolean alive;
    private boolean consoleMode;


    public PRLClient(String hostname, int port){
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
                    boolean instructionFound = false;
                    if(!instructionFound && userInput.equals("Connect")){
                        instructionFound = true;
                        boolean connectedToServer = this.tryConnection();
                        System.out.println("Attempt to connect succeeded > " + connectedToServer);
                    }

                    if(!instructionFound && userInput.equals("Stop")){
                        instructionFound = true;
                        this.stop();
                    }

                    if(!instructionFound && userInput.equals("Test")){
                        instructionFound = true;
                        System.out.println("Envoi d'un test");
                        this.sendTest();
                    }



                    if(!instructionFound && userInput.equals("List")){
                        instructionFound = true;
                        this.getInterfaceList();
                    }

                    if(!instructionFound){
                        System.out.println("Your command was not available");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void createSocketWithServer(){
        try {
            if(this.socket != null){
                this.socket.close();
                this.socket = null;
            }

            this.socket = new Socket(this.hostname, this.port);
            this.ndw = new NetDataWriter(this.socket.getOutputStream());
            this.ndr = new NetDataReader(this.socket.getInputStream());


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public boolean tryConnection(){
        boolean connectionSucceed;
        try {
            this.createSocketWithServer();

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

        this.closeSocket();

        return connectionSucceed;
    }

    public void sendTest() throws IOException {
        this.createSocketWithServer();
        this.ndw.writeDiscriminant(Protocol.TEST);
        this.ndw.send();

        this.closeSocket();
    }

    public void getInterfaceList(){
        this.createSocketWithServer();

        this.ndw.writeDiscriminant(Protocol.GET_INTERFACE_LIST);
        try {
            this.ndw.send();
        } catch (IOException e) {
            e.printStackTrace();
            this.closeSocket();
            return;
        }

        try {
            if( this.ndr.readDiscriminant() == Protocol.SERVER_SENDING_ITF_LIST ){
                int qtyOfStringToRead = this.ndr.readInt();

                for(int idx = 0 ; idx < qtyOfStringToRead ; idx++ ){
                    System.out.print(this.ndr.readString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            this.closeSocket();
            return;
        }

        this.closeSocket();

    }

    public void stop(){
        this.alive = false;
        this.closeSocket();
    }

    private void closeSocket(){
        if(this.socket != null){
            try {
                this.socket.close();
                this.socket = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setConsoleMode(boolean consoleMode) {
        this.consoleMode = consoleMode;
    }
}
