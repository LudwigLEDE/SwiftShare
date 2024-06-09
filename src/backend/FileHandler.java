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
            DataInputStream dis = new DataInputStream(clientSocket.getInputStream());

            // Read the file name
            String fileName = dis.readUTF();

            // Read the file size
            long fileSize = dis.readLong();

            if (fileName.contains(File.separator) || fileName.contains("..")) {
                throw new IOException("Invalid file path received.");
            }

            File downloadsFolder = new File(System.getProperty("user.home"), "Downloads");
            if (!downloadsFolder.exists()) {
                downloadsFolder.mkdirs();
            }

            File file = new File(downloadsFolder, fileName);
            saveFile(dis, file, fileSize);

            clientSocket.close();
            System.out.println("File received and saved as " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveFile(DataInputStream dis, File file, long fileSize) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(file)) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            long totalBytesRead = 0;

            while (totalBytesRead < fileSize && (bytesRead = dis.read(buffer, 0, (int) Math.min(buffer.length, fileSize - totalBytesRead))) != -1) {
                fos.write(buffer, 0, bytesRead);
                totalBytesRead += bytesRead;
            }
        }
    }
}
