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
        
        JLabel lblNewLabel_3 = new JLabel("Gyártásban levő termékek");
        lblNewLabel_3.setBounds(360, 275, 151, 14);
        add(lblNewLabel_3);
        
        JButton keres_gomb = new JButton("Keresés");
        keres_gomb.addActionListener(new Gyártásban_levo());
        keres_gomb.setBounds(548, 271, 89, 23);
        add(keres_gomb);
        
        setBackground(Foablak.hatter_szine);

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
                
                Workbook workbook = new Workbook();
                if(fajl != null)
                {
                    Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
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
                          System.out.println("Fut..");
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
                }
                Foablak.frame.setCursor(null);  
            }           
            catch(Exception e1)
            { 
                System.out.println(e1);
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                              //kiírja a hibaüzenetet
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
                Workbook workbook = new Workbook();
                if(fajl != null)
                {
                    Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
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
                }
                Foablak.frame.setCursor(null);  
            }           
            catch(Exception e1)
            { 
                System.out.println(e1);
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                //kiírja a hibaüzenetet
            }  
                               
         }
    }
    
    class Gyártásban_levo implements ActionListener                                                                                      //csv-t gyárt a gomb
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {                              
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                Workbook workbook = new Workbook();
                Worksheet sheet = workbook.getWorksheets().get(0);
                DataTable datatable = new DataTable();                            
                
                DriverManager.registerDriver(new oracle.jdbc.OracleDriver());                
                Class.forName("oracle.jdbc.OracleDriver");  //.driver                                    
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@IFSORA.IFS.videoton.hu:1521/IFSPROD","ZKOVACS","ZKOVACS");                                      
                Statement stmt = con.createStatement();                                    
                
                ResultSet rs = stmt.executeQuery("select b2.TRACY_ID, b2.TRACY_STATUS, b2.TRACY_SERIAL_NO, b2.PART_NO, b2.BOM_REVISION, b2.REVISION_TEXT, b2.ALT_TRACY_SERIAL_NO1,\r\n"
                        + "        b2.SPEC01, b2.PACKAGE_ID, b2.LAST_OPERATION_NO, to_char(b2.FIRST_OP_TRACY_DATE,'YYYY.MM.DD') FIRST_OP_TRACY_DATE,\r\n"
                        + "        to_char(b2.ACT_PART_NO_FIRST_OP_DATE,'YYYY.MM.DD') ACT_PART_NO_FIRST_OP_DATE, b2.OPER_TRACY_ID, to_char(b2.MANUF_DATE,'YYYY.MM.DD') MANUF_DATE,to_char(b2.PROCESS_DATE,'YYYY.MM.DD') PROCESS_DATE,b2.WORK_CENTER_NO,\r\n"
                        + "        b2.RESOURCE_ID, b2.SCAN_LOC, b2.OPERATION_NO, b2.PASS, b2.OPER_TRACY_TYPE, b2.REPETITIONS, b2.REPET_LAST_REPAIR, b2.LAST_OPER_TRACY,\r\n"
                        + "        to_char(b2.LAST_OP_TRACY_DATE,'YYYY.MM.DD') LAST_OP_TRACY_DATE, b2.LAST_NONREP_OPER_TRACY, b2.REPAIR_TYPE, b2.ERRORCODE, b2.FAILTESTNAME, b2.REPAIR_EVENT_POS_LIST\r\n"
                        + "       ,max(case when LAST_NONREP_OPER_TRACY = 'Y' \r\n"
                        + "                  then case when pass = 'N' \r\n"
                        + "                                 THEN 'Y'\r\n"
                        + "                       else 'N'\r\n"
                        + "                       end\r\n"
                        + "        end) over (partition by tracy_id) LAST_TEST_FAIL\r\n"
                        + "       ,sum(case when oper_tracy_type = 'RPR' and REPAIR_TYPE in ('Repaired', 'Javítás kész') then 1 else 0 end) over (partition by tracy_id) SUM_REPAIR\r\n"
                        + "       ,round(sysdate - ACT_PART_NO_FIRST_OP_DATE) DAYS_IN_MANUF\r\n"
                        + "       --,LAST_OP_tracy_Date\r\n"
                        + "       ,round(sysdate - LAST_OP_tracy_Date)DAYS_SINCE_LAST_MOVE\r\n"
                        + "       ,case when round(sysdate - ACT_PART_NO_FIRST_OP_DATE) <35 THEN '0-5 hét'\r\n"
                        + "             when round(sysdate - ACT_PART_NO_FIRST_OP_DATE) <70 THEN '5-10 hét'\r\n"
                        + "             when round(sysdate - ACT_PART_NO_FIRST_OP_DATE) <100 THEN '10+ hét'\r\n"
                        + "        else '10+ hét'  end AGE_OF_PRODUCT\r\n"
                        + "       ,case when round(sysdate - LAST_OP_tracy_Date) <10 then '0_InProgress_0-9'\r\n"
                        + "             when round(sysdate - LAST_OP_tracy_Date) <30 then '1_Stopped_10-29'\r\n"
                        + "        else '2_StoppedLongAgo_30+' end PROGRESS\r\n"
                        + "       ,case when sum(case when oper_tracy_type = 'RPR' and REPAIR_TYPE in ('Repaired', 'Javítás kész') then 1 else 0 end) over (partition by tracy_id) /*SUM_REPAIR*/  <4  /*akkor önmaga*/\r\n"
                        + "                  then to_char(sum(case when oper_tracy_type = 'RPR' and REPAIR_TYPE in ('Repaired', 'Javítás kész') then 1 else 0 end) over (partition by tracy_id))\r\n"
                        + "             when sum(case when oper_tracy_type = 'RPR' and REPAIR_TYPE in ('Repaired', 'Javítás kész') then 1 else 0 end) over (partition by tracy_id) <7 \r\n"
                        + "                  then '4_Many_4-6'\r\n"
                        + "        else '5_Lot_7+' end NUM_OF_REPAIR_CYCLES\r\n"
                        + "        ,max(case when b2.last_oper_tracy = 'Y' \r\n"
                        + "                       then case when b2.package_id >0\r\n"
                        + "                                      then '5_Csomagolt'\r\n"
                        + "                            else case when REPAIR_TYPE in ('Repaired', 'Javítás kész')\r\n"
                        + "                                           then '4_Visszaadásra vár'\r\n"
                        + "                                 else case when REPAIR_TYPE in ('Repairing', 'Javítás')\r\n"
                        + "                                                then '3_Javításon'\r\n"
                        + "                                      else case when (pass= 'N' and OPER_TRACY_TYPE <> 'RPR')\r\n"
                        + "                                                     then '2_Javításra vár'\r\n"
                        + "                                                when (pass = 'Y' and OPER_TRACY_TYPE <> 'RPR')\r\n"
                        + "                                                     then '1_Gyártásban'\r\n"
                        + "                                           else 'Nem kategorizált'\r\n"
                        + "                                          end\r\n"
                        + "                                     end\r\n"
                        + "                                end\r\n"
                        + "                            end\r\n"
                        + "             else null\r\n"
                        + "             end) over (partition by b2.tracy_id)  STATUS\r\n"
                        + "  from (select b.tracy_id\r\n"
                        + "             ,b.tracy_status\r\n"
                        + "             ,b.tracy_serial_no\r\n"
                        + "             ,b.part_no\r\n"
                        + "             ,b.bom_revision\r\n"
                        + "             ,ifsapp.PART_REVISION_API.Get_Revision_Text(b.contract,b.part_no, b.bom_revision) revision_text\r\n"
                        + "             ,b.alt_tracy_serial_no1\r\n"
                        + "             ,b.spec01\r\n"
                        + "             ,b.package_id\r\n"
                        + "             ,b.last_operation_no\r\n"
                        + "             --\r\n"
                        + "             ,b.first_op_tracy_date --tracy_head_create_date\r\n"
                        + "             ,b.ACT_PART_NO_FIRST_OP_DATE\r\n"
                        + "             ,b.oper_tracy_id\r\n"
                        + "             ,b.manuf_date\r\n"
                        + "             ,b.process_Date\r\n"
                        + "             ,b.work_center_no\r\n"
                        + "             ,b.resource_id\r\n"
                        + "             ,b.scan_loc\r\n"
                        + "             ,b.operation_no\r\n"
                        + "             ,b.pass\r\n"
                        + "             ,b.oper_tracy_type\r\n"
                        + "             ,case when b.oper_TRacy_type = 'RPR' then row_number() over (partition by b.tracy_id, b.oper_tracy_type order by nvl(b.manuf_date, b.process_Date_orig))\r\n"
                        + "                   else b.repetitions\r\n"
                        + "              end repetitions\r\n"
                        + "             ,case when b.oper_TRacy_type = 'RPR' then count(case when lag_rep_type in( 'Repaired', 'Javítás kész')\r\n"
                        + "                                                                       then 1\r\n"
                        + "                                                             else null end) over (partition by b.tracy_id, b.oper_tracy_type order by nvl(b.manuf_date, b.process_Date_orig))\r\n"
                        + "                                                             +1\r\n"
                        + "                                                                       \r\n"
                        + "                   else b.repet_last_repair\r\n"
                        + "              end repet_last_repair\r\n"
                        + "             ,case when b.rn = 1 then 'Y' else 'N' END LAST_OPER_TRACY\r\n"
                        + "             ,max(case when b.rn = 1 then b.process_Date else null END) over (partition by b.tracy_id) LAST_OP_tracy_Date\r\n"
                        + "             ,case when b.rep_rn = 1 AND b.oper_tracy_type <> 'RPR' then 'Y' else 'N' end LAST_NONREP_OPER_TRACY\r\n"
                        + "             ,b.REPAIR_TYPE\r\n"
                        + "             ,ERRORCODE\r\n"
                        + "             ,FAILTESTNAME\r\n"
                        + "             ,REPAIR_EVENT_POS_LIST\r\n"
                        + "        from (select t.contract, \r\n"
                        + "                     t.tracy_id\r\n"
                        + "                     ,t.tracy_status\r\n"
                        + "                     ,t.tracy_serial_no\r\n"
                        + "                     ,t.part_no\r\n"
                        + "                     ,t.bom_revision\r\n"
                        + "                     ,t.alt_tracy_serial_no1\r\n"
                        + "                     ,t.spec01\r\n"
                        + "                     ,t.package_id\r\n"
                        + "                     ,t.last_operation_no\r\n"
                        + "                     --\r\n"
                        + "                     ,min(trunc(nvl(ot.manuf_date, ot.process_date))) over (partition by ot.tracy_id) first_op_tracy_date --tracy_head_create_date\r\n"
                        + "                     ,min(case when ot.operation_no = vtapp.V_REPORT_HELPER_API.v_get_1st_trace_op_no(t.contract, t.part_no, '*', '1') \r\n"
                        + "                               then trunc(nvl(ot.manuf_date, ot.process_date))\r\n"
                        + "                          else to_date('9999.12.31', 'YYYY.MM.DD')\r\n"
                        + "                          end) over (partition by ot.tracy_id) ACT_PART_NO_FIRST_OP_DATE\r\n"
                        + "                     ,ot.oper_tracy_id\r\n"
                        + "                     ,ot.manuf_date,'YYYY-MM-DD'\r\n"
                        + "                     ,trunc(ot.process_date) process_Date\r\n"
                        + "                     ,ot.process_date process_Date_orig\r\n"
                        + "                     ,ot.work_center_no\r\n"
                        + "                     ,ot.resource_id\r\n"
                        + "                     ,ot.scan_loc\r\n"
                        + "                     ,ot.operation_no\r\n"
                        + "                     ,ot.pass\r\n"
                        + "                     ,ot.oper_tracy_type\r\n"
                        + "                     ,ot.repetitions\r\n"
                        + "                     ,ot.repet_last_repair\r\n"
                        + "                     ,row_number() over (partition by ot.tracy_id order by nvl(manuf_date, process_date) desc) rn\r\n"
                        + "                     ,row_number() over (partition by ot.tracy_id, case when ot.oper_tracy_type = 'RPR' then 1 else 2 end  order by nvl(manuf_date, process_date)desc ) REP_rn --(legutolsó javítási eseményhez)\r\n"
                        + "                     --spec_error:\r\n"
                        + "                     ,case when ot.pass = 'N' \r\n"
                        + "                           then (select max( case when s.tracy_spec_code = 'ERRORCODE' then s.alfa_num_value else null end)\r\n"
                        + "                                  from ifsapp.C_SPEC_TRACY_UNIT_FULL s\r\n"
                        + "                                where 1=1 \r\n"
                        + "                                      and s.tracy_id = ot.tracy_id\r\n"
                        + "                                      and s.oper_tracy_id = ot.oper_tracy_id\r\n"
                        + "                                      /*and s.tracy_spec_code = 'ERRORCODE'*/)\r\n"
                        + "                      else null end ERRORCODE\r\n"
                        + "                     ,case when ot.pass = 'N' \r\n"
                        + "                           then (select max( case when s.tracy_spec_code = 'FAILTESTNAME' then s.alfa_num_value else null end)\r\n"
                        + "                                  from ifsapp.C_SPEC_TRACY_UNIT_FULL s\r\n"
                        + "                                where 1=1 \r\n"
                        + "                                      and s.tracy_id = ot.tracy_id\r\n"
                        + "                                      and s.oper_tracy_id = ot.oper_tracy_id\r\n"
                        + "                                      /*and s.tracy_spec_code = 'FAILTESTNAME'*/)\r\n"
                        + "                      else null end FAILTESTNAME        \r\n"
                        + "                      --rep:\r\n"
                        + "                      ,rep.REPAIR_EVENT_POS_LIST\r\n"
                        + "                      ,rep.event_type REPAIR_TYPE\r\n"
                        + "                      ,lag(event_type,1,0) over (partition by ot.tracy_id, ot.oper_tracy_type order by nvl(manuf_Date, process_Date)) lag_rep_type\r\n"
                        + "                from ifsapp.c_tracy t\r\n"
                        + "                     ,ifsapp.c_oper_tracy ot\r\n"
                        + "                     ,(select min(event_Type) event_Type\r\n"
                        + "                              ,listagg(r.position, ';') within group (order by r.repair_id) REPAIR_EVENT_POS_LIST\r\n"
                        + "                              ,r.tracy_id\r\n"
                        + "                              ,r.oper_tracy_id\r\n"
                        + "                         from ifsapp.C_TRACY_REP_EVENT_EXT r\r\n"
                        + "                       where 1=1\r\n"
                        + "                      group by r.tracy_id, r.oper_tracy_id\r\n"
                        + "                       )rep\r\n"
                        + "              where 1=1 \r\n"
                        + "                    and t.tracy_id = ot.tracy_id\r\n"
                        + "                    --\r\n"
                        + "                    and ot.tracy_id = rep.tracy_ID (+)\r\n"
                        + "                    and ot.oper_tracy_id = rep.oper_tracy_id (+)\r\n"
                        + "                    and t.part_no like 'PROD%'\r\n"
                        + "                    and nvl(t.package_id, '1') = case when upper(nvl('Y', 'XXX')) not in ('XXX', '0', 'N')\r\n"
                        + "                                                           then 1 \r\n"
                        + "                                                 else nvl(t.package_id, '1')  end\r\n"
                        + "                    --and t.tracy_id in (53414770,47460051, 45166422) --45874124 --45861009\r\n"
                        + "                    and t.tracy_status not in ('SLD', 'STK', 'SCR', 'SML','TST', 'GLD') -- csak a gyártásban lév? - készlet és eladott kizárva SCRAP sem kell\r\n"
                        + "                    and ot.oper_tracy_type not in ('HDCHG')\r\n"
                        + "                    --and t.tracy_serial_no = '227009381413B'\r\n"
                        + "                    --and t.tracy_id = 46715722\r\n"
                        + "                    --\r\n"
                        + "              )b      \r\n"
                        + "        )b2\r\n"
                        + "order by tracy_id , oper_tracy_id");
                
                JdbcAdapter jdbcAdapter = new JdbcAdapter();
                jdbcAdapter.fillDataTable(datatable, rs);
                sheet.insertDataTable(datatable, true, 1, 1);
                
                //sheet2.insertDataTable(datatable2, true, 1, 1);
                sheet.getAutoFilters().setRange(sheet.getCellRange("A1:AL1"));
                sheet.getAllocatedRange().autoFitColumns();
                sheet.getAllocatedRange().autoFitRows();
                sheet.getCellRange("A1:AL1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                String hova = System.getProperty("user.home") + "\\Desktop\\Telecom gyártásban levő termékek.xlsx";
                workbook.saveToFile(hova, ExcelVersion.Version2016);
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
                JOptionPane.showMessageDialog(null, "Kész! \n Mentve az asztalra Telecom gyártásban levő termékek.xlsx néven!", "Info", 1); 
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
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                //kiírja a hibaüzenetet
            }  
                               
         }
    }
}
