package SwiatWitrualny.Zwierzeta;

import SwiatWitrualny.Gatunki;
import SwiatWitrualny.Swiat;
import SwiatWitrualny.Zwierze;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Czlowiek extends Zwierze implements KeyListener {

    private Point nastRuch;
    private int moc_czas_trwania;
    private int moc_pozostaly_czas_trwania;
    private int moc_czas_odnowy;
    private int moc_pozostaly_czas_odnowy;


    public Czlowiek(Swiat s, Point p)
    {
        super(s, Gatunki.CZLOWIEK,p);

        nastRuch = new Point(0,0);
        sila = 5;
        inicjatywa = 4;
        moc_czas_trwania = 5;
        moc_czas_odnowy = 5;
        moc_pozostaly_czas_odnowy = 0;
        moc_pozostaly_czas_trwania = 0;

    }

    @Override
    public boolean akcja()
    {
        if(super.akcja()) {
            if (moc_pozostaly_czas_trwania > 0) {
                zabijWszystkichWokol();
                moc_pozostaly_czas_trwania--;
                swiat.narrator.mocy_pozostalo("Całopalenie", moc_pozostaly_czas_trwania);
                if (moc_pozostaly_czas_trwania == 0)
                    moc_pozostaly_czas_odnowy = moc_czas_odnowy;
            }
            else if (moc_pozostaly_czas_odnowy > 0) {
                moc_pozostaly_czas_odnowy--;
                if(moc_pozostaly_czas_odnowy > 0)swiat.narrator.mocOdnowiSieZa("Całopalenie", moc_pozostaly_czas_odnowy);
                else swiat.narrator.mocOdnowiona("Całopalenie");
            }
            return true;
        }
        return false;
    }

    public boolean aktywujMoc()
    {
        if(moc_pozostaly_czas_odnowy > 0 || moc_pozostaly_czas_trwania > 0)
            return false;

        moc_pozostaly_czas_trwania = moc_czas_trwania;
        return true;
    }

    @Override
    public Point znajdzKierunekDoRuchu(){
        Point p = nastRuch.getLocation();
        nastRuch.move(0,0);
        return p;
    }

    public Point getnastRuch()
    {
        return nastRuch.getLocation();
    }

    public int getMoc_pozostaly_czas_trwania() {
        return moc_pozostaly_czas_trwania;
    }
    public int getMoc_pozostaly_czas_odnowy()
    {
        return moc_pozostaly_czas_odnowy;
    }

    @Override
    public String plec()
    {
        return "";
    }

    @Override
    public void keyTyped(KeyEvent k)
    {
        switch(k.getKeyCode())
        {

            case KeyEvent.VK_UP:
                nastRuch.move(0,-1);
                break;
            case KeyEvent.VK_DOWN:
                nastRuch.move(0,1);
                break;
            case KeyEvent.VK_LEFT:
                nastRuch.move(-1,0);
                break;
            case KeyEvent.VK_RIGHT:
                nastRuch.move(1,0);
                break;
        }
        System.out.println(nastRuch.toString());
    }

    @Override
    public void keyPressed(KeyEvent k)
    {
        System.out.println("pressed");
    }

    @Override
    public void keyReleased(KeyEvent k)
    {

    }
}
