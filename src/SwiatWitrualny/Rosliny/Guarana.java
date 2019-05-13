package SwiatWitrualny.Rosliny;

import SwiatWitrualny.Gatunki;
import SwiatWitrualny.Organizm;
import SwiatWitrualny.Roslina;
import SwiatWitrualny.Swiat;

import java.awt.*;

public class Guarana extends Roslina {

    public Guarana(Swiat s, Point p)
    {
        super(s, Gatunki.GUARANA,p);
    }

    @Override
    public boolean kolizja(Organizm o)
    {
        o.addSila(3);
        umrzyj();
        return true;
    }


    @Override
    public String plec()
    {
        return "a";
    }
}
