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
import java.sql.*;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
public class SQL 
{
    ResultSet resultSet;
    static DefaultTableModel alapmodell;
    
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
            sheet.getAutoFilters().setRange(sheet.getCellRange("A1:G1"));
            sheet.getAllocatedRange().autoFitColumns();
            sheet.getAllocatedRange().autoFitRows();
            
            sheet.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
            /*
            JFileChooser mentes_helye = new JFileChooser();
            mentes_helye.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            mentes_helye.showOpenDialog(mentes_helye);
            File fajl = mentes_helye.getSelectedFile();
            //System.out.println(fajl.getAbsolutePath());*/
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
        catch (SQLException e) 
        {
            e.printStackTrace();
            String hibauzenet2 = e.toString();
			JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
        } 
        catch (Exception e1) 
        {
            e1.printStackTrace();
            String hibauzenet2 = e1.toString();
			JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
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
        catch (SQLException e) 
        {
            e.printStackTrace();
            String hibauzenet2 = e.toString();
            JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
        } 
        catch (Exception e1) 
        {
            e1.printStackTrace();
            String hibauzenet2 = e1.toString();
            JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
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
        catch (SQLException e) 
        {
            e.printStackTrace();
            String hibauzenet2 = e.toString();
            JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
        } 
        catch (Exception e1) 
        {
            e1.printStackTrace();
            String hibauzenet2 = e1.toString();
            JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
        }
 
    }
	
	public void vevoi_napok(String querry, String datum, String cikkszam, String mikor, String mit)
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
            cstmt.setString(1, datum);
            cstmt.setString(2, cikkszam);
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
                    + "Datum = '"+ mikor +"' and Tipus = '"+ mit +"'";
                    
            visszair.ujrair_vevoi(sql);
           
            resultSet.close();
            statement.close();
            connection.close();          
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            String hibauzenet2 = e.toString();
            JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
        } 
        catch (Exception e1) 
        {
            e1.printStackTrace();
            String hibauzenet2 = e1.toString();
            JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
        }
 
    }
	
	public void sajat_sql(String SQL)
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
        sheet.getAutoFilters().setRange(sheet.getCellRange("A1:Z1"));
        sheet.getAllocatedRange().autoFitColumns();
        sheet.getAllocatedRange().autoFitRows();
        
        sheet.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
        
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
	    } 
	    catch (SQLException e1) 
	    {
	       e1.printStackTrace();
	    } 
	    catch (Exception e) 
	    {
	       e.printStackTrace();
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
	    JOptionPane.showMessageDialog(null, "Lekérdezés sikeres", "Info", 1);
	}
	
	
	public void muszaki_lekerdezo(String tipus)
    {
        Connection conn = null;
        Statement stmt = null;
        //DataTable datatable = new DataTable();
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
        String sajat = "SELECT * FROM  qualitydb.Muszaki_adatok where Tipus = '"+ tipus +"'";
        stmt.execute(sajat);
        resultSet = stmt.getResultSet();
        
        Muszaki_leker.eredmeny.setModel(buildTableModel(resultSet));
        System.out.print("elment");
        
       
        resultSet.close();
        stmt.close();
        conn.close();
        
        } 
        catch (SQLException e1) 
        {
           e1.printStackTrace();
        } 
        catch (Exception e) 
        {
           e.printStackTrace();
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
        JOptionPane.showMessageDialog(null, "Lekérdezés sikeres", "Info", 1);  
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
                        + "where VT_azon = '"+ tipus +"' and not Hibak_szam = '0' group by Pozicio order by Hibak_szam desc limit 7";  //group by Pozicio
        stmt.execute(sajat);
        resultSet = stmt.getResultSet();
        
        if(ProGlove.table_1 != null)
        {
            ProGlove.table_1.setModel(buildTableModel(resultSet));
        }
        else
        {
            Loxone.table_1.setModel(buildTableModel(resultSet));
        }

        resultSet.close();
        stmt.close();
        conn.close();
        
        } 
        catch (SQLException e1) 
        {
           e1.printStackTrace();
        } 
        catch (Exception e) 
        {
           e.printStackTrace();
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
	
	public void adat_modositashoz(String datum)
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
        String sajat = "SELECT * FROM  qualitydb.Gyartasi_adatok where Datum = '"+ datum +"' ";
        stmt.execute(sajat);
        resultSet = stmt.getResultSet();
        
        Adat_torles.table.setModel(buildTableModel(resultSet));

        resultSet.close();
        stmt.close();
        conn.close();
        
        } 
        catch (SQLException e1) 
        {
           e1.printStackTrace();
        } 
        catch (Exception e) 
        {
           e.printStackTrace();
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
	
	public void vevoi_lezarashoz(String datum, String cikkszam)
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
        String sajat = "SELECT * FROM  qualitydb.Vevoireklamacio_felelosok where Datum = '"+ datum +"' and Cikkszam = '"+ cikkszam +"' ";
        stmt.execute(sajat);
        resultSet = stmt.getResultSet();
        
        String sajat2 = "SELECT * FROM  qualitydb.Vevoireklamacio_detekt where Datum = '"+ datum +"' and Cikkszam = '"+ cikkszam +"' ";
        stmt2.execute(sajat2);
        resultSet2 = stmt2.getResultSet();
        
        Vevoi_reklamacio_lezaras.table.setModel(buildTableModel(resultSet));
        Vevoi_reklamacio_lezaras.table_1.setModel(buildTableModel(resultSet2));

        resultSet.close();
        stmt.close();
        conn.close();
        
        } 
        catch (SQLException e1) 
        {
           e1.printStackTrace();
        } 
        catch (Exception e) 
        {
           e.printStackTrace();
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
	
	public void vevoi_lekerdezes(String projekt, String datumtol, String datumig, String lezart, String nyitott)
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
        
        if(lezart.equals("igen") && nyitott.equals("igen"))
        {
            sql = "SELECT * FROM  qualitydb.Vevoireklamacio_alapadat where Projekt like '"+ projekt +"' and Datum >= '"+ datumtol 
                            +"' and Datum <= '"+ datumig +"'";
        }
        else if(lezart.equals("igen") && nyitott.equals("nem"))
        {
            sql = "SELECT * FROM  qualitydb.Vevoireklamacio_alapadat where Projekt like '"+ projekt +"' and Datum >= '"+ datumtol 
                            +"' and Datum <= '"+ datumig +"' and Lezaras_ido is not null  ";
        }
        else
        {
            sql = "SELECT * FROM  qualitydb.Vevoireklamacio_alapadat where Projekt like '"+ projekt +"' and Datum >= '"+ datumtol 
                    +"' and Datum <= '"+ datumig +"' and Lezaras_ido is null  ";
        }
        stmt.execute(sql);
        resultSet = stmt.getResultSet();

        Vevoi_reklamacio_lekerdezes.table.setModel(buildTableModel(resultSet));

        resultSet.close();
        stmt.close();
        conn.close();
        
        } 
        catch (SQLException e1) 
        {
           e1.printStackTrace();
        } 
        catch (Exception e) 
        {
           e.printStackTrace();
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
        String sql = "";

        if(projekt.equals("-"))
        {
            projekt = "%";
        }
        
        if(lezart.equals("igen") && nyitott.equals("igen"))
        {
            sql = "SELECT * FROM  qualitydb.Vevoireklamacio_alapadat where Projekt like '"+ projekt +"' and Datum >= '"+ datumtol 
                            +"' and Datum <= '"+ datumig +"'";
        }
        else if(lezart.equals("igen") && nyitott.equals("nem"))
        {
            sql = "SELECT DATE_FORMAT(Datum,'%Y%m'), projekt, rek_vagy, nyitva, Rek_db FROM  qualitydb.Vevoireklamacio_alapadat where Projekt like '"+ projekt +"' and Datum >= '"+ datumtol 
                            +"' and Datum <= '"+ datumig +"' and Lezaras_ido is not null";
        }
        else
        {
            sql = "SELECT * FROM  qualitydb.Vevoireklamacio_alapadat where Projekt like '"+ projekt +"' and Datum >= '"+ datumtol 
                    +"' and Datum <= '"+ datumig +"' and Lezaras_ido is null  ";
        }
        stmt.execute(sql);
        resultSet = stmt.getResultSet();

        Workbook workbook = new Workbook();
        JdbcAdapter jdbcAdapter = new JdbcAdapter();
        jdbcAdapter.fillDataTable(datatable, resultSet);

        //Get the first worksheet
        Worksheet sheet = workbook.getWorksheets().get(0);
        Worksheet sheet2 = workbook.getWorksheets().get(1);
        sheet2.insertDataTable(datatable, true, 1, 1);
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
        
        sheet.getRange().get("A" + 1).setText("Hónap");
        sheet.getRange().get("B" + 1).setText("Reklamáció");
        sheet.getRange().get("C" + 1).setText("Visszajelzés");
        
        for (int szamlalo = 0; szamlalo < datatable.getRows().size(); szamlalo++) 
        {
            if(datatable.getRows().get(szamlalo).getString(0).equals("202301"))
            {            
                if(datatable.getRows().get(szamlalo).getString(2).equals("Reklamáció"))
                {
                   reklamacio_jan++;                     
                }
                else
                {
                    visszajelzes_jan++;
                }
            }
            if(datatable.getRows().get(szamlalo).getString(0).equals("202302"))
            {            
                if(datatable.getRows().get(szamlalo).getString(2).equals("Reklamáció"))
                {
                   reklamacio_feb++;                     
                }
                else
                {
                    visszajelzes_feb++;
                }
            }
            if(datatable.getRows().get(szamlalo).getString(0).equals("202303"))
            {            
                if(datatable.getRows().get(szamlalo).getString(2).equals("Reklamáció"))
                {
                   reklamacio_mar++;                     
                }
                else
                {
                    visszajelzes_mar++;
                }
            }
            if(datatable.getRows().get(szamlalo).getString(0).equals("202304"))
            {            
                if(datatable.getRows().get(szamlalo).getString(2).equals("Reklamáció"))
                {
                   reklamacio_apr++;                     
                }
                else
                {
                    visszajelzes_apr++;
                }
            }
            if(datatable.getRows().get(szamlalo).getString(0).equals("202305"))
            {            
                if(datatable.getRows().get(szamlalo).getString(2).equals("Reklamáció"))
                {
                   reklamacio_maj++;                     
                }
                else
                {
                    visszajelzes_maj++;
                }
            }
            if(datatable.getRows().get(szamlalo).getString(0).equals("202306"))
            {            
                if(datatable.getRows().get(szamlalo).getString(2).equals("Reklamáció"))
                {
                   reklamacio_jun++;                     
                }
                else
                {
                    visszajelzes_jun++;
                }
            }
            if(datatable.getRows().get(szamlalo).getString(0).equals("202307"))
            {            
                if(datatable.getRows().get(szamlalo).getString(2).equals("Reklamáció"))
                {
                   reklamacio_jul++;                     
                }
                else
                {
                    visszajelzes_jul++;
                }
            }
            if(datatable.getRows().get(szamlalo).getString(0).equals("202308"))
            {            
                if(datatable.getRows().get(szamlalo).getString(2).equals("Reklamáció"))
                {
                   reklamacio_aug++;                     
                }
                else
                {
                    visszajelzes_aug++;
                }
            }
            if(datatable.getRows().get(szamlalo).getString(0).equals("202309"))
            {            
                if(datatable.getRows().get(szamlalo).getString(2).equals("Reklamáció"))
                {
                   reklamacio_sze++;                     
                }
                else
                {
                    visszajelzes_sze++;
                }
            }
            if(datatable.getRows().get(szamlalo).getString(0).equals("202310"))
            {            
                if(datatable.getRows().get(szamlalo).getString(2).equals("Reklamáció"))
                {
                   reklamacio_okt++;                     
                }
                else
                {
                    visszajelzes_okt++;
                }
            }
            if(datatable.getRows().get(szamlalo).getString(0).equals("202311"))
            {            
                if(datatable.getRows().get(szamlalo).getString(2).equals("Reklamáció"))
                {
                   reklamacio_nov++;                     
                }
                else
                {
                    visszajelzes_nov++;
                }
            }
            if(datatable.getRows().get(szamlalo).getString(0).equals("202312"))
            {            
                if(datatable.getRows().get(szamlalo).getString(2).equals("Reklamáció"))
                {
                   reklamacio_dec++;                     
                }
                else
                {
                    visszajelzes_dec++;
                }
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
        
        //createChartData(sheet);
        Chart chart = sheet.getCharts().add();
        chart.setDataRange(sheet.getCellRange("A1:C13"));
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
            for(int i = workbook2.getNumberOfSheets()-1; i > 1 ;i--)
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
        } 
        catch (Exception e) 
        {
           e.printStackTrace();
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
        catch (SQLException e1) 
        {
           e1.printStackTrace();
        } 
        catch (Exception e) 
        {
           e.printStackTrace();
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
	
	void po_kereses(String po)
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
                      
            String sql = "select     videoton.fkov.azon, videoton.fkov.hely,videoton.fkovsor.nev, videoton.fkov.ido, videoton.fkov.panel, cast(videoton.fkov.alsor as char(5)) as Teszterszam,"
                    + "if(videoton.fkov.ok in ('-1', '1'), \"Rendben\", \"Hiba\") as eredmeny, "
                    + "videoton.fkov.kod2 as 'PO szám', videoton.fkov.szeriaszam, videoton.fkov.hibakod, videoton.fkov.torolt, "
                    + "videoton.fkov.tesztszam, videoton.fkov.poz, videoton.fkov.teljesszam, videoton.fkov.failtestnames, videoton.fkov.error,"
                    + "videoton.fkov.dolgozo \n"
                    + "from videoton.fkov \n"
                    + "inner join videoton.fkovsor on videoton.fkovsor.azon = videoton.fkov.hely \n"
                    + " where hely = '26' and ido > '2022.06.01' and kod2 = 'AT-"+ po + "-10000' ";           //nev = 'Loxone FCT' and ido > '2022.06.01' and
                    
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