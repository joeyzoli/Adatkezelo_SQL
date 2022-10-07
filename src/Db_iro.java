import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

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
}
