import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Cursor;
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
import java.sql.Statement;
import java.util.Properties;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.spire.data.table.DataTable;
import com.spire.xls.ExcelVersion;
import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Nyilatkozatbekero extends JPanel {
    
    private JComboBox<String> cikkgyartok_box;
    private JComboBox<String> reach_box;
    private JComboBox<String> rohs_box;
    private JComboBox<String> cmrt_box;
    private JComboBox<String> reach2_box;
    private JComboBox<String> rohs2_box;
    private JComboBox<String> cmrt2_box;
    private JTextArea uzenet_mezo;
    private SQA_SQL beszallitok = new SQA_SQL();
    private JTable table;
    private DefaultTableModel modell;
    private String kodfejto = "\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\Kódfejtő.xlsx";
    private JTextField targy_mezo;

    /**
     * Create the panel.
     */
    public Nyilatkozatbekero() {
        setLayout(null);
        
        setBackground(Foablak.hatter_szine);
        
        JLabel lblNewLabel = new JLabel("Reach, Rohs, CMRT nyilatkozat bekérő");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setBounds(427, 49, 313, 14);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Cikk gyártók");
        lblNewLabel_1.setBounds(116, 177, 103, 14);
        add(lblNewLabel_1);
        
        String sql = "select ifsapp.MANUFACTURER_INFO_API.Get_Name(MANUFACTURER_NO)\r\n"
                + "from ifsapp.PART_MANUFACTURER   -- C_OPER_TRACY_EXT      C_OPER_TRACY_OVW\r\n"
                + "where 3 = 3\r\n"
                + "group by ifsapp.MANUFACTURER_INFO_API.Get_Name(MANUFACTURER_NO)\r\n"
                + "order by ifsapp.MANUFACTURER_INFO_API.Get_Name(MANUFACTURER_NO) asc";
        cikkgyartok_box = new JComboBox<String>(beszallitok.tombvissza(sql));                  //beszallitok.tombvissza(sql)
        cikkgyartok_box.setBounds(229, 173, 228, 22);
        cikkgyartok_box.addActionListener(new Hozzaad());
        add(cikkgyartok_box);
        
        uzenet_mezo = new JTextArea();
        uzenet_mezo.setLineWrap(true);
        uzenet_mezo.setWrapStyleWord(true);
        JScrollPane gorgeto = new JScrollPane(uzenet_mezo);
        gorgeto.setBounds(465, 266, 458, 159);
        add(gorgeto);
        
        String[] reach = {"-","0", "6", "7", "8", "9", "1", "2", "6, 9", "7, 9", "8, 9", "9, 9", "1, 9", "2, 9"};
        reach_box = new JComboBox<String>(reach);                            //reach
        reach_box.setBounds(529, 173, 111, 22);
        add(reach_box);
        
        String[] rohs = {"-","0", "2", "3", "3,9"};
        rohs_box = new JComboBox<String>(rohs);                             //rohs
        rohs_box.setBounds(665, 173, 111, 22);
        add(rohs_box);
        
        String[] cmrt = {"-","0", "60", "62", "63", "64", "69", "70", "72", "73", "74", "79", "80", "84", "85", "99"};
        cmrt_box = new JComboBox<String>(cmrt);                             //cmrt
        cmrt_box.setBounds(812, 173, 111, 22);
        add(cmrt_box);
        
        JLabel lblNewLabel_2 = new JLabel("Reach");
        lblNewLabel_2.setBounds(562, 148, 46, 14);
        add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("Rohs");
        lblNewLabel_3.setBounds(698, 148, 46, 14);
        add(lblNewLabel_3);
        
        JLabel lblNewLabel_4 = new JLabel("CMRT");
        lblNewLabel_4.setBounds(843, 148, 46, 14);
        add(lblNewLabel_4);
        
        JButton kuldes_gomb = new JButton("Küldés");
        kuldes_gomb.addActionListener(new Email_kuldes());
        kuldes_gomb.setBounds(519, 478, 89, 23);
        add(kuldes_gomb);
        
        modell = new DefaultTableModel();
        modell.setColumnIdentifiers(new Object[]{"Címzettek listája"});
        table = new JTable();
        //table.setBounds(151, 266, 228, 159);
        table = new JTable(){
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
          };;
        table.setModel(modell);
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {     // to detect doble click events
                   JTable target = (JTable)me.getSource();
                   int row = target.getSelectedRow(); // select a row 
                   try
                   {
                       modell.removeRow(row);
                   }
                   catch (Exception e1) 
                   {              
                       e1.printStackTrace();
                       String hibauzenet = e1.toString();
                       Email hibakuldes = new Email();
                       hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", this.getClass().getSimpleName()+" "+ hibauzenet);
                       JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
                   }
                }    
             }
          });
        JScrollPane gorgeto2 = new JScrollPane(table);
        gorgeto2.setBounds(151, 266, 228, 159);
        add(gorgeto2);
        
        JLabel lblNewLabel_5 = new JLabel("Címzettek");
        lblNewLabel_5.setBounds(40, 271, 89, 14);
        add(lblNewLabel_5);
        
        targy_mezo = new JTextField();
        targy_mezo.setBounds(465, 241, 458, 20);
        add(targy_mezo);
        targy_mezo.setColumns(10);
        
        reach2_box = new JComboBox<String>(reach);                           //reach
        reach2_box.setBounds(529, 208, 111, 22);
        add(reach2_box);
        
        rohs2_box = new JComboBox<String>(rohs);                            //rohs
        rohs2_box.setBounds(665, 208, 111, 22);
        add(rohs2_box);
        
        cmrt2_box = new JComboBox<String>(cmrt);                            //cmrt
        cmrt2_box.setBounds(812, 208, 111, 22);
        add(cmrt2_box);

    }
    
    class Hozzaad implements ActionListener                                                                                             //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                                                //egér mutató változtatása munka a háttérbenre
                modell.addRow(new Object[]{String.valueOf(cikkgyartok_box.getSelectedItem())});
                table.setModel(modell);
                Foablak.frame.setCursor(null);                                                                                          //egér mutató alaphelyzetbe állítása
            } 
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", this.getClass().getSimpleName()+" "+ hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
    
    class Email_kuldes implements ActionListener                                                                                             //termék gomb megnyomáskor hívodik meg
    {
        @SuppressWarnings("resource")
        public void actionPerformed(ActionEvent e)
         {
            try
            {                               
                DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                Class.forName("oracle.jdbc.OracleDriver");  //.driver
                                    
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@IFSORA.IFS.videoton.hu:1521/IFSPROD","ZKOVACS","ZKOVACS");                                      
                Statement stmt = con.createStatement();                      
                Workbook workbook = new Workbook();
                Workbook workbook2 = new Workbook();
                workbook.setVersion(ExcelVersion.Version2016);
                workbook.loadFromFile(kodfejto);
                Worksheet sheet = workbook.getWorksheets().get(0);
                DataTable datatable = new DataTable();
                datatable = sheet.exportDataTable(sheet.getAllocatedRange(), false, false );
                SQA_SQL gyarto_szama = new SQA_SQL();
                ResultSet rs = null;
                System.out.println("Kezdődik");
                for(int szamlalo = 0; szamlalo < table.getRowCount();szamlalo++)
                {
                    if(String.valueOf(reach_box.getSelectedItem()).equals("-")) {}
                    else
                    {
                        if(String.valueOf(reach2_box.getSelectedItem()).equals("-")) 
                        {
                            System.out.println("Kiválasztott reach "+String.valueOf(reach_box.getSelectedItem()));
                            String gyarto = gyarto_szama.beszallito("select MANUFACTURER_NO\r\n"
                                    + "from ifsapp.PART_MANUFACTURER\r\n"
                                    + "WHERE 3 = 3 and ifsapp.MANUFACTURER_INFO_API.Get_Name(MANUFACTURER_NO) = '"+ table.getValueAt(szamlalo, 0).toString() +"'\r\n"
                                    + "group by MANUFACTURER_NO");
                            String reach = "";                            
                            for(int szamlalo2 = 0; szamlalo2 < datatable.getRows().size();szamlalo2++)
                            {
                                if(datatable.getRows().get(szamlalo2).getString(0).equals(String.valueOf(reach_box.getSelectedItem())))
                                {
                                    reach = datatable.getRows().get(szamlalo2).getString(3);
                                }
                                
                            }
                            System.out.println("Reach "+reach);                             
                            rs = stmt.executeQuery("select part_no, ifsapp.Inventory_Part_API.Get_Description('VEAS',PART_NO), Manu_part_no\r\n"
                                    + "from ifsapp.part_manu_part_no_cfv nyilatkozatok\r\n"
                                    + "WHERE 3 = 3 and not nyilatkozatok.CF$_reach = '"+ reach +"'\r\n"
                                    + "and manufacturer_no = '"+ gyarto +"'");
                            Worksheet sheet2 = workbook2.getWorksheets().get(0);
                            int cellaszam = 1;
                            sheet2.getRange().get("A" + cellaszam).setText("VEAS Part No");
                            sheet2.getRange().get("B" + cellaszam).setText("Description");
                            sheet2.getRange().get("C" + cellaszam).setText("Manufacturer No");
                            cellaszam++;
                            int van = 0;
                            while(rs.next())
                            {
                                sheet2.getRange().get("A" + cellaszam).setText(rs.getString(1));
                                sheet2.getRange().get("B" + cellaszam).setText(rs.getString(2));
                                sheet2.getRange().get("C" + cellaszam).setText(rs.getString(3));
                                cellaszam++;
                                van++;
                            }
                            if(van > 0)
                            {
                                sheet2.getAutoFilters().setRange(sheet2.getCellRange("A1:C1"));
                                sheet2.getAllocatedRange().autoFitColumns();
                                sheet2.getAllocatedRange().autoFitRows();
                                
                                sheet2.getCellRange("A1:C1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                                
                                String menteshelye = System.getProperty("user.home") + "\\Desktop\\Cikkszámok nyilatkozathoz\\"+ table.getValueAt(szamlalo, 0).toString() +" Reach.xlsx";
                                workbook2.saveToFile(menteshelye, ExcelVersion.Version2016);
                                
                                
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
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(null, "Az alábbi gyártónak nincs a beállított Reach értéktől eltérő cikkszáma:"+ table.getValueAt(szamlalo, 0).toString() , "Info", 1);
                            }
                        }
                        else
                        {
                            String gyarto = gyarto_szama.beszallito("select MANUFACTURER_NO\r\n"
                                    + "from ifsapp.PART_MANUFACTURER\r\n"
                                    + "WHERE 3 = 3 and ifsapp.MANUFACTURER_INFO_API.Get_Name(MANUFACTURER_NO) = '"+ table.getValueAt(szamlalo, 0).toString() +"'\r\n"
                                    + "group by MANUFACTURER_NO");
                            String reach = "";
                            String reach2 = "";
                            for(int szamlalo2 = 0; szamlalo2 < datatable.getRows().size();szamlalo2++)
                            {
                                if(datatable.getRows().get(szamlalo2).getString(0).equals(String.valueOf(reach_box.getSelectedItem())))
                                {
                                    reach = datatable.getRows().get(szamlalo2).getString(3);
                                }
                                if(datatable.getRows().get(szamlalo2).getString(0).equals(String.valueOf(reach2_box.getSelectedItem())))
                                {
                                    reach2 = datatable.getRows().get(szamlalo2).getString(3);
                                }
                            }
                            System.out.println("Reach "+reach);
                            System.out.println("Reach2 "+reach2);
                            rs = stmt.executeQuery("select part_no, ifsapp.Inventory_Part_API.Get_Description('VEAS',PART_NO), Manu_part_no\r\n"
                                    + "from ifsapp.part_manu_part_no_cfv nyilatkozatok\r\n"
                                    + "WHERE 3 = 3 and not nyilatkozatok.CF$_reach = '"+ reach +"' and not nyilatkozatok.CF$_reach = '"+ reach2 +"'\r\n"
                                    + "and manufacturer_no = '"+ gyarto +"'");
                            Worksheet sheet2 = workbook2.getWorksheets().get(0);
                            int cellaszam = 1;
                            sheet2.getRange().get("A" + cellaszam).setText("VEAS Part No");
                            sheet2.getRange().get("B" + cellaszam).setText("Description");
                            sheet2.getRange().get("C" + cellaszam).setText("Manufacturer No");
                            cellaszam++;
                            int van = 0;
                            while(rs.next())
                            {
                                sheet2.getRange().get("A" + cellaszam).setText(rs.getString(1));
                                sheet2.getRange().get("B" + cellaszam).setText(rs.getString(2));
                                sheet2.getRange().get("C" + cellaszam).setText(rs.getString(3));
                                cellaszam++;
                                van++;
                            }
                            if(van > 0)
                            {
                                sheet2.getAutoFilters().setRange(sheet2.getCellRange("A1:C1"));
                                sheet2.getAllocatedRange().autoFitColumns();
                                sheet2.getAllocatedRange().autoFitRows();
                                
                                sheet2.getCellRange("A1:C1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                                
                                String menteshelye = System.getProperty("user.home") + "\\Desktop\\Cikkszámok nyilatkozathoz\\"+ table.getValueAt(szamlalo, 0).toString() +" Reach.xlsx";
                                workbook2.saveToFile(menteshelye, ExcelVersion.Version2016);
                                
                                
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
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(null, "Az alábbi gyártónak nincs a beállított Reach értéktől eltérő cikkszáma:"+ table.getValueAt(szamlalo, 0).toString() , "Info", 1);
                            }
                        }
                    }
                    
                    if(String.valueOf(rohs_box.getSelectedItem()).equals("-")) {}
                    else
                    {
                        if(String.valueOf(rohs2_box.getSelectedItem()).equals("-")) 
                        {
                            String gyarto = gyarto_szama.beszallito("select MANUFACTURER_NO\r\n"
                                    + "from ifsapp.PART_MANUFACTURER\r\n"
                                    + "WHERE 3 = 3 and ifsapp.MANUFACTURER_INFO_API.Get_Name(MANUFACTURER_NO) = '"+ table.getValueAt(szamlalo, 0).toString() +"'\r\n"
                                    + "group by MANUFACTURER_NO");
                            String rohs = "";
                            for(int szamlalo2 = 0; szamlalo2 < datatable.getRows().size();szamlalo2++)
                            {
                                if(datatable.getRows().get(szamlalo2).getString(0).equals(String.valueOf(rohs_box.getSelectedItem())))
                                {
                                    rohs = datatable.getRows().get(szamlalo2).getString(3);
                                }
                            }
                            System.out.println("Rohs "+rohs);
                            rs = stmt.executeQuery("select part_no, ifsapp.Inventory_Part_API.Get_Description('VEAS',PART_NO), Manu_part_no\r\n"
                                    + "from ifsapp.part_manu_part_no_cfv nyilatkozatok\r\n"
                                    + "WHERE 3 = 3 and not nyilatkozatok.CF$_reach = '"+ rohs +"'\r\n"
                                    + "and manufacturer_no = '"+ gyarto +"'");
                            Worksheet sheet2 = workbook2.getWorksheets().get(0);
                            int cellaszam = 1;
                            sheet2.getRange().get("A" + cellaszam).setText("VEAS Part No");
                            sheet2.getRange().get("B" + cellaszam).setText("Description");
                            sheet2.getRange().get("C" + cellaszam).setText("Manufacturer No");
                            cellaszam++;
                            int van = 0;
                            while(rs.next())
                            {
                                System.out.println(rs.getString(1) +" "+ rs.getString(2) +" "+ rs.getString(3));
                                sheet2.getRange().get("A" + cellaszam).setText(rs.getString(1));
                                sheet2.getRange().get("B" + cellaszam).setText(rs.getString(2));
                                sheet2.getRange().get("C" + cellaszam).setText(rs.getString(3));
                                cellaszam++;
                                van++;
                            }
                            if(van > 0)
                            {
                                sheet2.getAutoFilters().setRange(sheet2.getCellRange("A1:C1"));
                                sheet2.getAllocatedRange().autoFitColumns();
                                sheet2.getAllocatedRange().autoFitRows();
                                
                                sheet2.getCellRange("A1:C1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                                
                                String menteshelye = System.getProperty("user.home") + "\\Desktop\\Cikkszámok nyilatkozathoz\\"+ table.getValueAt(szamlalo, 0).toString() +" Rohs.xlsx";
                                workbook2.saveToFile(menteshelye, ExcelVersion.Version2016);
                                
                                
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
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(null, "Az alábbi gyártónak, nincs a beállított Rohs értéktől eltérő cikkszáma:"+ table.getValueAt(szamlalo, 0).toString() , "Info", 1);
                            }
                        }
                        else
                        {
                            String gyarto = gyarto_szama.beszallito("select MANUFACTURER_NO\r\n"
                                    + "from ifsapp.PART_MANUFACTURER\r\n"
                                    + "WHERE 3 = 3 and ifsapp.MANUFACTURER_INFO_API.Get_Name(MANUFACTURER_NO) = '"+ table.getValueAt(szamlalo, 0).toString() +"'\r\n"
                                    + "group by MANUFACTURER_NO");
                            String rohs = "";
                            String rohs2 = "";
                            for(int szamlalo2 = 0; szamlalo2 < datatable.getRows().size();szamlalo2++)
                            {
                                if(datatable.getRows().get(szamlalo2).getString(0).equals(String.valueOf(rohs_box.getSelectedItem())))
                                {
                                    rohs = datatable.getRows().get(szamlalo2).getString(3);
                                }
                                if(datatable.getRows().get(szamlalo2).getString(0).equals(String.valueOf(rohs2_box.getSelectedItem())))
                                {
                                    rohs2 = datatable.getRows().get(szamlalo2).getString(3);
                                }
                            }
                            System.out.println("Rohs "+rohs);
                            System.out.println("Rohs2 "+rohs2);
                            rs = stmt.executeQuery("select part_no, ifsapp.Inventory_Part_API.Get_Description('VEAS',PART_NO), Manu_part_no\r\n"
                                    + "from ifsapp.part_manu_part_no_cfv nyilatkozatok\r\n"
                                    + "WHERE 3 = 3 and not nyilatkozatok.CF$_reach = '"+ rohs +"' and not nyilatkozatok.CF$_reach = '"+ rohs2 +"'\r\n"
                                    + "and manufacturer_no = '"+ gyarto +"'");
                            Worksheet sheet2 = workbook2.getWorksheets().get(0);
                            int cellaszam = 1;
                            sheet2.getRange().get("A" + cellaszam).setText("VEAS Part No");
                            sheet2.getRange().get("B" + cellaszam).setText("Description");
                            sheet2.getRange().get("C" + cellaszam).setText("Manufacturer No");
                            cellaszam++;
                            int van = 0;
                            while(rs.next())
                            {
                                System.out.println(rs.getString(1) +" "+ rs.getString(2) +" "+ rs.getString(3));
                                sheet2.getRange().get("A" + cellaszam).setText(rs.getString(1));
                                sheet2.getRange().get("B" + cellaszam).setText(rs.getString(2));
                                sheet2.getRange().get("C" + cellaszam).setText(rs.getString(3));
                                cellaszam++;
                                van++;
                            }
                            if(van > 0)
                            {
                                sheet2.getAutoFilters().setRange(sheet2.getCellRange("A1:C1"));
                                sheet2.getAllocatedRange().autoFitColumns();
                                sheet2.getAllocatedRange().autoFitRows();
                                
                                sheet2.getCellRange("A1:C1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                                
                                String menteshelye = System.getProperty("user.home") + "\\Desktop\\Cikkszámok nyilatkozathoz\\"+ table.getValueAt(szamlalo, 0).toString() +" Rohs.xlsx";
                                workbook2.saveToFile(menteshelye, ExcelVersion.Version2016);
                                
                                
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
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(null, "Az alábbi gyártónak, nincs a beállított Rohs értéktől eltérő cikkszáma:"+ table.getValueAt(szamlalo, 0).toString() , "Info", 1);
                            }
                        }
                    }
                    
                    if(String.valueOf(cmrt_box.getSelectedItem()).equals("-")) {}
                    else
                    {
                        if(String.valueOf(cmrt_box.getSelectedItem()).equals("-")) 
                        {
                            String gyarto = gyarto_szama.beszallito("select MANUFACTURER_NO\r\n"
                                    + "from ifsapp.PART_MANUFACTURER\r\n"
                                    + "WHERE 3 = 3 and ifsapp.MANUFACTURER_INFO_API.Get_Name(MANUFACTURER_NO) = '"+ table.getValueAt(szamlalo, 0).toString() +"'\r\n"
                                    + "group by MANUFACTURER_NO");
                            String cmrt = "";
                            for(int szamlalo2 = 0; szamlalo2 < datatable.getRows().size();szamlalo2++)
                            {
                                if(datatable.getRows().get(szamlalo2).getString(0).equals(String.valueOf(cmrt_box.getSelectedItem())))
                                {
                                    cmrt = datatable.getRows().get(szamlalo2).getString(3);
                                }
                            }
                            System.out.println("CMRT "+cmrt);
                            rs = stmt.executeQuery("select part_no, ifsapp.Inventory_Part_API.Get_Description('VEAS',PART_NO), Manu_part_no\r\n"
                                    + "from ifsapp.part_manu_part_no_cfv nyilatkozatok\r\n"
                                    + "WHERE 3 = 3 and not nyilatkozatok.CF$_reach = '"+ cmrt +"'\r\n"
                                    + "and manufacturer_no = '"+ gyarto +"'");
                            Worksheet sheet2 = workbook2.getWorksheets().get(0);
                            int cellaszam = 1;
                            sheet2.getRange().get("A" + cellaszam).setText("VEAS Part No");
                            sheet2.getRange().get("B" + cellaszam).setText("Description");
                            sheet2.getRange().get("C" + cellaszam).setText("Manufacturer No");
                            cellaszam++;
                            int van = 0;
                            while(rs.next())
                            {
                                sheet2.getRange().get("A" + cellaszam).setText(rs.getString(1));
                                sheet2.getRange().get("B" + cellaszam).setText(rs.getString(2));
                                sheet2.getRange().get("C" + cellaszam).setText(rs.getString(3));
                                cellaszam++;
                                van++;
                            }
                            if(van > 0)
                            {
                                sheet2.getAutoFilters().setRange(sheet2.getCellRange("A1:C1"));
                                sheet2.getAllocatedRange().autoFitColumns();
                                sheet2.getAllocatedRange().autoFitRows();
                                
                                sheet2.getCellRange("A1:C1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                                
                                String menteshelye = System.getProperty("user.home") + "\\Desktop\\Cikkszámok nyilatkozathoz\\"+ table.getValueAt(szamlalo, 0).toString() +" CMRT.xlsx";
                                workbook2.saveToFile(menteshelye, ExcelVersion.Version2016);
                                
                                
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
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(null, "Az alábbi gyártónak, nincs a beállított CMRT értéktől eltérő cikkszáma:"+ table.getValueAt(szamlalo, 0).toString() , "Info", 1);
                            }
                        }
                        else
                        {
                            String gyarto = gyarto_szama.beszallito("select MANUFACTURER_NO\r\n"
                                    + "from ifsapp.PART_MANUFACTURER\r\n"
                                    + "WHERE 3 = 3 and ifsapp.MANUFACTURER_INFO_API.Get_Name(MANUFACTURER_NO) = '"+ table.getValueAt(szamlalo, 0).toString() +"'\r\n"
                                    + "group by MANUFACTURER_NO");
                            String cmrt = "";
                            String cmrt2 = "";
                            for(int szamlalo2 = 0; szamlalo2 < datatable.getRows().size();szamlalo2++)
                            {
                                if(datatable.getRows().get(szamlalo2).getString(0).equals(String.valueOf(cmrt_box.getSelectedItem())))
                                {
                                    cmrt = datatable.getRows().get(szamlalo2).getString(3);
                                }
                                if(datatable.getRows().get(szamlalo2).getString(0).equals(String.valueOf(cmrt2_box.getSelectedItem())))
                                {
                                    cmrt2 = datatable.getRows().get(szamlalo2).getString(3);
                                }
                            }
                            System.out.println("CMRT "+cmrt);
                            System.out.println("CMRT2 "+cmrt2);
                            rs = stmt.executeQuery("select part_no, ifsapp.Inventory_Part_API.Get_Description('VEAS',PART_NO), Manu_part_no\r\n"
                                    + "from ifsapp.part_manu_part_no_cfv nyilatkozatok\r\n"
                                    + "WHERE 3 = 3 and not nyilatkozatok.CF$_reach = '"+ cmrt +"' and not nyilatkozatok.CF$_reach = '"+ cmrt2 +"'\r\n"
                                    + "and manufacturer_no = '"+ gyarto +"'");
                            Worksheet sheet2 = workbook2.getWorksheets().get(0);
                            int cellaszam = 1;
                            sheet2.getRange().get("A" + cellaszam).setText("VEAS Part No");
                            sheet2.getRange().get("B" + cellaszam).setText("Description");
                            sheet2.getRange().get("C" + cellaszam).setText("Manufacturer No");
                            cellaszam++;
                            int van = 0;
                            while(rs.next())
                            {
                                sheet2.getRange().get("A" + cellaszam).setText(rs.getString(1));
                                sheet2.getRange().get("B" + cellaszam).setText(rs.getString(2));
                                sheet2.getRange().get("C" + cellaszam).setText(rs.getString(3));
                                cellaszam++;
                                van++;
                            }
                            if(van > 0)
                            {
                                sheet2.getAutoFilters().setRange(sheet2.getCellRange("A1:C1"));
                                sheet2.getAllocatedRange().autoFitColumns();
                                sheet2.getAllocatedRange().autoFitRows();
                                
                                sheet2.getCellRange("A1:C1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                                
                                String menteshelye = System.getProperty("user.home") + "\\Desktop\\Cikkszámok nyilatkozathoz\\"+ table.getValueAt(szamlalo, 0).toString() +" CMRT.xlsx";
                                workbook2.saveToFile(menteshelye, ExcelVersion.Version2016);
                                
                                
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
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(null, "Az alábbi gyártónak, nincs a beállított CMRT értéktől eltérő cikkszáma:"+ table.getValueAt(szamlalo, 0).toString() , "Info", 1);
                            }
                        }
                    }
                    
                    File fajl = new File(System.getProperty("user.home") + "\\Desktop\\Cikkszámok nyilatkozathoz\\");
                    if(fajl.exists()) {}
                    else
                    {
                        fajl.mkdir();
                    }
                    File[] torlendofajlok = fajl.listFiles();
                    
                    if(torlendofajlok.length == 1)
                    {
                        Properties props = new Properties(); //new Properties();     System.getProperties();
                        
                        props.put("mail.smtp.host", "172.20.22.254");                   //smtp.gmail.com                    //172.20.22.254 belső levelezés      //smtp-mail.outlook.com
                        props.put("mail.smtp.port", "25");                                      //587 TLS       //465  SSL          //25 Outlook                            //587
                        Session session = Session.getInstance(props, null);                                 //session létrehozűsa a megadott paraméterekkel
                        Message message = new MimeMessage(session);
                        message.setFrom(new InternetAddress("easqas@veas.videoton.hu"));                                  //feladó beállítása
                        message.setRecipients(Message.RecipientType.TO,
                            InternetAddress.parse("kovacs.zoltan@veas.videoton.hu"));                                                //címzett beállítása  ,jenei.erika@veas.videoton.hu,meszaros.agnes@veas.videoton.hu
                        message.setSubject(targy_mezo.getText());                                              //tárgy beállítása
                       
                        Multipart multipart = new MimeMultipart();                                          //csatoló osztály példányosítása
                       
                        MimeBodyPart textPart = new MimeBodyPart();                                         //levél szövegények osztály példányosítása
                        MimeBodyPart attachmentPart = new MimeBodyPart();
                        attachmentPart.attachFile(torlendofajlok[0]);
                        multipart.addBodyPart(attachmentPart);
                        textPart.setText(uzenet_mezo.getText());                                          //levél tartalmának csatolása
                        multipart.addBodyPart(textPart);                                            //csatolmány osztály           
                               
                        message.setContent(multipart);                                                  //message üzenethez mindent hozzáad
                        
                        Transport.send(message);                                                        //levél küldése
    
                        System.out.println("Done");                                                     //kiírja, ha lefutott minden rendben
                    }
                    if(torlendofajlok.length == 2)
                    {
                        Properties props = new Properties(); //new Properties();     System.getProperties();
                        
                        props.put("mail.smtp.host", "172.20.22.254");                   //smtp.gmail.com                    //172.20.22.254 belső levelezés      //smtp-mail.outlook.com
                        props.put("mail.smtp.port", "25");                                      //587 TLS       //465  SSL          //25 Outlook                            //587
                        Session session = Session.getInstance(props, null);                                 //session létrehozűsa a megadott paraméterekkel
                        Message message = new MimeMessage(session);
                        message.setFrom(new InternetAddress("easqas@veas.videoton.hu"));                                  //feladó beállítása
                        message.setRecipients(Message.RecipientType.TO,
                            InternetAddress.parse("kovacs.zoltan@veas.videoton.hu"));                                                //címzett beállítása
                        message.setSubject(targy_mezo.getText());                                              //tárgy beállítása
                       
                        Multipart multipart = new MimeMultipart();                                          //csatoló osztály példányosítása
                       
                        MimeBodyPart textPart = new MimeBodyPart();                                         //levél szövegények osztály példányosítása
                        MimeBodyPart attachmentPart = new MimeBodyPart();
                        MimeBodyPart attachmentPart2= new MimeBodyPart();
                        attachmentPart.attachFile(torlendofajlok[0]);
                        attachmentPart2.attachFile(torlendofajlok[1]);
                        multipart.addBodyPart(attachmentPart);
                        multipart.addBodyPart(attachmentPart2);
                        textPart.setText(uzenet_mezo.getText());                                          //levél tartalmának csatolása
                        multipart.addBodyPart(textPart);                                            //csatolmány osztály           
                               
                        message.setContent(multipart);                                                  //message üzenethez mindent hozzáad
                        
                        Transport.send(message);                                                        //levél küldése
    
                        System.out.println("Done");                                                     //kiírja, ha lefutott minden rendben
                    }
                    if(torlendofajlok.length == 3)
                    {
                        Properties props = new Properties(); //new Properties();     System.getProperties();
                        
                        props.put("mail.smtp.host", "172.20.22.254");                   //smtp.gmail.com                    //172.20.22.254 belső levelezés      //smtp-mail.outlook.com
                        props.put("mail.smtp.port", "25");                                      //587 TLS       //465  SSL          //25 Outlook                            //587
                        Session session = Session.getInstance(props, null);                                 //session létrehozűsa a megadott paraméterekkel
                        Message message = new MimeMessage(session);
                        message.setFrom(new InternetAddress("easqas@veas.videoton.hu"));                                  //feladó beállítása
                        message.setRecipients(Message.RecipientType.TO,
                            InternetAddress.parse("kovacs.zoltan@veas.videoton.hu"));                                                //címzett beállítása
                        message.setSubject(targy_mezo.getText());                                              //tárgy beállítása
                       
                        Multipart multipart = new MimeMultipart();                                          //csatoló osztály példányosítása
                       
                        MimeBodyPart textPart = new MimeBodyPart();                                         //levél szövegények osztály példányosítása
                        MimeBodyPart attachmentPart = new MimeBodyPart();
                        MimeBodyPart attachmentPart2= new MimeBodyPart();
                        MimeBodyPart attachmentPart3 = new MimeBodyPart();
                        attachmentPart.attachFile(torlendofajlok[0]);
                        attachmentPart2.attachFile(torlendofajlok[1]);
                        attachmentPart3.attachFile(torlendofajlok[2]);
                        multipart.addBodyPart(attachmentPart);
                        multipart.addBodyPart(attachmentPart2);
                        multipart.addBodyPart(attachmentPart3);
                        textPart.setText(uzenet_mezo.getText());                                          //levél tartalmának csatolása
                        multipart.addBodyPart(textPart);                                            //csatolmány osztály           
                               
                        message.setContent(multipart);                                                  //message üzenethez mindent hozzáad
                        
                        Transport.send(message);                                                        //levél küldése
    
                        System.out.println("Done");                                                     //kiírja, ha lefutott minden rendben
                    }
                    
                    
                    for(int szamlalo2 = 0; szamlalo2 < torlendofajlok.length; szamlalo2++)
                    {
                        torlendofajlok[szamlalo2].delete();
                    }
                    System.out.println("Fut a fő for");
                }
                /*
                sheet.getAutoFilters().setRange(sheet.getCellRange("A1:J1"));
                sheet.getAllocatedRange().autoFitColumns();
                sheet.getAllocatedRange().autoFitRows();
                
                sheet.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                
                workbook.saveToFile(menteshelye, ExcelVersion.Version2016);
                rs.close();
                stmt.close();
                con.close();
                
                FileInputStream fileStream = new FileInputStream(menteshelye);
                try (XSSFWorkbook workbook2 = new XSSFWorkbook(fileStream)) 
                {
                    for(int i = workbook2.getNumberOfSheets()-1; i>0 ;i--)
                    {    
                        workbook2.removeSheetAt(i); 
                    }      
                    FileOutputStream output = new FileOutputStream(menteshelye);
                    workbook2.write(output);
                    output.close();
                }   */
                rs.close();
                stmt.close();
                con.close();
                System.out.println("Vége");
                JOptionPane.showMessageDialog(null, "Kész! \n Email/Email-ek elküldve'", "Info", 1); 
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
}
