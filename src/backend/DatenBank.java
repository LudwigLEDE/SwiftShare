package src.backend;

import java.io.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 * The {@code DatenBank} class provides methods to save, load, and delete data from a file
 * into a JTable. The data is stored in a semicolon-separated format.
 */
public class DatenBank {
    static String dateiname = "DB_Friends.txt";

    /**
     * Saves the data from the provided {@code DefaultTableModel} into a file.
     * Each cell value is separated by a semicolon.
     *
     * @param model the {@code DefaultTableModel} containing the data to be saved
     */
    public static void speichern(DefaultTableModel model) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(dateiname))) {
            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    bw.write(model.getValueAt(i, j).toString() + ";");
                }
                bw.newLine();
            }
            System.out.println("Data saved successfully to " + dateiname);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the data from the specified file into the provided {@code JTable}.
     * Each line in the file corresponds to a row in the table, and cell values
     * are expected to be separated by semicolons.
     *
     * @param table the {@code JTable} to load the data into
     * @param dateiname the name of the file from which to load the data
     */
    public static void laden(JTable table, String dateiname) {
        try (BufferedReader br = new BufferedReader(new FileReader(dateiname))) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);
            String line;
            while ((line = br.readLine()) != null) {
                model.addRow(line.split(";"));
            }
            System.out.println("Data loaded successfully from " + dateiname);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes the selected row from the {@code JTable} and updates the file.
     *
     * @param table the {@code JTable} from which to delete the row
     * @param dateiname the name of the file to update
     * @param selectedRow the index of the row to delete
     */
    public static void delete(JTable table, String dateiname, int selectedRow) {
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.removeRow(selectedRow);
            speichern(model);
            System.out.println("Selected row deleted and file updated successfully.");
        } else {
            System.out.println("No row selected to delete.");
        }
    }
}
