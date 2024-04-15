package src.backend;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
//Ports die wir nutzen: 50000, 50001, 50002
public class SenderServer { // FileOwnerServer

    private static  final int SERVER_PORT = 50000;
    public static void start() {
        try {
            // Obtain the local host's IP address
            InetAddress localhost = InetAddress.getLocalHost();
            String serverIP = localhost.getHostAddress();

            // Display the server's IP address
            System.out.println("File owner's server IP address: " + serverIP);

            // Start the Server
            ServerSocket serverSocket = new ServerSocket(50000); // Choose a port
            System.out.println("File owner's server is running on port " + SERVER_PORT );
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
