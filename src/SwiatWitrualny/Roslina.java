package SwiatWitrualny;

import java.awt.*;

public abstract class Roslina extends Organizm {
    public Roslina(Swiat swiat, Gatunki g, Point p) {
        super(swiat, g, p);
        szansarozmn = 0.3;
    }

    @Override
    public boolean akcja() {
        if(wiek >1) {
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
        return true;
    }

}
