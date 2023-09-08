import java.awt.Cursor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import com.spire.data.table.DataTable;
import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;

public class SQA_SQL {
    
    private final String emaillista = "\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\SQA\\SQA email lista.xlsx";
    
    public String[] cikkszamok()
    {
        String[] cikkbox = null;
        try
        {            
            Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            Class.forName("oracle.jdbc.OracleDriver");  //.driver
                                
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@IFSORA.IFS.videoton.hu:1521/IFSPROD","ZKOVACS","ZKOVACS");                                      
            Statement stmt = con.createStatement();              
            ResultSet rs = stmt.executeQuery("select PART_NO , DESCRIPTION \r\n"
                    + "from ifsapp.INVENTORY_PART\r\n"
                    + "where 3 = 3 order by part_no asc");
            ArrayList<String> cikkszamok = new ArrayList<String>();
            while(rs.next())
            {
                cikkszamok.add(rs.getString(1)+ " "+ rs.getString(2));
            }
            cikkbox = cikkszamok.toArray(new String[cikkszamok.size()]);
            con.close();  
            Foablak.frame.setCursor(null);        
        }           
        catch(Exception e1)
        { 
            System.out.println(e1);
            e1.printStackTrace();
            String hibauzenet2 = e1.toString();
            JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);                                                 //kiírja a hibaüzenetet
        }
        return cikkbox;  
                           
     }
    
    public String beszallito(String sql)
    {
        String cikkbox = null;
        try
        {            
            Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            Class.forName("oracle.jdbc.OracleDriver");  //.driver
                                
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@IFSORA.IFS.videoton.hu:1521/IFSPROD","ZKOVACS","ZKOVACS");                                      
            Statement stmt = con.createStatement();              
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next())
            {
                cikkbox = rs.getString(1);
            }
            con.close();  
            Foablak.frame.setCursor(null);        
        }           
        catch(Exception e1)
        { 
            System.out.println(e1);
            e1.printStackTrace();
            String hibauzenet2 = e1.toString();
            JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);                                                 //kiírja a hibaüzenetet
        }
        return cikkbox;  
                           
     }
    
    public void mindenes(String SQL)
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
        conn = (Connection) DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
        stmt = (Statement) conn.createStatement();
        stmt.executeUpdate(SQL);
        
        stmt.close();
        conn.close();
                
        } 
        catch (SQLException e1) 
        {
            e1.printStackTrace();
            String hibauzenet2 = e1.toString();
            JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);                                                     //kivétel esetén kiírja a hibaüzenetet
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            String hibauzenet2 = e.toString();
            JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);                                                     //kivétel esetén kiírja a hibaüzenetet
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
               String hibauzenet2 = se.toString();
               JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);                                                     //kivétel esetén kiírja a hibaüzenetet
           }  
        }
        JOptionPane.showMessageDialog(null, "Kész", "Info", 1);
    }
    
    public void sqa_email()
    {
        Connection conn = null;
        Statement stmt = null;        
        DataTable datatable = new DataTable();        
        ResultSet resultSet;       
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
        String sql = "select id, inditotta,Statusz, Statusz_ido, DATEDIFF(now(), Statusz_ido) as 'Kulonbseg'\r\n"
                + "from qualitydb.SQA_reklamaciok\r\n"
                + "where 3 = 3";                                        
        stmt.execute(sql);      
        resultSet = stmt.getResultSet();
        ArrayList<String> cimzettek = new ArrayList<String>();
        while(resultSet.next())
        {
            if(resultSet.getString(5) != null)
            {
                if(resultSet.getInt(5) >=7)
                {
                    cimzettek.add(resultSet.getString(1)+";"+resultSet.getString(2)+";"+resultSet.getString(3)+";"+resultSet.getString(4));
                }
            }
        }
        if(cimzettek.size() >= 1 )
        {
            for(int szamlalo = 0; szamlalo < cimzettek.size();szamlalo++)
            {
                Workbook workbook = new Workbook();
                workbook.loadFromFile(emaillista);
                Worksheet sheet = workbook.getWorksheets().get(0);
                datatable = sheet.exportDataTable(sheet.getAllocatedRange(), false, false );
                Email emailkuldes = new Email();
                String[] adatok = cimzettek.get(szamlalo).split(";");
                String emailcim = "";
                for(int szamlalo2 = 0; szamlalo2 < datatable.getRows().size();szamlalo2++)
                {
                    if(datatable.getRows().get(szamlalo2).getString(0).equals(adatok[1]) )
                    {
                        emailcim = datatable.getRows().get(szamlalo2).getString(1);
                    }
                }
                if(emailcim.equals("")) {}
                else
                {
                    emailkuldes.sqa_emailkuldes(adatok[1],emailcim,emailcim,adatok[0],adatok[3],adatok[2]);
                }
            }
        }
        
        System.out.println("Lefutott az SQA Email rész");
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

}
