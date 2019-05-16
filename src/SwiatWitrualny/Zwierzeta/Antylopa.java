package SwiatWitrualny.Zwierzeta;

import SwiatWitrualny.Gatunki;
import SwiatWitrualny.Swiat;
import SwiatWitrualny.Zwierze;

import java.awt.*;
import java.util.Scanner;

public class Antylopa extends Zwierze {

    public Antylopa(Swiat swiat, Point poz) {
        super(swiat, Gatunki.ANTYLOPA, poz);
        sila = 4;
        inicjatywa = 4;
        zasieg = 2;
        szansaUcieczki = 0.5;
    }

    public Antylopa(Swiat s, Scanner in) {
        super(s, Gatunki.ANTYLOPA, in);
    }

    @Override
    public String plec() {
        return "a";
    }
}
