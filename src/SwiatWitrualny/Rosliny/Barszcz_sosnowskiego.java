package SwiatWitrualny.Rosliny;

import SwiatWitrualny.Gatunki;
import SwiatWitrualny.Organizm;
import SwiatWitrualny.Roslina;
import SwiatWitrualny.Swiat;

import java.awt.*;
import java.util.Scanner;

public class Barszcz_sosnowskiego extends Roslina {

    public Barszcz_sosnowskiego(Swiat s, Point p)
    {
        super(s, Gatunki.BARSZCZ_SOSNOWSKIEGO, p);
        szansarozmn = 0.1;

    }

    public Barszcz_sosnowskiego(Swiat s, Scanner in)
    {
        super(s,Gatunki.BARSZCZ_SOSNOWSKIEGO,in);
    }

    @Override
    public boolean akcja()
    {
        super.akcja();
        zabijWszystkichWokol();
        return true;
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
        return "";
    }


}
