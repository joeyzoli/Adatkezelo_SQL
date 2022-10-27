import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

import com.spire.data.table.DataTable;
import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;


public class Db_iro 
{
	/*
	 * Adatbázis írása paraméterekkel. Ez az osztály az adatbázisba való írász szolgálja
	 * 
	 */
	
	void iro_gyartas(int szam, String vt, String datum, String muszak, String ellenor_neve, String hiba_helye, int felajanlott, int minta_nagysag, String pcb, String hibakod, String pozicio, int hibak_szama, String sor )
	{	
		String[] koztes = vt.split(" - ");											//bejövő Stringet darabolni kell
		String[] hibakod_koztes = hibakod.split("-");
		Connection conn = null;
	    Statement stmt = null;
	    try 
	    {
	       try 
	       {
	          Class.forName("com.mysql.cj.jdbc.Driver");								//Driver meghívása
	       } 
	       catch (Exception e) 
	       {
	          System.out.println(e);
	          String hibauzenet2 = e.toString();
		      JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
	       }
	       
	    conn = (Connection) DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");							//kapcsolat létrehozása
	    stmt = (Statement) conn.createStatement();																									//csatlakozás
	    String query1 = "INSERT INTO qualitydb.Gyartasi_adatok " + "VALUES ("+ szam +",'"+koztes[0] +"', '"+koztes[1]+"', '"+koztes[2]+"', '"+ koztes[3]+ "', '" + datum + "', '" + muszak +"','"+ellenor_neve
	    		+ "', '" + hiba_helye + "', " + felajanlott + ", " + minta_nagysag + ", '" + pcb + "', " + hibakod_koztes[0] + ", '" + hibakod_koztes[1] + "','"+ pozicio+"',"+hibak_szama +",'"+sor+"')";		//String ami magát az SQL utasítást tartalmazza
	    stmt.executeUpdate(query1);																													//sql utasítás végrehajtása
	    
	    } 
	    catch (SQLException e1) 													//kivétel esetén történik
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
	    finally 																	//finally rész mindenképpen lefut, hogy hiba esetén is lezárja a kacsolatot
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
	    System.out.println("Minden kész!");
	}
	
	void iro_muszaki(String tipus, String datum, String veas_nr, String avm_nr, String pcb_nr, String pcb_verzio, 
	        String vegso, String prod, String stencil, String fa10, String fa21, String fa22, String fa23, String u4, String u42, String pcn, String remark)
	{
	    Connection conn = null;
        Statement stmt = null;
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
           
        conn = (Connection) DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");                           //kapcsolat létrehozása
        stmt = (Statement) conn.createStatement();                                                                                                  //csatlakozás
        String query1 = "INSERT INTO qualitydb.Muszaki_adatok VALUES ('"+tipus +"','"+ datum +"','"+ veas_nr +"','"+ avm_nr +"','"+
                                                                                pcb_nr +"','"+ pcb_verzio +"','"+ vegso +"','"+ prod +"','"+ stencil +"','"+
                                                                                fa10 +"','"+ fa21 +"','"+ fa22 +"','"+ fa23 +"','"+ u4 +"','"+ u42 +"','"+ pcn +"','"+ remark + "')";      //String ami magát az SQL utasítást tartalmazza
        stmt.executeUpdate(query1);                                                                                                                 //sql utasítás végrehajtása
        
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
        System.out.println("Minden kész!");
	}
	
	void feltolt()
	{
	    Connection conn = null;
        Statement stmt = null;
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
           
        conn = DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");                           //kapcsolat létrehozása
        stmt = conn.createStatement();                                                                                                  //csatlakozás
        
        PreparedStatement statement = conn.prepareStatement("INSERT INTO qualitydb.Gyartasi_adatok (ID, VT_azon, Cikksz, Vevo, Vevoi_megnev, Datum, Muszak, Ellenor_neve, Hibagyujtes_helye,"
                + "Felajanlott, Minta_nagysag, PCB_sorszam, Hibakod, Hiba_megnevezes, Pozicio, Hibak_szam, Sor) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        
        Workbook workbook = new Workbook();
        workbook.loadFromFile("c:\\Users\\kovacs.zoltan\\Desktop\\Mappák\\Java projekt\\minden.xlsx");
        Worksheet sheet = workbook.getWorksheets().get(0);

        DataTable dataTable = sheet.exportDataTable();                                  //sheet.getAllocatedRange(), false, false
        
        System.out.println("A sorok száma: " + dataTable.getRows().size());
        System.out.println("Az oszlopok száma: " + dataTable.getColumns().size());
        
        for (int i = 0; i < dataTable.getRows().size(); i++) 
        {
            for (int j = 0; j < dataTable.getColumns().size(); j++) 
            {
                statement.setString(j + 1, dataTable.getRows().get(i).getString(j));
            }
            statement.executeUpdate();
        }
        
        statement.close();
        conn.close();
                                                                                                                  
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
        System.out.println("Minden kész!");
	}
	
	
}
