package src.frontend;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
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
    JButton Main_Friends_Remove_Button = new JButton("Remove");
    DefaultTableModel friendsTableModel = new DefaultTableModel(new Object[] { "Name", "IP Address" }, 0);
    JTable Main_Friends_Table = new JTable(friendsTableModel);

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

    // Colors Preset
    public static final Color PRIMARY = new Color(1, 116, 228);
    public static final Color PRIMARY_CONTENT = new Color(229, 242, 255);
    public static final Color PRIMARY_DARK = new Color(1, 90, 177);
    public static final Color PRIMARY_LIGHT = new Color(26, 142, 254);

    public static final Color SECONDARY = new Color(56, 1, 228);
    public static final Color SECONDARY_CONTENT = new Color(235, 229, 255);
    public static final Color SECONDARY_DARK = new Color(44, 1, 177);
    public static final Color SECONDARY_LIGHT = new Color(81, 26, 254);

    public static final Color BACKGROUND = new Color(24, 26, 27);
    public static final Color FOREGROUND = new Color(36, 38, 40);
    public static final Color BORDER = new Color(61, 64, 67);

    public static final Color COPY = new Color(251, 251, 251);
    public static final Color COPY_LIGHT = new Color(215, 217, 219);
    public static final Color COPY_LIGHTER = new Color(161, 166, 170);

    public static final Color SUCCESS = new Color(1, 228, 1);
    public static final Color WARNING = new Color(228, 228, 1);
    public static final Color ERROR = new Color(228, 1, 1);
    public static final Color SUCCESS_CONTENT = new Color(0, 0, 0);
    public static final Color WARNING_CONTENT = new Color(0, 0, 0);
    public static final Color ERROR_CONTENT = new Color(255, 229, 229);

    public Ui() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 960);
        setTitle("Swift Share");

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
        Main_Files_Delete_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        Main_Files_Clear_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        Main_Friends_Add_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog(new JFrame(), "Add Friend", true); // Parent frame can be null or your main
                                                                                // application frame
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));

                // Components
                JLabel add_Friend_Name_Label = new JLabel("Name");
                JTextField add_Friend_Name_TextField = new JTextField(10);
                JLabel add_Friend_IP_Label = new JLabel("IP Address");
                JTextField add_Friend_IP_TextField = new JTextField(10);
                JButton add_Friend_Confirm = new JButton("Confirm");

                // Confirm button action
                add_Friend_Confirm.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            String Friend_Name = add_Friend_Name_TextField.getText().trim();
                            String Friend_IP = add_Friend_IP_TextField.getText().trim();

                            if (Friend_Name.isEmpty() || Friend_IP.isEmpty()) {
                                throw new IllegalArgumentException("Both fields must be filled.");
                            }

                            // Add to table model
                            friendsTableModel.addRow(new Object[] { Friend_Name, Friend_IP });

                            System.out.println("Input 1 / Name: " + Friend_Name);
                            System.out.println("Input 2 / IP Address: " + Friend_IP);

                            dialog.dispose(); // Close the dialog
                        } catch (IllegalArgumentException ex) {
                            JOptionPane.showMessageDialog(dialog, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                // Add components to dialog
                dialog.add(add_Friend_Name_Label);
                dialog.add(add_Friend_Name_TextField);
                dialog.add(add_Friend_IP_Label);
                dialog.add(add_Friend_IP_TextField);
                dialog.add(add_Friend_Confirm);

                // Finalize dialog
                dialog.pack();
                dialog.setLocationRelativeTo(null); // Center on screen
                dialog.setVisible(true);
            }
        });

        Main_Friends_Remove_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        Main_Setting_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        Main_SendFile_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        /*
         * // Hover Effects on Buttons
         * JButton[] buttons = { Main_Friends_Add_Button, Main_Files_Add_Button,
         * Main_Files_Clear_Button,
         * Main_SendFile_Button, Main_Setting_Button };
         * for (JButton button : buttons) {
         * button.addMouseListener((MouseListener) new MouseAdapter() {
         * public void mouseEntered(MouseEvent evt) {
         * button.setBackground(buttonColor.darker()); // Darker on hover
         * }
         * 
         * public void mouseExited(MouseEvent evt) {
         * button.setBackground(buttonColor); // Revert to original
         * }
         * });
         * }
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
        Main_Files_Panel.add(Main_Files_Label, fpg);
        fpg.gridy = 1;
        Main_Files_Panel.add(Main_Files_Add_Button, fpg);
        Main_Files_Panel.add(Main_Files_Delete_Button, fpg);
        Main_Files_Panel.add(Main_Files_Clear_Button, fpg);
        fpg.gridy = 2;
        fpg.gridwidth = 3;
        JScrollPane filesScrollPane = new JScrollPane(Main_Files_SelectedFiles_Table);
        Main_Files_Panel.add(filesScrollPane, fpg);

        // Adding the Friends Panel
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 1.0;
        Main_Friends_Panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        Main_Content_Panel.add(Main_Friends_Panel, gbc);

        // Adding components to the Friends Panel
        GridBagConstraints frpg = new GridBagConstraints();
        frpg.gridwidth = 1;
        frpg.weightx = 0.5;
        frpg.weighty = 0;
        frpg.gridy = 0;
        Main_Friends_Panel.add(Main_Friends_Label, frpg);
        frpg.gridy = 1;
        Main_Friends_Panel.add(Main_Friends_Add_Button, frpg);
        Main_Friends_Panel.add(Main_Friends_Remove_Button, frpg);
        JScrollPane friendsScrollPane = new JScrollPane(Main_Friends_Table);
        frpg.gridy = 2;
        frpg.weighty = 1.0;
        Main_Friends_Panel.add(friendsScrollPane, frpg);

        // Adding the Send Files Button
        gbc.gridx = 0; // Position X
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_START; // Anchor to start of line (left side)
        Main_Content_Panel.add(Main_Setting_Button, gbc);

        // Adding the Settings Button
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LAST_LINE_END;
        Main_SendFile_Button.setBackground(SECONDARY);
        Main_Content_Panel.add(Main_SendFile_Button, gbc);

        // Background Panel Settings
        Main_Background_Panel.setBackground(new Color(51, 51, 153));
        Main_Background_Panel.setOpaque(true);
        add(Main_Background_Panel);

        // Set the frame to be visible
        pack();
        setVisible(true);
    }
}
