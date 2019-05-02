package SwiatWitrualny;

import java.awt.*;

public abstract class Organizm implements Comparable<Organizm> {
    private Swiat swiat;
    private Dimension pozycja;
    private int inicjatywa;
    private int sila;
    private int zasieg;
    private double szansablok;


    public Organizm(Swiat swiat){
        this.swiat = swiat;
    }

    public Dimension GetPozycja() { return pozycja; }
    public int getSila() { return sila; };
    public int getInicjatywa() { return inicjatywa; }


    abstract public boolean akcja();
    abstract protected boolean kolizja(Organizm o);
    abstract protected void rozmnozSie();

    @Override
    public int compareTo(Organizm o){
        int compareini = o.getInicjatywa();
        return compareini - this.inicjatywa; //malejaco
    }


}
