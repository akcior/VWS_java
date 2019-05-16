package SwiatWitrualny.Rosliny;

import SwiatWitrualny.Gatunki;
import SwiatWitrualny.Organizm;
import SwiatWitrualny.Roslina;
import SwiatWitrualny.Swiat;

import java.awt.*;
import java.util.Scanner;

public class Wilcze_Jagody extends Roslina {

    public Wilcze_Jagody(Swiat s, Point p) {
        super(s, Gatunki.WILCZE_JAGODY, p);
        sila =99;
    }

    public Wilcze_Jagody(Swiat s, Scanner in) {
        super(s, Gatunki.WILCZE_JAGODY, in);
    }

    @Override
    public boolean kolizja(Organizm o) {
        umrzyj();
        return false;
    }

    @Override
    public String plec() {
        return "y";
    }
}