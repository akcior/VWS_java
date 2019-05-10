package SwiatWitrualny.Zwierzeta;

import SwiatWitrualny.Gatunki;
import SwiatWitrualny.Swiat;
import SwiatWitrualny.Zwierze;

import java.awt.*;

public class Owca extends Zwierze {

    public Owca(Swiat s, Point poz) {

        super(s, Gatunki.OWCA, poz);
        sila = 4;
        inicjatywa = 4;
    }
}
