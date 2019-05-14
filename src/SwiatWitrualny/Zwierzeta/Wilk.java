package SwiatWitrualny.Zwierzeta;

import SwiatWitrualny.Gatunki;
import SwiatWitrualny.Swiat;
import SwiatWitrualny.Zwierze;

import java.awt.*;
import java.util.Scanner;

public class Wilk extends Zwierze {

    public Wilk(Swiat swiat, Point poz) {

        super(swiat, Gatunki.WILK, poz);
        sila = 9;
        inicjatywa = 5;
    }

    public Wilk(Swiat s, Scanner in)
    {
        super(s,Gatunki.WILK,in);
    }

    @Override
    public String plec()
    {
        return "";
    }
}
