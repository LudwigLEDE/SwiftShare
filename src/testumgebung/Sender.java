package src.testumgebung;

import javax.swing.*;
import java.io.File;

public class Sender {

    private File file;
    public void setFile(File file){
        this.file = file;
    }
    public static void main(String[] args) {


        JFileChooser jfc = new JFileChooser();

        int returnValue = jfc.showOpenDialog(null);
        // int returnValue = jfc.showSaveDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            System.out.println(selectedFile.getAbsolutePath());
        }
    }
//FileInputStream und BufferedInputStream um die Files in bytes zu lesen.
//FileOutputStream und BufferedOutputStream um die Files in bytes zu schreiben.
//Bei beiden muss die default-Größe geändert werden
//Serversocket um auf die requests zu warten über das gleiche netzwerk und einen bestimmten port





}
