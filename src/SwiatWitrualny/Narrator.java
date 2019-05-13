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

    public void orgUmarlPrzezOrg(Organizm o1, Organizm o2)
    {
        wydarzenia.add(o1.toString() + " umarl"+o1.plec()+" przez " + o2.toString() );
    }
    public void orgRozmnozylSie(Organizm o1)
    {
        wydarzenia.add(o1.toString() + " rozmnozyl"+o1.plec()+" sie.");
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
