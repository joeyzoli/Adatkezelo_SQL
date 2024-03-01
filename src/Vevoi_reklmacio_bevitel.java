import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JCheckBox;
import javax.swing.table.DefaultTableModel;
import javax.swing.JSeparator;
import java.awt.Font;

public class Vevoi_reklmacio_bevitel extends JPanel 
{
    static JTextField datum_mezo;
    static JTextField reklamalt_db;
    static JTextField gyartasidopontja_mezo;
    private ComboBox combobox_tomb = new ComboBox();
    static JTextField rma_mezo;
    private JTextField zarolttetel_mezo;
    static JTable zarolt_tabla;
    static JScrollPane gorgeto2;
    //private JScrollPane gorgeto;
    static JTextField hibaoka_mezo;
    private JTextField felelos1;
    private JTextField hatarido1;
    static JTable felelos_tabla;
    static DefaultTableModel modell;
    static DefaultTableModel modell2;
    static DefaultTableModel modell3;
    static DefaultTableModel modell4;
    private JTextField felelos2;
    private JTextField hatarido2;
    static JTable helyesbito_tabla;
    private JTextField felelos3;
    private JTextField hatarido3;
    static JTextArea hibaleiras_mezo;
    static JTable table;
    private int szamlalo = 1;
    private JTextArea intezkedes_elo;
    private JTextField zaroltdb_mezo;
    private JTextField talalthiba_mezo;
    static JComboBox<String> projekt_box;
    static JComboBox<String> tipus_box;
    static JComboBox<String> vagy_vagy;
    static JComboBox<String> hibaokozoja_box;
    static JCheckBox muszaki_igen;
    static JCheckBox muszaki_nem;
    static JCheckBox termeles_igen;
    static JCheckBox termeles_nem;
    private JTextArea intezkedes_det;
    private ArrayList<String> kephelye = new ArrayList<String>();
    private ArrayList<String> kepneve = new ArrayList<String>();
    private ArrayList<String> excelhelye = new ArrayList<String>();
    private ArrayList<String> excelneve = new ArrayList<String>();
    private ArrayList<String> kivalasztott;
    static JTextField hibaoka2_mezo;
    static JComboBox<String> hibaokozoja2_box;
    private String kep = "\\\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\vevoi.jpg";
    static JTextField id_mezo;
    
    /**
     * Create the panel.
     */
    public Vevoi_reklmacio_bevitel() 
    {
        this.setPreferredSize(new Dimension(1200, 999));
        Foablak.meretek.setSize(1250, 999);
        setLayout(null);
        
        kivalasztott = new ArrayList<String>();
        JLabel lblNewLabel = new JLabel("Vevői reklamációk felvitele");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel.setBounds(478, 23, 220, 14);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Reklamáció időpont");
        lblNewLabel_1.setBounds(34, 97, 118, 14);
        add(lblNewLabel_1);
        
        datum_mezo = new JTextField();
        datum_mezo.setBounds(162, 94, 81, 20);
        add(datum_mezo);
        datum_mezo.setColumns(10);
        
        JLabel lblNewLabel_3 = new JLabel("Projekt");
        lblNewLabel_3.setBounds(253, 97, 46, 14);
        add(lblNewLabel_3);
        
        projekt_box = new JComboBox<String>(combobox_tomb.getCombobox(ComboBox.vevoi_projekt));                //combobox_tomb.getCombobox(ComboBox.vevoi_projekt)
        projekt_box.setBounds(309, 93, 124, 22);
        projekt_box.addActionListener(new Kivalaszt());
        add(projekt_box);
        
        JLabel lblNewLabel_4 = new JLabel("Típus");
        lblNewLabel_4.setBounds(459, 97, 46, 14);
        add(lblNewLabel_4);
        
        tipus_box = new JComboBox<String>(combobox_tomb.getCombobox(ComboBox.vevoi_cikk));                  //combobox_tomb.getCombobox(ComboBox.vevoi_cikk)
        tipus_box.setBounds(500, 93, 319, 22);
        add(tipus_box);
        
        String[] folyamatok = {"Visszajelzés", "Reklamáció"};
        vagy_vagy = new JComboBox<String>(folyamatok);                  //folyamatok
        vagy_vagy.setBounds(875, 93, 124, 22);
        add(vagy_vagy);
        
        JLabel lblNewLabel_6 = new JLabel("Reklamált db");
        lblNewLabel_6.setBounds(34, 139, 86, 14);
        add(lblNewLabel_6);
        
        reklamalt_db = new JTextField();
        reklamalt_db.setBounds(110, 136, 40, 20);
        add(reklamalt_db);
        reklamalt_db.setColumns(10);
        
        JLabel lblNewLabel_7 = new JLabel("Hiba leírása");
        lblNewLabel_7.setBounds(225, 139, 72, 14);
        add(lblNewLabel_7);
        
        hibaleiras_mezo = new JTextArea();
        hibaleiras_mezo.setBounds(323, 134, 274, 59);
        hibaleiras_mezo.setLineWrap(true);
        hibaleiras_mezo.setWrapStyleWord(true);
        add(hibaleiras_mezo);
        
        JLabel lblNewLabel_8 = new JLabel("Gyártás időpontja");
        lblNewLabel_8.setBounds(634, 139, 107, 14);
        add(lblNewLabel_8);
        
        gyartasidopontja_mezo = new JTextField();
        gyartasidopontja_mezo.setBounds(751, 136, 86, 20);
        add(gyartasidopontja_mezo);
        gyartasidopontja_mezo.setColumns(10);
        
        JLabel lblNewLabel_9 = new JLabel("Kiadott RMA");
        lblNewLabel_9.setBounds(888, 139, 72, 14);
        add(lblNewLabel_9);
        
        rma_mezo = new JTextField();
        rma_mezo.setBounds(970, 136, 86, 20);
        add(rma_mezo);
        rma_mezo.setColumns(10);
        
        JLabel lblNewLabel_10 = new JLabel("Zárolt tétel");
        lblNewLabel_10.setBounds(34, 231, 72, 14);
        add(lblNewLabel_10);
        
        zarolttetel_mezo = new JTextField();
        zarolttetel_mezo.setBounds(110, 228, 181, 20);
        add(zarolttetel_mezo);
        zarolttetel_mezo.setColumns(10);
        
        JButton hozzaad_gomb = new JButton("hozzáad");
        hozzaad_gomb.setBounds(323, 227, 89, 23);
        hozzaad_gomb.addActionListener(new Hozzaad_zarolt());
        add(hozzaad_gomb);
        
        zarolt_tabla = new JTable();
        modell2 = new DefaultTableModel();
        modell2.setColumnIdentifiers(new Object[]{"Sorszám", "Tétel", "Db szám", "Talált db szám"}); 
        zarolt_tabla.setBounds(422, 228, 302, 74);
        zarolt_tabla.setModel(modell2);
        gorgeto2 = new JScrollPane(zarolt_tabla);
        gorgeto2.setBounds(422, 228, 319, 74);
        add(gorgeto2);
        
        JLabel lblNewLabel_11 = new JLabel("Műszaki dokumntáció megnézve?");
        lblNewLabel_11.setBounds(776, 228, 215, 20);
        add(lblNewLabel_11);
        
        muszaki_igen = new JCheckBox("Igen");
        muszaki_igen.setBounds(990, 227, 66, 23);
        add(muszaki_igen);
        
        muszaki_nem = new JCheckBox("Nem");
        muszaki_nem.setBounds(1058, 227, 53, 23);
        add(muszaki_nem);
        
        ButtonGroup csoport = new ButtonGroup();
        csoport.add(muszaki_igen);
        csoport.add(muszaki_nem);
        
        JLabel lblNewLabel_12 = new JLabel("Termelés értesítése");
        lblNewLabel_12.setBounds(839, 277, 121, 14);
        add(lblNewLabel_12);
        
        termeles_igen = new JCheckBox("Igen");
        termeles_igen.setBounds(990, 273, 53, 23);
        add(termeles_igen);
        
        termeles_nem = new JCheckBox("Nem");
        termeles_nem.setBounds(1058, 273, 60, 23);
        add(termeles_nem);
        
        ButtonGroup csoport2 = new ButtonGroup();
        csoport2.add(termeles_igen);
        csoport2.add(termeles_nem);
        
        JLabel lblNewLabel_13 = new JLabel("Gyökérok");
        lblNewLabel_13.setBounds(34, 473, 66, 14);
        add(lblNewLabel_13);
        
        hibaoka_mezo = new JTextField();        
        hibaoka_mezo.setBounds(92, 470, 188, 20);
        add(hibaoka_mezo);
        hibaoka_mezo.setColumns(10);
        
        JLabel lblNewLabel_14 = new JLabel("Hiba okozója");
        lblNewLabel_14.setBounds(290, 473, 72, 14);
        add(lblNewLabel_14);
        
        hibaokozoja_box = new JComboBox<String>(combobox_tomb.getCombobox2(ComboBox.vevoi_hibaok));                            //combobox_tomb.getCombobox2(ComboBox.vevoi_hibaok)
        hibaokozoja_box.setBounds(372, 469, 175, 22);
        add(hibaokozoja_box);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(34, 204, 1096, 7);
        add(separator);
        
        JLabel lblNewLabel_15 = new JLabel("Felelős");
        lblNewLabel_15.setBounds(34, 324, 46, 14);
        add(lblNewLabel_15);
        
        felelos1 = new JTextField();
        felelos1.setBounds(110, 321, 116, 20);
        add(felelos1);
        felelos1.setColumns(10);
        
        JLabel lblNewLabel_16 = new JLabel("Határidő");
        lblNewLabel_16.setBounds(253, 324, 60, 14);
        add(lblNewLabel_16);
        
        hatarido1 = new JTextField();
        hatarido1.setBounds(323, 321, 86, 20);
        add(hatarido1);
        hatarido1.setColumns(10);
        
        JButton hozzaad2_gomb = new JButton("Hozzáad");
        hozzaad2_gomb.addActionListener(new Hozzaad_felelos1());
        hozzaad2_gomb.setBounds(432, 320, 89, 23);
        add(hozzaad2_gomb);
        
        felelos_tabla = new JTable();
        modell = new DefaultTableModel();
        modell.setColumnIdentifiers(new Object[]{"Felelős", "Határidő"}); 
        
        
        felelos_tabla.setModel(modell);
        felelos_tabla.setBounds(551, 324, 309, 68);
        JScrollPane gorgeto = new JScrollPane(felelos_tabla);
        gorgeto.setBounds(551, 324, 309, 91);
        add(gorgeto);
        
        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(34, 434, 1096, 7);
        add(separator_1);
        
        JSeparator separator_2 = new JSeparator();
        separator_2.setBounds(34, 508, 1096, 7);
        add(separator_2);
        
        JLabel lblNewLabel_17 = new JLabel("Intézkedés előfordulásra");
        lblNewLabel_17.setBounds(34, 526, 155, 14);
        add(lblNewLabel_17);
        
        intezkedes_elo = new JTextArea();
        intezkedes_elo.setBounds(180, 521, 215, 59);
        intezkedes_elo.setLineWrap(true);
        intezkedes_elo.setWrapStyleWord(true);
        add(intezkedes_elo);
        
        JLabel lblNewLabel_18 = new JLabel("Felelős");
        lblNewLabel_18.setBounds(422, 526, 46, 14);
        add(lblNewLabel_18);
        
        felelos2 = new JTextField();
        felelos2.setBounds(478, 526, 137, 20);
        add(felelos2);
        felelos2.setColumns(10);
        
        JLabel lblNewLabel_19 = new JLabel("Határidő");
        lblNewLabel_19.setBounds(634, 526, 60, 14);
        add(lblNewLabel_19);
        
        hatarido2 = new JTextField();
        hatarido2.setBounds(704, 526, 86, 20);
        add(hatarido2);
        hatarido2.setColumns(10);
        
        JButton hozzaad3_gomb = new JButton("Hozzáad");
        hozzaad3_gomb.addActionListener(new Hozzaad_elofordul());
        hozzaad3_gomb.setBounds(701, 557, 89, 23);
        add(hozzaad3_gomb);
        
        helyesbito_tabla = new JTable();        
        modell3 = new DefaultTableModel();
        modell3.setColumnIdentifiers(new Object[]{"Intézkedés", "Felelős", "Határidő"}); 
        helyesbito_tabla.setModel(modell3);
        JScrollPane gorgeto3 = new JScrollPane(helyesbito_tabla);
        gorgeto3.setBounds(824, 526, 287, 105);
        add(gorgeto3);
        
        JLabel lblNewLabel_20 = new JLabel("Intézkedés detektálásra");
        lblNewLabel_20.setBounds(34, 651, 155, 14);
        add(lblNewLabel_20);
        
        intezkedes_det = new JTextArea();
        intezkedes_det.setBounds(180, 646, 215, 59);
        intezkedes_det.setLineWrap(true);
        intezkedes_det.setWrapStyleWord(true);
        add(intezkedes_det);
        
        JLabel lblNewLabel_21 = new JLabel("Felelős");
        lblNewLabel_21.setBounds(422, 651, 46, 14);
        add(lblNewLabel_21);
        
        felelos3 = new JTextField();
        felelos3.setBounds(478, 648, 137, 20);
        add(felelos3);
        felelos3.setColumns(10);
        
        JLabel lblNewLabel_22 = new JLabel("Határidő");
        lblNewLabel_22.setBounds(634, 651, 60, 14);
        add(lblNewLabel_22);
        
        hatarido3 = new JTextField();
        hatarido3.setBounds(704, 648, 86, 20);
        add(hatarido3);
        hatarido3.setColumns(10);
        
        JButton hozzaad4_gomb = new JButton("hozzáad");
        hozzaad4_gomb.addActionListener(new Hozzaad_detect());
        hozzaad4_gomb.setBounds(701, 692, 89, 23);
        add(hozzaad4_gomb);
        
        table = new JTable();
        modell4 = new DefaultTableModel();
        modell4.setColumnIdentifiers(new Object[]{"Intézkedés", "Felelős", "Határidő"});
        table.setModel(modell4);
        JScrollPane gorgeto4 = new JScrollPane(table);
        gorgeto4.setBounds(824, 651, 287, 106);
        add(gorgeto4);
        
        JButton mentes_gomb = new JButton("Mentés");
        mentes_gomb.setBounds(500, 897, 89, 23);
        mentes_gomb.addActionListener(new Mentes());
        add(mentes_gomb);
        
        setBackground(Foablak.hatter_szine);
        
        JLabel lblNewLabel_23 = new JLabel("Zárolt db");
        lblNewLabel_23.setBounds(34, 256, 66, 14);
        add(lblNewLabel_23);
        
        zaroltdb_mezo = new JTextField();
        zaroltdb_mezo.setBounds(110, 253, 40, 20);
        zaroltdb_mezo.setText("0");
        add(zaroltdb_mezo);
        zaroltdb_mezo.setColumns(10);
        
        JLabel lblNewLabel_24 = new JLabel("Válogatás során talált hiba db");
        lblNewLabel_24.setBounds(34, 277, 178, 14);
        add(lblNewLabel_24);
        
        talalthiba_mezo = new JTextField();
        talalthiba_mezo.setBounds(225, 274, 46, 20);
        talalthiba_mezo.setText("0");
        add(talalthiba_mezo);
        talalthiba_mezo.setColumns(10);
        
        JButton kephozzaadasa_gomb = new JButton("Kép hozzáadása");
        kephozzaadasa_gomb.setBounds(478, 843, 137, 23);
        kephozzaadasa_gomb.addActionListener(new Kephozzadasa());
        add(kephozzaadasa_gomb);
        
        JLabel lblNewLabel_2 = new JLabel("Hiba előfordulásra");
        lblNewLabel_2.setBounds(225, 445, 120, 14);
        add(lblNewLabel_2);
        
        JLabel lblNewLabel_5 = new JLabel("Gyökérok");
        lblNewLabel_5.setBounds(599, 473, 66, 14);
        add(lblNewLabel_5);
        
        hibaoka2_mezo = new JTextField();
        hibaoka2_mezo.setBounds(662, 470, 175, 20);
        add(hibaoka2_mezo);
        hibaoka2_mezo.setColumns(10);
        
        JLabel lblNewLabel_25 = new JLabel("Hiba oka");
        lblNewLabel_25.setBounds(839, 473, 74, 14);
        add(lblNewLabel_25);
        
        hibaokozoja2_box = new JComboBox<String>(combobox_tomb.getCombobox2(ComboBox.vevoi_hibaok2));                                         //combobox_tomb.getCombobox2(ComboBox.vevoi_hibaok2)
        hibaokozoja2_box.setBounds(916, 469, 214, 22);
        add(hibaokozoja2_box);
        
        JLabel lblNewLabel_26 = new JLabel("Hiba nem észlelésre");
        lblNewLabel_26.setBounds(813, 445, 127, 14);
        add(lblNewLabel_26);
        
        JLabel hatter = new JLabel("");
        ImageIcon img = new ImageIcon(kep);
        hatter.setBounds(18, 631, 560, 465);
        hatter.setIcon(img);
        add(hatter);
        
        JLabel lblNewLabel_27 = new JLabel("ID");
        lblNewLabel_27.setBounds(34, 58, 29, 14);
        add(lblNewLabel_27);
        
        id_mezo = new JTextField();
        id_mezo.setBounds(73, 55, 40, 20);
        id_mezo.addKeyListener(new Enter());
        id_mezo.setText("");
        add(id_mezo);
        id_mezo.setColumns(10);
        
        JButton id_keres_gomb = new JButton("Keres");
        id_keres_gomb.setBounds(123, 54, 89, 23);
        id_keres_gomb.addActionListener(new ID_vissza());
        add(id_keres_gomb);
        
        JButton excelhozzaadasa_gomb = new JButton("Excel hozzáadása");
        excelhozzaadasa_gomb.setBounds(478, 787, 137, 23);
        excelhozzaadasa_gomb.addActionListener(new Excelhozzadasa());
        add(excelhozzaadasa_gomb);

    }
    
    class Mentes implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                String muszaki;
                String termeles;
                if(muszaki_igen.isSelected())
                {
                   muszaki = "igen"; 
                }
                else
                {
                    muszaki = "nem";
                }
                
                if(termeles_igen.isSelected())
                {
                   termeles = "igen"; 
                }
                else
                {
                    termeles = "nem";
                }
                Db_iro iras = new Db_iro();
       
                if(id_mezo.getText().equals(""))
                {
                    iras.iro_vevoi_alap(datum_mezo.getText(), String.valueOf(projekt_box.getSelectedItem()), String.valueOf(tipus_box.getSelectedItem()), String.valueOf(vagy_vagy.getSelectedItem()),
                                       Integer.parseInt(reklamalt_db.getText()), hibaleiras_mezo.getText(), gyartasidopontja_mezo.getText(), rma_mezo.getText(), hibaoka_mezo.getText(), 
                                       String.valueOf(hibaokozoja_box.getSelectedItem()), hibaoka2_mezo.getText(), String.valueOf(hibaokozoja2_box.getSelectedItem()));
                        
                    for(int szamlalo = 0; szamlalo < felelos_tabla.getRowCount(); szamlalo++)
                    {
                        for(int szamlalo2 = 0; szamlalo2 < zarolt_tabla.getRowCount(); szamlalo2++)
                        {
                            iras.iro_vevoi_felelos(datum_mezo.getText(), String.valueOf(tipus_box.getSelectedItem()), zarolt_tabla.getValueAt(szamlalo2, 1).toString(), Integer.parseInt(zarolt_tabla.getValueAt(szamlalo2, 2).toString()),
                                         Integer.parseInt(zarolt_tabla.getValueAt(szamlalo2, 3).toString()), muszaki, termeles, felelos_tabla.getValueAt(szamlalo, 0).toString(), felelos_tabla.getValueAt(szamlalo, 1).toString());                            
                        }
                        iras.iro_vevoi_id(datum_mezo.getText(), String.valueOf(tipus_box.getSelectedItem()));
                    }
                    iras.iro_vevoi_id(datum_mezo.getText(), String.valueOf(tipus_box.getSelectedItem()));
                    /*if(zarolt_tabla.getRowCount() == 0)
                    {
                        iras.ujrair_alapadat(Integer.parseInt(id_mezo.getText()), datum_mezo.getText(), String.valueOf(tipus_box.getSelectedItem()), 0, hibaoka_mezo.getText(),                             
                                String.valueOf(hibaokozoja_box.getSelectedItem()), hibaoka2_mezo.getText(), String.valueOf(hibaokozoja2_box.getSelectedItem()), "", Integer.parseInt(reklamalt_db.getText()), hibaleiras_mezo.getText(),1);
                    }
                    else
                    {
                        for(int szamlalo2 = 0; szamlalo2 < zarolt_tabla.getRowCount(); szamlalo2++)
                        {
                            iras.ujrair_alapadat(Integer.parseInt(id_mezo.getText()), datum_mezo.getText(), String.valueOf(tipus_box.getSelectedItem()), Integer.parseInt(zarolt_tabla.getValueAt(szamlalo2, 3).toString()), hibaoka_mezo.getText(),                             
                                    String.valueOf(hibaokozoja_box.getSelectedItem()), hibaoka2_mezo.getText(), String.valueOf(hibaokozoja2_box.getSelectedItem()), zarolt_tabla.getValueAt(szamlalo2, 1).toString(), Integer.parseInt(reklamalt_db.getText()), hibaleiras_mezo.getText(), 2);
                        }
                    } */  
                    for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
                    {
                        iras.iro_vevoi_intezkedes(datum_mezo.getText(), String.valueOf(tipus_box.getSelectedItem()), table.getValueAt(szamlalo, 0).toString(), table.getValueAt(szamlalo, 1).toString(), table.getValueAt(szamlalo, 2).toString(),"1");
                    }
                    
                    for(int szamlalo = 0; szamlalo < helyesbito_tabla.getRowCount(); szamlalo++)
                    {
                        iras.iro_vevoi_intezkedes(datum_mezo.getText(), String.valueOf(tipus_box.getSelectedItem()), helyesbito_tabla.getValueAt(szamlalo, 0).toString(), helyesbito_tabla.getValueAt(szamlalo, 1).toString(), helyesbito_tabla.getValueAt(szamlalo, 2).toString(),"1");
                    }
                    iras.iro_vevoi_id(datum_mezo.getText(), String.valueOf(tipus_box.getSelectedItem()));
                    
                }
                else
                {                  
                    if(zarolt_tabla.getRowCount() == 0)
                    {
                        iras.ujrair_alapadat(Integer.parseInt(id_mezo.getText()), datum_mezo.getText(), String.valueOf(tipus_box.getSelectedItem()), 0, hibaoka_mezo.getText(),                             
                                String.valueOf(hibaokozoja_box.getSelectedItem()), hibaoka2_mezo.getText(), String.valueOf(hibaokozoja2_box.getSelectedItem()), "", Integer.parseInt(reklamalt_db.getText()), hibaleiras_mezo.getText(),1,rma_mezo.getText());
                        SQA_SQL kapcsolok = new SQA_SQL();
                        String sql = "insert into qualitydb.Vevoireklamacio_felelosok (Datum,Cikkszam,Muszaki_doku,Termeles,Zarolt,Zarolt_db,Talalt_db,Felelos,Hatarido) Values('"+datum_mezo.getText()+"','"+ String.valueOf(tipus_box.getSelectedItem())+"','"+ muszaki +"','"+ termeles +"','-','0','0','','2099.12.31')";
                        kapcsolok.mindenes(sql);
                    }
                    else
                    {
                        for(int szamlalo2 = 0; szamlalo2 < zarolt_tabla.getRowCount(); szamlalo2++)
                        {
                            iras.ujrair_alapadat(Integer.parseInt(id_mezo.getText()), datum_mezo.getText(), String.valueOf(tipus_box.getSelectedItem()), Integer.parseInt(zarolt_tabla.getValueAt(szamlalo2, 3).toString()), hibaoka_mezo.getText(),                             
                                    String.valueOf(hibaokozoja_box.getSelectedItem()), hibaoka2_mezo.getText(), String.valueOf(hibaokozoja2_box.getSelectedItem()), zarolt_tabla.getValueAt(szamlalo2, 1).toString(), Integer.parseInt(reklamalt_db.getText()), hibaleiras_mezo.getText(), 2,rma_mezo.getText());
                        }
                        SQA_SQL kapcsolok = new SQA_SQL();
                        String sql = "update qualitydb.Vevoireklamacio_felelosok set Muszaki_doku = '"+ muszaki +"', Termeles = '"+ termeles +"' where datum = '"+datum_mezo.getText()+"' and Cikkszam = '"+ String.valueOf(tipus_box.getSelectedItem()) +"'";
                        kapcsolok.mindenes(sql);
                    }
                    
                    for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
                    {
                        iras.iro_vevoi_intezkedes(datum_mezo.getText(), String.valueOf(tipus_box.getSelectedItem()), table.getValueAt(szamlalo, 0).toString(), table.getValueAt(szamlalo, 1).toString(), table.getValueAt(szamlalo, 2).toString(), id_mezo.getText());
                    }
                    
                    for(int szamlalo = 0; szamlalo < helyesbito_tabla.getRowCount(); szamlalo++)
                    {
                        iras.iro_vevoi_intezkedes(datum_mezo.getText(), String.valueOf(tipus_box.getSelectedItem()), helyesbito_tabla.getValueAt(szamlalo, 0).toString(), helyesbito_tabla.getValueAt(szamlalo, 1).toString(), helyesbito_tabla.getValueAt(szamlalo, 2).toString(), id_mezo.getText());
                    }
                    iras.iro_vevoi_id(datum_mezo.getText(), String.valueOf(tipus_box.getSelectedItem()));
                }
                    for(int szamlalo = 0; szamlalo < kephelye.size(); szamlalo++)
                    {
                       iras.iro_vevoi_kep(datum_mezo.getText(), String.valueOf(tipus_box.getSelectedItem()), kephelye.get(szamlalo), kepneve.get(szamlalo));          
                    }
                    for(int szamlalo = 0; szamlalo < excelhelye.size(); szamlalo++)
                    {
                       iras.iro_vevoi_excel(datum_mezo.getText(), String.valueOf(tipus_box.getSelectedItem()), excelhelye.get(szamlalo), excelneve.get(szamlalo));          
                    }
                    kephelye.clear();
                    kepneve.clear();
                    excelhelye.clear();
                    excelneve.clear();
                    
                    Urlap_torlo torles = new Urlap_torlo();
                    torles.urlaptorles_veoi(datum_mezo, reklamalt_db, hibaleiras_mezo, gyartasidopontja_mezo, rma_mezo, hibaoka_mezo, hibaoka2_mezo);
                    
                    int rowCount = modell.getRowCount();
                    int rowCount2 = modell2.getRowCount();
                    int rowCount3 = modell3.getRowCount();
                    int rowCount4 = modell4.getRowCount();
                    
                    for (int i = rowCount - 1; i > -1; i--) 
                    {
                      modell.removeRow(i);
                    }
                    for (int i = rowCount2 - 1; i > -1; i--) 
                    {
                      modell2.removeRow(i);
                    }
                    for (int i = rowCount3 - 1; i > -1; i--) 
                    {
                      modell3.removeRow(i);
                    }
                    for (int i = rowCount4 - 1; i > -1; i--) 
                    {
                      modell4.removeRow(i);
                    }
                    table.setModel(modell4);
                    felelos_tabla.setModel(modell);
                    zarolt_tabla.setModel(modell2);
                    helyesbito_tabla.setModel(modell3);
                    muszaki_igen.setSelected(false);
                    muszaki_nem.setSelected(false);
                    termeles_igen.setSelected(false);
                    termeles_nem.setSelected(false);
                
                JOptionPane.showMessageDialog(null, "Mentés sikeres!", "Info", 1);
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
    
    class Hozzaad_felelos1 implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                modell.addRow(new Object[]{felelos1.getText(), hatarido1.getText()});
                felelos_tabla.setModel(modell);
                felelos1.setText("");
                hatarido1.setText("");
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
    
    class Hozzaad_zarolt implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                modell2.addRow(new Object[]{szamlalo, zarolttetel_mezo.getText(), zaroltdb_mezo.getText(), talalthiba_mezo.getText()});
                felelos_tabla.setModel(modell);
                szamlalo++;
                zarolttetel_mezo.setText("");
                zaroltdb_mezo.setText("0");
                talalthiba_mezo.setText("0");
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
    
    class Hozzaad_elofordul implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                modell3.addRow(new Object[]{intezkedes_elo.getText(), felelos2.getText(), hatarido2.getText()});
                helyesbito_tabla.setModel(modell3);
                szamlalo++;
                intezkedes_elo.setText("");   
                felelos2.setText("");
                hatarido2.setText("");
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
    
    class Hozzaad_detect implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                modell4.addRow(new Object[]{intezkedes_det.getText(), felelos3.getText(), hatarido3.getText()});
                table.setModel(modell4);
                szamlalo++;
                intezkedes_det.setText("");   
                felelos3.setText("");
                hatarido3.setText("");
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
    
    class Kephozzadasa implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                JFileChooser mentes_helye = new JFileChooser();
                //mentes_helye.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                if(System.getProperty("user.name").equals("mile.jozsef"))
                {
                    mentes_helye.setCurrentDirectory(new java.io.File("\\\\\\172.20.22.7\\kozos\\Gyártási_minőség_követése\\Vevői reklamációk\\"));
                }
                else
                {
                    mentes_helye.setCurrentDirectory(new java.io.File(System.getProperty("user.home") + "\\Desktop\\"));
                }
                mentes_helye.setMultiSelectionEnabled(true);
                mentes_helye.showOpenDialog(mentes_helye);
                File[] fajl = mentes_helye.getSelectedFiles();
                if(fajl != null)
                {
                    for(int szamlalo = 0; szamlalo < fajl.length;szamlalo++)
                    {
                        kephelye.add(fajl[szamlalo].getAbsolutePath());
                        kepneve.add(fajl[szamlalo].getName()); 
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
    }
    
    class Excelhozzadasa implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                JFileChooser mentes_helye = new JFileChooser();
                mentes_helye.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                if(System.getProperty("user.name").equals("mile.jozsef"))
                {
                    mentes_helye.setCurrentDirectory(new java.io.File("\\\\\\172.20.22.7\\kozos\\Gyártási_minőség_követése\\Vevői reklamációk\\"));
                }
                else
                {
                    mentes_helye.setCurrentDirectory(new java.io.File(System.getProperty("user.home") + "\\Desktop\\"));
                }
                mentes_helye.showOpenDialog(mentes_helye);
                File fajl = mentes_helye.getSelectedFile();
                if(fajl != null)
                {
                    excelhelye.add(fajl.getAbsolutePath());
                    excelneve.add(fajl.getName());
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
    
    class ID_vissza implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                SQL id = new SQL();
                id.vevoi_id_bevitel(id_mezo.getText());
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
    
    class Enter implements KeyListener                                                                                                 //billentyűzet figyelő eseménykezelő, kiszámolja mennyit kell ellenőrizni
    {
        public void keyPressed (KeyEvent e) 
        {    
            try 
            {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_ENTER)                                                                                               //ha az entert nyomják le akkor hívódik meg
                {
                    SQL id = new SQL();
                    id.vevoi_id_bevitel(id_mezo.getText());
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
            try 
            {
                DefaultComboBoxModel<String> model;
                String keresett = String.valueOf(projekt_box.getSelectedItem());
                
                    for(int szamlalo = 0; szamlalo < combobox_tomb.getCombobox2(ComboBox.vevoi_cikk).length; szamlalo++)
                    {
                        if(combobox_tomb.getCombobox2(ComboBox.vevoi_cikk)[szamlalo].toLowerCase().contains(keresett.toLowerCase()))
                        {
                            kivalasztott.add(combobox_tomb.getCombobox2(ComboBox.vevoi_cikk)[szamlalo]); 
                        }
                    }
                    
                    String[] ujmodell = new String[kivalasztott.size()];
                    for(int szamlalo = 0; szamlalo < kivalasztott.size(); szamlalo++)
                    {
                        ujmodell[szamlalo] = kivalasztott.get(szamlalo);
                    }
                    model = new DefaultComboBoxModel<>(ujmodell);
                
                tipus_box.setModel(model);
                kivalasztott.clear();
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
