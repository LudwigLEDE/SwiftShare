package src.backend;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Peer {
    private File[] selectedFile;

    public void setSelectedFile(File[] file) {
        selectedFile = file;
    }

    public void sendFile(String ip, int port) throws UnknownHostException {
        // Get the IP address of the file owner's machine
        /* InetAddress fileOwnerIPAddress = null;
        try {
            fileOwnerIPAddress = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        String serverIP = fileOwnerIPAddress.getHostAddress();
        int serverPort = 50000; // Replace with the file owner's server port
        InetAddress localhost = InetAddress.getLocalHost();
        String serverIP = localhost.getHostAddress();
*/


        try {
            for (File file : selectedFile) {
                // Connect to the server
                System.out.println("PEER: ...Connect to the server...");
                System.out.println("PEER: " + ip + " " + port);
                Socket socket = new Socket(ip, port);

                // Create input and output streams for communication
                /*
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

                // Send file name and size to the server
                dataOutputStream.writeUTF(selectedFile.getName());
                dataOutputStream.writeLong(selectedFile.length());
*/
                OutputStream os = socket.getOutputStream();
                // Open file input stream and send file content to the server
                try (FileInputStream fileInputStream = new FileInputStream(file)) {
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                        os.write(buffer, 0, bytesRead);
                    }
                    System.out.println("PEER: File sent successfully: " + file.getName());
                }
                catch (IOException e) {
                    e.printStackTrace();
                // Close the connection
                }

                os.close();
                socket.close();
            }

        }
        catch(IOException e){
                e.printStackTrace();
                System.err.println("PEER: Error occured while sending file : ");
        }
    }
}
