import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JSeparator;

public class Vevoireklamacio_V2 extends JPanel {

    /**
     * Create the panel.
     */
    static JPanel kartyak;
    static Vevoireklamacio_fejlec fejlec;
    static Vevoireklamacio_d0 d0;
    static Vevoireklamacio_d1 d1;
    static Vevoireklamacio_d2 d2;
    static Vevoireklamacio_d3 d3;
    static Vevoireklamacio_d4 d4;
    static Vevoireklamacio_d5 d5;
    static Vevoireklamacio_d6 d6;
    static Vevoireklamacio_d7 d7;
    static Vevoireklamacio_d8 d8;
    static CardLayout cardLayout;
    static JSeparator separator;
    
    
    public Vevoireklamacio_V2() {
        
        this.setPreferredSize(new Dimension(1400, 880));
        Foablak.meretek.setSize(1450, 900);
        setLayout(null);
        
        kartyak = new JPanel();
        cardLayout = new CardLayout();
        kartyak.setLayout(cardLayout);
        d0 = new Vevoireklamacio_d0();
        d1 = new Vevoireklamacio_d1();
        d2 = new Vevoireklamacio_d2();
        d3 = new Vevoireklamacio_d3();
        d4 = new Vevoireklamacio_d4();
        d5 = new Vevoireklamacio_d5();
        d6 = new Vevoireklamacio_d6();
        d7 = new Vevoireklamacio_d7();
        d8 = new Vevoireklamacio_d8();
        
        kartyak.add(d0, "d0");
        kartyak.add(d1, "d1");
        kartyak.add(d2, "d2");
        kartyak.add(d3, "d3");
        kartyak.add(d4, "d4");
        kartyak.add(d5, "d5");
        kartyak.add(d6, "d6");
        kartyak.add(d7, "d7");
        kartyak.add(d8, "d8");
        fejlec = new Vevoireklamacio_fejlec();
        fejlec.setBounds(0, 0, 1500, 150);
        kartyak.setBounds(0, 152, 1500, 750);
        separator = new JSeparator();
        separator.setBounds(0, 150, 1500, 50);
        add(separator);

        add(kartyak);
        add(fejlec);
        
        Utolso_sor sorszam = new Utolso_sor();
        int kovetkezo = Integer.parseInt(sorszam.utolso("qualitydb.Vevoireklamacio_alap"));
        Vevoireklamacio_fejlec.id_mezo.setText(String.valueOf(kovetkezo + 1));
        
    }
}
