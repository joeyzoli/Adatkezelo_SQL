import com.spire.data.table.DataTable;
import com.spire.data.table.common.JdbcAdapter;
import com.spire.xls.ExcelVersion;
import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;

import java.io.File;
import java.sql.*;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
 
public class SQL 
{
	public void lekerdez_projekt(String querry, String datum_tol, String datum_ig, String hiba_helye)
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
            cstmt.execute();
            //String sql = "select * from " + DB;
            ResultSet resultSet = cstmt.getResultSet();
 
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
            statement.close();
            connection.close();
            JOptionPane.showMessageDialog(null, "Mentés sikeres", "Info", 1);
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
	          Class.forName("com.mysql.jdbc.Driver");
	       } 
	       catch (Exception e) 
	       {
	          System.out.println(e);
	          String hibauzenet2 = e.toString();
		      JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
	    }
	    conn = (Connection) DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
	    System.out.println("Csatlakozás sikeres...");
	    stmt = (Statement) conn.createStatement();
	    String sajat = SQL;
	    stmt.execute(sajat);
	    System.out.println("Lekérdezés sikeres..................");
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
        System.out.println("Csatlakozás sikeres...");
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String sajat = "SELECT * FROM  qualitydb.Muszaki_adatok where Tipus = '"+ tipus +"'";
        stmt.execute(sajat);
        System.out.println("Lekérdezés sikeres..................");
        ResultSet resultSet = stmt.getResultSet();
        
        int numColumns = resultSet.getMetaData().getColumnCount();
      
       
        while(resultSet.next())
        {      
            for ( int i = 1 ; i <= numColumns ; i++ ) 
            {
               Muszaki_leker.eredmeny.append(resultSet.getString(i) + " - ");          //resultSet.getString(i) + " "
            }
            Muszaki_leker.eredmeny.append("\n");
            
        }
        
       
        /*
        Workbook workbook = new Workbook();
        JdbcAdapter jdbcAdapter = new JdbcAdapter();
        jdbcAdapter.fillDataTable(datatable, resultSet);

        //Get the first worksheet
        Worksheet sheet = workbook.getWorksheets().get(0);
        sheet.insertDataTable(datatable, true, 1, 1);
        sheet.getAutoFilters().setRange(sheet.getCellRange("A1:Z1"));                                   //filter rakása az első sorra
        sheet.getAllocatedRange().autoFitColumns();                                                     //auto méretezés
        sheet.getAllocatedRange().autoFitRows();                                                        //auto méretezés celláknak
        
        sheet.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
       
        JFileChooser mentes_helye = new JFileChooser();
        mentes_helye.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        mentes_helye.showOpenDialog(mentes_helye);
        File fajl = mentes_helye.getSelectedFile();
        //System.out.println(fajl.getAbsolutePath());
        workbook.saveToFile(fajl.getAbsolutePath(), ExcelVersion.Version2016);
        */
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
	
}