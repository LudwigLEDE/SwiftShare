package src.frontend;

import src.assets.ResourceManager;
import src.backend.DatenBank;
import src.backend.Options;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * The SettingsUi class is responsible for creating and managing the settings user interface.
 * It extends JPanel and includes various UI components such as buttons, labels, and text fields
 * to display and edit settings like IP address and port number.
 */
public class SettingsUi extends JPanel {
    private Ui ui;

    /**
     * Constructs a new SettingsUi with the specified Ui object.
     *
     * @param ui the Ui object that contains shared resources and methods
     */
    public SettingsUi(Ui ui) {
        this.ui = ui;
        initComponents();
    }

    /**
     * Initializes and sets up the components of the settings UI.
     */
    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(Ui.BACKGROUND);
        DatenBank.optionsLaden();

        // Panel for the title
        JPanel Settings_Title_Panel = new JPanel(new BorderLayout());
        JLabel Settings_Title_Label = new JLabel("Settings", SwingConstants.CENTER);

        // Font for Title
        Settings_Title_Label.setFont(ResourceManager.loadCustomFont("/src/assets/font/Involve-Regular.ttf", 30));


        // Panel for the back button and the Back Button
        JPanel Settings_Back_Panel = new JPanel(new BorderLayout());
        JButton Settings_Back_Button = new JButton("Back");
        Settings_Back_Panel.setBackground(Ui.BACKGROUND);

        // Panel for the content
        JPanel Settings_Content_Panel = new JPanel(new GridBagLayout());
        Settings_Content_Panel.setBackground(Ui.BACKGROUND);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Part 1: Display IP address
        JPanel Settings_Ip_Panel = new JPanel(new BorderLayout());
        Settings_Ip_Panel.setBackground(Ui.BACKGROUND);
        JLabel Settings_Ip_Label = new JLabel();
        Settings_Ip_Label.setForeground(Ui.FOREGROUND);

        // Part 2: Display and edit Port
        JPanel Settings_Port_Panel = new JPanel(new GridLayout(0,2));
        Settings_Port_Panel.setBackground(Ui.BACKGROUND);
        JLabel Settings_Port_Label = new JLabel();
        Settings_Port_Label.setForeground(Ui.FOREGROUND);
        JTextField Settings_Port_TextField = new JTextField(15);

        // Part 3: Display and edit Username
        JPanel Settings_Username_Panel = new JPanel(new GridLayout());
        Settings_Username_Panel.setBackground(Ui.BACKGROUND);
        JLabel Settings_Username_Label = new JLabel();
        Settings_Username_Label.setForeground(Ui.FOREGROUND);
        JTextField Settings_Username_TextField = new JTextField(15);

        // Part 4: Sound on or off
        JPanel Settings_Sound_Panel = new JPanel(new GridLayout());
        Settings_Sound_Panel.setBackground(Ui.BACKGROUND);
        JLabel Settings_Sound_Lable = new JLabel("Sound On/Off: ");
        Settings_Sound_Lable.setForeground(Ui.FOREGROUND);
        JCheckBox Settings_Sound_CheckBox = new JCheckBox();
        Settings_Sound_CheckBox.setBackground(Ui.BACKGROUND);

        // Part 5: Confirm Button
        JPanel Settings_Confirm_Panel = new JPanel(new BorderLayout());
        JButton Settings_Confirm_Button = new JButton("Confirm");
        Settings_Confirm_Button.setBackground(Ui.SENDFILES);
        Settings_Confirm_Button.setForeground(Color.BLACK);

        // Layer 1 Add of all Master Panels and Background Color
        add(Settings_Title_Panel, BorderLayout.NORTH);
        add(Settings_Content_Panel, BorderLayout.CENTER);
        add(Settings_Back_Panel, BorderLayout.SOUTH);
        setBackground(Ui.BACKGROUND);

        // Layer 2 Title Panel Settings
        Settings_Title_Panel.add(Settings_Title_Label, BorderLayout.CENTER);
        Settings_Title_Panel.setAlignmentX(CENTER_ALIGNMENT);
        Settings_Title_Panel.setBackground(Ui.BACKGROUND);
        Settings_Title_Label.setHorizontalAlignment(SwingConstants.CENTER);
        Settings_Title_Label.setForeground(Ui.FOREGROUND);

        // Layer 3 Add to Panels
        // Layer 3.1 Ip
        Settings_Ip_Panel.add(Settings_Ip_Label, BorderLayout.CENTER);

        // Layer 3.2 Port
        Settings_Port_Panel.add(Settings_Port_Label);
        Settings_Port_Panel.add(Settings_Port_TextField);

        // Layer 3.3 Username
        Settings_Username_Panel.add(Settings_Username_Label);
        Settings_Username_Panel.add(Settings_Username_TextField);

        // Layer 3.4 Sound
        Settings_Sound_Panel.add(Settings_Sound_Lable);
        Settings_Sound_Panel.add(Settings_Sound_CheckBox);

        // Layer 3.5 Confirm
        Settings_Confirm_Panel.add(Settings_Confirm_Button);

        // Layers 4 Settings Components to Content Panel
        // Layer 4.1 Ip
        gbc.gridx = 0;
        gbc.gridy = 0;
        Settings_Content_Panel.add(Settings_Ip_Panel, gbc);

        // Layer 4.2 Port
        gbc.gridx = 0;
        gbc.gridy = 1;
        Settings_Content_Panel.add(Settings_Port_Panel, gbc);

        // Layer 4.3 Username
        gbc.gridx = 0;
        gbc.gridy = 2;
        Settings_Content_Panel.add(Settings_Username_Panel, gbc);

        // Layer 4.4 Sound
        gbc.gridx = 0;
        gbc.gridy = 3;
        Settings_Content_Panel.add(Settings_Sound_Panel, gbc);

        //Layer 4.5 Confirm
        gbc.gridx = 0;
        gbc.gridy = 4;
        Settings_Content_Panel.add(Settings_Confirm_Panel, gbc);

        // Layer  Backbutton Settings
        Settings_Back_Panel.add(Settings_Back_Button, BorderLayout.CENTER);
        Settings_Back_Panel.setBackground(Ui.BACKGROUND);
        Settings_Back_Button.setBackground(Ui.PRIMARY);
        Settings_Back_Button.setForeground(Ui.FOREGROUND);
        Settings_Back_Button.addActionListener(e -> ui.back());


        // Ui Changes to New inputs
        SwingUtilities.invokeLater(() -> {
            String ipAddress = Ui.getIPAddress();
            Settings_Ip_Label.setText("Your IP Address: " + ipAddress);
        });

        // Port load
        Settings_Port_Label.setText("Current Port: " + DatenBank.optionsLaden().port);

        // Username load
        Settings_Username_Label.setText("Current Username: " + DatenBank.optionsLaden().username);

        // Sound load
        Settings_Sound_CheckBox.setSelected(DatenBank.optionsLaden().soundOn);

        // Actionlistener
        Settings_Confirm_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int newPort = Integer.parseInt(Settings_Port_TextField.getText());
                String newUsername = Settings_Username_TextField.getText();
                Boolean newSound = Settings_Sound_CheckBox.isSelected();


                DatenBank.optionsSpeichern(new Options(newPort,newUsername,newSound));

            }
        });
    }
}
