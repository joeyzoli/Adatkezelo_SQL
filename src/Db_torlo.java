import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Db_torlo 
{
	/*
	 * Adatbázis törlése, vagy egy magadott sor törlésére szolgáló osztály
	 * 
	 */
	
/******************************** Adatbázis törlő függvény ********************************/
	
	public void torlo(String DB)
	{
		Connection conn = null;
	    Statement stmt = null;
	    try 
	    {
	       try 
	       {
	          Class.forName("com.mysql.cj.jdbc.Driver");
	       } catch (Exception e) {
	          System.out.println(e);
	    }
	    conn = (Connection) DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");						//csatlakozás az SQL adatbázishoz
	    stmt = (Statement) conn.createStatement();
	    String torles = "DELETE FROM " + DB;																									//SQL utasítást tartalmazó String
	    stmt.executeUpdate(torles);																												//String végrehajtása
	    } 
	    catch (SQLException e1) 
	    {
	       e1.printStackTrace();
	       String hibauzenet2 = e1.toString();
	       JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
	    } 
	    catch (Exception e) 
	    {
	       e.printStackTrace();
	       String hibauzenet2 = e.toString();
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
	          String hibauzenet2 = se.toString();
		      JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
	       }  
	    }
	    JOptionPane.showMessageDialog(null, "Adatbázis törlése kész", "Info", 1);
	}
	
/************************************* Csak a megadott PCB sorát törli a függvény  **************************************/
	
	public void adat_torlo(String vt, String datum, String muszak, String hibahelye, int felajanlott, int hibakod)												//a függvény vár egy paramétert ami a törlendő PCB sorszámát tartalmazza
	{
		Connection conn = null;
	    Statement stmt = null;
	    try 
	    {
	       try 
	       {
	          Class.forName("com.mysql.jdbc.Driver");								//driver meghívása
	       } 
	       catch (Exception e) 
	       {
	          System.out.println(e);
	          String hibauzenet2 = e.toString();
		      JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
	    }
	    conn = (Connection) DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");					//kapcsolódás az SQL adatbázisban
	    stmt = (Statement) conn.createStatement();
	    String torles = "DELETE FROM qualitydb.Gyartasi_adatok where VT_azon = '" + vt +"'"
	    															+ "and datum = '" + datum +"'"
	    															+ "and muszak = '" + muszak +"'"
	    															+ "and Hibagyujtes_helye = '" + hibahelye +"'"
	    															+ "and Felajanlott = " + felajanlott; 
	    															//+ "and Hibakod = " + hibakod +"";
	    stmt.executeUpdate(torles);																											//SQL utasítás végrehajtása
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
	    //JOptionPane.showMessageDialog(null, "Törlés sikeres", "Info", 1);
	}  
}
	
	