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

    public Czlowiek(Swiat s, Point p)
    {
        super(s, Gatunki.CZLOWIEK,p);

        nastRuch = new Point(0,0);
        sila =5;
        inicjatywa = 4;

    }
    @Override
    public Point znajdzKierunekDoRuchu(){
        return new Point(nastRuch);
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
