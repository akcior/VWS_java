package SwiatWitrualny;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class PanelNarratora extends JScrollPane {

    private JTextArea poleTekstowe;

    public PanelNarratora() {
        super();
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        poleTekstowe = new JTextArea();
        poleTekstowe.setBounds(20, 20, 600, 100);
        poleTekstowe.setBorder(new LineBorder(OknoSym.tlo));
        poleTekstowe.setBackground(OknoSym.tlo);
        poleTekstowe.setHighlighter(null);
        poleTekstowe.setEditable(false);
        poleTekstowe.setText("Witaj w wirtualnym swiecie!!");
        poleTekstowe.setFont(new Font("Arial", Font.ITALIC, 18));
        getViewport().setBackground(OknoSym.tlo);
        setBorder(new LineBorder(OknoSym.tlo));
        getViewport().add(poleTekstowe);
        setPreferredSize(new Dimension(500, 100));
    }

    public JTextArea getPoleTekstowe() {
        return poleTekstowe;
    }
}
