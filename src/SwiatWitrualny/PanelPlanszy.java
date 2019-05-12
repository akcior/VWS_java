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

public class PanelPlanszy extends JPanel implements ActionListener {

    private HashMap<Gatunki, BufferedImage> obrazki;
    private Dimension rozmiarPlanszy;
    private Dimension rozmiarObrazka;
    private Dimension rozmiarPanelu;
    private Point pozycjaPanelu;
    public final Swiat swiat = new Swiat();

    public PanelPlanszy(int x, int y) {
        super();
        rozmiarPanelu = new Dimension(640, 640);
        pozycjaPanelu = new Point(100,50);
        setPreferredSize(rozmiarPanelu);
        setLocation(pozycjaPanelu);
        obrazki = new HashMap<Gatunki, BufferedImage>();
        rozmiarPlanszy = new Dimension(x, y);
        rozmiarObrazka = new Dimension(rozmiarPanelu.width/rozmiarPlanszy.width, rozmiarPanelu.height/rozmiarPlanszy.height);
        swiat.zmienRozmiar(new Dimension(x,y));
        //swiat = new Swiat(new Dimension(x,y));


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

    public void ustawPanelNarratora(PanelNarratora p)
    {
        swiat.narrator.ustawPoleTekstowe(p.getPoleTekstowe());
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor( new Color(85,210,75));
        g2d.fillRect(0,0,rozmiarPanelu.width,rozmiarPanelu.height);
        g2d.setColor(new Color(85, 255, 75));
        for (int i = 0; i <= rozmiarPlanszy.width; i++) {
            g2d.drawLine(i * (rozmiarObrazka.width), 0, i * (rozmiarObrazka.width), 640);
        }
        for (int i = 0; i <= rozmiarPlanszy.height; i++) {
            g2d.drawLine(0, i * (rozmiarObrazka.height), 640, i * (rozmiarObrazka.height));
        }
        for(Organizm o : swiat.getWszystkieOrganizmy())
        {
            g2d.drawImage(obrazki.get(o.getGatunek()).getSubimage(0, 0, 32, 32).getScaledInstance(rozmiarObrazka.width, rozmiarObrazka.height, Image.SCALE_DEFAULT), o.getPozycja().x*(rozmiarObrazka.width) + 1, o.getPozycja().y*rozmiarObrazka.height, this);
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
