package SwiatWitrualny;

import SwiatWitrualny.Zwierzeta.Owca;
import SwiatWitrualny.Zwierzeta.Wilk;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Swiat implements ActionListener {

    private boolean istnieje;
    private Dimension rozmiarSwiata;
    private ArrayList<Organizm> organizmy;
    private Random rand;

    public Swiat(Dimension rozmiar) {
        organizmy = new ArrayList<Organizm>();
        rozmiarSwiata = rozmiar;
        rand = new Random();
    }

    public Swiat() {
        organizmy = new ArrayList<Organizm>();
        rozmiarSwiata = new Dimension(20, 20);
        rand = new Random();
        stworzOrganizm(Gatunki.WILK,new Point(3,3));
        stworzOrganizm(Gatunki.OWCA, new Point(15,10));
    }

    public void nastepnaRunda() {
        Collections.sort(organizmy,Collections.reverseOrder());
        for(Organizm o : organizmy)
        {
            o.akcja();
        }

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
    public ArrayList<Organizm> getOrganizmNaPozycji(Point pos) throws IndexOutOfBoundsException {

        if(pos.x<0 && pos.x >=rozmiarSwiata.width &&
         pos.y <0 && pos.y >=rozmiarSwiata.height)
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
        int r = rand.nextInt() % 4;
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
                p.setLocation(pos);
                p.move(i, j);
                try {
                    orgs = getOrganizmNaPozycji(p);
                } catch (IndexOutOfBoundsException e) {
                    continue;
                }
                if(orgs.size() == 0)
                {
                    kier.add(new Point(i, j));
                }
            }
        }

        if (kier.size() > 0) {
            return kier.get(rand.nextInt() % kier.size());
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
