package SwiatWitrualny.Zwierzeta;

import SwiatWitrualny.Gatunki;
import SwiatWitrualny.Organizm;
import SwiatWitrualny.Swiat;
import SwiatWitrualny.Zwierze;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Lis extends Zwierze {

    public Lis(Swiat swiat, Point poz) {
        super(swiat, Gatunki.LIS, poz);
        sila = 3;
        inicjatywa = 9;
    }

    public Lis(Swiat s, Scanner in) {
        super(s, Gatunki.LIS, in);
    }

    @Override
    protected Point znajdzKierunekDoRuchu() {
        Point k;
        /*jesli po 4 probach nie znajdzie odpowiedniej pozycji, nie porusza sie*/
        for (int i = 0; i < 4; i++) {
            k = super.znajdzKierunekDoRuchu();
            ArrayList<Organizm> o = swiat.getOrganizmNaPozycji(pozycja);
            if (o.size() == 0) {
                return k;
            } else {
                if (silniejszyOd(o.get(0)) || o.get(0).getGatunek().equals(Gatunki.LIS)) {
                    return k;
                }
            }
        }
        return new Point(0, 0);
    }

    @Override
    public String plec() {
        return "";
    }

}
