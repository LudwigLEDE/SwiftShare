package src.frontend;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsUi extends JPanel {
    JLabel Settings_Title = new JLabel("Settings");
    JButton Settings_Back_button = new JButton("Back");

    public SettingsUi(){
        Settings_Back_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main_Background_Panel.remove(SettingsUi);

            }
        });


        add(Settings_Title);
        add(Settings_Back_button);
    }

}
