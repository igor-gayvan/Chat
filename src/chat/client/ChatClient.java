/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Igor Gayvan
 */
public class ChatClient implements Runnable {

    private String serverAddress;
    private int serverPort;

    private Socket socketClient;
    private DataOutputStream dos;
    private DataInputStream dis;

    public ChatClient(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;

        try {
            socketClient = new Socket();
            socketClient.setSoTimeout(1000);
            System.out.println("Connecting...");

            InetSocketAddress isa = new InetSocketAddress(serverAddress, serverPort);
            socketClient.connect(isa);
            System.out.println("Connect establish");

            dos = new DataOutputStream(socketClient.getOutputStream());
            dis = new DataInputStream(socketClient.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ChatClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        try {
            dos.writeUTF("MSG");
            dos.writeUTF("127.0.0.1"); ///*String.valueOf(socketClient.getLocalSocketAddress()*/));
            dos.writeUTF("Hello");

            dos.flush();

            String response = dis.readUTF();
            System.out.println(response);
        } catch (IOException ex) {
            Logger.getLogger(ChatClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
