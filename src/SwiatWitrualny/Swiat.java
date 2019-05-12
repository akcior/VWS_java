package SwiatWitrualny;

import SwiatWitrualny.Zwierzeta.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Swiat implements ActionListener {

    private boolean istnieje;
    private Dimension rozmiarSwiata;
    private ArrayList<Organizm> organizmy;
    private Random rand;
    public Narrator narrator;

    public Swiat()
    {
        organizmy = new ArrayList<Organizm>();
        rand = new Random();
        narrator = new Narrator();
        stworzOrganizm(Gatunki.WILK,new Point(0,0));
        stworzOrganizm(Gatunki.OWCA, new Point(2,3));
        stworzOrganizm(Gatunki.LIS, new Point(5,8));
        stworzOrganizm(Gatunki.ANTYLOPA, new Point(1,8));
        stworzOrganizm(Gatunki.ZOLW, new Point(8,2));
        stworzOrganizm(Gatunki.CZLOWIEK,new Point(5,5));
    }
    public Swiat(Dimension rozmiar) {
        rozmiarSwiata = rozmiar;
        organizmy = new ArrayList<Organizm>();
        rand = new Random();
        narrator = new Narrator();
        stworzOrganizm(Gatunki.WILK,new Point(0,0));
        stworzOrganizm(Gatunki.OWCA, new Point(2,3));
        stworzOrganizm(Gatunki.LIS, new Point(5,8));
        stworzOrganizm(Gatunki.ANTYLOPA, new Point(1,8));
        stworzOrganizm(Gatunki.ZOLW, new Point(8,2));
        stworzOrganizm(Gatunki.CZLOWIEK,new Point(5,5));
    }

    public void zmienRozmiar(Dimension r)
    {
        rozmiarSwiata = r;
    }

    public Czlowiek getCzlowiek()
    {
        for(Organizm o : organizmy)
        {
            if(o instanceof Czlowiek)
            {
                return (Czlowiek)o;
            }
        }
        return null;
    }

    public void nastepnaRunda() {
        Collections.sort(organizmy,Collections.reverseOrder());
        Organizm o;
        for(int i =0;i< organizmy.size();i++)
        {
            o = organizmy.get(i);
            o.akcja();
            System.out.println(o.getGatunek().toString() + " " + o.getPozycja().toString());
        }
        narrator.opowiadaj();

    }

    public boolean stworzOrganizm(Gatunki o, Point poz) {
        switch(o)
        {
            case WILK:
                organizmy.add(new Wilk(this, poz));
                break;
            case OWCA:
                organizmy.add(new Owca(this, poz));
                break;
            case LIS:
                organizmy.add(new Lis(this,poz));
                break;
            case ZOLW:
                organizmy.add(new Zolw(this,poz));
                break;
            case ANTYLOPA:
                organizmy.add(new Antylopa(this,poz));
                break;
            case CZLOWIEK:
                organizmy.add(new Czlowiek(this,poz));
                break;
        }
        return true;
    }

    public void usunOrganizm(Organizm org) {
        organizmy.remove(org);
    }

    public ArrayList<Organizm> getWszystkieOrganizmy()
    {
        return organizmy;
    }

    public ArrayList<Organizm> getWszystkieOrganizmyWokol(Point p)
    {
        ArrayList<Organizm> ow = new ArrayList<>();
        ArrayList<Organizm> o;
        Point k;
        for(int i =-1;i<2;i++)
        {
            for(int j =-1;j<2;j++)
            {
                if(!(i == 0 && j == 0)) {
                    k = p.getLocation();
                    k.translate(i, j);
                    o = getOrganizmNaPozycji(k);
                    if (o.size() > 0) ow.add(o.get(0));
                }
            }
        }
        return ow;
    }

    public ArrayList<Organizm> getOrganizmNaPozycji(Point pos) throws IndexOutOfBoundsException {

        if(pos.x<0 || pos.x >=rozmiarSwiata.width ||
         pos.y <0 || pos.y >=rozmiarSwiata.height)
        {
            throw new IndexOutOfBoundsException();
        }

        ArrayList<Organizm> orgs = new ArrayList<Organizm>();
        for (Organizm o : organizmy) {
            if (o.getPozycja().equals(pos))
                orgs.add(o);
        }
        return orgs;
    }

    public Point getLosowyKierunek() {
        Point kier = new Point();
        int r = ((rand.nextInt() % 4) + 4) % 4;
        switch (r) {
            case 0:
                kier.x = -1;
                kier.y = 0;
                break;
            case 1:
                kier.x = 0;
                kier.y = 1;
                break;
            case 2:
                kier.x = 1;
                kier.y = 0;
                break;
            case 3:
                kier.x = 0;
                kier.y = -1;
                break;
        }
        return kier;
    }

    public Point getLosowyWolnyKierunkWokol(Point pos) {
        ArrayList<Point> kier = new ArrayList<Point>();
        ArrayList<Organizm> orgs;
        Point p = new Point();

        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {

                if(i*j == 0) { //jesli pkt (i,j) jest w prostej linii od pos
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
        }
        else return new Point(0,0);

    }

    public boolean Istnieje() {
        return istnieje;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

    }
}
