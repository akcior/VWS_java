package SwiatWitrualny.Zwierzeta;

import SwiatWitrualny.Gatunki;
import SwiatWitrualny.Swiat;
import SwiatWitrualny.Zwierze;

import java.awt.*;

public class Antylopa extends Zwierze {

    public Antylopa(Swiat swiat, Point poz)
    {
        super(swiat, Gatunki.ANTYLOPA, poz);
        sila = 4;
        inicjatywa = 4;
        zasieg = 2;
        szansaUcieczki = 0.5;
    }

    @Override
    public String plec()
    {
        return "a";
    }
}
