package src.frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class SettingsUi extends JPanel {
    //Content Panel
    JPanel Settings_Content_Panel = new JPanel(new BorderLayout());
    //Title
    JPanel Settings_Title_Panel = new JPanel(new BorderLayout());
    JLabel Settings_Title_Label = new JLabel("Settings", SwingConstants.CENTER);
    //Back
    JPanel Settings_Back_Panel = new JPanel( new BorderLayout());
    JButton Settings_Back_Button = new JButton("Back");
    //Settings Panel
    JPanel Settings_Parts_Panel = new JPanel(new GridLayout(2,0));
    //Settings Panel 1 Users Ip Display
    JPanel Settings_Part_1_Panel = new JPanel();
    JLabel Settings_Part_1_Ip_Label = new JLabel(" ",SwingConstants.CENTER);
    //Settings Panel 2 Port Selection
    JPanel Settings_Part_2_Panel = new JPanel();


    //Settings Panel extra
    private Ui uiInstance;

    public SettingsUi(Ui uiInstance){
        this.uiInstance = uiInstance;

        //Font
        try {
            // Load the custom font from a file
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("src/assets/font/ttf/Involve-Regular.ttf"));
            font = font.deriveFont(24f); // Set font size to 24 pixels
            Settings_Title_Label.setFont(font);
        } catch (FontFormatException e) {
            System.err.println("UI:Invalid font format.");
        } catch (IOException e) {
            System.err.println("UI:Could not read the font file.");
        }

        Settings_Back_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uiInstance.back();
            }
        });

        //Layer 1
        setLayout(new BorderLayout());
        add(Settings_Content_Panel, BorderLayout.CENTER);
        Settings_Content_Panel.add(Settings_Title_Panel, BorderLayout.NORTH);
        add(Settings_Back_Panel, BorderLayout.SOUTH);
        Settings_Content_Panel.setBackground(Ui.BACKGROUND);

        //Layer 2
        Settings_Title_Panel.add(Settings_Title_Label, BorderLayout.CENTER);
        Settings_Title_Panel.setAlignmentX(CENTER_ALIGNMENT);
        Settings_Title_Panel.setBackground(Ui.BACKGROUND);

        //Layer 2.1
        Settings_Title_Label.setHorizontalAlignment(SwingConstants.CENTER);
        Settings_Title_Label.setForeground(Ui.FOREGROUND);

        //Layer 3
        Settings_Back_Panel.add(Settings_Back_Button, BorderLayout.CENTER);
        Settings_Back_Panel.setBackground(Ui.BACKGROUND);

        //Layer 3.1
        Settings_Back_Button.setBackground(Ui.PRIMARY);
        Settings_Back_Button.setForeground(Ui.FOREGROUND);

        //Layer 4
        Settings_Content_Panel.add(Settings_Parts_Panel);

        //Layer 4.1
        Settings_Parts_Panel.add(Settings_Part_1_Panel);

        //Layer 4.2.1
        Settings_Part_1_Panel.setBackground(Ui.BACKGROUND);
        Settings_Part_1_Panel.add(Settings_Part_1_Ip_Label);

        //Layer 4.2.2
        Settings_Part_1_Ip_Label.setForeground(Ui.FOREGROUND);
        SwingUtilities.invokeLater(() -> {
            String ipAddress = Ui.getIPAddress();
            Settings_Part_1_Ip_Label.setText("Your IP Address: " + ipAddress);
        });


    }
}
