package SwiatWitrualny;

import javax.swing.*;
import java.io.PrintStream;
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

    public void ogrUmarlPrzezOrg(Organizm o1, Organizm o2)
    {
        wydarzenia.add(o1.toString() + " umarl"+o1.plec()+" przez " + o2.toString() );
    }
    public void orgRozmnozylSieZOrg(Organizm o1, Organizm o2)
    {
        wydarzenia.add(o1.toString() + " rozmnozyl"+o1.plec()+" sie z " + o2.toString());
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
