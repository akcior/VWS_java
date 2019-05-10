package SwiatWitrualny;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class OknoSym extends JFrame {

    private BufferedImage logoImage;
    private Swiat swiat;

    public OknoSym() {
        super("Symulator Wirtualnego Swiata Jakub Lecki 175494");
        setPreferredSize(new Dimension(1280,720));

        PanelPlanszy panelPlanszy = new PanelPlanszy(20,20);
        JPanel panelIU = new PanelIU(new Dimension(300,35), panelPlanszy);
        setLayout(new BorderLayout());
        add(panelPlanszy,BorderLayout.LINE_START);
        add(panelIU,BorderLayout.PAGE_END);

        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        File file = new File("assets/human_logo.png");
        try{
            logoImage = ImageIO.read(file);
        }
        catch(IOException e) {
            System.err.println("Blad odczytu obrazu " + file.getName());
        }

        setIconImage(logoImage);
    }
}
