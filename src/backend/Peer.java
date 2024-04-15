package src.backend;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Peer {
    public Peer() {
        // Get the IP address of the file owner's machine
        InetAddress fileOwnerIPAddress = null;
        try {
            fileOwnerIPAddress = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        String serverIP = fileOwnerIPAddress.getHostAddress();
        int serverPort = 50000; // Replace with the file owner's server port

        try {
            // Connect to the server
            System.out.println("...Connect to the server...");
            Socket socket = new Socket(serverIP, serverPort);

            // Create input and output streams for communication
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

            // File to share
            File fileToSend = new File("file_to_share.txt");

            // Send file name and size to the server
            dataOutputStream.writeUTF(fileToSend.getName());
            dataOutputStream.writeLong(fileToSend.length());

            // Open file input stream and send file content to the server
            try (FileInputStream fileInputStream = new FileInputStream(fileToSend)) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                    dataOutputStream.write(buffer, 0, bytesRead);
                }
                System.out.println("File sent successfully!");
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Close the connection
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
