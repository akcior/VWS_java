package SwiatWitrualny;

import SwiatWitrualny.Zwierzeta.Czlowiek;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

public class PanelPlanszy extends JPanel implements ActionListener, MouseListener, Serializable {

    private HashMap<Gatunki, BufferedImage> obrazki;
    private BufferedImage celownik;
    private Dimension rozmiarPlanszy;
    private Dimension rozmiarObrazka;
    private Dimension rozmiarPanelu;
    public final Swiat swiat = new Swiat();

    public PanelPlanszy(int x, int y) {
        super();
        rozmiarPanelu = new Dimension(640, 640);
        setPreferredSize(rozmiarPanelu);
        obrazki = new HashMap<>();
        rozmiarPlanszy = new Dimension(x, y);
        rozmiarObrazka = new Dimension(rozmiarPanelu.width / rozmiarPlanszy.width, rozmiarPanelu.height / rozmiarPlanszy.height);
        swiat.zmienRozmiar(new Dimension(x, y));


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
        plik = new File("assets/celownik.png");
        try {
            celownik = ImageIO.read(plik);
        } catch (IOException e) {
            System.out.println("Błąd odczytu obrazka: " + plik.getName());
        }

    }

    public void ustawPanelNarratora(PanelNarratora p) {
        swiat.narrator.ustawPoleTekstowe(p.getPoleTekstowe());
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(new Color(85, 210, 75));
        g2d.fillRect(0, 0, rozmiarPanelu.width, rozmiarPanelu.height);
        g2d.setColor(new Color(85, 255, 75));
        for (int i = 0; i <= rozmiarPlanszy.width; i++) {
            g2d.drawLine(i * (rozmiarObrazka.width), 0, i * (rozmiarObrazka.width), 640);
        }
        for (int i = 0; i <= rozmiarPlanszy.height; i++) {
            g2d.drawLine(0, i * (rozmiarObrazka.height), 640, i * (rozmiarObrazka.height));
        }
        for (Organizm o : swiat.getWszystkieOrganizmy()) {
            g2d.drawImage(obrazki.get(o.getGatunek()).getSubimage(0, 0, 32, 32).getScaledInstance(rozmiarObrazka.width, rozmiarObrazka.height, Image.SCALE_DEFAULT),
                    o.getPozycja().x * (rozmiarObrazka.width), o.getPozycja().y * rozmiarObrazka.height, this);
            if (o instanceof Czlowiek && !((Czlowiek) o).getnastRuch().equals(new Point(0, 0)))
                g2d.drawImage(celownik.getScaledInstance(rozmiarObrazka.width, rozmiarObrazka.height, Image.SCALE_DEFAULT),
                        (o.getPozycja().x + ((Czlowiek) o).getnastRuch().x) * rozmiarObrazka.width, (o.getPozycja().y + ((Czlowiek) o).getnastRuch().y) * rozmiarObrazka.height, this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "Nastepna runda") {
            swiat.nastepnaRunda();
            repaint();
        }
        else if(e.getActionCommand() == "Uzyj supermocy")
        {
            Czlowiek cz = swiat.getCzlowiek();
            if(cz != null)
            {
                if(cz.aktywujMoc())
                {
                    swiat.narrator.orgUzylMocy(cz,"Całopalenie");
                    swiat.narrator.opowiadaj();
                }
            }
        }
        else if(e.getActionCommand() == "Zapisz gre")
        {
            String nazwa = JOptionPane.showInputDialog("Podaj nazwę zapisu:");
            File zapis = new File("zapisy/"+ nazwa);
            try
            {
                PrintWriter zout = new PrintWriter(zapis);
                swiat.zapisz(zout);
                zout.close();
            }
            catch( IOException iex)
            {
                System.out.println("Blad zapisu gry.");
            }
            //Path sciezka = Paths.get("./zapisy/" + nazwa);
//
//            try {
//                Files.createFile(sciezka);
//            }catch(IOException iex)
//            {
//                iex.printStackTrace();
//            }
        }
        else if(e.getActionCommand() == "Wczytaj gre")
        {
            String nazwa = JOptionPane.showInputDialog("Podaj nazwe zapisu:");
            File odczyt = new File("zapisy/"+nazwa);
            if(odczyt.exists())
            {
                try {
                    Scanner in = new Scanner(odczyt).useLocale(Locale.ENGLISH);
                    swiat.wczytaj(in);
                    swiat.narrator.wczytanoGre(nazwa);
                    swiat.narrator.opowiadaj();
                    rozmiarPlanszy = swiat.getRozmiarSwiata();
                    rozmiarObrazka = new Dimension(rozmiarPanelu.width / rozmiarPlanszy.width, rozmiarPanelu.height / rozmiarPlanszy.height);
                }
                catch(IOException iex)
                {
                    System.out.println("Blad wczytywania gry.");
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent event){

    }

    @Override
    public void mousePressed(MouseEvent event){}

    @Override
    public void mouseReleased(MouseEvent event){}

    @Override
    public void mouseEntered(MouseEvent event){}

    @Override
    public void mouseExited(MouseEvent event){}
}
