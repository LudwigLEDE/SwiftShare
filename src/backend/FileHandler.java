package src.backend;

import java.io.*;
import java.net.Socket;

public class FileHandler implements Runnable {
    private Socket clientSocket;
    public FileHandler(Socket clientSocket){
        this.clientSocket = clientSocket;
    }

    public void run() {
        try {
            /*(
                // Creating input and output streams for communication
                DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());
                DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream())
        ) {
            // Receive the file name and size from the client
            String fileName = dataInputStream.readUTF();
            long fileSize = dataInputStream.readLong();
*/

            // Receive file from client
            InputStream is = clientSocket.getInputStream();

            String fileName = "received_file";
            System.out.println("FileHandler: Receiving File: " + fileName);

            // Create a file output stream to write the received file
            File file = new File("received_" + fileName);
            try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                // Receive the file content
                byte[] buffer = new byte[4096];
                int bytesRead;

                while ((bytesRead = is.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, bytesRead);
                }

                System.out.println("FileHandler: File received successfully!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // Close the client socket
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

