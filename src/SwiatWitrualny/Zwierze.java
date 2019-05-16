package SwiatWitrualny;

import java.awt.*;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Zwierze extends Organizm {

    protected double szansaUcieczki;

    public Zwierze(Swiat swiat, Gatunki g, Point poz) {

        super(swiat, g, poz);
        szansaUcieczki = 0;
    }

    public Zwierze(Swiat s, Gatunki g, Scanner in) {

        super(s, g, in);
        szansaUcieczki = in.nextDouble();
        in.nextLine();
    }

    protected boolean zrobRuch() {
        Point kier = znajdzKierunekDoRuchu();
        if (kier.equals(new Point(0, 0)))
            return false;

        ArrayList<Organizm> o;
        /*sprawdzenie czy na pozycji nastepnej jest jakis organizm*/
        try {
            kier.translate(pozycja.x, pozycja.y);
            o = swiat.getOrganizmNaPozycji(kier);

        } catch (IndexOutOfBoundsException inex) {
            return false;
        }

        /*jesli nastepna pozycja jest wolna zwierze sie tam przemieszcza*/
        if (o.size() == 0) {
            pozycja.move(kier.x, kier.y);
            return true;
        } else { /* jesli nie, zostaje wywolana kolizja*/
            if (kolizja(o.get(0))) {
                pozycja.move(kier.x, kier.y);
                return true;
            }
            return false;
        }


    }

    protected Point znajdzKierunekDoRuchu() {
        ArrayList<Organizm> o = new ArrayList<Organizm>();
        ArrayList<Point> k = new ArrayList<>();

        /*znalezienie takiego kierunku aby zwierze nie znalazlo sie poza plansza*/
        Dimension rozmiarSwiata = swiat.getRozmiarSwiata();
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (pozycja.x + i >= 0 && pozycja.x + i < rozmiarSwiata.width &&
                        pozycja.y + j >= 0 && pozycja.y + j < rozmiarSwiata.height &&
                        i * j == 0 && (i!=0||j!=0))
                    k.add(new Point(i, j));
            }
        }
        if (k.size() > 0) {
            return k.get(Math.abs(orgrand.nextInt() % k.size()));
        }
        return new Point(0, 0);
    }

    public boolean zrobUnik() {

        if (orgrand.nextDouble() < szansaUcieczki) {
            Point p = swiat.getLosowyWolnyKierunekWokol(pozycja);
            /*jesli p == 0,0 oznacza to ze nie znaleziono wolnego kierunki, organizm wiec nie ma gdzie uciec*/
            if (p.equals(new Point(0, 0)))
                return false;
            pozycja.translate(p.x, p.y);
            return true;
        }
        return false;
    }

    @Override
    public boolean akcja() {
        wiek++;
        if (zyje) {
            for (int i = 0; i < zasieg; i++) {
                if (!zrobRuch())
                    break;
            }
        }
        if (!zyje) {
            umrzyj();
            return false;
        }
        return true;
    }

    @Override
    protected boolean kolizja(Organizm org) {

        if (org instanceof Zwierze) {
            Zwierze o = (Zwierze) org;

            /*jesli ten sam gatunek zwierze zostaje na swojej pozycji i rozmnaza sie*/
            if (gatunek.equals(o.getGatunek())) {
                if (wiek > 5 && o.getWiek() > 5) {
                    if (sprobujSieRozmnozyc()) {
                        rozmnozSie();
                    }
                    return false;
                }
            } else {
                if (silniejszyOd(o)) {
                    if (!o.zrobUnik()) {
                        if (!o.zablokujAtak(this)) {
                            o.umrzyj();
                            swiat.narrator.orgUmarlPrzezOrg(o, this);
                            return true;
                        }
                        return false;
                    }
                    return true;
                } else if (!zablokujAtak(o)) {
                    zyje = false;
                    swiat.narrator.orgUmarlPrzezOrg(this, o);
                    return false;
                }

            }
        } else if (org instanceof Roslina) {
            Roslina r = (Roslina) org;
            /*kolizja roslin moze zmieniac parametry zwierzecia ktore go zjadlo*/
            if (r.kolizja(this)) {
                swiat.narrator.orgUmarlPrzezOrg(r, this);
                return true;
            } else {
                zyje = false;
                swiat.narrator.orgUmarlPrzezOrg(this, r);
                return false;
            }
        }

        return false;
    }


    abstract public String plec();

    @Override
    public void zapisz(PrintWriter out) {
        super.zapisz(out);
        out.println(szansaUcieczki);
    }
}
