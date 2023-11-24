import javax.swing.JPanel;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.spire.data.table.DataTable;
import com.spire.data.table.common.JdbcAdapter;
import com.spire.xls.ExcelVersion;
import com.spire.xls.HorizontalAlignType;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JButton;

public class Techem_OQC extends JPanel {
    
    private String excelfile = "\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\OQC_control_datasheet.xlsx";   
    
    /**
     * Create the panel.
     */
    public Techem_OQC() {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Techem OQC adatok lekérdezése");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 13));
        lblNewLabel.setBounds(462, 40, 314, 14);
        add(lblNewLabel);
        
        JButton start_gomb = new JButton("Start");
        start_gomb.addActionListener(new Lekerdezes());
        start_gomb.setBounds(522, 198, 89, 23);
        add(start_gomb);
        
        setBackground(Foablak.hatter_szine);

    }
    
    class Lekerdezes implements ActionListener                                                                                      //csv-t gyárt a gomb
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {              
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                                                //egér mutató változtatása munka a háttérbenre
                Workbook workbook = new Workbook();
                Workbook workbook2 = new Workbook();
                workbook.loadFromFile(excelfile);              
                Worksheet sheet = workbook.getWorksheets().get(0);
                Worksheet sheet2 = workbook2.getWorksheets().get(0);
                DataTable datatable = new DataTable();
                
                DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                Class.forName("oracle.jdbc.OracleDriver");  //.driver
                                    
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@IFSORA.IFS.videoton.hu:1521/IFSPROD","ZKOVACS","ZKOVACS");                                      
                Statement stmt = con.createStatement();  
                //ResultSet rs2 = null;
                //ResultSet rs3 = null;
                //Connection conn = null;
                //Statement stmt2 = null;        
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
                Connection con2 = (Connection) DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
                Statement stmt2 = (Statement) con2.createStatement();
                
                String mysqlUrl = "jdbc:mysql://192.168.5.145/";                                                                        //mysql szerver ipcÃ­mÃ©hez valÃ³ csatlakozÃ¡s
                Connection con3 = DriverManager.getConnection(mysqlUrl, "quality", "Qua25!");
                Statement stmt3 = con3.createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = stmt.executeQuery("Select contract, part_no, location_no, waiv_dev_rej_no, availability_control_id, to_char(RECEIPT_DATE ,'YYYY.MM.DD') from ifsapp.INVENTORY_PART_IN_STOCK_UIV\r\n"
                        + "where PART_NO = '1742' and QTY_ONHAND > 0\r\n"
                        + "and location_no like 'TE%'");
                JdbcAdapter jdbcAdapter = new JdbcAdapter();
                jdbcAdapter.fillDataTable(datatable, rs);
                sheet2.insertDataTable(datatable, true, 1, 1);
                
                stmt.execute("Select location_no from ifsapp.INVENTORY_PART_IN_STOCK_UIV\r\n"
                        + "where PART_NO = '1742' and QTY_ONHAND > 0\r\n"
                        + "and location_no like 'TE%' group by location_no");
                //int szam = 1;
                
                rs = stmt.getResultSet();
                ArrayList<String> raklapok = new ArrayList<String>();
                ArrayList<String> szeriaszamok = new ArrayList<String>();
                ArrayList<String> meszamok = new ArrayList<String>();
                while(rs.next())
                {
                    raklapok.add(rs.getString(1));
                }
                for(int szamlalo = 0; szamlalo < raklapok.size(); szamlalo++)      //raklapok.size()
                {
                    stmt.execute("Select waiv_dev_rej_no from ifsapp.INVENTORY_PART_IN_STOCK_UIV\r\n"
                            + "where location_no = '"+ raklapok.get(szamlalo) +"'");
                    
                    rs = stmt.getResultSet();
                    int vanilyen = 0;
                    int cellaszam = 6;
                    int megjegyzes = 39;
                    int sorszam = 1;
                    meszamok.clear();
                    while(rs.next())
                    {
                        meszamok.add(rs.getString(1));
                    }
                    for(int szamlalo3 = 0; szamlalo3 < meszamok.size(); szamlalo3++)
                    {
                        if(meszamok.get(szamlalo3).equals("*")) {}
                        else
                        {
                            ResultSet rs2 = stmt.executeQuery("select tracy_serial_no \r\n"
                                    + "from ifsapp.C_TRACY\r\n"
                                    + "where PACKAGE_ID = '"+ meszamok.get(szamlalo3) +"'");
                            //System.out.println("Nézi a raklapokat");
                            szeriaszamok.clear();
                            while(rs2.next())
                            {
                                szeriaszamok.add(rs2.getString(1));
                            }
                            for(int szamlalo2 = 0; szamlalo2 < szeriaszamok.size(); szamlalo2++)
                            {
                                ResultSet rs3 = stmt2.executeQuery("select * from qualitydb.Techem_OQC where Szeriaszam like '"+ szeriaszamok.get(szamlalo2) +"%'");                        
                                if(rs3.next())
                                {
                                    String[] datum = rs3.getString(2).split(" ");
                                    sheet.getRange().get("D" + 2).setText(datum[0]);
                                    sheet.getRange().get("J" + 2).setText(rs3.getString(3));
                                    sheet.getRange().get("B" + cellaszam).setText(rs3.getString(4));
                                    sheet.getRange().get("C" + cellaszam).setText(rs3.getString(5));
                                    sheet.getRange().get("D" + cellaszam).setText(rs3.getString(6));
                                    sheet.getRange().get("E" + cellaszam).setText(rs3.getString(7));
                                    sheet.getRange().get("F" + cellaszam).setText(rs3.getString(8));
                                    sheet.getRange().get("G" + cellaszam).setText(rs3.getString(9));
                                    sheet.getRange().get("H" + cellaszam).setText(rs3.getString(10));
                                    sheet.getRange().get("I" + cellaszam).setText(rs3.getString(11));
                                    sheet.getRange().get("J" + cellaszam).setText(rs3.getString(12));
                                    sheet.getRange().get("K" + cellaszam).setText(rs3.getString(13));
                                    sheet.getRange().get("L" + cellaszam).setText(rs3.getString(14));
                                    
                                    rs = stmt3.executeQuery("select max(ido)\r\n"
                                            + "from videoton.fkov\r\n"
                                            + "where\r\n"
                                            + "videoton.fkov.hely = \"93\"   and\r\n"
                                            + "videoton.fkov.panel like \"4TCH%\" \r\n"
                                            + "and poz = 2");
                                    if(rs.next())
                                    {
                                        SimpleDateFormat hosszu = new SimpleDateFormat("yyyy.MM.dd");
                                        Date mikor = hosszu.parse(rs.getString(1));
                                        Calendar cal = Calendar.getInstance(); // creates calendar                                        
                                        cal.setTime(mikor);               // sets calendar time/date
                                        //cal.add(Calendar.HOUR_OF_DAY, 300);      // adds one hour
                                        //cal.getTime();                         // returns new date object plus one hour
                                        //System.out.println(sheet.getRange().get("D" + 2).getText());
                                        String[] pontos = sheet.getRange().get("D" + 2).getText().split("-"); 
                                        Date ellenorzes = hosszu.parse(pontos[0]+"."+pontos[1]+"."+pontos[2]); 
                                        //System.out.println("Tagolt dátum: "+pontos[0]+"."+pontos[1]+"."+pontos[2]);
                                        if(ellenorzes.compareTo(mikor) > 0) 
                                        {
                                            //System.out.println("Múltban Ellenorzes ideje: "+ ellenorzes + "   Berakva idő: "+ mikor);
                                            cal.add(Calendar.HOUR_OF_DAY, 600);
                                            String[] lejar = hosszu.format(cal.getTime()).split(" ");
                                            String szoveg = "Result after " + lejar[0];
                                            sheet.getRange().get("M" + cellaszam).setText(szoveg);
                                            sheet.getCellRange("M" + cellaszam +":O"+ cellaszam).getCellStyle().setHorizontalAlignment(HorizontalAlignType.Center);
                                        }
                                        else
                                        {
                                            cal.add(Calendar.HOUR_OF_DAY, 300);
                                            String[] lejar = hosszu.format(cal.getTime()).split(" ");
                                            String szoveg = "Result after " + lejar[0];
                                            sheet.getRange().get("M" + cellaszam).setText(szoveg);
                                            sheet.getCellRange("M" + cellaszam +":O"+ cellaszam).getCellStyle().setHorizontalAlignment(HorizontalAlignType.Center);
                                        }
                                    }
                                    if(rs3.getString(15) == null) {}
                                    else if(rs3.getString(15).equals(""))  {}
                                    else
                                    {
                                        sheet.getRange().get("D" + megjegyzes).setText(sorszam +" "+rs3.getString(15));
                                        megjegyzes++;
                                        sorszam++;
                                    }
                                    
                                    cellaszam++;
                                    vanilyen++;
                                }                        
                            }
                        }

                    }
                    if(vanilyen > 0)
                    {
                        File f = new File(System.getProperty("user.home") + "\\Desktop\\Techem OQC\\Techem OQC_Rhsz_"+ raklapok.get(szamlalo) +".xlsx");          //\\Techem OQC
                        f.getParentFile().mkdirs(); 
                        f.createNewFile();                
                        String hova = f.getAbsolutePath();
                        workbook.getConverterSetting().setSheetFitToPage(true);
                        workbook.saveToFile(hova, ExcelVersion.Version2016);                                //ExcelVersion.Version2016
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
                    } 
                }
                
                
                sheet2.getAutoFilters().setRange(sheet.getCellRange("A1:AC1"));
                sheet2.getAllocatedRange().autoFitColumns();
                sheet2.getAllocatedRange().autoFitRows();
                
                sheet.getCellRange("A1:AC1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                File f = new File(System.getProperty("user.home") + "\\Desktop\\Techem OQC\\Raktárban levő anyag.xlsx");         //Techem OQC\\
                f.getParentFile().mkdirs(); 
                f.createNewFile();
                String hova = f.getAbsolutePath();
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

                con.close(); 
                con2.close();
                con3.close();
                
                Process p = Runtime.getRuntime().exec("java -jar \\\\172.20.22.7\\kozos\\Jar-ok\\PDF_keszito.jar");               
                System.out.println(p);
                
                JOptionPane.showMessageDialog(null, "Mentve az asztalra", "Info", 1); 
                Foablak.frame.setCursor(null); 
            }           
            catch(Exception e1)
            { 
                System.out.println(e1);
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                Foablak.frame.setCursor(null);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                              //kiírja a hibaüzenetet
            }  
                               
         }
    }

}
