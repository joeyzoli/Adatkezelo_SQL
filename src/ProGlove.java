import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
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
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTable;
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
    private Utolso_sor utolso;
    private DefaultTableModel modell;
    private JScrollPane scrollPane2;

    /**
     * Create the panel.
     */
    public ProGlove() 
    {
        this.setPreferredSize(new Dimension(1100, 700));
        setLayout(null);
        combobox_tomb = new ComboBox();
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
        
        termek = new JComboBox<String>(combobox_tomb.getCombobox(ComboBox.proglove));               //combobox_tomb.getCombobox2(ComboBox.proglove)
        termek.setBounds(309, 131, 153, 22);
        termek.addActionListener(new Elem_valaszto());
        add(termek);
        
        JLabel lblNewLabel_4 = new JLabel("Ellenörzésre váró");
        lblNewLabel_4.setBounds(148, 164, 86, 14);
        add(lblNewLabel_4);
        
        ell_varo = new JTextField();
        ell_varo.setBounds(148, 189, 46, 20);
        ell_varo.setText("0");
        ell_varo.getDocument().addDocumentListener(new Enter());
        add(ell_varo);
        ell_varo.setColumns(10);
        
        JLabel lblNewLabel_5 = new JLabel("Szükséges mintavételi db");
        lblNewLabel_5.setBounds(309, 164, 113, 14);
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
        hiba_arany.setBounds(222, 338, 29, 20);
        hiba_arany.setEditable(false);
        add(hiba_arany);
        hiba_arany.setColumns(10);
        
        JLabel lblNewLabel_10 = new JLabel("Hibás db:");
        lblNewLabel_10.setBounds(309, 341, 46, 14);
        add(lblNewLabel_10);
        
        hibas_db = new JTextField();
        hibas_db.setBounds(365, 338, 46, 20);
        hibas_db.setEditable(false);
        add(hibas_db);
        hibas_db.setColumns(10);
        
        JLabel lblNewLabel_11 = new JLabel("Hibás alaktrész");
        lblNewLabel_11.setBounds(148, 366, 86, 14);
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
        add(folyamat);
        
        hibakod = new JComboBox<String>(combobox_tomb.getCombobox(ComboBox.hibakodok));              //combobox_tomb.getCombobox(ComboBox.hibakodok)
        hibakod.setBounds(244, 412, 167, 22);
        add(hibakod);
        
        JLabel lblNewLabel_14 = new JLabel("Megjegyzés");
        lblNewLabel_14.setBounds(148, 441, 86, 14);
        add(lblNewLabel_14);
        
        megjegyzes = new JTextField();
        megjegyzes.setBounds(148, 466, 263, 20);
        add(megjegyzes);
        megjegyzes.setColumns(10);
        
        JButton torles = new JButton("Törlés");
        torles.setBounds(148, 604, 64, 23);
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
        info.setBounds(918, 690, 89, 23);
        info.addActionListener(new Egyeb_info());
        add(info);
        
        kepkeret = new JLabel("");
        kepkeret.setBounds(527, 190, 550, 475);
        
        add(kepkeret);
        setBackground(Foablak.hatter_szine);
        
        JButton hozzaad = new JButton("Hozzáad");
        hozzaad.setBounds(309, 509, 89, 23);
        hozzaad.addActionListener(new Hozzaad());
        add(hozzaad);
        
        JLabel lblNewLabel_17 = new JLabel("Jó ");
        lblNewLabel_17.setBounds(148, 547, 46, 14);
        add(lblNewLabel_17);
        
        jo_mezo = new JTextField();
        jo_mezo.setBounds(222, 544, 29, 20);
        add(jo_mezo);
        jo_mezo.setColumns(10);
        
        JButton hozzaad2 = new JButton("Hozzáad");
        hozzaad2.setBounds(309, 543, 89, 23);
        add(hozzaad2);
        
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
    
    class Enter implements DocumentListener                                                                                                 //billentyűzet figyelő eseménykezelő
    {         
        @Override
        public void insertUpdate(DocumentEvent e) 
        {
            // TODO Auto-generated method stub
            try
            {
                szamolo();
            }
            catch(Exception e1)
            {
                szamolo();
            }
        }
        @Override
        public void removeUpdate(DocumentEvent e) 
        {
            // TODO Auto-generated method stub
            try
            {
                szamolo();
            }
            catch(Exception e1)
            {
                szamolo();
            }
        }
        @Override
        public void changedUpdate(DocumentEvent e) 
        {
            // TODO Auto-generated method stub
            try
            {
                szamolo();
            }
            catch(Exception e1)
            {
                szamolo();
            }
        }    
    }
    
    public void szamolo()
    {
        String[] koztes = String.valueOf(termek.getSelectedItem()).split(",");
        int ellenorizendo_menny = 0;

            ellenorizendo_menny = Integer.parseInt(ell_varo.getText());                       
            Workbook excel = new Workbook();
            excel.loadFromFile(infohelye);
            Worksheet sheet = excel.getWorksheets().get(0);
            dataTable = sheet.exportDataTable();
            if(String.valueOf(ell_helye.getSelectedItem()).contains("100% ellenőrzés"))
            {
               for (int szamlalo = 0; szamlalo < dataTable.getRows().size(); szamlalo++) 
               {
                   if(koztes[0].contains(dataTable.getRows().get(szamlalo).getString(0)))
                   {
                       ellenorizendo.setText(String.valueOf((ellenorizendo_menny/100)*Integer.parseInt(dataTable.getRows().get(szamlalo).getString(3))));
                   }
                     
               }
            }
            if(String.valueOf(ell_helye.getSelectedItem()).contains("KKS Végátvétel"))
            {
                for (int szamlalo = 0; szamlalo < dataTable.getRows().size(); szamlalo++) 
                {
                    if(koztes[0].contains(dataTable.getRows().get(szamlalo).getString(0)))
                    {
                        ellenorizendo.setText(String.valueOf((ellenorizendo_menny/100)*Integer.parseInt(dataTable.getRows().get(szamlalo).getString(4))));
                    }
                      
                }    
            }
            System.out.print(50/20);
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
            catch (IOException e1) 
            {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(null, "Nincs plusz infó!", "Hiba üzenet", 2);
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
                utolso = new Utolso_sor();
                int szam = Integer.parseInt(utolso.utolso("qualitydb.Gyartasi_adatok"));
                ir.iro_gyartas(szam+1, String.valueOf(termek.getSelectedItem()), idopont.getText(), "De", String.valueOf(nev.getSelectedItem()), String.valueOf(ell_helye.getSelectedItem()), 
                        Integer.parseInt(ell_varo.getText()), 0, megjegyzes.getText(), String.valueOf(hibakod.getSelectedItem()), hibas_alkatresz.getText(), 0, "-" );
            } 
            catch (Exception e1) 
            {
                Db_iro ir = new Db_iro();
                utolso = new Utolso_sor();
                int szam = Integer.parseInt(utolso.utolso("qualitydb.Gyartasi_adatok"));
                ir.iro_gyartas(szam+1, String.valueOf(termek.getSelectedItem()), idopont.getText(), "De", String.valueOf(nev.getSelectedItem()), String.valueOf(ell_helye.getSelectedItem()), 
                        Integer.parseInt(ell_varo.getText()), 0, megjegyzes.getText(), String.valueOf(hibakod.getSelectedItem()), hibas_alkatresz.getText(), 0, "-" );
                e1.printStackTrace();
                JOptionPane.showMessageDialog(null, "Hiba!", "Hiba üzenet", 2);
            }
         }
    }
    
    class Hozzaad implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                String[] koztes = String.valueOf(termek.getSelectedItem()).split(" - ");
                String[] koztes2 = String.valueOf(hibakod.getSelectedItem()).split(" - ");
                modell.addRow(new Object[]{koztes[0], hibas_alkatresz.getText(), koztes2[0], hiba_mezo.getText()});
                table.setModel(modell);
            } 
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                JOptionPane.showMessageDialog(null, "Hiba!", "Hiba üzenet", 2);
            }
         }
    }
    
    class Torles implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                modell.removeRow(0);
                table.setModel(modell);
                table.removeRowSelectionInterval(0, table.getRowCount()-1);;
            } 
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                JOptionPane.showMessageDialog(null, "Hiba!", "Hiba üzenet", 2);
            }
         }
    }
}
