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

    public PanelPlanszy() {
        super();
        rozmiarPanelu = new Dimension(640, 640);
        setPreferredSize(rozmiarPanelu);
        obrazki = new HashMap<>();
        rozmiarPlanszy = swiat.getRozmiarSwiata();//new Dimension(1,1);
        rozmiarObrazka = new Dimension(rozmiarPanelu.width / rozmiarPlanszy.width, rozmiarPanelu.height / rozmiarPlanszy.height);
        addMouseListener(this);

        /*zaladowanie wszystkich obrazkow organizmow*/
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
        //g2d.fillRect(0, 0, rozmiarObrazka.width*rozmiarPlanszy.width, rozmiarObrazka.height* rozmiarPlanszy.height);

        /*ustawianie tla planszy*/
        g2d.fillRect(0, 0, rozmiarPanelu.width, rozmiarPanelu.height);
        g2d.setColor(new Color(85, 255, 75));

        /*rysowanie lini siatki*/
        for (int i = 0; i <= rozmiarPlanszy.width; i++) {
            g2d.drawLine(i * (rozmiarObrazka.width), 0, i * (rozmiarObrazka.width), rozmiarObrazka.width * rozmiarPlanszy.width);
        }
        for (int i = 0; i <= rozmiarPlanszy.height; i++) {
            g2d.drawLine(0, i * (rozmiarObrazka.height), rozmiarObrazka.height * rozmiarPlanszy.height, i * (rozmiarObrazka.height));
        }

        /*rysowanie obrazkow na odpowiednich miejscach*/
        for (Organizm o : swiat.getWszystkieOrganizmy()) {
            g2d.drawImage(obrazki.get(o.getGatunek()).getSubimage(0, 0, 32, 32).getScaledInstance(rozmiarObrazka.width, rozmiarObrazka.height, Image.SCALE_DEFAULT),
                    o.getPozycja().x * (rozmiarObrazka.width), o.getPozycja().y * rozmiarObrazka.height, this);

            /* jesli czlowiek nie ma nastepnego ruchu nie rysuj celownika*/
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
        } else if (e.getActionCommand() == "Uzyj supermocy") {
            Czlowiek cz = swiat.getCzlowiek();
            if (cz != null) {
                if (cz.aktywujMoc()) {
                    swiat.narrator.orgUzylMocy(cz, "Całopalenie");
                    swiat.narrator.opowiadaj();
                }
            }
        } else if (e.getActionCommand() == "Nowa gra") {
            Dimension roz = new Dimension();
            /*wprowadzenie rozmiaru nowej planszy*/
            try {
                roz.width = Integer.parseInt(JOptionPane.showInputDialog("Podaj szerokosc planszy"));
                roz.height = Integer.parseInt(JOptionPane.showInputDialog("Podaj wysokosc planszy"));
            } catch (NumberFormatException nex) {
                System.out.println("Wprowadzono zle dane.");
                return;
            }
            rozmiarPlanszy = roz.getSize();
            rozmiarObrazka = new Dimension(rozmiarPanelu.width / rozmiarPlanszy.width, rozmiarPanelu.height / rozmiarPlanszy.height);
            swiat.nowaGra(roz);
            repaint();
        } else if (e.getActionCommand() == "Zapisz gre") {
            /*pobranie nazwy zapisu gry*/
            String nazwa = JOptionPane.showInputDialog("Podaj nazwę zapisu:");
            if (nazwa == null) return;
            File zapis = new File("zapisy/" + nazwa);
            try {
                PrintWriter zout = new PrintWriter(zapis);
                swiat.zapisz(zout);
                zout.close();
            } catch (IOException iex) {
                System.out.println("Blad zapisu gry.");
            }
        } else if (e.getActionCommand() == "Wczytaj gre") {
            String nazwa = JOptionPane.showInputDialog("Podaj nazwe zapisu:");
            if (nazwa == null) return;
            File odczyt = new File("zapisy/" + nazwa);
            if (odczyt.exists()) {
                try {
                    /*ustawienie pliku z zapisem jako scanner.
                     * Locale.ENGLISH jest uzywane aby poprawnie odczytac wartosci double*/
                    Scanner in = new Scanner(odczyt).useLocale(Locale.ENGLISH);
                    swiat.wczytaj(in);
                    swiat.narrator.wczytanoGre(nazwa);
                    swiat.narrator.opowiadaj();
                    rozmiarPlanszy = swiat.getRozmiarSwiata();
                    rozmiarObrazka = new Dimension(rozmiarPanelu.width / rozmiarPlanszy.width, rozmiarPanelu.height / rozmiarPlanszy.height);
                    repaint();
                } catch (IOException iex) {
                    System.out.println("Blad wczytywania gry.");
                }
            }
            JOptionPane.showMessageDialog(null,"Zapis nie istnieje!!!");

        }
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        System.out.println("KLIK!!");
        Point p = event.getPoint();

        /*ustalenie na jakim polu znajduje sie kursor*/
        for (int i = 0; i < rozmiarPlanszy.width; i++) {
            if (i * rozmiarObrazka.width < p.x && (i + 1) * rozmiarObrazka.width > p.x) {
                p.x = i;
                break;
            }
        }

        for (int i = 0; i < rozmiarPlanszy.height; i++) {
            if (i * rozmiarObrazka.height < p.y && (i + 1) * rozmiarObrazka.height > p.y) {
                p.y = i;
                break;
            }
        }

        /*jesli kursor znajduje sie poza plansza, nic nie rob*/
        if (p.x >= rozmiarPlanszy.width || p.y >= rozmiarPlanszy.height) {
            return;
        }

        /*wybranie gatunki do stworzenia*/
        Gatunki gat = (Gatunki) JOptionPane.showInputDialog(null, "Wybierz gatunek:", "nowy Organizm",
                JOptionPane.QUESTION_MESSAGE, null, Gatunki.values(), Gatunki.WILK);

        if (gat == null) return;

        /*jesli gatunek jest czlowiekem, sprawdz czy czlowiek nie istnieje juz na planszy*/
        if (gat == Gatunki.CZLOWIEK && swiat.getCzlowiek() != null) {
            JOptionPane.showMessageDialog(null, "nie mozesz dodac kolejnego czlowieka!");
            return;
        }

        if (!swiat.stworzOrganizm(gat, p))
            JOptionPane.showMessageDialog(null, "W tym miejscu jest juz organizm");
    }

    @Override
    public void mousePressed(MouseEvent event) {
    }

    @Override
    public void mouseReleased(MouseEvent event) {
    }

    @Override
    public void mouseEntered(MouseEvent event) {
    }

    @Override
    public void mouseExited(MouseEvent event) {
    }
}
