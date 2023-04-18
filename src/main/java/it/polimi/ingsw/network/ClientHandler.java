package it.polimi.ingsw.network;

import it.polimi.ingsw.message.Message;
import it.polimi.ingsw.message.StartMatch;

import java.io.IOError;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

/**
 * this class manage connection from a client to the server and handles message's requests
 */
public class ClientHandler implements Runnable{
    private final Server server;
    private final Socket client;
    private final ServerSocket serversocket;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private boolean connected;
    public ClientHandler(Socket client,ServerSocket serversock,Server server){
        this.client=client;
        this.server=server;
        this.serversocket=serversock;
        this.connected=true;
        try {
            this.output = new ObjectOutputStream(client.getOutputStream());
            this.input = new ObjectInputStream(client.getInputStream());
        } catch (IOException e) {

        }
    }

    /**
     * ClientHandler checks if the connection isn't dropped and shares the request with the server that has methods to satisfy it
     */
    @Override
    public void run () {
        try{
            handleClientConnection();
        }catch(IOError e){
            disconnect();
        }
    }
    /**
     * disconnect() handles the disconnection of a client
     */
    private void disconnect() {
        if (connected) {
            try {
                if (!client.isClosed()) {
                    client.close();
                }
            } catch (IOException e) {

            }
            connected = false;
            Thread.currentThread().interrupt();
            server.HandleDisconnection();
        }
    }
    /**
     * handleClientConnection() after deserialization of message handle it with server's methods
     */
    private void handleClientConnection() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                    Message message = (Message) input.readObject();
                    if(message.getMessage().equals("enter_player")){
                        server.addClient(message.getnickname(),this);
                    }
                    else if(message.getMessage().equals("Client_has_disconnected")){
                        server.removeClient(message.getnickname());
                    }
                    else if(message.getMessage().equals("start_game")){
                        server.startGame();
                    }
                    else{
                        if(message.getMessage().equals("Mex_in_chat")){
                            server.broadcastMessage(this,message);
                        }
                    }
            }
        } catch (ClassCastException | ClassNotFoundException | IOException e) {

        }

    }

    /**
     * sendMessage() is used to send message to the client of this specific clientHanlder
     * @param message
     */
    public void sendMessage(Message message){
        try {
                output.writeObject(message);
                output.reset();

        } catch (IOException e) {
            disconnect();
        }
    }
    public boolean isConnected() {
        return connected;
    }
}