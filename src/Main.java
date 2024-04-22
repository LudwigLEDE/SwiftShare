//Packages
package src;

//Imports
import src.backend.Peer;
import src.backend.SenderServer;
import src.frontend.Ui;

import java.net.UnknownHostException;

//Main Code
public class Main {
    public static void main(String[] args) {
            new Ui().anzeigen();
            SenderServer.start();
    }
}
