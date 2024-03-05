package src.backend;

import javax.swing.JFileChooser;
import java.io.File;

public class FileChooser() {

    // Create a JFileChooser instance
    JFileChooser fileChooser = new JFileChooser();

    // Optional: Set the current directory
    fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

    // Optional: Set to accept only files, directories, or both
    fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

    // Optional: Add a custom file filter
    fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image Files","jpg","png","gif","bmp"));

    // Show the dialog; wait until dialog is closed
    int result = fileChooser.showOpenDialog(null);

    // Check the selection
    if(result==JFileChooser.APPROVE_OPTION)
    {
        // User selected a file
        File selectedFile = fileChooser.getSelectedFile();
        System.out.println("Selected file: " + selectedFile.getAbsolutePath());
    }
}
