package src.frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class SettingsUi extends JPanel {
    private Ui ui;

    public SettingsUi(Ui ui) {
        this.ui = ui;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(Ui.BACKGROUND);

        // Panel for the title
        JPanel Settings_Title_Panel = new JPanel(new BorderLayout());
        JLabel Settings_Title_Label = new JLabel("Settings", SwingConstants.CENTER);

        // Font
        try {
            // Load the custom font from a file
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("src/assets/font/ttf/Involve-Regular.ttf"));
            font = font.deriveFont(24f); // Set font size to 24 pixels
            Settings_Title_Label.setFont(font);
        } catch (FontFormatException e) {
            System.err.println("UI: Invalid font format.");
        } catch (IOException e) {
            System.err.println("UI: Could not read the font file.");
        }

        // Panel for the back button
        JPanel Settings_Back_Panel = new JPanel(new BorderLayout());
        JButton Settings_Back_Button = new JButton("Back");

        // Panel for the content
        JPanel Settings_Content_Panel = new JPanel(new GridBagLayout());
        Settings_Content_Panel.setBackground(Ui.BACKGROUND);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Part 1: Display IP address
        JPanel Settings_Part_1_Panel = new JPanel(new BorderLayout());
        JLabel Settings_Part_1_Ip_Label = new JLabel();

        // Part 2: Display and edit port
        JPanel Settings_Part_2_Panel = new JPanel(new GridBagLayout());
        JLabel Settings_Part_2_Label = new JLabel();
        JTextField Settings_Part_2_TextField = new JTextField(15);
        JButton Settings_Confirm_Button = new JButton("Confirm");
        Settings_Confirm_Button.setBackground(Ui.SENDFILES);
        Settings_Confirm_Button.setForeground(Color.BLACK);

        // Layer 1
        add(Settings_Title_Panel, BorderLayout.NORTH);
        add(Settings_Back_Panel, BorderLayout.SOUTH);
        add(Settings_Content_Panel, BorderLayout.CENTER);
        setBackground(Ui.BACKGROUND);

        // Layer 2
        Settings_Title_Panel.add(Settings_Title_Label, BorderLayout.CENTER);
        Settings_Title_Panel.setAlignmentX(CENTER_ALIGNMENT);
        Settings_Title_Panel.setBackground(Ui.BACKGROUND);

        // Layer 2.1
        Settings_Title_Label.setHorizontalAlignment(SwingConstants.CENTER);
        Settings_Title_Label.setForeground(Ui.FOREGROUND);

        // Layer 3
        Settings_Back_Panel.add(Settings_Back_Button, BorderLayout.CENTER);
        Settings_Back_Panel.setBackground(Ui.BACKGROUND);

        // Layer 3.1
        Settings_Back_Button.setBackground(Ui.PRIMARY);
        Settings_Back_Button.setForeground(Ui.FOREGROUND);
        Settings_Back_Button.addActionListener(e -> ui.back());

        // Layer 4
        gbc.gridx = 0;
        gbc.gridy = 0;
        Settings_Content_Panel.add(Settings_Part_1_Panel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        Settings_Content_Panel.add(Settings_Part_2_Panel, gbc);

        // Layer 4.1
        Settings_Part_1_Panel.setBackground(Ui.BACKGROUND);
        Settings_Part_1_Panel.add(Settings_Part_1_Ip_Label, BorderLayout.CENTER);

        // Layer 4.2.1
        Settings_Part_1_Ip_Label.setForeground(Ui.FOREGROUND);
        SwingUtilities.invokeLater(() -> {
            String ipAddress = Ui.getIPAddress();
            Settings_Part_1_Ip_Label.setText("Your IP Address: " + ipAddress);
        });

        // Layer 5.1
        Settings_Part_2_Panel.setBackground(Ui.BACKGROUND);

        gbc.gridx = 0;
        gbc.gridy = 0;
        Settings_Part_2_Panel.add(Settings_Part_2_Label, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        Settings_Part_2_Panel.add(Settings_Part_2_TextField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        Settings_Part_2_Panel.add(Settings_Confirm_Button, gbc);

        // Layer 5.2
        Settings_Part_2_Label.setForeground(Ui.FOREGROUND);
        Settings_Part_2_Label.setText("Current Port: " + Ui.Port);

        // Layer 5.4
        Settings_Confirm_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newPort = Settings_Part_2_TextField.getText();
                if (newPort.matches("\\d+")) {
                    Ui.Port = Integer.parseInt(newPort);
                    Settings_Part_2_Label.setText("Current Port: " + Ui.Port);
                    JOptionPane.showMessageDialog(SettingsUi.this, "Port updated successfully.");
                } else {
                    JOptionPane.showMessageDialog(SettingsUi.this, "Invalid port number. Please enter a valid number.");
                }
            }
        });
    }
}
