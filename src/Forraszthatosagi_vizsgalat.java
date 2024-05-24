import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Cursor;
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
import java.util.Date;

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
    private JTextField zarolando_mezo;
    private JTextField kekcimke_mezo;
    private JComboBox<String> eloiras_box;
    private JComboBox<String> eredmeny_box;
    private JTextArea eloiras_mezo;
    private JTextField beerkezettdb_mezo;
    private JComboBox<String> vizsgalat_box;
    private JComboBox<String> ki_box;

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
        id_mezo.addKeyListener(new Enter2());
        id_mezo.setBounds(74, 58, 46, 20);
        add(id_mezo);
        id_mezo.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("Cikkszám");
        lblNewLabel_2.setBounds(39, 107, 68, 14);
        add(lblNewLabel_2);
        
        cikkszam_mezo = new JTextField();
        cikkszam_mezo.addKeyListener(new Enter());
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
        lblNewLabel_6.setBounds(644, 144, 94, 14);
        add(lblNewLabel_6);
        
        sorszam_mezo = new JTextField();
        sorszam_mezo.setBounds(748, 142, 91, 20);
        add(sorszam_mezo);
        sorszam_mezo.setColumns(10);
        
        JLabel lblNewLabel_7 = new JLabel("Terület");
        lblNewLabel_7.setBounds(849, 144, 46, 14);
        add(lblNewLabel_7);
        
        String[] terulet = {"-","Gépes","Kézi"};
        terület_box = new JComboBox<String>(terulet);                              //terulet
        terület_box.setBounds(938, 140, 129, 22);
        add(terület_box);
        
        JLabel lblNewLabel_8 = new JLabel("Alkatrész kora");
        lblNewLabel_8.setBounds(370, 144, 86, 14);
        add(lblNewLabel_8);
        
        kor_mezo = new JTextField();
        kor_mezo.setBounds(493, 141, 30, 20);
        add(kor_mezo);
        kor_mezo.setColumns(10);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(39, 183, 1158, 14);
        add(separator);
        
        JLabel lblNewLabel_9 = new JLabel("Van vevői előírás?");
        lblNewLabel_9.setBounds(39, 223, 107, 14);
        add(lblNewLabel_9);
        
        String[] eloiras = {"-","Igen","Nem"};
        eloiras_box = new JComboBox<String>(eloiras);                      //eloiras
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
        lblNewLabel_11.setBounds(39, 389, 130, 14);
        add(lblNewLabel_11);
        
        mennyiseg_mezo = new JTextField();
        mennyiseg_mezo.setText("4");
        mennyiseg_mezo.setBounds(179, 386, 46, 20);
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
        
        JLabel lblNewLabel_14 = new JLabel("Vizsgálatot végezte");
        lblNewLabel_14.setBounds(269, 389, 121, 14);
        add(lblNewLabel_14);
        
        JLabel lblNewLabel_15 = new JLabel("Vizsgálat eredménye");
        lblNewLabel_15.setBounds(156, 446, 130, 14);
        add(lblNewLabel_15);
        
        String[] eredmeny = {"-","OK","NOK"};
        eredmeny_box = new JComboBox<String>(eredmeny);                 //eredmeny
        eredmeny_box.addActionListener(new Vagy());
        eredmeny_box.setBounds(313, 442, 86, 22);
        add(eredmeny_box);
        
        JLabel lblNewLabel_16 = new JLabel("Zárolandó / SQA értesítés");
        lblNewLabel_16.setBounds(501, 446, 150, 14);
        add(lblNewLabel_16);
        
        zarolando_mezo = new JTextField();
        zarolando_mezo.setBounds(652, 443, 86, 20);
        add(zarolando_mezo);
        zarolando_mezo.setColumns(10);
        
        JLabel lblNewLabel_17 = new JLabel("Kék cimkével kerül gyártásba?");
        lblNewLabel_17.setBounds(786, 446, 191, 14);
        add(lblNewLabel_17);
        
        kekcimke_mezo = new JTextField();
        kekcimke_mezo.setBounds(987, 443, 86, 20);
        add(kekcimke_mezo);
        kekcimke_mezo.setColumns(10);
        
        JButton mentes_gomb = new JButton("Mentés");
        mentes_gomb.addActionListener(new Mentes());
        mentes_gomb.setBounds(562, 537, 89, 23);
        add(mentes_gomb);
        
        Utolso_sor sorszam = new Utolso_sor();
        int kovetkezo = Integer.parseInt(sorszam.utolso("qualitydb.Forraszthatosagi_vizsgalat"));
        id_mezo.setText(String.valueOf(kovetkezo + 1));
        
        JButton vissza_gomb = new JButton("Vissza");
        vissza_gomb.addActionListener(new Vissza());
        vissza_gomb.setBounds(1108, 57, 89, 23);
        add(vissza_gomb);
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        Date date = new Date();
        idopont_mezo.setText(formatter.format(date));
        
        JLabel lblNewLabel_18 = new JLabel("Beérkezett mennyiség");
        lblNewLabel_18.setBounds(39, 340, 130, 14);
        add(lblNewLabel_18);
        
        beerkezettdb_mezo = new JTextField();
        beerkezettdb_mezo.setBounds(179, 337, 46, 20);
        add(beerkezettdb_mezo);
        beerkezettdb_mezo.setColumns(10);
        
        String[] vizsgalat = {"-","Mártó forrasztás","Pasztázott PAD-re helyezés","Alkatrész pasztázás","Normál gyártási folyamat"};
        vizsgalat_box = new JComboBox<String>(vizsgalat);                                //vizsgalat
        vizsgalat_box.setBounds(627, 336, 570, 22);
        add(vizsgalat_box);
        
        JLabel lblNewLabel_19 = new JLabel("évnél idősebb");
        lblNewLabel_19.setBounds(533, 144, 101, 14);
        add(lblNewLabel_19);
        
        String[] ki = {"-","Kádár Zoltán","Ternák Sándor","Molnár József","Nagy Bálint"};
        ki_box = new JComboBox<String>(ki);
        ki_box.setBounds(409, 385, 202, 22);
        add(ki_box);

    }
    
    public Forraszthatosagi_vizsgalat(String id) {
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
        id_mezo.addKeyListener(new Enter2());
        id_mezo.setBounds(74, 58, 46, 20);
        add(id_mezo);
        id_mezo.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("Cikkszám");
        lblNewLabel_2.setBounds(39, 107, 68, 14);
        add(lblNewLabel_2);
        
        cikkszam_mezo = new JTextField();
        cikkszam_mezo.addKeyListener(new Enter());
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
        lblNewLabel_6.setBounds(644, 144, 94, 14);
        add(lblNewLabel_6);
        
        sorszam_mezo = new JTextField();
        sorszam_mezo.setBounds(748, 142, 91, 20);
        add(sorszam_mezo);
        sorszam_mezo.setColumns(10);
        
        JLabel lblNewLabel_7 = new JLabel("Terület");
        lblNewLabel_7.setBounds(849, 144, 46, 14);
        add(lblNewLabel_7);
        
        String[] terulet = {"-","Gépes","Kézi"};
        terület_box = new JComboBox<String>(terulet);                              //terulet
        terület_box.setBounds(938, 140, 129, 22);
        add(terület_box);
        
        JLabel lblNewLabel_8 = new JLabel("Alkatrész kora");
        lblNewLabel_8.setBounds(370, 144, 86, 14);
        add(lblNewLabel_8);
        
        kor_mezo = new JTextField();
        kor_mezo.setBounds(493, 141, 30, 20);
        add(kor_mezo);
        kor_mezo.setColumns(10);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(39, 183, 1158, 14);
        add(separator);
        
        JLabel lblNewLabel_9 = new JLabel("Van vevői előírás?");
        lblNewLabel_9.setBounds(39, 223, 107, 14);
        add(lblNewLabel_9);
        
        String[] eloiras = {"-","Igen","Nem"};
        eloiras_box = new JComboBox<String>(eloiras);                      //eloiras
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
        lblNewLabel_11.setBounds(39, 389, 130, 14);
        add(lblNewLabel_11);
        
        mennyiseg_mezo = new JTextField();
        mennyiseg_mezo.setText("4");
        mennyiseg_mezo.setBounds(179, 386, 46, 20);
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
        
        JLabel lblNewLabel_14 = new JLabel("Vizsgálatot végezte");
        lblNewLabel_14.setBounds(269, 389, 121, 14);
        add(lblNewLabel_14);        
        
        JLabel lblNewLabel_15 = new JLabel("Vizsgálat eredménye");
        lblNewLabel_15.setBounds(156, 446, 130, 14);
        add(lblNewLabel_15);
        
        String[] eredmeny = {"-","OK","NOK"};
        eredmeny_box = new JComboBox<String>(eredmeny);                 //eredmeny
        eredmeny_box.addActionListener(new Vagy());
        eredmeny_box.setBounds(313, 442, 86, 22);
        add(eredmeny_box);
        
        JLabel lblNewLabel_16 = new JLabel("Zárolandó / SQA értesítés");
        lblNewLabel_16.setBounds(501, 446, 150, 14);
        add(lblNewLabel_16);
        
        zarolando_mezo = new JTextField();
        zarolando_mezo.setBounds(652, 443, 86, 20);
        add(zarolando_mezo);
        zarolando_mezo.setColumns(10);
        
        JLabel lblNewLabel_17 = new JLabel("Kék cimkével kerül gyártásba?");
        lblNewLabel_17.setBounds(786, 446, 191, 14);
        add(lblNewLabel_17);
        
        kekcimke_mezo = new JTextField();
        kekcimke_mezo.setBounds(987, 443, 86, 20);
        add(kekcimke_mezo);
        kekcimke_mezo.setColumns(10);
        
        JButton mentes_gomb = new JButton("Mentés");
        mentes_gomb.addActionListener(new Mentes());
        mentes_gomb.setBounds(562, 537, 89, 23);
        add(mentes_gomb);
        
        Utolso_sor sorszam = new Utolso_sor();
        int kovetkezo = Integer.parseInt(sorszam.utolso("qualitydb.Forraszthatosagi_vizsgalat"));
        id_mezo.setText(String.valueOf(kovetkezo + 1));
        
        JButton vissza_gomb = new JButton("Vissza");
        vissza_gomb.addActionListener(new Vissza());
        vissza_gomb.setBounds(1108, 57, 89, 23);
        add(vissza_gomb);
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        Date date = new Date();
        idopont_mezo.setText(formatter.format(date));
        
        JLabel lblNewLabel_18 = new JLabel("Beérkezett mennyiség");
        lblNewLabel_18.setBounds(39, 340, 130, 14);
        add(lblNewLabel_18);
        
        beerkezettdb_mezo = new JTextField();
        beerkezettdb_mezo.setBounds(179, 337, 46, 20);
        add(beerkezettdb_mezo);
        beerkezettdb_mezo.setColumns(10);
        
        String[] vizsgalat = {"-","Mártó forrasztás","Pasztázott PAD-re helyezés","Alkatrész pasztázás","Normál gyártási folyamat"};
        vizsgalat_box = new JComboBox<String>(vizsgalat);                                //vizsgalat
        vizsgalat_box.setBounds(627, 336, 570, 22);
        add(vizsgalat_box);
        
        JLabel lblNewLabel_19 = new JLabel("évnél idősebb");
        lblNewLabel_19.setBounds(533, 144, 101, 14);
        add(lblNewLabel_19);
        
        String[] ki = {"-","Kádár Zoltán","Ternák Sándor","Molnár József","Nagy Bálint"};
        ki_box = new JComboBox<String>(ki);
        ki_box.setBounds(409, 385, 202, 22);
        add(ki_box);
        
        id_mezo.setText(id);
        
        visszatolt(id);

    }
    
    class Enter implements KeyListener                                                                                                 //billentyűzet figyelő eseménykezelő, kiszámolja mennyit kell ellenőrizni
    {
        public void keyPressed (KeyEvent e) 
        {                
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                                                //egér mutató változtatása munka a háttérbenre
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_ENTER)                                                                                               //ha az entert nyomják le akkor hívódik meg
                {
                    try
                    {
                        Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                                                //egér mutató változtatása munka a háttérbenre
                        SQA_SQL lekerdezes = new SQA_SQL();
                        String valasztott = cikkszam_mezo.getText();                                                           //kiválasztott elem Stringé alakítása
                        String sql = "select DESCRIPTION \r\n"
                                + "from ifsapp.INVENTORY_PART\r\n"
                                + "where 3 = 3\r\n"
                                + "and PART_NO = '"+ valasztott +"' -- and PRIMARY_VENDOR_DB = 'Y'";
                        
                        
                        if(lekerdezes.beszallito(sql) == null)
                        {
                            JOptionPane.showMessageDialog(null, "Nem létezik ilyen cikkszám", "Hiba üzenet", 2);
                        }
                        else
                        {
                            megnevezes_mezo.setText(lekerdezes.beszallito(sql));
                            sql = "select second_commodity\r\n"
                                    + "from ifsapp.INVENTORY_PART\r\n"
                                    + "where 3 = 3 \r\n"
                                    + "and part_no = '"+ valasztott +"'";
                            
                            String projekt = lekerdezes.beszallito(sql);
                            projekt_mezo.setText(projekt);
                   
                        }
                        Foablak.frame.pack();
                        Foablak.frame.setCursor(null);                                                                                          //egér mutató alaphelyzetbe állítása
                    }
                    catch (Exception e1) 
                    {
                        e1.printStackTrace();
                        String hibauzenet = e1.toString();
                        Email hibakuldes = new Email();
                        hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                        JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                     //kivétel esetén kiírja a hibaüzenetet
                    }
                }            
                Foablak.frame.setCursor(null);                                                                                          //egér mutató alaphelyzetbe állítása      
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
    
    class Mentes implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                SQA_SQL ment = new SQA_SQL();
                String sql = "select * from qualitydb.Forraszthatosagi_vizsgalat where id = '"+ id_mezo.getText() +"'";
                String[] letezik = ment.tombvissza_sajat(sql);
                if(letezik.length > 0)
                {
                    sql = "update qualitydb.Forraszthatosagi_vizsgalat set Cikkszam = '"+ cikkszam_mezo.getText() +"', Megnevezes = '"+ megnevezes_mezo.getText() +"', Projekt = '"+ projekt_mezo.getText() +"',"
                            + "Gyartas_datuma = '"+ gyartas_mezo.getText() +"', Kora = '"+ kor_mezo.getText() +"', Jkv_sorszam = '"+ sorszam_mezo.getText() +"',"
                            + "Terulet ='"+  String.valueOf(terület_box.getSelectedItem()) +"', Mennyiseg = '"+ mennyiseg_mezo.getText() +"', Idopont = '"+ idopont_mezo.getText() +"', "
                            + "Vizsgalati_mod = '"+ String.valueOf(vizsgalat_box.getSelectedItem()) +"', Vizsgalatot_vegezte = '"+ String.valueOf(ki_box.getSelectedItem()) +"', Eredmeny = '"+ String.valueOf(eredmeny_box.getSelectedItem()) +"',"
                            + "Zarolando = '"+ zarolando_mezo.getText() +"', Kek_cimke = '"+ kekcimke_mezo.getText() +"', Beerkezett_db = '"+ beerkezettdb_mezo.getText() +"' where id = '"+ id_mezo.getText() +"'";
                }
                else
                {
                    sql = "insert into qualitydb.Forraszthatosagi_vizsgalat (Cikkszam,Megnevezes,Projekt,Gyartas_datuma,Kora,Jkv_sorszam,Terulet,Mennyiseg,Idopont,Vizsgalati_mod,"
                            + "Vizsgalatot_vegezte,Eredmeny,Zarolando,Kek_cimke,Beerkezett_db)  Values('"+ cikkszam_mezo.getText() +"','"+ megnevezes_mezo.getText() +"','"+ projekt_mezo.getText() +"',"
                            + "'"+ gyartas_mezo.getText() +"','"+ kor_mezo.getText() +"','"+ sorszam_mezo.getText() +"',"
                            + "'"+ String.valueOf(terület_box.getSelectedItem()) +"','"+ mennyiseg_mezo.getText() +"','"+ idopont_mezo.getText() +"',"
                            + "'"+ String.valueOf(vizsgalat_box.getSelectedItem()) +"','"+ String.valueOf(ki_box.getSelectedItem()) +"','"+ String.valueOf(eredmeny_box.getSelectedItem()) +"',"
                            + "'"+ zarolando_mezo.getText() +"','"+ kekcimke_mezo.getText() +"','"+ beerkezettdb_mezo.getText() +"')";
                }
                ment.mindenes(sql);
                JOptionPane.showMessageDialog(null, "Fasza", "Info", 1);                                                //kiírja a hibaüzenetet
            }
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                //kiírja a hibaüzenetet
            }
         }
    }
    
    public void visszatolt( String id)
    {
        Connection conn = null;
        Statement stmt = null;        
        try 
        {
          
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
            stmt = (Statement) conn.createStatement();
            String sql = "select * from qualitydb.Forraszthatosagi_vizsgalat where id = '"+ id +"'";
            stmt.execute(sql);
            ResultSet rs = stmt.getResultSet();
            if(rs.next())
            {
                cikkszam_mezo.setText(rs.getString(2));
                megnevezes_mezo.setText(rs.getString(3));
                projekt_mezo.setText(rs.getString(4));
                gyartas_mezo.setText(rs.getString(5));
                kor_mezo.setText(rs.getString(6));
                sorszam_mezo.setText(rs.getString(7));
                terület_box.setSelectedItem(rs.getString(8));
                mennyiseg_mezo.setText(rs.getString(11));
                idopont_mezo.setText(rs.getString(12));
                vizsgalat_box.setSelectedItem(rs.getString(13));
                ki_box.setSelectedItem(rs.getString(14));
                eredmeny_box.setSelectedItem(rs.getString(15));
                zarolando_mezo.setText(rs.getString(16));
                kekcimke_mezo.setText(rs.getString(17));
                beerkezettdb_mezo.setText(rs.getString(18));
              
            }
            stmt.close();
            conn.close();        
        }          
        catch (Exception e) 
        {
            e.printStackTrace();
            String hibauzenet = e.toString();
            Email hibakuldes = new Email();
            hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
            JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                     //kivétel esetén kiírja a hibaüzenetet
        } finally 
        {
           try 
           {
              if (stmt != null)
                  stmt.close();
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
               JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                //kivétel esetén kiírja a hibaüzenetet
           }  
        }
        //JOptionPane.showMessageDialog(null, "Kész", "Info", 1);
    }
    
    class Enter2 implements KeyListener                                                                                                 //billentyűzet figyelő eseménykezelő, kiszámolja mennyit kell ellenőrizni
    {
        public void keyPressed (KeyEvent e) 
        {                
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                                                //egér mutató változtatása munka a háttérbenre
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_ENTER)                                                                                               //ha az entert nyomják le akkor hívódik meg
                {
                    try
                    {
                        Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                                                //egér mutató változtatása munka a háttérbenre
                        visszatolt(id_mezo.getText());
                        Foablak.frame.setCursor(null);                                                                                          //egér mutató alaphelyzetbe állítása
                    }
                    catch (Exception e1) 
                    {
                        e1.printStackTrace();
                        String hibauzenet = e1.toString();
                        Email hibakuldes = new Email();
                        hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                        JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                     //kivétel esetén kiírja a hibaüzenetet
                    }
                }            
                Foablak.frame.setCursor(null);                                                                                          //egér mutató alaphelyzetbe állítása      
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
    
    class Vissza implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                                                //egér mutató változtatása munka a háttérbenre
                Forraszthatosagi_osszesito vizsgalat = new Forraszthatosagi_osszesito();
                JScrollPane ablak = new JScrollPane(vizsgalat);
                ablak.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                ablak.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                Foablak.frame.setContentPane(ablak);
                Foablak.frame.pack();
                Foablak.frame.setCursor(null);                                                                                          //egér mutató alaphelyzetbe állítása
            }
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                     //kivétel esetén kiírja a hibaüzenetet
            }
         }
    }
    
    class Vagy implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                                                //egér mutató változtatása munka a háttérbenre
                String valasztott = String.valueOf(eredmeny_box.getSelectedItem());                                                           //kiválasztott elem Stringé alakítása
                if(valasztott.equals("OK"))
                {
                    zarolando_mezo.setText("Nem");
                    kekcimke_mezo.setText("Nem");
                }
                if(valasztott.equals("NOK"))
                {
                    zarolando_mezo.setText("Igen");
                    kekcimke_mezo.setText("Nem");
                }
                Foablak.frame.repaint();
                Foablak.frame.revalidate();
                Foablak.frame.setCursor(null);                                                                                          //egér mutató alaphelyzetbe állítása
            }
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                     //kivétel esetén kiírja a hibaüzenetet
            }
         }
    }
}
