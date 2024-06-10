//Package
package src.frontend;

//File Imports
import src.backend.DatenBank;
import src.backend.Peer;
import src.backend.FileHandler;


import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * The Ui class represents the main user interface of the application.
 * It provides the layout and functionality for user interaction with the application.
 */
public class Ui extends JFrame {

    // Components
    private JPanel Main_Background_Panel = new JPanel();
    private JPanel Main_Content_Panel = new JPanel(new GridBagLayout());
    private JPanel Main_Title_Panel = new JPanel();
    private JLabel Main_Title_Label = new JLabel("SwiftShare");
    private JLabel Main_Title_Logo_Label = new JLabel();

    private JPanel Main_Friends_Panel = new JPanel(new GridBagLayout());
    private JPanel Main_Friends_Header_Panel = new JPanel();
    private JLabel Main_Friends_Label = new JLabel("User List");
    private JButton Main_Friends_Add_Button = new JButton("Add");
    private JButton Main_Friends_Remove_Button = new JButton("Remove");
    private DefaultTableModel friendsTableModel = new DefaultTableModel(new Object[] { "Name", "IP Address" }, 0);
    private JTable Main_Friends_Table = new JTable(friendsTableModel);
    private JScrollPane friendsScrollPane = new JScrollPane(Main_Friends_Table);

    private JPanel Main_Files_Panel = new JPanel(new GridBagLayout());
    private JPanel Main_Files_Header_Panel = new JPanel();
    private JLabel Main_Files_Label = new JLabel("Selected Files");
    private JButton Main_Files_Clear_Button = new JButton("Clear");
    private JButton Main_Files_Delete_Button = new JButton("Delete");
    private JButton Main_Files_Add_Button = new JButton("Add");
    private DefaultTableModel filesTableModel = new DefaultTableModel(new Object[] { "File Name", "Path" }, 0);
    private JTable Main_Files_SelectedFiles_Table = new JTable(filesTableModel);
    private JScrollPane filesScrollPane = new JScrollPane(Main_Files_SelectedFiles_Table);

    private JPanel Main_Button_Panel = new JPanel();
    private JButton Main_Setting_Button = new JButton("Settings");
    private JButton Main_SendFile_Button = new JButton("Send Files");
    JFileChooser fileChooser = new JFileChooser();

    // Colors Preset
    public static final Color BLANK = new Color(0, 0, 0, 0);
    public static final Color PRIMARY = new Color(1, 116, 228);
    public static final Color BACKGROUND = new Color(0x131364);
    public static final Color FOREGROUND = new Color(0xFFFFFF);
    public static final Color BORDER = new Color(0xFFFFFF);
    public static final Color SENDFILES = new Color(0x0EE10E);

    public static int Port = 50000;

    /**
     * Constructs a new Ui instance and initializes the user interface components.
     */
    public void anzeigen() {

        //Datenbank Laden
        DatenBank.laden(Main_Friends_Table, "DB_Friends.txt");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280,   960);
        setTitle("Swift Share");
        setResizable(false);

        // Font Setup
        try {
            // Load the custom font from a file
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("src/assets/font/ttf/Involve-Regular.ttf"));
            font = font.deriveFont(24f); // Set font size to 24 pixels
            Main_Title_Label.setFont(font);
        } catch (FontFormatException e) {
            System.err.println("UI:Invalid font format.");
        } catch (IOException e) {
            System.err.println("UI:Could not read the font file.");
        }

        // Icon Setup
        Main_Title_Logo_Label.setIcon(new ImageIcon("src/assets/img/Logo100px.png"));
        // ActionListener
        Main_Files_Add_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                    System.out.println("UI: File selection cancelled or an error occurred.");
                }
            }
        });
        Main_Files_Delete_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected row index
                int selectedRow = Main_Files_SelectedFiles_Table.getSelectedRow();

                // Check if a row is actually selected
                if (selectedRow != -1) {
                    // Remove selected row from the model
                    filesTableModel.removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a file to delete.");
                }
            }
        });

        Main_Files_Clear_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Remove all rows from the model
                filesTableModel.setRowCount(0);
            }
        });

        Main_Friends_Add_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog(new JFrame(), "Add Friend", true);
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));

                // Components
                JLabel add_Friend_Name_Label = new JLabel("Name");
                JTextField add_Friend_Name_TextField = new JTextField(10);
                JLabel add_Friend_IP_Label = new JLabel("IP Address");
                JTextField add_Friend_IP_TextField = new JTextField("e.g., 192.168.1.1", 10);
                add_Friend_IP_TextField.setForeground(Color.GRAY);
                JButton add_Friend_Confirm = new JButton("Confirm");

                // Placeholder logic for IP Address
                add_Friend_IP_TextField.addFocusListener(new FocusAdapter() {
                    public void focusGained(FocusEvent e) {
                        if (add_Friend_IP_TextField.getText().equals("e.g., 192.168.1.1")) {
                            add_Friend_IP_TextField.setText("");
                            add_Friend_IP_TextField.setForeground(Color.BLACK);
                        }
                    }

                    public void focusLost(FocusEvent e) {
                        if (add_Friend_IP_TextField.getText().isEmpty()) {
                            add_Friend_IP_TextField.setForeground(Color.GRAY);
                            add_Friend_IP_TextField.setText("e.g., 192.168.1.1");
                        }
                    }
                });

                // Confirm button action
                add_Friend_Confirm.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            String Friend_Name = add_Friend_Name_TextField.getText().trim();
                            String Friend_IP = add_Friend_IP_TextField.getText().trim();

                            if (Friend_Name.isEmpty() || Friend_IP.isEmpty()) {
                                throw new IllegalArgumentException("Ui: Both fields must be filled.");
                            }

                            if (!Friend_IP.matches("^([0-9]{1,3}\\.){3}[0-9]{1,3}$")) {
                                throw new IllegalArgumentException("Ui: Invalid IP format.");
                            }

                            // Add to table model
                            friendsTableModel.addRow(new Object[] { Friend_Name, Friend_IP });

                            System.out.println("Ui: Input 1 / Name: " + Friend_Name);
                            System.out.println("Ui: Input 2 / IP Address: " + Friend_IP);

                            DatenBank.speichern(friendsTableModel);

                            dialog.dispose(); // Close the dialog
                        } catch (IllegalArgumentException ex) {
                            JOptionPane.showMessageDialog(dialog, ex.getMessage(), "Ui: Error",
                                    JOptionPane.ERROR_MESSAGE);
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
                // Get the selected row index
                int selectedRow = Main_Friends_Table.getSelectedRow();

                // Check if a row is actually selected
                if (selectedRow != -1) {
                    // Remove selected row from the model
                    DatenBank.delete(Main_Friends_Table,"DB_Friends.txt",selectedRow);
                } else {
                    JOptionPane.showMessageDialog(null, "Ui: Please select a friend to remove.");
                }
            }
        });

        Main_Setting_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settings();

            }
        });

        Main_SendFile_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    File[] selectedFiles = fileChooser.getSelectedFiles(); // Adjust this method to return File[] if not already
                    if (selectedFiles.length == 0) {
                        System.out.println("Ui: No files selected to send.");
                        return; // Exit if no files are selected
                    }
                    Peer peer = new Peer(); // Assume Peer can handle the files
                    peer.setSelectedFile(selectedFiles);
                    String selectedFriendIP = getSelectedFriendIP();
                    System.out.println("Selected Friend IP: " + selectedFriendIP);

                    for (File file : selectedFiles) {
                        System.out.println("File Path: " + file.getAbsolutePath());
                    }

                    peer.sendFile(selectedFriendIP, 50000);
                } catch (Exception ex) {
                    System.out.println("Ui: Error sending files: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });


        // Layout and more for UI
        //Layer 0
        setBackground(BACKGROUND);

        // Layer 1.1 Background Panel
        Main_Background_Panel.setBackground(BACKGROUND);
        Main_Background_Panel.setLayout(new BorderLayout(4, 4));
        add(Main_Background_Panel);

        // Layer 2.1 Content Panel
        Main_Content_Panel.setBackground(BACKGROUND);
        Main_Content_Panel.setLayout(new GridLayout(1, 1, 2, 2));
        Main_Background_Panel.add(Main_Content_Panel, BorderLayout.CENTER);

        // Layer 3.1 Title Panel
        Main_Title_Panel.setBackground(BACKGROUND);
        Main_Background_Panel.add(Main_Title_Panel, BorderLayout.NORTH);

        // Layer 3.2 Logo Label
        Main_Title_Panel.add(Main_Title_Logo_Label);

        // Layer 3.3 Title Label
        Main_Title_Label.setForeground(FOREGROUND);
        Main_Title_Panel.add(Main_Title_Label);

        // Layer 4.1 Files Panel
        Main_Files_Panel.setLayout(new BorderLayout(4, 4));
        Main_Files_Panel.setBackground(BACKGROUND);
        Main_Files_Panel.setForeground(FOREGROUND);
        Main_Files_Panel.add(Main_Files_Header_Panel, BorderLayout.NORTH);
        Main_Content_Panel.add(Main_Files_Panel);

        // Layer 4.2 Files Header
        Main_Files_Header_Panel.setBackground(BACKGROUND);
        Main_Files_Header_Panel.setForeground(FOREGROUND);
        Main_Files_Header_Panel.add(Main_Files_Label);
        Main_Files_Header_Panel.add(Main_Files_Add_Button);
        Main_Files_Header_Panel.add(Main_Files_Delete_Button);
        Main_Files_Header_Panel.add(Main_Files_Clear_Button);

        // Layer 4.2.2 Files Label
        Main_Files_Label.setForeground(FOREGROUND);

        // Layer 4.3 Files Table
        Main_Files_SelectedFiles_Table.setForeground(Color.BLACK);
        Main_Files_Panel.add(filesScrollPane, BorderLayout.CENTER);

        // Layer 4.3.2 Files Pane
        filesScrollPane.setBackground(BACKGROUND);
        filesScrollPane.setForeground(FOREGROUND);

        // Layer X.X
        JPanel Right_Content_Panel = new JPanel();
        Right_Content_Panel.setLayout(new BorderLayout(4, 4));
        Right_Content_Panel.setBackground(BACKGROUND);
        Main_Content_Panel.add(Right_Content_Panel);

        // Layer 5.1 Friends Panel
        Main_Friends_Panel.setLayout(new BorderLayout(4, 4));
        Main_Friends_Panel.setBackground(BACKGROUND);
        Main_Friends_Panel.setForeground(FOREGROUND);
        Right_Content_Panel.add(Main_Friends_Panel, BorderLayout.CENTER);

        // Layer 5.2 Friends Header
        Main_Friends_Header_Panel.setBackground(BACKGROUND);
        Main_Friends_Header_Panel.setForeground(FOREGROUND);
        Main_Friends_Header_Panel.add(Main_Friends_Label);
        Main_Friends_Header_Panel.add(Main_Friends_Add_Button);
        Main_Friends_Header_Panel.add(Main_Friends_Remove_Button);
        Main_Friends_Panel.add(Main_Friends_Header_Panel, BorderLayout.NORTH);

        // Layer 5.2.2 Friends Label
        Main_Friends_Label.setForeground(FOREGROUND);

        // Layer 5.4 Friends Table
        Main_Friends_Table.setForeground(Color.BLACK);

        // Layer 5.4.2 Friends Pane
        friendsScrollPane.setBackground(BACKGROUND);
        friendsScrollPane.setForeground(FOREGROUND);
        Main_Friends_Panel.add(friendsScrollPane, BorderLayout.CENTER);

        // Layer 6.1 Buttons Panel
        Main_Button_Panel.setBackground(BACKGROUND);
        Main_Button_Panel.setLayout(new BorderLayout(2, 2));
        Right_Content_Panel.add(Main_Button_Panel, BorderLayout.SOUTH);

        // Layer 6.2 Buttons
        Main_Button_Panel.add(Main_Setting_Button, BorderLayout.NORTH);
        Main_Button_Panel.add(Main_SendFile_Button, BorderLayout.SOUTH);

        // Layer 6.3 Setting Button
        Main_Setting_Button.setBackground(PRIMARY);
        Main_Setting_Button.setForeground(Color.white);

        // Layer 6.4 Send Button
        Main_SendFile_Button.setBackground(SENDFILES);
        Main_SendFile_Button.setForeground(Color.black);

        // Set the frame to be visible
        pack();
        setVisible(true);
    }

    /**
     * Retrieves the IP address of the selected friend from the friends table.
     *
     * @return the IP address of the selected friend, or null if no friend is selected or an error occurs.
     */
    public String getSelectedFriendIP() {
        int selectedRow = Main_Friends_Table.getSelectedRow();
        if (selectedRow != -1) {
            return Main_Friends_Table.getModel().getValueAt(selectedRow, 1).toString();
        } else {
            System.out.println("UI: No Ip Selected / IP Error");
            return null; // No selection or error handling
        }
    }

    /**
     * Switches the current view to the settings view.
     */
    public void settings(){
        Main_Background_Panel.remove(Main_Content_Panel);
        Main_Background_Panel.add(new SettingsUi(this), BorderLayout.CENTER);
        Main_Background_Panel.revalidate();
        Main_Background_Panel.repaint();
    }

    /**
     * Switches the current view back to the main view.
     */
    public void back(){
        Main_Background_Panel.removeAll();
        Main_Background_Panel.add(Main_Title_Panel, BorderLayout.NORTH); // Re-add the title panel
        Main_Background_Panel.add(Main_Content_Panel, BorderLayout.CENTER); // Re-add the content panel
        Main_Background_Panel.revalidate();
        Main_Background_Panel.repaint();
    }

    /**
     * Retrieves the IP address of the current machine.
     *
     * @return the IP address of the current machine, or a message indicating that the IP address could not be retrieved.
     */
    public static String getIPAddress() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = inetAddresses.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress.isSiteLocalAddress()) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
            return "Unable to get IP Address";
        }
        return "Unable to get IP Address";
    }

}
