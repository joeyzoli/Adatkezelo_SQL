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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JTextField;

import com.spire.xls.ExcelVersion;
import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JSeparator;

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
    private JTextField felelos_mezo2;
    private JTextField felelos_mezo3;
    private JTextField felelos_mezo4;
    private JTextField felelos_mezo5;
    private JTextField b2_mezo;
    private JTextField lezaras_mezo;
    private ComboBox combobox_tomb = new ComboBox();
    private JTextArea gyokerintezkedes_mezo;
    private JTextArea gyokerintezkedes2_mezo;
    private JTextArea gyokerintezkedes3_mezo;
    private JTextArea gyokerintezkedes4_mezo;
    private JTextArea gyokerintezkedes5_mezo;
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
    private JTextField ellenorzott_mezo;
    private JComboBox<String> hibacsoport_box;
    private JComboBox<String> hibatipus_box;
    private Workbook workbook = new Workbook();
    private String excelhelye2 = "\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\CCS2.xlsx";
    private Kivalaszt valaszto = new Kivalaszt();
    private String cc = "rabine.anita@veas.videoton.hu,sagi.szilvia@veas.videoton.hu,juhasz.iren@veas.videoton.hu,tatai.mihaly@veas.videoton.hu,kurgya.edina@veas.videoton.hu";
    private String gepes = "gepesfolyamatellenor@veas.videoton.hu";
    private String kezi = "quality.control@veas.videoton.hu,quality.inspection@veas.videoton.hu,folyamatellenor.163@veas.videoton.hu,hager.security.oqc@veas.videoton.hu,loxoneoqc@veas.videoton.hu";
    private String vegszereles = "avmoqc@veas.videoton.hu,osram_vegellenorzes@veas.videoton.hu,loxoneoqc@veas.videoton.hu,\r\n"
                                + "hager.security.oqc@veas.videoton.hu,techemoqc@veas.videoton.hu,telecomdesignoqc@veas.videoton.hu,proglove@veas.videoton.hu";
    private String cimzettek = "";
    private JTextField hatarido_mezo;
    private JTextField hatarido2_mezo;
    private JTextField hatarido3_mezo;
    private JTextField hatarido4_mezo;
    private JTextField hatarido5_mezo;
    private JComboBox<String> gyokerokvalaszto_box;
    private JLabel lblNewLabel_29;
    private JComboBox<String> zarolasoka_box;

    /**
     * Create the panel.
     */
    public Zarolasok_bevitel() {
        setLayout(null);
        
        this.setPreferredSize(new Dimension(1268, 800));
        Foablak.meretek.setSize(1250, 900);
        
        JLabel lblNewLabel = new JLabel("Zárolás felvitele");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setBounds(579, 11, 170, 14);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("ID");
        lblNewLabel_1.setBounds(42, 12, 46, 14);
        add(lblNewLabel_1);
        
        id_mezo = new JTextField();
        id_mezo.addKeyListener(new Enter());
        id_mezo.setBounds(98, 11, 46, 20);
        add(id_mezo);
        id_mezo.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("Projekt");
        lblNewLabel_2.setBounds(42, 88, 46, 14);
        add(lblNewLabel_2);
        
        projekt_box = new JComboBox<String>(combobox_tomb.getCombobox(ComboBox.vevoi_projekt));                    //combobox_tomb.getCombobox(ComboBox.vevoi_projekt)
        projekt_box.addActionListener(valaszto);
        projekt_box.setBounds(98, 84, 164, 22);
        add(projekt_box);
        
        JLabel lblNewLabel_3 = new JLabel("Típus");
        lblNewLabel_3.setBounds(286, 88, 46, 14);
        add(lblNewLabel_3);
        
        String[] info = {"Válassz projektet"};
        tipus_box = new JComboBox<String>(info);                      //cikkszamok()            //info
        tipus_box.setBounds(329, 84, 283, 22);
        add(tipus_box);
        
        JLabel lblNewLabel_4 = new JLabel("Észlelés helye");
        lblNewLabel_4.setBounds(770, 211, 85, 14);
        add(lblNewLabel_4);
        
        eszleleshelye_mezo = new JTextField();
        eszleleshelye_mezo.setBounds(865, 205, 184, 20);
        add(eszleleshelye_mezo);
        eszleleshelye_mezo.setColumns(10);
        
        JLabel lblNewLabel_5 = new JLabel("Műszak");
        lblNewLabel_5.setBounds(471, 48, 46, 14);
        add(lblNewLabel_5);
        
        muszak_mezo = new JTextField();
        muszak_mezo.setBounds(527, 45, 46, 20);
        add(muszak_mezo);
        muszak_mezo.setColumns(10);
        
        JLabel lblNewLabel_6 = new JLabel("Zárolást kezdeményező mérnök");
        lblNewLabel_6.setBounds(770, 261, 184, 14);
        add(lblNewLabel_6);
        
        mernok_mezo = new JTextField();
        mernok_mezo.setBounds(964, 258, 164, 20);
        add(mernok_mezo);
        mernok_mezo.setColumns(10);
        
        JLabel lblNewLabel_7 = new JLabel("Zárolt mennyiség");
        lblNewLabel_7.setBounds(45, 136, 99, 14);
        add(lblNewLabel_7);
        
        zaroltdb_mezo = new JTextField();
        zaroltdb_mezo.setBounds(154, 133, 46, 20);
        add(zaroltdb_mezo);
        zaroltdb_mezo.setColumns(10);
        
        JLabel lblNewLabel_8 = new JLabel("Hol van");
        lblNewLabel_8.setBounds(539, 136, 85, 14);
        add(lblNewLabel_8);
        
        zarolas_helye = new JTextField();
        zarolas_helye.setBounds(596, 133, 184, 20);
        add(zarolas_helye);
        zarolas_helye.setColumns(10);
        
        JLabel lblNewLabel_9 = new JLabel("Zárolás oka");
        lblNewLabel_9.setBounds(42, 211, 74, 14);
        add(lblNewLabel_9);
        
        JLabel lblNewLabel_10 = new JLabel("Azonnali intézkedés");
        lblNewLabel_10.setBounds(388, 211, 113, 14);
        add(lblNewLabel_10);
        
        zarolasoka_mezo = new JTextArea();
        zarolasoka_mezo.setLineWrap(true);
        zarolasoka_mezo.setWrapStyleWord(true);
        zarolasoka_mezo.setBounds(111, 211, 238, 92);
        add(zarolasoka_mezo);
        
        intezkedes_mezo = new JTextArea();
        intezkedes_mezo.setLineWrap(true);
        intezkedes_mezo.setWrapStyleWord(true);
        intezkedes_mezo.setBounds(511, 206, 238, 97);
        add(intezkedes_mezo);
        
        JLabel lblNewLabel_11 = new JLabel("Zárolás dátuma");
        lblNewLabel_11.setBounds(268, 48, 91, 14);
        add(lblNewLabel_11);
        
        datum_mezo = new JTextField();
        datum_mezo.setBounds(362, 45, 86, 20);
        add(datum_mezo);
        datum_mezo.setColumns(10);
        
        JLabel lblNewLabel_12 = new JLabel("Zároló papír sorszáma");
        lblNewLabel_12.setBounds(42, 48, 129, 14);
        add(lblNewLabel_12);
        
        sorszam_mezo = new JTextField();
        sorszam_mezo.setBounds(176, 45, 86, 20);
        add(sorszam_mezo);
        sorszam_mezo.setColumns(10);
        
        zarolta_box = new JComboBox<String>(ellenori_nevsor());                           //ellenori_nevsor()
        zarolta_box.setBounds(690, 84, 200, 22);
        add(zarolta_box);
        
        JLabel lblNewLabel_13 = new JLabel("Zárolta");
        lblNewLabel_13.setBounds(634, 88, 46, 14);
        add(lblNewLabel_13);
        
        JLabel lblNewLabel_14 = new JLabel("Válogatás/ellenőrzés eredménye:");
        lblNewLabel_14.setBounds(42, 388, 198, 14);
        add(lblNewLabel_14);
        
        eredmeny_mezo = new JTextField();
        eredmeny_mezo.setText("0");
        eredmeny_mezo.setBounds(578, 385, 46, 20);
        add(eredmeny_mezo);
        eredmeny_mezo.setColumns(10);
        
        JLabel lblNewLabel_15 = new JLabel("Újraellenőrzés dátuma");
        lblNewLabel_15.setBounds(664, 388, 129, 14);
        add(lblNewLabel_15);
        
        ujradatum_mezo = new JTextField();
        ujradatum_mezo.setBounds(804, 385, 86, 20);
        add(ujradatum_mezo);
        ujradatum_mezo.setColumns(10);
        
        JLabel lblNewLabel_16 = new JLabel("Ellenőrzésre fordított idő");
        lblNewLabel_16.setBounds(911, 388, 148, 14);
        add(lblNewLabel_16);
        
        ido_mezo = new JTextField();
        ido_mezo.setText("0");
        ido_mezo.setBounds(1059, 385, 91, 20);
        add(ido_mezo);
        ido_mezo.setColumns(10);
        
        technikus_csekk = new JCheckBox("Technikusi beavatkozást nem igényel");
        technikus_csekk.setBounds(924, 132, 240, 23);
        add(technikus_csekk);
        
        JLabel lblNewLabel_17 = new JLabel("Felelős terület");
        lblNewLabel_17.setBounds(924, 88, 82, 14);
        add(lblNewLabel_17);
        
        JLabel lblNewLabel_18 = new JLabel("Felelős");
        lblNewLabel_18.setBounds(837, 466, 46, 14);
        add(lblNewLabel_18);
        
        felelos_mezo = new JTextField();
        felelos_mezo.setBounds(908, 463, 251, 20);
        add(felelos_mezo);
        felelos_mezo.setColumns(10);
        
        felelos_mezo2 = new JTextField();
        felelos_mezo2.setBounds(908, 463, 251, 20);
        add(felelos_mezo2);
        felelos_mezo2.setColumns(10);
        felelos_mezo2.setVisible(false);
        
        felelos_mezo3 = new JTextField();
        felelos_mezo3.setBounds(908, 463, 251, 20);
        add(felelos_mezo3);
        felelos_mezo3.setColumns(10);
        felelos_mezo3.setVisible(false);
        
        felelos_mezo4 = new JTextField();
        felelos_mezo4.setBounds(908, 463, 251, 20);
        add(felelos_mezo4);
        felelos_mezo4.setColumns(10);
        felelos_mezo4.setVisible(false);
        
        felelos_mezo5 = new JTextField();
        felelos_mezo5.setBounds(908, 463, 251, 20);
        add(felelos_mezo5);
        felelos_mezo5.setColumns(10);
        felelos_mezo5.setVisible(false);
        
        JLabel lblNewLabel_19 = new JLabel("Hiba gyökéroka");
        lblNewLabel_19.setBounds(42, 466, 102, 14);
        add(lblNewLabel_19);
        
        gyokerok_mezo = new JTextArea();
        gyokerok_mezo.setLineWrap(true);
        gyokerok_mezo.setWrapStyleWord(true);
        gyokerok_mezo.setBounds(160, 461, 240, 115);
        add(gyokerok_mezo);
        
        JLabel lblNewLabel_20 = new JLabel("Gyökérokra hozott intézkedés");
        lblNewLabel_20.setBounds(410, 466, 175, 14);
        add(lblNewLabel_20);
        
        gyokerintezkedes_mezo = new JTextArea();
        gyokerintezkedes_mezo.setLineWrap(true);
        gyokerintezkedes_mezo.setWrapStyleWord(true);
        gyokerintezkedes_mezo.setBounds(579, 461, 248, 115);
        add(gyokerintezkedes_mezo);
        
        gyokerintezkedes2_mezo = new JTextArea();
        gyokerintezkedes2_mezo.setLineWrap(true);
        gyokerintezkedes2_mezo.setWrapStyleWord(true);
        gyokerintezkedes2_mezo.setBounds(579, 461, 248, 115);
        add(gyokerintezkedes2_mezo);
        gyokerintezkedes2_mezo.setVisible(false);
        
        gyokerintezkedes3_mezo = new JTextArea();
        gyokerintezkedes3_mezo.setLineWrap(true);
        gyokerintezkedes3_mezo.setWrapStyleWord(true);
        gyokerintezkedes3_mezo.setBounds(579, 461, 248, 115);
        add(gyokerintezkedes3_mezo);
        gyokerintezkedes3_mezo.setVisible(false);
        
        gyokerintezkedes4_mezo = new JTextArea();
        gyokerintezkedes4_mezo.setLineWrap(true);
        gyokerintezkedes4_mezo.setWrapStyleWord(true);
        gyokerintezkedes4_mezo.setBounds(579, 461, 248, 115);
        add(gyokerintezkedes4_mezo);
        gyokerintezkedes4_mezo.setVisible(false);
        
        gyokerintezkedes5_mezo = new JTextArea();
        gyokerintezkedes5_mezo.setLineWrap(true);
        gyokerintezkedes5_mezo.setWrapStyleWord(true);
        gyokerintezkedes5_mezo.setBounds(579, 461, 248, 115);
        add(gyokerintezkedes5_mezo);
        gyokerintezkedes5_mezo.setVisible(false);
        
        JLabel lblNewLabel_21 = new JLabel("IFS zárolás szükséges-e");
        lblNewLabel_21.setBounds(893, 424, 148, 14);
        add(lblNewLabel_21);
        
        b2_mezo = new JTextField();
        b2_mezo.setBounds(1059, 421, 93, 20);
        add(b2_mezo);
        b2_mezo.setColumns(10);
        
        JButton mentes_gomb = new JButton("Mentés");
        mentes_gomb.addActionListener(new Mentes());
        mentes_gomb.setBounds(539, 627, 89, 23);
        add(mentes_gomb);
        
        JLabel lblNewLabel_22 = new JLabel("Lezárás dátuma");
        lblNewLabel_22.setBounds(484, 681, 101, 14);
        add(lblNewLabel_22);
        
        lezaras_mezo = new JTextField();
        lezaras_mezo.setBounds(596, 678, 86, 20);
        add(lezaras_mezo);
        lezaras_mezo.setColumns(10);
        
        JButton lezaras_gomb = new JButton("Lezárás");
        lezaras_gomb.addActionListener(new Lezaras());
        lezaras_gomb.setBounds(539, 709, 89, 23);
        add(lezaras_gomb);
        
        String[] terulet = {"Gépes", "Kézi","Végszerelés"};
        felelosterulet_box = new JComboBox<String>(terulet);                         //terulet
        felelosterulet_box.setBounds(1028, 84, 124, 22);
        add(felelosterulet_box);
        
        JLabel lblNewLabel_23 = new JLabel("Gyökérokra hozott intézkedés visszaellenőrzése");
        lblNewLabel_23.setBounds(42, 599, 277, 14);
        add(lblNewLabel_23);
        
        visszaellenorzes_mezo = new JTextField();
        visszaellenorzes_mezo.setBounds(329, 596, 572, 20);
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
        meszam_mezo.setBounds(329, 133, 180, 20);
        add(meszam_mezo);
        meszam_mezo.setColumns(10);
        
        JLabel lblNewLabel_24 = new JLabel("Raklap ME szám");
        lblNewLabel_24.setBounds(227, 136, 102, 14);
        add(lblNewLabel_24);
        
        JLabel lblNewLabel_25 = new JLabel("Ellenőrzött mennyiség:");
        lblNewLabel_25.setBounds(271, 388, 129, 14);
        add(lblNewLabel_25);
        
        ellenorzott_mezo = new JTextField();
        ellenorzott_mezo.setText("0");
        ellenorzott_mezo.setBounds(409, 385, 39, 20);
        add(ellenorzott_mezo);
        ellenorzott_mezo.setColumns(10);
        
        JLabel hibas_db = new JLabel("Hibás db");
        hibas_db.setBounds(501, 388, 72, 14);
        add(hibas_db);
        
        JLabel lblNewLabel_26 = new JLabel("Hiba csoport");
        lblNewLabel_26.setBounds(42, 350, 91, 14);
        add(lblNewLabel_26);
        
        String[] hibacsoportok = {"","alkatrész hiba", "alkatrész sérülés", "azonosítás", "beültetési hiba", "csomagolás", "darabolás", "dokumentáció", "Forrasztási hiba", "Forrasztási hiba / Hiányzó jelölés", "Funkcionális", "Hiányzó jelölés", "Jelölés", "kiöntés", "lakkozás", "nyomtatás", "ragasztás", "szennyeződés", "szerelés", "vegyes", "anyagkezelés" };
        hibacsoport_box = new JComboBox<String>(hibacsoportok);                                              //hibacsoportok
        hibacsoport_box.setBounds(159, 342, 192, 22);
        add(hibacsoport_box);
        
        JLabel lblNewLabel_27 = new JLabel("Hiba típus");
        lblNewLabel_27.setBounds(427, 347, 74, 14);
        add(lblNewLabel_27);
        
        String[] hibatipusok = {"","AOI", "címke", "címke tartalom", "deformálódás", "elcsúszott alkatrész", "elektromos hiba", "FCT", "felcserélt kábel", "ferde", "fordított", "furatkitöltöttség", "hiány", "hiányos", "hiányzó", "hiányzó forr", "hiányzó forrasztás", "hiányzó ragasztás", "hideg forr", "hideg forrasztás, óngolyó", "hosszú láb", "Hűtőpaszta pozíció", "kevés ón", "kiborult", "kupak nincs levéve", "levert alkatrész", "maszkolás", "megolvadt alkatrész", "membrán", "méret eltérés", "nem kötött meg az anyag", "nyitott", "ónfelfutás", "óngolyó", "panel", "PCB felület", "QR kód", "ragasztó a paden", "sérülés", "sok ón", "sorjás alkatrész", "szennyezett címke", "termék azonosító keveredés", "Touch-up jelölés hiányzik", "TUP", "zárlat", "zárlat/TUP"};
        hibatipus_box = new JComboBox<String>(hibatipusok);                                                //hibatipusok
        hibatipus_box.setBounds(515, 339, 215, 22);
        add(hibatipus_box);
        
        ido();
        
        setBackground(Foablak.hatter_szine);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(42, 314, 1163, 14);
        add(separator);
        
        JLabel lblNewLabel_28 = new JLabel("Határidő");
        lblNewLabel_28.setBounds(837, 512, 64, 14);
        add(lblNewLabel_28);
        
        hatarido_mezo = new JTextField();
        hatarido_mezo.setBounds(908, 509, 86, 20);
        add(hatarido_mezo);
        hatarido_mezo.setColumns(10);
        
        hatarido2_mezo = new JTextField();
        hatarido2_mezo.setBounds(908, 509, 86, 20);
        add(hatarido2_mezo);
        hatarido2_mezo.setColumns(10);
        hatarido2_mezo.setVisible(false);
        
        hatarido3_mezo = new JTextField();
        hatarido3_mezo.setBounds(908, 509, 86, 20);
        add(hatarido3_mezo);
        hatarido3_mezo.setColumns(10);
        hatarido3_mezo.setVisible(false);
        
        hatarido4_mezo = new JTextField();
        hatarido4_mezo.setBounds(908, 509, 86, 20);
        add(hatarido4_mezo);
        hatarido4_mezo.setColumns(10);
        hatarido4_mezo.setVisible(false);
        
        hatarido5_mezo = new JTextField();
        hatarido5_mezo.setBounds(908, 509, 86, 20);
        add(hatarido5_mezo);
        hatarido5_mezo.setColumns(10);
        hatarido5_mezo.setVisible(false);
        
        String[] szamok = {"1", "2","3","4","5"};
        gyokerokvalaszto_box = new JComboBox<String>(szamok);                         //szamok
        gyokerokvalaszto_box.setBounds(676, 428, 73, 22);
        gyokerokvalaszto_box.addActionListener(new Gyokerok_valaszto());
        add(gyokerokvalaszto_box);
        
        lblNewLabel_29 = new JLabel("Zárolás oka");
        lblNewLabel_29.setBounds(42, 181, 74, 14);
        add(lblNewLabel_29);
        
        String[] okok = {"-", "Hiányzó TUP jelölés"};
        zarolasoka_box = new JComboBox<String>(okok);
        zarolasoka_box.setBounds(126, 178, 274, 22);
        add(zarolasoka_box);

    }
    
    public Zarolasok_bevitel(String id) {
        setLayout(null);
        
        this.setPreferredSize(new Dimension(1268, 800));
        Foablak.meretek.setSize(1250, 900);
        
        JLabel lblNewLabel = new JLabel("Zárolás felvitele");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setBounds(579, 11, 170, 14);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("ID");
        lblNewLabel_1.setBounds(42, 12, 46, 14);
        add(lblNewLabel_1);
        
        id_mezo = new JTextField();
        id_mezo.addKeyListener(new Enter());
        id_mezo.setBounds(98, 11, 46, 20);
        add(id_mezo);
        id_mezo.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("Projekt");
        lblNewLabel_2.setBounds(42, 88, 46, 14);
        add(lblNewLabel_2);
        
        projekt_box = new JComboBox<String>(combobox_tomb.getCombobox(ComboBox.vevoi_projekt));                    //combobox_tomb.getCombobox(ComboBox.vevoi_projekt)
        projekt_box.addActionListener(valaszto);
        projekt_box.setBounds(98, 84, 164, 22);
        add(projekt_box);
        
        JLabel lblNewLabel_3 = new JLabel("Típus");
        lblNewLabel_3.setBounds(286, 88, 46, 14);
        add(lblNewLabel_3);
        
        String[] info = {"Válassz projektet"};
        tipus_box = new JComboBox<String>(info);                      //cikkszamok()            //info
        tipus_box.setBounds(329, 84, 283, 22);
        add(tipus_box);
        
        JLabel lblNewLabel_4 = new JLabel("Észlelés helye");
        lblNewLabel_4.setBounds(770, 211, 85, 14);
        add(lblNewLabel_4);
        
        eszleleshelye_mezo = new JTextField();
        eszleleshelye_mezo.setBounds(865, 205, 184, 20);
        add(eszleleshelye_mezo);
        eszleleshelye_mezo.setColumns(10);
        
        JLabel lblNewLabel_5 = new JLabel("Műszak");
        lblNewLabel_5.setBounds(471, 48, 46, 14);
        add(lblNewLabel_5);
        
        muszak_mezo = new JTextField();
        muszak_mezo.setBounds(527, 45, 46, 20);
        add(muszak_mezo);
        muszak_mezo.setColumns(10);
        
        JLabel lblNewLabel_6 = new JLabel("Zárolást kezdeményező mérnök");
        lblNewLabel_6.setBounds(770, 261, 184, 14);
        add(lblNewLabel_6);
        
        mernok_mezo = new JTextField();
        mernok_mezo.setBounds(964, 258, 164, 20);
        add(mernok_mezo);
        mernok_mezo.setColumns(10);
        
        JLabel lblNewLabel_7 = new JLabel("Zárolt mennyiség");
        lblNewLabel_7.setBounds(45, 136, 99, 14);
        add(lblNewLabel_7);
        
        zaroltdb_mezo = new JTextField();
        zaroltdb_mezo.setBounds(154, 133, 46, 20);
        add(zaroltdb_mezo);
        zaroltdb_mezo.setColumns(10);
        
        JLabel lblNewLabel_8 = new JLabel("Hol van");
        lblNewLabel_8.setBounds(539, 136, 85, 14);
        add(lblNewLabel_8);
        
        zarolas_helye = new JTextField();
        zarolas_helye.setBounds(596, 133, 184, 20);
        add(zarolas_helye);
        zarolas_helye.setColumns(10);
        
        JLabel lblNewLabel_9 = new JLabel("Zárolás oka");
        lblNewLabel_9.setBounds(42, 211, 74, 14);
        add(lblNewLabel_9);
        
        JLabel lblNewLabel_10 = new JLabel("Azonnali intézkedés");
        lblNewLabel_10.setBounds(388, 211, 113, 14);
        add(lblNewLabel_10);
        
        zarolasoka_mezo = new JTextArea();
        zarolasoka_mezo.setLineWrap(true);
        zarolasoka_mezo.setWrapStyleWord(true);
        zarolasoka_mezo.setBounds(111, 211, 238, 92);
        add(zarolasoka_mezo);
        
        intezkedes_mezo = new JTextArea();
        intezkedes_mezo.setLineWrap(true);
        intezkedes_mezo.setWrapStyleWord(true);
        intezkedes_mezo.setBounds(511, 206, 238, 97);
        add(intezkedes_mezo);
        
        JLabel lblNewLabel_11 = new JLabel("Zárolás dátuma");
        lblNewLabel_11.setBounds(268, 48, 91, 14);
        add(lblNewLabel_11);
        
        datum_mezo = new JTextField();
        datum_mezo.setBounds(362, 45, 86, 20);
        add(datum_mezo);
        datum_mezo.setColumns(10);
        
        JLabel lblNewLabel_12 = new JLabel("Zároló papír sorszáma");
        lblNewLabel_12.setBounds(42, 48, 129, 14);
        add(lblNewLabel_12);
        
        sorszam_mezo = new JTextField();
        sorszam_mezo.setBounds(176, 45, 86, 20);
        add(sorszam_mezo);
        sorszam_mezo.setColumns(10);
        
        zarolta_box = new JComboBox<String>(ellenori_nevsor());                           //ellenori_nevsor()
        zarolta_box.setBounds(690, 84, 200, 22);
        add(zarolta_box);
        
        JLabel lblNewLabel_13 = new JLabel("Zárolta");
        lblNewLabel_13.setBounds(634, 88, 46, 14);
        add(lblNewLabel_13);
        
        JLabel lblNewLabel_14 = new JLabel("Válogatás/ellenőrzés eredménye:");
        lblNewLabel_14.setBounds(42, 388, 198, 14);
        add(lblNewLabel_14);
        
        eredmeny_mezo = new JTextField();
        eredmeny_mezo.setText("0");
        eredmeny_mezo.setBounds(578, 385, 46, 20);
        add(eredmeny_mezo);
        eredmeny_mezo.setColumns(10);
        
        JLabel lblNewLabel_15 = new JLabel("Újraellenőrzés dátuma");
        lblNewLabel_15.setBounds(664, 388, 129, 14);
        add(lblNewLabel_15);
        
        ujradatum_mezo = new JTextField();
        ujradatum_mezo.setBounds(804, 385, 86, 20);
        add(ujradatum_mezo);
        ujradatum_mezo.setColumns(10);
        
        JLabel lblNewLabel_16 = new JLabel("Ellenőrzésre fordított idő");
        lblNewLabel_16.setBounds(911, 388, 148, 14);
        add(lblNewLabel_16);
        
        ido_mezo = new JTextField();
        ido_mezo.setText("0");
        ido_mezo.setBounds(1059, 385, 91, 20);
        add(ido_mezo);
        ido_mezo.setColumns(10);
        
        technikus_csekk = new JCheckBox("Technikusi beavatkozást nem igényel");
        technikus_csekk.setBounds(924, 132, 240, 23);
        add(technikus_csekk);
        
        JLabel lblNewLabel_17 = new JLabel("Felelős terület");
        lblNewLabel_17.setBounds(924, 88, 82, 14);
        add(lblNewLabel_17);
        
        JLabel lblNewLabel_18 = new JLabel("Felelős");
        lblNewLabel_18.setBounds(837, 466, 46, 14);
        add(lblNewLabel_18);
        
        felelos_mezo = new JTextField();
        felelos_mezo.setBounds(908, 463, 251, 20);
        add(felelos_mezo);
        felelos_mezo.setColumns(10);
        
        felelos_mezo2 = new JTextField();
        felelos_mezo2.setBounds(908, 463, 251, 20);
        add(felelos_mezo2);
        felelos_mezo2.setColumns(10);
        felelos_mezo2.setVisible(false);
        
        felelos_mezo3 = new JTextField();
        felelos_mezo3.setBounds(908, 463, 251, 20);
        add(felelos_mezo3);
        felelos_mezo3.setColumns(10);
        felelos_mezo3.setVisible(false);
        
        felelos_mezo4 = new JTextField();
        felelos_mezo4.setBounds(908, 463, 251, 20);
        add(felelos_mezo4);
        felelos_mezo4.setColumns(10);
        felelos_mezo4.setVisible(false);
        
        felelos_mezo5 = new JTextField();
        felelos_mezo5.setBounds(908, 463, 251, 20);
        add(felelos_mezo5);
        felelos_mezo5.setColumns(10);
        felelos_mezo5.setVisible(false);
        
        JLabel lblNewLabel_19 = new JLabel("Hiba gyökéroka");
        lblNewLabel_19.setBounds(42, 466, 102, 14);
        add(lblNewLabel_19);
        
        gyokerok_mezo = new JTextArea();
        gyokerok_mezo.setLineWrap(true);
        gyokerok_mezo.setWrapStyleWord(true);
        gyokerok_mezo.setBounds(160, 461, 240, 115);
        add(gyokerok_mezo);
        
        JLabel lblNewLabel_20 = new JLabel("Gyökérokra hozott intézkedés");
        lblNewLabel_20.setBounds(410, 466, 175, 14);
        add(lblNewLabel_20);
        
        gyokerintezkedes_mezo = new JTextArea();
        gyokerintezkedes_mezo.setLineWrap(true);
        gyokerintezkedes_mezo.setWrapStyleWord(true);
        gyokerintezkedes_mezo.setBounds(579, 461, 248, 115);
        add(gyokerintezkedes_mezo);
        
        gyokerintezkedes2_mezo = new JTextArea();
        gyokerintezkedes2_mezo.setLineWrap(true);
        gyokerintezkedes2_mezo.setWrapStyleWord(true);
        gyokerintezkedes2_mezo.setBounds(579, 461, 248, 115);
        add(gyokerintezkedes2_mezo);
        gyokerintezkedes2_mezo.setVisible(false);
        
        gyokerintezkedes3_mezo = new JTextArea();
        gyokerintezkedes3_mezo.setLineWrap(true);
        gyokerintezkedes3_mezo.setWrapStyleWord(true);
        gyokerintezkedes3_mezo.setBounds(579, 461, 248, 115);
        add(gyokerintezkedes3_mezo);
        gyokerintezkedes3_mezo.setVisible(false);
        
        gyokerintezkedes4_mezo = new JTextArea();
        gyokerintezkedes4_mezo.setLineWrap(true);
        gyokerintezkedes4_mezo.setWrapStyleWord(true);
        gyokerintezkedes4_mezo.setBounds(579, 461, 248, 115);
        add(gyokerintezkedes4_mezo);
        gyokerintezkedes4_mezo.setVisible(false);
        
        gyokerintezkedes5_mezo = new JTextArea();
        gyokerintezkedes5_mezo.setLineWrap(true);
        gyokerintezkedes5_mezo.setWrapStyleWord(true);
        gyokerintezkedes5_mezo.setBounds(579, 461, 248, 115);
        add(gyokerintezkedes5_mezo);
        gyokerintezkedes5_mezo.setVisible(false);
        
        JLabel lblNewLabel_21 = new JLabel("IFS zárolás szükséges-e");
        lblNewLabel_21.setBounds(893, 424, 148, 14);
        add(lblNewLabel_21);
        
        b2_mezo = new JTextField();
        b2_mezo.setBounds(1059, 421, 93, 20);
        add(b2_mezo);
        b2_mezo.setColumns(10);
        
        JButton mentes_gomb = new JButton("Mentés");
        mentes_gomb.addActionListener(new Mentes());
        mentes_gomb.setBounds(539, 627, 89, 23);
        add(mentes_gomb);
        
        JLabel lblNewLabel_22 = new JLabel("Lezárás dátuma");
        lblNewLabel_22.setBounds(484, 681, 101, 14);
        add(lblNewLabel_22);
        
        lezaras_mezo = new JTextField();
        lezaras_mezo.setBounds(596, 678, 86, 20);
        add(lezaras_mezo);
        lezaras_mezo.setColumns(10);
        
        JButton lezaras_gomb = new JButton("Lezárás");
        lezaras_gomb.addActionListener(new Lezaras());
        lezaras_gomb.setBounds(539, 709, 89, 23);
        add(lezaras_gomb);
        
        String[] terulet = {"Gépes", "Kézi","Végszerelés"};
        felelosterulet_box = new JComboBox<String>(terulet);                         //terulet
        felelosterulet_box.setBounds(1028, 84, 124, 22);
        add(felelosterulet_box);
        
        JLabel lblNewLabel_23 = new JLabel("Gyökérokra hozott intézkedés visszaellenőrzése");
        lblNewLabel_23.setBounds(42, 599, 277, 14);
        add(lblNewLabel_23);
        
        visszaellenorzes_mezo = new JTextField();
        visszaellenorzes_mezo.setBounds(329, 596, 572, 20);
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
        meszam_mezo.setBounds(329, 133, 180, 20);
        add(meszam_mezo);
        meszam_mezo.setColumns(10);
        
        JLabel lblNewLabel_24 = new JLabel("Raklap ME szám");
        lblNewLabel_24.setBounds(227, 136, 102, 14);
        add(lblNewLabel_24);
        
        JLabel lblNewLabel_25 = new JLabel("Ellenőrzött mennyiség:");
        lblNewLabel_25.setBounds(271, 388, 129, 14);
        add(lblNewLabel_25);
        
        ellenorzott_mezo = new JTextField();
        ellenorzott_mezo.setText("0");
        ellenorzott_mezo.setBounds(409, 385, 39, 20);
        add(ellenorzott_mezo);
        ellenorzott_mezo.setColumns(10);
        
        JLabel hibas_db = new JLabel("Hibás db");
        hibas_db.setBounds(501, 388, 72, 14);
        add(hibas_db);
        
        JLabel lblNewLabel_26 = new JLabel("Hiba csoport");
        lblNewLabel_26.setBounds(42, 350, 91, 14);
        add(lblNewLabel_26);
        
        String[] hibacsoportok = {"","alkatrész hiba", "alkatrész sérülés", "azonosítás", "beültetési hiba", "csomagolás", "darabolás", "dokumentáció", "Forrasztási hiba", "Forrasztási hiba / Hiányzó jelölés", "Funkcionális", "Hiányzó jelölés", "Jelölés", "kiöntés", "lakkozás", "nyomtatás", "ragasztás", "szennyeződés", "szerelés", "vegyes", "anyagkezelés" };
        hibacsoport_box = new JComboBox<String>(hibacsoportok);                                              //hibacsoportok
        hibacsoport_box.setBounds(159, 342, 192, 22);
        add(hibacsoport_box);
        
        JLabel lblNewLabel_27 = new JLabel("Hiba típus");
        lblNewLabel_27.setBounds(427, 347, 74, 14);
        add(lblNewLabel_27);
        
        String[] hibatipusok = {"","AOI", "címke", "címke tartalom", "deformálódás", "elcsúszott alkatrész", "elektromos hiba", "FCT", "felcserélt kábel", "ferde", "fordított", "furatkitöltöttség", "hiány", "hiányos", "hiányzó", "hiányzó forr", "hiányzó forrasztás", "hiányzó ragasztás", "hideg forr", "hideg forrasztás, óngolyó", "hosszú láb", "Hűtőpaszta pozíció", "kevés ón", "kiborult", "kupak nincs levéve", "levert alkatrész", "maszkolás", "megolvadt alkatrész", "membrán", "méret eltérés", "nem kötött meg az anyag", "nyitott", "ónfelfutás", "óngolyó", "panel", "PCB felület", "QR kód", "ragasztó a paden", "sérülés", "sok ón", "sorjás alkatrész", "szennyezett címke", "termék azonosító keveredés", "Touch-up jelölés hiányzik", "TUP", "zárlat", "zárlat/TUP"};
        hibatipus_box = new JComboBox<String>(hibatipusok);                                                //hibatipusok
        hibatipus_box.setBounds(515, 339, 215, 22);
        add(hibatipus_box);
        
        ido();
        
        setBackground(Foablak.hatter_szine);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(42, 314, 1163, 14);
        add(separator);
        
        JLabel lblNewLabel_28 = new JLabel("Határidő");
        lblNewLabel_28.setBounds(837, 512, 64, 14);
        add(lblNewLabel_28);
        
        hatarido_mezo = new JTextField();
        hatarido_mezo.setBounds(908, 509, 86, 20);
        add(hatarido_mezo);
        hatarido_mezo.setColumns(10);
        
        hatarido2_mezo = new JTextField();
        hatarido2_mezo.setBounds(908, 509, 86, 20);
        add(hatarido2_mezo);
        hatarido2_mezo.setColumns(10);
        hatarido2_mezo.setVisible(false);
        
        hatarido3_mezo = new JTextField();
        hatarido3_mezo.setBounds(908, 509, 86, 20);
        add(hatarido3_mezo);
        hatarido3_mezo.setColumns(10);
        hatarido3_mezo.setVisible(false);
        
        hatarido4_mezo = new JTextField();
        hatarido4_mezo.setBounds(908, 509, 86, 20);
        add(hatarido4_mezo);
        hatarido4_mezo.setColumns(10);
        hatarido4_mezo.setVisible(false);
        
        hatarido5_mezo = new JTextField();
        hatarido5_mezo.setBounds(908, 509, 86, 20);
        add(hatarido5_mezo);
        hatarido5_mezo.setColumns(10);
        hatarido5_mezo.setVisible(false);
        
        String[] szamok = {"1", "2","3","4","5"};
        gyokerokvalaszto_box = new JComboBox<String>(szamok);                         //szamok
        gyokerokvalaszto_box.setBounds(676, 428, 73, 22);
        gyokerokvalaszto_box.addActionListener(new Gyokerok_valaszto());
        add(gyokerokvalaszto_box);
        
        lblNewLabel_29 = new JLabel("Zárolás oka");
        lblNewLabel_29.setBounds(42, 181, 74, 14);
        add(lblNewLabel_29);
        
        String[] okok = {"-", "Hiányzó TUP jelölés"};
        zarolasoka_box = new JComboBox<String>(okok);
        zarolasoka_box.setBounds(126, 178, 274, 22);
        add(zarolasoka_box);


        visszatolt(id);

    }
    
    public void ido()                                                                   //a pontos dátu meghatározására szolgáló függvény
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        Date date = new Date();
        datum_mezo.setText(formatter.format(date));                                        //az aktuális dátumot hozzáadja az időpont mezőhöz
    }
    
    public String[] cikkszamok()
    {
        String[] cikkbox = null;
        try
        {            
            Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            //DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
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
            hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu",this.getClass().getSimpleName()+" "+ hibauzenet);
            JOptionPane.showMessageDialog(null, getClass()+" "+ hibauzenet, "Hiba üzenet", 2);                                               //kiírja a hibaüzenetet
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
        catch (Exception e) 
        {
           e.printStackTrace();
           String hibauzenet = e.toString();
           Email hibakuldes = new Email();
           hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu",this.getClass().getSimpleName()+" "+ hibauzenet);
           JOptionPane.showMessageDialog(null, getClass()+" "+ hibauzenet, "Hiba üzenet", 2);                                                 //kiírja a hibaüzenetet
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
              hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu",this.getClass().getSimpleName()+" "+ hibauzenet);
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
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu",this.getClass().getSimpleName()+" "+ hibauzenet);
                JOptionPane.showMessageDialog(null, getClass()+" "+ hibauzenet, "Hiba üzenet", 2);                                                   //kivétel esetén kiírja a hibaüzenetet
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
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu",this.getClass().getSimpleName()+" "+ hibauzenet);
                JOptionPane.showMessageDialog(null, getClass()+" "+ hibauzenet, "Hiba üzenet", 2);                                                    //kivétel esetén kiírja a hibaüzenetet
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
                
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = (Connection) DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
                stmt = (Statement) conn.createStatement();
                String SQL = "select * from qualitydb.Zarolasok where id = '"+ id_mezo.getText() +"'";
                ResultSet rs = stmt.executeQuery(SQL);
                String sql = "";
                String technikus = "Igen";
                
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
                    String intezkedess = gyokerintezkedes_mezo.getText() +";"+gyokerintezkedes2_mezo.getText() +";"+gyokerintezkedes3_mezo.getText() +";"+gyokerintezkedes4_mezo.getText() +";"+gyokerintezkedes5_mezo.getText();
                    String feleloss = felelos_mezo.getText()+";"+felelos_mezo2.getText()+";"+felelos_mezo3.getText()+";"+felelos_mezo4.getText()+";"+felelos_mezo5.getText();
                    String hataridoo = hatarido_mezo.getText()+";"+hatarido2_mezo.getText()+";"+hatarido3_mezo.getText()+";"+hatarido4_mezo.getText()+";"+hatarido5_mezo.getText();
                    
                    String zarolasoka = zarolasoka_mezo.getText() +";"+ String.valueOf(zarolasoka_box.getSelectedItem());
                    String email_hibaok = "";
                    
                    if(String.valueOf(zarolasoka_box.getSelectedItem()).equals("-"))
                    {
                        email_hibaok = zarolasoka_mezo.getText();
                    }
                    else
                    {
                        email_hibaok = String.valueOf(zarolasoka_box.getSelectedItem()) +" "+zarolasoka_mezo.getText();
                    }
                    
                    if(Integer.valueOf(ellenorzott_mezo.getText()) != 0)
                    {
                        if(ellenorzott_mezo.getText().equals(zaroltdb_mezo.getText()))
                        {
                            System.out.println("egyezik");
                            if(rs.next())
                            {
                                if(technikus_csekk.isSelected())
                                {
                                    technikus = "Nem";                                    
                                }
                                sql = "update qualitydb.Zarolasok set  Projekt ='"+ String.valueOf(projekt_box.getSelectedItem()) +"',Tipus = '"+ String.valueOf(tipus_box.getSelectedItem()) +"',"
                                        + "Eszleles_helye ='"+ eszleleshelye_mezo.getText() +"',"
                                        + "muszak ='"+ muszak_mezo.getText() +"',Zarolo_mernok = '"+ mernok_mezo.getText() +"',Zarolt_db ='"+Integer.parseInt(zaroltdb_mezo.getText())+"',Hol_van ='"+ zarolas_helye.getText() +"',"
                                        + "Zarolas_oka ='"+ zarolasoka +"',Azonnali_intezkedes ='"+ intezkedes_mezo.getText() +"',Zarolas_datuma ='"+ datum_mezo.getText() +"',Papir_sorszama ='"+ sorszam_mezo.getText() +"',"
                                        + "Zarolta ='"+ String.valueOf(zarolta_box.getSelectedItem()) +"',"
                                        + "Valogatas_eredmenye ='"+ eredmeny_mezo.getText() +"',Ujraellenorzes_datuma ='"+ ujradatum_mezo.getText() +"',Ellenorzes_ido ='"+ ido_mezo.getText() +"',Technikusi_beavatkozas ='"+ technikus +"',"
                                        + "Felelos_terulet ='"+ String.valueOf(felelosterulet_box.getSelectedItem()) +"',"
                                        + "Felelos ='"+ feleloss +"',Hiba_gyokeroka ='"+ gyokerok_mezo.getText() +"',Gyokerok_intezkedes ='"+ intezkedess +"',"
                                        + "B2_zarolas ='"+ b2_mezo.getText() +"',visszaellenorzes ='"+ visszaellenorzes_mezo.getText() +"',ME_szam ='"+ meszam_mezo.getText() +"' ,"
                                        + "Ellenorzott_db ='"+ ellenorzott_mezo.getText() +"',Hibacsoport ='"+ String.valueOf(hibacsoport_box.getSelectedItem()) +"',Hibatipus ='"+String.valueOf(hibatipus_box.getSelectedItem()) +"' "
                                                + ", Hatarido = '"+ hataridoo +"' where id = '"+ id_mezo.getText() +"'";
                                
                            }
                            else
                            {
                                if(technikus_csekk.isSelected())
                                {
                                    technikus = "Nem";                                                                              
                                }
                                else
                                {
                                    technikus = "Igen";
                                    Email uzenet = new Email();
                                    if(felelosterulet_box.getSelectedItem().equals("Gépes"))
                                    {
                                        uzenet.zarolas_email("easqas@veas.videoton.hu", "ternak.sandor@veas.videoton.hu, kadar.zoltan@veas.videoton.hu, tatai.mihaly@veas.videoton.hu", String.valueOf(projekt_box.getSelectedItem()), String.valueOf(tipus_box.getSelectedItem()),
                                                zaroltdb_mezo.getText(), email_hibaok, String.valueOf(zarolta_box.getSelectedItem()), datum_mezo.getText(), muszak_mezo.getText(),"Zárolás - "+ String.valueOf(tipus_box.getSelectedItem())+" - "+
                                                zarolasoka_mezo.getText() +" - "+ sorszam_mezo.getText(), meszam_mezo.getText()) ;
                                    }
                                    else if(felelosterulet_box.getSelectedItem().equals("Kézi"))
                                    {
                                        uzenet.zarolas_email("easqas@veas.videoton.hu", "nagy.balint@veas.videoton.hu, molnar.jozsef@veas.videoton.hu,csader.zsolt@veas.videoton.hu, tatai.mihaly@veas.videoton.hu", String.valueOf(projekt_box.getSelectedItem()), String.valueOf(tipus_box.getSelectedItem()),
                                                zaroltdb_mezo.getText(), email_hibaok, String.valueOf(zarolta_box.getSelectedItem()), datum_mezo.getText(), muszak_mezo.getText(),"Zárolás - "+ String.valueOf(tipus_box.getSelectedItem())+" - "+
                                                        zarolasoka_mezo.getText() +" - "+ sorszam_mezo.getText(), meszam_mezo.getText());
                                    }
                                    else
                                    {
                                        uzenet.zarolas_email("easqas@veas.videoton.hu", "babud.imre@veas.videoton.hu, meszaros.hajnalka@veas.videoton.hu, serebrianska.kateryna@veas.videoton.hu, tatai.mihaly@veas.videoton.hu, fekete.mercedesz@veas.videoton.hu", String.valueOf(projekt_box.getSelectedItem()), String.valueOf(tipus_box.getSelectedItem()),
                                                zaroltdb_mezo.getText(), email_hibaok, String.valueOf(zarolta_box.getSelectedItem()), datum_mezo.getText(), muszak_mezo.getText(),"Zárolás - "+ String.valueOf(tipus_box.getSelectedItem())+" - "+
                                                        zarolasoka_mezo.getText() +" - "+ sorszam_mezo.getText(), meszam_mezo.getText());
                                    }   
                                }
                                sql = "insert into qualitydb.Zarolasok (ID,Projekt,Tipus,Eszleles_helye,Muszak,Zarolo_mernok,Zarolt_db,Hol_van,Zarolas_oka,Azonnali_intezkedes,Zarolas_datuma,"
                                        + "Papir_sorszama,Zarolta,Valogatas_eredmenye,Ujraellenorzes_datuma,\r\n"
                                        + "Ellenorzes_ido,Technikusi_beavatkozas,Felelos_terulet,Felelos,Hiba_gyokeroka,Gyokerok_intezkedes,B2_zarolas,visszaellenorzes,ME_szam, Ellenorzott_db,Hibacsoport,Hibatipus) "
                                        + "Values('"+ id_mezo.getText()+"','"+ String.valueOf(projekt_box.getSelectedItem()) +"','"+ String.valueOf(tipus_box.getSelectedItem()) +"',"
                                        + "'"+ eszleleshelye_mezo.getText() +"','"+ muszak_mezo.getText() +"','"+ mernok_mezo.getText() +"','"+Integer.parseInt(zaroltdb_mezo.getText())+"','"+ zarolas_helye.getText() +"',"
                                        + "'"+ zarolasoka +"','"+ intezkedes_mezo.getText() +"','"+ datum_mezo.getText() +"','"+ sorszam_mezo.getText() +"','"+ String.valueOf(zarolta_box.getSelectedItem()) +"',"
                                        + "'"+ eredmeny_mezo.getText() +"','"+ ujradatum_mezo.getText() +"','"+ ido_mezo.getText() +"','"+ technikus +"','"+ String.valueOf(felelosterulet_box.getSelectedItem()) +"',"
                                        + "'"+ feleloss +"','"+ gyokerok_mezo.getText() +"','"+ intezkedess +"','"+ b2_mezo.getText() +"','"+ visszaellenorzes_mezo.getText() +"'"
                                                + ",'"+ meszam_mezo.getText() +"','"+ ellenorzott_mezo.getText() +"','"+ String.valueOf(hibacsoport_box.getSelectedItem()) +"','"+ String.valueOf(hibatipus_box.getSelectedItem()) +"')";
                                
                            }
                            
                            if(intezkedes_mezo.getText().equals("")) {}
                            else
                            {
                                String sql2 = "select Email from qualitydb.Zarolasok where ID = '"+ id_mezo.getText() +"'";
                                if(lekerdezes.tombvissza_sajat(sql2).length > 0)
                                if(lekerdezes.tombvissza_sajat(sql2)[0].equals("nem"))
                                {
                                     
                                    if(felelosterulet_box.getSelectedItem().equals("Gépes"))
                                    {
                                        cimzettek = gepes;
                                    }
                                    else if(felelosterulet_box.getSelectedItem().equals("Kézi"))
                                    {
                                        cimzettek = kezi;
                                    }
                                    else
                                    {
                                        cimzettek = vegszereles;
                                    }     
                                    String uzenet = "Sziasztok! \n \n"
                                            + "Zárolva lett az alábbi tétel: \n"
                                            + "Projekt: "+ String.valueOf(projekt_box.getSelectedItem())+ "\n"
                                            + "Cikkszám: "+ String.valueOf(tipus_box.getSelectedItem()) + "\n"
                                            + "Zárolt db: "+ zaroltdb_mezo.getText() +"\n"
                                            + "Zárolás oka: "+ email_hibaok +"\n"
                                            + "Zárolta: "+ String.valueOf(zarolta_box.getSelectedItem()) +"\n"
                                            + "Dátum: "+ datum_mezo.getText() +"\n"
                                            + "Műszak: "+ muszak_mezo.getText()+"\n"
                                            + "Raklap ME száma: "+ meszam_mezo.getText()+"\n\n"
                                            + "A zárolásra hozott azonnali intézkedés: "+ intezkedes_mezo.getText()  +"\n\nÜdvözlettel: EASQAS program";
                                    Email email = new Email();
                                    email.mindenes_email("easqas@veas.videoton.hu", cimzettek, cc, "Zárolásra hozott intézkedés. Zárolos papír sorszáma: "+ sorszam_mezo.getText(), uzenet);
                                    sql2 = "update qualitydb.Zarolasok set Email = 'igen' where ID = '"+ id_mezo.getText() +"'";
                                    lekerdezes.mindenes(sql2);
                                }
                            }
                            
                            if(technikus.equals("Igen"))
                            {
                                if(gyokerintezkedes_mezo.getText().equals("")) {}
                                else
                                {
                                    String sql2 = "select Email2 from qualitydb.Zarolasok where ID = '"+ id_mezo.getText() +"'";
                                    if(lekerdezes.tombvissza_sajat(sql2).length > 0)
                                    if(lekerdezes.tombvissza_sajat(sql2)[0].equals("nem"))
                                    {
                                        if(felelosterulet_box.getSelectedItem().equals("Gépes"))
                                        {
                                            cimzettek = gepes;
                                        }
                                        else if(felelosterulet_box.getSelectedItem().equals("Kézi"))
                                        {
                                            cimzettek = kezi;
                                        }
                                        else
                                        {
                                            cimzettek = vegszereles;
                                        }     
                                        String uzenet = "Sziasztok! \n \n"
                                                + "Zárolva lett az alábbi tétel: \n"
                                                + "Projekt: "+ String.valueOf(projekt_box.getSelectedItem())+ "\n"
                                                + "Cikkszám: "+ String.valueOf(tipus_box.getSelectedItem()) + "\n"
                                                + "Zárolt db: "+ zaroltdb_mezo.getText() +"\n"
                                                + "Zárolás oka: "+ email_hibaok +"\n"
                                                + "Zárolta: "+ String.valueOf(zarolta_box.getSelectedItem()) +"\n"
                                                + "Dátum: "+ datum_mezo.getText() +"\n"
                                                + "Műszak: "+ muszak_mezo.getText()+"\n"
                                                + "Raklap ME száma: "+ meszam_mezo.getText()+"\n\n"
                                                + "A zárolásra hozott azonnali intézkedés: "+ intezkedes_mezo.getText()  +"\t\n"
                                                + "Gyökérokra hozott intézkedés: "+ gyokerintezkedes_mezo.getText() +"\n\nÜdvözlettel: EASQAS program";
                                        Email email = new Email();
                                        email.mindenes_email("easqas@veas.videoton.hu", cimzettek, cc, "Zárolásra hozott intézkedés. Zárolos papír sorszáma: "+ sorszam_mezo.getText(), uzenet);
                                        sql2 = "update qualitydb.Zarolasok set Email2 = 'igen' where ID = '"+ id_mezo.getText() +"'";
                                        lekerdezes.mindenes(sql2);
                                    }
                                }
                                if(gyokerintezkedes2_mezo.getText().equals("")) {}
                                else
                                {
                                    String sql2 = "select Email3 from qualitydb.Zarolasok where ID = '"+ id_mezo.getText() +"'";
                                    if(lekerdezes.tombvissza_sajat(sql2).length > 0)
                                    if(lekerdezes.tombvissza_sajat(sql2)[0].equals("nem"))
                                    {
                                        if(felelosterulet_box.getSelectedItem().equals("Gépes"))
                                        {
                                            cimzettek = gepes;
                                        }
                                        else if(felelosterulet_box.getSelectedItem().equals("Kézi"))
                                        {
                                            cimzettek = kezi;
                                        }
                                        else
                                        {
                                            cimzettek = vegszereles;
                                        }     
                                        String uzenet = "Sziasztok! \n \n"
                                                + "Zárolva lett az alábbi tétel: \n"
                                                + "Projekt: "+ String.valueOf(projekt_box.getSelectedItem())+ "\n"
                                                + "Cikkszám: "+ String.valueOf(tipus_box.getSelectedItem()) + "\n"
                                                + "Zárolt db: "+ zaroltdb_mezo.getText() +"\n"
                                                + "Zárolás oka: "+ email_hibaok +"\n"
                                                + "Zárolta: "+ String.valueOf(zarolta_box.getSelectedItem()) +"\n"
                                                + "Dátum: "+ datum_mezo.getText() +"\n"
                                                + "Műszak: "+ muszak_mezo.getText()+"\n"
                                                + "Raklap ME száma: "+ meszam_mezo.getText()+"\n\n"
                                                + "A zárolásra hozott azonnali intézkedés: "+ intezkedes_mezo.getText()  +"\t\n"
                                                + "Gyökérokra hozott 2. intézkedés: "+ gyokerintezkedes2_mezo.getText() +"\n\nÜdvözlettel: EASQAS program";
                                        Email email = new Email();
                                        email.mindenes_email("easqas@veas.videoton.hu", cimzettek, cc, "Zárolásra hozott intézkedés. Zárolos papír sorszáma: "+ sorszam_mezo.getText(), uzenet);
                                        sql2 = "update qualitydb.Zarolasok set Email3 = 'igen' where ID = '"+ id_mezo.getText() +"'";
                                        lekerdezes.mindenes(sql2);
                                    }
                                    
                                }
                                if(gyokerintezkedes3_mezo.getText().equals("")) {}
                                else
                                {
                                    String sql2 = "select Email4 from qualitydb.Zarolasok where ID = '"+ id_mezo.getText() +"'";
                                    if(lekerdezes.tombvissza_sajat(sql2).length > 0)
                                    if(lekerdezes.tombvissza_sajat(sql2)[0].equals("nem"))
                                    {
                                        if(felelosterulet_box.getSelectedItem().equals("Gépes"))
                                        {
                                            cimzettek = gepes;
                                        }
                                        else if(felelosterulet_box.getSelectedItem().equals("Kézi"))
                                        {
                                            cimzettek = kezi;
                                        }
                                        else
                                        {
                                            cimzettek = vegszereles;
                                        }     
                                        String uzenet = "Sziasztok! \n \n"
                                                + "Zárolva lett az alábbi tétel: \n"
                                                + "Projekt: "+ String.valueOf(projekt_box.getSelectedItem())+ "\n"
                                                + "Cikkszám: "+ String.valueOf(tipus_box.getSelectedItem()) + "\n"
                                                + "Zárolt db: "+ zaroltdb_mezo.getText() +"\n"
                                                + "Zárolás oka: "+ email_hibaok +"\n"
                                                + "Zárolta: "+ String.valueOf(zarolta_box.getSelectedItem()) +"\n"
                                                + "Dátum: "+ datum_mezo.getText() +"\n"
                                                + "Műszak: "+ muszak_mezo.getText()+"\n"
                                                + "Raklap ME száma: "+ meszam_mezo.getText()+"\n\n"
                                                + "A zárolásra hozott azonnali intézkedés: "+ intezkedes_mezo.getText()  +"\t\n"
                                                + "Gyökérokra hozott 3. intézkedés: "+ gyokerintezkedes3_mezo.getText() +"\n\nÜdvözlettel: EASQAS program";
                                        Email email = new Email();
                                        email.mindenes_email("easqas@veas.videoton.hu", cimzettek, cc, "Zárolásra hozott intézkedés. Zárolos papír sorszáma: "+ sorszam_mezo.getText(), uzenet);
                                        sql2 = "update qualitydb.Zarolasok set Email4 = 'igen' where ID = '"+ id_mezo.getText() +"'";
                                        lekerdezes.mindenes(sql2);
                                    }
                                }
                                if(gyokerintezkedes4_mezo.getText().equals("")) {}
                                else
                                {
                                    String sql2 = "select Email5 from qualitydb.Zarolasok where ID = '"+ id_mezo.getText() +"'";
                                    if(lekerdezes.tombvissza_sajat(sql2).length > 0)
                                    if(lekerdezes.tombvissza_sajat(sql2)[0].equals("nem"))
                                    {
                                        if(felelosterulet_box.getSelectedItem().equals("Gépes"))
                                        {
                                            cimzettek = gepes;
                                        }
                                        else if(felelosterulet_box.getSelectedItem().equals("Kézi"))
                                        {
                                            cimzettek = kezi;
                                        }
                                        else
                                        {
                                            cimzettek = vegszereles;
                                        }     
                                        String uzenet = "Sziasztok! \n \n"
                                                + "Zárolva lett az alábbi tétel: \n"
                                                + "Projekt: "+ String.valueOf(projekt_box.getSelectedItem())+ "\n"
                                                + "Cikkszám: "+ String.valueOf(tipus_box.getSelectedItem()) + "\n"
                                                + "Zárolt db: "+ zaroltdb_mezo.getText() +"\n"
                                                + "Zárolás oka: "+ email_hibaok +"\n"
                                                + "Zárolta: "+ String.valueOf(zarolta_box.getSelectedItem()) +"\n"
                                                + "Dátum: "+ datum_mezo.getText() +"\n"
                                                + "Műszak: "+ muszak_mezo.getText()+"\n"
                                                + "Raklap ME száma: "+ meszam_mezo.getText()+"\n\n"
                                                + "A zárolásra hozott azonnali intézkedés: "+ intezkedes_mezo.getText()  +"\t\n"
                                                + "Gyökérokra hozott 4. intézkedés: "+ gyokerintezkedes4_mezo.getText() +"\n\nÜdvözlettel: EASQAS program";
                                        Email email = new Email();
                                        email.mindenes_email("easqas@veas.videoton.hu", cimzettek, cc, "Zárolásra hozott intézkedés. Zárolos papír sorszáma: "+ sorszam_mezo.getText(), uzenet);
                                        sql2 = "update qualitydb.Zarolasok set Email5 = 'igen' where ID = '"+ id_mezo.getText() +"'";
                                        lekerdezes.mindenes(sql2);
                                    }
                                }
                                if(gyokerintezkedes5_mezo.getText().equals("")) {}
                                else
                                {
                                    String sql2 = "select Email6 from qualitydb.Zarolasok where ID = '"+ id_mezo.getText() +"'";
                                    if(lekerdezes.tombvissza_sajat(sql2).length > 0)
                                    if(lekerdezes.tombvissza_sajat(sql2)[0].equals("nem"))
                                    {
                                        if(felelosterulet_box.getSelectedItem().equals("Gépes"))
                                        {
                                            cimzettek = gepes;
                                        }
                                        else if(felelosterulet_box.getSelectedItem().equals("Kézi"))
                                        {
                                            cimzettek = kezi;
                                        }
                                        else
                                        {
                                            cimzettek = vegszereles;
                                        }     
                                        String uzenet = "Sziasztok! \n \n"
                                                + "Zárolva lett az alábbi tétel: \n"
                                                + "Projekt: "+ String.valueOf(projekt_box.getSelectedItem())+ "\n"
                                                + "Cikkszám: "+ String.valueOf(tipus_box.getSelectedItem()) + "\n"
                                                + "Zárolt db: "+ zaroltdb_mezo.getText() +"\n"
                                                + "Zárolás oka: "+ email_hibaok +"\n"
                                                + "Zárolta: "+ String.valueOf(zarolta_box.getSelectedItem()) +"\n"
                                                + "Dátum: "+ datum_mezo.getText() +"\n"
                                                + "Műszak: "+ muszak_mezo.getText()+"\n"
                                                + "Raklap ME száma: "+ meszam_mezo.getText()+"\n\n"
                                                + "A zárolásra hozott azonnali intézkedés: "+ intezkedes_mezo.getText()  +"\t\n"
                                                + "Gyökérokra hozott 5. intézkedés: "+ gyokerintezkedes5_mezo.getText() +"\n\nÜdvözlettel: EASQAS program";
                                        Email email = new Email();
                                        email.mindenes_email("easqas@veas.videoton.hu", cimzettek, cc, "Zárolásra hozott intézkedés. Zárolos papír sorszáma: "+ sorszam_mezo.getText(), uzenet);
                                        sql2 = "update qualitydb.Zarolasok set Email6 = 'igen' where ID = '"+ id_mezo.getText() +"'";
                                        lekerdezes.mindenes(sql2);
                                    }
                                }
                            }
                            
                            if(lekerdezes.mindenes2(sql) == 0)
                            {
                                visszaallit();
                            }
                                                       
                            int kovetkezo = Integer.parseInt(id_mezo.getText());
                            id_mezo.setText(String.valueOf(kovetkezo + 1));
                        }
                        else
                        {                           
                            if(rs.next())
                            {
                                if(technikus_csekk.isSelected())
                                {
                                    technikus = "Nem";                                    
                                }
                                sql = "update qualitydb.Zarolasok set  Projekt ='"+ String.valueOf(projekt_box.getSelectedItem()) +"',Tipus = '"+ String.valueOf(tipus_box.getSelectedItem()) +"',"
                                        + "Eszleles_helye ='"+ eszleleshelye_mezo.getText() +"',"
                                        + "muszak ='"+ muszak_mezo.getText() +"',Zarolo_mernok = '"+ mernok_mezo.getText() +"',Zarolt_db ='"+Integer.parseInt(zaroltdb_mezo.getText())+"',Hol_van ='"+ zarolas_helye.getText() +"',"
                                        + "Zarolas_oka ='"+ zarolasoka +"',Azonnali_intezkedes ='"+ intezkedes_mezo.getText() +"',Zarolas_datuma ='"+ datum_mezo.getText() +"',Papir_sorszama ='"+ sorszam_mezo.getText() +"',"
                                        + "Zarolta ='"+ String.valueOf(zarolta_box.getSelectedItem()) +"',"
                                        + "Valogatas_eredmenye ='"+ eredmeny_mezo.getText() +"',Ujraellenorzes_datuma ='"+ ujradatum_mezo.getText() +"',Ellenorzes_ido ='"+ ido_mezo.getText() +"',Technikusi_beavatkozas ='"+ technikus +"',"
                                        + "Felelos_terulet ='"+ String.valueOf(felelosterulet_box.getSelectedItem()) +"',"
                                        + "Felelos ='"+ feleloss +"',Hiba_gyokeroka ='"+ gyokerok_mezo.getText() +"',Gyokerok_intezkedes ='"+ intezkedess +"',"
                                        + "B2_zarolas ='"+ b2_mezo.getText() +"',visszaellenorzes ='"+ visszaellenorzes_mezo.getText() +"',ME_szam ='"+ meszam_mezo.getText() +"' ,"
                                        + "Ellenorzott_db ='"+ ellenorzott_mezo.getText() +"',Hibacsoport ='"+ String.valueOf(hibacsoport_box.getSelectedItem()) +"',Hibatipus ='"+String.valueOf(hibatipus_box.getSelectedItem()) +"' "
                                                + ", Hatarido = '"+ hataridoo +"' where id = '"+ id_mezo.getText() +"'";
                                
                            }
                            else
                            {
                                if(technikus_csekk.isSelected())
                                {
                                    technikus = "Nem";                                                                              
                                }
                                else
                                {
                                    technikus = "Igen";
                                    Email uzenet = new Email();
                                    if(felelosterulet_box.getSelectedItem().equals("Gépes"))
                                    {
                                        uzenet.zarolas_email("easqas@veas.videoton.hu", "ternak.sandor@veas.videoton.hu, kadar.zoltan@veas.videoton.hu, tatai.mihaly@veas.videoton.hu", String.valueOf(projekt_box.getSelectedItem()), String.valueOf(tipus_box.getSelectedItem()),
                                                zaroltdb_mezo.getText(), email_hibaok, String.valueOf(zarolta_box.getSelectedItem()), datum_mezo.getText(), muszak_mezo.getText(),"Zárolás - "+ String.valueOf(tipus_box.getSelectedItem())+" - "+
                                                zarolasoka_mezo.getText() +" - "+ sorszam_mezo.getText(), meszam_mezo.getText()) ;
                                    }
                                    else if(felelosterulet_box.getSelectedItem().equals("Kézi"))
                                    {
                                        uzenet.zarolas_email("easqas@veas.videoton.hu", "nagy.balint@veas.videoton.hu, molnar.jozsef@veas.videoton.hu,csader.zsolt@veas.videoton.hu, tatai.mihaly@veas.videoton.hu", String.valueOf(projekt_box.getSelectedItem()), String.valueOf(tipus_box.getSelectedItem()),
                                                zaroltdb_mezo.getText(), email_hibaok, String.valueOf(zarolta_box.getSelectedItem()), datum_mezo.getText(), muszak_mezo.getText(),"Zárolás - "+ String.valueOf(tipus_box.getSelectedItem())+" - "+
                                                        zarolasoka_mezo.getText() +" - "+ sorszam_mezo.getText(), meszam_mezo.getText());
                                    }
                                    else
                                    {
                                        uzenet.zarolas_email("easqas@veas.videoton.hu", "babud.imre@veas.videoton.hu, meszaros.hajnalka@veas.videoton.hu, serebrianska.kateryna@veas.videoton.hu, tatai.mihaly@veas.videoton.hu, fekete.mercedesz@veas.videoton.hu", String.valueOf(projekt_box.getSelectedItem()), String.valueOf(tipus_box.getSelectedItem()),
                                                zaroltdb_mezo.getText(), email_hibaok, String.valueOf(zarolta_box.getSelectedItem()), datum_mezo.getText(), muszak_mezo.getText(),"Zárolás - "+ String.valueOf(tipus_box.getSelectedItem())+" - "+
                                                        zarolasoka_mezo.getText() +" - "+ sorszam_mezo.getText(), meszam_mezo.getText());
                                    }
                                }
                                sql = "insert into qualitydb.Zarolasok (ID,Projekt,Tipus,Eszleles_helye,Muszak,Zarolo_mernok,Zarolt_db,Hol_van,Zarolas_oka,Azonnali_intezkedes,Zarolas_datuma,"
                                        + "Papir_sorszama,Zarolta,Valogatas_eredmenye,Ujraellenorzes_datuma,\r\n"
                                        + "Ellenorzes_ido,Technikusi_beavatkozas,Felelos_terulet,Felelos,Hiba_gyokeroka,Gyokerok_intezkedes,B2_zarolas,visszaellenorzes,ME_szam, Ellenorzott_db,Hibacsoport,Hibatipus) "
                                        + "Values('"+ id_mezo.getText()+"','"+ String.valueOf(projekt_box.getSelectedItem()) +"','"+ String.valueOf(tipus_box.getSelectedItem()) +"',"
                                        + "'"+ eszleleshelye_mezo.getText() +"','"+ muszak_mezo.getText() +"','"+ mernok_mezo.getText() +"','"+Integer.parseInt(zaroltdb_mezo.getText())+"','"+ zarolas_helye.getText() +"',"
                                        + "'"+ zarolasoka +"','"+ intezkedes_mezo.getText() +"','"+ datum_mezo.getText() +"','"+ sorszam_mezo.getText() +"','"+ String.valueOf(zarolta_box.getSelectedItem()) +"',"
                                        + "'"+ eredmeny_mezo.getText() +"','"+ ujradatum_mezo.getText() +"','"+ ido_mezo.getText() +"','"+ technikus +"','"+ String.valueOf(felelosterulet_box.getSelectedItem()) +"',"
                                        + "'"+ felelos_mezo.getText() +"','"+ gyokerok_mezo.getText() +"','"+ gyokerintezkedes_mezo.getText() +"','"+ b2_mezo.getText() +"','"+ visszaellenorzes_mezo.getText() +"'"
                                                + ",'"+ meszam_mezo.getText() +"','"+ ellenorzott_mezo.getText() +"','"+ String.valueOf(hibacsoport_box.getSelectedItem()) +"','"+ String.valueOf(hibatipus_box.getSelectedItem()) +"')";
                                
                            }
                            
                            if(intezkedes_mezo.getText().equals("")) {}
                            else
                            {
                                String sql2 = "select Email from qualitydb.Zarolasok where ID = '"+ id_mezo.getText() +"'";
                                if(lekerdezes.tombvissza_sajat(sql2).length > 0)
                                if(lekerdezes.tombvissza_sajat(sql2)[0].equals("nem"))
                                {
                                    if(felelosterulet_box.getSelectedItem().equals("Gépes"))
                                    {
                                        cimzettek = gepes;
                                    }
                                    else if(felelosterulet_box.getSelectedItem().equals("Kézi"))
                                    {
                                        cimzettek = kezi;
                                    }
                                    else
                                    {
                                        cimzettek = vegszereles;
                                    }     
                                    String uzenet = "Sziasztok! \n \n"
                                            + "Zárolva lett az alábbi tétel: \n"
                                            + "Projekt: "+ String.valueOf(projekt_box.getSelectedItem())+ "\n"
                                            + "Cikkszám: "+ String.valueOf(tipus_box.getSelectedItem()) + "\n"
                                            + "Zárolt db: "+ zaroltdb_mezo.getText() +"\n"
                                            + "Zárolás oka: "+ email_hibaok +"\n"
                                            + "Zárolta: "+ String.valueOf(zarolta_box.getSelectedItem()) +"\n"
                                            + "Dátum: "+ datum_mezo.getText() +"\n"
                                            + "Műszak: "+ muszak_mezo.getText()+"\n"
                                            + "Raklap ME száma: "+ meszam_mezo.getText()+"\n\n"
                                            + "A zárolásra hozott azonnali intézkedés: "+ intezkedes_mezo.getText()  +"\n\nÜdvözlettel: EASQAS program";
                                    Email email = new Email();
                                    email.mindenes_email("easqas@veas.videoton.hu", cimzettek, cc, "Zárolásra hozott intézkedés. Zárolos papír sorszáma: "+ sorszam_mezo.getText(), uzenet);
                                    sql2 = "update qualitydb.Zarolasok set Email = 'igen' where ID = '"+ id_mezo.getText() +"'";
                                    lekerdezes.mindenes(sql2);
                                }
                            }
                            
                            if(technikus.equals("Igen"))
                            {
                                if(gyokerintezkedes_mezo.getText().equals("")) {}
                                else
                                {
                                    String sql2 = "select Email2 from qualitydb.Zarolasok where ID = '"+ id_mezo.getText() +"'";
                                    if(lekerdezes.tombvissza_sajat(sql2).length > 0)
                                    if(lekerdezes.tombvissza_sajat(sql2)[0].equals("nem"))
                                    {
                                        if(felelosterulet_box.getSelectedItem().equals("Gépes"))
                                        {
                                            cimzettek = gepes;
                                        }
                                        else if(felelosterulet_box.getSelectedItem().equals("Kézi"))
                                        {
                                            cimzettek = kezi;
                                        }
                                        else
                                        {
                                            cimzettek = vegszereles;
                                        }     
                                        String uzenet = "Sziasztok! \n \n"
                                                + "Zárolva lett az alábbi tétel: \n"
                                                + "Projekt: "+ String.valueOf(projekt_box.getSelectedItem())+ "\n"
                                                + "Cikkszám: "+ String.valueOf(tipus_box.getSelectedItem()) + "\n"
                                                + "Zárolt db: "+ zaroltdb_mezo.getText() +"\n"
                                                + "Zárolás oka: "+ email_hibaok +"\n"
                                                + "Zárolta: "+ String.valueOf(zarolta_box.getSelectedItem()) +"\n"
                                                + "Dátum: "+ datum_mezo.getText() +"\n"
                                                + "Műszak: "+ muszak_mezo.getText()+"\n"
                                                + "Raklap ME száma: "+ meszam_mezo.getText()+"\n\n"
                                                + "A zárolásra hozott azonnali intézkedés: "+ intezkedes_mezo.getText()  +"\t\n"
                                                + "Gyökérokra hozott intézkedés: "+ gyokerintezkedes_mezo.getText() +"\n\nÜdvözlettel: EASQAS program";
                                        Email email = new Email();
                                        email.mindenes_email("easqas@veas.videoton.hu", cimzettek, cc, "Zárolásra hozott intézkedés. Zárolos papír sorszáma: "+ sorszam_mezo.getText(), uzenet);
                                        sql2 = "update qualitydb.Zarolasok set Email2 = 'igen' where ID = '"+ id_mezo.getText() +"'";
                                        lekerdezes.mindenes(sql2);
                                    }
                                }
                                if(gyokerintezkedes2_mezo.getText().equals("")) {}
                                else
                                {
                                    String sql2 = "select Email3 from qualitydb.Zarolasok where ID = '"+ id_mezo.getText() +"'";
                                    if(lekerdezes.tombvissza_sajat(sql2).length > 0)
                                    if(lekerdezes.tombvissza_sajat(sql2)[0].equals("nem"))
                                    {
                                        if(felelosterulet_box.getSelectedItem().equals("Gépes"))
                                        {
                                            cimzettek = gepes;
                                        }
                                        else if(felelosterulet_box.getSelectedItem().equals("Kézi"))
                                        {
                                            cimzettek = kezi;
                                        }
                                        else
                                        {
                                            cimzettek = vegszereles;
                                        }     
                                        String uzenet = "Sziasztok! \n \n"
                                                + "Zárolva lett az alábbi tétel: \n"
                                                + "Projekt: "+ String.valueOf(projekt_box.getSelectedItem())+ "\n"
                                                + "Cikkszám: "+ String.valueOf(tipus_box.getSelectedItem()) + "\n"
                                                + "Zárolt db: "+ zaroltdb_mezo.getText() +"\n"
                                                + "Zárolás oka: "+ email_hibaok +"\n"
                                                + "Zárolta: "+ String.valueOf(zarolta_box.getSelectedItem()) +"\n"
                                                + "Dátum: "+ datum_mezo.getText() +"\n"
                                                + "Műszak: "+ muszak_mezo.getText()+"\n"
                                                + "Raklap ME száma: "+ meszam_mezo.getText()+"\n\n"
                                                + "A zárolásra hozott azonnali intézkedés: "+ intezkedes_mezo.getText()  +"\t\n"
                                                + "Gyökérokra hozott 2. intézkedés: "+ gyokerintezkedes2_mezo.getText() +"\n\nÜdvözlettel: EASQAS program";
                                        Email email = new Email();
                                        email.mindenes_email("easqas@veas.videoton.hu", cimzettek, cc, "Zárolásra hozott intézkedés. Zárolos papír sorszáma: "+ sorszam_mezo.getText(), uzenet);
                                        sql2 = "update qualitydb.Zarolasok set Email3 = 'igen' where ID = '"+ id_mezo.getText() +"'";
                                        lekerdezes.mindenes(sql2);
                                    }
                                    
                                }
                                if(gyokerintezkedes3_mezo.getText().equals("")) {}
                                else
                                {
                                    String sql2 = "select Email4 from qualitydb.Zarolasok where ID = '"+ id_mezo.getText() +"'";
                                    if(lekerdezes.tombvissza_sajat(sql2).length > 0)
                                    if(lekerdezes.tombvissza_sajat(sql2)[0].equals("nem"))
                                    {
                                        if(felelosterulet_box.getSelectedItem().equals("Gépes"))
                                        {
                                            cimzettek = gepes;
                                        }
                                        else if(felelosterulet_box.getSelectedItem().equals("Kézi"))
                                        {
                                            cimzettek = kezi;
                                        }
                                        else
                                        {
                                            cimzettek = vegszereles;
                                        }     
                                        String uzenet = "Sziasztok! \n \n"
                                                + "Zárolva lett az alábbi tétel: \n"
                                                + "Projekt: "+ String.valueOf(projekt_box.getSelectedItem())+ "\n"
                                                + "Cikkszám: "+ String.valueOf(tipus_box.getSelectedItem()) + "\n"
                                                + "Zárolt db: "+ zaroltdb_mezo.getText() +"\n"
                                                + "Zárolás oka: "+ email_hibaok +"\n"
                                                + "Zárolta: "+ String.valueOf(zarolta_box.getSelectedItem()) +"\n"
                                                + "Dátum: "+ datum_mezo.getText() +"\n"
                                                + "Műszak: "+ muszak_mezo.getText()+"\n"
                                                + "Raklap ME száma: "+ meszam_mezo.getText()+"\n\n"
                                                + "A zárolásra hozott azonnali intézkedés: "+ intezkedes_mezo.getText()  +"\t\n"
                                                + "Gyökérokra hozott 3. intézkedés: "+ gyokerintezkedes3_mezo.getText() +"\n\nÜdvözlettel: EASQAS program";
                                        Email email = new Email();
                                        email.mindenes_email("easqas@veas.videoton.hu", cimzettek, cc, "Zárolásra hozott intézkedés. Zárolos papír sorszáma: "+ sorszam_mezo.getText(), uzenet);
                                        sql2 = "update qualitydb.Zarolasok set Email4 = 'igen' where ID = '"+ id_mezo.getText() +"'";
                                        lekerdezes.mindenes(sql2);
                                    }
                                }
                                if(gyokerintezkedes4_mezo.getText().equals("")) {}
                                else
                                {
                                    String sql2 = "select Email5 from qualitydb.Zarolasok where ID = '"+ id_mezo.getText() +"'";
                                    if(lekerdezes.tombvissza_sajat(sql2).length > 0)
                                    if(lekerdezes.tombvissza_sajat(sql2)[0].equals("nem"))
                                    {
                                        if(felelosterulet_box.getSelectedItem().equals("Gépes"))
                                        {
                                            cimzettek = gepes;
                                        }
                                        else if(felelosterulet_box.getSelectedItem().equals("Kézi"))
                                        {
                                            cimzettek = kezi;
                                        }
                                        else
                                        {
                                            cimzettek = vegszereles;
                                        }     
                                        String uzenet = "Sziasztok! \n \n"
                                                + "Zárolva lett az alábbi tétel: \n"
                                                + "Projekt: "+ String.valueOf(projekt_box.getSelectedItem())+ "\n"
                                                + "Cikkszám: "+ String.valueOf(tipus_box.getSelectedItem()) + "\n"
                                                + "Zárolt db: "+ zaroltdb_mezo.getText() +"\n"
                                                + "Zárolás oka: "+ email_hibaok +"\n"
                                                + "Zárolta: "+ String.valueOf(zarolta_box.getSelectedItem()) +"\n"
                                                + "Dátum: "+ datum_mezo.getText() +"\n"
                                                + "Műszak: "+ muszak_mezo.getText()+"\n"
                                                + "Raklap ME száma: "+ meszam_mezo.getText()+"\n\n"
                                                + "A zárolásra hozott azonnali intézkedés: "+ intezkedes_mezo.getText()  +"\t\n"
                                                + "Gyökérokra hozott 4. intézkedés: "+ gyokerintezkedes4_mezo.getText() +"\n\nÜdvözlettel: EASQAS program";
                                        Email email = new Email();
                                        email.mindenes_email("easqas@veas.videoton.hu", cimzettek, cc, "Zárolásra hozott intézkedés. Zárolos papír sorszáma: "+ sorszam_mezo.getText(), uzenet);
                                        sql2 = "update qualitydb.Zarolasok set Email5 = 'igen' where ID = '"+ id_mezo.getText() +"'";
                                        lekerdezes.mindenes(sql2);
                                    }
                                }
                                if(gyokerintezkedes5_mezo.getText().equals("")) {}
                                else
                                {
                                    String sql2 = "select Email6 from qualitydb.Zarolasok where ID = '"+ id_mezo.getText() +"'";
                                    if(lekerdezes.tombvissza_sajat(sql2).length > 0)
                                    if(lekerdezes.tombvissza_sajat(sql2)[0].equals("nem"))
                                    {
                                        if(felelosterulet_box.getSelectedItem().equals("Gépes"))
                                        {
                                            cimzettek = gepes;
                                        }
                                        else if(felelosterulet_box.getSelectedItem().equals("Kézi"))
                                        {
                                            cimzettek = kezi;
                                        }
                                        else
                                        {
                                            cimzettek = vegszereles;
                                        }     
                                        String uzenet = "Sziasztok! \n \n"
                                                + "Zárolva lett az alábbi tétel: \n"
                                                + "Projekt: "+ String.valueOf(projekt_box.getSelectedItem())+ "\n"
                                                + "Cikkszám: "+ String.valueOf(tipus_box.getSelectedItem()) + "\n"
                                                + "Zárolt db: "+ zaroltdb_mezo.getText() +"\n"
                                                + "Zárolás oka: "+ email_hibaok +"\n"
                                                + "Zárolta: "+ String.valueOf(zarolta_box.getSelectedItem()) +"\n"
                                                + "Dátum: "+ datum_mezo.getText() +"\n"
                                                + "Műszak: "+ muszak_mezo.getText()+"\n"
                                                + "Raklap ME száma: "+ meszam_mezo.getText()+"\n\n"
                                                + "A zárolásra hozott azonnali intézkedés: "+ intezkedes_mezo.getText()  +"\t\n"
                                                + "Gyökérokra hozott 5. intézkedés: "+ gyokerintezkedes5_mezo.getText() +"\n\nÜdvözlettel: EASQAS program";
                                        Email email = new Email();
                                        email.mindenes_email("easqas@veas.videoton.hu", cimzettek, cc, "Zárolásra hozott intézkedés. Zárolos papír sorszáma: "+ sorszam_mezo.getText(), uzenet);
                                        sql2 = "update qualitydb.Zarolasok set Email6 = 'igen' where ID = '"+ id_mezo.getText() +"'";
                                        lekerdezes.mindenes(sql2);
                                    }
                                }
                            }
                            
                            if(lekerdezes.mindenes2(sql) == 0)
                            {
                                visszaallit();
                            }
                            
                            
                            int kovetkezo = Integer.parseInt(id_mezo.getText());
                            id_mezo.setText(String.valueOf(kovetkezo + 1));
                        }
                    }
                    else
                    {
                        if(rs.next())
                        {
                            if(technikus_csekk.isSelected())
                            {
                                technikus = "Nem";
                                
                            }
                            sql = "update qualitydb.Zarolasok set  Projekt ='"+ String.valueOf(projekt_box.getSelectedItem()) +"',Tipus = '"+ String.valueOf(tipus_box.getSelectedItem()) +"',"
                                    + "Eszleles_helye ='"+ eszleleshelye_mezo.getText() +"',"
                                    + "muszak ='"+ muszak_mezo.getText() +"',Zarolo_mernok = '"+ mernok_mezo.getText() +"',Zarolt_db ='"+Integer.parseInt(zaroltdb_mezo.getText())+"',Hol_van ='"+ zarolas_helye.getText() +"',"
                                    + "Zarolas_oka ='"+ zarolasoka +"',Azonnali_intezkedes ='"+ intezkedes_mezo.getText() +"',Zarolas_datuma ='"+ datum_mezo.getText() +"',Papir_sorszama ='"+ sorszam_mezo.getText() +"',"
                                    + "Zarolta ='"+ String.valueOf(zarolta_box.getSelectedItem()) +"',"
                                    + "Valogatas_eredmenye ='"+ eredmeny_mezo.getText() +"',Ujraellenorzes_datuma ='"+ ujradatum_mezo.getText() +"',Ellenorzes_ido ='"+ ido_mezo.getText() +"',Technikusi_beavatkozas ='"+ technikus +"',"
                                    + "Felelos_terulet ='"+ String.valueOf(felelosterulet_box.getSelectedItem()) +"',"
                                    + "Felelos ='"+ feleloss +"',Hiba_gyokeroka ='"+ gyokerok_mezo.getText() +"',Gyokerok_intezkedes ='"+ intezkedess +"',"
                                    + "B2_zarolas ='"+ b2_mezo.getText() +"',visszaellenorzes ='"+ visszaellenorzes_mezo.getText() +"',ME_szam ='"+ meszam_mezo.getText() +"' ,"
                                    + "Ellenorzott_db ='"+ ellenorzott_mezo.getText() +"',Hibacsoport ='"+ String.valueOf(hibacsoport_box.getSelectedItem()) +"',Hibatipus ='"+String.valueOf(hibatipus_box.getSelectedItem()) +"' "
                                            + ", Hatarido = '"+ hataridoo +"' where id = '"+ id_mezo.getText() +"'";
                            
                        }
                        else
                        {
                            if(technikus_csekk.isSelected())
                            {
                                technikus = "Nem";                                                                            
                            }
                            else
                            {
                                technikus = "Igen";
                                Email uzenet = new Email();
                                if(felelosterulet_box.getSelectedItem().equals("Gépes"))
                                {
                                    uzenet.zarolas_email("easqas@veas.videoton.hu", "ternak.sandor@veas.videoton.hu, kadar.zoltan@veas.videoton.hu, tatai.mihaly@veas.videoton.hu", String.valueOf(projekt_box.getSelectedItem()), String.valueOf(tipus_box.getSelectedItem()),
                                            zaroltdb_mezo.getText(), email_hibaok, String.valueOf(zarolta_box.getSelectedItem()), datum_mezo.getText(), muszak_mezo.getText(),"Zárolás - "+ String.valueOf(tipus_box.getSelectedItem())+" - "+
                                            zarolasoka_mezo.getText() +" - "+ sorszam_mezo.getText(), meszam_mezo.getText()) ;
                                }
                                else if(felelosterulet_box.getSelectedItem().equals("Kézi"))
                                {
                                    uzenet.zarolas_email("easqas@veas.videoton.hu", "nagy.balint@veas.videoton.hu, molnar.jozsef@veas.videoton.hu,csader.zsolt@veas.videoton.hu, tatai.mihaly@veas.videoton.hu", String.valueOf(projekt_box.getSelectedItem()), String.valueOf(tipus_box.getSelectedItem()),
                                            zaroltdb_mezo.getText(), email_hibaok, String.valueOf(zarolta_box.getSelectedItem()), datum_mezo.getText(), muszak_mezo.getText(),"Zárolás - "+ String.valueOf(tipus_box.getSelectedItem())+" - "+
                                                    zarolasoka_mezo.getText() +" - "+ sorszam_mezo.getText(), meszam_mezo.getText());
                                }
                                else
                                {
                                    uzenet.zarolas_email("easqas@veas.videoton.hu", "babud.imre@veas.videoton.hu, meszaros.hajnalka@veas.videoton.hu, serebrianska.kateryna@veas.videoton.hu, tatai.mihaly@veas.videoton.hu, fekete.mercedesz@veas.videoton.hu", String.valueOf(projekt_box.getSelectedItem()), String.valueOf(tipus_box.getSelectedItem()),
                                            zaroltdb_mezo.getText(), email_hibaok, String.valueOf(zarolta_box.getSelectedItem()), datum_mezo.getText(), muszak_mezo.getText(),"Zárolás - "+ String.valueOf(tipus_box.getSelectedItem())+" - "+
                                                    zarolasoka_mezo.getText() +" - "+ sorszam_mezo.getText(), meszam_mezo.getText());
                                } 
                            }
                            sql = "insert into qualitydb.Zarolasok (ID,Projekt,Tipus,Eszleles_helye,Muszak,Zarolo_mernok,Zarolt_db,Hol_van,Zarolas_oka,Azonnali_intezkedes,Zarolas_datuma,"
                                    + "Papir_sorszama,Zarolta,Valogatas_eredmenye,Ujraellenorzes_datuma,\r\n"
                                    + "Ellenorzes_ido,Technikusi_beavatkozas,Felelos_terulet,Felelos,Hiba_gyokeroka,Gyokerok_intezkedes,B2_zarolas,visszaellenorzes,ME_szam, Ellenorzott_db,Hibacsoport,Hibatipus) "
                                    + "Values('"+ id_mezo.getText()+"','"+ String.valueOf(projekt_box.getSelectedItem()) +"','"+ String.valueOf(tipus_box.getSelectedItem()) +"',"
                                    + "'"+ eszleleshelye_mezo.getText() +"','"+ muszak_mezo.getText() +"','"+ mernok_mezo.getText() +"','"+Integer.parseInt(zaroltdb_mezo.getText())+"','"+ zarolas_helye.getText() +"',"
                                    + "'"+ zarolasoka +"','"+ intezkedes_mezo.getText() +"','"+ datum_mezo.getText() +"','"+ sorszam_mezo.getText() +"','"+ String.valueOf(zarolta_box.getSelectedItem()) +"',"
                                    + "'"+ eredmeny_mezo.getText() +"','"+ ujradatum_mezo.getText() +"','"+ ido_mezo.getText() +"','"+ technikus +"','"+ String.valueOf(felelosterulet_box.getSelectedItem()) +"',"
                                    + "'"+ felelos_mezo.getText() +"','"+ gyokerok_mezo.getText() +"','"+ gyokerintezkedes_mezo.getText() +"','"+ b2_mezo.getText() +"','"+ visszaellenorzes_mezo.getText() +"'"
                                            + ",'"+ meszam_mezo.getText() +"','"+ ellenorzott_mezo.getText() +"','"+ String.valueOf(hibacsoport_box.getSelectedItem()) +"','"+ String.valueOf(hibatipus_box.getSelectedItem()) +"')";
                            
                        }
                        if(intezkedes_mezo.getText().equals("")) {}
                        else
                        {
                            String sql2 = "select Email from qualitydb.Zarolasok where ID = '"+ id_mezo.getText() +"'";
                            if(lekerdezes.tombvissza_sajat(sql2).length > 0)
                            if(lekerdezes.tombvissza_sajat(sql2)[0].equals("nem"))
                            {
                                if(felelosterulet_box.getSelectedItem().equals("Gépes"))
                                {
                                    cimzettek = gepes;
                                }
                                else if(felelosterulet_box.getSelectedItem().equals("Kézi"))
                                {
                                    cimzettek = kezi;
                                }
                                else
                                {
                                    cimzettek = vegszereles;
                                }     
                                String uzenet = "Sziasztok! \n \n"
                                        + "Zárolva lett az alábbi tétel: \n"
                                        + "Projekt: "+ String.valueOf(projekt_box.getSelectedItem())+ "\n"
                                        + "Cikkszám: "+ String.valueOf(tipus_box.getSelectedItem()) + "\n"
                                        + "Zárolt db: "+ zaroltdb_mezo.getText() +"\n"
                                        + "Zárolás oka: "+ email_hibaok +"\n"
                                        + "Zárolta: "+ String.valueOf(zarolta_box.getSelectedItem()) +"\n"
                                        + "Dátum: "+ datum_mezo.getText() +"\n"
                                        + "Műszak: "+ muszak_mezo.getText()+"\n"
                                        + "Raklap ME száma: "+ meszam_mezo.getText()+"\n\n"
                                        + "A zárolásra hozott azonnali intézkedés: "+ intezkedes_mezo.getText()  +"\n\nÜdvözlettel: EASQAS program";
                                Email email = new Email();
                                email.mindenes_email("easqas@veas.videoton.hu", cimzettek, cc, "Zárolásra hozott intézkedés. Zárolos papír sorszáma: "+ sorszam_mezo.getText(), uzenet);
                                sql2 = "update qualitydb.Zarolasok set Email = 'igen' where ID = '"+ id_mezo.getText() +"'";
                                lekerdezes.mindenes(sql2);
                            }
                        }
                        
                        if(technikus.equals("Igen"))
                        {
                            if(gyokerintezkedes_mezo.getText().equals("")) {}
                            else
                            {
                                String sql2 = "select Email2 from qualitydb.Zarolasok where ID = '"+ id_mezo.getText() +"'";
                                if(lekerdezes.tombvissza_sajat(sql2).length > 0)
                                if(lekerdezes.tombvissza_sajat(sql2)[0].equals("nem"))
                                {
                                    if(felelosterulet_box.getSelectedItem().equals("Gépes"))
                                    {
                                        cimzettek = gepes;
                                    }
                                    else if(felelosterulet_box.getSelectedItem().equals("Kézi"))
                                    {
                                        cimzettek = kezi;
                                    }
                                    else
                                    {
                                        cimzettek = vegszereles;
                                    }     
                                    String uzenet = "Sziasztok! \n \n"
                                            + "Zárolva lett az alábbi tétel: \n"
                                            + "Projekt: "+ String.valueOf(projekt_box.getSelectedItem())+ "\n"
                                            + "Cikkszám: "+ String.valueOf(tipus_box.getSelectedItem()) + "\n"
                                            + "Zárolt db: "+ zaroltdb_mezo.getText() +"\n"
                                            + "Zárolás oka: "+ email_hibaok +"\n"
                                            + "Zárolta: "+ String.valueOf(zarolta_box.getSelectedItem()) +"\n"
                                            + "Dátum: "+ datum_mezo.getText() +"\n"
                                            + "Műszak: "+ muszak_mezo.getText()+"\n"
                                            + "Raklap ME száma: "+ meszam_mezo.getText()+"\n\n"
                                            + "A zárolásra hozott azonnali intézkedés: "+ intezkedes_mezo.getText()  +"\t\n"
                                            + "Gyökérokra hozott intézkedés: "+ gyokerintezkedes_mezo.getText() +"\n\nÜdvözlettel: EASQAS program";
                                    Email email = new Email();
                                    email.mindenes_email("easqas@veas.videoton.hu", cimzettek, cc, "Zárolásra hozott intézkedés. Zárolos papír sorszáma: "+ sorszam_mezo.getText(), uzenet);
                                    sql2 = "update qualitydb.Zarolasok set Email2 = 'igen' where ID = '"+ id_mezo.getText() +"'";
                                    lekerdezes.mindenes(sql2);
                                }
                            }
                            if(gyokerintezkedes2_mezo.getText().equals("")) {}
                            else
                            {
                                String sql2 = "select Email3 from qualitydb.Zarolasok where ID = '"+ id_mezo.getText() +"'";
                                if(lekerdezes.tombvissza_sajat(sql2).length > 0)
                                if(lekerdezes.tombvissza_sajat(sql2)[0].equals("nem"))
                                {
                                    if(felelosterulet_box.getSelectedItem().equals("Gépes"))
                                    {
                                        cimzettek = gepes;
                                    }
                                    else if(felelosterulet_box.getSelectedItem().equals("Kézi"))
                                    {
                                        cimzettek = kezi;
                                    }
                                    else
                                    {
                                        cimzettek = vegszereles;
                                    }     
                                    String uzenet = "Sziasztok! \n \n"
                                            + "Zárolva lett az alábbi tétel: \n"
                                            + "Projekt: "+ String.valueOf(projekt_box.getSelectedItem())+ "\n"
                                            + "Cikkszám: "+ String.valueOf(tipus_box.getSelectedItem()) + "\n"
                                            + "Zárolt db: "+ zaroltdb_mezo.getText() +"\n"
                                            + "Zárolás oka: "+ email_hibaok +"\n"
                                            + "Zárolta: "+ String.valueOf(zarolta_box.getSelectedItem()) +"\n"
                                            + "Dátum: "+ datum_mezo.getText() +"\n"
                                            + "Műszak: "+ muszak_mezo.getText()+"\n"
                                            + "Raklap ME száma: "+ meszam_mezo.getText()+"\n\n"
                                            + "A zárolásra hozott azonnali intézkedés: "+ intezkedes_mezo.getText()  +"\t\n"
                                            + "Gyökérokra hozott 2. intézkedés: "+ gyokerintezkedes2_mezo.getText() +"\n\nÜdvözlettel: EASQAS program";
                                    Email email = new Email();
                                    email.mindenes_email("easqas@veas.videoton.hu", cimzettek, cc, "Zárolásra hozott intézkedés. Zárolos papír sorszáma: "+ sorszam_mezo.getText(), uzenet);
                                    sql2 = "update qualitydb.Zarolasok set Email3 = 'igen' where ID = '"+ id_mezo.getText() +"'";
                                    lekerdezes.mindenes(sql2);
                                }
                                
                            }
                            if(gyokerintezkedes3_mezo.getText().equals("")) {}
                            else
                            {
                                String sql2 = "select Email4 from qualitydb.Zarolasok where ID = '"+ id_mezo.getText() +"'";
                                if(lekerdezes.tombvissza_sajat(sql2).length > 0)
                                if(lekerdezes.tombvissza_sajat(sql2)[0].equals("nem"))
                                {
                                    if(felelosterulet_box.getSelectedItem().equals("Gépes"))
                                    {
                                        cimzettek = gepes;
                                    }
                                    else if(felelosterulet_box.getSelectedItem().equals("Kézi"))
                                    {
                                        cimzettek = kezi;
                                    }
                                    else
                                    {
                                        cimzettek = vegszereles;
                                    }     
                                    String uzenet = "Sziasztok! \n \n"
                                            + "Zárolva lett az alábbi tétel: \n"
                                            + "Projekt: "+ String.valueOf(projekt_box.getSelectedItem())+ "\n"
                                            + "Cikkszám: "+ String.valueOf(tipus_box.getSelectedItem()) + "\n"
                                            + "Zárolt db: "+ zaroltdb_mezo.getText() +"\n"
                                            + "Zárolás oka: "+ email_hibaok +"\n"
                                            + "Zárolta: "+ String.valueOf(zarolta_box.getSelectedItem()) +"\n"
                                            + "Dátum: "+ datum_mezo.getText() +"\n"
                                            + "Műszak: "+ muszak_mezo.getText()+"\n"
                                            + "Raklap ME száma: "+ meszam_mezo.getText()+"\n\n"
                                            + "A zárolásra hozott azonnali intézkedés: "+ intezkedes_mezo.getText()  +"\t\n"
                                            + "Gyökérokra hozott 3. intézkedés: "+ gyokerintezkedes3_mezo.getText() +"\n\nÜdvözlettel: EASQAS program";
                                    Email email = new Email();
                                    email.mindenes_email("easqas@veas.videoton.hu", cimzettek, cc, "Zárolásra hozott intézkedés. Zárolos papír sorszáma: "+ sorszam_mezo.getText(), uzenet);
                                    sql2 = "update qualitydb.Zarolasok set Email4 = 'igen' where ID = '"+ id_mezo.getText() +"'";
                                    lekerdezes.mindenes(sql2);
                                }
                            }
                            if(gyokerintezkedes4_mezo.getText().equals("")) {}
                            else
                            {
                                String sql2 = "select Email5 from qualitydb.Zarolasok where ID = '"+ id_mezo.getText() +"'";
                                if(lekerdezes.tombvissza_sajat(sql2).length > 0)
                                if(lekerdezes.tombvissza_sajat(sql2)[0].equals("nem"))
                                {
                                    if(felelosterulet_box.getSelectedItem().equals("Gépes"))
                                    {
                                        cimzettek = gepes;
                                    }
                                    else if(felelosterulet_box.getSelectedItem().equals("Kézi"))
                                    {
                                        cimzettek = kezi;
                                    }
                                    else
                                    {
                                        cimzettek = vegszereles;
                                    }     
                                    String uzenet = "Sziasztok! \n \n"
                                            + "Zárolva lett az alábbi tétel: \n"
                                            + "Projekt: "+ String.valueOf(projekt_box.getSelectedItem())+ "\n"
                                            + "Cikkszám: "+ String.valueOf(tipus_box.getSelectedItem()) + "\n"
                                            + "Zárolt db: "+ zaroltdb_mezo.getText() +"\n"
                                            + "Zárolás oka: "+ email_hibaok +"\n"
                                            + "Zárolta: "+ String.valueOf(zarolta_box.getSelectedItem()) +"\n"
                                            + "Dátum: "+ datum_mezo.getText() +"\n"
                                            + "Műszak: "+ muszak_mezo.getText()+"\n"
                                            + "Raklap ME száma: "+ meszam_mezo.getText()+"\n\n"
                                            + "A zárolásra hozott azonnali intézkedés: "+ intezkedes_mezo.getText()  +"\t\n"
                                            + "Gyökérokra hozott 4. intézkedés: "+ gyokerintezkedes4_mezo.getText() +"\n\nÜdvözlettel: EASQAS program";
                                    Email email = new Email();
                                    email.mindenes_email("easqas@veas.videoton.hu", cimzettek, cc, "Zárolásra hozott intézkedés. Zárolos papír sorszáma: "+ sorszam_mezo.getText(), uzenet);
                                    sql2 = "update qualitydb.Zarolasok set Email5 = 'igen' where ID = '"+ id_mezo.getText() +"'";
                                    lekerdezes.mindenes(sql2);
                                }
                            }
                            if(gyokerintezkedes5_mezo.getText().equals("")) {}
                            else
                            {
                                String sql2 = "select Email6 from qualitydb.Zarolasok where ID = '"+ id_mezo.getText() +"'";
                                if(lekerdezes.tombvissza_sajat(sql2).length > 0)
                                if(lekerdezes.tombvissza_sajat(sql2)[0].equals("nem"))
                                {
                                    if(felelosterulet_box.getSelectedItem().equals("Gépes"))
                                    {
                                        cimzettek = gepes;
                                    }
                                    else if(felelosterulet_box.getSelectedItem().equals("Kézi"))
                                    {
                                        cimzettek = kezi;
                                    }
                                    else
                                    {
                                        cimzettek = vegszereles;
                                    }     
                                    String uzenet = "Sziasztok! \n \n"
                                            + "Zárolva lett az alábbi tétel: \n"
                                            + "Projekt: "+ String.valueOf(projekt_box.getSelectedItem())+ "\n"
                                            + "Cikkszám: "+ String.valueOf(tipus_box.getSelectedItem()) + "\n"
                                            + "Zárolt db: "+ zaroltdb_mezo.getText() +"\n"
                                            + "Zárolás oka: "+ email_hibaok +"\n"
                                            + "Zárolta: "+ String.valueOf(zarolta_box.getSelectedItem()) +"\n"
                                            + "Dátum: "+ datum_mezo.getText() +"\n"
                                            + "Műszak: "+ muszak_mezo.getText()+"\n"
                                            + "Raklap ME száma: "+ meszam_mezo.getText()+"\n\n"
                                            + "A zárolásra hozott azonnali intézkedés: "+ intezkedes_mezo.getText()  +"\t\n"
                                            + "Gyökérokra hozott 5. intézkedés: "+ gyokerintezkedes5_mezo.getText() +"\n\nÜdvözlettel: EASQAS program";
                                    Email email = new Email();
                                    email.mindenes_email("easqas@veas.videoton.hu", cimzettek, cc, "Zárolásra hozott intézkedés. Zárolos papír sorszáma: "+ sorszam_mezo.getText(), uzenet);
                                    sql2 = "update qualitydb.Zarolasok set Email6 = 'igen' where ID = '"+ id_mezo.getText() +"'";
                                    lekerdezes.mindenes(sql2);
                                }
                            }
                        }
                        
                        if(intezkedes_mezo.getText().equals("")) {}
                        else
                        {
                            String sql2 = "select Email from qualitydb.Zarolasok where ID = '"+ id_mezo.getText() +"'";
                            if(lekerdezes.tombvissza_sajat(sql2).length > 0)
                            if(lekerdezes.tombvissza_sajat(sql2)[0].equals("nem"))
                            {
                                 
                                if(felelosterulet_box.getSelectedItem().equals("Gépes"))
                                {
                                    cimzettek = gepes;
                                }
                                else if(felelosterulet_box.getSelectedItem().equals("Kézi"))
                                {
                                    cimzettek = kezi;
                                }
                                else
                                {
                                    cimzettek = vegszereles;
                                }     
                                String uzenet = "Sziasztok! \n \n"
                                        + "Zárolva lett az alábbi tétel: \n"
                                        + "Projekt: "+ String.valueOf(projekt_box.getSelectedItem())+ "\n"
                                        + "Cikkszám: "+ String.valueOf(tipus_box.getSelectedItem()) + "\n"
                                        + "Zárolt db: "+ zaroltdb_mezo.getText() +"\n"
                                        + "Zárolás oka: "+ email_hibaok +"\n"
                                        + "Zárolta: "+ String.valueOf(zarolta_box.getSelectedItem()) +"\n"
                                        + "Dátum: "+ datum_mezo.getText() +"\n"
                                        + "Műszak: "+ muszak_mezo.getText()+"\n"
                                        + "Raklap ME száma: "+ meszam_mezo.getText()+"\n\n"
                                        + "A zárolásra hozott azonnali intézkedés: "+ intezkedes_mezo.getText()  +"\n\nÜdvözlettel: EASQAS program";
                                Email email = new Email();
                                email.mindenes_email("easqas@veas.videoton.hu", cimzettek, cc, "Zárolásra hozott intézkedés. Zárolos papír sorszáma: "+ sorszam_mezo.getText(), uzenet);
                                sql2 = "update qualitydb.Zarolasok set Email = 'igen' where ID = '"+ id_mezo.getText() +"'";
                                lekerdezes.mindenes(sql2);
                            }
                        }
                        
                        if(lekerdezes.mindenes2(sql) == 0)
                        {
                            visszaallit();
                        }
                        
                        
                        int kovetkezo = Integer.parseInt(id_mezo.getText());
                        id_mezo.setText(String.valueOf(kovetkezo + 1));
                    }
                }
            }
                catch (Exception e1) 
                {
                    e1.printStackTrace();
                    String hibauzenet = e1.toString();
                    Email hibakuldes = new Email();
                    hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu",this.getClass().getSimpleName()+" "+ hibauzenet);
                    JOptionPane.showMessageDialog(null, getClass()+" "+ hibauzenet, "Hiba üzenet", 2);                                                     //kivétel esetén kiírja a hibaüzenetet                    
                    return;
                }
                Foablak.frame.setCursor(null);                                                                                         //egér mutató alaphelyzetbe állítása
            
           
         }
    }
    
    private void visszaallit()
    {
        eszleleshelye_mezo.setText("");
        muszak_mezo.setText("");
        mernok_mezo.setText("");
        zaroltdb_mezo.setText("0");
        zarolas_helye.setText("");
        zarolasoka_mezo.setText("");
        intezkedes_mezo.setText("");
        //datum_mezo.setText("");
        sorszam_mezo.setText("");
        eredmeny_mezo.setText("");
        ujradatum_mezo.setText("");
        ido_mezo.setText("");
        technikus_csekk.setSelected(false);
        felelos_mezo.setText("");
        felelos_mezo2.setText("");
        felelos_mezo3.setText("");
        felelos_mezo4.setText("");
        felelos_mezo5.setText("");
        gyokerok_mezo.setText("");
        gyokerintezkedes_mezo.setText("");
        gyokerintezkedes2_mezo.setText("");
        gyokerintezkedes3_mezo.setText("");
        gyokerintezkedes4_mezo.setText("");
        gyokerintezkedes5_mezo.setText("");
        hatarido_mezo.setText("");
        hatarido2_mezo.setText("");
        hatarido3_mezo.setText("");
        hatarido4_mezo.setText("");
        hatarido5_mezo.setText("");
        b2_mezo.setText("");
        visszaellenorzes_mezo.setText("");
        meszam_mezo.setText("");
        projekt_box.removeActionListener(valaszto);
        projekt_box.setSelectedIndex(0);
        tipus_box.setSelectedIndex(0);
        zarolta_box.setSelectedIndex(0);
        ellenorzott_mezo.setText("0");
        projekt_box.addActionListener(valaszto);
        gyokerokvalaszto_box.setSelectedIndex(0);
        zarolasoka_box.setSelectedIndex(0);
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
                projekt_box.removeActionListener(valaszto);
                projekt_box.setSelectedItem(rs.getString(2));
                projekt_box.addActionListener(valaszto);
                String[] cikkszam = {rs.getString(3)};
                DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(cikkszam);
                tipus_box.setModel(model);
                //tipus_box.setSelectedItem(rs.getString(3));
                eszleleshelye_mezo.setText(rs.getString(4));
                muszak_mezo.setText(rs.getString(5));
                mernok_mezo.setText(rs.getString(6));
                zaroltdb_mezo.setText(rs.getString(7));
                zarolas_helye.setText(rs.getString(8));
                String[] zarolasoka =  rs.getString(9).split(";");
                zarolasoka_mezo.setText(zarolasoka[0]);
                zarolasoka_box.setSelectedItem(zarolasoka[1]);
                intezkedes_mezo.setText(rs.getString(10));
                String[] datum = rs.getString(11).split(" "); 
                datum_mezo.setText(datum[0]);
                sorszam_mezo.setText(rs.getString(12));
                zarolta_box.setSelectedItem(rs.getString(13));
                eredmeny_mezo.setText(rs.getString(14));
                ujradatum_mezo.setText(rs.getString(15));
                ido_mezo.setText(rs.getString(16));
                if(rs.getString(17).equals("Nem"))
                {
                    technikus_csekk.setSelected(true);
                }
                felelosterulet_box.setSelectedItem(rs.getString(18));
                
                String[] felelosok = rs.getString(19).split(";");
                if(felelosok.length == 1)
                {
                    felelos_mezo.setText(felelosok[0]);
                }
                if(felelosok.length == 2)
                {
                    felelos_mezo.setText(felelosok[0]);
                    felelos_mezo2.setText(felelosok[1]);
                }
                if(felelosok.length == 3)
                {
                    felelos_mezo.setText(felelosok[0]);
                    felelos_mezo2.setText(felelosok[1]);
                    felelos_mezo3.setText(felelosok[2]);
                }
                if(felelosok.length == 4)
                {
                    felelos_mezo.setText(felelosok[0]);
                    felelos_mezo2.setText(felelosok[1]);
                    felelos_mezo3.setText(felelosok[2]);
                    felelos_mezo4.setText(felelosok[3]);
                }
                if(felelosok.length == 5)
                {
                    felelos_mezo.setText(felelosok[0]);
                    felelos_mezo2.setText(felelosok[1]);
                    felelos_mezo3.setText(felelosok[2]);
                    felelos_mezo4.setText(felelosok[3]);
                    felelos_mezo5.setText(felelosok[4]);
                }
                //felelos_mezo.setText(rs.getString(19));
                gyokerok_mezo.setText(rs.getString(20));
                
                String[] gyokerintezkedes = rs.getString(21).split(";");
                if(gyokerintezkedes.length == 1)
                {
                    gyokerintezkedes_mezo.setText(gyokerintezkedes[0]);
                }
                if(gyokerintezkedes.length == 2)
                {
                    gyokerintezkedes_mezo.setText(gyokerintezkedes[0]);
                    gyokerintezkedes2_mezo.setText(gyokerintezkedes[1]);
                }
                if(gyokerintezkedes.length == 3)
                {
                    gyokerintezkedes_mezo.setText(gyokerintezkedes[0]);
                    gyokerintezkedes2_mezo.setText(gyokerintezkedes[1]);
                    gyokerintezkedes3_mezo.setText(gyokerintezkedes[2]);
                }
                if(gyokerintezkedes.length == 4)
                {
                    gyokerintezkedes_mezo.setText(gyokerintezkedes[0]);
                    gyokerintezkedes2_mezo.setText(gyokerintezkedes[1]);
                    gyokerintezkedes3_mezo.setText(gyokerintezkedes[2]);
                    gyokerintezkedes4_mezo.setText(gyokerintezkedes[3]);
                }
                if(gyokerintezkedes.length == 5)
                {
                    gyokerintezkedes_mezo.setText(gyokerintezkedes[0]);
                    gyokerintezkedes2_mezo.setText(gyokerintezkedes[1]);
                    gyokerintezkedes3_mezo.setText(gyokerintezkedes[2]);
                    gyokerintezkedes4_mezo.setText(gyokerintezkedes[3]);
                    gyokerintezkedes5_mezo.setText(gyokerintezkedes[4]);
                }
                
                b2_mezo.setText(rs.getString(22));                
                visszaellenorzes_mezo.setText(rs.getString(23));
                if(rs.getString(24) != null)
                {
                    String[] lezarva = rs.getString(24).split(" ");
                    lezaras_mezo.setText(lezarva[0]);
                }
                meszam_mezo.setText(rs.getString(25));
                ellenorzott_mezo.setText(rs.getString(26));
                hibacsoport_box.setSelectedItem(rs.getString(27));
                hibatipus_box.setSelectedItem(rs.getString(28));
                
                //hatarido_mezo.setText(rs.getString(31));
                if(rs.getString(31) != null)
                {
                    String[] hataridok = rs.getString(31).split(";");
                    if(hataridok.length == 1)
                    {
                        hatarido_mezo.setText(hataridok[0]);
                    }
                    if(hataridok.length == 2)
                    {
                        hatarido_mezo.setText(hataridok[0]);
                        hatarido2_mezo.setText(hataridok[1]);
                    }
                    if(hataridok.length == 3)
                    {
                        hatarido_mezo.setText(hataridok[0]);
                        hatarido2_mezo.setText(hataridok[1]);
                        hatarido3_mezo.setText(hataridok[2]);
                    }
                    if(hataridok.length == 4)
                    {
                        hatarido_mezo.setText(hataridok[0]);
                        hatarido2_mezo.setText(hataridok[1]);
                        hatarido3_mezo.setText(hataridok[2]);
                        hatarido4_mezo.setText(hataridok[3]);
                    }
                    if(hataridok.length == 5)
                    {
                        hatarido_mezo.setText(hataridok[0]);
                        hatarido2_mezo.setText(hataridok[1]);
                        hatarido3_mezo.setText(hataridok[2]);
                        hatarido4_mezo.setText(hataridok[3]);
                        hatarido5_mezo.setText(hataridok[4]);
                    }
                }
    
            }
            stmt.close();
            conn.close();
                    
            } 
            catch (SQLException e1) 
            {
               e1.printStackTrace();
               String hibauzenet = e1.toString();
               Email hibakuldes = new Email();
               hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu",this.getClass().getSimpleName()+" "+ hibauzenet);
               JOptionPane.showMessageDialog(null, getClass()+" "+ hibauzenet, "Hiba üzenet", 2);
            } 
            catch (Exception e1) 
            {
               e1.printStackTrace();
               String hibauzenet = e1.toString();
               Email hibakuldes = new Email();
               hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu",this.getClass().getSimpleName()+" "+ hibauzenet);
               JOptionPane.showMessageDialog(null, getClass()+" "+ hibauzenet, "Hiba üzenet", 2);;
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
            hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu",this.getClass().getSimpleName()+" "+ hibauzenet);
            JOptionPane.showMessageDialog(null, getClass()+" "+ hibauzenet, "Hiba üzenet", 2);
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
    
    class Kivalaszt implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            String[] cikkbox = null;
            try 
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                                                //egér mutató változtatása munka a háttérbenre
                DefaultComboBoxModel<String> model;
                String keresett = String.valueOf(projekt_box.getSelectedItem());
                String vevo = "";

                workbook.loadFromFile(excelhelye2);
                workbook.setVersion(ExcelVersion.Version2016);
                Worksheet sheet = workbook.getWorksheets().get(0);
                for(int szamlalo = 1; szamlalo < sheet.getLastDataRow(); szamlalo++)
                {
                    if(keresett.toUpperCase().equals(sheet.getRange().get("B"+szamlalo).getText().toUpperCase()))
                    {
                        vevo =  sheet.getRange().get("A"+szamlalo).getText();
                        System.out.println(vevo);
                    }
                }                
                
                Class.forName("oracle.jdbc.OracleDriver");  //.driver
                
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@IFSORA.IFS.videoton.hu:1521/IFSPROD","ZKOVACS","ZKOVACS");                                      
                Statement stmt = con.createStatement();              
                ResultSet rs = stmt.executeQuery( "select cikkszamok.part_no, \r\n"
                        + "        cikkszamok.REVISION_TEXT,\r\n"
                        + "        cikkszamok.CF$_PRODUCT_CODE_DESC \r\n"
                        + "from ifsapp.PART_REVISION_cfv cikkszamok\r\n"
                        + "where 3 = 3 and cf$_second_commodity = '"+ vevo +"' order by part_no asc");
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
                
                model = new DefaultComboBoxModel<String>(cikkbox);
                tipus_box.setModel(model); 
                Foablak.frame.setCursor(null);                                                //egér mutató alaphelyzetbe állítása
            } 
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", this.getClass().getSimpleName()+" "+ hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
    
    class Gyokerok_valaszto implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                                                //egér mutató változtatása munka a háttérbenre
                if(String.valueOf(gyokerokvalaszto_box.getSelectedItem()).equals("1"))
                {
                    gyokerintezkedes_mezo.setVisible(true);
                    gyokerintezkedes2_mezo.setVisible(false);
                    gyokerintezkedes3_mezo.setVisible(false);
                    gyokerintezkedes4_mezo.setVisible(false);
                    gyokerintezkedes5_mezo.setVisible(false);
                    
                    felelos_mezo.setVisible(true);
                    felelos_mezo2.setVisible(false);
                    felelos_mezo3.setVisible(false);
                    felelos_mezo4.setVisible(false);
                    felelos_mezo5.setVisible(false);
                    
                    hatarido_mezo.setVisible(true);
                    hatarido2_mezo.setVisible(false);
                    hatarido3_mezo.setVisible(false);
                    hatarido4_mezo.setVisible(false);
                    hatarido5_mezo.setVisible(false);
                }
                if(String.valueOf(gyokerokvalaszto_box.getSelectedItem()).equals("2"))
                {
                    gyokerintezkedes_mezo.setVisible(false);
                    gyokerintezkedes2_mezo.setVisible(true);
                    gyokerintezkedes3_mezo.setVisible(false);
                    gyokerintezkedes4_mezo.setVisible(false);
                    gyokerintezkedes5_mezo.setVisible(false);
                    
                    felelos_mezo.setVisible(false);
                    felelos_mezo2.setVisible(true);
                    felelos_mezo3.setVisible(false);
                    felelos_mezo4.setVisible(false);
                    felelos_mezo5.setVisible(false);
                    
                    hatarido_mezo.setVisible(false);
                    hatarido2_mezo.setVisible(true);
                    hatarido3_mezo.setVisible(false);
                    hatarido4_mezo.setVisible(false);
                    hatarido5_mezo.setVisible(false);
                }
                if(String.valueOf(gyokerokvalaszto_box.getSelectedItem()).equals("3"))
                {
                    gyokerintezkedes_mezo.setVisible(false);
                    gyokerintezkedes2_mezo.setVisible(false);
                    gyokerintezkedes3_mezo.setVisible(true);
                    gyokerintezkedes4_mezo.setVisible(false);
                    gyokerintezkedes5_mezo.setVisible(false);
                    
                    felelos_mezo.setVisible(false);
                    felelos_mezo2.setVisible(false);
                    felelos_mezo3.setVisible(true);
                    felelos_mezo4.setVisible(false);
                    felelos_mezo5.setVisible(false);
                    
                    hatarido_mezo.setVisible(false);
                    hatarido2_mezo.setVisible(false);
                    hatarido3_mezo.setVisible(true);
                    hatarido4_mezo.setVisible(false);
                    hatarido5_mezo.setVisible(false);
                }
                if(String.valueOf(gyokerokvalaszto_box.getSelectedItem()).equals("4"))
                {
                    gyokerintezkedes_mezo.setVisible(false);
                    gyokerintezkedes2_mezo.setVisible(false);
                    gyokerintezkedes3_mezo.setVisible(false);
                    gyokerintezkedes4_mezo.setVisible(true);
                    gyokerintezkedes5_mezo.setVisible(false);
                    
                    felelos_mezo.setVisible(false);
                    felelos_mezo2.setVisible(false);
                    felelos_mezo3.setVisible(false);
                    felelos_mezo4.setVisible(true);
                    felelos_mezo5.setVisible(false);
                    
                    hatarido_mezo.setVisible(false);
                    hatarido2_mezo.setVisible(false);
                    hatarido3_mezo.setVisible(false);
                    hatarido4_mezo.setVisible(true);
                    hatarido5_mezo.setVisible(false);
                }
                if(String.valueOf(gyokerokvalaszto_box.getSelectedItem()).equals("5"))
                {
                    gyokerintezkedes_mezo.setVisible(false);
                    gyokerintezkedes2_mezo.setVisible(false);
                    gyokerintezkedes3_mezo.setVisible(false);
                    gyokerintezkedes4_mezo.setVisible(false);
                    gyokerintezkedes5_mezo.setVisible(true);
                    
                    felelos_mezo.setVisible(false);
                    felelos_mezo2.setVisible(false);
                    felelos_mezo3.setVisible(false);
                    felelos_mezo4.setVisible(false);
                    felelos_mezo5.setVisible(true);
                    
                    hatarido_mezo.setVisible(false);
                    hatarido2_mezo.setVisible(false);
                    hatarido3_mezo.setVisible(false);
                    hatarido4_mezo.setVisible(false);
                    hatarido5_mezo.setVisible(true);
                }
                Foablak.frame.setCursor(null);                                                                                         //egér mutató alaphelyzetbe állítása
            }
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu",this.getClass().getSimpleName()+" "+ hibauzenet);
                JOptionPane.showMessageDialog(null, getClass()+" "+ hibauzenet, "Hiba üzenet", 2);                                                    //kivétel esetén kiírja a hibaüzenetet
            }
         }
    }
}
