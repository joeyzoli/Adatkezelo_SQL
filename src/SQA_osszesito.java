import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.spire.xls.ExcelVersion;
import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;

public class SQA_osszesito extends JPanel {
    private JTable table;
    private DefaultTableModel modell;
    private DefaultTableModel szukitett_modell;
    private JComboBox<String> nev_box;
    private JCheckBox csak_gomb;
    private JComboBox<String> vagy_box;
    private String beallitasok = System.getProperty("user.home") + "\\sqa_szures.txt";
    private JComboBox<String> honap_box;
    private int honap = 0;

    /**
     * Create the panel.
     */
    public SQA_osszesito() {
        setLayout(null);
        this.setPreferredSize(new Dimension(1600, 759));
        Foablak.meretek.setSize(1600, 750);
        JLabel lblNewLabel = new JLabel("SQA reklamációk");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setBackground(Color.BLUE);
        lblNewLabel.setBounds(616, 23, 180, 14);
        add(lblNewLabel);
        
        table = new JTable();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        modell = new DefaultTableModel();
        szukitett_modell = new DefaultTableModel();
        modell.setColumnIdentifiers(new Object[]{"ID", "Dátum", "Inditotta", "Űrlap jellege","Cikkszám","Projekt","Lezárás ideje","Nyitva","Hibaleírás"});
        szukitett_modell.setColumnIdentifiers(new Object[]{"ID", "Reklamáció száma", "Inditotta", "Űrlap jellege","Cikkszám","Projekt","Lezárás ideje","Nyitva","Hibaleírás"});
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setModel(modell);
        
        JScrollPane gorgeto = new JScrollPane(table);
        gorgeto.setBounds(68, 119, 1467, 356);
        add(gorgeto);
        
        JLabel lblNewLabel_1 = new JLabel("Kiválasztott reklamáció megynyitása");
        lblNewLabel_1.setBounds(431, 525, 186, 14);
        add(lblNewLabel_1);
        
        JButton betolt_gomb = new JButton("Betölt");
        betolt_gomb.addActionListener(new Visszatolt());
        betolt_gomb.setBounds(643, 521, 89, 23);
        add(betolt_gomb);
        
        JLabel lblNewLabel_2 = new JLabel("Új reklamáció felvitele");
        lblNewLabel_2.setBounds(431, 574, 186, 14);
        add(lblNewLabel_2);
        
        JButton ujreki_gomb = new JButton("Start");
        ujreki_gomb.addActionListener(new Uj_reklamacio());
        ujreki_gomb.setBounds(643, 570, 89, 23);
        add(ujreki_gomb);
        
        setBackground(Foablak.hatter_szine);
        
        JButton excel_gomb = new JButton("excel");
        excel_gomb.addActionListener(new Excel());
        excel_gomb.setBounds(643, 618, 89, 23);
        add(excel_gomb);
        
        JLabel lblNewLabel_3 = new JLabel("Excel export");
        lblNewLabel_3.setBounds(431, 622, 79, 14);
        add(lblNewLabel_3);
        
        String[] nevek = {"-","Schweighardt Róbert", "Tóth Zoltán","Horváth Balázs"};
        nev_box = new JComboBox<String> (nevek);                                             //nevek
        nev_box.addActionListener(new Szukito());
        nev_box.setBounds(183, 73, 197, 22);
        add(nev_box);
        
        csak_gomb = new JCheckBox("Csak nyitottak");
        csak_gomb.addActionListener(new Szukito());
        csak_gomb.setBounds(1160, 73, 115, 23);
        add(csak_gomb);
        
        String[] jelleg ={"-","Reklamáció","Egyéb"};
        vagy_box = new JComboBox<String>(jelleg);                                             //jelleg
        vagy_box.addActionListener(new Szukito());
        vagy_box.setBounds(448, 73, 154, 22);
        add(vagy_box);
        
        String[] honapok = {"-","Január","Február","Március","Április","Május","Június","Július","Augusztus","Szeptember","Október","November","December"};
        honap_box = new JComboBox<String>(honapok);                                     //honapok
        honap_box.addActionListener(new Szukito());
        honap_box.setBounds(696, 73, 154, 22);
        add(honap_box);
        
        JLabel lblNewLabel_4 = new JLabel("Raktári cikkek típussal");
        lblNewLabel_4.setBounds(1040, 525, 154, 14);
        add(lblNewLabel_4);
        
        JButton keres_gomb = new JButton("Keresés");
        keres_gomb.addActionListener(new IFS_kereses());
        keres_gomb.setBounds(1204, 521, 89, 23);
        add(keres_gomb);
        
        JLabel lblNewLabel_5 = new JLabel("Kíválasztott sor törlése");
        lblNewLabel_5.setBounds(1040, 574, 142, 14);
        add(lblNewLabel_5);
        
        JButton torles_gomb = new JButton("Törlés");
        torles_gomb.addActionListener(new Torles());
        torles_gomb.setBounds(1204, 570, 89, 23);
        add(torles_gomb);
        adatok();
        
    }
    
    private void adatok()
    {
        Connection conn = null;
        Statement stmt = null;               
        ResultSet rs;       
        try 
        {          
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);       
        String sql = "select id, datum, inditotta, vagy,cikkszam,projekt, lezaras_ido,\r\n"
                + "if(lezaras_ido is null, DATEDIFF(now(), Datum), DATEDIFF(Lezaras_ido, Datum)) as nyitva,\r\n"
                + "Hibaleiras\r\n"
                + "from qualitydb.SQA_reklamaciok\r\n"
                + "where 3 = 3";                                        
        stmt.execute(sql);      
        rs = stmt.getResultSet();
        while(rs.next())
        {
            if(rs.getString(7) != null)
            {
                String[] datum = rs.getString(7).split(" ");
                String[] datum2 = rs.getString(2).split(" ");
                modell.addRow(new Object[]{rs.getString(1), datum2[0], rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),datum[0],rs.getString(8),rs.getString(9)});                  
            }
            else
            {
                String[] datum2 = rs.getString(2).split(" ");
                modell.addRow(new Object[]{rs.getString(1), datum2[0], rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)});
            }                                     
        }
        TableColumnModel columnModel = table.getColumnModel();
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
        }
        //table.setModel(modell);
        File letezik = new File(System.getProperty("user.home") + "\\sqa_szures.txt");
        if(letezik.exists())
        {
            //FileReader fr = new FileReader(System.getProperty("user.home") + "\\sqa_szures.txt");
            FileInputStream fis = new FileInputStream(System.getProperty("user.home") + "\\sqa_szures.txt");
            InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
            try (BufferedReader br = new BufferedReader(isr)) 
            {
                String sor = br.readLine();
                String[] adatok = sor.split(";");
                nev_box.setSelectedItem(adatok[0]);
                vagy_box.setSelectedItem(adatok[1]);
                String igaz = "true";
                Boolean valasztas = igaz.equals(adatok[2]);
                csak_gomb.setSelected(valasztas);
                if(adatok.length > 3)
                {
                    honap_box.setSelectedItem(adatok[3]);
                }
                //valaszto();
                Foablak.frame.setCursor(null);                                                                                          //egér mutató alaphelyzetbe állítása
            }
        }
        }
        catch (Exception e1) 
        {
            String hibauzenet = e1.toString();
            Email hibakuldes = new Email();
            hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", getClass()+" "+ hibauzenet);
            JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            e1.printStackTrace();
        }
    }
    
    class Uj_reklamacio implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                                                //egér mutató változtatása munka a háttérbenre
                SQA_bevitel sqa_rek = new SQA_bevitel();
                JScrollPane ablak = new JScrollPane(sqa_rek);
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
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                   //kivétel esetén kiírja a hibaüzenetet
            }
         }
    }
    
    class Visszatolt implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                int sor = table.getSelectedRow();
                if(sor< 0)
                {
                    JOptionPane.showMessageDialog(null, "Nincs kiválasztva reklmáció!!", "Hiba üzenet", 2);                                                     // hibaüzenetet kiratása
                }
                else
                {
                    Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                                                //egér mutató változtatása munka a háttérbenre                    
                    String id = table.getValueAt(sor, 0).toString();
                    SQA_bevitel sqa_rek = new SQA_bevitel(id);
                    JScrollPane ablak = new JScrollPane(sqa_rek);
                    ablak.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    ablak.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                    Foablak.frame.setContentPane(ablak);
                    Foablak.frame.pack();
                    Foablak.frame.setCursor(null);                                                                                          //egér mutató alaphelyzetbe állítása
                }
            }
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", getClass()+" "+ hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                              //kivétel esetén kiírja a hibaüzenetet
            }
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
                String sql = "select *,if(lezaras_ido is null, DATEDIFF(now(), Datum), DATEDIFF(Lezaras_ido, Datum)) as nyitva from qualitydb.SQA_reklamaciok where 3=3";
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

    class Szukito implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                valaszto();
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
    
    
    
    private void valaszto()
    {
        try
        {
            //setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            if(honap_box.getSelectedItem().equals("Január")){              
                honap = 1;
            }
            else if(honap_box.getSelectedItem().equals("Február")){              
                honap = 2;
            }
            else if(honap_box.getSelectedItem().equals("Március")){              
                honap = 3;
            }
            else if(honap_box.getSelectedItem().equals("Április")){              
                honap = 4;
            }
            else if(honap_box.getSelectedItem().equals("Május")){              
                honap = 5;
            }
            else if(honap_box.getSelectedItem().equals("Június")){              
                honap = 6;
            }
            else if(honap_box.getSelectedItem().equals("Július")){              
                honap = 7;
            }
            else if(honap_box.getSelectedItem().equals("Augusztus")){              
                honap = 8;
            }
            else if(honap_box.getSelectedItem().equals("Szeptember")){              
                honap = 9;
            }
            else if(honap_box.getSelectedItem().equals("Október")){              
                honap = 10;
            }
            else if(honap_box.getSelectedItem().equals("November")){              
                honap = 11;
            }
            else if(honap_box.getSelectedItem().equals("December")){              
                honap = 12;
            }
            else{
                honap = 0;
            }
            
            if(String.valueOf(nev_box.getSelectedItem()).equals("-"))
            {
                if(String.valueOf(vagy_box.getSelectedItem()).equals("-"))
                {
                    if(honap == 0)
                    {
                        if(csak_gomb.isSelected())
                        {
                            szukitett_modell.setRowCount(0);
                            table.setModel(modell);
                            for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
                            {
                                if(table.getValueAt(szamlalo, 6) == null)
                                {
                                    szukitett_modell.addRow(new Object[]{table.getValueAt(szamlalo, 0),table.getValueAt(szamlalo, 1), table.getValueAt(szamlalo, 2), table.getValueAt(szamlalo, 3),
                                            table.getValueAt(szamlalo, 4),table.getValueAt(szamlalo, 5),table.getValueAt(szamlalo, 6),table.getValueAt(szamlalo, 7),table.getValueAt(szamlalo, 8)});
                                }
                            }
                            table.setModel(szukitett_modell);
                        }
                        else
                        {
                            table.setModel(modell);
                        }
                    }
                    else
                    {
                        if(csak_gomb.isSelected())
                        {
                            szukitett_modell.setRowCount(0);
                            table.setModel(modell);                            
                            for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
                            {
                                String[] datum = table.getValueAt(szamlalo, 1).toString().split("-");
                                if(table.getValueAt(szamlalo, 6) == null && Integer.valueOf(datum[1]) == honap)
                                {
                                    szukitett_modell.addRow(new Object[]{table.getValueAt(szamlalo, 0),table.getValueAt(szamlalo, 1), table.getValueAt(szamlalo, 2), table.getValueAt(szamlalo, 3),
                                            table.getValueAt(szamlalo, 4),table.getValueAt(szamlalo, 5),table.getValueAt(szamlalo, 6),table.getValueAt(szamlalo, 7),table.getValueAt(szamlalo, 8)});
                                }
                            }
                            table.setModel(szukitett_modell);
                        }
                        else
                        {
                            szukitett_modell.setRowCount(0);
                            table.setModel(modell);                            
                            for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
                            {
                                String[] datum = table.getValueAt(szamlalo, 1).toString().split("-");
                                if(Integer.valueOf(datum[1]) == honap)
                                {
                                    szukitett_modell.addRow(new Object[]{table.getValueAt(szamlalo, 0),table.getValueAt(szamlalo, 1), table.getValueAt(szamlalo, 2), table.getValueAt(szamlalo, 3),
                                            table.getValueAt(szamlalo, 4),table.getValueAt(szamlalo, 5),table.getValueAt(szamlalo, 6),table.getValueAt(szamlalo, 7),table.getValueAt(szamlalo, 8)});
                                }
                            }
                            table.setModel(szukitett_modell);
                        }
                    }
                }
                if(String.valueOf(vagy_box.getSelectedItem()).equals("Reklamáció"))
                {
                    if(honap == 0)
                    {
                        if(csak_gomb.isSelected())
                        {
                            szukitett_modell.setRowCount(0);
                            table.setModel(modell);
                            for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
                            {
                                if(table.getValueAt(szamlalo, 6) == null && table.getValueAt(szamlalo, 3).toString().equals("Reklamáció"))
                                {
                                    szukitett_modell.addRow(new Object[]{table.getValueAt(szamlalo, 0),table.getValueAt(szamlalo, 1), table.getValueAt(szamlalo, 2), table.getValueAt(szamlalo, 3),
                                            table.getValueAt(szamlalo, 4),table.getValueAt(szamlalo, 5),table.getValueAt(szamlalo, 6),table.getValueAt(szamlalo, 7),table.getValueAt(szamlalo, 8)});
                                }
                            }
                            table.setModel(szukitett_modell);
                        }
                        else
                        {
                            table.setModel(modell);
                        }
                    }
                    else
                    {
                        if(csak_gomb.isSelected())
                        {
                            szukitett_modell.setRowCount(0);
                            table.setModel(modell);                            
                            for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
                            {
                                String[] datum = table.getValueAt(szamlalo, 1).toString().split("-");
                                if(table.getValueAt(szamlalo, 6) == null && Integer.valueOf(datum[1]) == honap && table.getValueAt(szamlalo, 3).toString().equals("Reklamáció"))
                                {
                                    szukitett_modell.addRow(new Object[]{table.getValueAt(szamlalo, 0),table.getValueAt(szamlalo, 1), table.getValueAt(szamlalo, 2), table.getValueAt(szamlalo, 3),
                                            table.getValueAt(szamlalo, 4),table.getValueAt(szamlalo, 5),table.getValueAt(szamlalo, 6),table.getValueAt(szamlalo, 7),table.getValueAt(szamlalo, 8)});
                                }
                            }
                            table.setModel(szukitett_modell);
                        }
                        else
                        {
                            szukitett_modell.setRowCount(0);
                            table.setModel(modell);                            
                            for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
                            {
                                String[] datum = table.getValueAt(szamlalo, 1).toString().split("-");
                                if(Integer.valueOf(datum[1]) == honap && table.getValueAt(szamlalo, 3).toString().equals("Reklamáció"))
                                {
                                    szukitett_modell.addRow(new Object[]{table.getValueAt(szamlalo, 0),table.getValueAt(szamlalo, 1), table.getValueAt(szamlalo, 2), table.getValueAt(szamlalo, 3),
                                            table.getValueAt(szamlalo, 4),table.getValueAt(szamlalo, 5),table.getValueAt(szamlalo, 6),table.getValueAt(szamlalo, 7),table.getValueAt(szamlalo, 8)});
                                }
                            }
                            table.setModel(szukitett_modell);
                        }
                    }
                }
                if(String.valueOf(vagy_box.getSelectedItem()).equals("Egyéb"))
                {
                    if(honap == 0)
                    {
                        if(csak_gomb.isSelected())
                        {
                            szukitett_modell.setRowCount(0);
                            table.setModel(modell);
                            for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
                            {
                                if(table.getValueAt(szamlalo, 6) == null && table.getValueAt(szamlalo, 3).toString().equals("Egyéb"))
                                {
                                    szukitett_modell.addRow(new Object[]{table.getValueAt(szamlalo, 0),table.getValueAt(szamlalo, 1), table.getValueAt(szamlalo, 2), table.getValueAt(szamlalo, 3),
                                            table.getValueAt(szamlalo, 4),table.getValueAt(szamlalo, 5),table.getValueAt(szamlalo, 6),table.getValueAt(szamlalo, 7),table.getValueAt(szamlalo, 8)});
                                }
                            }
                            table.setModel(szukitett_modell);
                        }
                        else
                        {
                            table.setModel(modell);
                        }
                    }
                    else
                    {
                        if(csak_gomb.isSelected())
                        {
                            szukitett_modell.setRowCount(0);
                            table.setModel(modell);                            
                            for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
                            {
                                String[] datum = table.getValueAt(szamlalo, 1).toString().split("-");
                                if(table.getValueAt(szamlalo, 6) == null && Integer.valueOf(datum[1]) == honap && table.getValueAt(szamlalo, 3).toString().equals("Egyéb"))
                                {
                                    szukitett_modell.addRow(new Object[]{table.getValueAt(szamlalo, 0),table.getValueAt(szamlalo, 1), table.getValueAt(szamlalo, 2), table.getValueAt(szamlalo, 3),
                                            table.getValueAt(szamlalo, 4),table.getValueAt(szamlalo, 5),table.getValueAt(szamlalo, 6),table.getValueAt(szamlalo, 7),table.getValueAt(szamlalo, 8)});
                                }
                            }
                            table.setModel(szukitett_modell);
                        }
                        else
                        {
                            szukitett_modell.setRowCount(0);
                            table.setModel(modell);                            
                            for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
                            {
                                String[] datum = table.getValueAt(szamlalo, 1).toString().split("-");
                                if(Integer.valueOf(datum[1]) == honap && table.getValueAt(szamlalo, 3).toString().equals("Egyéb"))
                                {
                                    szukitett_modell.addRow(new Object[]{table.getValueAt(szamlalo, 0),table.getValueAt(szamlalo, 1), table.getValueAt(szamlalo, 2), table.getValueAt(szamlalo, 3),
                                            table.getValueAt(szamlalo, 4),table.getValueAt(szamlalo, 5),table.getValueAt(szamlalo, 6),table.getValueAt(szamlalo, 7),table.getValueAt(szamlalo, 8)});
                                }
                            }
                            table.setModel(szukitett_modell);
                        }
                    }
                }
            }
            if(String.valueOf(nev_box.getSelectedItem()).equals("Schweighardt Róbert"))
            {
                if(String.valueOf(vagy_box.getSelectedItem()).equals("-"))
                {
                    if(honap == 0)
                    {
                        if(csak_gomb.isSelected())
                        {
                            szukitett_modell.setRowCount(0);
                            table.setModel(modell);
                            for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
                            {
                                if(table.getValueAt(szamlalo, 6) == null && table.getValueAt(szamlalo, 2).toString().equals("Schweighardt Róbert"))
                                {
                                    szukitett_modell.addRow(new Object[]{table.getValueAt(szamlalo, 0),table.getValueAt(szamlalo, 1), table.getValueAt(szamlalo, 2), table.getValueAt(szamlalo, 3),
                                            table.getValueAt(szamlalo, 4),table.getValueAt(szamlalo, 5),table.getValueAt(szamlalo, 6),table.getValueAt(szamlalo, 7),table.getValueAt(szamlalo, 8)});
                                }
                            }
                            table.setModel(szukitett_modell);
                        }
                        else
                        {
                            szukitett_modell.setRowCount(0);
                            table.setModel(modell);
                            for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
                            {
                                if(table.getValueAt(szamlalo, 2).toString().equals("Schweighardt Róbert"))
                                {
                                    szukitett_modell.addRow(new Object[]{table.getValueAt(szamlalo, 0),table.getValueAt(szamlalo, 1), table.getValueAt(szamlalo, 2), table.getValueAt(szamlalo, 3),
                                            table.getValueAt(szamlalo, 4),table.getValueAt(szamlalo, 5),table.getValueAt(szamlalo, 6),table.getValueAt(szamlalo, 7),table.getValueAt(szamlalo, 8)});
                                }
                            }
                            table.setModel(szukitett_modell);
                        }
                    }
                    else
                    {
                        if(csak_gomb.isSelected())
                        {
                            szukitett_modell.setRowCount(0);
                            table.setModel(modell);                            
                            for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
                            {
                                String[] datum = table.getValueAt(szamlalo, 1).toString().split("-");
                                if(table.getValueAt(szamlalo, 6) == null && Integer.valueOf(datum[1]) == honap && table.getValueAt(szamlalo, 2).toString().equals("Schweighardt Róbert"))
                                {
                                    szukitett_modell.addRow(new Object[]{table.getValueAt(szamlalo, 0),table.getValueAt(szamlalo, 1), table.getValueAt(szamlalo, 2), table.getValueAt(szamlalo, 3),
                                            table.getValueAt(szamlalo, 4),table.getValueAt(szamlalo, 5),table.getValueAt(szamlalo, 6),table.getValueAt(szamlalo, 7),table.getValueAt(szamlalo, 8)});
                                }
                            }
                            table.setModel(szukitett_modell);
                        }
                        else
                        {
                            szukitett_modell.setRowCount(0);
                            table.setModel(modell);                            
                            for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
                            {
                                String[] datum = table.getValueAt(szamlalo, 1).toString().split("-");
                                if(Integer.valueOf(datum[1]) == honap && table.getValueAt(szamlalo, 2).toString().equals("Schweighardt Róbert"))
                                {
                                    szukitett_modell.addRow(new Object[]{table.getValueAt(szamlalo, 0),table.getValueAt(szamlalo, 1), table.getValueAt(szamlalo, 2), table.getValueAt(szamlalo, 3),
                                            table.getValueAt(szamlalo, 4),table.getValueAt(szamlalo, 5),table.getValueAt(szamlalo, 6),table.getValueAt(szamlalo, 7),table.getValueAt(szamlalo, 8)});
                                }
                            }
                            table.setModel(szukitett_modell);
                        }
                    }
                }
                if(String.valueOf(vagy_box.getSelectedItem()).equals("Reklamáció"))
                {
                    if(honap == 0)
                    {
                        if(csak_gomb.isSelected())
                        {
                            szukitett_modell.setRowCount(0);
                            table.setModel(modell);
                            for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
                            {
                                if(table.getValueAt(szamlalo, 6) == null && table.getValueAt(szamlalo, 3).toString().equals("Reklamáció") && table.getValueAt(szamlalo, 2).toString().equals("Schweighardt Róbert"))
                                {
                                    szukitett_modell.addRow(new Object[]{table.getValueAt(szamlalo, 0),table.getValueAt(szamlalo, 1), table.getValueAt(szamlalo, 2), table.getValueAt(szamlalo, 3),
                                            table.getValueAt(szamlalo, 4),table.getValueAt(szamlalo, 5),table.getValueAt(szamlalo, 6),table.getValueAt(szamlalo, 7),table.getValueAt(szamlalo, 8)});
                                }
                            }
                            table.setModel(szukitett_modell);
                        }
                        else
                        {
                            szukitett_modell.setRowCount(0);
                            table.setModel(modell);
                            for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
                            {
                                if(table.getValueAt(szamlalo, 3).toString().equals("Reklamáció") && table.getValueAt(szamlalo, 2).toString().equals("Schweighardt Róbert"))
                                {
                                    szukitett_modell.addRow(new Object[]{table.getValueAt(szamlalo, 0),table.getValueAt(szamlalo, 1), table.getValueAt(szamlalo, 2), table.getValueAt(szamlalo, 3),
                                            table.getValueAt(szamlalo, 4),table.getValueAt(szamlalo, 5),table.getValueAt(szamlalo, 6),table.getValueAt(szamlalo, 7),table.getValueAt(szamlalo, 8)});
                                }
                            }
                            table.setModel(szukitett_modell);
                        }
                    }
                    else
                    {
                        if(csak_gomb.isSelected())
                        {
                            szukitett_modell.setRowCount(0);
                            table.setModel(modell);                            
                            for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
                            {
                                String[] datum = table.getValueAt(szamlalo, 1).toString().split("-");
                                if(table.getValueAt(szamlalo, 6) == null && Integer.valueOf(datum[1]) == honap && table.getValueAt(szamlalo, 3).toString().equals("Reklamáció") && table.getValueAt(szamlalo, 2).toString().equals("Schweighardt Róbert"))
                                {
                                    szukitett_modell.addRow(new Object[]{table.getValueAt(szamlalo, 0),table.getValueAt(szamlalo, 1), table.getValueAt(szamlalo, 2), table.getValueAt(szamlalo, 3),
                                            table.getValueAt(szamlalo, 4),table.getValueAt(szamlalo, 5),table.getValueAt(szamlalo, 6),table.getValueAt(szamlalo, 7),table.getValueAt(szamlalo, 8)});
                                }
                            }
                            table.setModel(szukitett_modell);
                        }
                        else
                        {
                            szukitett_modell.setRowCount(0);
                            table.setModel(modell);                            
                            for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
                            {
                                String[] datum = table.getValueAt(szamlalo, 1).toString().split("-");
                                if(Integer.valueOf(datum[1]) == honap && table.getValueAt(szamlalo, 3).toString().equals("Reklamáció") && table.getValueAt(szamlalo, 2).toString().equals("Schweighardt Róbert"))
                                {
                                    szukitett_modell.addRow(new Object[]{table.getValueAt(szamlalo, 0),table.getValueAt(szamlalo, 1), table.getValueAt(szamlalo, 2), table.getValueAt(szamlalo, 3),
                                            table.getValueAt(szamlalo, 4),table.getValueAt(szamlalo, 5),table.getValueAt(szamlalo, 6),table.getValueAt(szamlalo, 7),table.getValueAt(szamlalo, 8)});
                                }
                            }
                            table.setModel(szukitett_modell);
                        }
                    }
                }
                if(String.valueOf(vagy_box.getSelectedItem()).equals("Egyéb"))
                {
                    if(honap == 0)
                    {
                        if(csak_gomb.isSelected())
                        {
                            szukitett_modell.setRowCount(0);
                            table.setModel(modell);
                            for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
                            {
                                if(table.getValueAt(szamlalo, 6) == null && table.getValueAt(szamlalo, 3).toString().equals("Egyéb") && table.getValueAt(szamlalo, 2).toString().equals("Schweighardt Róbert"))
                                {
                                    szukitett_modell.addRow(new Object[]{table.getValueAt(szamlalo, 0),table.getValueAt(szamlalo, 1), table.getValueAt(szamlalo, 2), table.getValueAt(szamlalo, 3),
                                            table.getValueAt(szamlalo, 4),table.getValueAt(szamlalo, 5),table.getValueAt(szamlalo, 6),table.getValueAt(szamlalo, 7),table.getValueAt(szamlalo, 8)});
                                }
                            }
                            table.setModel(szukitett_modell);
                        }
                        else
                        {
                            szukitett_modell.setRowCount(0);
                            table.setModel(modell);
                            for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
                            {
                                if(table.getValueAt(szamlalo, 3).toString().equals("Egyéb") && table.getValueAt(szamlalo, 2).toString().equals("Schweighardt Róbert"))
                                {
                                    szukitett_modell.addRow(new Object[]{table.getValueAt(szamlalo, 0),table.getValueAt(szamlalo, 1), table.getValueAt(szamlalo, 2), table.getValueAt(szamlalo, 3),
                                            table.getValueAt(szamlalo, 4),table.getValueAt(szamlalo, 5),table.getValueAt(szamlalo, 6),table.getValueAt(szamlalo, 7),table.getValueAt(szamlalo, 8)});
                                }
                            }
                            table.setModel(szukitett_modell);
                        }
                    }
                    else
                    {
                        if(csak_gomb.isSelected())
                        {
                            szukitett_modell.setRowCount(0);
                            table.setModel(modell);                            
                            for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
                            {
                                String[] datum = table.getValueAt(szamlalo, 1).toString().split("-");
                                if(table.getValueAt(szamlalo, 6) == null && Integer.valueOf(datum[1]) == honap && table.getValueAt(szamlalo, 3).toString().equals("Egyéb") && table.getValueAt(szamlalo, 2).toString().equals("Schweighardt Róbert"))
                                {
                                    szukitett_modell.addRow(new Object[]{table.getValueAt(szamlalo, 0),table.getValueAt(szamlalo, 1), table.getValueAt(szamlalo, 2), table.getValueAt(szamlalo, 3),
                                            table.getValueAt(szamlalo, 4),table.getValueAt(szamlalo, 5),table.getValueAt(szamlalo, 6),table.getValueAt(szamlalo, 7),table.getValueAt(szamlalo, 8)});
                                }
                            }
                            table.setModel(szukitett_modell);
                        }
                        else
                        {
                            szukitett_modell.setRowCount(0);
                            table.setModel(modell);                            
                            for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
                            {
                                String[] datum = table.getValueAt(szamlalo, 1).toString().split("-");
                                if(Integer.valueOf(datum[1]) == honap && table.getValueAt(szamlalo, 3).toString().equals("Egyéb") && table.getValueAt(szamlalo, 2).toString().equals("Schweighardt Róbert"))
                                {
                                    szukitett_modell.addRow(new Object[]{table.getValueAt(szamlalo, 0),table.getValueAt(szamlalo, 1), table.getValueAt(szamlalo, 2), table.getValueAt(szamlalo, 3),
                                            table.getValueAt(szamlalo, 4),table.getValueAt(szamlalo, 5),table.getValueAt(szamlalo, 6),table.getValueAt(szamlalo, 7),table.getValueAt(szamlalo, 8)});
                                }
                            }
                            table.setModel(szukitett_modell);
                        }
                    }
                }
            }
            if(String.valueOf(nev_box.getSelectedItem()).equals("Tóth Zoltán"))
            {
                if(String.valueOf(vagy_box.getSelectedItem()).equals("-"))
                {
                    if(honap == 0)
                    {
                        if(csak_gomb.isSelected())
                        {
                            szukitett_modell.setRowCount(0);
                            table.setModel(modell);
                            for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
                            {
                                if(table.getValueAt(szamlalo, 6) == null && table.getValueAt(szamlalo, 2).toString().equals("Tóth Zoltán"))
                                {
                                    szukitett_modell.addRow(new Object[]{table.getValueAt(szamlalo, 0),table.getValueAt(szamlalo, 1), table.getValueAt(szamlalo, 2), table.getValueAt(szamlalo, 3),
                                            table.getValueAt(szamlalo, 4),table.getValueAt(szamlalo, 5),table.getValueAt(szamlalo, 6),table.getValueAt(szamlalo, 7),table.getValueAt(szamlalo, 8)});
                                }
                            }
                            table.setModel(szukitett_modell);
                        }
                        else
                        {
                            szukitett_modell.setRowCount(0);
                            table.setModel(modell);
                            for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
                            {
                                if(table.getValueAt(szamlalo, 2).toString().equals("Tóth Zoltán"))
                                {
                                    szukitett_modell.addRow(new Object[]{table.getValueAt(szamlalo, 0),table.getValueAt(szamlalo, 1), table.getValueAt(szamlalo, 2), table.getValueAt(szamlalo, 3),
                                            table.getValueAt(szamlalo, 4),table.getValueAt(szamlalo, 5),table.getValueAt(szamlalo, 6),table.getValueAt(szamlalo, 7),table.getValueAt(szamlalo, 8)});
                                }
                            }
                            table.setModel(szukitett_modell);
                        }
                    }
                    else
                    {
                        if(csak_gomb.isSelected())
                        {
                            szukitett_modell.setRowCount(0);
                            table.setModel(modell);                            
                            for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
                            {
                                String[] datum = table.getValueAt(szamlalo, 1).toString().split("-");
                                if(table.getValueAt(szamlalo, 6) == null && Integer.valueOf(datum[1]) == honap && table.getValueAt(szamlalo, 2).toString().equals("Tóth Zoltán"))
                                {
                                    szukitett_modell.addRow(new Object[]{table.getValueAt(szamlalo, 0),table.getValueAt(szamlalo, 1), table.getValueAt(szamlalo, 2), table.getValueAt(szamlalo, 3),
                                            table.getValueAt(szamlalo, 4),table.getValueAt(szamlalo, 5),table.getValueAt(szamlalo, 6),table.getValueAt(szamlalo, 7),table.getValueAt(szamlalo, 8)});
                                }
                            }
                            table.setModel(szukitett_modell);
                        }
                        else
                        {
                            szukitett_modell.setRowCount(0);
                            table.setModel(modell);                            
                            for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
                            {
                                String[] datum = table.getValueAt(szamlalo, 1).toString().split("-");
                                if(Integer.valueOf(datum[1]) == honap && table.getValueAt(szamlalo, 2).toString().equals("Tóth Zoltán"))
                                {
                                    szukitett_modell.addRow(new Object[]{table.getValueAt(szamlalo, 0),table.getValueAt(szamlalo, 1), table.getValueAt(szamlalo, 2), table.getValueAt(szamlalo, 3),
                                            table.getValueAt(szamlalo, 4),table.getValueAt(szamlalo, 5),table.getValueAt(szamlalo, 6),table.getValueAt(szamlalo, 7),table.getValueAt(szamlalo, 8)});
                                }
                            }
                            table.setModel(szukitett_modell);
                        }
                    }
                }
                if(String.valueOf(vagy_box.getSelectedItem()).equals("Reklamáció"))
                {
                    if(honap == 0)
                    {
                        if(csak_gomb.isSelected())
                        {
                            szukitett_modell.setRowCount(0);
                            table.setModel(modell);
                            for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
                            {
                                if(table.getValueAt(szamlalo, 6) == null && table.getValueAt(szamlalo, 3).toString().equals("Reklamáció") && table.getValueAt(szamlalo, 2).toString().equals("Tóth Zoltán"))
                                {
                                    szukitett_modell.addRow(new Object[]{table.getValueAt(szamlalo, 0),table.getValueAt(szamlalo, 1), table.getValueAt(szamlalo, 2), table.getValueAt(szamlalo, 3),
                                            table.getValueAt(szamlalo, 4),table.getValueAt(szamlalo, 5),table.getValueAt(szamlalo, 6),table.getValueAt(szamlalo, 7),table.getValueAt(szamlalo, 8)});
                                }
                            }
                            table.setModel(szukitett_modell);
                        }
                        else
                        {
                            szukitett_modell.setRowCount(0);
                            table.setModel(modell);
                            for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
                            {
                                if(table.getValueAt(szamlalo, 3).toString().equals("Reklamáció") && table.getValueAt(szamlalo, 2).toString().equals("Tóth Zoltán"))
                                {
                                    szukitett_modell.addRow(new Object[]{table.getValueAt(szamlalo, 0),table.getValueAt(szamlalo, 1), table.getValueAt(szamlalo, 2), table.getValueAt(szamlalo, 3),
                                            table.getValueAt(szamlalo, 4),table.getValueAt(szamlalo, 5),table.getValueAt(szamlalo, 6),table.getValueAt(szamlalo, 7),table.getValueAt(szamlalo, 8)});
                                }
                            }
                            table.setModel(szukitett_modell);
                        }
                    }
                    else
                    {
                        if(csak_gomb.isSelected())
                        {
                            szukitett_modell.setRowCount(0);
                            table.setModel(modell);                            
                            for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
                            {
                                String[] datum = table.getValueAt(szamlalo, 1).toString().split("-");
                                if(table.getValueAt(szamlalo, 6) == null && Integer.valueOf(datum[1]) == honap && table.getValueAt(szamlalo, 3).toString().equals("Reklamáció") && table.getValueAt(szamlalo, 2).toString().equals("Tóth Zoltán"))
                                {
                                    szukitett_modell.addRow(new Object[]{table.getValueAt(szamlalo, 0),table.getValueAt(szamlalo, 1), table.getValueAt(szamlalo, 2), table.getValueAt(szamlalo, 3),
                                            table.getValueAt(szamlalo, 4),table.getValueAt(szamlalo, 5),table.getValueAt(szamlalo, 6),table.getValueAt(szamlalo, 7),table.getValueAt(szamlalo, 8)});
                                }
                            }
                            table.setModel(szukitett_modell);
                        }
                        else
                        {
                            szukitett_modell.setRowCount(0);
                            table.setModel(modell);                            
                            for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
                            {
                                String[] datum = table.getValueAt(szamlalo, 1).toString().split("-");
                                if(Integer.valueOf(datum[1]) == honap && table.getValueAt(szamlalo, 3).toString().equals("Reklamáció") && table.getValueAt(szamlalo, 2).toString().equals("Tóth Zoltán"))
                                {
                                    szukitett_modell.addRow(new Object[]{table.getValueAt(szamlalo, 0),table.getValueAt(szamlalo, 1), table.getValueAt(szamlalo, 2), table.getValueAt(szamlalo, 3),
                                            table.getValueAt(szamlalo, 4),table.getValueAt(szamlalo, 5),table.getValueAt(szamlalo, 6),table.getValueAt(szamlalo, 7),table.getValueAt(szamlalo, 8)});
                                }
                            }
                            table.setModel(szukitett_modell);
                        }
                    }
                }
                if(String.valueOf(vagy_box.getSelectedItem()).equals("Egyéb"))
                {
                    if(honap == 0)
                    {
                        if(csak_gomb.isSelected())
                        {
                            szukitett_modell.setRowCount(0);
                            table.setModel(modell);
                            for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
                            {
                                if(table.getValueAt(szamlalo, 6) == null && table.getValueAt(szamlalo, 3).toString().equals("Egyéb") && table.getValueAt(szamlalo, 2).toString().equals("Tóth Zoltán"))
                                {
                                    szukitett_modell.addRow(new Object[]{table.getValueAt(szamlalo, 0),table.getValueAt(szamlalo, 1), table.getValueAt(szamlalo, 2), table.getValueAt(szamlalo, 3),
                                            table.getValueAt(szamlalo, 4),table.getValueAt(szamlalo, 5),table.getValueAt(szamlalo, 6),table.getValueAt(szamlalo, 7),table.getValueAt(szamlalo, 8)});
                                }
                            }
                            table.setModel(szukitett_modell);
                        }
                        else
                        {
                            szukitett_modell.setRowCount(0);
                            table.setModel(modell);
                            for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
                            {
                                if(table.getValueAt(szamlalo, 3).toString().equals("Egyéb") && table.getValueAt(szamlalo, 2).toString().equals("Tóth Zoltán"))
                                {
                                    szukitett_modell.addRow(new Object[]{table.getValueAt(szamlalo, 0),table.getValueAt(szamlalo, 1), table.getValueAt(szamlalo, 2), table.getValueAt(szamlalo, 3),
                                            table.getValueAt(szamlalo, 4),table.getValueAt(szamlalo, 5),table.getValueAt(szamlalo, 6),table.getValueAt(szamlalo, 7),table.getValueAt(szamlalo, 8)});
                                }
                            }
                            table.setModel(szukitett_modell);
                        }
                    }
                    else
                    {
                        if(csak_gomb.isSelected())
                        {
                            szukitett_modell.setRowCount(0);
                            table.setModel(modell);                            
                            for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
                            {
                                String[] datum = table.getValueAt(szamlalo, 1).toString().split("-");
                                if(table.getValueAt(szamlalo, 6) == null && Integer.valueOf(datum[1]) == honap && table.getValueAt(szamlalo, 3).toString().equals("Egyéb") && table.getValueAt(szamlalo, 2).toString().equals("Tóth Zoltán"))
                                {
                                    szukitett_modell.addRow(new Object[]{table.getValueAt(szamlalo, 0),table.getValueAt(szamlalo, 1), table.getValueAt(szamlalo, 2), table.getValueAt(szamlalo, 3),
                                            table.getValueAt(szamlalo, 4),table.getValueAt(szamlalo, 5),table.getValueAt(szamlalo, 6),table.getValueAt(szamlalo, 7),table.getValueAt(szamlalo, 8)});
                                }
                            }
                            table.setModel(szukitett_modell);
                        }
                        else
                        {
                            szukitett_modell.setRowCount(0);
                            table.setModel(modell);                            
                            for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
                            {
                                String[] datum = table.getValueAt(szamlalo, 1).toString().split("-");
                                if(Integer.valueOf(datum[1]) == honap && table.getValueAt(szamlalo, 3).toString().equals("Egyéb") && table.getValueAt(szamlalo, 2).toString().equals("Tóth Zoltán"))
                                {
                                    szukitett_modell.addRow(new Object[]{table.getValueAt(szamlalo, 0),table.getValueAt(szamlalo, 1), table.getValueAt(szamlalo, 2), table.getValueAt(szamlalo, 3),
                                            table.getValueAt(szamlalo, 4),table.getValueAt(szamlalo, 5),table.getValueAt(szamlalo, 6),table.getValueAt(szamlalo, 7),table.getValueAt(szamlalo, 8)});
                                }
                            }
                            table.setModel(szukitett_modell);
                        }
                    }
                }
            }
            if(String.valueOf(nev_box.getSelectedItem()).equals("Horváth Balázs"))
            {
                if(String.valueOf(vagy_box.getSelectedItem()).equals("-"))
                {
                    if(honap == 0)
                    {
                        if(csak_gomb.isSelected())
                        {
                            szukitett_modell.setRowCount(0);
                            table.setModel(modell);
                            for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
                            {
                                if(table.getValueAt(szamlalo, 6) == null && table.getValueAt(szamlalo, 2).toString().equals("Horváth Balázs"))
                                {
                                    szukitett_modell.addRow(new Object[]{table.getValueAt(szamlalo, 0),table.getValueAt(szamlalo, 1), table.getValueAt(szamlalo, 2), table.getValueAt(szamlalo, 3),
                                            table.getValueAt(szamlalo, 4),table.getValueAt(szamlalo, 5),table.getValueAt(szamlalo, 6),table.getValueAt(szamlalo, 7),table.getValueAt(szamlalo, 8)});
                                }
                            }
                            table.setModel(szukitett_modell);
                        }
                        else
                        {
                            szukitett_modell.setRowCount(0);
                            table.setModel(modell);
                            for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
                            {
                                if(table.getValueAt(szamlalo, 2).toString().equals("Horváth Balázs"))
                                {
                                    szukitett_modell.addRow(new Object[]{table.getValueAt(szamlalo, 0),table.getValueAt(szamlalo, 1), table.getValueAt(szamlalo, 2), table.getValueAt(szamlalo, 3),
                                            table.getValueAt(szamlalo, 4),table.getValueAt(szamlalo, 5),table.getValueAt(szamlalo, 6),table.getValueAt(szamlalo, 7),table.getValueAt(szamlalo, 8)});
                                }
                            }
                            table.setModel(szukitett_modell);
                        }
                    }
                    else
                    {
                        if(csak_gomb.isSelected())
                        {
                            szukitett_modell.setRowCount(0);
                            table.setModel(modell);                            
                            for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
                            {
                                String[] datum = table.getValueAt(szamlalo, 1).toString().split("-");
                                if(table.getValueAt(szamlalo, 6) == null && Integer.valueOf(datum[1]) == honap && table.getValueAt(szamlalo, 2).toString().equals("Horváth Balázs"))
                                {
                                    szukitett_modell.addRow(new Object[]{table.getValueAt(szamlalo, 0),table.getValueAt(szamlalo, 1), table.getValueAt(szamlalo, 2), table.getValueAt(szamlalo, 3),
                                            table.getValueAt(szamlalo, 4),table.getValueAt(szamlalo, 5),table.getValueAt(szamlalo, 6),table.getValueAt(szamlalo, 7),table.getValueAt(szamlalo, 8)});
                                }
                            }
                            table.setModel(szukitett_modell);
                        }
                        else
                        {
                            szukitett_modell.setRowCount(0);
                            table.setModel(modell);                            
                            for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
                            {
                                String[] datum = table.getValueAt(szamlalo, 1).toString().split("-");
                                if(Integer.valueOf(datum[1]) == honap && table.getValueAt(szamlalo, 2).toString().equals("Horváth Balázs"))
                                {
                                    szukitett_modell.addRow(new Object[]{table.getValueAt(szamlalo, 0),table.getValueAt(szamlalo, 1), table.getValueAt(szamlalo, 2), table.getValueAt(szamlalo, 3),
                                            table.getValueAt(szamlalo, 4),table.getValueAt(szamlalo, 5),table.getValueAt(szamlalo, 6),table.getValueAt(szamlalo, 7),table.getValueAt(szamlalo, 8)});
                                }
                            }
                            table.setModel(szukitett_modell);
                        }
                    }
                }
                if(String.valueOf(vagy_box.getSelectedItem()).equals("Reklamáció"))
                {
                    if(honap == 0)
                    {
                        if(csak_gomb.isSelected())
                        {
                            szukitett_modell.setRowCount(0);
                            table.setModel(modell);
                            for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
                            {
                                if(table.getValueAt(szamlalo, 6) == null && table.getValueAt(szamlalo, 3).toString().equals("Reklamáció") && table.getValueAt(szamlalo, 2).toString().equals("Horváth Balázs"))
                                {
                                    szukitett_modell.addRow(new Object[]{table.getValueAt(szamlalo, 0),table.getValueAt(szamlalo, 1), table.getValueAt(szamlalo, 2), table.getValueAt(szamlalo, 3),
                                            table.getValueAt(szamlalo, 4),table.getValueAt(szamlalo, 5),table.getValueAt(szamlalo, 6),table.getValueAt(szamlalo, 7),table.getValueAt(szamlalo, 8)});
                                }
                            }
                            table.setModel(szukitett_modell);
                        }
                        else
                        {
                            szukitett_modell.setRowCount(0);
                            table.setModel(modell);
                            for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
                            {
                                if(table.getValueAt(szamlalo, 3).toString().equals("Reklamáció") && table.getValueAt(szamlalo, 2).toString().equals("Horváth Balázs"))
                                {
                                    szukitett_modell.addRow(new Object[]{table.getValueAt(szamlalo, 0),table.getValueAt(szamlalo, 1), table.getValueAt(szamlalo, 2), table.getValueAt(szamlalo, 3),
                                            table.getValueAt(szamlalo, 4),table.getValueAt(szamlalo, 5),table.getValueAt(szamlalo, 6),table.getValueAt(szamlalo, 7),table.getValueAt(szamlalo, 8)});
                                }
                            }
                            table.setModel(szukitett_modell);
                        }
                    }
                    else
                    {
                        if(csak_gomb.isSelected())
                        {
                            szukitett_modell.setRowCount(0);
                            table.setModel(modell);                            
                            for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
                            {
                                String[] datum = table.getValueAt(szamlalo, 1).toString().split("-");
                                if(table.getValueAt(szamlalo, 6) == null && Integer.valueOf(datum[1]) == honap && table.getValueAt(szamlalo, 3).toString().equals("Reklamáció") && table.getValueAt(szamlalo, 2).toString().equals("Horváth Balázs"))
                                {
                                    szukitett_modell.addRow(new Object[]{table.getValueAt(szamlalo, 0),table.getValueAt(szamlalo, 1), table.getValueAt(szamlalo, 2), table.getValueAt(szamlalo, 3),
                                            table.getValueAt(szamlalo, 4),table.getValueAt(szamlalo, 5),table.getValueAt(szamlalo, 6),table.getValueAt(szamlalo, 7),table.getValueAt(szamlalo, 8)});
                                }
                            }
                            table.setModel(szukitett_modell);
                        }
                        else
                        {
                            szukitett_modell.setRowCount(0);
                            table.setModel(modell);                            
                            for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
                            {
                                String[] datum = table.getValueAt(szamlalo, 1).toString().split("-");
                                if(Integer.valueOf(datum[1]) == honap && table.getValueAt(szamlalo, 3).toString().equals("Reklamáció") && table.getValueAt(szamlalo, 2).toString().equals("Horváth Balázs"))
                                {
                                    szukitett_modell.addRow(new Object[]{table.getValueAt(szamlalo, 0),table.getValueAt(szamlalo, 1), table.getValueAt(szamlalo, 2), table.getValueAt(szamlalo, 3),
                                            table.getValueAt(szamlalo, 4),table.getValueAt(szamlalo, 5),table.getValueAt(szamlalo, 6),table.getValueAt(szamlalo, 7),table.getValueAt(szamlalo, 8)});
                                }
                            }
                            table.setModel(szukitett_modell);
                        }
                    }
                }
                if(String.valueOf(vagy_box.getSelectedItem()).equals("Egyéb"))
                {
                    if(honap == 0)
                    {
                        if(csak_gomb.isSelected())
                        {
                            szukitett_modell.setRowCount(0);
                            table.setModel(modell);
                            for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
                            {
                                if(table.getValueAt(szamlalo, 6) == null && table.getValueAt(szamlalo, 3).toString().equals("Egyéb") && table.getValueAt(szamlalo, 2).toString().equals("Horváth Balázs"))
                                {
                                    szukitett_modell.addRow(new Object[]{table.getValueAt(szamlalo, 0),table.getValueAt(szamlalo, 1), table.getValueAt(szamlalo, 2), table.getValueAt(szamlalo, 3),
                                            table.getValueAt(szamlalo, 4),table.getValueAt(szamlalo, 5),table.getValueAt(szamlalo, 6),table.getValueAt(szamlalo, 7),table.getValueAt(szamlalo, 8)});
                                }
                            }
                            table.setModel(szukitett_modell);
                        }
                        else
                        {
                            szukitett_modell.setRowCount(0);
                            table.setModel(modell);
                            for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
                            {
                                if(table.getValueAt(szamlalo, 3).toString().equals("Egyéb") && table.getValueAt(szamlalo, 2).toString().equals("Horváth Balázs"))
                                {
                                    szukitett_modell.addRow(new Object[]{table.getValueAt(szamlalo, 0),table.getValueAt(szamlalo, 1), table.getValueAt(szamlalo, 2), table.getValueAt(szamlalo, 3),
                                            table.getValueAt(szamlalo, 4),table.getValueAt(szamlalo, 5),table.getValueAt(szamlalo, 6),table.getValueAt(szamlalo, 7),table.getValueAt(szamlalo, 8)});
                                }
                            }
                            table.setModel(szukitett_modell);
                        }
                    }
                    else
                    {
                        if(csak_gomb.isSelected())
                        {
                            szukitett_modell.setRowCount(0);
                            table.setModel(modell);                            
                            for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
                            {
                                String[] datum = table.getValueAt(szamlalo, 1).toString().split("-");
                                if(table.getValueAt(szamlalo, 6) == null && Integer.valueOf(datum[1]) == honap && table.getValueAt(szamlalo, 3).toString().equals("Egyéb") && table.getValueAt(szamlalo, 2).toString().equals("Horváth Balázs"))
                                {
                                    szukitett_modell.addRow(new Object[]{table.getValueAt(szamlalo, 0),table.getValueAt(szamlalo, 1), table.getValueAt(szamlalo, 2), table.getValueAt(szamlalo, 3),
                                            table.getValueAt(szamlalo, 4),table.getValueAt(szamlalo, 5),table.getValueAt(szamlalo, 6),table.getValueAt(szamlalo, 7),table.getValueAt(szamlalo, 8)});
                                }
                            }
                            table.setModel(szukitett_modell);
                        }
                        else
                        {
                            szukitett_modell.setRowCount(0);
                            table.setModel(modell);                            
                            for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
                            {
                                String[] datum = table.getValueAt(szamlalo, 1).toString().split("-");
                                if(Integer.valueOf(datum[1]) == honap && table.getValueAt(szamlalo, 3).toString().equals("Egyéb") && table.getValueAt(szamlalo, 2).toString().equals("Horváth Balázs"))
                                {
                                    szukitett_modell.addRow(new Object[]{table.getValueAt(szamlalo, 0),table.getValueAt(szamlalo, 1), table.getValueAt(szamlalo, 2), table.getValueAt(szamlalo, 3),
                                            table.getValueAt(szamlalo, 4),table.getValueAt(szamlalo, 5),table.getValueAt(szamlalo, 6),table.getValueAt(szamlalo, 7),table.getValueAt(szamlalo, 8)});
                                }
                            }
                            table.setModel(szukitett_modell);
                        }
                    }
                }
            }
            ////// rendezés és az állapot mentése
            TableColumnModel columnModel = table.getColumnModel();
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
            }
            try
            {
                PrintWriter writer = new PrintWriter(beallitasok, "UTF-8");
                writer.print(String.valueOf(nev_box.getSelectedItem())+";"+String.valueOf(vagy_box.getSelectedItem())+";"+csak_gomb.isSelected()+";"+String.valueOf(honap_box.getSelectedItem()));
                writer.close();
            }
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", getClass()+" "+ hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                   //kivétel esetén kiírja a hibaüzenetet
            }
            Foablak.frame.setCursor(null);
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
    
    class IFS_kereses implements ActionListener                                                                                      //csv-t gyárt a gomb
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {              
                String menteshelye = System.getProperty("user.home") + "\\Desktop\\Raktári cikkek.xlsx";

                  DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                  Class.forName("oracle.jdbc.OracleDriver");  //.driver
                                      
                  Connection con = DriverManager.getConnection("jdbc:oracle:thin:@IFSORA.IFS.videoton.hu:1521/IFSPROD","ZKOVACS","ZKOVACS");                                      
                  Statement stmt = con.createStatement();                      
                                               
                  Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                
                  Workbook workbook = new Workbook();
                  Worksheet sheet = workbook.getWorksheets().get(0);               
                  ResultSet rs = stmt.executeQuery("select cikk.part_no, ifsapp.Inventory_Part_API.Get_Description(cikk.CONTRACT,cikk.PART_NO), ifsapp.inventory_part_api.Get_Second_Commodity(cikk.contract, cikk.Part_no), csoport.attr_value, osztaly.attr_value, tipus.attr_value\r\n"
                          + "from ifsapp.INVENTORY_PART_CHAR_ALL cikk,\r\n"
                          + "(select part_no, attr_value from ifsapp.INVENTORY_PART_CHAR_ALL where 3=3 and characteristic_code = 'GRP') csoport, -- attr_value\r\n"
                          + "(select part_no, attr_value from ifsapp.INVENTORY_PART_CHAR_ALL where 3=3 and characteristic_code = 'CLASS') osztaly,\r\n"
                          + "(select part_no, attr_value from ifsapp.INVENTORY_PART_CHAR_ALL where 3=3 and characteristic_code = 'TYPE') tipus\r\n"
                          + "where 3=3\r\n"
                          + "and cikk.part_no = csoport.part_no\r\n"
                          + "and cikk.part_no = osztaly.part_no\r\n"
                          + "and cikk.part_no = tipus.part_no\r\n"
                          + "group by cikk.part_no, csoport.attr_value, osztaly.attr_value, tipus.attr_value, \r\n"
                          + "ifsapp.Inventory_Part_API.Get_Description(cikk.CONTRACT,cikk.PART_NO), ifsapp.inventory_part_api.Get_Second_Commodity(cikk.contract, cikk.Part_no)\r\n"
                          + "");
                  
                  int cellaszam = 1;
                  sheet.getRange().get("A" + cellaszam).setText("Cikkszám");
                  sheet.getRange().get("B" + cellaszam).setText("Megnevezés");
                  sheet.getRange().get("C" + cellaszam).setText("Projekt");
                  sheet.getRange().get("D" + cellaszam).setText("Csoport");
                  sheet.getRange().get("E" + cellaszam).setText("Osztály");
                  sheet.getRange().get("F" + cellaszam).setText("Típus");
                  cellaszam++;
                  
                  while(rs.next())
                  {
                      sheet.getRange().get("A" + cellaszam).setText(rs.getString(1));
                      sheet.getRange().get("B" + cellaszam).setText(rs.getString(2));
                      sheet.getRange().get("C" + cellaszam).setText(rs.getString(3));
                      sheet.getRange().get("D" + cellaszam).setText(rs.getString(4));
                      sheet.getRange().get("E" + cellaszam).setText(rs.getString(5));
                      sheet.getRange().get("F" + cellaszam).setText(rs.getString(6));
                      cellaszam++;
                  }

                  
                  sheet.getAutoFilters().setRange(sheet.getCellRange("A1:J1"));
                  sheet.getAllocatedRange().autoFitColumns();
                  sheet.getAllocatedRange().autoFitRows();
                  
                  sheet.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                  
                  workbook.saveToFile(menteshelye, ExcelVersion.Version2016);
                  stmt.close();
                  con.close();
                  
                  FileInputStream fileStream = new FileInputStream(menteshelye);
                  try (XSSFWorkbook workbook3 = new XSSFWorkbook(fileStream)) 
                  {
                      for(int i = workbook3.getNumberOfSheets()-1; i>0 ;i--)
                      {    
                          workbook3.removeSheetAt(i); 
                      }      
                      FileOutputStream output = new FileOutputStream(menteshelye);
                      workbook3.write(output);
                      output.close();
                  }                       
                  JOptionPane.showMessageDialog(null, "Kész! \n Mentve az asztalra Raktári cikkek.xlsx néven!", "Info", 1); 
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
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                        //kiírja a hibaüzenetet
            }  
                               
         }
    }
    
    class Torles implements ActionListener                                                                                      //csv-t gyárt a gomb
    {
        public void actionPerformed(ActionEvent e)
         {
            Connection conn = null;
            Statement stmt = null;
          
            try 
            {
                int reply = JOptionPane.showConfirmDialog(null, "Biztos törlöd a kiválasztott sort?", "Értesítés", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                   Class.forName("com.mysql.cj.jdbc.Driver");
                   conn = (Connection) DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
                   stmt = (Statement) conn.createStatement();                             
                   Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                   
                   Utolso_sor sorszam = new Utolso_sor();
                   int sor = table.getSelectedRow();
                   int utolsosor = Integer.parseInt(sorszam.utolso("qualitydb.SQA_reklamaciok"));
                   int id = Integer.parseInt(table.getValueAt(sor, 0).toString());
                   int id2 = id+1;
                   
                   String sql = "delete from qualitydb.SQA_reklamaciok where id = '"+ table.getValueAt(sor, 0).toString() +"'";
                   stmt.execute(sql);
                   //ResultSet rs = stmt.getResultSet();
                   System.out.println("Utolsó sor "+utolsosor);
                   System.out.println("következő id "+id2);
                   String evszam = String.valueOf(id);
                   File torolni = new File("\\\\10.1.0.11\\minosegbiztositas\\SQA\\reklamációk\\20"+ evszam.subSequence(0, 2) +"\\"+ id+"\\");
                   System.out.println(torolni.getAbsolutePath());
                   deleteFolder(torolni);
                   
    
                   for(int szamlalo = id; szamlalo < (utolsosor+1); szamlalo++)
                   {
                       System.out.println("Fut a for");
                       sql = "update qualitydb.SQA_reklamaciok set id = '"+ id +"', futo_id = '"+ id +"'  where id = '"+ id2 +"'";
                       stmt.execute(sql);
                       id++;
                       id2++;
                       File ezt = new File("\\\\10.1.0.11\\minosegbiztositas\\SQA\\reklamációk\\20"+ evszam.subSequence(0, 2) +"\\"+ (szamlalo+1)+"\\");
                       File erre = new File("\\\\10.1.0.11\\minosegbiztositas\\SQA\\reklamációk\\20"+ evszam.subSequence(0, 2) +"\\"+ szamlalo+"\\");
                       ezt.renameTo(erre);
                   }
                   sql = "ALTER TABLE qualitydb.SQA_reklamaciok AUTO_INCREMENT = "+ id +"";
                   stmt.execute(sql);
                   stmt.close();
                   conn.close();
                   
                   int rowCount = modell.getRowCount();
                   
                   
                   for (int i = rowCount - 1; i > -1; i--) 
                   {
                     modell.removeRow(i);
                   }
                   
                   table.setModel(modell);
                   
                   adatok();
                   Foablak.frame.pack();
                   Foablak.frame.repaint();
                   Foablak.frame.setCursor(null);
                }
                else {}
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
    
    public void deleteFolder(File folder) {
        File[] files = folder.listFiles();
        if(files!=null) { //some JVMs return null for empty dirs
            for(File f: files) {
                if(f.isDirectory()) {
                    deleteFolder(f);
                } else {
                    f.delete();
                }
            }
        }
        folder.delete();
    }
    
    
}
