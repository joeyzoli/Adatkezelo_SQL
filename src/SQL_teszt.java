import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.spire.data.table.DataTable;
import com.spire.data.table.common.JdbcAdapter;
import com.spire.xls.ExcelPicture;
import com.spire.xls.ExcelVersion;
import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;

public class SQL_teszt 
{
    static ArrayList<String> beirt = new ArrayList<String>();
    private String kerdesek = "\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\kérdések\\kérdések.xlsx";
    //private File kep = new File(System.getProperty("user.home") + "\\"+ 1 +".jpg");
    
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
        ResultSet result;
        
        beirt.clear();
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
      
        while(result.next())
        {
            for(int szamlalo = 1; szamlalo < 52; szamlalo++)
            {
                beirt.add(result.getString(szamlalo));
            }           
        }

        result.close();
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
    
    void iro_kep(int szam,String kephelye, int id)
    {          
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
            String sql = "update qualitydb.Ellenori_vizsga set  kep"+ szam +" = ? where id = ?";
            stmt = conn.prepareStatement(sql);       
            stmt.setBinaryStream (1, fis, (int) image.length());
            stmt.setInt(2,id); 
            stmt.executeUpdate();                                                                                                                 //sql utasítás végrehajtása
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
    
    public void lekerdez_mutat(String querry, String kapcsolo)
    {
    
        String driverName = "com.mysql.cj.jdbc.Driver";                     //driver stringje
        String url = "jdbc:mysql://172.20.22.29";                           //adatbázis IP címe
        String userName = "veasquality";                                    //fehasználónév
        String password = "kg6T$kd14TWbs9&gd";                              //jelszó
        Statement stmt = null;
        try 
        {
            Class.forName(driverName);
            Connection conn = DriverManager.getConnection(url, userName, password);                           //csatlakozás a szerverhez
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);           
            stmt.execute(querry);
            //String sql = "select * from " + DB;
            ResultSet resultSet = null;
            
            if(kapcsolo.equals("igen"))
            {
                resultSet = stmt.getResultSet();
                Teszt_lezaras.table.setModel(SQL.buildTableModel(resultSet));
                resultSet.close();
            }
            
            stmt.close();
            conn.close();
            
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
       
    public void eredmenyek_excel(String nev)
    {   
        Connection con = null;
        PreparedStatement ps = null;
        FileOutputStream fs=null;
        Statement stmt = null;       
        String url = "jdbc:mysql://172.20.22.29";                           //adatbázis IP címe
        String userName = "veasquality";                                    //fehasználónév
        String password = "kg6T$kd14TWbs9&gd";                              //jelszó
        DataTable datatable = new DataTable();
        DataTable datatable2 = new DataTable();
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
            ps= con.prepareStatement("SELECT Kep1, Kep2, Kep3, Kep4, Kep5, Kep6, Kep7, Kep8, Kep9, Kep10 FROM qualitydb.Ellenori_vizsga WHERE nev = '"+ nev +"'");        //Datum = '"+ datum +"' and Cikkszam = '"+ cikkszam +"'"
            ResultSet rset = ps.executeQuery();         
            byte b[];
            Blob blob;
            int szam = 1;
            if(rset.next())
            {
                for(int szamlalo = 0; szamlalo < 10; szamlalo++)
                {
                    
                    File f = new File(System.getProperty("user.home") + "\\"+ szam +".jpg");
                    fs = new FileOutputStream(f);
                    blob = rset.getBlob("Kep"+ szam);
                    b = blob.getBytes(1, (int)blob.length());
                    fs.write(b);
                    fs.close();
                    szam++;
                }    
            }
            
            Connection conn = DriverManager.getConnection(url, userName, password);                           //csatlakozás a szerverhez
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);    
            String sql = "select nev, datum, valtozat, valasz1, valasz2, valasz3, valasz4, valasz5, valasz6, valasz7, valasz8, valasz9, valasz10, valasz11, valasz12, valasz13, valasz14, valasz15, valasz16, valasz17, "
                    + "valasz18, valasz19, valasz20, valasz21, valasz22, valasz23, valasz24, valasz25, valasz26, valasz27, valasz28, valasz29, valasz30, valasz31, valasz32, valasz33, valasz34, valasz35, valasz36,"
                    + " valasz37, valasz38 "
                    + " from qualitydb.Ellenori_vizsga WHERE nev = '"+ nev +"'";
            stmt.execute(sql);
            ResultSet resultSet = stmt.getResultSet();
            Workbook workbook = new Workbook();
            JdbcAdapter jdbcAdapter = new JdbcAdapter();
            jdbcAdapter.fillDataTable(datatable, resultSet);
            
            Worksheet sheet = workbook.getWorksheets().get(0);        
            Workbook excel = new Workbook();
            excel.loadFromFile(kerdesek);                                                                                          //infot tartalamzó excel betöltése
            Worksheet sheet2 = excel.getWorksheets().get(Integer.parseInt(datatable.getRows().get(0).getString(2))); 
            datatable2 = sheet2.exportDataTable();
            
            sheet.getRange().get("A1").setText("Név:");
            sheet.getRange().get("A2").setText("Dátum:");
            sheet.getRange().get("A3").setText("Változat:");
            sheet.getRange().get("B1").setText(datatable.getRows().get(0).getString(0));
            sheet.getRange().get("B2").setText(datatable.getRows().get(0).getString(1));
            sheet.getRange().get("B3").setText(datatable.getRows().get(0).getString(2));
            
            int cella = 4;
            int cella2 = 5;
            int cella3 = 6;
            int szamlalo;
            int szamlalo2 = 0;
            for(szamlalo = 3; szamlalo2 < 10; szamlalo++ )
            {
                sheet.getRange().get("A"+ cella).setText("Kérdés:");
                sheet.getRange().get("A"+ cella2).setText("Válasz:");
                sheet.getRange().get("A"+ cella3).setText("Pont:");
                sheet.getRange().get("B"+ cella).setText(datatable2.getRows().get(szamlalo2).getString(0));
                sheet.getRange().get("B"+ cella2).setText(datatable.getRows().get(0).getString(szamlalo));
                cella += 3;
                cella2 += 3;
                cella3 += 3;
                szamlalo2++;
            }
            sheet.getRange().get("A34").setText("Kérdés:");
            sheet.getRange().get("A35").setText("Válasz:");
            sheet.getRange().get("A36").setText("Válasz:");
            sheet.getRange().get("A37").setText("Pont:");
            sheet.getRange().get("B34").setText(datatable2.getRows().get(szamlalo2).getString(0));
            sheet.getRange().get("B35").setText(datatable.getRows().get(0).getString(szamlalo));
            szamlalo++;
            sheet.getRange().get("B36").setText(datatable.getRows().get(0).getString(szamlalo));
            sheet.getRange().get("A38").setText("Kérdés:");
            szamlalo++;
            szamlalo2 += 5;
            cella = 38;
            cella2 = 39;
            cella3 = 40;
            sheet.getRange().get("A"+ cella).setText("Kérdés:");
            sheet.getRange().get("A"+ cella2).setText("Válasz:");
            sheet.getRange().get("A"+ cella3).setText("Pont:");
            sheet.getRange().get("B"+ cella).setText(datatable2.getRows().get(szamlalo2).getString(0));
            sheet.getRange().get("B"+ cella2).setText(datatable.getRows().get(0).getString(szamlalo));
            cella += 3;
            cella2 += 3;
            cella3 += 3;
            szamlalo++;
            szamlalo2++;
            szamlalo2++;
            for(int a = 0; a < 10; a++)
            {
                if(a == 5)
                {
                    szamlalo2++;
                }
                sheet.getRange().get("A"+ cella).setText("Kérdés:");
                sheet.getRange().get("A"+ cella2).setText("Válasz:");
                sheet.getRange().get("A"+ cella3).setText("Pont:");
                sheet.getRange().get("B"+ cella).setText(datatable2.getRows().get(szamlalo2).getString(0));
                sheet.getRange().get("B"+ cella2).setText(datatable.getRows().get(0).getString(szamlalo));
                cella += 3;
                cella2 += 3;
                cella3 += 3;
                szamlalo++;
                szamlalo2++;
            }
            int sor = 73;
            int kepszam = 1;
            int valasz = 74;
            for(int a = 0; a < 10; a++)
            {
                sheet.getRange().get("A" + valasz).setText(datatable.getRows().get(0).getString(szamlalo));
                ExcelPicture picture = sheet.getPictures().add(sor,2,System.getProperty("user.home") + "\\"+ kepszam +".jpg");
                picture.setHeight(picture.getHeight()/3);
                picture.setWidth(picture.getWidth()/3);
                kepszam++;
                sor += 4;
                valasz += 4;
                szamlalo++;
            }           
            int vege = sheet.getLastRow() + 4;
            
            for(int a = 0; a < 5; a++)
            {
                sheet.getRange().get("A" + vege).setText("Kérdés:");
                sheet.getRange().get("A" + (vege+1)).setText("Válasz:");
                sheet.getRange().get("A" + (vege+2)).setText("Pont:");
                sheet.getRange().get("B" + vege).setText(datatable2.getRows().get(szamlalo2).getString(0));
                sheet.getRange().get("B" + (vege+1)).setText(datatable.getRows().get(0).getString(szamlalo));
                szamlalo++;
                szamlalo2++;
                vege += 3;
            }           
            
            sheet.getAllocatedRange().autoFitColumns();
            sheet.getAllocatedRange().autoFitRows();
            
            System.out.println(sheet.getLastRow());
            
            JFileChooser mentes_helye = new JFileChooser();
            mentes_helye.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            mentes_helye.showOpenDialog(mentes_helye);
            File fajl = mentes_helye.getSelectedFile();
            //System.out.println(fajl.getAbsolutePath());
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
            JOptionPane.showMessageDialog(null, "Mentés sikeres", "Info", 1);
            
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

}
