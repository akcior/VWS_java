package SwiatWitrualny;

import java.awt.*;

public abstract class Organizm implements Comparable<Organizm> {
    protected Swiat swiat;
    private Gatunki gatunek;
    protected Point pozycja;
    boolean zyje;
    protected int inicjatywa;
    protected int wiek;
    protected int sila;
    protected int zasieg;
    protected double szansablok;


    public Organizm(Swiat swiat, Gatunki gat, Point poz) {
        this.zyje = true;
        this.swiat = swiat;
        this.gatunek = gat;
        pozycja = poz;
        this.wiek = 0;
        sila = 0;
        zasieg = 1;
        szansablok = 0;
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

    abstract public boolean akcja();

    abstract protected boolean kolizja(Organizm o);

    protected boolean sprobujSieRozmnozyc() { return true; }

    protected boolean zablokujAtak() { return false; }

    protected void rozmnozSie() {
        Point p = swiat.getLosowyWolnyKierunkWokol(pozycja);
        if(!p.equals(new Point(0,0)))
        {
            p.translate(pozycja.x,pozycja.y);
            swiat.stworzOrganizm(gatunek,p);
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
        return gatunek.name() + " na pozycji " + pozycja.toString();
    }


}
