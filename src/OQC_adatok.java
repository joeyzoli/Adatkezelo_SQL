import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.spire.data.table.DataTable;
import com.spire.data.table.common.JdbcAdapter;
import com.spire.xls.ExcelVersion;
import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JButton;

public class OQC_adatok extends JPanel {
    private JTable table;
    private JTable table_2;
    private JTable table_3;
    //private Dimension meretek = new Dimension(1100, 850);
    private DefaultTableModel modell;
    private DefaultTableModel modell2;
    private DefaultTableModel modell3;
    private JTextField dobozdb_mezo;
    private JTextField termekdb_mezo;
    private String[] cikkszamok;
    private JComboBox<String> termek_box;
    private ArrayList<String[]> adatok = new ArrayList<String[]>();
    private ArrayList<String> raklapok = new ArrayList<String>();
    
    /**
     * Create the panel.
     */
    public OQC_adatok() {
        setLayout(null);
        setPreferredSize(new Dimension(1156, 850));
        JLabel lblNewLabel = new JLabel("OQC riport készítés");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 13));
        lblNewLabel.setBounds(535, 11, 218, 23);
        add(lblNewLabel);
        
        table = new JTable(){
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
          };;
        table.setFocusable(false);
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {     // to detect doble click events
                   JTable target = (JTable)me.getSource();
                   int row = target.getSelectedRow(); // select a row
                   modell3.addRow(new Object[]{table.getValueAt(row, 2)});
                   table_3.setModel(modell3);
                   raklapok.add(String.valueOf(table.getValueAt(row, 1))+ ";" +String.valueOf(table.getValueAt(row, 2)));      //String.valueOf()
                }
                else if (me.getClickCount() == 1) {     // to detect doble click events
                    try
                    {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection conn2 = (Connection) DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
                        Statement stmt2 = (Statement) conn2.createStatement();
                        ResultSet rs2 =null;
                        if(table.getValueAt(table.getSelectedRow(), 1).toString().contains("FB7530"))
                        {
                            rs2 = stmt2.executeQuery("select Szeriaszam_termek, hiba, Hibacsoport, megjegyzes, Kritikus_hiba,Sulyos_hiba, Enyhe_hiba from qualitydb.OQC_FB7530 where 3=3 and Raklapszam = '"+ table.getValueAt(table.getSelectedRow(), 2).toString() +"'");
                        }
                        else if(table.getValueAt(table.getSelectedRow(), 1).toString().contains("FD302"))
                        {
                            rs2 = stmt2.executeQuery("select Szeriaszam_doboz, hiba, Hibacsoport, megjegyzes, Kritikus_hiba,Sulyos_hiba, Enyhe_hiba from qualitydb.OQC_FD302 where 3=3 and Raklapszam = '"+ table.getValueAt(table.getSelectedRow(), 2).toString() +"'");
                        }
                        else if(table.getValueAt(table.getSelectedRow(), 1).toString().contains("FR1200"))
                        {
                            rs2 = stmt2.executeQuery("select Szeriaszam_termek, hiba, Hibacsoport, megjegyzes, Kritikus_hiba,Sulyos_hiba, Enyhe_hiba from qualitydb.OQC_FR1200 where 3=3 and Raklapszam = '"+ table.getValueAt(table.getSelectedRow(), 2).toString() +"'");
                        }
                        else if(table.getValueAt(table.getSelectedRow(), 1).toString().contains("FR2400"))
                        {
                            rs2 = stmt2.executeQuery("select Szeriaszam_termek, hiba, Hibacsoport, megjegyzes, Kritikus_hiba,Sulyos_hiba, Enyhe_hiba from qualitydb.OQC_FR2400 where 3=3 and Raklapszam = '"+ table.getValueAt(table.getSelectedRow(), 2).toString() +"'");
                        }
                        else if(table.getValueAt(table.getSelectedRow(), 1).toString().contains("FR600"))
                        {
                            rs2 = stmt2.executeQuery("select Szeriaszam_termek, hiba, Hibacsoport, megjegyzes, Kritikus_hiba,Sulyos_hiba, Enyhe_hiba from qualitydb.OQC_FR600 where 3=3 and Raklapszam = '"+ table.getValueAt(table.getSelectedRow(), 2).toString() +"'");
                        }
                        else if(table.getValueAt(table.getSelectedRow(), 1).toString().contains("FD301"))
                        {
                            rs2 = stmt2.executeQuery("select Szeriaszam_termek, hiba, Hibacsoport, megjegyzes, Kritikus_hiba,Sulyos_hiba, Enyhe_hiba from qualitydb.OQC_FD301 where 3=3 and Raklapszam = '"+ table.getValueAt(table.getSelectedRow(), 2).toString() +"'");
                        }
                        if (modell2.getRowCount() > 0) {
                            for (int i = modell2.getRowCount() - 1; i > -1; i--) {
                                modell2.removeRow(i);
                            }
                        }
                        while(rs2.next())
                        {
                            String hibafajta = "";
                            if(rs2.getString(5).equals("X"))
                            {
                                hibafajta = "Kritikus";
                            }
                            if(rs2.getString(6).equals("X"))
                            {
                                hibafajta = "Súlyos";
                            }
                            if(rs2.getString(7).equals("X"))
                            {
                                hibafajta = "Enyhe";
                            }
                            modell2.addRow(new Object[]{rs2.getString(1), rs2.getString(2), "", rs2.getString(3), rs2.getString(4),hibafajta});
                        }
                        table_2.setModel(modell2);
                    }
                    catch(Exception e1)
                    { 
                        System.out.println(e1);
                        e1.printStackTrace();
                        String hibauzenet2 = e1.toString();
                        JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);                                                 //kiírja a hibaüzenetet
                    }  
                 }
                
             }
          });       
        JScrollPane gorgeto = new JScrollPane(table);
        gorgeto.setBounds(37, 121, 1113, 310);
        add(gorgeto);
        
        modell = new DefaultTableModel();
        modell.setColumnIdentifiers(new Object[]{"Cikkszám", "Cikk megnevezés", "P Vonalkód", "Elérhető menny.", "Raktárhely száma","Raktár", "OQC-zett", "Zárolt"});
        table.setModel(modell);
        
        JLabel lblNewLabel_1 = new JLabel("Termék választás");
        lblNewLabel_1.setBounds(37, 59, 108, 14);
        add(lblNewLabel_1);
        
        cikkszamok();
        termek_box = new JComboBox<String>(cikkszamok);                       //cikkszamok
        termek_box.addActionListener(new Cikkvalaszto());
        termek_box.setBounds(144, 55, 269, 22);
        add(termek_box);
        
        JLabel lblNewLabel_2 = new JLabel("Doboz db");
        lblNewLabel_2.setBounds(462, 59, 58, 14);
        add(lblNewLabel_2);
        
        dobozdb_mezo = new JTextField();
        dobozdb_mezo.setBounds(535, 56, 46, 20);
        add(dobozdb_mezo);
        dobozdb_mezo.setColumns(10);
        
        JLabel lblNewLabel_3 = new JLabel("Termék db");
        lblNewLabel_3.setBounds(622, 59, 63, 14);
        add(lblNewLabel_3);
        
        termekdb_mezo = new JTextField();
        termekdb_mezo.setBounds(695, 56, 46, 20);
        add(termekdb_mezo);
        termekdb_mezo.setColumns(10);
        
        JLabel lblNewLabel_4 = new JLabel("OQC hiba adatok");
        lblNewLabel_4.setBounds(37, 442, 120, 14);
        add(lblNewLabel_4);
        
        table_2 = new JTable();
        JScrollPane gorgeto2 = new JScrollPane(table_2);
        gorgeto2.setBounds(37, 467, 664, 158);
        add(gorgeto2);
        
        modell2 = new DefaultTableModel();
        modell2.setColumnIdentifiers(new Object[]{"Szériaszám termék", "Hibakód", "Hiba", "Hibacsoport", "Megjegyzés","Hiba kategória"});
        table_2.setModel(modell2);
        
        JLabel lblNewLabel_5 = new JLabel("Kiajánlásra váró dobozok");
        lblNewLabel_5.setBounds(837, 442, 168, 14);
        add(lblNewLabel_5);
        
        table_3 = new JTable();
        JScrollPane gorgeto3 = new JScrollPane(table_3);
        gorgeto3.setBounds(837, 467, 313, 158);       
        add(gorgeto3);
        
        modell3 = new DefaultTableModel();
        modell3.setColumnIdentifiers(new Object[]{"P Vonalkód"});
        table_3.setModel(modell3);
        
        JButton excel_gomb = new JButton("Excel");
        excel_gomb.addActionListener(new Excel());
        excel_gomb.setBounds(473, 705, 89, 23);
        add(excel_gomb);
        
        JButton kiajaln_gomb = new JButton("Kiajánlás");
        kiajaln_gomb.addActionListener(new Kiajanlo());
        kiajaln_gomb.setBounds(724, 705, 89, 23);
        add(kiajaln_gomb);
        
        JButton oqcadatok_gomb = new JButton("OQC adatok");
        oqcadatok_gomb.setBounds(581, 777, 120, 23);
        add(oqcadatok_gomb);
        setBackground(Foablak.hatter_szine);
        lekerdezes();
        
        setBackground(Foablak.hatter_szine);
    }
    
    public void lekerdezes()
    {
        try
        {
            Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn2 = (Connection) DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
            Statement stmt2 = (Statement) conn2.createStatement();
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            Class.forName("oracle.jdbc.OracleDriver");  //.driver
                                
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@IFSORA.IFS.videoton.hu:1521/IFSPROD","ZKOVACS","ZKOVACS");                                      
            Statement stmt = con.createStatement();   
            
            ResultSet rs = stmt.executeQuery("select \r\n"
                    + "ip.second_commodity Cikkcsoport2\r\n"
                    + ",ip.part_no Cikkszam\r\n"
                    + ",ip.description Cikk_megnevezes\r\n"
                    + ",ipis.eng_chg_level Valtozatszam\r\n"
                    + ",ipis.lot_batch_no Sarzsszam\r\n"
                    + ",ipis.waiv_dev_rej_no ME_szam\r\n"
                    + ",cpb.origin_pack_size Eredeti_csomagmeret\r\n"
                    + ",concat('P',concat(ipis.part_no,concat('..',concat(cpb.origin_pack_size,concat('.',concat(ipis.eng_chg_level,concat('.',concat(ipis.waiv_dev_rej_no,concat('.',to_char(ipis.receipt_date,'YYYYMMDD')))))))))) P_vonalkod\r\n"
                    + ",ipis.qty_onhand-ipis.qty_reserved Elerh_menny\r\n"
                    + ",ipis.qty_reserved Foglalt_menny\r\n"
                    + ",ipis.qty_onhand Keszleten_menny\r\n"
                    + ",ipis.location_no Raktarhely_szama\r\n"
                    + ",ipis.warehouse Raktar\r\n"
                    + ",cpb.c_manuf_date Gyartas_datuma\r\n"
                    + ",ipis.receipt_date Keszletrevetel_datuma\r\n"
                    + ",ipis.last_activity_date Utolso_tev_datuma\r\n"
                    + ",ipis.availability_control_id Hasznalatvezerles_az\r\n"
                    + ",ifsapp.PART_AVAILABILITY_CONTROL_API.Get_Description(ipis.AVAILABILITY_CONTROL_ID) Használatvezérlés_megnevezés\r\n"
                    + "from ifsapp.inventory_part ip, ifsapp.INVENTORY_PART_IN_STOCK ipis, ifsapp.INVENTORY_PART_BARCODE cpb \r\n"
                    + "where\r\n"
                    + "2=2\r\n"
                    + "and ipis.contract=ip.contract\r\n"
                    + "and ipis.part_no=ip.part_no\r\n"
                    + "and cpb.part_no = ipis.part_no\r\n"
                    + "and to_char( cpb.barcode_id) = ipis.waiv_dev_rej_no \r\n"
                    + "and cpb.lot_batch_no = ipis.lot_batch_no\r\n"
                    + "and cpb.eng_chg_level = ipis.eng_chg_level\r\n"
                    + "and cpb.configuration_id = ipis.configuration_id\r\n"
                    + "and cpb.serial_no = ipis.serial_no\r\n"
                    + "and cpb.activity_seq = ipis.activity_seq\r\n"
                    + "and ip.contract = 'VEAS'\r\n"
                    + "\r\n"
                    + "and ip.second_commodity = 'VAVM'\r\n"
                    + "and ip.part_product_code = '1'\r\n"
                    + "and ipis.qty_onhand>0\r\n"
                    + "");
            
            while(rs.next())
            { 
                if(rs.getInt(9) >0)
                {                    
                    ResultSet rs2 = null;
                    if(rs.getString(3).contains("FB7530"))
                    {
                        rs2 = stmt2.executeQuery("select count(raklapszam), Kiajanlva, Zarolva from qualitydb.OQC_FB7530 where 3=3 and Raklapszam = '"+ rs.getString(8) +"'");
                    }
                    else if(rs.getString(3).contains("FD302"))
                    {
                        rs2 = stmt2.executeQuery("select count(raklapszam), Kiajanlva, Zarolva from qualitydb.OQC_FD302 where 3=3 and Raklapszam = '"+ rs.getString(8) +"'");
                    }
                    else if(rs.getString(3).contains("FR1200"))
                    {
                        rs2 = stmt2.executeQuery("select count(raklapszam), Kiajanlva, Zarolva from qualitydb.OQC_FR1200 where 3=3 and Raklapszam = '"+ rs.getString(8) +"'");
                    }
                    else if(rs.getString(3).contains("FR2400"))
                    {
                        rs2 = stmt2.executeQuery("select count(raklapszam), Kiajanlva, Zarolva from qualitydb.OQC_FR2400 where 3=3 and Raklapszam = '"+ rs.getString(8) +"'");
                    }
                    else if(rs.getString(3).contains("FR600"))
                    {
                        rs2 = stmt2.executeQuery("select count(raklapszam), Kiajanlva, Zarolva from qualitydb.OQC_FR600 where 3=3 and Raklapszam = '"+ rs.getString(8) +"'");
                    }
                    else if(rs.getString(3).contains("FD301"))
                    {
                        rs2 = stmt2.executeQuery("select count(raklapszam), Kiajanlva, Zarolva from qualitydb.OQC_FD301 where 3=3 and Raklapszam = '"+ rs.getString(8) +"'");
                    } 
                    if(rs2 != null)
                    {
                        if(rs2.next())
                        {
                            String[] ideiglenes = {rs.getString(2), rs.getString(3), rs.getString(8), rs.getString(9),rs.getString(12),rs.getString(13),rs2.getString(1),rs2.getString(3)};
                            adatok.add(ideiglenes);
                            if(rs2.getString(2) != null)
                            {
                                if(rs2.getString(2).equals("Nem"))
                                {                                   
                                    modell.addRow(new Object[]{rs.getString(2), rs.getString(3), rs.getString(8), rs.getString(9),rs.getString(12),rs.getString(13),rs2.getString(1),rs2.getString(3)});
                                }
                            }
                        }
                    }
                    else
                    {
                        String[] ideiglenes = {rs.getString(2), rs.getString(3), rs.getString(8), rs.getString(9),rs.getString(12),rs.getString(13),"",""};
                        adatok.add(ideiglenes);
                        modell.addRow(new Object[]{rs.getString(2), rs.getString(3), rs.getString(8), rs.getString(9),rs.getString(12),rs.getString(13),"",""});
                    }
                        
                }
            }
            table.setModel(modell);
            int doboz = table.getRowCount();
            int termek = 0;
            for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
            {
                termek += Integer.parseInt(table.getValueAt(szamlalo, 3).toString());
            }
            dobozdb_mezo.setText(String.valueOf(doboz));
            termekdb_mezo.setText(String.valueOf(termek));
            con.close();
            conn2.close();
            Foablak.frame.setCursor(null);             
        }           
        catch(Exception e1)
        { 
            System.out.println(e1);
            e1.printStackTrace();
            String hibauzenet = e1.toString();
            Email hibakuldes = new Email();
            hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
            JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                 //kiírja a hibaüzenetet
        }  
    }
    
    public void cikkszamok()
    {
        try
        {
            Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            Class.forName("oracle.jdbc.OracleDriver");  //.driver
                                
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@IFSORA.IFS.videoton.hu:1521/IFSPROD","ZKOVACS","ZKOVACS");                                      
            Statement stmt = con.createStatement();   
            
            ResultSet rs = stmt.executeQuery("select \r\n"
                    + "ip.description Cikk_megnevezes\r\n"
                    + "from ifsapp.inventory_part ip, ifsapp.INVENTORY_PART_IN_STOCK ipis, ifsapp.INVENTORY_PART_BARCODE cpb \r\n"
                    + "where\r\n"
                    + "3=3\r\n"
                    + "and ipis.contract=ip.contract\r\n"
                    + "and ipis.part_no=ip.part_no\r\n"
                    + "and cpb.part_no = ipis.part_no\r\n"
                    + "and to_char( cpb.barcode_id) = ipis.waiv_dev_rej_no \r\n"
                    + "and cpb.lot_batch_no = ipis.lot_batch_no\r\n"
                    + "and cpb.eng_chg_level = ipis.eng_chg_level\r\n"
                    + "and cpb.configuration_id = ipis.configuration_id\r\n"
                    + "and cpb.serial_no = ipis.serial_no\r\n"
                    + "and cpb.activity_seq = ipis.activity_seq\r\n"
                    + "and ip.contract = 'VEAS'\r\n"
                    + "\r\n"
                    + "and ip.second_commodity = 'VAVM'\r\n"
                    + "and ip.part_product_code = '1'\r\n"
                    + "and ipis.qty_onhand>0\r\n"
                    + "group by ip.description");
            ArrayList<String> koztes = new ArrayList<String>();
            while(rs.next())
            { 
                koztes.add(rs.getString(1));
            }
            cikkszamok = new String[koztes.size() +1];
            cikkszamok[0] = "";
            for(int szamlalo = 0; szamlalo < koztes.size(); szamlalo++)
            {
                cikkszamok[szamlalo +1] = koztes.get(szamlalo);
            }
            table.setModel(modell);
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
            JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                 //kiírja a hibaüzenetet
        }  
    }
    
    class Cikkvalaszto implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                                                //egér mutató változtatása munka a háttérbenre
                String valasztott = String.valueOf(termek_box.getSelectedItem());
                if (modell.getRowCount() > 0) {
                    for (int i = modell.getRowCount() - 1; i > -1; i--) {
                        modell.removeRow(i);
                    }
                }
                int termek = 0;
                for(int szamlalo = 0; szamlalo < adatok.size(); szamlalo++)
                {
                    if(adatok.get(szamlalo)[1].equals(valasztott))
                    {
                        termek += Integer.parseInt(adatok.get(szamlalo)[3]);
                        modell.addRow(new Object[]{adatok.get(szamlalo)[0],adatok.get(szamlalo)[1],adatok.get(szamlalo)[2],adatok.get(szamlalo)[3],adatok.get(szamlalo)[4],adatok.get(szamlalo)[5],adatok.get(szamlalo)[6],adatok.get(szamlalo)[7]});
                    }
                }
                table.setModel(modell);
                termekdb_mezo.setText(String.valueOf(termek));
                dobozdb_mezo.setText(String.valueOf(table.getRowCount()));
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
    
    class Excel implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                                                //egér mutató változtatása munka a háttérbenre
                Connection conn = null;
                Statement stmt = null;
                DataTable datatable = new DataTable();
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
                ResultSet resultSet = null;
                Workbook workbook = new Workbook();
                Worksheet sheet = workbook.getWorksheets().get(0);
                sheet.getRange().get("A" + 1).setText("ID");
                JdbcAdapter jdbcAdapter = new JdbcAdapter();
                String tipus = "";
                String[] koztes2 = raklapok.get(0).split(";");
                if(koztes2[0].contains("FB7530"))
                {
                    tipus = "FB7530";
                }
                else if(koztes2[0].contains("FD301"))
                {
                    tipus = "FD301";
                }
                else if(koztes2[0].contains("FD302"))
                {
                    tipus = "FD302";
                }
                else if(koztes2[0].contains("FR1200"))
                {
                    tipus = "FR1200";
                }
                else if(koztes2[0].contains("FR2400"))
                {
                    tipus = "FR2400";
                }
                else
                {
                    tipus = "FR600";
                }
                ArrayList<String> raklapszamok = new ArrayList<String>();               
                for(int szamlalo = 0; szamlalo < raklapok.size();szamlalo++)
                {
                    String[] koztes = raklapok.get(szamlalo).split(";");
                    raklapszamok.add(koztes[1]);
                }
                for(int szamlalo = 0; szamlalo < raklapszamok.size();szamlalo++)
                {                              
                    String sajat = "select * from qualitydb.OQC_"+ tipus +" where raklapszam = '"+ raklapszamok.get(szamlalo) +"'";
                    stmt.execute(sajat);
                    resultSet = stmt.getResultSet();                                
                    jdbcAdapter.fillDataTable(datatable, resultSet);                               
                    sheet.insertDataTable(datatable, false, sheet.getLastRow()+1, 1);
                }
                sheet.getAutoFilters().setRange(sheet.getCellRange("A1:Z1"));
                sheet.getAllocatedRange().autoFitColumns();
                sheet.getAllocatedRange().autoFitRows();
                
                sheet.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                
                JFileChooser mentes_helye = new JFileChooser();
                mentes_helye.setCurrentDirectory(new File(System.getProperty("user.home") + "\\Desktop\\"));
                mentes_helye.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                mentes_helye.showOpenDialog(mentes_helye);
                File fajl = mentes_helye.getSelectedFile();
                //System.out.println(fajl.getAbsolutePath());
                workbook.saveToFile(fajl.getAbsolutePath(), ExcelVersion.Version2016);
                resultSet.close();
                stmt.close();
                conn.close();
                
                FileInputStream fileStream = new FileInputStream(fajl.getAbsolutePath());
                try (XSSFWorkbook workbook2 = new XSSFWorkbook(fileStream)) 
                {
                    for(int i = workbook2.getNumberOfSheets()-1; i>0 ;i--)
                    {    
                        workbook2.removeSheetAt(i); 
                    }      
                    FileOutputStream output = new FileOutputStream(fajl.getAbsolutePath());
                    workbook2.write(output);
                    output.close();
                }
                JOptionPane.showMessageDialog(null, "Mentés sikeres", "Info", 1);
                } 
                catch (SQLException e1) 
                {
                   e1.printStackTrace();
                   String hibauzenet = e1.toString();
                   Email hibakuldes = new Email();
                   hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                   JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                     //kivétel esetén kiírja a hibaüzenetet
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
                   catch (SQLException se) {se.printStackTrace();}
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
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                     //kivétel esetén kiírja a hibaüzenetet
            }
         }
    }
    
    class Kiajanlo implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                                                //egér mutató változtatása munka a háttérbenre
                SQA_SQL atir = new SQA_SQL();
                String db = "";
                if(String.valueOf(termek_box.getSelectedItem()).contains("FB7530"))
                {
                    db = "OQC_FB7530";
                }
                if(String.valueOf(termek_box.getSelectedItem()).contains("FD302"))
                {
                    db = "OQC_FD302";
                }
                if(String.valueOf(termek_box.getSelectedItem()).contains("FR1200"))
                {
                    db = "OQC_FR1200";
                }
                if(String.valueOf(termek_box.getSelectedItem()).contains("FR2400"))
                {
                    db = "OQC_FR2400";
                }
                if(String.valueOf(termek_box.getSelectedItem()).contains("FR600"))
                {
                    db = "OQC_FR600";
                }
                
                for(int szamlalo = 0; szamlalo < table_3.getRowCount(); szamlalo++)
                {
                    String sql = "Update qualitydb."+ db +" set Kiajanlva = 'Igen' where Raklapszam = '"+ table_3.getValueAt(szamlalo, 0).toString() +"'";
                    atir.mindenes(sql);
                }
                for (int i = modell3.getRowCount() - 1; i > -1; i--) {
                    modell3.removeRow(i);
                }
                table_3.setModel(modell3);
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
