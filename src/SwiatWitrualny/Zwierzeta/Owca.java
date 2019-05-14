package SwiatWitrualny.Zwierzeta;

import SwiatWitrualny.Gatunki;
import SwiatWitrualny.Swiat;
import SwiatWitrualny.Zwierze;

import java.awt.*;
import java.util.Scanner;

public class Owca extends Zwierze {

    public Owca(Swiat s, Point poz) {

        super(s, Gatunki.OWCA, poz);
        sila = 4;
        inicjatywa = 4;
    }

    public Owca(Swiat s, Scanner in)
    {
        super(s,Gatunki.OWCA,in);
    }

    @Override
    public String plec()
    {
        return "a";
    }
}
