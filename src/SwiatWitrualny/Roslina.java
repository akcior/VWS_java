package SwiatWitrualny;

import java.awt.*;
import java.util.Scanner;

public abstract class Roslina extends Organizm {

    public Roslina(Swiat swiat, Gatunki g, Point p) {
        super(swiat, g, p);
        szansarozmn = 0.3;
    }

    public Roslina(Swiat s, Gatunki g, Scanner in) {
        super(s, g, in);
    }

    @Override
    public boolean akcja() {
        if (wiek > 1) {
            if (sprobujSieRozmnozyc()) {
                rozmnozSie();
            }
        }
        wiek++;
        return true;
    }

    @Override
    public boolean kolizja(Organizm o) {
        umrzyj();
        /*return true oznacza ze organizm o przezyl*/
        return true;
    }

}
