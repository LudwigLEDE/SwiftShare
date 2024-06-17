package src.assets;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ResourceManager {

    public BufferedImage loadImage(String url) {
            BufferedImage image1 = null;

            URL resource = getClass().getResource(  url);
            if (resource != null) {
                ImageIcon imageIcon = new ImageIcon(resource, null);
                image1 = new BufferedImage(imageIcon.getIconWidth(), imageIcon.getIconHeight(), BufferedImage.TYPE_4BYTE_ABGR);

                Graphics g = image1.createGraphics();

                int rule = AlphaComposite.SRC_OVER;
                Composite comp = AlphaComposite.getInstance(rule , 1 );
                ((Graphics2D)g).setComposite(comp );
                ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,
                        RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);


                // paint the Icon to the BufferedImage.
                imageIcon.paintIcon(null, g, 0, 0);
                g.dispose();
            }
            else{
                System.out.println("Fehlendes Bild: " + url);
            }
            return image1;
    }

    public static Font loadCustomFont(String fontFileName, float size) {
        Font customFont = null;
        try {
            // Load the font from the resource directory
            InputStream is = ResourceManager.class.getResourceAsStream(fontFileName);
            if (is != null) {
                customFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(size);
            } else {
                System.err.println("Font file not found!");
            }
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        if (customFont == null) {
            // Fallback to default font if custom font is not loaded
            customFont = new Font("Serif", Font.PLAIN, (int) size);
        }

        return customFont;
    }

    public Clip createSound(String filename)  {

        try {

            InputStream bufferedIn = new BufferedInputStream(getClass().getResourceAsStream("sounds/" + filename));
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            return clip;
        }
        catch (Exception ex){
            System.out.println("Sound " + filename + " konnte nicht geladen werden");
            return null;
        }
    }
}
