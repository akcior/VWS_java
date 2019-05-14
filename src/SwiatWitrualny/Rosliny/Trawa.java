package SwiatWitrualny.Rosliny;

import SwiatWitrualny.Gatunki;
import SwiatWitrualny.Roslina;
import SwiatWitrualny.Swiat;

import java.awt.*;
import java.util.Scanner;

public class Trawa extends Roslina {

    public Trawa(Swiat s, Point p)
    {
        super(s, Gatunki.TRAWA,p);

    }

    public Trawa(Swiat s, Scanner in)
    {
        super(s,Gatunki.TRAWA,in);
    }

    @Override
    public String plec()
    {
        return "a";
    }

}
