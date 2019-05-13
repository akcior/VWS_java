package SwiatWitrualny.Rosliny;

import SwiatWitrualny.Gatunki;
import SwiatWitrualny.Organizm;
import SwiatWitrualny.Roslina;
import SwiatWitrualny.Swiat;

import java.awt.*;

public class Wilcze_Jagody extends Roslina {

    public Wilcze_Jagody(Swiat s, Point p)
    {
        super(s, Gatunki.WILCZE_JAGODY, p);
    }

    @Override
    public boolean kolizja(Organizm o)
    {
        umrzyj();
        return false;
    }

    @Override
    public String plec()
    {
        return "y";
    }
}