package src.backend;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

//Ports die wir nutzen: 50000, 50001, 50002
public class SenderServer { // FileOwnerServer

    public static void start() {
        try {
            // Obtain the local host's IP address
            InetAddress localhost = InetAddress.getLocalHost();
            String serverIP = localhost.getHostAddress();

            // Display the server's IP address
            System.out.println("SenderServer: File owner's server IP address: " + serverIP);

            // Start the Server
            Options options = DatenBank.optionsLaden();

            ServerSocket serverSocket = new ServerSocket(options.port); // Choose a port
            System.out.println("SenderServer: File owner's server is running on port " + options.port );
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println(" SenderServer: Die Connection ist hergestellt");
                // Handle each connection in a new thread
                Thread clientThread = new Thread(new FileHandler(clientSocket));
                clientThread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
