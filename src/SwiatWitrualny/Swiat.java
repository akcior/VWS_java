package SwiatWitrualny;

import java.awt.*;
import java.util.ArrayList;
import java.util.Set;
import java.util.LinkedHashSet;

public class Swiat {

    private boolean istnieje;
    private Dimension rozmiarSwiata;
    private ArrayList<Organizm> organizmy;

    public Swiat(Dimension rozmiar){
        organizmy = new ArrayList<Organizm>();
        rozmiarSwiata = rozmiar;
    }

    public Swiat()
    {
        rozmiarSwiata = new Dimension(20,20);
    }

    public void NastepnaRunda()
    {
        //TODO sortowanie organizmow po inicjatywie
        //TODO wywolywanie akcji kazdego organizmu

    }

    public boolean Istnieje(){ return istnieje; }

}
