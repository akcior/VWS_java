package SwiatWitrualny;

import SwiatWitrualny.Zwierzeta.Czlowiek;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class OknoSym extends JFrame {

    private BufferedImage logoImage;
    public final static Color tlo = new Color(255,250,200);

    public OknoSym() {
        super("Symulator Wirtualnego Swiata Jakub Lecki 175494");
        setPreferredSize(new Dimension(1280,720));
        getContentPane().setBackground(tlo);

        PanelNarratora panelNarratora = new PanelNarratora();

        PanelPlanszy panelPlanszy = new PanelPlanszy(10,10);
        panelPlanszy.ustawPanelNarratora(panelNarratora);

        JPanel panelIU = new PanelIU(new Dimension(300,35), panelPlanszy);

        setLayout(new BorderLayout());
        add(panelPlanszy,BorderLayout.LINE_START);
        add(panelIU,BorderLayout.PAGE_END);
        add(panelNarratora,BorderLayout.LINE_END);

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                Czlowiek cz = panelPlanszy.swiat.getCzlowiek();

                if(cz != null)
                    cz.keyTyped(e);

                return false;
            }
        });



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
