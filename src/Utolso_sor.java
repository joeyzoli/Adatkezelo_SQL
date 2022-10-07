import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Utolso_sor 
{
	/*
	 * Ezt az osztályt azért hoztam létre, hogy lekérdezze az adatbázis utolsó sorát.
	 * Erre azért van szükség, hogy tujuk melyik sorszám következik ,ne legyen ürtközés
	 */
	
	String eredmeny = null;;
	
	public String utolso(String DB)							//utolsó sor számát adja vissza. paraméterként az adatbázist várja, így bármelyik adatbázisra meghívható vele
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
	    conn = (Connection) DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");			//csatlakozás IP, jelszó felhasználó val
	    stmt = (Statement) conn.createStatement();
	    String utolso = "SELECT * FROM " + DB + " ORDER BY ID DESC LIMIT 1";														//Végrehajtandó SQL String
	    stmt.execute(utolso);																										//SQL utasítás végrehajtása
	    ResultSet result = stmt.getResultSet();																						//az sql lekérdezés tartalmát odaadja egy result set változónak
	    result.next();																												//a kapott eredmény halmaz elejére ugrás
	    
	    eredmeny = result.getString(1);																								//kapott eredmény első eleme
	    
	    } 
	    catch (SQLException e1) 										//kivétel esetén történik
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
	    System.out.println("Utolsó sor indexe lekérve!");
	    return eredmeny;
	}
}
