import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class Forraszthatosagi_vizsgalat extends JPanel {
    private JTextField id_mezo;
    private JTextField cikkszam_mezo;
    private JTextField megnevezes_mezo;
    private JTextField projekt_mezo;
    private JTextField gyartas_mezo;
    private JTextField sorszam_mezo;
    private JComboBox<String> terület_box;
    private JTextField kor_mezo;
    private JTextField mennyiseg_mezo;
    private JTextField idopont_mezo;
    private JTextField vizsgalatimod_mezo;
    private JTextField ellenor_mezo;
    private JTextField zarolando_mezo;
    private JTextField kekcimke_mezo;
    private JComboBox<String> eloiras_box;
    private JComboBox<String> eredmeny_box;
    private JTextArea eloiras_mezo;

    /**
     * Create the panel.
     */
    public Forraszthatosagi_vizsgalat() {
        setLayout(null);
        
        setBackground(Foablak.hatter_szine);
        
        JLabel lblNewLabel = new JLabel("Forraszthatósági vizsgálat dokumnetálása");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 13));
        lblNewLabel.setBounds(493, 31, 324, 14);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("ID");
        lblNewLabel_1.setBounds(39, 61, 46, 14);
        add(lblNewLabel_1);
        
        id_mezo = new JTextField();
        id_mezo.setBounds(74, 58, 46, 20);
        add(id_mezo);
        id_mezo.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("Cikkszám");
        lblNewLabel_2.setBounds(39, 107, 68, 14);
        add(lblNewLabel_2);
        
        cikkszam_mezo = new JTextField();
        cikkszam_mezo.setBounds(128, 104, 191, 20);
        add(cikkszam_mezo);
        cikkszam_mezo.setColumns(10);
        
        JLabel lblNewLabel_3 = new JLabel("Megnevezés");
        lblNewLabel_3.setBounds(370, 107, 99, 14);
        add(lblNewLabel_3);
        
        megnevezes_mezo = new JTextField();
        megnevezes_mezo.setBounds(493, 104, 301, 20);
        add(megnevezes_mezo);
        megnevezes_mezo.setColumns(10);
        
        JLabel lblNewLabel_4 = new JLabel("Projekt");
        lblNewLabel_4.setBounds(849, 107, 68, 14);
        add(lblNewLabel_4);
        
        projekt_mezo = new JTextField();
        projekt_mezo.setBounds(938, 104, 86, 20);
        add(projekt_mezo);
        projekt_mezo.setColumns(10);
        
        JLabel lblNewLabel_5 = new JLabel("Gyártási dátum (DC)");
        lblNewLabel_5.setBounds(39, 144, 130, 14);
        add(lblNewLabel_5);
        
        gyartas_mezo = new JTextField();
        gyartas_mezo.setBounds(179, 141, 140, 20);
        add(gyartas_mezo);
        gyartas_mezo.setColumns(10);
        
        JLabel lblNewLabel_6 = new JLabel("Jkv. sorszám");
        lblNewLabel_6.setBounds(644, 144, 75, 14);
        add(lblNewLabel_6);
        
        sorszam_mezo = new JTextField();
        sorszam_mezo.setBounds(748, 142, 46, 20);
        add(sorszam_mezo);
        sorszam_mezo.setColumns(10);
        
        JLabel lblNewLabel_7 = new JLabel("Terület");
        lblNewLabel_7.setBounds(849, 144, 46, 14);
        add(lblNewLabel_7);
        
        String[] terulet = {"-","Gépes","Kézi"};
        terület_box = new JComboBox<String>(terulet);
        terület_box.setBounds(938, 140, 129, 22);
        add(terület_box);
        
        JLabel lblNewLabel_8 = new JLabel("Alkatrész kora");
        lblNewLabel_8.setBounds(370, 144, 86, 14);
        add(lblNewLabel_8);
        
        kor_mezo = new JTextField();
        kor_mezo.setBounds(493, 141, 107, 20);
        add(kor_mezo);
        kor_mezo.setColumns(10);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(39, 183, 1158, 14);
        add(separator);
        
        JLabel lblNewLabel_9 = new JLabel("Van vevői előírás?");
        lblNewLabel_9.setBounds(39, 223, 107, 14);
        add(lblNewLabel_9);
        
        String[] eloiras = {"-","Igen","Nem"};
        eloiras_box = new JComboBox<String>(eloiras);
        eloiras_box.setBounds(179, 219, 107, 22);
        add(eloiras_box);
        
        JLabel lblNewLabel_10 = new JLabel("Vevői előírás részletei");
        lblNewLabel_10.setBounds(370, 223, 140, 14);
        add(lblNewLabel_10);
        
        eloiras_mezo = new JTextArea();
        eloiras_mezo.setBounds(528, 218, 539, 85);
        add(eloiras_mezo);
        
        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(39, 315, 1158, 14);
        add(separator_1);
        
        JLabel lblNewLabel_11 = new JLabel("Vizsgált mennyiség");
        lblNewLabel_11.setBounds(39, 340, 130, 14);
        add(lblNewLabel_11);
        
        mennyiseg_mezo = new JTextField();
        mennyiseg_mezo.setBounds(179, 340, 46, 20);
        add(mennyiseg_mezo);
        mennyiseg_mezo.setColumns(10);
        
        JLabel lblNewLabel_12 = new JLabel("Vizsgálat időpontja");
        lblNewLabel_12.setBounds(269, 340, 130, 14);
        add(lblNewLabel_12);
        
        idopont_mezo = new JTextField();
        idopont_mezo.setBounds(409, 337, 86, 20);
        add(idopont_mezo);
        idopont_mezo.setColumns(10);
        
        JLabel lblNewLabel_13 = new JLabel("Vizsgálat módja");
        lblNewLabel_13.setBounds(535, 340, 116, 14);
        add(lblNewLabel_13);
        
        vizsgalatimod_mezo = new JTextField();
        vizsgalatimod_mezo.setBounds(661, 337, 489, 20);
        add(vizsgalatimod_mezo);
        vizsgalatimod_mezo.setColumns(10);
        
        JLabel lblNewLabel_14 = new JLabel("Vizsgálatot végezte");
        lblNewLabel_14.setBounds(39, 389, 121, 14);
        add(lblNewLabel_14);
        
        ellenor_mezo = new JTextField();
        ellenor_mezo.setBounds(179, 386, 140, 20);
        add(ellenor_mezo);
        ellenor_mezo.setColumns(10);
        
        JLabel lblNewLabel_15 = new JLabel("Vizsgálat eredménye");
        lblNewLabel_15.setBounds(409, 389, 130, 14);
        add(lblNewLabel_15);
        
        String[] eredmeny = {"-","OK","NOK"};
        eredmeny_box = new JComboBox<String>(eredmeny);
        eredmeny_box.setBounds(549, 385, 86, 22);
        add(eredmeny_box);
        
        JLabel lblNewLabel_16 = new JLabel("Zárolandó / SQA értesítés");
        lblNewLabel_16.setBounds(39, 446, 150, 14);
        add(lblNewLabel_16);
        
        zarolando_mezo = new JTextField();
        zarolando_mezo.setBounds(199, 443, 86, 20);
        add(zarolando_mezo);
        zarolando_mezo.setColumns(10);
        
        JLabel lblNewLabel_17 = new JLabel("Kék cimkével kerül gyártásba?");
        lblNewLabel_17.setBounds(409, 446, 191, 14);
        add(lblNewLabel_17);
        
        kekcimke_mezo = new JTextField();
        kekcimke_mezo.setBounds(610, 443, 86, 20);
        add(kekcimke_mezo);
        kekcimke_mezo.setColumns(10);
        
        JButton mentes_gomb = new JButton("Mentés");
        mentes_gomb.setBounds(562, 537, 89, 23);
        add(mentes_gomb);

    }
}
