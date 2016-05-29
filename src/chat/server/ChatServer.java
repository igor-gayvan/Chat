/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.server;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Igor Gayvan
 */
public class ChatServer implements Runnable {

    private static final int SERVER_TIMEOUT = 4500;

    private int ServerPort;
    private ServerSocket ServerSocket;
    private boolean listen;

    public ChatServer(int ServerPort) throws IOException {
        this.ServerPort = ServerPort;
        this.ServerSocket = new ServerSocket(ServerPort);
        this.ServerSocket.setSoTimeout(SERVER_TIMEOUT);
    }

    @Override
    public void run() {
        listen = true;
        while (listen) {
            try {
                Socket accept = ServerSocket.accept();
                accept.setSoTimeout(SERVER_TIMEOUT);
                accept.setSoLinger(true, 500);

                DataInputStream dis = new DataInputStream(accept.getInputStream());

                String input = dis.readUTF();

                switch (input) {
                    case "MSG": {
                        System.out.println(input);
                    }

                }

            } catch (SocketTimeoutException ex) {
                // NOOP
            } catch (IOException ex) {
                Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public void stop() {
        listen = false;
    }

}
