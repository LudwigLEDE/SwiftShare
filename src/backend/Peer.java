package src.backend;

import java.io.*;
import java.net.Socket;

public class Peer {
    //String serverIP = "die aktuelle ServerIP";
    //int serverPort = 12345;
    private static String filepath;
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345); // Connect to the server, Port needs to change to our
            // Implement file sharing logic here

            System.out.println("Connected to file owner in port 12345");
            // Two Streams, that read and write received Data from the socket
            DataInputStream in_so = new DataInputStream(socket.getInputStream());
            DataOutputStream out_so = new DataOutputStream(socket.getOutputStream());

            boolean connected = false;


            /*try{
                while(true){
                    try{
                        if(!connected){
                            new FileHandler(socket).run();
                            connected = true;
                        }
                        new
                    }
                }
            }*/

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
