package SwiatWitrualny;

import SwiatWitrualny.Zwierzeta.Wilk;

import java.awt.*;

public class FabrykaOrganizmow {
    public static Organizm StworzOrganizm(Gatunki o, Dimension pos, Swiat sw) {
        switch (o) {
            case WILK:
                return new Wilk(sw,new Point(3,3));

                default:
                    return new Wilk(sw,new Point(3,3));
        }
    }
}
