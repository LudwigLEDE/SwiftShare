package src.frontend;

import src.assets.ResourceManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

/**
 * The SettingsUi class is responsible for creating and managing the settings user interface.
 * It extends JPanel and includes various UI components such as buttons, labels, and text fields
 * to display and edit settings like IP address and port number.
 */
public class Settings extends JPanel {
    private Ui ui;
    private JTextField Settings_Part_2_TextField;
    private JLabel Settings_Part_2_Label;

    /**
     * Constructs a new SettingsUi with the specified Ui object.
     *
     * @param ui the Ui object that contains shared resources and methods
     */
    public Settings(Ui ui) {
        this.ui = ui;
        initComponents();
    }

    /**
     * Initializes and sets up the components of the settings UI.
     */
    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(Ui.BACKGROUND);

        // Panel for the title
        JPanel Settings_Title_Panel = new JPanel(new BorderLayout());
        Settings_Title_Panel.setBackground(Ui.BACKGROUND);
        JLabel Settings_Title_Label = new JLabel("Settings", SwingConstants.CENTER);
        Settings_Title_Label.setFont(ResourceManager.loadCustomFont("/src/assets/font/Involve-Regular.ttf", 30));
        Settings_Title_Label.setForeground(Color.WHITE);  // Assuming white text
        Settings_Title_Panel.add(Settings_Title_Label, BorderLayout.CENTER);

        // Panel for the back button
        JPanel Settings_Back_Panel = new JPanel(new BorderLayout());
        Settings_Back_Panel.setBackground(Ui.BACKGROUND);
        JButton Settings_Back_Button = new JButton("Back");
        Settings_Back_Button.setBackground(Color.GRAY);  // Assuming gray button color
        Settings_Back_Button.setForeground(Color.WHITE);  // Assuming white text on button
        Settings_Back_Panel.add(Settings_Back_Button, BorderLayout.WEST);

        // Panel for the content
        JPanel Settings_Content_Panel = new JPanel(new GridBagLayout());
        Settings_Content_Panel.setBackground(Ui.BACKGROUND);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Username setting
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.WHITE);  // Assuming white text
        JTextField usernameTextField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 1;
        Settings_Content_Panel.add(usernameLabel, gbc);
        gbc.gridx = 1;
        Settings_Content_Panel.add(usernameTextField, gbc);

        // Sound setting
        JLabel soundLabel = new JLabel("Sound:");
        soundLabel.setForeground(Color.WHITE);  // Assuming white text
        JCheckBox soundCheckBox = new JCheckBox();
        soundCheckBox.setBackground(Ui.BACKGROUND);  // Match background
        soundCheckBox.setForeground(Color.WHITE);  // Assuming white text
        gbc.gridx = 0;
        gbc.gridy = 2;
        Settings_Content_Panel.add(soundLabel, gbc);
        gbc.gridx = 1;
        Settings_Content_Panel.add(soundCheckBox, gbc);

        // Part 2: Port settings
        Settings_Part_2_Label = new JLabel("Current Port: " + Ui.Port);
        Settings_Part_2_Label.setForeground(Color.WHITE);  // Assuming white text
        Settings_Part_2_TextField = new JTextField(10);
        gbc.gridx = 0;
        gbc.gridy = 3;
        Settings_Content_Panel.add(Settings_Part_2_Label, gbc);
        gbc.gridx = 1;
        Settings_Content_Panel.add(Settings_Part_2_TextField, gbc);

        // Confirm button
        JButton Settings_Confirm_Button = new JButton("Confirm");
        Settings_Confirm_Button.setBackground(Color.GRAY);  // Assuming gray button color
        Settings_Confirm_Button.setForeground(Color.WHITE);  // Assuming white text on button
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        Settings_Content_Panel.add(Settings_Confirm_Button, gbc);

        // Action listener for the confirm button
        Settings_Confirm_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newPort = Settings_Part_2_TextField.getText();
                if (newPort.matches("\\d+")) {
                    Ui.Port = Integer.parseInt(newPort);
                    Settings_Part_2_Label.setText("Current Port: " + Ui.Port);
                    JOptionPane.showMessageDialog(Settings.this, "Port updated successfully.");
                } else {
                    JOptionPane.showMessageDialog(Settings.this, "Invalid port number. Please enter a valid number.");
                }
            }
        });

        // Adding parts to the main panel
        add(Settings_Title_Panel, BorderLayout.NORTH);
        add(Settings_Back_Panel, BorderLayout.SOUTH);
        add(Settings_Content_Panel, BorderLayout.CENTER);
    }
}
