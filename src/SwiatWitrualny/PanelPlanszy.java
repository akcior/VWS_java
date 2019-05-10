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

    private HashMap<Gatunki, BufferedImage> obrazki;
    private Dimension rozmiarPlanszy;
    private Dimension rozmiarObrazka;
    private Dimension rozmiarPanelu;
    private Point pozycjaPanelu;
    private Swiat swiat;

    public PanelPlanszy(int x, int y) {
        super();
        rozmiarPanelu = new Dimension(640, 640);
        pozycjaPanelu = new Point(100,50);
        setPreferredSize(rozmiarPanelu);
        setLocation(pozycjaPanelu);
        obrazki = new HashMap<Gatunki, BufferedImage>();
        rozmiarPlanszy = new Dimension(x, y);
        rozmiarObrazka = new Dimension(32, 32);
        swiat = new Swiat();
        //TODO zmiana rozmiaru obrazka w zaleznosci od rozmiaru planszy

        File plik;

        for (Gatunki o : Gatunki.values()) {
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

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor( new Color(85,210,75));
        g2d.fillRect(0,0,rozmiarPanelu.width,rozmiarPanelu.height);
        g2d.setColor(new Color(0, 0, 255));
        for (int i = 0; i <= rozmiarPlanszy.width; i++) {
            g2d.drawLine(i * (rozmiarObrazka.width), 0, i * (rozmiarObrazka.width), 640);
        }
        for (int i = 0; i <= rozmiarPlanszy.height; i++) {
            g2d.drawLine(0, i * (rozmiarObrazka.height), 640, i * (rozmiarObrazka.height));
        }
        for(Organizm o : swiat.getWszystkieOrganizmy())
        {
            g2d.drawImage(obrazki.get(o.getGatunek()).getSubimage(0, 0, rozmiarObrazka.width, rozmiarObrazka.height), o.getPozycja().x*(rozmiarObrazka.width) + 1, o.getPozycja().y*rozmiarObrazka.height, this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "Nastepna runda") {
            swiat.nastepnaRunda();
            repaint();
        }
    }

}
