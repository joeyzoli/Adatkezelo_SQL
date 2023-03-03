import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class SQL_teszt 
{
    static ArrayList<String> beirt = new ArrayList<String>();
    
    void iras(String sql, String nev, String datum)
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
        String query1 = sql;            //"UPDATE qualitydb.Retour set Hova = '" + hova +"', Hova_datum = '" + datum +"', Hova_felelos = '" + hova +"' where id = '" + id +"')"; 
        stmt.executeUpdate(query1);                                                                                                                 //sql utasítás végrehajtása
        if(nev.equals(""))
        {
            
        }
        else
        {
            String sql2 = "select id from qualitydb.Ellenori_vizsga where Nev = '"+ nev +"' and  Datum = '"+ datum +"'";
            stmt.execute(sql2);
            ResultSet result = stmt.getResultSet();
            if(result.next())
            {
                Teszt_kezdes.id = Integer.parseInt(result.getString(1));
            }
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
    
    public void beirva(int id)
    {
        Connection conn = null;
        Statement stmt = null;
        ResultSet result = null;
      
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
        String sajat = "SELECT * FROM  qualitydb.Ellenori_vizsga where ID = '"+ id +"' ";
        stmt.execute(sajat);
        result = stmt.getResultSet();
      
        if(result.next())
        {
            for(int szamlalo = 1; szamlalo < 52; szamlalo++)
            {
                beirt.add(result.getString(szamlalo));
            }           
        }

        //result.close();
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

}
