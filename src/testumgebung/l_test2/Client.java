package src.testumgebung.l_test2;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Replace "192.168.1.2" with the IP address of the server machine.
        try(Socket socket = new Socket("192.168.13.55", 50000)){
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

            while (true) {
                System.out.print("input> ");
                String message = scanner.nextLine();
                dataOutputStream.writeUTF(message);
                if(message.equalsIgnoreCase("exit()"))
                    break;
            }

        }catch (Exception e){
            System.out.println(e.toString());
            System.out.println("no conection");
        }
    }
}
