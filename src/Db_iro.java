import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	
	void iro_gyartas(String vt, String datum, String muszak, String ellenor_neve, String hiba_helye, int felajanlott, int minta_nagysag, String pcb, String hibakod, String pozicio, int hibak_szama, String sor, String idopont )
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
	    String query1 = "INSERT INTO qualitydb.Gyartasi_adatok (VT_azon, Cikksz, Vevo, Vevoi_megnev, Datum, Muszak, Ellenor_neve, Hibagyujtes_helye, FElajanlott, Minta_nagysag, PCB_sorszam, Hibakod, Hiba_megnevezes, Pozicio, Hibak_szam, Sor, Rogzites_idopontja)" + 
	                    "VALUES ('" + koztes[0] +"', '"+koztes[1]+"', '"+koztes[2]+"', '"+ koztes[3]+ "', '" + datum + "', '" + muszak +"','"+ellenor_neve
	                    + "', '" + hiba_helye + "', " + felajanlott + ", " + minta_nagysag + ", '" + pcb + "', " + hibakod_koztes[0] + ", '" + hibakod_koztes[1] + "','"+ pozicio+"',"+hibak_szama +",'"+sor+"', '"+ idopont +"' )";		//String ami magát az SQL utasítást tartalmazza
	    stmt.executeUpdate(query1);																													//sql utasítás végrehajtása
	    //+ szam +",
	    } 
	    catch (SQLException e1) 													//kivétel esetén történik
	    {
	       e1.printStackTrace();
	       String hibauzenet = e1.toString();
           Email hibakuldes = new Email();
           hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
	       JOptionPane.showMessageDialog(null, hibauzenet + "\n \n A Mentés sikertelen!!", "Hiba üzenet", 2);
	    } 
	    catch (Exception e) 
	    {
	       e.printStackTrace();
	       String hibauzenet = e.toString();
           Email hibakuldes = new Email();
           hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
	       JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
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
	}
	
	void iro_vevoi_alap(String datum, String projekt, String tipus, String rek_vagy, int rek_db, String hibaleiras, String gyartas_idopontja, String rma, String hibaoka, String hibaokozoja, String hibaoka2, String hibaokozoja2 )
    {   
        String[] koztes = tipus.split(",");                                          //bejövő Stringet darabolni kell
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
        String query1 = "INSERT INTO qualitydb.Vevoireklamacio_alapadat (Datum, Projekt, Tipus, Valtozat, Megnev, Rek_vagy, Rek_db, Hibaleiras, Gyartas_idopontja, Kiadott_RMA, Hiba_oka, Hiba_okozoja, Hiba_oka2, Hiba_okozoja2)" + 
                        "VALUES ('" + datum +"', '"+projekt+"', '"+koztes[0]+"', '"+koztes[1]+"', '"+koztes[2]+"', '" + rek_vagy + "', '"+ rek_db + "', '" + hibaleiras + "', '" + gyartas_idopontja +"', '" + rma +  "',"
                         +  "  '" + hibaoka + "', '" + hibaokozoja + "', '" + hibaoka2 + "', '" + hibaokozoja2 + "')"; 
        stmt.executeUpdate(query1);                                                                                                                 //sql utasítás végrehajtása
        //+ szam +",
        } 
        catch (SQLException e1)                                                     //kivétel esetén történik
        {
           e1.printStackTrace();
           String hibauzenet = e1.toString();
           Email hibakuldes = new Email();
           hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
           JOptionPane.showMessageDialog(null, hibauzenet + "\n \n A Mentés sikertelen!!", "Hiba üzenet", 2);
        } 
        catch (Exception e) 
        {
           e.printStackTrace();
           String hibauzenet = e.toString();
           Email hibakuldes = new Email();
           hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
           JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
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
	
	void iro_vevoi_felelos(String datum, String tipus, String zarolt, int zarolt_db, int talalt_db, String muszaki, String termeles, String felelos, String hatarido)
    {   
        String[] koztes = tipus.split(",");                                          //bejövő Stringet darabolni kell
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
        String query1 = "INSERT INTO qualitydb.Vevoireklamacio_felelosok (Datum, Cikkszam, Zarolt, Zarolt_db, Talalt_db, Muszaki_doku, Termeles, Felelos, Hatarido)" + 
                        "VALUES ('" + datum +"', '"+ koztes[0]+"', '"+zarolt+"', '" + zarolt_db + "', '" + talalt_db +"', '" + muszaki + "', '" + termeles + "', '" + felelos + "', '" + hatarido +"')"; 
        stmt.executeUpdate(query1);                                                                                                                 //sql utasítás végrehajtása
        //+ szam +",
        } 
        catch (SQLException e1)                                                     //kivétel esetén történik
        {
           e1.printStackTrace();
           String hibauzenet = e1.toString();
           Email hibakuldes = new Email();
           hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
           JOptionPane.showMessageDialog(null, hibauzenet + "\n \n A Mentés sikertelen!!", "Hiba üzenet", 2);
        } 
        catch (Exception e) 
        {
           e.printStackTrace();
           String hibauzenet = e.toString();
           Email hibakuldes = new Email();
           hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
           JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
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
	
	void iro_vevoi_intezkedes(String datum, String tipus, String intezkedes, String felelos, String hatarido, String id)
    {   
        String[] koztes = tipus.split(",");                                          //bejövő Stringet darabolni kell
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
        String sql = "select * from qualitydb.Vevoireklamacio_detekt where ID = '" + id +"' and Felelos = '" + felelos + "' and Intezkedes = '" + intezkedes + "' and hatarido = '" + hatarido + "'";
        stmt.execute(sql);
        ResultSet rs = stmt.getResultSet();
        if(rs.next())
        {
            String query1 = "Update qualitydb.Vevoireklamacio_detekt set  Intezkedes = '"+intezkedes+"', Hatarido = '" + hatarido +"' where "
                    + "sorszam = '"+ rs.getString(10) +"'";
            stmt.executeUpdate(query1);
        }
        else
        {
            String query1 = "INSERT INTO qualitydb.Vevoireklamacio_detekt (Datum, Cikkszam, Intezkedes, Felelos, Hatarido)" + 
                            "VALUES ('" + datum +"', '"+ koztes[0]+"', '"+intezkedes+"', '" + felelos + "', '" + hatarido +"')"; 
            stmt.executeUpdate(query1);                                                                                                             //sql utasítás végrehajtása
        }
        //+ szam +",
        }         
        catch (Exception e) 
        {
           e.printStackTrace();
           String hibauzenet = e.toString();
           Email hibakuldes = new Email();
           hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
           JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
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
	
	void iro_vevoi_id(String datum, String cikkszam)
    {   
        Connection conn = null;
        Statement stmt = null;
        Statement stmt2 = null;
        Statement stmt3 = null;
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
        stmt2 = (Statement) conn.createStatement();  
        stmt3 = (Statement) conn.createStatement();   
        String[] koztes = cikkszam.split(",");
        String sql = "select id from qualitydb.Vevoireklamacio_alapadat where Datum = '" + datum + "' and Tipus = '" + koztes[0] + "'";
        stmt.execute(sql);
        ResultSet result = stmt.getResultSet();
        int id = 0;
        if(result.next())
        {
            id = Integer.parseInt(result.getString(1));
        }
        
        String query = "update qualitydb.Vevoireklamacio_felelosok set  ID = '"+ id +"' where Datum = '" + datum + "' and Cikkszam = '" + koztes[0] + "'";
        String query2 = "update qualitydb.Vevoireklamacio_detekt set  ID = '"+ id +"' where Datum = '" + datum + "' and Cikkszam = '" + koztes[0] + "'";
        stmt2.executeUpdate(query);                                                                                                                 //sql utasítás végrehajtása
        stmt3.executeUpdate(query2);
        Vevoi_reklmacio_bevitel.id_mezo.setText(String.valueOf(id));
        //+ szam +",
        } 
        catch (SQLException e1)                                                     //kivétel esetén történik
        {
           e1.printStackTrace();
           String hibauzenet = e1.toString();
           Email hibakuldes = new Email();
           hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
           JOptionPane.showMessageDialog(null, hibauzenet + "\n \n A Mentés sikertelen!!", "Hiba üzenet", 2);
        } 
        catch (Exception e) 
        {
           e.printStackTrace();
           String hibauzenet = e.toString();
           Email hibakuldes = new Email();
           hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
           JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
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
	
	void iro_vevoi_kep(String datum, String cikkszam, String kephelye, String kepneve)
    {   
        String[] koztes = cikkszam.split(",");                                          //bejövő Stringet darabolni kell
        Connection conn = null;
        PreparedStatement stmt = null;
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
           
        conn = (Connection) DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");                           //kapcsolat létrehozása                                                                                                          //csatlakozás
        
        File image = new File(kephelye);
        FileInputStream fis = new FileInputStream (image);
        String sql = "INSERT INTO qualitydb.Vevoireklamacio_kepek(Datum, Cikkszam, Kep, Kepneve) VALUES(?,?,?,?)";
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, datum);
        stmt.setString(2, koztes[0]);
        stmt.setBinaryStream (3, fis, (int) image.length());
        stmt.setString(4, kepneve);
        stmt.executeUpdate();                                                                                                                 //sql utasítás végrehajtása
        }         
        catch (Exception e) 
        {
           e.printStackTrace();
           String hibauzenet = e.toString();
           Email hibakuldes = new Email();
           hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
           JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
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
	
	void iro_vevoi_excel(String datum, String cikkszam, String kephelye, String kepneve)
    {   
        String[] koztes = cikkszam.split(",");                                          //bejövő Stringet darabolni kell
        Connection conn = null;
        PreparedStatement stmt = null;
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
                                                                                                          //csatlakozás
        
        File image = new File(kephelye);
        FileInputStream fis = new FileInputStream (image);
        String sql = "INSERT INTO qualitydb.Vevoireklamacio_excel(Datum, Cikkszam, Excel, Excelneve) VALUES(?,?,?,?)";
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, datum);
        stmt.setString(2, koztes[0]);
        stmt.setBinaryStream (3, fis, (int) image.length());
        stmt.setString(4, kepneve);
        stmt.executeUpdate();                                                                                                                 //sql utasítás végrehajtása
        } 
        catch (SQLException e1)                                                     //kivétel esetén történik
        {
           e1.printStackTrace();
           String hibauzenet = e1.toString();
           Email hibakuldes = new Email();
           hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
           JOptionPane.showMessageDialog(null, hibauzenet + "\n \n A Mentés sikertelen!!", "Hiba üzenet", 2);
        } 
        catch (Exception e) 
        {
           e.printStackTrace();
           String hibauzenet = e.toString();
           Email hibakuldes = new Email();
           hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
           JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
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
	
	void ujrair_alapadat(int id, String datum, String cikkszam, int talalt, String gyokerok, String gyokerokozo, String gyokerok2, String gyokerokozo2, String zarolt, int rekdb, String hibaleiras, int melyik,String rma)
    {         
        Connection conn = null;
        Statement stmt = null;
        Statement stmt2 = null;
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
        stmt2 = (Statement) conn.createStatement();
        if(melyik == 1)
        {
            String query2 = "update qualitydb.Vevoireklamacio_alapadat set  Tipus = '"+ cikkszam +"', Hiba_oka = '"+ gyokerok +"', Hiba_okozoja = '" + gyokerokozo +"', Kiadott_rma = '" + rma +
                    "', Hiba_oka2 = '"+ gyokerok2 +"', Hiba_okozoja2 = '" + gyokerokozo2 + "'"+ ", Rek_db = '"+ rekdb + "', Hibaleiras = '"+ hibaleiras +"'"
                    + " where ID = '" + id + "'";
            stmt2.executeUpdate(query2);
        }
        else
        {
            String query = "update qualitydb.Vevoireklamacio_felelosok set  Talalt_db = '"+ talalt +"' where Datum = '" + datum + "' and Cikkszam = '" + cikkszam + "' and Zarolt = '" + zarolt + "'";
            stmt.executeUpdate(query);
            
            String query2 = "update qualitydb.Vevoireklamacio_alapadat set  Hiba_oka = '"+ gyokerok +"', Hiba_okozoja = '" + gyokerokozo + "', Kiadott_rma = '" + rma +
                    "', Hiba_oka2 = '"+ gyokerok2 +"', Hiba_okozoja2 = '" + gyokerokozo2 + "'"+ ", Rek_db = '"+ rekdb + "', Hibaleiras = '"+ hibaleiras +"'"
                    + " where ID = '" + id + "'";
            stmt2.executeUpdate(query2);
        }
        
        } 
        catch (SQLException e1)                                                     //kivétel esetén történik
        {
           e1.printStackTrace();
           String hibauzenet = e1.toString();
           Email hibakuldes = new Email();
           hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
           JOptionPane.showMessageDialog(null, hibauzenet + "\n \n A Mentés sikertelen!!", "Hiba üzenet", 2);
        } 
        catch (Exception e) 
        {
           e.printStackTrace();
           String hibauzenet = e.toString();
           Email hibakuldes = new Email();
           hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
           JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
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
	
	void iro_retour(String datum, String vevo, String tipus, String vagy, int beerkezett, int elteres, String rma, String megjegyzes, String vevoirma)
    {   
        String[] koztes = tipus.split(",");                                          //bejövő Stringet darabolni kell
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
        String query1 = "INSERT INTO qualitydb.Retour (Datum, Vevo, Tipus, Vagy, Beerkezett, Elteres, RMA, Megjegyzes,Vevoi_rma)" + 
                        "VALUES ('" + datum +"', '"+ vevo +"', '"+koztes[0]+"', '" + vagy + "', '" + beerkezett +"', '" + elteres +"', '" + rma +"', '" + megjegyzes +"'"
                                + ", '" + vevoirma +"')"; 
        stmt.executeUpdate(query1);                                                                                                                 //sql utasítás végrehajtása
        
        String sql = "select id from qualitydb.Retour where Datum = '" + datum + "' and Tipus = '" + koztes[0] + "'";
        stmt.execute(sql);
        ResultSet result = stmt.getResultSet();
        int id = 0;
        if(result.next())
        {
            id = Integer.parseInt(result.getString(1));
        }       
        Retour.id_mezo.setText(String.valueOf(id));
        
        } 
        catch (SQLException e1)                                                     //kivétel esetén történik
        {
           e1.printStackTrace();
           String hibauzenet = e1.toString();
           Email hibakuldes = new Email();
           hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
           JOptionPane.showMessageDialog(null, hibauzenet + "\n \n A Mentés sikertelen!!", "Hiba üzenet", 2);
        } 
        catch (Exception e) 
        {
           e.printStackTrace();
           String hibauzenet = e.toString();
           Email hibakuldes = new Email();
           hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
           JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
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
	
	void iro_retour_ido(String sql)
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
        
        
        } 
        catch (SQLException e1)                                                     //kivétel esetén történik
        {
           e1.printStackTrace();
           String hibauzenet = e1.toString();
           Email hibakuldes = new Email();
           hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
           JOptionPane.showMessageDialog(null, hibauzenet + "\n \n A Mentés sikertelen!!", "Hiba üzenet", 2);
        } 
        catch (Exception e) 
        {
           e.printStackTrace();
           String hibauzenet = e.toString();
           Email hibakuldes = new Email();
           hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
           JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
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
        
        PreparedStatement statement = conn.prepareStatement("INSERT INTO qualitydb.Gyartasi_adatok (VT_azon, Cikksz, Vevo, Vevoi_megnev, Datum, Muszak, Ellenor_neve, Hibagyujtes_helye,"
                + "Felajanlott, Minta_nagysag, PCB_sorszam, Hibakod, Hiba_megnevezes, Pozicio, Hibak_szam, Sor) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        
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
           String hibauzenet = e1.toString();
           Email hibakuldes = new Email();
           hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
           JOptionPane.showMessageDialog(null, hibauzenet + "\n \n A Mentés sikertelen!!", "Hiba üzenet", 2);
        } 
        catch (Exception e) 
        {
           e.printStackTrace();
           String hibauzenet = e.toString();
           Email hibakuldes = new Email();
           hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
           JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
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
	
	void ujrair(int id, String vt, String cikkszam, String vevo, String vevoi_megnev, String datum, String muszak, String ellenor_neve, String hiba_helye, int felajanlott, int minta_nagysag, String pcb, int hibakod, String hiba_megnev, String pozicio, int hibak_szama, String sor )
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
                                                                                                                    //sql utasítás végrehajtása
        String query = "update qualitydb.Gyartasi_adatok set  VT_azon = '"+ vt +"', Cikksz = '"+ cikkszam +"', Vevo = '"+ vevo +"', Vevoi_megnev = '"+ vevoi_megnev +"', "
                        + "Datum = '"+ datum +"', Muszak = '"+ muszak +"', Ellenor_neve = '"+ ellenor_neve +"', Hibagyujtes_helye = '"+ hiba_helye +"' "
                        + ", Felajanlott = '"+ felajanlott +"', Minta_nagysag = '"+ minta_nagysag +"', PCB_sorszam = '"+ pcb +"', hibakod = '"+ hibakod +"', Pozicio = '"+ pozicio +"' "
                                + ", Hibak_szam = '"+ hibak_szama +"', Sor = '"+ sor +"' where Id= '" + id + "'";
        stmt.executeUpdate(query);
        } 
        catch (SQLException e1)                                                     //kivétel esetén történik
        {
           e1.printStackTrace();
           String hibauzenet = e1.toString();
           Email hibakuldes = new Email();
           hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
           JOptionPane.showMessageDialog(null, hibauzenet + "\n \n A Mentés sikertelen!!", "Hiba üzenet", 2);
        } 
        catch (Exception e) 
        {
           e.printStackTrace();
           String hibauzenet = e.toString();
           Email hibakuldes = new Email();
           hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
           JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
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
	
	void ujrair_vevoi(String sql)
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
        
        String query = sql;
        stmt.executeUpdate(query);
        } 
        catch (SQLException e1)                                                     //kivétel esetén történik
        {
           e1.printStackTrace();
           String hibauzenet = e1.toString();
           Email hibakuldes = new Email();
           hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
           JOptionPane.showMessageDialog(null, hibauzenet + "\n \n A Mentés sikertelen!!", "Hiba üzenet", 2);
        } 
        catch (Exception e) 
        {
           e.printStackTrace();
           String hibauzenet = e.toString();
           Email hibakuldes = new Email();
           hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
           JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
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
	
	void atir(String mit, String mire )
    {   
        //String[] koztes = vt.split(" - ");                                          //bejövő Stringet darabolni kell
        //String[] hibakod_koztes = hibakod.split("-");
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
                                                                                                                 
        String query = "update qualitydb.Retour set  Vevo = '" + mire + "' where Vevo = '" + mit + "'";
        stmt.executeUpdate(query);
        } 
        catch (SQLException e1)                                                     //kivétel esetén történik
        {
           e1.printStackTrace();
           String hibauzenet = e1.toString();
           Email hibakuldes = new Email();
           hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
           JOptionPane.showMessageDialog(null, hibauzenet + "\n \n A Mentés sikertelen!!", "Hiba üzenet", 2);
        } 
        catch (Exception e) 
        {
           e.printStackTrace();
           String hibauzenet = e.toString();
           Email hibakuldes = new Email();
           hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
           JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
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
	
	void ellenori_nevek(String sql)
    {   
        //String[] koztes = vt.split(" - ");                                          //bejövő Stringet darabolni kell
        //String[] hibakod_koztes = hibakod.split("-");
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
                                                                                                                 
        stmt.executeUpdate(sql);
        } 
        catch (SQLException e1)                                                     //kivétel esetén történik
        {
           e1.printStackTrace();
           String hibauzenet = e1.toString();
           Email hibakuldes = new Email();
           hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
           JOptionPane.showMessageDialog(null, hibauzenet + "\n \n A Mentés sikertelen!!", "Hiba üzenet", 2);
        } 
        catch (Exception e) 
        {
           e.printStackTrace();
           String hibauzenet = e.toString();
           Email hibakuldes = new Email();
           hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
           JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
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
}
