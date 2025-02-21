import javax.swing.JPanel;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.spire.data.table.DataTable;
import com.spire.data.table.common.JdbcAdapter;
import com.spire.xls.ExcelVersion;
import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;

import javax.swing.JButton;
import javax.swing.JFileChooser;
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

public class IFS_archive extends JPanel {

    /**
     * Create the panel.
     */
    public IFS_archive() {
        setLayout(null);
        
        JButton folyamat_gomb = new JButton("Megnyitás");
        folyamat_gomb.setBounds(619, 191, 108, 23);
        folyamat_gomb.addActionListener(new Lekerdezes_folyamat());
        add(folyamat_gomb);
        
        JLabel lblNewLabel = new JLabel("Arhive traceability adatok lekérdezése");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setBounds(458, 81, 312, 14);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Tracy folyamatok lekérdezés");
        lblNewLabel_1.setBounds(423, 195, 186, 14);
        add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Tracy jellemzők lekérdezése");
        lblNewLabel_2.setBounds(423, 251, 186, 14);
        add(lblNewLabel_2);
        
        JButton jellemzo_gomb = new JButton("Megnyitás");
        jellemzo_gomb.addActionListener(new Lekerdezes_jellemzo());
        jellemzo_gomb.setBounds(619, 247, 108, 23);
        add(jellemzo_gomb);
        
        JLabel lblNewLabel_3 = new JLabel("ME szám mibe épült");
        lblNewLabel_3.setBounds(423, 301, 139, 14);
        add(lblNewLabel_3);
        
        JButton meszam_gomb = new JButton("Megnyitás");
        meszam_gomb.addActionListener(new Lekerdezes_me_szam());
        meszam_gomb.setBounds(619, 297, 108, 23);
        add(meszam_gomb);
        
        setBackground(Foablak.hatter_szine);
        
        JButton szeriaszam_gomb = new JButton("Megnyitás");
        szeriaszam_gomb.addActionListener(new Lekerdezes_Szeriaszam_beepules());
        szeriaszam_gomb.setBounds(619, 349, 108, 23);
        add(szeriaszam_gomb);
        
        JLabel lblNewLabel_4 = new JLabel("Szériaszámba mi épült");
        lblNewLabel_4.setBounds(423, 353, 167, 14);
        add(lblNewLabel_4);

    }
    
    class Lekerdezes_folyamat implements ActionListener                                                                                      //Archive folyamatokban keres szériaszámok alapján
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {              
                JFileChooser mentes_helye = new JFileChooser();
                mentes_helye.setCurrentDirectory(new java.io.File(System.getProperty("user.home") + "\\Desktop\\"));                        //asztalon nyílik meg a JFileChooser
                mentes_helye.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                mentes_helye.showOpenDialog(mentes_helye);
                File fajl = mentes_helye.getSelectedFile();
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                Workbook workbook = new Workbook();                                                                                         //excel munkafüzet létrehozása
                workbook.loadFromFile(fajl.getAbsolutePath());                                                                              //fájl betöltése
                Worksheet sheet = workbook.getWorksheets().get(0);
                DataTable datatable = new DataTable();
                DataTable datatable2 = new DataTable();
                datatable = sheet.exportDataTable(sheet.getAllocatedRange(), false, false );                
                Workbook workbook2 = new Workbook();
                Worksheet sheet2 = workbook2.getWorksheets().get(0);
                
                String osszefuzott = "";
                for(int szamlalo = 0; szamlalo < datatable.getRows().size(); szamlalo++)                                                    //beolvasott szériaszámokat összefűző for ciklus
                {
                    osszefuzott += "'"+ datatable.getRows().get(szamlalo).getString(0) +"',";
                }
                osszefuzott = osszefuzott.substring(0, osszefuzott.length() - 1);                                                           //az utolsó vessző levágása
                
                DriverManager.registerDriver(new oracle.jdbc.OracleDriver());                
                Class.forName("oracle.jdbc.OracleDriver");  //.driver                                    
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@IFSORA.IFS.videoton.hu:1521/IFSPROD","ZKOVACS","ZKOVACS");  //kacsolat létrehozása                                      
                Statement stmt = con.createStatement();                                    
                
                ResultSet rs = stmt.executeQuery("select c.TRACY_ID,\r\n"
                        + "       c.contract,\r\n"
                        + "       c.PART_NO,\r\n"
                        + "       c.TRACY_SERIAL_NO,\r\n"
                        + "       c.ALT_TRACY_SERIAL_NO1,\r\n"
                        + "       c.ALT_TRACY_SERIAL_NO2,\r\n"
                        + "       c.ALT_TRACY_SERIAL_NO3,\r\n"
                        + "       c.SPEC01,\r\n"
                        + "       c.SPEC02,\r\n"
                        + "       c.SPEC03,\r\n"
                        + "       nvl(o.DATA_ARCHIVE_DATE,  o2.DATA_ARCHIVE_DATE) DATA_ARCHIVE_DATE,\r\n"
                        + "       nvl(o.OPER_TRACY_ID,      o2.OPER_TRACY_ID) OPER_TRACY_ID,\r\n"
                        + "       nvl(o.OPERATION_NO,       o2.OPERATION_NO) OPERATION_NO,\r\n"
                        + "       nvl(o.WORK_CENTER_NO,     o2.WORK_CENTER_NO) WORK_CENTER_NO,\r\n"
                        + "       nvl(o.RESOURCE_ID,        o2.RESOURCE_ID) RESOURCE_ID,\r\n"
                        + "       nvl(o.SCAN_LOC,           o2.SCAN_LOC)SCAN_LOC,\r\n"
                        + "       nvl(o.MANUF_DATE,         o2.MANUF_DATE) MANUF_DATE,\r\n"
                        + "       nvl(o.PROCESS_DATE,       o2.PROCESS_DATE) PROCESS_DATE,\r\n"
                        + "       nvl(o.PASS,               o2.PASS) PASS,\r\n"
                        + "       nvl(o.CURRENT_HEAD_STATE, o2.CURRENT_HEAD_STATE) CURRENT_HEAD_STATE,\r\n"
                        + "       nvl(o.NEW_HEAD_STATE,     o2.NEW_HEAD_STATE) NEW_HEAD_STATE,\r\n"
                        + "       nvl(o.OPER_TRACY_TYPE,    o2.OPER_TRACY_TYPE) OPER_TRACY_TYPE,\r\n"
                        + "       nvl(o.QTY,                o2.QTY) QTY,\r\n"
                        + "       nvl(o.MESSAGE,            o2.MESSAGE) MESSAGE,\r\n"
                        + "       nvl(o.HISTORY_ID,         o2.HISTORY_ID) HISTORY_ID,\r\n"
                        + "       nvl(o.ROWVERSION,         o2.ROWVERSION) ROWVERSION,\r\n"
                        + "       nvl(o.ROWSTATE,           o2.ROWSTATE) ROWSTATE,\r\n"
                        + "       nvl(o.REPETITIONS,        o2.REPETITIONS) REPETITIONS,\r\n"
                        + "       nvl(o.REPET_LAST_REPAIR,  o2.REPET_LAST_REPAIR) REPET_LAST_REPAIR,\r\n"
                        + "       nvl(o.EMPLOYEE_ID,         o2.EMPLOYEE_ID) EMPLOYEE_ID\r\n"
                        + "  from ifsapp.C_TRACY c\r\n"
                        + "       ,ifsapp.C_OPER_TRACY_ARC o\r\n"
                        + "       ,ifsapp.C_OPER_TRACY_24_ARC o2\r\n"
                        + " where 1 = 1\r\n"
                        + "   and c.TRACY_ID = o.TRACY_ID (+)\r\n"
                        + "   and c.TRACY_ID = o2.TRACY_ID (+)\r\n"
                        + "      --\r\n"
                        + "   and (c.TRACY_SERIAL_NO in ("+ osszefuzott +") --'2EQF T85D' \r\n"
                        + "        or c.ALT_TRACY_SERIAL_NO1 in ("+ osszefuzott +") \r\n"
                        + "        or c.ALT_TRACY_SERIAL_NO2 in ("+ osszefuzott +") \r\n"
                        + "        or c.ALT_TRACY_SERIAL_NO3 in ("+ osszefuzott +"))\r\n"
                        + "order by nvl(o.OPER_TRACY_ID, o2.OPER_TRACY_ID) desc");
                
                JdbcAdapter jdbcAdapter = new JdbcAdapter();
                jdbcAdapter.fillDataTable(datatable2, rs);                                                                                          //result eredményét belerakja egy datattable-ba
                int cellaszam = 1;
                sheet2.getRange().get("A" + cellaszam).setText("Tracy ID");                                                                         //Excelben az oszlopok neveinek megadáa
                sheet2.getRange().get("B" + cellaszam).setText("Contract");
                sheet2.getRange().get("C" + cellaszam).setText("Cikkszám");
                sheet2.getRange().get("D" + cellaszam).setText("Tracy gyári szám");
                sheet2.getRange().get("E" + cellaszam).setText("Alternatív gyári szám 1");
                sheet2.getRange().get("F" + cellaszam).setText("Alternatív gyári szám 2");
                sheet2.getRange().get("G" + cellaszam).setText("Alternatív gyári szám 3");
                sheet2.getRange().get("H" + cellaszam).setText("Spec01");
                sheet2.getRange().get("I" + cellaszam).setText("Spec02");
                sheet2.getRange().get("J" + cellaszam).setText("Spec03");
                sheet2.getRange().get("K" + cellaszam).setText("Archiválás dátuma");
                sheet2.getRange().get("L" + cellaszam).setText("Opter Tray ID");
                sheet2.getRange().get("M" + cellaszam).setText("Oper no");
                sheet2.getRange().get("N" + cellaszam).setText("Munkaállomás száma");
                sheet2.getRange().get("O" + cellaszam).setText("Resource id");
                sheet2.getRange().get("P" + cellaszam).setText("Scan loc");
                sheet2.getRange().get("Q" + cellaszam).setText("Gyártás dátuma");
                sheet2.getRange().get("R" + cellaszam).setText("Felhasználás dátuma");
                sheet2.getRange().get("S" + cellaszam).setText("Pass");
                sheet2.getRange().get("T" + cellaszam).setText("Fej státusz");
                sheet2.getRange().get("U" + cellaszam).setText("Új fej státusz");
                sheet2.getRange().get("V" + cellaszam).setText("Trac folyamat");
                sheet2.getRange().get("W" + cellaszam).setText("Qty");
                sheet2.getRange().get("X" + cellaszam).setText("Message");
                sheet2.getRange().get("Y" + cellaszam).setText("History ID");
                sheet2.getRange().get("Z" + cellaszam).setText("Row version");
                sheet2.getRange().get("AA" + cellaszam).setText("Row state");
                sheet2.getRange().get("AB" + cellaszam).setText("Ismétlések száma");
                sheet2.getRange().get("AC" + cellaszam).setText("Javítás óta ismétlés száma");
                sheet2.getRange().get("AD" + cellaszam).setText("Dolgozó kódja");
                cellaszam++;
                for(int szamlalo = 0; szamlalo < datatable2.getRows().size(); szamlalo++)                                                       //adatok bemásolása az excel táblába
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
                    sheet2.getRange().get("S" + cellaszam).setText(datatable2.getRows().get(szamlalo).getString(18));
                    sheet2.getRange().get("T" + cellaszam).setText(datatable2.getRows().get(szamlalo).getString(19));
                    sheet2.getRange().get("U" + cellaszam).setText(datatable2.getRows().get(szamlalo).getString(20));
                    sheet2.getRange().get("V" + cellaszam).setText(datatable2.getRows().get(szamlalo).getString(21));
                    sheet2.getRange().get("W" + cellaszam).setText(datatable2.getRows().get(szamlalo).getString(22));
                    sheet2.getRange().get("X" + cellaszam).setText(datatable2.getRows().get(szamlalo).getString(23));
                    sheet2.getRange().get("Y" + cellaszam).setText(datatable2.getRows().get(szamlalo).getString(24));
                    sheet2.getRange().get("Z" + cellaszam).setText(datatable2.getRows().get(szamlalo).getString(25));
                    sheet2.getRange().get("AA" + cellaszam).setText(datatable2.getRows().get(szamlalo).getString(26));
                    sheet2.getRange().get("AB" + cellaszam).setText(datatable2.getRows().get(szamlalo).getString(27));
                    sheet2.getRange().get("AC" + cellaszam).setText(datatable2.getRows().get(szamlalo).getString(28));
                    sheet2.getRange().get("AD" + cellaszam).setText(datatable2.getRows().get(szamlalo).getString(29));
                    cellaszam++;
                }
                //sheet2.insertDataTable(datatable2, true, 1, 1);
                sheet2.getAutoFilters().setRange(sheet2.getCellRange("A1:AD1"));
                sheet2.getAllocatedRange().autoFitColumns();                                                                                    //automata mértezés oszlopokra
                sheet2.getAllocatedRange().autoFitRows();                                                                                       //automata mértezés sorokra
                sheet2.getCellRange("A1:AD1").getCellStyle().getExcelFont().isBold(true);                                                       // félkövér beállítás
                String hova = System.getProperty("user.home") + "\\Desktop\\IFS Archive folyamat adatok.xlsx";                                  //mentés helye 
                workbook2.saveToFile(hova, ExcelVersion.Version2016);
                FileInputStream fileStream = new FileInputStream(hova);
                try (XSSFWorkbook workbook3 = new XSSFWorkbook(fileStream)) 
                {
                    for(int i = workbook3.getNumberOfSheets()-1; i > 0 ;i--)                                                                    //felesleges sheet-ek kitörlése
                    {    
                        workbook3.removeSheetAt(i); 
                    }      
                    FileOutputStream output = new FileOutputStream(hova);
                    workbook3.write(output);
                    output.close();
                }
                JOptionPane.showMessageDialog(null, "Kész! \n Mentve az asztalra IFS Archive folyamat adatok.xlsx néven!", "Info", 1);          //értesítés a mentésről
                con.close();  
                Foablak.frame.setCursor(null);  
            }                      
            catch(Exception e1)                                                                                                                 //kivétel esetén történik
            { 
                System.out.println(e1);
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                             //kiírja a hibaüzenetet
            }  
                               
         }
    }
    
    class Lekerdezes_jellemzo implements ActionListener                                                                                         //Jellemzőkben keres
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
                //DataTable datatable2 = new DataTable();
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
                
                ResultSet rs = stmt.executeQuery("select c.TRACY_ID,\r\n"
                        + "       c.contract,\r\n"
                        + "       c.PART_NO,\r\n"
                        + "       c.TRACY_SERIAL_NO,\r\n"
                        + "       c.ALT_TRACY_SERIAL_NO1,\r\n"
                        + "       c.ALT_TRACY_SERIAL_NO2,\r\n"
                        + "       c.ALT_TRACY_SERIAL_NO3,\r\n"
                        + "       c.SPEC01,\r\n"
                        + "       c.SPEC02,\r\n"
                        + "       c.SPEC03,\r\n"
                        + "       nvl(o.DATA_ARCHIVE_DATE,  o2.DATA_ARCHIVE_DATE) DATA_ARCHIVE_DATE,\r\n"
                        + "       nvl(o.OPER_TRACY_ID,      o2.OPER_TRACY_ID) OPER_TRACY_ID,\r\n"
                        + "       nvl(o.OPERATION_NO,       o2.OPERATION_NO) OPERATION_NO,\r\n"
                        + "       nvl(o.WORK_CENTER_NO,     o2.WORK_CENTER_NO) WORK_CENTER_NO,\r\n"
                        + "       nvl(o.RESOURCE_ID,        o2.RESOURCE_ID) RESOURCE_ID,\r\n"
                        + "       nvl(o.SCAN_LOC,           o2.SCAN_LOC)SCAN_LOC,\r\n"
                        + "       nvl(o.MANUF_DATE,         o2.MANUF_DATE) MANUF_DATE,\r\n"
                        + "       nvl(o.PROCESS_DATE,       o2.PROCESS_DATE) PROCESS_DATE,\r\n"
                        + "       nvl(o.PASS,               o2.PASS) PASS,\r\n"
                        + "       nvl(o.CURRENT_HEAD_STATE, o2.CURRENT_HEAD_STATE) CURRENT_HEAD_STATE,\r\n"
                        + "       nvl(o.NEW_HEAD_STATE,     o2.NEW_HEAD_STATE) NEW_HEAD_STATE,\r\n"
                        + "       nvl(o.OPER_TRACY_TYPE,    o2.OPER_TRACY_TYPE) OPER_TRACY_TYPE,\r\n"
                        + "       nvl(o.QTY,                o2.QTY) QTY,\r\n"
                        + "       nvl(o.MESSAGE,            o2.MESSAGE) MESSAGE,\r\n"
                        + "       nvl(o.HISTORY_ID,         o2.HISTORY_ID) HISTORY_ID,\r\n"
                        + "       nvl(o.ROWVERSION,         o2.ROWVERSION) ROWVERSION,\r\n"
                        + "       nvl(o.ROWSTATE,           o2.ROWSTATE) ROWSTATE,\r\n"
                        + "       nvl(o.REPETITIONS,        o2.REPETITIONS) REPETITIONS,\r\n"
                        + "       nvl(o.REPET_LAST_REPAIR,  o2.REPET_LAST_REPAIR) REPET_LAST_REPAIR,\r\n"
                        + "       nvl(o.EMPLOYEE_ID,         o2.EMPLOYEE_ID) EMPLOYEE_ID,\r\n"
                        + "       a.tracy_spec_code,\r\n"
                        + "       a.unit_code,\r\n"
                        + "       a.min_value,\r\n"
                        + "       a.max_value,\r\n"
                        + "       nvl(s.NUM_VALUE,s2.NUM_VALUE) NUM_VALUE,\r\n"
                        + "       nvl(s.ALFA_NUM_VALUE, s2.ALFA_NUM_VALUE) ALFA_NUM_VALUE,\r\n"
                        + "       nvl(s.COMMENTS, s2.COMMENTS) COMMENTS,\r\n"
                        + "       nvl(s.OPER_TRACY_ID, s2.OPER_TRACY_ID) OPER_TRACY_ID, \r\n"
                        + "       nvl(s.CREATED_BY, s2.CREATED_BY) CREATED_BY, \r\n"
                        + "       nvl(s.ROWVERSION, s2.ROWVERSION) ROWVERSION,\r\n"
                        + "       nvl(s.SPEC_TRACY_UNIT_AUX_ID, s2.SPEC_TRACY_UNIT_AUX_ID) SPEC_TRACY_UNIT_AUX_ID,\r\n"
                        + "       nvl(s.CACHED, s2.CACHED) CACHED, \r\n"
                        + "       nvl(s.SPEC_PASS, s2.SPEC_PASS) SPEC_PASS\r\n"
                        + "  from ifsapp.C_TRACY c, \r\n"
                        + "       ifsapp.C_OPER_TRACY_ARC o\r\n"
                        + "       ,ifsapp.C_OPER_TRACY_24_ARC o2\r\n"
                        + "       ,ifsapp.C_SPEC_TRACY_UNIT_ARC s\r\n"
                        + "       ,ifsapp.C_SPEC_TRACY_UNIT_24_ARC s2\r\n"
                        + "       ,ifsapp.C_SPEC_TRACY_UNIT_AUX a\r\n"
                        + " where 1 = 1\r\n"
                        + "   and c.TRACY_ID = o.TRACY_ID (+)\r\n"
                        + "   and c.TRACY_ID = o2.TRACY_ID (+)\r\n"
                        + "      --\r\n"
                        + "   and nvl(o.TRACY_ID, o2.TRACY_ID) = s.TRACY_ID  (+)\r\n"
                        + "   and nvl(o.OPER_TRACY_ID, o2.OPER_TRACY_ID) = s.OPER_TRACY_ID (+)\r\n"
                        + "   --\r\n"
                        + "   and nvl(o.TRACY_ID, o2.TRACY_ID) = s2.TRACY_ID  (+)\r\n"
                        + "   and nvl(o.OPER_TRACY_ID, o2.OPER_TRACY_ID) = s2.OPER_TRACY_ID (+)\r\n"
                        + "   and nvl(s.SPEC_TRACY_UNIT_AUX_ID,s.SPEC_TRACY_UNIT_AUX_ID) = a.spec_tracy_unit_aux_id(+)\r\n"
                        + "      --\r\n"
                        + "   and (c.TRACY_SERIAL_NO  in ("+ osszefuzott +") --'2EQF T85D' \r\n"
                        + "        or c.ALT_TRACY_SERIAL_NO1  in ("+ osszefuzott +") \r\n"
                        + "        or c.ALT_TRACY_SERIAL_NO2  in ("+ osszefuzott +") \r\n"
                        + "        or c.ALT_TRACY_SERIAL_NO3  in ("+ osszefuzott +"))\r\n"
                        + "order by nvl(o.OPER_TRACY_ID, o2.OPER_TRACY_ID) desc, nvl(s.SPEC_TRACY_UNIT_ID, s.SPEC_TRACY_UNIT_ID) desc");
                
                //JdbcAdapter jdbcAdapter = new JdbcAdapter();
                //jdbcAdapter.fillDataTable(datatable2, rs);
                int cellaszam = 1;
                sheet2.getRange().get("A" + cellaszam).setText("Tracy ID");
                sheet2.getRange().get("B" + cellaszam).setText("Contract");
                sheet2.getRange().get("C" + cellaszam).setText("Cikkszám");
                sheet2.getRange().get("D" + cellaszam).setText("Tracy gyári szám");
                sheet2.getRange().get("E" + cellaszam).setText("Alternatív gyári szám 1");
                sheet2.getRange().get("F" + cellaszam).setText("Alternatív gyári szám 2");
                sheet2.getRange().get("G" + cellaszam).setText("Alternatív gyári szám 3");
                sheet2.getRange().get("H" + cellaszam).setText("Spec01");
                sheet2.getRange().get("I" + cellaszam).setText("Spec02");
                sheet2.getRange().get("J" + cellaszam).setText("Spec03");
                sheet2.getRange().get("K" + cellaszam).setText("Archiválás dátuma");
                sheet2.getRange().get("L" + cellaszam).setText("Opter Tray ID");
                sheet2.getRange().get("M" + cellaszam).setText("Oper no");
                sheet2.getRange().get("N" + cellaszam).setText("Munkaállomás száma");
                sheet2.getRange().get("O" + cellaszam).setText("Resource id");
                sheet2.getRange().get("P" + cellaszam).setText("Scan loc");
                sheet2.getRange().get("Q" + cellaszam).setText("Gyártás dátuma");
                sheet2.getRange().get("R" + cellaszam).setText("Felhasználás dátuma");
                sheet2.getRange().get("S" + cellaszam).setText("Pass");
                sheet2.getRange().get("T" + cellaszam).setText("Fej státusz");
                sheet2.getRange().get("U" + cellaszam).setText("Új fej státusz");
                sheet2.getRange().get("V" + cellaszam).setText("Trac folyamat");
                sheet2.getRange().get("W" + cellaszam).setText("Qty");
                sheet2.getRange().get("X" + cellaszam).setText("Message");
                sheet2.getRange().get("Y" + cellaszam).setText("History ID");
                sheet2.getRange().get("Z" + cellaszam).setText("Row version");
                sheet2.getRange().get("AA" + cellaszam).setText("Row state");
                sheet2.getRange().get("AB" + cellaszam).setText("Ismétlések száma");
                sheet2.getRange().get("AC" + cellaszam).setText("Javítás óta ismétlés száma");
                sheet2.getRange().get("AD" + cellaszam).setText("Dolgozó kódja");
                
                sheet2.getRange().get("AE" + cellaszam).setText("Tracy ID");
                sheet2.getRange().get("AF" + cellaszam).setText("Tracy spec kód");
                sheet2.getRange().get("AG" + cellaszam).setText("Unit code");
                sheet2.getRange().get("AH" + cellaszam).setText("Min érték");
                sheet2.getRange().get("AI" + cellaszam).setText("Max érták");
                sheet2.getRange().get("AJ" + cellaszam).setText("Numerikus érték");
                sheet2.getRange().get("AK" + cellaszam).setText("Alfa numerikus érték");
                sheet2.getRange().get("AL" + cellaszam).setText("Komment");
                sheet2.getRange().get("AM" + cellaszam).setText("Oper Tracy ID");
                sheet2.getRange().get("AN" + cellaszam).setText("Létrhozta");
                sheet2.getRange().get("AO" + cellaszam).setText("Row version");
                sheet2.getRange().get("AP" + cellaszam).setText("SPEC_TRACY_UNIT_AUX_ID");
                sheet2.getRange().get("AQ" + cellaszam).setText("Cached");
                //sheet2.getRange().get("AR" + cellaszam).setText("SPEC_Pass");
                cellaszam++;
                while(rs.next())
                {
                    sheet2.getRange().get("A" + cellaszam).setText(rs.getString(1));
                    sheet2.getRange().get("B" + cellaszam).setText(rs.getString(2));
                    sheet2.getRange().get("C" + cellaszam).setText(rs.getString(3));
                    sheet2.getRange().get("D" + cellaszam).setText(rs.getString(4));
                    sheet2.getRange().get("E" + cellaszam).setText(rs.getString(5));
                    sheet2.getRange().get("F" + cellaszam).setText(rs.getString(6));
                    sheet2.getRange().get("G" + cellaszam).setText(rs.getString(7));
                    sheet2.getRange().get("H" + cellaszam).setText(rs.getString(8));
                    sheet2.getRange().get("I" + cellaszam).setText(rs.getString(9));
                    sheet2.getRange().get("J" + cellaszam).setText(rs.getString(10));
                    sheet2.getRange().get("K" + cellaszam).setText(rs.getString(11));
                    sheet2.getRange().get("L" + cellaszam).setText(rs.getString(12));
                    sheet2.getRange().get("M" + cellaszam).setText(rs.getString(13));
                    sheet2.getRange().get("N" + cellaszam).setText(rs.getString(14));
                    sheet2.getRange().get("O" + cellaszam).setText(rs.getString(15));
                    sheet2.getRange().get("P" + cellaszam).setText(rs.getString(16));
                    sheet2.getRange().get("Q" + cellaszam).setText(rs.getString(17));
                    sheet2.getRange().get("R" + cellaszam).setText(rs.getString(18));
                    sheet2.getRange().get("S" + cellaszam).setText(rs.getString(19));
                    sheet2.getRange().get("T" + cellaszam).setText(rs.getString(20));
                    sheet2.getRange().get("U" + cellaszam).setText(rs.getString(21));
                    sheet2.getRange().get("V" + cellaszam).setText(rs.getString(22));
                    sheet2.getRange().get("W" + cellaszam).setText(rs.getString(23));
                    sheet2.getRange().get("X" + cellaszam).setText(rs.getString(24));
                    sheet2.getRange().get("Y" + cellaszam).setText(rs.getString(25));
                    sheet2.getRange().get("Z" + cellaszam).setText(rs.getString(26));
                    sheet2.getRange().get("AA" + cellaszam).setText(rs.getString(27));
                    sheet2.getRange().get("AB" + cellaszam).setText(rs.getString(28));
                    sheet2.getRange().get("AC" + cellaszam).setText(rs.getString(29));
                    sheet2.getRange().get("AD" + cellaszam).setText(rs.getString(30));
                    
                    sheet2.getRange().get("AE" + cellaszam).setText(rs.getString(31));
                    sheet2.getRange().get("AF" + cellaszam).setText(rs.getString(32));
                    sheet2.getRange().get("AG" + cellaszam).setText(rs.getString(33));
                    sheet2.getRange().get("AH" + cellaszam).setText(rs.getString(34));
                    sheet2.getRange().get("AI" + cellaszam).setText(rs.getString(35));
                    sheet2.getRange().get("AJ" + cellaszam).setText(rs.getString(36));
                    sheet2.getRange().get("AK" + cellaszam).setText(rs.getString(37));
                    sheet2.getRange().get("AL" + cellaszam).setText(rs.getString(38));
                    sheet2.getRange().get("AM" + cellaszam).setText(rs.getString(39));
                    sheet2.getRange().get("AN" + cellaszam).setText(rs.getString(40));
                    sheet2.getRange().get("AO" + cellaszam).setText(rs.getString(41));
                    sheet2.getRange().get("AP" + cellaszam).setText(rs.getString(42));
                    sheet2.getRange().get("AQ" + cellaszam).setText(rs.getString(43));
                    //sheet2.getRange().get("AR" + cellaszam).setText(rs.getString(44));
                    cellaszam++;
                }
                //sheet2.insertDataTable(datatable2, true, 1, 1);
                sheet2.getAutoFilters().setRange(sheet2.getCellRange("A1:AR1"));
                sheet2.getAllocatedRange().autoFitColumns();
                sheet2.getAllocatedRange().autoFitRows();
                sheet2.getCellRange("A1:AR1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                String hova = System.getProperty("user.home") + "\\Desktop\\IFS Archive jellemző adatok.xlsx";
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
                JOptionPane.showMessageDialog(null, "Kész! \n Mentve az asztalra IFS Archive jellemző adatok.xlsx néven!", "Info", 1); 
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
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                         //kiírja a hibaüzenetet
            }  
                               
         }
    }
    
    class Lekerdezes_me_szam implements ActionListener                                                                                      //ME szám alaján keres az archivban
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
                
                ResultSet rs = stmt.executeQuery("SELECT  c.TRACY_SERIAL_NO Szériaszám,\r\n"
                        + "        m.BARCODE_ID ME_szám,\r\n"
                        + "        o.MANUF_DATE Csomagolás_ideje\r\n"
                        + "FROM ifsapp.C_TRACY_ARC c, ifsapp.C_MTRL_TRACY_ARC m,  ifsapp.C_OPER_TRACY_ARC o   \r\n"
                        + "WHERE 3 = 3\r\n"
                        + "      and c.TRACY_ID = m.TRACY_ID\r\n"
                        + "      and c.TRACY_ID = o.TRACY_ID\r\n"
                        + "      and m.BARCODE_ID in ("+ osszefuzott +")\r\n"
                        + "      and o.MANUF_DATE = (select max(o.MANUF_DATE) from ifsapp.C_OPER_TRACY_ARC o where c.TRACY_ID = o.TRACY_ID)");
                
                JdbcAdapter jdbcAdapter = new JdbcAdapter();
                jdbcAdapter.fillDataTable(datatable2, rs);
                //sheet2.insertDataTable(datatable2, true, 1, 1);
                int cellaszam = 1;
                sheet2.getRange().get("A" + cellaszam).setText("Szériaszám");
                sheet2.getRange().get("B" + cellaszam).setText("ME szám");
                sheet2.getRange().get("C" + cellaszam).setText("Csomagolás ideje");                
                cellaszam++;
                for(int szamlalo = 0; szamlalo < datatable2.getRows().size(); szamlalo++)
                {
                    sheet2.getRange().get("A" + cellaszam).setText(datatable2.getRows().get(szamlalo).getString(0));
                    sheet2.getRange().get("B" + cellaszam).setText(datatable2.getRows().get(szamlalo).getString(1));
                    sheet2.getRange().get("C" + cellaszam).setText(datatable2.getRows().get(szamlalo).getString(2));                    
                    cellaszam++;
                }
                //sheet2.insertDataTable(datatable2, true, 1, 1);
                sheet2.getAutoFilters().setRange(sheet2.getCellRange("A1:AD1"));
                sheet2.getAllocatedRange().autoFitColumns();
                sheet2.getAllocatedRange().autoFitRows();
                sheet2.getCellRange("A1:AD1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                String hova = System.getProperty("user.home") + "\\Desktop\\IFS Archive ME szám beépülés.xlsx";
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
                JOptionPane.showMessageDialog(null, "Kész! \n Mentve az asztalra IFS Archive ME szám beépülés.xlsx néven!", "Info", 1); 
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
    }
    
    class Lekerdezes_Szeriaszam_beepules implements ActionListener                                                                                      //csv-t gyárt a gomb
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
                
                ResultSet rs = stmt.executeQuery("select c.TRACY_ID,\r\n"
                        + "       c.contract,\r\n"
                        + "       c.PART_NO,\r\n"
                        + "       c.TRACY_SERIAL_NO,\r\n"
                        + "       c.ALT_TRACY_SERIAL_NO1,\r\n"
                        + "       c.ALT_TRACY_SERIAL_NO2,\r\n"
                        + "       c.ALT_TRACY_SERIAL_NO3,\r\n"
                        + "       c.SPEC01,\r\n"
                        + "       c.SPEC02,\r\n"
                        + "       c.SPEC03,\r\n"
                        + "       nvl(o.DATA_ARCHIVE_DATE,  o2.DATA_ARCHIVE_DATE) DATA_ARCHIVE_DATE,\r\n"
                        + "       nvl(o.OPER_TRACY_ID,      o2.OPER_TRACY_ID) OPER_TRACY_ID,\r\n"
                        + "       nvl(o.OPERATION_NO,       o2.OPERATION_NO) OPERATION_NO,\r\n"
                        + "       nvl(o.WORK_CENTER_NO,     o2.WORK_CENTER_NO) WORK_CENTER_NO,\r\n"
                        + "       nvl(o.RESOURCE_ID,        o2.RESOURCE_ID) RESOURCE_ID,\r\n"
                        + "       nvl(o.SCAN_LOC,           o2.SCAN_LOC)SCAN_LOC,\r\n"
                        + "       nvl(o.MANUF_DATE,         o2.MANUF_DATE) MANUF_DATE,\r\n"
                        + "       nvl(o.PROCESS_DATE,       o2.PROCESS_DATE) PROCESS_DATE,\r\n"
                        + "       nvl(o.PASS,               o2.PASS) PASS,\r\n"
                        + "       nvl(o.CURRENT_HEAD_STATE, o2.CURRENT_HEAD_STATE) CURRENT_HEAD_STATE,\r\n"
                        + "       nvl(o.NEW_HEAD_STATE,     o2.NEW_HEAD_STATE) NEW_HEAD_STATE,\r\n"
                        + "       nvl(o.OPER_TRACY_TYPE,    o2.OPER_TRACY_TYPE) OPER_TRACY_TYPE,\r\n"
                        + "       nvl(o.QTY,                o2.QTY) QTY,\r\n"
                        + "       nvl(o.MESSAGE,            o2.MESSAGE) MESSAGE,\r\n"
                        + "       nvl(o.HISTORY_ID,         o2.HISTORY_ID) HISTORY_ID,\r\n"
                        + "       nvl(o.ROWVERSION,         o2.ROWVERSION) ROWVERSION,\r\n"
                        + "       nvl(o.ROWSTATE,           o2.ROWSTATE) ROWSTATE,\r\n"
                        + "       nvl(o.REPETITIONS,        o2.REPETITIONS) REPETITIONS,\r\n"
                        + "       nvl(o.REPET_LAST_REPAIR,  o2.REPET_LAST_REPAIR) REPET_LAST_REPAIR,\r\n"
                        + "       nvl(o.EMPLOYEE_ID,         o2.EMPLOYEE_ID) EMPLOYEE_ID,\r\n"
                        + "       nvl(m.MAT_TRACY_ID, m2.MAT_TRACY_ID) MAT_TRACY_ID,\r\n"
                        + "       nvl(m.OPER_TRACY_ID,m2.OPER_TRACY_ID) OPER_TRACY_ID,\r\n"
                        + "       nvl(m.QTY_CONSUMED, m2.QTY_CONSUMED) QTY_CONSUMED,\r\n"
                        + "       nvl(m.POSITIONS_CSV, m2.POSITIONS_CSV) POSITIONS_CSV,\r\n"
                        + "       nvl(m.CONSUMPTION_DATE, m2.CONSUMPTION_DATE) CONSUMPTION_DATE,\r\n"
                        + "       nvl(m.BARCODE_ID, m2.BARCODE_ID) BARCODE_ID,\r\n"
                        + "       nvl(m.CREATION_DATE, m2.CREATION_DATE) CREATION_DATE,\r\n"
                        + "       nvl(m.ROWVERSION, m2.ROWVERSION) v\r\n"
                        + "  from ifsapp.C_TRACY c\r\n"
                        + "       ,ifsapp.C_OPER_TRACY_ARC o\r\n"
                        + "       ,ifsapp.C_OPER_TRACY_24_ARC o2\r\n"
                        + "       ,ifsapp.C_MTRL_TRACY_ARC m\r\n"
                        + "       ,ifsapp.C_MTRL_TRACY_24_ARC m2\r\n"
                        + " where 1 = 1\r\n"
                        + "   and c.TRACY_ID = o.TRACY_ID (+)\r\n"
                        + "   and c.TRACY_ID = o2.TRACY_ID (+)\r\n"
                        + "   --\r\n"
                        + "   and nvl(o.TRACY_ID, o2.TRACY_ID) = m.TRACY_ID (+)\r\n"
                        + "   and nvl(o.OPER_TRACY_ID, o2.OPER_TRACY_ID)  = m.OPER_TRACY_ID (+)\r\n"
                        + "   --\r\n"
                        + "   and nvl(o.TRACY_ID, o2.TRACY_ID) = m2.TRACY_ID (+)\r\n"
                        + "   and nvl(o.OPER_TRACY_ID, o2.OPER_TRACY_ID)  = m2.OPER_TRACY_ID (+)\r\n"
                        + "   --\r\n"
                        + "   and (c.TRACY_SERIAL_NO in ("+ osszefuzott +") --'2EQF T85D' \r\n"
                        + "        or c.ALT_TRACY_SERIAL_NO1 in ("+ osszefuzott +") \r\n"
                        + "        or c.ALT_TRACY_SERIAL_NO2 in ("+ osszefuzott +") \r\n"
                        + "        or c.ALT_TRACY_SERIAL_NO3 in ("+ osszefuzott +"))\r\n"
                        + "order by nvl(o.OPER_TRACY_ID, o2.OPER_TRACY_ID) desc,nvl(m.MAT_TRACY_ID, m2.MAT_TRACY_ID) desc");
                
                JdbcAdapter jdbcAdapter = new JdbcAdapter();
                jdbcAdapter.fillDataTable(datatable2, rs);
                sheet2.insertDataTable(datatable2, true, 1, 1);
                /*int cellaszam = 1;
                sheet2.getRange().get("A" + cellaszam).setText("Szériaszám");
                sheet2.getRange().get("B" + cellaszam).setText("ME szám");
                sheet2.getRange().get("C" + cellaszam).setText("Csomagolás ideje");                
                cellaszam++;
                for(int szamlalo = 0; szamlalo < datatable2.getRows().size(); szamlalo++)
                {
                    sheet2.getRange().get("A" + cellaszam).setText(datatable2.getRows().get(szamlalo).getString(0));
                    sheet2.getRange().get("B" + cellaszam).setText(datatable2.getRows().get(szamlalo).getString(1));
                    sheet2.getRange().get("C" + cellaszam).setText(datatable2.getRows().get(szamlalo).getString(2));                    
                    cellaszam++;
                }*/
                //sheet2.insertDataTable(datatable2, true, 1, 1);
                sheet2.getAutoFilters().setRange(sheet2.getCellRange("A1:AD1"));
                sheet2.getAllocatedRange().autoFitColumns();
                sheet2.getAllocatedRange().autoFitRows();
                sheet2.getCellRange("A1:AD1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                String hova = System.getProperty("user.home") + "\\Desktop\\IFS Archive szériaszám beépülések.xlsx";
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
                JOptionPane.showMessageDialog(null, "Kész! \n Mentve az asztalra IFS Archive szériaszám beépülések néven!", "Info", 1); 
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
    }
}
