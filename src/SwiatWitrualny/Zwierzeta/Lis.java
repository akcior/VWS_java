package SwiatWitrualny.Zwierzeta;

import SwiatWitrualny.Gatunki;
import SwiatWitrualny.Organizm;
import SwiatWitrualny.Swiat;
import SwiatWitrualny.Zwierze;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Lis extends Zwierze {

    public Lis(Swiat swiat, Point poz)
    {
        super(swiat, Gatunki.LIS,poz);
        sila = 3;
        inicjatywa = 9;
    }

    public Lis(Swiat s, Scanner in)
    {
        super(s,Gatunki.LIS,in);
    }

    @Override
    protected Point znajdzKierunekDoRuchu()
    {

        Point k;
        k = swiat.getLosowyWolnyKierunkWokol(pozycja);

        if(!k.equals(new Point(0,0))) {
            return k;
        }

        ArrayList<Organizm> o = swiat.getWszystkieOrganizmyWokol(pozycja);

        for(Organizm org : o)
        {
            if(silniejszyOd(org))
            {
                k = org.getPozycja();
                k.translate(-pozycja.x,-pozycja.y);
                if(k.x*k.y == 0)
                    return k;
            }
        }
        return new Point(0,0);


    }

    @Override
    public String plec()
    {
        return "";
    }

}
