import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTextField;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.SwingConstants;

import com.spire.data.table.DataTable;
import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;

public class SQA_bevitel extends JPanel {
    private JTextField datum_mezo;
    private JTextField gyarto_mezo;
    private JTextField beszallito_mezo;
    private JTextField kontakt_mezo;
    private JTextField hibasdb_mezo;
    private JTextField deviza_mezo;
    private JTextField egysegar_mezo;
    private JTextField osszertek_mezo;
    private JTextField megjelenes_ido;
    private JTextField reklamacio_ido;
    private JTextField karterites_mezo;
    private JTextField belsokoltseg_mezo;
    private JTextField veszteseg_mezo;
    private JComboBox<String> cikkszam_box;
    private SQA_SQL lekerdezes = new SQA_SQL();
    private JTextField projekt_mezo;
    private JTextField id_mezo;
    private JTextField tertenes_mezo;
    private JTextField lezarido_mezo;
    private JTextField futoid_mezo;
    private JTextField link_mezo;
    private JTextField nyitva_mezo;
    private JComboBox<String> intezkedes_box;
    private String excel_helye = "\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\SQA\\Kontakt lista.xlsx";
    private Workbook excel = new Workbook();
    private Worksheet sheet;
    private DataTable datatable = new DataTable();
    private JComboBox<String> indito_box;
    private JComboBox<String> fajta_box;
    private JComboBox<String> deviza_box;
    private JTextArea hibaleiras_mezo;
    private JTextArea intezkedes_mezo;
    private JTextArea valasz_mezo;
    private JTextArea statusz_mezo;
    private JTextArea gyokerok_mezo;
     

    /**
     * Create the panel.
     */
    public SQA_bevitel() {
        this.setPreferredSize(new Dimension(1200, 999));
        Foablak.meretek.setSize(1250, 999);
        setLayout(null);
        
        excel.loadFromFile(excel_helye);
        sheet = excel.getWorksheets().get(0);
        datatable = sheet.exportDataTable();
        
        JLabel lblNewLabel = new JLabel("Beszállítói reklamációk kezelése");
        lblNewLabel.setForeground(new Color(0, 0, 255));
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 13));
        lblNewLabel.setBounds(501, 11, 253, 14);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Dátum");
        lblNewLabel_1.setBounds(43, 132, 46, 14);
        add(lblNewLabel_1);
        
        datum_mezo = new JTextField();
        datum_mezo.setBounds(102, 129, 86, 20);
        add(datum_mezo);
        datum_mezo.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("Cikkszám");
        lblNewLabel_2.setBounds(251, 132, 66, 14);
        add(lblNewLabel_2);
        
        cikkszam_box = new JComboBox<String>(lekerdezes.cikkszamok());                             //lekerdezes.cikkszamok()
        cikkszam_box.addActionListener(new Elem_valaszto());
        cikkszam_box.setBounds(343, 128, 383, 22);
        add(cikkszam_box);
        
        JLabel lblNewLabel_3 = new JLabel("Alapanyag fajta");
        lblNewLabel_3.setBounds(780, 132, 95, 14);
        add(lblNewLabel_3);
        
        String[] fajtak = {"PCB","SMD","THT","Műanyag","Fém","Csomagolóanyag","Üveg","Egyéb"};
        fajta_box = new JComboBox<String>(fajtak);                                      //fajtak
        fajta_box.setBounds(885, 128, 147, 22);
        add(fajta_box);
        
        JLabel lblNewLabel_4 = new JLabel("Gyártó");
        lblNewLabel_4.setBounds(43, 178, 46, 14);
        add(lblNewLabel_4);
        
        gyarto_mezo = new JTextField();
        gyarto_mezo.setBounds(99, 175, 184, 20);
        add(gyarto_mezo);
        gyarto_mezo.setColumns(10);
        
        JLabel lblNewLabel_5 = new JLabel("Beszállító");
        lblNewLabel_5.setBounds(320, 178, 66, 14);
        add(lblNewLabel_5);
        
        beszallito_mezo = new JTextField();
        beszallito_mezo.setBounds(396, 175, 278, 20);
        add(beszallito_mezo);
        beszallito_mezo.setColumns(10);
        
        JLabel lblNewLabel_6 = new JLabel("Kontakt");
        lblNewLabel_6.setBounds(856, 178, 51, 14);
        add(lblNewLabel_6);
        
        kontakt_mezo = new JTextField();
        kontakt_mezo.setBounds(911, 175, 253, 20);
        add(kontakt_mezo);
        kontakt_mezo.setColumns(10);
        
        JLabel lblNewLabel_7 = new JLabel("Hiba leírása");
        lblNewLabel_7.setBounds(42, 236, 75, 14);
        add(lblNewLabel_7);
        
        hibaleiras_mezo = new JTextArea();
        hibaleiras_mezo.setBounds(127, 231, 218, 88);
        add(hibaleiras_mezo);
        
        JLabel lblNewLabel_8 = new JLabel("Intézkedés");
        lblNewLabel_8.setBounds(396, 261, 66, 14);
        add(lblNewLabel_8);
        
        String[] intezkedes = {"Reklamáció","Tájékoztatás"};
        intezkedes_box = new JComboBox<String>(intezkedes);                           //intezkedes
        intezkedes_box.addActionListener(new Vagy());
        intezkedes_box.setBounds(217, 92, 157, 22);
        add(intezkedes_box);
        
        JLabel lblNewLabel_9 = new JLabel("Hibás db");
        lblNewLabel_9.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_9.setBounds(726, 236, 75, 14);
        add(lblNewLabel_9);
        
        hibasdb_mezo = new JTextField();
        hibasdb_mezo.setText("0");
        hibasdb_mezo.setBounds(811, 233, 51, 20);
        add(hibasdb_mezo);
        hibasdb_mezo.setColumns(10);
        
        JLabel lblNewLabel_10 = new JLabel("Deviza");
        lblNewLabel_10.setBounds(929, 236, 46, 14);
        add(lblNewLabel_10);
        
        deviza_mezo = new JTextField();
        deviza_mezo.setText("0");
        deviza_mezo.setBounds(1009, 233, 86, 20);
        add(deviza_mezo);
        deviza_mezo.setColumns(10);
        
        String[] deviza = {"EUR","USD","CHF","HUF"};
        deviza_box = new JComboBox<String>(deviza);                     //deviza
        deviza_box.setBounds(1105, 232, 59, 22);
        add(deviza_box);
        
        JLabel lblNewLabel_11 = new JLabel("Egységár");
        lblNewLabel_11.setBounds(929, 271, 59, 14);
        add(lblNewLabel_11);
        
        egysegar_mezo = new JTextField();
        egysegar_mezo.setText("0");
        egysegar_mezo.setBounds(1009, 268, 86, 20);
        add(egysegar_mezo);
        egysegar_mezo.setColumns(10);
        
        JLabel lblNewLabel_12 = new JLabel("Ft");
        lblNewLabel_12.setBounds(1105, 271, 46, 14);
        add(lblNewLabel_12);
        
        JLabel lblNewLabel_13 = new JLabel("Összérték");
        lblNewLabel_13.setBounds(929, 305, 70, 14);
        add(lblNewLabel_13);
        
        osszertek_mezo = new JTextField();
        osszertek_mezo.setText("0");
        osszertek_mezo.setBounds(1009, 299, 86, 20);
        add(osszertek_mezo);
        osszertek_mezo.setColumns(10);
        
        JLabel lblNewLabel_14 = new JLabel("Ft");
        lblNewLabel_14.setBounds(1105, 302, 46, 14);
        add(lblNewLabel_14);
        
        JLabel lblNewLabel_15 = new JLabel("Hiba megjelenése");
        lblNewLabel_15.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_15.setBounds(685, 271, 117, 14);
        add(lblNewLabel_15);
        
        megjelenes_ido = new JTextField();
        megjelenes_ido.setBounds(812, 268, 86, 20);
        add(megjelenes_ido);
        megjelenes_ido.setColumns(10);
        
        JLabel lblNewLabel_16 = new JLabel("Reklamáció kezdete");
        lblNewLabel_16.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_16.setBounds(685, 302, 117, 14);
        add(lblNewLabel_16);
        
        reklamacio_ido = new JTextField();
        reklamacio_ido.setBounds(812, 299, 86, 20);
        add(reklamacio_ido);
        reklamacio_ido.setColumns(10);
        
        JLabel lblNewLabel_17 = new JLabel("Gyökérok");
        lblNewLabel_17.setBounds(404, 360, 66, 14);
        add(lblNewLabel_17);
        
        valasz_mezo = new JTextArea();
        valasz_mezo.setBounds(168, 355, 218, 74);
        add(valasz_mezo);
        
        JLabel lblNewLabel_18 = new JLabel("Beszállítói válasz");
        lblNewLabel_18.setBounds(43, 360, 95, 14);
        add(lblNewLabel_18);
        
        gyokerok_mezo = new JTextArea();
        gyokerok_mezo.setBounds(480, 355, 194, 74);
        add(gyokerok_mezo);
        
        JLabel lblNewLabel_19 = new JLabel("Beszállítói kártérítés");
        lblNewLabel_19.setBounds(737, 360, 117, 14);
        add(lblNewLabel_19);
        
        JLabel lblNewLabel_20 = new JLabel("Belső költség");
        lblNewLabel_20.setBounds(737, 390, 117, 14);
        add(lblNewLabel_20);
        
        JLabel lblNewLabel_21 = new JLabel("Veszteség");
        lblNewLabel_21.setBounds(737, 421, 86, 14);
        add(lblNewLabel_21);
        
        karterites_mezo = new JTextField();
        karterites_mezo.setText("0");
        karterites_mezo.setBounds(864, 357, 86, 20);
        add(karterites_mezo);
        karterites_mezo.setColumns(10);
        
        belsokoltseg_mezo = new JTextField();
        belsokoltseg_mezo.setText("0");
        belsokoltseg_mezo.setBounds(864, 387, 86, 20);
        add(belsokoltseg_mezo);
        belsokoltseg_mezo.setColumns(10);
        
        veszteseg_mezo = new JTextField();
        veszteseg_mezo.setText("0");
        veszteseg_mezo.setBounds(864, 418, 86, 20);
        add(veszteseg_mezo);
        veszteseg_mezo.setColumns(10);
        
        JLabel lblNewLabel_22 = new JLabel("Inditotta");
        lblNewLabel_22.setBounds(46, 49, 59, 14);
        add(lblNewLabel_22);
        
        String[] nevek = {"Schweighardt Róbert", "Tóth Zoltán","Horváth Balázs"};
        indito_box = new JComboBox<String>(nevek);                             //nevek
        indito_box.setBounds(127, 45, 218, 22);
        add(indito_box);
        
        JButton mentes_gomb = new JButton("Mentés");
        mentes_gomb.addActionListener(new Mentes());
        mentes_gomb.setBounds(499, 516, 89, 23);
        add(mentes_gomb);
        
        JLabel lblNewLabel_23 = new JLabel("Reklamáció státusza");
        lblNewLabel_23.setBounds(50, 605, 129, 14);
        add(lblNewLabel_23);
        
        statusz_mezo = new JTextArea();
        statusz_mezo.setBounds(189, 600, 316, 204);
        add(statusz_mezo);
        
        JLabel lblNewLabel_24 = new JLabel("Projekt");
        lblNewLabel_24.setBounds(704, 178, 46, 14);
        add(lblNewLabel_24);
        
        projekt_mezo = new JTextField();
        projekt_mezo.setBounds(760, 175, 86, 20);
        add(projekt_mezo);
        projekt_mezo.setColumns(10);
        
        JLabel lblNewLabel_25 = new JLabel("Ft");
        lblNewLabel_25.setBounds(960, 360, 46, 14);
        add(lblNewLabel_25);
        
        JLabel lblNewLabel_26 = new JLabel("Ft");
        lblNewLabel_26.setBounds(960, 390, 46, 14);
        add(lblNewLabel_26);
        
        JLabel lblNewLabel_27 = new JLabel("Ft");
        lblNewLabel_27.setBounds(960, 421, 46, 14);
        add(lblNewLabel_27);
        
        JLabel lblNewLabel_28 = new JLabel("ID");
        lblNewLabel_28.setBounds(43, 96, 23, 14);
        add(lblNewLabel_28);
        
        id_mezo = new JTextField();
        id_mezo.addKeyListener(new Enter());
        id_mezo.setBounds(71, 93, 46, 20);
        add(id_mezo);
        id_mezo.setColumns(10);
        
        JLabel lblNewLabel_29 = new JLabel("Utolsó történés ideje");
        lblNewLabel_29.setBounds(515, 605, 139, 14);
        add(lblNewLabel_29);
        
        tertenes_mezo = new JTextField();
        tertenes_mezo.setBounds(640, 602, 86, 20);
        add(tertenes_mezo);
        tertenes_mezo.setColumns(10);
        
        JButton valtozas_gomb = new JButton("Válzotás mentése");
        valtozas_gomb.setBounds(571, 630, 120, 23);
        add(valtozas_gomb);
        
        setBackground(Foablak.hatter_szine);
        
        JLabel lblNewLabel_30 = new JLabel("Reklamáció lezásárának ideje");
        lblNewLabel_30.setBounds(379, 856, 179, 14);
        add(lblNewLabel_30);
        
        lezarido_mezo = new JTextField();
        lezarido_mezo.setBounds(568, 853, 86, 20);
        add(lezarido_mezo);
        lezarido_mezo.setColumns(10);
        
        JButton lezar_gomb = new JButton("Lezárás");
        lezar_gomb.setBounds(477, 881, 89, 23);
        add(lezar_gomb);
        
        JLabel lblNewLabel_31 = new JLabel("Futó ID");
        lblNewLabel_31.setBounds(406, 96, 46, 14);
        add(lblNewLabel_31);
        
        futoid_mezo = new JTextField();
        futoid_mezo.setBounds(462, 93, 86, 20);
        add(futoid_mezo);
        futoid_mezo.setColumns(10);
        
        JLabel lblNewLabel_32 = new JLabel("8D");
        lblNewLabel_32.setBounds(43, 474, 46, 14);
        add(lblNewLabel_32);
        
        JButton d_gomb = new JButton("Csatolás");
        d_gomb.setBounds(71, 470, 89, 23);
        add(d_gomb);
        
        JLabel lblNewLabel_33 = new JLabel("Credit Note");
        lblNewLabel_33.setBounds(199, 474, 66, 14);
        add(lblNewLabel_33);
        
        JButton nc_gomb = new JButton("Csatolás");
        nc_gomb.setBounds(275, 470, 89, 23);
        add(nc_gomb);
        
        JLabel lblNewLabel_34 = new JLabel("Mappa helye");
        lblNewLabel_34.setBounds(404, 474, 86, 14);
        add(lblNewLabel_34);
        
        link_mezo = new JTextField();
        link_mezo.setText("\\\\\\\\172.20.22.7\\\\minosegbiztositas\\\\SQA\\\\");
        link_mezo.setBounds(480, 471, 615, 20);
        add(link_mezo);
        link_mezo.setColumns(10);
        
        JLabel lblNewLabel_35 = new JLabel("Ennyi napja van nyitva");
        lblNewLabel_35.setBounds(536, 712, 139, 14);
        add(lblNewLabel_35);
        
        nyitva_mezo = new JTextField();
        nyitva_mezo.setEditable(false);
        nyitva_mezo.setBounds(668, 709, 86, 20);
        add(nyitva_mezo);
        nyitva_mezo.setColumns(10);
        
        JLabel lblNewLabel_36 = new JLabel("Űrlap jellege");
        lblNewLabel_36.setBounds(144, 96, 75, 14);
        add(lblNewLabel_36);
        
        intezkedes_mezo = new JTextArea();
        intezkedes_mezo.setBounds(462, 231, 215, 88);
        add(intezkedes_mezo);
        
        JLabel lblNewLabel_37 = new JLabel("Belső");
        lblNewLabel_37.setBounds(404, 236, 46, 14);
        add(lblNewLabel_37);
        
        JButton btnNewButton = new JButton("Újranyitás");
        btnNewButton.setBounds(477, 932, 89, 23);
        add(btnNewButton);
        
        Utolso_sor sorszam = new Utolso_sor();
        int kovetkezo = Integer.parseInt(sorszam.utolso("qualitydb.SQA_reklamaciok"));
        id_mezo.setText(String.valueOf(kovetkezo + 1));

    }
    
    class Elem_valaszto implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                                                //egér mutató változtatása munka a háttérbenre
                String valasztott = String.valueOf(cikkszam_box.getSelectedItem());                                                           //kiválasztott elem Stringé alakítása
                String[] cikk = valasztott.split(" ");
                String sql = "select ifsapp.SUPPLIER_API.Get_Vendor_Name(VENDOR_NO)\r\n"
                        + "from ifsapp.PURCHASE_PART_SUPPLIER\r\n"
                        + "where 3 = 3\r\n"
                        + "and PART_NO = '"+ cikk[0] +"' and PRIMARY_VENDOR_DB = 'Y'";
                beszallito_mezo.setText(lekerdezes.beszallito(sql));
                
                sql = "select ifsapp.MANUFACTURER_INFO_API.Get_Name(MANUFACTURER_NO)\r\n"
                        + "from ifsapp.PART_MANUFACTURER\r\n"
                        + "where 3 = 3\r\n"
                        + "and PART_NO = '"+ cikk[0] +"' and  PREFERRED_MANUFACTURER_DB = 'TRUE'";
                gyarto_mezo.setText(lekerdezes.beszallito(sql));
               
                sql = "select second_commodity\r\n"
                        + "from ifsapp.INVENTORY_PART\r\n"
                        + "where 3 = 3 \r\n"
                        + "and part_no = '"+ cikk[0] +"'";
                projekt_mezo.setText(lekerdezes.beszallito(sql));
                String kontaktok = "";
                gyarto_mezo.setForeground(Color.black);
                beszallito_mezo.setForeground(Color.black);
                kontakt_mezo.setText("");
                for(int szamlalo = 0; szamlalo < datatable.getRows().size(); szamlalo++)
                {
                    if(datatable.getRows().get(szamlalo).getString(1).contains(beszallito_mezo.getText()))
                    {
                        kontaktok += datatable.getRows().get(szamlalo).getString(2) +"; ";
                    }
                }
                if(kontaktok.equals(""))
                {
                    for(int szamlalo = 0; szamlalo < datatable.getRows().size(); szamlalo++)
                    {
                        if(datatable.getRows().get(szamlalo).getString(1).contains(gyarto_mezo.getText()))
                        {
                            kontaktok += datatable.getRows().get(szamlalo).getString(2) +"; ";
                        }
                    }
                    if(kontaktok.equals("")){}                    
                    else
                    {
                        gyarto_mezo.setForeground(Color.BLUE);
                        kontakt_mezo.setText(kontaktok);
                    }
                }
                else
                {
                    int szam = 0;
                    for(int szamlalo = 0; szamlalo < datatable.getRows().size(); szamlalo++)
                    {
                        if(datatable.getRows().get(szamlalo).getString(1).contains(gyarto_mezo.getText()))
                        {
                            kontaktok += datatable.getRows().get(szamlalo).getString(2) +"; ";
                            szam++;
                        }
                    }
                    if(szam > 0)
                    {
                        gyarto_mezo.setForeground(Color.BLUE);
                    }
                    beszallito_mezo.setForeground(Color.BLUE);
                    kontakt_mezo.setText(kontaktok);
                }
                
                
                Foablak.frame.setCursor(null);                                                                                          //egér mutató alaphelyzetbe állítása
            }
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet2 = e1.toString();
                JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);                                                     //kivétel esetén kiírja a hibaüzenetet
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
                String valasztott = String.valueOf(intezkedes_box.getSelectedItem());                                                           //kiválasztott elem Stringé alakítása
                if(valasztott.equals("Tájékoztatás"))
                {
                    futoid_mezo.setEditable(false);
                }
                if(valasztott.equals("Reklamáció"))
                {
                    futoid_mezo.setEditable(true);
                }
                Foablak.frame.repaint();
                Foablak.frame.revalidate();
                Foablak.frame.setCursor(null);                                                                                          //egér mutató alaphelyzetbe állítása
            }
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet2 = e1.toString();
                JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);                                                     //kivétel esetén kiírja a hibaüzenetet
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
                String sql = "insert into qualitydb.SQA_reklamaciok (ID,Futo_ID,Datum,Inditotta,Vagy,Cikkszam, Fajta,Gyarto,Beszallito,Projekt,Kontakt,"
                        + "Hibaleiras,Belso_intezkedes,Hibasdb,Megjelenesido,Reklamacioido,Deviza,Egysegar,Osszertek,Beszallitoi_valasz,Gyokerok,"
                        + "Beszallitoi_karterites,Belso_koltseg,VEszteseg,Mappa_helye) "
                        + "Value('"+ id_mezo.getText()+"','"+futoid_mezo.getText()+"','"+datum_mezo.getText()+"','"+ String.valueOf(indito_box.getSelectedItem())
                        +"','"+ String.valueOf(intezkedes_box.getSelectedItem())+"','"+ String.valueOf(cikkszam_box.getSelectedItem())+"','"+ String.valueOf(fajta_box.getSelectedItem())
                        +"','"+gyarto_mezo.getText()+"','"+beszallito_mezo.getText()+"','"+projekt_mezo.getText()+"','"+kontakt_mezo.getText()+"','"+hibaleiras_mezo.getText()
                        +"','"+intezkedes_mezo.getText()+"','"+Integer.parseInt(hibasdb_mezo.getText())+"','"+megjelenes_ido.getText()+"','"+reklamacio_ido.getText()
                        +"','"+deviza_mezo.getText()+" "+String.valueOf(deviza_box.getSelectedItem())+"','"+egysegar_mezo.getText()+"','"+osszertek_mezo.getText()
                        +"','"+valasz_mezo.getText()+"','"+gyokerok_mezo.getText()+"','"+karterites_mezo.getText()+"','"+belsokoltseg_mezo.getText()+"','"+veszteseg_mezo.getText()
                        +"',' "+link_mezo.getText()+"')";
                        
                lekerdezes.mindenes(sql);
                futoid_mezo.setText("");
                datum_mezo.setText("");
                gyarto_mezo.setText("");
                beszallito_mezo.setText("");
                projekt_mezo.setText("");
                kontakt_mezo.setText("");
                hibaleiras_mezo.setText("");
                intezkedes_mezo.setText("");
                hibasdb_mezo.setText("");
                megjelenes_ido.setText("");
                reklamacio_ido.setText("");
                deviza_mezo.setText("0");
                egysegar_mezo.setText("0");
                osszertek_mezo.setText("0");
                valasz_mezo.setText("");
                gyokerok_mezo.setText("");
                karterites_mezo.setText("0");
                belsokoltseg_mezo.setText("0");
                veszteseg_mezo.setText("0");
                Foablak.frame.setCursor(null);                                                                                          //egér mutató alaphelyzetbe állítása
            }
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet2 = e1.toString();
                JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);                                                     //kivétel esetén kiírja a hibaüzenetet
            }
         }
    }
    
    class Enter implements KeyListener                                                                                                 //billentyűzet figyelő eseménykezelő, kiszámolja mennyit kell ellenőrizni
    {
        public void keyPressed (KeyEvent e) 
        {    
            try 
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                                                //egér mutató változtatása munka a háttérbenre
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_ENTER)                                                                                               //ha az entert nyomják le akkor hívódik meg
                {
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
                          System.out.println(e);
                          String hibauzenet2 = e.toString();
                          JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
                    }
                    conn = (Connection) DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
                    stmt = (Statement) conn.createStatement();
                    String SQL = "select * from qualitydb.SQA_reklamaciok where id = '"+ id_mezo.getText() +"'";
                    ResultSet rs = stmt.executeQuery(SQL);
                    if(rs.next())
                    {
                        futoid_mezo.setText(rs.getString(2));
                        datum_mezo.setText(rs.getString(3));
                        indito_box.setSelectedItem(rs.getString(4));
                        intezkedes_box.setSelectedItem(rs.getString(5));
                        cikkszam_box.setSelectedItem(rs.getString(6));
                        fajta_box.setSelectedItem(rs.getString(7));
                        gyarto_mezo.setText(rs.getString(8));
                        beszallito_mezo.setText(rs.getString(9));
                        projekt_mezo.setText(rs.getString(10));
                        kontakt_mezo.setText(rs.getString(11));
                        hibaleiras_mezo.setText(rs.getString(12));
                        intezkedes_mezo.setText(rs.getString(13));
                        hibasdb_mezo.setText(rs.getString(14));
                        megjelenes_ido.setText(rs.getString(15));
                        reklamacio_ido.setText(rs.getString(16));
                        deviza_mezo.setText(rs.getString(17));
                        egysegar_mezo.setText(rs.getString(18));
                        osszertek_mezo.setText(rs.getString(19));
                        valasz_mezo.setText(rs.getString(20));
                        gyokerok_mezo.setText(rs.getString(21));
                        karterites_mezo.setText(rs.getString(22));
                        belsokoltseg_mezo.setText(rs.getString(23));
                        veszteseg_mezo.setText(rs.getString(24));
                        System.out.println(rs.getString(25));
                        Desktop.getDesktop().open(new File("\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\SQA\\Kontakt lista.xlsx"));                            //megnyitja az excelben szereplő helyen levő infó fájlt         
                    }
                    stmt.close();
                    conn.close();
                            
                    } 
                    catch (SQLException e1) 
                    {
                       e1.printStackTrace();
                    } 
                    catch (Exception e1) 
                    {
                       e1.printStackTrace();
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
                }
                Foablak.frame.setCursor(null);                                                                                          //egér mutató alaphelyzetbe állítása
            } 
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
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
