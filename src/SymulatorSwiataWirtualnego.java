import SwiatWitrualny.OknoSym;
import SwiatWitrualny.Swiat;

import javax.swing.*;
import java.awt.*;

public class SymulatorSwiataWirtualnego {

    public static void main(String[] args)
    {
        JFrame okno = new OknoSym();
        EventQueue.invokeLater( new Runnable(){
            @Override
            public void run(){
                okno.setFocusable(true);
                okno.requestFocus();
                okno.requestFocusInWindow();



            }
        });

    }
}
