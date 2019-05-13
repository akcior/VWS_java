package SwiatWitrualny.Rosliny;

import SwiatWitrualny.Gatunki;
import SwiatWitrualny.Organizm;
import SwiatWitrualny.Roslina;
import SwiatWitrualny.Swiat;

import java.awt.*;
import java.util.ArrayList;

public class Barszcz_sosnowskiego extends Roslina {

    public Barszcz_sosnowskiego(Swiat s, Point p)
    {
        super(s, Gatunki.BARSZCZ_SOSNOWSKIEGO, p);

    }

    @Override
    public boolean akcja()
    {
        super.akcja();
        ArrayList<Organizm> orgs = swiat.getWszystkieOrganizmyWokol(pozycja);
        for(Organizm o : orgs)
        {
            if(o instanceof Barszcz_sosnowskiego)
                continue;

            o.umrzyj();
            swiat.narrator.orgUmarlPrzezOrg(o,this);
        }
        return true;
    }

    @Override
    public String plec()
    {
        return "";
    }


}
