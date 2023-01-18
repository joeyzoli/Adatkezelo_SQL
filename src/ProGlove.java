import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.spire.data.table.DataTable;
import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;

import javax.swing.JComboBox;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class ProGlove extends JPanel 
{
    private JTextField idopont;
    private JTextField ell_varo;
    private JTextField ellenorizendo;
    private JTextField ellenorzott_db;
    private JTable table;
    private JTextField hiba_arany;
    private JTextField hibas_db;
    private JTextField hibas_alkatresz;
    private JTextField megjegyzes;
    private JTextField hiba_mezo;
    private ComboBox combobox_tomb;
    private JComboBox<String> nev;
    private JComboBox<String> ell_helye;
    private JComboBox<String> termek;
    private JComboBox<String> folyamat;
    private JComboBox<String> hibakod;
    private final String infohelye = "\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\Proglove_info.xlsx";
    private JLabel kepkeret;
    private JTextArea textArea;
    private DataTable dataTable;
    static JTable table_1;
    static JScrollPane scrollPane;
    private JTextField jo_mezo;
    private DefaultTableModel modell;
    private JScrollPane scrollPane2;
    private ArrayList<String[]> kiirando;
    private SimpleDateFormat rogzites;
    private Timestamp timestamp;
    private ArrayList<String> kivalasztott;

    /**
     * Create the panel.
     */
    public ProGlove() 
    {
        this.setPreferredSize(new Dimension(1100, 700));
        setLayout(null);
        combobox_tomb = new ComboBox();
        kivalasztott = new ArrayList<String>();
        JLabel lblNewLabel = new JLabel("Időpont");
        lblNewLabel.setBounds(148, 50, 46, 14);
        add(lblNewLabel);
        
        idopont = new JTextField();
        idopont.setBounds(148, 75, 113, 20);
        ido();
        add(idopont);
        idopont.setColumns(10);
      
        JLabel lblNewLabel_1 = new JLabel("Név");
        lblNewLabel_1.setBounds(309, 50, 46, 14);
        add(lblNewLabel_1);
        
        nev = new JComboBox<String>(combobox_tomb.getCombobox2(ComboBox.ellenorok));              //combobox_tomb.getCombobox2(ComboBox.ellenorok)
        nev.setBounds(309, 74, 153, 22);
        add(nev);
        
        JLabel lblNewLabel_2 = new JLabel("Ellenőrzés helye");
        lblNewLabel_2.setBounds(148, 106, 86, 14);
        add(lblNewLabel_2);
        
        String[] folyamatok = {"100% ellenőrzés", "KKS Végátvétel"};
        ell_helye = new JComboBox<String>(folyamatok);            //folyamatok
        ell_helye.setBounds(148, 131, 113, 22);
        add(ell_helye);
        
        JLabel lblNewLabel_3 = new JLabel("Termék");
        lblNewLabel_3.setBounds(309, 107, 46, 14);
        add(lblNewLabel_3);
        
        termek = new JComboBox<String>(combobox_tomb.getCombobox(ComboBox.proglove));               //combobox_tomb.getCombobox(ComboBox.proglove)
        termek.setBounds(309, 131, 153, 22);
        termek.addActionListener(new Elem_valaszto());
        add(termek);
        
        JLabel lblNewLabel_4 = new JLabel("Ellenörzésre váró");
        lblNewLabel_4.setBounds(148, 164, 103, 14);
        add(lblNewLabel_4);
        
        ell_varo = new JTextField();
        ell_varo.setBounds(148, 189, 46, 20);
        ell_varo.addKeyListener(new Enter());
        add(ell_varo);
        ell_varo.setColumns(10);
        
        JLabel lblNewLabel_5 = new JLabel("Szükséges mintavételi db");
        lblNewLabel_5.setBounds(309, 164, 153, 14);
        add(lblNewLabel_5);
        
        ellenorizendo = new JTextField();
        ellenorizendo.setBounds(309, 189, 46, 20);
        ellenorizendo.setEditable(false);
        add(ellenorizendo);
        ellenorizendo.setColumns(10);
        
        JLabel lblNewLabel_6 = new JLabel("ProGlove folyamat ellenőrzés és végátvétel");
        lblNewLabel_6.setFont(new Font("Arial", Font.BOLD, 13));
        lblNewLabel_6.setBounds(444, 11, 289, 14);
        add(lblNewLabel_6);
        
        JLabel lblNewLabel_7 = new JLabel("Átvételi adatok");
        lblNewLabel_7.setBounds(148, 220, 113, 14);
        add(lblNewLabel_7);
        
        JLabel lblNewLabel_8 = new JLabel("Sum ellenörzött");
        lblNewLabel_8.setBounds(309, 220, 64, 14);
        add(lblNewLabel_8);
        
        ellenorzott_db = new JTextField();
        ellenorzott_db.setBounds(383, 217, 39, 20);
        ellenorzott_db.setEditable(false);
        add(ellenorzott_db);
        ellenorzott_db.setColumns(10);
        
        table = new JTable();
        table.setBounds(148, 245, 274, 85);
        
        modell = new DefaultTableModel();
        modell.setColumnIdentifiers(new Object[]{"Termék", "Pozíció", "Hibakód", "db"});       
        //modell.addRow(new Object[]{"Column 1", "Column 2", "Column 3", "3"});
        
        table.setModel(modell);
        scrollPane2 = new JScrollPane(table);
        scrollPane2.setBounds(148, 245, 274, 85);
        //add(table);
        add(scrollPane2);
        
        JLabel lblNewLabel_9 = new JLabel("Hiba arány:");
        lblNewLabel_9.setBounds(148, 341, 64, 14);
        add(lblNewLabel_9);
        
        hiba_arany = new JTextField();
        hiba_arany.setBounds(222, 338, 50, 20);
        hiba_arany.setEditable(false);
        add(hiba_arany);
        hiba_arany.setColumns(10);
        
        JLabel lblNewLabel_10 = new JLabel("Hibás db:");
        lblNewLabel_10.setBounds(309, 341, 55, 14);
        add(lblNewLabel_10);
        
        hibas_db = new JTextField();
        hibas_db.setBounds(365, 338, 46, 20);
        hibas_db.setEditable(false);
        add(hibas_db);
        hibas_db.setColumns(10);
        
        JLabel lblNewLabel_11 = new JLabel("Hibás alaktrész");
        lblNewLabel_11.setBounds(148, 366, 94, 14);
        add(lblNewLabel_11);
        
        JLabel lblNewLabel_12 = new JLabel("Folyamat");
        lblNewLabel_12.setBounds(148, 391, 64, 14);
        add(lblNewLabel_12);
        
        JLabel lblNewLabel_13 = new JLabel("Hibakód");
        lblNewLabel_13.setBounds(148, 416, 46, 14);
        add(lblNewLabel_13);
        
        hibas_alkatresz = new JTextField();
        hibas_alkatresz.setBounds(244, 363, 86, 20);
        add(hibas_alkatresz);
        hibas_alkatresz.setColumns(10);
        
        folyamat = new JComboBox<String>(combobox_tomb.getCombobox(ComboBox.folyamat));                         //combobox_tomb.getCombobox(ComboBox.folyamat)
        folyamat.setBounds(244, 387, 167, 22);
        folyamat.addActionListener(new Kivalaszt());
        add(folyamat);
        
        hibakod = new JComboBox<String>(combobox_tomb.getCombobox(ComboBox.hibakodok));                          //combobox_tomb.getCombobox(ComboBox.hibakodok)
        hibakod.setBounds(244, 412, 167, 22);
        add(hibakod);
        
        JLabel lblNewLabel_14 = new JLabel("Megjegyzés");
        lblNewLabel_14.setBounds(148, 441, 86, 14);
        add(lblNewLabel_14);
        
        megjegyzes = new JTextField();
        megjegyzes.setText("");
        megjegyzes.setBounds(148, 466, 263, 20);
        add(megjegyzes);
        megjegyzes.setColumns(10);
        
        JButton torles = new JButton("Törlés");
        torles.setBounds(148, 604, 75, 23);
        torles.addActionListener(new Torles());
        add(torles);
        
        JLabel lblNewLabel_15 = new JLabel("Hiba:");
        lblNewLabel_15.setBounds(148, 513, 29, 14);
        add(lblNewLabel_15);
        
        hiba_mezo = new JTextField();
        hiba_mezo.setBounds(222, 510, 29, 20);
        add(hiba_mezo);
        hiba_mezo.setColumns(10);
        
        JButton mentes = new JButton("Mentés");
        mentes.setBounds(322, 604, 89, 23);
        mentes.addActionListener(new Mentes());
        add(mentes);
        
        JLabel lblNewLabel_16 = new JLabel("Informásió az ellenőrzött termékről");
        lblNewLabel_16.setBounds(528, 50, 205, 14);
        add(lblNewLabel_16);
        
        table_1 = new JTable();
        table_1.setBounds(528, 75, 516, 110);
        scrollPane = new JScrollPane(table_1);
        scrollPane.setBounds(528, 75, 516, 110);
        //add(table_1);
        add(scrollPane);
        
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setBounds(528, 670, 369, 63);
        add(textArea);
        
        JButton info = new JButton("Egyéb infó");
        info.setBounds(918, 690, 126, 23);
        info.addActionListener(new Egyeb_info());
        add(info);
        
        kepkeret = new JLabel("");
        kepkeret.setBounds(527, 190, 550, 475);
        
        add(kepkeret);
        setBackground(Foablak.hatter_szine);
        
        JButton hozzaad = new JButton("Hozzáad");
        hozzaad.setBounds(309, 509, 89, 23);
        hozzaad.addActionListener(new Hozzaad_hiba());
        add(hozzaad);
        
        JLabel lblNewLabel_17 = new JLabel("Jó ");
        lblNewLabel_17.setBounds(148, 547, 46, 14);
        add(lblNewLabel_17);
        
        jo_mezo = new JTextField();
        jo_mezo.setBounds(222, 544, 29, 20);
        add(jo_mezo);
        jo_mezo.setColumns(10);
        
        JButton hozzaad2 = new JButton("Hozzáad");
        hozzaad2.addActionListener(new Hozzaad_jo());
        hozzaad2.setBounds(309, 543, 89, 23);
        add(hozzaad2);
        
        kiirando = new ArrayList<String[]>();
        
    }
    
    class Elem_valaszto implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                String valasztott = String.valueOf(termek.getSelectedItem());
                Workbook excel = new Workbook();
                excel.loadFromFile(infohelye);
                Worksheet sheet = excel.getWorksheets().get(0);
                dataTable = sheet.exportDataTable();
                
                for (int szamlalo = 0; szamlalo < dataTable.getRows().size(); szamlalo++) 
                {
                    if(valasztott.contains(dataTable.getRows().get(szamlalo).getString(0)))
                    {
                        ImageIcon icon2 = new ImageIcon(dataTable.getRows().get(szamlalo).getString(6));
                        Image icon = icon2.getImage();  
                        Image resizedImage = icon.getScaledInstance(550, 475,  java.awt.Image.SCALE_SMOOTH);
                        ImageIcon meretezett = new ImageIcon(resizedImage);
                        kepkeret.setIcon(meretezett);
                        textArea.setText(dataTable.getRows().get(szamlalo).getString(5));
                        
                    }
                }              
                SQL sql = new SQL();
                String[] koztes = String.valueOf(termek.getSelectedItem()).split(" - ");
                sql.top_hiba(koztes[0]);
                Foablak.frame.setCursor(null);    
            }
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet2 = e1.toString();
                JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
            }
         }
    }
    
    class Enter implements KeyListener                                                                                                 //billentyűzet figyelő eseménykezelő
    {
        public void keyPressed (KeyEvent e) 
        {    
            int key = e.getKeyCode();
            String[] koztes = String.valueOf(termek.getSelectedItem()).split(",");
            int ellenorizendo_menny = 0;
            if (key == KeyEvent.VK_ENTER)                                                                                               //ha az entert nyomják le akkor hívódik meg
            {
                try
                {
                    ellenorizendo_menny = Integer.parseInt(ell_varo.getText());                       
                    Workbook excel = new Workbook();
                    excel.loadFromFile(infohelye);
                    Worksheet sheet = excel.getWorksheets().get(0);
                    dataTable = sheet.exportDataTable();
                    String szorzo = "0.";
                    
                    if(String.valueOf(ell_helye.getSelectedItem()).contains("100% ellenőrzés"))
                    {
                       for (int szamlalo = 0; szamlalo < dataTable.getRows().size(); szamlalo++) 
                       {
                           if(koztes[0].contains(dataTable.getRows().get(szamlalo).getString(0)))
                           {
                               String[] koztes2 = String.valueOf(ellenorizendo_menny * Float.parseFloat(szorzo + dataTable.getRows().get(szamlalo).getString(3))).split("\\.");
                               ellenorizendo.setText(koztes2[0]);
                           }
                             
                       }
                    }
                    if(String.valueOf(ell_helye.getSelectedItem()).contains("KKS Végátvétel"))
                    {
                        for (int szamlalo = 0; szamlalo < dataTable.getRows().size(); szamlalo++) 
                        {
                            if(koztes[0].contains(dataTable.getRows().get(szamlalo).getString(0)))
                            {
                                String[] koztes2 = String.valueOf(ellenorizendo_menny * Float.parseFloat(szorzo + dataTable.getRows().get(szamlalo).getString(4))).split("\\.");
                                ellenorizendo.setText(koztes2[0]);
                            }
                              
                        }    
                    }
                }
                catch (Exception e1) 
                {
                    e1.printStackTrace();
                    String hibauzenet2 = e1.toString();
                    JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
                }
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
    
    public void ido()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        Date date = new Date();
        idopont.setText(formatter.format(date));
    }
    
    class Egyeb_info implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                String valasztott = String.valueOf(termek.getSelectedItem());
                Workbook excel = new Workbook();
                excel.loadFromFile(infohelye);
                Worksheet sheet = excel.getWorksheets().get(0);
                dataTable = sheet.exportDataTable();
                for (int szamlalo = 0; szamlalo < dataTable.getRows().size(); szamlalo++) 
                {
                    if(valasztott.contains(dataTable.getRows().get(szamlalo).getString(0)))
                    {
                        Desktop.getDesktop().open(new File(dataTable.getRows().get(szamlalo).getString(7)));                        
                    }
                }      
                Foablak.frame.setCursor(null);
            } 
            catch (Exception e1) 
            {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(null, "Nincs plusz infó!", "Hiba üzenet", 2);
                Foablak.frame.setCursor(null);
            }
         }
    }
    
    class Mentes implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                Db_iro ir = new Db_iro();
                //utolso = new Utolso_sor();
                rogzites = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                timestamp = new Timestamp(System.currentTimeMillis());
                //int szam = Integer.parseInt(utolso.utolso("qualitydb.Gyartasi_adatok"));
                for(int szamlalo = 0; szamlalo < kiirando.size(); szamlalo++)
                {
                    ir.iro_gyartas(kiirando.get(szamlalo)[0], kiirando.get(szamlalo)[1], kiirando.get(szamlalo)[2], kiirando.get(szamlalo)[3], kiirando.get(szamlalo)[4], 
                        Integer.parseInt(kiirando.get(szamlalo)[5]), Integer.parseInt(kiirando.get(szamlalo)[6]), kiirando.get(szamlalo)[7], 
                        kiirando.get(szamlalo)[8], kiirando.get(szamlalo)[9], Integer.parseInt(kiirando.get(szamlalo)[10]), "-" , rogzites.format(timestamp));
                    //szam++;
                }
                JOptionPane.showMessageDialog(null, "Mentés kész", "Info", 1);
                torlo();
            } 
            catch (Exception e1) 
            {
                
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
    
    class Hozzaad_hiba implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                String[] koztes = String.valueOf(termek.getSelectedItem()).split(" - ");
                String[] koztes2 = String.valueOf(hibakod.getSelectedItem()).split(" - ");
                modell.addRow(new Object[]{koztes[0], hibas_alkatresz.getText(), koztes2[0], hiba_mezo.getText()});
                table.setModel(modell);
                String[] hiba = {String.valueOf(termek.getSelectedItem()), idopont.getText(), "De", String.valueOf(nev.getSelectedItem()), String.valueOf(ell_helye.getSelectedItem()), "0", "0",
                                    megjegyzes.getText(), String.valueOf(hibakod.getSelectedItem()), hibas_alkatresz.getText(), hiba_mezo.getText()};
                kiirando.add(hiba);
                osszead_db();
                osszead_hibas();
                hibaszazalek();
            } 
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
    
    class Hozzaad_jo implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                String[] koztes = String.valueOf(termek.getSelectedItem()).split(" - ");
                modell.addRow(new Object[]{koztes[0], "", "0", jo_mezo.getText()});
                table.setModel(modell);
                String[] jo = {String.valueOf(termek.getSelectedItem()), idopont.getText(), "De", String.valueOf(nev.getSelectedItem()), String.valueOf(ell_helye.getSelectedItem()), ell_varo.getText(), ellenorizendo.getText(),
                        megjegyzes.getText(), "0 - nincs hiba", "", "0"};
                kiirando.add(jo);
                osszead_db();
                osszead_hibas();
                hibaszazalek();
            } 
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
    
    class Torles implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                torlo();
            } 
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
    
    class Kivalaszt implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                String keresett = String.valueOf(folyamat.getSelectedItem());
                kivalasztott.add(combobox_tomb.getCombobox(ComboBox.hibakodok)[0]);
                
                for(int szamlalo = 0; szamlalo < combobox_tomb.getCombobox(ComboBox.hibakodok).length; szamlalo++)
                {
                    if(combobox_tomb.getCombobox(ComboBox.hibakodok)[szamlalo].contains(keresett))
                    {
                        kivalasztott.add(combobox_tomb.getCombobox(ComboBox.hibakodok)[szamlalo]); 
                    }
                }
                
                String[] ujmodell = new String[kivalasztott.size()];
                for(int szamlalo = 0; szamlalo < kivalasztott.size(); szamlalo++)
                {
                    ujmodell[szamlalo] = kivalasztott.get(szamlalo);
                }
                DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(ujmodell);
                hibakod.setModel(model);
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
    
    public void osszead_db()
    {
        int osszeg = 0;
        for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
        {
            osszeg += Integer.parseInt(table.getValueAt(szamlalo, 3).toString()); 
        }
        ellenorzott_db.setText(String.valueOf(osszeg));    
        //table.getValueAt(0, 0).toString();
    }
    
    public void osszead_hibas()
    {
        int osszeg = 0;
        for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
        {
            if(table.getValueAt(szamlalo, 2).toString().equals("0"))
            {
                
            }
            else
            {
                osszeg += Integer.parseInt(table.getValueAt(szamlalo, 3).toString());
            }
        }
        hibas_db.setText(String.valueOf(osszeg));    
        //table.getValueAt(0, 0).toString();
    }
    
    public void hibaszazalek()
    {
        float szazalek = (100/Float.parseFloat(ellenorzott_db.getText()))* Float.parseFloat(hibas_db.getText());
        hiba_arany.setText(String.valueOf(szazalek+ "%"));
    }
    
    public void torlo()
    {
        Urlap_torlo torlo = new Urlap_torlo();
        torlo.urlaptorles_proglove(ell_varo, ellenorizendo, hibas_alkatresz, megjegyzes, hiba_mezo, jo_mezo);
        int rowCount = modell.getRowCount();
     
        for (int i = rowCount - 1; i > -1; i--) 
        {
          modell.removeRow(i);
        }
        table.setModel(modell);
        kiirando.clear(); 
    }
}
