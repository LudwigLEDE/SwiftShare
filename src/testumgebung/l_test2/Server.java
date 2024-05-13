package src.testumgebung.l_test2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;

    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(50002)){
            System.out.println("Listening on port: 50000");
            Socket clientSocket = serverSocket.accept();
            System.out.println(clientSocket + " connected\n");
            dataInputStream = new DataInputStream(clientSocket.getInputStream());
            dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());

            String message;
            while (true) {
                message = dataInputStream.readUTF();
                System.out.println(message);
                if(message.equalsIgnoreCase("exit()"))
                    break;
            }
            clientSocket.close();

        } catch (Exception e){
            System.out.println(e.toString());
        }
    }
}
