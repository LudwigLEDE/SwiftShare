package src.backend;

import java.io.*;
import java.net.Socket;

public class Peer {
    public static void main(String[] args) {
        // Server's IP address and port
        String serverIP = "127.0.0.1"; // Change to the actual server's IP address
        int serverPort = 12345; // Change to the actual server's port

        try {
            // Connect to the server
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
