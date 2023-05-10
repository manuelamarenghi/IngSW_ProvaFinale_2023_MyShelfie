package it.polimi.ingsw;

import it.polimi.ingsw.Controller.ClientController;
import it.polimi.ingsw.modello.Match;
import it.polimi.ingsw.network.MessageHandler;
import it.polimi.ingsw.network.SocketClient;
import it.polimi.ingsw.view.Cli;
import it.polimi.ingsw.view.VirtualModel;

import java.io.IOException;

public class ClientApp {
    public static void main(String[] args) throws IOException, InterruptedException {
      /*  boolean cliParam = false; // default value

        for (String arg : args) {
            if (arg.equals("--cli") || arg.equals("-c")) {
                cliParam = true;
                break;
            }
        }

        if (cliParam) {
            Cli view = new Cli();
            ClientController clientcontroller = new ClientController(null,null);
        } else {
            //Application.launch(JavaFXGui.class);
        }*/
        /*Scanner in=new Scanner(System.in);
        SocketClient sClient=new SocketClient("localhost",16847);
        System.out.println("inserire nickname");
        String s=in.nextLine();
        Message m=new EnterPlayer(s);
        sClient.sendMessage(m);
        sClient.readMessage();
        Message m2=new MexInChat("Hi there ",s);
        System.out.println("sono qui");
        sClient.sendMessage(m2);
        sClient.readMessage();*/

        Match match = new Match();
        SocketClient socketClient = new SocketClient("localhost" , 16847);
        socketClient.readMessage();

        VirtualModel virtualModel = new VirtualModel(match);
        MessageHandler messageHandler = new MessageHandler(virtualModel);
        socketClient.addObserver(messageHandler);
        Cli cli = new Cli();
        virtualModel.addObserver(cli);
        ClientController clientController = new ClientController(cli, virtualModel,socketClient);
        cli.addObserver(clientController);
        cli.start();

    }
}

