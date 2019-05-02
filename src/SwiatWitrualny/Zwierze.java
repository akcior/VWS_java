package SwiatWitrualny;

public class Zwierze extends Organizm {

    public Zwierze(Swiat swiat)
    {
        super(swiat);
    }
    @Override
    public boolean akcja() {
        //TODO poruszanie sie i kolizja w razie napotkania na innuy organizm
        return true;
    }

    @Override
    protected boolean kolizja(Organizm o) {return true;}

    @Override
    protected void rozmnozSie() {};

}
