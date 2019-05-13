package SwiatWitrualny.Rosliny;

import SwiatWitrualny.Gatunki;
import SwiatWitrualny.Roslina;
import SwiatWitrualny.Swiat;

import java.awt.*;

public class Trawa extends Roslina {

    public Trawa(Swiat s, Point p)
    {
        super(s, Gatunki.TRAWA,p);

    }

    @Override
    public String plec()
    {
        return "a";
    }

}
