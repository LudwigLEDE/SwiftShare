package src.backend;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Peer {
    private File[] selectedFile;

    public void setSelectedFile(File[] file) {
        selectedFile = file;
    }

    public void sendFile(String ip, int port) throws UnknownHostException {

        try {
            for (File file : selectedFile) {
                // Connect to the server
                System.out.println("PEER: ...Connect to the server...");
                System.out.println("PEER: " + ip + " " + port);
                Socket socket = new Socket(ip, port);

                OutputStream os = socket.getOutputStream();
                DataOutputStream dos = new DataOutputStream(os);

                dos.writeInt(file.getName().length());
                dos.writeBytes(file.getName());
                dos.writeLong(file.length());

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
