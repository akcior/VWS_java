package SwiatWitrualny.Zwierzeta;

import SwiatWitrualny.Gatunki;
import SwiatWitrualny.Organizm;
import SwiatWitrualny.Swiat;
import SwiatWitrualny.Zwierze;

import java.awt.*;
import java.util.Scanner;

public class Zolw extends Zwierze {

    public Zolw(Swiat s, Point p) {
        super(s, Gatunki.ZOLW, p);
        sila = 2;
        inicjatywa = 1;
    }

    public Zolw(Swiat s, Scanner in) {
        super(s, Gatunki.ZOLW, in);
    }

    @Override
    public boolean akcja() {
        if (orgrand.nextDouble() < 0.25) return super.akcja();
        else {
            wiek++;
            return true;
        }
    }

    @Override
    public boolean zablokujAtak(Organizm o) {
        if (o.getSila() < 5) return true;
        return false;
    }

    @Override
    public String plec() {
        return "";
    }


}
