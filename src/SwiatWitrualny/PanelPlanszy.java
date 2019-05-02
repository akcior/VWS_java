package SwiatWitrualny;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class PanelPlanszy extends JPanel implements ActionListener {

    private HashMap<Organizmy, BufferedImage> obrazki;
    private Dimension rozmiarPlanszy;
    private Dimension rozmiarObrazka;
    private Swiat swiat;

    public PanelPlanszy(int x, int y) {
        super();
        setPreferredSize(new Dimension(660, 660));
        setLocation(new Point(1, 1));
        obrazki = new HashMap<Organizmy, BufferedImage>();
        rozmiarPlanszy = new Dimension(x, y);
        rozmiarObrazka = new Dimension(32, 32);
        swiat = new Swiat();
        //TODO zmiana rozmiaru obrazka w zaleznosci od rozmiaru planszy

        File plik;

        for (Organizmy o : Organizmy.values()) {
            BufferedImage obraz;
            plik = new File("assets/animated_" + o.name().toLowerCase() + ".png");
            try {
                obraz = ImageIO.read(plik);
            } catch (IOException e) {
                System.out.println("Błąd odczytu obrazka: " + plik.getName());
                continue;
            }
            obrazki.put(o, obraz);
        }

    }

    private void NastepnaRunda() {
        //
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(new Color(0, 0, 255));
        for (int i = 0; i <= rozmiarPlanszy.width; i++) {
            g2d.drawLine(i * (rozmiarObrazka.width + 1), 0, i * (rozmiarObrazka.width + 1), 660);
        }
        for (int i = 0; i <= rozmiarPlanszy.height; i++) {
            g2d.drawLine(0, i * (rozmiarObrazka.height + 1), 660, i * (rozmiarObrazka.height + 1));
        }

        //TODO rysowanie odpowiednich obrazkow w odbowiednich miejscach
        g2d.drawImage(obrazki.get(Organizmy.CZLOWIEK).getSubimage(0, 0, rozmiarObrazka.width, rozmiarObrazka.height), 1, 1, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "Nowa Runda") {
            repaint();
        }
    }

}
