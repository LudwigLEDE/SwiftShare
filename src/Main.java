//Packages
package src;

//Imports

import src.backend.DatenBank;
import src.backend.SenderServer;
import src.frontend.Ui;

//Main Code
public class Main {
    public static void main(String[] args) {
            new Ui().anzeigen();
            SenderServer.start();
            new DatenBank();
            DatenBank.optionsLaden();
    }
}
