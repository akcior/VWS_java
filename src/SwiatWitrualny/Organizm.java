package SwiatWitrualny;

import SwiatWitrualny.Rosliny.Barszcz_sosnowskiego;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public abstract class Organizm implements Comparable<Organizm> {
    protected Swiat swiat;
    protected Gatunki gatunek;
    protected Point pozycja;
    boolean zyje;
    protected int inicjatywa;
    protected int wiek;
    protected int sila;
    protected int zasieg;
    protected Random orgrand;
    protected double szansarozmn;


    public Organizm(Swiat swiat, Gatunki gat, Point poz) {
        this.zyje = true;
        this.swiat = swiat;
        this.gatunek = gat;
        this.pozycja = poz;
        this.orgrand = new Random();
        this.wiek = 0;
        this.sila = 0;
        this.zasieg = 1;
        this.szansarozmn = 1;
    }

    public boolean czyZyje()
    {
        return zyje;
    }
    public Point getPozycja() {
        return pozycja;
    }

    public int getSila() {
        return sila;
    }

    ;

    public void addSila(int s) {
        sila += s;
    }


    public int getInicjatywa() {
        return inicjatywa;
    }

    public Gatunki getGatunek() {
        return gatunek;
    }
    public int getWiek() { return wiek; }

    abstract public boolean akcja();

    abstract protected boolean kolizja(Organizm o);

    protected boolean sprobujSieRozmnozyc() { return orgrand.nextDouble() < szansarozmn; }

    protected boolean zablokujAtak(Organizm o) {
       return false;
    }

    protected void rozmnozSie() {
        Point p = swiat.getLosowyWolnyKierunkWokol(pozycja);
        if(!p.equals(new Point(0,0)))
        {
            p.translate(pozycja.x,pozycja.y);
            swiat.stworzOrganizm(gatunek,p);
            swiat.narrator.orgRozmnozylSie(this);
        }
    }

    public boolean silniejszyOd(Organizm o)
    {
        return sila >= o.getSila();
    }

    public void zabijWszystkichWokol()
    {
        ArrayList<Organizm> orgs = swiat.getWszystkieOrganizmyWokol(pozycja);
        for(Organizm o : orgs)
        {
            if(o.getGatunek().equals(gatunek))
                continue;

            o.umrzyj();
            swiat.narrator.orgUmarlPrzezOrg(o,this);
        }
    }

    public void umrzyj() {
        zyje = false;
        swiat.usunOrganizm(this);
    }

    @Override
    public int compareTo(Organizm o) {
        int compareini = this.getInicjatywa();
        return compareini - o.getInicjatywa(); //rosnaco
    }


    @Override
    public String toString() {
        return gatunek.name().charAt(0)+gatunek.name().substring(1).toLowerCase() + " na pozycji X:" + pozycja.x + " Y:"+pozycja.y;
    }

    abstract public String plec();


}
