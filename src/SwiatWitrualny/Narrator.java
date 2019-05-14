package SwiatWitrualny;

import javax.swing.*;
import java.util.ArrayList;

public class Narrator {
    private JTextArea poleTekstowe;
    private ArrayList<String> wydarzenia;

    public Narrator() {
        wydarzenia = new ArrayList<>();
    }

    public void ustawPoleTekstowe(JTextArea t)
    {
        poleTekstowe = t;
    }

    public void orgUmarlPrzezOrg(Organizm o1, Organizm o2)
    {
        wydarzenia.add(o1.toString() + " umarl"+o1.plec()+" przez " + o2.toString() );
    }
    public void orgRozmnozylSie(Organizm o1)
    {
        wydarzenia.add(o1.toString() + " rozmnozyl"+o1.plec()+" sie.");
    }

    public void orgUzylMocy(Organizm o, String nazwa)
    {
        wydarzenia.add(o.toString() + " uzyl mocy: "+ nazwa);
    }
    public void mocy_pozostalo(String nazwa, int pozostalo)
    {
        wydarzenia.add("Mocy " + nazwa + " pozostało: "+ pozostalo+ " rund.");
    }

    public void mocOdnowiSieZa(String nazwa, int odnowienie)
    {
        wydarzenia.add("Moc " + nazwa + " odnowi się za: " + odnowienie+ " rund.");
    }
    public void mocOdnowiona(String nazwa)
    {
        wydarzenia.add("Moc " + nazwa + " zostala odnowiona.");
    }

    public void wczytanoGre(String nazwa)
    {
        wydarzenia.add("Wczytano gre o nazwie: "+ nazwa);
    }

    public void opowiadaj()
    {
        poleTekstowe.setText("");

        for(String wydarzenie : wydarzenia)
        {
            poleTekstowe.append(wydarzenie);
            poleTekstowe.append("\n");
        }
//
        wydarzenia.clear();
    }
}
