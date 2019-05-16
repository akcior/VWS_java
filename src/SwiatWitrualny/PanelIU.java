package SwiatWitrualny;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PanelIU extends JPanel {
    private Dimension rozmiarPanelu;
    private ArrayList<JButton> przyciski;

    public PanelIU(Dimension rozmiar, PanelPlanszy plansza) {
        super();
        przyciski = new ArrayList<JButton>();
        rozmiarPanelu = rozmiar;
        przyciski.add(new JButton("Nastepna runda"));
        przyciski.add(new JButton("Uzyj supermocy"));
        przyciski.add(new JButton("Zapisz gre"));
        przyciski.add(new JButton("Nowa gra"));
        przyciski.add(new JButton("Wczytaj gre"));


        for (int i = 0; i < przyciski.size(); i++) {
            przyciski.get(i).addActionListener(plansza);
        }
        setPreferredSize(rozmiarPanelu);
        setLayout(new GridLayout(1, 5));

        for (JButton b : przyciski) {
            add(b);
        }

    }

}
