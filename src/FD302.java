import java.awt.CardLayout;

import javax.swing.JPanel;
import javax.swing.JSeparator;

public class FD302 extends JPanel {
    
    static JPanel kartyak;
    static FD302_Fejlec fejlec;
    static FD302_OQC oqc;   
    static FD302_Vizualis visual;
    static FD302_Kalibralas kalib;
    static CardLayout cardLayout;
    static JSeparator separator;

    /**
     * Create the panel.
     */
    public FD302() {
        setLayout(null);
        
        kartyak = new JPanel();
        cardLayout = new CardLayout();
        kartyak.setLayout(cardLayout);
        oqc = new FD302_OQC();
        visual = new FD302_Vizualis();
        kalib = new FD302_Kalibralas();
        
        kartyak.add(oqc, "oqc");
        kartyak.add(visual, "visual");
        kartyak.add(kalib, "kalib");
        
        fejlec = new FD302_Fejlec();
        fejlec.setBounds(0, 0, 1300, 150);
        kartyak.setBounds(0, 152, 1300, 750);
        add(fejlec);
        add(kartyak);
    }

}
