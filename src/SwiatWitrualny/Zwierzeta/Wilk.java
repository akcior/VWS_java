package SwiatWitrualny.Zwierzeta;

import SwiatWitrualny.Gatunki;
import SwiatWitrualny.Swiat;
import SwiatWitrualny.Zwierze;

import java.awt.*;

public class Wilk extends Zwierze {

    public Wilk(Swiat swiat, Point poz) {

        super(swiat, Gatunki.WILK, poz);
        sila = 9;
        inicjatywa = 5;
    }

}
