package SwiatWitrualny;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PanelIU extends JPanel {
    private Dimension rozmiarPanelu;
    private ArrayList<JButton> przyciski;

    public PanelIU(Dimension rozmiar, PanelPlanszy plansza)
    {
        super();
        przyciski = new ArrayList<JButton>();
        rozmiarPanelu = rozmiar;
        przyciski.add(new JButton("Nastepna runda"));
        przyciski.add(new JButton("Uzyj supermocy"));
        przyciski.add(new JButton("Zapisz gre"));
        przyciski.add(new JButton("Nowa Gra"));


        for(int i =0;i<przyciski.size();i++)
        {
            przyciski.get(i).addActionListener(plansza);
            //przyciski.get(i).setSize(new Dimension(50,50));
        }
        setPreferredSize(rozmiarPanelu);
        setLayout( new GridLayout(1,4));

        for(JButton b : przyciski)
        {
            add(b);
        }
        //pack();
        //revalidate();
        //repaint();

    }

}
