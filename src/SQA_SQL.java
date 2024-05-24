import java.awt.Cursor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.JOptionPane;
import javax.swing.JFormattedTextField.AbstractFormatter;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.spire.data.table.DataTable;
import com.spire.data.table.common.JdbcAdapter;
import com.spire.xls.ExcelVersion;
import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;

public class SQA_SQL {
    
    private final String emaillista = "\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\SQA\\SQA email lista.xlsx";
    private final String emaillista_retour = "\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\Projekt- mérnök.xlsx";
    
    public String[] cikkszamok()
    {
        String[] cikkbox = null;
        try
        {            
            Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            Class.forName("oracle.jdbc.OracleDriver");  //.driver
                                
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@IFSORA.IFS.videoton.hu:1521/IFSPROD","ZKOVACS","ZKOVACS");                                      
            Statement stmt = con.createStatement();              
            ResultSet rs = stmt.executeQuery("select PART_NO , DESCRIPTION \r\n"
                    + "from ifsapp.INVENTORY_PART\r\n"
                    + "where 3 = 3 order by part_no asc");
            ArrayList<String> cikkszamok = new ArrayList<String>();
            while(rs.next())
            {
                cikkszamok.add(rs.getString(1)+ " "+ rs.getString(2));
            }
            cikkbox = cikkszamok.toArray(new String[cikkszamok.size()]);
            con.close();  
            Foablak.frame.setCursor(null);        
        }           
        catch(Exception e1)
        { 
            System.out.println(e1);
            e1.printStackTrace();
            String hibauzenet = e1.toString();
            Email hibakuldes = new Email();
            hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", this.getClass().getSimpleName()+" "+ hibauzenet);
            JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                               //kiírja a hibaüzenetet
        }
        return cikkbox;  
                           
     }
    
    public String beszallito(String sql)
    {
        String cikkbox = null;
        try
        {            
            Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            Class.forName("oracle.jdbc.OracleDriver");  //.driver
                                
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@IFSORA.IFS.videoton.hu:1521/IFSPROD","ZKOVACS","ZKOVACS");                                      
            Statement stmt = con.createStatement();              
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next())
            {
                cikkbox = rs.getString(1);
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
            hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", this.getClass().getSimpleName()+" "+ hibauzenet);
            JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                 //kiírja a hibaüzenetet
        }
        return cikkbox;  
                           
     }
    
    public String[] tombvissza(String sql)
    {
        //String cikkbox[] = null;
        ArrayList<String> adatok = new ArrayList<String>();
        try
        {            
            Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            Class.forName("oracle.jdbc.OracleDriver");  //.driver
                                
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@IFSORA.IFS.videoton.hu:1521/IFSPROD","ZKOVACS","ZKOVACS");                                      
            Statement stmt = con.createStatement();              
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                adatok.add(rs.getString(1));
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
            hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", this.getClass().getSimpleName()+" "+ hibauzenet);
            JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                 //kiírja a hibaüzenetet
        }
        String[] cikkbox = adatok.toArray(new String[adatok.size()]);
        return cikkbox;  
                           
     }
    
    public String[] tombvissza_sajat(String sql)
    {
        //String cikkbox[] = null;
        ArrayList<String> adatok = new ArrayList<String>();
        try
        {            
            Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            Class.forName("com.mysql.cj.jdbc.Driver");  //.driver
                                
            Connection con = DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");                                      
            Statement stmt = con.createStatement();              
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                adatok.add(rs.getString(1));
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
            hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", this.getClass().getSimpleName()+" "+ hibauzenet);
            JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                 //kiírja a hibaüzenetet
        }
        String[] cikkbox = adatok.toArray(new String[adatok.size()]);
        return cikkbox;  
                           
     }
    
    public void mindenes(String SQL)
    {
        Connection conn = null;
        Statement stmt = null;        
        try 
        {
           try 
           {
              Class.forName("com.mysql.cj.jdbc.Driver");
           } 
           catch (Exception e) 
           {
              System.out.println(e);
              String hibauzenet2 = e.toString();
              JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
        }
        conn = (Connection) DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
        stmt = (Statement) conn.createStatement();
        stmt.executeUpdate(SQL);
        
        stmt.close();
        conn.close();
                
        }          
        catch (Exception e) 
        {
            e.printStackTrace();
            String hibauzenet = e.toString();
            Email hibakuldes = new Email();
            hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", this.getClass().getSimpleName()+" "+ hibauzenet);
            JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                     //kivétel esetén kiírja a hibaüzenetet
        } finally 
        {
           try 
           {
              if (stmt != null)
                 conn.close();
           } 
           catch (SQLException se) {}
           try 
           {
              if (conn != null)
                 conn.close();
           } 
           catch (SQLException se) 
           {
               se.printStackTrace();
               String hibauzenet = se.toString();
               Email hibakuldes = new Email();
               hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", this.getClass().getSimpleName()+" "+ hibauzenet);
               JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                //kivétel esetén kiírja a hibaüzenetet
           }  
        }
        //JOptionPane.showMessageDialog(null, "Kész", "Info", 1);
    }
    
    public void sqa_email()
    {
        Connection conn = null;
        Statement stmt = null;        
        DataTable datatable = new DataTable();        
        ResultSet resultSet;       
        try 
        {
           try 
           {
              Class.forName("com.mysql.cj.jdbc.Driver");
           } 
           catch (Exception e) 
           {
              System.out.println(e);
              String hibauzenet2 = e.toString();
              JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
           }
        conn = DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);       
        String sql = "select id, inditotta,Statusz, Statusz_ido, DATEDIFF(now(), Statusz_ido) as 'Kulonbseg'\r\n"
                + ", Ertesitve, Lezaras_ido from qualitydb.SQA_reklamaciok\r\n"
                + "where 3 = 3";                                        
        stmt.execute(sql);      
        resultSet = stmt.getResultSet();
        ArrayList<String> cimzettek = new ArrayList<String>();
        while(resultSet.next())
        {
            if(resultSet.getString(5) != null)
            {
                if(resultSet.getInt(5) >=7)
                {
                    if(resultSet.getString(6).equals("Nem"))
                    {
                        if(resultSet.getString(7) == null)
                        {
                            cimzettek.add(resultSet.getString(1)+";"+resultSet.getString(2)+";"+resultSet.getString(3)+";"+resultSet.getString(4));
                        }
                    }                   
                }
            }
        }
        if(cimzettek.size() >= 1 )
        {
            for(int szamlalo = 0; szamlalo < cimzettek.size();szamlalo++)
            {
                Workbook workbook = new Workbook();
                workbook.loadFromFile(emaillista);
                Worksheet sheet = workbook.getWorksheets().get(0);
                datatable = sheet.exportDataTable(sheet.getAllocatedRange(), false, false );
                Email emailkuldes = new Email();
                String[] adatok = cimzettek.get(szamlalo).split(";");
                String emailcim = "";
                for(int szamlalo2 = 0; szamlalo2 < datatable.getRows().size();szamlalo2++)
                {
                    if(datatable.getRows().get(szamlalo2).getString(0).equals(adatok[1]) )
                    {
                        emailcim = datatable.getRows().get(szamlalo2).getString(1);
                    }
                }
                if(emailcim.equals("")) {}
                else
                {
                    emailkuldes.sqa_emailkuldes(adatok[1],emailcim,emailcim,adatok[0],adatok[3],adatok[2]);
                    String modosit = "update qualitydb.SQA_reklamaciok set  Ertesitve = 'Igen' where ID = '"+ adatok[0]  +"'";
                    stmt.executeUpdate(modosit);
                }
            }
        }
        
        System.out.println("Lefutott az SQA Email rész");
        resultSet.close();
        stmt.close();
        conn.close();
        
        } 
        catch (SQLException e1) 
        {
           e1.printStackTrace();
           String hibauzenet = e1.toString();
           Email hibakuldes = new Email();
           hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", this.getClass().getSimpleName()+" "+ hibauzenet);
           JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
        } 
        catch (Exception e) 
        {
           e.printStackTrace();
           String hibauzenet = e.toString();
           Email hibakuldes = new Email();
           hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", this.getClass().getSimpleName()+" "+ hibauzenet);
           JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
        } 
        finally 
        {
           try 
           {
              if (stmt != null)
                 conn.close();
           } 
           catch (SQLException se) {}
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
    }
    
    public void retour_email()
    {
        Connection conn = null;
        Statement stmt = null;        
        DataTable datatable = new DataTable();
        DataTable datatable2 = new DataTable(); 
        ResultSet resultSet;       
        try 
        {
           try 
           {
              Class.forName("com.mysql.cj.jdbc.Driver");
           } 
           catch (Exception e) 
           {
              System.out.println(e);
              String hibauzenet2 = e.toString();
              JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
           }
        conn = DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);       
        String sql = "select * \r\n"
                + "from qualitydb.Retour\r\n"
                + "where 3 = 3 and modositas_datuma is not null and Raktar_datum is null and Ertesitve = 'Nem'\r\n"
                + "and DATEDIFF(now(), Modositas_datuma) >= 14\r\n"
                + "order by Vevo asc";                                        
        stmt.execute(sql);      
        resultSet = stmt.getResultSet();
        JdbcAdapter jdbcAdapter = new JdbcAdapter();
        jdbcAdapter.fillDataTable(datatable2, resultSet);
        
        Workbook workbook = new Workbook();
        Workbook workbook2 = new Workbook();
        workbook.loadFromFile(emaillista_retour);
        Worksheet sheet = workbook.getWorksheets().get(0);
        Worksheet sheet2 = workbook2.getWorksheets().get(0);
        datatable = sheet.exportDataTable(sheet.getAllocatedRange(), false, false );
        Email emailkuldes = new Email();
        
        int cellaszam = 1;
        sheet2.getRange().get("A" + cellaszam).setText("ID");
        sheet2.getRange().get("B" + cellaszam).setText("Dátum");
        sheet2.getRange().get("C" + cellaszam).setText("Vevő");
        sheet2.getRange().get("D" + cellaszam).setText("Cikkszám");
        sheet2.getRange().get("E" + cellaszam).setText("Vagy");
        sheet2.getRange().get("F" + cellaszam).setText("Beérkezett");
        sheet2.getRange().get("G" + cellaszam).setText("Eltérés");
        sheet2.getRange().get("H" + cellaszam).setText("RMA");
        sheet2.getRange().get("I" + cellaszam).setText("Megjegyzés");
        sheet2.getRange().get("J" + cellaszam).setText("Hova");
        sheet2.getRange().get("K" + cellaszam).setText("Hova dátum");
        sheet2.getRange().get("L" + cellaszam).setText("Hova felelős");
        sheet2.getRange().get("M" + cellaszam).setText("TEszt dátum");
        sheet2.getRange().get("N" + cellaszam).setText("Teszt felelős");
        sheet2.getRange().get("O" + cellaszam).setText("Végellenőrzés ideje");
        sheet2.getRange().get("P" + cellaszam).setText("Végellenőrzés felelős");
        sheet2.getRange().get("Q" + cellaszam).setText("Raktárra adás dátuma");
        sheet2.getRange().get("R" + cellaszam).setText("Raktárra adott db");
        sheet2.getRange().get("S" + cellaszam).setText("Selejt");
        sheet2.getRange().get("T" + cellaszam).setText("Vevői RMA");
        sheet2.getRange().get("U" + cellaszam).setText("Vevői hibalírás");        
        sheet2.getRange().get("V" + cellaszam).setText("Módosítás dátuma");
        cellaszam++;
        String id = "";
        for(int szamlalo = 0; szamlalo < datatable2.getRows().size();szamlalo++)
        {
            if(szamlalo+1 < datatable2.getRows().size())
            {
                if(datatable2.getRows().get(szamlalo).getString(2).equals(datatable2.getRows().get(szamlalo+1).getString(2)))
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
                    cellaszam++;
                    id += datatable2.getRows().get(szamlalo).getString(0)+",";
                }
                else
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
                    id += datatable2.getRows().get(szamlalo).getString(0)+",";
                    
                    sheet2.getAutoFilters().setRange(sheet2.getCellRange("A1:AD1"));
                    sheet2.getAllocatedRange().autoFitColumns();
                    sheet2.getAllocatedRange().autoFitRows();
                    sheet2.getCellRange("A1:AD1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                    String hova = System.getProperty("user.home") + "\\"+datatable2.getRows().get(szamlalo).getString(2)+".xlsx";
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
                    sheet2.deleteRow(2,sheet2.getLastRow());
                    String emailcim = "";
                    for(int szamlalo2 = 0; szamlalo2 < datatable.getRows().size();szamlalo2++)
                    {
                        if(datatable.getRows().get(szamlalo2).getString(0).toLowerCase().contains(datatable2.getRows().get(szamlalo).getString(2).toLowerCase()) )
                        {
                            emailcim = datatable.getRows().get(szamlalo2).getString(2)+", kovacs.sandor@veas.videoton.hu, reznyak.norbert@veas.videoton.hu";
                        }
                    }
                    //emailcim = "kovacs.zoltan@veas.videoton.hu";
                    File excel = new File(System.getProperty("user.home") + "\\"+datatable2.getRows().get(szamlalo).getString(2)+".xlsx");
                    emailkuldes.retour_emailkuldes("easqas@veas.videoton.hu", emailcim, datatable2.getRows().get(szamlalo).getString(2), excel);
                    id = id.substring(0, id.length() - 1);
                    String modosit = "update qualitydb.Retour set  Ertesitve = 'Igen' where ID in ("+ id  +")";
                    stmt.executeUpdate(modosit);
                    excel.delete();
                    id = "";
                    cellaszam = 2;
                }
            }
            else
            {
                if(datatable2.getRows().size() == 1)
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
                    
                    sheet2.getAutoFilters().setRange(sheet2.getCellRange("A1:AD1"));
                    sheet2.getAllocatedRange().autoFitColumns();
                    sheet2.getAllocatedRange().autoFitRows();
                    sheet2.getCellRange("A1:AD1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                    String hova = System.getProperty("user.home") + "\\"+datatable2.getRows().get(szamlalo).getString(2)+".xlsx";
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
                    sheet2.deleteRow(2,sheet2.getLastRow());
                    String emailcim = "";
                    for(int szamlalo2 = 0; szamlalo2 < datatable.getRows().size();szamlalo2++)
                    {
                        if(datatable.getRows().get(szamlalo2).getString(0).toLowerCase().contains(datatable2.getRows().get(szamlalo).getString(2).toLowerCase()) )
                        {
                            emailcim = datatable.getRows().get(szamlalo2).getString(2)+", kovacs.sandor@veas.videoton.hu, reznyak.norbert@veas.videoton.hu";
                        }
                    }
                    //emailcim = "kovacs.zoltan@veas.videoton.hu";
                    File excel = new File(System.getProperty("user.home") + "\\"+datatable2.getRows().get(szamlalo).getString(2)+".xlsx");
                    emailkuldes.retour_emailkuldes("easqas@veas.videoton.hu", emailcim, datatable2.getRows().get(szamlalo).getString(2), excel);
                    id = datatable2.getRows().get(szamlalo).getString(0);
                    String modosit = "update qualitydb.Retour set  Ertesitve = 'Igen' where ID in ("+ id  +")";
                    stmt.executeUpdate(modosit);
                    excel.delete();
                    id = "";
                }
                else if(datatable2.getRows().get(datatable2.getRows().size()-2).getString(2).equals(datatable2.getRows().get(datatable2.getRows().size()-1).getString(2)))
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
                    id += datatable2.getRows().get(szamlalo).getString(0)+",";
                    
                    sheet2.getAutoFilters().setRange(sheet2.getCellRange("A1:AD1"));
                    sheet2.getAllocatedRange().autoFitColumns();
                    sheet2.getAllocatedRange().autoFitRows();
                    sheet2.getCellRange("A1:AD1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                    String hova = System.getProperty("user.home") + "\\"+datatable2.getRows().get(szamlalo).getString(2)+".xlsx";
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
                    sheet2.deleteRow(2,sheet.getLastRow());
                    String emailcim = "";
                    for(int szamlalo2 = 0; szamlalo2 < datatable.getRows().size();szamlalo2++)
                    {
                        if(datatable.getRows().get(szamlalo2).getString(0).toLowerCase().contains(datatable2.getRows().get(szamlalo).getString(2).toLowerCase()) )
                        {
                            emailcim = datatable.getRows().get(szamlalo2).getString(2)+", kovacs.sandor@veas.videoton.hu, reznyak.norbert@veas.videoton.hu";
                        }
                    }
                    //emailcim = "kovacs.zoltan@veas.videoton.hu";
                    File excel = new File(System.getProperty("user.home") + "\\"+datatable2.getRows().get(szamlalo).getString(2)+".xlsx");
                    emailkuldes.retour_emailkuldes("easqas@veas.videoton.hu", emailcim, datatable2.getRows().get(szamlalo).getString(2), excel);
                    id = id.substring(0, id.length() - 1);
                    String modosit = "update qualitydb.Retour set  Ertesitve = 'Igen' where ID in ("+ id  +")";
                    stmt.executeUpdate(modosit);
                    excel.delete();
                    id = "";
                }
                else
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
                    id += datatable2.getRows().get(szamlalo).getString(0)+",";
                    
                    sheet2.getAutoFilters().setRange(sheet2.getCellRange("A1:AD1"));
                    sheet2.getAllocatedRange().autoFitColumns();
                    sheet2.getAllocatedRange().autoFitRows();
                    sheet2.getCellRange("A1:AD1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                    String hova = System.getProperty("user.home") + "\\"+datatable2.getRows().get(szamlalo).getString(2)+".xlsx";
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
                    sheet2.deleteRow(2,sheet.getLastRow());
                    String emailcim = "";
                    for(int szamlalo2 = 0; szamlalo2 < datatable.getRows().size();szamlalo2++)
                    {
                        if(datatable.getRows().get(szamlalo2).getString(0).toLowerCase().contains(datatable2.getRows().get(szamlalo).getString(2).toLowerCase()) )
                        {
                            emailcim = datatable.getRows().get(szamlalo2).getString(2)+", kovacs.sandor@veas.videoton.hu, reznyak.norbert@veas.videoton.hu";
                        }
                    }
                    //emailcim = "kovacs.zoltan@veas.videoton.hu";
                    File excel = new File(System.getProperty("user.home") + "\\"+datatable2.getRows().get(szamlalo).getString(2)+".xlsx");
                    emailkuldes.retour_emailkuldes("easqas@veas.videoton.hu", emailcim, datatable2.getRows().get(szamlalo).getString(2), excel);
                    id = id.substring(0, id.length() - 1);
                    String modosit = "update qualitydb.Retour set  Ertesitve = 'Igen' where ID in ("+ id  +")";
                    stmt.executeUpdate(modosit);
                    excel.delete();
                    id = "";
                }
            }
            
        }
        
        System.out.println("Lefutott az SQA Email rész");
        resultSet.close();
        stmt.close();
        conn.close();
        
        }        
        catch (Exception e) 
        {
           e.printStackTrace();
           String hibauzenet = e.toString();
           Email hibakuldes = new Email();
           hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", this.getClass().getSimpleName()+" "+ hibauzenet);
           JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
        } 
        finally 
        {
           try 
           {
              if (stmt != null)
                 conn.close();
           } 
           catch (SQLException se) {}
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
    }
    
    public void minden_excel(String SQL, String fajlneve)
    {
        Connection conn = null;
        Statement stmt = null;
        DataTable datatable = new DataTable();
        try 
        {
           try 
           {
              Class.forName("com.mysql.cj.jdbc.Driver");
           } 
           catch (Exception e) 
           {
              System.out.println(e);
              String hibauzenet2 = e.toString();
              JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
        }
        conn = (Connection) DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
        stmt = (Statement) conn.createStatement();
        String sajat = SQL;
        stmt.execute(sajat);
        ResultSet resultSet = stmt.getResultSet();
        
        Workbook workbook = new Workbook();
        JdbcAdapter jdbcAdapter = new JdbcAdapter();
        jdbcAdapter.fillDataTable(datatable, resultSet);

        //Get the first worksheet
        Worksheet sheet = workbook.getWorksheets().get(0);
        sheet.insertDataTable(datatable, true, 1, 1);
        sheet.getAutoFilters().setRange(sheet.getCellRange("A1:AC1"));
        sheet.getAllocatedRange().autoFitColumns();
        sheet.getAllocatedRange().autoFitRows();
        
        sheet.getCellRange("A1:AC1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
        
        /*JFileChooser mentes_helye = new JFileChooser();
        mentes_helye.setCurrentDirectory(new java.io.File(System.getProperty("user.home") + "\\Desktop\\"));
        mentes_helye.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        mentes_helye.showOpenDialog(mentes_helye);*/
        File fajl = new File(System.getProperty("user.home") + "\\Desktop\\"+ fajlneve);                                                        //mentes_helye.getSelectedFile();
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
        JOptionPane.showMessageDialog(null, "Mentve az asztalra "+ fajlneve +" néven", "Info", 1);
        }         
        catch (Exception e) 
        {
           e.printStackTrace();
           String hibauzenet = e.toString();
           Email hibakuldes = new Email();
           hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", this.getClass().getSimpleName()+" "+ hibauzenet);
        } finally 
        {
           try 
           {
              if (stmt != null)
                 conn.close();
           } 
           catch (SQLException se) {}
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
    }
    
    public void uj_rendelesek()
    {
        try
        {
            //Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            SQA_SQL ellenorzo = new SQA_SQL();
            String sql = "select Ertesitve from qualitydb.Uj_rendelesek where id = 1";
            if(ellenorzo.tombvissza_sajat(sql)[0].equals("nem"))
            { 
                Date date2 = new Date();
                JDatePickerImpl datum_tol;
                SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy.MM.dd");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
                LocalDate date = LocalDate.parse(formatter2.format(date2), formatter);
                // Increment the date by one day
                LocalDate newDate = date.minusDays(7);
                
                Date date3 = Date.from(newDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                /*String dateValue = "2024.03.23";
                try {
                    date3 = new SimpleDateFormat("yyyy.MM.dd").parse(dateValue);
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }*/
                UtilDateModel model = new UtilDateModel();
                model.setValue(date3);
                Properties p = new Properties();
                p.put("text.today", "Ma");
                p.put("text.month", "Hónap");
                p.put("text.year", "Év");
                JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
                datum_tol = new JDatePickerImpl(datePanel, new DateLabelFormatter());
                String menteshelye = System.getProperty("user.home") + "\\Desktop\\Új Rendelések.xlsx";
    
                  DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                  Class.forName("oracle.jdbc.OracleDriver");  //.driver
                                      
                  Connection con = DriverManager.getConnection("jdbc:oracle:thin:@IFSORA.IFS.videoton.hu:1521/IFSPROD","ZKOVACS","ZKOVACS");                                      
                  Statement stmt = con.createStatement();                      
                  
                  String[] datum = datum_tol.getJFormattedTextField().getText().split("\\.");
                  //System.out.println(datum_tol.getJFormattedTextField().getText());
                  
                  ResultSet rs = stmt.executeQuery("select majdnem.*,\r\n"
                          + "nyilatkozatok.CF$_cmrt as CMRT,\r\n"
                          + "nyilatkozatok.CF$_reach as Reach,\r\n"
                          + "nyilatkozatok.CF$_Rohs as Rohs\r\n"
                          + "from ifsapp.part_manu_part_no_cfv nyilatkozatok,\r\n"
                          + "(select alap.*,\r\n"
                          + "raktar.SECOND_COMMODITY as Projekt\r\n"
                          + "from ifsapp.INVENTORY_PART raktar,\r\n"
                          + "(select belso.Cikkszam, \r\n"
                          + "belso.Megnevezes,\r\n"
                          + "belso.Szallito,\r\n"
                          + "belso.Gyarto_szama,\r\n"
                          + "ifsapp.MANUFACTURER_INFO_API.Get_Name(kulso.MANUFACTURER_NO) as Gyarto,\r\n"
                          + "belso.Gyartoi_cikkszam,\r\n"
                          + "belso.Utolso_rendeles\r\n"
                          + "from ifsapp.PART_MANUFACTURER kulso,\r\n"
                          + "(select PART_NO as Cikkszam,\r\n"
                          + "DESCRIPTION as Megnevezes, \r\n"
                          + "ifsapp.Supplier_API.Get_Vendor_Name(VENDOR_NO) as Szallito,\r\n"
                          + "ifsapp.Purchase_Order_Line_Part_Api.Get_Manufacturer_id(ORDER_NO,LINE_NO,RELEASE_NO) as Gyarto_szama,\r\n"
                          + "ifsapp.Purchase_Order_Line_Part_Api.Get_Manufacturer_Part_No(ORDER_NO,LINE_NO,RELEASE_NO) as Gyartoi_cikkszam,\r\n"
                          + "max(DATE_ENTERED) as Utolso_rendeles\r\n"
                          + "from ifsapp.PURCHASE_ORDER_LINE_ALL\r\n"
                          + "where\r\n"
                          + "(OBJSTATE = (select ifsapp.PURCHASE_ORDER_LINE_API.FINITE_STATE_ENCODE__('Visszaigazolt') from dual) or \r\n"
                          + "OBJSTATE = (select ifsapp.PURCHASE_ORDER_LINE_API.FINITE_STATE_ENCODE__('Átvéve') from dual) or \r\n"
                          + "OBJSTATE = (select ifsapp.PURCHASE_ORDER_LINE_API.FINITE_STATE_ENCODE__('Beérkezett') from dual) or \r\n"
                          + "OBJSTATE = (select ifsapp.PURCHASE_ORDER_LINE_API.FINITE_STATE_ENCODE__('Lezárt') from dual)) and DATE_ENTERED > to_date( '"+ datum[0]+datum[1]+ datum[2]+"', 'YYYYMMDD' ) + ( 1 - 1/ ( 60*60*24 ) )\r\n"
                          + "group by ifsapp.Supplier_API.Get_Vendor_Name(VENDOR_NO), PART_NO,\r\n"
                          + "DESCRIPTION, \r\n"
                          + "ifsapp.Purchase_Part_Supplier_API.Get_Vendor_Part_No(CONTRACT,PART_NO,VENDOR_NO), \r\n"
                          + "ifsapp.Purchase_Part_Supplier_API.Get_Vendor_Part_Description(CONTRACT,PART_NO,VENDOR_NO), \r\n"
                          + "PROJECT_ID, \r\n"
                          + "ifsapp.Project_API.Get_Name(PROJECT_ID), \r\n"
                          + "ifsapp.Purchase_Order_Line_Part_Api.Get_Manufacturer_Id(ORDER_NO,LINE_NO,RELEASE_NO), \r\n"
                          + "ifsapp.Purchase_Order_Line_Part_Api.Get_Manufacturer_Part_No(ORDER_NO,LINE_NO,RELEASE_NO)) belso\r\n"
                          + "Where 3 = 3\r\n"
                          + "and belso.Gyarto_szama = kulso.MANUFACTURER_NO\r\n"
                          + "and belso.Cikkszam = kulso.part_no) alap\r\n"
                          + "where raktar.part_no = alap.cikkszam) majdnem\r\n"
                          + "where 3 = 3 and majdnem.cikkszam = nyilatkozatok.part_no\r\n"
                          + "and majdnem.Gyartoi_cikkszam = nyilatkozatok.manu_part_no\r\n"
                          + "and nyilatkozatok.CF$_cmrt is null\r\n"
                          + "and nyilatkozatok.CF$_reach is null\r\n"
                          + "and nyilatkozatok.CF$_Rohs is null");
                  
                  Workbook workbook = new Workbook();
                  //JdbcAdapter jdbcAdapter = new JdbcAdapter();
                  //jdbcAdapter.fillDataTable(datatable, rs);      
                  //Get the first worksheet
                  Worksheet sheet = workbook.getWorksheets().get(0);
                  //sheet.insertDataTable(datatable, true, 1, 1);
                  int cellaszam = 1;
                  sheet.getRange().get("A" + cellaszam).setText("Cikkszám");
                  sheet.getRange().get("B" + cellaszam).setText("Megnevezés");
                  sheet.getRange().get("C" + cellaszam).setText("Szállító");
                  sheet.getRange().get("D" + cellaszam).setText("Gyártó száma");
                  sheet.getRange().get("E" + cellaszam).setText("Gyártó");
                  sheet.getRange().get("F" + cellaszam).setText("Gyártói Cikkszám");
                  sheet.getRange().get("G" + cellaszam).setText("Utolsó rendelés");
                  sheet.getRange().get("H" + cellaszam).setText("Projekt");
                  sheet.getRange().get("I" + cellaszam).setText("CMRT");
                  sheet.getRange().get("J" + cellaszam).setText("REACH");
                  sheet.getRange().get("K" + cellaszam).setText("ROHS");
                  
                  cellaszam++;
                  while(rs.next())
                  {
                      sheet.getRange().get("A" + cellaszam).setText(rs.getString(1));
                      sheet.getRange().get("B" + cellaszam).setText(rs.getString(2));
                      sheet.getRange().get("C" + cellaszam).setText(rs.getString(3));
                      sheet.getRange().get("D" + cellaszam).setText(rs.getString(4));
                      sheet.getRange().get("E" + cellaszam).setText(rs.getString(5));
                      sheet.getRange().get("F" + cellaszam).setText(rs.getString(6));
                      sheet.getRange().get("G" + cellaszam).setText(rs.getString(7));
                      sheet.getRange().get("H" + cellaszam).setText(rs.getString(8));
                      sheet.getRange().get("I" + cellaszam).setText(rs.getString(9));
                      sheet.getRange().get("J" + cellaszam).setText(rs.getString(10));
                      sheet.getRange().get("K" + cellaszam).setText(rs.getString(11));
                      cellaszam++;
                  }
                  
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
                  }
                  Email email = new Email();              
                  File fajl = new File(menteshelye);
                  email.ujanyag_emailkuldes("easqas@veas.videoton.hu", "jenei.erika@veas.videoton.hu,meszaros.agnes@veas.videoton.hu,kovacs.zoltan@veas.videoton.hu", fajl);
                  
                  fajl.delete();
                  String modosit = "update qualitydb.Uj_rendelesek set  Ertesitve = 'igen' where id = 1";
                  ellenorzo.mindenes(modosit);
                  //JOptionPane.showMessageDialog(null, "Kész! \n Mentve az asztalra Rendelések.xlsx néven!", "Info", 1); 
                  con.close();
            }
             
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
        Foablak.frame.setCursor(null);       
    }
    
    public class DateLabelFormatter extends AbstractFormatter {

        private String datePattern = "yyyy.MM.dd";
        private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

        @Override
        public Object stringToValue(String text) throws ParseException {
            return dateFormatter.parseObject(text);
        }

        @Override
        public String valueToString(Object value) throws ParseException {
            if (value != null) {
                Calendar cal = (Calendar) value;
                return dateFormatter.format(cal.getTime());
            }
            //System.out.println(datePicker.getJFormattedTextField().getText());
            return "";
        }       

    }

}
