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
import java.io.File;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JCheckBox;
import javax.swing.table.DefaultTableModel;
import javax.swing.JSeparator;
import java.awt.Font;

public class Vevoi_reklmacio_bevitel extends JPanel 
{
    private JTextField datum_mezo;
    private JTextField reklamalt_db;
    private JTextField gyartasidopontja_mezo;
    private ComboBox combobox_tomb = new ComboBox();
    private JTextField rma_mezo;
    private JTextField zarolttetel_mezo;
    private JTable zarolt_tabla;
    //private JScrollPane gorgeto;
    private JTextField hibaoka_mezo;
    private JTextField felelos1;
    private JTextField hatarido1;
    private JTable felelos_tabla;
    private DefaultTableModel modell;
    private DefaultTableModel modell2;
    private DefaultTableModel modell3;
    private DefaultTableModel modell4;
    private JTextField felelos2;
    private JTextField hatarido2;
    private JTable helyesbito_tabla;
    private JTextField felelos3;
    private JTextField hatarido3;
    private JTextArea hibaleiras_mezo;
    private JTable table;
    private int szamlalo = 1;
    private JTextArea intezkedes_elo;
    private JTextField zaroltdb_mezo;
    private JTextField talalthiba_mezo;
    private JComboBox<String> projekt_box;
    private JComboBox<String> tipus_box;
    private JComboBox<String> vagy_vagy;
    private JComboBox<String> hibaokozoja_box;
    private JCheckBox muszaki_igen;
    private JCheckBox muszaki_nem;
    private JCheckBox termeles_igen;
    private JCheckBox termeles_nem;
    private JTextArea intezkedes_det;
    private ArrayList<String> kephelye = new ArrayList<String>();
    private ArrayList<String> kivalasztott;
    
    /**
     * Create the panel.
     */
    public Vevoi_reklmacio_bevitel() 
    {
        this.setPreferredSize(new Dimension(1159, 999));
        setLayout(null);
        
        kivalasztott = new ArrayList<String>();
        JLabel lblNewLabel = new JLabel("Vevői reklamációk felvitele");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel.setBounds(478, 23, 220, 14);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Reklamáció időpont");
        lblNewLabel_1.setBounds(34, 72, 101, 14);
        add(lblNewLabel_1);
        
        datum_mezo = new JTextField();
        datum_mezo.setBounds(145, 69, 81, 20);
        add(datum_mezo);
        datum_mezo.setColumns(10);
        
        JLabel lblNewLabel_3 = new JLabel("Projekt");
        lblNewLabel_3.setBounds(253, 72, 46, 14);
        add(lblNewLabel_3);
        
        projekt_box = new JComboBox<String>(combobox_tomb.getCombobox(ComboBox.projekt));                //combobox_tomb.getCombobox(ComboBox.projekt)
        projekt_box.setBounds(309, 68, 124, 22);
        projekt_box.addActionListener(new Kivalaszt());
        add(projekt_box);
        
        JLabel lblNewLabel_4 = new JLabel("Típus");
        lblNewLabel_4.setBounds(462, 72, 46, 14);
        add(lblNewLabel_4);
        
        tipus_box = new JComboBox<String>(combobox_tomb.getCombobox(ComboBox.vt_azon));                  //combobox_tomb.getCombobox(ComboBox.vt_azon)
        tipus_box.setBounds(506, 68, 319, 22);
        add(tipus_box);
        
        String[] folyamatok = {"Visszajelzés", "Reklamáció"};
        vagy_vagy = new JComboBox<String>(folyamatok);                  //folyamatok
        vagy_vagy.setBounds(847, 68, 124, 22);
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
        JScrollPane gorgeto2 = new JScrollPane(zarolt_tabla);
        gorgeto2.setBounds(422, 228, 319, 74);
        add(gorgeto2);
        
        JLabel lblNewLabel_11 = new JLabel("Műszaki dokumntáció");
        lblNewLabel_11.setBounds(751, 231, 131, 14);
        add(lblNewLabel_11);
        
        muszaki_igen = new JCheckBox("Igen");
        muszaki_igen.setBounds(888, 227, 66, 23);
        add(muszaki_igen);
        
        muszaki_nem = new JCheckBox("Nem");
        muszaki_nem.setBounds(970, 227, 97, 23);
        add(muszaki_nem);
        
        ButtonGroup csoport = new ButtonGroup();
        csoport.add(muszaki_igen);
        csoport.add(muszaki_nem);
        
        JLabel lblNewLabel_12 = new JLabel("Termelés értesítése");
        lblNewLabel_12.setBounds(751, 277, 116, 14);
        add(lblNewLabel_12);
        
        termeles_igen = new JCheckBox("Igen");
        termeles_igen.setBounds(888, 273, 53, 23);
        add(termeles_igen);
        
        termeles_nem = new JCheckBox("Nem");
        termeles_nem.setBounds(970, 273, 60, 23);
        add(termeles_nem);
        
        ButtonGroup csoport2 = new ButtonGroup();
        csoport2.add(termeles_igen);
        csoport2.add(termeles_nem);
        
        JLabel lblNewLabel_13 = new JLabel("Hiba oka");
        lblNewLabel_13.setBounds(34, 473, 66, 14);
        add(lblNewLabel_13);
        
        hibaoka_mezo = new JTextField();
        hibaoka_mezo.setBounds(110, 470, 181, 20);
        add(hibaoka_mezo);
        hibaoka_mezo.setColumns(10);
        
        JLabel lblNewLabel_14 = new JLabel("Hiba okozója");
        lblNewLabel_14.setBounds(323, 473, 72, 14);
        add(lblNewLabel_14);
        
        hibaokozoja_box = new JComboBox<String>(combobox_tomb.getCombobox2(ComboBox.vevoi_hibaok));                            //combobox_tomb.getCombobox2(ComboBox.vevoi_hibaok)
        hibaokozoja_box.setBounds(422, 469, 175, 22);
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
        JScrollPane gorgeto3 = new JScrollPane(helyesbito_tabla);
        modell3 = new DefaultTableModel();
        modell3.setColumnIdentifiers(new Object[]{"Intézkedés", "Felelős", "Határidő"}); 
        helyesbito_tabla.setModel(modell3);
        gorgeto3.setBounds(824, 526, 287, 105);
        add(gorgeto3);
        
        JLabel lblNewLabel_20 = new JLabel("Intézkedés detektálásra");
        lblNewLabel_20.setBounds(34, 651, 155, 14);
        add(lblNewLabel_20);
        
        intezkedes_det = new JTextArea();
        intezkedes_det.setBounds(180, 646, 215, 59);
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
        mentes_gomb.setBounds(488, 856, 89, 23);
        mentes_gomb.addActionListener(new Mentes());
        add(mentes_gomb);
        
        setBackground(Foablak.hatter_szine);
        
        JLabel lblNewLabel_23 = new JLabel("Zárolt db");
        lblNewLabel_23.setBounds(34, 256, 66, 14);
        add(lblNewLabel_23);
        
        zaroltdb_mezo = new JTextField();
        zaroltdb_mezo.setBounds(110, 253, 40, 20);
        add(zaroltdb_mezo);
        zaroltdb_mezo.setColumns(10);
        
        JLabel lblNewLabel_24 = new JLabel("Válogatás során talált hiba db");
        lblNewLabel_24.setBounds(34, 277, 155, 14);
        add(lblNewLabel_24);
        
        talalthiba_mezo = new JTextField();
        talalthiba_mezo.setBounds(199, 274, 46, 20);
        add(talalthiba_mezo);
        talalthiba_mezo.setColumns(10);
        
        JButton kephozzaadasa_gomb = new JButton("Kép hozzáadása");
        kephozzaadasa_gomb.setBounds(478, 787, 119, 23);
        kephozzaadasa_gomb.addActionListener(new Kephozzadasa());
        add(kephozzaadasa_gomb);

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
                
                iras.iro_vevoi_alap(datum_mezo.getText(), String.valueOf(projekt_box.getSelectedItem()), String.valueOf(tipus_box.getSelectedItem()), String.valueOf(vagy_vagy.getSelectedItem()),
                         Integer.parseInt(reklamalt_db.getText()), hibaleiras_mezo.getText(), gyartasidopontja_mezo.getText(), rma_mezo.getText(), hibaoka_mezo.getText(), String.valueOf(hibaokozoja_box.getSelectedItem()));
                
                for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
                {
                    iras.iro_vevoi_intezkedes(datum_mezo.getText(), String.valueOf(tipus_box.getSelectedItem()), table.getValueAt(szamlalo, 0).toString(), table.getValueAt(szamlalo, 1).toString(), table.getValueAt(szamlalo, 2).toString());
                }
                
                for(int szamlalo = 0; szamlalo < helyesbito_tabla.getRowCount(); szamlalo++)
                {
                    iras.iro_vevoi_intezkedes(datum_mezo.getText(), String.valueOf(tipus_box.getSelectedItem()), helyesbito_tabla.getValueAt(szamlalo, 0).toString(), helyesbito_tabla.getValueAt(szamlalo, 1).toString(), helyesbito_tabla.getValueAt(szamlalo, 2).toString());
                }
                
                for(int szamlalo = 0; szamlalo < felelos_tabla.getRowCount(); szamlalo++)
                {
                    for(int szamlalo2 = 0; szamlalo2 < zarolt_tabla.getRowCount(); szamlalo2++)
                    {
                        iras.iro_vevoi_felelos(datum_mezo.getText(), String.valueOf(tipus_box.getSelectedItem()), zarolt_tabla.getValueAt(szamlalo2, 1).toString(), Integer.parseInt(zarolt_tabla.getValueAt(szamlalo2, 2).toString()),
                                Integer.parseInt(zarolt_tabla.getValueAt(szamlalo2, 3).toString()), muszaki, termeles, felelos_tabla.getValueAt(szamlalo, 0).toString(), felelos_tabla.getValueAt(szamlalo, 1).toString());
                    }                 
                }
                
                for(int szamlalo = 0; szamlalo < kephelye.size(); szamlalo++)
                {
                   iras.iro_vevoi_kep(datum_mezo.getText(), String.valueOf(tipus_box.getSelectedItem()), kephelye.get(szamlalo));          
                }
                kephelye.clear();
                
                JOptionPane.showMessageDialog(null, "Mentés sikeres!", "Info", 1);
            } 
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
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
                felelos_tabla.setModel(modell);
                szamlalo++;
                intezkedes_elo.setText("");   
                felelos2.setText("");
                hatarido2.setText("");
            } 
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
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
                mentes_helye.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                mentes_helye.showOpenDialog(mentes_helye);
                File fajl = mentes_helye.getSelectedFile();
                kephelye.add(fajl.getAbsolutePath());
            } 
            catch (Exception e1) 
            {              
                
            }
         }
    }
    
    class Kivalaszt implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                String keresett = String.valueOf(projekt_box.getSelectedItem());
                //kivalasztott.add(combobox_tomb.getCombobox(ComboBox.vt_azon));
                
                for(int szamlalo = 0; szamlalo < combobox_tomb.getCombobox(ComboBox.vt_azon).length; szamlalo++)
                {
                    if(combobox_tomb.getCombobox(ComboBox.vt_azon)[szamlalo].contains(keresett))
                    {
                        kivalasztott.add(combobox_tomb.getCombobox(ComboBox.vt_azon)[szamlalo]); 
                    }
                }
                
                String[] ujmodell = new String[kivalasztott.size()];
                for(int szamlalo = 0; szamlalo < kivalasztott.size(); szamlalo++)
                {
                    ujmodell[szamlalo] = kivalasztott.get(szamlalo);
                }
                DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(ujmodell);
                tipus_box.setModel(model);
                kivalasztott.clear();
            } 
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
}
