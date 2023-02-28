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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
public class SQL 
{
    ResultSet resultSet;
    static DefaultTableModel alapmodell;
    private final String emaillista = "\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\E-mail lista_ reklamáció.xlsx";
    
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
        
        String sql2 = "SELECT zarolt, zarolt_db, talalt_db, felelos, hatarido FROM  qualitydb.Vevoireklamacio_felelosok "
                + "where Datum = '"+ datatable.getRows().get(0).getString(0) +"' and Cikkszam = '"+ datatable.getRows().get(0).getString(2) +"' "; 
        stmt2.execute(sql2);
        resultSet2 = stmt2.getResultSet();
        jdbcAdapter2.fillDataTable(datatable2, resultSet2);        
        int szamlalo2 = 1;            
        Vevoi_reklmacio_bevitel.modell.setRowCount(0);
        Vevoi_reklmacio_bevitel.modell2.setRowCount(0);
        for(int szamlalo = 0; szamlalo < datatable2.getRows().size(); szamlalo++)
        {
            Vevoi_reklmacio_bevitel.modell.addRow(new Object[]{datatable2.getRows().get(szamlalo).getString(3), datatable2.getRows().get(szamlalo).getString(4)});
            Vevoi_reklmacio_bevitel.modell2.addRow(new Object[]{szamlalo2, datatable2.getRows().get(szamlalo).getString(0), datatable2.getRows().get(szamlalo).getString(1), datatable2.getRows().get(szamlalo).getString(2)});
            szamlalo2++;
        }                       
        Vevoi_reklmacio_bevitel.felelos_tabla.setModel(Vevoi_reklmacio_bevitel.modell);
        Vevoi_reklmacio_bevitel.zarolt_tabla.setModel(Vevoi_reklmacio_bevitel.modell2);
        
        String sql3 = "SELECT hiba_oka, hiba_okozoja, hiba_oka2, hiba_okozoja2 FROM  qualitydb.Vevoireklamacio_alapadat "
                + "where Datum = '"+ datatable.getRows().get(0).getString(0) +"' and Tipus = '"+ datatable.getRows().get(0).getString(2) +"' "; 
        stmt3.execute(sql3);
        resultSet3 = stmt3.getResultSet();            
        jdbcAdapter3.fillDataTable(datatable3, resultSet3);
        String[] hibaoka = {datatable3.getRows().get(0).getString(1)};
        String[] hibaoka2 = {datatable3.getRows().get(0).getString(3)};
        DefaultComboBoxModel<String> hiba = new DefaultComboBoxModel<>(hibaoka);
        DefaultComboBoxModel<String> hiba2 = new DefaultComboBoxModel<>(hibaoka2);
        if(datatable3.getRows().get(0).getString(0).equals(""))
        {
            
        }
        else
        {
            Vevoi_reklmacio_bevitel.hibaokozoja_box.setModel(hiba);
            Vevoi_reklmacio_bevitel.hibaokozoja2_box.setModel(hiba2);
            Vevoi_reklmacio_bevitel.hibaoka_mezo.setText(datatable3.getRows().get(0).getString(0));
            Vevoi_reklmacio_bevitel.hibaoka2_mezo.setText(datatable3.getRows().get(0).getString(2));
            
        }
        
        
        
        resultSet.close();
        resultSet2.close();
        resultSet3.close();
        stmt.close();
        stmt2.close();
        stmt3.close();
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
        if(id.equals(""))
        {
            sajat = "SELECT * FROM  qualitydb.Vevoireklamacio_felelosok where Datum = '"+ datum +"' and Cikkszam = '"+ cikkszam +"' ";                
            sajat2 = "SELECT * FROM  qualitydb.Vevoireklamacio_detekt where Datum = '"+ datum +"' and Cikkszam = '"+ cikkszam +"' ";
        }
        else
        {
            sajat = "SELECT * FROM  qualitydb.Vevoireklamacio_felelosok where ID = '"+ id +"'";                
            sajat2 = "SELECT * FROM  qualitydb.Vevoireklamacio_detekt where ID = '"+ id +"'";
        }
            
        stmt.execute(sajat);
        resultSet = stmt.getResultSet();
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
        }
        else
        { 
            sql = "SELECT * FROM  qualitydb.Vevoireklamacio_alapadat where ID = '"+ id +"'"; 
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
        while(rset.next())
        {        
            File f = new File(System.getProperty("user.home") + "\\Desktop\\"+ rset.getString(2));
            fs = new FileOutputStream(f);
            blob = rset.getBlob("Kep");
            b = blob.getBytes(1, (int)blob.length());
            fs.write(b);
            fs.close();
        }                                                                                                                                                                                                                      
        } 
        catch (SQLException e1)                                                     //kivétel esetén történik
        {
           e1.printStackTrace();
           String hibauzenet2 = e1.toString();
           JOptionPane.showMessageDialog(null, hibauzenet2 + "\n \n A Mentés sikertelen!!", "Hiba üzenet", 2);
        } 
        catch (Exception e) 
        {
           e.printStackTrace();
           String hibauzenet2 = e.toString();
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
        while(rset.next())
        {
            File f = new File(System.getProperty("user.home") + "\\Desktop\\"+ rset.getString(2));
            fs = new FileOutputStream(f);
            blob = rset.getBlob("Excel");
            b = blob.getBytes(1, (int)blob.length());
            fs.write(b);
            fs.close();
        }                                                                                                                                                                                                                      
        } 
        catch (SQLException e1)                                                     //kivétel esetén történik
        {
           e1.printStackTrace();
           String hibauzenet2 = e1.toString();
           JOptionPane.showMessageDialog(null, hibauzenet2 + "\n \n A Mentés sikertelen!!", "Hiba üzenet", 2);
        } 
        catch (Exception e) 
        {
           e.printStackTrace();
           String hibauzenet2 = e.toString();
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
        Statement stmt2 = null;
        Statement stmt3 = null;
        Statement stmt4 = null;
        Statement stmt5 = null;
        Statement stmt6 = null;
        DataTable datatable2 = new DataTable();
        DataTable datatable3 = new DataTable();
        DataTable datatable4 = new DataTable();
        DataTable datatable6 = new DataTable();
        ResultSet resultSet2;
        ResultSet resultSet3;
        ResultSet resultSet4;
        ResultSet resultSet5;
        ResultSet resultSet6;
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
        String sql = "";
        String sql2 = "";
        String sql3 = "";
        String sql4 = "";
        String sql5 = "";
        String sql6 = "";

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
                + "where 3=3\n"
                + "group by projekt";
        
        sql3 = "select DATE_FORMAT(Datum,'%Y%m') as 'Hónap',\n"
                + "              Projekt, \n"
                + "              sum(if(rek_vagy in ('Reklamáció', 'Visszjelzés'), \"1\", \"0\")) as 'Reklamáció', \n"
                + "              sum(if(rek_vagy in ('Reklamáció', 'Visszjelzés'), \"0\", \"1\")) as 'Visszajelzés'\n"
                + "            from qualitydb.Vevoireklamacio_alapadat\n"
                + "                where 3=3\n"
                + "                group by Projekt, Hónap order by Hónap asc";
        
        sql4 = "SELECT * FROM  qualitydb.Vevoireklamacio_alapadat where Projekt like '"+ projekt +"' and Datum >= '"+ datumtol +
                                    "' and Datum <= '"+ datumig +"'";
        sql5 = "select DATE_FORMAT(Datum,'%Y%m') as 'Hónap',            \n"
                + "        sum(if(rek_vagy = 'Reklamáció' && Nyitva is null, 1,0)) as 'Nyitott reklamáció',\n"
                + "        sum(if(rek_vagy = 'Reklamáció' && Nyitva is not null, 1,0)) as 'Lezárt reklamáció',\n"
                + "        sum(if(rek_vagy = 'Visszajelzés' && Nyitva is null, 1,0)) as 'Nyitott visszajelzés',\n"
                + "        sum(if(rek_vagy = 'Visszajelzés' && Nyitva is not null, 1,0)) as 'Lezárt visszajelzés'\n"
                + "                from qualitydb.Vevoireklamacio_alapadat\n"
                + "                    where 3=3\n"
                + "                    group by Hónap order by Hónap asc";
        
        sql6 = "select DATE_FORMAT(Datum,'%Y%m') as 'Hónap',            \n"
                + "                cast(AVG(Nyitva) as decimal(3,0)) as 'Nyitva nap átlag'\n"
                + "                from qualitydb.Vevoireklamacio_alapadat\n"
                + "                    where 3=3\n"
                + "                    group by Hónap order by Hónap asc";
        
        stmt.execute(sql);
        stmt2.execute(sql2);
        stmt3.execute(sql3);
        stmt4.execute(sql4);
        stmt5.execute(sql5);
        stmt6.execute(sql6);
        resultSet = stmt.getResultSet();
        resultSet2 = stmt2.getResultSet();
        resultSet3 = stmt3.getResultSet();
        resultSet4 = stmt4.getResultSet();
        resultSet5 = stmt5.getResultSet();
        resultSet6 = stmt6.getResultSet();
        
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
        jdbcAdapter2.fillDataTable(datatable2, resultSet2);
        jdbcAdapter3.fillDataTable(datatable3, resultSet3);
        jdbcAdapter4.fillDataTable(datatable4, resultSet5);
        jdbcAdapter6.fillDataTable(datatable6, resultSet6);
        
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
        sheet2.getAutoFilters().setRange(sheet.getCellRange("A1:Q1"));
        sheet2.getAllocatedRange().autoFitColumns();
        sheet2.getAllocatedRange().autoFitRows();       
        sheet2.getCellRange("A1:Q1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
        
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
        
        sheet.getRange().get("A" + 1).setText("Hónap");
        sheet.getRange().get("B" + 1).setText("Reklamáció");
        sheet.getRange().get("C" + 1).setText("Visszajelzés");
        
        for (int szamlalo = 0; szamlalo < adatok.size(); szamlalo++) 
        {
            if(adatok.get(szamlalo)[0].equals("202301"))
            {            
                if(adatok.get(szamlalo)[2].equals("Reklamáció"))
                {
                   reklamacio_jan++;                     
                }
                else
                {
                    visszajelzes_jan++;
                }
            }
            if(adatok.get(szamlalo)[0].equals("202302"))
            {            
                if(adatok.get(szamlalo)[2].equals("Reklamáció"))
                {
                   reklamacio_feb++;                     
                }
                else
                {
                    visszajelzes_feb++;
                }
            }
            if(adatok.get(szamlalo)[0].equals("202303"))
            {            
                if(adatok.get(szamlalo)[2].equals("Reklamáció"))
                {
                   reklamacio_mar++;                     
                }
                else
                {
                    visszajelzes_mar++;
                }
            }
            if(adatok.get(szamlalo)[0].equals("202304"))
            {            
                if(adatok.get(szamlalo)[2].equals("Reklamáció"))
                {
                   reklamacio_apr++;                     
                }
                else
                {
                    visszajelzes_apr++;
                }
            }
            if(adatok.get(szamlalo)[0].equals("202305"))
            {            
                if(adatok.get(szamlalo)[2].equals("Reklamáció"))
                {
                   reklamacio_maj++;                     
                }
                else
                {
                    visszajelzes_maj++;
                }
            }
            if(adatok.get(szamlalo)[0].equals("202306"))
            {            
                if(adatok.get(szamlalo)[2].equals("Reklamáció"))
                {
                   reklamacio_jun++;                     
                }
                else
                {
                    visszajelzes_jun++;
                }
            }
            if(adatok.get(szamlalo)[0].equals("202307"))
            {            
                if(adatok.get(szamlalo)[2].equals("Reklamáció"))
                {
                   reklamacio_jul++;                     
                }
                else
                {
                    visszajelzes_jul++;
                }
            }
            if(adatok.get(szamlalo)[0].equals("202308"))
            {            
                if(adatok.get(szamlalo)[2].equals("Reklamáció"))
                {
                   reklamacio_aug++;                     
                }
                else
                {
                    visszajelzes_aug++;
                }
            }
            if(adatok.get(szamlalo)[0].equals("202309"))
            {            
                if(adatok.get(szamlalo)[2].equals("Reklamáció"))
                {
                   reklamacio_sze++;                     
                }
                else
                {
                    visszajelzes_sze++;
                }
            }
            if(adatok.get(szamlalo)[0].equals("202310"))
            {            
                if(adatok.get(szamlalo)[2].equals("Reklamáció"))
                {
                   reklamacio_okt++;                     
                }
                else
                {
                    visszajelzes_okt++;
                }
            }
            if(adatok.get(szamlalo)[0].equals("202311"))
            {            
                if(adatok.get(szamlalo)[2].equals("Reklamáció"))
                {
                   reklamacio_nov++;                     
                }
                else
                {
                    visszajelzes_nov++;
                }
            }
            if(adatok.get(szamlalo)[0].equals("202312"))
            {            
                if(adatok.get(szamlalo)[2].equals("Reklamáció"))
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
        
        int sumrek = reklamacio_jan + reklamacio_feb + reklamacio_mar + reklamacio_apr + reklamacio_maj + reklamacio_jun + reklamacio_jul + reklamacio_aug + reklamacio_sze + reklamacio_okt +
                reklamacio_nov + reklamacio_dec;        
        int sumvissza = visszajelzes_jan + visszajelzes_feb + visszajelzes_mar + visszajelzes_apr + visszajelzes_maj + visszajelzes_jun + visszajelzes_jul + visszajelzes_aug + visszajelzes_sze + visszajelzes_okt + 
                visszajelzes_nov + visszajelzes_dec;
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int month = localDate.getMonthValue();
        Float atlagrek = (float) (sumrek / month);
        Float atlagvissza = (float) (sumvissza/month);
        sheet.getCellRange("B" + 14).setNumberValue(atlagrek);
        sheet.getCellRange("C" + 15).setNumberValue(atlagvissza);
               
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
        
        sheet.getRange().get("R" + 2).setText("Január");
        sheet.getRange().get("R" + 3).setText("Február");
        sheet.getRange().get("R" + 4).setText("Március");
        sheet.getRange().get("R" + 5).setText("Április");
        sheet.getRange().get("R" + 6).setText("Május");
        sheet.getRange().get("R" + 7).setText("Június");
        sheet.getRange().get("R" + 8).setText("Július");
        sheet.getRange().get("R" + 9).setText("Augusztus");
        sheet.getRange().get("R" + 10).setText("Szeptember");
        sheet.getRange().get("R" + 11).setText("Október");
        sheet.getRange().get("R" + 12).setText("November");
        sheet.getRange().get("R" + 13).setText("December");
        sheet.getRange().get("R" + 14).setText("Átlag");
        
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
        for (int szamlalo = 0; szamlalo < datatable4.getRows().size(); szamlalo++) 
        {         
            sheet.getCellRange("W" + cella4).setNumberValue(Integer.parseInt(datatable6.getRows().get(szamlalo).getString(1)));           
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
        chart5.setDataRange(sheet.getCellRange("R1:W14"));
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
            for(int i = workbook2.getNumberOfSheets()-1; i > 1 ;i--)
            {    
                workbook2.removeSheetAt(i); 
            }
            workbook2.setActiveSheet(0);
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
	
	public void vevoi_email()
    {
        Connection conn = null;
        Statement stmt = null;
        Statement stmt2 = null;
        Statement stmt3 = null;
        //Statement stmt4 = null;
        DataTable datatable = new DataTable();
        DataTable datatable2 = new DataTable();
        //DataTable datatable3 = new DataTable();
        //DataTable datatable4 = new DataTable();
        ResultSet resultSet2;
        ResultSet resultSet3;
        //ResultSet resultSet4;
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
       
        String sql = "SELECT Felelos, Hatarido, ID, Datum, Cikkszam, Ertesitve, Zarolt FROM  qualitydb.Vevoireklamacio_felelosok where Lezarva is null";                
        String sql2 = "SELECT Felelos, Hatarido, ID, Datum, Cikkszam, Ertesitve, Intezkedes  FROM  qualitydb.Vevoireklamacio_detekt where Lezarva is null ";     
                    
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
        for(int szamlalo = 0; szamlalo < datatable.getRows().size(); szamlalo++)
        {
            Date hatarido = formatter.parse(datatable.getRows().get(szamlalo).getString(1));
            String hatarido2 = formatter.format(hatarido);
            cstmt.setString(1, hatarido2);
            cstmt.setString(2, maiido);
            cstmt.execute();
            resultSet3 = cstmt.getResultSet();           
            if(resultSet3.next())
            {              
                if(Integer.parseInt(resultSet3.getString(1)) <= 5)
                {
                    if(datatable.getRows().get(szamlalo).getString(5).equals("Nem"))
                    {
                        felelosok.add(datatable.getRows().get(szamlalo).getString(0));
                        adatok.add(datatable.getRows().get(szamlalo).getString(2)+ ","+ datatable.getRows().get(szamlalo).getString(3)+ ","+ datatable.getRows().get(szamlalo).getString(4)+ ","+datatable.getRows().get(szamlalo).getString(6));
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
                if(Integer.parseInt(resultSet3.getString(1)) <= 5)
                {
                    if(datatable2.getRows().get(szamlalo).getString(5).equals("Nem"))
                    {
                        felelosok.add(datatable2.getRows().get(szamlalo).getString(0));
                        adatok.add(datatable2.getRows().get(szamlalo).getString(2)+ ","+ datatable2.getRows().get(szamlalo).getString(3)+ ","+ datatable2.getRows().get(szamlalo).getString(4)+ ","+datatable2.getRows().get(szamlalo).getString(6));
                    }
                }
            }
        }
        
        for(int szamlalo = 0; szamlalo < felelosok.size(); szamlalo++)
        {          
            System.out.println(felelosok.get(szamlalo));
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
                        Email emailkuldes = new Email();
                        emailkuldes.emailkuldes(felelosexcel.getRows().get(szamlalo2).getString(0), felelosexcel.getRows().get(szamlalo2).getString(1), 
                               felelosexcel.getRows().get(szamlalo2).getString(1), felelosexcel.getRows().get(szamlalo2).getString(3), adat[0], adat[1], adat[2], adat[3]);
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
        System.out.println("Lefutott az Email rész");
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