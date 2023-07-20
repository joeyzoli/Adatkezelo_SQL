import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.SwingConstants;

public class Teszt_bejelentkezes extends JPanel {
    
    /**
     * Create the panel.
     */
    
    private int bennevan = 0;
    private Object zar_1= new Object();
    
    public Teszt_bejelentkezes() {            
        ellenorzes();
        
        synchronized(zar_1)
        {
            try
            {
                zar_1.wait();           // Várakozunk a jelzésre
            }
            catch (InterruptedException e)
            {
                System.out.print(e);
            }
        }
        if(bennevan > 0)
        {
            new Belepes().belep();;
        }
        else
        {
            setLayout(null);
        
            JLabel felirat = new JLabel("Nincs jogosultságod megnézni ezt a menüpontot");
            felirat.setHorizontalAlignment(SwingConstants.CENTER);
            felirat.setFont(new Font("Tahoma", Font.BOLD, 16));
            felirat.setBounds(206, 176, 713, 61);
            add(felirat);
            
            setBackground(Foablak.hatter_szine);
        }
        
    }
    
    private void ellenorzes()
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
            String sajat = "select nev from qualitydb.Vizsga_hozzaferes where 3 = 3";
            stmt.execute(sajat);
            ResultSet resultSet = stmt.getResultSet();

            while(resultSet.next())
            {
                if(resultSet.getString(1).equals(System.getProperty("user.name")))
                {
                    bennevan++;
                    System.out.println(resultSet.getString(1));
                }
            }
            synchronized(zar_1)
            {
                zar_1.notify();     // Értesítjük a zar_1-t, hogy mehet
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
    }
    
    private class Belepes 
    {
        void belep()
        {
            try 
            {
                Teszt_lezaras lezaras = new Teszt_lezaras();
                JScrollPane ablak = new JScrollPane(lezaras);
                ablak.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                ablak.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                Foablak.frame.setContentPane(ablak);
                Foablak.frame.pack();
            } 
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
        }
            
         
    }
}
