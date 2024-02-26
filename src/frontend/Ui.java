//Packages
package src.frontend;

//Imports
import javax.swing.*;
import java.awt.*;

//Ui Code
public class Ui extends JFrame{
    //Components erstellung
    JPanel Main_Background_Panel = new JPanel();

    //Title Panel
    JPanel Main_Title_Panel = new JPanel();
    JLabel Main_Title_Label = new JLabel();

    //Friends Panel
    JPanel Main_Friends_Panel = new JPanel();
    JLabel Main_Friends_Label = new JLabel();
    JButton Main_Friends_Add_Button = new JButton();

    //Files Panel
    JPanel Main_Files_Panel = new JPanel();
    JLabel Main_Files_Label = new JLabel();
    JButton Main_Files_Clear_Button = new JButton();
    JButton Main_Files_Add_Button = new JButton();
    JTable Main_Files_SelectedFiles_Table = new JTable();

    //Main Buttons
    JButton Main_Setting_Button = new JButton();
    JButton Main_SendFile_Button = new JButton();


    public Ui(){

        //Frame Settings
        setSize(1280, 960);
        setTitle("Swift Share");

        //Logo Icon Erstellen TODO:Ein logo icon für das Window muss noch eingebaut werden
        ImageIcon Logo = new ImageIcon("src/assets/img/Logo.png");


        //Background
        add(Main_Background_Panel);
        Main_Background_Panel.setLayout(new GridBagLayout());
        Main_Background_Panel.setBackground(new Color(51, 51, 153));
        Main_Background_Panel.setOpaque(true);

        //Title Panel TODO: Title Panel muss erstellt werden
        Main_Background_Panel.add(Main_Title_Panel);
        Main_Background_Panel.setLayout(new GridLayout(1,2));
        //Logo  TODO: Logo muss noch eingefügt werden

        //Title TODO: Title muss eingefügt werden mit richtige font
        Main_Title_Panel.add(Main_Title_Label);
        Main_Title_Label.setText("SwiftShare");

        //Logo zum Panel zuweisung
        Main_Title_Panel.add(Logo);

        setVisible(true);
    }
}
