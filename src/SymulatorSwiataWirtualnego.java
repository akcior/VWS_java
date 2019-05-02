import SwiatWitrualny.OknoSym;
import SwiatWitrualny.Swiat;

import java.awt.*;

public class SymulatorSwiataWirtualnego {

    public static void main(String[] args)
    {
        EventQueue.invokeLater( new Runnable(){
            @Override
            public void run(){
                new OknoSym();
            }
        });

    }
}
