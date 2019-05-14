package SwiatWitrualny.Rosliny;

import SwiatWitrualny.Gatunki;
import SwiatWitrualny.Organizm;
import SwiatWitrualny.Roslina;
import SwiatWitrualny.Swiat;

import java.awt.*;
import java.util.Scanner;

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

    public Guarana(Swiat s, Scanner in)
    {
        super(s,Gatunki.GUARANA,in);
    }

    @Override
    public String plec()
    {
        return "a";
    }
}
