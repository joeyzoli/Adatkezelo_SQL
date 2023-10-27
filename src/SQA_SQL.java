import java.awt.Cursor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
            hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
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
            hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
            JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                 //kiírja a hibaüzenetet
        }
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
        catch (SQLException e1) 
        {
            e1.printStackTrace();
            String hibauzenet = e1.toString();
            Email hibakuldes = new Email();
            hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
            JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                     //kivétel esetén kiírja a hibaüzenetet
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            String hibauzenet = e.toString();
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
               hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
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
           hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
           JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
        } 
        catch (Exception e) 
        {
           e.printStackTrace();
           String hibauzenet = e.toString();
           Email hibakuldes = new Email();
           hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
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
        String sql = "select id, Vevo, Tipus, DATEDIFF(now(), Modositas_datuma) as 'Kulonbseg', Ertesitve \r\n"
                + "from qualitydb.Retour\r\n"
                + "where 3 = 3 and modositas_datuma is not null and Raktar_datum is null";                                        
        stmt.execute(sql);      
        resultSet = stmt.getResultSet();
        ArrayList<String> cimzettek = new ArrayList<String>();
        while(resultSet.next())
        {
            if(resultSet.getInt(4) >=14)
            {                
                if(resultSet.getString(5).equals("Nem"))
                {                    
                    cimzettek.add(resultSet.getString(1)+";"+resultSet.getString(2)+";"+resultSet.getString(3)+";"+resultSet.getString(4));                    
                }                   
            }
        }
        if(cimzettek.size() >= 1 )
        {
            for(int szamlalo = 0; szamlalo < cimzettek.size();szamlalo++)
            {
                Workbook workbook = new Workbook();
                workbook.loadFromFile(emaillista_retour);
                Worksheet sheet = workbook.getWorksheets().get(0);
                datatable = sheet.exportDataTable(sheet.getAllocatedRange(), false, false );
                Email emailkuldes = new Email();
                String[] adatok = cimzettek.get(szamlalo).split(";");
                String emailcim = "";
                for(int szamlalo2 = 0; szamlalo2 < datatable.getRows().size();szamlalo2++)
                {
                    if(datatable.getRows().get(szamlalo2).getString(0).toLowerCase().contains(adatok[1].toLowerCase()) )
                    {
                        emailcim = datatable.getRows().get(szamlalo2).getString(2)+", kovacs.sandor@veas.videoton.hu";
                    }
                }
                if(emailcim.equals("")) {System.out.println("Nincs találat");}
                else
                {
                    String hol = "";
                    sql = "select hova_datum, Teszt_datum, Vegell_datum \r\n"
                            + "from qualitydb.Retour\r\n"
                            + "where 3 = 3 and modositas_datuma is not null and Raktar_datum is null";
                    stmt.execute(sql);      
                    resultSet = stmt.getResultSet();
                    if(resultSet.next())
                    {
                        if(resultSet.getString(1) == null)
                        {
                            hol = "Nincs még kiadva sehova";
                            emailkuldes.retour_emailkuldes("automataemail@veas.videoton.hu",emailcim,adatok[0],adatok[1],adatok[2],adatok[3], hol);
                        }
                        else if(resultSet.getString(2) == null)
                        {
                            hol = "Nem lett kiadva tesztelésre";
                            emailkuldes.retour_emailkuldes("automataemail@veas.videoton.hu",emailcim,adatok[0],adatok[1],adatok[2],adatok[3], hol);
                        }
                        else
                        {
                            hol = "Nincs továbbadva végellenőrzésre";
                            emailkuldes.retour_emailkuldes("automataemail@veas.videoton.hu",emailcim,adatok[0],adatok[1],adatok[2],adatok[3], hol);
                        }
                    }
                    
                    String modosit = "update qualitydb.Retour set  Ertesitve = 'Igen' where ID = '"+ adatok[0]  +"'";
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
           hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
           JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
        } 
        catch (Exception e) 
        {
           e.printStackTrace();
           String hibauzenet = e.toString();
           Email hibakuldes = new Email();
           hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
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
        catch (SQLException e1) 
        {
           e1.printStackTrace();
           String hibauzenet = e1.toString();
           Email hibakuldes = new Email();
           hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
        } 
        catch (Exception e) 
        {
           e.printStackTrace();
           String hibauzenet = e.toString();
           Email hibakuldes = new Email();
           hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
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

}
