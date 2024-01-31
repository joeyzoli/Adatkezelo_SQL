import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.JButton;

public class Zarolasok_bevitel extends JPanel {
    private JTextField id_mezo;
    private JTextField eszleleshelye_mezo;
    private JTextField muszak_mezo;
    private JTextField mernok_mezo;
    private JTextField zaroltdb_mezo;
    private JTextField zarolas_helye;
    private JTextField datum_mezo;
    private JTextField sorszam_mezo;
    private JTextField eredmeny_mezo;
    private JTextField ujradatum_mezo;
    private JTextField ido_mezo;
    private JTextField felelos_mezo;
    private JTextField b2_mezo;
    private JTextField lezaras_mezo;
    private ComboBox combobox_tomb = new ComboBox();
    private JTextArea gyokerintezkedes_mezo;
    private JTextArea zarolasoka_mezo;
    private JTextArea intezkedes_mezo;
    private JTextArea gyokerok_mezo;
    private JTextField visszaellenorzes_mezo;
    private JComboBox<String> projekt_box;
    private JComboBox<String> tipus_box;
    private JComboBox<String> zarolta_box;
    private JCheckBox technikus_csekk;
    private JComboBox<String> felelosterulet_box;
    private SQA_SQL lekerdezes = new SQA_SQL();
    private JTextField meszam_mezo;
    //private static final Object zar_1 = new Object();

    /**
     * Create the panel.
     */
    public Zarolasok_bevitel() {
        setLayout(null);
        
        this.setPreferredSize(new Dimension(1237, 800));
        Foablak.meretek.setSize(1250, 809);
        
        JLabel lblNewLabel = new JLabel("Zárolás felvitele");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setBounds(579, 33, 170, 14);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("ID");
        lblNewLabel_1.setBounds(42, 48, 46, 14);
        add(lblNewLabel_1);
        
        id_mezo = new JTextField();
        id_mezo.addKeyListener(new Enter());
        id_mezo.setBounds(98, 45, 46, 20);
        add(id_mezo);
        id_mezo.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("Projekt");
        lblNewLabel_2.setBounds(42, 88, 46, 14);
        add(lblNewLabel_2);
        
        projekt_box = new JComboBox<String>(combobox_tomb.getCombobox(ComboBox.vevoi_projekt));                    //combobox_tomb.getCombobox(ComboBox.vevoi_projekt)
        projekt_box.setBounds(98, 84, 164, 22);
        add(projekt_box);
        
        JLabel lblNewLabel_3 = new JLabel("Típus");
        lblNewLabel_3.setBounds(306, 88, 46, 14);
        add(lblNewLabel_3);
        
        tipus_box = new JComboBox<String>(cikkszamok());                      //cikkszamok()
        tipus_box.setBounds(362, 84, 283, 22);
        add(tipus_box);
        
        JLabel lblNewLabel_4 = new JLabel("Észlelés helye");
        lblNewLabel_4.setBounds(664, 88, 85, 14);
        add(lblNewLabel_4);
        
        eszleleshelye_mezo = new JTextField();
        eszleleshelye_mezo.setBounds(784, 85, 184, 20);
        add(eszleleshelye_mezo);
        eszleleshelye_mezo.setColumns(10);
        
        JLabel lblNewLabel_5 = new JLabel("Műszak");
        lblNewLabel_5.setBounds(995, 88, 46, 14);
        add(lblNewLabel_5);
        
        muszak_mezo = new JTextField();
        muszak_mezo.setBounds(1051, 85, 46, 20);
        add(muszak_mezo);
        muszak_mezo.setColumns(10);
        
        JLabel lblNewLabel_6 = new JLabel("Zárolást kezdeményező mérnök");
        lblNewLabel_6.setBounds(42, 136, 198, 14);
        add(lblNewLabel_6);
        
        mernok_mezo = new JTextField();
        mernok_mezo.setBounds(245, 133, 164, 20);
        add(mernok_mezo);
        mernok_mezo.setColumns(10);
        
        JLabel lblNewLabel_7 = new JLabel("Zárolt mennyiség");
        lblNewLabel_7.setBounds(430, 136, 99, 14);
        add(lblNewLabel_7);
        
        zaroltdb_mezo = new JTextField();
        zaroltdb_mezo.setBounds(539, 133, 46, 20);
        add(zaroltdb_mezo);
        zaroltdb_mezo.setColumns(10);
        
        JLabel lblNewLabel_8 = new JLabel("Hol van");
        lblNewLabel_8.setBounds(621, 136, 74, 14);
        add(lblNewLabel_8);
        
        zarolas_helye = new JTextField();
        zarolas_helye.setBounds(716, 133, 184, 20);
        add(zarolas_helye);
        zarolas_helye.setColumns(10);
        
        JLabel lblNewLabel_9 = new JLabel("Zárolás oka");
        lblNewLabel_9.setBounds(42, 180, 74, 14);
        add(lblNewLabel_9);
        
        JLabel lblNewLabel_10 = new JLabel("Azonnali intézkedés");
        lblNewLabel_10.setBounds(387, 180, 113, 14);
        add(lblNewLabel_10);
        
        zarolasoka_mezo = new JTextArea();
        zarolasoka_mezo.setLineWrap(true);
        zarolasoka_mezo.setWrapStyleWord(true);
        zarolasoka_mezo.setBounds(113, 180, 238, 92);
        add(zarolasoka_mezo);
        
        intezkedes_mezo = new JTextArea();
        intezkedes_mezo.setLineWrap(true);
        intezkedes_mezo.setWrapStyleWord(true);
        intezkedes_mezo.setBounds(510, 175, 238, 97);
        add(intezkedes_mezo);
        
        JLabel lblNewLabel_11 = new JLabel("Zárolás dátuma");
        lblNewLabel_11.setBounds(792, 180, 91, 14);
        add(lblNewLabel_11);
        
        datum_mezo = new JTextField();
        datum_mezo.setBounds(893, 177, 86, 20);
        add(datum_mezo);
        datum_mezo.setColumns(10);
        
        JLabel lblNewLabel_12 = new JLabel("Zároló papír sorszáma");
        lblNewLabel_12.setBounds(792, 233, 129, 14);
        add(lblNewLabel_12);
        
        sorszam_mezo = new JTextField();
        sorszam_mezo.setBounds(936, 230, 86, 20);
        add(sorszam_mezo);
        sorszam_mezo.setColumns(10);
        
        zarolta_box = new JComboBox<String>(ellenori_nevsor());                           //ellenori_nevsor()
        zarolta_box.setBounds(1024, 176, 184, 22);
        add(zarolta_box);
        
        JLabel lblNewLabel_13 = new JLabel("Zárolta");
        lblNewLabel_13.setBounds(1089, 151, 46, 14);
        add(lblNewLabel_13);
        
        JLabel lblNewLabel_14 = new JLabel("Válogatás/ellenőrzés eredméyne");
        lblNewLabel_14.setBounds(42, 297, 170, 14);
        add(lblNewLabel_14);
        
        eredmeny_mezo = new JTextField();
        eredmeny_mezo.setBounds(227, 294, 386, 20);
        add(eredmeny_mezo);
        eredmeny_mezo.setColumns(10);
        
        JLabel lblNewLabel_15 = new JLabel("Újraellenőrzés dátuma");
        lblNewLabel_15.setBounds(661, 297, 129, 14);
        add(lblNewLabel_15);
        
        ujradatum_mezo = new JTextField();
        ujradatum_mezo.setBounds(800, 294, 86, 20);
        add(ujradatum_mezo);
        ujradatum_mezo.setColumns(10);
        
        JLabel lblNewLabel_16 = new JLabel("Ellenőrzésre fordított idő");
        lblNewLabel_16.setBounds(908, 297, 148, 14);
        add(lblNewLabel_16);
        
        ido_mezo = new JTextField();
        ido_mezo.setText("0");
        ido_mezo.setBounds(1066, 294, 86, 20);
        add(ido_mezo);
        ido_mezo.setColumns(10);
        
        technikus_csekk = new JCheckBox("Technikusi beavatkozást igényel");
        technikus_csekk.setBounds(42, 339, 220, 23);
        add(technikus_csekk);
        
        JLabel lblNewLabel_17 = new JLabel("Felelős terület");
        lblNewLabel_17.setBounds(270, 343, 82, 14);
        add(lblNewLabel_17);
        
        JLabel lblNewLabel_18 = new JLabel("Felelős");
        lblNewLabel_18.setBounds(522, 343, 46, 14);
        add(lblNewLabel_18);
        
        felelos_mezo = new JTextField();
        felelos_mezo.setBounds(579, 340, 251, 20);
        add(felelos_mezo);
        felelos_mezo.setColumns(10);
        
        JLabel lblNewLabel_19 = new JLabel("Hiba gyökéroka");
        lblNewLabel_19.setBounds(42, 397, 102, 14);
        add(lblNewLabel_19);
        
        gyokerok_mezo = new JTextArea();
        gyokerok_mezo.setLineWrap(true);
        gyokerok_mezo.setWrapStyleWord(true);
        gyokerok_mezo.setBounds(163, 392, 215, 92);
        add(gyokerok_mezo);
        
        JLabel lblNewLabel_20 = new JLabel("Gyökérokra hozott intézkedés");
        lblNewLabel_20.setBounds(410, 397, 175, 14);
        add(lblNewLabel_20);
        
        gyokerintezkedes_mezo = new JTextArea();
        gyokerintezkedes_mezo.setLineWrap(true);
        gyokerintezkedes_mezo.setWrapStyleWord(true);
        gyokerintezkedes_mezo.setBounds(596, 392, 215, 92);
        add(gyokerintezkedes_mezo);
        
        JLabel lblNewLabel_21 = new JLabel("B2 zárolás szükséges-e");
        lblNewLabel_21.setBounds(857, 397, 148, 14);
        add(lblNewLabel_21);
        
        b2_mezo = new JTextField();
        b2_mezo.setBounds(1011, 394, 86, 20);
        add(b2_mezo);
        b2_mezo.setColumns(10);
        
        JButton mentes_gomb = new JButton("Mentés");
        mentes_gomb.addActionListener(new Mentes());
        mentes_gomb.setBounds(539, 557, 89, 23);
        add(mentes_gomb);
        
        JLabel lblNewLabel_22 = new JLabel("Lezárás dátuma");
        lblNewLabel_22.setBounds(484, 657, 101, 14);
        add(lblNewLabel_22);
        
        lezaras_mezo = new JTextField();
        lezaras_mezo.setBounds(597, 654, 86, 20);
        add(lezaras_mezo);
        lezaras_mezo.setColumns(10);
        
        JButton lezaras_gomb = new JButton("Lezárás");
        lezaras_gomb.addActionListener(new Lezaras());
        lezaras_gomb.setBounds(539, 685, 89, 23);
        add(lezaras_gomb);
        
        String[] terulet = {"Gépes", "Kézi","Végszerelés"};
        felelosterulet_box = new JComboBox<String>(terulet);                         //terulet
        felelosterulet_box.setBounds(362, 339, 102, 22);
        add(felelosterulet_box);
        
        JLabel lblNewLabel_23 = new JLabel("Gyökérokra hozott intézkedés visszaellenőrzése");
        lblNewLabel_23.setBounds(42, 506, 277, 14);
        add(lblNewLabel_23);
        
        visszaellenorzes_mezo = new JTextField();
        visszaellenorzes_mezo.setBounds(329, 503, 572, 20);
        add(visszaellenorzes_mezo);
        visszaellenorzes_mezo.setColumns(10);
        
        JButton vissza_gomb = new JButton("Vissza");
        vissza_gomb.addActionListener(new Vissza());
        vissza_gomb.setBounds(1089, 44, 89, 23);
        add(vissza_gomb);
        
        Utolso_sor sorszam = new Utolso_sor();
        int kovetkezo = Integer.parseInt(sorszam.utolso("qualitydb.Zarolasok"));
        id_mezo.setText(String.valueOf(kovetkezo + 1));
        
        setBackground(Foablak.hatter_szine);
        
        meszam_mezo = new JTextField();
        meszam_mezo.setBounds(1044, 111, 164, 20);
        add(meszam_mezo);
        meszam_mezo.setColumns(10);
        
        JLabel lblNewLabel_24 = new JLabel("Raklap ME szám");
        lblNewLabel_24.setBounds(943, 114, 102, 14);
        add(lblNewLabel_24);

    }
    
    public Zarolasok_bevitel(String id) {
        setLayout(null);
        
        this.setPreferredSize(new Dimension(1237, 800));
        Foablak.meretek.setSize(1250, 809);
        
        JLabel lblNewLabel = new JLabel("Zárolás felvitele");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setBounds(579, 33, 170, 14);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("ID");
        lblNewLabel_1.setBounds(42, 48, 46, 14);
        add(lblNewLabel_1);
        
        id_mezo = new JTextField();
        id_mezo.addKeyListener(new Enter());
        id_mezo.setBounds(98, 45, 46, 20);
        add(id_mezo);
        id_mezo.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("Projekt");
        lblNewLabel_2.setBounds(42, 88, 46, 14);
        add(lblNewLabel_2);
        
        projekt_box = new JComboBox<String>(combobox_tomb.getCombobox(ComboBox.vevoi_projekt));                    //combobox_tomb.getCombobox(ComboBox.vevoi_projekt)
        projekt_box.setBounds(98, 84, 164, 22);
        add(projekt_box);
        
        JLabel lblNewLabel_3 = new JLabel("Típus");
        lblNewLabel_3.setBounds(306, 88, 46, 14);
        add(lblNewLabel_3);
        
        tipus_box = new JComboBox<String>(cikkszamok());                      //cikkszamok()
        
        tipus_box.setBounds(362, 84, 212, 22);
        add(tipus_box);
        
        JLabel lblNewLabel_4 = new JLabel("Észlelés helye");
        lblNewLabel_4.setBounds(621, 88, 85, 14);
        add(lblNewLabel_4);
        
        eszleleshelye_mezo = new JTextField();
        eszleleshelye_mezo.setBounds(716, 85, 184, 20);
        add(eszleleshelye_mezo);
        eszleleshelye_mezo.setColumns(10);
        
        JLabel lblNewLabel_5 = new JLabel("Műszak");
        lblNewLabel_5.setBounds(943, 88, 46, 14);
        add(lblNewLabel_5);
        
        muszak_mezo = new JTextField();
        muszak_mezo.setBounds(999, 85, 46, 20);
        add(muszak_mezo);
        muszak_mezo.setColumns(10);
        
        JLabel lblNewLabel_6 = new JLabel("Zárolást kezdeményező mérnök");
        lblNewLabel_6.setBounds(42, 136, 198, 14);
        add(lblNewLabel_6);
        
        mernok_mezo = new JTextField();
        mernok_mezo.setBounds(245, 133, 164, 20);
        add(mernok_mezo);
        mernok_mezo.setColumns(10);
        
        JLabel lblNewLabel_7 = new JLabel("Zárolt mennyiség");
        lblNewLabel_7.setBounds(430, 136, 99, 14);
        add(lblNewLabel_7);
        
        zaroltdb_mezo = new JTextField();
        zaroltdb_mezo.setBounds(539, 133, 46, 20);
        add(zaroltdb_mezo);
        zaroltdb_mezo.setColumns(10);
        
        JLabel lblNewLabel_8 = new JLabel("Hol van");
        lblNewLabel_8.setBounds(621, 136, 74, 14);
        add(lblNewLabel_8);
        
        zarolas_helye = new JTextField();
        zarolas_helye.setBounds(716, 133, 184, 20);
        add(zarolas_helye);
        zarolas_helye.setColumns(10);
        
        JLabel lblNewLabel_9 = new JLabel("Zárolás oka");
        lblNewLabel_9.setBounds(42, 180, 74, 14);
        add(lblNewLabel_9);
        
        JLabel lblNewLabel_10 = new JLabel("Azonnali intézkedés");
        lblNewLabel_10.setBounds(387, 180, 113, 14);
        add(lblNewLabel_10);
        
        zarolasoka_mezo = new JTextArea();
        zarolasoka_mezo.setLineWrap(true);
        zarolasoka_mezo.setWrapStyleWord(true);
        zarolasoka_mezo.setBounds(113, 180, 238, 92);
        add(zarolasoka_mezo);
        
        intezkedes_mezo = new JTextArea();
        intezkedes_mezo.setLineWrap(true);
        intezkedes_mezo.setWrapStyleWord(true);
        intezkedes_mezo.setBounds(510, 175, 238, 97);
        add(intezkedes_mezo);
        
        JLabel lblNewLabel_11 = new JLabel("Zárolás dátuma");
        lblNewLabel_11.setBounds(792, 180, 91, 14);
        add(lblNewLabel_11);
        
        datum_mezo = new JTextField();
        datum_mezo.setBounds(893, 177, 86, 20);
        add(datum_mezo);
        datum_mezo.setColumns(10);
        
        JLabel lblNewLabel_12 = new JLabel("Zároló papír sorszáma");
        lblNewLabel_12.setBounds(792, 233, 129, 14);
        add(lblNewLabel_12);
        
        sorszam_mezo = new JTextField();
        sorszam_mezo.setBounds(936, 230, 86, 20);
        add(sorszam_mezo);
        sorszam_mezo.setColumns(10);
        
        zarolta_box = new JComboBox<String>(ellenori_nevsor());                           //ellenori_nevsor()
        zarolta_box.setBounds(1024, 176, 184, 22);
        add(zarolta_box);
        
        JLabel lblNewLabel_13 = new JLabel("Zárolta");
        lblNewLabel_13.setBounds(1089, 151, 46, 14);
        add(lblNewLabel_13);
        
        JLabel lblNewLabel_14 = new JLabel("Válogatás/ellenőrzés eredméyne");
        lblNewLabel_14.setBounds(42, 297, 170, 14);
        add(lblNewLabel_14);
        
        eredmeny_mezo = new JTextField();
        eredmeny_mezo.setBounds(227, 294, 386, 20);
        add(eredmeny_mezo);
        eredmeny_mezo.setColumns(10);
        
        JLabel lblNewLabel_15 = new JLabel("Újraellenőrzés dátuma");
        lblNewLabel_15.setBounds(661, 297, 129, 14);
        add(lblNewLabel_15);
        
        ujradatum_mezo = new JTextField();
        ujradatum_mezo.setBounds(800, 294, 86, 20);
        add(ujradatum_mezo);
        ujradatum_mezo.setColumns(10);
        
        JLabel lblNewLabel_16 = new JLabel("Ellenőrzésre fordított idő");
        lblNewLabel_16.setBounds(908, 297, 148, 14);
        add(lblNewLabel_16);
        
        ido_mezo = new JTextField();
        ido_mezo.setText("0");
        ido_mezo.setBounds(1066, 294, 86, 20);
        add(ido_mezo);
        ido_mezo.setColumns(10);
        
        technikus_csekk = new JCheckBox("Technikusi beavatkozást igényel");
        technikus_csekk.setBounds(42, 339, 220, 23);
        add(technikus_csekk);
        
        JLabel lblNewLabel_17 = new JLabel("Felelős terület");
        lblNewLabel_17.setBounds(270, 343, 82, 14);
        add(lblNewLabel_17);
        
        JLabel lblNewLabel_18 = new JLabel("Felelős");
        lblNewLabel_18.setBounds(522, 343, 46, 14);
        add(lblNewLabel_18);
        
        felelos_mezo = new JTextField();
        felelos_mezo.setBounds(579, 340, 251, 20);
        add(felelos_mezo);
        felelos_mezo.setColumns(10);
        
        JLabel lblNewLabel_19 = new JLabel("Hiba gyökéroka");
        lblNewLabel_19.setBounds(42, 397, 102, 14);
        add(lblNewLabel_19);
        
        gyokerok_mezo = new JTextArea();
        gyokerok_mezo.setLineWrap(true);
        gyokerok_mezo.setWrapStyleWord(true);
        gyokerok_mezo.setBounds(163, 392, 215, 92);
        add(gyokerok_mezo);
        
        JLabel lblNewLabel_20 = new JLabel("Gyökérokra hozott intézkedés");
        lblNewLabel_20.setBounds(410, 397, 175, 14);
        add(lblNewLabel_20);
        
        gyokerintezkedes_mezo = new JTextArea();
        gyokerintezkedes_mezo.setLineWrap(true);
        gyokerintezkedes_mezo.setWrapStyleWord(true);
        gyokerintezkedes_mezo.setBounds(596, 392, 215, 92);
        add(gyokerintezkedes_mezo);
        
        JLabel lblNewLabel_21 = new JLabel("B2 zárolás szükséges-e");
        lblNewLabel_21.setBounds(857, 397, 148, 14);
        add(lblNewLabel_21);
        
        b2_mezo = new JTextField();
        b2_mezo.setBounds(1011, 394, 86, 20);
        add(b2_mezo);
        b2_mezo.setColumns(10);
        
        JButton mentes_gomb = new JButton("Mentés");
        mentes_gomb.addActionListener(new Mentes());
        mentes_gomb.setBounds(539, 557, 89, 23);
        add(mentes_gomb);
        
        JLabel lblNewLabel_22 = new JLabel("Lezárás dátuma");
        lblNewLabel_22.setBounds(484, 657, 101, 14);
        add(lblNewLabel_22);
        
        lezaras_mezo = new JTextField();
        lezaras_mezo.setBounds(597, 654, 86, 20);
        add(lezaras_mezo);
        lezaras_mezo.setColumns(10);
        
        JButton lezaras_gomb = new JButton("Lezárás");
        lezaras_gomb.addActionListener(new Lezaras());
        lezaras_gomb.setBounds(539, 685, 89, 23);
        add(lezaras_gomb);
        
        String[] terulet = {"Gépes", "Kézi","Végszerelés"};
        felelosterulet_box = new JComboBox<String>(terulet);                         //terulet
        felelosterulet_box.setBounds(362, 339, 102, 22);
        add(felelosterulet_box);
        
        JLabel lblNewLabel_23 = new JLabel("Gyökérokra hozott intézkedés visszaellenőrzése");
        lblNewLabel_23.setBounds(42, 506, 277, 14);
        add(lblNewLabel_23);
        
        visszaellenorzes_mezo = new JTextField();
        visszaellenorzes_mezo.setBounds(329, 503, 572, 20);
        add(visszaellenorzes_mezo);
        visszaellenorzes_mezo.setColumns(10);
        
        JButton vissza_gomb = new JButton("Vissza");
        vissza_gomb.addActionListener(new Vissza());
        vissza_gomb.setBounds(1089, 44, 89, 23);
        add(vissza_gomb);
        
        Utolso_sor sorszam = new Utolso_sor();
        int kovetkezo = Integer.parseInt(sorszam.utolso("qualitydb.Zarolasok"));
        id_mezo.setText(String.valueOf(kovetkezo + 1));
        
        setBackground(Foablak.hatter_szine);
        
        meszam_mezo = new JTextField();
        meszam_mezo.setBounds(1044, 111, 164, 20);
        add(meszam_mezo);
        meszam_mezo.setColumns(10);
        
        JLabel lblNewLabel_24 = new JLabel("Raklap ME szám");
        lblNewLabel_24.setBounds(943, 114, 102, 14);
        add(lblNewLabel_24);

        visszatolt(id);

    }
    
    public String[] cikkszamok()
    {
        String[] cikkbox = null;
        try
        {            
            Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            Class.forName("oracle.jdbc.OracleDriver");  //.driver
                                
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@IFSORA.IFS.videoton.hu:1521/IFSPROD","ZKOVACS","ZKOVACS");                                      
            Statement stmt = con.createStatement();              
            ResultSet rs = stmt.executeQuery("select cikkszamok.part_no, \r\n"
                    + "        cikkszamok.REVISION_TEXT,\r\n"
                    + "        cikkszamok.CF$_PRODUCT_CODE_DESC \r\n"
                    + "from ifsapp.PART_REVISION_cfv cikkszamok\r\n"
                    + "where 3 = 3 order by part_no asc");
            ArrayList<String> cikkszamok = new ArrayList<String>();
            while(rs.next())
            {
                if(rs.getString(2) != null)
                {
                    cikkszamok.add(rs.getString(1)+ "- "+ rs.getString(2) + " "+ rs.getString(3));
                }
                else
                {
                    cikkszamok.add(rs.getString(1)+ " "+ rs.getString(3));
                }
            }
            cikkbox = cikkszamok.toArray(new String[cikkszamok.size()]);
            con.close();  
            Foablak.frame.setCursor(null);        
        }           
        catch(Exception e1)
        { 
            System.out.println(e1);
            e1.printStackTrace();
            String hibauzenet = e1.toString();
            Email hibakuldes = new Email();
            hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
            JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                               //kiírja a hibaüzenetet
        }
        
        return cikkbox;  
                           
     }
    
    public String[] ellenori_nevsor()
    {
        Connection conn = null;
        Statement stmt = null;
        ResultSet resultSet = null;
        String[] ellenorok = null;
        try 
        {
           try 
           {
              Class.forName("com.mysql.cj.jdbc.Driver");
           } 
           catch (Exception e) 
           {
              System.out.println(e);
              String hibauzenet2 = e.toString();
              JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
           }
        conn = DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String sajat = "select Nev from qualitydb.Alapadatok_ellenorok where 3 = 3 order by nev asc";  //group by Pozicio
        stmt.execute(sajat);
        resultSet = stmt.getResultSet();
        ArrayList<String> nevek = new ArrayList<String>();
        while(resultSet.next())
        {
            nevek.add(resultSet.getString(1));
        }
        
        ellenorok = nevek.toArray(new String[nevek.size()]);                                //sima tömbbé alakítja az ArrayListet

        resultSet.close();
        stmt.close();
        conn.close();
        
        } 
        catch (SQLException e1) 
        {
           e1.printStackTrace();
           String hibauzenet = e1.toString();
           Email hibakuldes = new Email();
           hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
           JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                 //kiírja a hibaüzenetet
        } 
        catch (Exception e) 
        {
           e.printStackTrace();
           String hibauzenet = e.toString();
           Email hibakuldes = new Email();
           hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
           JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                 //kiírja a hibaüzenetet
        } 
        finally 
        {
           try 
           {
              if (stmt != null)
                 conn.close();
           } 
           catch (SQLException se) {}
           try 
           {
              if (conn != null)
                 conn.close();
           } 
           catch (SQLException se) 
           {
              se.printStackTrace();
              String hibauzenet = se.toString();
              Email hibakuldes = new Email();
              hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
              JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                 //kiírja a hibaüzenetet
           }  
        }
        return ellenorok;
    }
    
    class Vissza implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                                                //egér mutató változtatása munka a háttérbenre
                Zarolasok_osszesito zarolasok = new Zarolasok_osszesito();
                JScrollPane ablak = new JScrollPane(zarolasok);
                ablak.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                ablak.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                Foablak.frame.setContentPane(ablak);
                Foablak.frame.pack();
                Foablak.frame.setCursor(null);                                                                                         //egér mutató alaphelyzetbe állítása
            }
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                   //kivétel esetén kiírja a hibaüzenetet
            }
         }
    }
    
    class Lezaras implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                                                //egér mutató változtatása munka a háttérbenre
                String sql = "update qualitydb.Zarolasok set  Lezaras_datuma ='"+ lezaras_mezo.getText() +"' where id = '"+ id_mezo.getText() +"'";
                lekerdezes.mindenes(sql);
                Foablak.frame.setCursor(null);                                                                                         //egér mutató alaphelyzetbe állítása
            }
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                    //kivétel esetén kiírja a hibaüzenetet
            }
         }
    }
    
    class Mentes implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                                                //egér mutató változtatása munka a háttérbenre
                Connection conn = null;
                Statement stmt = null;        
                try 
                {
                   Class.forName("com.mysql.cj.jdbc.Driver");
                } 
                catch (Exception e1) 
                {
                   System.out.println(e);
                   String hibauzenet2 = e.toString();
                   JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
                }
                conn = (Connection) DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
                stmt = (Statement) conn.createStatement();
                String SQL = "select * from qualitydb.Zarolasok where id = '"+ id_mezo.getText() +"'";
                ResultSet rs = stmt.executeQuery(SQL);
                String sql = "";
                String technikus = "Nem";
                
                if(ido_mezo.getText().equals(""))
                {
                    ido_mezo.setText("0");
                }
                if(datum_mezo.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Nem adtál meg dátumot!!", "Hiba üzenet", 2);
                }
                else if(zaroltdb_mezo.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Nem adtál meg darabszámot!!", "Hiba üzenet", 2);
                }            
                else
                {
                    if(rs.next())
                    {
                        if(technikus_csekk.isSelected())
                        {
                            technikus = "Igen";
                            
                        }
                        sql = "update qualitydb.Zarolasok set  Projekt ='"+ String.valueOf(projekt_box.getSelectedItem()) +"',Tipus = '"+ String.valueOf(tipus_box.getSelectedItem()) +"',"
                                + "Eszleles_helye ='"+ eszleleshelye_mezo.getText() +"',"
                                + "muszak ='"+ muszak_mezo.getText() +"',Zarolo_mernok = '"+ mernok_mezo.getText() +"',Zarolt_db ='"+Integer.parseInt(zaroltdb_mezo.getText())+"',Hol_van ='"+ zarolas_helye.getText() +"',"
                                + "Zarolas_oka ='"+ zarolasoka_mezo.getText() +"',Azonnali_intezkedes ='"+ intezkedes_mezo.getText() +"',Zarolas_datuma ='"+ datum_mezo.getText() +"',Papir_sorszama ='"+ sorszam_mezo.getText() +"',"
                                + "Zarolta ='"+ String.valueOf(zarolta_box.getSelectedItem()) +"',"
                                + "Valogatas_eredmenye ='"+ eredmeny_mezo.getText() +"',Ujraellenorzes_datuma ='"+ ujradatum_mezo.getText() +"',Ellenorzes_ido ='"+ ido_mezo.getText() +"',Technikusi_beavatkozas ='"+ technikus +"',"
                                + "Felelos_terulet ='"+ String.valueOf(felelosterulet_box.getSelectedItem()) +"',"
                                + "Felelos ='"+ felelos_mezo.getText() +"',Hiba_gyokeroka ='"+ gyokerok_mezo.getText() +"',Gyokerok_intezkedes ='"+ gyokerintezkedes_mezo.getText() +"',"
                                + "B2_zarolas ='"+ b2_mezo.getText() +"',visszaellenorzes ='"+ visszaellenorzes_mezo.getText() +"',ME_szam ='"+ meszam_mezo.getText() +"' where id = '"+ id_mezo.getText() +"'";
                        
                    }
                    else
                    {
                        if(technikus_csekk.isSelected())
                        {
                            technikus = "Igen";
                            Email uzenet = new Email();
                            if(felelosterulet_box.getSelectedItem().equals("Gépes"))
                            {
                                uzenet.zarolas_email("automataemail@veas.videoton.hu", "ternak.sandor@veas.videoton.hu, kadar.zoltan@veas.videoton.hu", String.valueOf(projekt_box.getSelectedItem()), String.valueOf(tipus_box.getSelectedItem()),
                                        zaroltdb_mezo.getText(), zarolasoka_mezo.getText(), String.valueOf(zarolta_box.getSelectedItem()), datum_mezo.getText(), muszak_mezo.getText());
                            }
                            else if(felelosterulet_box.getSelectedItem().equals("Kézi"))
                            {
                                uzenet.zarolas_email("automataemail@veas.videoton.hu", "nagy.balint@veas.videoton.hu, molnar.jozsef@veas.videoton.hu", String.valueOf(projekt_box.getSelectedItem()), String.valueOf(tipus_box.getSelectedItem()),
                                        zaroltdb_mezo.getText(), zarolasoka_mezo.getText(), String.valueOf(zarolta_box.getSelectedItem()), datum_mezo.getText(), muszak_mezo.getText());
                            }
                            else
                            {
                                uzenet.zarolas_email("automataemail@veas.videoton.hu", "babud.imre@veas.videoton.hu, meszaros.hajnalka@veas.videoton.hu, serebrianska.kateryna@veas.videoton.hu", String.valueOf(projekt_box.getSelectedItem()), String.valueOf(tipus_box.getSelectedItem()),
                                        zaroltdb_mezo.getText(), zarolasoka_mezo.getText(), String.valueOf(zarolta_box.getSelectedItem()), datum_mezo.getText(), muszak_mezo.getText());
                            }                                               
                        }
                        sql = "insert into qualitydb.Zarolasok (ID,Projekt,Tipus,Eszleles_helye,Muszak,Zarolo_mernok,Zarolt_db,Hol_van,Zarolas_oka,Azonnali_intezkedes,Zarolas_datuma,"
                                + "Papir_sorszama,Zarolta,Valogatas_eredmenye,Ujraellenorzes_datuma,\r\n"
                                + "Ellenorzes_ido,Technikusi_beavatkozas,Felelos_terulet,Felelos,Hiba_gyokeroka,Gyokerok_intezkedes,B2_zarolas,visszaellenorzes,ME_szam) "
                                + "Values('"+ id_mezo.getText()+"','"+ String.valueOf(projekt_box.getSelectedItem()) +"','"+ String.valueOf(tipus_box.getSelectedItem()) +"',"
                                + "'"+ eszleleshelye_mezo.getText() +"','"+ muszak_mezo.getText() +"','"+ mernok_mezo.getText() +"','"+Integer.parseInt(zaroltdb_mezo.getText())+"','"+ zarolas_helye.getText() +"',"
                                + "'"+ zarolasoka_mezo.getText() +"','"+ intezkedes_mezo.getText() +"','"+ datum_mezo.getText() +"','"+ sorszam_mezo.getText() +"','"+ String.valueOf(zarolta_box.getSelectedItem()) +"',"
                                + "'"+ eredmeny_mezo.getText() +"','"+ ujradatum_mezo.getText() +"','"+ ido_mezo.getText() +"','"+ technikus +"','"+ String.valueOf(felelosterulet_box.getSelectedItem()) +"',"
                                + "'"+ felelos_mezo.getText() +"','"+ gyokerok_mezo.getText() +"','"+ gyokerintezkedes_mezo.getText() +"','"+ b2_mezo.getText() +"','"+ visszaellenorzes_mezo.getText() +"'"
                                        + ",'"+ meszam_mezo.getText() +"')";
                        
                    }
                    lekerdezes.mindenes(sql);
                    eszleleshelye_mezo.setText("");
                    muszak_mezo.setText("");
                    mernok_mezo.setText("");
                    zaroltdb_mezo.setText("0");
                    zarolas_helye.setText("");
                    zarolasoka_mezo.setText("");
                    intezkedes_mezo.setText("");
                    datum_mezo.setText("");
                    sorszam_mezo.setText("");
                    eredmeny_mezo.setText("");
                    ujradatum_mezo.setText("");
                    ido_mezo.setText("");
                    technikus_csekk.setSelected(false);
                    felelos_mezo.setText("");
                    gyokerok_mezo.setText("");
                    gyokerintezkedes_mezo.setText("");
                    b2_mezo.setText("");
                    visszaellenorzes_mezo.setText("");
                    meszam_mezo.setText("");
                    projekt_box.setSelectedIndex(1);
                    tipus_box.setSelectedIndex(1);
                    zarolta_box.setSelectedIndex(1);
                    
                    int kovetkezo = Integer.parseInt(id_mezo.getText());
                    id_mezo.setText(String.valueOf(kovetkezo + 1));
                }
            }
                catch (Exception e1) 
                {
                    e1.printStackTrace();
                    String hibauzenet = e1.toString();
                    Email hibakuldes = new Email();
                    hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                    JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                     //kivétel esetén kiírja a hibaüzenetet
                }
                Foablak.frame.setCursor(null);                                                                                         //egér mutató alaphelyzetbe állítása
            
           
         }
    }
    
    public void visszatolt(String id)
    {
        try 
        {
            Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                                                //egér mutató változtatása munka a háttérbenre
            Connection conn = null;
            Statement stmt = null;        
            try 
            {
               try 
               {
                  Class.forName("com.mysql.cj.jdbc.Driver");
               } 
               catch (Exception e1) 
               {
                  System.out.println(e1);
                  String hibauzenet2 = e1.toString();
                  JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
               }
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
            stmt = (Statement) conn.createStatement();
            String SQL = "select * from qualitydb.Zarolasok where id = '"+ id +"'";
            ResultSet rs = stmt.executeQuery(SQL);
            if(rs.next())
            {
                id_mezo.setText(rs.getString(1));
                projekt_box.setSelectedItem(rs.getString(2));
                tipus_box.setSelectedItem(rs.getString(3));
                eszleleshelye_mezo.setText(rs.getString(4));
                muszak_mezo.setText(rs.getString(5));
                mernok_mezo.setText(rs.getString(6));
                zaroltdb_mezo.setText(rs.getString(7));
                zarolas_helye.setText(rs.getString(8));
                zarolasoka_mezo.setText(rs.getString(9));
                intezkedes_mezo.setText(rs.getString(10));
                String[] datum = rs.getString(11).split(" "); 
                datum_mezo.setText(datum[0]);
                sorszam_mezo.setText(rs.getString(12));
                zarolta_box.setSelectedItem(rs.getString(13));
                eredmeny_mezo.setText(rs.getString(14));
                ujradatum_mezo.setText(rs.getString(15));
                ido_mezo.setText(rs.getString(16));
                if(rs.getString(17).equals("Igen"))
                {
                    technikus_csekk.setSelected(true);
                }
                felelosterulet_box.setSelectedItem(rs.getString(18));
                felelos_mezo.setText(rs.getString(19));
                gyokerok_mezo.setText(rs.getString(20));
                gyokerintezkedes_mezo.setText(rs.getString(21));
                b2_mezo.setText(rs.getString(22));
                visszaellenorzes_mezo.setText(rs.getString(23));
                if(rs.getString(24) != null)
                {
                    String[] lezarva = rs.getString(24).split(" ");
                    lezaras_mezo.setText(lezarva[0]);
                }
                meszam_mezo.setText(rs.getString(25));
    
            }
            stmt.close();
            conn.close();
                    
            } 
            catch (SQLException e1) 
            {
               e1.printStackTrace();
               String hibauzenet = e1.toString();
               Email hibakuldes = new Email();
               hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
               JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            } 
            catch (Exception e1) 
            {
               e1.printStackTrace();
               String hibauzenet = e1.toString();
               Email hibakuldes = new Email();
               hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
               JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);;
            } finally 
            {
               try 
               {
                  if (stmt != null)
                     conn.close();
               } 
               catch (SQLException se) {}
               try 
               {
                  if (conn != null)
                     conn.close();
               } 
               catch (SQLException se) 
               {
                  se.printStackTrace();
               }  
            }
            Foablak.frame.setCursor(null);                                                                                          //egér mutató alaphelyzetbe állítása
        } 
        catch (Exception e1) 
        {              
            e1.printStackTrace();
            String hibauzenet = e1.toString();
            Email hibakuldes = new Email();
            hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
            JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
        }    
    }
    
    class Enter implements KeyListener                                                                                                 //billentyűzet figyelő eseménykezelő, kiszámolja mennyit kell ellenőrizni
    {
        public void keyPressed (KeyEvent e) 
        {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_ENTER)                                                                                               //ha az entert nyomják le akkor hívódik meg
            {
                visszatolt(id_mezo.getText());
            }
         
        }
        @Override
        public void keyTyped(KeyEvent e)                                                //kötelezően kell implementálni, de ezt nem akarom figyelni, így üresen hagyom 
        {
            // TODO Auto-generated method stub           
        }
        @Override
        public void keyReleased(KeyEvent e)                                             //kötelezően kell implementálni, de ezt nem akarom figyelni, így üresen hagyom 
        {
            // TODO Auto-generated method stub           
        }    
    }
    
    
}
