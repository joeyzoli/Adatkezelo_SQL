import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JRadioButton;

public class Retour_szeriaszamok extends JPanel {
    private JTextField retourid_mezo;
    private JTextField szeriaszam_mezo;
    private JCheckBox vizualis_csekk1;private JCheckBox vizualis_csekk3;private JCheckBox vizualis_csekk4;private JCheckBox vizualis_csekk5;private JCheckBox vizualis_csekk6;
    private JCheckBox ict_csekk1;private JCheckBox ict_csekk3;private JCheckBox ict_csekk4;private JCheckBox ict_csekk5;private JCheckBox ict_csekk6;
    private JCheckBox fct_csekk1;private JCheckBox fct_csekk3;private JCheckBox fct_csekk4;private JCheckBox fct_csekk5;private JCheckBox fct_csekk6;
    private JCheckBox meres_csekk1;private JCheckBox meres_csekk3;private JCheckBox meres_csekk4;private JCheckBox meres_csekk5;private JCheckBox meres_csekk6;
    private JCheckBox rontgen_csekk1;private JCheckBox rontgen_csekk3;private JCheckBox rontgen_csekk4;private JCheckBox rontgen_csekk5;private JCheckBox rontgen_csekk6;
    private JCheckBox egyeb_csekk1;private JCheckBox egyeb_csekk3;private JCheckBox egyeb_csekk4;private JCheckBox egyeb_csekk5;private JCheckBox egyeb_csekk6;
    private JCheckBox kiszallithato_OK;private JCheckBox kiszallithato_NOK;
    private JCheckBox beszallito;private JCheckBox veas;private JCheckBox vevo;
    private JTextArea hiba_mezo;private JTextArea javitas_mezo;private JTextArea hibaoka_mezo;private JTextArea intezkedes_mezo;
    private JComboBox<String> hibakod_box;
    private ComboBox combobox_tomb;
    private ArrayList<String> kephelye = new ArrayList<String>();
    private ArrayList<String> kepneve = new ArrayList<String>();
    private JRadioButton csekk1;private JRadioButton csekk2;private JRadioButton csekk3;private JRadioButton csekk4;private JRadioButton csekk5;private JRadioButton csekk6;private JRadioButton csekk7;
    private JRadioButton csekk8;private JRadioButton csekk9;private JRadioButton csekk10;private JRadioButton csekk11;private JRadioButton csekk12;private JRadioButton csekk13;private JRadioButton csekk14;
    private JRadioButton csekk15;private JRadioButton csekk16;private JRadioButton csekk17;private JRadioButton csekk18;
    private JButton kepmentes_gomb;
    private JCheckBox nff;
    private String db_id = "";

    /**
     * Create the panel.
     */
    public Retour_szeriaszamok() {
        setLayout(null);
        Foablak.meretek.setSize(1380, 850);
        JLabel lblNewLabel = new JLabel("Retour szériaszámok nyomonkövetése");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 13));
        lblNewLabel.setBounds(535, 44, 278, 14);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Retour ID");
        lblNewLabel_1.setBounds(78, 95, 67, 14);
        add(lblNewLabel_1);
        
        retourid_mezo = new JTextField();
        retourid_mezo.setBounds(155, 92, 46, 20);
        add(retourid_mezo);
        retourid_mezo.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("Retour szériaszám");
        lblNewLabel_2.setBounds(78, 134, 118, 14);
        add(lblNewLabel_2);
        
        szeriaszam_mezo = new JTextField();
        szeriaszam_mezo.addKeyListener(new Enter_szeriaszam());
        szeriaszam_mezo.setBounds(206, 131, 303, 20);
        add(szeriaszam_mezo);
        szeriaszam_mezo.setColumns(10);        
        setBackground(Foablak.hatter_szine);
        
        JLabel lblNewLabel_3 = new JLabel("Vizuális ellenőrzés");
        lblNewLabel_3.setBounds(78, 229, 132, 14);
        add(lblNewLabel_3);
        
        JLabel lblNewLabel_4 = new JLabel("ICT ellenőrzés");
        lblNewLabel_4.setBounds(78, 273, 86, 14);
        add(lblNewLabel_4);
        
        JLabel lblNewLabel_5 = new JLabel("FCT ellenőrzés");
        lblNewLabel_5.setBounds(78, 319, 83, 14);
        add(lblNewLabel_5);
        
        JLabel lblNewLabel_6 = new JLabel("Méréses ellenőrzés");
        lblNewLabel_6.setBounds(78, 366, 112, 14);
        add(lblNewLabel_6);
        
        JLabel lblNewLabel_7 = new JLabel("Röntgen ellenőrzés");
        lblNewLabel_7.setBounds(78, 415, 112, 14);
        add(lblNewLabel_7);
        
        JLabel lblNewLabel_8 = new JLabel("Egyéb");
        lblNewLabel_8.setBounds(78, 465, 46, 14);
        add(lblNewLabel_8);
        
        vizualis_csekk1 = new JCheckBox("");
        vizualis_csekk1.setBounds(220, 225, 37, 23);
        add(vizualis_csekk1);
        
        JLabel lblNewLabel_9 = new JLabel("Alkalazott");
        lblNewLabel_9.setBounds(219, 202, 77, 14);
        add(lblNewLabel_9);
        
        JLabel lblNewLabel_11 = new JLabel("Szükséges vizsgálatok");
        lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_11.setBounds(206, 177, 156, 14);
        add(lblNewLabel_11);
        
        ict_csekk1 = new JCheckBox("");
        ict_csekk1.setBounds(220, 269, 37, 23);
        add(ict_csekk1);
        
        fct_csekk1 = new JCheckBox("");
        fct_csekk1.setBounds(220, 315, 37, 23);
        add(fct_csekk1);
        
        meres_csekk1 = new JCheckBox("");
        meres_csekk1.setBounds(220, 362, 37, 23);
        add(meres_csekk1);
        
        rontgen_csekk1 = new JCheckBox("");
        rontgen_csekk1.setBounds(220, 411, 37, 23);
        add(rontgen_csekk1);
        
        egyeb_csekk1 = new JCheckBox("");
        egyeb_csekk1.setBounds(220, 461, 37, 23);
        add(egyeb_csekk1);
        
        JLabel lblNewLabel_12 = new JLabel("Beérkezéskor");
        lblNewLabel_12.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_12.setBounds(425, 177, 100, 14);
        add(lblNewLabel_12);
        
        vizualis_csekk3 = new JCheckBox("");
        vizualis_csekk3.setBounds(425, 225, 37, 23);
        add(vizualis_csekk3);
        
        ict_csekk3 = new JCheckBox("");
        ict_csekk3.setBounds(425, 269, 37, 23);
        add(ict_csekk3);
        
        fct_csekk3 = new JCheckBox("");
        fct_csekk3.setBounds(425, 315, 37, 23);
        add(fct_csekk3);
        
        meres_csekk3 = new JCheckBox("");
        meres_csekk3.setBounds(425, 362, 37, 23);
        add(meres_csekk3);
        
        rontgen_csekk3 = new JCheckBox("");
        rontgen_csekk3.setBounds(425, 411, 37, 23);
        add(rontgen_csekk3);
        
        egyeb_csekk3 = new JCheckBox("");
        egyeb_csekk3.setBounds(425, 461, 37, 23);
        add(egyeb_csekk3);
        
        JLabel lblNewLabel_13 = new JLabel("OK");
        lblNewLabel_13.setBounds(430, 202, 37, 14);
        add(lblNewLabel_13);
        
        JLabel lblNewLabel_14 = new JLabel("NOK");
        lblNewLabel_14.setBounds(481, 202, 46, 14);
        add(lblNewLabel_14);
        
        vizualis_csekk4 = new JCheckBox("");
        vizualis_csekk4.setBounds(481, 225, 28, 23);
        add(vizualis_csekk4);
        
        ict_csekk4 = new JCheckBox("");
        ict_csekk4.setBounds(481, 269, 28, 23);
        add(ict_csekk4);
        
        fct_csekk4 = new JCheckBox("");
        fct_csekk4.setBounds(481, 315, 28, 23);
        add(fct_csekk4);
        
        meres_csekk4 = new JCheckBox("");
        meres_csekk4.setBounds(481, 362, 28, 23);
        add(meres_csekk4);
        
        rontgen_csekk4 = new JCheckBox("");
        rontgen_csekk4.setBounds(481, 411, 28, 23);
        add(rontgen_csekk4);
        
        egyeb_csekk4 = new JCheckBox("");
        egyeb_csekk4.setBounds(481, 461, 28, 23);
        add(egyeb_csekk4);
        
        JLabel lblNewLabel_15 = new JLabel("Javítás után");
        lblNewLabel_15.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_15.setBounds(623, 178, 100, 14);
        add(lblNewLabel_15);
        
        vizualis_csekk5 = new JCheckBox("");
        vizualis_csekk5.setBounds(623, 225, 28, 23);
        add(vizualis_csekk5);
        
        ict_csekk5 = new JCheckBox("");
        ict_csekk5.setBounds(623, 269, 28, 23);
        add(ict_csekk5);
        
        fct_csekk5 = new JCheckBox("");
        fct_csekk5.setBounds(623, 315, 28, 23);
        add(fct_csekk5);
        
        meres_csekk5 = new JCheckBox("");
        meres_csekk5.setBounds(623, 362, 28, 23);
        add(meres_csekk5);
        
        rontgen_csekk5 = new JCheckBox("");
        rontgen_csekk5.setBounds(623, 411, 28, 23);
        add(rontgen_csekk5);
        
        egyeb_csekk5 = new JCheckBox("");
        egyeb_csekk5.setBounds(623, 461, 28, 23);
        add(egyeb_csekk5);
        
        vizualis_csekk6 = new JCheckBox("");
        vizualis_csekk6.setBounds(668, 225, 28, 23);
        add(vizualis_csekk6);
        
        ict_csekk6 = new JCheckBox("");
        ict_csekk6.setBounds(668, 269, 28, 23);
        add(ict_csekk6);
        
        fct_csekk6 = new JCheckBox("");
        fct_csekk6.setBounds(668, 315, 28, 23);
        add(fct_csekk6);
        
        meres_csekk6 = new JCheckBox("");
        meres_csekk6.setBounds(668, 362, 28, 23);
        add(meres_csekk6);
        
        rontgen_csekk6 = new JCheckBox("");
        rontgen_csekk6.setBounds(668, 411, 28, 23);
        add(rontgen_csekk6);
        
        egyeb_csekk6 = new JCheckBox("");
        egyeb_csekk6.setBounds(668, 461, 28, 23);
        add(egyeb_csekk6);
        
        JLabel lblNewLabel_16 = new JLabel("Megállapítás");
        lblNewLabel_16.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_16.setBounds(929, 178, 112, 14);
        add(lblNewLabel_16);
        
        JLabel lblNewLabel_17 = new JLabel("Hibakód");
        lblNewLabel_17.setBounds(776, 261, 46, 14);
        add(lblNewLabel_17);
        
        JLabel lblNewLabel_18 = new JLabel("Hiba eredete");
        lblNewLabel_18.setBounds(964, 202, 77, 14);
        add(lblNewLabel_18);
        
        beszallito = new JCheckBox("Beszállító");
        beszallito.setBounds(899, 225, 83, 23);
        add(beszallito);
        
        veas = new JCheckBox("VEAS");
        veas.setBounds(980, 225, 67, 23);
        add(veas);
        
        vevo = new JCheckBox("Vevő");
        vevo.setBounds(1046, 225, 67, 23);
        add(vevo);
        
        JLabel lblNewLabel_19 = new JLabel("Hiba leírása");
        lblNewLabel_19.setBounds(776, 319, 77, 14);
        add(lblNewLabel_19);
        
        hiba_mezo = new JTextArea();               
        hiba_mezo.setLineWrap(true);
        hiba_mezo.setWrapStyleWord(true);
        JScrollPane gorgeto1 = new JScrollPane(hiba_mezo);
        gorgeto1.setBounds(868, 314, 285, 81);
        add(gorgeto1);
        
        JLabel lblNewLabel_20 = new JLabel("Javítás leírása");
        lblNewLabel_20.setBounds(767, 420, 86, 14);
        add(lblNewLabel_20);
        
        javitas_mezo = new JTextArea();        
        javitas_mezo.setLineWrap(true);
        javitas_mezo.setWrapStyleWord(true);
        JScrollPane gorgeto2 = new JScrollPane(javitas_mezo);
        gorgeto2.setBounds(868, 423, 285, 81);
        add(gorgeto2);
        
        JLabel lblNewLabel_21 = new JLabel("OK");
        lblNewLabel_21.setBounds(628, 202, 28, 14);
        add(lblNewLabel_21);
        
        JLabel lblNewLabel_22 = new JLabel("NOK");
        lblNewLabel_22.setBounds(668, 202, 46, 14);
        add(lblNewLabel_22);
        
        JLabel lblNewLabel_23 = new JLabel("Vizsgálat/javítás után a termék kiszállítható");
        lblNewLabel_23.setBounds(78, 537, 270, 14);
        add(lblNewLabel_23);
        
        kiszallithato_OK = new JCheckBox("Igen");
        kiszallithato_OK.setBounds(101, 568, 53, 23);
        add(kiszallithato_OK);
        
        kiszallithato_NOK = new JCheckBox("Nem");
        kiszallithato_NOK.setBounds(177, 568, 53, 23);
        add(kiszallithato_NOK);
        
        JLabel lblNewLabel_24 = new JLabel("Hiba oka");
        lblNewLabel_24.setBounds(380, 537, 60, 14);
        add(lblNewLabel_24);
        
        hibaoka_mezo = new JTextArea();        
        hibaoka_mezo.setLineWrap(true);
        hibaoka_mezo.setWrapStyleWord(true);
        JScrollPane gorgeto3 = new JScrollPane(hibaoka_mezo);
        gorgeto3.setBounds(450, 532, 285, 81);
        add(gorgeto3);
        
        JLabel lblNewLabel_25 = new JLabel("Intézkedés leírása");
        lblNewLabel_25.setBounds(757, 537, 112, 14);
        add(lblNewLabel_25);
        
        intezkedes_mezo = new JTextArea();        
        intezkedes_mezo.setLineWrap(true);
        intezkedes_mezo.setWrapStyleWord(true);
        JScrollPane gorgeto4 = new JScrollPane(intezkedes_mezo);
        gorgeto4.setBounds(868, 532, 285, 81);
        add(gorgeto4);
        
        JButton mentes_gomb = new JButton("Mentés");
        mentes_gomb.addActionListener(new Mentes());
        mentes_gomb.setBounds(549, 687, 89, 23);
        add(mentes_gomb);
        
        combobox_tomb = new ComboBox();
        hibakod_box = new JComboBox<String>(combobox_tomb.getCombobox(ComboBox.hibakodok));                      //combobox_tomb.getCombobox(ComboBox.hibakodok)
        hibakod_box.setBounds(836, 257, 504, 22);
        add(hibakod_box);
        
        JButton kepcsatol_gomb = new JButton("Kép csatolása");
        kepcsatol_gomb.addActionListener(new Kephozzadasa());
        kepcsatol_gomb.setBounds(535, 626, 123, 23);
        add(kepcsatol_gomb);
        
        kepmentes_gomb = new JButton("Asztalra");
        kepmentes_gomb.addActionListener(new Kepmentes());
        kepmentes_gomb.setBounds(1027, 654, 89, 23);
        add(kepmentes_gomb);
        
        JLabel lblNewLabel_26 = new JLabel("Csatolt kép mentése az asztalra");
        lblNewLabel_26.setBounds(829, 658, 188, 14);
        add(lblNewLabel_26);
        
        setBackground(Foablak.hatter_szine);
        
        csekk1 = new JRadioButton("");
        csekk1.setBounds(310, 225, 28, 23);
        add(csekk1);
        
        JLabel lblNewLabel_27 = new JLabel("Nem alkalmazott");
        lblNewLabel_27.setBounds(289, 202, 104, 14);
        add(lblNewLabel_27);
        
        csekk4 = new JRadioButton("");
        csekk4.setBounds(310, 269, 28, 23);
        add(csekk4);
        
        csekk7 = new JRadioButton("");
        csekk7.setBounds(310, 315, 28, 23);
        add(csekk7);
        
        csekk10 = new JRadioButton("");
        csekk10.setBounds(310, 362, 28, 23);
        add(csekk10);
        
        csekk13 = new JRadioButton("");
        csekk13.setBounds(310, 411, 28, 23);
        add(csekk13);
        
        csekk16 = new JRadioButton("");
        csekk16.setBounds(310, 461, 28, 23);
        add(csekk16);
        
        csekk2 = new JRadioButton("");
        csekk2.setBounds(512, 225, 28, 23);
        add(csekk2);
        
        csekk5 = new JRadioButton("");
        csekk5.setBounds(511, 269, 28, 23);
        add(csekk5);
        
        csekk8 = new JRadioButton("");
        csekk8.setBounds(511, 315, 28, 23);
        add(csekk8);
        
        csekk11 = new JRadioButton("");
        csekk11.setBounds(511, 362, 28, 23);
        add(csekk11);
        
        csekk14 = new JRadioButton("");
        csekk14.setBounds(511, 411, 28, 23);
        add(csekk14);
        
        csekk17 = new JRadioButton("");
        csekk17.setBounds(512, 461, 28, 23);
        add(csekk17);
        
        csekk3 = new JRadioButton("");
        csekk3.setBounds(698, 225, 28, 23);
        add(csekk3);
        
        csekk6 = new JRadioButton("");
        csekk6.setBounds(698, 269, 28, 23);
        add(csekk6);
        
        csekk9 = new JRadioButton("");
        csekk9.setBounds(698, 315, 28, 23);
        add(csekk9);
        
        csekk12 = new JRadioButton("");
        csekk12.setBounds(698, 362, 28, 23);
        add(csekk12);
        
        csekk15 = new JRadioButton("");
        csekk15.setBounds(698, 411, 28, 23);
        add(csekk15);
        
        csekk18 = new JRadioButton("");
        csekk18.setBounds(698, 461, 28, 23);
        add(csekk18);
        
        JLabel lblNewLabel_28 = new JLabel("Nem alkalazott");
        lblNewLabel_28.setBounds(516, 202, 102, 14);
        add(lblNewLabel_28);
        
        JLabel lblNewLabel_29 = new JLabel("Nem alkalmazott");
        lblNewLabel_29.setBounds(703, 202, 100, 14);
        add(lblNewLabel_29);
        
        nff = new JCheckBox("NFF");
        nff.setBounds(1115, 225, 53, 23);
        add(nff);
        
        JButton tortenet_gomb = new JButton("Történet ellenőrzés");
        tortenet_gomb.addActionListener(new Tortenet());
        tortenet_gomb.setBounds(572, 130, 151, 23);
        add(tortenet_gomb);

    }
    
    class Enter_szeriaszam implements KeyListener                                                                                                 //billentyűzet figyelő eseménykezelő, ID alapján vissztölti az adatokat
    {
        public void keyPressed (KeyEvent e) 
        {    
            try 
            {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_ENTER)                                                                                               //ha az entert nyomják le akkor hívódik meg
                {
                    Connection conn = null;
                    Statement stmt = null;
                    kephelye.clear();
                    kepneve.clear();
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
                    String sql = "select * from qualitydb.Retour_szeriaszamok where VEAS_ID = '"+ szeriaszam_mezo.getText() +"' or Vevoi_ID = '"+ szeriaszam_mezo.getText() +"' order by id desc";
                    stmt.execute(sql);
                    ResultSet rs = stmt.getResultSet();
                    vizualis_csekk1.setSelected(false);        
                    vizualis_csekk3.setSelected(false);        
                    vizualis_csekk4.setSelected(false);        
                    vizualis_csekk5.setSelected(false);        
                    vizualis_csekk6.setSelected(false);       
                    ict_csekk1.setSelected(false);              
                    ict_csekk3.setSelected(false);        
                    ict_csekk4.setSelected(false);        
                    ict_csekk5.setSelected(false);       
                    ict_csekk6.setSelected(false);       
                    fct_csekk1.setSelected(false);             
                    fct_csekk3.setSelected(false);      
                    fct_csekk4.setSelected(false);        
                    fct_csekk5.setSelected(false);      
                    fct_csekk6.setSelected(false);       
                    meres_csekk1.setSelected(false);            
                    meres_csekk3.setSelected(false);        
                    meres_csekk4.setSelected(false);       
                    meres_csekk5.setSelected(false);        
                    meres_csekk6.setSelected(false);       
                    rontgen_csekk1.setSelected(false);              
                    rontgen_csekk3.setSelected(false);        
                    rontgen_csekk4.setSelected(false);       
                    rontgen_csekk5.setSelected(false);      
                    rontgen_csekk6.setSelected(false);       
                    egyeb_csekk1.setSelected(false);               
                    egyeb_csekk3.setSelected(false);        
                    egyeb_csekk4.setSelected(false);        
                    egyeb_csekk5.setSelected(false);        
                    egyeb_csekk6.setSelected(false);
                    csekk1.setSelected(false);
                    csekk2.setSelected(false); 
                    csekk3.setSelected(false); 
                    csekk4.setSelected(false); 
                    csekk5.setSelected(false); 
                    csekk6.setSelected(false); 
                    csekk7.setSelected(false); 
                    csekk8.setSelected(false); 
                    csekk9.setSelected(false); 
                    csekk10.setSelected(false); 
                    csekk11.setSelected(false); 
                    csekk12.setSelected(false); 
                    csekk13.setSelected(false); 
                    csekk14.setSelected(false); 
                    csekk15.setSelected(false); 
                    csekk16.setSelected(false); 
                    csekk17.setSelected(false); 
                    csekk18.setSelected(false); 
                    hibakod_box.setSelectedIndex(1);        
                    beszallito.setSelected(false);        
                    veas.setSelected(false);        
                    vevo.setSelected(false);
                    nff.setSelected(false);
                    hiba_mezo.setText("");
                    javitas_mezo.setText("");
                    hibaoka_mezo.setText("");
                    intezkedes_mezo.setText("");
                    if(rs.next()) {
                        if(rs.getString(5) != null) {
                            if(rs.getString(5).equals("OK")) {
                                vizualis_csekk1.setSelected(true);
                            }                            
                            else if(rs.getString(5).equals("N/A")) {
                                csekk1.setSelected(true);
                            }
                            else {}
                            if(rs.getString(6).equals("OK")) {
                                vizualis_csekk3.setSelected(true);
                            }
                            else if(rs.getString(6).equals("NOK")) {
                                vizualis_csekk4.setSelected(true);
                            }
                            else if(rs.getString(6).equals("N/A")) {
                                csekk2.setSelected(true);
                            }
                            else {}
                            if(rs.getString(7).equals("OK")) {
                                vizualis_csekk5.setSelected(true);
                            }
                            else if(rs.getString(7).equals("NOK")) {
                                vizualis_csekk6.setSelected(true);
                            }
                            else if(rs.getString(7).equals("N/A")) {
                                csekk3.setSelected(true);
                            }
                            else {}
                            if(rs.getString(8).equals("OK")) {
                                ict_csekk1.setSelected(true);
                            }                            
                            else if(rs.getString(8).equals("N/A")) {
                                csekk4.setSelected(true);
                            }
                            else {}
                            if(rs.getString(9).equals("OK")) {
                                ict_csekk3.setSelected(true);
                            }
                            else if(rs.getString(9).equals("NOK")) {
                                ict_csekk4.setSelected(true);
                            }
                            else if(rs.getString(9).equals("N/A")) {
                                csekk5.setSelected(true);
                            }
                            else {}
                            if(rs.getString(10).equals("OK")) {
                                ict_csekk5.setSelected(true);
                            }
                            else if(rs.getString(10).equals("NOK")) {
                                ict_csekk6.setSelected(true);
                            }
                            else if(rs.getString(10).equals("N/A")) {
                                csekk6.setSelected(true);
                            }
                            else {}
                            if(rs.getString(11).equals("OK")) {
                                fct_csekk1.setSelected(true);
                            }                           
                            else if(rs.getString(11).equals("N/A")) {
                                csekk7.setSelected(true);
                            }
                            else {}
                            if(rs.getString(12).equals("OK")) {
                                fct_csekk3.setSelected(true);
                            }
                            else if(rs.getString(12).equals("NOK")) {
                                fct_csekk4.setSelected(true);
                            }
                            else if(rs.getString(12).equals("N/A")) {
                                csekk8.setSelected(true);
                            }
                            else {}
                            if(rs.getString(13).equals("OK")) {
                                fct_csekk5.setSelected(true);
                            }
                            else if(rs.getString(13).equals("NOK")) {
                                fct_csekk6.setSelected(true);
                            }
                            else if(rs.getString(13).equals("N/A")) {
                                csekk9.setSelected(true);
                            }
                            else {}
                            if(rs.getString(14).equals("OK")) {
                                meres_csekk1.setSelected(true);
                            }                            
                            else if(rs.getString(14).equals("N/A")) {
                                csekk10.setSelected(true);
                            }
                            else {}
                            if(rs.getString(15).equals("OK")) {
                                meres_csekk3.setSelected(true);
                            }
                            else if(rs.getString(15).equals("NOK")) {
                                meres_csekk4.setSelected(true);
                            }
                            else if(rs.getString(15).equals("N/A")) {
                                csekk11.setSelected(true);
                            }
                            else {}
                            if(rs.getString(16).equals("OK")) {
                                meres_csekk5.setSelected(true);
                            }
                            else if(rs.getString(16).equals("NOK")) {
                                meres_csekk6.setSelected(true);
                            }
                            else if(rs.getString(16).equals("N/A")) {
                                csekk12.setSelected(true);
                            }
                            else {}
                            if(rs.getString(17).equals("OK")) {
                                rontgen_csekk1.setSelected(true);
                            }                            
                            else if(rs.getString(17).equals("N/A")) {
                                csekk13.setSelected(true);
                            }
                            else {}
                            if(rs.getString(18).equals("OK")) {
                                rontgen_csekk3.setSelected(true);
                            }
                            else if(rs.getString(18).equals("NOK")) {
                                rontgen_csekk4.setSelected(true);
                            }
                            else if(rs.getString(18).equals("N/A")) {
                                csekk14.setSelected(true);
                            }
                            else {}
                            if(rs.getString(19).equals("OK")) {
                                rontgen_csekk5.setSelected(true);
                            }
                            else if(rs.getString(19).equals("NOK")) {
                                rontgen_csekk6.setSelected(true);
                            }
                            else if(rs.getString(19).equals("N/A")) {
                                csekk15.setSelected(true);
                            }
                            else {}
                            if(rs.getString(20).equals("OK")) {
                                egyeb_csekk1.setSelected(true);
                            }                            
                            else if(rs.getString(20).equals("N/A")) {
                                csekk16.setSelected(true);
                            }
                            else {}
                            if(rs.getString(21).equals("OK")) {
                                egyeb_csekk3.setSelected(true);
                            }
                            else if(rs.getString(21).equals("NOK")) {
                                egyeb_csekk4.setSelected(true);
                            }
                            else if(rs.getString(21).equals("N/A")) {
                                csekk17.setSelected(true);
                            }
                            else {}
                            if(rs.getString(22).equals("OK")) {
                                egyeb_csekk5.setSelected(true);
                            }
                            else if(rs.getString(22).equals("NOK")) {
                                egyeb_csekk6.setSelected(true);
                            }
                            else if(rs.getString(22).equals("N/A")) {
                                csekk18.setSelected(true);
                            }
                            else {}
                            hibakod_box.setSelectedItem(rs.getString(23));
                            if(rs.getString(24).equals("Beszállító")) {
                                beszallito.setSelected(true);
                            }
                            else if(rs.getString(24).equals("VEAS")) {
                                veas.setSelected(true);
                            }
                            else if(rs.getString(24).equals("Vevő")) {
                                vevo.setSelected(true);
                            }
                            else if(rs.getString(24).equals("NFF")) {
                                nff.setSelected(true);
                            }
                            else {}
                            hiba_mezo.setText(rs.getString(25));
                            javitas_mezo.setText(rs.getString(26));
                            hibaoka_mezo.setText(rs.getString(27));
                            intezkedes_mezo.setText(rs.getString(28));
                            
                            if(rs.getString(29).equals("OK")) {
                                kiszallithato_OK.setSelected(true);
                            }
                            else if(rs.getString(29).equals("NOK")) {
                                kiszallithato_NOK.setSelected(true);
                            }
                            else {}
                        }
                        else{
                            hiba_mezo.setText(rs.getString(25));
                            javitas_mezo.setText(rs.getString(26));
                            hibaoka_mezo.setText(rs.getString(27));
                            intezkedes_mezo.setText(rs.getString(28));
                        }
                        db_id = rs.getString(1);
                        sql = "select * from qualitydb.Retour_kepek where Szeriaszam = '"+ szeriaszam_mezo.getText() +"'";
                        stmt.execute(sql);
                        rs = stmt.getResultSet();
                        if(rs.next())
                        {
                            kepmentes_gomb.setBackground(Color.GREEN);
                        }
                        else
                        {
                            kepmentes_gomb.setBackground(Color.RED);
                        }
                        
                    }
                    
                    stmt.close();
                    conn.close();
                            
                    }                    
                    catch (Exception e1) 
                    {
                        e1.printStackTrace();
                        String hibauzenet = e1.toString();
                        Email hibakuldes = new Email();
                        hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                        JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                     //kivétel esetén kiírja a hibaüzenetet
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
                           String hibauzenet = se.toString();
                           Email hibakuldes = new Email();
                           hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                           JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                     //kivétel esetén kiírja a hibaüzenetet
                       }  
                    }
                }
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
        @Override
        public void keyTyped(KeyEvent e)                                                //kötelezően kell implementálni, de ezt NOK akarom figyelni, így üresen hagyom 
        {
            // TODO Auto-generated method stub           
        }
        @Override
        public void keyReleased(KeyEvent e)                                             //kötelezően kell implementálni, de ezt NOK akarom figyelni, így üresen hagyom 
        {
            // TODO Auto-generated method stub           
        }    
    }
    
    class Mentes implements ActionListener                                                                                      //futtat gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
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
            String vizualis1 = "";String vizualis2 = "";String vizualis3 = "";
            String ict1 = "";String ict2 = "";String ict3 = "";
            String fct1 = "";String fct2 = "";String fct3 = "";
            String meres1 = "";String meres2 = "";String meres3 = "";
            String rontgen1 = "";String rontgen2 = "";String rontgen3 = "";
            String egyeb1 = "";String egyeb2 = "";String egyeb3 = "";
            String hibaeredet = "";String kiszallithato = "";
            if(retourid_mezo.getText().equals(""))
            {
                if(vizualis_csekk1.isSelected()) {
                    vizualis1 = "OK";
                }                
                else {if(csekk1.isSelected()) {vizualis1 = "N/A";}}
                
                if(vizualis_csekk3.isSelected()) {
                    vizualis2 = "OK";
                }
                else if(vizualis_csekk4.isSelected()) {
                    vizualis2 = "NOK";
                }
                else {if(csekk2.isSelected()) {vizualis2 = "N/A";}}
                
                if(vizualis_csekk5.isSelected()) {
                    vizualis3 = "OK";
                }
                else if(vizualis_csekk6.isSelected()) {
                    vizualis3 = "NOK";
                }
                else {if(csekk3.isSelected()) {vizualis3 = "N/A";}}
                
                if(ict_csekk1.isSelected()) {
                    ict1 = "OK";
                }                
                else {if(csekk4.isSelected()) { ict1 = "N/A";}}
                
                if(ict_csekk3.isSelected()) {
                    ict2 = "OK";
                }
                else if(ict_csekk4.isSelected()) {
                    ict2 = "NOK";
                }
                else {if(csekk5.isSelected()) { ict2 = "N/A";}}
                
                if(ict_csekk5.isSelected()) {
                    ict3 = "OK";
                }
                else if(ict_csekk6.isSelected()) {
                    ict3 = "NOK";
                }
                else {if(csekk6.isSelected()) { ict3 = "N/A";}}
                
                if(fct_csekk1.isSelected()) {
                    fct1 = "OK";
                }                
                else {if(csekk7.isSelected()) { fct1 = "N/A";}}
                
                if(fct_csekk3.isSelected()) {
                    fct2 = "OK";
                }
                else if(fct_csekk4.isSelected()) {
                    fct2 = "NOK";
                }
                else {if(csekk8.isSelected()) { fct2 = "N/A";}}
                
                if(fct_csekk5.isSelected()) {
                    fct3 = "OK";
                }
                else if(fct_csekk6.isSelected()) {
                    fct3 = "NOK";
                }
                else {if(csekk9.isSelected()) { fct3 = "N/A";}}
                
                if(meres_csekk1.isSelected()) {
                    meres1 = "OK";
                }                
                else {if(csekk10.isSelected()) { meres1 = "N/A";}}
                
                if(meres_csekk3.isSelected()) {
                    meres2 = "OK";
                }
                else if(meres_csekk4.isSelected()) {
                    meres2 = "NOK";
                }
                else {if(csekk11.isSelected()) { meres2 = "N/A";}}
                
                if(meres_csekk5.isSelected()) {
                    meres3 = "OK";
                }
                else if(meres_csekk6.isSelected()) {
                    meres3 = "NOK";
                }
                else {if(csekk12.isSelected()) { meres3 = "N/A";}}
                
                if(rontgen_csekk1.isSelected()) {
                    rontgen1 = "OK";
                }             
                else {if(csekk13.isSelected()) { rontgen1 = "N/A";}}
                
                if(rontgen_csekk3.isSelected()) {
                    rontgen2 = "OK";
                }
                else if(rontgen_csekk4.isSelected()) {
                    rontgen2 = "NOK";
                }
                else {if(csekk14.isSelected()) { rontgen2 = "N/A";}}
                
                if(rontgen_csekk5.isSelected()) {
                    rontgen3 = "OK";
                }
                else if(rontgen_csekk6.isSelected()) {
                    rontgen3 = "NOK";
                }
                else {if(csekk15.isSelected()) { rontgen3 = "N/A";}}
                
                if(egyeb_csekk1.isSelected()) {
                    egyeb1 = "OK";
                }               
                else {if(csekk16.isSelected()) { egyeb1 = "N/A";}}
                
                if(egyeb_csekk3.isSelected()) {
                    egyeb2 = "OK";
                }
                else if(egyeb_csekk4.isSelected()) {
                    egyeb2 = "NOK";
                }
                else {if(csekk17.isSelected()) { egyeb2 = "N/A";}}
                
                if(egyeb_csekk5.isSelected()) {
                    egyeb3 = "OK";
                }
                else if(egyeb_csekk6.isSelected()) {
                    egyeb3 = "NOK";
                }
                else {if(csekk18.isSelected()) { egyeb3 = "N/A";}}
                
                if(beszallito.isSelected()) {
                    hibaeredet = "Beszállító";
                }
                else if(veas.isSelected()) {
                    hibaeredet = "VEAS";
                }
                else if(vevo.isSelected()) {
                    hibaeredet = "Vevő";
                }
                else if(nff.isSelected()) {
                    hibaeredet = "NFF";
                }
                else {}
                if(kiszallithato_OK.isSelected()) {
                    kiszallithato = "OK";
                }
                else if(kiszallithato_NOK.isSelected()) {
                    kiszallithato = "NOK";
                }
                else {}                
                
                String sql = "update qualitydb.Retour_szeriaszamok set Vizualis_ell = '"+ vizualis1 +"', Vizualis_jav_elott ='"+ vizualis2 +"', Vizualis_jav_utan = '"+ vizualis3 +"', ICT_ell = '"+ ict1 +"',"
                        + " ICT_jav_elott ='"+ ict2 +"', ICT_jav_utan = '"+ ict3 +"',FCT_ell = '"+ fct1 +"', FCT_jav_elott ='"+ fct2 +"', FCT_jav_utan = '"+ fct3 +"', "
                        + "Meres_ell = '"+ meres1 +"', Meres_jav_elott ='"+ meres2 +"', Meres_jav_utan = '"+ meres3 +"',Rontgen_ell = '"+ rontgen1 +"', Rontgen_jav_elott ='"+ rontgen2 +"', Rontgen_jav_utan = '"+ rontgen3 +"',"
                        + "Egyeb_ell = '"+ egyeb1 +"', Egyeb_jav_elott ='"+ egyeb2 +"', Egyeb_jav_utan = '" + egyeb3 +"',Hibakod = '"+ String.valueOf(hibakod_box.getSelectedItem()) +"', Hiba_eredete ='"+ hibaeredet +"',"
                        + " Hiba_leirasa = '"+ hiba_mezo.getText() +"',Javitas_leirasa = '"+ javitas_mezo.getText() +"',Hiba_ok = '"+ hibaoka_mezo.getText() +"',Intezkedes = '"+ intezkedes_mezo.getText() +"',"
                                + "Kiszallithato = '"+ kiszallithato +"' "
                        + "where ID = '"+ db_id +"'";
                stmt.executeUpdate(sql);
            }
            else
            {
                if(vizualis_csekk1.isSelected()) {
                    vizualis1 = "OK";
                }                
                else {if(csekk1.isSelected()) {vizualis1 = "N/A";}}
                
                if(vizualis_csekk3.isSelected()) {
                    vizualis2 = "OK";
                }
                else if(vizualis_csekk4.isSelected()) {
                    vizualis2 = "NOK";
                }
                else {if(csekk2.isSelected()) {vizualis2 = "N/A";}}
                
                if(vizualis_csekk5.isSelected()) {
                    vizualis3 = "OK";
                }
                else if(vizualis_csekk6.isSelected()) {
                    vizualis3 = "NOK";
                }
                else {if(csekk3.isSelected()) {vizualis3 = "N/A";}}
                
                if(ict_csekk1.isSelected()) {
                    ict1 = "OK";
                }                
                else {if(csekk4.isSelected()) { ict1 = "N/A";}}
                
                if(ict_csekk3.isSelected()) {
                    ict2 = "OK";
                }
                else if(ict_csekk4.isSelected()) {
                    ict2 = "NOK";
                }
                else {if(csekk5.isSelected()) { ict2 = "N/A";}}
                
                if(ict_csekk5.isSelected()) {
                    ict3 = "OK";
                }
                else if(ict_csekk6.isSelected()) {
                    ict3 = "NOK";
                }
                else {if(csekk6.isSelected()) { ict3 = "N/A";}}
                
                if(fct_csekk1.isSelected()) {
                    fct1 = "OK";
                }               
                else {if(csekk7.isSelected()) { fct1 = "N/A";}}
                
                if(fct_csekk3.isSelected()) {
                    fct2 = "OK";
                }
                else if(fct_csekk4.isSelected()) {
                    fct2 = "NOK";
                }
                else {if(csekk8.isSelected()) { fct2 = "N/A";}}
                
                if(fct_csekk5.isSelected()) {
                    fct3 = "OK";
                }
                else if(fct_csekk6.isSelected()) {
                    fct3 = "NOK";
                }
                else {if(csekk9.isSelected()) { fct3 = "N/A";}}
                
                if(meres_csekk1.isSelected()) {
                    meres1 = "OK";
                }               
                else {if(csekk10.isSelected()) { meres1 = "N/A";}}
                
                if(meres_csekk3.isSelected()) {
                    meres2 = "OK";
                }
                else if(meres_csekk4.isSelected()) {
                    meres2 = "NOK";
                }
                else {if(csekk11.isSelected()) { meres2 = "N/A";}}
                
                if(meres_csekk5.isSelected()) {
                    meres3 = "OK";
                }
                else if(meres_csekk6.isSelected()) {
                    meres3 = "NOK";
                }
                else {if(csekk12.isSelected()) { meres3 = "N/A";}}
                
                if(rontgen_csekk1.isSelected()) {
                    rontgen1 = "OK";
                }               
                else {if(csekk13.isSelected()) { rontgen1 = "N/A";}}
                
                if(rontgen_csekk3.isSelected()) {
                    rontgen2 = "OK";
                }
                else if(rontgen_csekk4.isSelected()) {
                    rontgen2 = "NOK";
                }
                else {if(csekk14.isSelected()) { rontgen2 = "N/A";}}
                
                if(rontgen_csekk5.isSelected()) {
                    rontgen3 = "OK";
                }
                else if(rontgen_csekk6.isSelected()) {
                    rontgen3 = "NOK";
                }
                else {if(csekk15.isSelected()) { rontgen3 = "N/A";}}
                
                if(egyeb_csekk1.isSelected()) {
                    egyeb1 = "OK";
                }                
                else {if(csekk16.isSelected()) { egyeb1 = "N/A";}}
                
                if(egyeb_csekk3.isSelected()) {
                    egyeb2 = "OK";
                }
                else if(egyeb_csekk4.isSelected()) {
                    egyeb2 = "NOK";
                }
                else {if(csekk17.isSelected()) { egyeb2 = "N/A";}}
                
                if(egyeb_csekk5.isSelected()) {
                    egyeb3 = "OK";
                }
                else if(egyeb_csekk6.isSelected()) {
                    egyeb3 = "NOK";
                }
                else {if(csekk18.isSelected()) { egyeb3 = "N/A";}}
                
                if(beszallito.isSelected()) {
                    hibaeredet = "Beszállító";
                }
                else if(veas.isSelected()) {
                    hibaeredet = "VEAS";
                }
                else if(vevo.isSelected()) {
                    hibaeredet = "Vevő";
                }
                else if(nff.isSelected()) {
                    hibaeredet = "NFF";
                }
                else {}
                if(kiszallithato_OK.isSelected()) {
                    kiszallithato = "OK";
                }
                else if(kiszallithato_NOK.isSelected()) {
                    kiszallithato = "NOK";
                }
                else {}
             
                String sql = "update qualitydb.Retour_szeriaszamok set Vizualis_ell = '"+ vizualis1 +"', Vizualis_jav_elott ='"+ vizualis2 +"', Vizualis_jav_utan = '"+ vizualis3 +"', ICT_ell = '"+ ict1 +"',"
                        + " ICT_jav_elott ='"+ ict2 +"', ICT_jav_utan = '"+ ict3 +"',FCT_ell = '"+ fct1 +"', FCT_jav_elott ='"+ fct2 +"', FCT_jav_utan = '"+ fct3 +"', "
                        + "Meres_ell = '"+ meres1 +"', Meres_jav_elott ='"+ meres2 +"', Meres_jav_utan = '"+ meres3 +"',Rontgen_ell = '"+ rontgen1 +"', Rontgen_jav_elott ='"+ rontgen2 +"', Rontgen_jav_utan = '"+ rontgen3 +"',"
                        + "Egyeb_ell = '"+ egyeb1 +"', Egyeb_jav_elott ='"+ egyeb2 +"', Egyeb_jav_utan = '" + egyeb3 +"',Hibakod = '"+ String.valueOf(hibakod_box.getSelectedItem()) +"', Hiba_eredete ='"+ hibaeredet +"',"
                        + " Hiba_leirasa = '"+ hiba_mezo.getText() +"',Javitas_leirasa = '"+ javitas_mezo.getText() +"',Hiba_ok = '"+ hibaoka_mezo.getText() +"',Intezkedes = '"+ intezkedes_mezo.getText() +"',"
                        + "Kiszallithato = '"+ kiszallithato +"' "
                        + "where Retour_ID = '"+ retourid_mezo.getText() +"'";
                stmt.executeUpdate(sql);
            }
            for(int szamlalo = 0; szamlalo < kephelye.size(); szamlalo++)
            {
               iro_kep(szeriaszam_mezo.getText(), kephelye.get(szamlalo), kepneve.get(szamlalo));          
            }
            
            vizualis_csekk1.setSelected(false);       
            vizualis_csekk3.setSelected(false);        
            vizualis_csekk4.setSelected(false);        
            vizualis_csekk5.setSelected(false);        
            vizualis_csekk6.setSelected(false);       
            ict_csekk1.setSelected(false);               
            ict_csekk3.setSelected(false);        
            ict_csekk4.setSelected(false);        
            ict_csekk5.setSelected(false);       
            ict_csekk6.setSelected(false);       
            fct_csekk1.setSelected(false);              
            fct_csekk3.setSelected(false);      
            fct_csekk4.setSelected(false);        
            fct_csekk5.setSelected(false);      
            fct_csekk6.setSelected(false);       
            meres_csekk1.setSelected(false);              
            meres_csekk3.setSelected(false);        
            meres_csekk4.setSelected(false);       
            meres_csekk5.setSelected(false);        
            meres_csekk6.setSelected(false);       
            rontgen_csekk1.setSelected(false);                
            rontgen_csekk3.setSelected(false);        
            rontgen_csekk4.setSelected(false);       
            rontgen_csekk5.setSelected(false);      
            rontgen_csekk6.setSelected(false);       
            egyeb_csekk1.setSelected(false);               
            egyeb_csekk3.setSelected(false);        
            egyeb_csekk4.setSelected(false);        
            egyeb_csekk5.setSelected(false);        
            egyeb_csekk6.setSelected(false);
            csekk1.setSelected(false);
            csekk2.setSelected(false); 
            csekk3.setSelected(false); 
            csekk4.setSelected(false); 
            csekk5.setSelected(false); 
            csekk6.setSelected(false); 
            csekk7.setSelected(false); 
            csekk8.setSelected(false); 
            csekk9.setSelected(false); 
            csekk10.setSelected(false); 
            csekk11.setSelected(false); 
            csekk12.setSelected(false); 
            csekk13.setSelected(false); 
            csekk14.setSelected(false); 
            csekk15.setSelected(false); 
            csekk16.setSelected(false); 
            csekk17.setSelected(false); 
            csekk18.setSelected(false); 
            hibakod_box.setSelectedIndex(1);        
            beszallito.setSelected(false);        
            veas.setSelected(false);        
            vevo.setSelected(false);
            nff.setSelected(false);
            szeriaszam_mezo.setText("");
            hiba_mezo.setText("");
            javitas_mezo.setText("");
            hibaoka_mezo.setText("");
            intezkedes_mezo.setText("");
            stmt.close();
            conn.close();                  
            }                    
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                     //kivétel esetén kiírja a hibaüzenetet
            }
            Foablak.frame.setCursor(null);
         }
    }
    
    class Kephozzadasa implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                JFileChooser mentes_helye = new JFileChooser();
                //mentes_helye.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                mentes_helye.setCurrentDirectory(new java.io.File(System.getProperty("user.home") + "\\Desktop\\"));
                mentes_helye.setMultiSelectionEnabled(true);
                mentes_helye.showOpenDialog(mentes_helye);
                File[] fajl = mentes_helye.getSelectedFiles();
                for(int szamlalo = 0; szamlalo < fajl.length;szamlalo++)
                {
                    kephelye.add(fajl[szamlalo].getAbsolutePath());
                    kepneve.add(fajl[szamlalo].getName()); 
                }
                
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
    }
    
    class Kepmentes implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {               
                vevoi_kepmentes(szeriaszam_mezo.getText());
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
    }
    
    void iro_kep(String szeriaszam, String kephelye, String kepneve)
    {   
        Connection conn = null;
        PreparedStatement stmt = null;
        Statement stmt2 = null;
        try 
        {
           try 
           {
              Class.forName("com.mysql.cj.jdbc.Driver");                                //Driver meghívása
           } 
           catch (Exception e) 
           {
              System.out.println(e);
              String hibauzenet2 = e.toString();
              JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
           }
           
        conn = (Connection) DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");                           //kapcsolat létrehozása
        stmt2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);                                                                                                  //csatlakozás
        String sql = "Select Retour_ID from qualitydb.Retour_szeriaszamok where VEAS_ID = '"+ szeriaszam +"' or Vevoi_ID = '"+ szeriaszam +"'" ;
        stmt2.execute(sql);
        ResultSet rs = stmt2.getResultSet();
        String id = "";
        if(rs.next())
        {
            id = rs.getString(1);
        }
        
        File image = new File(kephelye);
        FileInputStream fis = new FileInputStream (image);
        sql = "INSERT INTO qualitydb.Retour_kepek(Szeriaszam, Kepneve,Kep, Retour_ID) VALUES(?,?,?,?)";
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, szeriaszam);
        stmt.setString(2, kepneve);
        stmt.setBinaryStream (3, fis, (int) image.length());
        stmt.setString(4, id);
        stmt.executeUpdate();                                                                                                                 //sql utasítás végrehajtása
        } 
        catch (SQLException e1)                                                     //kivétel esetén történik
        {
           e1.printStackTrace();
           String hibauzenet = e1.toString();
           Email hibakuldes = new Email();
           hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
           JOptionPane.showMessageDialog(null, hibauzenet + "\n \n A Mentés sikertelen!!", "Hiba üzenet", 2);
        } 
        catch (Exception e) 
        {
           e.printStackTrace();
           String hibauzenet = e.toString();
           Email hibakuldes = new Email();
           hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
           JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
        } 
        finally                                                                     //finally rész mindenképpen lefut, hogy hiba esetén is lezárja a kacsolatot
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
    
    public void vevoi_kepmentes(String szeriaszam)
    {   
        Connection con = null;
        PreparedStatement ps = null;
        FileOutputStream fs=null;
        try 
        {
           try 
           {
              Class.forName("com.mysql.cj.jdbc.Driver");                                //Driver meghívása
           } 
           catch (Exception e) 
           {
              System.out.println(e);
              String hibauzenet2 = e.toString();
              JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
           }
           
        con = (Connection) DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");                           //kapcsolat létrehozása
        ps= con.prepareStatement("SELECT Kep, Kepneve FROM qualitydb.Retour_kepek where Szeriaszam = '"+ szeriaszam_mezo.getText() +"'");        //Datum = '"+ datum +"' and Cikkszam = '"+ cikkszam +"'"
        ResultSet rset = ps.executeQuery();         
        byte b[];
        Blob blob;
        int szam = 0;
        while(rset.next())
        {        
            File f = new File(System.getProperty("user.home") + "\\Desktop\\"+ rset.getString(2));
            fs = new FileOutputStream(f);
            blob = rset.getBlob("Kep");
            b = blob.getBytes(1, (int)blob.length());
            fs.write(b);
            fs.close();
            szam++;
        }
        if(szam > 0)
        {
            JOptionPane.showMessageDialog(null, "Kép/ek mentve az asztalra", "Info", 1);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Nincsen csatolt kép", "Info", 1);
        }
        } 
        catch (SQLException e1)                                                     //kivétel esetén történik
        {
           e1.printStackTrace();
           String hibauzenet2 = e1.toString();
           Email hibakuldes = new Email();
           hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet2);
           JOptionPane.showMessageDialog(null, hibauzenet2 + "\n \n A Mentés sikertelen!!", "Hiba üzenet", 2);
        } 
        catch (Exception e) 
        {
           e.printStackTrace();
           String hibauzenet2 = e.toString();
           Email hibakuldes = new Email();
           hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet2);
           JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
        } 
        finally                                                                     //finally rész mindenképpen lefut, hogy hiba esetén is lezárja a kacsolatot
        {
           try 
           {
              if (ps != null)
                 con.close();
           } 
           catch (SQLException se) {}
           try 
           {
              if (con != null)
                 con.close();
           } 
           catch (SQLException se) 
           {
              se.printStackTrace();
           }  
        }
    }
    
    class Tortenet implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                SQA_SQL csekkol = new SQA_SQL();
                String sql = "select * from qualitydb.Retour_szeriaszamok where Veas_ID = '"+ szeriaszam_mezo.getText() +"' or vevoi_id = '"+ szeriaszam_mezo.getText() +"'";
                csekkol.minden_excel(sql, "Szériaszám történet.xlsx");
                Foablak.frame.setCursor(null);
                JOptionPane.showMessageDialog(null, "Mentve az asztalra Szériaszám történet.xlsx néven!", "Info", 1);
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
    }
}
