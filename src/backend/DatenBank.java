package src.backend;

import java.io.*;
import javax.swing.*;
import javax.swing.table.*;

public class DatenBank {
    static String dateiname = "DB_Friends.txt";

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
