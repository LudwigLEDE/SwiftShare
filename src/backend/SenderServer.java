package src.backend;

import java.net.ServerSocket;
import java.net.Socket;

public class SenderServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345); // Choose a port
            while (true) {
                Socket clientSocket = serverSocket.accept();
                // Handle each connection in a new thread
                new Thread(new FileHandler(clientSocket)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
