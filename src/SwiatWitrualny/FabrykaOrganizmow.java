package SwiatWitrualny;

import SwiatWitrualny.Zwierzeta.Wilk;

import java.awt.*;

public class FabrykaOrganizmow {
    public static Organizm StworzOrganizm(Organizmy o, Dimension pos, Swiat sw) {
        switch (o) {
            case WILK:
                return new Wilk(sw);

                default:
                    return new Wilk(sw);
        }
    }
}
