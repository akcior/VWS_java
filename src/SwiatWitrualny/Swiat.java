package SwiatWitrualny;

import SwiatWitrualny.Rosliny.*;
import SwiatWitrualny.Zwierzeta.*;

import java.awt.*;
import java.io.PrintWriter;
import java.util.*;

public class Swiat {

    private boolean istnieje;
    private Dimension rozmiarSwiata;
    private ArrayList<Organizm> organizmy;
    private Random rand;
    public final Narrator narrator = new Narrator();

    public Swiat() {
        organizmy = new ArrayList<Organizm>();
        rand = new Random();
        rozmiarSwiata = new Dimension(1, 1);//standardowy rozmiar swiata
        //nowaGra(rozmiarSwiata.getSize());
//        stworzOrganizm(Gatunki.WILK,new Point(0,0));
//        stworzOrganizm(Gatunki.WILK,new Point(1,0));
//        stworzOrganizm(Gatunki.OWCA, new Point(2,3));
//        stworzOrganizm(Gatunki.LIS, new Point(5,8));
//        stworzOrganizm(Gatunki.ANTYLOPA, new Point(9,8));
//        stworzOrganizm(Gatunki.ZOLW, new Point(8,2));
//        stworzOrganizm(Gatunki.CZLOWIEK,new Point(5,5));
//        stworzOrganizm(Gatunki.TRAWA, new Point(4,4));
//        stworzOrganizm(Gatunki.MLECZ, new Point(7,7));
//        stworzOrganizm(Gatunki.GUARANA, new Point(8,4));
//        stworzOrganizm(Gatunki.BARSZCZ_SOSNOWSKIEGO, new Point(1,6));
//        stworzOrganizm(Gatunki.WILCZE_JAGODY, new Point(7,6));

    }

    public Swiat(Dimension rozmiar) {
        rozmiarSwiata = rozmiar;
        organizmy = new ArrayList<Organizm>();
        rand = new Random();
        stworzOrganizm(Gatunki.WILK, new Point(0, 0));
        stworzOrganizm(Gatunki.OWCA, new Point(2, 3));
        stworzOrganizm(Gatunki.LIS, new Point(5, 8));
        stworzOrganizm(Gatunki.ANTYLOPA, new Point(1, 8));
        stworzOrganizm(Gatunki.ZOLW, new Point(8, 2));
        stworzOrganizm(Gatunki.CZLOWIEK, new Point(5, 5));
    }

    public void nowaGra(Dimension rozmiarGry) {
        organizmy.clear();
        rozmiarSwiata = rozmiarGry;
        for (Gatunki gat : Gatunki.values()) {
            int i = 0;
            if (gat == Gatunki.CZLOWIEK || gat == Gatunki.BARSZCZ_SOSNOWSKIEGO)
                i = 1; // jesli gatunek to czlowiek stworzymy tylko jednego;
            while (i < 2) {
                i++;
                Point p = new Point();
                for (int j = 0; j < 4; j++) {
                    p.x = Math.abs(rand.nextInt() % rozmiarSwiata.width);
                    p.y = Math.abs(rand.nextInt() % rozmiarSwiata.height);

                    if (getOrganizmNaPozycji(p).size() > 0) {
                        p.x = -1;
                        p.y = -1;
                    } else break;
                }
                if (p.x == -1) continue;
                stworzOrganizm(gat, p);
            }
        }
    }

    public Czlowiek getCzlowiek() {
        for (Organizm o : organizmy) {
            if (o instanceof Czlowiek) {
                return (Czlowiek) o;
            }
        }
        return null;
    }

    public void nastepnaRunda() {
        Collections.sort(organizmy, Collections.reverseOrder());
        Organizm o;
        for (int i = 0; i < organizmy.size(); i++) {
            o = organizmy.get(i);
            if (o.getWiek() > 0)
                o.akcja();
            else
                o.addWiek(1);
            //System.out.println(o.getGatunek().toString() + " " + o.getPozycja().toString());
        }
        narrator.opowiadaj();

    }

    public boolean stworzOrganizm(Gatunki o, Point poz) {

        try {
            ArrayList<Organizm> orgs;
            orgs = getOrganizmNaPozycji(poz.getLocation());
            if (orgs.size() > 0) return false;
        } catch (IndexOutOfBoundsException inex) {
            return false;
        }

        switch (o) {
            case WILK:
                organizmy.add(new Wilk(this, poz));
                break;
            case OWCA:
                organizmy.add(new Owca(this, poz));
                break;
            case LIS:
                organizmy.add(new Lis(this, poz));
                break;
            case ZOLW:
                organizmy.add(new Zolw(this, poz));
                break;
            case ANTYLOPA:
                organizmy.add(new Antylopa(this, poz));
                break;
            case CZLOWIEK:
                organizmy.add(new Czlowiek(this, poz));
                break;
            case TRAWA:
                organizmy.add(new Trawa(this, poz));
                break;
            case MLECZ:
                organizmy.add(new Mlecz(this, poz));
                break;
            case GUARANA:
                organizmy.add(new Guarana(this, poz));
                break;
            case BARSZCZ_SOSNOWSKIEGO:
                organizmy.add(new Barszcz_sosnowskiego(this, poz));
                break;
            case WILCZE_JAGODY:
                organizmy.add(new Wilcze_Jagody(this, poz));
                break;
        }
        return true;
    }

    public void usunOrganizm(Organizm org) {
        organizmy.remove(org);
    }

    public ArrayList<Organizm> getWszystkieOrganizmy() {
        return organizmy;
    }

    public ArrayList<Organizm> getWszystkieOrganizmyWokol(Point p) {
        ArrayList<Organizm> ow = new ArrayList<>();
        ArrayList<Organizm> o;
        Point k;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (!(i == 0 && j == 0)) {
                    k = p.getLocation();
                    k.translate(i, j);
                    try {
                        o = getOrganizmNaPozycji(k);
                    } catch (IndexOutOfBoundsException e) {
                        continue;
                    }
                    if (o.size() > 0) ow.add(o.get(0));
                }
            }
        }
        return ow;
    }

    public ArrayList<Organizm> getOrganizmNaPozycji(Point pos) throws IndexOutOfBoundsException {

        if (pos.x < 0 || pos.x >= rozmiarSwiata.width ||
                pos.y < 0 || pos.y >= rozmiarSwiata.height) {
            throw new IndexOutOfBoundsException();
        }

        ArrayList<Organizm> orgs = new ArrayList<Organizm>();
        for (Organizm o : organizmy) {
            if (o.getPozycja().equals(pos))
                orgs.add(o);
        }
        return orgs;
    }

//    public Point getLosowyKierunek() {
//        Point kier = new Point();
//        int r = ((rand.nextInt() % 4) + 4) % 4;
//        switch (r) {
//            case 0:
//                kier.x = -1;
//                kier.y = 0;
//                break;
//            case 1:
//                kier.x = 0;
//                kier.y = 1;
//                break;
//            case 2:
//                kier.x = 1;
//                kier.y = 0;
//                break;
//            case 3:
//                kier.x = 0;
//                kier.y = -1;
//                break;
//        }
//        return kier;
//    }

    public Point getLosowyWolnyKierunekWokol(Point pos) {
        ArrayList<Point> kier = new ArrayList<Point>();
        ArrayList<Organizm> orgs;
        Point p = new Point();

        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {

                if (i * j == 0) { //jesli pkt (i,j) jest w prostej linii od pos
                    p = pos.getLocation();
                    p.translate(i, j);
                    try {
                        orgs = getOrganizmNaPozycji(p);
                    } catch (IndexOutOfBoundsException e) {
                        continue;
                    }
                    if (orgs.size() == 0) {
                        kier.add(new Point(i, j));
                    }
                }
            }
        }

        if (kier.size() > 0) {
            return kier.get(Math.abs(rand.nextInt() % kier.size()));
        } else return new Point(0, 0);

    }

    public Dimension getRozmiarSwiata() {
        return rozmiarSwiata.getSize();
    }

    public boolean Istnieje() {
        return istnieje;
    }

    public void zapisz(PrintWriter out) {
        out.println(rozmiarSwiata.width);
        out.println(rozmiarSwiata.height);
        for (Organizm o : organizmy) {
            o.zapisz(out);
        }
    }

    public void wczytaj(Scanner in) {
        rozmiarSwiata.width = in.nextInt();
        in.nextLine();
        rozmiarSwiata.height = in.nextInt();
        in.nextLine();
        organizmy.clear();

        while (in.hasNextLine()) {
            String gatstring = in.nextLine();
            Gatunki gat = Gatunki.valueOf(gatstring);
            switch (gat) {
                case WILK:
                    organizmy.add(new Wilk(this, in));
                    break;
                case OWCA:
                    organizmy.add(new Owca(this, in));
                    break;
                case LIS:
                    organizmy.add(new Lis(this, in));
                    break;
                case ZOLW:
                    organizmy.add(new Zolw(this, in));
                    break;
                case ANTYLOPA:
                    organizmy.add(new Antylopa(this, in));
                    break;
                case CZLOWIEK:
                    organizmy.add(new Czlowiek(this, in));
                    break;
                case TRAWA:
                    organizmy.add(new Trawa(this, in));
                    break;
                case MLECZ:
                    organizmy.add(new Mlecz(this, in));
                    break;
                case GUARANA:
                    organizmy.add(new Guarana(this, in));
                    break;
                case BARSZCZ_SOSNOWSKIEGO:
                    organizmy.add(new Barszcz_sosnowskiego(this, in));
                    break;
                case WILCZE_JAGODY:
                    organizmy.add(new Wilcze_Jagody(this, in));
                    break;
            }

        }
    }
}
