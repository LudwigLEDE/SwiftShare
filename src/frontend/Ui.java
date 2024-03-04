//Packages
package src.frontend;

//Imports
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.io.File;
import java.io.IOException;

//Ui Code
public class Ui extends JFrame {
    // Components erstellung
    JPanel Main_Background_Panel = new JPanel();

    // Title Panel
    JPanel Main_Title_Panel = new JPanel();
    JLabel Main_Title_Label = new JLabel();
    JLabel Main_Title_Logo_Label = new JLabel();

    // Friends Panel
    JPanel Main_Friends_Panel = new JPanel();
    JLabel Main_Friends_Label = new JLabel();
    JButton Main_Friends_Add_Button = new JButton();
    JTable Main_Friends_Table = new JTable();

    // Files Panel
    JPanel Main_Files_Panel = new JPanel();
    JLabel Main_Files_Label = new JLabel();
    JButton Main_Files_Clear_Button = new JButton();
    JButton Main_Files_Add_Button = new JButton();
    JTable Main_Files_SelectedFiles_Table = new JTable();

    // Main Buttons
    JButton Main_Setting_Button = new JButton();
    JButton Main_SendFile_Button = new JButton();

    public Ui() {

        // Frame Settings
        setSize(1280, 960);
        setTitle("Swift Share");

        /*
         * //Logo Icon Erstellen TODO:Ein logo icon für das Window muss noch eingebaut
         * werden
         * ImageIcon Logo = new ImageIcon("src/assets/img/Logo.png");
         * Main_Title_Logo_Label.setIcon(Logo);
         * 
         * //Custom Font Erstellung
         * try {
         * Font Involve = Font.createFont(Font.TRUETYPE_FONT, new
         * File("src/assets/font/ttf/Involve-Medium.ttf"));
         * Main_Title_Label.setFont(Involve);
         * } catch (FontFormatException e) {
         * throw new RuntimeException(e);
         * } catch (IOException e) {
         * throw new RuntimeException(e);
         * }
         */

        // Background
        add(Main_Background_Panel);
        Main_Background_Panel.setLayout(new GridBagLayout());
        Main_Background_Panel.setBackground(new Color(51, 51, 153));
        Main_Background_Panel.setOpaque(true);

        /*
         * TODO: Muss weider eingeführt werden
         * 
         * //Title Panel TODO: Title Panel muss erstellt werden
         * Main_Background_Panel.add(Main_Title_Panel);
         * Main_Background_Panel.setLayout(new GridLayout(1,2));
         * 
         * //Logo zum Panel zuweisung
         * Main_Title_Panel.add(Main_Title_Logo_Label);
         * 
         * //Title TODO: Title muss eingefügt werden mit richtige font
         * Main_Title_Panel.add(Main_Title_Label);
         * Main_Title_Label.setForeground(Color.BLACK); //TODO: Muss weiß werden wenn
         * background aktiv ist
         * Main_Title_Label.setText("SwiftShare");
         */

        // File Select Panel
        Main_Files_Panel.setLayout(new GridBagLayout());
        Main_Files_Panel.add(Main_Files_Label);
        Main_Files_Panel.add(Main_Files_Add_Button);
        Main_Files_Panel.add(Main_Files_Clear_Button);
        Main_Files_Panel.add(Main_Files_SelectedFiles_Table);
        add(Main_Files_Panel);

        // Friend List
        Main_Friends_Panel.setLayout(new GridBagLayout());
        Main_Friends_Panel.add(Main_Friends_Label);
        Main_Friends_Panel.add(Main_Friends_Add_Button);
        Main_Friends_Panel.add(Main_Friends_Table);
        add(Main_Friends_Panel);

        setVisible(true);
    }
}
