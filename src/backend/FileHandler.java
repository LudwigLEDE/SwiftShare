package src.backend;

import java.io.*;
import java.net.Socket;
import javax.swing.JOptionPane;

public class FileHandler implements Runnable {
    private Socket clientSocket;
    public FileHandler(Socket clientSocket){
        this.clientSocket = clientSocket;
    }

    public void run(){
        try {
            // Prompt the user to accept or reject the file
            if (confirmTransfer()) {
                // If the user accepts, proceed with file transfer
                handleTransfer();
            } else {
                // If the user rejects, close the connection
                clientSocket.close();
                System.out.println("File transfer rejected by user.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Confirm wether you want to accept or deny the request for File transfering, and if yes, return yes.
    private boolean confirmTransfer() {
        int choice = JOptionPane.showConfirmDialog(null, "Do you want to receive the file?", "File Transfer Confirmation", JOptionPane.YES_NO_OPTION);
        return choice == JOptionPane.YES_OPTION;
    }
    public void handleTransfer() {
        try {
            // Receive file from client
            InputStream is = clientSocket.getInputStream();

            // Read file name sent by client
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String fileName = reader.readLine();
            System.out.println("Received file name: " + fileName);

            // Save received file
            File downloadsFolder = new File(System.getProperty("user.home"), "Downloads");
            File newFile = new File(downloadsFolder, fileName);
            saveFile(is, newFile);

            is.close();
            clientSocket.close();
            System.out.println("File received and saved: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveFile(InputStream is, File file) {
        try {
            // Save received file to the specified directory
            FileOutputStream fos = new FileOutputStream(file);

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }

            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
