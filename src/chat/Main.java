/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import chat.server.ChatServer;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Igor Gayvan
 */
public class Main {

    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 5781;
    private static final int CLIENT_SERVER_PORT = 5782;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // создаем сервер
        ChatServer chatServer = null;
        try {
            chatServer = new ChatServer(CLIENT_SERVER_PORT);
            Thread thread = new Thread(chatServer);

            thread.start();

        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (chatServer != null) {
            try (Scanner scanner = new Scanner(System.in)) {
                boolean work = true;

                while (work) {
                    System.out.println("ACTIONS:");
                    System.out.println("1. [S]end message");
                    System.out.println("0. [E]xit");

                    String input = scanner.nextLine();

                    switch (input.toLowerCase()) {
                        case "0":
                        case "e":
                            chatServer.stop();
                            work = false;
                            continue;
                        case "1":
                        case "s":
                            System.out.print("Enter recepient address 192.168.1.");
                            String recepient = "localhost" /*192.168.1.".concat(scanner.nextLine())*/;
                            System.out.print("Enter message: ");
                            String message = scanner.nextLine();
                            
                            // создаем клиента для сервера
//                            ChatClient chatClient = new ChatClient(SERVER_ADDRESS, SERVER_PORT);
//                            chatClient.sendMessage(recepient, message);
                            break;
                        default:
                            System.out.println("UNKNOWN ACTION");
                            break;
                    }
                }
            }
        }
    }

}
