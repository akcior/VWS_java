package SwiatWitrualny.Rosliny;

import SwiatWitrualny.Gatunki;
import SwiatWitrualny.Roslina;
import SwiatWitrualny.Swiat;

import java.awt.*;

public class Mlecz extends Roslina {

    public Mlecz(Swiat s, Point p)
    {
        super(s, Gatunki.MLECZ,p);

    }

    @Override
    public boolean akcja()
    {
        for(int i =0;i<3;i++)
        {
            super.akcja();
            if(i < 2) wiek--;
        }
        return true;
    }

    @Override
    public String plec()
    {
        return "";
    }
}
