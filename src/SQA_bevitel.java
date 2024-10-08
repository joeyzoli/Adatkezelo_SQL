import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import com.spire.data.table.DataTable;
import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JSeparator;


public class SQA_bevitel extends JPanel {
    private JTextField datum_mezo;
    private JTextField hibasdb_mezo;
    private JTextField deviza_mezo;
    private JTextField osszertek_mezo;
    private JTextField megjelenes_ido;
    private JTextField reklamacio_ido;
    private JTextField karterites_mezo;
    private JTextField belsokoltseg_mezo;
    private JTextField veszteseg_mezo;
    private SQA_SQL lekerdezes = new SQA_SQL();
    private JTextField id_mezo;
    private JTextField tortenesideje_mezo;
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
    private JCheckBox d_csekk;
    private JCheckBox cn_csekk;
    private JTextField cikkszam_mezo;
    private JTable table;
    private DefaultTableModel modell;
    private File link;
    private int valasztott = 0;
    private int tombmeret;
    private String[] hibaleiras;
    private String[] belsointezkedes;
    private String[] valasz;
    private String[] gyokerok;
    private String[] hibasdb;
    private String[] megjelenes;
    private String[] rekkezdet;
    private String[] osszertek;
    private String[] karterites;
    private String[] belsokoltseg;
    private String[] veszteseg;
    private String[] deviza;
    private String[] cseredatum;
    private String beszallito;
    private JComboBox<String> comboBox1;
    private ArrayList<String> beszallitok = new ArrayList<String>();
    private ArrayList<String> gyartok = new ArrayList<String>();
    private int letezik = 0;
    private JTextField mindenertek_mezo;
    private JTextField cseredatum_mezo;
    private String cimzettek = "csader.zsolt@veas.videoton.hu,etl.csaba@veas.videoton.hu,ternak.sandor@veas.videoton.hu,kadar.zoltan@veas.videoton.hu,molnar.jozsef@veas.videoton.hu,nagy.balint@veas.videoton.hu,"
            + "rabine.anita@veas.videoton.hu,fekete.mercedesz@veas.videoton.hu,QA-Assy@veas.videoton.hu";
    private String cc = "schweighardt.robert@veas.videoton.hu,horvath.balazs@veas.videoton.hu,toth.zoltan@veas.videoton.hu";
    
     

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
        lblNewLabel_2.setBounds(588, 96, 66, 14);
        add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("Alapanyag fajta");
        lblNewLabel_3.setBounds(43, 180, 95, 14);
        add(lblNewLabel_3);
        
        String[] fajtak = {"PCB","SMD","THT","Műanyag","Fém","Csomagolóanyag","Üveg","Egyéb"};
        fajta_box = new JComboBox<String>(fajtak);                                      //fajtak
        fajta_box.setBounds(148, 176, 147, 22);
        add(fajta_box);
        
        JLabel lblNewLabel_7 = new JLabel("Hiba leírása");
        lblNewLabel_7.setBounds(42, 236, 75, 14);
        add(lblNewLabel_7);
        
        hibaleiras_mezo = new JTextArea();
        hibaleiras_mezo.setLineWrap(true);
        hibaleiras_mezo.setWrapStyleWord(true);
        hibaleiras_mezo.setBounds(127, 231, 218, 88);
        add(hibaleiras_mezo);
        
        JLabel lblNewLabel_8 = new JLabel("Intézkedés");
        lblNewLabel_8.setBounds(396, 261, 66, 14);
        add(lblNewLabel_8);
        
        String[] intezkedes = {"Reklamáció","Egyéb"};
        intezkedes_box = new JComboBox<String>(intezkedes);                           //intezkedes
        //intezkedes_box.addActionListener(new Vagy());
        intezkedes_box.setBounds(217, 92, 157, 22);
        add(intezkedes_box);
        
        JLabel lblNewLabel_9 = new JLabel("Hibás db");
        lblNewLabel_9.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_9.setBounds(726, 236, 75, 14);
        add(lblNewLabel_9);
        
        hibasdb_mezo = new JTextField();
        hibasdb_mezo.addKeyListener(new Szamol());
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
        
        String[] deviza = {"EUR","USD","CHF","HUF","GBP","JPY"};
        deviza_box = new JComboBox<String>(deviza);                     //deviza
        deviza_box.setBounds(1105, 232, 59, 22);
        add(deviza_box);
        
        JLabel lblNewLabel_13 = new JLabel("Érték");
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
        valasz_mezo.setLineWrap(true);
        valasz_mezo.setWrapStyleWord(true);
        valasz_mezo.setBounds(168, 355, 218, 74);
        add(valasz_mezo);
        
        JLabel lblNewLabel_18 = new JLabel("Beszállítói válasz");
        lblNewLabel_18.setBounds(43, 360, 95, 14);
        add(lblNewLabel_18);
        
        gyokerok_mezo = new JTextArea();
        gyokerok_mezo.setLineWrap(true);
        gyokerok_mezo.setWrapStyleWord(true);
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
        indito_box = new JComboBox<String>(nevek);                                       //nevek
        indito_box.setBounds(127, 45, 218, 22);
        add(indito_box);
        
        JButton mentes_gomb = new JButton("Mentés");
        mentes_gomb.addActionListener(new Mentes());
        mentes_gomb.setBounds(501, 538, 89, 23);
        add(mentes_gomb);
        
        JLabel lblNewLabel_23 = new JLabel("Reklamáció státusza");
        lblNewLabel_23.setBounds(50, 605, 129, 14);
        add(lblNewLabel_23);
        
        statusz_mezo = new JTextArea();
        statusz_mezo.setLineWrap(true);
        statusz_mezo.setWrapStyleWord(true);
        statusz_mezo.setBounds(189, 600, 316, 204);
        add(statusz_mezo);
        
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
        lblNewLabel_29.setBounds(535, 605, 139, 14);
        add(lblNewLabel_29);
        
        tortenesideje_mezo = new JTextField();
        tortenesideje_mezo.setBounds(668, 602, 86, 20);
        add(tortenesideje_mezo);
        tortenesideje_mezo.setColumns(10);
        
        JButton valtozas_gomb = new JButton("Válzotás mentése");
        valtozas_gomb.addActionListener(new Tortenet());
        valtozas_gomb.setBounds(552, 630, 139, 23);
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
        lezar_gomb.addActionListener(new Lezaras());
        lezar_gomb.setBounds(565, 884, 89, 23);
        add(lezar_gomb);
        
        JLabel lblNewLabel_31 = new JLabel("Futó ID");
        lblNewLabel_31.setBounds(406, 96, 46, 14);
        add(lblNewLabel_31);
        
        futoid_mezo = new JTextField();
        futoid_mezo.setBounds(462, 93, 86, 20);
        add(futoid_mezo);
        futoid_mezo.setColumns(10);
        
        JLabel lblNewLabel_32 = new JLabel("8D");
        lblNewLabel_32.setBounds(43, 474, 23, 14);
        add(lblNewLabel_32);
        
        JLabel lblNewLabel_33 = new JLabel("Credit Note");
        lblNewLabel_33.setBounds(127, 474, 66, 14);
        add(lblNewLabel_33);
        
        JLabel lblNewLabel_34 = new JLabel("Mappa helye");
        lblNewLabel_34.setBounds(251, 474, 86, 14);
        add(lblNewLabel_34);
        
        Utolso_sor sorszam = new Utolso_sor();
        int kovetkezo = Integer.parseInt(sorszam.utolso("qualitydb.SQA_reklamaciok"));
        id_mezo.setText(String.valueOf(kovetkezo + 1));
        
        link_mezo = new JTextField();
        link_mezo.setText("2024\\"+ id_mezo.getText());            //\\10.1.0.11\\minosegbiztositas\\SQA\\reklamációk\\
        link_mezo.setBounds(343, 471, 752, 20);
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
        intezkedes_mezo.setLineWrap(true);
        intezkedes_mezo.setWrapStyleWord(true);
        intezkedes_mezo.setBounds(462, 231, 215, 88);
        add(intezkedes_mezo);
        
        JLabel lblNewLabel_37 = new JLabel("Belső");
        lblNewLabel_37.setBounds(404, 236, 46, 14);
        add(lblNewLabel_37);
        
        JButton ujranyit_gomb = new JButton("Újranyitás");
        ujranyit_gomb.addActionListener(new Ujranyit());
        ujranyit_gomb.setBounds(712, 884, 89, 23);
        add(ujranyit_gomb);

        d_csekk = new JCheckBox("");
        d_csekk.setBounds(71, 470, 23, 23);
        add(d_csekk);
        
        cn_csekk = new JCheckBox("");
        cn_csekk.setBounds(199, 470, 23, 23);
        add(cn_csekk);
        
        JButton vissza_gomb = new JButton("Vissza");
        vissza_gomb.addActionListener(new Vissza());
        vissza_gomb.setBounds(1075, 11, 89, 23);
        add(vissza_gomb);
        
        JButton hozzaad_gomb = new JButton("Hozzáad");
        hozzaad_gomb.addActionListener(new Elem_valaszto());
        hozzaad_gomb.setBounds(1075, 92, 89, 23);
        add(hozzaad_gomb);
        setBackground(Foablak.hatter_szine);
        
        JLabel lblNewLabel_38 = new JLabel("Excel export");
        lblNewLabel_38.setBounds(536, 790, 86, 14);
        add(lblNewLabel_38);
        
        JButton excel_gomb = new JButton("Excel");
        excel_gomb.addActionListener(new Excel());
        excel_gomb.setBounds(640, 786, 89, 23);
        add(excel_gomb);
        
        cikkszam_mezo = new JTextField();
        cikkszam_mezo.setBounds(689, 93, 347, 20);
        add(cikkszam_mezo);
        cikkszam_mezo.setColumns(10);
        
        modell = new DefaultTableModel();
        modell.setColumnIdentifiers(new Object[]{"Cikkszám", "Megnevezés", "Gyártó", "Beszállító", "Projekt", "Kontakt","Egységár"});
        table = new JTable();
        table.setModel(modell);
        table.getSelectionModel().addListSelectionListener(new Sorvalaszto());
        JScrollPane gorgeto = new JScrollPane(table);
        gorgeto.setBounds(343, 132, 821, 93);
        add(gorgeto);
        
        JButton mappamegnyit_gomb = new JButton("Mappa megnyitása");
        mappamegnyit_gomb.addActionListener(new Mappa());
        mappamegnyit_gomb.setBounds(960, 516, 135, 23);
        add(mappamegnyit_gomb);
        
        JButton sortorles_gomb = new JButton("Sor törlés");
        sortorles_gomb.addActionListener(new Sor_torles());
        sortorles_gomb.setBounds(689, 59, 112, 23);
        add(sortorles_gomb);
        
        mindenertek_mezo = new JTextField();
        mindenertek_mezo.setBounds(1034, 387, 86, 20);
        add(mindenertek_mezo);
        mindenertek_mezo.setColumns(10);
        
        JLabel lblNewLabel_4 = new JLabel("Összérték");
        lblNewLabel_4.setBounds(1049, 360, 85, 14);
        add(lblNewLabel_4);
        
        JButton letrehoz_gomb = new JButton("Mappa létrehozása");
        letrehoz_gomb.addActionListener(new Mappa_letrehoz());
        letrehoz_gomb.setBounds(726, 516, 136, 23);
        add(letrehoz_gomb);
        
        JLabel lblNewLabel_5 = new JLabel("Cserealkatrész beérkezésének dátuma");
        lblNewLabel_5.setBounds(43, 520, 234, 14);
        add(lblNewLabel_5);
        
        cseredatum_mezo = new JTextField();
        cseredatum_mezo.setBounds(288, 517, 86, 20);
        add(cseredatum_mezo);
        cseredatum_mezo.setColumns(10);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(10, 820, 1180, 22);
        add(separator);
        
        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(10, 572, 1180, 20);
        add(separator_1);
       
        beszallitok.clear();
        gyartok.clear();
        
        SQA_SQL ment = new SQA_SQL();
        String sql = "insert into qualitydb.SQA_reklamaciok (ID,Futo_ID,Datum,Inditotta,Vagy,Cikkszam, Fajta,Gyarto,Beszallito,Projekt,Kontakt,"
                + "Hibaleiras,Belso_intezkedes,Hibasdb,Megjelenesido,Reklamacioido,Deviza,Egysegar,Osszertek,Beszallitoi_valasz,Gyokerok,"
                + "Beszallitoi_karterites,Belso_koltseg,VEszteseg,Mappa_helye,8D,Credit_note, Megnevezes, Rek_ertek, Csere_datum) "
                + "Value('"+ id_mezo.getText()+"','"+id_mezo.getText()+"','"+"2099.12.31"+"','"+ String.valueOf(indito_box.getSelectedItem())
                +"','"+ String.valueOf(intezkedes_box.getSelectedItem())+"','"+ ""+"','"+ String.valueOf(fajta_box.getSelectedItem())
                +"','"+""+"','"+""+"','"+""+"','"+""+"','"+""
                +"','"+""+"','"+""+"','"+""+"','"+""
                +"','"+""+"','"+""+"','"+""
                +"','"+""+"','"+""+"','"+""+"','"+""+"','"+""
                +"','"+""+"','"+ "" +"','"+ "" +"','"+ "" +"','"+ "" +"','"+ "" +"')";
        ment.mindenes(sql);
    }
    
    public SQA_bevitel(String id) {
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
        lblNewLabel_2.setBounds(588, 96, 66, 14);
        add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("Alapanyag fajta");
        lblNewLabel_3.setBounds(43, 180, 95, 14);
        add(lblNewLabel_3);
        
        String[] fajtak = {"PCB","SMD","THT","Műanyag","Fém","Csomagolóanyag","Üveg","Egyéb"};
        fajta_box = new JComboBox<String>(fajtak);                                      //fajtak
        fajta_box.setBounds(148, 176, 147, 22);
        add(fajta_box);
        
        JLabel lblNewLabel_7 = new JLabel("Hiba leírása");
        lblNewLabel_7.setBounds(42, 236, 75, 14);
        add(lblNewLabel_7);
        
        hibaleiras_mezo = new JTextArea();
        hibaleiras_mezo.setLineWrap(true);
        hibaleiras_mezo.setWrapStyleWord(true);
        hibaleiras_mezo.setBounds(127, 231, 218, 88);
        add(hibaleiras_mezo);
        
        JLabel lblNewLabel_8 = new JLabel("Intézkedés");
        lblNewLabel_8.setBounds(396, 261, 66, 14);
        add(lblNewLabel_8);
        
        String[] intezkedes = {"Reklamáció","Egyéb"};
        intezkedes_box = new JComboBox<String>(intezkedes);                           //intezkedes
        //intezkedes_box.addActionListener(new Vagy());
        intezkedes_box.setBounds(217, 92, 157, 22);
        add(intezkedes_box);
        
        JLabel lblNewLabel_9 = new JLabel("Hibás db");
        lblNewLabel_9.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_9.setBounds(726, 236, 75, 14);
        add(lblNewLabel_9);
        
        hibasdb_mezo = new JTextField();
        hibasdb_mezo.addKeyListener(new Szamol());
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
        
        String[] deviza = {"EUR","USD","CHF","HUF","GBP","JPY"};
        deviza_box = new JComboBox<String>(deviza);                     //deviza
        deviza_box.setBounds(1105, 232, 59, 22);
        add(deviza_box);
        
        JLabel lblNewLabel_13 = new JLabel("Érték");
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
        valasz_mezo.setLineWrap(true);
        valasz_mezo.setWrapStyleWord(true);
        valasz_mezo.setBounds(168, 355, 218, 74);
        add(valasz_mezo);
        
        JLabel lblNewLabel_18 = new JLabel("Beszállítói válasz");
        lblNewLabel_18.setBounds(43, 360, 95, 14);
        add(lblNewLabel_18);
        
        gyokerok_mezo = new JTextArea();
        gyokerok_mezo.setLineWrap(true);
        gyokerok_mezo.setWrapStyleWord(true);
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
        indito_box = new JComboBox<String>(nevek);                                       //nevek
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
        statusz_mezo.setLineWrap(true);
        statusz_mezo.setWrapStyleWord(true);
        statusz_mezo.setBounds(189, 600, 316, 204);
        add(statusz_mezo);
        
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
        
        tortenesideje_mezo = new JTextField();
        tortenesideje_mezo.setBounds(640, 602, 86, 20);
        add(tortenesideje_mezo);
        tortenesideje_mezo.setColumns(10);
        
        JButton valtozas_gomb = new JButton("Válzotás mentése");
        valtozas_gomb.addActionListener(new Tortenet());
        valtozas_gomb.setBounds(552, 630, 139, 23);
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
        lezar_gomb.addActionListener(new Lezaras());
        lezar_gomb.setBounds(565, 884, 89, 23);
        add(lezar_gomb);
        
        JLabel lblNewLabel_31 = new JLabel("Futó ID");
        lblNewLabel_31.setBounds(406, 96, 46, 14);
        add(lblNewLabel_31);
        
        futoid_mezo = new JTextField();
        futoid_mezo.setBounds(462, 93, 86, 20);
        add(futoid_mezo);
        futoid_mezo.setColumns(10);
        
        JLabel lblNewLabel_32 = new JLabel("8D");
        lblNewLabel_32.setBounds(43, 474, 23, 14);
        add(lblNewLabel_32);
        
        JLabel lblNewLabel_33 = new JLabel("Credit Note");
        lblNewLabel_33.setBounds(127, 474, 66, 14);
        add(lblNewLabel_33);
        
        JLabel lblNewLabel_34 = new JLabel("Mappa helye");
        lblNewLabel_34.setBounds(251, 474, 86, 14);
        add(lblNewLabel_34);
        
        link_mezo = new JTextField();
        link_mezo.setText("2023\\");                //\\10.1.0.11\\minosegbiztositas\\SQA\\reklamációk\\
        link_mezo.setBounds(343, 471, 752, 20);
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
        intezkedes_mezo.setLineWrap(true);
        intezkedes_mezo.setWrapStyleWord(true);
        intezkedes_mezo.setBounds(462, 231, 215, 88);
        add(intezkedes_mezo);
        
        JLabel lblNewLabel_37 = new JLabel("Belső");
        lblNewLabel_37.setBounds(404, 236, 46, 14);
        add(lblNewLabel_37);
        
        JButton ujranyit_gomb = new JButton("Újranyitás");
        ujranyit_gomb.addActionListener(new Ujranyit());
        ujranyit_gomb.setBounds(712, 884, 89, 23);
        add(ujranyit_gomb);
        
        Utolso_sor sorszam = new Utolso_sor();
        int kovetkezo = Integer.parseInt(sorszam.utolso("qualitydb.SQA_reklamaciok"));
        id_mezo.setText(String.valueOf(kovetkezo + 1));
        
        d_csekk = new JCheckBox("");
        d_csekk.setBounds(71, 470, 23, 23);
        add(d_csekk);
        
        cn_csekk = new JCheckBox("");
        cn_csekk.setBounds(199, 470, 23, 23);
        add(cn_csekk);
        
        JButton vissza_gomb = new JButton("Vissza");
        vissza_gomb.addActionListener(new Vissza());
        vissza_gomb.setBounds(1075, 11, 89, 23);
        add(vissza_gomb);
        
        JButton hozzaad_gomb = new JButton("Hozzáad");
        hozzaad_gomb.addActionListener(new Elem_valaszto());
        hozzaad_gomb.setBounds(1075, 92, 89, 23);
        add(hozzaad_gomb);
        setBackground(Foablak.hatter_szine);
        
        JLabel lblNewLabel_38 = new JLabel("Excel export");
        lblNewLabel_38.setBounds(536, 790, 86, 14);
        add(lblNewLabel_38);
        
        JButton excel_gomb = new JButton("Excel");
        excel_gomb.addActionListener(new Excel());
        excel_gomb.setBounds(640, 786, 89, 23);
        add(excel_gomb);
        
        cikkszam_mezo = new JTextField();
        cikkszam_mezo.setBounds(689, 93, 347, 20);
        add(cikkszam_mezo);
        cikkszam_mezo.setColumns(10);
        
        modell = new DefaultTableModel();
        modell.setColumnIdentifiers(new Object[]{"Cikkszám", "Megnevezés", "Gyártó", "Beszállító", "Projekt", "Kontakt","Egységár"});
        table = new JTable();
        table.setModel(modell);
        table.getSelectionModel().addListSelectionListener(new Sorvalaszto());
        JScrollPane gorgeto = new JScrollPane(table);
        gorgeto.setBounds(343, 132, 821, 93);
        add(gorgeto);
        
        JButton mappamegnyit_gomb = new JButton("Mappa megnyitása");
        mappamegnyit_gomb.addActionListener(new Mappa());
        mappamegnyit_gomb.setBounds(960, 516, 135, 23);
        add(mappamegnyit_gomb);
        
        JButton sortorles_gomb = new JButton("Sor törlés");
        sortorles_gomb.addActionListener(new Sor_torles());
        sortorles_gomb.setBounds(689, 59, 112, 23);
        add(sortorles_gomb);
        
        mindenertek_mezo = new JTextField();
        mindenertek_mezo.setBounds(1034, 387, 86, 20);
        add(mindenertek_mezo);
        mindenertek_mezo.setColumns(10);
        
        JLabel lblNewLabel_4 = new JLabel("Összérték");
        lblNewLabel_4.setBounds(1049, 360, 85, 14);
        add(lblNewLabel_4);
        
        JButton letrehoz_gomb = new JButton("Mappa létrehozása");
        letrehoz_gomb.setBounds(726, 516, 136, 23);
        add(letrehoz_gomb);
        
        letrehoz_gomb.addActionListener(new Mappa_letrehoz());
        
        letezik = 1;
        
        JLabel lblNewLabel_5 = new JLabel("Cserealkatrész beérkezésének dátuma");
        lblNewLabel_5.setBounds(43, 520, 234, 14);
        add(lblNewLabel_5);
        
        cseredatum_mezo = new JTextField();
        cseredatum_mezo.setBounds(288, 517, 86, 20);
        add(cseredatum_mezo);
        cseredatum_mezo.setColumns(10);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(10, 820, 1180, 22);
        add(separator);
        
        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(10, 572, 1180, 20);
        add(separator_1);
        
        visszatolt(id);

        beszallitok.clear();
        gyartok.clear();

    }
    
    class Elem_valaszto implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                                                //egér mutató változtatása munka a háttérbenre
                SQA_SQL tomb = new SQA_SQL();
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
                    String megnevezes = lekerdezes.beszallito(sql);
                    
                    sql = "select ifsapp.SUPPLIER_API.Get_Vendor_Name(VENDOR_NO)\r\n"
                            + "from ifsapp.PURCHASE_PART_SUPPLIER\r\n"
                            + "where 3 = 3\r\n"
                            + "and PART_NO = '"+ valasztott +"'";
                    String[] koztes = tomb.tombvissza(sql);
                    for(int szamlalo = 0; szamlalo < koztes.length; szamlalo++)
                    {
                        beszallitok.add(koztes[szamlalo]);
                    }
                    String[] beszallitotomb = beszallitok.toArray(new String[beszallitok.size()]);
                    TableColumn sportColumn = table.getColumnModel().getColumn(3);
                    comboBox1 = new JComboBox<String>(beszallitotomb);
                    sportColumn.setCellEditor(new DefaultCellEditor(comboBox1));  
                    sql = "select ifsapp.MANUFACTURER_INFO_API.Get_Name(MANUFACTURER_NO)\r\n"
                            + "from ifsapp.PART_MANUFACTURER\r\n"
                            + "where 3 = 3\r\n"
                            + "and PART_NO = '"+ valasztott +"'";
                    
                    String[] koztes2 = tomb.tombvissza(sql);
                    for(int szamlalo = 0; szamlalo < koztes2.length; szamlalo++)
                    {
                        gyartok.add(koztes2[szamlalo]);
                    }
                    String[] gyartotomb = gyartok.toArray(new String[gyartok.size()]);
                    TableColumn sportColumn2 = table.getColumnModel().getColumn(2);
                    JComboBox<String> comboBox2 = new JComboBox<String>(gyartotomb);
                    sportColumn2.setCellEditor(new DefaultCellEditor(comboBox2));
                                        
                    String gyarto = lekerdezes.beszallito(sql);
             
                    sql = "select second_commodity\r\n"
                            + "from ifsapp.INVENTORY_PART\r\n"
                            + "where 3 = 3 \r\n"
                            + "and part_no = '"+ valasztott +"'";
                    
                    String projekt = lekerdezes.beszallito(sql);
                    
                    sql = "select inventory_value from ifsapp.INVENTORY_PART_UNIT_COST_SUM\r\n"
                            + "where part_no =  '"+ valasztott +"'";
                    float ertek1 = Float.valueOf(lekerdezes.beszallito(sql));
                    int ertek = Math.round(ertek1);
                    String egysegar = String.valueOf(ertek);
    
                    String kontaktok = "";
                    if(beszallitotomb.length > 0)
                    {
                        beszallito = beszallitotomb[0];
                    }
                    else
                    {
                        beszallito = "";
                    }
                    int van = 0;
                    for(int szamlalo = 0; szamlalo < datatable.getRows().size(); szamlalo++)
                    {
                        if(datatable.getRows().get(szamlalo).getString(1).contains(beszallito))
                        {
                            kontaktok += datatable.getRows().get(szamlalo).getString(2) +"; ";
                            van++;
                        }
                    }
                    if(van == 0)
                    {
                        kontaktok = "@";
                    }
                    modell.addRow(new Object[]{valasztott,megnevezes,gyarto,beszallito,projekt,kontaktok,egysegar});
                    
                    /*final TableColumnModel columnModel = table.getColumnModel();
                    for (int column = 0; column < table.getColumnCount(); column++) {
                        int width = 15; // Min width
                        for (int row = 0; row < table.getRowCount(); row++) {
                            TableCellRenderer renderer = table.getCellRenderer(row, column);
                            Component comp = table.prepareRenderer(renderer, row, column);
                            width = Math.max(comp.getPreferredSize().width +1 , width);
                        }
                        if(width > 300)
                            width=300;
                        columnModel.getColumn(column).setPreferredWidth(width);
                    }*/
                    
                    table.setModel(modell);
                    cikkszam_mezo.setText("");
                    
                    tombmeret = table.getRowCount();
                    ArrayList<String[]> adatok = new ArrayList<String[]>();
                    if(letezik == 0)
                    {
                        hibaleiras = new String[tombmeret];
                        for(int szamlalo = 0; szamlalo < hibaleiras.length; szamlalo++)
                        {
                            hibaleiras[szamlalo] = "";
                        }
                        belsointezkedes = new String[tombmeret];
                        for(int szamlalo = 0; szamlalo < belsointezkedes.length; szamlalo++)
                        {
                            belsointezkedes[szamlalo] = "";
                        }
                        valasz = new String[tombmeret];
                        for(int szamlalo = 0; szamlalo < valasz.length; szamlalo++)
                        {
                            valasz[szamlalo] = "";
                        }
                        gyokerok = new String[tombmeret];
                        for(int szamlalo = 0; szamlalo < gyokerok.length; szamlalo++)
                        {
                            gyokerok[szamlalo] = "";
                        }
                        
                        hibasdb = new String[tombmeret];
                        for(int szamlalo = 0; szamlalo < hibasdb.length; szamlalo++)
                        {
                            hibasdb[szamlalo] = "";
                        }
                        megjelenes = new String[tombmeret];
                        for(int szamlalo = 0; szamlalo < megjelenes.length; szamlalo++)
                        {
                            megjelenes[szamlalo] = "";
                        }
                        rekkezdet = new String[tombmeret];
                        for(int szamlalo = 0; szamlalo < rekkezdet.length; szamlalo++)
                        {
                            rekkezdet[szamlalo] = "";
                        }
                        osszertek = new String[tombmeret];
                        for(int szamlalo = 0; szamlalo < osszertek.length; szamlalo++)
                        {
                            osszertek[szamlalo] = "";
                        }
                        karterites = new String[tombmeret];
                        for(int szamlalo = 0; szamlalo < karterites.length; szamlalo++)
                        {
                            karterites[szamlalo] = "";
                        }
                        belsokoltseg = new String[tombmeret];
                        for(int szamlalo = 0; szamlalo < belsokoltseg.length; szamlalo++)
                        {
                            belsokoltseg[szamlalo] = "";
                        }
                        veszteseg = new String[tombmeret];
                        for(int szamlalo = 0; szamlalo < veszteseg.length; szamlalo++)
                        {
                            veszteseg[szamlalo] = "";
                        }
                        deviza = new String[tombmeret];
                        for(int szamlalo = 0; szamlalo < deviza.length; szamlalo++)
                        {
                            deviza[szamlalo] = "";
                        }
                        cseredatum = new String[tombmeret];
                        for(int szamlalo = 0; szamlalo < cseredatum.length; szamlalo++)
                        {
                            cseredatum[szamlalo] = "";
                        }
                        System.out.println("Az alap futott le");
                    }
                    else
                    {
                        System.out.println("A másik változat futott le");
                        adatok.add(hibaleiras);
                        hibaleiras = new String[tombmeret];
                        for(int szamlalo = 0; szamlalo < adatok.get(0).length; szamlalo++)
                        {
                            hibaleiras[szamlalo] = adatok.get(0)[szamlalo];
                        }
                        hibaleiras[hibaleiras.length -1] = "";
                        adatok.clear();
                        adatok.add(belsointezkedes);
                        belsointezkedes = new String[tombmeret];
                        for(int szamlalo = 0; szamlalo < adatok.get(0).length; szamlalo++)
                        {
                            belsointezkedes[szamlalo] = adatok.get(0)[szamlalo];
                        }
                        belsointezkedes[belsointezkedes.length -1] = "";
                        adatok.clear();
                        adatok.add(valasz);
                        valasz = new String[tombmeret];
                        for(int szamlalo = 0; szamlalo < adatok.get(0).length; szamlalo++)
                        {
                            valasz[szamlalo] = adatok.get(0)[szamlalo];
                        }
                        valasz[valasz.length -1] = "";
                        adatok.clear();
                        adatok.add(gyokerok);
                        gyokerok = new String[tombmeret];
                        for(int szamlalo = 0; szamlalo < adatok.get(0).length; szamlalo++)
                        {
                            gyokerok[szamlalo] = adatok.get(0)[szamlalo];
                        }
                        gyokerok[gyokerok.length -1] = "";
                        adatok.clear();
                        adatok.add(hibasdb);
                        hibasdb = new String[tombmeret];
                        for(int szamlalo = 0; szamlalo < adatok.get(0).length; szamlalo++)
                        {
                            hibasdb[szamlalo] = adatok.get(0)[szamlalo];
                        }
                        hibasdb[hibasdb.length -1] = "";
                        adatok.clear();
                        adatok.add(megjelenes);
                        megjelenes = new String[tombmeret];
                        for(int szamlalo = 0; szamlalo < adatok.get(0).length; szamlalo++)
                        {
                            megjelenes[szamlalo] = adatok.get(0)[szamlalo];
                        }
                        megjelenes[megjelenes.length -1] = "";
                        adatok.clear();
                        adatok.add(rekkezdet);
                        rekkezdet = new String[tombmeret];
                        for(int szamlalo = 0; szamlalo < adatok.get(0).length; szamlalo++)
                        {
                            rekkezdet[szamlalo] = adatok.get(0)[szamlalo];
                        }
                        rekkezdet[rekkezdet.length -1] = "";
                        adatok.clear();
                        adatok.add(osszertek);
                        osszertek = new String[tombmeret];
                        for(int szamlalo = 0; szamlalo < adatok.get(0).length; szamlalo++)
                        {
                            osszertek[szamlalo] = adatok.get(0)[szamlalo];
                        }
                        osszertek[osszertek.length -1] = "";
                        adatok.clear();
                        adatok.add(karterites);
                        karterites = new String[tombmeret];
                        for(int szamlalo = 0; szamlalo < adatok.get(0).length; szamlalo++)
                        {
                            karterites[szamlalo] = adatok.get(0)[szamlalo];
                        }
                        karterites[karterites.length -1] = "";
                        adatok.clear();
                        adatok.add(belsokoltseg);
                        belsokoltseg = new String[tombmeret];
                        for(int szamlalo = 0; szamlalo < adatok.get(0).length; szamlalo++)
                        {
                            belsokoltseg[szamlalo] = adatok.get(0)[szamlalo];
                        }
                        belsokoltseg[belsokoltseg.length -1] = "";
                        adatok.clear();
                        adatok.add(veszteseg);
                        veszteseg = new String[tombmeret];
                        for(int szamlalo = 0; szamlalo < adatok.get(0).length; szamlalo++)
                        {
                            veszteseg[szamlalo] = adatok.get(0)[szamlalo];
                        }
                        veszteseg[veszteseg.length -1] = "";
                        adatok.clear();
                        adatok.add(deviza);
                        deviza = new String[tombmeret];
                        for(int szamlalo = 0; szamlalo < adatok.get(0).length; szamlalo++)
                        {
                            deviza[szamlalo] = adatok.get(0)[szamlalo];
                        }
                        deviza[deviza.length -1] = "";
                        adatok.clear();
                        adatok.add(cseredatum);
                        cseredatum = new String[tombmeret];
                        for(int szamlalo = 0; szamlalo < adatok.get(0).length; szamlalo++)
                        {
                            cseredatum[szamlalo] = adatok.get(0)[szamlalo];
                        }
                        cseredatum[cseredatum.length -1] = "";
                        adatok.clear();
                    }
                    
                }
                Foablak.frame.pack();
                Foablak.frame.setCursor(null);                                                                                          //egér mutató alaphelyzetbe állítása
            }
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", getClass()+" "+ hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                     //kivétel esetén kiírja a hibaüzenetet
            }
         }
    }
    
    class Sor_torles implements ActionListener                                                                                      
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                ((DefaultTableModel) table.getModel()).removeRow(table.getSelectedRow());
            }
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                Foablak.frame.setCursor(null);
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", getClass()+" "+ hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
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
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", getClass()+" "+ hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                     //kivétel esetén kiírja a hibaüzenetet
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
                if(datum_mezo.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Nem adtál meg dátumot", "Hiba üzenet", 2);
                    Foablak.frame.setCursor(null);
                }               
                else
                {
                    if(hibasdb_mezo.getText().equals(""))
                    {
                        JOptionPane.showMessageDialog(null, "Nem adtál meg db számot", "Hiba üzenet", 2); 
                    }
                    else
                    {
                        hibaleiras[valasztott] = hibaleiras_mezo.getText();
                        belsointezkedes[valasztott] = intezkedes_mezo.getText();
                        valasz[valasztott] = valasz_mezo.getText();
                        gyokerok[valasztott] = gyokerok_mezo.getText();
                        
                        hibasdb[valasztott] = hibasdb_mezo.getText();                
                        megjelenes[valasztott] = megjelenes_ido.getText();
                        cseredatum[valasztott] = cseredatum_mezo.getText();
                        if(reklamacio_ido.getText().equals("")) {}
                        else
                        {
                            for(int szamlalo = 0; szamlalo < rekkezdet.length;szamlalo++)
                            {
                                rekkezdet[szamlalo] = reklamacio_ido.getText();
                            }
                        }
                                        
                        osszertek[valasztott] = osszertek_mezo.getText();               
                        karterites[valasztott] = karterites_mezo.getText();               
                        belsokoltseg[valasztott] = belsokoltseg_mezo.getText();              
                        veszteseg[valasztott] = veszteseg_mezo.getText();
                        String[] koztes = deviza_mezo.getText().split(" ");
                        if(koztes.length> 1)
                        {
                            deviza[valasztott] = deviza_mezo.getText();      //+ " "+ String.valueOf(deviza_box.getSelectedItem());
                        }
                        else
                        {
                            deviza[valasztott] = deviza_mezo.getText()+ " "+ String.valueOf(deviza_box.getSelectedItem());
                        }
                        Connection conn = null;
                        Statement stmt = null;        
                        
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        conn = (Connection) DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
                        stmt = (Statement) conn.createStatement();
                        String SQL = "select * from qualitydb.SQA_reklamaciok where id = '"+ id_mezo.getText() +"'";
                        ResultSet rs = stmt.executeQuery(SQL);
                        String sql = "";
                        if(rs.next())
                        {
                            String d = "";
                            String cn = "";
                            if(d_csekk.isSelected())
                            {
                                d = "igen";
                            }
                            else
                            {
                                d = "nem";
                            }
                            if(cn_csekk.isSelected())
                            {
                                cn = "igen";
                            }
                            else
                            {
                                cn = "nem";
                            }                            
                            String[] link = link_mezo.getText().split("\\\\");
                            String link2;
                            if(link.length < 2)
                            {
                                link2 = link[0] +"\\\\";
                            }
                            else
                            {
                                link2 = link[0] +"\\\\"+ link[1];
                            }
                            
                            String cikkszam = "";
                            String megnevezes = "";
                            String gyarto = "";
                            String beszallito = "";
                            String projekt = "";
                            String kontakt = "";
                            String egysegar = "";
                            String hibaleir = "";
                            String intezked = "";
                            String besz_valasz = "";
                            String gyoker = "";
                            String hibas = "";
                            String megjelen = "";
                            String kezdet = "";
                            String ertek = "";
                            String terit = "";
                            String koltseg = "";
                            String veszt = "";
                            String dev = "";
                            String csere = "";
                            
                            for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
                            {
                                cikkszam += table.getValueAt(szamlalo, 0).toString() +"\n";
                                megnevezes += table.getValueAt(szamlalo, 1).toString() +"\n";
                                gyarto += table.getValueAt(szamlalo, 2).toString() +"\n";
                                beszallito += table.getValueAt(szamlalo, 3).toString() +"\n";
                                projekt += table.getValueAt(szamlalo, 4).toString() +"\n";
                                kontakt += table.getValueAt(szamlalo, 5).toString() +"\n";
                                egysegar += table.getValueAt(szamlalo, 6).toString() +"\n";
                                if(hibaleiras.length > 1)
                                {
                                    if(hibaleiras[szamlalo] == null)
                                    {
                                        hibaleiras[szamlalo] = "";
                                    }
                                    hibaleir += hibaleiras[szamlalo].replace("\n", "\t\t")+"\n";
                                }
                                else
                                {
                                    hibaleir = hibaleiras_mezo.getText().replace("\n", "\t\t")+"\n";
                                }
                                if(belsointezkedes.length > 1)
                                {
                                    if(belsointezkedes[szamlalo] == null)
                                    {
                                        belsointezkedes[szamlalo] = "";
                                    }
                                    intezked += belsointezkedes[szamlalo].replace("\n", "\t\t")+"\n";
                                }
                                else
                                {
                                    intezked = intezkedes_mezo.getText().replace("\n", "\t\t")+"\n";
                                }
                                if(valasz.length > 1)
                                {
                                    if(valasz[szamlalo] == null)
                                    {
                                        valasz[szamlalo] = "";
                                    }
                                    besz_valasz += valasz[szamlalo].replace("\n", "\t\t")+"\n";
                                }
                                else
                                {
                                    besz_valasz = valasz_mezo.getText().replace("\n", "\t\t")+"\n";
                                }
                                if(gyokerok.length > 1)
                                {
                                    if(gyokerok[szamlalo] == null)
                                    {
                                        gyokerok[szamlalo] = "";
                                    }
                                    gyoker += gyokerok[szamlalo].replace("\n", "\t\t")+"\n";
                                }
                                else
                                {
                                    gyoker = gyokerok_mezo.getText().replace("\n", "\t\t")+"\n";
                                }
                                if(hibasdb.length > 1)
                                {
                                    if(hibasdb[szamlalo] == null)
                                    {
                                        hibasdb[szamlalo] = "";
                                    }
                                    hibas += hibasdb[szamlalo]+"\n"; 
                                }
                                else
                                {
                                    hibas = hibasdb_mezo.getText()+"\n";
                                }
                                if(megjelenes.length > 1)
                                {
                                    if(megjelenes[szamlalo] == null)
                                    {
                                        megjelenes[szamlalo] = "";
                                    }
                                    megjelen += megjelenes[szamlalo]+"\n";
                                }
                                else
                                {
                                    megjelen = megjelenes_ido.getText()+"\n";
                                }
                                if(rekkezdet.length > 1)
                                {
                                    if(rekkezdet[szamlalo] == null)
                                    {
                                        rekkezdet[szamlalo] = "";
                                    }
                                    kezdet += rekkezdet[szamlalo]+"\n";
                                }
                                else
                                {
                                    kezdet = reklamacio_ido.getText()+"\n";
                                }
                                if(osszertek.length > 1)
                                {
                                    if(osszertek[szamlalo] == null)
                                    {
                                        osszertek[szamlalo] = "";
                                    }
                                    ertek += osszertek[szamlalo]+"\n";
                                }
                                else
                                {
                                    ertek = osszertek_mezo.getText()+"\n";
                                }
                                if(karterites.length > 1)
                                {
                                    if(karterites[szamlalo] == null)
                                    {
                                        karterites[szamlalo] = "";
                                    }
                                    terit += karterites[szamlalo]+"\n"; 
                                }
                                else
                                {
                                    terit = karterites_mezo.getText()+"\n"; 
                                }
                                if(belsokoltseg.length > 1)
                                {
                                    if(belsokoltseg[szamlalo] == null)
                                    {
                                        belsokoltseg[szamlalo] = "";
                                    }
                                    koltseg += belsokoltseg[szamlalo]+"\n"; 
                                }
                                else
                                {
                                    koltseg = belsokoltseg_mezo.getText()+"\n";  
                                }
                                if(veszteseg.length > 1)
                                {
                                    if(veszteseg[szamlalo] == null)
                                    {
                                        veszteseg[szamlalo] = "";
                                    }
                                    veszt += veszteseg[szamlalo]+"\n";
                                }                                  
                                else
                                {             
                                    veszt = veszteseg_mezo.getText()+"\n";
                                }
                                if(deviza.length > 1)
                                {
                                    if(deviza[szamlalo] == null)
                                    {
                                        deviza[szamlalo] = "";
                                    }
                                    dev += deviza[szamlalo]+"\n";
                                }                                  
                                else
                                {             
                                    dev = deviza_mezo.getText()+"\n";
                                }
                                if(cseredatum.length > 1)
                                {
                                    if(cseredatum[szamlalo] == null)
                                    {
                                        cseredatum[szamlalo] = "";
                                    }
                                    csere += cseredatum[szamlalo]+"\n";
                                }                                  
                                else
                                {             
                                    csere = cseredatum_mezo.getText()+"\n";
                                }
                            }
                            sql = "update qualitydb.SQA_reklamaciok set Rek_ertek = '"+ mindenertek_mezo.getText() +"', Fajta = '"+ String.valueOf(fajta_box.getSelectedItem()) +"', Futo_id = '"+ futoid_mezo.getText() +"', "
                                    + "vagy = '"+ String.valueOf(intezkedes_box.getSelectedItem()) +"', "
                                    + "DAtum = '"+ datum_mezo.getText() +"',  Hibaleiras = '"+ hibaleir +"', "
                                    + "Belso_intezkedes = '"+intezked+"', Inditotta = '"+String.valueOf(indito_box.getSelectedItem())+"',"
                                    + "Hibasdb = '"+hibas+"', Megjelenesido = '"+megjelen+"', Reklamacioido = '"+kezdet+"', Deviza = '"
                                    + dev +"', Osszertek = '"+ertek+"', Beszallitoi_valasz ='"
                                    + besz_valasz+"',Gyokerok = '"+gyoker+"',Beszallitoi_karterites ='"+terit+"',Belso_koltseg ='"+koltseg
                                    +"',Veszteseg = '"+veszt+"',Mappa_helye = '"+link2+"',8D = '"+ d +"',Credit_note = '"+ cn +"',kontakt = '"+ kontakt +"',cikkszam = '"+ cikkszam +"'"
                                    + ",gyarto = '"+ gyarto +"',beszallito = '"+ beszallito +"',projekt = '"+ projekt +"',egysegar = '"+ egysegar +"',Megnevezes = '"+ megnevezes +"',Csere_datum = '"+ csere +"' "
                                            + "where id ='" + id_mezo.getText() + "'";
                        }
                        else
                        {
                            String d = "";
                            String cn = "";
                            if(d_csekk.isSelected())
                            {
                                d = "igen";
                            }
                            else
                            {
                                d = "nem";
                            }
                            if(cn_csekk.isSelected())
                            {
                                cn = "igen";
                            }
                            else
                            {
                                cn = "nem";
                            }
                            String[] link = link_mezo.getText().split("\\\\");
                            String link2;
                            if(link.length < 2)
                            {
                                link2 = link[0] +"\\\\";
                            }
                            else
                            {
                                link2 = link[0] +"\\\\"+ link[1];
                            }
                            String cikkszam = "";
                            String megnevezes = "";
                            String gyarto = "";
                            String beszallito = "";
                            String projekt = "";
                            String kontakt = "";
                            String egysegar = "";
                            String hibaleir = "";
                            String intezked = "";
                            String besz_valasz = "";
                            String gyoker = "";
                            String hibas = "";
                            String megjelen = "";
                            String kezdet = "";
                            String ertek = "";
                            String terit = "";
                            String koltseg = "";
                            String veszt = "";
                            String dev = "";
                            String csere = "";
                            
                            for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
                            {
                                cikkszam += table.getValueAt(szamlalo, 0).toString() +"\n";
                                if(table.getValueAt(szamlalo, 2) == null)
                                {
                                    gyarto += "" +"\n";
                                }
                                else
                                {
                                    gyarto += table.getValueAt(szamlalo, 2).toString() +"\n";
                                }                               
                                if(table.getValueAt(szamlalo, 3) == null)
                                {
                                    beszallito += "" +"\n";
                                }
                                else
                                {
                                    beszallito += table.getValueAt(szamlalo, 3).toString() +"\n";
                                }
                                megnevezes += table.getValueAt(szamlalo, 1).toString() +"\n";
                                projekt += table.getValueAt(szamlalo, 4).toString() +"\n";
                                kontakt += table.getValueAt(szamlalo, 5).toString() +"\n";
                                egysegar += table.getValueAt(szamlalo, 6).toString() +"\n";
                                /*System.out.println(table.getValueAt(szamlalo, 0).toString());
                                System.out.println(table.getValueAt(szamlalo, 1).toString());
                                System.out.println(table.getValueAt(szamlalo, 2).toString());
                                System.out.println(table.getValueAt(szamlalo, 3).toString());
                                System.out.println(table.getValueAt(szamlalo, 4).toString());
                                System.out.println(table.getValueAt(szamlalo, 5).toString());
                                System.out.println(table.getValueAt(szamlalo, 6).toString());
                                */
                                if(table.getRowCount() > 1)
                                {
                                    if(hibaleiras[szamlalo] == null)
                                    {
                                        hibaleiras[szamlalo] = "";
                                    }
                                    hibaleir += hibaleiras[szamlalo].replace("\n", "\t\t")+"\n";
                                }
                                else
                                {
                                    hibaleir = hibaleiras_mezo.getText()+"\n";
                                }
                                if(table.getRowCount() > 1)
                                {
                                    if(belsointezkedes[szamlalo] == null)
                                    {
                                        belsointezkedes[szamlalo] = "";
                                    }
                                    intezked += belsointezkedes[szamlalo].replace("\n", "\t\t")+"\n";
                                }
                                else
                                {
                                    intezked = intezkedes_mezo.getText()+"\n";
                                }
                                if(table.getRowCount() > 1)
                                {
                                    if(valasz[szamlalo] == null)
                                    {
                                        valasz[szamlalo] = "";
                                    }
                                    besz_valasz += valasz[szamlalo].replace("\n", "\t\t")+"\n";
                                }
                                else
                                {
                                    besz_valasz = valasz_mezo.getText().replace("\n", "\t\t")+"\n";
                                }
                                if(table.getRowCount() > 1)
                                {
                                    if(gyokerok[szamlalo] == null)
                                    {
                                        gyokerok[szamlalo] = "";
                                    }
                                    gyoker += gyokerok[szamlalo].replace("\n", "\t\t")+"\n";
                                }
                                else
                                {
                                    gyoker = gyokerok_mezo.getText().replace("\n", "\t\t")+"\n";
                                }
                                if(table.getRowCount() > 1)
                                {
                                    if(hibasdb[szamlalo] == null)
                                    {
                                        hibasdb[szamlalo] = "";
                                    }
                                    hibas += hibasdb[szamlalo]+"\n"; 
                                }
                                else
                                {
                                    hibas = hibasdb_mezo.getText()+"\n";
                                }
                                if(table.getRowCount() > 1)
                                {
                                    if(megjelenes[szamlalo] == null)
                                    {
                                        megjelenes[szamlalo] = "";
                                    }
                                    megjelen += megjelenes[szamlalo]+"\n";
                                }
                                else
                                {
                                    megjelen = megjelenes_ido.getText()+"\n";
                                }
                                if(table.getRowCount() > 1)
                                {
                                    if(rekkezdet[szamlalo] == null)
                                    {
                                        rekkezdet[szamlalo] = "";
                                    }
                                    kezdet += rekkezdet[szamlalo]+"\n";
                                }
                                else
                                {
                                    kezdet = reklamacio_ido.getText()+"\n";
                                }
                                if(table.getRowCount() > 1)
                                {
                                    if(osszertek[szamlalo] == null)
                                    {
                                        osszertek[szamlalo] = "";
                                    }
                                    ertek += osszertek[szamlalo]+"\n";
                                }
                                else
                                {
                                    ertek = osszertek_mezo.getText()+"\n";
                                }
                                if(table.getRowCount() > 1)
                                {
                                    if(karterites[szamlalo] == null)
                                    {
                                        karterites[szamlalo] = "";
                                    }
                                    terit += karterites[szamlalo]+"\n"; 
                                }
                                else
                                {
                                    terit = karterites_mezo.getText()+"\n"; 
                                }
                                if(table.getRowCount() > 1)
                                {
                                    if(belsokoltseg[szamlalo] == null)
                                    {
                                        belsokoltseg[szamlalo] = "";
                                    }
                                    koltseg += belsokoltseg[szamlalo]+"\n"; 
                                }
                                else
                                {
                                    koltseg = belsokoltseg_mezo.getText()+"\n";  
                                }
                                if(table.getRowCount() > 1)
                                {
                                    if(veszteseg[szamlalo] == null)
                                    {
                                        veszteseg[szamlalo] = "";
                                    }
                                    veszt += veszteseg[szamlalo]+"\n";
                                }                                  
                                else
                                {             
                                    veszt = veszteseg_mezo.getText()+"\n";
                                }
                                if(deviza.length > 1)
                                {
                                    if(deviza[szamlalo] == null)
                                    {
                                        deviza[szamlalo] = "";
                                    }
                                    dev += deviza[szamlalo]+"\n";
                                }                                  
                                else
                                {             
                                    dev = deviza_mezo.getText()+"\n";
                                }
                                if(cseredatum.length > 1)
                                {
                                    if(cseredatum[szamlalo] == null)
                                    {
                                        cseredatum[szamlalo] = "";
                                    }
                                    csere += cseredatum[szamlalo]+"\n";
                                }                                  
                                else
                                {             
                                    csere = cseredatum_mezo.getText()+"\n";
                                }
                            }
                            sql = "insert into qualitydb.SQA_reklamaciok (ID,Futo_ID,Datum,Inditotta,Vagy,Cikkszam, Fajta,Gyarto,Beszallito,Projekt,Kontakt,"
                                    + "Hibaleiras,Belso_intezkedes,Hibasdb,Megjelenesido,Reklamacioido,Deviza,Egysegar,Osszertek,Beszallitoi_valasz,Gyokerok,"
                                    + "Beszallitoi_karterites,Belso_koltseg,VEszteseg,Mappa_helye,8D,Credit_note, Megnevezes, Rek_ertek, Csere_datum) "
                                    + "Value('"+ id_mezo.getText()+"','"+futoid_mezo.getText()+"','"+datum_mezo.getText()+"','"+ String.valueOf(indito_box.getSelectedItem())
                                    +"','"+ String.valueOf(intezkedes_box.getSelectedItem())+"','"+ cikkszam+"','"+ String.valueOf(fajta_box.getSelectedItem())
                                    +"','"+gyarto+"','"+beszallito+"','"+projekt+"','"+kontakt+"','"+hibaleir
                                    +"','"+intezked+"','"+hibas+"','"+megjelen+"','"+kezdet
                                    +"','"+dev+"','"+egysegar+"','"+ertek
                                    +"','"+besz_valasz+"','"+gyoker+"','"+terit+"','"+koltseg+"','"+veszt
                                    +"','"+link2+"','"+ d +"','"+ cn +"','"+ megnevezes +"','"+ mindenertek_mezo.getText() +"','"+ csere +"')";
                        }
                        lekerdezes.mindenes(sql);
                        int ertesiteni = 0;
                        String cikkek = "";
                        String hiba = "";
                        int bennevan = 0;
                        if(System.getProperty("user.name").equals("toth.zoltan")){}
                        else
                        {
                            for(int szamlalo = 0; szamlalo < cseredatum.length; szamlalo++)
                            {
                                //System.out.println(cseredatum.length);
                                if(cseredatum[szamlalo] != null) 
                                {                                    
                                    if(cseredatum[szamlalo].equals("")) {}
                                    else
                                    {
                                        try
                                        {
                                            
                                            String formazott = cseredatum[szamlalo].replace("-", ".");
                                            @SuppressWarnings("unused")
                                            Date date = new SimpleDateFormat("yyyy.MM.dd").parse(formazott);
                                            ertesiteni++;
                                            cikkek += table.getValueAt(szamlalo, 0).toString() +";";
                                            hiba += hibaleiras[szamlalo] +";";
                                        }
                                        catch (Exception e1) 
                                        {
                                            System.out.println("Nem dátum " +cseredatum[szamlalo]);
                                        }
                                    }
                                }
                                else
                                {
                                    cseredatum[szamlalo] = "";
                                }
                            }
                            if(ertesiteni > 0)
                            {
                                sql = "select Ertesitve_2 from qualitydb.SQA_reklamaciok where id = "+ id_mezo.getText();
                                String[] ertesitve = lekerdezes.tombvissza_sajat(sql);
                                String[] cikkszamok = ertesitve[0].split(";");
                                String[] keresett = cikkek.split(";");
                                String[] hibak = hiba.split(";"); 
                                for(int szamlalo = 0; szamlalo < keresett.length; szamlalo++)
                                {
                                    bennevan = 0;
                                    for(int szamlalo2 = 0; szamlalo2 < cikkszamok.length; szamlalo2++)
                                    {
                                        if(keresett[szamlalo].equals(cikkszamok[szamlalo2])) 
                                        {
                                            bennevan++;
                                        }
                                    }                                                      
                                    if(bennevan == 0)
                                    {
                                        Email email = new Email();          //schweighardt.robert@veas.videoton.hu,horvath.balazs@veas.videoton.hu,toth.zoltan@veas.videoton.hu
                                        String targy = "Csere alkatrész érkezett a "+ id_mezo.getText() +" azonosítójú reklamáció esetén";
                                        email.mindenes_email("veassqa@veas.videoton.hu",cimzettek, cc, targy, 
                                                "Sziasztok! \n \nBeérkezett a cserealkatrész az alábbi reklamációhoz: \n\n"
                                                + "Reklamáció ID: "+ id_mezo.getText() +" \n"
                                                + "Cikkszám: "+ keresett[szamlalo] +"\n"
                                                + "Hibaleírás: "+ hibak[szamlalo] +" \n"
                                                + "Beérkezés dátuma: "+ cseredatum_mezo.getText() +" \n \n"
                                                + "Ha a csere alkatrésszel lokációzás előtt egyéb teendő szükséges (pl. mérnöki gyártás), akkor az email érkezése után egy napon belül keresd fel kérlek az SQA mérnökséget! \n \n"
                                                + "Üdvözlettel: SQA");
                                        sql = "Update qualitydb.SQA_reklamaciok set ertesitve_2 ='"+ ertesitve[0]+";"+ keresett[szamlalo] +"' where id = '"+ id_mezo.getText() +"'";
                                        lekerdezes.mindenes(sql);
                                    }
                                }
                            }
                        }
                        cseredatum_mezo.setText("");
                        futoid_mezo.setText("");
                        datum_mezo.setText("");                        
                        hibaleiras_mezo.setText("");
                        intezkedes_mezo.setText("");
                        hibasdb_mezo.setText("");
                        megjelenes_ido.setText("");
                        reklamacio_ido.setText("");
                        deviza_mezo.setText("0");
                        osszertek_mezo.setText("0");
                        valasz_mezo.setText("");
                        gyokerok_mezo.setText("");
                        karterites_mezo.setText("0");
                        belsokoltseg_mezo.setText("0");
                        veszteseg_mezo.setText("0");
                        cikkszam_mezo.setText("");
                        d_csekk.setSelected(false);
                        cn_csekk.setSelected(false);
                        tortenesideje_mezo.setText("");
                        mindenertek_mezo.setText("");
                        int rowCount = modell.getRowCount();
                        for (int i = rowCount - 1; i > -1; i--) 
                        {
                          modell.removeRow(i);
                        }
                        table.setModel(modell);
                        Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                                                //egér mutató változtatása munka a háttérbenre                    
                        String id = id_mezo.getText();
                        SQA_bevitel sqa_rek = new SQA_bevitel(id);
                        JScrollPane ablak = new JScrollPane(sqa_rek);
                        ablak.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                        ablak.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                        Foablak.frame.setContentPane(ablak);
                        Foablak.frame.pack();
                        Foablak.frame.setCursor(null);                                                                                          //egér mutató alaphelyzetbe állítása
                        Foablak.frame.setCursor(null);          //egér mutató alaphelyzetbe állítása
                    }
                }
                }
                catch (Exception e1) 
                {
                    e1.printStackTrace();
                    String hibauzenet = e1.toString();
                    Email hibakuldes = new Email();
                    hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", getClass()+" "+ hibauzenet);
                    JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                     //kivétel esetén kiírja a hibaüzenetet
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
                    String id = id_mezo.getText();
                    SQA_bevitel sqa_rek = new SQA_bevitel(id);
                    JScrollPane ablak = new JScrollPane(sqa_rek);
                    ablak.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    ablak.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                    Foablak.frame.setContentPane(ablak);
                    Foablak.frame.pack();
                }
                
                Foablak.frame.setCursor(null);                                                                                          //egér mutató alaphelyzetbe állítása
            } 
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", getClass()+" "+ hibauzenet);
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
    
    class Tortenet implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                                                //egér mutató változtatása munka a háttérbenre
                String sql = "update qualitydb.SQA_reklamaciok set  Statusz = '"+ statusz_mezo.getText() +"', Statusz_ido = '"+ tortenesideje_mezo.getText() +"' where ID = '"+ id_mezo.getText() +"'";
                lekerdezes.mindenes(sql);
                Foablak.frame.setCursor(null);                                                                                          //egér mutató alaphelyzetbe állítása
            }
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", getClass()+" "+ hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                     //kivétel esetén kiírja a hibaüzenetet
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
                String sql = "update qualitydb.SQA_reklamaciok set  Lezaras_ido = '"+ lezarido_mezo.getText() +"' where ID = '"+ id_mezo.getText() +"'";
                lekerdezes.mindenes(sql);
                Foablak.frame.setCursor(null);                                                                                          //egér mutató alaphelyzetbe állítása
            }
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", getClass()+" "+ hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                     //kivétel esetén kiírja a hibaüzenetet
            }
         }
    }
    
    class Ujranyit implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                                                //egér mutató változtatása munka a háttérbenre
                String sql = "update qualitydb.SQA_reklamaciok set  Lezaras_ido = null where ID = '"+ id_mezo.getText() +"'";
                lekerdezes.mindenes(sql);
                Foablak.frame.setCursor(null);                                                                                          //egér mutató alaphelyzetbe állítása
            }
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", getClass()+" "+ hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                     //kivétel esetén kiírja a hibaüzenetet
            }
         }
    }
    
    class Vissza implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                                                //egér mutató változtatása munka a háttérbenre
                SQA_osszesito sqa_ossz = new SQA_osszesito();
                JScrollPane ablak = new JScrollPane(sqa_ossz);
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
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", getClass()+" "+ hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                     //kivétel esetén kiírja a hibaüzenetet
            }
         }
    }
    
    
    
    @SuppressWarnings("resource")
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
            String SQL = "select * from qualitydb.SQA_reklamaciok where id = '"+ id +"'";
            ResultSet rs = stmt.executeQuery(SQL);
            if(rs.next())
            {
                id_mezo.setText(id);
                futoid_mezo.setText(rs.getString(2));
                String[] ido = rs.getString(3).split(" ");
                String ido2 = ido[0];
                datum_mezo.setText(ido[0]);
                indito_box.setSelectedItem(rs.getString(4));
                intezkedes_box.setSelectedItem(rs.getString(5));                           
                fajta_box.setSelectedItem(rs.getString(7));
                //deviza_mezo.setText(rs.getString(17));
                
                String[] cikkszam = rs.getString(6).split("\n");
                String[] megnevezes = rs.getString(32).split("\n");
                String[] gyarto = rs.getString(8).split("\n");
                if(gyarto.length < 1)
                {
                    gyarto =  new String[]{""};
                }
                String[] beszallito = rs.getString(9).split("\n");
                if(beszallito.length < 1)
                {
                    beszallito =  new String[]{""};
                }
                String[] projekt = rs.getString(10).split("\n");
                String[] kontakt = rs.getString(11).split("\n");
                if(kontakt.length < 1)
                {
                    kontakt =  new String[]{""};
                }
                String[] egysegar = rs.getString(18).split("\n");
                for(int szamlalo = 0;szamlalo < cikkszam.length;szamlalo++)
                {
                    modell.addRow(new Object[]{cikkszam[szamlalo],megnevezes[szamlalo],gyarto[szamlalo],beszallito[szamlalo],projekt[szamlalo],kontakt[szamlalo],egysegar[szamlalo]});                            
                }
                
                /*final TableColumnModel columnModel = table.getColumnModel();                    //JTable automatikus mértezése
                for (int column = 0; column < table.getColumnCount(); column++) {
                    int width = 15; // Min width
                    for (int row = 0; row < table.getRowCount(); row++) {
                        TableCellRenderer renderer = table.getCellRenderer(row, column);
                        Component comp = table.prepareRenderer(renderer, row, column);
                        width = Math.max(comp.getPreferredSize().width +1 , width);
                    }
                    if(width > 300)
                        width=300;
                    columnModel.getColumn(column).setPreferredWidth(width);
                }*/
                table.getColumnModel().getColumn(0).setPreferredWidth(100);
                table.getColumnModel().getColumn(1).setPreferredWidth(100);
                table.getColumnModel().getColumn(2).setPreferredWidth(100);
                table.getColumnModel().getColumn(3).setPreferredWidth(100);
                table.getColumnModel().getColumn(4).setPreferredWidth(20);
                table.getColumnModel().getColumn(5).setPreferredWidth(20);
                table.getColumnModel().getColumn(6).setPreferredWidth(20);
                table.setRowSelectionInterval(0, 0);          /////////////////////////////////////////////////////////////////////////////////////////
                String link2 = rs.getString(25);
                
                hibaleiras = new String[table.getRowCount()];
                belsointezkedes = new String[table.getRowCount()];
                valasz = new String[table.getRowCount()];
                gyokerok = new String[table.getRowCount()];
                
                String[] hibaleira = rs.getString(12).split("\n");
                for(int szamlalo =0; szamlalo < hibaleira.length;szamlalo++)
                {
                    hibaleiras[szamlalo] = hibaleira[szamlalo];
                }
                String[] belsointezkede =  rs.getString(13).split("\n");
                for(int szamlalo =0; szamlalo < belsointezkede.length;szamlalo++)
                {
                    belsointezkedes[szamlalo] = belsointezkede[szamlalo];
                }
                String[] valas = rs.getString(20).split("\n");
                for(int szamlalo =0; szamlalo < valas.length;szamlalo++)
                {
                    valasz[szamlalo] = valas[szamlalo];
                }
                String[] gyokero = rs.getString(21).split("\n");
                for(int szamlalo =0; szamlalo < gyokero.length;szamlalo++)
                {
                    gyokerok[szamlalo] = gyokero[szamlalo];
                }
                
                if(hibaleiras.length > 0)
                {
                    hibaleiras_mezo.setText(hibaleiras[0]);
                }
                if(belsointezkedes.length > 0)
                {
                    intezkedes_mezo.setText(belsointezkedes[0]);
                }
                if(valasz.length > 0)
                {
                    valasz_mezo.setText(valasz[0]);
                }
                if(gyokerok.length > 0)
                {
                    gyokerok_mezo.setText(gyokerok[0]);
                }
                
                hibasdb = new String[table.getRowCount()];
                megjelenes = new String[table.getRowCount()];
                rekkezdet = new String[table.getRowCount()];
                osszertek = new String[table.getRowCount()];
                karterites = new String[table.getRowCount()];
                belsokoltseg = new String[table.getRowCount()];
                veszteseg = new String[table.getRowCount()];
                deviza = new String[table.getRowCount()];
                cseredatum = new String[table.getRowCount()];
                
                
                String[] hibasd = rs.getString(14).split("\n");
                for(int szamlalo =0; szamlalo < hibasd.length;szamlalo++)
                {
                    if(szamlalo > hibasdb.length)
                    {
                        hibasdb[szamlalo-1] += hibasd[szamlalo];
                    }
                    else
                    {    
                        hibasdb[szamlalo] = hibasd[szamlalo];
                    }
                }
                String[] megjelene = rs.getString(15).split("\n");
                for(int szamlalo =0; szamlalo < megjelene.length;szamlalo++)
                {
                    if(szamlalo > megjelenes.length)
                    {
                        megjelenes[szamlalo-1] += megjelene[szamlalo];
                    }
                    else
                    {
                        megjelenes[szamlalo] = megjelene[szamlalo];
                    }
                }
                String[] rekkezde = rs.getString(16).split("\n");
                for(int szamlalo =0; szamlalo < rekkezde.length;szamlalo++)
                {
                    if(szamlalo > rekkezdet.length)
                    {
                        rekkezdet[szamlalo-1] += rekkezde[szamlalo];
                    }
                    else
                    {
                        rekkezdet[szamlalo] = rekkezde[szamlalo];
                    }
                }
                String[] osszerte = rs.getString(19).split("\n");
                for(int szamlalo =0; szamlalo < osszerte.length;szamlalo++)
                {
                    if(szamlalo > osszertek.length)
                    {
                        osszertek[szamlalo-1] += osszerte[szamlalo];
                    }
                    else
                    {
                        osszertek[szamlalo] = osszerte[szamlalo];
                    }
                }
                String[] karterite = rs.getString(22).split("\n");
                for(int szamlalo =0; szamlalo < karterite.length;szamlalo++)
                {
                    if(szamlalo > karterites.length)
                    {
                        karterites[szamlalo-1] += karterite[szamlalo];
                    }
                    else
                    {
                        karterites[szamlalo] = karterite[szamlalo];
                    }
                }
                String[] belsokoltse= rs.getString(23).split("\n");
                for(int szamlalo =0; szamlalo < belsokoltse.length;szamlalo++)
                {
                    if(szamlalo > belsokoltseg.length)
                    {
                        belsokoltseg[szamlalo-1] += belsokoltse[szamlalo];
                    }
                    else
                    {
                        belsokoltseg[szamlalo] = belsokoltse[szamlalo];
                    }
                }
                String[] vesztese = rs.getString(24).split("\n");
                for(int szamlalo =0; szamlalo < vesztese.length;szamlalo++)
                {
                    veszteseg[szamlalo] = vesztese[szamlalo];
                }
                String[] deviz = rs.getString(17).split("\n");
                for(int szamlalo =0; szamlalo < deviz.length;szamlalo++)
                {
                    deviza[szamlalo] = deviz[szamlalo];
                }
                if(rs.getString(34) != null)
                {
                    String[] cseredatu = rs.getString(34).split("\n");
                    for(int szamlalo =0; szamlalo < cseredatu.length;szamlalo++)
                    {
                        cseredatum[szamlalo] = cseredatu[szamlalo];
                    }
                }                
                if(hibasdb.length > 0)
                {
                    hibasdb_mezo.setText(hibasdb[0]);
                    //ido = rs.getString(15).split(" ");
                }
                if(megjelenes.length > 0)
                {
                    megjelenes_ido.setText(megjelenes[0]);
                    //ido = rs.getString(16).split(" ");
                }
                if(rekkezdet.length > 0)
                {
                    reklamacio_ido.setText(rekkezdet[0]);
                }
                
                if(osszertek.length > 0)
                {
                    osszertek_mezo.setText(osszertek[0]);
                }
                //egysegar_mezo.setText(rs.getString(18));
                if(karterites.length > 0)
                {
                    karterites_mezo.setText(karterites[0]);
                }
                if(belsokoltseg.length > 0)
                {
                    belsokoltseg_mezo.setText(belsokoltseg[0]);
                }
                if(veszteseg.length > 0)
                {
                    veszteseg_mezo.setText(veszteseg[0]);
                }
                if(deviza.length > 0)
                {
                    deviza_mezo.setText(deviza[0]);
                }
                if(cseredatum.length > 0)
                {
                    cseredatum_mezo.setText(cseredatum[0]);
                }
                
                if(rs.getString(26).equals("igen"))
                {
                    d_csekk.setSelected(true);
                }
                if(rs.getString(27).equals("igen"))
                {
                    cn_csekk.setSelected(true);
                }                       
                statusz_mezo.setText(rs.getString(28));
                if(rs.getString(29) != null)
                {
                    ido = rs.getString(29).split(" ");
                    tortenesideje_mezo.setText(ido[0]);
                }
                mindenertek_mezo.setText(rs.getString(33));
                if(rs.getString(30) != null)
                {                            
                    String[] lezarasideje = rs.getString(30).split(" ");
                    SQL = "select  DATEDIFF('"+ lezarasideje[0] +"', '"+ ido2 +"')";
                    rs = stmt.executeQuery(SQL);
                    if(rs.next())
                    {
                        nyitva_mezo.setText(rs.getString(1));
                        lezarido_mezo.setText(lezarasideje[0]);
                    } 
                }
                else
                {
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
                    Date date = new Date();                                     
                    SQL = "select  DATEDIFF('"+ formatter.format(date) +"', '"+ ido2 +"')";
                    rs = stmt.executeQuery(SQL);
                    if(rs.next())
                    {
                        nyitva_mezo.setText(rs.getString(1));
                    } 
                }
                
                link_mezo.setText(link2);
                link = new File("\\\\10.1.0.11\\minosegbiztositas\\SQA\\\\reklamációk\\"+link2);
                
            }
            stmt.close();
            conn.close();
                    
            }             
            catch (Exception e1) 
            {
               e1.printStackTrace();
               String hibauzenet = e1.toString();
               Email hibakuldes = new Email();
               hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", getClass()+" "+ hibauzenet);
               JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
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
            hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", getClass()+" "+ hibauzenet);
            JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
        }    
    }
    
    class Excel implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                                                //egér mutató változtatása munka a háttérbenre
                SQA_SQL lekerdez = new SQA_SQL();
                String sql = "select * from qualitydb.SQA_reklamaciok where 3=3 and ID = '"+ id_mezo.getText() +"'";
                lekerdez.minden_excel(sql, "SQA Reklamáció-k.xlsx");
                Foablak.frame.setCursor(null);                                                                                          //egér mutató alaphelyzetbe állítása
            }
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", getClass()+" "+ hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                   //kivétel esetén kiírja a hibaüzenetet
            }
         }
    }
    
    class Mappa implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                                                //egér mutató változtatása munka a háttérbenre
                if(link.exists())
                {
                    Desktop.getDesktop().open(new File(link.getAbsolutePath()));                            //megnyitja az excelben szereplő helyen levő infó fájlt      
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Nincs ilyen mappa", "Hiba üzenet", 2); 
                }
                Foablak.frame.setCursor(null);                                                                                          //egér mutató alaphelyzetbe állítása
            }
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", getClass()+" "+ hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                   //kivétel esetén kiírja a hibaüzenetet
            }
         }
    }
    
    class Mappa_letrehoz implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                                                //egér mutató változtatása munka a háttérbenre
                link = new File("\\\\10.1.0.11\\minosegbiztositas\\SQA\\\\reklamációk\\"+link_mezo.getText()+"\\");
                if(link.exists()){System.out.println("Létezik");}
                else
                {
                    link.mkdirs();
                    System.out.println("Nem Létezik");
                    //link.createNewFile();  
                }
                
                Foablak.frame.setCursor(null);                                                                                          //egér mutató alaphelyzetbe állítása
            }
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", getClass()+" "+ hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                   //kivétel esetén kiírja a hibaüzenetet
            }
         }
    }
    
    private class Sorvalaszto implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            // TODO Auto-generated method stub            
            //int row = table.getSelectedRow();
            //int col = table.getSelectedColumn();
            
            if(e.getValueIsAdjusting())
            {
                //System.out.println(table.getValueAt(row, col));
                
                hibaleiras[valasztott] = hibaleiras_mezo.getText();
                belsointezkedes[valasztott] = intezkedes_mezo.getText();
                valasz[valasztott] = valasz_mezo.getText();
                gyokerok[valasztott] = gyokerok_mezo.getText();
                
                hibasdb[valasztott] = hibasdb_mezo.getText();                
                megjelenes[valasztott] = megjelenes_ido.getText();
                cseredatum[valasztott] = cseredatum_mezo.getText();
                if(reklamacio_ido.getText().equals("")) {}
                else
                {
                    for(int szamlalo = 0; szamlalo < rekkezdet.length;szamlalo++)
                    {
                        rekkezdet[szamlalo] = reklamacio_ido.getText();
                    }
                }
                                
                osszertek[valasztott] = osszertek_mezo.getText();               
                karterites[valasztott] = karterites_mezo.getText();               
                belsokoltseg[valasztott] = belsokoltseg_mezo.getText();              
                veszteseg[valasztott] = veszteseg_mezo.getText();
                String[] koztes = deviza_mezo.getText().split(" ");
                if(koztes.length> 1)
                {
                    deviza[valasztott] = deviza_mezo.getText();      //+ " "+ String.valueOf(deviza_box.getSelectedItem());
                }
                else
                {
                    deviza[valasztott] = deviza_mezo.getText()+ " "+ String.valueOf(deviza_box.getSelectedItem());
                }
                valasztott = table.getSelectedRow();
                hibaleiras_mezo.setText(hibaleiras[valasztott]);              
                intezkedes_mezo.setText(belsointezkedes[valasztott]);              
                valasz_mezo.setText(valasz[valasztott]);
                gyokerok_mezo.setText(gyokerok[valasztott]);
                
                hibasdb_mezo.setText(hibasdb[valasztott]);                
                megjelenes_ido.setText(megjelenes[valasztott]);                
                reklamacio_ido.setText(rekkezdet[valasztott]);                
                osszertek_mezo.setText(osszertek[valasztott]);               
                karterites_mezo.setText(karterites[valasztott]);               
                belsokoltseg_mezo.setText(belsokoltseg[valasztott]);              
                veszteseg_mezo.setText(veszteseg[valasztott]);
                deviza_mezo.setText(deviza[valasztott]);
                cseredatum_mezo.setText(cseredatum[valasztott]);
             
            }           
        }
    }
    
    class Szamol implements KeyListener                                                                                                 //billentyűzet figyelő eseménykezelő, kiszámolja mennyit kell ellenőrizni
    {
        public void keyPressed (KeyEvent e) 
        {
            try
            {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_ENTER)                                                                                               //ha az entert nyomják le akkor hívódik meg
                {
                    Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                                                //egér mutató változtatása munka a háttérbenre
                    int sor = table.getSelectedRow();
                    if(sor < 0)
                    {
                        sor = 0;
                    }
                    osszertek_mezo.setText(String.valueOf(Integer.parseInt(hibasdb_mezo.getText()) * Integer.parseInt(table.getValueAt(sor, 6).toString())));
                    int ertek = 0;
                    if(osszertek.length > 1)
                    {
                        for(int szamlalo = 0; szamlalo < osszertek.length; szamlalo++)
                        {
                            try
                            {
                                ertek += Integer.parseInt(osszertek[szamlalo]);
                            }
                            catch (Exception e1) 
                            {
                                ertek += 0;                           
                            }
                        }
                        mindenertek_mezo.setText(String.valueOf(ertek));
                    }
                    if(table.getRowCount() == 1)
                    {
                        mindenertek_mezo.setText(osszertek_mezo.getText());
                    }
                    
                    Foablak.frame.setCursor(null);                                                                                          //egér mutató alaphelyzetbe állítása
                }
            }
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", getClass()+" "+ hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                   //kivétel esetén kiírja a hibaüzenetet
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void keyReleased(KeyEvent e) {
            // TODO Auto-generated method stub
            
        }
    }
}
