import javax.swing.JPanel;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.spire.data.table.DataTable;
import com.spire.data.table.common.JdbcAdapter;
import com.spire.xls.ExcelVersion;
import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFileChooser;

public class Telecom_utolso extends JPanel {

    /**
     * Create the panel.
     */
    public Telecom_utolso() {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Telecom Desig utolsó folyamat lekérdezés");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setBounds(414, 34, 316, 27);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Utolsó folyamat");
        lblNewLabel_1.setBounds(430, 151, 92, 14);
        add(lblNewLabel_1);
        
        JButton megnyit_gomb = new JButton("Megnyit");
        megnyit_gomb.addActionListener(new Lekerdezes());
        megnyit_gomb.setBounds(548, 147, 89, 23);
        add(megnyit_gomb);
        
        setBackground(Foablak.hatter_szine);
        
        JLabel lblNewLabel_2 = new JLabel("Minden adat");
        lblNewLabel_2.setBounds(430, 204, 92, 14);
        add(lblNewLabel_2);
        
        JButton minden_gomb = new JButton("Megnyitás");
        minden_gomb.addActionListener(new Lekerdezes_minden());
        minden_gomb.setBounds(548, 200, 89, 23);
        add(minden_gomb);

    }
    
    class Lekerdezes implements ActionListener                                                                                      //csv-t gyárt a gomb
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {              
                JFileChooser mentes_helye = new JFileChooser();
                mentes_helye.setCurrentDirectory(new java.io.File(System.getProperty("user.home") + "\\Desktop\\"));
                mentes_helye.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                mentes_helye.showOpenDialog(mentes_helye);
                File fajl = mentes_helye.getSelectedFile();
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                Workbook workbook = new Workbook();
                workbook.loadFromFile(fajl.getAbsolutePath());
                Worksheet sheet = workbook.getWorksheets().get(0);
                DataTable datatable = new DataTable();
                datatable = sheet.exportDataTable(sheet.getAllocatedRange(), false, false );
                
                Workbook workbook2 = new Workbook();
                Worksheet sheet2 = workbook2.getWorksheets().get(0);
                 
                  DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                  Class.forName("oracle.jdbc.OracleDriver");  //.driver
                                      
                  Connection con = DriverManager.getConnection("jdbc:oracle:thin:@IFSORA.IFS.videoton.hu:1521/IFSPROD","ZKOVACS","ZKOVACS");                                      
                  Statement stmt = con.createStatement();                      
                  int cellaszam = 1;
                  sheet2.getRange().get("A" + cellaszam).setText("Szériaszám");
                  sheet2.getRange().get("B" + cellaszam).setText("Feldolgozás dátuma");
                  sheet2.getRange().get("C" + cellaszam).setText("Munkahely száma");
                  sheet2.getRange().get("D" + cellaszam).setText("Eredmény");
                  sheet2.getRange().get("E" + cellaszam).setText("Új fej státusz");
                  cellaszam++;
                  
                  for(int szamlalo = 0; szamlalo < datatable.getRows().size(); szamlalo++)
                  {
                      ResultSet rs = stmt.executeQuery("select TRACY_SERIAL_NO,\r\n"
                              + "PROCESS_DATE,\r\n"
                              + "WORK_CENTER_NO, \r\n"
                              + "PASS, NEW_HEAD_STATE "
                              + "from ifsapp.C_OPER_TRACY_OVW, \r\n"
                              + "(select TRACY_SERIAL_NO as szeriaszam,\r\n"
                              + "MAX(PROCESS_DATE) as maxido\r\n"
                              + "from ifsapp.C_OPER_TRACY_OVW\r\n"
                              + "where 3=3\r\n"
                              + "and TRACY_SERIAL_NO = '"+ datatable.getRows().get(szamlalo).getString(0) +"'\r\n"
                              + "group by TRACY_SERIAL_NO) belso\r\n"
                              + "where belso.szeriaszam = TRACY_SERIAL_NO\r\n"
                              + "and belso.maxido = PROCESS_DATE");
                      if(rs.next())
                      {                        
                          sheet2.getRange().get("A" + cellaszam).setText(rs.getString(1));
                          sheet2.getRange().get("B" + cellaszam).setText(rs.getString(2));
                          sheet2.getRange().get("C" + cellaszam).setText(rs.getString(3));
                          sheet2.getRange().get("D" + cellaszam).setText(rs.getString(4));
                          sheet2.getRange().get("E" + cellaszam).setText(rs.getString(5));
                      }                      
                      else
                      {
                          sheet2.getRange().get("A" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(0));
                      }
                      cellaszam++;
                      rs.next();
                  }
                  
                  sheet2.getAutoFilters().setRange(sheet2.getCellRange("A1:P1"));
                  sheet2.getAllocatedRange().autoFitColumns();
                  sheet2.getAllocatedRange().autoFitRows();
                  sheet2.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                  String hova = System.getProperty("user.home") + "\\Desktop\\IFS utolsó folyamat.xlsx";
                  workbook2.saveToFile(hova, ExcelVersion.Version2016);
                  FileInputStream fileStream = new FileInputStream(hova);
                  try (XSSFWorkbook workbook3 = new XSSFWorkbook(fileStream)) 
                  {
                      for(int i = workbook3.getNumberOfSheets()-1; i > 0 ;i--)
                      {    
                          workbook3.removeSheetAt(i); 
                      }      
                      FileOutputStream output = new FileOutputStream(hova);
                      workbook3.write(output);
                      output.close();
                  }
                  JOptionPane.showMessageDialog(null, "Kész! \n Mentve az asztalra IFS utolsó folyamat.xlsx néven!", "Info", 1); 
                  con.close();  
                  Foablak.frame.setCursor(null);  
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
    
    class Lekerdezes_minden implements ActionListener                                                                                      //csv-t gyárt a gomb
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {              
                JFileChooser mentes_helye = new JFileChooser();
                mentes_helye.setCurrentDirectory(new java.io.File(System.getProperty("user.home") + "\\Desktop\\"));
                mentes_helye.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                mentes_helye.showOpenDialog(mentes_helye);
                File fajl = mentes_helye.getSelectedFile();
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                Workbook workbook = new Workbook();
                workbook.loadFromFile(fajl.getAbsolutePath());
                Worksheet sheet = workbook.getWorksheets().get(0);
                DataTable datatable = new DataTable();
                DataTable datatable2 = new DataTable();
                datatable = sheet.exportDataTable(sheet.getAllocatedRange(), false, false );                
                Workbook workbook2 = new Workbook();
                Worksheet sheet2 = workbook2.getWorksheets().get(0);
                
                String osszefuzott = "";
                for(int szamlalo = 0; szamlalo < datatable.getRows().size(); szamlalo++)
                {
                    osszefuzott += "'"+ datatable.getRows().get(szamlalo).getString(0) +"',";
                }
                osszefuzott = osszefuzott.substring(0, osszefuzott.length() - 1);
                
                DriverManager.registerDriver(new oracle.jdbc.OracleDriver());                
                Class.forName("oracle.jdbc.OracleDriver");  //.driver                                    
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@IFSORA.IFS.videoton.hu:1521/IFSPROD","ZKOVACS","ZKOVACS");                                      
                Statement stmt = con.createStatement();                                    
                
                ResultSet rs = stmt.executeQuery("select ot.contract,\r\n"
                        + "       ct.part_no,\r\n"
                        + "       ot.tracy_id,\r\n"
                        + "       ot.TRACY_SERIAL_NO,\r\n"
                        + "       ot.ALT_TRACY_SERIAL_NO1,\r\n"
                        + "       ot.manuf_date,\r\n"
                        + "       ot.work_center_no,\r\n"
                        + "       ot.resource_id,\r\n"
                        + "       ot.scan_loc,\r\n"
                        + "       ot.operation_no,\r\n"
                        + "       ot.operation_description,\r\n"
                        + "       ot.pass,\r\n"
                        + "       ot.repetitions,\r\n"
                        + "       ot.REPET_LAST_REPAIR,\r\n"
                        + "       so.comments,\r\n"
                        + "       so.tracy_spec_code,\r\n"
                        + "       so.alfa_num_value,\r\n"
                        + "       ct.package_id\r\n"
                        + "  from ifsapp.C_OPER_TRACY_OVW      ot,\r\n"
                        + "       ifsapp.C_SPEC_TRACY_UNIT_OVW so,\r\n"
                        + "       ifsapp.C_TRACY_HIST_OVW      ct\r\n"
                        + " where 3 = 3\r\n"
                        + "   and ot.TRACY_SERIAL_NO in("+ osszefuzott +")\r\n"
                        + "   and ot.contract = so.contract(+)\r\n"
                        + "   and ot.tracy_id = so.tracy_id(+)\r\n"
                        + "   and ot.oper_tracy_id = so.oper_tracy_id(+)\r\n"
                        + "      --and ot.tracy_id = '35903860'\r\n"
                        + "   and so.COMMENTS(+) is not null\r\n"
                        + "   -- and (so.tracy_spec_code = 'MANUF_START_DATE'  or  so.tracy_spec_code = 'MANUF_END_DATE')\r\n"
                        + "   and ot.history_id = ct.history_id\r\n"
                        + "   and ot.tracy_id = ct.tracy_id\r\n"
                        + "      --and nvl(ot.manuf_date, ot.process_date) >= trunc(SYSDATE)\r\n"                       
                        + " order by ot.manuf_date asc ");
                
                JdbcAdapter jdbcAdapter = new JdbcAdapter();
                jdbcAdapter.fillDataTable(datatable2, rs);
                //sheet2.insertDataTable(datatable2, true, 1, 1);
                int cellaszam = 1;
                sheet2.getRange().get("A" + cellaszam).setText("Contract");
                sheet2.getRange().get("B" + cellaszam).setText("Part no");
                sheet2.getRange().get("C" + cellaszam).setText("Tracy ID");
                sheet2.getRange().get("D" + cellaszam).setText("Szériaszám");
                sheet2.getRange().get("E" + cellaszam).setText("Alternatív Szériaszám");
                sheet2.getRange().get("F" + cellaszam).setText("Gyártás Dátuma");
                sheet2.getRange().get("G" + cellaszam).setText("Beolvasási pont");
                sheet2.getRange().get("H" + cellaszam).setText("REsource ID");
                sheet2.getRange().get("I" + cellaszam).setText("Scan Loc");
                sheet2.getRange().get("J" + cellaszam).setText("Művelet száma");
                sheet2.getRange().get("K" + cellaszam).setText("Művelet megnevezése");
                sheet2.getRange().get("L" + cellaszam).setText("Pass");
                sheet2.getRange().get("M" + cellaszam).setText("Ismétlések száma");
                sheet2.getRange().get("N" + cellaszam).setText("Javítás óta ismétlések száma");
                sheet2.getRange().get("O" + cellaszam).setText("Komment");
                sheet2.getRange().get("P" + cellaszam).setText("Tracy Space code");
                sheet2.getRange().get("Q" + cellaszam).setText("Alfanumerikus érték");
                sheet2.getRange().get("R" + cellaszam).setText("Package ID");
                
                cellaszam++;
                for(int szamlalo = 0; szamlalo < datatable2.getRows().size(); szamlalo++)
                {
                    sheet2.getRange().get("A" + cellaszam).setText(datatable2.getRows().get(szamlalo).getString(0));
                    sheet2.getRange().get("B" + cellaszam).setText(datatable2.getRows().get(szamlalo).getString(1));
                    sheet2.getRange().get("C" + cellaszam).setText(datatable2.getRows().get(szamlalo).getString(2));
                    sheet2.getRange().get("D" + cellaszam).setText(datatable2.getRows().get(szamlalo).getString(3));
                    sheet2.getRange().get("E" + cellaszam).setText(datatable2.getRows().get(szamlalo).getString(4));
                    sheet2.getRange().get("F" + cellaszam).setText(datatable2.getRows().get(szamlalo).getString(5));
                    sheet2.getRange().get("G" + cellaszam).setText(datatable2.getRows().get(szamlalo).getString(6));
                    sheet2.getRange().get("H" + cellaszam).setText(datatable2.getRows().get(szamlalo).getString(7));
                    sheet2.getRange().get("I" + cellaszam).setText(datatable2.getRows().get(szamlalo).getString(8));
                    sheet2.getRange().get("J" + cellaszam).setText(datatable2.getRows().get(szamlalo).getString(9));
                    sheet2.getRange().get("K" + cellaszam).setText(datatable2.getRows().get(szamlalo).getString(10));
                    sheet2.getRange().get("L" + cellaszam).setText(datatable2.getRows().get(szamlalo).getString(11));
                    sheet2.getRange().get("M" + cellaszam).setText(datatable2.getRows().get(szamlalo).getString(12));
                    sheet2.getRange().get("N" + cellaszam).setText(datatable2.getRows().get(szamlalo).getString(13));
                    sheet2.getRange().get("O" + cellaszam).setText(datatable2.getRows().get(szamlalo).getString(14));
                    sheet2.getRange().get("P" + cellaszam).setText(datatable2.getRows().get(szamlalo).getString(15));
                    sheet2.getRange().get("Q" + cellaszam).setText(datatable2.getRows().get(szamlalo).getString(16));
                    sheet2.getRange().get("R" + cellaszam).setText(datatable2.getRows().get(szamlalo).getString(17));
                    cellaszam++;
                }
                //sheet2.insertDataTable(datatable2, true, 1, 1);
                sheet2.getAutoFilters().setRange(sheet2.getCellRange("A1:AD1"));
                sheet2.getAllocatedRange().autoFitColumns();
                sheet2.getAllocatedRange().autoFitRows();
                sheet2.getCellRange("A1:AD1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                String hova = System.getProperty("user.home") + "\\Desktop\\Telecom minden adat.xlsx";
                workbook2.saveToFile(hova, ExcelVersion.Version2016);
                FileInputStream fileStream = new FileInputStream(hova);
                try (XSSFWorkbook workbook3 = new XSSFWorkbook(fileStream)) 
                {
                    for(int i = workbook3.getNumberOfSheets()-1; i > 0 ;i--)
                    {    
                        workbook3.removeSheetAt(i); 
                    }      
                    FileOutputStream output = new FileOutputStream(hova);
                    workbook3.write(output);
                    output.close();
                }
                JOptionPane.showMessageDialog(null, "Kész! \n Mentve az asztalra Telecom minden adat.xlsx néven!", "Info", 1); 
                con.close();  
                Foablak.frame.setCursor(null);  
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
}
