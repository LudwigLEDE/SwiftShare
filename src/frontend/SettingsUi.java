package src.frontend;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsUi extends JPanel {
    JLabel Settings_Title = new JLabel("Settings");
    JButton Settings_Back_button = new JButton("Back");
    private Ui uiInstance;

    public SettingsUi(Ui uiInstance){
        this.uiInstance = uiInstance;

        Settings_Back_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uiInstance.back();
            }
        });

        add(Settings_Title);
        add(Settings_Back_button);
    }
}
