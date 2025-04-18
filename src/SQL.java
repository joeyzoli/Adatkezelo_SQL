import com.spire.data.table.DataTable;
import com.spire.data.table.common.JdbcAdapter;
import com.spire.xls.Chart;
import com.spire.xls.ExcelChartType;
import com.spire.xls.ExcelVersion;
import com.spire.xls.LegendPositionType;
import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;
import com.spire.xls.charts.ChartSerie;
import com.spire.xls.charts.ChartSeries;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
public class SQL 
{
    ResultSet resultSet;
    static DefaultTableModel alapmodell;
    private final String emaillista = "\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\E-mail lista_ reklamáció.xlsx";
    private final String emaillista2 = "\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\Projekt- mérnök.xlsx";
    private final String emaillista3 = "\\\\172.20.22.7\\kozos\\telefonkönyv\\Telefonlista_VEAS_új.xls";
    
	public void lekerdez_projekt(String querry, String datum_tol, String datum_ig, String hiba_helye, String projekt, String menteshelye)
	{
    
        String driverName = "com.mysql.cj.jdbc.Driver";						//driver stringje
        String url = "jdbc:mysql://172.20.22.29";							//adatbázis IP címe
        String userName = "veasquality";									//fehasználónév
        String password = "kg6T$kd14TWbs9&gd";								//jelszó
        DataTable datatable = new DataTable();
  
        try 
        {
            Class.forName(driverName);
            Connection connection = DriverManager.getConnection(url, userName, password);							//csatlakozás a szerverhez
            Statement statement = connection.createStatement();
            CallableStatement cstmt = connection.prepareCall("{" + querry + "}");									//tárolt eljárást hívja meg
            cstmt.setString(1, datum_tol);
            cstmt.setString(2, datum_ig);
            cstmt.setString(3, hiba_helye);
            cstmt.setString(4, projekt);
            cstmt.execute();
            //String sql = "select * from " + DB;
            ResultSet resultSet = cstmt.getResultSet();
 
            Workbook workbook = new Workbook();
            JdbcAdapter jdbcAdapter = new JdbcAdapter();
            jdbcAdapter.fillDataTable(datatable, resultSet);
 
            //Get the first worksheet
            Worksheet sheet = workbook.getWorksheets().get(0);
            sheet.insertDataTable(datatable, true, 1, 1);
            sheet.getAutoFilters().setRange(sheet.getCellRange("A1:J1"));
            sheet.getAllocatedRange().autoFitColumns();
            sheet.getAllocatedRange().autoFitRows();
            
            if(System.getProperty("user.name").equals("csader.zsolt"))                  //csader.zsolt
            {
                UIManager.put("FileChooser.openButtonText","Mentés");
                JFileChooser mentes_helye = new JFileChooser();
                mentes_helye.setCurrentDirectory(new java.io.File("\\\\\\172.20.22.7\\kozos\\Gyártási_minőség_követése\\Kézi_hibák_követése\\2025\\Heti ppm, tup 2025"));
                mentes_helye.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                mentes_helye.showOpenDialog(mentes_helye);
                File fajl = mentes_helye.getSelectedFile();
                if(fajl != null)
                {
                    String menteshelye2 = fajl.getAbsolutePath();
                    System.out.println(menteshelye2);
                    if(menteshelye2.contains(".xlsx")){}
                    else
                    {
                        menteshelye2 = menteshelye2 + ".xlsx";
                    }                
                    System.out.println(menteshelye2);
                    workbook.saveToFile(menteshelye2, ExcelVersion.Version2016);          
                    FileInputStream fileStream = new FileInputStream(menteshelye2);
                    try (XSSFWorkbook workbook2 = new XSSFWorkbook(fileStream)) 
                    {
                        for(int i = workbook2.getNumberOfSheets()-1; i>0 ;i--)
                        {    
                            workbook2.removeSheetAt(i); 
                        }      
                        FileOutputStream output = new FileOutputStream(menteshelye2);
                        workbook2.write(output);
                        output.close();
                    }
                }
            }
            else
            {
                try 
                {
                    System.out.println("Ez a szál is fut");
                    workbook.saveToFile(menteshelye, ExcelVersion.Version2016);          
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
                }
                catch (Exception e2) 
                {
                    System.out.println("Új szál is fut");
                    if(menteshelye.contains("ProjektPPM"))
                    {
                        menteshelye = System.getProperty("user.home") + "\\Desktop\\ProjektPPM1.xlsx";
                    }
                    else if(menteshelye.contains("ProjektPPM"))
                    {
                        menteshelye = System.getProperty("user.home") + "\\Desktop\\TermékPPM1.xlsx";
                    }
                    else
                    {
                        menteshelye = System.getProperty("user.home") + "\\Desktop\\Hibák_adatai1.xlsx";
                    }
                    workbook.saveToFile(menteshelye, ExcelVersion.Version2016);          
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
                    JOptionPane.showMessageDialog(null, "Mentve az asztalra a végén egy 1-el mert nyitva volt a fájl!", "Info", 1);
                }
            }
            resultSet.close();
            statement.close();
            connection.close();
        }         
        catch (Exception e1) 
        {
            e1.printStackTrace();
            String hibauzenet2 = e1.toString();
            Email hibakuldes = new Email();
            hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", this.getClass().getSimpleName()+" "+ hibauzenet2);
			JOptionPane.showMessageDialog(null, getClass()+" "+ hibauzenet2, "Hiba üzenet", 2);
        } 
	}
	
	public void lekerdez_projekt_osszegez(String querry, String datum_tol, String datum_ig, String hiba_helye, String projekt, String menteshelye)
    {
    
        String driverName = "com.mysql.cj.jdbc.Driver";                     //driver stringje
        String url = "jdbc:mysql://172.20.22.29";                           //adatbázis IP címe
        String userName = "veasquality";                                    //fehasználónév
        String password = "kg6T$kd14TWbs9&gd";                              //jelszó
        DataTable datatable = new DataTable();
  
        try 
        {
            Class.forName(driverName);
            Connection connection = DriverManager.getConnection(url, userName, password);                           //csatlakozás a szerverhez
            Statement statement = connection.createStatement();
            CallableStatement cstmt = connection.prepareCall("{" + querry + "}");                                   //tárolt eljárást hívja meg
            cstmt.setString(1, datum_tol);
            cstmt.setString(2, datum_ig);
            cstmt.setString(3, hiba_helye);
            cstmt.setString(4, projekt);
            cstmt.execute();
            //String sql = "select * from " + DB;
            ResultSet resultSet = cstmt.getResultSet();
 
            Workbook workbook = new Workbook();
            JdbcAdapter jdbcAdapter = new JdbcAdapter();
            jdbcAdapter.fillDataTable(datatable, resultSet);
            
            int sumfelajanlott = 0;
            int summinta = 0;
            int sumhiba = 0;
            float ppm = 0;
            for(int szamlalo = 0; szamlalo < datatable.getRows().size(); szamlalo++)
            {
                sumfelajanlott += Integer.parseInt(datatable.getRows().get(szamlalo).getString(3));
            }
            for(int szamlalo = 0; szamlalo < datatable.getRows().size(); szamlalo++)
            {
                summinta += Integer.parseInt(datatable.getRows().get(szamlalo).getString(4));
            }
            for(int szamlalo = 0; szamlalo < datatable.getRows().size(); szamlalo++)
            {
                sumhiba += Integer.parseInt(datatable.getRows().get(szamlalo).getString(5));
            }
            ppm = ((float)sumhiba/(float)sumfelajanlott)* (float)1000000;
            
            Worksheet sheet = workbook.getWorksheets().get(0);
            sheet.insertDataTable(datatable, true, 1, 1);
            int utolsosor = sheet.getLastRow() +1;
            sheet.getRange().get("C" + (utolsosor)).setText("Sum:");
            sheet.getRange().get("D" + (utolsosor)).setText(String.valueOf(sumfelajanlott));
            sheet.getRange().get("E" + (utolsosor)).setText(String.valueOf(summinta));
            sheet.getRange().get("F" + (utolsosor)).setText(String.valueOf(sumhiba));
            sheet.getRange().get("G" + (utolsosor)).setText(String.valueOf(ppm));
            
            sheet.getAutoFilters().setRange(sheet.getCellRange("A1:G1"));
            sheet.getAllocatedRange().autoFitColumns();
            sheet.getAllocatedRange().autoFitRows();
            
            sheet.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
            if(System.getProperty("user.name").equals("csader.zsolt"))
            {
                UIManager.put("FileChooser.openButtonText","Mentés");
                JFileChooser mentes_helye = new JFileChooser();
                mentes_helye.setCurrentDirectory(new java.io.File(System.getProperty("user.home") + "\\Desktop\\"));
                mentes_helye.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                mentes_helye.showOpenDialog(mentes_helye);
                File fajl = mentes_helye.getSelectedFile();
                menteshelye = fajl.getAbsolutePath();
            }
            workbook.saveToFile(menteshelye, ExcelVersion.Version2016);
            resultSet.close();
            statement.close();
            connection.close();
            
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
            
            //JOptionPane.showMessageDialog(null, "Mentés sikeres", "Info", 1);
        }        
        catch (Exception e1) 
        {
            e1.printStackTrace();
            String hibauzenet2 = e1.toString();
            Email hibakuldes = new Email();
            hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", this.getClass().getSimpleName()+" "+ hibauzenet2);
            JOptionPane.showMessageDialog(null, getClass()+" "+ hibauzenet2, "Hiba üzenet", 2);
        }
 
    }
	
	public void lekerdez_mutat(String querry, String datum_tol, String datum_ig, String hiba_helye, String projekt)
    {
    
        String driverName = "com.mysql.cj.jdbc.Driver";                     //driver stringje
        String url = "jdbc:mysql://172.20.22.29";                           //adatbázis IP címe
        String userName = "veasquality";                                    //fehasználónév
        String password = "kg6T$kd14TWbs9&gd";                              //jelszó
        try 
        {
            Class.forName(driverName);
            Connection connection = DriverManager.getConnection(url, userName, password);                           //csatlakozás a szerverhez
            CallableStatement cstmt = connection.prepareCall("{" + querry + "}");                                   //tárolt eljárást hívja meg
            cstmt.setString(1, datum_tol);
            cstmt.setString(2, datum_ig);
            cstmt.setString(3, hiba_helye);
            cstmt.setString(4, projekt);
            cstmt.execute();
            //String sql = "select * from " + DB;
            ResultSet resultSet = cstmt.getResultSet();
            
            EASQAS_adatok.table.setModel(buildTableModel(resultSet));

            resultSet.close();
            cstmt.close();
            connection.close();
            
        }        
        catch (Exception e1) 
        {
            e1.printStackTrace();
            String hibauzenet2 = e1.toString();
            Email hibakuldes = new Email();
            hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", this.getClass().getSimpleName()+" "+ hibauzenet2);
            JOptionPane.showMessageDialog(null, getClass()+" "+ hibauzenet2, "Hiba üzenet", 2);
        }
 
    }
	
	public void vevoi_napok(String querry, String id)
    {
    
        String driverName = "com.mysql.cj.jdbc.Driver";                     //driver stringje
        String url = "jdbc:mysql://172.20.22.29";                           //adatbázis IP címe
        String userName = "veasquality";                                    //fehasználónév
        String password = "kg6T$kd14TWbs9&gd";                              //jelszó
        try 
        {
            Class.forName(driverName);
            Connection connection = DriverManager.getConnection(url, userName, password);                           //csatlakozás a szerverhez
            Statement statement = connection.createStatement();
            CallableStatement cstmt = connection.prepareCall("{" + querry + "}");                                   //tárolt eljárást hívja meg
            cstmt.setString(1, id);
            cstmt.execute();
            //String sql = "select * from " + DB;
            ResultSet resultSet = cstmt.getResultSet();
            int napok = 0;
            //resultSet.next();            
            while (resultSet.next()) 
            {
                if(resultSet.getString(1) != null)
                {
                    napok = Integer.parseInt(resultSet.getString(1));
                }              
            }
      
            Db_iro visszair = new Db_iro();
            String sql = "update qualitydb.Vevoireklamacio_alapadat set  Nyitva = '"+ napok +"' where "
                    + "ID = '"+ id +"'";
                    
            visszair.ujrair_vevoi(sql);
           
            resultSet.close();
            statement.close();
            connection.close();          
        }         
        catch (Exception e1) 
        {
            e1.printStackTrace();
            String hibauzenet2 = e1.toString();
            Email hibakuldes = new Email();
            hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", this.getClass().getSimpleName()+" "+ hibauzenet2);
            JOptionPane.showMessageDialog(null, getClass()+" "+ hibauzenet2, "Hiba üzenet", 2);
        }
 
    }
	
	public void sajat_sql(String SQL)
	{
		Connection conn = null;
	    Statement stmt = null;
	    DataTable datatable = new DataTable();
	    try 
	    {
	        Class.forName("com.mysql.cj.jdbc.Driver");   
    	    conn = (Connection) DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
    	    stmt = (Statement) conn.createStatement();
    	    String sajat = SQL;
    	    stmt.execute(sajat);
    	    ResultSet resultSet = stmt.getResultSet();
	    
	    if(resultSet != null)
	    {
            Workbook workbook = new Workbook();
            workbook.setVersion(ExcelVersion.Version2016);
            JdbcAdapter jdbcAdapter = new JdbcAdapter();
            jdbcAdapter.fillDataTable(datatable, resultSet);
    
            //Get the first worksheet
            Worksheet sheet = workbook.getWorksheets().get(0);
            sheet.insertDataTable(datatable, true, 1, 1);
            sheet.getAutoFilters().setRange(sheet.getCellRange("A1:Z1"));
            sheet.getAllocatedRange().autoFitColumns();
            sheet.getAllocatedRange().autoFitRows();
            
            sheet.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
            
            UIManager.put("FileChooser.openButtonText","Mentés");
            JFileChooser mentes_helye = new JFileChooser();
            mentes_helye.setCurrentDirectory(new java.io.File(System.getProperty("user.home") + "\\Desktop\\"));
            mentes_helye.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            mentes_helye.showOpenDialog(mentes_helye);
            File fajl = mentes_helye.getSelectedFile();
            //System.out.println(fajl.getAbsolutePath());
            if(fajl != null)
            {
                if(fajl.getName().contains(".xlsx"))
                {
                    workbook.saveToFile(fajl.getAbsolutePath(), ExcelVersion.Version2016);  
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
                }
                else
                {
                    workbook.saveToFile(fajl.getAbsolutePath()+".xlsx", ExcelVersion.Version2016);  
                    FileInputStream fileStream = new FileInputStream(fajl.getAbsolutePath()+".xlsx");
                    try (XSSFWorkbook workbook2 = new XSSFWorkbook(fileStream)) 
                    {
                        for(int i = workbook2.getNumberOfSheets()-1; i>0 ;i--)
                        {    
                            workbook2.removeSheetAt(i); 
                        }      
                        FileOutputStream output = new FileOutputStream(fajl.getAbsolutePath()+".xlsx");
                        workbook2.write(output);
                        output.close();
                    }
                }
                JOptionPane.showMessageDialog(null, "Mentés sikeres", "Info", 1);
            }
            resultSet.close();
            stmt.close();
            conn.close();                        
	    }
	    else
	    {
	        JOptionPane.showMessageDialog(null, "SQL lefutott", "Info", 1);
	    }
	    }	    
	    catch (Exception e) 
	    {
	       e.printStackTrace();
	       String hibauzenet = e.toString();
	       Email hibakuldes = new Email();
	       JOptionPane.showMessageDialog(null, hibauzenet, "Hiba", 2);
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
	
	public void top_hiba(String tipus)
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
        conn = DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String sajat = "SELECT Pozicio as \"Pozíció\", Hiba_megnevezes as \"Hiba megnevezés\", sum(Hibak_szam) as \"Hibák száma\" FROM  qualitydb.Gyartasi_adatok "
                        + "where VT_azon = '"+ tipus +"' and not Hibak_szam = '0' group by Pozicio order by sum(Hibak_szam) desc limit 7";  //group by Pozicio
        stmt.execute(sajat);
        resultSet = stmt.getResultSet();
        
        if(ProGlove.table_1 != null)
        {
            ProGlove.table_1.setModel(buildTableModel(resultSet));
        }
        else if(Loxone.table_1 != null)
        {
            Loxone.table_1.setModel(buildTableModel(resultSet));
        }
        else if(Socomec.table_1 != null)
        {
            Socomec.table_1.setModel(buildTableModel(resultSet));
        }
        else
        {
            Hager.table_1.setModel(buildTableModel(resultSet));
        }

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
	
	public void adat_modositashoz(String sql)
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
        conn = DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String sajat = sql;
        stmt.execute(sajat);
        resultSet = stmt.getResultSet();
        
        Adat_torles.table.setModel(buildTableModel(resultSet));

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
	
	public void vevoi_id_bevitel(String id)
    {
        Connection conn = null;
        Statement stmt = null;
        Statement stmt2 = null;
        Statement stmt3 = null;
        ResultSet resultSet2;
        ResultSet resultSet3;
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
        stmt2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        stmt3 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
       
        String sql = "SELECT datum, projekt, tipus, rek_vagy, rek_db, hibaleiras, gyartas_idopontja, kiadott_rma FROM  qualitydb.Vevoireklamacio_alapadat where ID = '"+ id +"'";
       
        stmt.execute(sql);       
        resultSet = stmt.getResultSet();      
        
        JdbcAdapter jdbcAdapter = new JdbcAdapter();
        JdbcAdapter jdbcAdapter2 = new JdbcAdapter();
        JdbcAdapter jdbcAdapter3 = new JdbcAdapter();
        DataTable datatable = new DataTable();
        DataTable datatable2 = new DataTable();
        DataTable datatable3 = new DataTable();
        jdbcAdapter.fillDataTable(datatable, resultSet);      
        
        String[] datum = datatable.getRows().get(0).getString(0).split(" ");
        String[] projekt = {datatable.getRows().get(0).getString(1)};;
        String[] cikkszam = {datatable.getRows().get(0).getString(2)};;
        String[] rekvagy = {datatable.getRows().get(0).getString(3)};;
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(projekt);
        DefaultComboBoxModel<String> model2 = new DefaultComboBoxModel<>(cikkszam);
        DefaultComboBoxModel<String> model3 = new DefaultComboBoxModel<>(rekvagy);
        Vevoi_reklmacio_bevitel.projekt_box.setModel(model);
        Vevoi_reklmacio_bevitel.tipus_box.setModel(model2);
        Vevoi_reklmacio_bevitel.vagy_vagy.setModel(model3);
        
        Vevoi_reklmacio_bevitel.datum_mezo.setText(datum[0]);
        Vevoi_reklmacio_bevitel.reklamalt_db.setText(datatable.getRows().get(0).getString(4));
        Vevoi_reklmacio_bevitel.hibaleiras_mezo.setText(datatable.getRows().get(0).getString(5));
        Vevoi_reklmacio_bevitel.gyartasidopontja_mezo.setText(datatable.getRows().get(0).getString(6));
        Vevoi_reklmacio_bevitel.rma_mezo.setText(datatable.getRows().get(0).getString(7));
        
        String sql2 = "SELECT zarolt, zarolt_db, talalt_db, felelos, hatarido, muszaki_doku, termeles FROM  qualitydb.Vevoireklamacio_felelosok "
                + "where ID = '"+ id +"' ";      //+ "where Datum = '"+ datatable.getRows().get(0).getString(0) +"' and Cikkszam = '"+ datatable.getRows().get(0).getString(2) +"' ";
        stmt2.execute(sql2);
        resultSet2 = stmt2.getResultSet();
        jdbcAdapter2.fillDataTable(datatable2, resultSet2);        
        int szamlalo2 = 1;            
        Vevoi_reklmacio_bevitel.modell.setRowCount(0);
        Vevoi_reklmacio_bevitel.modell2.setRowCount(0);
        Vevoi_reklmacio_bevitel.muszaki_igen.setSelected(false);
        Vevoi_reklmacio_bevitel.muszaki_nem.setSelected(false);
        Vevoi_reklmacio_bevitel.termeles_igen.setSelected(false);
        Vevoi_reklmacio_bevitel.termeles_nem.setSelected(false);
        int rowCount = Vevoi_reklmacio_bevitel.modell.getRowCount();
        int rowCount2 = Vevoi_reklmacio_bevitel.modell2.getRowCount();
        int rowCount3 = Vevoi_reklmacio_bevitel.modell3.getRowCount();
        int rowCount4 = Vevoi_reklmacio_bevitel.modell4.getRowCount();

        for (int i = rowCount - 1; i > -1; i--) 
        {
            Vevoi_reklmacio_bevitel.modell.removeRow(i);
        }
        for (int i = rowCount2 - 1; i > -1; i--) 
        {
            Vevoi_reklmacio_bevitel.modell2.removeRow(i);
        }
        for (int i = rowCount3 - 1; i > -1; i--) 
        {
            Vevoi_reklmacio_bevitel.modell3.removeRow(i);
        }
        for (int i = rowCount4 - 1; i > -1; i--) 
        {
            Vevoi_reklmacio_bevitel.modell4.removeRow(i);
        }
        Vevoi_reklmacio_bevitel.table.setModel(Vevoi_reklmacio_bevitel.modell4);
        Vevoi_reklmacio_bevitel.felelos_tabla.setModel(Vevoi_reklmacio_bevitel.modell);
        Vevoi_reklmacio_bevitel.zarolt_tabla.setModel(Vevoi_reklmacio_bevitel.modell2);
        Vevoi_reklmacio_bevitel.helyesbito_tabla.setModel(Vevoi_reklmacio_bevitel.modell3);
        for(int szamlalo = 0; szamlalo < datatable2.getRows().size(); szamlalo++)
        {
            Vevoi_reklmacio_bevitel.modell.addRow(new Object[]{datatable2.getRows().get(szamlalo).getString(3), datatable2.getRows().get(szamlalo).getString(4)});
            Vevoi_reklmacio_bevitel.modell2.addRow(new Object[]{szamlalo2, datatable2.getRows().get(szamlalo).getString(0), datatable2.getRows().get(szamlalo).getString(1), datatable2.getRows().get(szamlalo).getString(2)});
            szamlalo2++;
            if(datatable2.getRows().get(0).getString(5).equals("igen"))
            {
                Vevoi_reklmacio_bevitel.muszaki_igen.setSelected(true);
            }
            else
            {
                Vevoi_reklmacio_bevitel.muszaki_nem.setSelected(true);
            }
            
            if(datatable2.getRows().get(0).getString(6).equals("igen"))
            {
                Vevoi_reklmacio_bevitel.termeles_igen.setSelected(true);
            }
            else
            {
                Vevoi_reklmacio_bevitel.termeles_nem.setSelected(true);
            }
        }                       
        Vevoi_reklmacio_bevitel.felelos_tabla.setModel(Vevoi_reklmacio_bevitel.modell);
        Vevoi_reklmacio_bevitel.zarolt_tabla.setModel(Vevoi_reklmacio_bevitel.modell2);             
        
        String sql3 = "SELECT hiba_oka, hiba_okozoja, hiba_oka2, hiba_okozoja2 FROM  qualitydb.Vevoireklamacio_alapadat "
                + "where ID = '"+ id +"' "; 
        stmt3.execute(sql3);
        resultSet3 = stmt3.getResultSet();            
        jdbcAdapter3.fillDataTable(datatable3, resultSet3);
        Vevoi_reklmacio_bevitel.hibaoka_mezo.setText("");
        Vevoi_reklmacio_bevitel.hibaoka2_mezo.setText("");
        //String[] hibaoka = {datatable3.getRows().get(0).getString(1)};
        //String[] hibaoka2 = {datatable3.getRows().get(0).getString(3)};
        //DefaultComboBoxModel<String> hiba = new DefaultComboBoxModel<>(hibaoka);
        //DefaultComboBoxModel<String> hiba2 = new DefaultComboBoxModel<>(hibaoka2);
        //Vevoi_reklmacio_bevitel.hibaokozoja_box.setModel(hiba);
        //Vevoi_reklmacio_bevitel.hibaokozoja2_box.setModel(hiba2);
        Vevoi_reklmacio_bevitel.hibaokozoja_box.setSelectedItem(datatable3.getRows().get(0).getString(1));
        Vevoi_reklmacio_bevitel.hibaokozoja2_box.setSelectedItem(datatable3.getRows().get(0).getString(3));
        
        Vevoi_reklmacio_bevitel.hibaoka_mezo.setText(datatable3.getRows().get(0).getString(0));
        Vevoi_reklmacio_bevitel.hibaoka2_mezo.setText(datatable3.getRows().get(0).getString(2));
        sql = "SELECT intezkedes, felelos, hatarido FROM qualitydb.Vevoireklamacio_detekt where ID = '"+ id +"'";
        
        stmt.execute(sql);       
        resultSet = stmt.getResultSet();
        while(resultSet.next())
        {
            String[] ido = resultSet.getString(3).split(" ");
            Vevoi_reklmacio_bevitel.modell4.addRow(new Object[]{resultSet.getString(1), resultSet.getString(2),ido[0]});
        }
        Vevoi_reklmacio_bevitel.table.setModel(Vevoi_reklmacio_bevitel.modell4);
        
        resultSet.close();
        resultSet2.close();
        resultSet3.close();
        stmt.close();
        stmt2.close();
        stmt3.close();
        conn.close();
        
        }        
        catch (Exception e) 
        {
           e.printStackTrace();
           String hibauzenet = e.toString();
           Email hibakuldes = new Email();
           hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", this.getClass().getSimpleName()+" "+ hibauzenet);
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
	
	public void vevoi_lezarashoz(String datum, String cikkszam, String id)
    {
        Connection conn = null;
        Statement stmt = null;
        Statement stmt2 = null;
        ResultSet resultSet2;
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
        stmt2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String sajat = "";
        String sajat2 = "";
        String sajat3 = "";
        if(id.equals(""))
        {
            sajat = "SELECT * FROM  qualitydb.Vevoireklamacio_felelosok where Datum = '"+ datum +"' and Cikkszam = '"+ cikkszam +"' ";                
            sajat2 = "SELECT * FROM  qualitydb.Vevoireklamacio_detekt where Datum = '"+ datum +"' and Cikkszam = '"+ cikkszam +"' ";
            sajat3 = "SELECT Belso_koltseg, fuvar_koltseg, Selejt_koltseg, Egyeb_koltseg FROM  qualitydb.Vevoireklamacio_alapadat where Datum = '"+ datum +"' and Cikkszam = '"+ cikkszam +"' ";
        }
        else
        {
            sajat = "SELECT * FROM  qualitydb.Vevoireklamacio_felelosok where ID = '"+ id +"'";                
            sajat2 = "SELECT * FROM  qualitydb.Vevoireklamacio_detekt where ID = '"+ id +"'";
            sajat3 = "SELECT Belso_koltseg, fuvar_koltseg, Selejt_koltseg, Egyeb_koltseg FROM  qualitydb.Vevoireklamacio_alapadat where ID = '"+ id +"'";
        }
            
        stmt.execute(sajat);
        resultSet = stmt.getResultSet();
        stmt2.execute(sajat2);
        resultSet2 = stmt2.getResultSet();
        
        Vevoi_reklamacio_lezaras.table.setModel(buildTableModel(resultSet));
        Vevoi_reklamacio_lezaras.table_1.setModel(buildTableModel(resultSet2));
        
        stmt.execute(sajat3);
        resultSet = stmt.getResultSet();
        if(resultSet.next())
        {
            Vevoi_reklamacio_lezaras.koltseg_1.setText(resultSet.getString(1));
            Vevoi_reklamacio_lezaras.koltseg_2.setText(resultSet.getString(2));
            Vevoi_reklamacio_lezaras.koltseg_3.setText(resultSet.getString(3));
            Vevoi_reklamacio_lezaras.koltseg_4.setText(resultSet.getString(4));
        }

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
	
	public void vevoi_lekerdezes(String projekt, String datumtol, String datumig, String lezart, String nyitott, String id)
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
        conn = DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String sql = "";

        if(projekt.equals("-"))
        {
            projekt = "%";
        }
        
        if(id.equals(""))
        {      
            if(lezart.equals("igen") && nyitott.equals("igen"))
            {
                sql = "SELECT id, mi, vevo, Ertesites_datuma, Tipus, Miaproblema, lezaras_datuma, figyelem FROM  qualitydb.Vevoireklamacio_alap where Vevo like '"+ projekt +"' and Ertesites_Datuma >= '"+ datumtol 
                                +"' and Ertesites_Datuma <= '"+ datumig +"'";
            }
            else if(lezart.equals("igen") && nyitott.equals("nem"))
            {
                sql = "SELECT id, mi, vevo, Ertesites_datuma, Tipus, Miaproblema, lezaras_datuma, figyelem FROM  qualitydb.Vevoireklamacio_alap where Vevo like '"+ projekt +"' and Ertesites_Datuma >= '"+ datumtol 
                                +"' and Ertesites_Datuma <= '"+ datumig +"' and not Lezaras_datuma = ''  ";
            }
            else
            {
                sql = "SELECT id, mi, vevo, Ertesites_datuma, Tipus, Miaproblema, lezaras_datuma, figyelem FROM  qualitydb.Vevoireklamacio_alap where Vevo like '"+ projekt +"' and Ertesites_Datuma >= '"+ datumtol 
                        +"' and Ertesites_Datuma <= '"+ datumig +"' and Lezaras_datuma = ''  ";
            }
        }
        else
        { 
            sql = "SELECT * FROM  qualitydb.Vevoireklamacio_alap where ID = '"+ id +"'"; 
        }
        stmt.execute(sql);
        resultSet = stmt.getResultSet();

        Vevoi_reklamacio_lekerdezes.table.setModel(buildTableModel(resultSet));

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
	
	public void vevoi_kepmentes(String datum, String cikk)
    {   
        Connection con = null;
        PreparedStatement ps = null;
        FileOutputStream fs=null;
        try 
        {
           try 
           {
              Class.forName("com.mysql.cj.jdbc.Driver");                                //Driver meghívása
           } 
           catch (Exception e) 
           {
              System.out.println(e);
              String hibauzenet2 = e.toString();
              JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
           }
           
        con = (Connection) DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");                           //kapcsolat létrehozása
        ps= con.prepareStatement("SELECT Kep, Kepneve FROM qualitydb.Vevoireklamacio_kepek WHERE Datum = '"+ datum +"' and Cikkszam = '"+ cikk +"'");        //Datum = '"+ datum +"' and Cikkszam = '"+ cikkszam +"'"
        ResultSet rset = ps.executeQuery();         
        byte b[];
        Blob blob;
        int szam = 0;
        while(rset.next())
        {        
            File f = new File(System.getProperty("user.home") + "\\Desktop\\"+ rset.getString(2));
            fs = new FileOutputStream(f);
            blob = rset.getBlob("Kep");
            b = blob.getBytes(1, (int)blob.length());
            fs.write(b);
            fs.close();
            szam++;
        }
        if(szam > 0)
        {
            JOptionPane.showMessageDialog(null, "Kép/ek mentve az asztalra", "Info", 1);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Nincsen csatolt kép", "Info", 1);
        }
        }
        catch (Exception e) 
        {
           e.printStackTrace();
           String hibauzenet2 = e.toString();
           Email hibakuldes = new Email();
           hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", this.getClass().getSimpleName()+" "+ hibauzenet2);
           JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
        } 
        finally                                                                     //finally rész mindenképpen lefut, hogy hiba esetén is lezárja a kacsolatot
        {
           try 
           {
              if (ps != null)
                 con.close();
           } 
           catch (SQLException se) {}
           try 
           {
              if (con != null)
                 con.close();
           } 
           catch (SQLException se) 
           {
              se.printStackTrace();
           }  
        }
    }
	
	public void vevoi_excelmentes(String datum, String cikk)
    {   
        Connection con = null;
        PreparedStatement ps = null;
        FileOutputStream fs=null;
        try 
        {
           try 
           {
              Class.forName("com.mysql.cj.jdbc.Driver");                                //Driver meghívása
           } 
           catch (Exception e) 
           {
              System.out.println(e);
              String hibauzenet2 = e.toString();
              JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
           }
           
        con = (Connection) DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");                           //kapcsolat létrehozása
        ps= con.prepareStatement("SELECT Excel, Excelneve FROM qualitydb.Vevoireklamacio_excel WHERE Datum = '"+ datum +"' and Cikkszam = '"+ cikk +"'");        //Datum = '"+ datum +"' and Cikkszam = '"+ cikkszam +"'"
        ResultSet rset = ps.executeQuery();       
        byte b[];
        Blob blob;
        int szam = 0;
        while(rset.next())
        {
            File f = new File(System.getProperty("user.home") + "\\Desktop\\"+ rset.getString(2));
            fs = new FileOutputStream(f);
            blob = rset.getBlob("Excel");
            b = blob.getBytes(1, (int)blob.length());
            fs.write(b);
            fs.close();
            szam++;
        }
        if(szam > 0)
        {
            JOptionPane.showMessageDialog(null, "Excel/ek mentve az asztalra", "Info", 1);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Nincsen csatolt excel", "Info", 1);
        }
        
        }
        catch (Exception e) 
        {
           e.printStackTrace();
           String hibauzenet2 = e.toString();
           Email hibakuldes = new Email();
           hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", this.getClass().getSimpleName()+" "+ hibauzenet2);
           JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
        } 
        finally                                                                     //finally rész mindenképpen lefut, hogy hiba esetén is lezárja a kacsolatot
        {
           try 
           {
              if (ps != null)
                 con.close();
           } 
           catch (SQLException se) {}
           try 
           {
              if (con != null)
                 con.close();
           } 
           catch (SQLException se) 
           {
              se.printStackTrace();
           }  
        }
    }
	
	public void vevoi_6d(String datum, String cikkszam, String excelhelye)
    {
        Connection conn = null;
        Statement stmt = null;
        Statement stmt2 = null;
        Statement stmt3 = null;
        DataTable datatable = new DataTable();
        DataTable datatable2 = new DataTable();
        DataTable datatable3 = new DataTable();
        ResultSet resultSet2;
        ResultSet resultSet3;
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
        stmt2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        stmt3 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String sql = "select Felelos,\n"
                + "        zarolt,\n"
                + "        zarolt_db,\n"
                + "        talalt_db\n"
                + "from qualitydb.Vevoireklamacio_felelosok\n"
                + "where 3=3\n"
                + "     and Datum = '" + datum +"'\n"
                + "    and Cikkszam = '"+ cikkszam +"'";
        
        String sql2 = "select Felelos,\n"
                + "        intezkedes,\n"
                + "        lezarva\n"
                + "from qualitydb.Vevoireklamacio_detekt\n"
                + "where 3=3\n"
                + "     and Datum = '" + datum +"'\n"
                + "    and Cikkszam = '"+ cikkszam +"'";
        
        String sql4 = "select Hibaleiras,\n"
                + "        Hiba_oka,\n"
                + "        Hiba_oka2 \n"
                + "from qualitydb.Vevoireklamacio_alapadat \n"
                + "where 3=3 \n"
                + "     and Datum = '" + datum +"' \n"
                + "    and Tipus = '"+ cikkszam +"'";
        
        stmt.execute(sql);
        stmt2.execute(sql2);
        stmt3.execute(sql4);
        resultSet = stmt.getResultSet();
        resultSet2 = stmt2.getResultSet();
        resultSet3 = stmt3.getResultSet();

        Workbook workbook = new Workbook();
        workbook.loadFromFile(excelhelye);
        Worksheet sheet = workbook.getWorksheets().get(0);
        JdbcAdapter jdbcAdapter = new JdbcAdapter();
        JdbcAdapter jdbcAdapter2 = new JdbcAdapter();
        JdbcAdapter jdbcAdapter3 = new JdbcAdapter();
        jdbcAdapter.fillDataTable(datatable, resultSet);
        jdbcAdapter2.fillDataTable(datatable2, resultSet2);
        jdbcAdapter3.fillDataTable(datatable3, resultSet3);
        
        String d1 = "";
        String d2 = "";
        String d3 = "";
        String d4 = "";
        String d5 = "";
        String d6 = "";
        for (int szamlalo = 0; szamlalo < datatable.getRows().size(); szamlalo++) 
        {
            d1 += datatable.getRows().get(szamlalo).getString(0) + " ";
            d3 += datatable.getRows().get(szamlalo).getString(1) + "  ";
            d3 += datatable.getRows().get(szamlalo).getString(2) + " db/";
            d3 += datatable.getRows().get(szamlalo).getString(3) + " db ";
        }
        
        for (int szamlalo = 0; szamlalo < datatable2.getRows().size(); szamlalo++) 
        {
            d1 += datatable2.getRows().get(szamlalo).getString(0) + " ";
            d6 += datatable2.getRows().get(szamlalo).getString(1) + " " + datatable2.getRows().get(szamlalo).getString(0) + " " + datatable2.getRows().get(szamlalo).getString(2) + " ";
            d5 += datatable2.getRows().get(szamlalo).getString(1) + " ";
        }
        
        for (int szamlalo = 0; szamlalo < datatable3.getRows().size(); szamlalo++) 
        {
            d2 += datatable3.getRows().get(szamlalo).getString(0) + " ";
            d4 += datatable3.getRows().get(szamlalo).getString(1) + ",  ";
            d4 += datatable3.getRows().get(szamlalo).getString(2) + " ";
        }
        
        sheet.getRange().get("E" + 2).setText(d1);
        sheet.getRange().get("E" + 5).setText(d2);
        sheet.getRange().get("E" + 8).setText(d3);
        sheet.getRange().get("E" + 11).setText(d4);
        sheet.getRange().get("E" + 14).setText(d5);
        sheet.getRange().get("E" + 17).setText(d6);
        
        JFileChooser mentes_helye = new JFileChooser();
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
            for(int i = workbook2.getNumberOfSheets()-1; i > 0 ;i--)
            {    
                workbook2.removeSheetAt(i); 
            }      
            FileOutputStream output = new FileOutputStream(fajl.getAbsolutePath());
            workbook2.write(output);
            output.close();
        }
        JOptionPane.showMessageDialog(null, "Mentés sikeres", "Info", 1);

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
        } 
        catch (Exception e) 
        {
           e.printStackTrace();
           String hibauzenet = e.toString();
           Email hibakuldes = new Email();
           hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", this.getClass().getSimpleName()+" "+ hibauzenet);
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
	
	public void vevoi_lekerdezes_excel(String projekt, String datumtol, String datumig, String lezart, String nyitott)
    {
        Connection conn = null;
        Statement stmt = null;
        Statement stmt2 = null;
        Statement stmt3 = null;
        Statement stmt4 = null;
        Statement stmt5 = null;
        Statement stmt6 = null;
        Statement stmt7 = null;
        DataTable datatable2 = new DataTable();
        DataTable datatable3 = new DataTable();
        DataTable datatable4 = new DataTable();
        DataTable datatable6 = new DataTable();
        DataTable datatable7 = new DataTable();
        ResultSet resultSet2;
        ResultSet resultSet3;
        ResultSet resultSet4;
        ResultSet resultSet5;
        ResultSet resultSet6;
        ResultSet resultSet7;
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
        stmt2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        stmt3 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        stmt4 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        stmt5 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        stmt6 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        stmt7 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String sql = "";
        String sql2 = "";
        String sql3 = "";
        String sql4 = "";
        String sql5 = "";
        String sql6 = "";
        String sql7 = "";

        if(projekt.equals("-"))
        {
            projekt = "%";
        }
        
        if(lezart.equals("igen") && nyitott.equals("igen"))
        {
            sql = "SELECT DATE_FORMAT(Datum,'%Y%m'), projekt, rek_vagy, nyitva, Rek_db FROM  qualitydb.Vevoireklamacio_alapadat where Projekt like '"+ projekt +"' and Datum >= '"+ datumtol 
                            +"' and Datum <= '"+ datumig +"'";
        }
        else if(lezart.equals("igen") && nyitott.equals("nem"))
        {
            sql = "SELECT DATE_FORMAT(Datum,'%Y%m'), projekt, rek_vagy, nyitva, Rek_db FROM  qualitydb.Vevoireklamacio_alapadat where Projekt like '"+ projekt +"' and Datum >= '"+ datumtol 
                            +"' and Datum <= '"+ datumig +"' and Lezaras_ido is not null";
        }
        else
        {
            sql = "SELECT DATE_FORMAT(Datum,'%Y%m'), projekt, rek_vagy, nyitva, Rek_db FROM  qualitydb.Vevoireklamacio_alapadat where Projekt like '"+ projekt +"' and Datum >= '"+ datumtol 
                    +"' and Datum <= '"+ datumig +"' and Lezaras_ido is null  ";
        }
        // DATE_FORMAT(Datum,'%Y%m'), projekt, rek_vagy, nyitva, Rek_db
        sql2 = "select projekt, sum(Rek_db)\n"
                + "from qualitydb.Vevoireklamacio_alapadat\n"
                + "where 3=3  and Datum >= '"+ datumtol +"' and Datum <= '"+ datumig +"' \n"
           
                + "group by projekt";
        
        sql3 = "select DATE_FORMAT(Datum,'%Y%m') as 'Hónap',\n"
                + "              Projekt, \n"
                + "              sum(if(rek_vagy = 'Reklamáció', 1, 0)) as 'Reklamáció', \n"
                + "              sum(if(rek_vagy = 'Visszajelzés', 1, 0)) as 'Visszajelzés'\n"
                + "            from qualitydb.Vevoireklamacio_alapadat\n"
                + "                where 3=3 and Datum >= '"+ datumtol +"' and Datum <= '"+ datumig +"'  \n"
                + "                group by Projekt, Hónap order by Hónap asc";
        
        sql4 = "SELECT * FROM  qualitydb.Vevoireklamacio_alapadat where Projekt like '"+ projekt +"' and Datum >= '"+ datumtol +
                                    "' and Datum <= '"+ datumig +"' order by ID asc";
        sql5 = "select DATE_FORMAT(Datum,'%Y%m') as 'Hónap',            \n"
                + "        sum(if(rek_vagy = 'Reklamáció' && Nyitva is null, 1,0)) as 'Nyitott reklamáció',\n"
                + "        sum(if(rek_vagy = 'Reklamáció' && Nyitva is not null, 1,0)) as 'Lezárt reklamáció',\n"
                + "        sum(if(rek_vagy = 'Visszajelzés' && Nyitva is null, 1,0)) as 'Nyitott visszajelzés',\n"
                + "        sum(if(rek_vagy = 'Visszajelzés' && Nyitva is not null, 1,0)) as 'Lezárt visszajelzés'\n"
                + "                from qualitydb.Vevoireklamacio_alapadat\n"
                + "                    where 3=3 and Datum >= '"+ datumtol +"' and Datum <= '"+ datumig +"' \n"
                + "                    group by Hónap ";        //order by Hónap asc
        
        sql6 = "select DATE_FORMAT(Datum,'%Y%m') as 'Hónap',\n"        // --
                + "   cast(AVG(if(Nyitva is null, DATEDIFF(now(), Datum), Nyitva )) as decimal(3,0)) as 'Nyitva nap átlag'\n"
                + "           from qualitydb.Vevoireklamacio_alapadat\n"
                + "       where 3=3 and Datum >= '"+ datumtol +"' and Datum <= '"+ datumig +"' \n"
                + "             group by Hónap order by Hónap asc";
        
        sql7 = "select id, Intezkedes, felelos \n"
                + "from qualitydb.Vevoireklamacio_detekt \n"
                + "where 3 = 3 order by ID asc ";
        
        stmt.execute(sql);
        stmt2.execute(sql2);
        stmt3.execute(sql3);
        stmt4.execute(sql4);
        stmt5.execute(sql5);
        stmt6.execute(sql6);
        stmt7.execute(sql7);
        resultSet = stmt.getResultSet();
        resultSet2 = stmt2.getResultSet();
        resultSet3 = stmt3.getResultSet();
        resultSet4 = stmt4.getResultSet();
        resultSet5 = stmt5.getResultSet();
        resultSet6 = stmt6.getResultSet();
        resultSet7 = stmt7.getResultSet();
        
        ArrayList<String[]> adatok = new ArrayList<String[]>();
        ArrayList<String[]> minden = new ArrayList<String[]>();  
        while(resultSet.next())
        {
            String[] kontener = {resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5)};
            adatok.add(kontener);        
        }
        while(resultSet4.next())
        {
            String[] datum = resultSet4.getString(2).split(" ");
            String[] lezaras;
            
            if(resultSet4.getString(16) != null)
            {
                lezaras = resultSet4.getString(16).split(" ");
                String[] kontener = {resultSet4.getString(1), datum[0], resultSet4.getString(3), resultSet4.getString(4), resultSet4.getString(5), resultSet4.getString(6), resultSet4.getString(7),
                        resultSet4.getString(8), resultSet4.getString(9), resultSet4.getString(10), resultSet4.getString(11), resultSet4.getString(12), resultSet4.getString(13), resultSet4.getString(14),
                        resultSet4.getString(15), lezaras[0], resultSet4.getString(17)};
                minden.add(kontener);
            }
            else
            {
                String[] kontener = {resultSet4.getString(1), datum[0], resultSet4.getString(3), resultSet4.getString(4), resultSet4.getString(5), resultSet4.getString(6), resultSet4.getString(7),
                        resultSet4.getString(8), resultSet4.getString(9), resultSet4.getString(10), resultSet4.getString(11), resultSet4.getString(12), resultSet4.getString(13), resultSet4.getString(14),
                        resultSet4.getString(15), resultSet4.getString(16), resultSet4.getString(17)};
                minden.add(kontener);
            }          
        }
                     
        Workbook workbook = new Workbook();
        Worksheet sheet = workbook.getWorksheets().get(0);
        Worksheet sheet2 = workbook.getWorksheets().get(1);
        sheet.setName("Diagrammok");
        sheet2.setName("Alapadatok");
        JdbcAdapter jdbcAdapter2 = new JdbcAdapter();
        JdbcAdapter jdbcAdapter3 = new JdbcAdapter();    
        JdbcAdapter jdbcAdapter4 = new JdbcAdapter();   
        JdbcAdapter jdbcAdapter6 = new JdbcAdapter();   
        JdbcAdapter jdbcAdapter7 = new JdbcAdapter();   
        jdbcAdapter2.fillDataTable(datatable2, resultSet2);
        jdbcAdapter3.fillDataTable(datatable3, resultSet3);
        jdbcAdapter4.fillDataTable(datatable4, resultSet5);
        jdbcAdapter6.fillDataTable(datatable6, resultSet6);
        jdbcAdapter7.fillDataTable(datatable7, resultSet7);
        
        sheet2.getRange().get("A" + 1).setText("ID");
        sheet2.getRange().get("B" + 1).setText("Dátum");
        sheet2.getRange().get("C" + 1).setText("Projekt");
        sheet2.getRange().get("D" + 1).setText("Cikkszám");
        sheet2.getRange().get("E" + 1).setText("Változat");
        sheet2.getRange().get("F" + 1).setText("Megnevezés");
        sheet2.getRange().get("G" + 1).setText("Reklamáció vagy visszajelzés");
        sheet2.getRange().get("H" + 1).setText("Relamált db");
        sheet2.getRange().get("I" + 1).setText("Hibaleírás");
        sheet2.getRange().get("J" + 1).setText("Gyártás időpontja");
        sheet2.getRange().get("K" + 1).setText("Kiadott RMA");
        sheet2.getRange().get("L" + 1).setText("Gyökérok 1");
        sheet2.getRange().get("M" + 1).setText("Hiba okozója 1");
        sheet2.getRange().get("N" + 1).setText("Gyökérok 2");
        sheet2.getRange().get("O" + 1).setText("Hiba okozója 2");
        sheet2.getRange().get("P" + 1).setText("Lezárás időpontja");
        sheet2.getRange().get("Q" + 1).setText("nyitva");
        int szamlalo2 = 2;
        for(int szamlalo = 0; szamlalo < minden.size(); szamlalo++)
        {
            sheet2.getRange().get("A" + szamlalo2).setText(minden.get(szamlalo)[0]);
            sheet2.getRange().get("B" + szamlalo2).setText(minden.get(szamlalo)[1]);
            sheet2.getRange().get("C" + szamlalo2).setText(minden.get(szamlalo)[2]);
            sheet2.getRange().get("D" + szamlalo2).setText(minden.get(szamlalo)[3]);
            sheet2.getRange().get("E" + szamlalo2).setText(minden.get(szamlalo)[4]);
            sheet2.getRange().get("F" + szamlalo2).setText(minden.get(szamlalo)[5]);
            sheet2.getRange().get("G" + szamlalo2).setText(minden.get(szamlalo)[6]);
            sheet2.getRange().get("H" + szamlalo2).setText(minden.get(szamlalo)[7]);
            sheet2.getRange().get("I" + szamlalo2).setText(minden.get(szamlalo)[8]);
            sheet2.getRange().get("J" + szamlalo2).setText(minden.get(szamlalo)[9]);
            sheet2.getRange().get("K" + szamlalo2).setText(minden.get(szamlalo)[10]);
            sheet2.getRange().get("L" + szamlalo2).setText(minden.get(szamlalo)[11]);
            sheet2.getRange().get("M" + szamlalo2).setText(minden.get(szamlalo)[12]);
            sheet2.getRange().get("N" + szamlalo2).setText(minden.get(szamlalo)[13]);
            sheet2.getRange().get("O" + szamlalo2).setText(minden.get(szamlalo)[14]);
            sheet2.getRange().get("P" + szamlalo2).setText(minden.get(szamlalo)[15]);
            sheet2.getRange().get("Q" + szamlalo2).setText(minden.get(szamlalo)[16]);
            szamlalo2++;
        }             
        
        /********************************Első diagramm***************************************/
        
        
        //sheet2.insertDataTable(datatable4, true, 1, 1);
        int reklamacio_jan = 0;
        int visszajelzes_jan = 0;
        int reklamacio_feb = 0;
        int visszajelzes_feb = 0;
        int reklamacio_mar = 0;
        int visszajelzes_mar = 0;
        int reklamacio_apr = 0;
        int visszajelzes_apr = 0;
        int reklamacio_maj = 0;
        int visszajelzes_maj = 0;
        int reklamacio_jun = 0;
        int visszajelzes_jun = 0;
        int reklamacio_jul = 0;
        int visszajelzes_jul = 0;
        int reklamacio_aug = 0;
        int visszajelzes_aug = 0;
        int reklamacio_sze = 0;
        int visszajelzes_sze = 0;
        int reklamacio_okt = 0;
        int visszajelzes_okt = 0;
        int reklamacio_nov = 0;
        int visszajelzes_nov = 0;
        int reklamacio_dec = 0;
        int visszajelzes_dec = 0;
        
        int oszto1 = 0;
        int oszto2 = 0;
        int oszto3 = 0;
        int oszto4 = 0;
        int oszto5 = 0;
        int oszto6 = 0;
        int oszto7 = 0;
        int oszto8 = 0;
        int oszto9 = 0;
        int oszto10 = 0;
        int oszto11 = 0;
        int oszto12 = 0;
        
        sheet.getRange().get("A" + 1).setText("Hónap");
        sheet.getRange().get("B" + 1).setText("Reklamáció");
        sheet.getRange().get("C" + 1).setText("Visszajelzés");
        
        for (int szamlalo = 0; szamlalo < adatok.size(); szamlalo++) 
        {
            if(adatok.get(szamlalo)[0].contains("01"))
            {            
                if(adatok.get(szamlalo)[2].equals("Reklamáció"))
                {
                   reklamacio_jan++;                     
                }
                else
                {
                    visszajelzes_jan++;
                }
                oszto1 = 1;
            }          
            else if(adatok.get(szamlalo)[0].contains("03"))
            {            
                if(adatok.get(szamlalo)[2].equals("Reklamáció"))
                {
                   reklamacio_mar++;                     
                }
                else
                {
                    visszajelzes_mar++;
                }
                oszto3 = 1;
            }
            else if(adatok.get(szamlalo)[0].contains("04"))
            {            
                if(adatok.get(szamlalo)[2].equals("Reklamáció"))
                {
                   reklamacio_apr++;                     
                }
                else
                {
                    visszajelzes_apr++;
                }
                oszto4 = 1;
            }
            else if(adatok.get(szamlalo)[0].contains("05"))
            {            
                if(adatok.get(szamlalo)[2].equals("Reklamáció"))
                {
                   reklamacio_maj++;                     
                }
                else
                {
                    visszajelzes_maj++;
                }
                oszto5 = 1;
            }
            else if(adatok.get(szamlalo)[0].contains("06"))
            {            
                if(adatok.get(szamlalo)[2].equals("Reklamáció"))
                {
                   reklamacio_jun++;                     
                }
                else
                {
                    visszajelzes_jun++;
                }
                oszto6 = 1;
            }
            else if(adatok.get(szamlalo)[0].contains("07"))
            {            
                if(adatok.get(szamlalo)[2].equals("Reklamáció"))
                {
                   reklamacio_jul++;                     
                }
                else
                {
                    visszajelzes_jul++;
                }
                oszto7 = 1;
            }
            else if(adatok.get(szamlalo)[0].contains("08"))
            {            
                if(adatok.get(szamlalo)[2].equals("Reklamáció"))
                {
                   reklamacio_aug++;                     
                }
                else
                {
                    visszajelzes_aug++;
                }
                oszto8 = 1;
            }
            else if(adatok.get(szamlalo)[0].contains("09"))
            {            
                if(adatok.get(szamlalo)[2].equals("Reklamáció"))
                {
                   reklamacio_sze++;                     
                }
                else
                {
                    visszajelzes_sze++;
                }
                oszto9 = 1;
            }
            else if(adatok.get(szamlalo)[0].contains("10"))
            {            
                if(adatok.get(szamlalo)[2].equals("Reklamáció"))
                {
                   reklamacio_okt++;                     
                }
                else
                {
                    visszajelzes_okt++;
                }
                oszto11 = 1;
            }
            else if(adatok.get(szamlalo)[0].contains("11"))
            {            
                if(adatok.get(szamlalo)[2].equals("Reklamáció"))
                {
                   reklamacio_nov++;                     
                }
                else
                {
                    visszajelzes_nov++;
                }
                oszto11 = 1;
            }
            else if(adatok.get(szamlalo)[0].contains("12"))
            {            
                if(adatok.get(szamlalo)[2].equals("Reklamáció"))
                {
                   reklamacio_dec++;                     
                }
                else
                {
                    visszajelzes_dec++;
                }
                oszto12 = 1;
            }///idáig kell           
            else if(adatok.get(szamlalo)[0].contains("02"))
            {            
                if(adatok.get(szamlalo)[2].equals("Reklamáció"))
                {
                   reklamacio_feb++;                     
                }
                else
                {
                    visszajelzes_feb++;
                }
                oszto2 = 1;
            } 
        }   
        sheet.getRange().get("A" + 2).setText("Január");
        sheet.getRange().get("A" + 3).setText("Február");
        sheet.getRange().get("A" + 4).setText("Március");
        sheet.getRange().get("A" + 5).setText("Április");
        sheet.getRange().get("A" + 6).setText("Május");
        sheet.getRange().get("A" + 7).setText("Június");
        sheet.getRange().get("A" + 8).setText("Július");
        sheet.getRange().get("A" + 9).setText("Augusztus");
        sheet.getRange().get("A" + 10).setText("Szeptember");
        sheet.getRange().get("A" + 11).setText("Október");
        sheet.getRange().get("A" + 12).setText("November");
        sheet.getRange().get("A" + 13).setText("December");
        sheet.getRange().get("A" + 14).setText("Átlag Rek.");
        sheet.getRange().get("A" + 15).setText("Átlag Vissz.");
        
        sheet.getRange().get("B" + 2).setNumberValue(reklamacio_jan);
        sheet.getRange().get("C" + 2).setNumberValue(visszajelzes_jan);        
        sheet.getCellRange("B" + 3).setNumberValue(reklamacio_feb);
        sheet.getCellRange("C" + 3).setNumberValue(visszajelzes_feb);
        sheet.getCellRange("B" + 4).setNumberValue(reklamacio_mar);
        sheet.getCellRange("C" + 4).setNumberValue(visszajelzes_mar);
        sheet.getCellRange("B" + 5).setNumberValue(reklamacio_apr);
        sheet.getCellRange("C" + 5).setNumberValue(visszajelzes_apr);
        sheet.getCellRange("B" + 6).setNumberValue(reklamacio_maj);
        sheet.getCellRange("C" + 6).setNumberValue(visszajelzes_maj);
        sheet.getCellRange("B" + 7).setNumberValue(reklamacio_jun);
        sheet.getCellRange("C" + 7).setNumberValue(visszajelzes_jun);
        sheet.getCellRange("B" + 8).setNumberValue(reklamacio_jul);
        sheet.getCellRange("C" + 8).setNumberValue(visszajelzes_jul);
        sheet.getCellRange("B" + 9).setNumberValue(reklamacio_aug);
        sheet.getCellRange("C" + 9).setNumberValue(visszajelzes_aug);
        sheet.getCellRange("B" + 10).setNumberValue(reklamacio_sze);
        sheet.getCellRange("C" + 10).setNumberValue(visszajelzes_sze);
        sheet.getCellRange("B" + 11).setNumberValue(reklamacio_okt);
        sheet.getCellRange("C" + 11).setNumberValue(visszajelzes_okt);
        sheet.getCellRange("B" + 12).setNumberValue(reklamacio_nov);
        sheet.getCellRange("C" + 12).setNumberValue(visszajelzes_nov);
        sheet.getCellRange("B" + 13).setNumberValue(reklamacio_dec);
        sheet.getCellRange("C" + 13).setNumberValue(visszajelzes_dec);
        
        float sumrek = reklamacio_jan + reklamacio_feb + reklamacio_mar + reklamacio_apr + reklamacio_maj + reklamacio_jun + reklamacio_jul + reklamacio_aug + reklamacio_sze + reklamacio_okt +
                reklamacio_nov + reklamacio_dec;        
        float sumvissza = visszajelzes_jan + visszajelzes_feb + visszajelzes_mar + visszajelzes_apr + visszajelzes_maj + visszajelzes_jun + visszajelzes_jul + visszajelzes_aug + visszajelzes_sze + visszajelzes_okt + 
                visszajelzes_nov + visszajelzes_dec;
        //Date date = new Date();
        //LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        // int month = localDate.getMonthValue();
        String[] datum_tol = datumtol.split("\\.");
        String[] datum_ig = datumig.split("\\.");
        LocalDate start_date = LocalDate.of(Integer.parseInt(datum_tol[0]),Integer.parseInt(datum_tol[1]),Integer.parseInt(datum_tol[2]));
        LocalDate end_date = LocalDate.of(Integer.parseInt(datum_ig[0]),Integer.parseInt(datum_ig[1]),Integer.parseInt(datum_ig[2]));
        Period diff = Period.between(start_date,end_date);
        System.out.println("Eltelt hónapok száma: "+diff.getMonths());
        System.out.println("SUM rek: "+diff.getMonths());
        float atlagrek = 0.0f;
        float atlagvissza = 0.0f;
        if(diff.getMonths() == 0)
        {
            atlagrek =  sumrek;
            atlagvissza =  sumvissza;
        }
        else
        {
            atlagrek = (float) (sumrek / (oszto1+oszto2+oszto3+oszto4+oszto5+oszto6+oszto7+oszto8+oszto9+oszto10+oszto11+oszto12));           //month
            atlagvissza = (float) (sumvissza / (oszto1+oszto2+oszto3+oszto4+oszto5+oszto6+oszto7+oszto8+oszto9+oszto10+oszto11+oszto12));       //month
        }
        System.out.println("Átlag rek: "+ atlagrek);
        System.out.println("Átlag vissza: "+atlagvissza);
        sheet.getCellRange("B" + 14).setNumberValue(Math.round(atlagrek));
        sheet.getCellRange("C" + 15).setNumberValue(Math.round(atlagvissza));
               
        Chart chart = sheet.getCharts().add();
        chart.setDataRange(sheet.getCellRange("A1:C15"));
        chart.setSeriesDataFromRange(false);
        
        chart.setLeftColumn(1);
        chart.setTopRow(15);
        chart.setRightColumn(11);
        chart.setBottomRow(35);
        /*
        if (false)
        {
            chart.setChartType(ExcelChartType.Column3DStacked);
        }
        else
        {
            chart.setChartType(ExcelChartType.ColumnStacked);
        }
        */
        chart.setChartType(ExcelChartType.ColumnStacked);
        
        chart.setChartTitle("Reklamáció és visszajelzés száma");
        chart.getChartTitleArea().isBold(true);
        chart.getChartTitleArea().setSize(14);
        chart.getPrimaryCategoryAxis().setTitle("Projektek");
        chart.getPrimaryCategoryAxis().getFont().isBold(true);
        chart.getPrimaryCategoryAxis().getTitleArea().isBold(true);
        chart.getPrimaryValueAxis().setTitle("Összesen");
        chart.getPrimaryValueAxis().hasMajorGridLines(false);
        chart.getPrimaryValueAxis().setMinValue(0);
        chart.getPrimaryValueAxis().getTitleArea().isBold(true);
        chart.getPrimaryValueAxis().getTitleArea().setTextRotationAngle(90);
        
        ChartSeries series = chart.getSeries();
        for (int i = 0;i < series.size();i++)
        {
            ChartSerie cs = series.get(i);
            cs.getFormat().getOptions().isVaryColor(true);
            cs.getDataPoints().getDefaultDataPoint().getDataLabels().hasValue(true);           
        }
        
        chart.getLegend().setPosition(LegendPositionType.Top);
        
        /********************************Második diagramm*******************************************/
        
        sheet.getRange().get("L" + 1).setText("Projekt");
        sheet.getRange().get("M" + 1).setText("Reklamált db");
        int cella = 2;
        for (int szamlalo = 0; szamlalo < datatable2.getRows().size(); szamlalo++) 
        {
            sheet.getRange().get("L" + cella).setText(datatable2.getRows().get(szamlalo).getString(0));
            sheet.getRange().get("M" + cella).setNumberValue(Integer.parseInt(datatable2.getRows().get(szamlalo).getString(1)));
            cella++;
        }
        
        Chart chart2 = sheet.getCharts().add();
        chart2.setDataRange(sheet.getCellRange("L1:M" +cella));
        chart2.setSeriesDataFromRange(false);
        
        chart2.setLeftColumn(12);
        chart2.setTopRow(15);
        chart2.setRightColumn(22);
        chart2.setBottomRow(35);
        
        chart2.setChartType(ExcelChartType.ColumnStacked);
        
        chart2.setChartTitle("Reklamált db projektenként");
        chart2.getChartTitleArea().isBold(true);
        chart2.getChartTitleArea().setSize(14);
        chart2.getPrimaryCategoryAxis().setTitle("Projektek");
        chart2.getPrimaryCategoryAxis().getFont().isBold(true);
        chart2.getPrimaryCategoryAxis().getTitleArea().isBold(true);
        chart2.getPrimaryValueAxis().setTitle("Összesen");
        chart2.getPrimaryValueAxis().hasMajorGridLines(false);
        chart2.getPrimaryValueAxis().setMinValue(0);
        chart2.getPrimaryValueAxis().getTitleArea().isBold(true);
        chart2.getPrimaryValueAxis().getTitleArea().setTextRotationAngle(90);
        
        ChartSeries series2 = chart2.getSeries();
        for (int i = 0;i < series2.size();i++)
        {
            ChartSerie cs2 = series2.get(i);
            cs2.getFormat().getOptions().isVaryColor(true);
            cs2.getDataPoints().getDefaultDataPoint().getDataLabels().hasValue(true);           
        }        
        chart2.getLegend().setPosition(LegendPositionType.Top);
        
        /********************************Harmadik diagramm**********************************************/
        
        sheet.getRange().get("F" + 1).setText("Hónap");
        //sheet.getRange().get("G" + 1).setText("Projekt");
        sheet.getRange().get("G" + 1).setText("Reklamáció");
        sheet.getRange().get("H" + 1).setText("Visszajelzés");
        int cella2 = 2;
        for (int szamlalo = 0; szamlalo < datatable3.getRows().size(); szamlalo++) 
        {
            sheet.getRange().get("F" + cella2).setText(datatable3.getRows().get(szamlalo).getString(0)+" " + datatable3.getRows().get(szamlalo).getString(1));
            //sheet.getRange().get("G" + cella2).setText(datatable3.getRows().get(szamlalo).getString(1));
            String[] reklamacio = datatable3.getRows().get(szamlalo).getString(2).split("\\.");
            String[] visszajelzes = datatable3.getRows().get(szamlalo).getString(3).split("\\.");
            sheet.getCellRange("G" + cella2).setNumberValue(Integer.parseInt(reklamacio[0]));
            sheet.getCellRange("H" + cella2).setNumberValue(Integer.parseInt(visszajelzes[0]));
            cella2++;
        }
        
        Chart chart3 = sheet.getCharts().add();
        chart3.setDataRange(sheet.getCellRange("F1:H" +cella2));
        chart3.setSeriesDataFromRange(false);
        
        chart3.setLeftColumn(1);
        chart3.setTopRow(36);
        chart3.setRightColumn(16);
        chart3.setBottomRow(72);
        
        chart3.setChartType(ExcelChartType.ColumnStacked);
        
        chart3.setChartTitle("Reklamáció és visszajelzés projektenként");
        chart3.getChartTitleArea().isBold(true);
        chart3.getChartTitleArea().setSize(14);
        chart3.getPrimaryCategoryAxis().setTitle("Projektek");
        chart3.getPrimaryCategoryAxis().getFont().isBold(true);
        chart3.getPrimaryCategoryAxis().getTitleArea().isBold(true);
        chart3.getPrimaryValueAxis().setTitle("Összesen");
        chart3.getPrimaryValueAxis().hasMajorGridLines(false);
        chart3.getPrimaryValueAxis().setMinValue(0);
        chart3.getPrimaryValueAxis().getTitleArea().isBold(true);
        chart3.getPrimaryValueAxis().getTitleArea().setTextRotationAngle(90);
        
        ChartSeries series3 = chart3.getSeries();
        for (int i = 0;i < series3.size();i++)
        {
            ChartSerie cs3 = series3.get(i);
            cs3.getFormat().getOptions().isVaryColor(true);
            cs3.getDataPoints().getDefaultDataPoint().getDataLabels().hasValue(true);           
        }
        
        chart3.getLegend().setPosition(LegendPositionType.Top);
        
        
        /**********************************************Negyedik diagramm******************************************/
        
        sheet.getRange().get("R" + 1).setText("Hónap");
        sheet.getRange().get("S" + 1).setText("Nyitott rek");
        sheet.getRange().get("T" + 1).setText("Lezárt rek");
        sheet.getRange().get("U" + 1).setText("Nyitott vissza");
        sheet.getRange().get("V" + 1).setText("Lezárt vissza");
        sheet.getRange().get("W" + 1).setText("Átlag nyitva");
        
        int cella3 = 2;
        for (int szamlalo = 0; szamlalo < datatable4.getRows().size(); szamlalo++) 
        {          
            sheet.getCellRange("S" + cella3).setNumberValue(Integer.parseInt(datatable4.getRows().get(szamlalo).getString(1)));
            sheet.getCellRange("T" + cella3).setNumberValue(Integer.parseInt(datatable4.getRows().get(szamlalo).getString(2)));
            sheet.getCellRange("U" + cella3).setNumberValue(Integer.parseInt(datatable4.getRows().get(szamlalo).getString(3)));
            sheet.getCellRange("V" + cella3).setNumberValue(Integer.parseInt(datatable4.getRows().get(szamlalo).getString(4)));          
            cella3++;
        }
        int cella4 = 2;
        int sum = 0;
        String[] evszam = datumig.split("\\.");
        String[] evszam2 = datumtol.split("\\.");
        int valtoev = 0;
        if(Integer.valueOf(evszam[0]) > Integer.valueOf(evszam2[0]))
        { 
            valtoev = 1;
        }
        int ig = Integer.valueOf(evszam[0]);
        int tol = ig-1;
        System.out.println(ig);
        System.out.println(tol);
        System.out.println(evszam[1]);
        for (int szamlalo = 0; szamlalo < datatable4.getRows().size(); szamlalo++) 
        {            
            if(datatable6.getRows().get(szamlalo).getString(0).contains("02"))
            {
                if(valtoev == 1)
                {
                    if(Integer.valueOf(evszam[1]) >= 2)
                    {
                        ig = Integer.valueOf(evszam[0]);
                        tol = ig-1;
                    }
                    else
                    {
                        ig = Integer.valueOf(evszam[0]);
                        tol = ig-1;
                        ig--;
                        tol--;
                    }
                }
                sheet.getCellRange("R" + cella4).setText("Február");
                sql = "select  cast(AVG(if(Nyitva is null, DATEDIFF('"+ ig +".02.28', Datum), if(lezaras_ido > '"+ ig +".02.28',DATEDIFF('"+ ig +".02.28', Datum), DATEDIFF(Lezaras_ido, Datum) ) )) as decimal(3,0)) as 'Nyitva nap átlag'\n"
                        + "from qualitydb.Vevoireklamacio_alapadat\n"
                        + "where 3=3 and Datum >= '"+ tol +".03.01' and Datum <= '"+ ig +".02.28'";
                stmt.execute(sql);
                resultSet = stmt.getResultSet();
                if(resultSet.next())
                sheet.getCellRange("W" + cella4).setNumberValue(resultSet.getInt(1));
                if(datatable6.getRows().get(szamlalo).getString(0).contains("2022"))
                {
                    sheet.getCellRange("W" + cella4).setNumberValue(69);
                }
            }
            if(datatable6.getRows().get(szamlalo).getString(0).contains("01"))
            {
                if(valtoev == 1)
                {
                    if(Integer.valueOf(evszam[1]) >= 1)
                    {
                        ig = Integer.valueOf(evszam[0]);
                        tol = ig-1;
                    }
                    else
                    {
                        ig = Integer.valueOf(evszam[0]);
                        tol = ig-1;
                        ig--;
                        tol--;
                    }
                }
                sheet.getCellRange("R" + cella4).setText("Január");
                sql = "select  cast(AVG(if(Nyitva is null, DATEDIFF('"+ ig +".01.31', Datum), if(lezaras_ido > '"+ ig +".01.31',DATEDIFF('"+ ig +".01.31', Datum), DATEDIFF(Lezaras_ido, Datum) ) )) as decimal(3,0)) as 'Nyitva nap átlag'\n"
                        + "from qualitydb.Vevoireklamacio_alapadat\n"
                        + "where 3=3 and Datum >= '"+ tol +".02.01' and Datum <= '"+ ig +".01.31'";
                stmt.execute(sql);
                resultSet = stmt.getResultSet();
                if(resultSet.next())
                sheet.getCellRange("W" + cella4).setNumberValue(resultSet.getInt(1));
                if(datatable6.getRows().get(szamlalo).getString(0).contains("2022"))
                {
                    sheet.getCellRange("W" + cella4).setNumberValue(69);
                }
            }          
            if(datatable6.getRows().get(szamlalo).getString(0).contains("03"))
            {
                if(valtoev == 1)
                {
                    if(Integer.valueOf(evszam[1]) >= 3)
                    {
                        ig = Integer.valueOf(evszam[0]);
                        tol = ig-1;
                    }
                    else
                    {
                        ig = Integer.valueOf(evszam[0]);
                        tol = ig-1;
                        ig--;
                        tol--;
                    }
                }
                sheet.getCellRange("R" + cella4).setText("Március");
                sql = "select  cast(AVG(if(Nyitva is null, DATEDIFF('"+ ig +".03.31', Datum), if(lezaras_ido > '"+ ig +".03.31',DATEDIFF('"+ ig +".03.31', Datum), DATEDIFF(Lezaras_ido, Datum) ) )) as decimal(3,0)) as 'Nyitva nap átlag'\n"
                        + "from qualitydb.Vevoireklamacio_alapadat\n"
                        + "where 3=3 and Datum >= '"+ tol +".04.01' and Datum <= '"+ ig +".03.31'";
                stmt.execute(sql);
                resultSet = stmt.getResultSet();
                if(resultSet.next())
                sheet.getCellRange("W" + cella4).setNumberValue(resultSet.getInt(1));
                if(datatable6.getRows().get(szamlalo).getString(0).contains("2022"))
                {
                    sheet.getCellRange("W" + cella4).setNumberValue(69);
                }
            }
            if(datatable6.getRows().get(szamlalo).getString(0).contains("04"))
            {
                if(valtoev == 1)
                {
                    if(Integer.valueOf(evszam[1]) >= 4)
                    {
                        ig = Integer.valueOf(evszam[0]);
                        tol = ig-1;
                    }
                    else
                    {
                        ig = Integer.valueOf(evszam[0]);
                        tol = ig-1;
                        ig--;
                        tol--;
                    }
                }
                sheet.getCellRange("R" + cella4).setText("Április");
                sql = "select  cast(AVG(if(Nyitva is null, DATEDIFF('"+ ig +".04.30', Datum), if(lezaras_ido > '"+ ig +".04.30',DATEDIFF('"+ ig +".04.30', Datum), DATEDIFF(Lezaras_ido, Datum) ) )) as decimal(3,0)) as 'Nyitva nap átlag'\n"
                        + "from qualitydb.Vevoireklamacio_alapadat\n"
                        + "where 3=3 and Datum >= '"+ tol +".05.01' and Datum <= '"+ ig +".04.30'";
                stmt.execute(sql);
                resultSet = stmt.getResultSet();
                if(resultSet.next())
                sheet.getCellRange("W" + cella4).setNumberValue(resultSet.getInt(1));
                if(datatable6.getRows().get(szamlalo).getString(0).contains("2022"))
                {
                    sheet.getCellRange("W" + cella4).setNumberValue(69);
                }
            }
            if(datatable6.getRows().get(szamlalo).getString(0).contains("05"))
            {
                if(valtoev == 1)
                {
                    if(Integer.valueOf(evszam[1]) >= 5)
                    {
                        ig = Integer.valueOf(evszam[0]);
                        tol = ig-1;
                    }
                    else
                    {
                        ig = Integer.valueOf(evszam[0]);
                        tol = ig-1;
                        ig--;
                        tol--;
                    }
                }
                sheet.getCellRange("R" + cella4).setText("Május");
                sql = "select  cast(AVG(if(Nyitva is null, DATEDIFF('"+ ig +".05.31', Datum), if(lezaras_ido > '"+ ig +".05.31',DATEDIFF('"+ ig +".05.31', Datum), DATEDIFF(Lezaras_ido, Datum) ) )) as decimal(3,0)) as 'Nyitva nap átlag'\n"
                        + "from qualitydb.Vevoireklamacio_alapadat\n"
                        + "where 3=3 and Datum >= '"+ tol +".06.01' and Datum <= '"+ ig +".05.31'";
                stmt.execute(sql);
                resultSet = stmt.getResultSet();
                if(resultSet.next())
                sheet.getCellRange("W" + cella4).setNumberValue(resultSet.getInt(1));
                if(datatable6.getRows().get(szamlalo).getString(0).contains("2022"))
                {
                    sheet.getCellRange("W" + cella4).setNumberValue(69);
                }
            }
            if(datatable6.getRows().get(szamlalo).getString(0).contains("06"))
            {
                if(valtoev == 1)
                {
                    if(Integer.valueOf(evszam[1]) >= 6)
                    {
                        ig = Integer.valueOf(evszam[0]);
                        tol = ig-1;
                    }
                    else
                    {
                        ig = Integer.valueOf(evszam[0]);
                        tol = ig-1;
                        ig--;
                        tol--;
                    }
                }
                sheet.getCellRange("R" + cella4).setText("Június");
                sql = "select  cast(AVG(if(Nyitva is null, DATEDIFF('"+ ig +".06.30', Datum), if(lezaras_ido > '"+ ig +".06.30',DATEDIFF('"+ ig +".06.30', Datum), DATEDIFF(Lezaras_ido, Datum) ) )) as decimal(3,0)) as 'Nyitva nap átlag'\n"
                        + "from qualitydb.Vevoireklamacio_alapadat\n"
                        + "where 3=3 and Datum >= '"+ tol +".07.01' and Datum <= '"+ ig +".06.30'";
                stmt.execute(sql);
                resultSet = stmt.getResultSet();
                if(resultSet.next())
                sheet.getCellRange("W" + cella4).setNumberValue(resultSet.getInt(1));
                if(datatable6.getRows().get(szamlalo).getString(0).contains("2022"))
                {
                    sheet.getCellRange("W" + cella4).setNumberValue(69);
                }
            }
            if(datatable6.getRows().get(szamlalo).getString(0).contains("07"))
            {
                if(valtoev == 1)
                {
                    if(Integer.valueOf(evszam[1]) >= 7)
                    {
                        ig = Integer.valueOf(evszam[0]);
                        tol = ig-1;
                    }
                    else
                    {
                        ig = Integer.valueOf(evszam[0]);
                        tol = ig-1;
                        ig--;
                        tol--;
                    }
                }
                sheet.getCellRange("R" + cella4).setText("Július");
                sql = "select  cast(AVG(if(Nyitva is null, DATEDIFF('"+ ig +".07.31', Datum), if(lezaras_ido > '"+ ig +".07.31',DATEDIFF('"+ ig +".07.31', Datum), DATEDIFF(Lezaras_ido, Datum) ) )) as decimal(3,0)) as 'Nyitva nap átlag'\n"
                        + "from qualitydb.Vevoireklamacio_alapadat\n"
                        + "where 3=3 and Datum >= '"+ tol +".08.01' and Datum <= '"+ ig +".07.31'";
                stmt.execute(sql);
                resultSet = stmt.getResultSet();
                if(resultSet.next())
                sheet.getCellRange("W" + cella4).setNumberValue(resultSet.getInt(1));
                if(datatable6.getRows().get(szamlalo).getString(0).contains("2022"))
                {
                    sheet.getCellRange("W" + cella4).setNumberValue(69);
                }
            }
            if(datatable6.getRows().get(szamlalo).getString(0).contains("08"))
            {
                if(valtoev == 1)
                {
                    if(Integer.valueOf(evszam[1]) >= 8)
                    {
                        ig = Integer.valueOf(evszam[0]);
                        tol = ig-1;
                    }
                    else
                    {
                        ig = Integer.valueOf(evszam[0]);
                        tol = ig-1;
                        ig--;
                        tol--;
                    }
                }
                sheet.getCellRange("R" + cella4).setText("Augusztus");
                sql = "select  cast(AVG(if(Nyitva is null, DATEDIFF('"+ ig +".08.31', Datum), if(lezaras_ido > '"+ ig +".08.31',DATEDIFF('"+ ig +".08.31', Datum), DATEDIFF(Lezaras_ido, Datum) ) )) as decimal(3,0)) as 'Nyitva nap átlag'\n"
                        + "from qualitydb.Vevoireklamacio_alapadat\n"
                        + "where 3=3 and Datum >= '"+ tol +".09.01' and Datum <= '"+ ig +".08.31'";
                stmt.execute(sql);
                resultSet = stmt.getResultSet();
                if(resultSet.next())
                sheet.getCellRange("W" + cella4).setNumberValue(resultSet.getInt(1));
                if(datatable6.getRows().get(szamlalo).getString(0).contains("2022"))
                {
                    sheet.getCellRange("W" + cella4).setNumberValue(69);
                }
            }
            if(datatable6.getRows().get(szamlalo).getString(0).contains("09"))
            {
                if(valtoev == 1)
                {
                    if(Integer.valueOf(evszam[1]) >= 9)
                    {
                        ig = Integer.valueOf(evszam[0]);
                        tol = ig-1;
                    }
                    else
                    {
                        ig = Integer.valueOf(evszam[0]);
                        tol = ig-1;
                        ig--;
                        tol--;
                    }
                }
                sheet.getCellRange("R" + cella4).setText("Szeptember");
                sql = "select  cast(AVG(if(Nyitva is null, DATEDIFF('"+ ig +".09.30', Datum), if(lezaras_ido > '"+ ig +".09.30',DATEDIFF('"+ ig +".09.30', Datum), DATEDIFF(Lezaras_ido, Datum) ) )) as decimal(3,0)) as 'Nyitva nap átlag'\n"
                        + "from qualitydb.Vevoireklamacio_alapadat\n"
                        + "where 3=3 and Datum >= '"+ tol +".10.01' and Datum <= '"+ ig +".09.30'";
                stmt.execute(sql);
                resultSet = stmt.getResultSet();
                if(resultSet.next())
                sheet.getCellRange("W" + cella4).setNumberValue(resultSet.getInt(1));
                if(datatable6.getRows().get(szamlalo).getString(0).contains("2022"))
                {
                    sheet.getCellRange("W" + cella4).setNumberValue(69);
                }
            }
            if(datatable6.getRows().get(szamlalo).getString(0).contains("10"))
            {
                if(valtoev == 1)
                {
                    if(Integer.valueOf(evszam[1]) >= 10)
                    {
                        ig = Integer.valueOf(evszam[0]);
                        tol = ig-1;
                    }
                    else
                    {
                        ig = Integer.valueOf(evszam[0]);
                        tol = ig-1;
                        ig--;
                        tol--;
                    }
                }
                sheet.getCellRange("R" + cella4).setText("Október");
                sql = "select  cast(AVG(if(Nyitva is null, DATEDIFF('"+ ig +".10.31', Datum), if(lezaras_ido > '"+ ig +".10.31',DATEDIFF('"+ ig +".10.31', Datum), DATEDIFF(Lezaras_ido, Datum) ) )) as decimal(3,0)) as 'Nyitva nap átlag'\n"
                        + "from qualitydb.Vevoireklamacio_alapadat\n"
                        + "where 3=3 and Datum >= '"+ tol +".11.01' and Datum <= '"+ ig +".10.31'";
                stmt.execute(sql);
                resultSet = stmt.getResultSet();
                if(resultSet.next())
                sheet.getCellRange("W" + cella4).setNumberValue(resultSet.getInt(1));
                if(datatable6.getRows().get(szamlalo).getString(0).contains("2022"))
                {
                    sheet.getCellRange("W" + cella4).setNumberValue(69);
                }
            }
            if(datatable6.getRows().get(szamlalo).getString(0).contains("11"))
            {
                if(valtoev == 1)
                {
                    if(Integer.valueOf(evszam[1]) >= 11)
                    {
                        ig = Integer.valueOf(evszam[0]);
                        tol = ig-1;
                    }
                    else
                    {
                        ig = Integer.valueOf(evszam[0]);
                        tol = ig-1;
                        ig--;
                        tol--;
                    }
                }
                sheet.getCellRange("R" + cella4).setText("November");
                sql = "select  cast(AVG(if(Nyitva is null, DATEDIFF('"+ ig +".11.30', Datum), if(lezaras_ido > '"+ ig +".11.30',DATEDIFF('"+ ig +".11.30', Datum), DATEDIFF(Lezaras_ido, Datum) ) )) as decimal(3,0)) as 'Nyitva nap átlag'\n"
                        + "from qualitydb.Vevoireklamacio_alapadat\n"
                        + "where 3=3 and Datum >= '"+ tol +".12.01' and Datum <= '"+ ig +".11.30'";
                stmt.execute(sql);
                resultSet = stmt.getResultSet();
                if(resultSet.next())
                sheet.getCellRange("W" + cella4).setNumberValue(resultSet.getInt(1));
                if(datatable6.getRows().get(szamlalo).getString(0).contains("2022"))
                {
                    sheet.getCellRange("W" + cella4).setNumberValue(69);
                }
            }
            if(datatable6.getRows().get(szamlalo).getString(0).contains("12"))
            {
                if(valtoev == 1)
                {
                    if(Integer.valueOf(evszam[1]) >= 12)
                    {
                        ig = Integer.valueOf(evszam[0]);
                        tol = ig-1;
                    }
                    else
                    {
                        ig = Integer.valueOf(evszam[0]);
                        tol = ig-1;
                        ig--;
                        tol--;
                    }
                }
                sheet.getCellRange("R" + cella4).setText("December");
                sql = "select  cast(AVG(if(Nyitva is null, DATEDIFF('"+ ig +".12.31', Datum), if(lezaras_ido > '"+ ig +".12.31',DATEDIFF('"+ ig +".12.31', Datum), DATEDIFF(Lezaras_ido, Datum) ) )) as decimal(3,0)) as 'Nyitva nap átlag'\n"
                        + "from qualitydb.Vevoireklamacio_alapadat\n"
                        + "where 3=3 and Datum >= '"+ ig +".01.01' and Datum <= '"+ ig +".12.31'";
                stmt.execute(sql);
                resultSet = stmt.getResultSet();
                if(resultSet.next())
                sheet.getCellRange("W" + cella4).setNumberValue(resultSet.getInt(1));
                if(datatable6.getRows().get(szamlalo).getString(0).contains("2022"))
                {
                    sheet.getCellRange("W" + cella4).setNumberValue(69);
                }
            }
            //sheet.getCellRange("R" + cella4).setNumberValue(Integer.parseInt(datatable6.getRows().get(szamlalo).getString(0)));
            cella4++;
        }
        
        for (int szamlalo = 0; szamlalo < datatable4.getRows().size(); szamlalo++) 
        {         
           sum += Integer.parseInt(datatable6.getRows().get(szamlalo).getString(1));          
        }
        int atlag = sum / datatable4.getRows().size();
        sheet.getCellRange("W14").setNumberValue(atlag);
        
        Chart chart5 = sheet.getCharts().add();
        chart5.setChartTitle("Nyitott és lezárt visszajelzés és reklamáció");
        chart5.setDataRange(sheet.getCellRange("R1:W13"));
        chart5.setSeriesDataFromRange(false);
        chart5.getPrimaryValueAxis().setTitle("Reklamáció db szám");
        chart5.getSecondaryValueAxis().setTitle("Átlag nyitva nap");
 
        //Set position of the chart
        chart5.setLeftColumn(1);
        chart5.setTopRow(73);
        chart5.setRightColumn(14);
        chart5.setBottomRow(93);
 
        //Apply different chart types to different data series
        ChartSerie cs5 = (ChartSerie)chart5.getSeries().get(0);
        cs5.setSerieType(ExcelChartType.ColumnStacked);
        ChartSerie cs6 = (ChartSerie)chart5.getSeries().get(4);
        cs6.setSerieType(ExcelChartType.LineMarkers);
 
        //Add a secondary Y axis to the chart
        cs6.setUsePrimaryAxis(false);
        
/**********************************************Ötödik diagramm******************************************/
        
        sheet.getRange().get("A" + 100).setText("Projekt");
        sheet.getRange().get("B" + 100).setText("Belső költség");
        sheet.getRange().get("C" + 100).setText("Fuvar költség");
        sheet.getRange().get("D" + 100).setText("Selejt költség");
        sheet.getRange().get("E" + 100).setText("Egyéb költség");
        int cellaszam6 = 101;
        int resultvege = 0;
        sql = "select projekt,\n"
                + "sum(belso_koltseg),\n"
                + "sum(fuvar_koltseg),\n"
                + "sum(selejt_koltseg),\n"
                + "sum(egyeb_koltseg)\n"
                + "from qualitydb.Vevoireklamacio_alapadat\n"
                + "where 3=3 and datum >= '"+datumtol +"' and datum <= '"+ datumig +"'\n"
                + "group by projekt order by projekt asc";
        stmt.execute(sql);
        resultSet = stmt.getResultSet();
        while(resultSet.next())
        {
            sheet.getRange().get("A" + cellaszam6).setText(resultSet.getString(1));
            sheet.getRange().get("B" + cellaszam6).setNumberValue(resultSet.getInt(2));
            sheet.getRange().get("C" + cellaszam6).setNumberValue(resultSet.getInt(3));
            sheet.getRange().get("D" + cellaszam6).setNumberValue(resultSet.getInt(4));
            sheet.getRange().get("E" + cellaszam6).setNumberValue(resultSet.getInt(5));
            cellaszam6++;
            resultvege++;
        }
        Chart chart6 = sheet.getCharts().add();
        chart6.setDataRange(sheet.getCellRange("A100:E"+ (100+resultvege)));            //
        chart6.setSeriesDataFromRange(false);
        
        chart6.setLeftColumn(1);
        chart6.setTopRow(105);
        chart6.setRightColumn(11);
        chart6.setBottomRow(135);
        
        chart6.setChartType(ExcelChartType.ColumnStacked);
        
        chart6.setChartTitle("Reklamáció költsége projektenként");
        chart6.getChartTitleArea().isBold(true);
        chart6.getChartTitleArea().setSize(14);
        chart6.getPrimaryCategoryAxis().setTitle("Projektek");
        chart6.getPrimaryCategoryAxis().getFont().isBold(true);
        chart6.getPrimaryCategoryAxis().getTitleArea().isBold(true);
        chart6.getPrimaryValueAxis().setTitle("Összesen");
        chart6.getPrimaryValueAxis().hasMajorGridLines(false);
        chart6.getPrimaryValueAxis().setMinValue(0);
        chart6.getPrimaryValueAxis().setMaxValue(500000);
        chart6.getPrimaryValueAxis().getTitleArea().isBold(true);
        chart6.getPrimaryValueAxis().getTitleArea().setTextRotationAngle(90);
        
        ChartSeries series6 = chart6.getSeries();
        for (int i = 0;i < series6.size();i++)
        {
            ChartSerie cs = series6.get(i);
            cs.getFormat().getOptions().isVaryColor(true);
            cs.getDataPoints().getDefaultDataPoint().getDataLabels().hasValue(true);           
        }
        
        chart6.getLegend().setPosition(LegendPositionType.Top);
        
        /****************************************beírom a hiányzó adatokat a második oldalra****************************************/
        String intezkedes = "";
        String felelos = "";
        int sor = 2;
        
        for(int a = 0; a < minden.size(); a++)
        {
            for(int b = 0; b < datatable7.getRows().size(); b++)
            {
                if(minden.get(a)[0].equals(datatable7.getRows().get(b).getString(0)))
                {
                    intezkedes += datatable7.getRows().get(b).getString(1) + "\n";
                    felelos += datatable7.getRows().get(b).getString(2) + "\n";
                }
            }
            sheet2.getRange().get("R" + sor).setText(intezkedes);
            sheet2.getRange().get("S" + sor).setText(felelos);
            sor++;
            intezkedes = "";
            felelos = "";
        }
        sheet2.getAutoFilters().setRange(sheet.getCellRange("A1:S1"));
        sheet2.getAllocatedRange().autoFitColumns();
        sheet2.getAllocatedRange().autoFitRows();       
        sheet2.getCellRange("A1:S1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
        
        sheet.getAllocatedRange().autoFitColumns();
        sheet.getAllocatedRange().autoFitRows();       
        sheet.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                
        String mentes_helye = System.getProperty("user.home") + "\\Desktop\\Vevői reklamáció kimutatás.xlsx";
        
        resultSet.close();
        stmt.close();
        conn.close();
        File f = new File(mentes_helye);
        f.getParentFile().mkdirs();
        f.createNewFile();
        FileInputStream fileStream = new FileInputStream(mentes_helye);
        try
        {
            workbook.saveToFile(mentes_helye, ExcelVersion.Version2016);
            try (XSSFWorkbook workbook2 = new XSSFWorkbook(fileStream)) 
            {
                for(int i = workbook2.getNumberOfSheets()-1; i > 1 ;i--)
                {    
                    workbook2.removeSheetAt(i); 
                }
                workbook2.setActiveSheet(0);
                FileOutputStream output = new FileOutputStream(mentes_helye);
                workbook2.write(output);
                output.close();
                JOptionPane.showMessageDialog(null, "Mentve az asztalra Vevői reklamáció kimutatás.xlsx néven", "Info", 1);
            }
        }
        catch (Exception e1) 
        {
            mentes_helye = System.getProperty("user.home") + "\\Desktop\\Vevői reklamáció kimutatás_"+ Vevoi_reklamacio_lekerdezes.fajlszam +".xlsx";
            workbook.saveToFile(mentes_helye, ExcelVersion.Version2016);
            try (XSSFWorkbook workbook2 = new XSSFWorkbook(fileStream)) 
            {
                for(int i = workbook2.getNumberOfSheets()-1; i > 1 ;i--)
                {    
                    workbook2.removeSheetAt(i); 
                }
                workbook2.setActiveSheet(0);
                FileOutputStream output = new FileOutputStream(mentes_helye);
                workbook2.write(output);
                output.close();
                JOptionPane.showMessageDialog(null, "Mentve az asztalra Vevői reklamáció kimutatás_"+ Vevoi_reklamacio_lekerdezes.fajlszam +".xlsx néven", "Info", 1);
            }
            Vevoi_reklamacio_lekerdezes.fajlszam++;
        } 
        

        resultSet.close();
        stmt.close();
        conn.close();
        
        } 
        catch (SQLException e1) 
        {
           e1.printStackTrace();
           String hibauzenet2 = e1.toString();
           Email hibakuldes = new Email();
           hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", this.getClass().getSimpleName()+" "+ hibauzenet2);
           JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
        } 
        catch (Exception e) 
        {
           e.printStackTrace();
           String hibauzenet2 = e.toString();
           Email hibakuldes = new Email();
           hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", this.getClass().getSimpleName()+" "+ hibauzenet2);
           JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
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
	
	public void vevoi_email()
    {
        Connection conn = null;
        Statement stmt = null;
        Statement stmt2 = null;
        Statement stmt3 = null;
        Statement stmt4 = null;
        DataTable datatable = new DataTable();
        DataTable datatable2 = new DataTable();
        //DataTable datatable3 = new DataTable();
        //DataTable datatable4 = new DataTable();
        ResultSet resultSet2;
        ResultSet resultSet3;
        ResultSet resultSet4;
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
        stmt2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        stmt3 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        stmt4 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
       
        String sql = "SELECT Felelos, Hatarido, ID, Datum, Cikkszam, Ertesitve, Zarolt, Ujra_ertesitve FROM  qualitydb.Vevoireklamacio_felelosok where Lezarva is null";                
        String sql2 = "SELECT Felelos, Hatarido, ID, Datum, Cikkszam, Ertesitve, Intezkedes, Ujra_ertesitve  FROM  qualitydb.Vevoireklamacio_detekt where Lezarva is null ";     
                    
        stmt.execute(sql);
        stmt2.execute(sql2);       
        resultSet = stmt.getResultSet();
        resultSet2 = stmt2.getResultSet();
      
        
        JdbcAdapter jdbcAdapter = new JdbcAdapter();
        JdbcAdapter jdbcAdapter2 = new JdbcAdapter();       
        jdbcAdapter.fillDataTable(datatable, resultSet);
        jdbcAdapter2.fillDataTable(datatable2, resultSet2);
       
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        
        String maiido = formatter.format(date);
       
        
        CallableStatement cstmt = conn.prepareCall("{call qualitydb.vevoi_ertesites(?,?) }");                                   //tárolt eljárást hívja meg
        ArrayList<String> felelosok = new ArrayList<String>();
        ArrayList<String> adatok = new ArrayList<String>();
        ArrayList<String> lejart_felelosok = new ArrayList<String>();
        ArrayList<String> lejart_adatok = new ArrayList<String>();
        for(int szamlalo = 0; szamlalo < datatable.getRows().size(); szamlalo++)
        {
            if(datatable.getRows().get(szamlalo).getString(1) != null)
            {
                Date hatarido = formatter.parse(datatable.getRows().get(szamlalo).getString(1));
                String hatarido2 = formatter.format(hatarido);
                cstmt.setString(1, hatarido2);
                cstmt.setString(2, maiido);
                cstmt.execute();
                resultSet3 = cstmt.getResultSet();           
                if(resultSet3.next())
                {              
                    if(Integer.parseInt(resultSet3.getString(1)) <= 5 && Integer.parseInt(resultSet3.getString(1)) >= 0)
                    {
                        if(datatable.getRows().get(szamlalo).getString(5).equals("Nem"))
                        {
                            felelosok.add(datatable.getRows().get(szamlalo).getString(0));
                            adatok.add(datatable.getRows().get(szamlalo).getString(2)+ ","+ datatable.getRows().get(szamlalo).getString(3)+ ","+ datatable.getRows().get(szamlalo).getString(4)+ ","+datatable.getRows().get(szamlalo).getString(6));
                        }
                    }
                    if(Integer.parseInt(resultSet3.getString(1)) < 0)
                    {
                        if(datatable.getRows().get(szamlalo).getString(5).equals("Igen") && datatable.getRows().get(szamlalo).getString(7).equals("Nem"))
                        {
                            lejart_felelosok.add(datatable.getRows().get(szamlalo).getString(0));
                            lejart_adatok.add(datatable.getRows().get(szamlalo).getString(2)+ ","+ datatable.getRows().get(szamlalo).getString(3)+ ","+ datatable.getRows().get(szamlalo).getString(4)+ ","+datatable.getRows().get(szamlalo).getString(6));
                        }
                    }
                }
            }
        }
        
        for(int szamlalo = 0; szamlalo < datatable2.getRows().size(); szamlalo++)
        {
            Date hatarido = formatter.parse(datatable2.getRows().get(szamlalo).getString(1));
            String hatarido2 = formatter.format(hatarido);
            cstmt.setString(1, hatarido2);
            cstmt.setString(2, maiido);
            cstmt.execute();
            resultSet3 = cstmt.getResultSet();
            if(resultSet3.next())
            {
                if(Integer.parseInt(resultSet3.getString(1)) <= 5 && Integer.parseInt(resultSet3.getString(1)) >= 0)
                {
                    if(datatable2.getRows().get(szamlalo).getString(5).equals("Nem"))
                    {
                        felelosok.add(datatable2.getRows().get(szamlalo).getString(0));
                        adatok.add(datatable2.getRows().get(szamlalo).getString(2)+ ","+ datatable2.getRows().get(szamlalo).getString(3)+ ","+ datatable2.getRows().get(szamlalo).getString(4)+ ","+datatable2.getRows().get(szamlalo).getString(6));
                    }
                }
                if(Integer.parseInt(resultSet3.getString(1)) < 0)
                {
                    if(datatable2.getRows().get(szamlalo).getString(5).equals("Igen") && datatable2.getRows().get(szamlalo).getString(7).equals("Nem"))
                    {
                        lejart_felelosok.add(datatable2.getRows().get(szamlalo).getString(0));
                        lejart_adatok.add(datatable2.getRows().get(szamlalo).getString(2)+ ","+ datatable2.getRows().get(szamlalo).getString(3)+ ","+ datatable2.getRows().get(szamlalo).getString(4)+ ","+datatable2.getRows().get(szamlalo).getString(6));
                    }
                }
            }
        }
        
        for(int szamlalo = 0; szamlalo < felelosok.size(); szamlalo++)
        {          
            System.out.println(felelosok.get(szamlalo));
        }
        for(int szamlalo = 0; szamlalo < lejart_felelosok.size(); szamlalo++)
        {          
            System.out.println("Lejárt: "+lejart_felelosok.get(szamlalo));
        }
        
        if(felelosok.size() > 0)
        {
            Workbook workbook = new Workbook();
            workbook.loadFromFile(emaillista);
            Worksheet sheet = workbook.getWorksheets().get(0);
            DataTable felelosexcel = sheet.exportDataTable(sheet.getAllocatedRange(), false, false );
            for(int szamlalo = 0; szamlalo < felelosok.size(); szamlalo++)
            {          
                for(int szamlalo2 = 0; szamlalo2 < felelosexcel.getRows().size(); szamlalo2++)
                {          
                    if(felelosok.get(szamlalo).equals(felelosexcel.getRows().get(szamlalo2).getString(0)))
                    {                       
                        String[] adat = adatok.get(szamlalo).split(",");
                        String sql4 = "select hibaleiras from qualitydb.Vevoireklamacio_alapadat where id = '"+ adat[0] +"'";
                        stmt4.execute(sql4);       
                        resultSet4 = stmt4.getResultSet();
                        if(resultSet4.next())
                        {
                            Email emailkuldes = new Email();
                            emailkuldes.sima_emailkuldes(felelosexcel.getRows().get(szamlalo2).getString(0), felelosexcel.getRows().get(szamlalo2).getString(1), 
                                   felelosexcel.getRows().get(szamlalo2).getString(1), adat[0], adat[1], adat[2], adat[3], resultSet4.getString(1));
                            System.out.println(felelosexcel.getRows().get(szamlalo2).getString(0)+ " " + felelosexcel.getRows().get(szamlalo2).getString(1) + " "+ felelosexcel.getRows().get(szamlalo2).getString(1)+" " 
                                   + felelosexcel.getRows().get(szamlalo2).getString(3));
                            String modosit = "update qualitydb.Vevoireklamacio_felelosok set  Ertesitve = 'Igen' where ID = '"+ adat[0]  +"' and Felelos = '"+ felelosok.get(szamlalo)  +"'";
                            String modosit2 = "update qualitydb.Vevoireklamacio_detekt set  Ertesitve = 'Igen' where ID = '"+ adat[0]  +"' and Felelos = '"+ felelosok.get(szamlalo)  +"'";
                            stmt3.executeUpdate(modosit);
                            stmt3.executeUpdate(modosit2);
                        }
                    }
                }
            }
            
        }
        if(lejart_felelosok.size() > 0)
        {
            Workbook workbook = new Workbook();
            workbook.loadFromFile(emaillista);
            Worksheet sheet = workbook.getWorksheets().get(0);
            DataTable felelosexcel = sheet.exportDataTable(sheet.getAllocatedRange(), false, false );
            for(int szamlalo = 0; szamlalo < lejart_felelosok.size(); szamlalo++)
            {          
                for(int szamlalo2 = 0; szamlalo2 < felelosexcel.getRows().size(); szamlalo2++)
                {          
                    if(lejart_felelosok.get(szamlalo).equals(felelosexcel.getRows().get(szamlalo2).getString(0)))
                    {                       
                        String[] adat = lejart_adatok.get(szamlalo).split(",");
                        String sql4 = "select hibaleiras from qualitydb.Vevoireklamacio_alapadat where id = '"+ adat[0] +"'";
                        stmt4.execute(sql4);       
                        resultSet4 = stmt4.getResultSet();
                        if(resultSet4.next())
                        {
                            Email emailkuldes = new Email();
                            emailkuldes.emailkuldes(felelosexcel.getRows().get(szamlalo2).getString(0), felelosexcel.getRows().get(szamlalo2).getString(1), 
                                   felelosexcel.getRows().get(szamlalo2).getString(1), felelosexcel.getRows().get(szamlalo2).getString(3), adat[0], adat[1], adat[2], adat[3], resultSet4.getString(1));
                            System.out.println(felelosexcel.getRows().get(szamlalo2).getString(0)+ " " + felelosexcel.getRows().get(szamlalo2).getString(1) + " "+ felelosexcel.getRows().get(szamlalo2).getString(1)+" " 
                                   + felelosexcel.getRows().get(szamlalo2).getString(3));
                            String modosit = "update qualitydb.Vevoireklamacio_felelosok set  Ujra_ertesitve = 'Igen' where ID = '"+ adat[0]  +"' and Felelos = '"+ lejart_felelosok.get(szamlalo)  +"'";
                            String modosit2 = "update qualitydb.Vevoireklamacio_detekt set  Ujra_ertesitve = 'Igen' where ID = '"+ adat[0]  +"' and Felelos = '"+ lejart_felelosok.get(szamlalo)  +"'";
                            stmt3.executeUpdate(modosit);
                            stmt3.executeUpdate(modosit2);
                        }
                    }
                }
            }
            
        }
        System.out.println("Lefutott az Email rész");
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
	
	public void vevoi_email2()
    {
        Connection conn = null;
        Statement stmt = null;
        Statement stmt2 = null;
        ResultSet rs;
        ResultSet rs2;
        //ResultSet rs2;
        try 
        {           
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "SELECT ID, Felelos, Vevo, Tipus, Miaproblema FROM  qualitydb.Vevoireklamacio_alap where D3 is null and D3_ertesitve = 'nem' and ((DATEDIFF(now(), ertesites_datuma)) -\n"
                    + "        ((WEEK(now()) - WEEK(ertesites_datuma)) * 2) -\n"
                    + "        (case when weekday(now()) = 5 then 1 else 0 end) -\n"
                    + "        (case when weekday(ertesites_datuma) = 4 then 1 else 0 end)) >= 3";                                            
            stmt.execute(sql);    
            rs = stmt.getResultSet();      
            Workbook workbook = new Workbook();
            workbook.loadFromFile(emaillista2);
            //workbook.loadFromFile(emaillista3);
            Worksheet sheet = workbook.getWorksheets().get(0);
            Email email = new Email();
            String cimzett = "";
            SQA_SQL modosit = new SQA_SQL();
            //////Lejárt D3 keresése
            while(rs.next())
            {
                String[] nevek = rs.getString(2).split(";");
                for(int szamlalo = 1; szamlalo < sheet.getLastRow(); szamlalo++)
                {
                    if(nevek[nevek.length-1].equals(sheet.getRange().get("B" + szamlalo).getText()))
                    {
                        String bathelye = "\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\bat fájlok\\indito"+ rs.getString(1) +".bat";
                        try
                        {
                            PrintWriter writer = new PrintWriter(bathelye, "UTF-8");
                            writer.print("java -jar \\\\172.20.22.7\\kozos\\Jar-ok\\EASQAS.jar Vevoi "+rs.getString(1));
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
                        cimzett = sheet.getRange().get("C" + szamlalo).getText();
                        email.linkes_email("easqas@veas.videoton.hu", cimzett, "", "Lejárt D3", "<br>Tisztelt "+nevek[nevek.length-1] +"! <br>"
                                + "<br>Lejárt a D3 határideje az alábbi reklamációnál: \nID: "+ rs.getString(1)+"</br>"
                                + "<br>Vevő: "+ rs.getString(3) +"</br>"
                                + "<br>Típus: "+ rs.getString(4) +"</br>" 
                                + "<br>Mi a probléma: "+ rs.getString(5) +"</br>"
                                + "<br>Kérem, minél előbb zárja le!" +"</br>"
                                + "<br>Link: <a href='"+ bathelye +"'>Reklamáció link</a> </br>"
                                + "<br>Üdvözlettel: EASQAS program"+"</br>");
                        modosit.mindenes("Update qualitydb.Vevoireklamacio_alap set D3_ertesitve = 'igen' where id = '"+ rs.getString(1) +"'");
                        break;
                    }
                }                
            }            
            ///// lejárt D5 keresése
            sql = "SELECT ID, Felelos, Vevo, Tipus, Miaproblema FROM  qualitydb.Vevoireklamacio_alap where D5 is null and D5_ertesitve = 'nem' and ((DATEDIFF(now(), ertesites_datuma)) -\n"
                    + "        ((WEEK(now()) - WEEK(ertesites_datuma)) * 2) -\n"
                    + "        (case when weekday(now()) = 5 then 1 else 0 end) -\n"
                    + "        (case when weekday(ertesites_datuma) = 4 then 1 else 0 end)) >= 15";                                            
            stmt.execute(sql);    
            rs = stmt.getResultSet();
            while(rs.next())
            {
                String[] nevek = rs.getString(2).split(";");
                for(int szamlalo = 1; szamlalo < sheet.getLastRow(); szamlalo++)
                {
                    if(nevek[nevek.length-1].equals(sheet.getRange().get("B" + szamlalo).getText()))
                    {
                        String bathelye = "\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\bat fájlok\\indito"+ rs.getString(1) +".bat";
                        try
                        {
                            PrintWriter writer = new PrintWriter(bathelye, "UTF-8");
                            writer.print("java -jar \\\\172.20.22.7\\kozos\\Jar-ok\\EASQAS.jar Vevoi "+rs.getString(1));
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
                        cimzett = sheet.getRange().get("C" + szamlalo).getText()+",reznyak.norbert@veas.videoton.hu";       //+",reznyak.norbert@veas.videoton.hu"
                        email.linkes_email("easqas@veas.videoton.hu", cimzett, "", "Lejárt D5", "<br>Tisztelt "+nevek[nevek.length-1] +"! <br>"
                                + "<br>Lejárt a D5 határideje az alábbi reklamációnál: \nID: "+ rs.getString(1)+"</br>"
                                + "<br>Vevő: "+ rs.getString(3) +"</br>"
                                + "<br>Típus: "+ rs.getString(4) +"</br>" 
                                + "<br>Mi a probléma: "+ rs.getString(5) +"</br>"
                                + "<br>Kérem, minél előbb zárja le!" +"</br>"
                                + "<br>Link: <a href='"+ bathelye +"'>Reklamáció link</a> </br>"
                                + "<br>Üdvözlettel: EASQAS program"+"</br>");
                        modosit.mindenes("Update qualitydb.Vevoireklamacio_alap set D5_ertesitve = 'igen' where id = '"+ rs.getString(1) +"'");
                        break;
                    }
                }                
            }
            /// több mint 1 hete lejárt D5 keresése
            sql = "SELECT ID, Felelos, Vevo, Tipus, Miaproblema FROM  qualitydb.Vevoireklamacio_alap where D5 is null and D5_ujraertesitve = 'nem' and ((DATEDIFF(now(), ertesites_datuma)) -\n"
                    + "        ((WEEK(now()) - WEEK(ertesites_datuma)) * 2) -\n"
                    + "        (case when weekday(now()) = 5 then 1 else 0 end) -\n"
                    + "        (case when weekday(ertesites_datuma) = 4 then 1 else 0 end)) >= 21";                                            
            stmt.execute(sql);    
            rs = stmt.getResultSet();
            while(rs.next())
            {
                String[] nevek = rs.getString(2).split(";");
                for(int szamlalo = 1; szamlalo < sheet.getLastRow(); szamlalo++)
                {
                    if(nevek[nevek.length-1].equals(sheet.getRange().get("B" + szamlalo).getText()))
                    {
                        String bathelye = "\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\bat fájlok\\indito"+ rs.getString(1) +".bat";
                        try
                        {
                            PrintWriter writer = new PrintWriter(bathelye, "UTF-8");
                            writer.print("java -jar \\\\172.20.22.7\\kozos\\Jar-ok\\EASQAS.jar Vevoi "+rs.getString(1));
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
                        cimzett = sheet.getRange().get("C" + szamlalo).getText() +",reznyak.norbert@veas.videoton.hu,makk.aron@veas.videoton.hu";      //+",reznyak.norbert@veas.videoton.hu,makk.aron@veas.videoton.hu"
                        email.linkes_email("easqas@veas.videoton.hu", cimzett, "", "Lejárt D5", "<br>Tisztelt "+nevek[nevek.length-1] +"! <br>"
                                + "<br>Lejárt a D5 határideje az alábbi reklamációnál: \nID: "+ rs.getString(1)+"</br>"
                                + "<br>Vevő: "+ rs.getString(3) +"</br>"
                                + "<br>Típus: "+ rs.getString(4) +"</br>" 
                                + "<br>Mi a probléma: "+ rs.getString(5) +"</br>"
                                + "<br>Kérem, minél előbb zárja le!" +"</br>"
                                + "<br>Link: <a href='"+ bathelye +"'>Reklamáció link</a> </br>"
                                + "<br>Üdvözlettel: EASQAS program"+"</br>");
                        modosit.mindenes("Update qualitydb.Vevoireklamacio_alap set D5_ujraertesitve = 'igen' where id = '"+ rs.getString(1) +"'");
                        break;
                    }
                }                
            }
            //// lejárt D8 keresése
            sql = "SELECT ID, Felelos, Vevo, Tipus, Miaproblema FROM  qualitydb.Vevoireklamacio_alap where D8 is null and D8_ertesitve = 'nem' and ((DATEDIFF(now(), ertesites_datuma)) -\n"
                    + "        ((WEEK(now()) - WEEK(ertesites_datuma)) * 2) -\n"
                    + "        (case when weekday(now()) = 5 then 1 else 0 end) -\n"
                    + "        (case when weekday(ertesites_datuma) = 4 then 1 else 0 end)) >= 31";                                            
            stmt.execute(sql);    
            rs = stmt.getResultSet();
            while(rs.next())
            {
                String[] nevek = rs.getString(2).split(";");
                for(int szamlalo = 1; szamlalo < sheet.getLastRow(); szamlalo++)
                {
                    if(nevek[nevek.length-1].equals(sheet.getRange().get("B" + szamlalo).getText()))
                    {
                        String bathelye = "\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\bat fájlok\\indito"+ rs.getString(1) +".bat";
                        try
                        {
                            PrintWriter writer = new PrintWriter(bathelye, "UTF-8");
                            writer.print("java -jar \\\\172.20.22.7\\kozos\\Jar-ok\\EASQAS.jar Vevoi "+rs.getString(1));
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
                        cimzett = sheet.getRange().get("C" + szamlalo).getText() +",reznyak.norbert@veas.videoton.hu,makk.aron@veas.videoton.hu";      //+",reznyak.norbert@veas.videoton.hu,makk.aron@veas.videoton.hu"
                        email.linkes_email("easqas@veas.videoton.hu", cimzett, "", "Lejárt D8", "<br>Tisztelt "+nevek[nevek.length-1] +"! <br>"
                                + "<br>Lejárt a D8 határideje az alábbi reklamációnál: \nID: "+ rs.getString(1)+"</br>"
                                + "<br>Vevő: "+ rs.getString(3) +"</br>"
                                + "<br>Típus: "+ rs.getString(4) +"</br>" 
                                + "<br>Mi a probléma: "+ rs.getString(5) +"</br>"
                                + "<br>Kérem, minél előbb zárja le!" +"</br>"
                                + "<br>Link: <a href='"+ bathelye +"'>Reklamáció link</a> </br>"
                                + "<br>Üdvözlettel: EASQAS program"+"</br>");
                        modosit.mindenes("Update qualitydb.Vevoireklamacio_alap set D8_ertesitve = 'igen' where id = '"+ rs.getString(1) +"'");
                        break;
                    }
                }                
            }
            /////Lejáró előfordulás keresése
            sql = "select * from qualitydb.Vevoireklamacio_elo where Lezaras_datuma =  '' and DATEDIFF(Hatarido,now()) <= 2 and DATEDIFF(Hatarido,now()) >= 0 and Ertesitve = 'nem'";                                            
            stmt.execute(sql);    
            rs = stmt.getResultSet();
            workbook.loadFromFile(emaillista3);
            sheet = workbook.getWorksheets().get(0);
            while(rs.next())
            {
                for(int szamlalo = 1; szamlalo < sheet.getLastRow()+1; szamlalo++)
                {
                    if(sheet.getRange().get("C" + szamlalo).getText().equals(rs.getString(4)))
                    {
                        sql = "select felelos, Vevo, tipus from qualitydb.Vevoireklamacio_alap where ID =  '"+ rs.getString(2) +"'";                                            
                        stmt2.execute(sql);    
                        rs2 = stmt2.getResultSet();
                        if(rs2.next());
                        String[] felelos = rs2.getString(1).split(";");                        
                        cimzett = sheet.getRange().get("I" + szamlalo).getText() ;      //+",reznyak.norbert@veas.videoton.hu,makk.aron@veas.videoton.hu"                        
                        for(int szamlalo2 = 1; szamlalo2 < sheet.getLastRow()+1; szamlalo2++)
                        {
                            if(sheet.getRange().get("A" + szamlalo2).getText().equals(felelos[felelos.length-1]))
                            {
                                cimzett += ","+sheet.getRange().get("I" + szamlalo2).getText();
                            }
                        }
                        String bathelye = "\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\bat fájlok\\indito"+ rs.getString(2) +".bat";
                        try
                        {
                            PrintWriter writer = new PrintWriter(bathelye, "UTF-8");
                            writer.print("java -jar \\\\172.20.22.7\\kozos\\Jar-ok\\EASQAS.jar Vevoi "+rs.getString(2));
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
                        email.linkes_email("easqas@veas.videoton.hu", cimzett, "", "Lejáró feladat", "<br>Tisztelt "+rs.getString(4) +"!</br> <br>2 napon belül lejár a feladat határideje az alábbi reklamációnál: </br>"
                                + "<br>ID: "+ rs.getString(2) +"</br>"
                                + "<br>Vevő: "+ rs2.getString(2) +"</br>"
                                + "<br>Típus: "+ rs2.getString(3) +"</br>"
                                + "<br>Feladat: "+ rs.getString(3) +"</br>"
                                + "<br>Határidő: "+ rs.getString(5) +"</br>"
                                +"<br>Kérem, minél előbb zárja le!"+"</br>"
                                + "<br>Link: <a href='"+ bathelye +"'>Reklamáció link</a> </br>"
                                + "<br>Üdvözlettel: EASQAS program"+"</br>");
                        modosit.mindenes("Update qualitydb.Vevoireklamacio_elo set Ertesitve = 'igen' where id = '"+ rs.getString(1) +"'");
                        break;
                    }
                }                
            }
            /////Lejárt előfordulás keresése
            sql = "select * from qualitydb.Vevoireklamacio_elo where Lezaras_datuma =  '' and DATEDIFF(Hatarido,now()) < 0 and Ertesitve2 = 'nem'";                                            
            stmt.execute(sql);    
            rs = stmt.getResultSet();
            workbook.loadFromFile(emaillista3);
            sheet = workbook.getWorksheets().get(0);
            while(rs.next())
            {
                for(int szamlalo = 1; szamlalo < sheet.getLastRow()+1; szamlalo++)
                {
                    if(sheet.getRange().get("C" + szamlalo).getText().equals(rs.getString(4)))
                    {
                        System.out.println("Ez az ID: " +rs.getString(2));
                        sql = "select felelos, Vevo, tipus from qualitydb.Vevoireklamacio_alap where ID =  '"+ rs.getString(2) +"'";                                            
                        stmt2.execute(sql);    
                        rs2 = stmt2.getResultSet();
                        if(rs2.next());
                        System.out.println("Ez van benne: " +rs2.getString(1));
                        String[] felelos = rs2.getString(1).split(";");                        
                        cimzett = sheet.getRange().get("I" + szamlalo).getText() ;      //+",reznyak.norbert@veas.videoton.hu,makk.aron@veas.videoton.hu"                        
                        for(int szamlalo2 = 1; szamlalo2 < sheet.getLastRow()+1; szamlalo2++)
                        {
                            if(sheet.getRange().get("C" + szamlalo2).getText().equals(felelos[felelos.length-1]))
                            {
                                cimzett += ","+sheet.getRange().get("I" + szamlalo2).getText();
                            }
                        }
                        String bathelye = "\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\bat fájlok\\indito"+ rs.getString(2) +".bat";
                        try
                        {
                            PrintWriter writer = new PrintWriter(bathelye, "UTF-8");
                            writer.print("java -jar \\\\172.20.22.7\\kozos\\Jar-ok\\EASQAS.jar Vevoi "+rs.getString(2));
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
                        email.linkes_email("easqas@veas.videoton.hu", cimzett, "", "Lejáró feladat", "<br>Tisztelt "+rs.getString(4) +"!</br> <br>Lejárt a feladat határideje az alábbi reklamációnál: </br>"
                                + "<br>ID: "+ rs.getString(2) +"</br>"
                                + "<br>Vevő: "+ rs2.getString(2) +"</br>"
                                + "<br>Típus: "+ rs2.getString(3) +"</br>"
                                + "<br>Feladat: "+ rs.getString(3) +"</br>"
                                + "<br>Határidő: "+ rs.getString(5) +"</br>"
                                +"<br>Kérem, minél előbb zárja le!"+"</br>"
                                + "<br>Link: <a href='"+ bathelye +"'>Reklamáció link</a> </br>"
                                + "<br>Üdvözlettel: EASQAS program"+"</br>");
                        modosit.mindenes("Update qualitydb.Vevoireklamacio_elo set Ertesitve2 = 'igen' where id = '"+ rs.getString(1) +"'");
                        break;
                    }
                }                
            }
            //lejáró detektálhatóságra keresés
            sql = "select * from qualitydb.Vevoireklamacio_det where Lezaras_datuma =  '' and DATEDIFF(Hatarido,now()) <= 2 and DATEDIFF(Hatarido,now()) >= 0 and Ertesitve = 'nem'";                                            
            stmt.execute(sql);    
            rs = stmt.getResultSet();
            workbook.loadFromFile(emaillista3);
            sheet = workbook.getWorksheets().get(0);
            while(rs.next())
            {
                for(int szamlalo = 1; szamlalo < sheet.getLastRow()+1; szamlalo++)
                {
                    if(sheet.getRange().get("C" + szamlalo).getText().equals(rs.getString(4)))
                    {
                        sql = "select felelos, Vevo, tipus from qualitydb.Vevoireklamacio_alap where ID =  '"+ rs.getString(2) +"'";                                            
                        stmt2.execute(sql);    
                        rs2 = stmt2.getResultSet();
                        if(rs2.next());
                        String[] felelos = rs2.getString(1).split(";");                        
                        cimzett = sheet.getRange().get("I" + szamlalo).getText() ;      //+",reznyak.norbert@veas.videoton.hu,makk.aron@veas.videoton.hu"                        
                        for(int szamlalo2 = 1; szamlalo2 < sheet.getLastRow()+1; szamlalo2++)
                        {
                            if(sheet.getRange().get("C" + szamlalo2).getText().equals(felelos[felelos.length-1]))
                            {
                                cimzett += ","+sheet.getRange().get("I" + szamlalo2).getText();
                            }
                        }
                        String bathelye = "\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\bat fájlok\\indito"+ rs.getString(2) +".bat";
                        try
                        {
                            PrintWriter writer = new PrintWriter(bathelye, "UTF-8");
                            writer.print("java -jar \\\\172.20.22.7\\kozos\\Jar-ok\\EASQAS.jar Vevoi "+rs.getString(2));
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
                        email.linkes_email("easqas@veas.videoton.hu", cimzett, "", "Lejáró feladat", "<br>Tisztelt "+rs.getString(4) +"!</br> <br>2 napon belül lejár a feladat határideje az alábbi reklamációnál: </br>"
                                + "<br>ID: "+ rs.getString(2) +"</br>"
                                + "<br>Vevő: "+ rs2.getString(2) +"</br>"
                                + "<br>Típus: "+ rs2.getString(3) +"</br>"
                                + "<br>Feladat: "+ rs.getString(3) +"</br>"
                                + "<br>Határidő: "+ rs.getString(5) +"</br>"
                                +"<br>Kérem, minél előbb zárja le!"+"</br>"
                                + "<br>Link: <a href='"+ bathelye +"'>Reklamáció link</a> </br>"
                                + "<br>Üdvözlettel: EASQAS program"+"</br>");
                        modosit.mindenes("Update qualitydb.Vevoireklamacio_det set Ertesitve = 'igen' where id = '"+ rs.getString(1) +"'");
                        break;
                    }
                }                
            }
            //lejárt detektálhatóságra keresés
            sql = "select * from qualitydb.Vevoireklamacio_det where Lezaras_datuma =  '' and DATEDIFF(Hatarido,now()) < 0 and Ertesitve2 = 'nem'";                                            
            stmt.execute(sql);    
            rs = stmt.getResultSet();
            workbook.loadFromFile(emaillista3);
            sheet = workbook.getWorksheets().get(0);
            while(rs.next())
            {
                for(int szamlalo = 1; szamlalo < sheet.getLastRow()+1; szamlalo++)
                {
                    if(sheet.getRange().get("C" + szamlalo).getText().equals(rs.getString(4)))
                    {
                        sql = "select felelos, Vevo, tipus from qualitydb.Vevoireklamacio_alap where ID =  '"+ rs.getString(2) +"'";                                            
                        stmt2.execute(sql);    
                        rs2 = stmt2.getResultSet();
                        if(rs2.next());
                        String[] felelos = rs2.getString(1).split(";");                        
                        cimzett = sheet.getRange().get("I" + szamlalo).getText() ;      //+",reznyak.norbert@veas.videoton.hu,makk.aron@veas.videoton.hu"                        
                        for(int szamlalo2 = 1; szamlalo2 < sheet.getLastRow()+1; szamlalo2++)
                        {
                            if(sheet.getRange().get("C" + szamlalo2).getText().equals(felelos[felelos.length-1]))
                            {
                                cimzett += ","+sheet.getRange().get("I" + szamlalo2).getText();
                            }
                        }
                        String bathelye = "\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\bat fájlok\\indito"+ rs.getString(2) +".bat";
                        try
                        {
                            PrintWriter writer = new PrintWriter(bathelye, "UTF-8");
                            writer.print("java -jar \\\\172.20.22.7\\kozos\\Jar-ok\\EASQAS.jar Vevoi "+rs.getString(2));
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
                        email.linkes_email("easqas@veas.videoton.hu", cimzett, "", "Lejáró feladat", "<br>Tisztelt "+rs.getString(4) +"!</br> <br>Lejárt a feladat határideje az alábbi reklamációnál: </br>"
                                + "<br>ID: "+ rs.getString(2) +"</br>"
                                + "<br>Vevő: "+ rs2.getString(2) +"</br>"
                                + "<br>Típus: "+ rs2.getString(3) +"</br>"
                                + "<br>Feladat: "+ rs.getString(3) +"</br>"
                                + "<br>Határidő: "+ rs.getString(5) +"</br>"
                                +"<br>Kérem, minél előbb zárja le!"+"</br>"
                                + "<br>Link: <a href='"+ bathelye +"'>Reklamáció link</a> </br>"
                                + "<br>Üdvözlettel: EASQAS program"+"</br>");
                        modosit.mindenes("Update qualitydb.Vevoireklamacio_det set Ertesitve2 = 'igen' where id = '"+ rs.getString(1) +"'");
                        break;
                    }
                }                
            }
    
            System.out.println("Lefutott az Email2 rész");
            //resultSet.close();
            stmt.close();
            conn.close();
        
        }        
        catch (Exception e) 
        {
           e.printStackTrace();
           String hibauzenet = e.toString();
           Email hibakuldes = new Email();
           hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", this.getClass().getSimpleName()+" "+ hibauzenet);
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
	/*
	public void retour_vissza(String id)
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
        conn = DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String sajat = "SELECT * FROM  qualitydb.Retour where ID = '"+ id +"'";         
        stmt.execute(sajat);
        resultSet = stmt.getResultSet();
        
        if(resultSet.next())
        {
            String[] datum = resultSet.getString(2).split(" ");
            String[] datum2 = new String[2];        //resultSet.getString(11).split(" ");
            String[] datum3 = new String[2];        //resultSet.getString(13).split(" ");
            String[] datum4 = new String[2];        //resultSet.getString(15).split(" ");
            String[] datum5 = new String[2];        //resultSet.getString(17).split(" ");
            if(resultSet.getString(11) != null)
            {
                datum2 =resultSet.getString(11).split(" ");
            }
            else
            {
                datum2[0] = "";
                datum2[1] = "";
            }
            if(resultSet.getString(13) != null)
            {
                datum3 =resultSet.getString(13).split(" ");
            }
            else
            {
                datum3[0] = "";
                datum3[1] = "";
            }
            if(resultSet.getString(15) != null)
            {
                datum4 =resultSet.getString(15).split(" ");
            }
            else
            {
                datum4[0] = "";
                datum4[1] = "";
            }
            if(resultSet.getString(17) != null)
            {
                datum5 =resultSet.getString(17).split(" ");
            }
            else
            {
                datum5[0] = "";
                datum5[1] = "";
            }
            Retour.datum_mezo.setText(datum[0]);
            Retour.projekt_box.setSelectedItem(resultSet.getString(3));
            Retour.cikk_box.addItem(resultSet.getString(4));
            Retour.cikk_box.setSelectedItem(resultSet.getString(4));            
            Retour.javagy_box.setSelectedItem(resultSet.getString(5));
            Retour.beerkezett_mezo.setText(resultSet.getString(6));
            Retour.elteres_mezo.setText(resultSet.getString(7));
            Retour.rma_mezo.setText(resultSet.getString(8));
            Retour.megjegyzes_mezo.setText(resultSet.getString(9));
            Retour.hova_mezo.setText(resultSet.getString(10));
            Retour.kiadas_mezo.setText(datum2[0]);
            Retour.felelos_mezo.setText(resultSet.getString(12));
            Retour.teszt_mezo.setText(datum3[0]);
            Retour.felelos2_mezo.setText(resultSet.getString(14));
            Retour.veg_mezo.setText(datum4[0]);
            Retour.felelos3_mezo.setText(resultSet.getString(16));
            Retour.raktarra_mezo.setText(datum5[0]);
            Retour.raktarradb_mezo.setText(resultSet.getString(18));
            Retour.selejt_mezo.setText(resultSet.getString(19));
            Retour.vevoirma_mezo.setText(resultSet.getString(20));
            
            
        }
       
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
	*/
	public void hitlista(String projekt, String cikk, String datumtol, String datumig, String poz)
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
        conn = DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String sajat = "SELECT * FROM  qualitydb.Gyartasi_adatok where Vevo = '"+ projekt +"' and VT_azon like '"+ cikk +"' and Datum >= '"+ datumtol +"' and Datum <= '"+ datumig +"' and Pozicio = '"+ poz +"' ";         
        stmt.execute(sajat);
        resultSet = stmt.getResultSet();
        
        Workbook workbook = new Workbook();
        JdbcAdapter jdbcAdapter = new JdbcAdapter();
        jdbcAdapter.fillDataTable(datatable, resultSet);

        //Get the first worksheet
        Worksheet sheet = workbook.getWorksheets().get(0);
        sheet.insertDataTable(datatable, true, 1, 1);
        sheet.getAutoFilters().setRange(sheet.getCellRange("A1:Z1"));
        sheet.getAllocatedRange().autoFitColumns();
        sheet.getAllocatedRange().autoFitRows();
        
        sheet.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
        
        UIManager.put("FileChooser.openButtonText","Mentés");
        JFileChooser mentes_helye = new JFileChooser();
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
	
	void po_kereses(String po, String datum)
    {
	    Connection con = null;
        Statement stmt = null;
        try
        {
            ResultSet result;
            JdbcAdapter jdbcAdapter;
            DataTable datatable;
            Workbook workbook;
            //Registering the Driver
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());                                                       //jdbc mysql driver meghÃ­vÃ¡sa
                
            //Getting the connection
            String mysqlUrl = "jdbc:mysql://192.168.5.145/";                                                                        //mysql szerver ipcÃ­mÃ©hez valÃ³ csatlakozÃ¡s
            con = DriverManager.getConnection(mysqlUrl, "quality", "Qua25!");                                           //a megadott ip-re csatlakozik a jelszÃ³ felhasznÃ¡lÃ³ nÃ©vvel
            System.out.println("Connection established......");
                      
            /*String sql = "select     videoton.fkov.azon, videoton.fkov.hely,videoton.fkovsor.nev, videoton.fkov.ido, videoton.fkov.panel, cast(videoton.fkov.alsor as char(5)) as Teszterszam,"
                    + "if(videoton.fkov.ok in ('-1', '1'), \"Rendben\", \"Hiba\") as eredmeny, "
                    + "videoton.fkov.kod2 as 'PO szám', videoton.fkov.szeriaszam, videoton.fkov.hibakod, videoton.fkov.torolt, "
                    + "videoton.fkov.tesztszam, videoton.fkov.poz, videoton.fkov.teljesszam, videoton.fkov.failtestnames, videoton.fkov.error,"
                    + "videoton.fkov.dolgozo \n"
                    + "from videoton.fkov \n"
                    + "inner join videoton.fkovsor on videoton.fkovsor.azon = videoton.fkov.hely \n"
                    + " where videoton.fkovsor.nev = 'Loxone FCT' and ido > '"+ datum +"' and kod2 = 'AT-"+ po + "-10000' ";           //nev = 'Loxone FCT' and ido > '2022.06.01' and
            */
            String sql = "select *"    
                    + "from videoton.fkov \n"
                    + " where videoton.fkov.hely = '26' and videoton.fkov.ido >= '"+ datum +"' and  videoton.fkov.kod2 = 'AT-"+ po + "-10000' ";
            Statement cstmt = con.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
                       
            cstmt.execute(sql);                                                                                                     //sql llekérdezés futtatása                    
            result = cstmt.getResultSet();                                                                                              //az sql lekÃ©rdezÃ©s tartalmÃ¡t odaadja egy result set vÃ¡ltozÃ³nak           
            datatable = new DataTable();
            
            workbook = new Workbook();
            workbook.setVersion(ExcelVersion.Version2016); 
            jdbcAdapter = new JdbcAdapter();         
            jdbcAdapter.fillDataTable(datatable, result);
         
            //Get the first worksheet
            Worksheet sheet = workbook.getWorksheets().get(0);
            sheet.insertDataTable(datatable, true, 1, 1);
            sheet.getAutoFilters().setRange(sheet.getCellRange("A1:P1"));
            sheet.getAllocatedRange().autoFitColumns();
            sheet.getAllocatedRange().autoFitRows();
                
            sheet.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // fÃ©lkÃ¶vÃ©r beÃ¡llÃ­tÃ¡s
             
            result.close();
            cstmt.close();
            con.close();
            workbook.setActiveSheetIndex(0);
            JFileChooser mentes_helye = new JFileChooser();
            mentes_helye.setCurrentDirectory(new java.io.File(System.getProperty("user.home") + "\\Desktop\\"));
            mentes_helye.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            mentes_helye.showOpenDialog(mentes_helye);
            File menteshelye = mentes_helye.getSelectedFile();
            workbook.saveToFile(menteshelye.getAbsolutePath(), ExcelVersion.Version2016);
            
            FileInputStream fileStream = new FileInputStream(menteshelye.getAbsolutePath());
            try (XSSFWorkbook workbook2 = new XSSFWorkbook(fileStream)) 
            {
                for(int i = workbook2.getNumberOfSheets()-1; i>0 ;i--)
                {    
                    workbook2.removeSheetAt(i); 
                }      
                FileOutputStream output = new FileOutputStream(menteshelye.getAbsolutePath());
                workbook2.write(output);
                output.close();
            }
            JOptionPane.showMessageDialog(null, "Mentés sikeres", "Infó", 1);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            String hibauzenet2 = e.toString();
            Email hibakuldes = new Email();
            hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", this.getClass().getSimpleName()+" "+ hibauzenet2);
            JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
        }
        finally                                                                     //finally rÃ©sz mindenkÃ©ppen lefut, hogy hiba esetÃ©n is lezÃ¡rja a kacsolatot
        {
            try 
            {
              if (stmt != null)
                 con.close();
            } 
            catch (SQLException se) {}
            try 
            {
              if (con != null)
                 con.close();
            } 
            catch (SQLException se) 
            {
              se.printStackTrace();
            }  
        }   
    }
	
	public void lekerdez_retour(String datumtol, String datumig, String id, String vevo, String rma)
    {
    
        String driverName = "com.mysql.cj.jdbc.Driver";                     //driver stringje
        String url = "jdbc:mysql://172.20.22.29";                           //adatbázis IP címe
        String userName = "veasquality";                                    //fehasználónév
        String password = "kg6T$kd14TWbs9&gd";                              //jelszó
        Statement stmt = null;
        try 
        {
            Class.forName(driverName);
            Connection connection = DriverManager.getConnection(url, userName, password);                           //csatlakozás a szerverhez
            stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "";       //"select * from qualitydb.Retour where ";
            if(id.equals(""))
            {
                if(rma.equals(""))
                {
                    if(vevo.equals("-"))
                    {
                        sql = "select * from qualitydb.Retour where Datum >= '"+ datumtol + "' and Datum <= '"+ datumig + "'";
                    }
                    else
                    {
                        sql = "select * from qualitydb.Retour where Datum >= '"+ datumtol + "' and Datum <= '"+ datumig + "' and Vevo = '"+ vevo +"'";
                    }
                }
                else
                {
                    sql = "select * from qualitydb.Retour where RMA = '"+ rma + "'";
                }
            }
            else
            {
                sql = "select * from qualitydb.Retour where id = '"+ id + "'";
            }
       
            stmt.execute(sql);
            //String sql = "select * from " + DB;
            ResultSet resultSet = stmt.getResultSet();
            
            Retour_lekerdez.table.setModel(buildTableModel(resultSet));

            resultSet.close();
            stmt.close();
            connection.close();
            
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            String hibauzenet2 = e.toString();
            Email hibakuldes = new Email();
            hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", this.getClass().getSimpleName()+" "+ hibauzenet2);
            JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
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
	
	public void retour_excel(String datumtol, String datumig, String id)
    {
    
        String driverName = "com.mysql.cj.jdbc.Driver";                     //driver stringje
        String url = "jdbc:mysql://172.20.22.29";                           //adatbázis IP címe
        String userName = "veasquality";                                    //fehasználónév
        String password = "kg6T$kd14TWbs9&gd";                              //jelszó
        Statement stmt = null;
        Statement stmt2 = null;
        DataTable datatable = new DataTable();
        ResultSet resultset2 = null;
        try 
        {
            Class.forName(driverName);
            Connection connection = DriverManager.getConnection(url, userName, password);                           //csatlakozás a szerverhez
            stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt2 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
            String sql = "select Vevo,\n"
                    + " cast(sum(Beerkezett) as decimal(15,0)),\n"
                    + " cast(sum(if(raktar_db is null or raktar_db = '', 0, raktar_db)) as decimal(15,0)),\n"
                    + " cast(sum(if(selejt is null or selejt = '', 0, selejt)) as decimal(15,0))  \n"
                    + " from qualitydb.Retour \n"
                    + " where Datum >= '"+ datumtol +"' and Datum <= '"+ datumig +"' and Vagy = 'Javítás' "
                            + "and not Vevo ='Formlabs' and not Vevo = 'Techem' and not Vevo = 'EBM' and not Vevo = 'Innogy' and not Vevo = 'Alstom France' \n"
                    + " group by Vevo";
           
            stmt.execute(sql);
            ResultSet resultSet = stmt.getResultSet();
            
            Workbook workbook = new Workbook();
            Worksheet sheet = workbook.getWorksheets().get(0);         
            sheet.setName("Diagrammok");           
            JdbcAdapter jdbcAdapter = new JdbcAdapter();
            
            /***************************************************első diagramm*************************************/
            
            jdbcAdapter.fillDataTable(datatable, resultSet);
            sheet.getRange().get("A" + 1).setText("Projekt");
            sheet.getRange().get("B" + 1).setText("Selejt");
            sheet.getRange().get("C" + 1).setText("Raktárra adva");
            sheet.getRange().get("D" + 1).setText("Beérkezett");
            
            int cella = 2;
            for (int szamlalo = 0; szamlalo < datatable.getRows().size(); szamlalo++) 
            {          
                sheet.getRange().get("A" + cella).setText(datatable.getRows().get(szamlalo).getString(0));
                sheet.getCellRange("B" + cella).setNumberValue(Integer.parseInt(datatable.getRows().get(szamlalo).getString(3)));
                sheet.getCellRange("C" + cella).setNumberValue(Integer.parseInt(datatable.getRows().get(szamlalo).getString(2)));
                sheet.getCellRange("D" + cella).setNumberValue(Integer.parseInt(datatable.getRows().get(szamlalo).getString(1)));                         
                cella++;
            }
            
            Chart chart = sheet.getCharts().add();
            chart.setDataRange(sheet.getCellRange("A1:D" +cella));
            chart.setSeriesDataFromRange(false);
            
            chart.setLeftColumn(1);
            chart.setTopRow(10);
            chart.setRightColumn(16);
            chart.setBottomRow(30);
            
            chart.setChartType(ExcelChartType.Column3DClustered);
            chart.setChartType(ExcelChartType.Column3D);
            
            chart.setChartTitle("Retour darabok");
            chart.getChartTitleArea().isBold(true);
            chart.getChartTitleArea().setSize(14);
            chart.getPrimaryCategoryAxis().setTitle("Projektek");
            chart.getPrimaryCategoryAxis().getFont().isBold(true);
            chart.getPrimaryCategoryAxis().getTitleArea().isBold(true);
            chart.getPrimaryValueAxis().setTitle("Összesen");
            chart.getPrimaryValueAxis().hasMajorGridLines(false);
            chart.getPrimaryValueAxis().setMinValue(0);
            chart.getPrimaryValueAxis().getTitleArea().isBold(true);
            chart.getPrimaryValueAxis().getTitleArea().setTextRotationAngle(90);
            
            ChartSeries series = chart.getSeries();
            for (int i = 0;i < series.size();i++)
            {
                ChartSerie cs = series.get(i);
                cs.getFormat().getOptions().isVaryColor(true);
                cs.getDataPoints().getDefaultDataPoint().getDataLabels().hasValue(true);           
            }
            
            chart.getLegend().setPosition(LegendPositionType.Top);
            
            /********************************************Második diagramm*************************************************/
            
            String sql2 = "select Vevo,\n"
                    + "sum(Beerkezett),\n"
                    + "AVG(if(Raktar_datum is null or Raktar_datum = '',DATEDIFF(now(), Datum),DATEDIFF(Raktar_datum, Datum))) AS 'Kulonbseg'       \n"
                    + "from qualitydb.Retour\n"
                    + "where 3 = 3 and Datum >= '"+ datumtol +"' and Datum <= '"+ datumig +"' and Vagy = 'Javítás' \n"
                            + "and not Vevo ='Formlabs' and not Vevo = 'Techem' and not Vevo = 'EBM' and not Vevo = 'Innogy' and not Vevo = 'Alstom France' \n"
                    + "group by Vevo";
            stmt2.execute(sql2);
            resultset2 = stmt2.getResultSet();
            sheet.getRange().get("G" + 1).setText("Projekt");
            sheet.getRange().get("H" + 1).setText("Sum beérkezett");
            sheet.getRange().get("I" + 1).setText("Üzemben átlag");
            
            int cella2 = 2;
            while(resultset2.next())
            {   
                sheet.getRange().get("G" + cella2).setText(resultset2.getString(1));
                sheet.getCellRange("H" + cella2).setNumberValue(Integer.parseInt(resultset2.getString(2)));
                String[] atlag = resultset2.getString(3).split("\\.");
                sheet.getCellRange("I" + cella2).setNumberValue(Integer.parseInt(atlag[0]));
                cella2++;
            }
            
            Chart chart5 = sheet.getCharts().add();
            chart5.setChartTitle("VEAS-ban eltöltött napok átlaga a retourok esetében \n"
                    + "");
            chart5.setDataRange(sheet.getCellRange("G1:I" + cella2));
            chart5.setSeriesDataFromRange(false);
            chart5.getPrimaryValueAxis().setTitle("Retour db szám");
            chart5.getSecondaryValueAxis().setTitle("Üzemben töltött átlag");
     
            //Set position of the chart
            chart5.setLeftColumn(1);
            chart5.setTopRow(31);
            chart5.setRightColumn(18);
            chart5.setBottomRow(51);
     
            //Apply different chart types to different data series
            ChartSerie cs5 = (ChartSerie)chart5.getSeries().get(0);
            cs5.setSerieType(ExcelChartType.ColumnStacked);
            ChartSerie cs6 = (ChartSerie)chart5.getSeries().get(1);
            cs6.setSerieType(ExcelChartType.LineMarkers);
     
            //Add a secondary Y axis to the chart
            cs6.setUsePrimaryAxis(false);
            
            String hova = System.getProperty("user.home") + "\\Desktop\\Retour adatok.xlsx";
            File fajl = new File(hova);
            //System.out.println(fajl.getAbsolutePath());
            workbook.saveToFile(fajl.getAbsolutePath(), ExcelVersion.Version2016);          
            
            FileInputStream fileStream = new FileInputStream(fajl.getAbsolutePath());
            try (XSSFWorkbook workbook2 = new XSSFWorkbook(fileStream)) 
            {
                for(int i = workbook2.getNumberOfSheets()-1; i > 0 ;i--)
                {    
                    workbook2.removeSheetAt(i); 
                }
                workbook2.setActiveSheet(0);
                FileOutputStream output = new FileOutputStream(fajl.getAbsolutePath());
                workbook2.write(output);
                output.close();
            }
            JOptionPane.showMessageDialog(null, "Mentve az asztalra Retour adatok.xlsx néven!", "Info", 1);
                      
            resultSet.close();
            stmt.close();
            connection.close();
            
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            String hibauzenet = e.toString();
            Email hibakuldes = new Email();
            hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", this.getClass().getSimpleName()+" "+ hibauzenet);
            JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
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
	
	public void lekerdez_ellenorok(String querry, String querry2, String querry3, String querry4, int valtozat)
    {
    
	    Connection conn = null;
        Statement stmt = null;
      
        try 
        {          
           Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);           
            if(valtozat == 1)
            {
                stmt.execute(querry);
                resultSet = stmt.getResultSet();
                
                //Gepes_ellenorok.table.setModel(buildTableModel(resultSet));
                while(resultSet.next())
                {
                    Gepes_ellenorok.modell.addRow(new Object[]{resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),
                            resultSet.getString(7),resultSet.getString(8),resultSet.getString(9),resultSet.getString(10), resultSet.getString(11), resultSet.getString(12),resultSet.getString(13),
                            resultSet.getString(14),resultSet.getString(15),resultSet.getString(16),resultSet.getString(17),resultSet.getString(18)});
                }
                
                stmt.execute(querry2);
                resultSet = stmt.getResultSet();
                Gepes_ellenorok.table_1.setModel(buildTableModel(resultSet));
                
                stmt.execute(querry3);
                resultSet = stmt.getResultSet();
                FileOutputStream fs=null;
                byte b[];
                Blob blob;
                int szam = 0;
                while(resultSet.next())
                {        
                    File f = new File(System.getProperty("user.home") + "\\Desktop\\"+ resultSet.getString(2));
                    fs = new FileOutputStream(f);
                    blob = resultSet.getBlob("Kep");
                    b = blob.getBytes(1, (int)blob.length());
                    fs.write(b);
                    fs.close();
                    szam++;
                }            
                stmt.execute(querry4);
                resultSet = stmt.getResultSet();
                Gepes_ellenorok.table_2.setModel(buildTableModel(resultSet));
                if(szam > 0)
                { 
                    JOptionPane.showMessageDialog(null, "Képek mentve az asztalra", "Info", 1);
                }
            }
            if(valtozat == 2)
            {
                stmt.execute(querry);
                resultSet = stmt.getResultSet();
                Workbook workbook = new Workbook();
                Worksheet sheet = workbook.getWorksheets().get(0);
                Worksheet sheet2 = workbook.getWorksheets().get(1);
                DataTable datatable = new DataTable();
                DataTable datatable2 = new DataTable();
                JdbcAdapter jdbcAdapter = new JdbcAdapter();
                jdbcAdapter.fillDataTable(datatable, resultSet);
                sheet.insertDataTable(datatable, true, 1, 1);
                
                stmt.execute(querry2);
                resultSet = stmt.getResultSet();
                jdbcAdapter.fillDataTable(datatable2, resultSet);
                sheet2.insertDataTable(datatable2, true, 1, 1);
                
                sheet.getAutoFilters().setRange(sheet.getCellRange("A1:Z1"));
                sheet.getAllocatedRange().autoFitColumns();
                sheet.getAllocatedRange().autoFitRows();                
                sheet.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                sheet2.getAutoFilters().setRange(sheet2.getCellRange("A1:Z1"));
                sheet2.getAllocatedRange().autoFitColumns();
                sheet2.getAllocatedRange().autoFitRows();                
                sheet2.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás                
                
                stmt.execute(querry3);
                resultSet = stmt.getResultSet();
                FileOutputStream fs=null;
                byte b[];
                Blob blob;
                int szam = 0;
                while(resultSet.next())
                {        
                    File f = new File(System.getProperty("user.home") + "\\Desktop\\"+ resultSet.getString(2));
                    fs = new FileOutputStream(f);
                    blob = resultSet.getBlob("Kep");
                    b = blob.getBytes(1, (int)blob.length());
                    fs.write(b);
                    fs.close();
                    szam++;
                }            
                
                if(szam > 0)
                { 
                    JOptionPane.showMessageDialog(null, "Képek mentve az asztalra", "Info", 1);
                }
                workbook.saveToFile(System.getProperty("user.home") + "\\Desktop\\Gépes folyamatellenőrzés.xlsx", ExcelVersion.Version2016);               
                
                FileInputStream fileStream = new FileInputStream(System.getProperty("user.home") + "\\Desktop\\Gépes folyamatellenőrzés.xlsx");
                try (XSSFWorkbook workbook2 = new XSSFWorkbook(fileStream)) 
                {
                    for(int i = workbook2.getNumberOfSheets()-1; i>1 ;i--)
                    {    
                        workbook2.removeSheetAt(i); 
                    }      
                    FileOutputStream output = new FileOutputStream(System.getProperty("user.home") + "\\Desktop\\Gépes folyamatellenőrzés.xlsx");
                    workbook2.write(output);
                    output.close();
                }
                JOptionPane.showMessageDialog(null, "Excel mentve az asztalra Gépes folyamatellenőrzés.xlsx néven", "Info", 1);
            }
            resultSet.close();
            stmt.close();
            conn.close();
            
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            String hibauzenet = e.toString();
            Email hibakuldes = new Email();
            hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", this.getClass().getSimpleName()+" "+ hibauzenet);
            JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
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
	
	public void lekerdez_ellenori_nevsor(String querry, int szam)
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
            conn = DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);           
            stmt.execute(querry);
            resultSet = stmt.getResultSet();
            
            if(szam == 1)
            {
                Ellenorok.table.setModel(buildTableModel(resultSet));    
            }
            else
            {
                Workbook workbook = new Workbook();
                JdbcAdapter jdbcAdapter = new JdbcAdapter();
                DataTable datatable = new DataTable();
                jdbcAdapter.fillDataTable(datatable, resultSet);
     
                //Get the first worksheet
                Worksheet sheet = workbook.getWorksheets().get(0);
                sheet.insertDataTable(datatable, true, 1, 1);
                sheet.getAutoFilters().setRange(sheet.getCellRange("A1:C1"));
                sheet.getAllocatedRange().autoFitColumns();
                sheet.getAllocatedRange().autoFitRows();
                
                sheet.getCellRange("A1:C1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                
                String hova = System.getProperty("user.home") + "\\Desktop\\Ellenőrök.xlsx";
                workbook.saveToFile(hova, ExcelVersion.Version2016);
                
                FileInputStream fileStream = new FileInputStream(hova);
                try (XSSFWorkbook workbook2 = new XSSFWorkbook(fileStream)) 
                {
                    for(int i = workbook2.getNumberOfSheets()-1; i>0 ;i--)
                    {    
                        workbook2.removeSheetAt(i); 
                    }      
                    FileOutputStream output = new FileOutputStream(hova);
                    workbook2.write(output);
                    output.close();
                }
                JOptionPane.showMessageDialog(null, "Kész! \n Mentve az asztalra Ellenőrök.xlsx néven!", "Info", 1); 
            }
            
               

            resultSet.close();
            stmt.close();
            conn.close();
            
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            String hibauzenet = e.toString();
            Email hibakuldes = new Email();
            hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", this.getClass().getSimpleName()+" "+ hibauzenet);
            JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
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
	
	public void lekerdez_szeriaszamok(String sql, String menteshelye)
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
        String sajat = sql;
        stmt.execute(sajat);
        ResultSet resultSet = stmt.getResultSet();
        
        Workbook workbook = new Workbook();
        JdbcAdapter jdbcAdapter = new JdbcAdapter();
        jdbcAdapter.fillDataTable(datatable, resultSet);

        //Get the first worksheet
        Worksheet sheet = workbook.getWorksheets().get(0);
        sheet.insertDataTable(datatable, true, 1, 1);
        sheet.getAutoFilters().setRange(sheet.getCellRange("A1:Z1"));
        sheet.getAllocatedRange().autoFitColumns();
        sheet.getAllocatedRange().autoFitRows();
        
        sheet.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
        
        workbook.saveToFile(menteshelye, ExcelVersion.Version2016);
        resultSet.close();
        stmt.close();
        conn.close();
        
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
        JOptionPane.showMessageDialog(null, "Mentve az asztalra Átvételi adatok.xlsx néven", "Info", 1);
        } 
        catch (SQLException e1) 
        {
           e1.printStackTrace();
           String hibauzenet = e1.toString();
           Email hibakuldes = new Email();
           hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", this.getClass().getSimpleName()+" "+ hibauzenet);
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
	
	public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException 
    {
        ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        Vector<Object> columnNames = new Vector<Object>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) 
        {
            columnNames.add(metaData.getColumnName(column));
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) 
        {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) 
            {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }

        return alapmodell =  new DefaultTableModel(data, columnNames);

    }
	
}