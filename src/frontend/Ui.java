package src.frontend;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class Ui extends JFrame {

    // Components
    JPanel Main_Background_Panel = new JPanel();
    JPanel Main_Content_Panel = new JPanel(new GridBagLayout());
    JPanel Main_Title_Panel = new JPanel();
    JLabel Main_Title_Label = new JLabel("SwiftShare");
    JLabel Main_Title_Logo_Label = new JLabel();

    JPanel Main_Friends_Panel = new JPanel(new GridBagLayout());
    JLabel Main_Friends_Label = new JLabel("User List");
    JButton Main_Friends_Add_Button = new JButton("Add");
    JTable Main_Friends_Table = new JTable();

    JPanel Main_Files_Panel = new JPanel(new GridBagLayout());
    JLabel Main_Files_Label = new JLabel("Selected Files");
    JButton Main_Files_Clear_Button = new JButton("Clear");
    JButton Main_Files_Delete_Button = new JButton("Delete");
    JButton Main_Files_Add_Button = new JButton("Add");
    DefaultTableModel filesTableModel = new DefaultTableModel(new Object[] { "File Name", "Path" }, 0);
    JTable Main_Files_SelectedFiles_Table = new JTable(filesTableModel);

    JPanel Main_Button_Panel = new JPanel();
    JButton Main_Setting_Button = new JButton("Settings");
    JButton Main_SendFile_Button = new JButton("Send Files");

    public Ui() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 960);
        setTitle("Swift Share");

        // Dimensions for components
        Dimension button_Panal = new Dimension(120, 30);

        // Color of the panel and buttons / components
        Color SendFile_Button = new Color(100, 149, 237);

        // ActionListener
        Main_Files_Add_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setMultiSelectionEnabled(true); // Enable multiple file selection

                // Show the file chooser dialog
                int result = fileChooser.showOpenDialog(null); // Use 'null' or 'Ui.this' as the parent

                // Process the result
                if (result == JFileChooser.APPROVE_OPTION) {
                    // User selected files
                    File[] selectedFiles = fileChooser.getSelectedFiles();
                    for (File file : selectedFiles) {
                        // Add file details to the table model
                        ((DefaultTableModel) Main_Files_SelectedFiles_Table.getModel())
                                .addRow(new Object[] { file.getName(), file.getAbsolutePath() });
                    }
                } else {
                    // User cancelled or an error occurred
                    System.out.println("File selection cancelled or an error occurred.");
                }
            }
        });
/*
        // Hover Effects on Buttons
        JButton[] buttons = { Main_Friends_Add_Button, Main_Files_Add_Button, Main_Files_Clear_Button,
                Main_SendFile_Button, Main_Setting_Button };
        for (JButton button : buttons) {
            button.addMouseListener((MouseListener) new MouseAdapter() {
                public void mouseEntered(MouseEvent evt) {
                    button.setBackground(buttonColor.darker()); // Darker on hover
                }

                public void mouseExited(MouseEvent evt) {
                    button.setBackground(buttonColor); // Revert to original
                }
            });
        }
*/
        // Layout for the main content panel
        Main_Background_Panel.setLayout(new BorderLayout());
        Main_Background_Panel.add(Main_Content_Panel, BorderLayout.CENTER);

        // GridBag constraints for the components
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Adding the Title Panel
        Main_Title_Panel.add(Main_Title_Label);
        Main_Background_Panel.add(Main_Title_Panel, BorderLayout.NORTH);

        // Adding the Files Panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 1.0;
        Main_Files_Panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        Main_Content_Panel.add(Main_Files_Panel, gbc);

        // Adding components to the Files Panel
        GridBagConstraints fpg = new GridBagConstraints();
        fpg.gridy = 0;
        fpg.gridwidth = 3;
        Main_Files_Panel.add(Main_Files_Label,fpg);
        fpg.gridy = 1;
        Main_Files_Panel.add(Main_Files_Add_Button,fpg);
        Main_Files_Panel.add(Main_Files_Delete_Button,fpg);
        Main_Files_Panel.add(Main_Files_Clear_Button,fpg);
        fpg.gridy = 2;
        fpg.gridwidth = 3;
        JScrollPane filesScrollPane = new JScrollPane(Main_Files_SelectedFiles_Table);
        Main_Files_Panel.add(filesScrollPane,fpg);

        // Adding the Friends Panel
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 1.0;
        Main_Friends_Panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        Main_Content_Panel.add(Main_Friends_Panel, gbc);

        // Adding components to the Friends Panel
        gbc.gridwidth = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 0;
        Main_Friends_Panel.add(Main_Friends_Label, gbc);
        Main_Friends_Panel.add(Main_Friends_Add_Button, gbc);
        JScrollPane friendsScrollPane = new JScrollPane(Main_Friends_Table);
        gbc.gridy = 1;
        gbc.weighty = 1.0;
        Main_Friends_Panel.add(friendsScrollPane, gbc);

        // Adding the Send Files Button

        gbc.gridx = 0; // Position X
        gbc.gridy = GridBagConstraints.RELATIVE; // Position Y, relative positioning for bottom
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_START; // Anchor to start of line (left side)
        Main_Content_Panel.add(Main_Setting_Button, gbc);

        // Adding the Settings Button
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.LAST_LINE_END;
        Main_Content_Panel.add(Main_Setting_Button, gbc);

        // Background Panel Settings
        Main_Background_Panel.setBackground(new Color(51, 51, 153));
        Main_Background_Panel.setOpaque(true);
        add(Main_Background_Panel);

        // Set the frame to be visible
        pack();
        setVisible(true);
    }
}
