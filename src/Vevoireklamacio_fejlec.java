import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;

public class Vevoireklamacio_fejlec extends JPanel {
    static JTextField id_mezo;
    static JComboBox<String> fajta_box;
    static JLabel ertesites_cimke;
    static JLabel qr_cimke;
    static JRadioButton d0_gomb;
    static JRadioButton d1_gomb;
    static JRadioButton d3_gomb;
    static JLabel d3_cimke;
    static JRadioButton d4_gomb;
    static JRadioButton d5_gomb;
    static JLabel d5_cimke;
    static JRadioButton d6_gomb;
    static JRadioButton d7_gomb;
    static JRadioButton d8_gomb;
    static JRadioButton d2_gomb;
    static JLabel lezaras_cimke;
    static Color ertesitve = Color.gray;
    static Color qr = Color.gray;
    static Color d3 = Color.gray;
    static Color d5 = Color.gray;
    static Color lezaras = Color.gray;

    /**
     * Create the panel.
     */
    public Vevoireklamacio_fejlec() {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("ID");
        lblNewLabel.setBounds(33, 24, 46, 14);
        add(lblNewLabel);
        
        id_mezo = new JTextField();
        id_mezo.setBounds(72, 21, 46, 20);
        add(id_mezo);
        id_mezo.setColumns(10);
        
        fajta_box = new JComboBox<String>();
        fajta_box.setBounds(72, 64, 199, 22);
        add(fajta_box);
        
        ertesites_cimke = new JLabel("New label");
        ertesites_cimke.setBounds(440, 68, 81, 14);
        add(ertesites_cimke);
        
        qr_cimke = new JLabel("New label");
        qr_cimke.setBounds(613, 68, 69, 14);
        add(qr_cimke);
        
        d0_gomb = new JRadioButton("D0");
        d0_gomb.addActionListener(e -> Vevoireklamacio_V2.cardLayout.show(Vevoireklamacio_V2.kartyak, "d0"));
        d0_gomb.setBounds(247, 117, 46, 23);
        add(d0_gomb);
        
        d1_gomb = new JRadioButton("D1");
        d1_gomb.addActionListener(e -> Vevoireklamacio_V2.cardLayout.show(Vevoireklamacio_V2.kartyak, "d1"));
        d1_gomb.setBounds(353, 117, 46, 23);
        add(d1_gomb);
        
        d2_gomb = new JRadioButton("D2");
        d2_gomb.addActionListener(e -> Vevoireklamacio_V2.cardLayout.show(Vevoireklamacio_V2.kartyak, "d2"));
        d2_gomb.setBounds(474, 117, 51, 23);
        add(d2_gomb);
        
        d3_gomb = new JRadioButton("D3");
        d3_gomb.addActionListener(e -> Vevoireklamacio_V2.cardLayout.show(Vevoireklamacio_V2.kartyak, "d3"));
        d3_gomb.setBounds(594, 117, 46, 23);
        add(d3_gomb);
        
        d3_cimke = new JLabel("New label");
        d3_cimke.setBounds(793, 68, 69, 14);
        add(d3_cimke);
        
        d4_gomb = new JRadioButton("D4");
        d4_gomb.addActionListener(e -> Vevoireklamacio_V2.cardLayout.show(Vevoireklamacio_V2.kartyak, "d4"));
        d4_gomb.setBounds(722, 117, 51, 23);
        add(d4_gomb);
        
        d5_gomb = new JRadioButton("D5");
        d5_gomb.setBounds(836, 117, 51, 23);
        add(d5_gomb);
        
        d5_cimke = new JLabel("New label");
        d5_cimke.setBounds(929, 68, 69, 14);
        add(d5_cimke);
        
        d6_gomb = new JRadioButton("D6");
        d6_gomb.setBounds(929, 117, 51, 23);
        add(d6_gomb);
        
        d7_gomb = new JRadioButton("D7");
        d7_gomb.setBounds(1016, 117, 51, 23);
        add(d7_gomb);
        
        d8_gomb = new JRadioButton("D8");
        d8_gomb.setBounds(1118, 117, 51, 23);
        add(d8_gomb);
        
        
        
        ButtonGroup csoport = new ButtonGroup();
        csoport.add(d0_gomb);
        csoport.add(d1_gomb);
        csoport.add(d2_gomb);
        csoport.add(d3_gomb);
        csoport.add(d4_gomb);
        csoport.add(d5_gomb);
        csoport.add(d6_gomb);
        csoport.add(d7_gomb);
        csoport.add(d8_gomb);
        
        JButton mentes_gomb = new JButton("Mentés");
        mentes_gomb.setBounds(1257, 64, 116, 23);
        add(mentes_gomb);
        
        JButton attekinto_gomb = new JButton("Áttekintő");
        attekinto_gomb.setBounds(1257, 20, 116, 23);
        add(attekinto_gomb);
        
        lezaras_cimke = new JLabel("New label");
        lezaras_cimke.setBounds(1073, 68, 81, 14);
        add(lezaras_cimke);
        
        JLabel lblNewLabel_1 = new JLabel("Vevői értesítés");
        lblNewLabel_1.setBounds(427, 24, 91, 14);
        add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("QR < 24");
        lblNewLabel_2.setBounds(621, 24, 71, 14);
        add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("Lezárás");
        lblNewLabel_3.setBounds(1074, 24, 46, 14);
        add(lblNewLabel_3);
        
        JLabel lblNewLabel_4 = new JLabel("D3 állapot");
        lblNewLabel_4.setBounds(793, 24, 81, 14);
        add(lblNewLabel_4);
        
        JLabel lblNewLabel_5 = new JLabel("D5 állapot");
        lblNewLabel_5.setBounds(929, 24, 87, 14);
        add(lblNewLabel_5);
        
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
       super.paintComponent(g);
       g.setColor(ertesitve);
       g.fillOval(455, 43, 15, 15);
       
       g.setColor(qr);
       g.fillOval(630, 43, 15, 15);
       
       g.setColor(d3);
       g.fillOval(807, 43, 15, 15);
       
       g.setColor(d5);
       g.fillOval(950, 43, 15, 15);
       
       g.setColor(lezaras);
       g.fillOval(1090, 43, 15, 15);
       repaint();
    }
    /*
    public void switchRed() {
        this.ertesitve = Color.GREEN;
        this.qr = Color.BLACK;
        this.d3 = Color.BLACK;
        this.d5 = Color.BLACK;
        this.lezaras = Color.BLACK;
 
        repaint();
    }*/
}
