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
    JLabel Settings_Title_Label = new JLabel("Settings");
    //Back
    JPanel Settings_Back_Panel = new JPanel( new BorderLayout());
    JButton Settings_Back_Button = new JButton("Back");
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
        add(Settings_Content_Panel);
        Settings_Content_Panel.add(Settings_Title_Panel, BorderLayout.NORTH);
        Settings_Content_Panel.add(Settings_Back_Panel, BorderLayout.SOUTH);

        //Layer 2
        Settings_Title_Panel.add(Settings_Title_Label, BorderLayout.CENTER);

        //Layer 3
        Settings_Back_Panel.add(Settings_Back_Button);
    }
}
