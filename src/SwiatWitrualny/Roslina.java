package SwiatWitrualny;

import java.awt.*;

public abstract class Roslina extends Organizm {
    public Roslina(Swiat swiat, Gatunki g, Point p){
        super(swiat,g,p);
    }

    @Override
    public boolean akcja()
    { return true; }

    @Override
    public boolean kolizja(Organizm o)
    {
        return true;
    }

}
