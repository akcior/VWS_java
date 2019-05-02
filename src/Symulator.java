import SwiatWitrualny.Swiat;

public class Symulator {

    private Swiat swiat;
    private boolean dziala;

    public Symulator(int x, int y)
    {
        swiat = new Swiat();
        dziala = true;
    }

    public void Rysowanie()
    {
    }

    public void NastepnaRunda()
    {
        swiat.NastepnaRunda();
    }

    public void ObslugaWydarzen()
    {

    }

    public boolean Dziala(){ return dziala ; }
}
