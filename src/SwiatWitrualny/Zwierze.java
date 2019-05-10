package SwiatWitrualny;

import java.awt.*;
import java.util.ArrayList;

public class Zwierze extends Organizm {

    public Zwierze(Swiat swiat, Gatunki o, Point poz) {
        super(swiat, o,poz);
    }

    boolean zrobRuch() {
        Point kier;
        boolean sukces = false;
        boolean puste = false;
        ArrayList<Organizm> o = new ArrayList<Organizm>();
        Point npoz = pozycja.getLocation();
        while (!sukces) {
            npoz = pozycja.getLocation();
            kier = swiat.getLosowyKierunek();
            npoz.translate(kier.x, kier.y);
            try {
                o=swiat.getOrganizmNaPozycji(npoz);
                sukces = true;
            } catch (IndexOutOfBoundsException e) {
                sukces = false;
            }

        }

        if (o.size() == 0) {
            pozycja.setLocation(npoz);
            return true;
        }
        else
        {
            if(kolizja(o.get(0)))
            {
                pozycja.setLocation(npoz);
                return true;
            }
            return false;
        }


    }

    @Override
    public boolean akcja() {
        wiek++;
        if(zyje && wiek>1)
        {
            for(int i =0;i<zasieg;i++)
            {
                if(!zrobRuch())
                    break;
            }
        }
        if(!zyje)
        {
            umrzyj();
            return false;
        }
        return true;
    }

    @Override
    protected boolean kolizja(Organizm o) {
        return true;
    }

    @Override
    protected void rozmnozSie() {

    }


}
