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
import javax.swing.JCheckBox;

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
    private JCheckBox reach_gomb;
    private JCheckBox rohs_gomb;
    private JCheckBox cmrt_gomb;
    private JCheckBox emrt_gomb;
    private final String reach_helye = "\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\Nyilatkozatok\\Q-B-007 REACH declaration for supplier 2.docx";
    private final String rohs_helye = "\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\Nyilatkozatok\\Q-B-006 RoHS declaration for supplier.docx";
    private final String cmrt_helye = "\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\Nyilatkozatok\\RMI_CMRT_6.4.xlsx";
    private final String emrt_helye = "\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\Nyilatkozatok\\RMI_EMRT_1.3.xlsx";
    private final String pfas_helye = "\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\Nyilatkozatok\\PFAS Supplier Declaration Form.docx";
    private JComboBox<String> pfas_box;
    private JCheckBox pfas_gomb;

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
        rohs_box = new JComboBox<String>(reach);                             //reach
        rohs_box.setBounds(665, 173, 111, 22);
        add(rohs_box);
        
        String[] cmrt = {"-","0", "60", "62", "63", "64", "69", "70", "72", "73", "74", "79", "80", "84", "85", "99"};
        cmrt_box = new JComboBox<String>(reach);                             //reach
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
        
        reach_gomb = new JCheckBox("Reach csatolása");
        reach_gomb.setBounds(962, 268, 126, 23);
        add(reach_gomb);
        
        rohs_gomb = new JCheckBox("Rohs csatolása");
        rohs_gomb.setBounds(962, 312, 126, 23);
        add(rohs_gomb);
        
        cmrt_gomb = new JCheckBox("CMRT csatolása");
        cmrt_gomb.setBounds(962, 349, 126, 23);
        add(cmrt_gomb);
        
        emrt_gomb = new JCheckBox("EMRT csatolása");
        emrt_gomb.setBounds(962, 392, 126, 23);
        add(emrt_gomb);
        
        String[] pfas = {"-","YES","NO"};
        pfas_box = new JComboBox<String>(pfas);                         //pfas
        pfas_box.setBounds(962, 173, 111, 22);
        add(pfas_box);
        
        JLabel lblNewLabel_6 = new JLabel("Pfas");
        lblNewLabel_6.setBounds(992, 148, 46, 14);
        add(lblNewLabel_6);
        
        pfas_gomb = new JCheckBox("Pfas csatolása");
        pfas_gomb.setBounds(962, 432, 126, 23);
        add(pfas_gomb);

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
                Statement stmt2 = con.createStatement();
                Workbook workbook = new Workbook();
                Workbook workbook2 = new Workbook();
                workbook.setVersion(ExcelVersion.Version2016);
                workbook.loadFromFile(kodfejto);
                Worksheet sheet = workbook.getWorksheets().get(0);
                DataTable datatable = new DataTable();
                datatable = sheet.exportDataTable(sheet.getAllocatedRange(), false, false );
                SQA_SQL gyarto_szama = new SQA_SQL();
                ResultSet rs = null;
                ResultSet rs2 = null;
                System.out.println("Kezdődik");
                int voltlevel = 0;
                for(int szamlalo = 0; szamlalo < table.getRowCount();szamlalo++)
                {
                    if(String.valueOf(reach_box.getSelectedItem()).equals("-")) {}
                    else
                    {
                        if(String.valueOf(reach2_box.getSelectedItem()).equals("-")) 
                        {
                            //System.out.println("Kiválasztott reach "+String.valueOf(reach_box.getSelectedItem()));
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
                                    break;
                                }
                                
                            }
                            //System.out.println("Reach "+reach);                             
                            rs = stmt.executeQuery("select part_no, ifsapp.Inventory_Part_API.Get_Description('VEAS',PART_NO), Manu_part_no\r\n"
                                    + "from ifsapp.part_manu_part_no_cfv nyilatkozatok\r\n"
                                    + "WHERE 3 = 3 and not nyilatkozatok.CF$_reach = '"+ reach +"'\r\n"
                                    + "and manufacturer_no = '"+ gyarto +"' or (manufacturer_no = '"+ gyarto +"' and nyilatkozatok.CF$_reach is null)");
                            Worksheet sheet2 = workbook2.getWorksheets().get(0);
                            int cellaszam = 1;
                            sheet2.getRange().get("A" + cellaszam).setText("VEAS Part No");
                            sheet2.getRange().get("B" + cellaszam).setText("Description");
                            sheet2.getRange().get("C" + cellaszam).setText("Manufacturer Part No");
                            cellaszam++;
                            int van = 0;
                            while(rs.next())
                            {
                                String cikkszam = rs.getString(1);
                                String megnevezes = rs.getString(2);
                                String gyartoicikk = rs.getString(3);
                                rs2 = stmt2.executeQuery("Select TO_DATE(CURRENT_DATE,'YYYY.MM.DD') - TO_DATE(rendeles.Utolso_rendeles,'YYYY.MM.DD')\r\n"
                                        + "from\r\n"
                                        + "(select\r\n"
                                        + "max(DATE_ENTERED) as Utolso_rendeles\r\n"
                                        + "from ifsapp.PURCHASE_ORDER_LINE_ALL\r\n"
                                        + "where\r\n"
                                        + "(OBJSTATE = (select ifsapp.PURCHASE_ORDER_LINE_API.FINITE_STATE_ENCODE__('Visszaigazolt') from dual) or \r\n"
                                        + "OBJSTATE = (select ifsapp.PURCHASE_ORDER_LINE_API.FINITE_STATE_ENCODE__('Átvéve') from dual) or \r\n"
                                        + "OBJSTATE = (select ifsapp.PURCHASE_ORDER_LINE_API.FINITE_STATE_ENCODE__('Beérkezett') from dual) or \r\n"
                                        + "OBJSTATE = (select ifsapp.PURCHASE_ORDER_LINE_API.FINITE_STATE_ENCODE__('Lezárt') from dual)) \r\n"
                                        + "and part_no = '"+ cikkszam +"') rendeles");
                                //System.out.println(cikkszam);
                                //System.out.println(megnevezes);
                                //System.out.println(gyartoicikk);
                                if(rs2.next())
                                {
                                    if(rs2.getString(1)!= null)
                                    {
                                        if(rs2.getInt(1) < 720)
                                        {
                                            sheet2.getRange().get("A" + cellaszam).setText(cikkszam);
                                            sheet2.getRange().get("B" + cellaszam).setText(megnevezes);
                                            sheet2.getRange().get("C" + cellaszam).setText(gyartoicikk);
                                            cellaszam++;
                                            van++;
                                        }
                                        else
                                        {
                                            System.out.println("Régi anyag "+ cikkszam +" reach");
                                        }
                                    }
                                    else
                                    {
                                        System.out.println("Még sosem rendeltünk "+ cikkszam +" reach");
                                    }
                                }                               
                            }
                            if(van > 0)
                            {
                                sheet2.getAutoFilters().setRange(sheet2.getCellRange("A1:C1"));
                                sheet2.getAllocatedRange().autoFitColumns();
                                sheet2.getAllocatedRange().autoFitRows();
                                
                                sheet2.getCellRange("A1:C1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                                
                                String menteshelye = System.getProperty("user.home") + "\\Desktop\\Cikkszámok nyilatkozathoz\\"+ table.getValueAt(szamlalo, 0).toString() +" REACH parts list.xlsx";
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
                            //System.out.println("Reach "+reach);
                            //System.out.println("Reach2 "+reach2);
                            rs = stmt.executeQuery("select part_no, ifsapp.Inventory_Part_API.Get_Description('VEAS',PART_NO), Manu_part_no\r\n"
                                    + "from ifsapp.part_manu_part_no_cfv nyilatkozatok\r\n"
                                    + "WHERE 3 = 3 and not nyilatkozatok.CF$_reach = '"+ reach +"' and not nyilatkozatok.CF$_reach = '"+ reach2 +"'\r\n"
                                    + "and manufacturer_no = '"+ gyarto +"' or (manufacturer_no = '"+ gyarto +"' and nyilatkozatok.CF$_reach is null)");
                            Worksheet sheet2 = workbook2.getWorksheets().get(0);
                            int cellaszam = 1;
                            sheet2.getRange().get("A" + cellaszam).setText("VEAS Part No");
                            sheet2.getRange().get("B" + cellaszam).setText("Description");
                            sheet2.getRange().get("C" + cellaszam).setText("Manufacturer Part No");
                            cellaszam++;
                            int van = 0;
                            while(rs.next())
                            {
                                String cikkszam = rs.getString(1);
                                String megnevezes = rs.getString(2);
                                String gyartoicikk = rs.getString(3);
                                rs2 = stmt2.executeQuery("Select TO_DATE(CURRENT_DATE,'YYYY.MM.DD') - TO_DATE(rendeles.Utolso_rendeles,'YYYY.MM.DD')\r\n"
                                        + "from\r\n"
                                        + "(select\r\n"
                                        + "max(DATE_ENTERED) as Utolso_rendeles\r\n"
                                        + "from ifsapp.PURCHASE_ORDER_LINE_ALL\r\n"
                                        + "where\r\n"
                                        + "(OBJSTATE = (select ifsapp.PURCHASE_ORDER_LINE_API.FINITE_STATE_ENCODE__('Visszaigazolt') from dual) or \r\n"
                                        + "OBJSTATE = (select ifsapp.PURCHASE_ORDER_LINE_API.FINITE_STATE_ENCODE__('Átvéve') from dual) or \r\n"
                                        + "OBJSTATE = (select ifsapp.PURCHASE_ORDER_LINE_API.FINITE_STATE_ENCODE__('Beérkezett') from dual) or \r\n"
                                        + "OBJSTATE = (select ifsapp.PURCHASE_ORDER_LINE_API.FINITE_STATE_ENCODE__('Lezárt') from dual)) \r\n"
                                        + "and part_no = '"+ cikkszam +"') rendeles");
                                if(rs2.next())
                                {
                                    if(rs2.getString(1)!= null)
                                    {
                                        if(rs2.getInt(1) < 720)
                                        {
                                            sheet2.getRange().get("A" + cellaszam).setText(cikkszam);
                                            sheet2.getRange().get("B" + cellaszam).setText(megnevezes);
                                            sheet2.getRange().get("C" + cellaszam).setText(gyartoicikk);
                                            cellaszam++;
                                            van++;
                                        }
                                        else
                                        {
                                            System.out.println("Régi anyag "+ cikkszam +" reach");
                                        }
                                    }
                                    else
                                    {
                                        System.out.println("Még sosem rendeltünk "+ cikkszam +" reach");
                                    }
                                }
                            }
                            if(van > 0)
                            {
                                sheet2.getAutoFilters().setRange(sheet2.getCellRange("A1:C1"));
                                sheet2.getAllocatedRange().autoFitColumns();
                                sheet2.getAllocatedRange().autoFitRows();
                                
                                sheet2.getCellRange("A1:C1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                                
                                String menteshelye = System.getProperty("user.home") + "\\Desktop\\Cikkszámok nyilatkozathoz\\"+ table.getValueAt(szamlalo, 0).toString() +" REACH parts list.xlsx";
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
                            //System.out.println("Rohs "+rohs);
                            rs = stmt.executeQuery("select part_no, ifsapp.Inventory_Part_API.Get_Description('VEAS',PART_NO), Manu_part_no\r\n"
                                    + "from ifsapp.part_manu_part_no_cfv nyilatkozatok\r\n"
                                    + "WHERE 3 = 3 and not nyilatkozatok.CF$_Rohs = '"+ rohs +"'\r\n"
                                    + "and manufacturer_no = '"+ gyarto +"' or (manufacturer_no = '"+ gyarto +"' and nyilatkozatok.CF$_Rohs is null)");
                            Worksheet sheet2 = workbook2.getWorksheets().get(0);
                            int cellaszam = 1;
                            sheet2.getRange().get("A" + cellaszam).setText("VEAS Part No");
                            sheet2.getRange().get("B" + cellaszam).setText("Description");
                            sheet2.getRange().get("C" + cellaszam).setText("Manufacturer Part No");
                            cellaszam++;
                            int van = 0;
                            while(rs.next())
                            {
                                String cikkszam = rs.getString(1);
                                String megnevezes = rs.getString(2);
                                String gyartoicikk = rs.getString(3);
                                rs2 = stmt2.executeQuery("Select TO_DATE(CURRENT_DATE,'YYYY.MM.DD') - TO_DATE(rendeles.Utolso_rendeles,'YYYY.MM.DD')\r\n"
                                        + "from\r\n"
                                        + "(select\r\n"
                                        + "max(DATE_ENTERED) as Utolso_rendeles\r\n"
                                        + "from ifsapp.PURCHASE_ORDER_LINE_ALL\r\n"
                                        + "where\r\n"
                                        + "(OBJSTATE = (select ifsapp.PURCHASE_ORDER_LINE_API.FINITE_STATE_ENCODE__('Visszaigazolt') from dual) or \r\n"
                                        + "OBJSTATE = (select ifsapp.PURCHASE_ORDER_LINE_API.FINITE_STATE_ENCODE__('Átvéve') from dual) or \r\n"
                                        + "OBJSTATE = (select ifsapp.PURCHASE_ORDER_LINE_API.FINITE_STATE_ENCODE__('Beérkezett') from dual) or \r\n"
                                        + "OBJSTATE = (select ifsapp.PURCHASE_ORDER_LINE_API.FINITE_STATE_ENCODE__('Lezárt') from dual)) \r\n"
                                        + "and part_no = '"+ cikkszam +"') rendeles");
                                if(rs2.next())
                                {
                                    if(rs2.getString(1)!= null)
                                    {
                                        if(rs2.getInt(1) < 720)
                                        {
                                            sheet2.getRange().get("A" + cellaszam).setText(cikkszam);
                                            sheet2.getRange().get("B" + cellaszam).setText(megnevezes);
                                            sheet2.getRange().get("C" + cellaszam).setText(gyartoicikk);
                                            cellaszam++;
                                            van++;
                                        }
                                        else
                                        {
                                            System.out.println("Régi anyag "+ cikkszam +" rohs");
                                        }
                                    }
                                    else
                                    {
                                        System.out.println("Még sosem rendeltünk "+ cikkszam +" rohs");
                                    }
                                }
                            }
                            if(van > 0)
                            {
                                sheet2.getAutoFilters().setRange(sheet2.getCellRange("A1:C1"));
                                sheet2.getAllocatedRange().autoFitColumns();
                                sheet2.getAllocatedRange().autoFitRows();
                                
                                sheet2.getCellRange("A1:C1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                                
                                String menteshelye = System.getProperty("user.home") + "\\Desktop\\Cikkszámok nyilatkozathoz\\"+ table.getValueAt(szamlalo, 0).toString() +" ROHS parts list.xlsx";
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
                            //System.out.println("Rohs "+rohs);
                            //System.out.println("Rohs2 "+rohs2);
                            rs = stmt.executeQuery("select part_no, ifsapp.Inventory_Part_API.Get_Description('VEAS',PART_NO), Manu_part_no\r\n"
                                    + "from ifsapp.part_manu_part_no_cfv nyilatkozatok\r\n"
                                    + "WHERE 3 = 3 and not nyilatkozatok.CF$_Rohs = '"+ rohs +"' and not nyilatkozatok.CF$_Rohs = '"+ rohs2 +"'\r\n"
                                    + "and manufacturer_no = '"+ gyarto +"' or (manufacturer_no = '"+ gyarto +"' and nyilatkozatok.CF$_Rohs is null)");
                            Worksheet sheet2 = workbook2.getWorksheets().get(0);
                            int cellaszam = 1;
                            sheet2.getRange().get("A" + cellaszam).setText("VEAS Part No");
                            sheet2.getRange().get("B" + cellaszam).setText("Description");
                            sheet2.getRange().get("C" + cellaszam).setText("Manufacturer Part No");
                            cellaszam++;
                            int van = 0;
                            while(rs.next())
                            {
                                String cikkszam = rs.getString(1);
                                String megnevezes = rs.getString(2);
                                String gyartoicikk = rs.getString(3);
                                rs2 = stmt2.executeQuery("Select TO_DATE(CURRENT_DATE,'YYYY.MM.DD') - TO_DATE(rendeles.Utolso_rendeles,'YYYY.MM.DD')\r\n"
                                        + "from\r\n"
                                        + "(select\r\n"
                                        + "max(DATE_ENTERED) as Utolso_rendeles\r\n"
                                        + "from ifsapp.PURCHASE_ORDER_LINE_ALL\r\n"
                                        + "where\r\n"
                                        + "(OBJSTATE = (select ifsapp.PURCHASE_ORDER_LINE_API.FINITE_STATE_ENCODE__('Visszaigazolt') from dual) or \r\n"
                                        + "OBJSTATE = (select ifsapp.PURCHASE_ORDER_LINE_API.FINITE_STATE_ENCODE__('Átvéve') from dual) or \r\n"
                                        + "OBJSTATE = (select ifsapp.PURCHASE_ORDER_LINE_API.FINITE_STATE_ENCODE__('Beérkezett') from dual) or \r\n"
                                        + "OBJSTATE = (select ifsapp.PURCHASE_ORDER_LINE_API.FINITE_STATE_ENCODE__('Lezárt') from dual)) \r\n"
                                        + "and part_no = '"+ cikkszam +"') rendeles");
                                if(rs2.next())
                                {
                                    if(rs2.getString(1)!= null)
                                    {
                                        if(rs2.getInt(1) < 720)
                                        {
                                            sheet2.getRange().get("A" + cellaszam).setText(cikkszam);
                                            sheet2.getRange().get("B" + cellaszam).setText(megnevezes);
                                            sheet2.getRange().get("C" + cellaszam).setText(gyartoicikk);
                                            cellaszam++;
                                            van++;
                                        }
                                        else
                                        {
                                            System.out.println("Régi anyag "+ cikkszam +" rohs");
                                        }
                                    }
                                    else
                                    {
                                        System.out.println("Még sosem rendeltünk "+ cikkszam +" rohs");
                                    }
                                }
                            }
                            if(van > 0)
                            {
                                sheet2.getAutoFilters().setRange(sheet2.getCellRange("A1:C1"));
                                sheet2.getAllocatedRange().autoFitColumns();
                                sheet2.getAllocatedRange().autoFitRows();
                                
                                sheet2.getCellRange("A1:C1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                                
                                String menteshelye = System.getProperty("user.home") + "\\Desktop\\Cikkszámok nyilatkozathoz\\"+ table.getValueAt(szamlalo, 0).toString() +" ROHS parts list.xlsx";
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
                        if(String.valueOf(cmrt2_box.getSelectedItem()).equals("-")) 
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
                            //System.out.println("CMRT "+cmrt);
                            rs = stmt.executeQuery("select part_no, ifsapp.Inventory_Part_API.Get_Description('VEAS',PART_NO), Manu_part_no\r\n"
                                    + "from ifsapp.part_manu_part_no_cfv nyilatkozatok\r\n"
                                    + "WHERE 3 = 3 and not nyilatkozatok.CF$_cmrt = '"+ cmrt +"'\r\n"
                                    + "and manufacturer_no = '"+ gyarto +"' or (manufacturer_no = '"+ gyarto +"' and nyilatkozatok.CF$_cmrt is null)");
                            Worksheet sheet2 = workbook2.getWorksheets().get(0);
                            int cellaszam = 1;
                            sheet2.getRange().get("A" + cellaszam).setText("VEAS Part No");
                            sheet2.getRange().get("B" + cellaszam).setText("Description");
                            sheet2.getRange().get("C" + cellaszam).setText("Manufacturer Part No");
                            cellaszam++;
                            int van = 0;
                            while(rs.next())
                            {
                                String cikkszam = rs.getString(1);
                                String megnevezes = rs.getString(2);
                                String gyartoicikk = rs.getString(3);
                                rs2 = stmt2.executeQuery("Select TO_DATE(CURRENT_DATE,'YYYY.MM.DD') - TO_DATE(rendeles.Utolso_rendeles,'YYYY.MM.DD')\r\n"
                                        + "from\r\n"
                                        + "(select\r\n"
                                        + "max(DATE_ENTERED) as Utolso_rendeles\r\n"
                                        + "from ifsapp.PURCHASE_ORDER_LINE_ALL\r\n"
                                        + "where\r\n"
                                        + "(OBJSTATE = (select ifsapp.PURCHASE_ORDER_LINE_API.FINITE_STATE_ENCODE__('Visszaigazolt') from dual) or \r\n"
                                        + "OBJSTATE = (select ifsapp.PURCHASE_ORDER_LINE_API.FINITE_STATE_ENCODE__('Átvéve') from dual) or \r\n"
                                        + "OBJSTATE = (select ifsapp.PURCHASE_ORDER_LINE_API.FINITE_STATE_ENCODE__('Beérkezett') from dual) or \r\n"
                                        + "OBJSTATE = (select ifsapp.PURCHASE_ORDER_LINE_API.FINITE_STATE_ENCODE__('Lezárt') from dual)) \r\n"
                                        + "and part_no = '"+ cikkszam +"') rendeles");
                                if(rs2.next())
                                {
                                    if(rs2.getString(1)!= null)
                                    {
                                        if(rs2.getInt(1) < 720)
                                        {
                                            sheet2.getRange().get("A" + cellaszam).setText(cikkszam);
                                            sheet2.getRange().get("B" + cellaszam).setText(megnevezes);
                                            sheet2.getRange().get("C" + cellaszam).setText(gyartoicikk);
                                            cellaszam++;
                                            van++;
                                        }
                                        else
                                        {
                                            System.out.println("Régi anyag "+ cikkszam +" cmrt");
                                        }
                                    }
                                    else
                                    {
                                        System.out.println("Még sosem rendeltünk "+ cikkszam +" cmrt");
                                    }
                                }
                            }
                            if(van > 0)
                            {
                                sheet2.getAutoFilters().setRange(sheet2.getCellRange("A1:C1"));
                                sheet2.getAllocatedRange().autoFitColumns();
                                sheet2.getAllocatedRange().autoFitRows();
                                
                                sheet2.getCellRange("A1:C1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                                
                                String menteshelye = System.getProperty("user.home") + "\\Desktop\\Cikkszámok nyilatkozathoz\\"+ table.getValueAt(szamlalo, 0).toString() +" CMRT, EMRT parts list.xlsx";
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
                            //System.out.println("CMRT "+cmrt);
                            //System.out.println("CMRT2 "+cmrt2);
                            rs = stmt.executeQuery("select part_no, ifsapp.Inventory_Part_API.Get_Description('VEAS',PART_NO), Manu_part_no\r\n"
                                    + "from ifsapp.part_manu_part_no_cfv nyilatkozatok\r\n"
                                    + "WHERE 3 = 3 and not nyilatkozatok.CF$_cmrt = '"+ cmrt +"' and not nyilatkozatok.CF$_cmrt = '"+ cmrt2 +"'\r\n"
                                    + "and manufacturer_no = '"+ gyarto +"' or (manufacturer_no = '"+ gyarto +"' and nyilatkozatok.CF$_cmrt is null)");
                            Worksheet sheet2 = workbook2.getWorksheets().get(0);
                            int cellaszam = 1;
                            sheet2.getRange().get("A" + cellaszam).setText("VEAS Part No");
                            sheet2.getRange().get("B" + cellaszam).setText("Description");
                            sheet2.getRange().get("C" + cellaszam).setText("Manufacturer Part No");
                            cellaszam++;
                            int van = 0;
                            while(rs.next())
                            {
                                String cikkszam = rs.getString(1);
                                String megnevezes = rs.getString(2);
                                String gyartoicikk = rs.getString(3);
                                rs2 = stmt2.executeQuery("Select TO_DATE(CURRENT_DATE,'YYYY.MM.DD') - TO_DATE(rendeles.Utolso_rendeles,'YYYY.MM.DD')\r\n"
                                        + "from\r\n"
                                        + "(select\r\n"
                                        + "max(DATE_ENTERED) as Utolso_rendeles\r\n"
                                        + "from ifsapp.PURCHASE_ORDER_LINE_ALL\r\n"
                                        + "where\r\n"
                                        + "(OBJSTATE = (select ifsapp.PURCHASE_ORDER_LINE_API.FINITE_STATE_ENCODE__('Visszaigazolt') from dual) or \r\n"
                                        + "OBJSTATE = (select ifsapp.PURCHASE_ORDER_LINE_API.FINITE_STATE_ENCODE__('Átvéve') from dual) or \r\n"
                                        + "OBJSTATE = (select ifsapp.PURCHASE_ORDER_LINE_API.FINITE_STATE_ENCODE__('Beérkezett') from dual) or \r\n"
                                        + "OBJSTATE = (select ifsapp.PURCHASE_ORDER_LINE_API.FINITE_STATE_ENCODE__('Lezárt') from dual)) \r\n"
                                        + "and part_no = '"+ cikkszam +"') rendeles");
                                if(rs2.next())
                                {
                                    if(rs2.getString(1)!= null)
                                    {
                                        if(rs2.getInt(1) < 720)
                                        {
                                            sheet2.getRange().get("A" + cellaszam).setText(cikkszam);
                                            sheet2.getRange().get("B" + cellaszam).setText(megnevezes);
                                            sheet2.getRange().get("C" + cellaszam).setText(gyartoicikk);
                                            cellaszam++;
                                            van++;
                                        }
                                        else
                                        {
                                            System.out.println("Régi anyag "+ cikkszam +" cmrt");
                                        }
                                    }
                                    else
                                    {
                                        System.out.println("Még sosem rendeltünk "+ cikkszam +" cmrt");
                                    }
                                }
                            }
                            if(van > 0)
                            {
                                sheet2.getAutoFilters().setRange(sheet2.getCellRange("A1:C1"));
                                sheet2.getAllocatedRange().autoFitColumns();
                                sheet2.getAllocatedRange().autoFitRows();
                                
                                sheet2.getCellRange("A1:C1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                                
                                String menteshelye = System.getProperty("user.home") + "\\Desktop\\Cikkszámok nyilatkozathoz\\"+ table.getValueAt(szamlalo, 0).toString() +" CMRT, EMRT parts list.xlsx";
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
                    /////////////////////Pfas kezdődik
                    if(String.valueOf(pfas_box.getSelectedItem()).equals("-")) {}
                    else
                    {
                        String gyarto = gyarto_szama.beszallito("select MANUFACTURER_NO\r\n"
                                + "from ifsapp.PART_MANUFACTURER\r\n"
                                + "WHERE 3 = 3 and ifsapp.MANUFACTURER_INFO_API.Get_Name(MANUFACTURER_NO) = '"+ table.getValueAt(szamlalo, 0).toString() +"'\r\n"
                                + "group by MANUFACTURER_NO");
                        String pfas = "";
                        for(int szamlalo2 = 0; szamlalo2 < datatable.getRows().size();szamlalo2++)
                        {
                            if(datatable.getRows().get(szamlalo2).getString(0).equals(String.valueOf(pfas_box.getSelectedItem())))
                            {
                                pfas = datatable.getRows().get(szamlalo2).getString(3);
                            }
                        }
                        //System.out.println("PFAS "+pfas);
                        rs = stmt.executeQuery("select part_no, ifsapp.Inventory_Part_API.Get_Description('VEAS',PART_NO), Manu_part_no\r\n"
                                + "from ifsapp.part_manu_part_no_cfv nyilatkozatok\r\n"
                                + "WHERE 3 = 3 and not nyilatkozatok.CF$_pfas = '"+ pfas +"'\r\n"
                                + "and manufacturer_no = '"+ gyarto +"' or (manufacturer_no = '"+ gyarto +"' and nyilatkozatok.CF$_cmrt is null)");
                        Worksheet sheet2 = workbook2.getWorksheets().get(0);
                        int cellaszam = 1;
                        sheet2.getRange().get("A" + cellaszam).setText("VEAS Part No");
                        sheet2.getRange().get("B" + cellaszam).setText("Description");
                        sheet2.getRange().get("C" + cellaszam).setText("Manufacturer Part No");
                        cellaszam++;
                        int van = 0;
                        while(rs.next())
                        {
                            String cikkszam = rs.getString(1);
                            String megnevezes = rs.getString(2);
                            String gyartoicikk = rs.getString(3);
                            rs2 = stmt2.executeQuery("Select TO_DATE(CURRENT_DATE,'YYYY.MM.DD') - TO_DATE(rendeles.Utolso_rendeles,'YYYY.MM.DD')\r\n"
                                    + "from\r\n"
                                    + "(select\r\n"
                                    + "max(DATE_ENTERED) as Utolso_rendeles\r\n"
                                    + "from ifsapp.PURCHASE_ORDER_LINE_ALL\r\n"
                                    + "where\r\n"
                                    + "(OBJSTATE = (select ifsapp.PURCHASE_ORDER_LINE_API.FINITE_STATE_ENCODE__('Visszaigazolt') from dual) or \r\n"
                                    + "OBJSTATE = (select ifsapp.PURCHASE_ORDER_LINE_API.FINITE_STATE_ENCODE__('Átvéve') from dual) or \r\n"
                                    + "OBJSTATE = (select ifsapp.PURCHASE_ORDER_LINE_API.FINITE_STATE_ENCODE__('Beérkezett') from dual) or \r\n"
                                    + "OBJSTATE = (select ifsapp.PURCHASE_ORDER_LINE_API.FINITE_STATE_ENCODE__('Lezárt') from dual)) \r\n"
                                    + "and part_no = '"+ cikkszam +"') rendeles");
                            if(rs2.next())
                            {
                                if(rs2.getString(1)!= null)
                                {
                                    if(rs2.getInt(1) < 720)
                                    {
                                        sheet2.getRange().get("A" + cellaszam).setText(cikkszam);
                                        sheet2.getRange().get("B" + cellaszam).setText(megnevezes);
                                        sheet2.getRange().get("C" + cellaszam).setText(gyartoicikk);
                                        cellaszam++;
                                        van++;
                                    }
                                    else
                                    {
                                        System.out.println("Régi anyag "+ cikkszam +" pfas");
                                    }
                                }
                                else
                                {
                                    System.out.println("Még sosem rendeltünk "+ cikkszam +" pfas");
                                }
                            }
                        }
                        if(van > 0)
                        {
                            sheet2.getAutoFilters().setRange(sheet2.getCellRange("A1:C1"));
                            sheet2.getAllocatedRange().autoFitColumns();
                            sheet2.getAllocatedRange().autoFitRows();
                            
                            sheet2.getCellRange("A1:C1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                            
                            String menteshelye = System.getProperty("user.home") + "\\Desktop\\Cikkszámok nyilatkozathoz\\"+ table.getValueAt(szamlalo, 0).toString() +" PFAS parts list.xlsx";
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
                            JOptionPane.showMessageDialog(null, "Az alábbi gyártónak, nincs a beállított PFAS értéktől eltérő cikkszáma:"+ table.getValueAt(szamlalo, 0).toString() , "Info", 1);
                        }
                    }
                    
                    
                    File fajl = new File(System.getProperty("user.home") + "\\Desktop\\Cikkszámok nyilatkozathoz\\");
                    if(fajl.exists()) {}
                    else
                    {
                        fajl.mkdir();
                    }
                    File[] torlendofajlok = fajl.listFiles();
                    
                    SQA_SQL cimzett = new SQA_SQL();
                    String emailcim = null;
                    
                    if(cimzett.tombvissza_sajat("select email from qualitydb.Kontaktlista where Gyarto = '"+ table.getValueAt(szamlalo, 0).toString() +"'").length == 0){System.out.println("nulla hossz");}
                    else
                    {
                        emailcim = cimzett.tombvissza_sajat("select email from qualitydb.Kontaktlista where Gyarto = '"+ table.getValueAt(szamlalo, 0).toString() +"'")[0];
                        System.out.println("Van iylen emailcím");
                    }
                    
                    
                    if(torlendofajlok.length == 1)
                    {
                        if(emailcim == null)
                        {
                            for(int szamlalo2 = 0; szamlalo2 < torlendofajlok.length; szamlalo2++)
                            {
                                Workbook workbook4 = new Workbook();
                                String menteshelye = System.getProperty("user.home") + "\\Desktop\\El nem küldött fájlok\\"+torlendofajlok[szamlalo2].getName();
                                workbook4.loadFromFile(System.getProperty("user.home") + "\\Desktop\\Cikkszámok nyilatkozathoz\\"+torlendofajlok[szamlalo2].getName());                               
                                workbook4.saveToFile(menteshelye, ExcelVersion.Version2016);
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
                                torlendofajlok[szamlalo2].delete();
                            }
                            JOptionPane.showMessageDialog(null, "Az alábbi gyártó nincs felvéve a kontaktlistára: "+ table.getValueAt(szamlalo, 0).toString() , "Info", 1);
                        }
                        else if(emailcim.contains("@"))
                        {
                            Properties props = new Properties(); //new Properties();     System.getProperties();                        
                            props.put("mail.smtp.host", "172.20.22.254");                   //smtp.gmail.com                    //172.20.22.254 belső levelezés      //smtp-mail.outlook.com
                            props.put("mail.smtp.port", "25");                                      //587 TLS       //465  SSL          //25 Outlook                            //587
                            Session session = Session.getInstance(props, null);                                 //session létrehozűsa a megadott paraméterekkel
                            Message message = new MimeMessage(session);
                            message.setFrom(new InternetAddress("easqas@veas.videoton.hu"));                                  //feladó beállítása
                            if(System.getProperty("user.name").equals("meszaros.agnes"))          //tatai.mihaly
                            {
                                message.setFrom(new InternetAddress("meszaros.agnes@veas.videoton.hu"));
                                message.setRecipients(Message.RecipientType.CC,
                                        InternetAddress.parse("jenei.erika@veas.videoton.hu,meszaros.agnes@veas.videoton.hu"));
                            }
                            if(System.getProperty("user.name").equals("jenei.erika"))          //tatai.mihaly
                            {
                                message.setFrom(new InternetAddress("jenei.erika@veas.videoton.hu"));
                                message.setRecipients(Message.RecipientType.CC,
                                        InternetAddress.parse("jenei.erika@veas.videoton.hu,meszaros.agnes@veas.videoton.hu"));
                            }
                            if(System.getProperty("user.name").equals("kovacs.zoltan"))          //tatai.mihaly
                            {
                                message.setFrom(new InternetAddress("kovacs.zoltan@veas.videoton.hu"));
                                message.setRecipients(Message.RecipientType.CC,
                                        InternetAddress.parse("kovacs.zoltan@veas.videoton.hu"));
                            }
                            message.setRecipients(Message.RecipientType.TO,
                                InternetAddress.parse("kovacs.zoltan@veas.videoton.hu"));                                                //címzett beállítása  ,jenei.erika@veas.videoton.hu,meszaros.agnes@veas.videoton.hu                            
                            message.setSubject(targy_mezo.getText()+ " "+ emailcim);                                              //tárgy beállítása
                           
                            Multipart multipart = new MimeMultipart();                                          //csatoló osztály példányosítása
                           
                            MimeBodyPart textPart = new MimeBodyPart();                                         //levél szövegények osztály példányosítása
                            MimeBodyPart attachmentPart = new MimeBodyPart();
                            MimeBodyPart attachmentPart2 = new MimeBodyPart();
                            MimeBodyPart attachmentPart3 = new MimeBodyPart();
                            MimeBodyPart attachmentPart4 = new MimeBodyPart();
                            MimeBodyPart attachmentPart5 = new MimeBodyPart();
                            MimeBodyPart attachmentPart6 = new MimeBodyPart();
                            
                            attachmentPart.attachFile(torlendofajlok[0]);
                            multipart.addBodyPart(attachmentPart);
                            
                            if(reach_gomb.isSelected())
                            {
                                File fajl1 = new File(reach_helye);
                                attachmentPart2.attachFile(fajl1);
                                multipart.addBodyPart(attachmentPart2);
                            }
                            if(rohs_gomb.isSelected())
                            {
                                File fajl1 = new File(rohs_helye);
                                attachmentPart3.attachFile(fajl1);
                                multipart.addBodyPart(attachmentPart3);
                            }
                            if(cmrt_gomb.isSelected())
                            {
                                File fajl1 = new File(cmrt_helye);
                                attachmentPart4.attachFile(fajl1);
                                multipart.addBodyPart(attachmentPart4);
                            }
                            if(emrt_gomb.isSelected())
                            {
                                File fajl1 = new File(emrt_helye);
                                attachmentPart5.attachFile(fajl1);
                                multipart.addBodyPart(attachmentPart5);
                            }
                            if(pfas_gomb.isSelected())
                            {
                                File fajl1 = new File(pfas_helye);
                                attachmentPart6.attachFile(fajl1);
                                multipart.addBodyPart(attachmentPart6);
                            }
                            
                            if(String.valueOf(pfas_box.getSelectedItem()).equals("-"))
                            {
                                textPart.setText(uzenet_mezo.getText());                                          //levél tartalmának csatolása
                            }
                            else
                            {
                                String uzenet = "";
                                
                                if(System.getProperty("user.name").equals("jenei.erika"))          //tatai.mihaly
                                {
                                    uzenet = "<br>Dear Supplier,</br>\r\n"
                                            + "\r\n"
                                            + "<br>We are writing to you to ensure that you are informed about the upcoming legislatives requirements relating to per- and polyfluorinated alkyl substances (PFAS). The EU is currently developing a ‘global’ PFAS restriction under Registration, Evaluation, Authorisation and Restriction of Chemicals (REACH)  1907/2006/EC. The restriction proposes to ban the manufacture, use, sale and import of most PFAS substances within the European Union. If passed, this restriction would apply to all applications and is expected to take effect in mid-2026. For full information on the proposed restriction this can be found at https://echa.europa.eu/restrictions-under-consideration/-/substance-rev/72301/term. Other restrictions or bans are also expected to be enforced globally in the future. There are over 10,000 substances included in the PFAS group and PFAS are used for a variety of applications. A small number of PFAS are already identified as REACH restricted substances and substances on the Candidate List as well as EU Persistent Organic Pollutants Regulation and the International Stockholm Convention.\r\n"
                                            + "<br>\r\n"
                                            + "<br>We will be requiring all our suppliers to provide information on if PFAS are contained within the parts, materials or packagings that are supplied to us. The concerned parts are listed in the attachment. It is important that we receive a response from your company <b><i>within two weeks.</i></b>\r\n"
                                            + "</br>\r\n"
                                            + "<br>Please complete and return the attached PFAS declaration form or send us your own declaration or other documentation concerning PFAS. If you source materials for your products from another supplier, you will need to gather the appropriate information from your supply chain.\r\n"
                                            + "/<br>\r\n"
                                            + "<br>Thank you in advance for supplying the requested information.\r\n"
                                            + "<br>\r\n"
                                            + "<br>Best regards,\r\n"
                                            +  "<br><b>Erika Jenei</b> \r\n"                         //"Erika Jenei\r\n"
                                            + "<br>Environmental engineer\r\n"
                                            + "<br>jenei.erika@veas.videoton.hu\r\n"
                                            + "<br>\r\n"
                                            + "<br>VIDEOTON EAS Kft.\r\n"
                                            + "<br>H-8000 Székesfehérvár, Berényi út 72-100.\r\n"
                                            + "<br>Tel.:    +36 22 533 861\r\n"
                                            + "<br>Mobil: +36 20 227 2750\r\n"
                                            + "<br>www.videoton.hu\r\n"
                                            + "<br>Before printing, think about Environment.</br>\r\n";
                                }
                                if(System.getProperty("user.name").equals("meszaros.agnes"))          //tatai.mihaly
                                {
                                    uzenet = "<br>Dear Supplier,</br>\r\n"
                                            + "\r\n"
                                            + "<br>We are writing to you to ensure that you are informed about the upcoming legislatives requirements relating to per- and polyfluorinated alkyl substances (PFAS). The EU is currently developing a ‘global’ PFAS restriction under Registration, Evaluation, Authorisation and Restriction of Chemicals (REACH)  1907/2006/EC. The restriction proposes to ban the manufacture, use, sale and import of most PFAS substances within the European Union. If passed, this restriction would apply to all applications and is expected to take effect in mid-2026. For full information on the proposed restriction this can be found at https://echa.europa.eu/restrictions-under-consideration/-/substance-rev/72301/term. Other restrictions or bans are also expected to be enforced globally in the future. There are over 10,000 substances included in the PFAS group and PFAS are used for a variety of applications. A small number of PFAS are already identified as REACH restricted substances and substances on the Candidate List as well as EU Persistent Organic Pollutants Regulation and the International Stockholm Convention.\r\n"
                                            + "<br>\r\n"
                                            + "<br>We will be requiring all our suppliers to provide information on if PFAS are contained within the parts, materials or packagings that are supplied to us. The concerned parts are listed in the attachment. It is important that we receive a response from your company <b><i>within two weeks.</i></b>\r\n"
                                            + "</br>\r\n"
                                            + "<br>Please complete and return the attached PFAS declaration form or send us your own declaration or other documentation concerning PFAS. If you source materials for your products from another supplier, you will need to gather the appropriate information from your supply chain.\r\n"
                                            + "/<br>\r\n"
                                            + "<br>Thank you in advance for supplying the requested information.\r\n"
                                            + "<br>\r\n"
                                            + "<br>Best regards,\r\n"
                                            +  "<br><b>Ágnes Mészáros</b> \r\n"                         //"Erika Jenei\r\n"
                                            + "<br>Environmental engineer\r\n"
                                            + "<br>meszaros.agnes@veas.videoton.hu\r\n"
                                            + "<br>\r\n"
                                            + "<br>VIDEOTON EAS Kft.\r\n"
                                            + "<br>H-8000 Székesfehérvár, Berényi út 72-100.\r\n"
                                            + "<br>Tel.:    +36 22 533 861\r\n"
                                            + "<br>Mobil: +36 20 359 3902\r\n"
                                            + "<br>www.videoton.hu\r\n"
                                            + "<br>Before printing, think about Environment.</br>\r\n";
                                }
                                if(System.getProperty("user.name").equals("kovacs.zoltan"))          //tatai.mihaly
                                {
                                    uzenet = "<br>Dear Supplier,</br>\r\n"
                                            + "\r\n"
                                            + "<br>We are writing to you to ensure that you are informed about the upcoming legislatives requirements relating to per- and polyfluorinated alkyl substances (PFAS). The EU is currently developing a ‘global’ PFAS restriction under Registration, Evaluation, Authorisation and Restriction of Chemicals (REACH)  1907/2006/EC. The restriction proposes to ban the manufacture, use, sale and import of most PFAS substances within the European Union. If passed, this restriction would apply to all applications and is expected to take effect in mid-2026. For full information on the proposed restriction this can be found at https://echa.europa.eu/restrictions-under-consideration/-/substance-rev/72301/term. Other restrictions or bans are also expected to be enforced globally in the future. There are over 10,000 substances included in the PFAS group and PFAS are used for a variety of applications. A small number of PFAS are already identified as REACH restricted substances and substances on the Candidate List as well as EU Persistent Organic Pollutants Regulation and the International Stockholm Convention.\r\n"
                                            + "<br>\r\n"
                                            + "<br>We will be requiring all our suppliers to provide information on if PFAS are contained within the parts, materials or packagings that are supplied to us. The concerned parts are listed in the attachment. It is important that we receive a response from your company <b><i>within two weeks.</i></b>\r\n"
                                            + "</br>\r\n"
                                            + "<br>Please complete and return the attached PFAS declaration form or send us your own declaration or other documentation concerning PFAS. If you source materials for your products from another supplier, you will need to gather the appropriate information from your supply chain.\r\n"
                                            + "/<br>\r\n"
                                            + "<br>Thank you in advance for supplying the requested information.\r\n"
                                            + "<br>\r\n"
                                            + "<br>Best regards,\r\n"
                                            +  "<br><b>Ágnes Mészáros</b> \r\n"                         //"Erika Jenei\r\n"
                                            + "<br>Environmental engineer\r\n"
                                            + "<br>meszaros.agnes@veas.videoton.hu\r\n"
                                            + "<br>\r\n"
                                            + "<br>VIDEOTON EAS Kft.\r\n"
                                            + "<br>H-8000 Székesfehérvár, Berényi út 72-100.\r\n"
                                            + "<br>Tel.:    +36 22 533 861\r\n"
                                            + "<br>Mobil: +36 20 359 3902\r\n"
                                            + "<br>www.videoton.hu\r\n"
                                            + "<br>Before printing, think about Environment.</br>\r\n";
                                }
                                
                                //System.out.println(String.format("\u001B[1mTeszt kiírás\u001B[0m"));
                                //message.setContent(uzenet, "text/html; charset=utf-8");        
                                textPart.setText(uzenet,"UTF-8", "html");                                          //levél tartalmának csatolása 
                            }
                            multipart.addBodyPart(textPart);                                            //csatolmány osztály           
                                   
                            message.setContent(multipart);                                                  //message üzenethez mindent hozzáad
                            
                            Transport.send(message);                                                        //levél küldése
                            voltlevel++;
                            System.out.println("Done");                                                     //kiírja, ha lefutott minden rendben
                        }
                        else
                        {
                            for(int szamlalo2 = 0; szamlalo2 < torlendofajlok.length; szamlalo2++)
                            {
                                Workbook workbook4 = new Workbook();
                                String menteshelye = System.getProperty("user.home") + "\\Desktop\\El nem küldött fájlok\\"+torlendofajlok[szamlalo2].getName();
                                workbook4.loadFromFile(System.getProperty("user.home") + "\\Desktop\\Cikkszámok nyilatkozathoz\\"+torlendofajlok[szamlalo2].getName());                               
                                workbook4.saveToFile(menteshelye, ExcelVersion.Version2016);
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
                                torlendofajlok[szamlalo2].delete();
                            }
                            JOptionPane.showMessageDialog(null, "Az alábbi gyártónak nincs a beállított Email címe, nem tudom elküldeni a levelet: "+ table.getValueAt(szamlalo, 0).toString() , "Info", 1);
                        }
                    }
                    if(torlendofajlok.length == 2)
                    {
                        if(emailcim == null)
                        {
                            for(int szamlalo2 = 0; szamlalo2 < torlendofajlok.length; szamlalo2++)
                            {
                                Workbook workbook4 = new Workbook();
                                String menteshelye = System.getProperty("user.home") + "\\Desktop\\El nem küldött fájlok\\"+torlendofajlok[szamlalo2].getName();
                                workbook4.loadFromFile(System.getProperty("user.home") + "\\Desktop\\Cikkszámok nyilatkozathoz\\"+torlendofajlok[szamlalo2].getName());                               
                                workbook4.saveToFile(menteshelye, ExcelVersion.Version2016);
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
                                torlendofajlok[szamlalo2].delete();
                            }
                            JOptionPane.showMessageDialog(null, "Az alábbi gyártó nincs felvéve a kontaktlistára: "+ table.getValueAt(szamlalo, 0).toString() , "Info", 1);
                        }
                        else if(emailcim.contains("@"))
                        {
                            Properties props = new Properties(); //new Properties();     System.getProperties();
                            
                            props.put("mail.smtp.host", "172.20.22.254");                   //smtp.gmail.com                    //172.20.22.254 belső levelezés      //smtp-mail.outlook.com
                            props.put("mail.smtp.port", "25");                                      //587 TLS       //465  SSL          //25 Outlook                            //587
                            Session session = Session.getInstance(props, null);                                 //session létrehozűsa a megadott paraméterekkel
                            Message message = new MimeMessage(session);
                            message.setFrom(new InternetAddress("easqas@veas.videoton.hu"));                                  //feladó beállítása
                            if(System.getProperty("user.name").equals("meszaros.agnes"))          //tatai.mihaly
                            {
                                message.setFrom(new InternetAddress("meszaros.agnes@veas.videoton.hu"));
                                message.setRecipients(Message.RecipientType.CC,
                                        InternetAddress.parse("jenei.erika@veas.videoton.hu,meszaros.agnes@veas.videoton.hu"));
                            }
                            if(System.getProperty("user.name").equals("jenei.erika"))          //tatai.mihaly
                            {
                                message.setFrom(new InternetAddress("jenei.erika@veas.videoton.hu"));
                                message.setRecipients(Message.RecipientType.CC,
                                        InternetAddress.parse("jenei.erika@veas.videoton.hu,meszaros.agnes@veas.videoton.hu"));
                            }
                            if(System.getProperty("user.name").equals("kovacs.zoltan"))          //tatai.mihaly
                            {
                                message.setFrom(new InternetAddress("kovacs.zoltan@veas.videoton.hu"));
                                message.setRecipients(Message.RecipientType.CC,
                                        InternetAddress.parse("kovacs.zoltan@veas.videoton.hu"));
                            }
                            message.setRecipients(Message.RecipientType.TO,
                                InternetAddress.parse("kovacs.zoltan@veas.videoton.hu"));                                                //címzett beállítása  ,jenei.erika@veas.videoton.hu,meszaros.agnes@veas.videoton.hu                            
                            message.setSubject(targy_mezo.getText()+ " "+ emailcim);                                              //tárgy beállítása
                           
                            Multipart multipart = new MimeMultipart();                                          //csatoló osztály példányosítása
                           
                            MimeBodyPart textPart = new MimeBodyPart();                                         //levél szövegények osztály példányosítása
                            MimeBodyPart attachmentPart = new MimeBodyPart();
                            MimeBodyPart attachmentPart2= new MimeBodyPart();
                            MimeBodyPart attachmentPart3 = new MimeBodyPart();
                            MimeBodyPart attachmentPart4 = new MimeBodyPart();
                            MimeBodyPart attachmentPart5 = new MimeBodyPart();
                            MimeBodyPart attachmentPart6 = new MimeBodyPart();
                            attachmentPart.attachFile(torlendofajlok[0]);
                            attachmentPart2.attachFile(torlendofajlok[1]);
                            multipart.addBodyPart(attachmentPart);
                            multipart.addBodyPart(attachmentPart2);
                            
                            if(reach_gomb.isSelected())
                            {
                                File fajl1 = new File(reach_helye);
                                attachmentPart3.attachFile(fajl1);
                                multipart.addBodyPart(attachmentPart3);
                            }
                            if(rohs_gomb.isSelected())
                            {
                                File fajl1 = new File(rohs_helye);
                                attachmentPart4.attachFile(fajl1);
                                multipart.addBodyPart(attachmentPart4);
                            }
                            if(cmrt_gomb.isSelected())
                            {
                                File fajl1 = new File(cmrt_helye);
                                attachmentPart5.attachFile(fajl1);
                                multipart.addBodyPart(attachmentPart5);
                            }
                            if(emrt_gomb.isSelected())
                            {
                                File fajl1 = new File(emrt_helye);
                                attachmentPart6.attachFile(fajl1);
                                multipart.addBodyPart(attachmentPart6);
                            }
                            if(pfas_gomb.isSelected())
                            {
                                File fajl1 = new File(pfas_helye);
                                attachmentPart6.attachFile(fajl1);
                                multipart.addBodyPart(attachmentPart6);
                            }
                            
                            textPart.setText(uzenet_mezo.getText());                                          //levél tartalmának csatolása
                            multipart.addBodyPart(textPart);                                            //csatolmány osztály           
                                   
                            message.setContent(multipart);                                                  //message üzenethez mindent hozzáad
                            
                            Transport.send(message);                                                        //levél küldése
                            voltlevel++;
                            System.out.println("Done");                                                     //kiírja, ha lefutott minden rendben
                        }
                        else
                        {
                            for(int szamlalo2 = 0; szamlalo2 < torlendofajlok.length; szamlalo2++)
                            {
                                File ujhely = new File(System.getProperty("user.home") + "\\Desktop\\El nem küldött fájlok\\"+torlendofajlok[szamlalo2].getName());
                                ujhely.getParentFile().mkdirs(); 
                                ujhely.createNewFile();                                
                                torlendofajlok[szamlalo2].delete();
                            }
                            JOptionPane.showMessageDialog(null, "Az alábbi gyártónak nincs a beállított Email címe, nem tudom elküldeni a levelet: "+ table.getValueAt(szamlalo, 0).toString() , "Info", 1);
                        }
                    }
                    if(torlendofajlok.length == 3)
                    {
                        if(emailcim == null)
                        {
                            for(int szamlalo2 = 0; szamlalo2 < torlendofajlok.length; szamlalo2++)
                            {
                                Workbook workbook4 = new Workbook();
                                String menteshelye = System.getProperty("user.home") + "\\Desktop\\El nem küldött fájlok\\"+torlendofajlok[szamlalo2].getName();
                                workbook4.loadFromFile(System.getProperty("user.home") + "\\Desktop\\Cikkszámok nyilatkozathoz\\"+torlendofajlok[szamlalo2].getName());                               
                                workbook4.saveToFile(menteshelye, ExcelVersion.Version2016);
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
                                torlendofajlok[szamlalo2].delete();
                            }
                            JOptionPane.showMessageDialog(null, "Az alábbi gyártó nincs felvéve a kontaktlistára: "+ table.getValueAt(szamlalo, 0).toString() , "Info", 1);
                        }
                        else if(emailcim.contains("@"))
                        {
                            Properties props = new Properties(); //new Properties();     System.getProperties();
                            
                            props.put("mail.smtp.host", "172.20.22.254");                   //smtp.gmail.com                    //172.20.22.254 belső levelezés      //smtp-mail.outlook.com
                            props.put("mail.smtp.port", "25");                                      //587 TLS       //465  SSL          //25 Outlook                            //587
                            Session session = Session.getInstance(props, null);                                 //session létrehozűsa a megadott paraméterekkel
                            Message message = new MimeMessage(session);
                            message.setFrom(new InternetAddress("easqas@veas.videoton.hu"));                                  //feladó beállítása
                            if(System.getProperty("user.name").equals("meszaros.agnes"))          //tatai.mihaly
                            {
                                message.setFrom(new InternetAddress("meszaros.agnes@veas.videoton.hu"));
                                message.setRecipients(Message.RecipientType.CC,
                                        InternetAddress.parse("jenei.erika@veas.videoton.hu,meszaros.agnes@veas.videoton.hu"));
                            }
                            if(System.getProperty("user.name").equals("jenei.erika"))          //tatai.mihaly
                            {
                                message.setFrom(new InternetAddress("jenei.erika@veas.videoton.hu"));
                                message.setRecipients(Message.RecipientType.CC,
                                        InternetAddress.parse("jenei.erika@veas.videoton.hu,meszaros.agnes@veas.videoton.hu"));
                            }
                            if(System.getProperty("user.name").equals("kovacs.zoltan"))          //tatai.mihaly
                            {
                                message.setFrom(new InternetAddress("kovacs.zoltan@veas.videoton.hu"));
                                message.setRecipients(Message.RecipientType.CC,
                                        InternetAddress.parse("kovacs.zoltan@veas.videoton.hu"));
                            }
                            if(System.getProperty("user.name").equals("recepcio"))          //tatai.mihaly
                            {
                                message.setFrom(new InternetAddress("kovacs.zoltan@veas.videoton.hu"));
                                message.setRecipients(Message.RecipientType.CC,
                                        InternetAddress.parse("kovacs.zoltan@veas.videoton.hu,recepcio@veas.videoton.hu,jenei.erika@veas.videoton.hu,meszaros.agnes@veas.videoton.hu"));
                            }
                            message.setRecipients(Message.RecipientType.TO,
                                InternetAddress.parse("kovacs.zoltan@veas.videoton.hu"));                                                //címzett beállítása  ,jenei.erika@veas.videoton.hu,meszaros.agnes@veas.videoton.hu                            
                            message.setSubject(targy_mezo.getText()+ " "+ emailcim);                                              //tárgy beállítása
                           
                            Multipart multipart = new MimeMultipart();                                          //csatoló osztály példányosítása
                           
                            MimeBodyPart textPart = new MimeBodyPart();                                         //levél szövegények osztály példányosítása
                            MimeBodyPart attachmentPart = new MimeBodyPart();
                            MimeBodyPart attachmentPart2= new MimeBodyPart();
                            MimeBodyPart attachmentPart3 = new MimeBodyPart();
                            MimeBodyPart attachmentPart4 = new MimeBodyPart();
                            MimeBodyPart attachmentPart5 = new MimeBodyPart();
                            MimeBodyPart attachmentPart6 = new MimeBodyPart();
                            MimeBodyPart attachmentPart7 = new MimeBodyPart();
                            MimeBodyPart attachmentPart8 = new MimeBodyPart();
                            attachmentPart.attachFile(torlendofajlok[0]);
                            attachmentPart2.attachFile(torlendofajlok[1]);
                            attachmentPart3.attachFile(torlendofajlok[2]);
                            multipart.addBodyPart(attachmentPart);
                            multipart.addBodyPart(attachmentPart2);
                            multipart.addBodyPart(attachmentPart3);
                            
                            if(reach_gomb.isSelected())
                            {
                                File fajl1 = new File(reach_helye);
                                attachmentPart4.attachFile(fajl1);
                                multipart.addBodyPart(attachmentPart4);
                            }
                            if(rohs_gomb.isSelected())
                            {
                                File fajl1 = new File(rohs_helye);
                                attachmentPart5.attachFile(fajl1);
                                multipart.addBodyPart(attachmentPart5);
                            }
                            if(cmrt_gomb.isSelected())
                            {
                                File fajl1 = new File(cmrt_helye);
                                attachmentPart6.attachFile(fajl1);
                                multipart.addBodyPart(attachmentPart6);
                            }
                            if(emrt_gomb.isSelected())
                            {
                                File fajl1 = new File(emrt_helye);
                                attachmentPart7.attachFile(fajl1);
                                multipart.addBodyPart(attachmentPart7);
                            }
                            if(pfas_gomb.isSelected())
                            {
                                File fajl1 = new File(pfas_helye);
                                attachmentPart8.attachFile(fajl1);
                                multipart.addBodyPart(attachmentPart8);
                            }
                            
                            textPart.setText(uzenet_mezo.getText());                                          //levél tartalmának csatolása
                            multipart.addBodyPart(textPart);                                            //csatolmány osztály           
                                   
                            message.setContent(multipart);                                                  //message üzenethez mindent hozzáad
                            
                            Transport.send(message);                                                        //levél küldése
                            voltlevel++; 
                            System.out.println("Done");                                                     //kiírja, ha lefutott minden rendben
                        }
                        else
                        {
                            for(int szamlalo2 = 0; szamlalo2 < torlendofajlok.length; szamlalo2++)
                            {
                                Workbook workbook4 = new Workbook();
                                String menteshelye = System.getProperty("user.home") + "\\Desktop\\El nem küldött fájlok\\"+torlendofajlok[szamlalo2].getName();
                                workbook4.loadFromFile(System.getProperty("user.home") + "\\Desktop\\Cikkszámok nyilatkozathoz\\"+torlendofajlok[szamlalo2].getName());                               
                                workbook4.saveToFile(menteshelye, ExcelVersion.Version2016);
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
                                torlendofajlok[szamlalo2].delete();
                            }
                            JOptionPane.showMessageDialog(null, "Az alábbi gyártónak nincs a beállított Email címe, nem tudom elküldeni a levelet: "+ table.getValueAt(szamlalo, 0).toString() , "Info", 1);
                        }
                    }
                    
                    if(torlendofajlok.length == 4)
                    {
                        if(emailcim == null)
                        {
                            for(int szamlalo2 = 0; szamlalo2 < torlendofajlok.length; szamlalo2++)
                            {
                                Workbook workbook4 = new Workbook();
                                String menteshelye = System.getProperty("user.home") + "\\Desktop\\El nem küldött fájlok\\"+torlendofajlok[szamlalo2].getName();
                                workbook4.loadFromFile(System.getProperty("user.home") + "\\Desktop\\Cikkszámok nyilatkozathoz\\"+torlendofajlok[szamlalo2].getName());                               
                                workbook4.saveToFile(menteshelye, ExcelVersion.Version2016);
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
                                torlendofajlok[szamlalo2].delete();
                            }
                            JOptionPane.showMessageDialog(null, "Az alábbi gyártó nincs felvéve a kontaktlistára: "+ table.getValueAt(szamlalo, 0).toString() , "Info", 1);
                        }
                        else if(emailcim.contains("@"))
                        {
                            Properties props = new Properties(); //new Properties();     System.getProperties();
                            
                            props.put("mail.smtp.host", "172.20.22.254");                   //smtp.gmail.com                    //172.20.22.254 belső levelezés      //smtp-mail.outlook.com
                            props.put("mail.smtp.port", "25");                                      //587 TLS       //465  SSL          //25 Outlook                            //587
                            Session session = Session.getInstance(props, null);                                 //session létrehozűsa a megadott paraméterekkel
                            Message message = new MimeMessage(session);
                            message.setFrom(new InternetAddress("easqas@veas.videoton.hu"));                                  //feladó beállítása
                            if(System.getProperty("user.name").equals("meszaros.agnes"))          //tatai.mihaly
                            {
                                message.setFrom(new InternetAddress("meszaros.agnes@veas.videoton.hu"));
                                message.setRecipients(Message.RecipientType.CC,
                                        InternetAddress.parse("jenei.erika@veas.videoton.hu,meszaros.agnes@veas.videoton.hu"));
                            }
                            if(System.getProperty("user.name").equals("jenei.erika"))          //tatai.mihaly
                            {
                                message.setFrom(new InternetAddress("jenei.erika@veas.videoton.hu"));
                                message.setRecipients(Message.RecipientType.CC,
                                        InternetAddress.parse("jenei.erika@veas.videoton.hu,meszaros.agnes@veas.videoton.hu"));
                            }
                            if(System.getProperty("user.name").equals("kovacs.zoltan"))          //tatai.mihaly
                            {
                                message.setFrom(new InternetAddress("kovacs.zoltan@veas.videoton.hu"));
                                message.setRecipients(Message.RecipientType.CC,
                                        InternetAddress.parse("kovacs.zoltan@veas.videoton.hu"));
                            }
                            if(System.getProperty("user.name").equals("recepcio"))          //tatai.mihaly
                            {
                                message.setFrom(new InternetAddress("kovacs.zoltan@veas.videoton.hu"));
                                message.setRecipients(Message.RecipientType.CC,
                                        InternetAddress.parse("kovacs.zoltan@veas.videoton.hu,recepcio@veas.videoton.hu,jenei.erika@veas.videoton.hu,meszaros.agnes@veas.videoton.hu"));
                            }
                            message.setRecipients(Message.RecipientType.TO,
                                InternetAddress.parse("kovacs.zoltan@veas.videoton.hu"));                                                //címzett beállítása  ,jenei.erika@veas.videoton.hu,meszaros.agnes@veas.videoton.hu                            
                            message.setSubject(targy_mezo.getText()+ " "+ emailcim);                                              //tárgy beállítása
                           
                            Multipart multipart = new MimeMultipart();                                          //csatoló osztály példányosítása
                           
                            MimeBodyPart textPart = new MimeBodyPart();                                         //levél szövegények osztály példányosítása
                            MimeBodyPart attachmentPart = new MimeBodyPart();
                            MimeBodyPart attachmentPart2= new MimeBodyPart();
                            MimeBodyPart attachmentPart3 = new MimeBodyPart();
                            MimeBodyPart attachmentPart4 = new MimeBodyPart();
                            MimeBodyPart attachmentPart5 = new MimeBodyPart();
                            MimeBodyPart attachmentPart6 = new MimeBodyPart();
                            MimeBodyPart attachmentPart7 = new MimeBodyPart();
                            MimeBodyPart attachmentPart8 = new MimeBodyPart();
                            MimeBodyPart attachmentPart9 = new MimeBodyPart();
                            attachmentPart.attachFile(torlendofajlok[0]);
                            attachmentPart2.attachFile(torlendofajlok[1]);
                            attachmentPart3.attachFile(torlendofajlok[2]);
                            attachmentPart9.attachFile(torlendofajlok[3]);
                            multipart.addBodyPart(attachmentPart);
                            multipart.addBodyPart(attachmentPart2);
                            multipart.addBodyPart(attachmentPart3);
                            multipart.addBodyPart(attachmentPart9);
                            
                            if(reach_gomb.isSelected())
                            {
                                File fajl1 = new File(reach_helye);
                                attachmentPart4.attachFile(fajl1);
                                multipart.addBodyPart(attachmentPart4);
                            }
                            if(rohs_gomb.isSelected())
                            {
                                File fajl1 = new File(rohs_helye);
                                attachmentPart5.attachFile(fajl1);
                                multipart.addBodyPart(attachmentPart5);
                            }
                            if(cmrt_gomb.isSelected())
                            {
                                File fajl1 = new File(cmrt_helye);
                                attachmentPart6.attachFile(fajl1);
                                multipart.addBodyPart(attachmentPart6);
                            }
                            if(emrt_gomb.isSelected())
                            {
                                File fajl1 = new File(emrt_helye);
                                attachmentPart7.attachFile(fajl1);
                                multipart.addBodyPart(attachmentPart7);
                            }
                            if(pfas_gomb.isSelected())
                            {
                                File fajl1 = new File(pfas_helye);
                                attachmentPart8.attachFile(fajl1);
                                multipart.addBodyPart(attachmentPart8);
                            }
                            
                            
                            if(String.valueOf(pfas_box.getSelectedItem()).equals("-"))
                            {
                                textPart.setText(uzenet_mezo.getText());                                          //levél tartalmának csatolása
                            }
                            else
                            {
                                
                                String uzenet = "Dear Supplier,\r\n"
                                        + "\r\n"
                                        + "We are writing to you to ensure that you are informed about the upcoming legislatives requirements relating to per- and polyfluorinated alkyl substances (PFAS). The EU is currently developing a ‘global’ PFAS restriction under Registration, Evaluation, Authorisation and Restriction of Chemicals (REACH)  1907/2006/EC. The restriction proposes to ban the manufacture, use, sale and import of most PFAS substances within the European Union. If passed, this restriction would apply to all applications and is expected to take effect in mid-2026. For full information on the proposed restriction this can be found at https://echa.europa.eu/restrictions-under-consideration/-/substance-rev/72301/term. Other restrictions or bans are also expected to be enforced globally in the future. There are over 10,000 substances included in the PFAS group and PFAS are used for a variety of applications. A small number of PFAS are already identified as REACH restricted substances and substances on the Candidate List as well as EU Persistent Organic Pollutants Regulation and the International Stockholm Convention.\r\n"
                                        + "\r\n"
                                        + "We will be requiring all our suppliers to provide information on if PFAS are contained within the parts, materials or packagings that are supplied to us. The concerned parts are listed in the attachment. It is important that we receive a response from your company within two weeks.\r\n"
                                        + "\r\n"
                                        + "Please complete and return the attached PFAS declaration form or send us your own declaration or other documentation concerning PFAS. If you source materials for your products from another supplier, you will need to gather the appropriate information from your supply chain.\r\n"
                                        + "\r\n"
                                        + "Thank you in advance for supplying the requested information.\r\n"
                                        + "\r\n"
                                        + "Best regards,\r\n"
                                        + "Erika Jenei \r\n"
                                        + "Environmental engineer\r\n"
                                        + "jenei.erika@veas.videoton.hu\r\n"
                                        + "\r\n"
                                        + "\r\n"
                                        + "VIDEOTON EAS Kft.\r\n"
                                        + "H-8000 Székesfehérvár, Berényi út 72-100.\r\n"
                                        + "Tel.:    +36 22 533 861\r\n"
                                        + "Mobil: +36 20 227 2750\r\n"
                                        + "www.videoton.hu\r\n"
                                        + " Before printing, think about Environment.\r\n"
                                        + "";
                                textPart.setText(uzenet);                                          //levél tartalmának csatolása 
                            }
                            multipart.addBodyPart(textPart);                                            //csatolmány osztály       
                                   
                            message.setContent(multipart);                                                  //message üzenethez mindent hozzáad
                            
                            Transport.send(message);                                                        //levél küldése
                            voltlevel++; 
                            System.out.println("Done");                                                     //kiírja, ha lefutott minden rendben
                        }
                        else
                        {
                            for(int szamlalo2 = 0; szamlalo2 < torlendofajlok.length; szamlalo2++)
                            {
                                Workbook workbook4 = new Workbook();
                                String menteshelye = System.getProperty("user.home") + "\\Desktop\\El nem küldött fájlok\\"+torlendofajlok[szamlalo2].getName();
                                workbook4.loadFromFile(System.getProperty("user.home") + "\\Desktop\\Cikkszámok nyilatkozathoz\\"+torlendofajlok[szamlalo2].getName());                               
                                workbook4.saveToFile(menteshelye, ExcelVersion.Version2016);
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
                                torlendofajlok[szamlalo2].delete();
                            }
                            JOptionPane.showMessageDialog(null, "Az alábbi gyártónak nincs a beállított Email címe, nem tudom elküldeni a levelet: "+ table.getValueAt(szamlalo, 0).toString() , "Info", 1);
                        }
                    }
                    
                    
                    for(int szamlalo2 = 0; szamlalo2 < torlendofajlok.length; szamlalo2++)
                    {
                        torlendofajlok[szamlalo2].delete();
                    }
                    System.out.println("Fut a fő for");
                }
                
                rs.close();
                stmt.close();
                con.close();
                System.out.println("Vége");
                if(voltlevel > 0)
                {
                    JOptionPane.showMessageDialog(null, "Kész! \n Email/Email-ek elküldve'", "Info", 1);
                }
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
