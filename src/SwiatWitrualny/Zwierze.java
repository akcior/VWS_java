package SwiatWitrualny;

import java.awt.*;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public abstract class Zwierze extends Organizm {

    protected double szansaUcieczki;

    public Zwierze(Swiat swiat, Gatunki g, Point poz) {

        super(swiat, g, poz);
        szansaUcieczki = 0;
    }

    public Zwierze(Swiat s, Gatunki g, Scanner in) {

        super(s,g,in);
        szansaUcieczki =in.nextDouble();
        in.nextLine();
    }

    protected boolean zrobRuch() {
        Point kier = znajdzKierunekDoRuchu();
        if (kier.equals(new Point(0, 0)))
            return false;

        ArrayList<Organizm> o;
        try {
            kier.translate(pozycja.x, pozycja.y);
            o = swiat.getOrganizmNaPozycji(kier);

        }catch(IndexOutOfBoundsException inex)
        {
            return false;
        }
        if (o.size() == 0) {
            pozycja.move(kier.x, kier.y);
            return true;
        } else {
            if (kolizja(o.get(0))) {
                pozycja.move(kier.x, kier.y);
                return true;
            }
            return false;
        }


    }

    protected Point znajdzKierunekDoRuchu() {
        boolean sukces = false;
        Point kier = new Point(0, 0);
        ArrayList<Organizm> o = new ArrayList<Organizm>();
        Point npoz;
        while (!sukces) {
            npoz = pozycja.getLocation();
            kier = swiat.getLosowyKierunek();
            npoz.translate(kier.x, kier.y);
            try {
                o = swiat.getOrganizmNaPozycji(npoz);
                sukces = true;
            } catch (IndexOutOfBoundsException e) {
                sukces = false;
            }

        }
        return kier;
    }

    public boolean zrobUnik() {

        if(orgrand.nextDouble() < szansaUcieczki)
        {
            Point p = swiat.getLosowyWolnyKierunkWokol(pozycja);
            if(p.equals( new Point(0,0)))
                return false;
            pozycja.translate(p.x,p.y);
            return true;
        }
        return false;
    }

    @Override
    public boolean akcja() {
        wiek++;
        if (zyje) {
            for (int i = 0; i < zasieg; i++) {
                if (!zrobRuch())
                    break;
            }
        }
        if (!zyje) {
            umrzyj();
            return false;
        }
        return true;
    }

    @Override
    protected boolean kolizja(Organizm org) {

        if (org instanceof Zwierze) {
            Zwierze o = (Zwierze) org;
            if (gatunek.equals(o.getGatunek())) {
                if (wiek > 5 && o.getWiek() > 5) {
                    if (sprobujSieRozmnozyc()) {
                        rozmnozSie();
                    }
                    return false;
                }
            } else {
                if (silniejszyOd(o)) {
                    if (!o.zrobUnik()) {
                        if (!o.zablokujAtak(this)) {
                            o.umrzyj();
                            swiat.narrator.orgUmarlPrzezOrg(o,this);
                            return true;
                        }
                        return false;
                    }
                    return true;
                } else if (!zablokujAtak(o)) {
                    zyje = false;
                    swiat.narrator.orgUmarlPrzezOrg(this,o);
                    return false;
                }

            }
        } else if (org instanceof Roslina) {
            Roslina r = (Roslina) org;
            if (r.kolizja(this)) {
                swiat.narrator.orgUmarlPrzezOrg(r,this);
                return true;
            } else {
                zyje = false;
                swiat.narrator.orgUmarlPrzezOrg(this,r);
                return false;
            }
        }

        return false;
    }


    abstract public String plec();

    @Override
    public void zapisz(PrintWriter out)
    {
        super.zapisz(out);
        out.println(szansaUcieczki);
    }
}
